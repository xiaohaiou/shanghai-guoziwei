package com.softline.entity;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.softline.common.Common;
import com.softline.entityTemp.SDK_empdirector;

/**
 * HhUsers entity. @author MyEclipse Persistence Tools
 */

public class HhUsersCopy implements java.io.Serializable {

	// Fields

	private Integer id;
	private String vcEmployeeId;
	private String nnodeId;
	private String vcName;
	private String vcAccount;
	private String csex;
	private String cflag;
	private String doperatorDate;
	private String vcOrganId;
	private String vcFullName;
	private String nadminLevelId;
	private String vcAdminLevelName;
	private String cifManageLeader;
	private String tbLNNodeId;
	private String vcName1;
	private String vcSecondName;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private Boolean isOut;
	private Integer Persontype ;
	private String PersontypeName ;
	/**
	 * 临时字段
	 */
	//所属角色权限(角色id)
	private List<Integer> roleIds;
	private String vcOrganIds;
	//所属数据权限(公司id)
	private String[] chkValues;
	//暂存员工照片
	private String employeeImg;
	//所属数据权限(类型1、资产 2、股权 3、工商 4、印章 5、档案)
	private String type;
	//菜单
	private String menus ;
	//首页权限
	private String homePage ;
	//全球资产分布图权限
	private String map ;
	//督办提醒权限
	private String remind ;
	//当前登陆用户
	private String usersEmployeeId;
	//是否集团管理员
	private String isSystem;
	//所属BIM权限(bimid)
	private List<Integer> bimIds;
	
	/**
	 * formula
	 */
	private String isallocate;
	private String isbusiness;

	// Constructors

	/** default constructor */
	public HhUsersCopy() {
	}

	/** minimal constructor */
	public HhUsersCopy(String vcEmployeeId) {
		this.vcEmployeeId = vcEmployeeId;
	}

	

	// Property accessors

	public HhUsersCopy(Integer id, String vcEmployeeId, String nnodeId,
			String vcName, String vcAccount, String csex, String cflag,
			String doperatorDate, String vcOrganId, String vcFullName,
			String nadminLevelId, String vcAdminLevelName,
			String cifManageLeader, String tbLNNodeId, String vcName1,
			String vcSecondName, String createPersonName,
			String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, Boolean isOut, Integer persontype,
			String persontypeName, List<Integer> roleIds, String vcOrganIds,
			String[] chkValues, String employeeImg, String type,
			String isallocate, String menus, String homePage, String map,
			String remind) {
		super();
		this.id = id;
		this.vcEmployeeId = vcEmployeeId;
		this.nnodeId = nnodeId;
		this.vcName = vcName;
		this.vcAccount = vcAccount;
		this.csex = csex;
		this.cflag = cflag;
		this.doperatorDate = doperatorDate;
		this.vcOrganId = vcOrganId;
		this.vcFullName = vcFullName;
		this.nadminLevelId = nadminLevelId;
		this.vcAdminLevelName = vcAdminLevelName;
		this.cifManageLeader = cifManageLeader;
		this.tbLNNodeId = tbLNNodeId;
		this.vcName1 = vcName1;
		this.vcSecondName = vcSecondName;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
		this.isOut = isOut;
		Persontype = persontype;
		PersontypeName = persontypeName;
		this.roleIds = roleIds;
		this.vcOrganIds = vcOrganIds;
		this.chkValues = chkValues;
		this.employeeImg = employeeImg;
		this.type = type;
		this.isallocate = isallocate;
		this.menus = menus;
		this.homePage = homePage;
		this.map = map;
		this.remind = remind;
	}

	public Boolean getIsOut() {
		return isOut;
	}

	public void setIsOut(Boolean isOut) {
		this.isOut = isOut;
	}
	
	public Integer getPersontype() {
		return Persontype;
	}

	public void setPersontype(Integer persontype) {
		Persontype = persontype;
	}
	
