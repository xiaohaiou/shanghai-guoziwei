package com.softline.entity.bimr;

/**
 * 合同范本实体类
 * @author pengguolin
 */
public class BimrContractModel implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	/** 单位id */ private Integer companyId;
	/** 单位名称 */ private String companyName;
	/** 合同类型(1:租赁合同2:买卖合同3:财务合同4:技术合同5:融资租赁合同) */ private Integer contractType;
	/** 删除标记（0否1是） */ private Integer isDel;
	/** 创建人 */ private String createPersonName;
	/** 创建人ID */ private String createPersonId;
	/** 创建时间 */ private String createDate;
	/** 最后修改人ID */ private String lastModifyPersonId;
	/** 最后修改人名 */ private String lastModifyPersonName;
	/** 最后修改时间 */ private String lastModifyDate;
	
	/** pdf附件路径 */ private String filePath1;
	/** pdf附件名称 */ private String fileName1;
	/** 空白模板路径 */ private String filePath2;
	/** 空白模板名称 */ private String fileName2;
	
	public BimrContractModel() {
	}
	
	public BimrContractModel(Integer id, Integer companyId, String companyName,
			Integer contractType, Integer isDel, String createPersonName,
			String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.companyName = companyName;
		this.contractType = contractType;
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
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public Integer getContractType() {
		return contractType;
	}

	public void setContractType(Integer contractType) {
		this.contractType = contractType;
	}

	public String getFilePath1() {
		return filePath1;
	}

	public void setFilePath1(String filePath1) {
		this.filePath1 = filePath1;
	}

	public String getFileName1() {
		return fileName1;
	}

	public void setFileName1(String fileName1) {
		this.fileName1 = fileName1;
	}

	public String getFilePath2() {
		return filePath2;
	}

	public void setFilePath2(String filePath2) {
		this.filePath2 = filePath2;
	}

	public String getFileName2() {
		return fileName2;
	}

	public void setFileName2(String fileName2) {
		this.fileName2 = fileName2;
	}
}
