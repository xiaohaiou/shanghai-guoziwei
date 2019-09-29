package com.softline.service.bimr.impl;

import com.softline.dao.bimr.IBimrInsideAuditDifficultDao;
import com.softline.entity.bimr.BimrInsideAuditDifficult;
import com.softline.service.bimr.IBimrInsideAuditDifficultService;
import com.softline.util.MsgPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iBimrInsideAuditDifficultService")
public class BimrInsideAuditDifficultService implements IBimrInsideAuditDifficultService {

    @Autowired
    @Qualifier("iBimrInsideAuditDifficultDao")
    private IBimrInsideAuditDifficultDao iBimrInsideAuditDifficultDao;

    @Override
    public MsgPage getBimrInsideAuditDifficults(BimrInsideAuditDifficult entity, Integer curPageNum, Integer pageSize, String dataAuthority) {
        MsgPage msgPage = new MsgPage();
        //总记录数
        Integer totalRecords = iBimrInsideAuditDifficultDao.getBimrInsideAuditDifficultListCount(entity,dataAuthority);
        //当前页开始记录
        int offset = msgPage.countOffset(curPageNum,pageSize);
        //分页查询结果集
        List<BimrInsideAuditDifficult> list = iBimrInsideAuditDifficultDao.getBimrInsideAuditDifficultList(entity,offset,pageSize,dataAuthority);
        for (BimrInsideAuditDifficult biad : list) {

        }
        msgPage.setPageNum(curPageNum);
        msgPage.setPageSize(pageSize);
        msgPage.setTotalRecords(totalRecords);
        msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
        msgPage.setList(list);
        return msgPage;
    }

    @Override
    public Integer saveBimrInsideAuditDifficult(BimrInsideAuditDifficult entity) {
        return iBimrInsideAuditDifficultDao.saveBimrInsideAuditDifficult(entity);
    }

    @Override
    public void updateBimrInsideAuditDifficult(BimrInsideAuditDifficult entity) {
        iBimrInsideAuditDifficultDao.updateBimrInsideAuditDifficult(entity);
    }

    @Override
    public void deleteBimrInsideAuditDifficult(Integer id) {
        iBimrInsideAuditDifficultDao.deleteBimrInsideAuditDifficult(id);
    }

    @Override
    public BimrInsideAuditDifficult getBimrInsideAuditDifficult(Integer id) {
        return iBimrInsideAuditDifficultDao.getBimrInsideAuditDifficultById(id);
    }

    @Override
    public BimrInsideAuditDifficult getBimrInsideAuditDifficult(BimrInsideAuditDifficult entity) {
        return iBimrInsideAuditDifficultDao.getBimrInsideAuditDifficult(entity);
    }

    @Override
    public List<BimrInsideAuditDifficult> getBimrInsideAuditDifficultHasNoChild() {
        return iBimrInsideAuditDifficultDao.getBimrInsideAuditDifficultHasNoChild();
    }

    @Override
    public List<BimrInsideAuditDifficult> getBimrInsideAuditDifficultForList(Integer projectId) {
        return iBimrInsideAuditDifficultDao.getBimrInsideAuditDifficultForList(projectId);
    }
}