	public String getPersontypeName() {
		return PersontypeName;
	}

	public void setPersontypeName(String persontypeName) {
		PersontypeName = persontypeName;
	}

	public HhUsersCopy(Integer id, String vcEmployeeId, String nnodeId,
			String vcName, String vcAccount, String csex, String cflag,
			String doperatorDate, String vcOrganId, String vcFullName,
			String nadminLevelId, String vcAdminLevelName,
			String cifManageLeader, String tbLNNodeId, String vcName1,
			String vcSecondName, String createPersonName,
			String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate) {
		super();
		this.id = id;
		this.vcEmployeeId = vcEmployeeId;
		this.nnodeId = nnodeId;
		this.vcName = vcName;
		this.vcAccount = vcAccount;
		this.csex = csex;
		this.cflag = cflag;
		this.doperatorDate = doperatorDate;
		this.vcOrganId = vcOrganId;
		this.vcFullName = vcFullName;
		this.nadminLevelId = nadminLevelId;
		this.vcAdminLevelName = vcAdminLevelName;
		this.cifManageLeader = cifManageLeader;
		this.tbLNNodeId = tbLNNodeId;
		this.vcName1 = vcName1;
		this.vcSecondName = vcSecondName;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
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
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVcEmployeeId() {
		return this.vcEmployeeId;
	}

	public void setVcEmployeeId(String vcEmployeeId) {
		this.vcEmployeeId = vcEmployeeId;
	}

	public String getNnodeId() {
		return this.nnodeId;
	}

	public void setNnodeId(String nnodeId) {
		this.nnodeId = nnodeId;
	}

	public String getVcName() {
		return this.vcName;
	
	}

	public void setVcName(String vcName) {
			this.vcName=vcName;
	}

	public String getVcAccount() {
		return this.vcAccount;
	}

	public void setVcAccount(String vcAccount) {
		this.vcAccount = vcAccount;
	}

	public String getCsex() {
		return this.csex;
	}

	public void setCsex(String csex) {
		this.csex = csex;
	}

	public String getCflag() {
		return this.cflag;
	}

	public void setCflag(String cflag) {
		this.cflag = cflag;
	}

	public String getDoperatorDate() {
		return this.doperatorDate;
	}

	public void setDoperatorDate(String doperatorDate) {
		this.doperatorDate = doperatorDate;
	}

	public String getVcOrganId() {
		return this.vcOrganId;
	}

	public void setVcOrganId(String vcOrganId) {
		this.vcOrganId = vcOrganId;
	}

	public String getVcFullName() {
		return this.vcFullName;
	}

	public void setVcFullName(String vcFullName) {
		this.vcFullName = vcFullName;
	}

	public String getNadminLevelId() {
		return this.nadminLevelId;
	}

	public void setNadminLevelId(String nadminLevelId) {
		this.nadminLevelId = nadminLevelId;
	}

	public String getVcAdminLevelName() {
		return this.vcAdminLevelName;
	}

	public void setVcAdminLevelName(String vcAdminLevelName) {
		this.vcAdminLevelName = vcAdminLevelName;
	}

	public String getCifManageLeader() {
		return this.cifManageLeader;
	}

	public void setCifManageLeader(String cifManageLeader) {
		this.cifManageLeader = cifManageLeader;
	}

	public String getTbLNNodeId() {
		return this.tbLNNodeId;
	}

	public void setTbLNNodeId(String tbLNNodeId) {
		this.tbLNNodeId = tbLNNodeId;
	}

	public String getVcName1() {
		return this.vcName1;
	}

	public void setVcName1(String vcName1) {
		this.vcName1 = vcName1;
	}

	public String getVcSecondName() {
		return this.vcSecondName;
	}

	public void setVcSecondName(String vcSecondName) {
		this.vcSecondName = vcSecondName;
	}

	public List<Integer> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}

