package com.softline.service.settingCenter.user.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.CellStyle;
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

import com.softline.dao.settingCenter.user.IPageCodeDao;
import com.softline.entity.HhPagecode;
import com.softline.entity.settingCenter.HhModelRegister;
import com.softline.entity.settingCenter.PortalHhPagecode;
import com.softline.entity.settingCenter.HhPageregister;
import com.softline.service.settingCenter.user.IModelRegisterService;
import com.softline.service.settingCenter.user.IPageCodeService;
import com.softline.service.settingCenter.user.IPageRegisterService;
import com.softline.service.system.IAuthorityService;
import com.softline.service.system.ICommonService;
import com.softline.util.MsgPage;

@Service("pageCodeService")
public class PageCodeService implements IPageCodeService {
	@Autowired
	@Qualifier("pageCodeDao")
	private IPageCodeDao pageCodeDao;
	
	@Resource(name = "modelRegisterService")
	private IModelRegisterService modelRegisterService;
	
	@Resource(name = "pageRegisterService")
	private IPageRegisterService pageRegisterService;
	
	@Resource(name = "commonService")
	private ICommonService commonService;
	
	@Resource(name = "authorityService")
	private IAuthorityService authorityService;

	@Override
	public MsgPage getPageMsgCodeList(String qCodeName, String qModelId, String qPageId, Integer curPageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = pageCodeDao.getAllRowCount(qCodeName, qModelId, qPageId);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<PortalHhPagecode> list = pageCodeDao.getPageCodeList(qCodeName, qModelId, qPageId, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}

	@Override
	public PortalHhPagecode getThePageCode(Integer id) {
		// TODO Auto-generated method stub
		return pageCodeDao.getThePageCode(id);
	}

	@Override
	public void saveOrUpdatePageCode(PortalHhPagecode register) {
		pageCodeDao.saveOrUpdatePageCode(register);
		//调接口
		Integer modelId = register.getModelId();
		Integer pageId = register.getPageId();
		HhModelRegister hhModelRegister = modelRegisterService.getTheModelRegister(modelId);
		HhPageregister hhPageregister = pageRegisterService.getThePageRegister(pageId);
		String modelNum = hhModelRegister.getModelNum();
		if(modelNum.equals("bim_show")){
//			ShowWebserviceService service = new ShowWebserviceService();
//			IShowWebservice is = service.getPort(IShowWebservice.class);
//			ShowPageCode showPageCode = new ShowPageCode();
//			showPageCode.setPortalId(register.getId());
//			showPageCode.setIsDel(0);
//			showPageCode.setSeqNo(register.getSeqNo());
//			showPageCode.setCode(register.getCode());
//			showPageCode.setCodeName(register.getCodeName());
//			showPageCode.setPageId(register.getPageId());
//			showPageCode.setPageNum(hhPageregister.getPageNum());
//			is.showUpdaterPageCode(showPageCode);
		}
		if(modelNum.equals("bim_task")){
//			TaskWebserviceService service = new TaskWebserviceService();
//			ITaskWebservice it = service.getPort(ITaskWebservice.class);
//			com.softline.client.task.HhPagecode hhPagecode = new com.softline.client.task.HhPagecode();
//			hhPagecode.setPortalId(register.getId());
//			hhPagecode.setIsDel(0);
//			hhPagecode.setSeqNo(register.getSeqNo());
//			hhPagecode.setCode(register.getCode());
//			hhPagecode.setCodeName(register.getCodeName());
//			hhPagecode.setPageId(register.getPageId());
//			hhPagecode.setPageNum(hhPageregister.getPageNum());
//			it.updateHhPagecode(hhPagecode);
		}
		if(modelNum.equals("bim_work")){
			HhPagecode hhPagecode = new HhPagecode();
			hhPagecode.setPortalId(register.getId());
			hhPagecode.setIsDel(0);
			hhPagecode.setSeqNo(register.getSeqNo());
			hhPagecode.setCode(register.getCode());
			hhPagecode.setCodeName(register.getCodeName());
			hhPagecode.setPageId(register.getPageId());
			hhPagecode.setPageNum(hhPageregister.getPageNum());
			authorityService.updateHhPagecode(hhPagecode);
		}
	}

	@Override
	public String deletePageCode(Integer id) {
		// TODO Auto-generated method stub
		pageCodeDao.deletePageCode(id);
		//调用接口，把删除的功能传到选择的子模块中去
		PortalHhPagecode register = (PortalHhPagecode)commonService.findById(PortalHhPagecode.class, id);
		Integer modelId = register.getModelId();
		Integer pageId = register.getPageId();
		HhModelRegister hhModelRegister = modelRegisterService.getTheModelRegister(modelId);
		HhPageregister hhPageregister = pageRegisterService.getThePageRegister(pageId);
		String modelNum = hhModelRegister.getModelNum();
		if(modelNum.equals("bim_show")){
//			ShowWebserviceService service = new ShowWebserviceService();
//			IShowWebservice is = service.getPort(IShowWebservice.class);
//			ShowPageCode showPageCode = new ShowPageCode();
//			showPageCode.setPortalId(register.getId());
//			showPageCode.setIsDel(1);
//			showPageCode.setSeqNo(register.getSeqNo());
//			showPageCode.setCode(register.getCode());
//			showPageCode.setCodeName(register.getCodeName());
//			showPageCode.setPageId(register.getPageId());
//			showPageCode.setPageNum(hhPageregister.getPageNum());
//			is.showUpdaterPageCode(showPageCode);
		}
		if(modelNum.equals("bim_task")){
//			TaskWebserviceService service = new TaskWebserviceService();
//			ITaskWebservice it = service.getPort(ITaskWebservice.class);
//			com.softline.client.task.HhPagecode hhPagecode = new com.softline.client.task.HhPagecode();
//			hhPagecode.setPortalId(register.getId());
//			hhPagecode.setIsDel(1);
//			hhPagecode.setSeqNo(register.getSeqNo());
//			hhPagecode.setCode(register.getCode());
//			hhPagecode.setCodeName(register.getCodeName());
//			hhPagecode.setPageId(register.getPageId());
//			hhPagecode.setPageNum(hhPageregister.getPageNum());
//			it.updateHhPagecode(hhPagecode);
		}
		if(modelNum.equals("bim_work")){
			HhPagecode hhPagecode = new HhPagecode();
			hhPagecode.setPortalId(register.getId());
			hhPagecode.setIsDel(1);
			hhPagecode.setSeqNo(register.getSeqNo());
			hhPagecode.setCode(register.getCode());
			hhPagecode.setCodeName(register.getCodeName());
			hhPagecode.setPageId(register.getPageId());
			hhPagecode.setPageNum(hhPageregister.getPageNum());
			authorityService.updateHhPagecode(hhPagecode);
		}
		return "success";
	}

	@Override
	public List<PortalHhPagecode> getCodeList(Integer sysId, Integer pageId) {
		return pageCodeDao.getCodeList(sysId, pageId);
	}


	@Override
	public List<PortalHhPagecode> getPagecodesByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		return pageCodeDao.getPagecodesByRoleId(roleId);
	}

