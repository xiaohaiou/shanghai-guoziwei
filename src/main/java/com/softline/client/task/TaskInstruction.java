package com.softline.client.task;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for taskInstruction complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="taskInstruction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accountNameDeps" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createEndTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createStartTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createrTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creatorAccount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creatorDep" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creatorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deadLine" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deadlineEndTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deadlineStartTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fatherTaskName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="handlerAccount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="handlerDep" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="handlerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="imgPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isDel" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="isOvertime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="isTerminate" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="lastReportTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastUpdateTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="modifierId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="modifyTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="parentTaskInstruction" type="{http://webService.softline.com/}taskInstruction" minOccurs="0"/>
 *         &lt;element name="progressList" type="{http://webService.softline.com/}taskProgress" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="queryType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="slctPersons" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sonTaskInstruction" type="{http://webService.softline.com/}taskInstruction" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="sort" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="srcId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="taskCompletion" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="taskDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taskHandler" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taskInstructionType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taskInstructionUrgency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taskLevel" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="taskName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taskNode1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taskNode2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taskParentId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="taskProgress" type="{http://webService.softline.com/}taskProgress" minOccurs="0"/>
 *         &lt;element name="taskRecevieTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taskReferPercent" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="taskStatus" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="taskThumbsupList" type="{http://webService.softline.com/}taskThumbsup" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="taskType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="taskUrgency" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="task_file" type="{http://webService.softline.com/}sysFile" minOccurs="0"/>
 *         &lt;element name="taskccList" type="{http://webService.softline.com/}taskCc" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="taskccStrs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="thumbsupLevel" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="timeProgress" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="cVcFullName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cVcName1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cVcPostName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gVcFullName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gVcName1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gVcPostName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sSubInstruction" type="{http://webService.softline.com/}taskInstruction" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "taskInstruction", propOrder = { "accountNameDeps",
		"createEndTime", "createStartTime", "createrTime", "creator",
		"creatorAccount", "creatorDep", "creatorName", "deadLine",
		"deadlineEndTime", "deadlineStartTime", "fatherTaskName",
		"handlerAccount", "handlerDep", "handlerName", "id", "imgPath",
		"isDel", "isOvertime", "isTerminate", "lastReportTime",
		"lastUpdateTime", "modifierId", "modifyTime", "parentTaskInstruction",
		"progressList", "queryType", "slctPersons", "sonTaskInstruction",
		"sort", "srcId", "taskCompletion", "taskDescription", "taskHandler",
		"taskInstructionType", "taskInstructionUrgency", "taskLevel",
		"taskName", "taskNode1", "taskNode2", "taskParentId", "taskProgress",
		"taskRecevieTime", "taskReferPercent", "taskStatus",
		"taskThumbsupList", "taskType", "taskUrgency", "taskFile",
		"taskccList", "taskccStrs", "thumbsupLevel", "timeProgress",
		"cVcFullName", "cVcName1", "cVcPostName", "gVcFullName", "gVcName1",
		"gVcPostName", "sSubInstruction" })
public class TaskInstruction {

	protected String accountNameDeps;
	protected String createEndTime;
	protected String createStartTime;
	protected String createrTime;
	protected String creator;
	protected String creatorAccount;
	protected String creatorDep;
	protected String creatorName;
	protected String deadLine;
	protected String deadlineEndTime;
	protected String deadlineStartTime;
	protected String fatherTaskName;
	protected String handlerAccount;
	protected String handlerDep;
	protected String handlerName;
	protected Integer id;
	protected String imgPath;
	protected Integer isDel;
	protected Integer isOvertime;
	protected Integer isTerminate;
	protected String lastReportTime;
	protected String lastUpdateTime;
	protected String modifierId;
	protected String modifyTime;
	protected TaskInstruction parentTaskInstruction;
	@XmlElement(nillable = true)
	protected List<TaskProgress> progressList;
	protected Integer queryType;
	protected String slctPersons;
	@XmlElement(nillable = true)
	protected List<TaskInstruction> sonTaskInstruction;
	protected String sort;
	protected Integer srcId;
	protected Double taskCompletion;
	protected String taskDescription;
	protected String taskHandler;
	protected String taskInstructionType;
	protected String taskInstructionUrgency;
	protected Integer taskLevel;
	protected String taskName;
	protected String taskNode1;
	protected String taskNode2;
	protected Integer taskParentId;
	protected TaskProgress taskProgress;
	protected String taskRecevieTime;
	protected Double taskReferPercent;
	protected Integer taskStatus;
	@XmlElement(nillable = true)
	protected List<TaskThumbsup> taskThumbsupList;
	protected Integer taskType;
	protected Integer taskUrgency;
	@XmlElement(name = "task_file")
	protected SysFile taskFile;
	@XmlElement(nillable = true)
	protected List<TaskCc> taskccList;
	protected String taskccStrs;
	protected Integer thumbsupLevel;
	protected Double timeProgress;
	protected String cVcFullName;
	protected String cVcName1;
	protected String cVcPostName;
	protected String gVcFullName;
	protected String gVcName1;
	protected String gVcPostName;
	protected TaskInstruction sSubInstruction;

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
	 * Gets the value of the createEndTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCreateEndTime() {
		return createEndTime;
	}

	/**
	 * Sets the value of the createEndTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCreateEndTime(String value) {
		this.createEndTime = value;
	}

	/**
	 * Gets the value of the createStartTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCreateStartTime() {
		return createStartTime;
	}

	/**
	 * Sets the value of the createStartTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCreateStartTime(String value) {
		this.createStartTime = value;
	}

	/**
	 * Gets the value of the createrTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCreaterTime() {
		return createrTime;
	}

	/**
	 * Sets the value of the createrTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCreaterTime(String value) {
		this.createrTime = value;
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
	 * Gets the value of the creatorAccount property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCreatorAccount() {
		return creatorAccount;
	}

	/**
	 * Sets the value of the creatorAccount property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCreatorAccount(String value) {
		this.creatorAccount = value;
	}

	/**
	 * Gets the value of the creatorDep property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCreatorDep() {
		return creatorDep;
	}

	/**
	 * Sets the value of the creatorDep property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCreatorDep(String value) {
		this.creatorDep = value;
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
	 * Gets the value of the deadLine property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDeadLine() {
		return deadLine;
	}

	/**
	 * Sets the value of the deadLine property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDeadLine(String value) {
		this.deadLine = value;
	}

	/**
	 * Gets the value of the deadlineEndTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDeadlineEndTime() {
		return deadlineEndTime;
	}

	/**
	 * Sets the value of the deadlineEndTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDeadlineEndTime(String value) {
		this.deadlineEndTime = value;
	}

	/**
	 * Gets the value of the deadlineStartTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDeadlineStartTime() {
		return deadlineStartTime;
	}

	/**
	 * Sets the value of the deadlineStartTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDeadlineStartTime(String value) {
		this.deadlineStartTime = value;
	}

	/**
	 * Gets the value of the fatherTaskName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFatherTaskName() {
		return fatherTaskName;
	}

	/**
	 * Sets the value of the fatherTaskName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFatherTaskName(String value) {
		this.fatherTaskName = value;
	}

	/**
	 * Gets the value of the handlerAccount property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getHandlerAccount() {
		return handlerAccount;
	}

	/**
	 * Sets the value of the handlerAccount property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setHandlerAccount(String value) {
		this.handlerAccount = value;
	}

	/**
	 * Gets the value of the handlerDep property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getHandlerDep() {
		return handlerDep;
	}

	/**
	 * Sets the value of the handlerDep property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setHandlerDep(String value) {
		this.handlerDep = value;
	}

	/**
	 * Gets the value of the handlerName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getHandlerName() {
		return handlerName;
	}

	/**
	 * Sets the value of the handlerName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setHandlerName(String value) {
		this.handlerName = value;
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
	 * Gets the value of the imgPath property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getImgPath() {
		return imgPath;
	}

	/**
	 * Sets the value of the imgPath property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setImgPath(String value) {
		this.imgPath = value;
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
	 * Gets the value of the isOvertime property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getIsOvertime() {
		return isOvertime;
	}

	/**
	 * Sets the value of the isOvertime property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setIsOvertime(Integer value) {
		this.isOvertime = value;
	}

	/**
	 * Gets the value of the isTerminate property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getIsTerminate() {
		return isTerminate;
	}

	/**
	 * Sets the value of the isTerminate property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setIsTerminate(Integer value) {
		this.isTerminate = value;
	}

	/**
	 * Gets the value of the lastReportTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLastReportTime() {
		return lastReportTime;
	}

	/**
	 * Sets the value of the lastReportTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLastReportTime(String value) {
		this.lastReportTime = value;
	}

	/**
	 * Gets the value of the lastUpdateTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	/**
	 * Sets the value of the lastUpdateTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLastUpdateTime(String value) {
		this.lastUpdateTime = value;
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
	 * Gets the value of the parentTaskInstruction property.
	 * 
	 * @return possible object is {@link TaskInstruction }
	 * 
	 */
	public TaskInstruction getParentTaskInstruction() {
		return parentTaskInstruction;
	}

	/**
	 * Sets the value of the parentTaskInstruction property.
	 * 
	 * @param value
	 *            allowed object is {@link TaskInstruction }
	 * 
	 */
	public void setParentTaskInstruction(TaskInstruction value) {
		this.parentTaskInstruction = value;
	}

	/**
	 * Gets the value of the progressList property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the progressList property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getProgressList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link TaskProgress }
	 * 
	 * 
	 */
	public List<TaskProgress> getProgressList() {
		if (progressList == null) {
			progressList = new ArrayList<TaskProgress>();
		}
		return this.progressList;
	}

	/**
	 * Gets the value of the queryType property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getQueryType() {
		return queryType;
	}

	/**
	 * Sets the value of the queryType property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setQueryType(Integer value) {
		this.queryType = value;
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
	 * Gets the value of the sonTaskInstruction property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the sonTaskInstruction property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getSonTaskInstruction().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link TaskInstruction }
	 * 
	 * 
	 */
	public List<TaskInstruction> getSonTaskInstruction() {
		if (sonTaskInstruction == null) {
			sonTaskInstruction = new ArrayList<TaskInstruction>();
		}
		return this.sonTaskInstruction;
	}

	/**
	 * Gets the value of the sort property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSort() {
		return sort;
	}

	/**
	 * Sets the value of the sort property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSort(String value) {
		this.sort = value;
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

	/**
	 * Gets the value of the taskCompletion property.
	 * 
	 * @return possible object is {@link Double }
	 * 
	 */
	public Double getTaskCompletion() {
		return taskCompletion;
	}

	/**
	 * Sets the value of the taskCompletion property.
	 * 
	 * @param value
	 *            allowed object is {@link Double }
	 * 
	 */
	public void setTaskCompletion(Double value) {
		this.taskCompletion = value;
	}

	/**
	 * Gets the value of the taskDescription property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTaskDescription() {
		return taskDescription;
	}

	/**
	 * Sets the value of the taskDescription property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTaskDescription(String value) {
		this.taskDescription = value;
	}

	/**
	 * Gets the value of the taskHandler property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTaskHandler() {
		return taskHandler;
	}

	/**
	 * Sets the value of the taskHandler property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTaskHandler(String value) {
		this.taskHandler = value;
	}

	/**
	 * Gets the value of the taskInstructionType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTaskInstructionType() {
		return taskInstructionType;
	}

	/**
	 * Sets the value of the taskInstructionType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTaskInstructionType(String value) {
		this.taskInstructionType = value;
	}

	/**
	 * Gets the value of the taskInstructionUrgency property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTaskInstructionUrgency() {
		return taskInstructionUrgency;
	}

	/**
	 * Sets the value of the taskInstructionUrgency property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTaskInstructionUrgency(String value) {
		this.taskInstructionUrgency = value;
	}

	/**
	 * Gets the value of the taskLevel property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getTaskLevel() {
		return taskLevel;
	}

	/**
	 * Sets the value of the taskLevel property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setTaskLevel(Integer value) {
		this.taskLevel = value;
	}

	/**
	 * Gets the value of the taskName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * Sets the value of the taskName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTaskName(String value) {
		this.taskName = value;
	}

	/**
	 * Gets the value of the taskNode1 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTaskNode1() {
		return taskNode1;
	}

	/**
	 * Sets the value of the taskNode1 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTaskNode1(String value) {
		this.taskNode1 = value;
	}

	/**
	 * Gets the value of the taskNode2 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTaskNode2() {
		return taskNode2;
	}

	/**
	 * Sets the value of the taskNode2 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTaskNode2(String value) {
		this.taskNode2 = value;
	}

	/**
	 * Gets the value of the taskParentId property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getTaskParentId() {
		return taskParentId;
	}

	/**
	 * Sets the value of the taskParentId property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setTaskParentId(Integer value) {
		this.taskParentId = value;
	}

	/**
	 * Gets the value of the taskProgress property.
	 * 
	 * @return possible object is {@link TaskProgress }
	 * 
	 */
	public TaskProgress getTaskProgress() {
		return taskProgress;
	}

	/**
	 * Sets the value of the taskProgress property.
	 * 
	 * @param value
	 *            allowed object is {@link TaskProgress }
	 * 
	 */
	public void setTaskProgress(TaskProgress value) {
		this.taskProgress = value;
	}

	/**
	 * Gets the value of the taskRecevieTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTaskRecevieTime() {
		return taskRecevieTime;
	}

	/**
	 * Sets the value of the taskRecevieTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTaskRecevieTime(String value) {
		this.taskRecevieTime = value;
	}

	/**
	 * Gets the value of the taskReferPercent property.
	 * 
	 * @return possible object is {@link Double }
	 * 
	 */
	public Double getTaskReferPercent() {
		return taskReferPercent;
	}

	/**
	 * Sets the value of the taskReferPercent property.
	 * 
	 * @param value
	 *            allowed object is {@link Double }
	 * 
	 */
	public void setTaskReferPercent(Double value) {
		this.taskReferPercent = value;
	}

	/**
	 * Gets the value of the taskStatus property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getTaskStatus() {
		return taskStatus;
	}

	/**
	 * Sets the value of the taskStatus property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setTaskStatus(Integer value) {
		this.taskStatus = value;
	}

	/**
	 * Gets the value of the taskThumbsupList property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the taskThumbsupList property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getTaskThumbsupList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link TaskThumbsup }
	 * 
	 * 
	 */
	public List<TaskThumbsup> getTaskThumbsupList() {
		if (taskThumbsupList == null) {
			taskThumbsupList = new ArrayList<TaskThumbsup>();
		}
		return this.taskThumbsupList;
	}

	/**
	 * Gets the value of the taskType property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getTaskType() {
		return taskType;
	}

	/**
	 * Sets the value of the taskType property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setTaskType(Integer value) {
		this.taskType = value;
	}

	/**
	 * Gets the value of the taskUrgency property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getTaskUrgency() {
		return taskUrgency;
	}

	/**
	 * Sets the value of the taskUrgency property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setTaskUrgency(Integer value) {
		this.taskUrgency = value;
	}

	/**
	 * Gets the value of the taskFile property.
	 * 
	 * @return possible object is {@link SysFile }
	 * 
	 */
	public SysFile getTaskFile() {
		return taskFile;
	}

	/**
	 * Sets the value of the taskFile property.
	 * 
	 * @param value
	 *            allowed object is {@link SysFile }
	 * 
	 */
	public void setTaskFile(SysFile value) {
		this.taskFile = value;
	}

	/**
	 * Gets the value of the taskccList property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the taskccList property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getTaskccList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link TaskCc }
	 * 
	 * 
	 */
	public List<TaskCc> getTaskccList() {
		if (taskccList == null) {
			taskccList = new ArrayList<TaskCc>();
		}
		return this.taskccList;
	}

	/**
	 * Gets the value of the taskccStrs property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTaskccStrs() {
		return taskccStrs;
	}

	/**
	 * Sets the value of the taskccStrs property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTaskccStrs(String value) {
		this.taskccStrs = value;
	}

	/**
	 * Gets the value of the thumbsupLevel property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getThumbsupLevel() {
		return thumbsupLevel;
	}

	/**
	 * Sets the value of the thumbsupLevel property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setThumbsupLevel(Integer value) {
		this.thumbsupLevel = value;
	}

	/**
	 * Gets the value of the timeProgress property.
	 * 
	 * @return possible object is {@link Double }
	 * 
	 */
	public Double getTimeProgress() {
		return timeProgress;
	}

	/**
	 * Sets the value of the timeProgress property.
	 * 
	 * @param value
	 *            allowed object is {@link Double }
	 * 
	 */
	public void setTimeProgress(Double value) {
		this.timeProgress = value;
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

	/**
	 * Gets the value of the gVcFullName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getGVcFullName() {
		return gVcFullName;
	}

	/**
	 * Sets the value of the gVcFullName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setGVcFullName(String value) {
		this.gVcFullName = value;
	}

	/**
	 * Gets the value of the gVcName1 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getGVcName1() {
		return gVcName1;
	}

	/**
	 * Sets the value of the gVcName1 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setGVcName1(String value) {
		this.gVcName1 = value;
	}

	/**
	 * Gets the value of the gVcPostName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getGVcPostName() {
		return gVcPostName;
	}

	/**
	 * Sets the value of the gVcPostName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setGVcPostName(String value) {
		this.gVcPostName = value;
	}

	/**
	 * Gets the value of the sSubInstruction property.
	 * 
	 * @return possible object is {@link TaskInstruction }
	 * 
	 */
	public TaskInstruction getSSubInstruction() {
		return sSubInstruction;
	}

	/**
	 * Sets the value of the sSubInstruction property.
	 * 
	 * @param value
	 *            allowed object is {@link TaskInstruction }
	 * 
	 */
	public void setSSubInstruction(TaskInstruction value) {
		this.sSubInstruction = value;
	}

}
