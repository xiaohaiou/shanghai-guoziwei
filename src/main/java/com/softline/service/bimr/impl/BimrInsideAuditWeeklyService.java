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

import com.softline.dao.bimr.IBimrInsideAuditWeeklyDao;
import com.softline.dao.system.ICommonDao;
import com.softline.entity.bimr.BimrInsideAuditProject;
import com.softline.entity.bimr.BimrInsideAuditWeekly;
import com.softline.service.bimr.IBimrInsideAuditWeeklyService;
import com.softline.service.system.ICommonService;
import com.softline.util.MsgPage;

@Service("iBimrInsideAuditWeelyService")
public class BimrInsideAuditWeeklyService implements IBimrInsideAuditWeeklyService {
   @Autowired
   @Qualifier("iBimrInsideAuditWeeklyDao")
   private IBimrInsideAuditWeeklyDao iBimrInsideAuditWeeklyDao;

   @Autowired
   @Qualifier("commonDao")
   private ICommonDao commonDao;

    @Autowired
    @Qualifier("commonService")
    private ICommonService commonService;


    @Override
    public MsgPage getBimrInsideAuditWeeklys(BimrInsideAuditWeekly entity, Integer curPageNum, Integer pageSize, String dataAuthority) {
        MsgPage msgPage = new MsgPage();
        //总记录数
        Integer totalRecords = iBimrInsideAuditWeeklyDao.getBimrInsideAuditWeeklyListCount(entity,dataAuthority);
        //当前页开始记录
        int offset = msgPage.countOffset(curPageNum,pageSize);
        //分页查询结果集
        List<BimrInsideAuditWeekly> list = iBimrInsideAuditWeeklyDao.getBimrInsideAuditWeeklyList(entity,offset,pageSize,dataAuthority);
        for (BimrInsideAuditWeekly biaw : list) {

        }
        msgPage.setPageNum(curPageNum);
        msgPage.setPageSize(pageSize);
        msgPage.setTotalRecords(totalRecords);
        msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
        msgPage.setList(list);
        return msgPage;
    }

    @Override
    public Integer saveBimrInsideAuditWeekly(BimrInsideAuditWeekly entity) {
        /*HhFile f = null;
        //保存附件
		if (file!= null && !file.isEmpty()) {
			if (entity.getFileOne() != null) {
				String uuid = entity.getFileOne().getFileUuid();
				Common.deleteFile(DES.report_path, uuid);
				commonDao.delete(entity.getFileOne());
			}
			f = commonService.saveFile(file, entity.getId(),
					Base.inspect_project, DES.report_path);
		}
		if (ret == 1 && f != null){
		    result = 1;
        }*/
        return iBimrInsideAuditWeeklyDao.saveBimrInsideAuditWeekly(entity);
    }

    @Override
    public void updateBimrInsideAuditWeekly(BimrInsideAuditWeekly entity) {
        iBimrInsideAuditWeeklyDao.updateBimrInsideAuditWeekly(entity);
    }

    @Override
    public void deleteBimrInsideAuditWeekly(Integer id) {
        iBimrInsideAuditWeeklyDao.deleteBimrInsideAuditWeekly(id);
    }

    @Override
    public BimrInsideAuditWeekly getBimrInsideAuditWeekly(Integer id) {
        return iBimrInsideAuditWeeklyDao.getBimrInsideAuditWeeklyById(id);
    }

    @Override
    public BimrInsideAuditWeekly getBimrInsideAuditWeekly(BimrInsideAuditWeekly entity) {
        return iBimrInsideAuditWeeklyDao.getBimrInsideAuditWeekly(entity);
    }

    @Override
    public List<BimrInsideAuditWeekly> getBimrInsideAuditWeeklyHasNoChild() {
        return iBimrInsideAuditWeeklyDao.getBimrInsideAuditWeeklyHasNoChild();
    }

    @Override
    public List<BimrInsideAuditWeekly> getBimrInsideAuditWeeklyForList(Integer status) {
        return null;
    }

	@Override
	public List<BimrInsideAuditWeekly> getBimrInsideAuditWeeklyList(
			int projectid) {
		// TODO Auto-generated method stub
		return iBimrInsideAuditWeeklyDao.getBimrInsideAuditWeeklyList(projectid);
	}
	
	@Override
	public List<Object[]> getInsideExportXM1(
			BimrInsideAuditWeekly entity, BimrInsideAuditProject entity1,String dataAuthority,String vcEmployeeId) {
		List<Object[]>  list=iBimrInsideAuditWeeklyDao.getInsideExport1(entity,entity1,dataAuthority,vcEmployeeId);
		return list;
	}

//	@Override
//	public XSSFWorkbook getInsideExportWorkbook3(
//			List<BimrInsideAuditWeekly> list1) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	
//	总监工作日报导出222
	
