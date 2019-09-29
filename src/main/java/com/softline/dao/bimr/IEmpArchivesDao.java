package com.softline.dao.bimr;

import java.util.List;

import com.softline.entity.EmployeeAccountabilityViewId;
import com.softline.entity.bimr.BimrAccountable;
import com.softline.entity.bimr.BimrCivilcaseWeek;
import com.softline.entity.bimr.BimrCompliance;
import com.softline.entity.bimr.BimrCriminalcaseWeek;
import com.softline.entity.bimr.BimrDuty;
import com.softline.entity.bimr.BimrInsideAuditProject;
import com.softline.entity.bimr.BimrRiskEvent;
import com.softline.entityTemp.CompliancePersionAbility;


/**
 * 员工问责Dao
 * @author pengguolin
 */
public interface IEmpArchivesDao {
	
	BimrDuty getById(Integer id);
	//根据员工号获取员工问责信息
	List<BimrDuty> getByPersonId(String personId);
	
	
		
	/**
	 * 获取总记录数
	 * @param entity 带查询实体
	 * @param dataAuthority 数据权限字符串
	 * @return 总记录数
	 */
	Integer getEmpAchivesListCount(BimrDuty entity, String dataAuthority);
	
	/**
	 * 分页查询结果集
	 * @param entity 待查询实体
	 * @param offset 开始页
	 * @param pageSize 每页显示数据条数
	 * @param dataAuthority 数据权限字符串
	 * @return 每页结果集
	 */
//	List<BimrDuty> getEmpAchivesList(BimrDuty entity, Integer offset,Integer pageSize, String dataAuthority);
	
	Integer getEmpAccsidListCount(EmployeeAccountabilityViewId entity,String dataAuthority);
	
	List<EmployeeAccountabilityViewId> getEmpAccidList(EmployeeAccountabilityViewId entity, Integer offset,Integer pageSize,String dataAuthority);
	
	List<BimrAccountable> getPersionAccountability(int projectId,int projectType);
	
	BimrAccountable getBimrAccountableById(int id);
	
	List<CompliancePersionAbility> getCompliancePersionAbility(int complianceId);
	
	//获取风险事件情况
		List<BimrRiskEvent> getRisklist(String orgId,Integer offset, Integer pageSize);
	//获取企业风控审计信息
		List<BimrInsideAuditProject> getFkAuditProjects(String orgId,Integer offset, Integer pageSize) ;
		//获取企业风控合规信息
		List<BimrCompliance> getFkCompliance(String orgId,Integer offset, Integer pageSize) ;	
		//获取企业风控民事案件信息
		List<BimrCivilcaseWeek> getFkCivilcaseWeek(String orgId,Integer offset, Integer pageSize) ;	
		//获取企业风控刑事案件信息
		List<BimrCriminalcaseWeek> getFkCriminalcaseWeek(String orgId,Integer offset, Integer pageSize);	
		//获取企业风控刑事合同信息
		List<Object[]> getFkContractMonth(String orgId,Integer offset, Integer pageSize);
//		查询
		List<BimrDuty> getEmpAchivesList(BimrDuty entity, Integer offset,Integer pageSize, String dataAuthority);	
		
//		导出
		public List<BimrDuty> getempArchivesCaseExport(BimrDuty entity, String dataAuthority);
//		public List<BimrDuty> getcivilCaseExport(BimrDuty entity,int type);
}
