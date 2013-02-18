/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.chord4j.chord.dic;

/**
 * 音程 http://en.wikipedia.org/wiki/Interval_(music)
 * 
 * @author wangfei
 * @created 2013-1-10
 * 
 * @version 1.0
 */
public enum Interval {
    P1(0, "Perfect unison", "P1", Category.PERFECT),
    d2(0,"Diminished second","d2",Category.DIMINISHED),
    m2(1,"Minor second","m2",Category.MINOR),
    A1(1,"Augmented unison","A1",Category.AURGMENTED),
    M2(2,"Major second","M2",Category.MAJOR),
    d3(2,"Diminished third","d3",Category.DIMINISHED),
    m3(3,"Minor third","m3",Category.MINOR),
    A2(3,"Augmented second","A2",Category.AURGMENTED),
    M3(4,"Major third","M3",Category.MAJOR),
    d4(4,"Diminished fourth","d4",Category.DIMINISHED),
    P4(5,"Perfect fourth","P4",Category.PERFECT),
    A3(5,"Augmented third","A3",Category.AURGMENTED),
    d5(6,"Diminished fifth","d5",Category.DIMINISHED),
    A4(6,"Augmented fourth","A4",Category.AURGMENTED),
    P5(7,"Perfect fifth","P5",Category.PERFECT),
    d6(7,"Diminished sixth","d6",Category.DIMINISHED),
    m6(8,"Minor sixth","m6",Category.MINOR),
    A5(8,"Augmented fifth","A5",Category.AURGMENTED),
    M6(9,"Major sixth","M6",Category.MAJOR),
    d7(9,"Diminished seventh","d7",Category.DIMINISHED),
    m7(10,"Minor seventh","m7",Category.MINOR),
    A6(10,"Augmented sixth","A6",Category.AURGMENTED),
    M7(11,"Major seventh","M7",Category.MAJOR),
    d8(11,"Diminished octave","d8",Category.DIMINISHED),
    P8(12,"Perfect octave","P8",Category.PERFECT),
    A7(12,"Augmented seventh","A7",Category.AURGMENTED);

    private Integer semitoneNum;// 半音数
    private Integer noteNum;// 包含的音名数
    private String name;
    private String shortName;
    private Category category;

    private Interval(Integer semitoneNum, String name, String shortName, Category category) {
        this.semitoneNum = semitoneNum;
        this.name = name;
        this.shortName = shortName;
        this.category = category;
        noteNum = Integer.parseInt(shortName.charAt(1) + "");
    }

    /**
     * 
     * 音程类型 大小增减全
     * 
     * @author wangfei
     * @created 2013-1-30
     * 
     * @version 1.0
     */
    private enum Category {
        MINOR, MAJOR, PERFECT, AURGMENTED, DIMINISHED
    }

    public Integer getSemitoneNum() {
        return semitoneNum;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public Category getCategory() {
        return category;
    }

    public Integer getNoteNum() {
        return noteNum;
    }

}
