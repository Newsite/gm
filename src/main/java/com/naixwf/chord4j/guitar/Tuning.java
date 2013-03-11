/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.chord4j.guitar;

import java.util.Arrays;
import java.util.List;

import com.naixwf.chord4j.chord.Note;

/**
 * 
 * @author wangfei
 * @created 2013-1-30
 * 
 * @version 1.0
 */
public enum Tuning {
    STANDARD(Arrays.asList(Note.E, Note.A, Note.D, Note.G, Note.B, Note.E));
    Tuning(List<Note> noteList) {
        this.noteList = noteList;
    }

    List<Note> noteList;// 各弦音名，顺序为从6弦到1弦

    /**
     * 获得某弦空弦音名
     * 
     * @author wangfei
     * @param string
     * @return
     */
    public Note getNote(Integer string) {
        for (int i = 1, size = noteList.size(); i <= size; i++) {
            if ((size + 1 - i) == string) {
                return noteList.get(i - 1);
            }
        }
        return null;
    }
}
