package com.softline.service.publicCompany.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.dao.publicCompany.IOutCompareDao;
import com.softline.dao.system.ISystemDao;
import com.softline.entityTemp.BimfOutCompare;
import com.softline.entityTemp.BimfOutCompareData;
import com.softline.entityTemp.OutCompareCompany;
import com.softline.service.publicCompany.IOutCompareService;
import com.softline.service.system.ISystemService;

@Service("outcompareService")
/**
 * 
 * @author tch
 *
 */
public class OutCompareService implements IOutCompareService{

	@Resource(name = "systemService")
	private ISystemService systemService;
	
	
	@Autowired
	@Qualifier("outcompareDao")
	private IOutCompareDao outcompareDao;
	@Autowired
	@Qualifier("systemDao")
	private ISystemDao systemDao;
	
	/**
	 * 外部对标获取数据
	 * @param orgID
	 * @param type 查询方案年度还是季度
	 * @return
	 */
	public BimfOutCompare getYearData(String orgID,String outorgID,int year)
	{
		BimfOutCompare result=new BimfOutCompare();
		String[] a = orgID.split("-");
		String[] b = outorgID.split("-");
		orgID = a[0];
		outorgID = b[0];
		int [] datearr=new int [3];
		for (int i = 0; i < 3; i++) {
			datearr[i]=year-i;
		}
		result.setPublicdata(outcompareDao.getYearData(orgID,datearr));
		result.setOutdata(outcompareDao.getYearData(outorgID,datearr));
		
		return result;
		
	}
	
	/**
	 * 外部对标获取数据
	 * @param orgID
	 * @param type 查询方案年度还是季度
	 * @return
	 */
	public BimfOutCompare getSeasonData(String orgID,String outorgID,int year,int season)
	{
		BimfOutCompare result=new BimfOutCompare();
		
		String[] a = orgID.split("-");
		String[] b = outorgID.split("-");
		int [][] datearr=new int [3][2];
		if(a[0].equals("1") || b[0].equals("1")){//存在香港公司，香港公司只有年报和半年报
			for (int i = 0; i < 3; i++) {
				if(season%2!=0){
					season = season - 1;
					if(season == 0){
						season = 4;
						year = year-1;
					}
				}
				datearr[i][0]=year;
				datearr[i][1]=season;
				season = season -1;
			}
		}else{
			for (int i = 0; i < 3; i++) {
				if(i!=0)
				{
					if(season!=1)
					{
						season=season-1;
					}else
					{
						season=4;
						year=year-1;
					}
				}
				datearr[i][0]=year;
				datearr[i][1]=season;
			}
		}
		orgID = a[0];
		outorgID = b[0];
		result.setPublicdata(outcompareDao.getSeasonData(orgID,datearr));
		result.setOutdata(outcompareDao.getSeasonData(outorgID,datearr));
		return result;
		
	}

	@Override
	public List<Object[]> getPublicCompanyStockList() {
		// TODO Auto-generated method stub
		return outcompareDao.getPublicCompanyStockList();
	}
	
}
