package com.softline.client.show;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for role complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="role">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="isDel" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="isUse" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="modifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="modifyTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="portalId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="roleDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="roleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="roleNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "role", propOrder = { "createTime", "creator", "id", "isDel",
		"isUse", "modifier", "modifyTime", "portalId", "roleDescription",
		"roleName", "roleNum" })
public class Role {

	protected String createTime;
	protected String creator;
	protected Integer id;
	protected Integer isDel;
	protected Integer isUse;
	protected String modifier;
	protected String modifyTime;
	protected Integer portalId;
	protected String roleDescription;
	protected String roleName;
	protected String roleNum;

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
	 * Gets the value of the isUse property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getIsUse() {
		return isUse;
	}

	/**
	 * Sets the value of the isUse property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setIsUse(Integer value) {
		this.isUse = value;
	}

	/**
	 * Gets the value of the modifier property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getModifier() {
		return modifier;
	}

	/**
	 * Sets the value of the modifier property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setModifier(String value) {
		this.modifier = value;
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
	 * Gets the value of the portalId property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getPortalId() {
		return portalId;
	}

	/**
	 * Sets the value of the portalId property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setPortalId(Integer value) {
		this.portalId = value;
	}

	/**
	 * Gets the value of the roleDescription property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRoleDescription() {
		return roleDescription;
	}

	/**
	 * Sets the value of the roleDescription property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRoleDescription(String value) {
		this.roleDescription = value;
	}

	/**
	 * Gets the value of the roleName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * Sets the value of the roleName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRoleName(String value) {
		this.roleName = value;
	}

	/**
	 * Gets the value of the roleNum property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRoleNum() {
		return roleNum;
	}

	/**
	 * Sets the value of the roleNum property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRoleNum(String value) {
		this.roleNum = value;
	}

}
