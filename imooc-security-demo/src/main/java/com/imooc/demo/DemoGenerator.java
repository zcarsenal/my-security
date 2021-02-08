package com.imooc.demo;

import com.imooc.dto.ImageCode;
import com.imooc.validator.ValidatorCodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

//@Component(value = "imageCodeGenerator")
@Slf4j
public class DemoGenerator implements ValidatorCodeGenerator {

    @Override
    public ImageCode generator(ServletWebRequest request) {
        log.info("自定义更复杂的验证码");
        return null;
    }
}
