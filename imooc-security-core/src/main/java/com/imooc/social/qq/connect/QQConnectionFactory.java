package com.imooc.social.qq.connect;

import com.imooc.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

  /** Create a {@link OAuth2ConnectionFactory}. */
  public QQConnectionFactory(String providerId, String appId, String appSecret) {
    super(providerId, new QQServiceProvider(appId, appSecret), new QQApiAdapter());
  }
}
