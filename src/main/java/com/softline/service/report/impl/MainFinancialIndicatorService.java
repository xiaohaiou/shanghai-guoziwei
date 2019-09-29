package com.softline.service.report.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.dao.report.IMainFinancialIndicatorDao;
import com.softline.dao.report.IReportDao;
import com.softline.dao.system.ICommonDao;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entity.MainFinancialIndicator;
import com.softline.entity.MainFinancialIndicatorStock;
import com.softline.entityTemp.CommitResult;
import com.softline.entityTemp.ReportIndexAndData;
import com.softline.service.report.IMainFinancialIndicatorService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Service("mainFinancialIndicatorsService")
/**
 * 
 * @author tch
 *
 */
public class MainFinancialIndicatorService implements IMainFinancialIndicatorService{

	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	
	@Autowired
	@Qualifier("reportDao")
	private IReportDao reportDao;
	
	@Autowired
	@Qualifier("mainFinancialIndicatorDao")
	private IMainFinancialIndicatorDao mainFinancialIndicatorDao;
	
	@Resource(name = "examineService")
	private IExamineService examineService;	
	
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	public MsgPage getMainFinancialIndicatorListView(MainFinancialIndicator entity, Integer curPageNum, Integer pageSize)
	{
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = mainFinancialIndicatorDao.getMainFinancialIndicatorListCount(entity);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<MainFinancialIndicator> list = mainFinancialIndicatorDao.getMainFinancialIndicatorList(entity, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}
	/**
	 * 导出
	 * 
	 */
	@Override
	public List<MainFinancialIndicator> getMainFinancialIndicatorExportList(MainFinancialIndicator entity) {
		// TODO Auto-generated method stub
		List<MainFinancialIndicator> list = mainFinancialIndicatorDao.getMainFinancialIndicatorExportList(entity);
		return list;
	}
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public MainFinancialIndicator getMainFinancialIndicator(Integer id)
	{
		return mainFinancialIndicatorDao.getMainFinancialIndicator( id);
	}
	
	/**
	 * 保存校验
	 * @param entity
	 * @return
	 */
	public CommitResult saveMainFinancialIndicatorCheck(MainFinancialIndicator entity)
	{
		CommitResult result=new CommitResult();
		if(!mainFinancialIndicatorDao.saveMainFinancialIndicatorRepeatCheck(entity))
		{
			result=CommitResult.createErrorResult(entity.getYear()+"年该期已经有数据，不能再添加");
			return result;
		}
		if(!mainFinancialIndicatorDao.checkCanEdit(entity))
		{
			result=CommitResult.createErrorResult("该数据已经被上报不能修改");
			return result;
		}
		result.setResult(true);
		return result;
	}
	
	/**
	 * @param data
	 * @param organl
	 * @param Date
	 * @param use
	 * @param type
	 * @return
	 */
	public void stepBackMainFinancialIndicator(List<ReportIndexAndData> data,String organl,String Date,HhUsers use,int type)
	{
			int year=Integer.parseInt(Date.substring(0,4));
			int month=Integer.parseInt(Date.substring(5,7));
			HhOrganInfoTreeRelation org=systemService.getTreeOrganInfoNode(Base.financetype, organl);
			//先删除主要财务指标
			saveDeleteMainFinancialIndicator(year, month, org.getId().getNnodeId(), type,use);
	}
	
	/**
	 * 保存更新
	 * @param entity
	 * @param stock
	 * @param use
	 * @return
	 */
	public MainFinancialIndicator saveMainFinancialIndicator(List<ReportIndexAndData> data,String organl,String Date,HhUsers use,int type)
	{
			int year=Integer.parseInt(Date.substring(0,4));
			int month=Integer.parseInt(Date.substring(5,7));
			HhOrganInfoTreeRelation org=systemService.getTreeOrganInfoNode(Base.financetype, organl);
			//先删除主要财务指标
			saveDeleteMainFinancialIndicator(year, month, org.getId().getNnodeId(), type,use);
			//然后添加主要财务指标
			if(data==null && data.size()==0)
				return new MainFinancialIndicator();
			MainFinancialIndicator obj=new MainFinancialIndicator();
			obj.setOrg(org.getId().getNnodeId());
			obj.setOrgname(org.getVcFullName());
			obj.setParentorg(org.getVcOrganId());
			obj.setReportType(type);
			obj.setStatus(Base.examstatus_4);
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			obj.setCreateDate(formatter.format(new Date()));
			obj.setCreatePersonId(use.getVcEmployeeId());
			obj.setCreatePersonName(use.getVcName());
			obj.setYear(year);
			obj.setMonth(month);
			obj.setIsdel(0);
			Map<Integer, MainFinancialIndicatorStock> stockmap=new HashMap<Integer, MainFinancialIndicatorStock>();
			Map<Integer, MainFinancialIndicatorStock> stockmap2=new HashMap<Integer, MainFinancialIndicatorStock>();
			for (ReportIndexAndData item : data) {
				if(item.getIndexRowID().equals("0") && item.getIndexColID().equals("321")){//业态名
					obj.setBusinessorgname(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && item.getIndexColID().equals("323")){//负责人
					obj.setChargePerson(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && item.getIndexColID().equals("361")){//电话
					obj.setPhoneNumber(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && item.getIndexColID().equals("324")){//地址
					obj.setAddress(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && item.getIndexColID().equals("325")){//主运营地
					obj.setMainCamp(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && item.getIndexColID().equals("326")){//会计师事务所
					obj.setAccountingFirm(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && item.getIndexColID().equals("342")){//注册资本
					obj.setRegisteredCapital(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("107") && item.getIndexColID().equals("155")){//总资产
					obj.setTotalAssets(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("140") && item.getIndexColID().equals("157")){//总负债
					obj.setTotalLiabilities(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("153") && item.getIndexColID().equals("157")){//所有者权益
					obj.setOwnerEquity(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("141") && item.getIndexColID().equals("157")){//实收资本
					obj.setPaidCapital(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("67") && item.getIndexColID().equals("155")){// 货币资金
					obj.setMonetaryFund(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("83") && item.getIndexColID().equals("155")){// 存货
					obj.setStock(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("81") && item.getIndexColID().equals("155")){// 其他应收款
					obj.setOtherReceivables(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("93") && item.getIndexColID().equals("155")){// 投资性房地产
					obj.setInvestmentTypeRealEstate(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("94") && item.getIndexColID().equals("155")){// 固定资产
					obj.setFixedAssets(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("95") && item.getIndexColID().equals("155")){// 在建工程(元)
					obj.setUnderConstructionProject(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("100") && item.getIndexColID().equals("155")){// 无形资产
					obj.setIntangibleAssets(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("122") && item.getIndexColID().equals("157")){// 其他应付款
					obj.setOtherPayable(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("108") && item.getIndexColID().equals("157")){// 短期借款
					obj.setShortTermLoan(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("113") && item.getIndexColID().equals("157")){// 应付票据
					obj.setNotessPayable(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("128") && item.getIndexColID().equals("157")){// 1年内到期的非流动负债
					obj.setNonCurrentLiabilities(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("131") && item.getIndexColID().equals("157")){// 长期借款
					obj.setLongTermLoan(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("132") && item.getIndexColID().equals("157")){// 应付债券
					obj.setBondsPayable(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("133") && item.getIndexColID().equals("157")){// 长期应付款
					obj.setLongTermPayables(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("149") && item.getIndexColID().equals("157")){// 未分配利润
					obj.setUndistributedProfit(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("151") && item.getIndexColID().equals("157")){//归属母公司的所有者权益
					obj.setOwnerEquityAttributableToTheParentCompany(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("152") && item.getIndexColID().equals("157")){//少数股东权益
					obj.setMinorityShareholdersEquity(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("267") && item.getIndexColID().equals("318")){//营业总收入本期
					obj.setTotalRevenue(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("273") && item.getIndexColID().equals("318")){//营业成本
					obj.setOperatingCost(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("295") && item.getIndexColID().equals("318")){//利润总额
					obj.setTotalProfit(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("297") && item.getIndexColID().equals("318")){//净利润
					obj.setNetProfit(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("281") && item.getIndexColID().equals("318")){//税金及附加
					obj.setBusinessTaxesAndSurcharges(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("282") && item.getIndexColID().equals("318")){//销售费用
					obj.setSellingExpenses(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("283") && item.getIndexColID().equals("318")){//管理费用
					obj.setManagementExpenses(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("284") && item.getIndexColID().equals("318")){//财务费用
					obj.setFinancialExpenses(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("286") && item.getIndexColID().equals("318")){//公允价值变动收益
					obj.setFairValueChangeIncome(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("287") && item.getIndexColID().equals("318")){//投资收益
					obj.setIncomeFromInvestment(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("296") && item.getIndexColID().equals("318")){//所得税费用
					obj.setIncomeTaxExpense(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("296") && item.getIndexColID().equals("318")){//所得税费用
					obj.setIncomeTaxExpense(item.getValue());
					continue;
				}
				
				//注册资本对应的股东信息
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("343")  ){//股东1
					Integer key=Integer.parseInt(item.getIndexColID());
					MainFinancialIndicatorStock stock=stockmap.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap.put(key, stock);
					}
					stock.setName(item.getValue());
					stock.setType(1);
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("344")  ){//股东1
					Integer key=Integer.parseInt(item.getIndexColID())-1;
					MainFinancialIndicatorStock stock=stockmap.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap.put(key, stock);
					}
					stock.setPrecent(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("345")  ){//股东2
					Integer key=Integer.parseInt(item.getIndexColID());
					MainFinancialIndicatorStock stock=stockmap.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap.put(key, stock);
					}
					stock.setName(item.getValue());
					stock.setType(1);
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("346")  ){//股东2
					Integer key=Integer.parseInt(item.getIndexColID())-1;
					MainFinancialIndicatorStock stock=stockmap.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap.put(key, stock);
					}
					stock.setPrecent(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("347")  ){//股东3
					Integer key=Integer.parseInt(item.getIndexColID());
					MainFinancialIndicatorStock stock=stockmap.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap.put(key, stock);
					}
					stock.setName(item.getValue());
					stock.setType(1);
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("348")  ){//股东3
					Integer key=Integer.parseInt(item.getIndexColID())-1;
					MainFinancialIndicatorStock stock=stockmap.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap.put(key, stock);
					}
					stock.setPrecent(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("349")  ){//股东4
					Integer key=Integer.parseInt(item.getIndexColID());
					MainFinancialIndicatorStock stock=stockmap.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap.put(key, stock);
					}
					stock.setName(item.getValue());
					stock.setType(1);
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("350")  ){//股东4
					Integer key=Integer.parseInt(item.getIndexColID())-1;
					MainFinancialIndicatorStock stock=stockmap.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap.put(key, stock);
					}
					stock.setPrecent(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("351")  ){//股东5
					Integer key=Integer.parseInt(item.getIndexColID());
					MainFinancialIndicatorStock stock=stockmap.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap.put(key, stock);
					}
					stock.setName(item.getValue());
					stock.setType(1);
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("352")  ){//股东5
					Integer key=Integer.parseInt(item.getIndexColID())-1;
					MainFinancialIndicatorStock stock=stockmap.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap.put(key, stock);
					}
					stock.setPrecent(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("353")  ){//股东6
					Integer key=Integer.parseInt(item.getIndexColID());
					MainFinancialIndicatorStock stock=stockmap.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap.put(key, stock);
					}
					stock.setName(item.getValue());
					stock.setType(1);
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("354")  ){//股东6
					Integer key=Integer.parseInt(item.getIndexColID())-1;
					MainFinancialIndicatorStock stock=stockmap.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap.put(key, stock);
					}
					stock.setPrecent(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("355")  ){//股东7
					Integer key=Integer.parseInt(item.getIndexColID());
					MainFinancialIndicatorStock stock=stockmap.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap.put(key, stock);
					}
					stock.setName(item.getValue());
					stock.setType(1);
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("356")  ){//股东7
					Integer key=Integer.parseInt(item.getIndexColID())-1;
					MainFinancialIndicatorStock stock=stockmap.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap.put(key, stock);
					}
					stock.setPrecent(item.getValue());
					continue;
				}
				
				
				//实收 资本对应的股东信息
				
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("328")  ){//股东1
					Integer key=Integer.parseInt(item.getIndexColID());
					MainFinancialIndicatorStock stock=stockmap2.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap2.put(key, stock);
					}
					stock.setName(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("329")  ){//股东1
					Integer key=Integer.parseInt(item.getIndexColID())-1;
					MainFinancialIndicatorStock stock=stockmap2.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap2.put(key, stock);
					}
					stock.setPrecent(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("330")  ){//股东2
					Integer key=Integer.parseInt(item.getIndexColID());
					MainFinancialIndicatorStock stock=stockmap2.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap2.put(key, stock);
					}
					stock.setName(item.getValue());
					
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("331")  ){//股东2
					Integer key=Integer.parseInt(item.getIndexColID())-1;
					MainFinancialIndicatorStock stock=stockmap2.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap2.put(key, stock);
					}
					stock.setPrecent(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("332")  ){//股东3
					Integer key=Integer.parseInt(item.getIndexColID());
					MainFinancialIndicatorStock stock=stockmap2.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap2.put(key, stock);
					}
					stock.setName(item.getValue());
					
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("333")  ){//股东3
					Integer key=Integer.parseInt(item.getIndexColID())-1;
					MainFinancialIndicatorStock stock=stockmap2.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap2.put(key, stock);
					}
					stock.setPrecent(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("334")  ){//股东4
					Integer key=Integer.parseInt(item.getIndexColID());
					MainFinancialIndicatorStock stock=stockmap2.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap2.put(key, stock);
					}
					stock.setName(item.getValue());
					
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("335")  ){//股东4
					Integer key=Integer.parseInt(item.getIndexColID())-1;
					MainFinancialIndicatorStock stock=stockmap2.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap2.put(key, stock);
					}
					stock.setPrecent(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("336")  ){//股东5
					Integer key=Integer.parseInt(item.getIndexColID());
					MainFinancialIndicatorStock stock=stockmap2.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap2.put(key, stock);
					}
					stock.setName(item.getValue());
					
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("337")  ){//股东5
					Integer key=Integer.parseInt(item.getIndexColID())-1;
					MainFinancialIndicatorStock stock=stockmap2.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap2.put(key, stock);
					}
					stock.setPrecent(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("338")  ){//股东6
					Integer key=Integer.parseInt(item.getIndexColID());
					MainFinancialIndicatorStock stock=stockmap2.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap2.put(key, stock);
					}
					stock.setName(item.getValue());
					
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("339")  ){//股东6
					Integer key=Integer.parseInt(item.getIndexColID())-1;
					MainFinancialIndicatorStock stock=stockmap2.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap2.put(key, stock);
					}
					stock.setPrecent(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("340")  ){//股东7
					Integer key=Integer.parseInt(item.getIndexColID());
					MainFinancialIndicatorStock stock=stockmap2.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap2.put(key, stock);
					}
					stock.setName(item.getValue());
					
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("341")  ){//股东7
					Integer key=Integer.parseInt(item.getIndexColID())-1;
					MainFinancialIndicatorStock stock=stockmap2.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap2.put(key, stock);
					}
					stock.setPrecent(item.getValue());
					continue;
				}
				
			}
			commonDao.saveOrUpdate(obj);
			for (Map.Entry<Integer, MainFinancialIndicatorStock> entry : stockmap.entrySet()) {
				MainFinancialIndicatorStock child= entry.getValue();
				child.setParentId(obj.getId());
				child.setIsdel(0);
				child.setType(1);//注册资本
				child.setCreateDate(formatter.format(new Date()));
				child.setCreatePersonId(use.getVcEmployeeId());
				child.setCreatePersonName(use.getVcName());
				commonDao.saveOrUpdate(child);
			}
			for (Map.Entry<Integer, MainFinancialIndicatorStock> entry : stockmap2.entrySet()) {
				MainFinancialIndicatorStock child= entry.getValue();
				child.setParentId(obj.getId());
				child.setIsdel(0);
				child.setType(0);//实收资本
				child.setCreateDate(formatter.format(new Date()));
				child.setCreatePersonId(use.getVcEmployeeId());
				child.setCreatePersonName(use.getVcName());
				commonDao.saveOrUpdate(child);
			}
			return obj;
		}
	
	
	/**
	 * 保存更新
	 * @param entity
	 * @param stock
	 * @param use
	 * @return
	 */
	public MainFinancialIndicator getMainFinancialIndicatorData(List<ReportIndexAndData> data,String organl,String Date,HhUsers use,int type)
	{
			if(data==null && data.size()==0)
				return new MainFinancialIndicator();
			HhOrganInfoTreeRelation org=systemService.getTreeOrganInfoNode(Base.financetype, organl);
			MainFinancialIndicator obj=new MainFinancialIndicator();
			obj.setOrg(org.getId().getNnodeId());
			obj.setOrgname(org.getVcFullName());
			obj.setParentorg(org.getVcOrganId());
			obj.setReportType(type);
			obj.setStatus(Base.examstatus_4);
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			obj.setCreateDate(formatter.format(new Date()));
			obj.setCreatePersonId(use.getVcEmployeeId());
			obj.setCreatePersonName(use.getVcName());
			int year=Integer.parseInt(Date.substring(0,4));
			int month=Integer.parseInt(Date.substring(5,7));
			obj.setYear(year);
			obj.setMonth(month);
			obj.setIsdel(0);
			Map<Integer, MainFinancialIndicatorStock> stockmap=new HashMap<Integer, MainFinancialIndicatorStock>();
			Map<Integer, MainFinancialIndicatorStock> stockmap2=new HashMap<Integer, MainFinancialIndicatorStock>();
			for (ReportIndexAndData item : data) {
				if(item.getIndexRowID().equals("0") && item.getIndexColID().equals("321")){//业态名
					obj.setBusinessorgname(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && item.getIndexColID().equals("323")){//负责人
					obj.setChargePerson(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && item.getIndexColID().equals("324")){//地址
					obj.setAddress(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && item.getIndexColID().equals("325")){//主运营地
					obj.setMainCamp(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && item.getIndexColID().equals("326")){//会计师事务所
					obj.setAccountingFirm(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && item.getIndexColID().equals("342")){//注册资本
					obj.setRegisteredCapital(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("107") && item.getIndexColID().equals("155")){//总资产
					obj.setTotalAssets(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("140") && item.getIndexColID().equals("157")){//总负债
					obj.setTotalLiabilities(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("153") && item.getIndexColID().equals("157")){//所有者权益
					obj.setOwnerEquity(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("141") && item.getIndexColID().equals("157")){//实收资本
					obj.setPaidCapital(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("67") && item.getIndexColID().equals("155")){// 货币资金
					obj.setMonetaryFund(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("83") && item.getIndexColID().equals("155")){// 存货
					obj.setStock(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("81") && item.getIndexColID().equals("155")){// 其他应收款
					obj.setOtherReceivables(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("93") && item.getIndexColID().equals("155")){// 投资性房地产
					obj.setInvestmentTypeRealEstate(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("94") && item.getIndexColID().equals("155")){// 固定资产
					obj.setFixedAssets(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("95") && item.getIndexColID().equals("155")){// 在建工程(元)
					obj.setUnderConstructionProject(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("100") && item.getIndexColID().equals("155")){// 无形资产
					obj.setIntangibleAssets(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("122") && item.getIndexColID().equals("157")){// 其他应付款
					obj.setOtherPayable(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("108") && item.getIndexColID().equals("157")){// 短期借款
					obj.setShortTermLoan(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("128") && item.getIndexColID().equals("157")){// 1年内到期的非流动负债
					obj.setNonCurrentLiabilities(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("131") && item.getIndexColID().equals("157")){// 长期借款
					obj.setLongTermLoan(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("133") && item.getIndexColID().equals("157")){// 长期应付款
					obj.setLongTermPayables(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("149") && item.getIndexColID().equals("157")){// 未分配利润
					obj.setUndistributedProfit(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("151") && item.getIndexColID().equals("157")){//归属母公司的所有者权益
					obj.setOwnerEquityAttributableToTheParentCompany(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("152") && item.getIndexColID().equals("157")){//少数股东权益
					obj.setMinorityShareholdersEquity(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("267") && item.getIndexColID().equals("318")){//营业总收入本期
					obj.setTotalRevenue(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("273") && item.getIndexColID().equals("318")){//营业成本
					obj.setOperatingCost(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("295") && item.getIndexColID().equals("318")){//利润总额
					obj.setTotalProfit(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("297") && item.getIndexColID().equals("318")){//净利润
					obj.setNetProfit(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("281") && item.getIndexColID().equals("318")){//税金及附加
					obj.setBusinessTaxesAndSurcharges(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("282") && item.getIndexColID().equals("318")){//销售费用
					obj.setSellingExpenses(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("283") && item.getIndexColID().equals("318")){//管理费用
					obj.setManagementExpenses(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("284") && item.getIndexColID().equals("318")){//财务费用
					obj.setFinancialExpenses(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("286") && item.getIndexColID().equals("318")){//公允价值变动收益
					obj.setFairValueChangeIncome(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("287") && item.getIndexColID().equals("318")){//投资收益
					obj.setIncomeFromInvestment(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("296") && item.getIndexColID().equals("318")){//所得税费用
					obj.setIncomeTaxExpense(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("296") && item.getIndexColID().equals("318")){//所得税费用
					obj.setIncomeTaxExpense(item.getValue());
					continue;
				}
				
				//注册资本对应的股东信息
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("343")  ){//股东1
					Integer key=Integer.parseInt(item.getIndexColID());
					MainFinancialIndicatorStock stock=stockmap.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap.put(key, stock);
					}
					stock.setName(item.getValue());
					stock.setType(1);
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("344")  ){//股东1
					Integer key=Integer.parseInt(item.getIndexColID())-1;
					MainFinancialIndicatorStock stock=stockmap.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap.put(key, stock);
					}
					stock.setPrecent(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("345")  ){//股东2
					Integer key=Integer.parseInt(item.getIndexColID());
					MainFinancialIndicatorStock stock=stockmap.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap.put(key, stock);
					}
					stock.setName(item.getValue());
					stock.setType(1);
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("346")  ){//股东2
					Integer key=Integer.parseInt(item.getIndexColID())-1;
					MainFinancialIndicatorStock stock=stockmap.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap.put(key, stock);
					}
					stock.setPrecent(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("347")  ){//股东3
					Integer key=Integer.parseInt(item.getIndexColID());
					MainFinancialIndicatorStock stock=stockmap.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap.put(key, stock);
					}
					stock.setName(item.getValue());
					stock.setType(1);
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("348")  ){//股东3
					Integer key=Integer.parseInt(item.getIndexColID())-1;
					MainFinancialIndicatorStock stock=stockmap.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap.put(key, stock);
					}
					stock.setPrecent(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("349")  ){//股东4
					Integer key=Integer.parseInt(item.getIndexColID());
					MainFinancialIndicatorStock stock=stockmap.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap.put(key, stock);
					}
					stock.setName(item.getValue());
					stock.setType(1);
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("350")  ){//股东4
					Integer key=Integer.parseInt(item.getIndexColID())-1;
					MainFinancialIndicatorStock stock=stockmap.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap.put(key, stock);
					}
					stock.setPrecent(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("351")  ){//股东5
					Integer key=Integer.parseInt(item.getIndexColID());
					MainFinancialIndicatorStock stock=stockmap.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap.put(key, stock);
					}
					stock.setName(item.getValue());
					stock.setType(1);
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("352")  ){//股东5
					Integer key=Integer.parseInt(item.getIndexColID())-1;
					MainFinancialIndicatorStock stock=stockmap.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap.put(key, stock);
					}
					stock.setPrecent(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("353")  ){//股东6
					Integer key=Integer.parseInt(item.getIndexColID());
					MainFinancialIndicatorStock stock=stockmap.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap.put(key, stock);
					}
					stock.setName(item.getValue());
					stock.setType(1);
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("354")  ){//股东6
					Integer key=Integer.parseInt(item.getIndexColID())-1;
					MainFinancialIndicatorStock stock=stockmap.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap.put(key, stock);
					}
					stock.setPrecent(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("355")  ){//股东7
					Integer key=Integer.parseInt(item.getIndexColID());
					MainFinancialIndicatorStock stock=stockmap.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap.put(key, stock);
					}
					stock.setName(item.getValue());
					stock.setType(1);
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("356")  ){//股东7
					Integer key=Integer.parseInt(item.getIndexColID())-1;
					MainFinancialIndicatorStock stock=stockmap.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap.put(key, stock);
					}
					stock.setPrecent(item.getValue());
					continue;
				}
				
				
				//实收 资本对应的股东信息
				
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("328")  ){//股东1
					Integer key=Integer.parseInt(item.getIndexColID());
					MainFinancialIndicatorStock stock=stockmap2.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap2.put(key, stock);
					}
					stock.setName(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("329")  ){//股东1
					Integer key=Integer.parseInt(item.getIndexColID())-1;
					MainFinancialIndicatorStock stock=stockmap2.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap2.put(key, stock);
					}
					stock.setPrecent(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("330")  ){//股东2
					Integer key=Integer.parseInt(item.getIndexColID());
					MainFinancialIndicatorStock stock=stockmap2.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap2.put(key, stock);
					}
					stock.setName(item.getValue());
					
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("331")  ){//股东2
					Integer key=Integer.parseInt(item.getIndexColID())-1;
					MainFinancialIndicatorStock stock=stockmap2.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap2.put(key, stock);
					}
					stock.setPrecent(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("332")  ){//股东3
					Integer key=Integer.parseInt(item.getIndexColID());
					MainFinancialIndicatorStock stock=stockmap2.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap2.put(key, stock);
					}
					stock.setName(item.getValue());
					
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("333")  ){//股东3
					Integer key=Integer.parseInt(item.getIndexColID())-1;
					MainFinancialIndicatorStock stock=stockmap2.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap2.put(key, stock);
					}
					stock.setPrecent(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("334")  ){//股东4
					Integer key=Integer.parseInt(item.getIndexColID());
					MainFinancialIndicatorStock stock=stockmap2.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap2.put(key, stock);
					}
					stock.setName(item.getValue());
					
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("335")  ){//股东4
					Integer key=Integer.parseInt(item.getIndexColID())-1;
					MainFinancialIndicatorStock stock=stockmap2.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap2.put(key, stock);
					}
					stock.setPrecent(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("336")  ){//股东5
					Integer key=Integer.parseInt(item.getIndexColID());
					MainFinancialIndicatorStock stock=stockmap2.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap2.put(key, stock);
					}
					stock.setName(item.getValue());
					
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("337")  ){//股东5
					Integer key=Integer.parseInt(item.getIndexColID())-1;
					MainFinancialIndicatorStock stock=stockmap2.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap2.put(key, stock);
					}
					stock.setPrecent(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("338")  ){//股东6
					Integer key=Integer.parseInt(item.getIndexColID());
					MainFinancialIndicatorStock stock=stockmap2.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap2.put(key, stock);
					}
					stock.setName(item.getValue());
					
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("339")  ){//股东6
					Integer key=Integer.parseInt(item.getIndexColID())-1;
					MainFinancialIndicatorStock stock=stockmap2.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap2.put(key, stock);
					}
					stock.setPrecent(item.getValue());
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("340")  ){//股东7
					Integer key=Integer.parseInt(item.getIndexColID());
					MainFinancialIndicatorStock stock=stockmap2.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap2.put(key, stock);
					}
					stock.setName(item.getValue());
					
					continue;
				}
				if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("341")  ){//股东7
					Integer key=Integer.parseInt(item.getIndexColID())-1;
					MainFinancialIndicatorStock stock=stockmap2.get(key);
					if(stock==null){
						stock=new MainFinancialIndicatorStock();
						stockmap2.put(key, stock);
					}
					stock.setPrecent(item.getValue());
					continue;
				}
				
			}
			List<MainFinancialIndicatorStock> stock1 = new ArrayList<MainFinancialIndicatorStock>();
			List<MainFinancialIndicatorStock> stock2 = new ArrayList<MainFinancialIndicatorStock>();
			for (Map.Entry<Integer, MainFinancialIndicatorStock> entry : stockmap.entrySet()) {
				MainFinancialIndicatorStock child= entry.getValue();
				child.setParentId(obj.getId());
				child.setIsdel(0);
				child.setType(1);//注册资本
				child.setCreateDate(formatter.format(new Date()));
				child.setCreatePersonId(use.getVcEmployeeId());
				child.setCreatePersonName(use.getVcName());
				stock1.add(child);
			}
			for (Map.Entry<Integer, MainFinancialIndicatorStock> entry : stockmap2.entrySet()) {
				MainFinancialIndicatorStock child= entry.getValue();
				child.setParentId(obj.getId());
				child.setIsdel(0);
				child.setType(0);//实收资本
				child.setCreateDate(formatter.format(new Date()));
				child.setCreatePersonId(use.getVcEmployeeId());
				child.setCreatePersonName(use.getVcName());
				stock2.add(child);
			}
			obj.setStick1(stock1);
			obj.setStick2(stock2);
			return obj;
		}
	

	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteMainFinancialIndicator(Integer id,HhUsers use)
	{
		
		MainFinancialIndicator entity=mainFinancialIndicatorDao.getMainFinancialIndicator(id);
		CommitResult result=new CommitResult();
		if(!mainFinancialIndicatorDao.checkCanEdit(entity))
		{
			result=CommitResult.createErrorResult("该数据已经被上报不能修改");
			return result;
		}
		if(entity!=null)
		{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			deleteStockData( entity.getId(), use);
			entity.setIsdel(1);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
			commonDao.saveOrUpdate(entity);	
		}
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}
	
	
	/**
	 * 审核校验判断是否还能够审核，可能别人已经审核过了
	 * @param entityID 采购ID
	 * @return
	 */
	public CommitResult saveMainFinancialIndicatorExamineCheck(Integer entityID)
	{
		CommitResult result=new CommitResult();
		MainFinancialIndicator entity=mainFinancialIndicatorDao.getMainFinancialIndicator(entityID);
		if(entity==null)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
			return result;
		}
		if(!entity.getStatus().equals(Base.examstatus_2))
		{
			result=CommitResult.createErrorResult("该信息已不用审核");
			return result;
		}
		result.setResult(true);
		return result;
	}
	
	/**
	 * 审核
	 * @param entityID 采购ID
	 * @param examStr 审核备注
	 * @param examResult 审核意见
	 * @param use
	 * @return
	 */
	public CommitResult saveMainFinancialIndicatorExamine(Integer entityID,String examStr,Integer examResult,HhUsers use)
	{
		
		MainFinancialIndicator entity=getMainFinancialIndicator(entityID);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=new CommitResult();
		result=saveMainFinancialIndicatorExamineCheck(entityID);
		if(!result.isResult())
		{
			return result;
		}
		//审核不通过
		if(examResult.equals(Base.examresult_1))
			entity.setStatus(Base.examstatus_3);
		//审核通过
		else if(examResult.equals(Base.examresult_2))
			entity.setStatus(Base.examstatus_4);
		String a=df.format(new Date());
		entity.setAuditorDate(df.format(new Date()));
		entity.setAuditorPersonId(use.getVcEmployeeId());
		entity.setAuditorPersonName(use.getVcName());
		//保存MainFinancialIndicator
		commonDao.saveOrUpdate(entity);
		
		Integer examineentityid=entity.getId();

		examineService.saveExamine( examineentityid, Base.examkind_mainfinanceindicator, use, examStr, examResult);
		
		result.setResult(true);
		return result;
	}
	
	/**
	 * 获取谋个主要财务指标数据数据的股东数据
	 * @param parentID
	 * @return
	 */
	public List<MainFinancialIndicatorStock> getStockData(int parentID)
	{
		return mainFinancialIndicatorDao.getStockData(parentID);
	}
	/**
	 * 删除谋个主要财务指标数据数据的股东数据
	 * @param parentID
	 * @return
	 */
	private void deleteStockData(int parentID,HhUsers use)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<MainFinancialIndicatorStock> stock=getStockData(parentID);
		for (MainFinancialIndicatorStock item : stock) {
			item.setIsdel(1);
			item.setLastModifyDate(df.format(new Date()));
			item.setLastModifyPersonId(use.getVcEmployeeId());
			item.setLastModifyPersonName(use.getVcName());
			commonDao.saveOrUpdate(item);
		}
	}


	@Override
	public List<MainFinancialIndicatorStock> getSszbData(MainFinancialIndicator condition) {
		String year = condition.getYear()+"";
		String month = String.format("%02d",condition.getMonth());
		String reportTime = year + "-" + month;
		String organl = condition.getOrg();
		int[] formKind = new int[1];
		if(condition.getReportType().intValue() == Base.reportgroup_type_simple){//补充与合并都取单户
			formKind[0] = 51010;//补充表单户
		}else{
			formKind[0] = 51010;//补充表单户
		}
		List<ReportIndexAndData> cellValues = reportDao.getReportValueBy(reportTime, organl, formKind);
		Map<Integer, MainFinancialIndicatorStock> stockmap=new HashMap<Integer, MainFinancialIndicatorStock>();
		for (ReportIndexAndData item : cellValues) {
			if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("328")  ){//股东1
				Integer key=Integer.parseInt(item.getIndexColID());
				MainFinancialIndicatorStock stock=stockmap.get(key);
				if(stock==null){
					stock=new MainFinancialIndicatorStock();
					stockmap.put(key, stock);
				}
				stock.setName(item.getValue());
				continue;
			}
			if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("329")  ){//股东1
				Integer key=Integer.parseInt(item.getIndexColID())-1;
				MainFinancialIndicatorStock stock=stockmap.get(key);
				if(stock==null){
					stock=new MainFinancialIndicatorStock();
					stockmap.put(key, stock);
				}
				stock.setPrecent(item.getValue());
				continue;
			}
			if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("330")  ){//股东2
				Integer key=Integer.parseInt(item.getIndexColID());
				MainFinancialIndicatorStock stock=stockmap.get(key);
				if(stock==null){
					stock=new MainFinancialIndicatorStock();
					stockmap.put(key, stock);
				}
				stock.setName(item.getValue());
				
				continue;
			}
			if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("331")  ){//股东2
				Integer key=Integer.parseInt(item.getIndexColID())-1;
				MainFinancialIndicatorStock stock=stockmap.get(key);
				if(stock==null){
					stock=new MainFinancialIndicatorStock();
					stockmap.put(key, stock);
				}
				stock.setPrecent(item.getValue());
				continue;
			}
			if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("332")  ){//股东3
				Integer key=Integer.parseInt(item.getIndexColID());
				MainFinancialIndicatorStock stock=stockmap.get(key);
				if(stock==null){
					stock=new MainFinancialIndicatorStock();
					stockmap.put(key, stock);
				}
				stock.setName(item.getValue());
				
				continue;
			}
			if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("333")  ){//股东3
				Integer key=Integer.parseInt(item.getIndexColID())-1;
				MainFinancialIndicatorStock stock=stockmap.get(key);
				if(stock==null){
					stock=new MainFinancialIndicatorStock();
					stockmap.put(key, stock);
				}
				stock.setPrecent(item.getValue());
				continue;
			}
			if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("334")  ){//股东4
				Integer key=Integer.parseInt(item.getIndexColID());
				MainFinancialIndicatorStock stock=stockmap.get(key);
				if(stock==null){
					stock=new MainFinancialIndicatorStock();
					stockmap.put(key, stock);
				}
				stock.setName(item.getValue());
				
				continue;
			}
			if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("335")  ){//股东4
				Integer key=Integer.parseInt(item.getIndexColID())-1;
				MainFinancialIndicatorStock stock=stockmap.get(key);
				if(stock==null){
					stock=new MainFinancialIndicatorStock();
					stockmap.put(key, stock);
				}
				stock.setPrecent(item.getValue());
				continue;
			}
			if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("336")  ){//股东5
				Integer key=Integer.parseInt(item.getIndexColID());
				MainFinancialIndicatorStock stock=stockmap.get(key);
				if(stock==null){
					stock=new MainFinancialIndicatorStock();
					stockmap.put(key, stock);
				}
				stock.setName(item.getValue());
				
				continue;
			}
			if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("337")  ){//股东5
				Integer key=Integer.parseInt(item.getIndexColID())-1;
				MainFinancialIndicatorStock stock=stockmap.get(key);
				if(stock==null){
					stock=new MainFinancialIndicatorStock();
					stockmap.put(key, stock);
				}
				stock.setPrecent(item.getValue());
				continue;
			}
			if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("338")  ){//股东6
				Integer key=Integer.parseInt(item.getIndexColID());
				MainFinancialIndicatorStock stock=stockmap.get(key);
				if(stock==null){
					stock=new MainFinancialIndicatorStock();
					stockmap.put(key, stock);
				}
				stock.setName(item.getValue());
				
				continue;
			}
			if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("339")  ){//股东6
				Integer key=Integer.parseInt(item.getIndexColID())-1;
				MainFinancialIndicatorStock stock=stockmap.get(key);
				if(stock==null){
					stock=new MainFinancialIndicatorStock();
					stockmap.put(key, stock);
				}
				stock.setPrecent(item.getValue());
				continue;
			}
			if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("340")  ){//股东7
				Integer key=Integer.parseInt(item.getIndexColID());
				MainFinancialIndicatorStock stock=stockmap.get(key);
				if(stock==null){
					stock=new MainFinancialIndicatorStock();
					stockmap.put(key, stock);
				}
				stock.setName(item.getValue());
				
				continue;
			}
			if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("341")  ){//股东7
				Integer key=Integer.parseInt(item.getIndexColID())-1;
				MainFinancialIndicatorStock stock=stockmap.get(key);
				if(stock==null){
					stock=new MainFinancialIndicatorStock();
					stockmap.put(key, stock);
				}
				stock.setPrecent(item.getValue());
				continue;
			}
		}
		 Collection<MainFinancialIndicatorStock> valueCollection = stockmap.values();
		 List<MainFinancialIndicatorStock> result = new ArrayList<MainFinancialIndicatorStock>(valueCollection);
		
		return result;
	}


	@Override
	public List<MainFinancialIndicatorStock> getZczbData(MainFinancialIndicator condition) {
		String year = condition.getYear()+"";
		String month = String.format("%02d",condition.getMonth());
		String reportTime = year + "-" + month;
		String organl = condition.getOrg();
		int[] formKind = new int[1];
		if(condition.getReportType().intValue() == Base.reportgroup_type_simple){//补充表合并与单体都取单体的
			formKind[0] = 51010;//补充表单户
		}else{
			formKind[0] = 51010;//补充表单户
		}
		List<ReportIndexAndData> cellValues = reportDao.getReportValueBy(reportTime, organl, formKind);
		Map<Integer, MainFinancialIndicatorStock> stockmap=new HashMap<Integer, MainFinancialIndicatorStock>();
		for (ReportIndexAndData item : cellValues) {
			if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("343")  ){//股东1
				Integer key=Integer.parseInt(item.getIndexColID());
				MainFinancialIndicatorStock stock=stockmap.get(key);
				if(stock==null){
					stock=new MainFinancialIndicatorStock();
					stockmap.put(key, stock);
				}
				stock.setName(item.getValue());
				continue;
			}
			if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("344")  ){//股东1
				Integer key=Integer.parseInt(item.getIndexColID())-1;
				MainFinancialIndicatorStock stock=stockmap.get(key);
				if(stock==null){
					stock=new MainFinancialIndicatorStock();
					stockmap.put(key, stock);
				}
				stock.setPrecent(item.getValue());
				continue;
			}
			if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("345")  ){//股东2
				Integer key=Integer.parseInt(item.getIndexColID());
				MainFinancialIndicatorStock stock=stockmap.get(key);
				if(stock==null){
					stock=new MainFinancialIndicatorStock();
					stockmap.put(key, stock);
				}
				stock.setName(item.getValue());
				
				continue;
			}
			if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("346")  ){//股东2
				Integer key=Integer.parseInt(item.getIndexColID())-1;
				MainFinancialIndicatorStock stock=stockmap.get(key);
				if(stock==null){
					stock=new MainFinancialIndicatorStock();
					stockmap.put(key, stock);
				}
				stock.setPrecent(item.getValue());
				continue;
			}
			if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("347")  ){//股东3
				Integer key=Integer.parseInt(item.getIndexColID());
				MainFinancialIndicatorStock stock=stockmap.get(key);
				if(stock==null){
					stock=new MainFinancialIndicatorStock();
					stockmap.put(key, stock);
				}
				stock.setName(item.getValue());
				
				continue;
			}
			if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("348")  ){//股东3
				Integer key=Integer.parseInt(item.getIndexColID())-1;
				MainFinancialIndicatorStock stock=stockmap.get(key);
				if(stock==null){
					stock=new MainFinancialIndicatorStock();
					stockmap.put(key, stock);
				}
				stock.setPrecent(item.getValue());
				continue;
			}
			if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("349")  ){//股东4
				Integer key=Integer.parseInt(item.getIndexColID());
				MainFinancialIndicatorStock stock=stockmap.get(key);
				if(stock==null){
					stock=new MainFinancialIndicatorStock();
					stockmap.put(key, stock);
				}
				stock.setName(item.getValue());
				
				continue;
			}
			if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("350")  ){//股东4
				Integer key=Integer.parseInt(item.getIndexColID())-1;
				MainFinancialIndicatorStock stock=stockmap.get(key);
				if(stock==null){
					stock=new MainFinancialIndicatorStock();
					stockmap.put(key, stock);
				}
				stock.setPrecent(item.getValue());
				continue;
			}
			if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("351")  ){//股东5
				Integer key=Integer.parseInt(item.getIndexColID());
				MainFinancialIndicatorStock stock=stockmap.get(key);
				if(stock==null){
					stock=new MainFinancialIndicatorStock();
					stockmap.put(key, stock);
				}
				stock.setName(item.getValue());
				
				continue;
			}
			if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("352")  ){//股东5
				Integer key=Integer.parseInt(item.getIndexColID())-1;
				MainFinancialIndicatorStock stock=stockmap.get(key);
				if(stock==null){
					stock=new MainFinancialIndicatorStock();
					stockmap.put(key, stock);
				}
				stock.setPrecent(item.getValue());
				continue;
			}
			if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("353")  ){//股东6
				Integer key=Integer.parseInt(item.getIndexColID());
				MainFinancialIndicatorStock stock=stockmap.get(key);
				if(stock==null){
					stock=new MainFinancialIndicatorStock();
					stockmap.put(key, stock);
				}
				stock.setName(item.getValue());
				
				continue;
			}
			if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("354")  ){//股东6
				Integer key=Integer.parseInt(item.getIndexColID())-1;
				MainFinancialIndicatorStock stock=stockmap.get(key);
				if(stock==null){
					stock=new MainFinancialIndicatorStock();
					stockmap.put(key, stock);
				}
				stock.setPrecent(item.getValue());
				continue;
			}
			if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("355")  ){//股东7
				Integer key=Integer.parseInt(item.getIndexColID());
				MainFinancialIndicatorStock stock=stockmap.get(key);
				if(stock==null){
					stock=new MainFinancialIndicatorStock();
					stockmap.put(key, stock);
				}
				stock.setName(item.getValue());
				
				continue;
			}
			if(item.getIndexRowID().equals("0") && Common.notEmpty(item.getValue()) && item.getIndexColID().equals("356")  ){//股东7
				Integer key=Integer.parseInt(item.getIndexColID())-1;
				MainFinancialIndicatorStock stock=stockmap.get(key);
				if(stock==null){
					stock=new MainFinancialIndicatorStock();
					stockmap.put(key, stock);
				}
				stock.setPrecent(item.getValue());
				continue;
			}
		}
		 Collection<MainFinancialIndicatorStock> valueCollection = stockmap.values();
		 List<MainFinancialIndicatorStock> result = new ArrayList<MainFinancialIndicatorStock>(valueCollection);
		
		return result;
	}
	@Override
	public List<MainFinancialIndicatorStock> getSszbDataFT(
			MainFinancialIndicator condition) {
		// TODO Auto-generated method stub
		return mainFinancialIndicatorDao.getSszbDataFT(condition);
	}
	@Override
	public List<MainFinancialIndicatorStock> getZczbDataFT(
			MainFinancialIndicator condition) {
		// TODO Auto-generated method stub
		return mainFinancialIndicatorDao.getZczbDataFT(condition);
	}

	
	/**
	 * 根据你删除主要财务指标
	 * @param year
	 * @param month
	 * @param org
	 * @param reportType
	 */
	private void saveDeleteMainFinancialIndicator(Integer year,Integer month,String org,Integer reportType,HhUsers user){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<MainFinancialIndicator>  mfis = mainFinancialIndicatorDao.getMainFinancialIndicators(year, month, org, reportType);
		for(MainFinancialIndicator main : mfis){
			main.setIsdel(1);
			main.setLastModifyDate(df.format(new Date()));
			main.setLastModifyPersonId(user.getVcEmployeeId());
			main.setLastModifyPersonName(user.getVcName());
			List<MainFinancialIndicatorStock> stocks = mainFinancialIndicatorDao.getStockData(main.getId());
			for(MainFinancialIndicatorStock stock : stocks){
				stock.setIsdel(1);
				stock.setLastModifyDate(df.format(new Date()));
				stock.setLastModifyPersonId(user.getVcEmployeeId());
				stock.setLastModifyPersonName(user.getVcName());
				commonDao.saveOrUpdate(stock);
			}
			commonDao.saveOrUpdate(main);
		}
		
	}
	
	
	
}
