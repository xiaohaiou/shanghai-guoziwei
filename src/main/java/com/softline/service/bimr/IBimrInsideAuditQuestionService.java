package com.softline.service.bimr;

import com.softline.entity.bimr.BimrInsideAuditMeasures;
import com.softline.entity.bimr.BimrInsideAuditProject;
import com.softline.entity.bimr.BimrInsideAuditQuestion;
import com.softline.entity.bimr.BimrInsideAuditRectify;
import com.softline.util.MsgPage;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface IBimrInsideAuditQuestionService {
    MsgPage getBimrInsideAuditQuestionts(BimrInsideAuditQuestion entity, Integer curPageNum, Integer pageSize, String dataAuthority);

    Integer saveBimrInsideAuditQuestion(BimrInsideAuditQuestion entity);

    void updateBimrInsideAuditQuestion(BimrInsideAuditQuestion entity);

    void deleteBimrInsideAuditQuestion(Integer id);

    BimrInsideAuditQuestion getBimrInsideAuditQuestion(Integer id);

    BimrInsideAuditQuestion getBimrInsideAuditQuestion(BimrInsideAuditQuestion entity);

    List<BimrInsideAuditQuestion> getBimrInsideAuditQuestionHasNoChild();

    List<BimrInsideAuditQuestion> getBimrInsideAuditQuestionForList(Integer projectId, String rectifyTrackId);

    MsgPage getUnFinishListView(String yearMonth, BimrInsideAuditProject entity, Integer pageNum, Integer pagesize,String dataAuthority, String vcEmployeeId);
    
    MsgPage getQuestionAnalyzeListView(String yearMonth, BimrInsideAuditProject entity, Integer pageNum, Integer pageSize,String dataAuthority, String vcEmployeeId);

	List<Object[]> getUnfinishExport(BimrInsideAuditProject entity,BimrInsideAuditQuestion entity1,BimrInsideAuditRectify entity2,BimrInsideAuditMeasures entity3,String yearMonth,
			String dataAuthority, String vcEmployeeId);

	XSSFWorkbook getgetUnfinishExportWorkbook1(List<Object[]> list1);
}
