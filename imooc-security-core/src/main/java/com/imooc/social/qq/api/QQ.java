package com.imooc.social.qq.api;

/**
 * 此类对应OAUTH2 服务提供者资源管理器下的流程图的API
 *
 *     social:
 *       filter-processes-url: /socialLogin
 *       qq:
 *         app-id: 101535674
 *         app-secret: c7e634902af400b133a8e1514c12c1e7
 *         provider-id: qq
 *
 */
public interface QQ {

    QQUserInfo getUserInfo();
}
