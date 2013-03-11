/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.naixwf.chord4j.chord.Chord;
import com.naixwf.gm.domain.ChordFret;

/**
 * @author wangfei
 * @created 2013-2-21
 * 
 * @version 1.0
 */
@Repository
public class ChordFretDao extends BaseDao<ChordFret> {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(ChordFretDao.class);

    /**
     * 根据和弦类型和根音查找
     * 
     * @author wangfei
     * @param chordId
     * @param rootNoteName
     */
    public List<ChordFret> listByChordIdAndRootNote(Integer chordId, String rootNoteName) {
        DetachedCriteria crit = DetachedCriteria.forClass(ChordFret.class);
        crit.add(Restrictions.eq("chordId", chordId))
                .add(Restrictions.eq("rootNote", rootNoteName));
        return list(crit);
    }

    /**
     * @author wangfei
     * @param chordList
     * @return
     */
    public List<ChordFret> listByChords(List<Chord> chordList) {
        List<ChordFret> list = new ArrayList<ChordFret>();

        List<String> nameList = new ArrayList<String>();
        if (chordList != null && !chordList.isEmpty()) {
            for (Chord chord : chordList) {
                nameList.add(chord.getName());
            }
        }
        if (!nameList.isEmpty()) {
            DetachedCriteria crit = DetachedCriteria.forClass(ChordFret.class);
            crit.add(Restrictions.in("name", nameList));
            list = list(crit);
        }

        return list;
    }
}
