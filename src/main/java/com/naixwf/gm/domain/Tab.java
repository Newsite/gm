package com.naixwf.gm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tab")
public class Tab{
	//
	private Integer id;
	//曲名
	private String name;
	//歌手/乐队
	private String singer;
	//编排、来源
	private String source;
	//曲谱内容
	private String content;
	private String site;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	public Integer getId(){
		return this.id;
	}
	public void setId(Integer id){
		this.id=id;
	}
	@Column(name="name")
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}
	@Column(name="singer")
	public String getSinger(){
		return this.singer;
	}
	public void setSinger(String singer){
		this.singer=singer;
	}
	@Column(name="source")
	public String getSource(){
		return this.source;
	}
	public void setSource(String source){
		this.source=source;
	}
	@Column(name="content")
	public String getContent(){
		return this.content;
	}
	public void setContent(String content){
		this.content=content;
	}
	@Column(name="site")
    public String getSite() {
        return site;
    }
    public void setSite(String site) {
        this.site = site;
    }
	
}
