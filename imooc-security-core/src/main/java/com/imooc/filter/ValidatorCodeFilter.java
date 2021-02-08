package com.imooc.filter;

import com.imooc.core.properties.SecurityProperties;
import com.imooc.dto.ImageCode;
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

@Component
public class ValidatorCodeFilter extends OncePerRequestFilter {

  @Autowired private SecurityProperties securityProperties;

  /** Spring 自带的验证匹配器 */
  private AntPathMatcher antPathMatcher = new AntPathMatcher();

  @Autowired private AuthenticationFailureHandler imoocAuthenticationFailureHandler;

  private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

  private static final String SESSION_IMAGE_CODE = "IMAGE_CODE:SESSION_IMAGE_CODE";

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    for (String url : securityProperties.getCode().getUrls()) {
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
    String image = request.getParameter("image");
    if (StringUtils.isBlank(image)) {
      throw new ValidateCodeException("请输入验证码");
    }
    ImageCode imageCode =
        (ImageCode)
            sessionStrategy.getAttribute(
                new ServletWebRequest(request, response), SESSION_IMAGE_CODE);
    if (imageCode == null) {
      throw new ValidateCodeException("验证码不存在");
    }

    if (imageCode.isExpired()) {
      sessionStrategy.removeAttribute(new ServletWebRequest(request, response), SESSION_IMAGE_CODE);
      throw new ValidateCodeException("验证码已过期");
    }

    if (!StringUtils.equalsIgnoreCase(image, imageCode.getCode())) {
      throw new ValidateCodeException("验证码输入有误");
    }
  }
}
