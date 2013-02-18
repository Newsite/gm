/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.chord4j.chord.dic;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * 和弦标识 http://zh.wikipedia.org/wiki/%E4%B8%89%E5%92%8C%E5%BC%A6
 * 
 * @author wangfei
 * @created 2013-1-15
 * 
 * @version 1.0
 */
public abstract class Chord {
    private Note root;

    protected Chord(Note root) {
        this.root = root;
    }

    /**
     * 返回和弦名
     * 
     * @author wangfei
     * @return
     */
    public String getStringName() {
        StringBuilder sb = new StringBuilder();
        sb.append(getRootNote().getName()).append(getPostfix());
        return sb.toString();
    }

    /**
     * 将一个字符串转换为和弦
     * 
     * @author wangfei
     * @param str
     * @return
     */
    public static Chord string2Chord(String str) {
        String noteName = null;// 大写字母
        for (int j = 0, l = str.length(); j < l; j++) {
            char tmp = str.charAt(j);
            if (tmp >= 'A' && tmp <= 'Z') {// 找到大写字母
                noteName = tmp + "";
                if (str.contains(ToneMarker.SHARP.getName() + "") || str.contains("#")) {
                    noteName = tmp + "" + ToneMarker.SHARP.getName();
                }
                if (str.contains(ToneMarker.FLAT.getName() + "")) {
                    noteName = tmp + "" + ToneMarker.FLAT.getName();
                }
                Note root = Note.getByName(noteName);
                String postfix = str.substring(j + 1);
                return newChord(root, postfix);
            }
        }
        throw new RuntimeException(String.format("转换失败,string=", str));
    }

    public static Chord newChord(Note root, String postfix) {
        Chord chord = null;
        if (ChordPostfix.MAJOR_TRIAD.getList().contains(postfix)) {
            chord = new MajorTriads(root);
        } else if (ChordPostfix.MINOR_TRIAD.getMain().contains(postfix)) {
            chord = new MinorTriads(root);
        } else if (ChordPostfix.DIMINISHED_TRIAD.getMain().contains(postfix)) {
            chord = new DiminishedTriads(root);
        } else if (ChordPostfix.AUGMENTED_TRIAD.getMain().contains(postfix)) {
            chord = new AugmentedTriads(root);
        } else if (ChordPostfix.DOMINANT_SEVENTH.getMain().contains(postfix)) {
            chord = new DominantSevenths(root);
        } else {
            throw new RuntimeException("没有找到这个和弦后缀:" + postfix);
        }
        return chord;
    }

    /**
     * 获取和弦内的音名列表
     * 
     * @author wangfei
     * @return
     */
    public List<Note> getNoteList() {
        List<Note> list = new ArrayList<Note>();
        Note root = getRootNote();
        assert root != null;
        List<Interval> intervalList = getIntervalList();
        assert intervalList != null;
        assert getNoteNum() == (intervalList.size() + 1);

        list.add(root);
        for (Interval interval : intervalList) {
            list.add(root.add(interval.getSemitoneNum()));
        }
        return list;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getRootNote().getName()).append(getPostfix()).append("\t");
        sb.append(root.getName());
        for (Interval interval : getIntervalList()) {
            Note tmp = root.add(interval.getSemitoneNum());
            sb.append("-").append(tmp.getName());
        }
        return sb.toString();
    }

    /**
     * 获取根音
     * 
     * @author wangfei
     * @return
     */
    public Note getRootNote() {
        return root;
    }

    /**
     * 各音与根音的音程关系
     */
    protected abstract List<Interval> getIntervalList();

    /**
     * 由几个音构成
     */
    protected abstract Integer getNoteNum();

    /**
     * 和弦名称后缀
     * 
     * @author wangfei
     * @return
     */
    public abstract String getPostfix();

}
