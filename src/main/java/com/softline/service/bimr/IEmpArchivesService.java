package com.softline.service.bimr;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.softline.entity.EmployeeAccountabilityViewId;

import com.softline.entity.HhUsers;
import com.softline.entity.bimr.BimrAccountable;
import com.softline.entity.bimr.BimrCivilcaseWeek;
import com.softline.entity.bimr.BimrCompliance;
import com.softline.entity.bimr.BimrCriminalcaseWeek;
import com.softline.entity.bimr.BimrDuty;
import com.softline.entity.bimr.BimrInsideAuditProject;
import com.softline.entity.bimr.BimrRiskEvent;

import com.softline.entityTemp.CommitResult;
import com.softline.entityTemp.CompliancePersionAbility;
import com.softline.util.MsgPage;

/**
 * 员工问责Service
 * @author pengguolin
 */
public interface IEmpArchivesService {
	/**
	 * 分页查询结果集
	 * @param entity 待查询实体
	 * @param curPageNum 当前页
	 * @param pageSize 每页显示条数
	 * @param dataAuthority 数据权限字符串
	 * @return 每页结果集
	 */
	MsgPage getEmpAchivesList(BimrDuty entity, Integer curPageNum, Integer pageSize,String dataAuthority);
	
	BimrDuty getBimrDuty(BimrDuty entity);
	
	//根据员工号获取员工问责信息
			List<BimrDuty> getByPersonId(String personId);
	
	
	//获取问责填报数据
	MsgPage getEmpAccountAbilityId(EmployeeAccountabilityViewId entity, Integer curPageNum, Integer pageSize,String dataAuthority);
	//获取问责人员信息
	List<BimrAccountable> getPersionAccountability(int projectId,int projectType);
	//获取问责人员信息
	BimrAccountable getBimrAccountableById(int id);
	//保存问责人员信息
	void saveAcountable(BimrAccountable entity);
	//获取合规问责人员信息
	List<CompliancePersionAbility> getCompliancePersionAbility(int complianceId);
	
	void saveBimrDuty(BimrDuty enetity);
	//获取风险事件情况
	MsgPage getRisklist(String orgId, Integer curPageNum, Integer pageSize);
	//获取企业风控审计信息
		MsgPage getFkAuditProjects(String orgId, Integer curPageNum, Integer pageSize) ;
	
	//获取企业风控合规信息
	MsgPage getFkCompliance(String orgId, Integer curPageNum, Integer pageSize) ;
	//获取企业风控民事案件信息
		MsgPage getFkCivilcaseWeek(String orgId, Integer curPageNum, Integer pageSize) ;	
	//获取企业风控刑事案件信息
		MsgPage getFkCriminalcaseWeek(String orgId, Integer curPageNum, Integer pageSize);	
	//获取企业风控刑事合同信息
		MsgPage getFkContractMonth(String orgId, Integer curPageNum, Integer pageSize);	
//		public List<BimrDuty> getempArchivesCase(BimrDuty entity,int type);
//		查询
//		public List<BimrDuty> getEmpAchivesListCount(BimrCivilcaseWeek civilcase,String caseDateStart,String caseDateEnd,String amountSection,int type);

		List<BimrDuty> getempArchivesCase(BimrDuty entity, Integer offset,
				Integer pageSize, String dataAuthority);
		
							 
		public List<BimrDuty> getempArchivesCaseExport(BimrDuty entity,  String dataAuthority);
		/**
		 * BimrDuty entity, Integer offset,Integer pageSize, String dataAuthority
		 */

		XSSFWorkbook getempArchivesExportWorkbook(List<BimrDuty> list1);

	
		
}
