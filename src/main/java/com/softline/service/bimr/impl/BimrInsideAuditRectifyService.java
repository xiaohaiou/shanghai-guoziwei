package com.softline.service.bimr.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.jdt.internal.compiler.problem.ProblemSeverities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.softline.common.Base;
import com.softline.dao.bimr.IBimrInsideAuditRectifyDao;
import com.softline.dao.system.ICommonDao;
import com.softline.entity.HhUsers;
import com.softline.entity.bimr.BimrCivilcaseWeek;
import com.softline.entity.bimr.BimrInsideAuditFeedback;
import com.softline.entity.bimr.BimrInsideAuditMeasures;
import com.softline.entity.bimr.BimrInsideAuditProject;
import com.softline.entity.bimr.BimrInsideAuditQuestion;
import com.softline.entity.bimr.BimrInsideAuditRectify;
import com.softline.service.bimr.IBimrInsideAuditRectifyService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IPortalMsgService;
import com.softline.util.MsgPage;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

@Service("bimrInsideAuditRectifyService")
public class BimrInsideAuditRectifyService implements IBimrInsideAuditRectifyService {

    @Autowired
    @Qualifier("bimrInsideAuditRectifyDao")
    private IBimrInsideAuditRectifyDao bimrInsideAuditRectifyDao;
    
    @Autowired
    @Qualifier("commonDao")
    private ICommonDao commonDao;

	@Resource(name = "commonService")
	private ICommonService commonService;
	
	@Resource(name = "examineService")
	private IExamineService examineService;	
	
	@Resource(name = "potalMsgService")
	private IPortalMsgService potalMsgService;	

    @Override
    public MsgPage getBimrInsideRectify(BimrInsideAuditRectify entity, Integer curPageNum, Integer pageSize, String dataAuthority) {
        MsgPage msgPage = new MsgPage();
        //总记录数
        Integer totalRecords = bimrInsideAuditRectifyDao.getBimrInsideRectifyListCount(entity,dataAuthority);
        //当前页开始记录
        int offset = msgPage.countOffset(curPageNum,pageSize);
        //分页查询结果集
        List<BimrInsideAuditRectify> list = bimrInsideAuditRectifyDao.getBimrInsideRectifyList(entity,offset,pageSize,dataAuthority);
        msgPage.setPageNum(curPageNum);
        msgPage.setPageSize(pageSize);
        msgPage.setTotalRecords(totalRecords);
        msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
        msgPage.setList(list);
        return msgPage;
    }
    
    @Override
	public List<BimrInsideAuditRectify> getBimrInsideRectifyQuestionList(
			int projectId) {
		// TODO Auto-generated method stub
    	 List<BimrInsideAuditRectify> list = bimrInsideAuditRectifyDao.getBimrInsideRectifyQuestionList(projectId);
		return list;
	}
    @Override
    public void saveBimrInsideRectify(BimrInsideAuditRectify entity, String[] cs, String[] csTime) {
        Integer res =  bimrInsideAuditRectifyDao.saveBimrInsideRectifyQuestion(entity);
        if(cs != null){
        	for(int i =0;i<cs.length;i++){
        		String measure = cs[i];
        		String measureTime = csTime[i];
        		BimrInsideAuditMeasures measures = new BimrInsideAuditMeasures();
        		measures.setProjectId(entity.getProjectId());
        		measures.setRectifyId(entity.getId());
        		measures.setRectifyMeasure(measure);
        		measures.setRectifyMeasureTime(measureTime);
        		measures.setStatus("1");
        		commonDao.saveOrUpdate(measures);
        	}
        }
    }

    @Override
    public void updateBimrInsideRectify(BimrInsideAuditRectify entity, String[] cs, String[] csTime) {
    	bimrInsideAuditRectifyDao.updateBimrInsideRectifyQuestion(entity);
    	//删除历史记录，然后新增
    	bimrInsideAuditRectifyDao.deleteBimrInsideMesaures(entity.getId());
        if(cs != null){
        	for(int i =0;i<cs.length;i++){
        		String measure = cs[i];
        		String measureTime = csTime[i];
        		BimrInsideAuditMeasures measures = new BimrInsideAuditMeasures();
        		measures.setProjectId(entity.getProjectId());
        		measures.setRectifyId(entity.getId());
        		measures.setRectifyMeasure(measure);
        		measures.setRectifyMeasureTime(measureTime);
        		measures.setStatus("1");
        		commonDao.saveOrUpdate(measures);
        	}
        }
    }

