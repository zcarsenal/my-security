package com.imooc.sync;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class OrderQueue {

  private String placeOrder;

  private String completeOrder;

  public String getPlaceOrder() {
    return placeOrder;
  }

  public void setPlaceOrder(String placeOrder) {
    new Thread(
            () -> {
              log.info("消息队列开始,{}", placeOrder);
              try {
                TimeUnit.SECONDS.sleep(1);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              this.completeOrder = placeOrder;
              log.info("消息队列结束,{}", placeOrder);
            })
        .start();
  }

  public String getCompleteOrder() {
    return completeOrder;
  }

  public void setCompleteOrder(String completeOrder) {
    this.completeOrder = completeOrder;
  }
}
