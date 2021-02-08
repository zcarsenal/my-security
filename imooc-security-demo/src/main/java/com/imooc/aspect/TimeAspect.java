package com.imooc.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeAspect {

  @Pointcut("execution(* com.imooc.controller.SysUserController.*(..))")
  public void cutPoint() {}

  @Around(value = "cutPoint()")
  public Object handle(ProceedingJoinPoint handle) throws Throwable {
    System.out.println("aspect in");
    Object obj = handle.proceed();
    return obj;
  }
}
