package com.softline.client.task;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for getFormalProgressResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="getFormalProgressResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://webService.softline.com/}taskProgress" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getFormalProgressResponse", propOrder = { "_return" })
public class GetFormalProgressResponse {

	@XmlElement(name = "return")
	protected TaskProgress _return;

	/**
	 * Gets the value of the return property.
	 * 
	 * @return possible object is {@link TaskProgress }
	 * 
	 */
	public TaskProgress getReturn() {
		return _return;
	}

	/**
	 * Sets the value of the return property.
	 * 
	 * @param value
	 *            allowed object is {@link TaskProgress }
	 * 
	 */
	public void setReturn(TaskProgress value) {
		this._return = value;
	}

}
