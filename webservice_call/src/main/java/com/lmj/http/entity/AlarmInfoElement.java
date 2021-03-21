package com.lmj.http.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 报警信息
 * @author 李梦杰
 * @date 2020-11-23
 */
@XmlRootElement(name = "info")
@XmlAccessorType(XmlAccessType.FIELD)
public class AlarmInfoElement {
	@XmlElement(name = "AlarmInformation")
	List<AlarmInfo> alarmInfos;

	public List<AlarmInfo> getAlarmInfos() {
		return alarmInfos;
	}

	public void setAlarmInfos(List<AlarmInfo> alarmInfos) {
		this.alarmInfos = alarmInfos;
	}
}
