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

import com.naixwf.chord4j.chord.dic.Chord;
import com.naixwf.chord4j.chord.dic.MajorTriads;
import com.naixwf.chord4j.chord.dic.Note;

/**
 * 指板
 * 
 * @author wangfei
 * @created 2013-1-30
 * 
 * @version 1.0
 */
public class Fretboard {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(Fretboard.class);
    private static final Tuning DEFULT_TUNNING = Tuning.STANDARD;
    private static int MAX_FRET = 20;// 最高品位
    private static int MAX_OFFSET = 5;// 一个和弦最大跨几个品格
    private static int NO_FRET = -1;// 某弦为和弦外音

    /**
     * 获取某弦某品的音名
     * 
     * @author wangfei
     * @param string
     * @param fret
     */
    public static Note getNote(Integer string, Integer fret) {
        Note emptyNote = DEFULT_TUNNING.getNote(string);
        return emptyNote.add(fret);
    }

    /**
     * 获取某和弦在某把位的按法
     * 
     * @author wangfei
     * @param chord
     *            和弦
     * @param fret
     *            把位
     * @return
     */
    public static List<Integer> getFretNumberByChord(Chord chord, Integer fret) {
        List<Note> noteList = chord.getNoteList();
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 6; i > 0; i--) {
            boolean done = false;// 此弦已查完
            for (int j = fret; j <= (fret + MAX_OFFSET); j++) {
                Note tmp = getNote(i, j);
                if (noteList.contains(tmp)) {
                    list.add(j);
                    done = true;
                    break;
                }
            }
            if (!done) {
                list.add(NO_FRET);
            }
        }
        return list;
    }

    public static void printChord(Chord chord, Integer baseFret) {
        List<Integer> fretNumberSequence = Fretboard.getFretNumberByChord(chord, baseFret);
        // 打印标准调弦的指板
        for (int i = 6; i >= 1; i--) {
            StringBuilder sb = new StringBuilder(7 - i + ":");
            String tmp = null;
            for (int j = 0; j < MAX_FRET; j++) {
                if (fretNumberSequence.get(i - 1) == j) {
                    tmp = getNote(i, j).getName();
                } else {
                    tmp = "  ";
                }
                sb.append("|\t").append(tmp).append("\t|");
            }
            System.out.println(sb);
        }
    }

    public static void main(String[] args) {
        Chord chord = new MajorTriads(Note.C);
        printChord(chord, 0);
    }
}
