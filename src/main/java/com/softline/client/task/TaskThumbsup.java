package com.softline.client.task;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for taskThumbsup complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="taskThumbsup">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="taskId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "taskThumbsup", propOrder = { "createName", "createTime",
		"creator", "id", "taskId" })
public class TaskThumbsup {

	protected String createName;
	protected String createTime;
	protected String creator;
	protected Integer id;
	protected Integer taskId;

	/**
	 * Gets the value of the createName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCreateName() {
		return createName;
	}

	/**
	 * Sets the value of the createName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCreateName(String value) {
		this.createName = value;
	}

	/**
	 * Gets the value of the createTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * Sets the value of the createTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCreateTime(String value) {
		this.createTime = value;
	}

	/**
	 * Gets the value of the creator property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * Sets the value of the creator property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCreator(String value) {
		this.creator = value;
	}

	/**
	 * Gets the value of the id property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the value of the id property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setId(Integer value) {
		this.id = value;
	}

	/**
	 * Gets the value of the taskId property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getTaskId() {
		return taskId;
	}

	/**
	 * Sets the value of the taskId property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setTaskId(Integer value) {
		this.taskId = value;
	}

}
