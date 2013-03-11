/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.chord4j.chord;

import java.util.Arrays;
import java.util.List;

/**
 * 和弦后缀名
 * http://zh.wikipedia.org/wiki/%E4%B8%89%E5%92%8C%E5%BC%A6#.E4.B8.89.E5.92
 * .8C.E5.BC.A6
 * 
 * @author wangfei
 * @created 2013-2-17
 * 
 * @version 1.0
 */
public enum ChordCategory {
    /**
     * 三和弦
     */
    MAJOR_TRIAD(3001, MajorTriad.class, Arrays.asList("", "M", "maj", "△"), Arrays.asList(
            Interval.M3, Interval.P5)),

    MINOR_TRIAD(3002, MinorTriad.class, Arrays.asList("m", "min", "-", "-△"), Arrays.asList(
            Interval.m3, Interval.P5)),

    AUGMENTED_TRIAD(3003, AugmentedTriad.class, Arrays.asList("aug", "+", "+5", "(#5)", "（#5）",
            "+△", "△+5", "△(#5)", "△（#5）"), Arrays.asList(Interval.M3, Interval.A5)),

    DIMINISHED_TRIAD(3004, DiminishedTriad.class, Arrays.asList("dim", "o", "o△", "m-5", "m（♭5）",
            "m(♭5)"), Arrays.asList(Interval.m3, Interval.d5)),
    /**
     * 挂留和弦
     */
    SUSPENDED2(3005, Suspended2.class, Arrays.asList("sus2"), Arrays.asList(Interval.P4,
            Interval.P5)),

    SUSPENDED4(3006, Suspended4.class, Arrays.asList("sus4"), Arrays.asList(Interval.M2,
            Interval.P5)),
    /**
     * 七和弦
     */
    DOMINANT_SEVENTH(7001, DominantSeventh.class, Arrays.asList("7", "Mm7"), Arrays.asList(
            Interval.M3, Interval.P5, Interval.m7)),

    MAJOR_SEVENTH(7002, MajorSeventh.class, Arrays.asList("M7", "MM7", "maj7"), Arrays.asList(
            Interval.M3, Interval.P5, Interval.M7)),

    MINOR_SEVENTH(7003, MinorSeventh.class, Arrays.asList("m7", "mm7", "-7"), Arrays.asList(
            Interval.m3, Interval.P5, Interval.m7)),

    HALF_DIMINISHED_SEVENTH(7004, HalfDiminishedSeventh.class, Arrays.asList("half-dim7", "m7-5",
            "m7（♭5）", "m7(♭5)"), Arrays.asList(Interval.m3, Interval.d5, Interval.m7)),

    MINOR_MAJOR_SEVENTH(7005, MinorMajorSeventh.class, Arrays.asList("mM7"), Arrays.asList(
            Interval.m3, Interval.P5, Interval.M7)),

    DIMINISHED_SEVENTH(7006, DiminishedSeventh.class, Arrays.asList("dim7", "o7"), Arrays.asList(
            Interval.m3, Interval.d5, Interval.d7)),

    AUGMENTED_MAJOR_SEVENTH(7007, AugmentedMajorSeventh.class, Arrays.asList("aug7", "（#5）M7",
            "(#5)M7"), Arrays.asList(Interval.M3, Interval.A5, Interval.M7)),
    /**
     * 九和弦
     */
    NORMAL_NINTH(9001, NormalNinth.class, Arrays.asList("9"), Arrays.asList(Interval.M3,
            Interval.P5, Interval.m7, Interval.M9)),

    /**
     * 六和弦
     */
    MAJOR_SIXTH(6001, MajorSixth.class, Arrays.asList("6"), Arrays.asList(Interval.M2, Interval.m2,
            Interval.M6));
    /**
     * 十一和弦 TODO
     */
    /**
     * 十三和弦 TODO
     */

    private List<String> postfixList;
    private List<Interval> intervalList;
    private Class<? extends Chord> chordClass;
    private Integer chordId;

    private ChordCategory(Integer chordId, Class<? extends Chord> chordClass, List<String> list,
            List<Interval> intervalList) {
        this.postfixList = list;
        this.chordClass = chordClass;
        this.intervalList = intervalList;
        this.chordId = chordId;
    }

    /**
     * 根据后缀找类别
     * 
     * @author wangfei
     * @param postfix
     * @return
     */
    public static ChordCategory getClassByPostfix(String postfix) {
        for (ChordCategory cc : ChordCategory.values()) {
            if (cc.getPostfixList().contains(postfix)) {
                return cc;
            }
        }
        return null;
    }

    /**
     * 根据和弦类找和弦类别
     * 
     * @author wangfei
     * @param class1
     * @return
     */
    public static ChordCategory getByChordClass(Class<? extends Chord> clazz) {
        for (ChordCategory cc : ChordCategory.values()) {
            if (cc.getChordClass().equals(clazz)) {
                return cc;
            }
        }
        return null;
    }

    /**
     * @author wangfei
     * @return
     */
    public static ChordCategory getByChordId(Integer chordId) {
        for (ChordCategory cc : ChordCategory.values()) {
            if (cc.getChordId().equals(chordId)) {
                return cc;
            }
        }
        return null;
    }

    public List<String> getPostfixList() {
        return postfixList;
    }

    public Class<? extends Chord> getChordClass() {
        return chordClass;
    }

    public List<Interval> getIntervalList() {
        return intervalList;
    }

    public String getMain() {
        return postfixList.get(0);
    }

    public Integer getChordId() {
        return chordId;
    }

}
