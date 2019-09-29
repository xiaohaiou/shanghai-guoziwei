package com.softline.dao.bimr;

import java.util.List;

import com.softline.entity.HhUsers;
import com.softline.entity.bimr.BimrContractMonth;
import com.softline.entity.bimr.BimrContractMonthList;

public interface IContractMonthDao {
	
	//合同查询查询
	public List<BimrContractMonth> getContractMonthList(
			BimrContractMonth entity, Integer offsize, Integer pagesize);
	
	public List<BimrContractMonthList> getBimrContractMonthList(
			BimrContractMonthList entity, Integer offsize, Integer pagesize);
	//合同导出查询
	public List<BimrContractMonth> getContractMonthListExport(
			BimrContractMonth entity);
	
	public List<BimrContractMonthList> getBimrContractMonthListExport(
			BimrContractMonthList entity);
	
	//合同管理查询个数
	public int  getContractMonthListCount(BimrContractMonth entity);
	
	public int  getBimrContractMonthListCount(BimrContractMonthList entity);
	
	public BimrContractMonth GetBimrContractMonthById(Integer bid);
	
	public Integer  addBimrContractMonth(BimrContractMonth entity);
	public Boolean  addBimrContractMonthLists(List<BimrContractMonthList> entitys);
	public Boolean  updateBimrContractMonth(BimrContractMonth entity);
	public Boolean  delBimrContractMonthLists(Integer bimrContractId,HhUsers user);
	public Boolean ReportContractMonthById(Integer bid,Integer status,  HhUsers user);
	
	public int getContractMonthDetailListCount(BimrContractMonthList entity);
	
	public List<BimrContractMonthList> getContractMonthDetailList(
			BimrContractMonthList entity, Integer offsize, Integer pagesize);
	
	public BimrContractMonthList GetBimrContractMonthListById(Integer id);
}
