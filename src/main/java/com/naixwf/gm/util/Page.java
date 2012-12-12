/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 查询分页
 * 
 * @author wangfei
 * @created 2012-12-11
 * 
 * @version 1.0
 */
public class Page {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(Page.class);
    public static int DEFAULT_PAGESIZE = 20;

    public static int DEFAULT_PAGE = 1;

    private int pageNo = DEFAULT_PAGE;

    private int pageSize = DEFAULT_PAGESIZE;

    private int totalCount = -1;

    private int totalPageCount = 1;

    public Page() {
        this.pageNo = DEFAULT_PAGE;
        this.pageSize = DEFAULT_PAGESIZE;
    }

    public Page(Integer pageNo) {
        this.pageNo = pageNo;
        this.pageSize = DEFAULT_PAGESIZE;
    }

    public Page(Integer pageNo, Integer pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize == null ? DEFAULT_PAGESIZE : pageSize;
    }

    // 第一条记录在结果集中的位置,序号从0开始.
    public int getStart() {
        if (pageNo < 0 || pageSize < 0) {
            return -1;
        } else {
            return ((pageNo - 1) * pageSize);
        }
    }

    // 获取总页数
    public int getTotalPageCount() {
        calculateTotalPageCount();
        return totalPageCount;
    }

    public void calculateTotalPageCount() {
        totalPageCount = totalCount / pageSize;
        if (totalCount % pageSize > 0) {
            totalPageCount++;
        }

        // 校正页码
        if (pageNo > totalPageCount) {
            pageNo = totalPageCount;
        }
        if (pageNo < 1) {
            pageNo = 1;
        }
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

}
