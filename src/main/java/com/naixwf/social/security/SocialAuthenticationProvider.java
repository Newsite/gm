package com.naixwf.social.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * Created with IntelliJ IDEA.
 * User: wangfei
 * Date: 13-4-27
 * Time: 下午5:13
 */

public class SocialAuthenticationProvider implements AuthenticationProvider {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(SocialAuthenticationProvider.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!supports(authentication.getClass())) {
            return null;
        }
        //授权
        SocialAuthentication auth = (SocialAuthentication) authentication;
        auth.addAuthority("ROLE_USER");
        authentication.setAuthenticated(true);
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SocialAuthentication.class.isAssignableFrom(authentication);
    }
}
