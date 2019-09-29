package com.softline.service.bimr.impl;

import java.util.List;

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

import com.softline.dao.bimr.IBimrInsideAuditQuestionDao;
import com.softline.entity.bimr.BimrInsideAuditMeasures;
import com.softline.entity.bimr.BimrInsideAuditProject;
import com.softline.entity.bimr.BimrInsideAuditQuestion;
import com.softline.entity.bimr.BimrInsideAuditRectify;
import com.softline.service.bimr.IBimrInsideAuditQuestionService;
import com.softline.util.MsgPage;

@Service("iBimrInsideAuditQuestionService")
public class BimrInsideAuditQuestionService implements IBimrInsideAuditQuestionService {

    @Autowired
    @Qualifier("iBimrInsideAuditQuestionDao")
    private IBimrInsideAuditQuestionDao iBimrInsideAuditQuestionDao;

    @Override
    public MsgPage getBimrInsideAuditQuestionts(BimrInsideAuditQuestion entity, Integer curPageNum, Integer pageSize, String dataAuthority) {
        MsgPage msgPage = new MsgPage();
        //总记录数
        Integer totalRecords = iBimrInsideAuditQuestionDao.getBimrInsideAuditQuestionListCount(entity,dataAuthority);
        //当前页开始记录
        int offset = msgPage.countOffset(curPageNum,pageSize);
        //分页查询结果集
        List<BimrInsideAuditQuestion> list = iBimrInsideAuditQuestionDao.getBimrInsideAuditQuestionList(entity,offset,pageSize,dataAuthority);
        for (BimrInsideAuditQuestion biap : list) {

        }
        msgPage.setPageNum(curPageNum);
        msgPage.setPageSize(pageSize);
        msgPage.setTotalRecords(totalRecords);
        msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
        msgPage.setList(list);
        return msgPage;
    }

    @Override
    public Integer saveBimrInsideAuditQuestion(BimrInsideAuditQuestion entity) {
        return iBimrInsideAuditQuestionDao.saveBimrInsideAuditQuestion(entity);
    }

    @Override
    public void updateBimrInsideAuditQuestion(BimrInsideAuditQuestion entity) {
        iBimrInsideAuditQuestionDao.updateBimrInsideAuditQuestion(entity);
    }

    @Override
    public void deleteBimrInsideAuditQuestion(Integer id) {
        iBimrInsideAuditQuestionDao.deleteBimrInsideAuditQuestion(id);
    }

    @Override
    public BimrInsideAuditQuestion getBimrInsideAuditQuestion(Integer id) {
        return iBimrInsideAuditQuestionDao.getBimrInsideAuditQuestionById(id);
    }

    @Override
    public BimrInsideAuditQuestion getBimrInsideAuditQuestion(BimrInsideAuditQuestion entity) {
        return iBimrInsideAuditQuestionDao.getBimrInsideAuditQuestion(entity);
    }

    @Override
    public List<BimrInsideAuditQuestion> getBimrInsideAuditQuestionHasNoChild() {
        return iBimrInsideAuditQuestionDao.getBimrInsideAuditQuestionHasNoChild();
    }

    @Override
    public List<BimrInsideAuditQuestion> getBimrInsideAuditQuestionForList(Integer projectId,String rectifyTrackId) {
        return iBimrInsideAuditQuestionDao.getBimrInsideAuditQuestionForList(projectId,rectifyTrackId);
    }
    
    @Override
    public MsgPage getUnFinishListView(String yearMonth, BimrInsideAuditProject entity, Integer pageNum, Integer pageSize,String dataAuthority,String vcEmployeeId) {
        MsgPage msgPage = new MsgPage();
        Integer totalRecords = iBimrInsideAuditQuestionDao.getUnFinishListCount(yearMonth, entity, dataAuthority, vcEmployeeId);
        int offset = msgPage.countOffset(pageNum, pageSize);
        List<Object[]> list = iBimrInsideAuditQuestionDao.getUnFinishList(yearMonth, entity, offset, pageSize, dataAuthority, vcEmployeeId);
        msgPage.setPageNum(pageNum);
        msgPage.setPageSize(pageSize);
        msgPage.setTotalRecords(totalRecords);
        msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
        msgPage.setList(list);
        return msgPage;
    }

