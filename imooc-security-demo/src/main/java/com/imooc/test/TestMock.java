package com.imooc.test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class TestMock {

  public static void main(String[] args) {
    //
      WireMockConfiguration wireMockConfiguration = wireMockConfig();
      wireMockConfiguration.port(8062);
      WireMockServer wireMockServer = new WireMockServer(wireMockConfiguration);
      //WireMock.configureFor("wiremock.host", 8089);
      wireMockServer.start();
  }
}
