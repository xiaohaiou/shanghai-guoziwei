package com.softline.service.bimr.impl;

import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.dao.bimr.IEmpArchivesDao;
import com.softline.entity.EmployeeAccountabilityViewId;
import com.softline.entity.HhBase;
import com.softline.entity.SysExamine;
import com.softline.entity.bimr.BimrAccountable;
import com.softline.entity.bimr.BimrCivilcaseWeek;
import com.softline.entity.bimr.BimrCompliance;
import com.softline.entity.bimr.BimrCriminalcaseWeek;
import com.softline.entity.bimr.BimrDuty;
import com.softline.entity.bimr.BimrInsideAuditProject;
import com.softline.entity.bimr.BimrRiskEvent;
import com.softline.entity.project.PjNode;

import com.softline.entityTemp.CommitResult;
import com.softline.entityTemp.CompliancePersionAbility;
import com.softline.service.bimr.IEmpArchivesService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.IExamineService;
import com.softline.util.MsgPage;

/**
 * 干部员工风控档案ServiceImpl
 * 
 */
@Service("empArchivesService")
public class EmpArchivesServiceImpl implements IEmpArchivesService {
	
	@Autowired @Qualifier("empArchivesDao") 
	private IEmpArchivesDao empArchivesDao;
	@Autowired 
	private ICommonService commonService;
	@Autowired
	private IExamineService examineService;
	@Override
	public MsgPage getEmpAchivesList(BimrDuty entity, Integer curPageNum,Integer pageSize, String dataAuthority) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = empArchivesDao.getEmpAchivesListCount(entity, dataAuthority);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum, pageSize);  
    	//分页查询结果集
    	List<BimrDuty> list = empArchivesDao.getEmpAchivesList(entity, offset, pageSize, dataAuthority);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}
	
	@Override
	public BimrDuty getBimrDuty(BimrDuty entity) {
		return empArchivesDao.getById(entity.getId());
	}
	@Override
	public List<BimrDuty> getByPersonId(String personId) {
		// TODO Auto-generated method stub
		return empArchivesDao.getByPersonId(personId);
	}
	


	@Override
	public List<BimrAccountable> getPersionAccountability(
			int projectId,int projectType) {
		// TODO Auto-generated method stub
		return empArchivesDao.getPersionAccountability(projectId,projectType);
	}

	@Override
	public void saveAcountable(BimrAccountable entity) {
		// TODO Auto-generated method stub
		commonService.saveOrUpdate(entity);
		
	}

	@Override
	public List<CompliancePersionAbility> getCompliancePersionAbility(
			int complianceId) {
		// TODO Auto-generated method stub
		return empArchivesDao.getCompliancePersionAbility(complianceId);
	}

	@Override
	public MsgPage getEmpAccountAbilityId(EmployeeAccountabilityViewId entity,
			Integer curPageNum, Integer pageSize,String dataAuthority) {
		// TODO Auto-generated method stub
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = empArchivesDao.getEmpAccsidListCount(entity,dataAuthority);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum, pageSize);
    	List<EmployeeAccountabilityViewId> list = empArchivesDao.getEmpAccidList(entity, offset, pageSize,dataAuthority);
    	EmployeeAccountabilityViewId e=new EmployeeAccountabilityViewId();
    	
    	for (int i = 0; i < list.size(); i++) {
			if(null!=list.get(i)){
				 e=list.get(i);
				 
				if(1==e.getProjecttype()){
					List<BimrAccountable> accountable_list=getPersionAccountability(e.getId(),e.getProjecttype());
					//如果问责人员表中没有此项目的问题和人员信息，则静止审核等功能
					 if(accountable_list.size()==0){
						 e.setStatus(4);
						 continue;
					 }
				}else if(2==e.getProjecttype()){
					//如果此项目无问责人员或问题，则静止审核等功能
					/*List<CompliancePersionAbility> compliancePersionAbilities=getCompliancePersionAbility(e.getId());
					if(compliancePersionAbilities.size()==0){
						e.setStatus(4);
						continue;
					}else {
						if (e.getStatus()==0||null==e.getStatus()) {
							e.setStatus(5);
							continue;
						}*/
					e.setStatus(2);
					
					
				}else if (3==e.getProjecttype()) {
					List<BimrAccountable> accountable_list=getPersionAccountability(e.getId(),e.getProjecttype());
					//如果问责人员表中没有此项目的问题和人员信息，则静止审核等功能
					 if(accountable_list.size()==0){
						 e.setStatus(4);
						 continue;
					 }
				}else {
					List<BimrAccountable> accountable_list=getPersionAccountability(e.getId(),e.getProjecttype());
					//如果问责人员表中没有此项目的问题和人员信息，则静止审核等功能
					 if(accountable_list.size()==0){
						 e.setStatus(4);
						 continue;
					 }
				}
				
			}
		}
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}

	@Override
	public void saveBimrDuty(BimrDuty entity) {
		// TODO Auto-generated method stub
		commonService.saveOrUpdate(entity);
	}

	@Override
	public BimrAccountable getBimrAccountableById(int id) {
		// TODO Auto-generated method stub
		return empArchivesDao.getBimrAccountableById(id);
	}
	@Override
	public MsgPage getRisklist(String orgId, Integer curPageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		MsgPage msgPage = new MsgPage();
		//总记录数
		List<BimrRiskEvent> t = empArchivesDao.getRisklist(orgId,null,null);
        Integer totalRecords = t.size();
        //当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<BimrRiskEvent> list = empArchivesDao.getRisklist(orgId, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}
	@Override
	public MsgPage getFkAuditProjects(String orgId, Integer curPageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		MsgPage msgPage = new MsgPage();
		//总记录数
		List<BimrInsideAuditProject> t = empArchivesDao.getFkAuditProjects(orgId,null,null);
        Integer totalRecords = t.size();
        //当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<BimrInsideAuditProject> list = empArchivesDao.getFkAuditProjects(orgId, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}

	@Override
	public MsgPage getFkCompliance(String orgId, Integer curPageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		MsgPage msgPage = new MsgPage();
		//总记录数
		List<BimrCompliance> t = empArchivesDao.getFkCompliance(orgId,null,null);
        Integer totalRecords = t.size();
        //当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<BimrCompliance> list = empArchivesDao.getFkCompliance(orgId, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}

	@Override
	public MsgPage getFkCivilcaseWeek(String orgId, Integer curPageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		MsgPage msgPage = new MsgPage();
		//总记录数
		List<BimrCivilcaseWeek> t = empArchivesDao.getFkCivilcaseWeek(orgId,null,null);
        Integer totalRecords = t.size();
        //当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<BimrCivilcaseWeek> list = empArchivesDao.getFkCivilcaseWeek(orgId, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}

	@Override
	public MsgPage getFkCriminalcaseWeek(String orgId, Integer curPageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		MsgPage msgPage = new MsgPage();
		//总记录数
		List<BimrCriminalcaseWeek> t = empArchivesDao.getFkCriminalcaseWeek(orgId,null,null);
        Integer totalRecords = t.size();
        //当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<BimrCriminalcaseWeek> list = empArchivesDao.getFkCriminalcaseWeek(orgId, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}

	@Override
	public MsgPage getFkContractMonth(String orgId, Integer curPageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		MsgPage msgPage = new MsgPage();
		//总记录数
		List<Object[]> t = empArchivesDao.getFkContractMonth(orgId,null,null);
        Integer totalRecords = t.size();
        //当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<Object[]> list = empArchivesDao.getFkContractMonth(orgId, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}
//	public List<BimrCivilcaseWeek> getcivilCaseExport(BimrCivilcaseWeek civilcase,String caseDateStart,String caseDateEnd,String amountSection,int type) {
//		// TODO Auto-generated method stub
//		List<BimrCivilcaseWeek> list = caseDao.getcivilCaseExport(civilcase,caseDateStart,caseDateEnd,amountSection,type);
//		return list;
//	}
	
//	查询
	@Override
	public List<BimrDuty> getempArchivesCase(BimrDuty entity, Integer offset,Integer pageSize, String dataAuthority) {
		// TODO Auto-generated method stub
		List<BimrDuty> list = empArchivesDao.getEmpAchivesList(entity,offset,pageSize,dataAuthority);
		return list;
	}


//	导出

	@Override
	public List<BimrDuty> getempArchivesCaseExport(BimrDuty entity, String dataAuthority) {
		List<BimrDuty> list = empArchivesDao.getempArchivesCaseExport(entity,dataAuthority);
		return list;
	}

//	@Override
//	public XSSFWorkbook getempArchivesExportWorkbook(List<BimrDuty> list1) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	@Override
	public XSSFWorkbook getempArchivesExportWorkbook(List<BimrDuty> list1){
	
		XSSFWorkbook workBook = new XSSFWorkbook();
		   CellStyle style=workBook.createCellStyle();
//		   CellStyle style1=workBook.createCellStyle();
		   XSSFFont font = workBook.createFont();
//		   XSSFFont font1 = workBook.createFont();
		   font.setBold(true);
//			 创建一个工作簿
			 XSSFCell cell = null;
			 int count=0;
//			 设置单元格为空
			 XSSFSheet sheet = workBook.createSheet("干部人员导出");
			 
			 

			 XSSFRow row = sheet.createRow((int) 0);  
			 cell = row.createCell((short) 0);

			 empArch(list1,sheet,style, 1);

		return workBook;
	}
//"判决/调解金额(人民币万元)","已执行款项(人民币万元)","避免/挽回经济损失(人民币万元)","案件编号","案件归属单位"
	public void empArch(List<BimrDuty> list,XSSFSheet sheet, CellStyle style,int type) {
		XSSFRow row=sheet.createRow((int)0);
		String [] header1={"员工姓名","员工号","现任职单位","任职职务","问责来源","发生时间","问责原因","建议问责落实情况","处分问责承办公文编号",
				 "处分问责办文编号"};
		 
		
		
		 
		 
		 XSSFCell cell = null;
		 for (int i = 0; i < header1.length; i++) {
				cell = row.createCell((short) i);
				cell.setCellValue(header1[i]);

		}
		/* style.setFillForegroundColor(IndexedColors.RED.getIndex());  
	        style.setFillPattern(CellStyle.SOLID_FOREGROUND); */
//		 font1.setColor(IndexedColors.RED.index);
//		 style.setFont(font1);
		 for (int i = 0; i < list.size(); i++) {
				int k=0;
				row=sheet.createRow((int) i + 1);
				BimrDuty  value=list.get(i);
//				任职单位personCompany任职职务personPost happenDate发生时间	
//				reason问责原因	建议问责落实情况	proposal处分问责呈报公文编号bumfNum处分问责办文编号articleNum
				row.createCell((short)k++).setCellValue(value.getPerson());
				row.createCell((short)k++).setCellValue(value.getPersonId());
				row.createCell((short)k++).setCellValue(value.getPersonCompany());
				row.createCell((short)k++).setCellValue(value.getPersonPost());
				


			     if (value.getSource().equals(1)) {
			    	 row.createCell((short)k++).setCellValue("风险信息报告");
				}else if(value.getSource().equals(2)){
					row.createCell((short)k++).setCellValue("案件事项");
				}else if(value.getSource().equals(3)){
					row.createCell((short)k++).setCellValue("合同事项");
				}else if(value.getSource().equals(4)){
					row.createCell((short)k++).setCellValue("内部审计");
				}else if(value.getSource().equals(5)){
					row.createCell((short)k++).setCellValue("合规调查");
				}
				else {
					row.createCell((short)k++).setCellValue("其他事项");
				}
//				row.createCell((short)k++).setCellValue(value.getSource());
//				调查来源
				row.createCell((short)k++).setCellValue(value.getHappenDate());
				row.createCell((short)k++).setCellValue(value.getReason());
				row.createCell((short)k++).setCellValue(value.getProposal());
				row.createCell((short)k++).setCellValue(value.getBumfNum());
				row.createCell((short)k++).setCellValue(value.getArticleNum());

		 
	}
	}
	
	
}
