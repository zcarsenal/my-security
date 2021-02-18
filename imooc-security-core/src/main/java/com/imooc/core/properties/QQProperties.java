package com.imooc.core.properties;

import lombok.Data;

@Data
public class QQProperties {
  private String filterProcessesUrl = "/auth";
  private String providerId;
  private String appId;
  private String appSecret;
}
