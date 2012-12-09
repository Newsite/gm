/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.service;

import com.naixwf.gm.domain.Jitapu;


/**
 * 将指定格式的data转换成tab
 *
 * @author wangfei
 * @created 2012-9-6
 *
 * @version 1.0
 */
public interface TabTransService {
    /**
     * 将jitapu.com的谱子转换成tab
     * @author wangfei
     * @param jitapu
     */
    public String insertJitapu(Jitapu jitapu);
}
