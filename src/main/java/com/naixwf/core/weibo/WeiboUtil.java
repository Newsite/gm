/*
 * @author wangfei
 */
package com.naixwf.core.weibo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.naixwf.core.util.WebUtil;

/**
 * 新浪微博util
 * 
 * @author wangfei
 * @created 2013-3-10
 * 
 * @version 1.0
 */
public class WeiboUtil {
    private static final Logger logger = LoggerFactory.getLogger(WeiboUtil.class);
    public static final String APP_KEY = "3513144720";
    public static final String APP_SECRET = "a4fb8f3984789d96e35d877c2bdf205c";
    public static final String STATE = "state";
    public static final String CODE = "code";

    // 请求用户授权Token
    public static final String URL_AUTHORIZE = "https://api.weibo.com/oauth2/authorize";
    // 获取授权过的Access Token
    public static final String URL_ACCESS_TOKEN = "https://api.weibo.com/oauth2/access_token";

    /**
     * redirect to login dialog
     * 
     * @author wangfei
     * @param request
     * @param response
     */
    public static String login(HttpServletRequest request, HttpServletResponse response) {
        // CSRF protection
        String state = getState();
        HttpSession session = request.getSession();
        session.setAttribute(WeiboUtil.STATE, state);

        // dialogUrl
        String redirectUrl = null;
        try {
            // redirectUrl = URLEncoder.encode(
            // "http://" + request.getRemoteAddr() + request.getContextPath()
            // + "/weibo/canvas", "utf-8");
            redirectUrl = URLEncoder
                    .encode("http://guitarme.cloudfoundry.com" + request.getContextPath()
                            + "/weibo/canvas", "utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("", e);
            return null;
        }

        String fbLoginDialogUrl = WeiboUtil.URL_AUTHORIZE + "?client_id=" + WeiboUtil.APP_KEY
                + "&redirect_uri=" + redirectUrl + "&state=" + state;

        // redirect to login dialog
        return fbLoginDialogUrl;
    }

    /**
     * @author wangfei
     * @return
     */
    private static String getState() {
        String state = UUID.randomUUID().toString();
        state = state.replaceAll("-", "");
        return state;
    }

    /**
     * Exchange the code for an Access Token
     * 
     * @author wangfei
     * @param code
     * @return
     */
    public static String getToken(String code, HttpServletRequest request) {
        String redirectUrl = null;
        try {
            // redirectUrl = URLEncoder.encode(
            // "http://" + request.getRemoteAddr() + request.getContextPath()
            // + "/weibo/canvas", "utf-8");
            redirectUrl = URLEncoder
                    .encode("http://guitarme.cloudfoundry.com" + request.getContextPath()
                            + "/weibo/canvas", "utf-8");

        } catch (UnsupportedEncodingException e) {
            logger.error("", e);
        }
        String tokenUrl = WeiboUtil.URL_ACCESS_TOKEN + "?client_id=" + WeiboUtil.APP_KEY
                + "&client_secret=" + WeiboUtil.APP_SECRET + "&grant_type=authorization_code&code="
                + code + "&redirect_url=" + redirectUrl;

        String result = null;
        try {
            result = WebUtil.httpGet(tokenUrl);
        } catch (Exception e) {
            throw new RuntimeException("Operation timed out:" + tokenUrl, e);
        }

        if (result != null) {
            // TODO 这里要判断返回值是否有效
            // logger.debug(result);
            // JsonNode node = JsonUtil.toJsonNode(result);
            // if (node != null) {
            // JsonNode error = node.get("error");
            // if (error != null) {
            // throw new
            // RuntimeException("error occured when getting access token:"
            // + error.toString());
            // }
            // }
        }

        return result;
    }
}
