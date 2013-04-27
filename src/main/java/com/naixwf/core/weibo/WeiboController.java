/*
 * @author wangfei
 */
package com.naixwf.core.weibo;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naixwf.core.util.WebUtil;
import com.naixwf.gm.web.vo.User;

/**
 * 
 * via http://open.weibo.com/wiki/Oauth2/authorize
 * 
 * @author wangfei
 * @created 2013-3-10
 * 
 * @version 1.0
 */
@Controller
@RequestMapping("/weibo")
public class WeiboController {
    private static final Logger logger = LoggerFactory.getLogger(WeiboController.class);

    /**
     * 登陆对话框的回调页面用来接收用户授权code 授权回调地址，站外应用需与设置的回调地址一致，站内应用需填写canvas page的地址。
     * 
     * @author wangfei
     * @param code
     */
    @RequestMapping("/canvas")
    public void getCode(String code, HttpServletRequest request, HttpServletResponse response) {
        assert code != null;

        HttpSession session = request.getSession();
        String state = (String) session.getAttribute(WeiboUtil.STATE);
        if (state != null) {// has login
            handleFirstSeccessLogin(request, code, state);
        }
    }

    /**
     * Handle the response from the Login Dialog
     * 
     * @author wangfei
     * @param code
     */
    private void handleFirstSeccessLogin(HttpServletRequest request, String code, String state) {

        HttpSession session = request.getSession();
        logger.info("code:" + code);
        logger.debug("state:" + state);
        String stateInRequest = WebUtil.getRequestParam(request, WeiboUtil.STATE);

        if (state.equals(stateInRequest)) {
            // state variable matches
            String accessTokenString = WeiboUtil.getToken(code, request);
            if (accessTokenString != null) {
                try {
                    String[] tmp = accessTokenString.split("&");
                    String accessToken = tmp[0].substring("access_token=".length());
                    String expires = tmp[1].substring("expires=".length());

                    // TODO accessToken 需要进一步解析
                    User onlineUser = new User();
                    onlineUser.setAccessToken(accessToken);
                    onlineUser.setExpires(expires);
                    onlineUser.setLastLoginTime(new Date());

                    // mark this user as online
                    session.setAttribute("user", onlineUser);
                } catch (Exception e) {
                    logger.error("accessToken error:" + accessTokenString, e);
                }
            } else {
                throw new RuntimeException("Get Access Token error");
            }

        } else {
            logger.info("The state does not match. You may be a victim of CSRF.");
            // 将此用户重置为未登录状态
            session.setAttribute(WeiboUtil.STATE, null);
            session.setAttribute("user", null);
        }

    }
}
