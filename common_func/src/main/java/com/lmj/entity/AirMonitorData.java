package com.lmj.entity;

import java.io.Serializable;
import java.util.Date;

import com.lmj.constant.Constant;

public class AirMonitorData implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private Date monitorTime; // 数据监测时间
	private Date receiveTime = new Date(); // 数据接收时间
	private String code; // 站点、城市code
	private String name; // 站点、城市名称
	private Double so2;
	private Double no2;
	private Double pm25;
	private Double pm10;
	private Double co;
	private Double o3;
	private Double o38; // 有臭氧八小时数据
	private Double aqi;
	private Double compositeIndex;
	private String pollution;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getMonitorTime() {
		return monitorTime;
	}
	public void setMonitorTime(Date monitorTime) {
		this.monitorTime = monitorTime;
	}
	public Date getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getSo2() {
		return so2;
	}
	public void setSo2(Double so2) {
		this.so2 = so2;
	}
	public Double getNo2() {
		return no2;
	}
	public void setNo2(Double no2) {
		this.no2 = no2;
	}
	public Double getPm25() {
		return pm25;
	}
	public void setPm25(Double pm25) {
		this.pm25 = pm25;
	}
	public Double getPm10() {
		return pm10;
	}
	public void setPm10(Double pm10) {
		this.pm10 = pm10;
	}
	public Double getCo() {
		return co;
	}
	public void setCo(Double co) {
		this.co = co;
	}
	public Double getO3() {
		return o3;
	}
	public void setO3(Double o3) {
		this.o3 = o3;
	}
	public Double getO38() {
		return o38;
	}
	public void setO38(Double o38) {
		this.o38 = o38;
	}
	public Double getAqi() {
		return aqi;
	}
	public void setAqi(Double aqi) {
		this.aqi = aqi;
	}
	public Double getCompositeIndex() {
		return compositeIndex;
	}
	public void setCompositeIndex(Double compositeIndex) {
		this.compositeIndex = compositeIndex;
	}
	public String getPollution() {
		return pollution == null ? Constant.POLLUTION_NULL: pollution;
	}
	
	public void setPollution(String pollution) {
		this.pollution = pollution == null ? Constant.POLLUTION_NULL: pollution;;
	}
	
}
