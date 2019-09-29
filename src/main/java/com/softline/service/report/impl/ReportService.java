package com.softline.service.report.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.softline.common.Base;
import com.softline.common.Cale;
import com.softline.common.Common;
import com.softline.common.DES;
import com.softline.common.ExcelTool;
import com.softline.dao.report.IReportDao;
import com.softline.dao.report.IReportRemindCompanyDao;
import com.softline.dao.system.ICommonDao;
import com.softline.dao.system.IExamineDao;
import com.softline.dao.system.IFinanceHistroyTreeDao;
import com.softline.dao.system.ISDKDao;
import com.softline.dao.system.ISystemDao;
import com.softline.entity.HhFile;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhOrganInfoTreeRelationHistory;
import com.softline.entity.HhUsers;
import com.softline.entity.HhUsersmessageinfo;
import com.softline.entity.MainFinancialIndicator;
import com.softline.entity.MainFinancialIndicatorStock;
import com.softline.entity.ReportExportout;
import com.softline.entity.ReportForms;
import com.softline.entity.ReportFormsCell;
import com.softline.entity.ReportFormsGroup;
import com.softline.entity.ReportFormsOrganal;
import com.softline.entity.ReportFormsPattend;
import com.softline.entity.ReportFormsUnFilledCompany;
import com.softline.entity.SysExamineReport;
import com.softline.entityTemp.CellMyStyle;
import com.softline.entityTemp.CommitResult;
import com.softline.entityTemp.ExcelMergedRegin;
import com.softline.entityTemp.ReportIndexAndData;
import com.softline.entityTemp.ReportQueryView;
import com.softline.entityTemp.Report_Forms_DetailView;
import com.softline.entityTemp.Report_formsView;
import com.softline.entityTemp.Report_forms_groupView;
import com.softline.entityTemp.TableCell;
import com.softline.service.report.IMainFinancialIndicatorService;
import com.softline.service.report.IReportService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IFinanceHistroyTreeService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Service("reportService")
public class ReportService implements IReportService{

	private static final boolean ReportForms = false;
	@Autowired
	@Qualifier("reportDao")
	private IReportDao reportDao;
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	@Autowired
	@Qualifier("sdkDao")
	private ISDKDao sdkDao;
	@Autowired
	@Qualifier("systemDao")
	private ISystemDao systemDao;
	@Resource(name = "examineService")
	private IExamineService examineService;
	@Resource(name = "systemService")
	private ISystemService systemService;
	@Resource(name = "commonService")
	private ICommonService commonService;
	
	@Resource(name = "mainFinancialIndicatorsService")
	private IMainFinancialIndicatorService mainFinancialIndicatorsService;
	
	@Resource(name = "financeHistroyTreeService")
	private IFinanceHistroyTreeService financeHistroyTreeService;
	
	@Autowired
	@Qualifier("financeHistroyTreeDao")
	private IFinanceHistroyTreeDao financeHistroyTreeDao;
	
	@Autowired
	@Qualifier("examineDao")
	private IExamineDao examineDao;
	
	@Autowired
	@Qualifier("reportRemindCompanyDao")
	private IReportRemindCompanyDao reportRemindCompanyDao;
		
	/**
	 * 保存报表组以及其下的所有报表的检查
	 * @param entity
	 * @return
	 */
	private CommitResult saveReportFormsGroupCheck(ReportFormsGroup entity)
	{
		CommitResult result=new CommitResult();
		result.setResult(false);
		if(entity==null)
		{
			result.setResult(false);
			result.setExceptionStr("保存内容不能为空");
			return result;
		}
		String checkresult=reportDao.checkReportFormsGroups(entity);
		if(checkresult.equals(""))
			result.setResult(true);
		else
			result.setExceptionStr(checkresult);
		return result;
	}
	
	
	/**
	 * 保存报表组以及其下的所有报表
	 * @param entity
	 * @param reportGroupFile 整体导出文件
	 * @param reportGroupExportFile 导入文件
	 * @return
	 */
	public CommitResult saveReportFormsGroup(Report_forms_groupView entity,HhUsers use,MultipartFile reportGroupFile,MultipartFile reportGroupExportFile)
	{
	 	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult returnData=saveReportFormsGroupCheck(entity.getGroup());
		if(!returnData.isResult())
			return returnData;
		entity.getGroup().setIsdel(0);
		entity.getGroup().setCreateDate(df.format(new Date()));
		entity.getGroup().setCreatePersonId(use.getVcEmployeeId());
		entity.getGroup().setCreatePersonName(use.getVcName());
		
		commonDao.saveOrUpdate(entity.getGroup());
		Integer groupID=entity.getGroup().getId();
		ReportForms forms=new ReportForms();
		forms.setGroupId(groupID);
		List<ReportForms> oldforms=  reportDao.getReportFormsList(forms,null,null);
		Map<Integer, Boolean> hasmap=new HashMap();
		int sort=1;
		if(oldforms!=null && oldforms.size()!=0)
		{
			for (int i = 0; i < entity.getGroup_item().size(); i++) {

				ReportForms item = entity.getGroup_item().get(i);
				item.setIsdel(0);
				//新数据
				if(item.getId()==null)
				{
					item.setSort(sort);
					item.setGroupId(groupID);
					item.setCreateDate(df.format(new Date()));
					item.setCreatePersonId(use.getVcEmployeeId());
					item.setCreatePersonName(use.getVcName());
					commonDao.saveOrUpdate(item);
					continue;
				}
				//处理旧数据
				for (Iterator iterator = oldforms.iterator(); iterator.hasNext();)
				{
					ReportForms olditem = (ReportForms) iterator.next();
					if(item.getId()!=null && item.getId().equals(olditem.getId()))
					{
						olditem.setSort(sort);
						olditem.setLastModifyDate(df.format(new Date()));
						olditem.setLastModifyPersonId(use.getVcEmployeeId());
						olditem.setLastModifyPersonName(use.getVcName());
						olditem.setFormkind(item.getFormkind());
						olditem.setFre(item.getFre());
						commonDao.saveOrUpdate(olditem);
						oldforms.remove(olditem);
						break;
					}
				}
				sort++;
			}
			//删除旧的
			for (ReportForms reportForms : oldforms) {
				reportDao.deleteReportForms(reportForms.getId().toString(), use);
				//commonDao.saveOrUpdate(reportForms);
			}
		}
		else
		{
			for (ReportForms item : entity.getGroup_item()) {
				item.setIsdel(0);
				if(item.getId()==null)
				{
					item.setSort(sort);
					item.setGroupId(groupID);
					item.setCreateDate(df.format(new Date()));
					item.setCreatePersonId(use.getVcEmployeeId());
					item.setCreatePersonName(use.getVcName());
					commonDao.saveOrUpdate(item);
					sort++;
				}
			}
		}
		//excel 整体导出文件
		if(reportGroupFile!=null && reportGroupFile.getSize()>0)
		{
			//文件修改
	        HhFile _file = commonService.getFileByEnIdAndType(entity.getGroup().getId(),Base.reportGroupFile);
	        //以前有就覆盖写入
			if (_file != null ) {
				updateFile(_file,reportGroupFile,DES.reportgroup_path);
			}else{
				_file=commonService.saveFile(reportGroupFile, entity.getGroup().getId(),Base.reportGroupFile, DES.reportgroup_path);
			}
		}
		//excel 导入文件
		if(reportGroupExportFile!=null && reportGroupExportFile.getSize()>0)
		{
			//文件修改
	        HhFile _file = commonService.getFileByEnIdAndType(entity.getGroup().getId(),Base.reportGroupExportIn);
	        //以前有就覆盖写入
			if (_file != null) {
				updateFile(_file,reportGroupExportFile,DES.reportgroup_path);
			}else{
				_file=commonService.saveFile(reportGroupExportFile, entity.getGroup().getId(),Base.reportGroupExportIn, DES.reportgroup_path);
			}
		}
		return returnData;
	}
	
	/**
	 * 删除报表组
	 * @param groupID
	 * @param user
	 */
	public CommitResult deleteReportFormsGroup(int groupID,HhUsers user)
	{
		CommitResult result=new CommitResult();
		reportDao.deleteReportFormsGroups(groupID, user);
		result.setResult(true);
		return result;
	}
	
	/**
	 * 根据查询实体，获取某个报表组及其组中的报表
	 * @param entity
	 * @return
	 */
	public Report_forms_groupView getReport_forms_groupView(ReportFormsGroup entity)
	{
		Report_forms_groupView returnData=new Report_forms_groupView();
		if(entity==null)
			return returnData;
		ReportFormsGroup group=	reportDao.getReportFormsGroup(entity);
		returnData.setGroup(group);
		ReportForms searchReportForms=new ReportForms();
		searchReportForms.setGroupId(group.getId());
		List<ReportForms> ReportFormsList=reportDao.getReportFormsList(searchReportForms,null,null);
		returnData.setGroup_item(ReportFormsList);
		return returnData;
	} 
	
