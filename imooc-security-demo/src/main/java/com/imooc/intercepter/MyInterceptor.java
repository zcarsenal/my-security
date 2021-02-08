package com.imooc.intercepter;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    System.out.println("preHandle");
    //System.out.println(((HandlerMethod) handler).getMethod().getName());
    //System.out.println(((HandlerMethod) handler).getBean().getClass().getName());
    request.setAttribute("startTime", System.currentTimeMillis());
    return true;
  }

  @Override
  public void postHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      ModelAndView modelAndView)
      throws Exception {
    System.out.println("preHandle");
    System.out.println(
        "耗时: " + (System.currentTimeMillis() - (long) request.getAttribute("startTime")));
  }

  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {

    System.out.println("afterCompletion");
    System.out.println(
        "耗时: " + (System.currentTimeMillis() - (long) request.getAttribute("startTime")));
    //    ex.printStackTrace();
//    System.out.println(ex.getMessage());
  }
}
