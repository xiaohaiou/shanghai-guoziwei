package com.softline.entity;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.softline.common.Common;
import com.softline.entityTemp.SDK_empdirector;

/**
 * HhUsers entity. @author MyEclipse Persistence Tools
 */

public class HhUsers implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1183489510816050527L;
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
	private String password;
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
	public HhUsers() {
	}

	/** minimal constructor */
	public HhUsers(String vcEmployeeId) {
		this.vcEmployeeId = vcEmployeeId;
	}

	

	// Property accessors

	public HhUsers(Integer id, String vcEmployeeId, String nnodeId,
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

	public HhUsers(Integer id, String vcEmployeeId, String nnodeId,
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

	public static HhUsers ConvertTo(HhUsers hhUsers, SDK_empdirector data)
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Persontype == null) ? 0 : Persontype.hashCode());
		result = prime * result + ((PersontypeName == null) ? 0 : PersontypeName.hashCode());
		result = prime * result + ((bimIds == null) ? 0 : bimIds.hashCode());
		result = prime * result + ((cflag == null) ? 0 : cflag.hashCode());
		result = prime * result + Arrays.hashCode(chkValues);
		result = prime * result + ((cifManageLeader == null) ? 0 : cifManageLeader.hashCode());
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((createPersonId == null) ? 0 : createPersonId.hashCode());
		result = prime * result + ((createPersonName == null) ? 0 : createPersonName.hashCode());
		result = prime * result + ((csex == null) ? 0 : csex.hashCode());
		result = prime * result + ((doperatorDate == null) ? 0 : doperatorDate.hashCode());
		result = prime * result + ((employeeImg == null) ? 0 : employeeImg.hashCode());
		result = prime * result + ((homePage == null) ? 0 : homePage.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isOut == null) ? 0 : isOut.hashCode());
		result = prime * result + ((isSystem == null) ? 0 : isSystem.hashCode());
		result = prime * result + ((isallocate == null) ? 0 : isallocate.hashCode());
		result = prime * result + ((isbusiness == null) ? 0 : isbusiness.hashCode());
		result = prime * result + ((lastModifyDate == null) ? 0 : lastModifyDate.hashCode());
		result = prime * result + ((lastModifyPersonId == null) ? 0 : lastModifyPersonId.hashCode());
		result = prime * result + ((lastModifyPersonName == null) ? 0 : lastModifyPersonName.hashCode());
		result = prime * result + ((map == null) ? 0 : map.hashCode());
		result = prime * result + ((menus == null) ? 0 : menus.hashCode());
		result = prime * result + ((nadminLevelId == null) ? 0 : nadminLevelId.hashCode());
		result = prime * result + ((nnodeId == null) ? 0 : nnodeId.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((remind == null) ? 0 : remind.hashCode());
		result = prime * result + ((roleIds == null) ? 0 : roleIds.hashCode());
		result = prime * result + ((tbLNNodeId == null) ? 0 : tbLNNodeId.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((usersEmployeeId == null) ? 0 : usersEmployeeId.hashCode());
		result = prime * result + ((vcAccount == null) ? 0 : vcAccount.hashCode());
		result = prime * result + ((vcAdminLevelName == null) ? 0 : vcAdminLevelName.hashCode());
		result = prime * result + ((vcEmployeeId == null) ? 0 : vcEmployeeId.hashCode());
		result = prime * result + ((vcFullName == null) ? 0 : vcFullName.hashCode());
		result = prime * result + ((vcName == null) ? 0 : vcName.hashCode());
		result = prime * result + ((vcName1 == null) ? 0 : vcName1.hashCode());
		result = prime * result + ((vcOrganId == null) ? 0 : vcOrganId.hashCode());
		result = prime * result + ((vcOrganIds == null) ? 0 : vcOrganIds.hashCode());
		result = prime * result + ((vcSecondName == null) ? 0 : vcSecondName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HhUsers other = (HhUsers) obj;
		if (Persontype == null) {
			if (other.Persontype != null)
				return false;
		} else if (!Persontype.equals(other.Persontype))
			return false;
		if (PersontypeName == null) {
			if (other.PersontypeName != null)
				return false;
		} else if (!PersontypeName.equals(other.PersontypeName))
			return false;
		if (bimIds == null) {
			if (other.bimIds != null)
				return false;
		} else if (!bimIds.equals(other.bimIds))
			return false;
		if (cflag == null) {
			if (other.cflag != null)
				return false;
		} else if (!cflag.equals(other.cflag))
			return false;
		if (!Arrays.equals(chkValues, other.chkValues))
			return false;
		if (cifManageLeader == null) {
			if (other.cifManageLeader != null)
				return false;
		} else if (!cifManageLeader.equals(other.cifManageLeader))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (createPersonId == null) {
			if (other.createPersonId != null)
				return false;
		} else if (!createPersonId.equals(other.createPersonId))
			return false;
		if (createPersonName == null) {
			if (other.createPersonName != null)
				return false;
		} else if (!createPersonName.equals(other.createPersonName))
			return false;
		if (csex == null) {
			if (other.csex != null)
				return false;
		} else if (!csex.equals(other.csex))
			return false;
		if (doperatorDate == null) {
			if (other.doperatorDate != null)
				return false;
		} else if (!doperatorDate.equals(other.doperatorDate))
			return false;
		if (employeeImg == null) {
			if (other.employeeImg != null)
				return false;
		} else if (!employeeImg.equals(other.employeeImg))
			return false;
		if (homePage == null) {
			if (other.homePage != null)
				return false;
		} else if (!homePage.equals(other.homePage))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isOut == null) {
			if (other.isOut != null)
				return false;
		} else if (!isOut.equals(other.isOut))
			return false;
		if (isSystem == null) {
			if (other.isSystem != null)
				return false;
		} else if (!isSystem.equals(other.isSystem))
			return false;
		if (isallocate == null) {
			if (other.isallocate != null)
				return false;
		} else if (!isallocate.equals(other.isallocate))
			return false;
		if (isbusiness == null) {
			if (other.isbusiness != null)
				return false;
		} else if (!isbusiness.equals(other.isbusiness))
			return false;
		if (lastModifyDate == null) {
			if (other.lastModifyDate != null)
				return false;
		} else if (!lastModifyDate.equals(other.lastModifyDate))
			return false;
		if (lastModifyPersonId == null) {
			if (other.lastModifyPersonId != null)
				return false;
		} else if (!lastModifyPersonId.equals(other.lastModifyPersonId))
			return false;
		if (lastModifyPersonName == null) {
			if (other.lastModifyPersonName != null)
				return false;
		} else if (!lastModifyPersonName.equals(other.lastModifyPersonName))
			return false;
		if (map == null) {
			if (other.map != null)
				return false;
		} else if (!map.equals(other.map))
			return false;
		if (menus == null) {
			if (other.menus != null)
				return false;
		} else if (!menus.equals(other.menus))
			return false;
		if (nadminLevelId == null) {
			if (other.nadminLevelId != null)
				return false;
		} else if (!nadminLevelId.equals(other.nadminLevelId))
			return false;
		if (nnodeId == null) {
			if (other.nnodeId != null)
				return false;
		} else if (!nnodeId.equals(other.nnodeId))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (remind == null) {
			if (other.remind != null)
				return false;
		} else if (!remind.equals(other.remind))
			return false;
		if (roleIds == null) {
			if (other.roleIds != null)
				return false;
		} else if (!roleIds.equals(other.roleIds))
			return false;
		if (tbLNNodeId == null) {
			if (other.tbLNNodeId != null)
				return false;
		} else if (!tbLNNodeId.equals(other.tbLNNodeId))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (usersEmployeeId == null) {
			if (other.usersEmployeeId != null)
				return false;
		} else if (!usersEmployeeId.equals(other.usersEmployeeId))
			return false;
		if (vcAccount == null) {
			if (other.vcAccount != null)
				return false;
		} else if (!vcAccount.equals(other.vcAccount))
			return false;
		if (vcAdminLevelName == null) {
			if (other.vcAdminLevelName != null)
				return false;
		} else if (!vcAdminLevelName.equals(other.vcAdminLevelName))
			return false;
		if (vcEmployeeId == null) {
			if (other.vcEmployeeId != null)
				return false;
		} else if (!vcEmployeeId.equals(other.vcEmployeeId))
			return false;
		if (vcFullName == null) {
			if (other.vcFullName != null)
				return false;
		} else if (!vcFullName.equals(other.vcFullName))
			return false;
		if (vcName == null) {
			if (other.vcName != null)
				return false;
		} else if (!vcName.equals(other.vcName))
			return false;
		if (vcName1 == null) {
			if (other.vcName1 != null)
				return false;
		} else if (!vcName1.equals(other.vcName1))
			return false;
		if (vcOrganId == null) {
			if (other.vcOrganId != null)
				return false;
		} else if (!vcOrganId.equals(other.vcOrganId))
			return false;
		if (vcOrganIds == null) {
			if (other.vcOrganIds != null)
				return false;
		} else if (!vcOrganIds.equals(other.vcOrganIds))
			return false;
		if (vcSecondName == null) {
			if (other.vcSecondName != null)
				return false;
		} else if (!vcSecondName.equals(other.vcSecondName))
			return false;
		return true;
	}
		
}