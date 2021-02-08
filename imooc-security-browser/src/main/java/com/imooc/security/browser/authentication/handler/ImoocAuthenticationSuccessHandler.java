package com.imooc.security.browser.authentication.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.core.properties.LoginType;
import com.imooc.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 认证成功处理器 */
@Component
@Slf4j
public class ImoocAuthenticationSuccessHandler
    extends SavedRequestAwareAuthenticationSuccessHandler {

  @Autowired private ObjectMapper objectMapper;

  @Autowired private SecurityProperties securityProperties;

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {
    log.info("认证成功");

    if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
      response.setContentType("application/json;charset=UTF-8");
      response.getWriter().write(objectMapper.writeValueAsString(authentication));
      return;
    }
    super.onAuthenticationSuccess(request, response, authentication);
  }
}
