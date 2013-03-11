/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.service;

import java.util.List;

import com.naixwf.chord4j.chord.Chord;
import com.naixwf.chord4j.guitar.ChordChartVo;
import com.naixwf.gm.domain.ChordFret;

/**
 * 和弦指法相关
 * 
 * @author wangfei
 * @created 2013-2-21
 * 
 * @version 1.0
 */
public interface ChordFretService {

    /**
     * 根据和弦查找
     * 
     * @author wangfei
     * @param chord
     * @return
     */
    List<ChordFret> listByChord(Chord chord);

    /**
     * 根据id获取
     * 
     * @author wangfei
     * @param chordFretId
     * @return
     */
    ChordFret findById(Integer chordFretId);

    /**
     * 获取指定和弦的第一个图例
     * 
     * @author wangfei
     * @param chordList
     * @return
     */
    List<ChordChartVo> listFirstChordFretByChordList(List<Chord> chordList);

    /**
     * @author wangfei
     * @param chordFretList
     */
    void insert(List<ChordFret> chordFretList);

}
