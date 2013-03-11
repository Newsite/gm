/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.chord4j.guitar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.naixwf.chord4j.chord.Note;

/**
 * 单弦按法
 * 
 * @author wangfei
 * @created 2013-2-22
 * 
 * @version 1.0
 */
public class StringFret {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(StringFret.class);
    // 弦序号
    private Integer index;
    // 按第几品
    private Integer fretNumber;
    // 用哪个手指按
    private Integer finger;
    // 音名
    private Note note;
    // 空弦或者和弦外音
    private String status;

    public StringFret(Integer index, Integer fretNumber, Integer finger) {
        this.index = index;
        this.fretNumber = fretNumber;
        this.finger = finger;
        this.note = Fretboard.getNote(index, fretNumber);
        if (fretNumber == 0) {
            status = "o";
        } else if (fretNumber == Fretboard.NO_FRET) {
            status = "x";
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d弦,%d品,%d指,%s,status=%s", index, fretNumber, finger,
                note.getName(), status));
        return sb.toString();
    }

    public Integer getIndex() {
        return index;
    }

    public Integer getFretNumber() {
        return fretNumber;
    }

    public Integer getFinger() {
        return finger;
    }

    public Note getNote() {
        return note;
    }

    public String getStatus() {
        return status;
    }

}
