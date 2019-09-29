package com.softline.service.report;

import com.softline.entity.HhUsers;
import com.softline.entity.ReportKeywork;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;

public interface IReportKeyWorkService {
	MsgPage getKeyWorkYear(ReportKeywork entity, Integer curPageNum, Integer pageSize);
	
	ReportKeywork getKeyworkbyID(Integer id);
	
	/**
	 * 保存更新
	 * @param entity
	 * @param use
	 * @return
	 */
	 CommitResult saveKeywork(ReportKeywork entity,HhUsers use,String op);
	 
	 
	 /**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	 CommitResult deleteKeywork(Integer id,HhUsers use);
	 
	 
	 /**
	 * 审核
	 * @param entityid  采购ID
	 * @param examStr 审核备注
	 * @param examResult 审核意见
	 * @param use
	 * @return
	 */
	 CommitResult saveKeyWorkExamine(Integer entityid,String examStr,Integer examResult,HhUsers use);
}
