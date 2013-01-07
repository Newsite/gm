/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.iphone;

import java.io.IOException;

import org.cloudfoundry.org.codehaus.jackson.JsonParseException;
import org.cloudfoundry.org.codehaus.jackson.map.JsonMappingException;
import org.cloudfoundry.org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author wangfei
 * @created 2012-12-22
 * 
 * @version 1.0
 */
public class IPhoneInfo {
    private static ObjectMapper objectMapper = new ObjectMapper();
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(IPhoneInfo.class);
    // 这是第几次查询
    private String count;
    // 初次解砖时间
    private String firstUnbrickDate;
    // 是否维修过
    private String hasRepair;
    // iccid
    private String iccID;
    // imei
    private String imeiNumber;
    // 最后解砖时间
    private String lastUnbrickDate;
    // APPLE型号描述
    private String partDescription;
    // 产品版本
    private String productVersion;
    // 序列号
    private String serialNumber;

    private String success;
    private String valid;

    @Override
    public String toString() {
        return String.format(
                "IMEI:%s,型号描述:%s,产品版本:%s,序列号:%s,ICCID:%s,首次解砖时间:%s,最后解砖时间:%s,是否维修过:%s", imeiNumber,
                partDescription, productVersion, serialNumber, iccID, firstUnbrickDate,
                lastUnbrickDate, hasRepair);
    }

    /**
     * @param result
     * @return
     */
    public static IPhoneInfo getInfo(String json) {
        try {
            return objectMapper.readValue(json, IPhoneInfo.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getFirstUnbrickDate() {
        return firstUnbrickDate;
    }

    public void setFirstUnbrickDate(String firstUnbrickDate) {
        this.firstUnbrickDate = firstUnbrickDate;
    }

    public String getHasRepair() {
        return hasRepair;
    }

    public void setHasRepair(String hasRepair) {
        this.hasRepair = hasRepair;
    }

    public String getIccID() {
        return iccID;
    }

    public void setIccID(String iccID) {
        this.iccID = iccID;
    }

    public String getImeiNumber() {
        return imeiNumber;
    }

    public void setImeiNumber(String imeiNumber) {
        this.imeiNumber = imeiNumber;
    }

    public String getLastUnbrickDate() {
        return lastUnbrickDate;
    }

    public void setLastUnbrickDate(String lastUnbrickDate) {
        this.lastUnbrickDate = lastUnbrickDate;
    }

    public String getPartDescription() {
        return partDescription;
    }

    public void setPartDescription(String partDescription) {
        this.partDescription = partDescription;
    }

    public String getProductVersion() {
        return productVersion;
    }

    public void setProductVersion(String productVersion) {
        this.productVersion = productVersion;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

}
