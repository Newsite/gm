/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.web;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.naixwf.chord4j.chord.dic.Note;
import com.naixwf.chord4j.chord.dic.Pitch;
import com.naixwf.gm.domain.TabTxt;
import com.naixwf.gm.exception.InvalidParamException;
import com.naixwf.gm.service.TabTxtService;
import com.naixwf.gm.util.Page;
import com.naixwf.gm.util.TabUtil;
import com.naixwf.gm.web.vo.TabContentVo;

/**
 * 具体谱子相关
 * 
 * @author wangfei
 * @created 2012-9-5
 * 
 * @version 1.0
 */
@Controller
@RequestMapping("/tab")
public class TabController extends BaseController {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(TabController.class);
    @Resource
    private TabTxtService tabTxtService;

    /**
     * 具体曲谱
     * 
     * @author wangfei
     * @param tabId
     * @param model
     * @return
     */
    @RequestMapping("/details")
    public String details(Integer tabId, Integer offset, Model model) {
        if (offset != null && (offset < 0 || offset > 11)) {
            throw new InvalidParamException("offset必须在0-11之间");
        }

        TabTxt tab = tabTxtService.findById(tabId);
        tab.setKeyOrigin(Note.getByName(tab.getKeyOrigin()).getName());
        
        // 变调器
        putTransposer(tab.getKeyChosen(), model);

        TabContentVo content = TabUtil.jsonToTabContent(tab.getContent());

        if (offset != null) {// 处理转调
            content.transpose(offset);
            Note note = Note.getByName(tab.getKeyChosen());
            tab.setKeyChosen(note.add(offset).getName());
        } else {
            offset = 0;
        }
        model.addAttribute("offset", offset);

        tab.setContentVo(content);
        model.addAttribute("tab", tab);

        putSearchType2Model(model);

        // TODO 和弦转换图
        return "tab/details";
    }

    /**
     * 搜索曲谱
     * 
     * @author wangfei
     * @param type
     * @param value
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String searchTab(Integer type, String keyword, Page page, Model model) {
        List<TabTxt> list = tabTxtService.searchTab(type, keyword, page);
        model.addAttribute("page", page);
        model.addAttribute("list", list);

        putSearchType2Model(model);

        return "tab/list";
    }
}
