/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.chord4j.chord;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 减七和弦
 * 
 * @author wangfei
 * @created 2013-2-18
 * 
 * @version 1.0
 */
public class DiminishedSeventh extends Seventh {

    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(DiminishedSeventh.class);

    /**
     * @param root
     */
    public DiminishedSeventh(Note root) {
        super(root);
    }

}
