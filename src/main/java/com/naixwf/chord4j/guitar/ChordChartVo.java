/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.chord4j.guitar;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.naixwf.chord4j.chord.Chord;
import com.naixwf.gm.domain.ChordFret;
import com.naixwf.core.util.ArrayListUtil;

/**
 * 和弦图示vo
 * 
 * @author wangfei
 * @created 2013-2-22
 * 
 * @version 1.0
 */
public class ChordChartVo {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(ChordChartVo.class);
    // 指板按法
    private List<StringFret> stringFretList;
    // 抽象和弦按法
    private ChordFret chordFret;

    private Chord chord;

    public String getRootNote() {
        return chordFret.getRootNote();
    }

    public ChordChartVo(final ChordFret chordFret) {
        this.chordFret = chordFret;

        List<Integer> fretNumbers = ArrayListUtil.string2IntegerList(chordFret.getFretNumbers());
        List<Integer> fingers = ArrayListUtil.string2IntegerList(chordFret.getFinger());

        assert fretNumbers.size() == 6;
        assert fingers.size() == 6;

        stringFretList = new ArrayList<StringFret>();
        for (int i = 1; i <= 6; i++) {
            StringFret stringFret = new StringFret(i, fretNumbers.get(6 - i), fingers.get(6 - i));
            stringFretList.add(stringFret);
        }

        // chord
        this.chord = Chord.string2Chord(chordFret.getName());
    }

    public String getChordName() {
        return chord.getName();
    }

    public Chord getChord() {
        return chord;
    }

    public Integer getPosition() {
        return chordFret.getPosition();
    }

    public List<StringFret> getStringFretList() {
        return stringFretList;
    }

    public void setStringFretList(List<StringFret> stringFretList) {
        this.stringFretList = stringFretList;
    }

    public ChordFret getChordFret() {
        return chordFret;
    }

}
