package com.softline.client.sso;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for addNewLoginResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="addNewLoginResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://webService.softline.com/}ssoCheckResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addNewLoginResponse", propOrder = { "_return" })
public class AddNewLoginResponse {

	@XmlElement(name = "return")
	protected SsoCheckResult _return;

	/**
	 * Gets the value of the return property.
	 * 
	 * @return possible object is {@link SsoCheckResult }
	 * 
	 */
	public SsoCheckResult getReturn() {
		return _return;
	}

	/**
	 * Sets the value of the return property.
	 * 
	 * @param value
	 *            allowed object is {@link SsoCheckResult }
	 * 
	 */
	public void setReturn(SsoCheckResult value) {
		this._return = value;
	}

}
