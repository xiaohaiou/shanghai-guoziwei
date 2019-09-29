package com.softline.dao.bimr;

import com.softline.entity.bimr.BimrInsideAuditMeasures;
import com.softline.entity.bimr.BimrInsideAuditProject;
import com.softline.entity.bimr.BimrInsideAuditQuestion;
import com.softline.entity.bimr.BimrInsideAuditRectify;

import java.util.List;

public interface IBimrInsideAuditQuestionDao {
    Integer getBimrInsideAuditQuestionListCount(BimrInsideAuditQuestion entity, String dataAuthority);

    List<BimrInsideAuditQuestion> getBimrInsideAuditQuestionList(BimrInsideAuditQuestion entity, Integer offset, Integer pageSize, String dataAuthority);

    Integer saveBimrInsideAuditQuestion(BimrInsideAuditQuestion entity);

    void updateBimrInsideAuditQuestion(BimrInsideAuditQuestion entity);

    void deleteBimrInsideAuditQuestion(Integer id);

    BimrInsideAuditQuestion getBimrInsideAuditQuestionById(Integer id);

    BimrInsideAuditQuestion getBimrInsideAuditQuestion(BimrInsideAuditQuestion entity);

    List<BimrInsideAuditQuestion> getBimrInsideAuditQuestionHasNoChild();

    List<BimrInsideAuditQuestion> getBimrInsideAuditQuestionForList(Integer projectId, String rectifyTrackId);

    Integer getUnFinishListCount(String yearMonth, BimrInsideAuditProject project,String dataAuthority,String vcEmployeeId);
    
    List<Object[]> getUnFinishList(String yearMonth, BimrInsideAuditProject project, Integer offset, Integer pageSize,String dataAuthority,String vcEmployeeId);
    
    Integer getQuestionAnalyzeListCount(String yearMonth, BimrInsideAuditProject project,String dataAuthority,String vcEmployeeId);
    
    List<Object[]> getQuestionAnalyzeList(String yearMonth, BimrInsideAuditProject project, Integer offset, Integer pageSize,String dataAuthority,String vcEmployeeId);
    public List<Object[]> getUnfinishExport(
			BimrInsideAuditProject entity,BimrInsideAuditQuestion entity1,BimrInsideAuditRectify entity2,BimrInsideAuditMeasures entity3,String yearMonth, String dataAuthority,String vcEmployeeId);

}
