package com.lmj.http.entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 车辆信息
 * @author 李梦杰
 * @date 2020-11-23
 */
@XmlRootElement(name = "info")
@XmlAccessorType(XmlAccessType.FIELD)
public class VehicleInfo {
	private String id;
	@XmlElement(name = "HPHM") private String hphm; // 号牌号码.
	@XmlElement(name = "HPZLID") private String hpzlid; // 号牌种类代码.
	@XmlElement(name = "HPZL") private String hpzl; // 号牌种类.
	@XmlElement(name = "CLSBDH") private String clsbdh; // 车辆识别代码.
	@XmlElement(name = "XZQH") private String xzqh; // 所属区县.
	@XmlElement(name = "CLLB") private String cllb; // 车辆类别.
	@XmlElement(name = "CLLX") private String cllx; // 车辆类型.
	@XmlElement(name = "CCDJRQ") private String ccdjrq; // 登记日期.
	@XmlElement(name = "CCRQ") private String ccrq; // 出厂日期.
	@XmlElement(name = "SYR") private String syr; // 车主单位.
	@XmlElement(name = "SYRDZ") private String syrdz; // 车主地址.
	@XmlElement(name = "SYRLXDH") private String syrlxdh; // 车主电话.
	@XmlElement(name = "SYZT") private String syzt; // 使用情况.
	@XmlElement(name = "NYCJRCZ") private String nycjrcz; // 进入城镇农用车.0-否	1-是
	@XmlElement(name = "SYXZ") private String syxz; // 使用性质.
	@XmlElement(name = "FDJXH") private String fdjxh; // 发动机型号.
	@XmlElement(name = "FDJH") private String fdjh; // 发动机号.
	@XmlElement(name = "CLPP") private String clpp; // 车辆厂牌.
	@XmlElement(name = "CLXH") private String clxh; // 车辆型号.
	@XmlElement(name = "CLZZCMC") private String clzzcmc; // 汽车制造厂.
	@XmlElement(name = "FDJZZCMC") private String fdjzzcmc; // 发动机制造厂.
	@XmlElement(name = "QDXSID") private String qdxsid; // 驱动形式代码.
	@XmlElement(name = "QDXS") private String qdxs; // 驱动形式.
	@XmlElement(name = "BSXXS") private String bsxxs; // 变速箱类型.
	@XmlElement(name = "DWGS") private String dwgs; // 档位个数.
	@XmlElement(name = "QGS") private String qgs; // 气缸个数.
	@XmlElement(name = "PQGS") private String pqgs; // 排气管数.
	@XmlElement(name = "HDZK") private String hdzk; // 载客人数.
	@XmlElement(name = "DCZZ") private String dczz; // 单车轴重(kg).
	@XmlElement(name = "ZZL") private String zzl; // 总质量(kg).
	@XmlElement(name = "HDZZL") private String hdzzl; // 核定载质量(kg).
	@XmlElement(name = "ZBZL") private String zbzl; // 整备质量(kg).
	@XmlElement(name = "CSYS") private String csys; // 车身颜色.
	@XmlElement(name = "SYOBD") private String syobd; // 是否使用 OBD 标识.0-否	1-是
	@XmlElement(name = "SYOBDTAG") private String syobdtag; // 是否使用 OBD(是、否).
	@XmlElement(name = "EDZS") private String edzs; // 额定转速(r/min):3500.
	@XmlElement(name = "EDGL") private String edgl; // 额定功率(kw).
	@XmlElement(name = "PL") private String pl; // 发动机排量.
	@XmlElement(name = "GYFSID") private String gyfsid; // 供油方式代码.
	@XmlElement(name = "GYFS") private String gyfs; // 供油方式.
	@XmlElement(name = "RLZLID") private String rlzlid; // 燃料种类代码.
	@XmlElement(name = "RLZL") private String rlzl; // 燃料种类.
	@XmlElement(name = "JQFSID") private String jqfsid; // 进气方式代码.
	@XmlElement(name = "JQFS") private String jqfs; // 进气方式.
	@XmlElement(name = "MinLMD") private String minlmd; // λ最小值.
	@XmlElement(name = "MaxLMD") private String maxlmd; // λ最大值.
	@XmlElement(name = "MTCSCC") private String mtcscc; // 摩托车四冲程标识.
	@XmlElement(name = "MTCSCCTAG") private String mtcscctag; // 摩托车四冲程.
	@XmlElement(name = "Egr") private String egr; // 是否有 EGR 标识.0-无，1-有
	@XmlElement(name = "EgrTag") private String egrtag; // 是否有 EGR.
	@XmlElement(name = "Tg") private String tg; // 是否有燃油蒸发控制装置标识.0-无，1-有
	@XmlElement(name = "TgTag") private String tgtag; // 是否有燃油蒸发控制装置.
	@XmlElement(name = "HclType") private String hcltype; // 后处理种类.
	@XmlElement(name = "DPF") private String dpf; // DPF.0-无 1-有
	@XmlElement(name = "DPFXH") private String dpfxh; // DPF 型号.
	@XmlElement(name = "SCR") private String scr; // SCR.0-无 1-有
	@XmlElement(name = "SCRXH") private String scrxh; // SCR 型号.
	@XmlElement(name = "DPSCQY") private String dpscqy; // 底盘生产企业.
	@XmlElement(name = "CCHGZH") private String cchgzh; // 车辆出厂合格证号.
	@XmlElement(name = "SFDK") private String sfdk; // 是否电控标识.0-否 1-是
	@XmlElement(name = "SFDKTAG") private String sfdktag; // 是否电控.
	@XmlElement(name = "SFGBCSWD") private String sfgbcswd; // 是否能关闭车身稳定标识.0-否 1-是
	@XmlElement(name = "SFGBCSWDTAG") private String sfgbcswdtag; // 是否能关闭车身稳定.
	@XmlElement(name = "CLFL") private String clfl; // 车辆分类.
	@XmlElement(name = "DDJXH") private String ddjxh; // 电动机型号.
	@XmlElement(name = "CNZZXH") private String cnzzxh; // 储能装置型号.
	@XmlElement(name = "DCRL") private String dcrl; // 电池容量(千瓦时).
	@XmlElement(name = "CHZHQXH") private String chzhqxh; // 催化转化器型号.
	
