package com.softline.dao.administration;

import java.util.List;

import com.softline.entity.AdImportant;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.SysExamine;

public interface IAdImportantDao {

	Integer getImportantListCount(AdImportant entity, Integer fun);

	List<AdImportant> getImportantList(AdImportant entity, Integer offset,
			Integer pageSize, Integer fun);

	AdImportant getImportant(AdImportant entity);

	boolean saveImportantCheck(AdImportant entity);

	AdImportant getImportant(Integer id);

	SysExamine getOneExamine(Integer examineentityid, int examinekind);

	HhOrganInfo getCoreCompId(String importantCompId);

	boolean theTimeImportantCheck(AdImportant entity);

}
