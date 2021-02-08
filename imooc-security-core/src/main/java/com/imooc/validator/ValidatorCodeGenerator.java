package com.imooc.validator;

import com.imooc.dto.ImageCode;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.ServletRequest;

public interface ValidatorCodeGenerator {

  ImageCode generator(ServletWebRequest request);
}
