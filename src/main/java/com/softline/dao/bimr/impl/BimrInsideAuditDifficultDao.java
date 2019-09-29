package com.softline.dao.bimr.impl;

import com.softline.common.Common;
import com.softline.dao.bimr.IBimrInsideAuditDifficultDao;
import com.softline.entity.bimr.BimrInsideAuditDifficult;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository(value = "iBimrInsideAuditDifficultDao")
public class BimrInsideAuditDifficultDao implements IBimrInsideAuditDifficultDao{

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer getBimrInsideAuditDifficultListCount(BimrInsideAuditDifficult entity, String dataAuthority) {
        if(entity == null){
            return 0;
        }
        StringBuilder  hql = new StringBuilder();
        hql.append("select count(0) from BimrInsideAuditDifficult s where 1=1 and s.isDel=0 ");
        if(entity != null) {
            if (entity.getProjectId() != null){
                hql.append(" and s.projectId =" + entity.getProjectId());
            }
            if(Common.notEmpty(entity.getProjectName())) {
                hql.append(" and s.projectName like '%"+entity.getProjectName()+ "%'");
            }
        }
        Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
        return Integer.parseInt(query.uniqueResult().toString());
    }

    @Override
    public List<BimrInsideAuditDifficult> getBimrInsideAuditDifficultList(BimrInsideAuditDifficult entity, Integer offset, Integer pageSize, String dataAuthority) {
        if(entity==null) {
            return new ArrayList<BimrInsideAuditDifficult>();
        }
        StringBuilder  hql = new StringBuilder();
        hql.append("from BimrInsideAuditDifficult s where 1=1 and isDel=0 ");
        if(entity!=null) {
            if (entity.getProjectId()!= null){
                hql.append(" and s.projectId = " + entity.getProjectId());
            }
            if(Common.notEmpty(entity.getProjectName())) {
                hql.append(" and s.projectName like '%"+entity.getProjectName()+ "%'");
            }
        }
        hql.append(" order by s.createDate desc ");
        Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
        if(offset != null){
            query.setFirstResult(offset);
        }
        if(pageSize != null){
            query.setMaxResults(pageSize);
        }
        return query.list();
    }

    @Override
    public Integer saveBimrInsideAuditDifficult(BimrInsideAuditDifficult entity) {
        return (Integer) sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public void updateBimrInsideAuditDifficult(BimrInsideAuditDifficult entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    @Override
    public void deleteBimrInsideAuditDifficult(Integer id) {
        String hql = "update BimrInsideAuditDifficult s set s.isDel = 1" + " where s.id = " + id + "";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.executeUpdate();
    }

    @Override
    public BimrInsideAuditDifficult getBimrInsideAuditDifficultById(Integer id) {
        String hql = "from BimrInsideAuditDifficult s where s.isDel = 0 and s.id = " + id;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return (BimrInsideAuditDifficult)query.uniqueResult();
    }

    @Override
    public BimrInsideAuditDifficult getBimrInsideAuditDifficult(BimrInsideAuditDifficult entity) {
        String hql = "from BimrInsideAuditDifficult s where s.isDel = 0 and s.id = " + entity.getId();
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return (BimrInsideAuditDifficult)query.uniqueResult();
    }

    @Override
    public List<BimrInsideAuditDifficult> getBimrInsideAuditDifficultHasNoChild() {
        String hql = "from BimrInsideAuditDifficult s where s.isDel = 0 and s.isChildren = 0";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public List<BimrInsideAuditDifficult> getBimrInsideAuditDifficultForList(Integer projectId) {
        StringBuilder hql = new StringBuilder();
        hql.append(" from");
        hql.append(" BimrInsideAuditDifficult s");
        hql.append(" where");
        hql.append(" s.isDel = 0");
        hql.append(" and");
        hql.append(" s.projectId = ");
        hql.append(projectId);
        Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
        return query.list();
    }
}
