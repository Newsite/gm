package com.naixwf.social.domain;

import org.hibernate.id.UUIDHexGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.ConnectionData;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: wangfei
 * Date: 13-4-18
 * Time: 下午4:56
 * 本地用户和social用户建立的连接 1.
 */

@Entity
@Table(name = "social_connection", uniqueConstraints = {@UniqueConstraint(columnNames = {"userId", "providerId", "providerUserId"}),
        @UniqueConstraint(columnNames = {"userId", "providerId", "rank"})})
public class SocialConnection {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(SocialConnection.class);


    private int id;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String providerId;

    private String providerUserId;

    @Column(nullable = false)
    private int rank;

    private String displayName;

    private String profileUrl;

    private String imageUrl;

    @Column(nullable = false)
    private String accessToken;

    private String secret;

    private String refreshToken;
    private Long expireTime;

    private Date createDate = new Date();

    public SocialConnection() {

    }

    public SocialConnection(ConnectionData data, Integer rank) {
        this.userId = UUID.randomUUID().toString().replaceAll("-", "");
        this.providerId = data.getProviderId();
        this.providerUserId = data.getProviderUserId();
        this.rank = rank;
        this.displayName = data.getDisplayName();
        this.profileUrl = data.getProfileUrl();
        this.imageUrl = data.getImageUrl();
        this.accessToken = data.getAccessToken();
        this.secret = data.getSecret();
        this.refreshToken = data.getRefreshToken();
        this.expireTime = data.getExpireTime();
        this.createDate = new Date();
    }

    public SocialConnection(String userId, ConnectionData data, Integer rank) {
        this(data, rank);
        this.userId = userId;
    }

    public void update(String userId, ConnectionData data) {
        this.userId = userId;
        this.providerId = data.getProviderId();
        this.providerUserId = data.getProviderUserId();
        this.displayName = data.getDisplayName();
        this.profileUrl = data.getProfileUrl();
        this.imageUrl = data.getImageUrl();
        this.accessToken = data.getAccessToken();
        this.secret = data.getSecret();
        this.refreshToken = data.getRefreshToken();
        this.expireTime = data.getExpireTime();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "userId")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "providerId")
    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    @Column(name = "providerUserId")
    public String getProviderUserId() {
        return providerUserId;
    }

    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }

    @Column(name = "rank")
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Column(name = "displayName")
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Column(name = "profileUrl")
    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    @Column(name = "imageUrl")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column(name = "accessToken")
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Column(name = "secret")
    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Column(name = "refreshToken")
    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Column(name = "expireTime")
    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    @Column(name = "create_time")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


}