package com.softline.entity.bimr;

/**
 * BimrComplianceSituationPerson entity. @author MyEclipse Persistence Tools
 */

public class BimrComplianceSituationPerson implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer situationId;
	private String accountabilityPerson;
	private String accountabilityPersonId;
	private Integer accountabilityDefinition;
	private Integer personPostion;
	private String accountabilityItem;
	private String suggestInfo;
	private String submitOfficeId;
	private String officeId;

	// Constructors

	/** default constructor */
	public BimrComplianceSituationPerson() {
	}

	 

	public BimrComplianceSituationPerson(Integer id, Integer situationId,
			String accountabilityPerson, Integer accountabilityDefinition,
			Integer personPostion, String accountabilityItem,
			String suggestInfo, String submitOfficeId, String officeId,String accountabilityPersonId) {
		super();
		this.id = id;
		this.situationId = situationId;
		this.accountabilityPerson = accountabilityPerson;
		this.accountabilityDefinition = accountabilityDefinition;
		this.personPostion = personPostion;
		this.accountabilityItem = accountabilityItem;
		this.suggestInfo = suggestInfo;
		this.submitOfficeId = submitOfficeId;
		this.officeId = officeId;
		this.accountabilityPersonId=accountabilityPersonId;
	}



	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSituationId() {
		return this.situationId;
	}

	public void setSituationId(Integer situationId) {
		this.situationId = situationId;
	}

	public String getAccountabilityPerson() {
		return this.accountabilityPerson;
	}

	public void setAccountabilityPerson(String accountabilityPerson) {
		this.accountabilityPerson = accountabilityPerson;
	}

	public Integer getAccountabilityDefinition() {
		return this.accountabilityDefinition;
	}

	public void setAccountabilityDefinition(Integer accountabilityDefinition) {
		this.accountabilityDefinition = accountabilityDefinition;
	}

	public Integer getPersonPostion() {
		return this.personPostion;
	}

	public void setPersonPostion(Integer personPostion) {
		this.personPostion = personPostion;
	}

	public String getAccountabilityItem() {
		return accountabilityItem;
	}

	public void setAccountabilityItem(String accountabilityItem) {
		this.accountabilityItem = accountabilityItem;
	}

	public String getSuggestInfo() {
		return suggestInfo;
	}

	public void setSuggestInfo(String suggestInfo) {
		this.suggestInfo = suggestInfo;
	}

	public String getSubmitOfficeId() {
		return submitOfficeId;
	}

	public void setSubmitOfficeId(String submitOfficeId) {
		this.submitOfficeId = submitOfficeId;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}



	public String getAccountabilityPersonId() {
		return accountabilityPersonId;
	}



	public void setAccountabilityPersonId(String accountabilityPersonId) {
		this.accountabilityPersonId = accountabilityPersonId;
	}
	
}