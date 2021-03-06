package com.softline.client.task;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for getTaskInsructionList03 complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="getTaskInsructionList03">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://webService.softline.com/}taskInstruction" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getTaskInsructionList03", propOrder = { "arg0" })
public class GetTaskInsructionList03 {

	protected TaskInstruction arg0;

	/**
	 * Gets the value of the arg0 property.
	 * 
	 * @return possible object is {@link TaskInstruction }
	 * 
	 */
	public TaskInstruction getArg0() {
		return arg0;
	}

	/**
	 * Sets the value of the arg0 property.
	 * 
	 * @param value
	 *            allowed object is {@link TaskInstruction }
	 * 
	 */
	public void setArg0(TaskInstruction value) {
		this.arg0 = value;
	}

}
