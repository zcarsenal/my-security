package com.imooc.controller;

import com.imooc.dto.ImageCode;
import com.imooc.validator.ValidatorCodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@Slf4j
public class BasicController {

  private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

  private static final String SESSION_IMAGE_CODE = "IMAGE_CODE:SESSION_IMAGE_CODE";

  @Autowired
  private ValidatorCodeGenerator validatorCodeGenerator;

  @GetMapping("/code/image")
  public void generateImageCode(HttpServletRequest request, HttpServletResponse response) {
    // 1. 生成ImageCode
    ImageCode imageCode = validatorCodeGenerator.generator(new ServletWebRequest(request,response));
    log.info("生成的code是:{}", imageCode);
    // 2. code保存到session
    saveImageCode(imageCode, request, response);
    // 3. 响应出去
    responseImageCode(imageCode.getImage(), response);
  }

  private void responseImageCode(BufferedImage image, HttpServletResponse response) {
    try {
      ImageIO.write(image, "PNG", response.getOutputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void saveImageCode(
      ImageCode code, HttpServletRequest request, HttpServletResponse response) {
    sessionStrategy.setAttribute(
        new ServletWebRequest(request, response), SESSION_IMAGE_CODE, code);
  }
}
