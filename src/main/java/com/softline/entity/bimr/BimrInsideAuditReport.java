package com.softline.entity.bimr;

/**
 * BimrInsideAuditReport entity. @author MyEclipse Persistence Tools
 */
public class BimrInsideAuditReport implements java.io.Serializable {

	private Integer id;
	private String reportDate;
	private String vcCompanyId;
	private String vcCompanyName;
	private Integer measureAmount;
	private Integer measureFinishedAmount;
	private Double rectifyRate;
	private Double coverRate;
	private Integer status;
	private Short isDel;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private String examinePersonId;
	private String examinePersonName;
	private String examineDate;

	public BimrInsideAuditReport() {

	}

	public BimrInsideAuditReport(Integer id, String reportDate, 
			String vcCompanyId, String vcCompanyName, Integer measureAmount,
			Integer measureFinishedAmount, Double rectifyRate, Integer status,
			Short isDel, String createPersonName, String createPersonId,
			String createDate, String lastModifyPersonId, Double coverRate,
			String lastModifyPersonName, String lastModifyDate,
			String examinePersonId, String examinePersonName, String examineDate) {
		this.id = id;
		this.reportDate = reportDate;
		this.vcCompanyId = vcCompanyId;
		this.vcCompanyName = vcCompanyName;
		this.measureAmount = measureAmount;
		this.measureFinishedAmount = measureFinishedAmount;
		this.rectifyRate = rectifyRate;
		this.status = status;
		this.isDel = isDel;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
		this.examinePersonId = examinePersonId;
		this.examinePersonName = examinePersonName;
		this.examineDate = examineDate;
		this.coverRate = coverRate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public Double getCoverRate() {
		return coverRate;
	}

	public void setCoverRate(Double coverRate) {
		this.coverRate = coverRate;
	}

	public String getVcCompanyId() {
		return vcCompanyId;
	}

	public void setVcCompanyId(String vcCompanyId) {
		this.vcCompanyId = vcCompanyId;
	}

	public String getVcCompanyName() {
		return vcCompanyName;
	}

	public void setVcCompanyName(String vcCompanyName) {
		this.vcCompanyName = vcCompanyName;
	}

	public Integer getMeasureAmount() {
		return measureAmount;
	}

	public void setMeasureAmount(Integer measureAmount) {
		this.measureAmount = measureAmount;
	}

	public Integer getMeasureFinishedAmount() {
		return measureFinishedAmount;
	}

	public void setMeasureFinishedAmount(Integer measureFinishedAmount) {
		this.measureFinishedAmount = measureFinishedAmount;
	}

	public Double getRectifyRate() {
		return rectifyRate;
	}

	public void setRectifyRate(Double rectifyRate) {
		this.rectifyRate = rectifyRate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Short getIsDel() {
		return isDel;
	}

	public void setIsDel(Short isDel) {
		this.isDel = isDel;
	}

	public String getCreatePersonName() {
		return createPersonName;
	}

	public void setCreatePersonName(String createPersonName) {
		this.createPersonName = createPersonName;
	}

	public String getCreatePersonId() {
		return createPersonId;
	}

	public void setCreatePersonId(String createPersonId) {
		this.createPersonId = createPersonId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getLastModifyPersonId() {
		return lastModifyPersonId;
	}

	public void setLastModifyPersonId(String lastModifyPersonId) {
		this.lastModifyPersonId = lastModifyPersonId;
	}

	public String getLastModifyPersonName() {
		return lastModifyPersonName;
	}

	public void setLastModifyPersonName(String lastModifyPersonName) {
		this.lastModifyPersonName = lastModifyPersonName;
	}

	public String getLastModifyDate() {
		return lastModifyDate;
	}

	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	public String getExaminePersonId() {
		return examinePersonId;
	}

	public void setExaminePersonId(String examinePersonId) {
		this.examinePersonId = examinePersonId;
	}

	public String getExaminePersonName() {
		return examinePersonName;
	}

	public void setExaminePersonName(String examinePersonName) {
		this.examinePersonName = examinePersonName;
	}

	public String getExamineDate() {
		return examineDate;
	}

	public void setExamineDate(String examineDate) {
		this.examineDate = examineDate;
	}
}
