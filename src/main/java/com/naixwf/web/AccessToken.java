/*
 * @author wangfei
 */
package com.naixwf.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http://open.weibo.com/wiki/Oauth2/access_token
 * 
 * @author wangfei
 * @created 2013-3-10
 * 
 * @version 1.0
 */
public class AccessToken {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(AccessToken.class);
    private String access_token;
    private String expires_in;
    private String remind_in;
    private String uid;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getRemind_in() {
        return remind_in;
    }

    public void setRemind_in(String remind_in) {
        this.remind_in = remind_in;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

}