	/**
	 * 根据查询实体，获取报表组列表
	 * @param entity
	 * @return
	 */
	public MsgPage getReportformsgroupView(ReportFormsGroup entity, Integer curPageNum, Integer pageSize)
	{
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = reportDao.getReportFormsGroupsListCount(entity);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<ReportFormsGroup> list = reportDao.getReportFormsGroupsList(entity, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}
	
	/**
	 * 根据查询实体，获取报表组列表
	 * @param entity
	 * @return
	 */
	public List<ReportFormsGroup> getReportformsgroupList(ReportFormsGroup entity)
	{
	
    	return reportDao.getReportFormsGroupsList(entity, null, null);
    
	}
	/**
	 * 根据查询实体，获取一个报表组 (通过ID，或者属性、类型和时间的组合键能唯一确定)
	 * @param entity
	 * @return
	 */
	public ReportFormsGroup getReportFormsGroup(ReportFormsGroup entity)
	{
		return reportDao.getReportFormsGroup(entity);
	}
	////针对reportform 和reportpattend 因为这两个一个报表对于一套样式所以查样式的时候查的是reportform的列表
	

	
	
	/**
	 * 查询报表列表
	 * @param entity
	 * @param curPageNum
	 * @param pageSize
	 * @return
	 */
	public MsgPage getReportformsView(Report_formsView entity, Integer curPageNum, Integer pageSize)
	{
		MsgPage msgPage = new MsgPage();       
       //总记录数
        Integer totalRecords = reportDao.getReportFormsListViewCount(entity);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<Report_formsView> list = reportDao.getReportFormsListView(entity, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);  
        return msgPage;
	}
	
	/**
	 * 查询报表
	 * @param entity
	 * @return
	 */
	public List<ReportForms> getReportformsList(ReportForms entity)
	{
		return reportDao.getReportformsList(entity);
	}
	
	/**
	 * 查询报表
	 * @param entity
	 * @param curPageNum
	 * @param pageSize
	 * @return
	 */
	public ReportForms getReportforms(ReportForms entity)
	{
		return reportDao.getReportForms( entity);
	}
	
	
	/**
	 * 保存一张报表的样式的检查
	 * @param wb 报表数据
	 * @param formsID 报表样式对应的报表ID
	 * @return
	 */
	private CommitResult saveReportPattendCheck(String xlsPath,Integer formsID)
	{
		CommitResult returnData=new CommitResult();
        int iIndex = xlsPath.lastIndexOf(".");
        String ext = (iIndex < 0) ? "" : xlsPath.substring(iIndex + 1).toLowerCase();
        if ( ( !"xls".contains(ext) && !"xlsx".contains(ext) )|| "".contains(ext)) {
        	returnData.setResult(false);
        	returnData.setExceptionStr("请上传xls或者xlsx文件！");
        	return returnData;
        }
        if (formsID==null) {
        	returnData.setResult(false);
        	returnData.setExceptionStr("未选定报表类型");
        	return returnData;
        }
        returnData.setResult(true);
		return returnData;
	}
	
	
	/**
	 * 保存一张报表的样式
	 * @param wb
	 * @param formsID
	 * @return
	 * @throws Exception 
	 */
	public CommitResult saveReportPattend(Integer formsId,HhUsers use,MultipartFile pattendFile,MultipartFile outFile) throws Exception
	{	
		CommitResult returnData=new CommitResult();
       try{ // String package_path = DES.report_path;
		ExcelTool exceltool=new ExcelTool();
        //先暂存附件
        List<String> fileStrList = Common.saveFile(pattendFile, DES.temp_path);
        //文件路径
        String xlsPath=fileStrList.get(1);
	 	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		returnData=saveReportPattendCheck( xlsPath, formsId);
		if(!returnData.isResult())
		   	 return returnData;

		//删除旧样式
		reportDao.deleteReportPattend(formsId.toString(),use);
		String regex = "(indexIDrow=(.*))(indexIDcol=(.*))(unit=(.*))(isdata=(.*))(isformula=(.*))(formula=(.*))";
		
		InputStream is = new FileInputStream(xlsPath);
	       
        Workbook workbook = null; 
        try { 
        	workbook = new XSSFWorkbook(is); 
        } catch (Exception ex) { 
        	 POIFSFileSystem poifsFileSystem= new POIFSFileSystem(new FileInputStream(xlsPath));
        	workbook = new HSSFWorkbook(poifsFileSystem); 
        } 
        Sheet  sheet = workbook.getSheetAt(0);
       
	         List<ReportFormsPattend> pattendlist=new ArrayList<ReportFormsPattend>();
	         for (int k = 0; k < sheet.getPhysicalNumberOfRows(); k++) {
	        	 Row row = sheet.getRow(k);
	              int col=(row==null) ? 0: row.getPhysicalNumberOfCells();
	              for (int j = 0; j < col; j++) {
	                  ReportFormsPattend pattend=new ReportFormsPattend();
	            	  ExcelMergedRegin data= exceltool.isMergedRegion(workbook,sheet,k,j);
	            	  if(data.isNeedignore())
	            		  continue;
	                  pattend.setStartcol(data.getStartcol());
	                  pattend.setStartrow(data.getStartrow());
	                  pattend.setEndcol(data.getEndcol());
	                  pattend.setEndrow(data.getEndrow());
	                  pattend.setColspan(data.getEndcol()-data.getStartcol()+1);
	                  pattend.setRowspan(data.getEndrow()-data.getStartrow()+1);
	                  pattend.setEndrow(data.getEndrow());
	                  pattend.setCreateDate(df.format(new Date()));
	                  pattend.setCreatePersonId(use.getVcEmployeeId());
	                  pattend.setCreatePersonName(use.getVcName());
	                  Cell b=row.getCell(j);
	                 
	                  CellStyle a= b.getCellStyle();
	                  Font font= workbook.getFontAt(a.getFontIndex()); 
	                  CellMyStyle style=new CellMyStyle(workbook,a, font);
	                  pattend.setCellcss(JSON.toJSONString(style));
	                  pattend.setFormsId(formsId);
	                  Pattern pattern = Pattern.compile(regex);
	                 
	                  pattend.setIsdel(0);
	                  pattend.setIsdata(false);
	                  pattend.setIsunit(false);
	                  pattend.setIsformula(false);
	                  if(Common.notEmpty(data.getValue()) )
	                  {
	                	  Matcher m = pattern.matcher(data.getValue());
		                  pattend.setValue(data.getValue());
		                  pattend.setIsunit(false);
		                  if (m.find()) 
		                  {
		                	    try
		                	    {
		                		    Integer indexIDrow= m.group(2).equals("") ? null: Integer.parseInt(m.group(2));
		                		    Integer indexIDcol= m.group(4).equals("") ? null: Integer.parseInt(m.group(4));
				                  	String[] unitstr= m.group(6).split(",");
				                  	Integer unit=Integer.parseInt(unitstr[0]);
				                  	Integer isunit=Integer.parseInt(unitstr[1]);
				                  	Integer isdata= Integer.parseInt(m.group(8));
				                  	String isformula=m.group(10);
				                  	String formula=m.group(12);
				                  	pattend.setIndexIdcol(indexIDcol);
				                  	pattend.setIndexIdrow(indexIDrow);	
				                  
				                  	pattend.setUnit(unit);
				                 
				                  	pattend.setFormula(formula);
				                  	if(isformula!=null && isformula.equals("1"))
				                  		pattend.setIsformula(true);
				                  	else
				                  		pattend.setIsformula(false);
				                  	if(isdata.equals(1))
				                  		pattend.setIsdata(true);
				                  	else if(isdata.equals(0))
				                  		pattend.setIsdata(false);
				                  	else
				                  		throw new Exception();
				                 	if(isunit.equals(1))
				                  		pattend.setIsunit(true);
				                  	else if(isunit.equals(0))
				                  		pattend.setIsunit(false);
				                  	else
				                  		throw new Exception();
				                 	pattend.setValue("");
				                 	//row.getCell(j).setCellValue("");
		                	  }
		                	  catch(Exception e) 
		                	  {   
		                		  TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		                		  returnData.setExceptionStr((k+1)+ "行"+(j+1)+"列样式   " +"  样式不符合规则");
		                		  returnData.setResult(false);
		                		  return returnData;
		                	  }
		                  }
	                  }else
	                  {
	                	  pattend.setValue("");
	                  }
	                 if(data.isMergedRegin())
	                	 j=data.getEndcol();
	                 pattendlist.add(pattend);
	                 //commonDao.saveOrUpdate(pattend);
				  }     
	         }
	         
	         reportDao.saveReportPattend(pattendlist);
	         
	        //文件修改
	        HhFile _file = commonService.getFileByEnIdAndType(formsId,Base.reportFile);
	        //以前有就覆盖写入
			if (_file != null) {
				updateFile(_file,outFile,DES.report_path);
			}else{
				_file=commonService.saveFile(outFile, formsId,  Base.reportFile, DES.report_path);
			}
	        ReportForms entity=new ReportForms();
	        entity.setId(formsId);
	        ReportForms oldReportForms =reportDao.getReportForms(entity);
	        oldReportForms.setFileId(_file.getId());
	        oldReportForms.setLastModifyDate(df.format(new Date()));
	        oldReportForms.setLastModifyPersonId(use.getVcEmployeeId());
	        oldReportForms.setLastModifyPersonName(use.getVcName());
	        commonDao.saveOrUpdate(oldReportForms);
	        //删掉临时文件
	        File tempfile=new File(xlsPath);
	        tempfile.delete();
       }
       catch(Exception e)
       {
    	   TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    	   returnData=CommitResult.createErrorResult("上传excel出现问题");
    	   e.printStackTrace();
       }
       finally{
		    return returnData;
       }
	}
	
	//替换文件
	private HhFile updateFile(HhFile file, MultipartFile multFile,String package_path) {
		try {
			File a=new File(file.getFilePath());
			a.delete();
			List<String> fileStrList = Common.saveFile(multFile, package_path);
			file.setFileName(fileStrList.get(0));
			file.setFilePath(fileStrList.get(1));
			file.setFileUuid(fileStrList.get(2));

			// 先执行保存
			commonService.saveOrUpdate(file);
			return file;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
		

	
	/**
	 * 保存报表值之前的校验
	 * @param organalID 机构ID
	 * @param formID 报表ID
	 * @param xlsPath 数据表
	 * @param xlsPath2 样式表
	 * @return
	 */
	private CommitResult saveReportValueCheck(String organalID,Integer formID,String xlsPath,String xlsPath2){
		CommitResult returnData=new CommitResult();
		if(!Common.notEmpty(organalID)){
			returnData.setExceptionStr("机构不能为空");
			returnData.setResult(false);
		}
		HhOrganInfoTreeRelation thisorgan=systemDao.getTreeOrganInfoNode(Base.financetype, organalID);
		if(thisorgan.getStatus().equals(Base.report_organal_invent))
		{
			returnData=CommitResult.createErrorResult("该机构是虚拟节点，不能上传报表");
		}
		if(formID==null)
		{
			returnData.setExceptionStr("请选择要提交的报表种类");
			returnData.setResult(false);
		}
	    int iIndex = xlsPath.lastIndexOf(".");
        String ext = (iIndex < 0) ? "" : xlsPath.substring(iIndex + 1).toLowerCase();
        if(Common.notEmpty(ext))
        {
	        if ( ( !"xls".contains(ext) &&  !"xlsx".contains(ext) ) || "".contains(ext)) {
	        	returnData.setResult(false);
	        	returnData.setExceptionStr("请上传xls,xlsx文件！");
	        	return returnData;
	        }
        }
        else
        {
        	returnData.setExceptionStr("未上传数据表！");
        	return returnData;
        }
        if(!Common.notEmpty(xlsPath2))
        {
        	ReportForms entity=new ReportForms();
        	entity.setId(formID);
        	ReportForms froms=reportDao.getReportForms(entity);
        	if(froms!=null)
        		returnData=CommitResult.createErrorResult("该组报表组内的"+froms.getFormkindName()+"未上传样式，请联系管理员！");
        	else
        		returnData=CommitResult.createErrorResult("该组报表组内无formID="+formID+"的报表，请联系管理员！");
        	return returnData;
        }
		returnData.setResult(true);
		return returnData;
	}
	////针对企业和报表关联的
	
	
	/**
	 * 获取组织机构和报表关联
	 * @param Date
	 * @param organal
	 * @param groupID
	 * @return
	 */
	public List<ReportFormsOrganal>getReportFormsOrganalData(String Date,String organal,Integer groupID)
	{
		
		ReportFormsOrganal search=new ReportFormsOrganal();
		search.setGroupID(groupID);
		search.setOrganalId(organal);
		search.setReportTime(Date);
		List<ReportFormsOrganal> organalentity=reportDao.getReportFormsOrganal(search);
		return organalentity;
	}
	
	/**
	 * 审核检查（都处于待审核才能审核）
	 * @param Date
	 * @param organal
	 * @param groupID
	 * @return
	 */
	public CommitResult saveReportFormExamineCheck(String Date,String organal,Integer groupID)
	{
		CommitResult result=new CommitResult();
		List<ReportFormsOrganal> organalentity=getReportFormsOrganalData( Date, organal, groupID);
		if( organalentity==null || organalentity.size()==0)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
			return result;
		}
		for (ReportFormsOrganal reportFormsOrganal : organalentity) {
			if(!reportFormsOrganal.getIsexamine().equals(Base.examstatus_2))
			{
				result=CommitResult.createErrorResult("该数据已被审核不需要再审核");
				return result;
			}
		}
		result.setResult(true);
		return result;
	}
	
	/**
	 * 	预算审核通过50115、回退操作、返回回退公司上层级所有公司信息
	 * @param Date
	 * @param use
	 * @param organal
	 * @param groupID
	 * @param examineresult
	 * @param examineStr
	 * @return
	 */
	@Override
	public CommitResult setBackExamineStatus(String Date,String organal,Integer groupID,Integer examineresult){
		CommitResult result=new CommitResult();
		Integer status;
		//审核通过
		if(examineresult.equals(Base.examresult_2))
			status=Base.examstatus_4;
		//审核不通过
		else 
			status=Base.examstatus_3;

		//判断是否为叶子公司，以及预算、核算栓选条件
		HhOrganInfoTreeRelation node=  systemDao.getTreeOrganInfoNode(Base.financetype,organal);
		List<HhOrganInfoTreeRelation> child=systemDao.getChildrenTreeOrganInfos(Base.financetype, node.getVcOrganId());
		ReportFormsGroup simplegroup=new ReportFormsGroup();
		simplegroup.setId(groupID);
		ReportFormsGroup examgroup= getReportFormsGroup(simplegroup);

		//预算叶子公司，审核未通过，且审核状态为已审核（50115），执行退回操作	
		if(status.equals(Base.examstatus_3)&& 
				examgroup.getNameID().equals(Base.reportgroup_BudgetEnforcement)&&
				(examgroup.getType().equals(Base.reportgroup_type_simple) || examgroup.getType().equals(Base.reportgroup_type_collect))){	
			//获取当前公司上面所有层级公司数据，并返回是否有已审核的标志位
			result=saveCheckedStatusBudgetEnforcementExmine(Date, organal);
			return result;
		}
		return result;
	}
	
	
	/**
	 * 审核
	 * @param Date 报表时间
	 * @param use 用户
	 * @param organal 公司ID
	 * @param groupID 组ID
	 * @param examineresult 审核结果
	 * @param examineStr 审核意见
	 * @return
	 * 	新增审核通过后生成预算叶子公司汇总数据校验，防止出现重复数据
	 * 		Author by zl 2018/8/28
	 * 
	 * 	新增核算公司，审核通过后，执行回退操作
	 * 		Author by zl 2018/8/29
	 */
	public CommitResult saveReportFormExamine(String Date,HhUsers use,String organal,Integer groupID,Integer examineresult,String examineStr){
		CommitResult result=new CommitResult();
		Integer status;
		//审核通过
		if(examineresult.equals(Base.examresult_2))
			status=Base.examstatus_4;
		//审核不通过
		else 
			status=Base.examstatus_3;

		//判断是否为叶子公司，以及预算、核算栓选条件
		HhOrganInfoTreeRelation node=  systemDao.getTreeOrganInfoNode(Base.financetype,organal);
		List<HhOrganInfoTreeRelation> child=systemDao.getChildrenTreeOrganInfos(Base.financetype, node.getVcOrganId());
		ReportFormsGroup simplegroup=new ReportFormsGroup();
		simplegroup.setId(groupID);
		ReportFormsGroup examgroup= getReportFormsGroup(simplegroup);

		//预算叶子公司，审核未通过，且审核状态为已审核（50115），执行退回操作	
		if(status.equals(Base.examstatus_3)&&examgroup.getType().equals(Base.reportgroup_type_simple) && examgroup.getNameID().equals(Base.reportgroup_BudgetEnforcement)){	
			//获取当前公司上面所有层级公司数据，并返回是否有已审核的标志位
			result=saveCheckedStatusBudgetEnforcementExmine(Date, organal);
			if(!result.isResult()){
				return result;
			}
			//回退当前公司汇总数据
			deleteLeafOrgBudgetCollectData(Date,organal,status,use);			
		}
		
		//预算虚拟公司，审核未通过，且审核状态为已审核（50115），执行退回操作	
		if(status.equals(Base.examstatus_3)&&examgroup.getType().equals(Base.reportgroup_type_collect) && examgroup.getNameID().equals(Base.reportgroup_BudgetEnforcement)){	
			//预算虚拟公司，回退操作直接删除对应的数据
			result=saveCheckedStatusBudgetEnforcementExmine(Date, organal);
			if(result.isResult()){
				reportDao.saveReportFormExamine(organal,groupID,Date,status,use);
			}
		}
		
		//核算公司，审核状态处于已审核（50115），执行回退操作   -- 保存时会删除主要指标
		if(examgroup.getNameID().equals(Base.reportgroup_adjust) && !examineresult.equals(Base.examresult_2)){
			stepBackAdjustMainFinancialIndicator( Date, organal, groupID, use);
		}

		//如果审核通过,公司是叶子节点，则对应操作
		if(groupID!=null && examgroup.getType().equals(Base.reportgroup_type_simple) && examgroup.getNameID().equals(Base.reportgroup_BudgetEnforcement) && examineresult.equals(Base.examresult_2) && child!=null && child.size()==0) 
		{	// 生成叶子公司的预算汇总数据
			result = createLeafOrgBudgetCollectData( Date, organal, groupID, status, use);
			// 生成汇总数据失败，直接返回，不该变审核状态
			if(!result.isResult())
				return result;
		}else if(examgroup.getNameID().equals(Base.reportgroup_adjust) && examineresult.equals(Base.examresult_2)){
			//核算数据
			createAdjustMainFinancialIndicator( Date, organal, groupID, use);
		}
		
		// 设置审核状态，并生成记录
		reportDao.saveReportFormExamine(organal, groupID, Date, status, use); 
		examineService.saveReportExamine(groupID, Date, organal, use, examineStr, examineresult);
		result.setResult(true);
		return result;
	}
	
	/**
	 * 生成叶子公司的预算汇总数据
	 * @param Date
	 * @param organal
	 * @param groupID
	 * @param status
	 * @param use
	 * 
	 * 	叶子公司在生成汇总数据前进行校验，如果已经存对应数据，则返回错误信息
	 * 		Author by zl
	 * 
	 * 
	 */
	private CommitResult createLeafOrgBudgetCollectData(String Date,String organal,Integer groupID,Integer status,HhUsers use){
		CommitResult result = new CommitResult();
		result.setResult(true);
		ReportFormsGroup group=new ReportFormsGroup();
		group.setNameID(Base.reportgroup_BudgetEnforcement);
		group.setType(Base.reportgroup_type_collect);
		group.setTime(Date.split("-")[0]);
		ReportFormsGroup hzgroup= getReportFormsGroup(group);
		if(hzgroup!=null){
			List<ReportFormsOrganal> organalentity=getReportFormsOrganalData( Date, organal, groupID);
			/*
			 * 新增叶子公司汇总数据进行查询判断，判断是否已有相关的数据
			 * 			groupID -- hzgroup.getID()
			 */
			result = checkIsExistleafCompanySumData( Date, organal, hzgroup.getId());
			if(!result.isResult())
				return result;
			for (ReportFormsOrganal oldEntity : organalentity) {
				ReportFormsOrganal newEntity=new ReportFormsOrganal();
				BeanUtils.copyProperties(oldEntity,newEntity);
				newEntity.setId(null);
				newEntity.setGroupID(hzgroup.getId());
				newEntity.setIsexamine(status);
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				newEntity.setAuditorDate(formatter.format(new Date()));
				newEntity.setAuditorPersonId(use.getVcEmployeeId());
				newEntity.setAuditorPersonName(use.getVcName());
				ReportForms entity=new ReportForms();
				entity.setGroupId(hzgroup.getId());
				newEntity.setFormsId(reportDao.getChangeNewformsID(oldEntity.getFormsId().toString(),hzgroup.getId()));
				commonDao.saveOrUpdate(newEntity);
				reportDao.saveChangeReportCell(oldEntity.getId(), newEntity.getId());
			}
			//复制审核信息
			examineService.saveChangeReportExamine(groupID, hzgroup.getId(), Date, organal);
		}
		return result;
	}
	
	/**
	 * 查询是否已经存在公司与报表关联的信息
	 * @param Date
	 * @param organal
	 * @param groupID
	 * @return
	 */
	public CommitResult checkIsExistleafCompanySumData(String Date,String organal,Integer groupID){
		CommitResult result = new CommitResult();
		result.setResult(true);
		ReportFormsOrganal entity = new ReportFormsOrganal();
		entity.setReportTime(Date);
		entity.setOrganalId(organal);
		entity.setGroupID(groupID);
		List<ReportFormsOrganal> list = reportDao.getReportFormsOrganal(entity);
		if(list.size()>0){
			result.setEntity(entity);
			result.setExceptionStr("已经存在叶子公司汇总数据，无法进行审核！请执行回退操作，以便删除已汇总数据。");
			result.setResult(false);
			return result;
		}
		return result;
	}
	
	/**
	 * 删除叶子公司的预算汇总数据
	 */
	private void deleteLeafOrgBudgetCollectData(String Date,String organal,Integer status,HhUsers use){
		ReportFormsGroup group=new ReportFormsGroup();
		group.setNameID(Base.reportgroup_BudgetEnforcement);
		group.setType(Base.reportgroup_type_collect);
		group.setTime(Date.split("-")[0]);
		ReportFormsGroup hzgroup= getReportFormsGroup(group);
		if(hzgroup!=null){
			List<ReportFormsOrganal> organalentity=getReportFormsOrganalData(Date, organal, hzgroup.getId());
			for (ReportFormsOrganal oldEntity : organalentity){
				// 删除所有审核数据
				oldEntity.setIsdel(1);		
				oldEntity.setIsexamine(status);				
				examineDao.deleteListReportExamine(hzgroup.getId(),Date,organal);
				commonDao.saveOrUpdate(oldEntity);
			}
		}
	}
	
	/**
	 * 已审核公司，回退时检查上层级公司是否有数据的状态
	 * @param Date
	 * @param organal
	 * @param groupID
	 * @return
	 */
	public CommitResult saveCheckedStatusBudgetEnforcementExmine(String Date,String organal){
		CommitResult result=new CommitResult();
		//查询历史树获取已汇总公司数据
		StringBuffer sb = new StringBuffer();
		this.getUpHhOrganTreeRelation(organal,Date,sb);
		//处理返回的查询信息
		if(sb.length()>0)
			sb.setLength(sb.length()-1);
		//预算公司汇总分为单体和虚拟公司
		ReportFormsGroup group=new ReportFormsGroup();
		group.setNameID(Base.reportgroup_BudgetEnforcement);
		group.setType(Base.reportgroup_type_collect);
		group.setTime(Date.split("-")[0]);
		ReportFormsGroup hzgroup= getReportFormsGroup(group);
		int[] groupId = {25,hzgroup.getId()};
		//设置查询条件
		ReportFormsOrganal searchBean = new ReportFormsOrganal();
		searchBean.setReportTime(Date);
		searchBean.setOrganalId(sb.toString());
		//封装返回的结果集
		List<ReportFormsOrganal> organalEntityList = new ArrayList<ReportFormsOrganal>();
		for(int tempId:groupId){
			searchBean.setGroupID(tempId);
			organalEntityList.addAll(reportDao.getReportFormsOrganal(searchBean));
		}
		//判断是否有上级公司
		if(organalEntityList.size()>0){
			result.setEntity(organalEntityList);
			result.setExceptionStr("上层公司存在数据，无法进行回退功能！");				
			return result;
		}
		result.setResult(true);
		return result;
	}
	
	//获取财务树向上层级org
	public void getUpHhOrganTreeRelation(String org,String date,StringBuffer sb){		
		//查询历史树获取已汇总公司数据
		HhOrganInfoTreeRelationHistory hhOrganInfoTreeRelationHistory = financeHistroyTreeDao.getUpTreeOrganInfoByNnodeID(Base.financetype,org,date);
		if(null!=hhOrganInfoTreeRelationHistory){
			sb.append(hhOrganInfoTreeRelationHistory.getId().getNnodeId()).append(",");
			this.getUpHhOrganTreeRelation(hhOrganInfoTreeRelationHistory.getId().getNnodeId(),date,sb);
		}
	}
		
	/**
	 * 生成核算主要指标数据
	 * @param Date
	 * @param organal
	 * @param groupID
	 */
	private void createAdjustMainFinancialIndicator(String Date,String organal,Integer groupID,HhUsers use)
	{
		//  说服不了李计刚把单户合并拆开，懒得说了就这样吧
		//单体与合并都取补充表单体（51010）
		int[] simple={51006,51010,51011};//单户
		int[] combine={51012,51010,51014};//合并 
		List<ReportIndexAndData> simpleData=reportDao.getReportValueBy( Date,organal, simple);
		List<ReportIndexAndData> combineData=reportDao.getReportValueBy( Date,organal, combine);
		mainFinancialIndicatorsService.saveMainFinancialIndicator(simpleData,organal,Date,use,Base.reportgroup_type_simple);
		mainFinancialIndicatorsService.saveMainFinancialIndicator(combineData,organal,Date,use,Base.reportgroup_type_collect);	
	}
	
	/**
	 * 回退核算主要的指标数据
	 * @param Date
	 * @param organal
	 * @param groupID
	 * @param use
	 */
	private void stepBackAdjustMainFinancialIndicator(String Date,String organal,Integer groupID,HhUsers use){
		//  说服不了李计刚把单户合并拆开，懒得说了就这样吧
		//单体与合并都取补充表单体（51010）
		int[] simple={51006,51010,51011};//单户
		int[] combine={51012,51010,51014};//合并 
		List<ReportIndexAndData> simpleData=reportDao.getReportValueBy( Date,organal, simple);
		List<ReportIndexAndData> combineData=reportDao.getReportValueBy( Date,organal, combine);
		mainFinancialIndicatorsService.stepBackMainFinancialIndicator(simpleData,organal,Date,use,Base.reportgroup_type_simple);
		mainFinancialIndicatorsService.stepBackMainFinancialIndicator(combineData,organal,Date,use,Base.reportgroup_type_collect);		
	}

	public MainFinancialIndicator AdjustMainFinancialIndicatorExport(String Date,String organal,Integer groupID,HhUsers use,int type)
	{
		//  说服不了李计刚把单户合并拆开，懒得说了就这样吧
		int[] simple={51006,51010,51011};//单户
		int[] combine={51012,51013,51014};//合并 
		List<ReportIndexAndData> simpleData=reportDao.getReportValueBy( Date,organal, simple);
		List<ReportIndexAndData> combineData=reportDao.getReportValueBy( Date,organal, combine);
		MainFinancialIndicator mainFinancialIndicator=null;
		if(type==52004){
			mainFinancialIndicator=mainFinancialIndicatorsService.saveMainFinancialIndicator(simpleData,organal,Date,use,Base.reportgroup_type_simple);

		}else {
			 mainFinancialIndicator=mainFinancialIndicatorsService.saveMainFinancialIndicator(combineData,organal,Date,use,Base.reportgroup_type_collect);

		}
		return mainFinancialIndicator;
	}
	

	
	/**
	 * 上报检查（都处于未上报和退回才能上报）
	 * @param Date
	 * @param use
	 * @param organal
	 * @param groupID
	 * @param reportstatus
	 * @return
	 */
	public CommitResult saveReportFormReportCheck(String Date,String organal,Integer groupID)
	{
		CommitResult result=new CommitResult();
		
		List<ReportFormsOrganal> organalentity=getReportFormsOrganalData( Date, organal, groupID);
		
		
		
		if( organalentity==null || organalentity.size()==0)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
			return result;
		}
		for (ReportFormsOrganal reportFormsOrganal : organalentity) {
			if(! reportFormsOrganal.getIsexamine().equals(Base.examstatus_1) && ! reportFormsOrganal.getIsexamine().equals(Base.examstatus_3))
			{
				result=CommitResult.createErrorResult("该数据已被上报不需要再上报");
				return result;
			}
		}
		if(organalentity==null || organalentity.size()==0)
		{
			result=CommitResult.createErrorResult("该数据不存在已被删除");
			return result;
		}
		ReportForms searchReportForms=new ReportForms();
		searchReportForms.setGroupId(groupID);
		List<ReportForms> ReportFormsList=reportDao.getReportFormsList(searchReportForms,null,null);
		String month="";
		month=Date.split("-")[1];
		//只显示月报
		if( !month.equals("03") && !month.equals("06") && !month.equals("09") && !month.equals("12"))
		{
			
			Iterator<ReportForms> it = ReportFormsList.iterator();
			while(it.hasNext()){
				ReportForms reportForms = it.next();
				if(reportForms.getFre().equals(Base.seasonly) || reportForms.getFre().equals(Base.yearly))
				{
					it.remove();
				}
			}
			
		}
		//不显示年报
		else if(!month.equals("12"))
		{
			Iterator<ReportForms> it = ReportFormsList.iterator();
			while(it.hasNext()){
				ReportForms reportForms = it.next();
				if( reportForms.getFre().equals(Base.yearly))
				{
					it.remove();
				}
			}
		}
		if(ReportFormsList.size()!=organalentity.size())
		{
			if(!noCheckForGo1(ReportFormsList, organalentity)){
				result=CommitResult.createErrorResult("同组报表还有没上传的");
				return result;
			}
		}
		result.setResult(true);
		return result;
	}
	
	
	/**
	 * 核算股权口径报表上传时，资产负债表和利润表的合并表改成非必填
	 * 资产负债表（合并）  51012
	 * 利润表（合并）51014
	 * 存量类的利润汇总表  51004
	 * 存量类的财务收支汇总表  51005
	 * @param ReportFormsList  报表组中存在的所有报表
	 * @param organalentity    ReportFormsOrganal 实际上传的报表
	 * @return
	 */
	private boolean noCheckForGo1(List<ReportForms> ReportFormsList,List<ReportFormsOrganal> organalentity){
		int formsSize = ReportFormsList.size();
		int organalSize = organalentity.size();
		for(ReportForms formEntity : ReportFormsList){
			if(formEntity.getFormkind().equals(51012) || formEntity.getFormkind().equals(51014) 
					|| formEntity.getFormkind().equals(51004) || formEntity.getFormkind().equals(51005)){
				formsSize = formsSize - 1;
			}
		}
		for(ReportFormsOrganal organalEntity : organalentity){
			if(organalEntity.getFormsKind().equals(51012) || organalEntity.getFormsKind().equals(51014) 
					|| organalEntity.getFormsKind().equals(51004) || organalEntity.getFormsKind().equals(51005)){
				organalSize = organalSize - 1;
			}
		}
		//剔除非必填的报表然后比较大小
		if(formsSize == organalSize){
			return true;
		}else{
			return false;
		}
	}
	
	
	
	/**
	 * 上报
	 * @param Date
	 * @param use
	 * @param organal
	 * @param groupID
	 * @param reportstatus
	 * @return
	 */
	public CommitResult saveReportFormReport(String Date,HhUsers use,String organal,Integer groupID)
	{
		CommitResult result=new CommitResult();
		result=saveReportFormReportCheck( Date, organal, groupID);
		if(result.isResult()==false)
		{
			return result;
		}
		result.setResult(false);
		ReportForms searchform=new ReportForms();
		searchform.setGroupId(groupID);
		List<ReportForms> forms=reportDao.getReportformsList(searchform);
		
		
		List<ReportFormsOrganal> organalentity=getReportFormsOrganalData(Date,organal,groupID);

		if(organalentity.size()>forms.size())
		{
			result=CommitResult.createErrorResult("有错误数据，联系管理员");
			System.out.println("报表数据有问题(Date, use, organal, groupID,reportstatus)"+ Date+"-"+use+"-"+ organal+"-"+ groupID+"-");
		}
		if(organalentity.size()<forms.size())
		{
			result=CommitResult.createErrorResult("还有报表未上传请全部上传后再上报");
		}
		else
		{
			Boolean flag=false;
			for (ReportFormsOrganal item1 : organalentity) {
				for (ReportForms item2 : forms) {
					
					if(item1.getFormsId().equals(item2.getId()))
					{
						flag=true;
						break;
					}
					
				}
				if(flag==false)
				{
					result=CommitResult.createErrorResult("有错误数据，联系管理员");
					System.out.println("报表数据有问题(Date, use, organal, groupID,reportstatus)"+ Date+"-"+use+"-"+ organal+"-"+ groupID+"-");
				}
				flag=false;
			}
		}
		HhOrganInfoTreeRelation node= systemDao.getTreeOrganInfoNode(Base.financetype, organal);
		reportDao.saveReportFormReport(organal, groupID, Date,Base.examstatus_2 , use,node.getVcOrganId());
		result.setResult(true);
		return result;
	}
	
	/**
	 * 根据查询公司和报表的关联
	 * @param pagenum
	 * @param pagesize
	 * @param entity
	 * @param Status  公司种类（虚拟、实体）
	 * @return
	 * 
	 */
	public MsgPage getReportFormsOrganalList(Integer pagenum,Integer pagesize,ReportQueryView entity,String Status)
	{
		MsgPage msgPage = new MsgPage();       
       //总记录数
        Integer totalRecords = reportDao.getReportFormsOrganalListCount(entity,Status);
    	//当前页开始记录
    	int offset = msgPage.countOffset(pagenum,pagesize);  
    	//分页查询结果集
    	List<ReportQueryView> list = reportDao.getReportFormsOrganalList( offset, pagesize,entity,Status);
    	msgPage.setPageNum(pagenum);
    	msgPage.setPageSize(pagesize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pagesize));
    	msgPage.setList(list);  
        return msgPage;
	}
	
