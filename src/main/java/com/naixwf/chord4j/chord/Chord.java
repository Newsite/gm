/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.chord4j.chord;

/**
 * 单个和弦
 * 
 * @author wangfei
 * @created 2013-1-10
 * 
 * @version 1.0
 */
public interface Chord {
    /**
     * 获取音数
     * 
     * @author wangfei
     * @return
     */
    int getYinShu();

    /**
     * 获取音程序列
     * 
     * @author wangfei
     * @return
     */
    int[] getYinCheng();
}
