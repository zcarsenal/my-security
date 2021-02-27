package com.imooc.social.qq.connect;

import com.imooc.social.qq.api.QQ;
import com.imooc.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

//认证服务器接口
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

  // 获取QQ授权码接口
  private static final String URL_GET_AUTHORIZE_URL = "https://graph.qq.com/oauth2.0/authorize";

  // 获取QQ access_token接口
  private static final String URL_GET_TOKEN = "https://graph.qq.com/oauth2.0/token";

  private String appId;

  public QQServiceProvider(String appId, String appSecret) {
    super(new QQOAuth2Template(appId, appSecret, URL_GET_AUTHORIZE_URL, URL_GET_TOKEN));
    this.appId = appId;
  }

  @Override
  public QQ getApi(String accessToken) {
    return new QQImpl(accessToken, appId);
  }
}
