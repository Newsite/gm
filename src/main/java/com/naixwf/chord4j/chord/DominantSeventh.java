/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.chord4j.chord;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 属七和弦/大小七和弦
 * 
 * @author wangfei
 * @created 2013-1-30
 * 
 * @version 1.0
 */
public class DominantSeventh extends Seventh {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(DominantSeventh.class);

    /**
     * @param root
     */
    public DominantSeventh(Note root) {
        super(root);
    }

}
