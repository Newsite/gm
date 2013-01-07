/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.util;

import java.io.StringWriter;
import java.util.Map;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 用于将model和ftl文件合并，并返回字符串
 * 
 * @author wangfei
 * @created 2012-3-7
 * 
 * @version 1.0
 */
public class ScreenResolver {

    /**
     * Freemarker配置
     */
    private FreeMarkerConfigurer freemarkerConfigurer;

    public FreeMarkerConfigurer getFreemarkerConfigurer() {
        return freemarkerConfigurer;
    }

    public void setFreemarkerConfigurer(FreeMarkerConfigurer freemarkerConfigurer) {
        this.freemarkerConfigurer = freemarkerConfigurer;
    }

    /**
     * 渲染视图, 将数据合并至模板中.
     * 
     * @param ftlName
     * @param model
     * @return String
     * @throws Exception
     */
    public String mergeModelToTemplate(String ftlName, Map<String, Object> model) throws Exception {

        Template template = null;
        StringWriter swriter = new StringWriter();

        Configuration configuration = freemarkerConfigurer.getConfiguration();

        template = configuration.getTemplate(ftlName);
        template.process(model, swriter);

        return swriter.toString();
    }
}
