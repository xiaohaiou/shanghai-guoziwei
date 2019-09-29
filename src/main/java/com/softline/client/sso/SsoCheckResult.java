package com.softline.client.sso;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ssoCheckResult complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="ssoCheckResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="exception" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="result" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="token" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userData" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ssoCheckResult", propOrder = { "exception", "result", "token",
		"userData" })
public class SsoCheckResult {

	protected String exception;
	protected boolean result;
	protected String token;
	protected String userData;

	/**
	 * Gets the value of the exception property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getException() {
		return exception;
	}

	/**
	 * Sets the value of the exception property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setException(String value) {
		this.exception = value;
	}

	/**
	 * Gets the value of the result property.
	 * 
	 */
	public boolean isResult() {
		return result;
	}

	/**
	 * Sets the value of the result property.
	 * 
	 */
	public void setResult(boolean value) {
		this.result = value;
	}

	/**
	 * Gets the value of the token property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Sets the value of the token property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setToken(String value) {
		this.token = value;
	}

	/**
	 * Gets the value of the userData property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUserData() {
		return userData;
	}

	/**
	 * Sets the value of the userData property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUserData(String value) {
		this.userData = value;
	}

}
