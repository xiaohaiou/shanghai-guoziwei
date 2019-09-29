package com.softline.dao.bimr;

import java.util.List;

import com.softline.entity.bimr.BimrInsideAuditFeedback;
import com.softline.entity.bimr.BimrInsideAuditMeasures;
import com.softline.entity.bimr.BimrInsideAuditProject;
import com.softline.entity.bimr.BimrInsideAuditQuestion;
import com.softline.entity.bimr.BimrInsideAuditRectify;
import com.softline.entity.bimr.BimrInsideAuditWeekly;

public interface IBimrInsideAuditRectifyDao {
	
    public Integer getBimrInsideRectifyListCount(BimrInsideAuditRectify entity, String dataAuthority);

    public List<BimrInsideAuditRectify> getBimrInsideRectifyList(BimrInsideAuditRectify entity, Integer offset, Integer pageSize, String dataAuthority);

    public List<BimrInsideAuditRectify> getBimrInsideRectifyQuestionList(int projectId);
    
    public Integer saveBimrInsideRectifyQuestion(BimrInsideAuditRectify entity);

    public void updateBimrInsideRectifyQuestion(BimrInsideAuditRectify entity);

    public void deleteBimrInsideRectifyQuestion(Integer id);

    public BimrInsideAuditRectify getBimrInsideRectifyById(Integer id);

    public BimrInsideAuditRectify getBimrInsideRectifyQuestion(BimrInsideAuditRectify entity);

    public List<BimrInsideAuditRectify> getBimrInsideRectifyQuestionHasNoChild();
    
    public BimrInsideAuditRectify getRectifyQuestionByAnswerId(Integer answerId);
    
    public List<BimrInsideAuditRectify> getRectifyQuestionByAnswerIdList(Integer answerId);
    
    public List<BimrInsideAuditMeasures> getBimrInsideRectifyMeasures(Integer rectifyId);
    
    public void deleteBimrInsideMesaures(Integer rectifyId);
    
    public void updateBimrInsideMeasuresStateById(String id, String state);
    
    public void deleteBimrInsideFeedbackByMonth(String month, String rectifyId);
    
    public Integer getFeedbackMsgCount(String rectifyId);
    
    public List<BimrInsideAuditFeedback> getFeedbackMsgList(String rectifyId, Integer offset, Integer pageSize);
    
    public List<BimrInsideAuditFeedback> getBimrInsideAuditFeedbackByRectifyId(String rectifyId);
    
//    public List<Object[]> getInsideExportXMDC(BimrInsideAuditProject entity,
//			BimrInsideAuditWeekly entity1, String dataAuthority,
//			String vcEmployeeId);
    
    public List<Object[]> getTimeDimensionExport(BimrInsideAuditRectify entity,BimrInsideAuditMeasures entity1,BimrInsideAuditProject entity2,BimrInsideAuditQuestion entity4,String dataAuthority,String vcEmployeeId);
    public List<Object[]> getProjectDimensionExport(BimrInsideAuditRectify entity,BimrInsideAuditMeasures entity1,BimrInsideAuditProject entity2,BimrInsideAuditFeedback entity3,BimrInsideAuditQuestion entity4,String dataAuthority,String vcEmployeeId);
    
}
