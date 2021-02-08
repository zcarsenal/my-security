package com.imooc.security.browser;

import com.imooc.core.properties.SecurityProperties;
import com.imooc.security.browser.supports.SimpleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class BrowserSecurityController {

  @Autowired private SecurityProperties securityProperties;

  // spring security 会将请求信息存放在这个缓存里
  private RequestCache requestCache = new HttpSessionRequestCache();

  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

  @RequestMapping("authentication/require")
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public SimpleResponse authenticationRequire(
      HttpServletRequest request, HttpServletResponse response) throws IOException {
    SavedRequest savedRequest = requestCache.getRequest(request, response);
    if (savedRequest != null) {
      String redirectUrl = savedRequest.getRedirectUrl();
      if (StringUtils.endsWithIgnoreCase(redirectUrl, ".html")) {
        redirectStrategy.sendRedirect(
            request, response, securityProperties.getBrowser().getLoginUrl());
      }
    }

    return new SimpleResponse("请先授权后再进行操作");
  }
}