    @Override
    public void deleteBimrInsideRectifyQuestion(Integer id) {
    	bimrInsideAuditRectifyDao.deleteBimrInsideRectifyQuestion(id);
    }

    @Override
    public BimrInsideAuditRectify getBimrInsideRectify(Integer id) {
        return bimrInsideAuditRectifyDao.getBimrInsideRectifyById(id);
    }

    @Override
    public BimrInsideAuditRectify getBimrInsideRectifyQuestion(BimrInsideAuditRectify entity) {
        return bimrInsideAuditRectifyDao.getBimrInsideRectifyQuestion(entity);
    }

    @Override
    public List<BimrInsideAuditRectify> getBimrInsideRectifyQuestionHasNoChild() {
        return bimrInsideAuditRectifyDao.getBimrInsideRectifyQuestionHasNoChild();
    }

	@Override
	public BimrInsideAuditRectify getRectifyQuestionByAnswerId(Integer answerId) {
		return bimrInsideAuditRectifyDao.getRectifyQuestionByAnswerId(answerId);
	}
	
	@Override
	public List<BimrInsideAuditMeasures> getBimrInsideRectifyMeasures(Integer rectifyId){
		return bimrInsideAuditRectifyDao.getBimrInsideRectifyMeasures(rectifyId);
	}
	
	@Override
	public void saveFeedBack(String value){
		JSONArray json = JSONArray.parseArray(value);
		if(json.size() > 0){
			String feedbackDate = json.get(0).toString();
			String[] vals = json.get(1).toString().split(";-;");
			String rectifyId = vals[4]==""?"0":vals[4];
			//删除重复月份的跟进记录
			bimrInsideAuditRectifyDao.deleteBimrInsideFeedbackByMonth(feedbackDate.substring(0,7),rectifyId);
	    	for(int i=1;i<json.size();i++){
	    		Object obj = json.get(i);
	    		String[] val = obj.toString().split(";-;");
	    		//更新措施状态
	    		String state = val[3];
	    		String id = val[0];
	    		bimrInsideAuditRectifyDao.updateBimrInsideMeasuresStateById(id, state);
	    		//新增跟踪反馈
	    		BimrInsideAuditFeedback fd = new BimrInsideAuditFeedback();
	    		fd.setMeasuresId(Integer.parseInt(val[0]==""?"0":val[0]));
	    		fd.setFeedbackDate(feedbackDate);
	    		fd.setMeasuresDesc(val[1]);
	    		fd.setFeedbackDesc(val[2]);
	    		fd.setStatus(val[3]);
	    		fd.setRectifyId(Integer.parseInt(val[4]==""?"0":val[4]));
	    		commonDao.saveOrUpdate(fd);
	    	}
		}
	}
	
	@Override
	public MsgPage getFeedbackMsg(String rectifyId, Integer curPageNum, Integer pageSize){
		MsgPage msgPage = new MsgPage();
        //总记录数
        Integer totalRecords = bimrInsideAuditRectifyDao.getFeedbackMsgCount(rectifyId);
        //当前页开始记录
        int offset = msgPage.countOffset(curPageNum,pageSize);
        //分页查询结果集
        List<BimrInsideAuditFeedback> list = bimrInsideAuditRectifyDao.getFeedbackMsgList(rectifyId,offset,pageSize);
        msgPage.setPageNum(curPageNum);
        msgPage.setPageSize(pageSize);
        msgPage.setTotalRecords(totalRecords);
        msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
        msgPage.setList(list);
        return msgPage;
	}
	
	@Override
	public BimrInsideAuditRectify examineInsideProjectRectifyFeedBack(Integer id, String examStr, Integer examResult, HhUsers user){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		BimrInsideAuditRectify rectify = bimrInsideAuditRectifyDao.getBimrInsideRectifyById(id);
		if(examResult.toString().equals("50115")){
			rectify.setStatus("4");
		}
		if(examResult.toString().equals("50114")){
			rectify.setStatus("2");
		}
		rectify.setAuditorPersonId(user.getVcEmployeeId());
		rectify.setAuditorPersonName(user.getVcFullName());
		rectify.setAuditorDate(df.format(new Date()));
		commonService.saveOrUpdate(rectify);
		examineService.saveExamine(rectify.getId(), Base.examkind_insideProjectRectifyFeedBack, user, examStr, examResult);
		potalMsgService.updatePortalMsg("bimWork_bimr_insideProjectRectify", rectify.getId().toString());
		return rectify;
	}
	
