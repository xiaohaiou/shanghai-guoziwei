package com.softline.service.bimr;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.softline.entity.HhUsers;
import com.softline.entity.ReportPersonalloanNearweek;
import com.softline.entity.ReportPersonlloaninfoNew;
import com.softline.entity.bimr.BimrInsideAuditFeedback;
import com.softline.entity.bimr.BimrInsideAuditMeasures;
import com.softline.entity.bimr.BimrInsideAuditProject;
import com.softline.entity.bimr.BimrInsideAuditQuestion;
import com.softline.entity.bimr.BimrInsideAuditRectify;
import com.softline.util.MsgPage;

public interface IBimrInsideAuditRectifyService {
	
    public MsgPage getBimrInsideRectify(BimrInsideAuditRectify entity, Integer curPageNum, Integer pageSize, String dataAuthority);
    
    public List<BimrInsideAuditRectify> getBimrInsideRectifyQuestionList(int projectId);

    public void saveBimrInsideRectify(BimrInsideAuditRectify entity, String[] cs, String[] csTime);

    public void updateBimrInsideRectify(BimrInsideAuditRectify entity, String[] cs, String[] csTime);

    public void deleteBimrInsideRectifyQuestion(Integer id);

    public BimrInsideAuditRectify getBimrInsideRectify(Integer id);

    public BimrInsideAuditRectify getBimrInsideRectifyQuestion(BimrInsideAuditRectify entity);

    public List<BimrInsideAuditRectify> getBimrInsideRectifyQuestionHasNoChild();
    
    public BimrInsideAuditRectify getRectifyQuestionByAnswerId(Integer answerId);
    
    public List<BimrInsideAuditRectify> getRectifyQuestionByAnswerIdList(Integer answerId);
    
    public List<BimrInsideAuditMeasures> getBimrInsideRectifyMeasures(Integer rectifyId);
    
    public void saveFeedBack(String value);
    
    public MsgPage getFeedbackMsg(String rectifyId, Integer curPageNum, Integer pageSize);
    
    public BimrInsideAuditRectify examineInsideProjectRectifyFeedBack(Integer id, String examStr, Integer examResult, HhUsers user);

    public List<BimrInsideAuditFeedback> getBimrInsideAuditFeedbackByRectifyId(Integer rectifyId);

	public List<Object[]> getTimeDimension(
			BimrInsideAuditRectify entity,BimrInsideAuditMeasures entity1, BimrInsideAuditProject entity2,BimrInsideAuditQuestion entity4,String dataAuthority,
			String vcEmployeeId);
	public List<Object[]> getProjectDimension(
			BimrInsideAuditRectify entity,BimrInsideAuditMeasures entity1, BimrInsideAuditProject entity2,BimrInsideAuditFeedback entity3,BimrInsideAuditQuestion entity4,String dataAuthority,
			String vcEmployeeId);
	
	

	public XSSFWorkbook getTimeDimensionWorkbook1(List<Object[]> list1);
	public XSSFWorkbook getProjectDimensionWorkbook1(List<Object[]> list1);

//	List<Object[]> getProjectDimension(BimrInsideAuditRectify entity,
//			BimrInsideAuditMeasures entity1, BimrInsideAuditProject entity2,String dataAuthority, 
//			String vcEmployeeId);
    
    
//    public List<ReportPersonalloanNearweek> getcoreComovertimeDetail(ReportPersonlloaninfoNew entity,String yearStart,String yearEnd);
    

    
}
