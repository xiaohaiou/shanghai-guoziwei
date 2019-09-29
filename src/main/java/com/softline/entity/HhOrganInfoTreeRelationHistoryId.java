package com.softline.entity;

/**
 * HhOrganInfoTreeRelationHistoryId entity. @author MyEclipse Persistence Tools
 */

public class HhOrganInfoTreeRelationHistoryId implements java.io.Serializable {

	// Fields

	private Integer type;
	private String nnodeId;
	private String updatetime;

	// Constructors

	/** default constructor */
	public HhOrganInfoTreeRelationHistoryId() {
	}

	/** full constructor */
	public HhOrganInfoTreeRelationHistoryId(Integer type, String nnodeId,
			String updatetime) {
		this.type = type;
		this.nnodeId = nnodeId;
		this.updatetime = updatetime;
	}

	// Property accessors

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getNnodeId() {
		return this.nnodeId;
	}

	public void setNnodeId(String nnodeId) {
		this.nnodeId = nnodeId;
	}

	public String getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof HhOrganInfoTreeRelationHistoryId))
			return false;
		HhOrganInfoTreeRelationHistoryId castOther = (HhOrganInfoTreeRelationHistoryId) other;

		return ((this.getType() == castOther.getType()) || (this.getType() != null
				&& castOther.getType() != null && this.getType().equals(
				castOther.getType())))
				&& ((this.getNnodeId() == castOther.getNnodeId()) || (this
						.getNnodeId() != null && castOther.getNnodeId() != null && this
						.getNnodeId().equals(castOther.getNnodeId())))
				&& ((this.getUpdatetime() == castOther.getUpdatetime()) || (this
						.getUpdatetime() != null
						&& castOther.getUpdatetime() != null && this
						.getUpdatetime().equals(castOther.getUpdatetime())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getType() == null ? 0 : this.getType().hashCode());
		result = 37 * result
				+ (getNnodeId() == null ? 0 : this.getNnodeId().hashCode());
		result = 37
				* result
				+ (getUpdatetime() == null ? 0 : this.getUpdatetime()
						.hashCode());
		return result;
	}

}