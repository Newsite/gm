/*
 * @author wangfei
 */
package com.naixwf.gm.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author wangfei
 * @created 2013-3-6
 * 
 * @version 1.0
 */
public class WebUtil {
    private static final Logger logger = LoggerFactory.getLogger(WebUtil.class);

    public static String httpGet(String url) {
        String result = null;
        try {
            HttpRequestBase httpRequest = new HttpGet(url);
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpResponse response = httpClient.execute(httpRequest);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
            return result;
        } catch (Exception e) {
            logger.error("读取url出错:" + url, e);
        }
        return result;
    }

    public String httpPost(HttpPost httpPost) {
        String result = null;
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
            return result;
        } catch (Exception e) {
            logger.error("读取url出错:" + httpPost.getURI().toString(), e);
        }
        return result;
    }

    /**
     * 获取某个request的某个请求参数
     * 
     * @author wangfei
     * @param request
     * @param code
     * @return
     */
    public static String getRequestParam(HttpServletRequest request, String key) {
        @SuppressWarnings("unchecked")
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap != null) {
            String[] tmp = (String[]) parameterMap.get(key);
            if (tmp != null) {
                return (tmp)[0];
            }
        }
        return null;
    }

    /**
     * 隐式重定向
     * 
     * @author wangfei
     * @param response
     * @param string
     */
    public static void location(HttpServletResponse response, String url) {
        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        response.setHeader("Location", url);
    }

}
