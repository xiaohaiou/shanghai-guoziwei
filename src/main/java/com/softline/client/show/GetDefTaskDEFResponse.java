package com.softline.client.show;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for getDefTaskDEFResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="getDefTaskDEFResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://webService.softline.com/}defTask" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDefTaskDEFResponse", propOrder = { "_return" })
public class GetDefTaskDEFResponse {

	@XmlElement(name = "return")
	protected DefTask _return;

	/**
	 * Gets the value of the return property.
	 * 
	 * @return possible object is {@link DefTask }
	 * 
	 */
	public DefTask getReturn() {
		return _return;
	}

	/**
	 * Sets the value of the return property.
	 * 
	 * @param value
	 *            allowed object is {@link DefTask }
	 * 
	 */
	public void setReturn(DefTask value) {
		this._return = value;
	}

}
