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

import com.naixwf.chord4j.chord.Chord;
import com.naixwf.chord4j.guitar.ChordChartVo;
import com.naixwf.gm.domain.ChordFret;
import com.naixwf.gm.service.ChordFretService;

/**
 * 管理和弦的按法
 * 
 * @author wangfei
 * @created 2013-2-21
 * 
 * @version 1.0
 */
@Controller
@RequestMapping("/chord")
public class ChordController {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(ChordController.class);
    @Resource
    private ChordFretService chordFretService;

    /**
     * 根据和弦名称获取和弦按法列表
     * 
     * @author wangfei
     * @param chordName
     * @param model
     * @return
     */
    @RequestMapping("/get_fret_by_chord_name")
    public String getFretByChordName(String chordName, Model model) {
        Chord chord = Chord.string2Chord(chordName);
        List<ChordFret> chordFretList = chordFretService.listByChord(chord);
        model.addAttribute("chordFretList", chordFretList);
        return "manage/get_fret_by_chord_name";
    }

    /**
     * 根据id获取一种和弦按法
     * 
     * @author wangfei
     * @param chordFretId
     * @param model
     * @return
     */
    @RequestMapping("/get_fret_by_id")
    public String getFret(Integer chordFretId, Model model) {
        ChordFret chordFret = chordFretService.findById(chordFretId);
        ChordChartVo chordChart = new ChordChartVo(chordFret);
        model.addAttribute("chordChart", chordChart);
        return "manage/get_fret_by_id";
    }
}
