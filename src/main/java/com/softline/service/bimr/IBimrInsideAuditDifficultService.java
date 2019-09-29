package com.softline.service.bimr;

import com.softline.entity.bimr.BimrInsideAuditDifficult;
import com.softline.util.MsgPage;

import java.util.List;

public interface IBimrInsideAuditDifficultService {
    MsgPage getBimrInsideAuditDifficults(BimrInsideAuditDifficult entity, Integer curPageNum, Integer pageSize, String dataAuthority);

    Integer saveBimrInsideAuditDifficult(BimrInsideAuditDifficult entity);

    void updateBimrInsideAuditDifficult(BimrInsideAuditDifficult entity);

    void deleteBimrInsideAuditDifficult(Integer id);

    BimrInsideAuditDifficult getBimrInsideAuditDifficult(Integer id);

    BimrInsideAuditDifficult getBimrInsideAuditDifficult(BimrInsideAuditDifficult entity);

    List<BimrInsideAuditDifficult> getBimrInsideAuditDifficultHasNoChild();

    List<BimrInsideAuditDifficult> getBimrInsideAuditDifficultForList(Integer projectId);

}
