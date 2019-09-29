package com.softline.client.task;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for taskProgress complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="taskProgress">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createAccount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="creatorTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="isDel" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="isRead" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="isReport" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="messageType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="progressFiles" type="{http://webService.softline.com/}sysFile" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="progressRemark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="readPersonId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="readPersonName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="readTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reportTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taskInsturctionId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="taskInsturctionName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="users" type="{http://webService.softline.com/}hhUsers" minOccurs="0"/>
 *         &lt;element name="vcAccount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vcEmployeeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vcName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cVcFullName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cVcName1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cVcPostName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "taskProgress", propOrder = { "createAccount", "createName",
		"createType", "creatorTime", "id", "isDel", "isRead", "isReport",
		"messageType", "progressFiles", "progressRemark", "readPersonId",
		"readPersonName", "readTime", "reportTime", "taskInsturctionId",
		"taskInsturctionName", "users", "vcAccount", "vcEmployeeId", "vcName",
		"cVcFullName", "cVcName1", "cVcPostName" })
public class TaskProgress {

	protected String createAccount;
	protected String createName;
	protected Integer createType;
	protected String creatorTime;
	protected Integer id;
	protected Integer isDel;
	protected Integer isRead;
	protected Integer isReport;
	protected Integer messageType;
	@XmlElement(nillable = true)
	protected List<SysFile> progressFiles;
	protected String progressRemark;
	protected String readPersonId;
	protected String readPersonName;
	protected String readTime;
	protected String reportTime;
	protected Integer taskInsturctionId;
	protected String taskInsturctionName;
	protected HhUsers users;
	protected String vcAccount;
	protected String vcEmployeeId;
	protected String vcName;
	protected String cVcFullName;
	protected String cVcName1;
	protected String cVcPostName;

	/**
	 * Gets the value of the createAccount property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCreateAccount() {
		return createAccount;
	}

	/**
	 * Sets the value of the createAccount property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCreateAccount(String value) {
		this.createAccount = value;
	}

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
	 * Gets the value of the createType property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getCreateType() {
		return createType;
	}

	/**
	 * Sets the value of the createType property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setCreateType(Integer value) {
		this.createType = value;
	}

	/**
	 * Gets the value of the creatorTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCreatorTime() {
		return creatorTime;
	}

	/**
	 * Sets the value of the creatorTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCreatorTime(String value) {
		this.creatorTime = value;
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
	 * Gets the value of the isRead property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getIsRead() {
		return isRead;
	}

	/**
	 * Sets the value of the isRead property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setIsRead(Integer value) {
		this.isRead = value;
	}

	/**
	 * Gets the value of the isReport property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getIsReport() {
		return isReport;
	}

	/**
	 * Sets the value of the isReport property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setIsReport(Integer value) {
		this.isReport = value;
	}

	/**
	 * Gets the value of the messageType property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getMessageType() {
		return messageType;
	}

	/**
	 * Sets the value of the messageType property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setMessageType(Integer value) {
		this.messageType = value;
	}

	/**
	 * Gets the value of the progressFiles property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the progressFiles property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getProgressFiles().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link SysFile }
	 * 
	 * 
	 */
	public List<SysFile> getProgressFiles() {
		if (progressFiles == null) {
			progressFiles = new ArrayList<SysFile>();
		}
		return this.progressFiles;
	}

	/**
	 * Gets the value of the progressRemark property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProgressRemark() {
		return progressRemark;
	}

	/**
	 * Sets the value of the progressRemark property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProgressRemark(String value) {
		this.progressRemark = value;
	}

	/**
	 * Gets the value of the readPersonId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReadPersonId() {
		return readPersonId;
	}

	/**
	 * Sets the value of the readPersonId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReadPersonId(String value) {
		this.readPersonId = value;
	}

	/**
	 * Gets the value of the readPersonName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReadPersonName() {
		return readPersonName;
	}

	/**
	 * Sets the value of the readPersonName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReadPersonName(String value) {
		this.readPersonName = value;
	}

	/**
	 * Gets the value of the readTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReadTime() {
		return readTime;
	}

	/**
	 * Sets the value of the readTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReadTime(String value) {
		this.readTime = value;
	}

	/**
	 * Gets the value of the reportTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReportTime() {
		return reportTime;
	}

	/**
	 * Sets the value of the reportTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReportTime(String value) {
		this.reportTime = value;
	}

	/**
	 * Gets the value of the taskInsturctionId property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getTaskInsturctionId() {
		return taskInsturctionId;
	}

	/**
	 * Sets the value of the taskInsturctionId property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setTaskInsturctionId(Integer value) {
		this.taskInsturctionId = value;
	}

	/**
	 * Gets the value of the taskInsturctionName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTaskInsturctionName() {
		return taskInsturctionName;
	}

	/**
	 * Sets the value of the taskInsturctionName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTaskInsturctionName(String value) {
		this.taskInsturctionName = value;
	}

	/**
	 * Gets the value of the users property.
	 * 
	 * @return possible object is {@link HhUsers }
	 * 
	 */
	public HhUsers getUsers() {
		return users;
	}

	/**
	 * Sets the value of the users property.
	 * 
	 * @param value
	 *            allowed object is {@link HhUsers }
	 * 
	 */
	public void setUsers(HhUsers value) {
		this.users = value;
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
	 * Gets the value of the cVcFullName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCVcFullName() {
		return cVcFullName;
	}

	/**
	 * Sets the value of the cVcFullName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCVcFullName(String value) {
		this.cVcFullName = value;
	}

	/**
	 * Gets the value of the cVcName1 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCVcName1() {
		return cVcName1;
	}

	/**
	 * Sets the value of the cVcName1 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCVcName1(String value) {
		this.cVcName1 = value;
	}

	/**
	 * Gets the value of the cVcPostName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCVcPostName() {
		return cVcPostName;
	}

	/**
	 * Sets the value of the cVcPostName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCVcPostName(String value) {
		this.cVcPostName = value;
	}

}
