package com.softline.entity;

/**
 * HhOrganInfoTreeRelationId entity. @author MyEclipse Persistence Tools
 */

public class HhOrganInfoTreeRelationId implements java.io.Serializable {

	// Fields

	private Integer type;
	private String nnodeId;

	// Constructors

	/** default constructor */
	public HhOrganInfoTreeRelationId() {
	}

	/** full constructor */
	public HhOrganInfoTreeRelationId(Integer type, String nnodeId) {
		this.type = type;
		this.nnodeId = nnodeId;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof HhOrganInfoTreeRelationId))
			return false;
		HhOrganInfoTreeRelationId castOther = (HhOrganInfoTreeRelationId) other;

		return ((this.getType() == castOther.getType()) || (this.getType() != null
				&& castOther.getType() != null && this.getType().equals(
				castOther.getType())))
				&& ((this.getNnodeId() == castOther.getNnodeId()) || (this
						.getNnodeId() != null && castOther.getNnodeId() != null && this
						.getNnodeId().equals(castOther.getNnodeId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getType() == null ? 0 : this.getType().hashCode());
		result = 37 * result
				+ (getNnodeId() == null ? 0 : this.getNnodeId().hashCode());
		return result;
	}

}