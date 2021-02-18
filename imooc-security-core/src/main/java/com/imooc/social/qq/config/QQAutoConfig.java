package com.imooc.social.qq.config;

import com.imooc.core.properties.SecurityProperties;
import com.imooc.social.qq.connect.QQConnectionFactory;
import com.imooc.social.support.SocialAutoConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

@Configuration
@ConditionalOnProperty(prefix = "imooc.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

  @Autowired private SecurityProperties securityProperties;

  protected ConnectionFactory<?> createConnectionFactory() {
    // 创建连接工厂,初始化参数
    return new QQConnectionFactory(
        securityProperties.getSocial().getQq().getProviderId(),
        securityProperties.getSocial().getQq().getAppId(),
        securityProperties.getSocial().getQq().getAppSecret());
  }
}
