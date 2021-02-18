package com.imooc.social;

import com.imooc.core.properties.SecurityProperties;
import com.imooc.social.support.ImoocSpringSocialConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

  @Autowired private SecurityProperties securityProperties;

  @Autowired private DataSource dataSource;

  @Override
  public UsersConnectionRepository getUsersConnectionRepository(
      ConnectionFactoryLocator connectionFactoryLocator) {
    JdbcUsersConnectionRepository jdbcUsersConnectionRepository =
        new JdbcUsersConnectionRepository(
            dataSource, connectionFactoryLocator, Encryptors.noOpText());
    jdbcUsersConnectionRepository.setTablePrefix("imooc_");
    return jdbcUsersConnectionRepository;
  }

  /**
   * 社交登录配置类，供浏览器或app模块引入设计登录配置用。
   *
   * @return
   */
  @Bean
  public SpringSocialConfigurer imoocSpringSocialConfigurer() {
    ImoocSpringSocialConfigurer imoocSpringSocialConfigurer =
        new ImoocSpringSocialConfigurer(
            securityProperties.getSocial().getQq().getFilterProcessesUrl());
    return imoocSpringSocialConfigurer;
  }

  /**
   * 用来处理注册流程的工具类
   *
   * @param connectionFactoryLocator
   * @return
   */
  @Bean
  public ProviderSignInUtils providerSignInUtils(
      ConnectionFactoryLocator connectionFactoryLocator) {
    return new ProviderSignInUtils(
        connectionFactoryLocator, getUsersConnectionRepository(connectionFactoryLocator)) {};
  }
}