	@Override
	public MsgPage getQuestionAnalyzeListView(String yearMonth,
			BimrInsideAuditProject entity, Integer pageNum, Integer pageSize,String dataAuthority, String vcEmployeeId) {
		
		 MsgPage msgPage = new MsgPage();
	     Integer totalRecords = iBimrInsideAuditQuestionDao.getQuestionAnalyzeListCount(yearMonth, entity,dataAuthority,vcEmployeeId);
	     int offset = msgPage.countOffset(pageNum, pageSize);
	     List<Object[]> list = iBimrInsideAuditQuestionDao.getQuestionAnalyzeList(yearMonth, entity, offset, pageSize,dataAuthority,vcEmployeeId);
	     msgPage.setPageNum(pageNum);
	     msgPage.setPageSize(pageSize);
	     msgPage.setTotalRecords(totalRecords);
	     msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
	     msgPage.setList(list);
	     return msgPage;
	}
//导出的查询条件
	@Override
	public List<Object[]> getUnfinishExport(BimrInsideAuditProject entity,BimrInsideAuditQuestion entity1,BimrInsideAuditRectify entity2,BimrInsideAuditMeasures entity3,String yearMonth,
			String dataAuthority, String vcEmployeeId) {
		// TODO Auto-generated method stub
		List<Object[]> list =iBimrInsideAuditQuestionDao.getUnfinishExport(entity,entity1,entity2,entity3,yearMonth,dataAuthority,vcEmployeeId);
		return list;

	}
//
	@Override
	public XSSFWorkbook getgetUnfinishExportWorkbook1(List<Object[]> list1) {
		XSSFWorkbook workBook = new XSSFWorkbook();
		   CellStyle style=workBook.createCellStyle();
		   CellStyle style1=workBook.createCellStyle();
		   XSSFFont font = workBook.createFont();
		   XSSFFont font1 = workBook.createFont();
		   font.setBold(true);
//			 创建一个工作簿
			 XSSFCell cell = null;
			 int count=0;
//			 设置单元格为空
			 XSSFSheet sheet = workBook.createSheet("未完成审计项目发现问题查询");
			 sheet.addMergedRegion(new CellRangeAddress(0,0,0,5));
			 XSSFRow row = sheet.createRow((int) 0);  
			 cell = row.createCell((short) 0);
			 font.setFontHeightInPoints((short) 15);
			 style.setFont(font);
			 style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中  
			 style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直  
			 cell.setCellStyle(style);
				cell.setCellValue("未完成审计项目发现问题查询");
			 empArch6(list1,sheet,style,1,font1);

		return workBook;
	}
//
	public void empArch6(List<Object[]> list,XSSFSheet sheet, CellStyle style,int type,XSSFFont font1) {
		 XSSFCell cell = null;
		XSSFRow row=sheet.createRow((int)1);
		
	/**
	 * 序号	审计项目	项目所属核心单位	发现问题数	当期应完成整改措施数	当期已完成整改措施数	完成率

	 */
		String[] header1 = {"序号","审计项目","项目所属核心单位","发现问题数","当期应完成整改措施数","当期已完成整改措施数","完成率%"};
		 font1.setBold(true);
		 font1.setFontHeightInPoints((short) 12);
			 style.setFont(font1);
		 for (int i = 0; i < header1.length; i++) {
				cell = row.createCell((short) i);
				cell.setCellValue(header1[i]);
				cell.setCellStyle(style);
		}
		 
		 int i =0;
		    int count=1;
			for(Object obj : list) {
				Object[] item = (Object[])obj;
				
				int k=0;
				row=sheet.createRow((int) i +2);
				
//				project_name  rectify_advice  
//				rectify_measure  rectify_response_name 
//				rectify_time  status
				String AuditProjectName=(String) item[1];
				String AuditDeptedName=(String) item[2];
				int findQuestion=Integer.parseInt(String.valueOf(item[3]));
				int finishModify=Integer.parseInt(String.valueOf(item[4]));
				int finish=Integer.parseInt(String.valueOf(item[5]));
//				Double completion=Double.parseDouble(String.valueOf(item[6]));
				
				String  completion=String.valueOf(item[6]);
				
				row.createCell(k).setCellValue((i+1)+"");k++; //序号
				row.createCell((short)k++).setCellValue(AuditProjectName);   //审计 项目名称
				row.createCell((short)k++).setCellValue(AuditDeptedName);	//项目所属核心单位
				row.createCell((short)k++).setCellValue(findQuestion);		//发现问题数
				row.createCell((short)k++).setCellValue(finishModify);		//当期应完成整改措施数
				row.createCell((short)k++).setCellValue(finish);	
				if (item[6]!=null) {
					row.createCell((short)k++).setCellValue(completion);
				}else {
					row.createCell((short)k++).setCellValue("0");
				}
				//完成率
				
				
				i++;
		 
		 
		 
//		 for (int i = 0; i < list.size(); i++) {
//				int k=0;
//				row=sheet.createRow((int) i +2);
//				BimrInsideAuditProject  value=list.get(i);
////				任职单位personCompany任职职务personPost happenDate发生时间	
////				reason问责原因	建议问责落实情况	proposal处分问责呈报公文编号bumfNum处分问责办文编号articleNum
//				row.createCell(k).setCellValue((i+1)+"");k++; //序号
//				
//				row.createCell((short)k++).setCellValue(value.getAuditProjectName());
////				审计项目名称1
//				row.createCell((short)k++).setCellValue(value.getAuditDeptedName());
////				项目所属核心单位2
//				row.createCell((short)k++).setCellValue("");
////				发现问题数3
//				
//				row.createCell((short)k++).setCellValue("");
////				当期应完成整改措施数4
//				
//				row.createCell((short)k++).setCellValue("");
////				完成率5
//				
//
//			
//				
//
//		 
//	}
			}
	}
    
	
}
