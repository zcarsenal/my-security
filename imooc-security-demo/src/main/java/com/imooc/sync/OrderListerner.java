package com.imooc.sync;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class OrderListerner implements ApplicationListener<ContextRefreshedEvent> {

  @Autowired private OrderQueue orderQueue;

  @Autowired private DeferredResultHolder deferredResultHolder;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    new Thread(
            () -> {
              while (true) {
                String completeOrder = orderQueue.getCompleteOrder();
                if (StringUtils.isNotBlank(completeOrder)) {
                  log.info("返回订单处理结果{}", completeOrder);
                  deferredResultHolder.getMap().get(completeOrder).setResult("order complete");
                  orderQueue.setCompleteOrder(null);
                } else {
                  try {
                    TimeUnit.MILLISECONDS.sleep(200);
                  } catch (InterruptedException e) {
                    e.printStackTrace();
                  }
                }
              }
            })
        .start();
  }
}
