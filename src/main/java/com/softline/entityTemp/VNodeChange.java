package com.softline.entityTemp;

/**
 * VNodeChangeId entity. @author MyEclipse Persistence Tools
 */

public class VNodeChange implements java.io.Serializable {

	// Fields
	private Integer id;
	private Integer pjId;
	private String pnName;
	private Integer pnOrder;
	private String oldCompletionTime;
	private String newCompletionTime;
	private Double oldProgress;
	private Double newProgress;

	// Constructors

	/** default constructor */
	public VNodeChange() {
	}

	

	/** full constructor */
	public VNodeChange(Integer id,Integer pjId,String pnName, Integer pnOrder,
			String oldCompletionTime, String newCompletionTime,
			Double oldProgress, Double newProgress) {
		this.id = id;
		this.pjId = pjId;
		this.pnName = pnName;
		this.pnOrder = pnOrder;
		this.oldCompletionTime = oldCompletionTime;
		this.newCompletionTime = newCompletionTime;
		this.oldProgress = oldProgress;
		this.newProgress = newProgress;
	}

	// Property accessors

	public Integer getId() {
		return id;
	}
	
	
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getPnName() {
		return this.pnName;
	}




	public void setPnName(String pnName) {
		this.pnName = pnName;
	}

	public Integer getPnOrder() {
		return this.pnOrder;
	}

	public void setPnOrder(Integer pnOrder) {
		this.pnOrder = pnOrder;
	}

	public String getOldCompletionTime() {
		return this.oldCompletionTime;
	}

	public void setOldCompletionTime(String oldCompletionTime) {
		this.oldCompletionTime = oldCompletionTime;
	}

	public String getNewCompletionTime() {
		return this.newCompletionTime;
	}

	public void setNewCompletionTime(String newCompletionTime) {
		this.newCompletionTime = newCompletionTime;
	}

	public Double getOldProgress() {
		return this.oldProgress;
	}

	public void setOldProgress(Double oldProgress) {
		this.oldProgress = oldProgress;
	}

	public Double getNewProgress() {
		return this.newProgress;
	}

	public void setNewProgress(Double newProgress) {
		this.newProgress = newProgress;
	}



	public Integer getPjId() {
		return pjId;
	}



	public void setPjId(Integer pjId) {
		this.pjId = pjId;
	}
	
}