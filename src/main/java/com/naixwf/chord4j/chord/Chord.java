/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.chord4j.chord;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import com.naixwf.chord4j.guitar.Fretboard;

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
    public String getName() {
        StringBuilder sb = new StringBuilder();
        sb.append(getRootNote().getName()).append(getPostfix());
        return sb.toString();
    }

    public List<Integer> getFretNumbers() {
        return Fretboard.getFretNumberByChord(this, 0);
    }

    /**
     * 获取和弦种类id
     * 
     * @author wangfei
     * @return
     */
    public Integer getChordId() {
        ChordCategory cc = ChordCategory.getByChordClass(this.getClass());
        return cc.getChordId();
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
                String postfix = str.substring(j + 1);// 后缀
                noteName = tmp + "";
                if (str.contains(ToneMarker.SHARP.getName() + "") || str.contains("#")) {
                    noteName = tmp + "" + ToneMarker.SHARP.getName();
                    // 针对C♯m 这种情况去除升降号
                    postfix = postfix.replaceAll("#", "");
                    postfix = postfix.replaceAll(ToneMarker.SHARP.getName() + "", "");
                }
                if (str.contains(ToneMarker.FLAT.getName() + "")) {
                    noteName = tmp + "" + ToneMarker.FLAT.getName();
                    postfix = postfix.replaceAll("b", "");
                    postfix = postfix.replaceAll(ToneMarker.FLAT.getName() + "", "");
                }
                Note root = Note.getByName(noteName);
                return newChord(root, postfix);
            }
        }
        throw new RuntimeException(String.format("转换失败,string=", str));
    }

    /**
     * 根据根音和和弦名称后缀，生成和弦
     * 
     * @author wangfei
     * @param root
     * @param postfix
     * @return
     */
    public static Chord newChord(Note root, String postfix) {
        assert root != null;
        assert postfix != null;

        ChordCategory cc = ChordCategory.getClassByPostfix(postfix);
        if (cc == null) {
            throw new RuntimeException("没有找到这个和弦后缀:" + postfix);
        }

        Class<? extends Chord> clazz = cc.getChordClass();
        Constructor<? extends Chord> c;

        Chord chord = null;
        try {
            c = clazz.getConstructor(Note.class);
            chord = c.newInstance(root);
        } catch (Exception e) {
            throw new RuntimeException("构造chord失败:" + postfix, e);
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
     * @author wangfei
     * @param o
     * @return
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        Chord c = (Chord) o;
        if (o == null) {
            return false;
        }
        return c.getRootNote().equals(this.getRootNote()) && this.getClass().equals(o.getClass());
    }

    @Override
    public int hashCode() {
        return this.getClass().hashCode() + this.getRootNote().hashCode();
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
    protected List<Interval> getIntervalList() {
        ChordCategory cc = ChordCategory.getByChordClass(this.getClass());
        if (cc == null) {
            throw new RuntimeException("ChordCategory中找不到这个和弦类:" + this.getClass());
        }
        return cc.getIntervalList();
    }

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
    public String getPostfix() {
        ChordCategory cc = ChordCategory.getByChordClass(this.getClass());
        if (cc == null) {
            throw new RuntimeException("ChordCategory中找不到这个和弦类:" + this.getClass());
        }
        return cc.getMain();
    }

    public static void main(String[] args) {
        Chord a = Chord.string2Chord("Em");
        Chord b = Chord.string2Chord("Em");
        System.out.println(a.hashCode() + ":" + b.hashCode());
        System.out.println(a.equals(b));
    }
}
