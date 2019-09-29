package com.softline.entity.bimr;

import java.io.Serializable;

public class BimrInsideAuditDifficult implements Serializable{
    /**
     * 版本序列化.
     */
    private static final long serialVersionUID = -2017552409639415172L;
    /**
     * 表主键.
     */
    private Integer id;
    /**
     * 审计项目ID.
     */
    private Integer projectId;
    /**
     * 审计项目名称.
     */
    private String projectName;
    /**
     * 困难和应对措施.
     */
    private String difficultAndMeasure;
    /**
     * 是否已删除.
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

    public BimrInsideAuditDifficult() {
    }

    public BimrInsideAuditDifficult(Integer id, Integer projectId, String projectName, String difficultAndMeasure, Integer isDel, String createPersonName, String createPersonId, String createDate, String lastModifyPersonId, String lastModifyPersonName, String lastModifyDate) {
        this.id = id;
        this.projectId = projectId;
        this.projectName = projectName;
        this.difficultAndMeasure = difficultAndMeasure;
        this.isDel = isDel;
        this.createPersonName = createPersonName;
        this.createPersonId = createPersonId;
        this.createDate = createDate;
        this.lastModifyPersonId = lastModifyPersonId;
        this.lastModifyPersonName = lastModifyPersonName;
        this.lastModifyDate = lastModifyDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDifficultAndMeasure() {
        return difficultAndMeasure;
    }

    public void setDifficultAndMeasure(String difficultAndMeasure) {
        this.difficultAndMeasure = difficultAndMeasure;
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

    @Override
    public String toString() {
        return "BimrInsideAuditDifficult{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", difficultAndMeasure='" + difficultAndMeasure + '\'' +
                ", isDel=" + isDel +
                ", createPersonName='" + createPersonName + '\'' +
                ", createPersonId='" + createPersonId + '\'' +
                ", createDate='" + createDate + '\'' +
                ", lastModifyPersonId='" + lastModifyPersonId + '\'' +
                ", lastModifyPersonName='" + lastModifyPersonName + '\'' +
                ", lastModifyDate='" + lastModifyDate + '\'' +
                '}';
    }
}
