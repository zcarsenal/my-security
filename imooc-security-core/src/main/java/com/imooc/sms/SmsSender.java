package com.imooc.sms;

import com.imooc.dto.ValidateCode;
import org.springframework.web.bind.ServletRequestBindingException;

import javax.servlet.http.HttpServletRequest;

public interface SmsSender {

    void send(ValidateCode smsCode, HttpServletRequest request) throws ServletRequestBindingException;
}
