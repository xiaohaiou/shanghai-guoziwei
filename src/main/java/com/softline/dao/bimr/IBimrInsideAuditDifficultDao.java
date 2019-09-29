package com.softline.dao.bimr;

import com.softline.entity.bimr.BimrInsideAuditDifficult;

import java.util.List;

public interface IBimrInsideAuditDifficultDao {
    Integer getBimrInsideAuditDifficultListCount(BimrInsideAuditDifficult entity, String dataAuthority);

    List<BimrInsideAuditDifficult> getBimrInsideAuditDifficultList(BimrInsideAuditDifficult entity, Integer offset, Integer pageSize, String dataAuthority);

    Integer saveBimrInsideAuditDifficult(BimrInsideAuditDifficult entity);

    void updateBimrInsideAuditDifficult(BimrInsideAuditDifficult entity);

    void deleteBimrInsideAuditDifficult(Integer id);

    BimrInsideAuditDifficult getBimrInsideAuditDifficultById(Integer id);

    BimrInsideAuditDifficult getBimrInsideAuditDifficult(BimrInsideAuditDifficult entity);

    List<BimrInsideAuditDifficult> getBimrInsideAuditDifficultHasNoChild();

    List<BimrInsideAuditDifficult> getBimrInsideAuditDifficultForList(Integer projectId);
}
