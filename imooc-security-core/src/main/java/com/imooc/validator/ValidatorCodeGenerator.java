package com.imooc.validator;

import com.imooc.dto.ImageCode;
import com.imooc.dto.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.ServletRequest;

public interface ValidatorCodeGenerator {

  ValidateCode generator(ServletWebRequest request);
}
