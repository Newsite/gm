/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.gm.crawl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.naixwf.chord4j.chord.Chord;
import com.naixwf.chord4j.chord.ChordCategory;
import com.naixwf.chord4j.chord.Note;
import com.naixwf.gm.domain.ChordFret;
import com.naixwf.core.util.ArrayListUtil;

/**
 * 
 * @author wangfei
 * @created 2013-2-26
 * 
 * @version 1.0
 */
public class Ijita extends BaseCrawl {
    private static final Logger logger = LoggerFactory.getLogger(Ijita.class);

    public Ijita() {
    }

    /**
     * 根据和弦名称获取按法
     * 
     * @author wangfei
     * @param chordName
     * @return
     */
    public List<ChordFret> crawlFretFromIjita(Chord chord) {
        String url = "http://ijita.com/chord/make.php";
        String name = chord.getName();
        if (name.contains("♯")) {
            name = name.replaceAll("♯", "");
            name = "#" + name;
        }
        if (name.contains("♭")) {
            name = name.replaceAll("♭", "");
            name = "b" + name;
        }
        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
        nvps.add(new BasicNameValuePair("action", "cq"));
        nvps.add(new BasicNameValuePair("c", name));
        HttpPost httpPost = new HttpPost(url);

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        } catch (UnsupportedEncodingException e) {
            logger.error("", e);
        }
        // header
        httpPost.addHeader("Referer", "http://ijita.com/tool/chord/");
        String result = post(httpPost);

        return transXml2ChordFret(result, chord);
    }

    /**
     * @author wangfei
     * @param result
     * @return
     */
    private List<ChordFret> transXml2ChordFret(String result, Chord chord) {
        List<ChordFret> list = new ArrayList<ChordFret>();
        try {

            String[] chordList = result.split("desenhaAcorde");
            for (int i = 1; i < chordList.length; i++) {

                ChordFret chordFret = new ChordFret();
                chordFret.setChordId(chord.getChordId());
                chordFret.setComment("from ijita 20130226");
                chordFret.setFinger("1,1,1,1,1,1");// TODO
                chordFret.setName(chord.getName());

                chordFret.setRootNote(chord.getRootNote().getName());

                String[] tmp = chordList[i].split(",")[2].replaceAll("'", "")
                        .replaceAll("</script>", "").substring(1).replaceAll("\\);", "").split(" ");
                List<Integer> integerList = new ArrayList<Integer>();
                Integer min = Integer.MAX_VALUE;
                for (String x : tmp) {
                    if (x.contains("P")) {// 有拇指
                        chordFret.setFinger("2,2,2,2,2,2");// TODO
                        x = x.replaceAll("P", "");
                    }
                    Integer num = transChar2Num(x);
                    if (num == null) {
                        logger.debug(x);
                        continue;

                    }
                    if (num >= 0 && num < min) {
                        min = num;
                    }
                    integerList.add(num);
                }
                // 指板
                chordFret.setPosition(min);

                for (int x = 0; x < 6; x++) {
                    Integer t = null;
                    if (x > integerList.size() - 1) {
                        integerList.add(-1);
                        continue;
                    } else {
                        t = integerList.get(x);
                    }

                    if (min > 0 && t != -1) {
                        integerList.set(x, t - min + 1);
                    }
                }
                chordFret.setFretNumbers(ArrayListUtil.list2String(integerList, ","));
                list.add(chordFret);
            }
        } catch (Exception e) {

            e.printStackTrace();
            logger.debug(chord.getName() + ":无法解析:" + e.getMessage());
            return null;
        }
        return list;
    }

    private Integer transChar2Num(final String chara) {
        String tmp = chara;
        tmp = tmp.replaceAll("X", "-1");
        tmp = tmp.replaceAll("S", "0");
        tmp = tmp.replaceAll("A", "1");
        tmp = tmp.replaceAll("D", "2");
        tmp = tmp.replaceAll("F", "3");
        tmp = tmp.replaceAll("G", "4");
        tmp = tmp.replaceAll("H", "5");
        tmp = tmp.replaceAll("J", "6");
        tmp = tmp.replaceAll("K", "7");
        tmp = tmp.replaceAll("L", "8");
        tmp = tmp.replaceAll("O", "9");
        Integer integer = null;
        try {
            integer = Integer.parseInt(tmp);
            return integer;
        } catch (Exception e) {
            logger.info("", e);
        }

        return null;
    }

    public static void main(String[] args) {
        Chord test = Chord.newChord(Note.E, ChordCategory.MAJOR_TRIAD.getMain());

        Ijita ijita = new Ijita();
        List<ChordFret> chordFrets = ijita.crawlFretFromIjita(test);
        if (chordFrets != null) {
            for (ChordFret chordFret : chordFrets) {
                System.out.println(chordFret.getName() + ":" + chordFret.getPosition() + ":"
                        + chordFret.getFretNumbers());
            }
        }
        // for (ChordCategory cc : ChordCategory.values()) {
        // for (Note n : Note.values()) {
        // Chord chord = Chord.newChord(n, cc.getMain());
        // if (chordCrawled.contains(chord)) {
        // continue;
        // }
        // logger.debug("start crawl " + chord.getName());
        // chordCrawled.add(chord);
        // List<ChordFret> chordFrets = ijita.crawlFretFromIjita(chord);
        // if (chordFrets != null) {
        // for (ChordFret chordFret : chordFrets) {
        // System.out.println(chordFret.getName() + ":" +
        // chordFret.getFretNumbers()
        // + ":" + chordFret.getPosition());
        // }
        // }
        // }
        // }
    }
}
