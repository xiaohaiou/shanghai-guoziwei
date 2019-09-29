package com.softline.client.bima;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for hhUsers complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="hhUsers">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bimIds" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="cflag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chkValues" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="cifManageLeader" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createPersonId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createPersonName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="csex" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="doperatorDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="employeeImg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="homePage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="isOut" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="isSystem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isallocate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isbusiness" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifyDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifyPersonId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifyPersonName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="map" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="menus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nadminLevelId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nnodeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="persontype" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="persontypeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remind" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="roleIds" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="tbLNNodeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usersEmployeeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vcAccount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vcAdminLevelName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vcEmployeeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vcFullName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vcName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vcName1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vcOrganId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vcOrganIds" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vcSecondName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "hhUsers", propOrder = { "bimIds", "cflag", "chkValues",
		"cifManageLeader", "createDate", "createPersonId", "createPersonName",
		"csex", "doperatorDate", "employeeImg", "homePage", "id", "isOut",
		"isSystem", "isallocate", "isbusiness", "lastModifyDate",
		"lastModifyPersonId", "lastModifyPersonName", "map", "menus",
		"nadminLevelId", "nnodeId", "persontype", "persontypeName", "remind",
		"roleIds", "tbLNNodeId", "type", "usersEmployeeId", "vcAccount",
		"vcAdminLevelName", "vcEmployeeId", "vcFullName", "vcName", "vcName1",
		"vcOrganId", "vcOrganIds", "vcSecondName" })
public class HhUsers {

	@XmlElement(nillable = true)
	protected List<Integer> bimIds;
	protected String cflag;
	@XmlElement(nillable = true)
	protected List<String> chkValues;
	protected String cifManageLeader;
	protected String createDate;
	protected String createPersonId;
	protected String createPersonName;
	protected String csex;
	protected String doperatorDate;
	protected String employeeImg;
	protected String homePage;
	protected Integer id;
	protected Boolean isOut;
	protected String isSystem;
	protected String isallocate;
	protected String isbusiness;
	protected String lastModifyDate;
	protected String lastModifyPersonId;
	protected String lastModifyPersonName;
	protected String map;
	protected String menus;
	protected String nadminLevelId;
	protected String nnodeId;
	protected Integer persontype;
	protected String persontypeName;
	protected String remind;
	@XmlElement(nillable = true)
	protected List<Integer> roleIds;
	protected String tbLNNodeId;
	protected String type;
	protected String usersEmployeeId;
	protected String vcAccount;
	protected String vcAdminLevelName;
	protected String vcEmployeeId;
	protected String vcFullName;
	protected String vcName;
	protected String vcName1;
	protected String vcOrganId;
	protected String vcOrganIds;
	protected String vcSecondName;

	/**
	 * Gets the value of the bimIds property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the bimIds property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getBimIds().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link Integer }
	 * 
	 * 
	 */
	public List<Integer> getBimIds() {
		if (bimIds == null) {
			bimIds = new ArrayList<Integer>();
		}
		return this.bimIds;
	}

	/**
	 * Gets the value of the cflag property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCflag() {
		return cflag;
	}

	/**
	 * Sets the value of the cflag property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCflag(String value) {
		this.cflag = value;
	}

	/**
	 * Gets the value of the chkValues property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the chkValues property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getChkValues().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link String }
	 * 
	 * 
	 */
	public List<String> getChkValues() {
		if (chkValues == null) {
			chkValues = new ArrayList<String>();
		}
		return this.chkValues;
	}

	/**
	 * Gets the value of the cifManageLeader property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCifManageLeader() {
		return cifManageLeader;
	}

	/**
	 * Sets the value of the cifManageLeader property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCifManageLeader(String value) {
		this.cifManageLeader = value;
	}

	/**
	 * Gets the value of the createDate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCreateDate() {
		return createDate;
	}

	/**
	 * Sets the value of the createDate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCreateDate(String value) {
		this.createDate = value;
	}

	/**
	 * Gets the value of the createPersonId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCreatePersonId() {
		return createPersonId;
	}

	/**
	 * Sets the value of the createPersonId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCreatePersonId(String value) {
		this.createPersonId = value;
	}

	/**
	 * Gets the value of the createPersonName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCreatePersonName() {
		return createPersonName;
	}

	/**
	 * Sets the value of the createPersonName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCreatePersonName(String value) {
		this.createPersonName = value;
	}

	/**
	 * Gets the value of the csex property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCsex() {
		return csex;
	}

	/**
	 * Sets the value of the csex property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCsex(String value) {
		this.csex = value;
	}

	/**
	 * Gets the value of the doperatorDate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDoperatorDate() {
		return doperatorDate;
	}

	/**
	 * Sets the value of the doperatorDate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDoperatorDate(String value) {
		this.doperatorDate = value;
	}

	/**
	 * Gets the value of the employeeImg property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEmployeeImg() {
		return employeeImg;
	}

	/**
	 * Sets the value of the employeeImg property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEmployeeImg(String value) {
		this.employeeImg = value;
	}

	/**
	 * Gets the value of the homePage property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getHomePage() {
		return homePage;
	}

	/**
	 * Sets the value of the homePage property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setHomePage(String value) {
		this.homePage = value;
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
	 * Gets the value of the isOut property.
	 * 
	 * @return possible object is {@link Boolean }
	 * 
	 */
	public Boolean isIsOut() {
		return isOut;
	}

	/**
	 * Sets the value of the isOut property.
	 * 
	 * @param value
	 *            allowed object is {@link Boolean }
	 * 
	 */
	public void setIsOut(Boolean value) {
		this.isOut = value;
	}

