package com.softline.dao.bimr;

import java.util.List;

import com.softline.entity.bimr.BimrCompliance;
import com.softline.entity.bimr.BimrComplianceBJInfo;
import com.softline.entity.bimr.BimrCompliancePerson;
import com.softline.entity.bimr.BimrComplianceProgress;
import com.softline.entity.bimr.BimrCompliancePrompt;
import com.softline.entity.bimr.BimrComplianceSituation;
import com.softline.entity.bimr.BimrComplianceSituationPerson;
import com.softline.entity.bimr.BimrComplianceSuggest;
import com.softline.entity.bimr.BimrDuty;

/**
 * 合规调查数据操作
 * 
 * @author tch
 */
public interface IComplianceDao {
	
	public int getComplianceListCount(BimrCompliance entity);
	
	public List<BimrCompliance> getComplianceList(
			BimrCompliance entity, Integer offsize, Integer pagesize);
	
	public BimrCompliance getComplianceById(Integer id);
	
	public List<BimrCompliancePerson> getCompliancePerson(Integer complianceId);
	
	public List<BimrComplianceSituation> getComplianceSituation(Integer complianceId,Integer dataType);

	Integer getListCountByStatuses(BimrCompliance entity, Integer[] statuses);
	
	List<BimrCompliance> getListByStatuses(
			BimrCompliance entity, Integer[] statuses, Integer offset, Integer pageSize);
	//导出
	List<BimrCompliance> getListByStatusesExport(
			BimrCompliance entity, Integer[] statuses);
	
	public List<BimrComplianceSituationPerson> getComplianceSituationPerson(Integer situationId);
	
	public BimrComplianceSituation getComplianceSituationById(Integer id);
	
	public BimrCompliancePrompt getCompliancePromptById(Integer id);
	
	public List<BimrCompliancePrompt> getCompliancePrompt(Integer complianceId);
	
	public BimrComplianceSuggest getComplianceSuggestById(Integer id);
	
	public List<BimrComplianceSuggest> getComplianceSuggest(Integer complianceId);
	
	public BimrComplianceProgress getComplianceProgressById(Integer id);
	
	public List<BimrComplianceProgress> getComplianceProgress(Integer complianceId);

	BimrComplianceBJInfo getBJInfoByComplianceId(Integer complianceId);
	
	List<BimrComplianceSuggest> getSuggestListByComplianceId(Integer complianceId);
	
	List<BimrComplianceSituationPerson> getSituationPersonListByByComplianceId(Integer complianceId);
	public List<BimrCompliance>  getcompliance(BimrCompliance compliance1);
//	合规调查基本信息维护  查询
	
	//获取项目编号
	public List getProjectCodelist(String projectCode,int id);
	
	
	public List<Object[]> getgetSuggestAndEmail();
	
	
	public int saveEmailInfo(String date,String sendPerson,String recivePerson,int status,int type,String emailtitle,String result);

	public List<Object[]> getQueryExportDC(BimrCompliance entity1,BimrCompliancePerson entity2, BimrComplianceSituation entity3,
											BimrComplianceSituationPerson entity4, BimrComplianceSuggest entity5,
											BimrComplianceBJInfo entity6, String dataAuthority,
											String vcEmployeeId);
	
	public Integer getComplianceAbilityCount(Integer id);
}
