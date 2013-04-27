package com.naixwf.social.weibo.api;


/**
 * Created with IntelliJ IDEA.
 * User: wangfei
 * Date: 13-4-24
 * Time: 下午3:50
 * 所有可以被调用的api的集合
 *
 * http://open.weibo.com/wiki/API%E6%96%87%E6%A1%A3_V2
 */
public interface Weibo {

    /**
     * 微博
     *
     * @return
     */
    StatusOperations statuses();

    /**
     * 评论
     *
     * @return
     */
    CommentOperations comments();

    /**
     * 用户相关接口
     *
     * @return
     */
    UserOperations users();

    /**
     * 好友
     *
     * @return
     */
    FriendshipOperations friendships();

    /**
     * 好友分组
     *
     * @return
     */
    FriendshipGroupOperations friendshipGroups();

    /**
     * 账户
     *
     * @return
     */
    AccountOperations account();

    /**
     * 收藏
     *
     * @return
     */
    FavoriteOperations favorites();

    /**
     * 话题
     *
     * @return
     */
    TrendOperations trends();

    /**
     * 微博标签
     *
     * @return
     */
    StatusTagOperations statusTags();

    /**
     * 用户标签
     *
     * @return
     */
    TagOperations tags();

    /**
     * 注册相关的操作
     *
     * @return
     */
    RegisterOperations register();

    /**
     * 搜索
     *
     * @return
     */
    SearchOperations search();

    /**
     * 推荐
     *
     * @return
     */
    SuggestionOperations suggestions();

    /**
     * 提醒
     * @return
     */
    RemaindOperations remaind();

    /**
     * 短链
     * @return
     */
    ShortUrlOperations shortUrl();

    /**
     * 通知
     * @return
     */
    NotificationOperations notification();

    /**
     * 公共服务
     * @return
     */
    CommonOperations common();


    /**
     * 地理信息
     * @return
     */
    LocationOperations location();

}
