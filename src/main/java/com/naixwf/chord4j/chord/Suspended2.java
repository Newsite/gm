/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.chord4j.chord;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 挂二和弦
 * 
 * @author wangfei
 * @created 2013-2-20
 * 
 * @version 1.0
 */
public class Suspended2 extends Triad {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(Suspended2.class);

    /**
     * @param root
     */
    public Suspended2(Note root) {
        super(root);
    }
}