	/**
	 * 根据查询公司和报表的关联用于预算汇总页面
	 * @return
	 */
	public MsgPage getReportFormsOrganalCollectList(Integer pagenum,Integer pagesize,ReportQueryView entity)
	{
		MsgPage msgPage = new MsgPage();   
		if(Common.notEmpty(entity.getOrganalID())){
       //总记录数
		ReportQueryView simpleentity=new ReportQueryView();
		BeanUtils.copyProperties(entity, simpleentity);
		simpleentity.setType(Base.reportgroup_type_simple);
		int num=0;
		List<ReportQueryView> simplelist = reportDao.getReportFormsOrganalList( null, null,simpleentity,null);
		if(simpleentity==null)
			simplelist=new ArrayList<ReportQueryView>();
		num=simplelist.size();
		
		
		//当前页开始记录
    	int offset = msgPage.countOffset(pagenum,pagesize);  
    	int newpagesize=pagesize;
    	if(pagenum==1 && num>0){//第一页,如果有自己的单体数据后面的取数响应减少
    		newpagesize--;
    	}
    	if(num>0)
    		offset=offset-num;
    	
    	String tempOrgID = financeHistroyTreeService.getNextLevelChildAuthDataStr(entity.getOrganalID(), Base.financetype,entity.getTime());
    	if(StringUtils.isBlank(tempOrgID)){
    		tempOrgID = "-";
    	}
    	entity.setOrganalID(tempOrgID);
		List<ReportQueryView> list=reportDao.getReportFormsOrganalList( offset,newpagesize,entity,null);
		if(list.size()>0){
			for (ReportQueryView reportQueryView : list) {
				switch (Integer.parseInt(reportQueryView.getStatus())) {
				case 50500:
					reportQueryView.setOrganalname(reportQueryView.getOrganalname()+"(实体公司)");
					break;
				case 50501:
					reportQueryView.setOrganalname(reportQueryView.getOrganalname()+"(虚拟公司)");
					break;
				case 50502:
					reportQueryView.setOrganalname(reportQueryView.getOrganalname()+"(壳公司)");
					break;
				}
			}
		}
		Integer totalRecords = reportDao.getReportFormsOrganalListCount(entity,null)+num;
		if(num>0 && pagenum==1)
		{
			list.add(0, simplelist.get(0));
			
		}
    	msgPage.setPageNum(pagenum);
    	msgPage.setPageSize(pagesize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pagesize));
    	msgPage.setList(list);
		}
        return msgPage;
	}
	
	/**
	 * 根据ID获取报表实例 reportformOrganal
	 * @param id
	 * @return
	 */
	public ReportFormsOrganal getFormOrganal(Integer id)
	{
		return reportDao.getFormOrganal( id);
	}
	
	
	/**
	 * 根据报表ID和公司ID获取报表实例 reportformOrganal
	 * @param id
	 * @return
	 */
	public ReportFormsOrganal getFormOrganal(Integer formid,String organalID,String Date)
	{
		return reportDao.getFormOrganal( formid,organalID,Date);
	}
	
	
	/**
	 * 保存一张报表的值
	 * @param use
	 * @param organalID
	 * @param formID
	 * @param reportFile
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public CommitResult saveReportValueCheck(String reportTime, HhUsers use,String organalID,Integer grouptype)
	{
		CommitResult returnData=new CommitResult();
		returnData.setResult(false);
		if(!Common.notEmpty(reportTime))
		{
			returnData=CommitResult.createErrorResult("报表时间不能为空");
			return returnData;
		}
		if(!Common.notEmpty(organalID))
		{
			returnData=CommitResult.createErrorResult("机构不能为空");
			return returnData;
		}
		if(grouptype==null)
		{
			returnData=CommitResult.createErrorResult("报表组类型不能为空");
			return returnData;
		}
		
		returnData.setResult(true);
		return returnData;
		
	}
	
	/**
	 * 删除一个报表组所有报表的值
	 * @param use
	 * @param organalID
	 * @param formID
	 * @param Date 
	 * @return
	 */
	public CommitResult deleteReportValue(String Date,HhUsers use,String organalID,Integer groupid)
	{
		CommitResult returnData=new CommitResult();
		ReportForms entity=new ReportForms();
		entity.setGroupId(groupid);
		List<ReportForms> forms=getReportformsList(entity);
		String formID="";
		for (ReportForms item : forms) {
			if(formID.equals(""))
				formID=item.getId().toString();
			else
				formID+=","+item.getId().toString();
		}
		
		//删除
		String old= reportDao.getReportFormsOrganalByFormIDAndOrganIDOrDate(formID.toString(),organalID,Date);
		reportDao.deleteReportOrganalByID(old, use);
		
		//删除已有的审核数据
		List<SysExamineReport> ss = examineDao.getListReportExamine(groupid, Date, organalID);
		for(SysExamineReport s : ss){
			s.setIsdel(1);
		}
		
		returnData.setResult(true);
		return returnData;
	}
	
	/**
	 * 根据报表ID和企业ID和报表时间删除报表数据
	 * @param formID
	 * @param organalID
	 * @param reportTime
	 * @param use
	 */
	public void deleteReportCell(String formID,String organalID,String reportTime,HhUsers use)
	{
		String old= reportDao.getReportFormsOrganalByFormIDAndOrganIDOrDate(formID.toString(),organalID,reportTime);
		reportDao.deleteReportCell(old, use);
	}
	
	
	
	
	/**
	 * 汇总报表组所有报表的值
	 * @param use
	 * @param organalID
	 * @param grouptype
	 * @param Date 
	 * @return
	 */
	public CommitResult savecollectReportValue(String reportTime,HhUsers use,String organalID,Integer grouptype)
	{
		CommitResult returnData=new CommitResult();
		String Year=reportTime.split("-")[0];
		ReportFormsGroup entity=new ReportFormsGroup();
		entity.setNameID(grouptype);
		entity.setTime(Year);
		entity.setType(Base.reportgroup_type_collect);
		ReportFormsGroup collectgroup=getReportFormsGroup(entity);
		entity.setType(Base.reportgroup_type_simple);
		ReportFormsGroup simplegroup=getReportFormsGroup(entity);
		
		
		boolean flag = reportDao.saveReportValueCheck( reportTime, organalID, collectgroup.getId());
		if(reportDao.saveReportValueCheck( reportTime, organalID, collectgroup.getId()))
		{
			returnData=CommitResult.createErrorResult("该期数据正在审核或者审核通过，不能再修改");
			return returnData;
		}
		
		if(collectgroup==null)
		{
			returnData=CommitResult.createErrorResult("报表组为空不能汇总");
		}
		else
		{
			HhOrganInfoTreeRelationHistory thisorgan=financeHistroyTreeDao.getTreeOrganInfoNode(Base.financetype, organalID,reportTime);
			if(thisorgan == null){
				returnData=CommitResult.createErrorResult("财务树中没有这家公司");
				return returnData;
			}
			//只用虚体公司能进行汇总操作
			if(!thisorgan.getStatus().equals(Base.report_organal_invent)){
				returnData=CommitResult.createErrorResult("该公司不是虚体公司，不能进行汇总操作");
				return returnData;
			}
			List<HhOrganInfoTreeRelationHistory> node=financeHistroyTreeDao.getChildrenTreeOrganInfos(Base.financetype, thisorgan.getVcOrganId(),reportTime);
			String nodeid="";
			for (HhOrganInfoTreeRelationHistory item : node) {
				if(!item.getStatus().equals(Base.report_organal_ke)){//过滤壳公司
					if(nodeid.equals(""))
						nodeid=item.getId().getNnodeId();
					else
						nodeid+=","+item.getId().getNnodeId();
				}
			}
			//没有子公司
			if(nodeid.equals(""))
			{
				returnData=CommitResult.createErrorResult("该公司无须汇总");
				return returnData;
			}
			//检查自己和子公司的数据是否有上传
			if(!reportDao.saveCollectReportCellCheck(nodeid,collectgroup.getId(),reportTime) )
			{
				returnData=CommitResult.createErrorResult("还有成员公司的报表还未上报或审核未通过，不能汇总");
				return returnData;
			}
			if(Base.report_organal_entity.equals(thisorgan.getStatus()) && !reportDao.saveCollectReportCellCheck(thisorgan.getId().getNnodeId(),simplegroup.getId(),reportTime) )
			{
				returnData=CommitResult.createErrorResult("本公司的单体报表还未上报或审核未通过，不能汇总");
				return returnData;
			}
			else{
				
				ReportForms searchforms=new ReportForms();
				searchforms.setGroupId(collectgroup.getId());
				List<ReportForms> forms=getReportformsList(searchforms);//查出该组内的所有报表种类
			
				
				for (ReportForms reportForms : forms) {
					ReportFormsOrganal organ=new ReportFormsOrganal();
					organ.setOrganalId(nodeid);
					organ.setReportTime(reportTime);
					organ.setFormsId(reportForms.getId());
					List<ReportFormsOrganal> reportformorgan= reportDao.getReportFormsOrganal(organ);
					String childrelation="";
					for (ReportFormsOrganal item : reportformorgan) {
						if(childrelation.equals(""))
							childrelation=item.getId().toString();
						else
							childrelation+=","+item.getId().toString();
					}
					
					//实体企业 子公司的报表实例加上本公司的，非实体的就不加了
					if( Base.report_organal_entity.equals(thisorgan.getStatus()))
					{
						String  MyRelationID=reportDao.getCollectMyRelationID(reportForms.getId(),simplegroup.getId(),organalID,reportTime);
						childrelation+=","+MyRelationID;
					}	
					returnData=savecollectOneReportValue(reportTime,use,organalID,collectgroup.getId(),reportForms.getId(),childrelation);
					
					if(!returnData.isResult())
					{
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						System.out.println(reportTime+","+use.getVcEmployeeId()+","+organalID+","+grouptype);
						return returnData;
					}
				}
				returnData.setResult(true);
			}
		}
		
		return returnData;
	}

	/**
	 * 汇总一张报表的值
	 * @param use
	 * @param organalID
	 * @param grouptype
	 * @param Date 
	 * @return
	 * @throws Exception 
	 */
	public CommitResult savecollectOneReportValue(String reportTime,HhUsers use,String organalID,Integer groupID,Integer formID,String childrelation)
	{
		try{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			//存实例
			ReportFormsOrganal saveorgan =saveorupdateReportFormsOrganal(df.format(new Date()), use, formID, organalID, reportTime, groupID);
			
			HashMap<String, String> cells= reportDao.getCollectReportCell(childrelation);
			if(cells.isEmpty())
				return CommitResult.createErrorResult("汇总失败，数据获取失败");
			ReportFormsPattend pattendentity=new ReportFormsPattend();
			pattendentity.setFormsId(formID);
			pattendentity.setIsdata(true);
			List<ReportFormsCell> collectvalue=new ArrayList<ReportFormsCell>();
			List<ReportFormsPattend> pattendlist=reportDao.getReportFormsPattend(pattendentity);
			for (ReportFormsPattend pattenditem : pattendlist) {
				//值
				String cellvalue=cells.get("["+pattenditem.getIndexIdrow()+","+pattenditem.getIndexIdcol()+"]");
				//是需要计算的
				if(pattenditem.getIsformula())
				{
					//计算公司
					String formula=pattenditem.getFormula();
					{
						Cale cale=new Cale();
						
						cellvalue=(cale.getValue(formula, cells));
					}
				}
				collectvalue.add(structReportFormsCell(pattenditem,use,df.format(new Date()),saveorgan.getId(),cellvalue,false));
			}
			//删除旧的详细数据
			deleteReportCell(formID.toString(), organalID, reportTime, use);
			reportDao.saveReportCell(collectvalue);
		}
		catch(Exception e)
		{
			CommitResult result=CommitResult.createErrorResult(e.getMessage());
			return result;
		}
		CommitResult result=new CommitResult();
		result.setResult(true);
		return result;
	}
	
	/**
	 * 保存某个报表组之前进行校验,返回true说明已经审核通过,或者待审核不能再修改，返回false说明还可以修改
	 * @param use
	 * @param organalID
	 * @param formID
	 * @param reportFile
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public CommitResult saveReportValue(String reportTime, HhUsers use,String organalID,Integer grouptype,Integer[] fileformsID,CommonsMultipartFile files[],int type) throws FileNotFoundException, IOException
	{
		CommitResult returnData=new CommitResult();
		returnData= saveReportValueCheck( reportTime,  use, organalID, grouptype);
		if(returnData.isResult()==false)
			return returnData;
		returnData.setResult(false);
		try{
			String[] sTemp = reportTime.split("-");
			String Year= sTemp[0];
			ReportFormsGroup entity=new ReportFormsGroup();
			entity.setNameID(grouptype);
			entity.setTime(Year);
			entity.setType(type);
			entity.setMonth(Integer.valueOf(sTemp[1]).toString());
			ReportFormsGroup group=getReportFormsGroup(entity);
			
			if(group!=null)
			{
				if(reportDao.saveReportValueCheck( reportTime, organalID, group.getId()))
				{
					returnData=CommitResult.createErrorResult("该期数据正在审核或者审核通过，不能再修改");
					return returnData;
				}
				ReportForms searchforms=new ReportForms();
				searchforms.setGroupId(group.getId());
				List<ReportForms> forms=getReportformsList(searchforms);
				if(forms==null || forms.size()==0)
				{
					returnData=CommitResult.createErrorResult("该报表组内的报表为空，不能保存报表");
				}
				else
				{
					int i=0;
					
					for (CommonsMultipartFile fileobj : files) {
						if(fileobj.getSize()!=0)
						{	
							returnData=saveOneReportValue(reportTime,use,organalID,fileformsID[i],fileobj);
							if(returnData.isResult()==false)
							{
								TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
								return returnData;
							}
						}
						i++;
					}
				}
				returnData.setResult(true);
			}
			else
			{
				returnData=CommitResult.createErrorResult("报表组为空，不能保存报表");
			}
		}
		catch(Exception e)
		{
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			returnData=CommitResult.createErrorResult("上传失败");
			e.printStackTrace();
		}
		return returnData;
	}
	
	
	
	/**
	 * 保存一张报表的值
	 * @param use
	 * @param organalID
	 * @param formID
	 * @param reportFile
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public CommitResult saveOneReportValue(String reportTime, HhUsers use,String organalID,Integer formID,CommonsMultipartFile reportFile) throws FileNotFoundException, IOException
	{
		CommitResult returnData=new CommitResult();
		ReportForms entity=new ReportForms();
		entity.setId(formID);
		ReportForms  reportform= reportDao.getReportForms(entity);
		if(reportform==null)
		{
			returnData=CommitResult.createErrorResult("系统内该报表不存在，不能上传该报表");
			return returnData;
		}
		try{

	        //先暂存附件
	        List<String> fileStrList = Common.saveFile(reportFile, DES.temp_path);
	        //文件路径
	        String xlsPath1=fileStrList.get(1);
			//报表的样式附件
			HhFile _file = commonService.getFileByEnIdAndType(formID,Base.reportFile);
			String xlsPath2="";
			
			if(_file!=null)
				xlsPath2=_file.getFilePath();

		 	
			returnData=saveReportValueCheck( organalID, formID,xlsPath1,xlsPath2);
			if(!returnData.isResult())
			{
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return returnData;
			}  	 
			//删除旧的数据
			deleteReportCell(formID.toString(), organalID, reportTime, use);
			//判断两个sheet的格子是否一样的标识符
			
			InputStream is1 = new FileInputStream(xlsPath1);
			Workbook workbook1 = null; 
		        try { 
		        	workbook1 = new XSSFWorkbook(is1); 
		        } catch (Exception ex) { 
		        	 POIFSFileSystem poifsFileSystem= new POIFSFileSystem(new FileInputStream(xlsPath1));
		        	 workbook1 = new HSSFWorkbook(poifsFileSystem); 
		        } 
		        Sheet  sheet1 = workbook1.getSheetAt(0);
			
	        InputStream is2 = new FileInputStream(xlsPath2);
			Workbook workbook2 = null; 
		        try { 
		        	workbook2 = new XSSFWorkbook(is2); 
		        } catch (Exception ex) { 
		        	POIFSFileSystem poifsFileSystem= new POIFSFileSystem(new FileInputStream(xlsPath2));
		        	workbook2 = new HSSFWorkbook(poifsFileSystem);
		        } 
		        Sheet  sheet2 = workbook2.getSheetAt(0);
			
	        
	        returnData=saveOneSheetReportValue( reportTime,  use, reportform, organalID, sheet1, sheet2);
	        //不成功回滚
	        if(!returnData.isResult())
	        {
	        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	        }	
		}
		catch(Exception e)
		{
			returnData=CommitResult.createErrorResult(reportform.getFormkindName() +"上传excel文件出现问题,样式文件未上传");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}

		return returnData;	
	}
	
	
	
	
	//存报表实例
	/**
	 * 
	 * @param time 操作时间
	 * @param use 操作人员
	 * @param reportformId 报表ID
	 * @param organalID 机构ID
	 * @param examstatus 报表状态
	 * @param reportTime 报表时间
	 * @param groupId 报表组ID
	 * @return
	 */
	private ReportFormsOrganal saveorupdateReportFormsOrganal(String time,HhUsers use,Integer  reportformId,String organalID,String reportTime,Integer groupId)
	{
		ReportFormsOrganal old= reportDao.getReportFormsOrganal(organalID,reportformId,reportTime);
		//原本是取hr公司的后面说预算和核算是不一样的所以取变种树的公司名字,如果都查不到公司默认去查HR树
		ReportFormsGroup entity=new ReportFormsGroup();
		entity.setId(groupId);
		entity=reportDao.getReportFormsGroup(entity);
		HhOrganInfoTreeRelation relation= systemDao.getTreeOrganInfoNode(Base.financetype, organalID);
		String organName="";
		organName=relation.getVcFullName();
		if(old==null)
		{
			old =new ReportFormsOrganal();
			old.setIsexamine(Base.examstatus_1);
		}
		HhOrganInfoTreeRelation node= systemDao.getTreeOrganInfoNode(Base.financetype, organalID);
		old.setCreateDate(time);
		old.setCreatePersonId(use.getVcEmployeeId());
		old.setCreatePersonName(use.getVcName());
		old.setIsdel(0);
		old.setOrganName(organName);
		old.setFormsId(reportformId);
		old.setOrganalId(organalID);
		old.setReportTime(reportTime);
		old.setGroupID(groupId);
		old.setLastModifyDate(time);
		old.setLastModifyPersonId(use.getVcEmployeeId());
		old.setLastModifyPersonName(use.getVcName());
		old.setParentorg(node.getVcOrganId());
		ReportForms forms=new ReportForms();
		forms.setId(reportformId);
		forms=reportDao.getReportForms(forms);
		old.setFormsKind(forms.getFormkind());
		commonDao.saveOrUpdate(old);	
		return old;
	}
	
	/**
	 * 保存一个sheet里面的报表值
	 * @param use 用户
	 * @param formID 报表ID
	 * @param organalID 机构表
	 * @param sheet1 提交的数据表
	 * @param sheet2 样式表
	 * @return
	 */
	public CommitResult saveOneSheetReportValue(String reportTime, HhUsers use,ReportForms reportform,String organalID,Sheet sheet1,Sheet sheet2)
	{   
		CommitResult returnData=new CommitResult();
	 	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 	String time=df.format(new Date());

		String ExceptionStr="上传的"+reportform.getFormkindName()+"的样式与本系统该报表的样式不一致，保存操作被取消";
		ExcelTool exceltool=new ExcelTool();
		
		if(!exceltool.judgeSheetTheSame(sheet1, sheet2))
		{
			returnData= CommitResult.createErrorResult(ExceptionStr);
			return returnData;
		}
		Integer ReleatiionId;
		
		//存报表实例
		ReportFormsOrganal saveorgan=saveorupdateReportFormsOrganal(time,use,reportform.getId(),organalID,reportTime,reportform.getGroupId());
		ReleatiionId=saveorgan.getId();
		ReportFormsPattend pattendentity=new ReportFormsPattend();
		pattendentity.setFormsId(reportform.getId());
		pattendentity.setIsdata(true);
		List<ReportFormsPattend> pattendlist=reportDao.getReportFormsPattend(pattendentity);
		List<ReportFormsCell> cellList=new ArrayList<ReportFormsCell>();
		for (ReportFormsPattend item : pattendlist) {
			int row=item.getStartrow();
			int col=item.getStartcol();
			ExcelMergedRegin a=exceltool.isMergedRegion(sheet1.getWorkbook(),sheet1, row, col);
			cellList.add(structReportFormsCell(item,use,time,ReleatiionId,a.getValue(),true));
		}
		reportDao.saveReportCell(cellList);
		CommitResult result=new CommitResult();
		result.setResult(true);
		return result;
		
	}

	/**
	 * 构造格子数据
	 * @param item
	 * @param use
	 * @param time
	 * @param ReleatiionId
	 * @param valuestr
	 * @param changeflag 是否需要单位转换
	 * @return
	 */
	private  ReportFormsCell structReportFormsCell(ReportFormsPattend item, HhUsers use,String time,int ReleatiionId,String valuestr ,Boolean changeflag)
	{
		ReportFormsCell value=new ReportFormsCell();
		value.setIsdel(0);
		value.setIsformula(false);
		value.setFormulaStr("");
		value.setCreateDate(time);
		value.setCreatePersonId(use.getVcEmployeeId());
		value.setCreatePersonName(use.getVcName());
		value.setReleatiionId(ReleatiionId);
		value.setIndexIdcol(item.getIndexIdcol());
		value.setIndexIdrow(item.getIndexIdrow());
		
		if(item.getIsdata())
		{
			//如果是数字那么转成数字，不是数字存字符
			try{
				BigDecimal d;
				//是否需要单位转换
				if(changeflag)
					d=new BigDecimal(valuestr).multiply( new BigDecimal(item.getUnit()));
				else
					d=new BigDecimal(valuestr);
				value.setCellvalue( String.format("%s",d));
			}
			catch(Exception e)
			{
				value.setCellvalue(valuestr);
				//e.printStackTrace();
			}
			
		}
		else
			value.setCellvalue("");
		return value;
	}

	
	/**
	 * 获取机构和报表的关联
	 * @param entity
	 * @return
	 */
	public List<ReportFormsOrganal> getReportFormsOrganal(ReportFormsOrganal entity)
	{
		return reportDao.getReportFormsOrganal(entity);
	}
	
	
	//获取报表的详细数据通过报表实例 ReportFormsOrganal 的ID
	public Report_Forms_DetailView getReportDetailViewByID(Integer id)
	{
		Report_Forms_DetailView returnData=new Report_Forms_DetailView();
		if(id==null)
			return returnData;
		ReportFormsOrganal entity=new ReportFormsOrganal();
		entity.setId(id);
		List<ReportFormsOrganal> reportlist= reportDao.getReportFormsOrganal(entity);
		if(reportlist==null || reportlist.size()==0)
			return null;
		//报表实例
		ReportFormsOrganal report=reportlist.get(0);
		returnData.setCreateDate(report.getCreateDate());
		returnData.setCreatePersonName(report.getCreatePersonName());
		returnData.setReportDate(report.getReportDate());
		returnData.setReportPersonName(report.getReportPersonName());
		
		//报表
		ReportForms Formsentity=new ReportForms();
		Formsentity.setId(report.getFormsId());
		ReportForms forms=reportDao.getReportForms(Formsentity);
		//样式
		ReportFormsPattend pattendentity=new ReportFormsPattend();
		pattendentity.setFormsId(forms.getId());
		List<ReportFormsPattend> pattend=reportDao.getReportFormsPattend(pattendentity);
		//数据
		ReportFormsCell cellentity=new ReportFormsCell();
		cellentity.setReleatiionId(id);
		List<ReportFormsCell> cell=reportDao.getReportFormsCell(cellentity);
		if(cell.size()==0)
			return null;
		List<TableCell> tabledata=new ArrayList<TableCell>();
		for (Iterator iterator = pattend.iterator(); iterator.hasNext();) {
			ReportFormsPattend pattenditem = (ReportFormsPattend) iterator.next();
			ReportFormsCell cellitem=new ReportFormsCell();
			TableCell tablecell=new TableCell();
			tablecell.setCellcss(pattenditem.getCellcss());
			tablecell.setColspan(pattenditem.getColspan());
			tablecell.setEndcol(pattenditem.getEndcol());
			tablecell.setEndrow(pattenditem.getEndrow());
			tablecell.setIndexIdcol(pattenditem.getIndexIdcol());
			tablecell.setIndexIdrow(pattenditem.getIndexIdrow());
			tablecell.setIsdata(pattenditem.getIsdata());
			tablecell.setIsunit(pattenditem.getIsunit());
			
			tablecell.setRowspan(pattenditem.getRowspan());
			tablecell.setStartcol(pattenditem.getStartcol());
			tablecell.setStartrow(pattenditem.getStartrow());
			tablecell.setUnit(pattenditem.getUnit());
			
			
			if(pattenditem.getIsdata())
				for (ReportFormsCell cellobj : cell) {
					if(((cellobj.getIndexIdcol()==null && pattenditem.getIndexIdcol()==null) || (cellobj.getIndexIdcol().equals(pattenditem.getIndexIdcol()))) 
							&& ((cellobj.getIndexIdrow()==null && pattenditem.getIndexIdrow()==null) || cellobj.getIndexIdrow().equals(pattenditem.getIndexIdrow())))
					{
					
						DecimalFormat df = new java.text.DecimalFormat("#,##0.00");
						Boolean flag= (pattenditem.getUnit()!=null || !pattenditem.getUnit().equals(0));
						//如果是数字那么转成数字，不是数字存字符
						try{
							BigDecimal  data;
							if(flag && pattenditem.getIsunit())
									data=new BigDecimal(cellobj.getCellvalue()).divide(new BigDecimal(pattenditem.getUnit())); 
							else
									data=new BigDecimal(cellobj.getCellvalue());
							
							tablecell.setValue(df.format(data.setScale(2, BigDecimal.ROUND_HALF_UP))); //保留两位小数，并进行四舍五入处理
						}
						catch(Exception e)
						{
							String values=cellobj.getCellvalue();
							if(values!=null && values.contains(" "))
								System.out.println(values);
							tablecell.setValue(values==null ? "":values.replace(" ", "&nbsp;"));
						}
						cell.remove(cellobj);
						break;
					}
				}
			else
				tablecell.setValue(pattenditem.getValue()!=null ? pattenditem.getValue().replace(" ", "&nbsp;"):"");
			tabledata.add(tablecell);
		}
		returnData.setCell(tabledata);
		returnData.setForms(forms);
		returnData.setId(id==null ? "":id.toString());
		returnData.setOrganID(report.getOrganalId());
		returnData.setOrganName(report.getOrganName());
		return returnData;
	}
	
    //获取上传页面需要展示的组内报表，如果组ID为空那么根据另两个条件查询
	/**
	 * 
	 * @param groupid 报表组ID
	 * @param grouptype 报表组类型
	 * @param Date 时间
	 * @return
	 */
	public List<ReportForms> getGroupForm(Integer groupid,Integer grouptype,String Date,Integer type)
	{
		List<ReportForms> forms=new ArrayList<ReportForms>();
		if(groupid!=null)
		{
			ReportForms entity=new ReportForms();
			entity.setGroupId(groupid);
			forms=getReportformsList(entity);
		}
		else
		{
			if(grouptype!=null && Common.notEmpty(Date) && type!=null)
			{
				ReportFormsGroup entity=new ReportFormsGroup();
				entity.setNameID(grouptype);
				String[] temp = Date.split("-");
				entity.setTime(temp[0]);
				entity.setType(type);
				entity.setMonth(Integer.valueOf(temp[1]).toString());
				ReportFormsGroup group=getReportFormsGroup(entity);
				if(group==null)
					return new ArrayList<ReportForms>();
				ReportForms form=new ReportForms();
				form.setGroupId(group.getId());
				forms=getReportformsList(form);
			}
			
		}
		
		String month=Date.split("-")[1];
		//只显示月报
		if( !month.equals("03") && !month.equals("06") && !month.equals("09") && !month.equals("12"))
		{
			
			Iterator<ReportForms> it = forms.iterator();
			while(it.hasNext()){
				ReportForms reportForms = it.next();
				if(reportForms.getFre().equals(Base.seasonly) || reportForms.getFre().equals(Base.yearly))
				{
					it.remove();
				}
			}
			
		}
		//不显示年报
		else if(!month.equals("12"))
		{
			Iterator<ReportForms> it = forms.iterator();
			while(it.hasNext()){
				ReportForms reportForms = it.next();
				if( reportForms.getFre().equals(Base.yearly))
				{
					it.remove();
				}
			}
		}
		
		return forms;
	}
	
	/**
	 * 导出模板
	 * @param Date 时间
	 * @param grouptype 模板种类
	 * @return
	 */
	public HhFile getReportExportTemplet(Integer grouptype,int type)
	{
		Integer groupID= reportDao.getLastGroup(grouptype,type);
		HhFile _file = commonService.getFileByEnIdAndType(groupID,Base.reportGroupExportIn);
		_file.setFilePath(_file.getFilePath().replace("\\", "/"));
		return _file;
	}

	public HhFile getReportExportAdjustTemplet(Integer year,Integer month){
		Integer groupID= reportDao.getAdjustGroup(year, month);
		if(groupID == null){
			return null;
		}
		HhFile _file = commonService.getFileByEnIdAndType(groupID,Base.reportGroupExportIn);
		_file.setFilePath(_file.getFilePath().replace("\\", "/"));
		return _file;
	}


	@Override
	public int getReportId(int time, int type, int nameId) {
		// TODO Auto-generated method stub
		return reportDao.getReportId(time, type, nameId);
	}



	@Override
	public MsgPage getHsNoCreateCompanyList(String reportTime,
			String authdata,String org,Integer curPageNum, Integer pageSize) {
		
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = reportDao.getHsNoCreateCompanyList(reportTime, authdata,org,null,null).size();
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<Object[]> list = reportDao.getHsNoCreateCompanyList(reportTime,authdata, org,offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}
	
	@Override
	public MsgPage getAdjustAccountRemindCompanyList(String reportTime,
			String authdata,String org,Integer curPageNum, Integer pageSize,String isAddAll) {
		MsgPage msgPage = new MsgPage();       
		if("all".equals(isAddAll)){
			//总记录数
			Integer totalRecords = reportDao.getAdjustAccountRemindCompanyList(reportTime, authdata,org,null,null).size();
			//当前页开始记录
			int offset = msgPage.countOffset(curPageNum,pageSize);  
			//分页查询结果集
			List<Object[]> list = reportDao.getAdjustAccountRemindCompanyList(reportTime,authdata, org,null,null);
			msgPage.setPageNum(curPageNum);
			msgPage.setPageSize(pageSize);
			msgPage.setTotalRecords(totalRecords);
			msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
			msgPage.setList(list);    		
		}else{
			//总记录数
			Integer totalRecords = reportDao.getAdjustAccountRemindCompanyList(reportTime, authdata,org,null,null).size();
			//当前页开始记录
			int offset = msgPage.countOffset(curPageNum,pageSize);  
			//分页查询结果集
			List<Object[]> list = reportDao.getAdjustAccountRemindCompanyList(reportTime,authdata, org,offset, pageSize);
			msgPage.setPageNum(curPageNum);
			msgPage.setPageSize(pageSize);
			msgPage.setTotalRecords(totalRecords);
			msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
			msgPage.setList(list);    
		}
        return msgPage;
	}
	
	
	private List<TableCell> getReportTableCellByFormId(Integer id){
		
		ReportFormsOrganal entity=new ReportFormsOrganal();
		entity.setId(id);
		List<ReportFormsOrganal> reportlist= reportDao.getReportFormsOrganal(entity);
		if(reportlist==null || reportlist.size()==0)
			return null;
		//报表实例
		ReportFormsOrganal report=reportlist.get(0);
		
		//报表
		ReportForms Formsentity=new ReportForms();
		Formsentity.setId(report.getFormsId());
		ReportForms forms=reportDao.getReportForms(Formsentity);
		//样式
		ReportFormsPattend pattendentity=new ReportFormsPattend();
		pattendentity.setFormsId(forms.getId());
		List<ReportFormsPattend> pattend=reportDao.getReportFormsPattend(pattendentity);
		//数据
		ReportFormsCell cellentity=new ReportFormsCell();
		cellentity.setReleatiionId(id);
		List<ReportFormsCell> cell=reportDao.getReportFormsCell(cellentity);
		if(cell.size()==0)
			return null;
		List<TableCell> tabledata=new ArrayList<TableCell>();
		for (Iterator iterator = pattend.iterator(); iterator.hasNext();) {
			ReportFormsPattend pattenditem = (ReportFormsPattend) iterator.next();
			ReportFormsCell cellitem=new ReportFormsCell();
			TableCell tablecell=new TableCell();
			tablecell.setCellcss(pattenditem.getCellcss());
			tablecell.setColspan(pattenditem.getColspan());
			tablecell.setEndcol(pattenditem.getEndcol());
			tablecell.setEndrow(pattenditem.getEndrow());
			tablecell.setIndexIdcol(pattenditem.getIndexIdcol());
			tablecell.setIndexIdrow(pattenditem.getIndexIdrow());
			tablecell.setIsdata(pattenditem.getIsdata());
			tablecell.setIsunit(pattenditem.getIsunit());
			
			tablecell.setRowspan(pattenditem.getRowspan());
			tablecell.setStartcol(pattenditem.getStartcol());
			tablecell.setStartrow(pattenditem.getStartrow());
			tablecell.setUnit(pattenditem.getUnit());
			
			
			if(pattenditem.getIsdata())
				for (ReportFormsCell cellobj : cell) {
					if(((cellobj.getIndexIdcol()==null && pattenditem.getIndexIdcol()==null) || (cellobj.getIndexIdcol().equals(pattenditem.getIndexIdcol()))) 
							&& ((cellobj.getIndexIdrow()==null && pattenditem.getIndexIdrow()==null) || cellobj.getIndexIdrow().equals(pattenditem.getIndexIdrow())))
					{
					
						DecimalFormat df = new java.text.DecimalFormat("#,##0.00");
						Boolean flag= (pattenditem.getUnit()!=null || !pattenditem.getUnit().equals(0));
						//如果是数字那么转成数字，不是数字存字符
						try{
							BigDecimal  data;
							if(flag && pattenditem.getIsunit())
									data=new BigDecimal(cellobj.getCellvalue()).divide(new BigDecimal(pattenditem.getUnit())); 
							else
									data=new BigDecimal(cellobj.getCellvalue());
							
							tablecell.setValue(df.format(data));
						}
						catch(Exception e)
						{
							String values=cellobj.getCellvalue();
							if(values!=null && values.contains(" "))
								System.out.println(values);
							tablecell.setValue(values==null ? "":values);
						}
						cell.remove(cellobj);
						break;
					}
				}
			else
				tablecell.setValue(pattenditem.getValue()!=null ? pattenditem.getValue():"");
			tabledata.add(tablecell);
		}
		
		return tabledata;
	}

	@Override
	public XSSFWorkbook getExportExcel(int type, String Date, String organalID,
			Integer groupid,Integer grouptype) {
		List<ReportForms> forms=this.getGroupForm(groupid, grouptype, Date,type);
		//创建工作簿  
        XSSFWorkbook workBook = new XSSFWorkbook();  
		for(ReportForms form : forms){
			//创建工作表  
	        XSSFSheet sheet = workBook.createSheet(form.getFormkindName());
	        ReportFormsOrganal organ=	this.getFormOrganal(form.getId(),organalID,Date);
			List<TableCell> data = getReportTableCellByFormId(organ.getId());
			if(data != null){
				Map<Integer, XSSFRow> rowMap = new HashMap<Integer, XSSFRow>();
				for(TableCell cellEntity : data){
					XSSFRow row = rowMap.get(cellEntity.getStartrow());
					if(row == null){
						row = sheet.createRow(cellEntity.getStartrow());
						rowMap.put(cellEntity.getStartrow(), row);
					}
					XSSFCell cell = row.createCell(cellEntity.getStartcol());
					XSSFCellStyle style = getCellStyle(workBook, cellEntity.getCellcss());
					style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
					cell.setCellValue(cellEntity.getValue());  
			        cell.setCellStyle(style); 
			        sheet.autoSizeColumn((short)cellEntity.getStartcol().intValue()); //调整列宽度
			        if(cellEntity.getStartrow()!=cellEntity.getEndrow() || cellEntity.getStartcol()!= cellEntity.getEndcol()){//存在合并单元格
			        	CellRangeAddress margeCell = new CellRangeAddress(cellEntity.getStartrow(),cellEntity.getEndrow(),cellEntity.getStartcol(), cellEntity.getEndcol());
			        	sheet.addMergedRegion(margeCell);
			        }
				}
			}
		}
		return workBook;
	}
	
	private XSSFCellStyle getCellStyle(XSSFWorkbook workBook,String cellCss){
		XSSFCellStyle style = workBook.createCellStyle();
		CellMyStyle obj = new CellMyStyle(cellCss);
		String alignment  = obj.getAlignment();
		if("left".equals(alignment)){
			style.setAlignment(HorizontalAlignment.LEFT);
		}else if("rigth".equals(alignment)){
			style.setAlignment(HorizontalAlignment.RIGHT);
		}else if("center".equals(alignment)){
			style.setAlignment(HorizontalAlignment.CENTER);
		}
		XSSFFont font = workBook.createFont(); 
		font.setBoldweight((short) obj.getBoldweight());
		font.setFontName(obj.getFontName());
		font.setFontHeightInPoints((short) obj.getHeightInPoints());
		style.setFont(font);
		return style;
	}


	@Override
	public List getHsNoCreateCompanyList(String reportTime, String authdata,
			String org) {
		List<Object[]> list = reportDao.getHsNoCreateCompanyList(reportTime,authdata, org,null, null);
		return list;
	}


	@Override
	public MsgPage getYsNoCreateCompanyList(String reportTime, String authdata,
			String org, Integer formKind, Integer CompanyKind,
			Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = reportDao.getYsNoCreateCompanyList(reportTime, authdata, org, formKind, CompanyKind, null, null).size();
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<Object[]> list = reportDao.getYsNoCreateCompanyList(reportTime, authdata, org, formKind, CompanyKind, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}

	@Override
	public MsgPage getRemindCompanyList(String reportTime, String authdata,
			String org, Integer formKind, Integer CompanyKind,
			Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = reportDao.getRemindCompanyList(reportTime, authdata, org, formKind, CompanyKind, null, null).size();
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<Object[]> list = reportDao.getRemindCompanyList(reportTime, authdata, org, formKind, CompanyKind, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}

	@Override
	public List getYsNoCreateCompanyList(String reportTime, String authdata,
			String org, Integer formKind, Integer CompanyKind) {
		List<Object[]> list = reportDao.getYsNoCreateCompanyList(reportTime, authdata, org, formKind, CompanyKind, null, null);
		return list;
	}
	
	@Override
	public boolean isVirtualCompany(String organalId){
		
		boolean flag = false;
		int backStatus = reportDao.isVirtualCompany(organalId);
		if(backStatus == 50500 || backStatus ==50502)
			flag = true;
		return flag;
	}
	
	@Override
	public void addAllDataToRemindPlan(String reportTime, String authdata,String org, Integer formKind, Integer CompanyKind) {
		// 更新本期已有数据为提醒计划
		reportRemindCompanyDao.setAllHavenDataRemind(reportTime, 51111);
		// 添加所有的数据到本期提醒计划
		reportDao.addAllDataToRemindPlan(reportTime, authdata, org, formKind, CompanyKind);
	}
	
	/**
	 * 		  预算模块指令下达任务   
	 * @param listIn    本期所有提醒数据
	 * @param listOut1
	 * @param mapOut2 1.sBSlctPersons 返回选择人公司信息   2.sBAccountNameDeps 返回人账号信息
	 * 				  3.nNodeId 公司编码        4.updateTime 更新时间
	 * @return
	 *    	author zl
	 */
	@Override
	public int getInstractionVcAccount(List<Object[]> listIn,
									   /*返回已发送填报公司邮箱信息*/List listOut1,
									   /*返回选中发送人账号和账号描述信息*/Map<String,Object> mapOut2){			
		if(null ==listIn)
			return -1;
		StringBuilder sBSlctPersons = new StringBuilder();		//记录需要发送公司负责人的账号信息
		StringBuilder sBAccountNameDeps = new StringBuilder();	//记录发送公司负责人的描述
		List<Object> nNodeIdList = new ArrayList<Object>(); 	//标识已发送公司编码信息
		for(Object[] objArr:listIn){		
			HhUsersmessageinfo hhUserMessageInfoTemp;
//			= sdkDao.checkmessageinfoRepeat(String.valueOf(objArr[4])); //通过员工号获取账号信息
			if(objArr[4]!=null && 
					(hhUserMessageInfoTemp = sdkDao.checkmessageinfoRepeat(String.valueOf(objArr[4])))!=null){
				sBSlctPersons.append(String.valueOf(objArr[4])).append(",");
				sBAccountNameDeps.append("无/"+String.valueOf(objArr[0])+"(财务树负责人)/无").append(",");
				nNodeIdList.add(objArr[7]);
				listOut1.add(hhUserMessageInfoTemp.getVcEmail());			
			}
		}	
		//设置返选择公司信息
		if(sBSlctPersons.length()==0)
			mapOut2.put("sBSlctPersons", null);
		else{
			sBSlctPersons.setLength(sBSlctPersons.length()-1);
			mapOut2.put("sBSlctPersons", sBSlctPersons.toString());
		}
		
		if(sBAccountNameDeps.length()==0)
			mapOut2.put("sBAccountNameDeps", null);
		else{
			sBAccountNameDeps.setLength(sBAccountNameDeps.length()-1);
			mapOut2.put("sBAccountNameDeps", sBAccountNameDeps.toString());
		}	
		mapOut2.put("nNodeId", nNodeIdList);
		//清空传入list集合中无用对象
		listIn.clear();
		return 0;
	}
}
