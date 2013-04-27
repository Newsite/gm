/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.web;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.naixwf.chord4j.chord.Chord;
import com.naixwf.chord4j.chord.ChordCategory;
import com.naixwf.chord4j.chord.Note;
import com.naixwf.gm.crawl.Ijita;
import com.naixwf.gm.dao.TabTxtDao;
import com.naixwf.gm.domain.ChordFret;
import com.naixwf.gm.domain.TabTxt;
import com.naixwf.gm.service.ChordFretService;
import com.naixwf.core.util.JsonUtil;
import com.naixwf.gm.web.vo.TabContentVo;

/**
 * 曲谱转换
 * 
 * @author wangfei
 * @created 2013-1-22
 * 
 * @version 1.0
 */
@Controller
public class TransferController {
    private static final Logger logger = LoggerFactory.getLogger(TransferController.class);
    @Resource
    private TabTxtDao tabTxtDao;
    @Resource
    private ChordFretService chordFretService;

    @RequestMapping("/transfer/chord")
    public @ResponseBody
    String chord() {
        Ijita ijita = new Ijita();
        Set<Chord> chordCrawled = new HashSet<Chord>();

        for (ChordCategory cc : ChordCategory.values()) {
            for (Note n : Note.values()) {
                Chord chord = Chord.newChord(n, cc.getMain());

                if (chordCrawled.contains(chord)) {
                    continue;
                }
                logger.debug("start crawl " + chord.getName());
                chordCrawled.add(chord);
                List<ChordFret> chordFrets = ijita.crawlFretFromIjita(chord);
                if (chordFrets != null) {
                    chordFretService.insert(chordFrets);
                }
            }
        }
        return "success";
    }

    @RequestMapping("/transfer/ijita")
    public @ResponseBody
    String ijita() {
        List<TabTxt> tabList = getTabList();
        for (TabTxt tab : tabList) {
            tabTxtDao.insert(tab);
        }
        return "success";
    }

    @RequestMapping("/transfer/jitapu")
    public @ResponseBody
    String jitapu() {
        
        return "success";
    }

    /**
     * 获取ijita曲谱列表
     * 
     * @author wangfei
     * @return
     */
    private List<TabTxt> getTabList() {
        List<TabTxt> list = new ArrayList<TabTxt>();
        String path = "/Users/wangfei/test/ijita/www.ijita.com/tab";
        File dir = new File(path);
        File[] files = dir.listFiles();
        FileInputStream fis = null;
        for (File file : files) {
            if (file.getName().endsWith(".html")) {
                try {
                    fis = new FileInputStream(file);
                    int length = fis.available();
                    byte inword[] = new byte[length];
                    fis.read(inword, 0, length);
                    fis.close();
                    String str = new String(inword);
                    TabTxt tab = file2TabTxt(str);
                    if (tab == null) {
                        continue;
                    }
                    tab.setComment(file.getName());
                    list.add(tab);
                } catch (Exception e) {
                    logger.warn(file.getName(), e);
                }

            }
        }
        return list;
    }

    /**
     * 从文件转换单个曲谱
     * 
     * @author wangfei
     * @param file
     * @return
     */
    private TabTxt file2TabTxt(String str) {
        Boolean isTxt = str.contains("文本谱");
        if (isTxt) {
            TabTxt tabTxt = new TabTxt();
            str = cutHead(str, "<div class=\"mainBody\">");

            str = cutHead(str, "<h1>");
            String name = getBefore(str, "<span>");

            str = cutHead(str, "<strong>");
            String singer = getBefore(str, "</strong>");

            str = cutHead(str, "<dd class=\"melody1\">");
            String keyOrigin = getBefore(str, "</dd>");

            str = cutHead(str, "<dd class=\"melody2\">");
            String keyChosen = getBefore(str, "</dd>");

            str = cutHead(str, "<pre>");
            String content = getBefore(str, "</pre>");
            content = getContentFromIjita(content);

            tabTxt.setName(name);
            tabTxt.setSingerId(0);
            tabTxt.setSinger(singer);
            tabTxt.setKeyOrigin(keyOrigin);
            tabTxt.setKeyChosen(keyChosen);
            tabTxt.setContent(content);
            tabTxt.setAddTime(new Date());
            logger.debug(name);
            return tabTxt;
        }
        return null;
    }

    /**
     * 将ijita的文本内容格式化为json
     * 
     * @author wangfei
     * @param content
     * @return
     */
    private String getContentFromIjita(String content) {
        TabContentVo vo = new TabContentVo();
        // String[] ss = content.split("</p>");
        // for (String s : ss) {
        // List<String> chordList = new ArrayList<String>();
        // String chordLine = getBefore(s, "<p>");
        //
        // String lyricLine = cutHead(s, "<p>");
        // lyricLine = getBefore(lyricLine, "</p>");
        //
        // String[] chords = chordLine.split("</u>");
        // for (String chord : chords) {
        // chord = cutHead(chord, "<u>");
        // chord = getBefore(chord, "</u>");
        // if (chord.length() > 0 && !chord.contains("\n")) {
        // chordList.add(chord);
        // }
        //
        // }
        //
        // Sentence sentence = new Sentence(chordList, lyricLine);
        // vo.add(sentence);
        // }
        return JsonUtil.toJsonString(vo);
    }

    /**
     * 获取n之前的部分
     * 
     * @author wangfei
     * @param str
     * @param string
     * @return
     */
    private String getBefore(String str, String n) {
        int i = str.indexOf(n);
        if (i < 0) {
            return str;
        }
        return str.substring(0, i);
    }

    /**
     * @author wangfei
     * @param str
     * @return
     */
    private String cutHead(String str, String n) {
        int i = str.indexOf(n);
        if (i < 0) {
            return str;
        }
        return str.substring(i + n.length());
    }
}
