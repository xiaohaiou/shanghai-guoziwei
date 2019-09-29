package com.softline.dao.administration;

import java.util.List;

import com.softline.entity.AdDocument;
import com.softline.entity.SysExamine;

public interface IAdDocumentDao {

	Integer getDocumentListCount(AdDocument entity, Integer fun);

	List<AdDocument> getDocumentList(AdDocument entity, Integer offset,
			Integer pageSize, Integer fun);

	AdDocument getDocument(AdDocument entity);

	boolean saveDocumentCheck(AdDocument entity);

	AdDocument getDocument(Integer id);

	SysExamine getOneExamine(Integer examineentityid, int examinekind);

	boolean theTimeDocumentCheck(AdDocument entity);
	
	public List getVcCompanyId(String year, String month);

}
