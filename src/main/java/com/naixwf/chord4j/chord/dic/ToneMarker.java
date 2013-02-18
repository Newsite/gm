/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.chord4j.chord.dic;

/**
 * TODO 升降调标记
 * 
 * @author wangfei
 * @created 2013-1-14
 * 
 * @version 1.0
 */
public enum ToneMarker {
    NONE(0, ' '), SHARP(1, '♯'), FLAT(-1, 'b');
    private int index;
    private char name;

    ToneMarker(int value, char name) {
        this.index = value;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public char getName() {
        return name;
    }

    public static Integer getIndex(char name) {
        for (ToneMarker toneMarker : ToneMarker.values()) {
            if (name == toneMarker.getName()) {
                return toneMarker.getIndex();
            }
        }
        return null;
    }

    public static Character getName(int index) {
        for (ToneMarker toneMarker : ToneMarker.values()) {
            if (index == toneMarker.getIndex()) {
                return toneMarker.getName();
            }
        }
        return null;
    }

    public static ToneMarker get(char name) {
        for (ToneMarker toneMarker : ToneMarker.values()) {
            if (name == toneMarker.getName()) {
                return toneMarker;
            }
        }
        return null;
    }

    public static ToneMarker get(int index) {
        for (ToneMarker toneMarker : ToneMarker.values()) {
            if (index == toneMarker.getIndex()) {
                return toneMarker;
            }
        }
        return null;
    }
}
