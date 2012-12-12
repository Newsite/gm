/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 曲谱搜索类型定义
 * 
 * @author wangfei
 * @created 2012-12-12
 * 
 * @version 1.0
 */
public class SearchTypeDef {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(SearchTypeDef.class);
    // 全部
    public static final int ALL = 0;
    // 曲名
    public static final int SONG = 1;
    // 歌手
    public static final int SINGER = 2;

}
