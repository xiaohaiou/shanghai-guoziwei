package com.softline.service.administration;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.softline.entity.AdDocument;
import com.softline.entity.HhUsers;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;

public interface IAdDocumentService {

	MsgPage getDocumentView(AdDocument entity, Integer curPageNum, Integer pageSize, Integer fun);
	
	MsgPage getDocumentView(AdDocument entity, Integer curPageNum,Integer pageSize, Integer fun,String isAllCompany);

	AdDocument getDocument(AdDocument entity);

	CommitResult saveOrUpdateDocument(AdDocument entity, HhUsers use);

	CommitResult deleteDocument(AdDocument entity, HhUsers use);

	AdDocument getDocument(Integer id);

	CommitResult saveDocumentExamine(AdDocument entity, String examStr,
			Integer examResult, HhUsers use);

	List getSynDocumentComp(String year, String month);
	
	CommitResult synIDocument();
	
	/**
	 * 新增导出功能
	 *   Author by zl
	 * @param row
	 * @param sheet
	 * @param listEntity
	 * @param style
	 * @param rowNum
	 * @return
	 */
	public HSSFSheet setSumExcelData(HSSFRow row,HSSFSheet sheet,List<AdDocument> listEntity,HSSFCellStyle style,Integer rowNum);
	
	/**
	 * 获取总共的记录数据
	 * @param entity
	 * @param fun
	 * @return
	 */
	public MsgPage getAllDocumentView(AdDocument entity,Integer fun);
}
