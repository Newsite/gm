/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * arraylist工具类
 * 
 * @author wangfei
 * @created 2012-12-12
 * 
 * @version 1.0
 */
public class ArrayListUtil {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(ArrayListUtil.class);

    /**
     * 将列表b merge进dest中
     * 
     * @author wangfei
     * @param <T>
     * @param dest
     * @param b
     */
    @SuppressWarnings("unchecked")
    public static <T> void merge(List<T> dest, List<T> b) {
        for (Object o : b) {
            boolean isIn = false;
            for (Object tmp : dest) {
                if (tmp.equals(o)) {
                    isIn = true;
                    break;
                }
            }
            if (!isIn) {
                dest.add((T) o);
            }
        }
    }
}
