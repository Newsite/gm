package com.naixwf.social.weibo.connect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * Created with IntelliJ IDEA.
 * User: wangfei
 * Date: 13-4-24
 * Time: 下午3:53
 */

public class WeiboOAuth2Template extends OAuth2Template {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(WeiboOAuth2Template.class);

    public WeiboOAuth2Template(String clientId, String clientSecret) {
        super(clientId, clientSecret, "https://api.weibo.com/oauth2/authorize", "https://api.weibo.com/oauth2/access_token");
    }
}
