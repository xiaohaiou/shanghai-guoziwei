package com.softline.entity.bimr;

import com.softline.entity.HhFile;

import java.io.Serializable;

public class BimrInsideAuditWeekly implements Serializable {
    /**
     * 版本序列化.
     */
    private static final long serialVersionUID = 5944428236032560982L;
    /**
     * 表主键,
     */
    private Integer id;
    /**
     * 周数,
     */
    private String week;
    /**
     * 周审计累计发现问题数(个),
     */
    private Integer problemCount;
    /**
     * 周建议问责人员累计数(人),
     */
    private Integer accountPerson;
    /**
     * 年份,
     */
    private String year;
    /**
     * 周开始日期,
     */
    private String weekStartDate;
    /**
     * 周结束日期,
     */
    private String weekEndDate;
    /**
     * 项目计划结束日期,
     */
    private String projectEndDate;
    /**
     * 项目状态及本周进展,
     */
    private Integer projectStatusProgress;
    /**
     * 审计发现的重大问题或可疑事项,
     */
    private String importantAndSuspicious;
    /**
     * 审计工作面临的困难和应对措施,
     */
    private String difficultAndMeasures;
    /**
     * 下周工作计划,
     */
    private String nextWeekPlan;
    /**
     * 关联项目的id.
     */
    private Integer projectId;
    /**
     * 关联项目的名称.
     */
    private String projectName;
    /**
     * 是否删除.
     */
    private Integer isDel;
    /**
     * 创建人姓名.
     */
    private String createPersonName;
    /**
     * 创建人ID.
     */
    private String createPersonId;
    /**
     * 创建时间.
     */
    private String createDate;
    /**
     * 最后修改人ID.
     */
    private String lastModifyPersonId;
    /**
     * 最后修改人名.
     */
    private String lastModifyPersonName;
    /**
     * 最后修改时间.
     */
    private String lastModifyDate;


    /**
     * 项目进展
     */
    private String projectprogress;
    
    /**
     * 项目状态name,
     */
    private String projectStatusProgressName;
    
    private HhFile fileOne;

    public BimrInsideAuditWeekly() {
    }

    public BimrInsideAuditWeekly(Integer id, String week, Integer problemCount, Integer accountPerson, String year, String weekStartDate, String weekEndDate, String projectEndDate, Integer projectStatusProgress, String importantAndSuspicious, String difficultAndMeasures, String nextWeekPlan, Integer projectId, String projectName, Integer isDel, String createPersonName, String createPersonId, String createDate, String lastModifyPersonId, String lastModifyPersonName, String lastModifyDate,String projectprogress) {
        this.id = id;
        this.week = week;
        this.problemCount = problemCount;
        this.accountPerson = accountPerson;
        this.year = year;
        this.weekStartDate = weekStartDate;
        this.weekEndDate = weekEndDate;
        this.projectEndDate = projectEndDate;
        this.projectStatusProgress = projectStatusProgress;
        this.importantAndSuspicious = importantAndSuspicious;
        this.difficultAndMeasures = difficultAndMeasures;
        this.nextWeekPlan = nextWeekPlan;
        this.projectId = projectId;
        this.projectName = projectName;
        this.isDel = isDel;
        this.createPersonName = createPersonName;
        this.createPersonId = createPersonId;
        this.createDate = createDate;
        this.lastModifyPersonId = lastModifyPersonId;
        this.lastModifyPersonName = lastModifyPersonName;
        this.lastModifyDate = lastModifyDate;
        this.projectprogress = projectprogress;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public Integer getProblemCount() {
        return problemCount;
    }

    public void setProblemCount(Integer problemCount) {
        this.problemCount = problemCount;
    }

    public Integer getAccountPerson() {
        return accountPerson;
    }

    public void setAccountPerson(Integer accountPerson) {
        this.accountPerson = accountPerson;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getWeekStartDate() {
        return weekStartDate;
    }

    public void setWeekStartDate(String weekStartDate) {
        this.weekStartDate = weekStartDate;
    }

    public String getWeekEndDate() {
        return weekEndDate;
    }

    public void setWeekEndDate(String weekEndDate) {
        this.weekEndDate = weekEndDate;
    }

    public String getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(String projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public Integer getProjectStatusProgress() {
        return projectStatusProgress;
    }

    public void setProjectStatusProgress(Integer projectStatusProgress) {
        this.projectStatusProgress = projectStatusProgress;
    }

    public String getImportantAndSuspicious() {
        return importantAndSuspicious;
    }

    public void setImportantAndSuspicious(String importantAndSuspicious) {
        this.importantAndSuspicious = importantAndSuspicious;
    }

    public String getDifficultAndMeasures() {
        return difficultAndMeasures;
    }

    public void setDifficultAndMeasures(String difficultAndMeasures) {
        this.difficultAndMeasures = difficultAndMeasures;
    }

    public String getNextWeekPlan() {
        return nextWeekPlan;
    }

    public void setNextWeekPlan(String nextWeekPlan) {
        this.nextWeekPlan = nextWeekPlan;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
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

    
    public String getProjectprogress() {
		return projectprogress;
	}

	public void setProjectprogress(String projectprogress) {
		this.projectprogress = projectprogress;
	}

	public HhFile getFileOne() {
        return fileOne;
    }

    public void setFileOne(HhFile fileOne) {
        this.fileOne = fileOne;
    }
    
    

    public String getProjectStatusProgressName() {
		return projectStatusProgressName;
	}

	public void setProjectStatusProgressName(String projectStatusProgressName) {
		this.projectStatusProgressName = projectStatusProgressName;
	}

	@Override
    public String toString() {
        return "BimrInsideAuditWeekly{" +
                "id=" + id +
                ", week='" + week + '\'' +
                ", problemCount=" + problemCount +
                ", accountPerson=" + accountPerson +
                ", year='" + year + '\'' +
                ", weekStartDate='" + weekStartDate + '\'' +
                ", weekEndDate='" + weekEndDate + '\'' +
                ", projectEndDate='" + projectEndDate + '\'' +
                ", projectStatusProgress=" + projectStatusProgress +
                ", importantAndSuspicious='" + importantAndSuspicious + '\'' +
                ", difficultAndMeasures='" + difficultAndMeasures + '\'' +
                ", nextWeekPlan='" + nextWeekPlan + '\'' +
                ", projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", isDel=" + isDel +
                ", createPersonName='" + createPersonName + '\'' +
                ", createPersonId='" + createPersonId + '\'' +
                ", createDate='" + createDate + '\'' +
                ", lastModifyPersonId='" + lastModifyPersonId + '\'' +
                ", lastModifyPersonName='" + lastModifyPersonName + '\'' +
                ", lastModifyDate='" + lastModifyDate + '\'' +
                ", projectprogress='" + projectprogress + '\'' +
                ", fileOne=" + fileOne +
                '}';
    }
}
