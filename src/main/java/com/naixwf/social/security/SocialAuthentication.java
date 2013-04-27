package com.naixwf.social.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: wangfei
 * Date: 13-4-27
 * Time: 下午4:57
 */

public class SocialAuthentication implements Authentication {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(SocialAuthentication.class);

    private String userId;
    private Boolean authenticated = false;

    private final Collection<GrantedAuthority> authorities;

    public SocialAuthentication(String userId) {
        this.userId = userId;
        authorities = new HashSet<GrantedAuthority>();
    }

    public void addAuthority(String authority) {
        GrantedAuthority ga = new GrantedAuthorityImpl(authority);
        authorities.add(ga);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object getDetails() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object getPrincipal() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return userId;
    }


}
