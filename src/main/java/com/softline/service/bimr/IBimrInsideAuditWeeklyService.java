package com.softline.service.bimr;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.softline.entity.bimr.BimrInsideAuditProject;
import com.softline.entity.bimr.BimrInsideAuditWeekly;
import com.softline.util.MsgPage;

public interface IBimrInsideAuditWeeklyService {
    MsgPage getBimrInsideAuditWeeklys(BimrInsideAuditWeekly entity, Integer curPageNum, Integer pageSize, String dataAuthority);

    Integer saveBimrInsideAuditWeekly(BimrInsideAuditWeekly entity);
    
    List<BimrInsideAuditWeekly> getBimrInsideAuditWeeklyList(int projectid);
    


    void updateBimrInsideAuditWeekly(BimrInsideAuditWeekly entity);

    void deleteBimrInsideAuditWeekly(Integer id);

    BimrInsideAuditWeekly getBimrInsideAuditWeekly(Integer id);

    BimrInsideAuditWeekly getBimrInsideAuditWeekly(BimrInsideAuditWeekly entity);

	List<BimrInsideAuditWeekly> getBimrInsideAuditWeeklyHasNoChild();

    List<BimrInsideAuditWeekly> getBimrInsideAuditWeeklyForList(Integer status);

	List<Object[]> getInsideExportXM1(
			BimrInsideAuditWeekly entity,BimrInsideAuditProject entity1, String dataAuthority,String vcEmployeeId);

	XSSFWorkbook getInsideExportWorkbook3(List<Object[]> list1);
	
//	List<BimrInsideAuditWeekly> getInsideExportXM3(
//			BimrInsideAuditWeekly entity, String dataAuthority,
//			String vcEmployeeId);
	
	
}
