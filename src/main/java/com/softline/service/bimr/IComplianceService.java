package com.softline.service.bimr;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.softline.entity.HhUsers;
import com.softline.entity.bimr.BimrCompliance;
import com.softline.entity.bimr.BimrComplianceBJInfo;
import com.softline.entity.bimr.BimrCompliancePerson;
import com.softline.entity.bimr.BimrComplianceProgress;
import com.softline.entity.bimr.BimrCompliancePrompt;
import com.softline.entity.bimr.BimrComplianceSituation;
import com.softline.entity.bimr.BimrComplianceSituationPerson;
import com.softline.entity.bimr.BimrComplianceSuggest;
import com.softline.entity.bimr.BimrDuty;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;
/**
 * 
 * @author tch
 *
 */
public interface IComplianceService {

	public MsgPage getComplianceDaoListView(BimrCompliance entity,Integer curPageNum, Integer pageSize);
	
	public BimrCompliance getComplianceById(Integer id);
	
	public CommitResult saveCompliance(BimrCompliance entity,List<BimrCompliancePerson> personList,HhUsers use,MultipartFile file1,String manage, MultipartFile indictmentFile);
	
	public CommitResult deleteCompliance(BimrCompliance entity, HhUsers use);
	
	public List<BimrCompliancePerson> getCompliancePerson(Integer complianceId);
	
	public List<BimrComplianceSituation> getComplianceSituation(Integer complianceId,Integer dataType);

	MsgPage getReportBJListView(BimrCompliance entity, Integer pageNum, Integer pageSize);
	
	CommitResult saveReportBJ(BimrComplianceBJInfo entity, HhUsers users, MultipartFile multFile, String package_path);
	
	MsgPage getQueryListView(BimrCompliance entity, Integer pageNum, Integer pageSize);
	
	MsgPage getAssignListView(BimrCompliance entity, Integer pageNum, Integer pageSize);
	
	MsgPage getCorrectListView(BimrCompliance entity, Integer pageNum, Integer pageSize);
	
	MsgPage getCorrectListExamineView(BimrCompliance entity, Integer pageNum, Integer pageSize);
	
	public CommitResult saveComplianceSituation(BimrComplianceSituation entity,List<BimrComplianceSituationPerson> personList,HhUsers use, MultipartFile file1);
	
	public BimrComplianceSituation getComplianceSituationById(Integer id);
	
	public List<BimrComplianceSituationPerson> getComplianceSituationPerson(Integer situationId);
	
	public BimrCompliancePrompt getCompliancePromptById(Integer id);
	
	public List<BimrCompliancePrompt> getCompliancePrompt(Integer complianceId);
	
	public CommitResult saveCompliancePrompt(BimrCompliancePrompt entity,HhUsers use, MultipartFile file1);
	
	public List<BimrComplianceSuggest> getComplianceSuggest(Integer complianceId);
	
	public BimrComplianceSuggest getComplianceSuggestById(Integer id);
	
	public CommitResult saveComplianceSuggest(BimrComplianceSuggest entity,HhUsers use, MultipartFile file1);
	
	public BimrComplianceProgress getComplianceProgressById(Integer id);
	
	public List<BimrComplianceProgress> getComplianceProgress(Integer complianceId);
	
	public CommitResult saveComplianceProgress(BimrComplianceProgress entity,HhUsers use, MultipartFile file1);
	
	BimrComplianceBJInfo getBJInfoByComplianceId(Integer complianceId);
	
	BimrComplianceSuggest getSuggestById(Integer id);
	
	List<BimrComplianceSuggest> getSuggestListByComplianceId(Integer complianceId);
	
	List<BimrComplianceSituationPerson> getSituationPersonListByComplianceId(Integer complianceId);
	
	CommitResult saveCorrectProblem(BimrComplianceSuggest entity, HhUsers user);
	
	BimrComplianceSituationPerson getSituationPerson(Integer id);
	
	CommitResult saveSituationPerson(BimrComplianceSituationPerson entity, HhUsers user);
	
	CommitResult saveCorrectArchive(Integer id, HhUsers user);
	
	CommitResult saveErrorArchive(Integer id, HhUsers user);
	
	CommitResult saveCorrectStatus(Integer id, HhUsers user);
	
	public CommitResult updateStatus(BimrCompliance entity, HhUsers use);
	
	CommitResult saveAssignFollowPerson(Integer id, String followPerson, HhUsers user);
	public List<BimrCompliance>  getcompliance(BimrCompliance compliance1);
//	合规调查基本信息维护  查询导出
	//查询距离整改时限不到30天项目信息和跟踪人信息
	boolean getSuggestAndEmail();

	public List<Object[]> getQueryExport(BimrCompliance entity1,
			BimrCompliancePerson entity2, BimrComplianceSituation entity3,
			BimrComplianceSituationPerson entity4, BimrComplianceSuggest entity5,
			BimrComplianceBJInfo entity6, String dataAuthority,
			String vcEmployeeId);

	public XSSFWorkbook getQueryExportWorkbook1(List<Object[]> list1);
	
	public XSSFWorkbook complanceExport(BimrCompliance entity);
	
}
