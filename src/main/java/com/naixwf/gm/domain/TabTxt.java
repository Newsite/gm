package com.naixwf.gm.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.naixwf.gm.web.vo.TabContentVo;

@Entity
@Table(name = "tab_txt")
public class TabTxt {
    //
    private Integer id;
    // 曲名
    private String name;
    // 歌手id
    private Integer singerId;
    // 歌手
    private String singer;
    // 曲谱内容
    private String content;
    // 备注
    private String comment;
    // 原调
    private String keyOrigin;
    // 选调
    private String keyChosen;
    // 添加时间
    private Date addTime;
    //
    private Date lastTs;
    // 内容vo
    private TabContentVo contentVo;

    @Transient
    public TabContentVo getContentVo() {
        return contentVo;
    }

    public void setContentVo(TabContentVo contentVo) {
        this.contentVo = contentVo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "singer_id")
    public Integer getSingerId() {
        return this.singerId;
    }

    public void setSingerId(Integer singerId) {
        this.singerId = singerId;
    }

    @Column(name = "singer")
    public String getSinger() {
        return this.singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    @Column(name = "content")
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "comment")
    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Column(name = "key_origin")
    public String getKeyOrigin() {
        return this.keyOrigin;
    }

    public void setKeyOrigin(String keyOrigin) {
        this.keyOrigin = keyOrigin;
    }

    @Column(name = "key_chosen")
    public String getKeyChosen() {
        return this.keyChosen;
    }

    public void setKeyChosen(String keyChosen) {
        this.keyChosen = keyChosen;
    }

    @Column(name = "add_time")
    public Date getAddTime() {
        return this.addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    @Column(name = "last_ts")
    public Date getLastTs() {
        return this.lastTs;
    }

    public void setLastTs(Date lastTs) {
        this.lastTs = lastTs;
    }
}
