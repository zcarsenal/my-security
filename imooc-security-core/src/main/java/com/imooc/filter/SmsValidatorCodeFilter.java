package com.imooc.filter;

import com.imooc.core.properties.SecurityProperties;
import com.imooc.dto.ValidateCode;
import com.imooc.exception.ValidateCodeException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * OncePerRequestFilter 保证每个请求此过滤器只执行一次
 */
@Component
public class SmsValidatorCodeFilter extends OncePerRequestFilter {

  @Autowired private SecurityProperties securityProperties;

  /** Spring 自带的验证匹配器 */
  private AntPathMatcher antPathMatcher = new AntPathMatcher();

  @Autowired private AuthenticationFailureHandler imoocAuthenticationFailureHandler;

  private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

  private static final String SESSION_SMS_CODE = "IMAGE_CODE:SESSION_CODE_SMS";

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    for (String url : securityProperties.getCode().getSms().getUrls()) {
      if (antPathMatcher.match(url, request.getRequestURI())) {
        // 需要进行验证码校验, 如果失败直接返回
        try {
          validate(request, response);
        } catch (AuthenticationException ex) {
          imoocAuthenticationFailureHandler.onAuthenticationFailure(request, response, ex);
          return;
        }
      }
    }

    filterChain.doFilter(request, response);
  }

  private void validate(HttpServletRequest request, HttpServletResponse response) {
    String smsCode = request.getParameter("smsCode");
    if (StringUtils.isBlank(smsCode)) {
      throw new ValidateCodeException("请输入验证码");
    }
    ValidateCode validateCode =
        (ValidateCode)
            sessionStrategy.getAttribute(
                new ServletWebRequest(request, response), SESSION_SMS_CODE);
    if (validateCode == null) {
      throw new ValidateCodeException("验证码不存在");
    }

    if (validateCode.isExpired()) {
      sessionStrategy.removeAttribute(new ServletWebRequest(request, response), SESSION_SMS_CODE);
      throw new ValidateCodeException("验证码已过期");
    }

    if (!StringUtils.equalsIgnoreCase(smsCode, validateCode.getCode())) {
      throw new ValidateCodeException("验证码输入有误");
    }

    //验证通过删除验证码
    sessionStrategy.removeAttribute(new ServletWebRequest(request, response), SESSION_SMS_CODE);

  }
}
