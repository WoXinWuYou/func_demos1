package com.lmj.http.entity;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
/**
 * root 根元素内容
 * @author 李梦杰
 * @date 2020-11-23
 */
@javax.xml.bind.annotation.XmlRootElement(name="root")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomXmlRootElement<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "body")
	private CustomXmlBodyElement<T> body;

	/**
	 * 1为成功 0为失败
	 * */
	public String getCode() {
		if(this.body.getHead() != null) {
			return this.body.getHead().getCode();
		}else {
			return "0";
		}
	}
	/**
	 * 返回状态，Code 值 为 0时 返 回失 败原因，code 为1 时返回空
	 * */
	public String getMessage() {
		if(this.body.getHead() != null) {
			return this.body.getHead().getMessage();
		}else {
			return null;
		}
	}
	
	/**
	 * Code 值 为 0时返回 null，code为1时返回具体信息。
	 * */
	public List<T> getInfos() {
		if(this.body.getHead() != null) {
			return this.body.getHead().getInfos();
		}else {
			return null;
		}
	}
	
	public CustomXmlBodyElement<T> getBody() {
		return body;
	}

	public void setBody(CustomXmlBodyElement<T> body) {
		this.body = body;
	}

}
