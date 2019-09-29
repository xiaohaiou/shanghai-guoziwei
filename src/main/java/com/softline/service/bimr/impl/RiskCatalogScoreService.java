package com.softline.service.bimr.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softline.dao.bimr.IRiskCatalogScoreDao;
import com.softline.entity.HhUsers;
import com.softline.entity.bimr.BimrRiskCatalog;
import com.softline.entity.bimr.BimrRiskCatalogScore;
import com.softline.entityTemp.CommitResult;
import com.softline.service.bimr.IRiskCatalogScoreService;
import com.softline.service.bimr.IRiskCatalogService;
import com.softline.service.system.ICommonService;

/**
 * 实现二级风险目录评分业务服务
 * 
 * @author liu
 */
@Service
public class RiskCatalogScoreService implements IRiskCatalogScoreService {
	
	@Autowired
	private ICommonService commonService; 
	
	@Autowired
	private IRiskCatalogScoreDao dao;
	
	@Autowired
	private IRiskCatalogService catalogService;

	@Override
	public List<BimrRiskCatalogScore> getRiskCatalogScoreList(Integer year, Integer month,String coreOrg,String coreOrgName) {
		List<BimrRiskCatalogScore> scores = dao.getRiskCatalogScoreList(year, month,coreOrg);
		List<Object[]> willScores = dao.getWillScoreRiskCatalogList(year, month,coreOrg,coreOrgName);
		return mergeScores(year, month, scores, willScores);
	}
	
	private List<BimrRiskCatalogScore> mergeScores(Integer year, Integer month, List<BimrRiskCatalogScore> scores, List<Object[]> willScores){
		List<BimrRiskCatalogScore> list = new ArrayList<BimrRiskCatalogScore>(willScores.size());
		for(Object[] array: willScores){
			list.add(mergeScore(year, month, (Integer)array[0], (String)array[1], Integer.parseInt(array[2].toString()), Integer.parseInt(array[3].toString()),array[4].toString(),array[5].toString(),scores));
		}
		return list;
	}
	
	private BimrRiskCatalogScore mergeScore(Integer year, Integer month, 
			Integer ctgId, String ctgName, Integer eventNum,Integer eventImportantNum,String coreOrg,String coreOrgName, List<BimrRiskCatalogScore> scores){
		
		BimrRiskCatalogScore t = null;
		for(BimrRiskCatalogScore o: scores){
			if(o.getCtgId().intValue() == ctgId.intValue()){
				t = o;
				break;
			}
		}
		
		if(t != null){
			t.setEventNum(eventNum);
			t.setEventImportantNum(eventImportantNum);
			return t;
		}

		t = new BimrRiskCatalogScore();
		t.setYear(year);
		t.setMonth(month);
		t.setCtgId(ctgId);
		t.setCtgName(ctgName);
		t.setEventNum(eventNum);
		t.setEventImportantNum(eventImportantNum);
		t.setHapScore(1);
		t.setCriScore(1);
		t.setCoreOrg(coreOrg);
		t.setCoreOrgName(coreOrgName);
		return t;
	}

	@Override
	public CommitResult batchSaveAndUpdate(List<BimrRiskCatalogScore> entities, HhUsers user) {
		for(BimrRiskCatalogScore t: entities){
			saveOrUpdate(t, user);
		}
		CommitResult result = new CommitResult();
		result.setResult(true);
		return result;
	}
	
	private void saveOrUpdate(BimrRiskCatalogScore t, HhUsers user){
		if(isSaveNew(t.getId())){
			saveNew(t, user);
		}else{
			update(t, user);
		}
	}
	
	private boolean isSaveNew(Integer id){
		return id == null || id != -1;
	}
	
	private void saveNew(BimrRiskCatalogScore t, HhUsers user){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowStr = df.format(new Date());
		BimrRiskCatalog catalog = catalogService.getRiskCatalog(t.getCtgId());
		t.setCtgName(catalog.getName());
		t.setCreateDate(nowStr);
		t.setCreatePersonId(user.getVcEmployeeId());
		t.setCreatePersonName(user.getVcName());
		t.setLastModifyDate(nowStr);
		t.setLastModifyPersonId(user.getVcEmployeeId());
		t.setLastModifyPersonName(user.getVcName());
		t.setIsDel(0);
		commonService.saveOrUpdate(t);
	}
	
	private void update(BimrRiskCatalogScore t, HhUsers user){
		
		BimrRiskCatalogScore o = (BimrRiskCatalogScore)commonService.findById(
				BimrRiskCatalogScore.class, t.getId());
		o.setHapScore(t.getHapScore());
		o.setCriScore(t.getCriScore());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		o.setLastModifyDate(df.format(new Date()));
		o.setLastModifyPersonId(user.getVcEmployeeId());
		o.setLastModifyPersonName(user.getVcName());
		commonService.saveOrUpdate(o);
	}
}