	@Override
	public XSSFWorkbook getInsideExportWorkbook3(List<Object[]> list1){
		
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
			 XSSFSheet sheet = workBook.createSheet("总监工作周报");
			 sheet.addMergedRegion(new CellRangeAddress(0,0,0,11));
			 XSSFRow row = sheet.createRow((int) 0);  
			 cell = row.createCell((short) 0);
			 font.setFontHeightInPoints((short) 15);
			 style.setFont(font);
			 style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中  
			 style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直  
			 cell.setCellStyle(style);
				cell.setCellValue("审计体系");
			 empArch3(list1,sheet,style, 1,font1);

		return workBook;
	}
//"判决/调解金额(人民币万元)","已执行款项(人民币万元)","避免/挽回经济损失(人民币万元)","案件编号","案件归属单位"
	public void empArch3(List<Object[]> list,XSSFSheet sheet, CellStyle style,int type,XSSFFont font1) {
		 XSSFCell cell = null;
		XSSFRow row=sheet.createRow((int)1);
		
	
		String[] header1 = {"序号","项目类别","项目性质","项目名称","项目状态"};
		 font1.setBold(true);
		 font1.setFontHeightInPoints((short) 12);
			 style.setFont(font1);
		 for (int i = 0; i < header1.length; i++) {
				cell = row.createCell((short) i);
				cell.setCellValue(header1[i]);
				cell.setCellStyle(style);
		}
		/* style.setFillForegroundColor(IndexedColors.RED.getIndex());  
	        style.setFillPattern(CellStyle.SOLID_FOREGROUND); */
//		 font1.setColor(IndexedColors.RED.index);
//		 style.setFont(font1);
		 
		    int i =0;
			for(Object obj : list) {
				Object[] item = (Object[])obj;
				
				int k=0;
				row=sheet.createRow((int) i +2);
				int isImportant = Integer.parseInt(String.valueOf(item[0]));
//				项目类别
				int auditProjectProp=Integer.parseInt(String.valueOf(item[1]));
//				项目性质
				String projectName = (String)item[2];
//				项目名称
				int projectStatusProgress=0;
//				项目状态
				//System.out.println(item[1]);
				if(null==item[3]){
					projectStatusProgress=0;
				}else {
				    projectStatusProgress =Integer.parseInt(String.valueOf(item[3]));
				}

				

			
				row.createCell(k).setCellValue((i+1)+"");k++; //序号
//				
				if (isImportant==0) {
					row.createCell((short)k++).setCellValue(String.valueOf("年度计划内项目"));
				}else if (isImportant==1) {
					row.createCell((short)k++).setCellValue(String.valueOf("年度计划外项目"));
				}else if (isImportant==2) {
					row.createCell((short)k++).setCellValue(String.valueOf("上级交办项目"));
				}
				
				if (auditProjectProp==80027) {
					row.createCell((short)k++).setCellValue("风险导向设计");
				}else if (auditProjectProp==80028) {
					row.createCell((short)k++).setCellValue("风险管理设计");
				}else if (auditProjectProp==80029) {
					row.createCell((short)k++).setCellValue("内部控制评价");
				}else if (auditProjectProp==80030) {
					row.createCell((short)k++).setCellValue("经济责任审计");
				}else if (auditProjectProp==80031) {
					row.createCell((short)k++).setCellValue("企业巡视");
				}else {
					row.createCell((short)k++).setCellValue("调研管理");
				}
				

				row.createCell((short)k++).setCellValue(projectName);

				if (projectStatusProgress==81080) {
					row.createCell((short)k++).setCellValue(String.valueOf("审计立项"));
				}else if (projectStatusProgress==81081) {
					row.createCell((short)k++).setCellValue(String.valueOf("发出审计通知"));
				}else if (projectStatusProgress==81082) {
					row.createCell((short)k++).setCellValue(String.valueOf("审前调查中"));
				}else if (projectStatusProgress==81083) {
					row.createCell((short)k++).setCellValue(String.valueOf("现场实施中"));
				}else if (projectStatusProgress==81084) {
					row.createCell((short)k++).setCellValue(String.valueOf("撰写审计报告中"));
				}else if (projectStatusProgress==81085) {
					row.createCell((short)k++).setCellValue(String.valueOf("审计报告初稿审阅中"));
				}else if (projectStatusProgress==81086) {
					row.createCell((short)k++).setCellValue(String.valueOf("审计报告征求意见中"));
				}else if (projectStatusProgress==81087) {
					row.createCell((short)k++).setCellValue(String.valueOf("审计报告公文流转中"));
				}else if (projectStatusProgress==81088) {
					row.createCell((short)k++).setCellValue(String.valueOf("审计报告公文审批结束"));
				}else if (projectStatusProgress==81089) {
					row.createCell((short)k++).setCellValue(String.valueOf("补充审计或审计延伸"));
				}else if (projectStatusProgress==810810) {
					row.createCell((short)k++).setCellValue(String.valueOf("整改方案制定中"));
				}else if (projectStatusProgress==810811) {
					row.createCell((short)k++).setCellValue(String.valueOf("整改落实中"));
				}else if (projectStatusProgress==810812) {
					row.createCell((short)k++).setCellValue(String.valueOf("整改完成"));
				}
				
				
				

				

				i++;
				
				
				
			}
		 
		 
		 
		 

	}
	
	
	
	
	
}
