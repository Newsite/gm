package com.naixwf.social.weibo.connect;

import com.naixwf.social.weibo.api.Weibo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.ApiException;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * Created with IntelliJ IDEA.
 * User: wangfei
 * Date: 13-4-24
 * Time: 下午3:49
 *
 */

public class WeiboAdapter implements ApiAdapter<Weibo> {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(WeiboAdapter.class);

    @Override
    public boolean test(Weibo weibo) {
        try {
            weibo.users().show();
            return true;
        } catch (ApiException e) {
            return false;
        }
    }

    @Override
    public void setConnectionValues(Weibo api, ConnectionValues values) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public UserProfile fetchUserProfile(Weibo api) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updateStatus(Weibo api, String message) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
