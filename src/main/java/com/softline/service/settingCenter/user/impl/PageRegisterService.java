package com.softline.service.settingCenter.user.impl;

import java.util.ArrayList;
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
import com.softline.dao.settingCenter.user.IPageRegisterDao;
import com.softline.entity.HhPage;
import com.softline.entity.settingCenter.HhModelRegister;
import com.softline.entity.settingCenter.HhPageregister;
import com.softline.entity.settingCenter.PortalHhPagecode;
import com.softline.service.settingCenter.user.IModelRegisterService;
import com.softline.service.settingCenter.user.IPageCodeService;
import com.softline.service.settingCenter.user.IPageRegisterService;
import com.softline.service.system.IAuthorityService;
import com.softline.service.system.ICommonService;
import com.softline.util.MsgPage;

@Service("pageRegisterService")
public class PageRegisterService implements IPageRegisterService {
	@Autowired
	@Qualifier("pageRegisterDao")
	private IPageRegisterDao pageRegisterDao;
	
	@Resource(name= "modelRegisterService")
	private IModelRegisterService modelRegisterService;
	
	@Resource(name = "pageCodeService")
	private IPageCodeService pageCodeService;
	
	@Resource(name = "commonService")
	private ICommonService commonService;
	
	@Resource(name = "authorityService")
	private IAuthorityService authorityService;

	@Override
	public MsgPage getPageMsgRegisterList(String qPageNum, String qPageName, String qModelId, Integer curPageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = pageRegisterDao.getAllRowCount(qPageNum, qPageName, qModelId);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<HhPageregister> list = pageRegisterDao.getPageRegisterList(qPageNum, qPageName, qModelId, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}

	@Override
	public HhPageregister getThePageRegister(Integer id) {
		// TODO Auto-generated method stub
		return pageRegisterDao.getThePageRegister(id);
	}

	@Override
	public void saveOrUpdatePageRegister(HhPageregister register) {
		pageRegisterDao.saveOrUpdatePageRegister(register);
		//调用接口，将对应模块上注册的页面信息传给对应的模块
		Integer modelId = register.getModelId();
		HhModelRegister hhModelRegister = modelRegisterService.getTheModelRegister(modelId);
		String modelNum = hhModelRegister.getModelNum();
		if(modelNum.equals("bim_show")){
//			ShowWebserviceService service = new ShowWebserviceService();
//			IShowWebservice is = service.getPort(IShowWebservice.class);
//			ShowPage showPage = new ShowPage();
//			showPage.setPortalId(register.getId());
//			showPage.setIsDel(0);
//			showPage.setPageNum(register.getPageNum());
//			showPage.setPageName(register.getPageName());
//			showPage.setPageDescription(register.getPageDescription());
//			is.showUpdaterPage(showPage);
		}
		if(modelNum.equals("bim_task")){
//			TaskWebserviceService service = new TaskWebserviceService();
//			ITaskWebservice it = service.getPort(ITaskWebservice.class);
//			HhPage page = new HhPage();
//			page.setPortalId(register.getId());
//			page.setIsDel(0);
//			page.setPageNum(register.getPageNum());
//			page.setPageName(register.getPageName());
//			page.setPageDescription(register.getPageDescription());
//			it.updateHhPage(page);
		}
		if(modelNum.equals("bim_work")){
			HhPage page = new HhPage();
			page.setPortalId(register.getId());
			page.setIsDel(0);
			page.setPageNum(register.getPageNum());
			page.setPageName(register.getPageName());
			page.setPageDescription(register.getPageDescription());
			authorityService.updateHhPage(page);
		}
	}

	@Override
	public String deletePageRegister(Integer id) {
		//先查询此页面上是否注册功能
		List<PortalHhPagecode> list = pageCodeService.getCodeList( null, id);
		if (!list.isEmpty()) {
			return "false";
		}else {
			//删除此页面
			pageRegisterDao.deletePageRegister(id);
			//调用接口把页面保存到选择的对应模块中去
			HhPageregister register = (HhPageregister)commonService.findById(HhPageregister.class, id);
			Integer modelId = register.getModelId();
			HhModelRegister hhModelRegister = modelRegisterService.getTheModelRegister(modelId);
			String modelNum = hhModelRegister.getModelNum();
			if(modelNum.equals("bim_show")){
//				ShowWebserviceService service = new ShowWebserviceService();
//				IShowWebservice is = service.getPort(IShowWebservice.class);
//				ShowPage showPage = new ShowPage();
//				showPage.setPortalId(register.getId());
//				showPage.setIsDel(1);
//				showPage.setPageNum(register.getPageNum());
//				showPage.setPageName(register.getPageName());
//				showPage.setPageDescription(register.getPageDescription());
//				is.showUpdaterPage(showPage);
			}
			if(modelNum.equals("bim_task")){
//				TaskWebserviceService service = new TaskWebserviceService();
//				ITaskWebservice it = service.getPort(ITaskWebservice.class);
//				HhPage page = new HhPage();
//				page.setPortalId(register.getId());
//				page.setIsDel(1);
//				page.setPageNum(register.getPageNum());
//				page.setPageName(register.getPageName());
//				page.setPageDescription(register.getPageDescription());
//				it.updateHhPage(page);
			}
			if(modelNum.equals("bim_work")){
				HhPage page = new HhPage();
				page.setPortalId(register.getId());
				page.setIsDel(1);
				page.setPageNum(register.getPageNum());
				page.setPageName(register.getPageName());
				page.setPageDescription(register.getPageDescription());
				authorityService.updateHhPage(page);
			}
			return "success";
		}
		
	}

	@Override
	public List<HhPageregister> getRegistedList() {
		// TODO Auto-generated method stub
		return pageRegisterDao.getRegistedList();
	}

	@Override
	public List<HhPageregister> getPagesByModelId(Integer modelId) {
		// TODO Auto-generated method stub
		return pageRegisterDao.getPagesByModelId(modelId);
	}

	@Override
	public List<HhPageregister> getPagesByRoleId(Integer id) {
		// TODO Auto-generated method stub
		return pageRegisterDao.getPagesByRoleId(id);
	}

	@Override
	public List<HhPageregister> getRolePageListByModelIdAndRoleId(
			Integer modelId, Integer roleId) {
		// TODO Auto-generated method stub
		return pageRegisterDao.getRolePageListByModelIdAndRoleId(modelId, roleId);
	}

	@Override
	public Integer checkPageNum(String pageNum, Integer id) {
		// TODO Auto-generated method stub
		return pageRegisterDao.checkPageNum(pageNum, id);
	}
//	查看
	@Override
	public MsgPage getPageMsgPersonList(Integer modelId, String vcName,String vcAccount, Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage(); 
		//总记录数
        Integer totalRecords = pageRegisterDao.getPagePersonlistCount(modelId, vcName, vcAccount);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<Object[]> list = pageRegisterDao.getPagePersonlist(modelId, vcName, vcAccount, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}
	
	
//	导出
	@Override
	public XSSFWorkbook getPageMsgPersonListExport(Integer modelId,
			String vcName, String vcAccount) {
		List<Object[]> list = pageRegisterDao.getPagePersonlist(modelId, vcName, vcAccount, null, null);
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

}
