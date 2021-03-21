package com.lmj.http.entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 检测结果信息
 * @author 李梦杰
 * @date 2021-1-15
 */
@XmlRootElement(name = "DetData")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResultInfo {
	private String id;
	@XmlElement(name = "DetLsh") private String detlsh; // 流水号（唯一键）
	@XmlElement(name = "HPHM") private String hphm; // 号牌号码
	@XmlElement(name = "HPZLID") private String hpzlid; // 号牌种类代码
	@XmlElement(name = "HPZL") private String hpzl; // 号牌种类
	@XmlElement(name = "JCLBID") private String jclbid; // 检测类别代码
	@XmlElement(name = "JCLB") private String jclb; // 检测类别
	@XmlElement(name = "CCJCRQ") String ccjcrq; // 初检时间
	@XmlElement(name = "ZHJCRQ") String zhjcrq; // 末次检测时间
	@XmlElement(name = "Evl") private String evl; // 检测结果
	@XmlElement(name = "StaName") private String staname; // 末次检测站名称
	@XmlElement(name = "PFBZ") private String pfbz; // 排放等级

	private Date createDate;
	private Date updateDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDetlsh() {
		return detlsh;
	}
	public void setDetlsh(String detlsh) {
		this.detlsh = detlsh;
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
	public String getJclbid() {
		return jclbid;
	}
	public void setJclbid(String jclbid) {
		this.jclbid = jclbid;
	}
	public String getJclb() {
		return jclb;
	}
	public void setJclb(String jclb) {
		this.jclb = jclb;
	}
	public String getCcjcrq() {
		return ccjcrq;
	}
	public void setCcjcrq(String ccjcrq) {
		this.ccjcrq = ccjcrq;
	}
	public String getZhjcrq() {
		return zhjcrq;
	}
	public void setZhjcrq(String zhjcrq) {
		this.zhjcrq = zhjcrq;
	}
	public String getEvl() {
		return evl;
	}
	public void setEvl(String evl) {
		this.evl = evl;
	}
	public String getStaname() {
		return staname;
	}
	public void setStaname(String staname) {
		this.staname = staname;
	}
	public String getPfbz() {
		return pfbz;
	}
	public void setPfbz(String pfbz) {
		this.pfbz = pfbz;
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