	public String getVcOrganIds() {
		return vcOrganIds;
	}

	public void setVcOrganIds(String vcOrganIds) {
		this.vcOrganIds = vcOrganIds;
	}

	public String[] getChkValues() {
		return chkValues;
	}

	public void setChkValues(String[] chkValues) {
		this.chkValues = chkValues;
	}

	public String getEmployeeImg() {
		return employeeImg;
	}

	public void setEmployeeImg(String employeeImg) {
		this.employeeImg = employeeImg;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsallocate() {
		return isallocate;
	}

	public void setIsallocate(String isallocate) {
		this.isallocate = isallocate;
	}

	public String getMenus() {
		return menus;
	}

	public void setMenus(String menus) {
		this.menus = menus;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public String getRemind() {
		return remind;
	}

	public void setRemind(String remind) {
		this.remind = remind;
	}
	
	public String getUsersEmployeeId() {
		return usersEmployeeId;
	}

	public void setUsersEmployeeId(String usersEmployeeId) {
		this.usersEmployeeId = usersEmployeeId;
	}

	public String getIsbusiness() {
		return isbusiness;
	}

	public void setIsbusiness(String isbusiness) {
		this.isbusiness = isbusiness;
	}

	public String getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(String isSystem) {
		this.isSystem = isSystem;
	}

	public List<Integer> getBimIds() {
		return bimIds;
	}

	public void setBimIds(List<Integer> bimIds) {
		this.bimIds = bimIds;
	}

	public static HhUsersCopy ConvertTo(HhUsersCopy hhUsers, SDK_empdirector data)
	{
		String temp="";
		String regex = "[^\\d.]+";
		String str = Common.notEmpty(data.getVcName())?data.getVcName():"";
		if(str!=null)
		{
			Pattern p=Pattern.compile(regex);
			Matcher m = p.matcher(str);
			while(m.find())
			{
				temp+=m.group();
			}	
		}
		hhUsers.setVcEmployeeId(Common.notEmpty(data.getVcEmployeeID())?data.getVcEmployeeID():"");//员工编号
		hhUsers.setNnodeId(Common.notEmpty(data.getnNodeID())?data.getnNodeID():"");//所属机构ID
		hhUsers.setVcName(temp);//姓名
		hhUsers.setVcAccount(Common.notEmpty(data.getVcAccount())?data.getVcAccount():"");//账号
		hhUsers.setCsex(Common.notEmpty(data.getcSex())?data.getcSex():"");//性别
		hhUsers.setCflag(Common.notEmpty(data.getcFlag())?data.getcFlag():"");//是否在岗、在职标示
		hhUsers.setDoperatorDate(Common.notEmpty(data.getdOperatorDate())?data.getdOperatorDate():"");//操作时间
		hhUsers.setVcOrganId(Common.notEmpty(data.getVcOrganID())?data.getVcOrganID():"");//机构节点编号
		hhUsers.setVcFullName(Common.notEmpty(data.getVcFullName())?data.getVcFullName():"");//机构全称
		hhUsers.setNadminLevelId(Common.notEmpty(data.getnAdminLevelID())?data.getnAdminLevelID():"");//管理级别编号
		hhUsers.setVcAdminLevelName(Common.notEmpty(data.getVcAdminLevelName())?data.getVcAdminLevelName():"");//管理级别名称
		hhUsers.setCifManageLeader(Common.notEmpty(data.getcIfManageLeader())?data.getcIfManageLeader():"");//是否是管理干部
		hhUsers.setTbLNNodeId(Common.notEmpty(data.getTbL_nNodeID())?data.getTbL_nNodeID():"");//所属公司编号
		hhUsers.setVcName1(Common.notEmpty(data.getVcName1())?data.getVcName1():"");//机构名称
		hhUsers.setVcSecondName(Common.notEmpty(data.getVcSecondName())?data.getVcSecondName():"");//别名
	    return hhUsers;
	}
	
}