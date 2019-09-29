package com.softline.entityTemp;

import com.alibaba.fastjson.JSON;

/**
 * 重点基建工程周报接口，返回的结果封装为bean
 * @author sht
 *
 */
public class ProjectWeekReportESBEntity {
	private String ReportID; // 周报ID
	private String ProjectID;//项目ID
	private String Years;//周报所属年度
	private String ProjectName;//项目名称
	private String Weekly;//周次
	private String CreateName;//创建人姓名
	private String CreateID;//创建人ID
	private String CreateTime;//创建时间
	private String WeekProgress;//本周周报内容
	private String NextWeekPlan;//下周周报内容
	private String LagCondition;//滞后状态
	private String LagCause;//滞后原因
	private String AfterMeasures;//追赶措施
	private String DirectorAudit;//总监审核
	private String DisplayImage;//周报形象图片文件的URL
	private String UploadOn;//周报更新时间
	private String UploadByName;//周报更新人
	private String UploadBy;//周报更新人ID
	private String Descript;//周报形象图片说明
	private String UniqueIdentifie;//周报唯一值（周报ID）
	private String UpdateStatus;//周报状态
	private String JunctionsID;//节点编号
	private String JunctionsName;//节点名称
	private String DurationID;//分期编号
	private String DurationName;//分期名称
	private String buildingID;//楼栋编号
	private String BuildingName;//楼栋名称
	private String FilePath;//文件路径
	private String FileType;//文件类型(默认0:图片,1:视频)
	private String ImageDesc;//文件描述
	private String thisProgress;//工程进度
	private String thisQuality;//工程质量
	private String thisSHE;//工程安全
	private String thisProjApplication;//前期报建
	private String thisDesign;//设计图纸
	private String thisPurchase;//招标采购
	private String thisCost;//成本管理
	private String thisContract;//合同及付款情况
	private String thisWeek;//本周历史记录
	private String nextProgress;//工程进度
	private String nextQuality;//工程质量
	private String nextSHE;//工程安全
	private String nextProjApplication;//前期报建
	private String nextDesign;//设计图纸
	private String nextPurchase;//招标采购
	private String nextCost;//成本管理
	private String nextContract;//合同及付款情况
	private String nextWeek;//下周历史记录
	private String Month;//月份
	private String Day;//日期
	private String StartDate;//开始时间
	private String EndDate;//结束时间
	public String getReportID() {
		return ReportID;
	}
	public void setReportID(String reportID) {
		ReportID = reportID;
	}
	public String getProjectID() {
		return ProjectID;
	}
	public void setProjectID(String projectID) {
		ProjectID = projectID;
	}
	public String getYears() {
		return Years;
	}
	public void setYears(String years) {
		Years = years;
	}
	public String getProjectName() {
		return ProjectName;
	}
	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}
	public String getWeekly() {
		return Weekly;
	}
	public void setWeekly(String weekly) {
		Weekly = weekly;
	}
	public String getCreateName() {
		return CreateName;
	}
	public void setCreateName(String createName) {
		CreateName = createName;
	}
	public String getCreateID() {
		return CreateID;
	}
	public void setCreateID(String createID) {
		CreateID = createID;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getWeekProgress() {
		return WeekProgress;
	}
	public void setWeekProgress(String weekProgress) {
		WeekProgress = weekProgress;
	}
	public String getNextWeekPlan() {
		return NextWeekPlan;
	}
	public void setNextWeekPlan(String nextWeekPlan) {
		NextWeekPlan = nextWeekPlan;
	}
	public String getLagCondition() {
		return LagCondition;
	}
	public void setLagCondition(String lagCondition) {
		LagCondition = lagCondition;
	}
	public String getLagCause() {
		return LagCause;
	}
	public void setLagCause(String lagCause) {
		LagCause = lagCause;
	}
	public String getAfterMeasures() {
		return AfterMeasures;
	}
	public void setAfterMeasures(String afterMeasures) {
		AfterMeasures = afterMeasures;
	}
	public String getDirectorAudit() {
		return DirectorAudit;
	}
	public void setDirectorAudit(String directorAudit) {
		DirectorAudit = directorAudit;
	}
	public String getDisplayImage() {
		return DisplayImage;
	}
	public void setDisplayImage(String displayImage) {
		DisplayImage = displayImage;
	}
	public String getUploadOn() {
		return UploadOn;
	}
	public void setUploadOn(String uploadOn) {
		UploadOn = uploadOn;
	}
	public String getUploadByName() {
		return UploadByName;
	}
	public void setUploadByName(String uploadByName) {
		UploadByName = uploadByName;
	}
	public String getUploadBy() {
		return UploadBy;
	}
	public void setUploadBy(String uploadBy) {
		UploadBy = uploadBy;
	}
	public String getDescript() {
		return Descript;
	}
	public void setDescript(String descript) {
		Descript = descript;
	}
	public String getUniqueIdentifie() {
		return UniqueIdentifie;
	}
	public void setUniqueIdentifie(String uniqueIdentifie) {
		UniqueIdentifie = uniqueIdentifie;
	}
	public String getUpdateStatus() {
		return UpdateStatus;
	}
	public void setUpdateStatus(String updateStatus) {
		UpdateStatus = updateStatus;
	}
	public String getJunctionsID() {
		return JunctionsID;
	}
	public void setJunctionsID(String junctionsID) {
		JunctionsID = junctionsID;
	}
	public String getJunctionsName() {
		return JunctionsName;
	}
	public void setJunctionsName(String junctionsName) {
		JunctionsName = junctionsName;
	}
	public String getDurationID() {
		return DurationID;
	}
	public void setDurationID(String durationID) {
		DurationID = durationID;
	}
	public String getDurationName() {
		return DurationName;
	}
	public void setDurationName(String durationName) {
		DurationName = durationName;
	}
	public String getBuildingID() {
		return buildingID;
	}
	public void setBuildingID(String buildingID) {
		this.buildingID = buildingID;
	}
	public String getBuildingName() {
		return BuildingName;
	}
	public void setBuildingName(String buildingName) {
		BuildingName = buildingName;
	}
	public String getFilePath() {
		return FilePath;
	}
	public void setFilePath(String filePath) {
		FilePath = filePath;
	}
	public String getFileType() {
		return FileType;
	}
	public void setFileType(String fileType) {
		FileType = fileType;
	}
	public String getImageDesc() {
		return ImageDesc;
	}
	public void setImageDesc(String imageDesc) {
		ImageDesc = imageDesc;
	}
	public String getThisProgress() {
		return thisProgress;
	}
	public void setThisProgress(String thisProgress) {
		this.thisProgress = thisProgress;
	}
	public String getThisQuality() {
		return thisQuality;
	}
	public void setThisQuality(String thisQuality) {
		this.thisQuality = thisQuality;
	}
	public String getThisSHE() {
		return thisSHE;
	}
	public void setThisSHE(String thisSHE) {
		this.thisSHE = thisSHE;
	}
	public String getThisProjApplication() {
		return thisProjApplication;
	}
	public void setThisProjApplication(String thisProjApplication) {
		this.thisProjApplication = thisProjApplication;
	}
	public String getThisDesign() {
		return thisDesign;
	}
	public void setThisDesign(String thisDesign) {
		this.thisDesign = thisDesign;
	}
	public String getThisPurchase() {
		return thisPurchase;
	}
	public void setThisPurchase(String thisPurchase) {
		this.thisPurchase = thisPurchase;
	}
	public String getThisCost() {
		return thisCost;
	}
	public void setThisCost(String thisCost) {
		this.thisCost = thisCost;
	}
	public String getThisContract() {
		return thisContract;
	}
	public void setThisContract(String thisContract) {
		this.thisContract = thisContract;
	}
	public String getThisWeek() {
		return thisWeek;
	}
	public void setThisWeek(String thisWeek) {
		this.thisWeek = thisWeek;
	}
	public String getNextProgress() {
		return nextProgress;
	}
	public void setNextProgress(String nextProgress) {
		this.nextProgress = nextProgress;
	}
	public String getNextQuality() {
		return nextQuality;
	}
	public void setNextQuality(String nextQuality) {
		this.nextQuality = nextQuality;
	}
	public String getNextSHE() {
		return nextSHE;
	}
	public void setNextSHE(String nextSHE) {
		this.nextSHE = nextSHE;
	}
	public String getNextProjApplication() {
		return nextProjApplication;
	}
	public void setNextProjApplication(String nextProjApplication) {
		this.nextProjApplication = nextProjApplication;
	}
	public String getNextDesign() {
		return nextDesign;
	}
	public void setNextDesign(String nextDesign) {
		this.nextDesign = nextDesign;
	}
	public String getNextPurchase() {
		return nextPurchase;
	}
	public void setNextPurchase(String nextPurchase) {
		this.nextPurchase = nextPurchase;
	}
	public String getNextCost() {
		return nextCost;
	}
	public void setNextCost(String nextCost) {
		this.nextCost = nextCost;
	}
	public String getNextContract() {
		return nextContract;
	}
	public void setNextContract(String nextContract) {
		this.nextContract = nextContract;
	}
	public String getNextWeek() {
		return nextWeek;
	}
	public void setNextWeek(String nextWeek) {
		this.nextWeek = nextWeek;
	}
	public String getMonth() {
		return Month;
	}
	public void setMonth(String month) {
		Month = month;
	}
	public String getDay() {
		return Day;
	}
	public void setDay(String day) {
		Day = day;
	}
	
	
	public String getStartDate() {
		return StartDate;
	}
	public void setStartDate(String startDate) {
		StartDate = startDate;
	}
	public String getEndDate() {
		return EndDate;
	}
	public void setEndDate(String endDate) {
		EndDate = endDate;
	}
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
