/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 非法参数
 * 
 * @author wangfei
 * @created 2012-12-27
 * 
 * @version 1.0
 */
public class InvalidParamException extends RuntimeException {

    private static final long serialVersionUID = -4787048662231408799L;
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(InvalidParamException.class);

    /**
     * @param string
     */
    public InvalidParamException(String message) {
        super(message);
    }
}
