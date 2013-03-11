/*
 * @author wangfei
 */
package com.naixwf.gm.web.vo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 存放在session中的用户信息
 * 
 * @author wangfei
 * @created 2013-3-6
 * 
 * @version 1.0
 */
public class User {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(User.class);

    private String first_name;
    private String gender;
    private String id;
    private String last_name;
    private String link;
    private String locale;
    private String name;
    private Integer timezone;
    private String updated_time;
    private String username;
    private Boolean verified;

    private String accessToken;
    private String expires;

    private Date lastLoginTime;

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTimezone() {
        return timezone;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    public String getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(String updated_time) {
        this.updated_time = updated_time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }
}
