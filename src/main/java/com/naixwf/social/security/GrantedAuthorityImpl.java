package com.naixwf.social.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;

/**
 * Created with IntelliJ IDEA.
 * User: wangfei
 * Date: 13-4-27
 * Time: 下午5:16
 */

public class GrantedAuthorityImpl implements GrantedAuthority {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(GrantedAuthorityImpl.class);

    private final String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
