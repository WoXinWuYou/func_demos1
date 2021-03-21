package com.lmj.http.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * body元素内容
 * @author 李梦杰
 * @date 2020-11-23
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomXmlBodyElement<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "head")
	private CustomXmlHeadElement<T> head;

	public CustomXmlHeadElement<T> getHead() {
		return head;
	}

	public void setHead(CustomXmlHeadElement<T> head) {
		this.head = head;
	}

}
