package com.softline.dao.bimr;

import java.util.List;

import com.softline.entity.bimr.BimrInsideAuditProject;
import com.softline.entity.bimr.BimrInsideAuditWeekly;

public interface IBimrInsideAuditWeeklyDao {
    Integer getBimrInsideAuditWeeklyListCount(BimrInsideAuditWeekly entity, String dataAuthority);

    List<BimrInsideAuditWeekly> getBimrInsideAuditWeeklyList(BimrInsideAuditWeekly entity, Integer offset, Integer pageSize, String dataAuthority);


    List<BimrInsideAuditWeekly> getBimrInsideAuditWeeklyList(int projectid);
    
    Integer saveBimrInsideAuditWeekly(BimrInsideAuditWeekly entity);

    void updateBimrInsideAuditWeekly(BimrInsideAuditWeekly entity);

    void deleteBimrInsideAuditWeekly(Integer id);

    BimrInsideAuditWeekly getBimrInsideAuditWeeklyById(Integer id);

    BimrInsideAuditWeekly getBimrInsideAuditWeekly(BimrInsideAuditWeekly entity);

	List<BimrInsideAuditWeekly> getBimrInsideAuditWeeklyHasNoChild();

    List<BimrInsideAuditWeekly> getBimrInsideAuditWeeklyForList(Integer status);

    List<BimrInsideAuditWeekly> getBimrInsideAuditWeeklyForChildren(Integer id);
    
//    List<BimrInsideAuditWeekly> getInsideExport1(Integer id);
    
    public List<Object[]> getInsideExport1(
    		BimrInsideAuditWeekly entity,BimrInsideAuditProject entity1, String dataAuthority,String vcEmployeeId);
    
}
