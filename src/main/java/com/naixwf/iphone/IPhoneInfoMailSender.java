/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.iphone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.naixwf.gm.mail.MailSenderInfo;
import com.naixwf.gm.mail.SimpleMailSender;

/**
 * iphone信息邮件发送
 * 
 * @author wangfei
 * @created 2012-12-22
 * 
 * @version 1.0
 */
public class IPhoneInfoMailSender {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(IPhoneInfoMailSender.class);

    public static void send(String content) {
        // 这个类主要是设置邮件
        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setMailServerHost("smtp.yeah.net");
        mailInfo.setMailServerPort("25");
        mailInfo.setValidate(true);
        mailInfo.setUserName("naixwf");
        mailInfo.setPassword("ak4747");
        mailInfo.setFromAddress("naixwf@yeah.net");
        mailInfo.setToAddress("naixwf@yeah.net");
        mailInfo.setSubject("IPHONE刷机信息");
        mailInfo.setContent(content);
        // 这个类主要来发送邮件
        SimpleMailSender sms = new SimpleMailSender();
        // sms.sendTextMail(mailInfo);// 发送文体格式
        sms.sendHtmlMail(mailInfo);// 发送html格式
    }

    public static void main(String[] args) {
        send("test");
    }
}
