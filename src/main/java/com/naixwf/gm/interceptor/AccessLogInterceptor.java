/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.interceptor;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * @author wangfei
 * @created 2012-12-14
 * 
 * @version 1.0
 */
public class AccessLogInterceptor extends HandlerInterceptorAdapter {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(AccessLogInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        //把request本身放入attibute以便ftl调用
        request.setAttribute("request", request);
        // logId
        String logId = UUID.randomUUID().toString();
        // uri
        String uri = request.getRequestURI();
        uri = uri.substring(request.getContextPath().length());
        // method
        String method = request.getMethod();
        // 访问者ip
        String accessorIp = getRemoteIp(request);

        StringBuilder params = new StringBuilder();

        @SuppressWarnings("unchecked")
        Map<String, String> parameterMap = request.getParameterMap();
        for (Iterator<String> iter = parameterMap.keySet().iterator(); iter.hasNext();) {
            String key = iter.next();

            if (key != null && !key.trim().equals("")) {
                String value = request.getParameter(key);

                if (value.contains("\r") || value.contains("\n")) {
                    value = value.replaceAll("\r", "").replace("\n", "|");
                }

                params.append(key).append("=").append(value);
            }

            if (iter.hasNext()) {
                params.append("&");
            }
        }

        StringBuilder logString = new StringBuilder();
        logString.append("logId=").append(logId);
        logString.append(" ").append(accessorIp);
        logString.append(" ").append(method);
        logString.append(" ").append("uri=").append(uri);
        logString.append(" ").append("params=[").append(params).append("]");
        logger.debug(logString.toString());
        return true;
    }

    /**
     * @author wangfei
     * @param request
     * @return
     */
    private String getRemoteIp(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        String rip = request.getHeader("X-Real-IP");
        if (rip == null) {
            rip = request.getHeader("X-Forwarded-For");
        }
        return rip != null ? rip : ip;
    }
}
