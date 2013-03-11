/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.chord4j.chord;

import java.util.Arrays;
import java.util.List;

/**
 * 十二平均律音名 http://en.wikipedia.org/wiki/Chromatic_circle
 * 
 * @author wangfei
 * @created 2013-1-30
 * @version 1.0
 */
public enum Note {
    C(Arrays.asList("C"), 0), C_SHARP(Arrays.asList("C♯", "D♭"), 1), D(Arrays.asList("D"), 2), D_SHARP(
            Arrays.asList("D♯", "E♭"), 3), E(Arrays.asList("E"), 4), F(Arrays.asList("F"), 5), F_SHARP(
            Arrays.asList("F♯", "G♭"), 6), G(Arrays.asList("G"), 7), G_SHARP(Arrays.asList("G♯",
            "A♭"), 8), A(Arrays.asList("A"), 9), A_SHARP(Arrays.asList("A♯", "B♭"), 10), B(Arrays
            .asList("B"), 11);
    Note(List<String> nameList, Integer index) {
        this.nameList = nameList;
        this.index = index;
    }

    private List<String> nameList;
    private Integer index;

    /**
     * 返回offset个半音之后的note
     * 
     * @author wangfei
     * @param offset
     * @return
     */
    public Note add(Integer offset) {
        Integer newIndex = (index + offset) % 12;
        return getByIndex(newIndex);
    }

    public static Note getByName(String name) {
        String waitForReplace = null;
        if (name.contains("#") || name.contains("♯")) {
            waitForReplace = "♯";
            name = name.replace("#", "");
            name = name.replace("♯", "");
        }
        if (name.contains("b") || name.contains("♭")) {
            waitForReplace = "♭";
            name = name.replace("b", "");
            name = name.replace("♭", "");
        }
        if (waitForReplace != null) {
            name = name + waitForReplace;
        }

        for (Note note : Note.values()) {
            if (note.getNameList().contains(name)) {
                return note;
            }
        }
        return null;
    }

    public static Note getByIndex(Integer index) {
        for (Note note : Note.values()) {
            if (index.equals(note.getIndex())) {
                return note;
            }
        }
        return null;
    }

    public String getName() {
        return nameList.get(0);
    }

    public Integer getIndex() {
        return index;
    }

    public List<String> getNameList() {
        return nameList;
    }

}
