package com.imooc.social.qq.connect;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

public class QQOAuth2Template extends OAuth2Template {

  public QQOAuth2Template(
      String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
    super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
    // 设置成true才会将clientId和clientSecret放到请求参数中
    setUseParametersForClientAuthentication(true);
  }

  /**
   * 自定义发送请求并解析
   * https://wiki.connect.qq.com/%E4%BD%BF%E7%94%A8authorization_code%E8%8E%B7%E5%8F%96access_token
   *
   * @param accessTokenUrl
   * @param parameters
   * @return
   */
  @Override
  protected AccessGrant postForAccessGrant(
      String accessTokenUrl, MultiValueMap<String, String> parameters) {
    /** 虽然qq互联上文档中获取accessToken要求发送GET请求， 但是结果并不如意，最终通过postForObject获得期望的结果 */
    String result = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);
    return createAccessGrant(result);
  }

  /**
   * 根据返回的结构创建AccessGrant 成功返回，即可在返回包中获取到Access Token。 如：
   * access_token=FE04************************CCE2&expires_in=7776000&refresh_token=88E4************************BE14
   *
   * @param responseResult
   * @return
   */
  private AccessGrant createAccessGrant(String responseResult) {
    String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(responseResult, "&");
    String accessToken = StringUtils.substringAfterLast(items[0], "=");
    String expiresIn = StringUtils.substringAfterLast(items[1], "=");
    String refreshToken = StringUtils.substringAfterLast(items[2], "=");
    return new AccessGrant(accessToken, StringUtils.EMPTY, refreshToken, Long.valueOf(expiresIn));
  }

  /**
   * 因为返回access_token的响应类型是text/html，所以需要添加额外的HttpMessageConverter
   *
   * @return
   */
  @Override
  protected RestTemplate createRestTemplate() {
    RestTemplate restTemplate = super.createRestTemplate();
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
    return restTemplate;
  }
}
