package com.naixwf.social.weibo.connect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * Created with IntelliJ IDEA.
 * User: wangfei
 * Date: 13-4-24
 * Time: 下午3:44
 */

public class WeiboConnectionFactory extends OAuth2ConnectionFactory {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(WeiboConnectionFactory.class);

    public WeiboConnectionFactory(String appKey, String appSecret) {
        super("weibo", new WeiboServiceProvider(appKey, appSecret), new WeiboAdapter());
    }
}
