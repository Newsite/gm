/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.chord4j.chord;


/**
 * 音高
 * 
 * @author wangfei
 * @created 2013-1-10
 * 
 * @version 1.0
 */
public class Pitch {
//    private static final Logger logger = LoggerFactory.getLogger(Pitch.class);
//    private static final int A4_VALUE = 69;
//    private static final double A4_FREQUENCY = 440f;
//    private static final int OCTAVE_DEFAULT = 4;// 默认八度区
//    // 第4八度区的MIDI标准value map
//    private static Map<String, Integer> octave4ValueMap;
//    // MIDI标准数值
//    private int value;
//    // 频率 Hz
//    private double frequency;
//    /** 科学音调标记法 **/
//    // 写法(科学音调记号法) #C4
//    private String name;
//    // 八度区 4
//    private int octave;
//    // 音名 C
//    private char character;
//    // 升降调标记
//    private ToneMarker toneMarker;
//
//    public Pitch(String name) {
//        try {
//            this.name = name;
//            // 升降号
//            char markerName = name.charAt(0);
//            ToneMarker toneMarker = ToneMarker.get(markerName);
//            if (toneMarker != null) {
//                this.toneMarker = toneMarker;
//                name = name.substring(1);// 将升降号部分去除
//            } else {
//                toneMarker = ToneMarker.get(0);
//            }
//            // 音名 八度区
//            this.character = name.charAt(0);
//            if (name.length() > 1) {
//                this.octave = Integer.parseInt(name.charAt(1) + "");
//            } else {
//                this.octave = OCTAVE_DEFAULT;
//            }
//            // value
//            logger.debug(this.character + "");
//            Integer index = getOctave4().get(this.character + "");
//            if (index == null) {
//                throw new RuntimeException("pitch name=" + name + " 无法被识别");
//            }
//            this.value = index + (this.octave - OCTAVE_DEFAULT) * 12 + toneMarker.getIndex();// 12平均律
//            this.frequency = getFrequencyByValue(value);
//        } catch (Exception e) {
//            logger.error("生成note错误:" + name, e);
//        }
//    }
//
//    /**
//     * 根据数值获取基频
//     * 
//     * @author wangfei
//     * @param value2
//     * @return
//     */
//    private double getFrequencyByValue(int value) {
//        return (A4_FREQUENCY * Math.pow(2, (value - A4_VALUE) / 12f));
//    }
//
//    /**
//     * 
//     * @param value
//     *            数值
//     * @param character
//     *            主音
//     */
//    public Pitch(int value, char character) {
//        this.value = value;
//        this.frequency = getFrequencyByValue(value);
//    }
//
//    private Map<String, Integer> getOctave4() {
//        if (octave4ValueMap == null) {
//            octave4ValueMap = new HashMap<String, Integer>();
//            octave4ValueMap.put("C", A4_VALUE - 9);
//            octave4ValueMap.put("#C", A4_VALUE - 8);
//            octave4ValueMap.put("D", A4_VALUE - 7);
//            octave4ValueMap.put("bE", A4_VALUE - 6);
//            octave4ValueMap.put("E", A4_VALUE - 5);
//            octave4ValueMap.put("F", A4_VALUE - 4);
//            octave4ValueMap.put("#F", A4_VALUE - 3);
//            octave4ValueMap.put("G", A4_VALUE - 2);
//            octave4ValueMap.put("#G", A4_VALUE - 1);
//            octave4ValueMap.put("A", A4_VALUE);
//            octave4ValueMap.put("bB", A4_VALUE + 1);
//            octave4ValueMap.put("B", A4_VALUE + 2);
//        }
//        return octave4ValueMap;
//    }
//
//    /**
//     * 获取音高数值
//     * 
//     * @author wangfei
//     * @return
//     */
//    public int getValue() {
//        return value;
//    }
//
//    /**
//     * 获取频率
//     * 
//     * @author wangfei
//     * @return
//     */
//    public double getFrequency() {
//        return frequency;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public int getOctave() {
//        return octave;
//    }
//
//    public char getCharacter() {
//        return character;
//    }
//
//    public ToneMarker getToneMarker() {
//        return toneMarker;
//    }
//
//    public String toString() {
//        return String.format("name=%s,octave=%d,character=%c,toneMarker=%s,value=%d,frequency=%f",
//                name, octave, character, toneMarker == null ? null : toneMarker.getName(), value,
//                frequency);
//    }
//
//    /**
//     * 返回距离当前音高interval的pitch
//     * 
//     * @author wangfei
//     * @param interval
//     * @return
//     */
//    public Pitch add(int interval) {
//        String name = getNameByValue(getValue() + interval);
//        return new Pitch(name);
//    }
//
//    /**
//     * 根据音高数值返回音名
//     * 
//     * @author wangfei
//     * @param value
//     * @return
//     */
//    private String getNameByValue(int value) {
//        int value0 = getOctave4().get("C");
//        int tmp = value - value0;
//        tmp = value % 12;
//        if (tmp < 0) {
//            tmp += 12;
//        }
//        tmp += value0;
//        for (String key : getOctave4().keySet()) {
//            Integer v = getOctave4().get(key);
//            if (v.equals(tmp)) {
//                return key;
//            }
//        }
//        logger.warn("value=" + value + ":找不到音名");
//        return null;
//    }
//
//    public static void main(String[] args) {
//        Pitch p = new Pitch("#F");
//        System.out.println(p.getName());
//    }
}
