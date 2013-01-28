/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.core;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.bull.javamelody.MonitoringFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 继承jiavamelody默认的filter，添加权限控制，修改访问路径
 * 
 * @author wangfei
 * @created 2013-1-7
 * 
 * @version 1.0
 */
public class GmMonitoringFilter extends MonitoringFilter {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(GmMonitoringFilter.class);

    /**
     * @author wangfei
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     * @see net.bull.javamelody.MonitoringFilter#doFilter(javax.servlet.ServletRequest,
     *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        super.doFilter(request, response, chain);
    }
}
