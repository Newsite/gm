package com.naixwf.social.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: wangfei
 * Date: 13-4-27
 * Time: 下午4:44
 */

public class SecurityService {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);

    /**
     * 向security添加认证通过的用户
     *
     * @param userId
     */
    public void setAuthenticationByUserId(String userId) {
        Authentication authentication = new SocialAuthentication(userId);
        //TODO
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
