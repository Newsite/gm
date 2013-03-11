/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.naixwf.chord4j.chord.Chord;
import com.naixwf.chord4j.chord.ChordCategory;
import com.naixwf.chord4j.guitar.ChordChartVo;
import com.naixwf.gm.dao.ChordFretDao;
import com.naixwf.gm.domain.ChordFret;
import com.naixwf.gm.service.ChordFretService;

/**
 * 
 * @author wangfei
 * @created 2013-2-21
 * 
 * @version 1.0
 */
@Service
public class ChordFretServiceImpl implements ChordFretService {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(ChordFretServiceImpl.class);
    @Resource
    private ChordFretDao chordFretDao;

    /**
     * @author wangfei
     * @param chord
     * @return
     * @see com.naixwf.gm.service.ChordFretService#listByChord(com.naixwf.chord4j.chord.Chord)
     */
    @Override
    public List<ChordFret> listByChord(Chord chord) {
        ChordCategory cc = ChordCategory.getByChordClass(chord.getClass());
        if (cc == null) {
            throw new RuntimeException("ChordCategory没有这个类型的和弦：" + chord.getClass());
        }
        Integer chordId = cc.getChordId();
        String rootNoteName = chord.getRootNote().getName();
        List<ChordFret> list = chordFretDao.listByChordIdAndRootNote(chordId, rootNoteName);
        return list;
    }

    /**
     * @author wangfei
     * @param chordFretId
     * @return
     * @see com.naixwf.gm.service.ChordFretService#findById(java.lang.Integer)
     */
    @Override
    public ChordFret findById(Integer chordFretId) {
        return chordFretDao.findById(chordFretId);
    }

    /**
     * @author wangfei
     * @param chordList
     * @return
     * @see com.naixwf.gm.service.ChordFretService#listFirstChordFretByChordList(java.util.List)
     */
    @Override
    public List<ChordChartVo> listFirstChordFretByChordList(List<Chord> chordList) {
        assert chordList != null;
        List<ChordFret> fretList = chordFretDao.listByChords(chordList);
        List<ChordChartVo> list = new ArrayList<ChordChartVo>();
        if (fretList != null && !fretList.isEmpty()) {
            ChordFret tmp = null;
            for (ChordFret chordFret : fretList) {
                if (tmp != null && chordFret.getChordId().equals(tmp.getChordId())
                        && chordFret.getRootNote().equals(tmp.getRootNote())) {
                    continue;
                }
                tmp = chordFret;
                list.add(new ChordChartVo(chordFret));
            }
        }
        return list;
    }

    /**
     * @author wangfei
     * @param chordFret
     * @see com.naixwf.gm.service.ChordFretService#insert(com.naixwf.gm.domain.ChordFret)
     */
    @Override
    public void insert(List<ChordFret> chordFretList) {
        for (ChordFret fret : chordFretList) {
            chordFretDao.insert(fret);
        }
    }
}
