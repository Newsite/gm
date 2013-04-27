package com.naixwf.social.weibo.connect;

import com.naixwf.social.weibo.api.Weibo;
import com.naixwf.social.weibo.api.WeiboTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * Created with IntelliJ IDEA.
 * User: wangfei
 * Date: 13-4-24
 * Time: 下午3:55
 */

public class WeiboServiceProvider extends AbstractOAuth2ServiceProvider<Weibo> {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(WeiboServiceProvider.class);

    /**
     * Create a new {@link org.springframework.social.oauth2.OAuth2ServiceProvider}.
     */
    public WeiboServiceProvider(String appKey, String appSecret) {
        super(new WeiboOAuth2Template(appKey, appSecret));
    }

    @Override
    public Weibo getApi(String accessToken) {
        return new WeiboTemplate(accessToken);
    }
}
