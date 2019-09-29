package com.softline.entityTemp;

/**
 * VProjectChangeId entity. @author MyEclipse Persistence Tools
 */

public class VProjectChange implements java.io.Serializable {

	// Fields

	private Integer id;
	private String pjName;
	private Double oldPjProgress;
	private Double newPjProgress;
	private Double oldContractProgress;
	private Double newContractProgress;

	// Constructors

	/** default constructor */
	public VProjectChange() {
	}

	

	/** full constructor */
	public VProjectChange( Integer id,String pjName, Double oldPjProgress,
			Double newPjProgress, Double oldContractProgress,
			Double newContractProgress) {
		this.id = id;
		this.pjName = pjName;
		this.oldPjProgress = oldPjProgress;
		this.newPjProgress = newPjProgress;
		this.oldContractProgress = oldContractProgress;
		this.newContractProgress = newContractProgress;
	}

	// Property accessors

	public Integer getId() {
		return id;
	}
	
	
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getPjName() {
		return this.pjName;
	}



	public void setPjName(String pjName) {
		this.pjName = pjName;
	}

	public Double getOldPjProgress() {
		return this.oldPjProgress;
	}

	public void setOldPjProgress(Double oldPjProgress) {
		this.oldPjProgress = oldPjProgress;
	}

	public Double getNewPjProgress() {
		return this.newPjProgress;
	}

	public void setNewPjProgress(Double newPjProgress) {
		this.newPjProgress = newPjProgress;
	}

	public Double getOldContractProgress() {
		return this.oldContractProgress;
	}

	public void setOldContractProgress(Double oldContractProgress) {
		this.oldContractProgress = oldContractProgress;
	}

	public Double getNewContractProgress() {
		return this.newContractProgress;
	}

	public void setNewContractProgress(Double newContractProgress) {
		this.newContractProgress = newContractProgress;
	}
}