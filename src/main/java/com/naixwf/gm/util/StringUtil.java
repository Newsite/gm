/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author wangfei
 * @created 2012-9-6
 * 
 * @version 1.0
 */
public class StringUtil {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(StringUtil.class);

    /**
     * 检查字符串是否有内容。
     * 
     * @param obj
     * @return
     */
    public static boolean isBlank(Object obj) {
        if (obj == null)
            return true;
        if (obj instanceof String) {
            String str = (String) obj;
            return str == null ? true : "".equals(str.trim());
        }
        try {
            String str = String.valueOf(obj);
            return str == null ? true : "".equals(str.trim());
        } catch (Exception e) {
            return true;
        }
    }
}
