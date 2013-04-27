/*
 * @author wangfei
 */
package com.naixwf.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 使用微博登陆 http://www.sinaimg.cn/blog/developer/wiki/oAuth2_01.gif
 * http://open.weibo
 * .com/wiki/%E6%8E%88%E6%9D%83%E6%9C%BA%E5%88%B6%E8%AF%B4%E6%98%8E
 * 
 * @author wangfei
 * @created 2013-3-6
 * 
 * @version 1.0
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        HttpSession session = request.getSession();
        // // check if the login flow has begun
        // Object userObj = session.getAttribute("user");
        //
        // String code = null;
        // code = WebUtil.getRequestParam(request, WeiboUtil.CODE);
        //
        // if (userObj == null) {
        // if (code == null) {
        // String loginDialogUrl = WeiboUtil.login(request, response);
        // WebUtil.location(response, loginDialogUrl);
        // return Boolean.FALSE;
        // } else {
        // // TODO 如果是weibo/canvas 直接允许它进入controller
        // return Boolean.TRUE;
        // }
        // } else {// online
        // User user = (User) userObj;
        // logger.info("current user:" + user.getAccessToken());
        // // TODO
        // return Boolean.TRUE;
        // }
        return Boolean.TRUE;
    }
}
