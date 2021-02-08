package com.imooc.security.browser.authentication.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.core.properties.LoginType;
import com.imooc.core.properties.SecurityProperties;
import com.imooc.security.browser.supports.SimpleResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 认证失败处理器 */
@Component
@Slf4j
public class ImoocAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

  @Autowired private ObjectMapper objectMapper;

  @Autowired private SecurityProperties securityProperties;

  @Override
  public void onAuthenticationFailure(
      HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
      throws IOException, ServletException {
    log.info("认证失败");

    if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      response.setContentType("application/json;charset=UTF-8");
      response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));
      return;
    }
    super.onAuthenticationFailure(request, response, exception);
  }
}
