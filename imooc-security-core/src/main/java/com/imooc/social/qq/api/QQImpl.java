package com.imooc.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/** 获取QQ用户信息 API */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

  // QQ互联接口获取 用户信息接口
  private static final String URL_GET_USER_INFO =
      "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

  // QQ获取用户open_id
  private static final String URL_GET_OPEN_ID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

  private String appId;

  private String openId;

  private ObjectMapper objectMapper = new ObjectMapper();

  /**
   * 此处覆盖父类的构造方法是为了修改 tokenStrategy为了解决URL传递参数
   *
   * @param accessToken
   */
  public QQImpl(String accessToken, String appId) {
    super(accessToken, TokenStrategy.OAUTH_TOKEN_PARAMETER);
    this.appId = appId;
    // 获取openId
    String url = String.format(URL_GET_OPEN_ID, accessToken);
    String ret = getRestTemplate().getForObject(url, String.class);
    try {
      QQOpenIdInfo qqOpenIdInfo = objectMapper.readValue(ret, QQOpenIdInfo.class);
      this.openId = qqOpenIdInfo.getOpenid();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public QQUserInfo getUserInfo() {
    String url = String.format(URL_GET_USER_INFO, appId, openId);
    String ret = getRestTemplate().getForObject(url, String.class);
    try {
      QQUserInfo qqUserInfo = objectMapper.readValue(ret, QQUserInfo.class);
      qqUserInfo.setOpenId(openId);
      return qqUserInfo;
    } catch (IOException e) {
      throw new RuntimeException("获取用户信息失败", e);
    }
  }
}
