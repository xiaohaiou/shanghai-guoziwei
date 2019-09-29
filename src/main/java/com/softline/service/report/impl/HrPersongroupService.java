package com.softline.service.report.impl;

import java.io.FileInputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
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

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.DES;
import com.softline.common.ExcelTool;
import com.softline.dao.report.IHrPersongroupDao;
import com.softline.dao.system.ICommonDao;
import com.softline.dao.system.ISystemDao;
import com.softline.entity.HhBase;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entity.HrPersonOrganization;
import com.softline.entity.HrPersongroup;
import com.softline.entity.HrPersonitem;
import com.softline.entity.ReportPersonlloaninfoNew;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IHrPersongroupService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Service("hrPersongroupService")
/**
 * 
 * @author tch
 *
 */
public class HrPersongroupService implements IHrPersongroupService{

	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	@Autowired
	@Qualifier("systemDao")
	private ISystemDao systemDao;
	@Autowired
	@Qualifier("hrPersongroupDao")
	private IHrPersongroupDao hrPersongroupDao;
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "examineService")
	private IExamineService examineService;	
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	
	public MsgPage getHrPersongroupListView(HrPersongroup entity, Integer curPageNum, Integer pageSize)
	{
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = hrPersongroupDao.getHrPersongroupListCount(entity);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<HrPersongroup> list = hrPersongroupDao.getHrPersongroupList(entity, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}
	
	
	public MsgPage getHrPersonitemListView(HrPersongroup entity, Integer curPageNum, Integer pageSize)
	{
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = hrPersongroupDao.getHrPersonitemCount(entity.getId());
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<HrPersonitem> list = hrPersongroupDao.getHrPersonitem(entity.getId(), offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}
	
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public HrPersongroup getHrPersongroup(Integer id)
	{
		return hrPersongroupDao.getHrPersongroup( id);
	}
	
	/**
	 * 保存校验
	 * @param entity
	 * @return
	 */
	private CommitResult saveHrPersongroupCheck(HrPersongroup entity)
	{
		CommitResult result=new CommitResult();
		if(!hrPersongroupDao.saveHrPersongroupRepeatCheck(entity))
		{
			result=CommitResult.createErrorResult(entity.getYear()+"年该期已经有数据，不能再添加");
			return result;
		}
		if(!hrPersongroupDao.checkCanEdit(entity))
		{
			result=CommitResult.createErrorResult("该数据已经被上报不能修改");
			return result;
		}
		result.setResult(true);
		return result;
	}
	
	/**
	 * 保存更新
	 * @param entity
	 * @param use
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public CommitResult saveHrPersongroup(HrPersongroup entity,HhUsers use,MultipartFile itemfile,MultipartFile organizationFile) throws FileNotFoundException, IOException
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String[] time=entity.getDate().split("-");
		entity.setYear(Integer.parseInt(time[0]));
		entity.setMonth(Integer.parseInt(time[1]));
		CommitResult result=saveHrPersongroupCheck(entity);
		if(!result.isResult())
		{
			return result;
		}
		
		if(entity.getId()==null)
		{
			entity.setIsdel(0);
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(use.getVcEmployeeId());
			entity.setCreatePersonName(use.getVcName());
			
		}
		else
		{
			entity.setIsdel(0);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
		}
		
		HhOrganInfoTreeRelation a= systemService.getTreeOrganInfoNode(Base.financetype, entity.getOrg());
		entity.setOrgname(a.getVcFullName());
		entity.setParentorg(a.getVcOrganId());
		
		commonDao.saveOrUpdate(entity);
		if(itemfile!=null && itemfile.getSize()>0)
		{
			try{
				result=saveHrPersonitem( itemfile, entity);
			}catch(Exception e)
			{
				result=CommitResult.createErrorResult("文件不是xls或者xlsx文档或者文档损坏，保存失败");
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return result;		
			}
			if(result.isResult()==false)
			{
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return result;
			}
		}
		if(organizationFile!=null && organizationFile.getSize()>0)
		{
			try{
				result=saveHrPersonorgabization( organizationFile, entity);
			}catch(Exception e)
			{
				result=CommitResult.createErrorResult("文件不是xls或者xlsx文档或者文档损坏，保存失败");
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return result;		
			}
			if(result.isResult()==false)
			{
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return result;
			}
		}
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}

	
	/**
	 * 维护财务编制数据
	 * @param organizationFile
	 * @param groupID
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	private CommitResult saveHrPersonorgabization(MultipartFile organizationFile, HrPersongroup entity)throws FileNotFoundException, IOException{
		CommitResult result=new CommitResult();
		hrPersongroupDao.deleteHrPersonorganization(entity.getId().toString());
		List<HrPersonOrganization> hrPersonOrganization=new ArrayList<HrPersonOrganization>();
		List<HhOrganInfo> organ=systemDao.getCompanyOrganInfos();
		ExcelTool exceltool=new ExcelTool();
        //先暂存附件
        List<String> fileStrList = Common.saveFile(organizationFile, DES.hrfinance_path);
        //文件路径
        String xlsPath=fileStrList.get(1);
        InputStream is = new FileInputStream(xlsPath);
       
        Workbook workbook = null; 
        try { 
        	workbook = new XSSFWorkbook(is); 
        } catch (Exception ex) { 
        	POIFSFileSystem poifsFileSystem= new POIFSFileSystem(is);
        	workbook = new HSSFWorkbook(poifsFileSystem); 
        } 
        Sheet  sheet = workbook.getSheetAt(1);
        boolean flag = false; 
       
        //核心公司
        List<HhBase> businesscompanyBase=baseService.getBases(82201);
        //所属管理类别
        List<HhBase> managelevel=baseService.getBases(53050);
        //所属岗位类别
        List<HhBase> postkindBase=baseService.getBases(53040);
        //干部/员工
        List<HhBase> poststatusBase=baseService.getBases(53037);
       //所属管理类别
        List<HhBase> actualLevelBase=baseService.getBases(53050);
        
        for (int i = 0; i <= sheet.getLastRowNum();) {  
            Row r = sheet.getRow(i);  
            if(r == null){  
                // 如果是空行（即没有任何数据、格式），直接把它以下的数据往上移动  
                sheet.shiftRows(i+1, sheet.getLastRowNum(),-1);  
                continue;  
            }  
            flag = false;  
            for(Cell c:r){  
                if(c.getCellType() != Cell.CELL_TYPE_BLANK){  
                    flag = true;  
                    break;  
                }  
            }  
            if(flag){  
                i++;  
                continue;  
            }  
            else{//如果是空白行（即可能没有数据，但是有一定格式）  
                if(i == sheet.getLastRowNum())//如果到了最后一行，直接将那一行remove掉  
                    sheet.removeRow(r);  
                else//如果还没到最后一行，则数据往上移一行  
                    sheet.shiftRows(i+1, sheet.getLastRowNum(),-1);  
            }  
        }  

        
        for (int k = 1; k <= sheet.getLastRowNum(); k++) {
        	Row row = sheet.getRow(k);
        	int i=0;//列索引
        	HrPersonOrganization item=new HrPersonOrganization();
        	Cell cell= row.getCell(0);
        	item.setIsdel(0);
        	item.setGroupId(entity.getId());
        	
        	
        	String businesscompany=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	if(Common.notEmpty(businesscompany))
        	{
        		for (HhBase obj : businesscompanyBase) {
        			if(obj.getDescription().equals(businesscompany.trim()))
        			{
        				item.setBusinesscompany(obj.getId());
        				break;
        			}
        		}
        	}
        	
        	String companyname=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	item.setCompanyname(companyname);
        	
        	String post=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	item.setPost(post);
        	
        	String postkind=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	if(Common.notEmpty(postkind))
        	{
        		for (HhBase obj : postkindBase) {
        			if(obj.getDescription().equals(postkind.trim()))
        			{
        				item.setPostkind(obj.getId());
        				break;
        			}
        		}
        	}
			
        	String other_work_remark=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	item.setOtherWorkRemark(other_work_remark);
			
        	String post_status=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	if(Common.notEmpty(post_status))
        	{
        		for (HhBase obj : poststatusBase) {
        			if(obj.getDescription().equals(post_status.trim()))
        			{
        				item.setPostStatus(obj.getId());
        				break;
        			}
        		}
        	}
        	
        	String manage_level=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	if(Common.notEmpty(manage_level))
        	{
        		for (HhBase obj : managelevel) {
        			if(obj.getDescription().equals(manage_level.trim()))
        			{
        				item.setManageLevel(obj.getId());
        				break;
        			}
        		}
        	}
        	    
            try{
	            String number_people_A=exceltool.getCellValue(workbook, row.getCell(i));i++;
	            item.setNumberPeopleA(Integer.parseInt(number_people_A));
            }
            catch(Exception e)
            {}
            
            try{
	            String actual_number_people_B=exceltool.getCellValue(workbook, row.getCell(i));i++;
	            item.setActualNumberPeopleB(Integer.parseInt(actual_number_people_B));
            }
            catch(Exception e)
            {}
            
        	String name_actual_people=exceltool.getCellValue(workbook, row.getCell(i));i++;
            item.setNameActualPeople(name_actual_people);
            

            String actual_level=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	if(Common.notEmpty(actual_level))
        	{
        		for (HhBase obj : actualLevelBase) {
        			if(obj.getDescription().equals(actual_level.trim()))
        			{
        				item.setActualLevel(obj.getId());
        				break;
        			}
        		}
        	}
	            
            try{
	            String super_lack=exceltool.getCellValue(workbook, row.getCell(i));i++;
	            item.setSuperLack((int) Double.parseDouble(super_lack));
            }
            catch(Exception e)
            {}
        	
            String remark=exceltool.getCellValue(workbook, row.getCell(i));i++;
            item.setRemark(remark);
            
        	
        	
        	
        	hrPersonOrganization.add(item);
        }
        hrPersongroupDao.saveHrPersonOrganization(hrPersonOrganization);
        commonDao.saveOrUpdate(entity);
        result.setResult(true);
        return result;
	}


	/**
	 * 维护人事数据
	 * @param itemfile
	 * @param groupID
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	private CommitResult saveHrPersonitem(MultipartFile itemfile,HrPersongroup entity) throws FileNotFoundException, IOException
	{
		CommitResult result=new CommitResult();
		hrPersongroupDao.deleteHrPersonitem(entity.getId().toString());
		List<HrPersonitem> HrPersonitem=new ArrayList<HrPersonitem>();
		List<HhOrganInfo> organ=systemDao.getCompanyOrganInfos();
		ExcelTool exceltool=new ExcelTool();
        //先暂存附件
        List<String> fileStrList = Common.saveFile(itemfile, DES.hrfinance_path);
        //文件路径
        String xlsPath=fileStrList.get(1);
        InputStream is = new FileInputStream(xlsPath);
       
        Workbook workbook = null; 
        try { 
        	workbook = new XSSFWorkbook(is); 
        } catch (Exception ex) { 
        	POIFSFileSystem poifsFileSystem= new POIFSFileSystem(is);
        	workbook = new HSSFWorkbook(poifsFileSystem); 
        } 
        Sheet  sheet = workbook.getSheetAt(1);
       
        //所属岗位类别
        List<HhBase> managelevel=baseService.getBases(53050);
        //所属岗位类别
        List<HhBase> postkindBase=baseService.getBases(53040);
        //干部/员工
        List<HhBase> poststatusBase=baseService.getBases(53037);
        //性别
        List<HhBase> sexBase=baseService.getBases(53035);
        //学历
        List<HhBase> educationBase=baseService.getBases(53031);
        //院校类别
        List<HhBase> schoollevelBase=baseService.getBases(53025);
        //财务职称或资格最高级别
        List<HhBase> financialtitleBase=baseService.getBases(53019);
        //英语水平
        List<HhBase> englishlevelBase=baseService.getBases(53013);
        //是否有境外留学或工作经历
        List<HhBase> overseasexperienceBase=baseService.getBases(53010);
        //核心企业
        List<HhBase> businesscompanyBase=baseService.getBases(82201);
        //是否
        List<HhBase> truefalseBase=baseService.getBases(Base.truefalse);
        
        //学习形式
        List<HhBase> learnFunBase=baseService.getBases(53060);
        //职称合格人数
        int titlepass=0;
        //财务从业人员数
        int all=0;
        //财务专业资质合格人数
       // int financepass=0;
        for (int k = 1; k < sheet.getPhysicalNumberOfRows(); k++) {
        	Row row = sheet.getRow(k);
        	if(Common.notEmpty(exceltool.getCellValue(workbook, row.getCell(7)))){//姓名不能为空
        	int i=1;//列索引
        	all++;
        	HrPersonitem item=new HrPersonitem();
        	Cell cell= row.getCell(0);
        	item.setIsdel(0);
        	item.setGroupId(entity.getId());
        	
        	String companyname=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	item.setCompanyname(companyname);
        	
        	String post=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	item.setPost(post);
        	
        	String postkind=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	if(Common.notEmpty(postkind))
        	{
        		for (HhBase obj : postkindBase) {
        			if(obj.getDescription().equals(postkind.trim()))
        			{
        				item.setPostkind(obj.getId());
        				break;
        			}
        		}
        	}
			
        	String other_work_remark=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	item.setOtherWorkRemark(other_work_remark);
			
        	String post_status=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	if(Common.notEmpty(post_status))
        	{
        		for (HhBase obj : poststatusBase) {
        			if(obj.getDescription().equals(post_status.trim()))
        			{
        				item.setPostStatus(obj.getId());
        				break;
        			}
        		}
        	}
        	
        	String manage_level=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	if(Common.notEmpty(manage_level))
        	{
        		for (HhBase obj : managelevel) {
        			if(obj.getDescription().equals(manage_level.trim()))
        			{
        				item.setManageLevel(obj.getId());
        				break;
        			}
        		}
        	}
        	
        	String name=exceltool.getCellValue(workbook, row.getCell(i));i++;
            item.setName(name);
        	
        	String sex=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	if(Common.notEmpty(sex))
        	{
        		for (HhBase obj : sexBase) {
        			if(obj.getDescription().equals(sex.trim()))
        			{
        				item.setSex(obj.getId());
        				break;
        			}
        		}
        	}
        	
        	String birthday=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	
            	item.setBirthday((birthday));
           
            
            try{
	            String age=exceltool.getCellValue(workbook, row.getCell(i));i++;
	            item.setAge(Integer.parseInt(age));
            }
            catch(Exception e)
            {}
            
            try{
	            String workage=exceltool.getCellValue(workbook, row.getCell(i));i++;
	            item.setWorkage(Integer.parseInt(workage));
            }
            catch(Exception e)
            {}
            
            String setup_time=exceltool.getCellValue(workbook, row.getCell(i));i++;
           
            	item.setSetupTime((setup_time));
           
            String education=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	if(Common.notEmpty(education))
        	{
        		for (HhBase obj : educationBase) {
        			if(obj.getDescription().equals(education.trim()))
        			{
        				item.setEducation(obj.getId());
        				break;
        			}
        		}
        	}
        	
        	String school=exceltool.getCellValue(workbook, row.getCell(i));i++;
         	item.setSchool(school);
         	
         	String schoollevel=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	if(Common.notEmpty(schoollevel))
        	{
        		for (HhBase obj : schoollevelBase) {
        			if(obj.getDescription().equals(schoollevel.trim()))
        			{
        				item.setSchoollevel(obj.getId());
        				break;
        			}
        		}
        	}
        	String learnfun = exceltool.getCellValue(workbook, row.getCell(i));i++;
        	if(Common.notEmpty(learnfun))
        	{
        		for (HhBase obj : learnFunBase) {
        			if(obj.getDescription().equals(learnfun.trim()))
        			{
        				item.setLearnfun(obj.getId());
        				break;
        			}
        		}
        	}
        	
        	String schoolqualified=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	if(Common.notEmpty(schoolqualified))
        	{
        		for (HhBase obj : truefalseBase) {
        			if(obj.getDescription().equals(schoolqualified.trim()))
        			{
        				item.setSchoolqualified(obj.getId());
        				break;
        			}
        		}
        	}
        	
        	String major=exceltool.getCellValue(workbook, row.getCell(i));i++;
         	item.setMajor(major);
         	
         	
         	String isfinance=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	if(Common.notEmpty(isfinance))
        	{
        		for (HhBase obj : truefalseBase) {
        			if(obj.getDescription().equals(isfinance.trim()))
        			{
        				item.setIsfinance(obj.getId());
        				break;
        			}
        		}
        	}
        	
        	String financialtitle=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	if(Common.notEmpty(financialtitle))
        	{
        		for (HhBase obj : financialtitleBase) {
        			if(obj.getDescription().equals(financialtitle.trim()))
        			{
        				item.setFinancialtitle(obj.getId());
        				break;
        			}
        		}
        	}
        	
        	String titlequalified=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	if(Common.notEmpty(titlequalified))
        	{
        		for (HhBase obj : truefalseBase) {
        			if(obj.getDescription().equals(titlequalified.trim()))
        			{
        				item.setTitlequalified(obj.getId());
        				//是
        				if(obj.getId().equals(Base.truefalse_true))
        				{
        					titlepass++;
        				}
        				break;
        			}
        		}
        	}
        	
        	String titlename=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	item.setTitlename(titlename);
        	
        	String englishlevel=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	if(Common.notEmpty(englishlevel))
        	{
        		for (HhBase obj : englishlevelBase) {
        			if(obj.getDescription().equals(englishlevel.trim()))
        			{
        				item.setEnglishlevel(obj.getId());
        				break;
        			}
        		}
        	}
        	
        	String englishqualified=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	if(Common.notEmpty(englishqualified))
        	{
        		for (HhBase obj : truefalseBase) {
        			if(obj.getDescription().equals(englishqualified.trim()))
        			{
        				item.setEnglishqualified(obj.getId());
        				break;
        			}
        		}
        	}
        	
        	String englishlevelremark=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	item.setEnglishlevelremark(englishlevelremark);
        	
        	String workplace=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	item.setWorkplace(workplace);
        	
        	String overseasexperience=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	if(Common.notEmpty(overseasexperience))
        	{
        		for (HhBase obj : overseasexperienceBase) {
        			if(obj.getDescription().equals(overseasexperience.trim()))
        			{
        				item.setOverseasexperience(obj.getId());
        				break;
        			}
        		}
        	}
        	
        	String businesscompany=exceltool.getCellValue(workbook, row.getCell(i));i++;
        	if(Common.notEmpty(businesscompany))
        	{
        		for (HhBase obj : businesscompanyBase) {
        			if(obj.getDescription().equals(businesscompany.trim()))
        			{
        				item.setBusinesscompany(obj.getId());
        				break;
        			}
        		}
        	}
        	
        	HrPersonitem.add(item);
        }
        }
        hrPersongroupDao.saveHrPersonitem(HrPersonitem);
        entity.setFinancepeople(all);
        entity.setTitlepasspeople(titlepass);
        commonDao.saveOrUpdate(entity);
        result.setResult(true);
        return result;
	}
	
	
	
	
	/**
	 * 校验文本内容
	 * @param str
	 * @return
	 */
	private boolean checkStr(String str)
	{
		if(!Common.notEmpty(str))
			return true;
		if(!Common.canparseNumber(str) || str.length()>=30)
    	{
    		return false;
    	}
		return true;
	}
	
	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteHrPersongroup(Integer id,HhUsers use)
	{
		
		HrPersongroup entity=hrPersongroupDao.getHrPersongroup(id);
		CommitResult result=new CommitResult();
		if(!hrPersongroupDao.checkCanEdit(entity))
		{
			result=CommitResult.createErrorResult("该数据已经被上报不能修改");
			return result;
		}
		if(entity!=null)
		{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entity.setIsdel(1);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
			hrPersongroupDao.deleteHrPersonitem(entity.getId().toString());
			commonDao.saveOrUpdate(entity);
			
		}
		
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}
	
	
	/**
	 * 审核校验判断是否还能够审核，可能别人已经审核过了
	 * @param entityID 采购ID
	 * @return
	 */
	public CommitResult saveHrPersongroupExamineCheck(Integer entityID)
	{
		CommitResult result=new CommitResult();
		HrPersongroup entity=hrPersongroupDao.getHrPersongroup(entityID);
		if(entity==null)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
			return result;
		}
	/*	if(!entity.getStatus().equals(Base.examstatus_2))
		{
			result=CommitResult.createErrorResult("该信息已不用审核");
			return result;
		}*/
		result.setResult(true);
		return result;
	}
	
	/**
	 * 审核
	 * @param entityID 采购ID
	 * @param examStr 审核备注
	 * @param examResult 审核意见
	 * @param use
	 * @return
	 */
	public CommitResult saveHrPersongroupExamine(Integer entityID,String examStr,Integer examResult,HhUsers use)
	{
		CommitResult result=new CommitResult();
		HrPersongroup entity=getHrPersongroup(entityID);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		result=saveHrPersongroupExamineCheck(entityID);
		if(!result.isResult())
		{
			return result;
		}
		//审核不通过
		if(examResult.equals(Base.examresult_1))
			entity.setStatus(Base.examstatus_3);
		//审核通过
		else if(examResult.equals(Base.examresult_2))
			entity.setStatus(Base.examstatus_4);
		String a=df.format(new Date());
		entity.setAuditorDate(df.format(new Date()));
		entity.setAuditorPersonId(use.getVcEmployeeId());
		entity.setAuditorPersonName(use.getVcName());
		//保存HrPersongroup
		commonDao.saveOrUpdate(entity);
		
		Integer examineentityid=entity.getId();

		examineService.saveExamine( examineentityid, Base.examkind_hr, use, examStr, examResult);
		
		result.setResult(true);
		return result;
	}


	@Override
	public MsgPage getHrPersonorganizationListView(HrPersongroup entity,Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = hrPersongroupDao.getHrPersonorganizationCount(entity.getId());
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<HrPersonOrganization> list = hrPersongroupDao.getHrPersonorganization(entity.getId(), offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}

	

	@Override
	public List<HrPersongroup> getHrPersongroup(HrPersongroup entity) {
		// TODO Auto-generated method stub
		return hrPersongroupDao.getHrPersongroup(entity);
	}


	@Override
	public List<HrPersonOrganization> getHrPersonorganization(Integer id) {
		// TODO Auto-generated method stub
		return hrPersongroupDao.getHrPersonorganization(id);
	}


	@Override
	public XSSFWorkbook getHrPeresonorganizationList(List<HrPersongroup> exportLists,List<HrPersonOrganization> exportList) {
		
		XSSFWorkbook workBook = new XSSFWorkbook();
		XSSFCell cell = null;
		XSSFSheet sheet = workBook.createSheet("财务人员编制数据");
		CellStyle style=workBook.createCellStyle();
	
		XSSFRow row = sheet.createRow((int) 0);  
		String[] header={"序号","时间","单位名称","核心公司","公司全称","岗位","所属岗位类别","[其他]工作类别备注栏","干部/员工","管理级别",
				"编制人数A","实际在岗人数B","在岗人员姓名","实际级别","超编+/缺编-(A-B)","备注"};
		for(int i = 0; i < header.length; i++) {
			row.createCell(i).setCellValue(header[i]);
		}
        for (int i = 0; i < exportLists.size(); i++) {
			  for (int j = 0; j < exportList.size(); j++) {
				    if(exportLists.get(i).getId().equals(exportList.get(j).getGroupId())){
				    	 int k = 0;
						 HrPersonOrganization item = exportList.get(j);
						 row=sheet.createRow(j+1);
						 //序号
						 row.createCell(k).setCellValue(j+1);k++;
						 //时间
					     row.createCell(k).setCellValue(exportLists.get(i).getYear()+"-"+exportLists.get(i).getMonth());k++;
					     //单位名称
					     row.createCell(k).setCellValue(exportLists.get(i).getOrgname());k++;
					     //核心公司
					     row.createCell(k).setCellValue(item.getBusinesscompanyName());k++;
					     //公司全称
					     row.createCell(k).setCellValue(item.getCompanyname());k++;
					     //岗位
					     row.createCell(k).setCellValue(item.getPost());k++;
					     //所属岗位类别
					     row.createCell(k).setCellValue(item.getPostkindName());k++;			  
					     //[其他]工作类别备注栏
					     row.createCell(k).setCellValue(item.getOtherWorkRemark());k++;
					     //干部/员工
					     row.createCell(k).setCellValue(item.getPostkindName());k++;
					     //管理级别
					     row.createCell(k).setCellValue(item.getManageLevelName());k++;
					     //编制人数A
					     if(null != item.getNumberPeopleA()){
					    	 row.createCell(k).setCellValue(item.getNumberPeopleA());k++;
					     }else{
					    	 row.createCell(k).setCellValue("");k++;
					     }
					     //实际在岗人数B
					     if(null != item.getActualNumberPeopleB()){
					    	 row.createCell(k).setCellValue(item.getActualNumberPeopleB());k++;
					     }else{
					    	 row.createCell(k).setCellValue("");k++;
					     }
					     //在岗人员姓名
					     row.createCell(k).setCellValue(item.getNameActualPeople());k++;
					     //实际级别
					     row.createCell(k).setCellValue(item.getActualLevelName());k++;
					     //超编+/缺编-(A-B)
					     if(null != item.getSuperLack()){
					    	 row.createCell(k).setCellValue(item.getSuperLack());k++;
					     }else{
					    	 row.createCell(k).setCellValue("");k++;
					     }
					    
					     //备注
					     row.createCell(k).setCellValue(item.getRemark());k++;
				    }
				
			}
		}

		 return workBook;
		
	}


	@Override
	public List<HrPersonitem> getHrPersonitem(Integer id) {
		// TODO Auto-generated method stub
		return hrPersongroupDao.getHrPersonitem(id);
	}

	@Override
	public XSSFWorkbook getHrPeresonitemList(List<HrPersongroup> exportLists,List<HrPersonitem> exportList) {
		
		XSSFWorkbook workBook = new XSSFWorkbook();
		XSSFCell cell = null;
		XSSFSheet sheet = workBook.createSheet("财务人员资质数据");
		CellStyle style=workBook.createCellStyle();
	
		XSSFRow row = sheet.createRow((int) 0);  
		String[] header={"序号","时间","单位名称","岗位","所属岗位类别","[其他]工作类别备注栏","干部/员工","管理级别","姓名","性别","出生年月日",
				"年龄","海航工龄","现岗位定岗时间","学历","毕业院校","院校类别","学习形式","院校是否达标","专业全称","是否为财经专业","财务职称或资格最高级别",
				"职称是否达标","财务专业技术职称及资格全称（初级及以上）","英语水平","英语是否达标","[其他]英语水平备注栏","工作地点",
				"是否有境外留学或工作经历","核心公司"};
		for(int i = 0; i < header.length; i++) {
			row.createCell(i).setCellValue(header[i]);
		}
       for (int i = 0; i < exportLists.size(); i++) {
    	   for (int j = 0; j < exportList.size(); j++) {
    		   if(exportLists.get(i).getId().equals(exportList.get(j).getGroupId())){
    			     int k = 0;
	  				 HrPersonitem item = exportList.get(j);
	  				 row=sheet.createRow(j+1);
	  				 //序号
	  				 row.createCell(k).setCellValue(j+1);k++;
	  				 //时间
	  			     row.createCell(k).setCellValue(exportLists.get(i).getYear()+"-"+exportLists.get(i).getMonth());k++;
	  			     //单位名称
	  			     row.createCell(k).setCellValue(exportLists.get(i).getOrgname());k++;
	  			     //岗位
	  			     row.createCell(k).setCellValue(item.getPost());k++;
	  			     //所属岗位类别
	  			     row.createCell(k).setCellValue(item.getPostkindName());k++;			  
	  			     //[其他]工作类别备注栏
	  			     row.createCell(k).setCellValue(item.getOtherWorkRemark());k++;
	  			     //干部/员工
	  			     row.createCell(k).setCellValue(item.getPostkindName());k++;
	  			     //管理级别
	  			     row.createCell(k).setCellValue(item.getManageLevelName());k++;
	  			     //姓名
	  			     row.createCell(k).setCellValue(item.getName());k++;
	  			     //性别
	  			     row.createCell(k).setCellValue(item.getSexName());k++;
	  			     //出生年月日
	  			     row.createCell(k).setCellValue(item.getBirthday());k++;
	  			     //年龄
	  			     if(null != item.getAge()){
	  			    	row.createCell(k).setCellValue(item.getAge());k++;
	  			     }else{
	  			    	row.createCell(k).setCellValue("");k++;
	  			     }
	  			     
	  			     //海航工龄
	  			     if(null != item.getWorkage()){
	  			    	 row.createCell(k).setCellValue(item.getWorkage());k++;
	  			     }else{
	  			    	 row.createCell(k).setCellValue("");k++;
	  			     }
	  			    
	  			     //现岗位定岗时间
	  			     row.createCell(k).setCellValue(item.getSetupTime());k++;			     
	  			     //学历
	  			     row.createCell(k).setCellValue(item.getEducationName());k++;
	  			     //毕业院校
	  			     row.createCell(k).setCellValue(item.getSchool());k++;
	  			     //院校类别
	  			     row.createCell(k).setCellValue(item.getSchoollevelName());k++;			  
	  			     //学习形式
	  			     row.createCell(k).setCellValue(item.getLearnfunName());k++;
	  			     //院校是否达标
	  			     row.createCell(k).setCellValue(item.getSchoolqualifiedName());k++;
	  			     //专业全称
	  			     row.createCell(k).setCellValue(item.getMajor());k++;
	  			     //是否为财经专业
	  			     row.createCell(k).setCellValue(item.getIsfinanceName());k++;
	  			     //财务职称或资格最高级别
	  			     row.createCell(k).setCellValue(item.getFinancialtitleName());k++;
	  			     //职称是否达标
	  			     row.createCell(k).setCellValue(item.getTitlequalifiedName());k++;
	  			     //财务专业技术职称及资格全称（初级及以上）
	  			     row.createCell(k).setCellValue(item.getTitlename());k++;			     
	  			     //英语水平
	  			     row.createCell(k).setCellValue(item.getEnglishlevelName());k++;
	  			     //英语是否达标
	  			     row.createCell(k).setCellValue(item.getEnglishqualifiedName());k++;
	  			     //【其他】英语水平备注栏
	  			     row.createCell(k).setCellValue(item.getEnglishlevelremark());k++;			  
	  			     //工作地点
	  			     row.createCell(k).setCellValue(item.getWorkplace());k++;
	  			     //是否有境外留学或工作经历
	  			     row.createCell(k).setCellValue(item.getOverseasexperienceName());k++;
	  			     //核心公司
	  			     row.createCell(k).setCellValue(item.getBusinesscompanyName());k++;
    		   }
		}
		      
	}
	   for (int i = 0; i < exportList.size(); i++) {
				
				    
			}
	  return workBook;
	}









}
