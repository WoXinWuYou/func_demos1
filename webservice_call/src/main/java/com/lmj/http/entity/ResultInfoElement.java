package com.lmj.http.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 监测结果ELement
 * @author 李梦杰
 * @date 2021-1-15
 */
@XmlRootElement(name = "info")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResultInfoElement {
	@XmlElement(name = "DetData")
	List<ResultInfo> resultInfos;

	public List<ResultInfo> getResultInfos() {
		return resultInfos;
	}

	public void setResultInfos(List<ResultInfo> resultInfos) {
		this.resultInfos = resultInfos;
	}
}