	@Override
	public List<BimrInsideAuditFeedback> getBimrInsideAuditFeedbackByRectifyId(Integer rectifyId){
		return bimrInsideAuditRectifyDao.getBimrInsideAuditFeedbackByRectifyId(rectifyId.toString());
	}

	@Override
	public List<BimrInsideAuditRectify> getRectifyQuestionByAnswerIdList(
			Integer answerId) {
		// TODO Auto-generated method stub
		return bimrInsideAuditRectifyDao.getRectifyQuestionByAnswerIdList(answerId);
	}
//时点维度
	@Override
	public List<Object[]> getTimeDimension(BimrInsideAuditRectify entity,BimrInsideAuditMeasures entity1,BimrInsideAuditProject entity2,BimrInsideAuditQuestion entity4, String dataAuthority,String vcEmployeeId) {
		// TODO Auto-generated method stub
		
		List<Object[]> list=bimrInsideAuditRectifyDao.getTimeDimensionExport(entity,entity1,entity2,entity4,dataAuthority,vcEmployeeId);
		return list;

	}
	
//	项目维度
	
	public  List<Object[]>  getProjectDimension(BimrInsideAuditRectify entity,BimrInsideAuditMeasures entity1,BimrInsideAuditProject entity2,BimrInsideAuditFeedback entity3,BimrInsideAuditQuestion entity4, String dataAuthority,String vcEmployeeId){
		
		List<Object[]>  list =bimrInsideAuditRectifyDao.getProjectDimensionExport(entity, entity1, entity2,entity3, entity4,dataAuthority, vcEmployeeId);
		
		return  list;
	}
//审计整改验收单 时点维度导出getTimeDimensionExport   getProjectDimensionExport
	@Override
	public XSSFWorkbook getTimeDimensionWorkbook1(List<Object[]> list1) {
		// TODO Auto-generated method stub
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
			 XSSFSheet sheet = workBook.createSheet("审计整改验收单（单一时点维度）");
			 sheet.addMergedRegion(new CellRangeAddress(0,0,0,5));
			 XSSFRow row = sheet.createRow((int) 0);  
			 cell = row.createCell((short) 0);
			 font.setFontHeightInPoints((short) 15);
			 style.setFont(font);
			 style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中  
			 style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直  
			 cell.setCellStyle(style);
				
				cell.setCellValue("审计项目整改验收单");
//				
			 empArch3(list1,sheet,style,1,font1);

		return workBook;
	}
//
	public void empArch3(List<Object[]> list,XSSFSheet sheet, CellStyle style,int type,XSSFFont font1) {
		 XSSFCell cell = null;
		XSSFRow row=sheet.createRow((int)1);
		
		
		String[] header1 = {"序号","审计项目编号","审计项目名称","存在问题","审计建议","整改措施","整改负责人","整改时限","整改验收情况"};
//		整改反馈就是本月反馈feedbackDesc
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
				String auditProjectCode=(String) item[0];
				String auditProjectName=(String) item[1];
				String problem=(String) item[2];
				String rectifyAdvice=(String) item[3];
				String rectifyMeasure=(String) item[4];
				String rectifyResponseName=(String) item[5];
				String rectifyTime=(String) item[6];
				String status=(String) item[7];
//				String feedbackDesc=(String) item[6];
				


				

			
				row.createCell(k).setCellValue((i+1)+"");k++; //序号
				row.createCell((short)k++).setCellValue(auditProjectCode);
//				审计项目编号
				row.createCell((short)k++).setCellValue(auditProjectName);
//				审计项目名称
				row.createCell((short)k++).setCellValue(problem);
				row.createCell((short)k++).setCellValue(rectifyAdvice);
				row.createCell((short)k++).setCellValue(rectifyMeasure);
				row.createCell((short)k++).setCellValue(rectifyResponseName);
				row.createCell((short)k++).setCellValue(rectifyTime);

				if (status.equals("1")) {
					row.createCell((short)k++).setCellValue("待整改");
				}else if (status.equals("2")) {
					row.createCell((short)k++).setCellValue("整改中");
				}else if (status.equals("3")) {
					row.createCell((short)k++).setCellValue("整改完成待审核");
				}else if (status.equals("4")) {
					row.createCell((short)k++).setCellValue("整改完成");
				}else {
					row.createCell((short)k++).setCellValue("");
				}
				
//				row.createCell((short)k++).setCellValue(feedbackDesc);
				i++;
				 
			}
		 
		
	}