	/**
	 * Gets the value of the isSystem property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIsSystem() {
		return isSystem;
	}

	/**
	 * Sets the value of the isSystem property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIsSystem(String value) {
		this.isSystem = value;
	}

	/**
	 * Gets the value of the isallocate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIsallocate() {
		return isallocate;
	}

	/**
	 * Sets the value of the isallocate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIsallocate(String value) {
		this.isallocate = value;
	}

	/**
	 * Gets the value of the isbusiness property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIsbusiness() {
		return isbusiness;
	}

	/**
	 * Sets the value of the isbusiness property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIsbusiness(String value) {
		this.isbusiness = value;
	}

	/**
	 * Gets the value of the lastModifyDate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLastModifyDate() {
		return lastModifyDate;
	}

	/**
	 * Sets the value of the lastModifyDate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLastModifyDate(String value) {
		this.lastModifyDate = value;
	}

	/**
	 * Gets the value of the lastModifyPersonId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLastModifyPersonId() {
		return lastModifyPersonId;
	}

	/**
	 * Sets the value of the lastModifyPersonId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLastModifyPersonId(String value) {
		this.lastModifyPersonId = value;
	}

	/**
	 * Gets the value of the lastModifyPersonName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLastModifyPersonName() {
		return lastModifyPersonName;
	}

	/**
	 * Sets the value of the lastModifyPersonName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLastModifyPersonName(String value) {
		this.lastModifyPersonName = value;
	}

	/**
	 * Gets the value of the map property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMap() {
		return map;
	}

	/**
	 * Sets the value of the map property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMap(String value) {
		this.map = value;
	}

	/**
	 * Gets the value of the menus property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMenus() {
		return menus;
	}

	/**
	 * Sets the value of the menus property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMenus(String value) {
		this.menus = value;
	}

	/**
	 * Gets the value of the nadminLevelId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getNadminLevelId() {
		return nadminLevelId;
	}

	/**
	 * Sets the value of the nadminLevelId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setNadminLevelId(String value) {
		this.nadminLevelId = value;
	}

	/**
	 * Gets the value of the nnodeId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getNnodeId() {
		return nnodeId;
	}

	/**
	 * Sets the value of the nnodeId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setNnodeId(String value) {
		this.nnodeId = value;
	}

	/**
	 * Gets the value of the persontype property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getPersontype() {
		return persontype;
	}

	/**
	 * Sets the value of the persontype property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setPersontype(Integer value) {
		this.persontype = value;
	}

	/**
	 * Gets the value of the persontypeName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPersontypeName() {
		return persontypeName;
	}

	/**
	 * Sets the value of the persontypeName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPersontypeName(String value) {
		this.persontypeName = value;
	}

	/**
	 * Gets the value of the remind property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRemind() {
		return remind;
	}

	/**
	 * Sets the value of the remind property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRemind(String value) {
		this.remind = value;
	}

	/**
	 * Gets the value of the roleIds property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the roleIds property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getRoleIds().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link Integer }
	 * 
	 * 
	 */
	public List<Integer> getRoleIds() {
		if (roleIds == null) {
			roleIds = new ArrayList<Integer>();
		}
		return this.roleIds;
	}

	/**
	 * Gets the value of the tbLNNodeId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTbLNNodeId() {
		return tbLNNodeId;
	}

	/**
	 * Sets the value of the tbLNNodeId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTbLNNodeId(String value) {
		this.tbLNNodeId = value;
	}

	/**
	 * Gets the value of the type property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the value of the type property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setType(String value) {
		this.type = value;
	}

	/**
	 * Gets the value of the usersEmployeeId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUsersEmployeeId() {
		return usersEmployeeId;
	}

	/**
	 * Sets the value of the usersEmployeeId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUsersEmployeeId(String value) {
		this.usersEmployeeId = value;
	}

	/**
	 * Gets the value of the vcAccount property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getVcAccount() {
		return vcAccount;
	}

	/**
	 * Sets the value of the vcAccount property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setVcAccount(String value) {
		this.vcAccount = value;
	}

	/**
	 * Gets the value of the vcAdminLevelName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getVcAdminLevelName() {
		return vcAdminLevelName;
	}

	/**
	 * Sets the value of the vcAdminLevelName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setVcAdminLevelName(String value) {
		this.vcAdminLevelName = value;
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
	 * Gets the value of the vcFullName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getVcFullName() {
		return vcFullName;
	}

	/**
	 * Sets the value of the vcFullName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setVcFullName(String value) {
		this.vcFullName = value;
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
	 * Gets the value of the vcName1 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getVcName1() {
		return vcName1;
	}

	/**
	 * Sets the value of the vcName1 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setVcName1(String value) {
		this.vcName1 = value;
	}

	/**
	 * Gets the value of the vcOrganId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getVcOrganId() {
		return vcOrganId;
	}

	/**
	 * Sets the value of the vcOrganId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setVcOrganId(String value) {
		this.vcOrganId = value;
	}

	/**
	 * Gets the value of the vcOrganIds property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getVcOrganIds() {
		return vcOrganIds;
	}

	/**
	 * Sets the value of the vcOrganIds property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setVcOrganIds(String value) {
		this.vcOrganIds = value;
	}

	/**
	 * Gets the value of the vcSecondName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getVcSecondName() {
		return vcSecondName;
	}

	/**
	 * Sets the value of the vcSecondName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setVcSecondName(String value) {
		this.vcSecondName = value;
	}

}
