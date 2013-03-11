/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.crawl;

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
 * @author wangfei
 * @created 2013-2-26
 * 
 * @version 1.0
 */
public class BaseCrawl {
    private static final Logger logger = LoggerFactory.getLogger(BaseCrawl.class);

    public String get(String url) {
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

    public String post(HttpPost httpPost) {
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

}