//	审计整改验收单项目维度
	@Override
	public XSSFWorkbook getProjectDimensionWorkbook1(List<Object[]> list1) {
		
		// TODO Auto-generated method stub
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
			 XSSFSheet sheet = workBook.createSheet("审计整改验收单（单一项目维度）");
			 sheet.addMergedRegion(new CellRangeAddress(0,0,0,5));
			 XSSFRow row = sheet.createRow((int) 0);  
			 cell = row.createCell((short) 0);
			 font.setFontHeightInPoints((short) 15);
			 style.setFont(font);
			 style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中  
			 style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直  

			 cell.setCellStyle(style);

			 
			 
//				auditProjectName  项目名字
			
			 empArch4(list1,sheet,style,1,font1);
			 cell.setCellValue("审计项目整改验收单");

		return workBook;
	}
//
	public void empArch4(List<Object[]> list,XSSFSheet sheet, CellStyle style,int type,XSSFFont font1) {
		 XSSFCell cell = null;
	
		XSSFRow row=sheet.createRow((int)1);
		
	
		String[] header1 = {"序号","存在问题","审计建议","整改措施","整改负责人","整改时限","整改验收情况","整改反馈"};
		 font1.setBold(true);
		 font1.setFontHeightInPoints((short) 12);
			 style.setFont(font1);
		 for (int i = 0; i < header1.length; i++) {
				cell = row.createCell((short) i);
				cell.setCellValue(header1[i]);
				cell.setCellStyle(style);
		}
//		 style.setFillForegroundColor(IndexedColors.RED.getIndex());  
//	        style.setFillPattern(CellStyle.SOLID_FOREGROUND); 
//		 font1.setColor(IndexedColors.RED.index);
//		 style.setFont(font1);
		 
		    int i =0;
		    int count=1;
			for(Object obj : list) {
				Object[] item = (Object[])obj;
				
				int k=0;
				row=sheet.createRow((int) i +2);
				
//				project_name  rectify_advice  
//				rectify_measure  rectify_response_name 
//				rectify_time  status
//				String auditProjectCode=(String) item[0];
//				String auditProjectName=(String) item[1];
				String problem=(String) item[0];
				String rectifyAdvice=(String) item[1];
				String rectifyMeasure=(String) item[2];
				String rectifyResponseName=(String) item[3];
				String rectifyTime=(String) item[4];
				String status=(String) item[5];
				String feedbackDesc=(String) item[6];
				
//				int isImportant = Integer.parseInt(String.valueOf(item[0]));
//				String projectName = (String)item[1];
//				int projectStatusProgress=0;
//				//System.out.println(item[1]);
//				if(null==item[2]){
//					projectStatusProgress=0;
//				}else {
//				    projectStatusProgress =Integer.parseInt(String.valueOf(item[2]));
//				}

				

			
				row.createCell(k).setCellValue((i+1)+"");k++; //序号
				
				
				
				if (item[0]!=null) {
					row.createCell((short)k++).setCellValue(problem);
				}else {
					row.createCell((short)k++).setCellValue("");
				}
				
				if (item[1]!=null) {
					row.createCell((short)k++).setCellValue(rectifyAdvice);
				}else {
					row.createCell((short)k++).setCellValue("");
				}
				if (item[2]!=null) {
					row.createCell((short)k++).setCellValue(rectifyMeasure);
//				+"  "+(count++)+"个"
				}else {
					row.createCell((short)k++).setCellValue("");
				}
				
				
				if (item[3]!=null) {
					row.createCell((short)k++).setCellValue(rectifyResponseName);
				}else {
					row.createCell((short)k++).setCellValue("");
				}
				
				if (item[4]!=null) {
					row.createCell((short)k++).setCellValue(rectifyTime);
				}else {
					row.createCell((short)k++).setCellValue("");
				}
				
				
//				row.createCell((short)k++).setCellValue(status);
				if (item[5]!=null) {
					
				
				if (status.equals("1")) {
					row.createCell((short)k++).setCellValue("待整改");
				}else if (status.equals("2")) {
					row.createCell((short)k++).setCellValue("整改中");
				}else if (status.equals("3")) {
					row.createCell((short)k++).setCellValue("整改完成待审核");
				}else if (status.equals("4")) {
					row.createCell((short)k++).setCellValue("整改完成");
				}else {
					row.createCell((short)k++).setCellValue("");
				}
		
				}
				
				
				if (feedbackDesc!=null) {
					row.createCell((short)k++).setCellValue(feedbackDesc);
				}else {
					row.createCell((short)k++).setCellValue("");
				}
				

				i++;
				
				
				
			}
	}

	
}