	@XmlElement(name = "CLLBID") private String cllbid; // 车辆类别编码
	@XmlElement(name = "CLLXID") private String cllxid; // 车辆类型编码
	@XmlElement(name = "SYZTTAG") private String syzttag; // 使用情况描述
	@XmlElement(name = "SYXZID") private String syxzid; // 使用性质编码
	@XmlElement(name = "CSYSID") private String csysid; // 车身颜色编码

	private Date createDate;
	private Date updateDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getHphm() {
		return hphm;
	}
	public void setHphm(String hphm) {
		this.hphm = hphm;
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
	public String getXzqh() {
		return xzqh;
	}
	public void setXzqh(String xzqh) {
		this.xzqh = xzqh;
	}
	public String getCllb() {
		return cllb;
	}
	public void setCllb(String cllb) {
		this.cllb = cllb;
	}
	public String getCllx() {
		return cllx;
	}
	public void setCllx(String cllx) {
		this.cllx = cllx;
	}
	public String getCcdjrq() {
		return ccdjrq;
	}
	public void setCcdjrq(String ccdjrq) {
		this.ccdjrq = ccdjrq;
	}
	public String getCcrq() {
		return ccrq;
	}
	public void setCcrq(String ccrq) {
		this.ccrq = ccrq;
	}
	public String getSyr() {
		return syr;
	}
	public void setSyr(String syr) {
		this.syr = syr;
	}
	public String getSyrlxdh() {
		return syrlxdh;
	}
	public void setSyrlxdh(String syrlxdh) {
		this.syrlxdh = syrlxdh;
	}
	public String getSyzt() {
		return syzt;
	}
	public void setSyzt(String syzt) {
		this.syzt = syzt;
	}
	public String getNycjrcz() {
		return nycjrcz;
	}
	public void setNycjrcz(String nycjrcz) {
		this.nycjrcz = nycjrcz;
	}
	public String getSyxz() {
		return syxz;
	}
	public void setSyxz(String syxz) {
		this.syxz = syxz;
	}
	public String getFdjxh() {
		return fdjxh;
	}
	public void setFdjxh(String fdjxh) {
		this.fdjxh = fdjxh;
	}
	public String getFdjh() {
		return fdjh;
	}
	public void setFdjh(String fdjh) {
		this.fdjh = fdjh;
	}
	public String getClpp() {
		return clpp;
	}
	public void setClpp(String clpp) {
		this.clpp = clpp;
	}
	public String getClxh() {
		return clxh;
	}
	public void setClxh(String clxh) {
		this.clxh = clxh;
	}
	public String getClzzcmc() {
		return clzzcmc;
	}
	public void setClzzcmc(String clzzcmc) {
		this.clzzcmc = clzzcmc;
	}
	public String getFdjzzcmc() {
		return fdjzzcmc;
	}
	public void setFdjzzcmc(String fdjzzcmc) {
		this.fdjzzcmc = fdjzzcmc;
	}
	public String getQdxs() {
		return qdxs;
	}
	public void setQdxs(String qdxs) {
		this.qdxs = qdxs;
	}
	public String getBsxxs() {
		return bsxxs;
	}
	public void setBsxxs(String bsxxs) {
		this.bsxxs = bsxxs;
	}
	public String getDwgs() {
		return dwgs;
	}
	public void setDwgs(String dwgs) {
		this.dwgs = dwgs;
	}
	public String getQgs() {
		return qgs;
	}
	public void setQgs(String qgs) {
		this.qgs = qgs;
	}
	public String getPqgs() {
		return pqgs;
	}
	public void setPqgs(String pqgs) {
		this.pqgs = pqgs;
	}
	public String getHdzk() {
		return hdzk;
	}
	public void setHdzk(String hdzk) {
		this.hdzk = hdzk;
	}
	public String getDczz() {
		return dczz;
	}
	public void setDczz(String dczz) {
		this.dczz = dczz;
	}
	public String getZzl() {
		return zzl;
	}
	public void setZzl(String zzl) {
		this.zzl = zzl;
	}
	public String getHdzzl() {
		return hdzzl;
	}
	public void setHdzzl(String hdzzl) {
		this.hdzzl = hdzzl;
	}
	public String getZbzl() {
		return zbzl;
	}
	public void setZbzl(String zbzl) {
		this.zbzl = zbzl;
	}
	public String getCsys() {
		return csys;
	}
	public void setCsys(String csys) {
		this.csys = csys;
	}
	public String getSyobd() {
		return syobd;
	}
	public void setSyobd(String syobd) {
		this.syobd = syobd;
	}
	public String getSyobdtag() {
		return syobdtag;
	}
	public void setSyobdtag(String syobdtag) {
		this.syobdtag = syobdtag;
	}
	public String getEdzs() {
		return edzs;
	}
	public void setEdzs(String edzs) {
		this.edzs = edzs;
	}
	public String getEdgl() {
		return edgl;
	}
	public void setEdgl(String edgl) {
		this.edgl = edgl;
	}
	public String getPl() {
		return pl;
	}
	public void setPl(String pl) {
		this.pl = pl;
	}
	public String getGyfsid() {
		return gyfsid;
	}
	public void setGyfsid(String gyfsid) {
		this.gyfsid = gyfsid;
	}
	public String getGyfs() {
		return gyfs;
	}
	public void setGyfs(String gyfs) {
		this.gyfs = gyfs;
	}
	public String getRlzlid() {
		return rlzlid;
	}
	public void setRlzlid(String rlzlid) {
		this.rlzlid = rlzlid;
	}
	public String getRlzl() {
		return rlzl;
	}
	public void setRlzl(String rlzl) {
		this.rlzl = rlzl;
	}
	public String getJqfsid() {
		return jqfsid;
	}
	public void setJqfsid(String jqfsid) {
		this.jqfsid = jqfsid;
	}
	public String getJqfs() {
		return jqfs;
	}
	public void setJqfs(String jqfs) {
		this.jqfs = jqfs;
	}
	public String getMinlmd() {
		return minlmd;
	}
	public void setMinlmd(String minlmd) {
		this.minlmd = minlmd;
	}
	public String getMaxlmd() {
		return maxlmd;
	}
	public void setMaxlmd(String maxlmd) {
		this.maxlmd = maxlmd;
	}
	public String getMtcscc() {
		return mtcscc;
	}
	public void setMtcscc(String mtcscc) {
		this.mtcscc = mtcscc;
	}
	public String getMtcscctag() {
		return mtcscctag;
	}
	public void setMtcscctag(String mtcscctag) {
		this.mtcscctag = mtcscctag;
	}
	public String getEgr() {
		return egr;
	}
	public void setEgr(String egr) {
		this.egr = egr;
	}
	public String getEgrtag() {
		return egrtag;
	}
	public void setEgrtag(String egrtag) {
		this.egrtag = egrtag;
	}
	public String getTg() {
		return tg;
	}
	public void setTg(String tg) {
		this.tg = tg;
	}
	public String getTgtag() {
		return tgtag;
	}
	public void setTgtag(String tgtag) {
		this.tgtag = tgtag;
	}
	public String getHcltype() {
		return hcltype;
	}
	public void setHcltype(String hcltype) {
		this.hcltype = hcltype;
	}
	public String getDpf() {
		return dpf;
	}
	public void setDpf(String dpf) {
		this.dpf = dpf;
	}
	public String getDpfxh() {
		return dpfxh;
	}
	public void setDpfxh(String dpfxh) {
		this.dpfxh = dpfxh;
	}
	public String getScr() {
		return scr;
	}
	public void setScr(String scr) {
		this.scr = scr;
	}
	public String getScrxh() {
		return scrxh;
	}
	public void setScrxh(String scrxh) {
		this.scrxh = scrxh;
	}
	public String getDpscqy() {
		return dpscqy;
	}
	public void setDpscqy(String dpscqy) {
		this.dpscqy = dpscqy;
	}
	public String getCchgzh() {
		return cchgzh;
	}
	public void setCchgzh(String cchgzh) {
		this.cchgzh = cchgzh;
	}
	public String getSfdk() {
		return sfdk;
	}
	public void setSfdk(String sfdk) {
		this.sfdk = sfdk;
	}
	public String getSfdktag() {
		return sfdktag;
	}
	public void setSfdktag(String sfdktag) {
		this.sfdktag = sfdktag;
	}
	public String getSfgbcswd() {
		return sfgbcswd;
	}
	public void setSfgbcswd(String sfgbcswd) {
		this.sfgbcswd = sfgbcswd;
	}
	public String getSfgbcswdtag() {
		return sfgbcswdtag;
	}
	public void setSfgbcswdtag(String sfgbcswdtag) {
		this.sfgbcswdtag = sfgbcswdtag;
	}
	public String getClfl() {
		return clfl;
	}
	public void setClfl(String clfl) {
		this.clfl = clfl;
	}
	public String getDdjxh() {
		return ddjxh;
	}
	public void setDdjxh(String ddjxh) {
		this.ddjxh = ddjxh;
	}
	public String getCnzzxh() {
		return cnzzxh;
	}
	public void setCnzzxh(String cnzzxh) {
		this.cnzzxh = cnzzxh;
	}
	public String getDcrl() {
		return dcrl;
	}
	public void setDcrl(String dcrl) {
		this.dcrl = dcrl;
	}
	public String getChzhqxh() {
		return chzhqxh;
	}
	public void setChzhqxh(String chzhqxh) {
		this.chzhqxh = chzhqxh;
	}
	public String getHpzlid() {
		return hpzlid;
	}
	public void setHpzlid(String hpzlid) {
		this.hpzlid = hpzlid;
	}
	public String getQdxsid() {
		return qdxsid;
	}
	public void setQdxsid(String qdxsid) {
		this.qdxsid = qdxsid;
	}
	public String getSyrdz() {
		return syrdz;
	}
	public void setSyrdz(String syrdz) {
		this.syrdz = syrdz;
	}
	public String getCllbid() {
		return cllbid;
	}
	public void setCllbid(String cllbid) {
		this.cllbid = cllbid;
	}
	public String getCllxid() {
		return cllxid;
	}
	public void setCllxid(String cllxid) {
		this.cllxid = cllxid;
	}
	public String getSyzttag() {
		return syzttag;
	}
	public void setSyzttag(String syzttag) {
		this.syzttag = syzttag;
	}
	public String getSyxzid() {
		return syxzid;
	}
	public void setSyxzid(String syxzid) {
		this.syxzid = syxzid;
	}
	public String getCsysid() {
		return csysid;
	}
	public void setCsysid(String csysid) {
		this.csysid = csysid;
	}
}
