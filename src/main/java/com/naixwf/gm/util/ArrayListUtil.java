/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.util;

import java.util.ArrayList;
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

    /**
     * 字符串转数字列表
     * 
     * @author wangfei
     * @param fretNumbers
     * @return
     */
    public static List<Integer> string2IntegerList(String fretNumbers) {
        String[] fretNumberAry = fretNumbers.split(",");
        List<Integer> list = new ArrayList<Integer>();
        for (String s : fretNumberAry) {
            try {
                list.add(Integer.parseInt(s));
            } catch (Exception e) {
                logger.info("fretNumbers contains NAN:" + fretNumbers, e);
                return null;
            }
        }
        return list;
    }

    /**
     * 将列表拼成字符串
     * 
     * @author wangfei
     * @param string
     * @return
     */
    public static String list2String(List<? extends Object> list, String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0, size = list.size(); i < size; i++) {
            if (i > 0) {
                sb.append(s);
            }
            sb.append(list.get(i));
        }
        return sb.toString();
    }
}
