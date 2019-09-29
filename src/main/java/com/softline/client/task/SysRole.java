package com.softline.client.task;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for sysRole complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="sysRole">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creater" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="isDel" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="modifierId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="modifyTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="srcId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sysRole", propOrder = { "createTime", "creater",
		"description", "id", "isDel", "modifierId", "modifyTime", "name",
		"srcId" })
public class SysRole {

	protected String createTime;
	protected String creater;
	protected String description;
	protected Integer id;
	protected Integer isDel;
	protected String modifierId;
	protected String modifyTime;
	protected String name;
	protected Integer srcId;

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
	 * Gets the value of the creater property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCreater() {
		return creater;
	}

	/**
	 * Sets the value of the creater property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCreater(String value) {
		this.creater = value;
	}

	/**
	 * Gets the value of the description property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the value of the description property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDescription(String value) {
		this.description = value;
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
	 * Gets the value of the modifierId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getModifierId() {
		return modifierId;
	}

	/**
	 * Sets the value of the modifierId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setModifierId(String value) {
		this.modifierId = value;
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
	 * Gets the value of the name property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the value of the name property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setName(String value) {
		this.name = value;
	}

	/**
	 * Gets the value of the srcId property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getSrcId() {
		return srcId;
	}

	/**
	 * Sets the value of the srcId property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setSrcId(Integer value) {
		this.srcId = value;
	}

}
