package com.softline.entity;

/**
 * ReportInspectProjectOrder entity. @author MyEclipse Persistence Tools
 */

public class ReportInspectProjectOrder implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer inspectProjectId;
	private String orderSenderId;
	private String orderSenderName;
	private String orderDescription;
	private String orderDate;
	private String orderReportId;
	private String orderReportName;
	private String orderReportDescription;
	private String orderReportDate;

	// Constructors

	/** default constructor */
	public ReportInspectProjectOrder() {
	}

	/** full constructor */
	public ReportInspectProjectOrder(Integer inspectProjectId,
			String orderSenderId, String orderSenderName,
			String orderDescription, String orderDate, String orderReportId,
			String orderReportName, String orderReportDescription,
			String orderReportDate) {
		this.inspectProjectId = inspectProjectId;
		this.orderSenderId = orderSenderId;
		this.orderSenderName = orderSenderName;
		this.orderDescription = orderDescription;
		this.orderDate = orderDate;
		this.orderReportId = orderReportId;
		this.orderReportName = orderReportName;
		this.orderReportDescription = orderReportDescription;
		this.orderReportDate = orderReportDate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getInspectProjectId() {
		return this.inspectProjectId;
	}

	public void setInspectProjectId(Integer inspectProjectId) {
		this.inspectProjectId = inspectProjectId;
	}

	public String getOrderSenderId() {
		return this.orderSenderId;
	}

	public void setOrderSenderId(String orderSenderId) {
		this.orderSenderId = orderSenderId;
	}

	public String getOrderSenderName() {
		return this.orderSenderName;
	}

	public void setOrderSenderName(String orderSenderName) {
		this.orderSenderName = orderSenderName;
	}

	public String getOrderDescription() {
		return this.orderDescription;
	}

	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}

	public String getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderReportId() {
		return this.orderReportId;
	}

	public void setOrderReportId(String orderReportId) {
		this.orderReportId = orderReportId;
	}

	public String getOrderReportName() {
		return this.orderReportName;
	}

	public void setOrderReportName(String orderReportName) {
		this.orderReportName = orderReportName;
	}

	public String getOrderReportDescription() {
		return this.orderReportDescription;
	}

	public void setOrderReportDescription(String orderReportDescription) {
		this.orderReportDescription = orderReportDescription;
	}

	public String getOrderReportDate() {
		return this.orderReportDate;
	}

	public void setOrderReportDate(String orderReportDate) {
		this.orderReportDate = orderReportDate;
	}

}