package com.imooc.core.properties;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ValidatorCodeProperties {

    /**
     * 需要发送验证码的url
     */
    private Set<String> urls = new HashSet<>();

    private ImageCodeProperties image = new ImageCodeProperties();

    private ValidateCodeProperties sms = new ValidateCodeProperties();
}
