package com.softline.client.task;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for taskCc complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="taskCc">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ccDep" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ccId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ccName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creatorId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creatorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="isDel" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="modifyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="modifyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="modifyTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiveTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tiId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "taskCc", propOrder = { "ccDep", "ccId", "ccName",
		"createTime", "creatorId", "creatorName", "id", "isDel", "modifyId",
		"modifyName", "modifyTime", "receiveTime", "tiId" })
public class TaskCc {

	protected String ccDep;
	protected String ccId;
	protected String ccName;
	protected String createTime;
	protected String creatorId;
	protected String creatorName;
	protected Integer id;
	protected Integer isDel;
	protected String modifyId;
	protected String modifyName;
	protected String modifyTime;
	protected String receiveTime;
	protected Integer tiId;

	/**
	 * Gets the value of the ccDep property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCcDep() {
		return ccDep;
	}

	/**
	 * Sets the value of the ccDep property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCcDep(String value) {
		this.ccDep = value;
	}

	/**
	 * Gets the value of the ccId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCcId() {
		return ccId;
	}

	/**
	 * Sets the value of the ccId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCcId(String value) {
		this.ccId = value;
	}

	/**
	 * Gets the value of the ccName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCcName() {
		return ccName;
	}

	/**
	 * Sets the value of the ccName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCcName(String value) {
		this.ccName = value;
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
	 * Gets the value of the creatorId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCreatorId() {
		return creatorId;
	}

	/**
	 * Sets the value of the creatorId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCreatorId(String value) {
		this.creatorId = value;
	}

	/**
	 * Gets the value of the creatorName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCreatorName() {
		return creatorName;
	}

	/**
	 * Sets the value of the creatorName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCreatorName(String value) {
		this.creatorName = value;
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
	 * Gets the value of the isDel property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getIsDel() {
		return isDel;
	}

	/**
	 * Sets the value of the isDel property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setIsDel(Integer value) {
		this.isDel = value;
	}

	/**
	 * Gets the value of the modifyId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getModifyId() {
		return modifyId;
	}

	/**
	 * Sets the value of the modifyId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setModifyId(String value) {
		this.modifyId = value;
	}

	/**
	 * Gets the value of the modifyName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getModifyName() {
		return modifyName;
	}

	/**
	 * Sets the value of the modifyName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setModifyName(String value) {
		this.modifyName = value;
	}

	/**
	 * Gets the value of the modifyTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getModifyTime() {
		return modifyTime;
	}

	/**
	 * Sets the value of the modifyTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setModifyTime(String value) {
		this.modifyTime = value;
	}

	/**
	 * Gets the value of the receiveTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReceiveTime() {
		return receiveTime;
	}

	/**
	 * Sets the value of the receiveTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReceiveTime(String value) {
		this.receiveTime = value;
	}

	/**
	 * Gets the value of the tiId property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getTiId() {
		return tiId;
	}

	/**
	 * Sets the value of the tiId property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setTiId(Integer value) {
		this.tiId = value;
	}

}
