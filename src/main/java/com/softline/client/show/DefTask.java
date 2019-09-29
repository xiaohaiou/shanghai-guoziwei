package com.softline.client.show;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for defTask complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="defTask">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accountNameDeps" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="mapId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mapName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sbaccountNameDeps" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sbslctPersons" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sbvcName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="slctPersons" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="typeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vcEmployeeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vcName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sTheme" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "defTask", propOrder = { "accountNameDeps", "id", "mapId",
		"mapName", "sbaccountNameDeps", "sbslctPersons", "sbvcName",
		"slctPersons", "type", "typeName", "vcEmployeeId", "vcName", "sTheme" })
public class DefTask {

	protected String accountNameDeps;
	protected Integer id;
	protected String mapId;
	protected String mapName;
	protected String sbaccountNameDeps;
	protected String sbslctPersons;
	protected String sbvcName;
	protected String slctPersons;
	protected Integer type;
	protected String typeName;
	protected String vcEmployeeId;
	protected String vcName;
	protected String sTheme;

	/**
	 * Gets the value of the accountNameDeps property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAccountNameDeps() {
		return accountNameDeps;
	}

	/**
	 * Sets the value of the accountNameDeps property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAccountNameDeps(String value) {
		this.accountNameDeps = value;
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
	 * Gets the value of the mapId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMapId() {
		return mapId;
	}

	/**
	 * Sets the value of the mapId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMapId(String value) {
		this.mapId = value;
	}

	/**
	 * Gets the value of the mapName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMapName() {
		return mapName;
	}

	/**
	 * Sets the value of the mapName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMapName(String value) {
		this.mapName = value;
	}

	/**
	 * Gets the value of the sbaccountNameDeps property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSbaccountNameDeps() {
		return sbaccountNameDeps;
	}

	/**
	 * Sets the value of the sbaccountNameDeps property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSbaccountNameDeps(String value) {
		this.sbaccountNameDeps = value;
	}

	/**
	 * Gets the value of the sbslctPersons property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSbslctPersons() {
		return sbslctPersons;
	}

	/**
	 * Sets the value of the sbslctPersons property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSbslctPersons(String value) {
		this.sbslctPersons = value;
	}

	/**
	 * Gets the value of the sbvcName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSbvcName() {
		return sbvcName;
	}

	/**
	 * Sets the value of the sbvcName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSbvcName(String value) {
		this.sbvcName = value;
	}

	/**
	 * Gets the value of the slctPersons property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSlctPersons() {
		return slctPersons;
	}

	/**
	 * Sets the value of the slctPersons property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSlctPersons(String value) {
		this.slctPersons = value;
	}

	/**
	 * Gets the value of the type property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * Sets the value of the type property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setType(Integer value) {
		this.type = value;
	}

	/**
	 * Gets the value of the typeName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * Sets the value of the typeName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTypeName(String value) {
		this.typeName = value;
	}

	/**
	 * Gets the value of the vcEmployeeId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getVcEmployeeId() {
		return vcEmployeeId;
	}

	/**
	 * Sets the value of the vcEmployeeId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setVcEmployeeId(String value) {
		this.vcEmployeeId = value;
	}

	/**
	 * Gets the value of the vcName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getVcName() {
		return vcName;
	}

	/**
	 * Sets the value of the vcName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setVcName(String value) {
		this.vcName = value;
	}

	/**
	 * Gets the value of the sTheme property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSTheme() {
		return sTheme;
	}

	/**
	 * Sets the value of the sTheme property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSTheme(String value) {
		this.sTheme = value;
	}

}
