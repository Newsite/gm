/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.naixwf.gm.dao.JitapuDao;
import com.naixwf.gm.exam.Question;
import com.naixwf.gm.exam.QuestionType;
import com.naixwf.gm.exam.question.factory.QuestionFactory;
import com.naixwf.gm.service.TabTransService;
import com.naixwf.gm.util.DateUtil;
import com.naixwf.gm.util.ScreenResolver;
import com.naixwf.iphone.IPhoneInfo;
import com.naixwf.iphone.IPhoneInfoMailSender;

/**
 * 
 * @author wangfei
 * @created 2012-6-5
 * 
 * @version 1.0
 */
@Controller
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Resource
    JitapuDao jitapuDao;
    @Resource
    TabTransService tabTransService;
    @Resource
    private FreeMarkerConfigurer freemarkerConfig;

    /**
     * 首页
     * 
     * @author wangfei
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String index(Integer tabId, Model model) {
        return "redirect:exam/index";
    }

    /**
     * 把jitapu表转化进tab表
     * 
     * @author wangfei
     */
    @RequestMapping("/transe_jitapu")
    public @ResponseBody
    Map<String, Object> saveJitapu() {
        // List<String> errorList = new ArrayList<String>();
        // List<Jitapu> list = jitapuDao.listAll();
        // for (Jitapu item : list) {
        // String error = tabTransService.insertJitapu(item);
        // if (error != null) {
        // errorList.add(error);
        // }
        // }
        // logger.warn(String.format("共%d条,失败%d条", list.size(),
        // errorList.size()));
        // for (String item : errorList) {
        // logger.warn(item);
        // }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", "转换完毕");
        return map;
    }

    /**
     * 查看并发送IPhone信息
     * 
     * @author wangfei
     * @return
     */
    @RequestMapping("/iphone")
    public String checkIPhone(Model model) {
        String url = "http://iccid.idlphone.com/AppleQuery";
        String key = "c8214c25d34a5f3";
        try {
            HttpPost post = new HttpPost(url);
            post.setHeader(
                    "User-Agent",
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.101 Safari/537.11");
            List<NameValuePair> qparams = new ArrayList<NameValuePair>();
            qparams.add(new BasicNameValuePair("imei", key));
            UrlEncodedFormEntity params = new UrlEncodedFormEntity(qparams, "UTF-8");
            post.setEntity(params);
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpResponse response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            IPhoneInfo info = IPhoneInfo.getInfo(result);
            logger.info("查询结果:" + info.toString());

            // 生成邮件正文
            String content = getContent(info);
            IPhoneInfoMailSender.send(content);

            model.addAttribute("info", info);
            model.addAttribute("time", DateUtil.Date2StringSec(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "iphone";
    }

    /**
     * @author wangfei
     * @param info
     * @return
     * @throws Exception
     */
    private String getContent(IPhoneInfo info) throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("info", info);
        model.put("time", DateUtil.Date2StringSec(new Date()));
        ScreenResolver screenResolver = new ScreenResolver();
        screenResolver.setFreemarkerConfigurer(freemarkerConfig);
        String content = screenResolver.mergeModelToTemplate("/iphone.ftl", model);
        return content;
    }
}
