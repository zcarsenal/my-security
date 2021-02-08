package com.imooc.core.properties;

import lombok.Data;

@Data
public class BrowserProperties {

  private String loginUrl = "/my_login.html";

  private LoginType loginType = LoginType.JSON;

  private int rememberMeExpireIn = 60;

}
