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

import com.softline.dao.settingCenter.user.IModelRegisterDao;
import com.softline.entity.settingCenter.HhModelRegister;
import com.softline.entity.settingCenter.HhPageregister;
import com.softline.service.settingCenter.user.IModelRegisterService;
import com.softline.service.settingCenter.user.IPageRegisterService;
import com.softline.service.settingCenter.user.ISysRegisterService;
import com.softline.util.MsgPage;

@Service("modelRegisterService")
public class ModelRegisterService implements IModelRegisterService {
	@Autowired
	@Qualifier("modelRegisterDao")
	private IModelRegisterDao modelRegisterDao;
	
	@Resource(name = "sysRegisterService")
	private ISysRegisterService sysRegisterService;
	
	@Resource(name = "pageRegisterService")
	private IPageRegisterService pageRegisterService;

	@Override
	public MsgPage getModelMsgRegisterList(String qModelNum, String qModelName, String qSysId, Integer curPageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = modelRegisterDao.getAllRowCount(qModelNum, qModelName, qSysId);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<HhModelRegister> list = modelRegisterDao.getModelRegisterList(qModelNum, qModelName, qSysId, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}

	@Override
	public HhModelRegister getTheModelRegister(Integer id) {
		// TODO Auto-generated method stub
		return modelRegisterDao.getTheModelRegister(id);
	}

	@Override
	public void saveOrUpdateModelRegister(HhModelRegister register) {
		modelRegisterDao.saveOrUpdateModelRegister(register);
	}

	@Override
	public String deleteModelRegister(Integer id) {
		// TODO Auto-generated method stub
		//先查询此页面上是否注册功能
		List<HhPageregister> list = pageRegisterService.getPagesByModelId(id);
		if (!list.isEmpty()) {
			return "false";
		}else {
			//删除此模板
			modelRegisterDao.deleteModelRegister(id);
			return "success";
		}
	}

	@Override
	public List<HhModelRegister> getRegistedList() {
		// TODO Auto-generated method stub
		return modelRegisterDao.getRegistedList();
	}

	@Override
	public List<HhModelRegister> getModelsBySysId(Integer sysId) {
		// TODO Auto-generated method stub
		return modelRegisterDao.getModelsBySysId(sysId);
	}

	@Override
	public List<HhModelRegister> getModelsByRoleId(Integer id) {
		// TODO Auto-generated method stub
		return modelRegisterDao.getModelsByRoleId(id);
	}

	@Override
	public Integer checkModelNum(String modelNum, Integer id) {
		// TODO Auto-generated method stub
		return modelRegisterDao.checkModelNum(modelNum, id);
	}

	@Override
	public List<HhModelRegister> getRegistedModelList(Integer roleId) {
		// TODO Auto-generated method stub
		return modelRegisterDao.getRegistedModelList(roleId);
	}

	@Override
	public List<HhModelRegister> getRegistedModelListBySysId(Integer sysId) {
		// TODO Auto-generated method stub
		return modelRegisterDao.getRegistedModelListBySysId(sysId);
	}

	@Override
	public List<HhModelRegister> getRoleModelListBySysIdAndRoleId(
			Integer sysId, Integer roleId) {
		// TODO Auto-generated method stub
		return modelRegisterDao.getRoleModelListBySysIdAndRoleId(sysId, roleId);
	}

	@Override
	public MsgPage getModelMsgPersonList(Integer modelId, String vcName, String vcAccount, Integer curPageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = modelRegisterDao.getModelPersonlistCount(modelId, vcName, vcAccount);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<Object[]> list = modelRegisterDao.getModelPersonlist(modelId, vcName, vcAccount, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}

	@Override
	public XSSFWorkbook getModelMsgPersonListExport(Integer modelId,
			String vcName, String vcAccount) {
		// TODO Auto-generated method stub
		List<Object[]> list = modelRegisterDao.getModelPersonlist(modelId, vcName, vcAccount, null, null);
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
