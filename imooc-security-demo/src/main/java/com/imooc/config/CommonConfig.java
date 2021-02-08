package com.imooc.config;

import com.imooc.filter.MyFlilter;
import com.imooc.intercepter.MyInterceptor;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class CommonConfig extends WebMvcConfigurerAdapter {

  @Autowired
  private MyInterceptor myInterceptor;

  @Bean
  public FilterRegistrationBean<MyFlilter> myFilter() {
    FilterRegistrationBean<MyFlilter> filterRegistrationBean = new FilterRegistrationBean<>();
    MyFlilter myFlilter = new MyFlilter();
    filterRegistrationBean.setFilter(myFlilter);
    List<String> urls = Lists.newArrayList();
    urls.add("/*");
    filterRegistrationBean.setUrlPatterns(urls);
    return filterRegistrationBean;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    super.addInterceptors(registry);
    registry.addInterceptor(myInterceptor);
  }

}
