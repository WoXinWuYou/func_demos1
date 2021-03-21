package com.lmj.http.entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 报警信息
 * @author 李梦杰
 * @date 2020-11-23
 */
@XmlRootElement(name = "alarmInformation")
@XmlAccessorType(XmlAccessType.FIELD)
public class AlarmInfo {
	@XmlElement(name = "ID") private String id; // 报警数据 ID.车辆检测流水号
	@XmlElement(name = "HPHM") private String hphm; // 号牌号码.
	@XmlElement(name = "HPZLID") private String hpzlid; // 号牌种类代码.
	@XmlElement(name = "HPZL") private String hpzl; // 号牌种类.
	@XmlElement(name = "CLSBDH") private String clsbdh; // 车辆识别代号.别名：VIN 或大架号
	@XmlElement(name = "StaID") private String staid; // 检测站编码.
	@XmlElement(name = "JCFFID") private String jcffid; // 检测方法代码.D-怠速，S-双怠速A-Asm，V-VMAS，Y-自由加速(烟度)，B-自由加速(不透光) L-加载减速
	@XmlElement(name = "JCFFIDTAG") private String jcffidtag; // 检测方法.
	@XmlElement(name = "ZHJCRQ") private String zhjcrq; // 最后检测日期.
	@XmlElement(name = "Message") private String message; // 数据异常原因.
	
	private Date createDate;
	private Date updateDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHphm() {
		return hphm;
	}
	public void setHphm(String hphm) {
		this.hphm = hphm;
	}
	public String getHpzlid() {
		return hpzlid;
	}
	public void setHpzlid(String hpzlid) {
		this.hpzlid = hpzlid;
	}
	public String getHpzl() {
		return hpzl;
	}
	public void setHpzl(String hpzl) {
		this.hpzl = hpzl;
	}
	public String getClsbdh() {
		return clsbdh;
	}
	public void setClsbdh(String clsbdh) {
		this.clsbdh = clsbdh;
	}
	public String getStaid() {
		return staid;
	}
	public void setStaid(String staid) {
		this.staid = staid;
	}
	public String getJcffid() {
		return jcffid;
	}
	public void setJcffid(String jcffid) {
		this.jcffid = jcffid;
	}
	public String getJcffidtag() {
		return jcffidtag;
	}
	public void setJcffidtag(String jcffidtag) {
		this.jcffidtag = jcffidtag;
	}
	public String getZhjcrq() {
		return zhjcrq;
	}
	public void setZhjcrq(String zhjcrq) {
		this.zhjcrq = zhjcrq;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
