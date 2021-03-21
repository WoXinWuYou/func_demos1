package com.lmj.http.entity;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;

/**
 * 头消息
 * @author 李梦杰
 * @date 2020-11-23
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomXmlHeadElement<T> implements Serializable{
	private static final long serialVersionUID = -4714017541670657412L;
	@XmlElement(name="code")  
	private String code; // 1为成功 0为失败
	@XmlElement(name="message")  
	private String message; // 返回状态，Code 值 为 0时 返 回失 败原因，code 为1 时返回空
	/**
     * XmlAnyElement 这个注解可以去调生成的xml中带的xsi:type等信息，使用这个注解就不能使用 @XmlElement，
     *  需要在泛型对应的实体上增加 @XmlRootElement 注解
     *  XmlElementWrapper 这个注解可以在集合外层包装一个节点
     */
    @XmlAnyElement(lax = true)
	private List<T> infos; // Code 值 为 0时返回 null，code为1时返回具体信息。
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<T> getInfos() {
		return infos;
	}
	public void setInfos(List<T> infos) {
		this.infos = infos;
	}
}
