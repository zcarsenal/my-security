package com.imooc.sms;

import com.imooc.dto.ValidateCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class DefaultSmsSender implements SmsSender {

  @Override
  public void send(ValidateCode smsCode, HttpServletRequest request) throws ServletRequestBindingException {
    // 此处调用第三方短信运营商接口
    String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");
    log.info("手机号:{},收到的验证码是:{}", mobile, smsCode);
  }
}
