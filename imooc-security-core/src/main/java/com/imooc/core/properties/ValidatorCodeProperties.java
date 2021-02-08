package com.imooc.core.properties;

import lombok.Data;

@Data
public class ValidatorCodeProperties {

    private ImageCodeProperties image = new ImageCodeProperties();

    private ValidateCodeProperties sms = new ValidateCodeProperties();
}