	@Override
	public List<PortalHhPagecode> getPagecodesByModelIdAndRoleId(Integer id,
			Integer roleId) {
		// TODO Auto-generated method stub
		return pageCodeDao.getPagecodesByModelIdAndRoleId(id, roleId);
	}

	@Override
	public Integer checkPagecodeNum(String pagecodeNum, Integer id) {
		// TODO Auto-generated method stub
		return pageCodeDao.checkPagecodeNum(pagecodeNum, id);
	}

	@Override
	public MsgPage getPageCodePersonList(Integer modelId, String vcName,String vcAccount, Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage(); 
		//总记录数
        Integer totalRecords = pageCodeDao.getPageCodePersonlistCount(modelId, vcName, vcAccount);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<Object[]> list = pageCodeDao.getPageCodePersonlist(modelId, vcName, vcAccount, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	
	
	}
	
		
	

	
	@Override
	public XSSFWorkbook getPageCodePersonListExport(Integer modelId,String vcName, String vcAccount) {
		List<Object[]> list = pageCodeDao.getPageCodePersonlist(modelId, vcName, vcAccount, null, null);
		XSSFWorkbook workBook = new XSSFWorkbook();
		 CellStyle style=workBook.createCellStyle();
		 XSSFFont font = workBook.createFont();
		 
		XSSFCell cell=null;
//		定义单元格属性为空2
		XSSFSheet sheet=workBook.createSheet("人员列表");
		XSSFRow row=sheet.createRow((int)0);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
		font.setBold(true);
		 font.setFontHeightInPoints((short) 15);
		 style.setFont(font);
		 style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中  
		 style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直  
		
		cell=row.createCell((short) 0);
		cell.setCellValue("人员列表");
		cell.setCellStyle(style);
		  row=sheet.createRow((int)1);
			String [] header={"序号","姓名","账号","单位"
					};
			for (int i = 0; i < header.length; i++) {
				//字段长度
				cell=row.createCell((short) i);
				cell.setCellValue(header[i]);
				cell.setCellStyle(style);
			}
			
			for (int i = 0; i < list.size(); i++) {
				 row=sheet.createRow(2+i);
				 Object[] object = list.get(i);
				 
				 //序号
				 cell=row.createCell(0);
				 cell.setCellValue(i+1);
				 
				 //姓名
				 cell=row.createCell(1);
				 cell.setCellValue(object[0]==null?"":object[0].toString());
				 //账号
				 cell=row.createCell(2);
				 cell.setCellValue(object[1]==null?"":object[1].toString());
				 //单位
				 cell=row.createCell(3);
				 cell.setCellValue(object[2]==null?"":object[2].toString());
			}
		return workBook;
	}

	/*@Override
	public Integer checkSysNum(String sysNum) {
		// TODO Auto-generated method stub
		return pageRegisterDao.checkSysNum(sysNum);
	}*/

}
