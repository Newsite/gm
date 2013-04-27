package com.naixwf.social.weibo.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: wangfei
 * Date: 13-4-24
 * Time: 下午4:25
 */

public class WeiboTemplate implements Weibo {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(WeiboTemplate.class);

    public WeiboTemplate(String accessToken) {
    }

    @Override
    public StatusOperations statuses() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public CommentOperations comments() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public UserOperations users() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public FriendshipOperations friendships() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public FriendshipGroupOperations friendshipGroups() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public AccountOperations account() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public FavoriteOperations favorites() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public TrendOperations trends() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public StatusTagOperations statusTags() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public TagOperations tags() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public RegisterOperations register() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public SearchOperations search() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public SuggestionOperations suggestions() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public RemaindOperations remaind() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ShortUrlOperations shortUrl() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public NotificationOperations notification() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public CommonOperations common() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public LocationOperations location() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
