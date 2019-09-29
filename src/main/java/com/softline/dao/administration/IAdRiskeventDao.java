package com.softline.dao.administration;

import java.util.List;

import com.softline.entity.AdRiskevent;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.SysExamine;

public interface IAdRiskeventDao {

	Integer getRiskeventListCount(AdRiskevent entity, Integer fun);

	List<AdRiskevent> getRiskeventList(AdRiskevent entity, Integer offset,
			Integer pageSize, Integer fun);

	AdRiskevent getRiskevent(AdRiskevent entity);

	boolean saveRiskeventCheck(AdRiskevent entity);

	AdRiskevent getRiskevent(Integer id);

	SysExamine getOneExamine(Integer examineentityid, int examinekind);

	HhOrganInfo getCoreCompId(String riskCompId);

	boolean theTimeRiskeventCheck(AdRiskevent entity);

}
