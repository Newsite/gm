package com.naixwf.gm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chord_fret")
public class ChordFret {
    //
    private Integer id;
    // å’Œå¼¦id
    private Integer chordId;
    // æ ¹éŸ³
    private String rootNote;
    // æŒ‡æ¿æ•°å€¼åºåˆ— ä»Ž6å¼¦åˆ°1å¼¦
    private String fretNumbers;
    // æŒ‡æ³•åºåˆ— ä»Ž6å¼¦åˆ°1å¼¦
    private String finger;
    // å¤‡æ³¨
    private String comment;
    // 和弦名称
    private String name;
    private Integer position;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "chord_id")
    public Integer getChordId() {
        return this.chordId;
    }

    public void setChordId(Integer chordId) {
        this.chordId = chordId;
    }

    @Column(name = "position")
    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "root_note")
    public String getRootNote() {
        return this.rootNote;
    }

    public void setRootNote(String rootNote) {
        this.rootNote = rootNote;
    }

    @Column(name = "fret_numbers")
    public String getFretNumbers() {
        return this.fretNumbers;
    }

    public void setFretNumbers(String fretNumbers) {
        this.fretNumbers = fretNumbers;
    }

    @Column(name = "finger")
    public String getFinger() {
        return this.finger;
    }

    public void setFinger(String finger) {
        this.finger = finger;
    }

    @Column(name = "comment")
    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
