package com.imooc.sync;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/sync")
@Slf4j
public class SyncController {

  @GetMapping("order")
  public Callable<?> order() {
    log.info("主线程开始");
    Callable<?> result =
        () -> {
          log.info("副线程开始");
          TimeUnit.SECONDS.sleep(1);
          log.info("副线程结束");
          return "order001";
        };
    log.info("主线程结束");
    return result;
  }

  @Autowired private OrderQueue orderQueue;

  @Autowired private DeferredResultHolder deferredResultHolder;

  @GetMapping("order1")
  public DeferredResult<String> order1() {
    log.info("主线程开始");
    final String orderNo = RandomStringUtils.randomNumeric(8);
    orderQueue.setPlaceOrder(orderNo);
    DeferredResult<String> result = new DeferredResult<>();
    deferredResultHolder.getMap().put(orderNo, result);
    result.onCompletion(() -> deferredResultHolder.getMap().remove(orderNo));
    log.info("主线程结束");
    return result;
  }
}
