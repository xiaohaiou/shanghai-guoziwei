package com.softline.service.report.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.common.Base;
import com.softline.dao.report.ITaxPayDao;
import com.softline.dao.system.ICommonDao;
import com.softline.dao.system.ISystemDao;
import com.softline.entity.DataHrHeadcount;
import com.softline.entity.DataHrLaborproduction;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entity.PortalMsg;
import com.softline.entity.taxTask.DataTaxPay;
import com.softline.entity.taxTask.DataTaxPayDetailNow;
import com.softline.entity.taxTask.DataTaxPayDetailPrev;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.ITaxPayService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IPortalMsgService;
import com.softline.service.system.ISystemService;
import com.softline.util.ExcelCellValueValidate;
import com.softline.util.ExcelCellValueValidateResult;
import com.softline.util.MsgPage;

@Service("taxPayService")
/**
 * 
 * @author zy
 *
 */
public class TaxPayService implements ITaxPayService {

	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;

	@Autowired
	@Qualifier("taxPayDao")
	private ITaxPayDao taxPayDao;

	@Resource(name = "examineService")
	private IExamineService examineService;

	@Resource(name = "systemService")
	private ISystemService systemService;

	@Autowired
	@Qualifier("potalMsgService")
	private IPortalMsgService portalMsgService;

	@Autowired
	@Qualifier("systemDao")
	private ISystemDao systemDao;
	
	public boolean isTopCompany(String nNodeID){
		return taxPayDao.isTopCompany(nNodeID);
	}


	/**
	 * 一览画面查询
	 * 
	 * @param entity
	 *            查询实体
	 * @param curPageNum
	 *            当前页
	 * @param pageSize
	 *            页大小
	 * @param fun
	 *            机能分类
	 * @return
	 * 
	 *  优化 税务 填报页面海航物流下因数据量过大，查询缓慢
	 * 		当查询的为航海物流时，查询显示所有的实体数据。
	 * 
	 */
	public MsgPage getTaxPayListView(DataTaxPay entity, Integer curPageNum,
			Integer pageSize, Integer fun) {
		Integer totalRecords;
		List<DataTaxPay> list;
		MsgPage msgPage = new MsgPage();
		// 当前页开始记录
		int offset = msgPage.countOffset(curPageNum, pageSize);
//		if(this.isTopCompany(entity.getParentorg())){
//		// 总记录数
//		totalRecords = taxPayDao.getAllTaxPayListCount(entity, fun);
//		// 分页查询结果集		
//		list = taxPayDao.getAllTaxPayList(entity, offset,pageSize, fun);		
//	}else{
//		// 总记录数
//		totalRecords = taxPayDao.getTaxPayListCount(entity, fun);
//		// 分页查询结果集		
//		list = taxPayDao.getTaxPayList(entity, offset,pageSize, fun);
//	}
	// 总记录数
	totalRecords = taxPayDao.getTaxPayListCount(entity, fun);
	// 分页查询结果集		
	list = taxPayDao.getTaxPayList(entity, offset,pageSize, fun);
	
	
	
		msgPage.setPageNum(curPageNum);
		msgPage.setPageSize(pageSize);
		msgPage.setTotalRecords(totalRecords);
		msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
		msgPage.setList(list);
		return msgPage;
	}

	/**
	 * 单个ID查询
	 * 
	 * @param id
	 *            查询ID
	 * @return
	 */
	public DataTaxPay getTaxPay(Integer id) {
		return taxPayDao.getTaxPay(id);
	}

	/**
	 * 保存更新
	 * 
	 * @param entity
	 * @param listOb
	 * @param use
	 * @return
	 */
	public CommitResult saveTaxPay(DataTaxPay entity,List<List<Object>> listOb, HhUsers use,String isConfirm) {
		
		CommitResult result=new CommitResult();
		ExcelCellValueValidate excelValidate = new ExcelCellValueValidate();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 校验
		result = saveTaxPayCheck(entity);
		if (!result.isResult()) {
			return result;
		}
		if (taxPayDao.isvirtual(entity)){
			result =CommitResult.createErrorResult("该公司为虚拟公司，无法导入！");
			return result;
		}
		result=checkVales(entity.getHnTax());
        if(!result.isResult()){
     	   return result;
        }
		// 导入的场合
		if (entity.getId() == null) {
			// 基本信息
			entity.setIsdel(0);
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(use.getVcEmployeeId());
			entity.setCreatePersonName(use.getVcName());
			// 导入数据处理
			// 本期实缴、去年同期实缴、年度累计实缴、去年同期年度累计
			int nowCn = 0, prevCn = 0;

			// 对应行数取得
			for (int i = 1; i < listOb.size(); i++) {
				List<Object> lo = listOb.get(i);
				if (lo.get(0).equals("本期实缴")) {
					nowCn = i;
					continue;
				}
				if (lo.get(0).equals("去年同期实缴")) {
					prevCn = i;
					continue;
				}
			}
	/*		if(null==(isConfirm)){
				for (int i = nowCn; i < prevCn; i++) {
					List<Object> los = listOb.get(i);
					 for (int j = 2; j < los.size(); j++) {
							if(Double.parseDouble(String.valueOf(los.get(j)))>1000){
					                result=CommitResult.createErrorResult("当前导入数据单位为万元，请确认数据是否无误");
					                result.setEntity("1");
									return result;
							}
						}
				}
				for (int i = prevCn; i <listOb.size(); i++) {
					List<Object> lo = listOb.get(i);
					for (int j = 2; j < lo.size(); j++) {
						if(Double.parseDouble(String.valueOf(lo.get(j)))>1000){
							 result=CommitResult.createErrorResult("当前导入数据单位为万元，请确认数据是否无误");
							 result.setEntity("1");
							 return result;
						}
					}
				}
			}*/
			// 纳税数据保存

			HhOrganInfoTreeRelation node = systemDao.getTreeOrganInfoNode(
					Base.financetype, entity.getOrg());
			entity.setParentorg(node.getVcOrganId());
			entity.setMonth(entity.getYear().substring(entity.getYear().length()-2));		
			// 本期实缴明细数据保存
			double now_sum = 0.0d;
			Set<DataTaxPayDetailNow> dataTaxPayDetailNowSet = new HashSet<DataTaxPayDetailNow>();
			for (int i = nowCn; i < prevCn; i++) {
				List<Object> lo = listOb.get(i);
				DataTaxPayDetailNow dataTaxPayDetailNow = new DataTaxPayDetailNow();
				dataTaxPayDetailNow.setYear(entity.getYear());
				dataTaxPayDetailNow.setMonth(entity.getYear().substring(entity.getYear().length()-2));
				if(!entity.getOrgName().trim().equals(String.valueOf(lo.get(1)))){
					result = CommitResult.createErrorResult("公司名字与所选公司不符合，无法导入！");
					return result;
				}
				ExcelCellValueValidateResult v1 = excelValidate.validate(String.valueOf(lo.get(1)), "NotEmpty", "varChar", 255);
				if(v1.isResult()){
					dataTaxPayDetailNow.setOrgName(String.valueOf(lo.get(1)));
				}else{
					result=CommitResult.createErrorResult("第" + (nowCn+2) + "行,第3列" + "数值有问题，请重新输写");
					return result;
				}
				if(null==(isConfirm)){
				 for (int j = 2; j < lo.size(); j++) {
					 if(!String.valueOf(lo.get(j)).equals("")){
						if(Double.parseDouble(String.valueOf(lo.get(j)))>1000){
				                result=CommitResult.createErrorResult("当前导入数据单位为万元，请确认数据是否无误");
				                result.setEntity("1");
								return result;
						}
					 }
					}
				}
				// 根据公司名查公司编号
				String orgID = systemDao.getFinancenNodeID(
						String.valueOf(lo.get(1)), Base.financetype);
				dataTaxPayDetailNow.setOrg(orgID);
				if (orgID != null) {
					HhOrganInfoTreeRelation node1 = systemDao.getTreeOrganInfoNode(Base.financetype, orgID);
					dataTaxPayDetailNow.setParentorg(node1.getVcOrganId());
				}
                v1= excelValidate.validate(String.valueOf(lo.get(2)), "Empty", "double", 50);
                if(v1.isResult()){
    				dataTaxPayDetailNow.setBusinessTax(String.valueOf(lo.get(2)));
				}else{
					result=CommitResult.createErrorResult("第" + (nowCn+2) + "行,第4列" + "数值有问题，请重新输写");
					return result;
				}
                v1= excelValidate.validate(String.valueOf(lo.get(2)), "Empty", "double", 50);
                if(v1.isResult()){
    				dataTaxPayDetailNow.setBusinessTax(String.valueOf(lo.get(2)));
				}else{
					result=CommitResult.createErrorResult("第" + (nowCn+2) + "行,第4列" + "数值有问题，请重新输写");
					return result;
				}
                v1= excelValidate.validate(String.valueOf(lo.get(3)), "Empty", "double", 50);
                if(v1.isResult()){
    				dataTaxPayDetailNow.setValueAddedTax(String.valueOf(lo.get(3)));
				}else{
					result=CommitResult.createErrorResult("第" + (nowCn+2) + "行,第5列" + "数值有问题，请重新输写");
					return result;
				}
                v1= excelValidate.validate(String.valueOf(lo.get(4)), "Empty", "double", 50);
                if(v1.isResult()){
    				dataTaxPayDetailNow.setConsumptionTax(String.valueOf(lo.get(4)));
				}else{
					result=CommitResult.createErrorResult("第" + (nowCn+2) + "行,第6列" + "数值有问题，请重新输写");
					return result;
				}
                v1= excelValidate.validate(String.valueOf(lo.get(5)), "Empty", "double", 50);
                if(v1.isResult()){
    				dataTaxPayDetailNow.setCesTax(String.valueOf(lo.get(5)));
				}else{
					result=CommitResult.createErrorResult("第" + (nowCn+2) + "行,第7列" + "数值有问题，请重新输写");
					return result;
				}
                v1= excelValidate.validate(String.valueOf(lo.get(6)), "Empty", "double", 50);
                if(v1.isResult()){
    				dataTaxPayDetailNow.setImportVat(String.valueOf(lo.get(6)));
				}else{
					result=CommitResult.createErrorResult("第" + (nowCn+2) + "行,第8列" + "数值有问题，请重新输写");
					return result;
				}
                v1= excelValidate.validate(String.valueOf(lo.get(7)), "Empty", "double", 50);
                if(v1.isResult()){
    				dataTaxPayDetailNow.setTariff(String.valueOf(lo.get(7)));
				}else{
					result=CommitResult.createErrorResult("第" + (nowCn+2) + "行,第9列" + "数值有问题，请重新输写");
					return result;
				}
                v1= excelValidate.validate(String.valueOf(lo.get(8)), "Empty", "double", 50);
                if(v1.isResult()){
    				dataTaxPayDetailNow.setEincomeTax(String.valueOf(lo.get(8)));
				}else{
					result=CommitResult.createErrorResult("第" + (nowCn+2) + "行,第10列" + "数值有问题，请重新输写");
					return result;
				}
                v1= excelValidate.validate(String.valueOf(lo.get(9)), "Empty", "double", 50);
                if(v1.isResult()){
    				dataTaxPayDetailNow.setWithholdTax(String.valueOf(lo.get(9)));
				}else{
					result=CommitResult.createErrorResult("第" + (nowCn+2) + "行,第11列" + "数值有问题，请重新输写");
					return result;
				}
                v1= excelValidate.validate(String.valueOf(lo.get(10)), "Empty", "double", 50);
                if(v1.isResult()){
    				dataTaxPayDetailNow.setPincomeTax(String.valueOf(lo.get(10)));
				}else{
					result=CommitResult.createErrorResult("第" + (nowCn+2) + "行,第12列" + "数值有问题，请重新输写");
					return result;
				}
                v1= excelValidate.validate(String.valueOf(lo.get(11)), "Empty", "double", 50);
                if(v1.isResult()){
    				dataTaxPayDetailNow.setHousingTax(String.valueOf(lo.get(11)));
				}else{
					result=CommitResult.createErrorResult("第" + (nowCn+2) + "行,第13列" + "数值有问题，请重新输写");
					return result;
				}
                v1= excelValidate.validate(String.valueOf(lo.get(12)), "Empty", "double", 50);
                if(v1.isResult()){
    				dataTaxPayDetailNow.setDeedTax(String.valueOf(lo.get(12)));
				}else{
					result=CommitResult.createErrorResult("第" + (nowCn+2) + "行,第14列" + "数值有问题，请重新输写");
					return result;
				}
                v1= excelValidate.validate(String.valueOf(lo.get(13)), "Empty", "double", 50);
                if(v1.isResult()){
    				dataTaxPayDetailNow.setLandTax(String.valueOf(lo.get(13)));
				}else{
					result=CommitResult.createErrorResult("第" + (nowCn+2) + "行,第15列" + "数值有问题，请重新输写");
					return result;
				}
                v1= excelValidate.validate(String.valueOf(lo.get(14)), "Empty", "double", 50);
                if(v1.isResult()){
    				dataTaxPayDetailNow.setLandVat(String.valueOf(lo.get(14)));
				}else{
					result=CommitResult.createErrorResult("第" + (nowCn+2) + "行,第16列" + "数值有问题，请重新输写");
					return result;
				}
                v1= excelValidate.validate(String.valueOf(lo.get(15)), "Empty", "double", 50);
                if(v1.isResult()){
    				dataTaxPayDetailNow.setStampTax(String.valueOf(lo.get(15)));
				}else{
					result=CommitResult.createErrorResult("第" + (nowCn+2) + "行,第17列" + "数值有问题，请重新输写");
					return result;
				}
                v1= excelValidate.validate(String.valueOf(lo.get(16)), "Empty", "double", 50);
                if(v1.isResult()){
    				dataTaxPayDetailNow.setOtherTax(String.valueOf(lo.get(16)));
				}else{
					result=CommitResult.createErrorResult("第" + (nowCn+2) + "行,第18列" + "数值有问题，请重新输写");
					return result;
				}
                v1= excelValidate.validate(String.valueOf(lo.get(17)), "Empty", "double", 50);
                if(v1.isResult()){
    				dataTaxPayDetailNow.setSum(String.valueOf(lo.get(17)));
				}else{
					result=CommitResult.createErrorResult("第" + (nowCn+2) + "行,第19列" + "数值有问题，请重新输写");
					return result;
				}
				dataTaxPayDetailNow.setIsdel(0);
				dataTaxPayDetailNow.setType(3);
				dataTaxPayDetailNow.setDataTaxPay(entity);
				dataTaxPayDetailNowSet.add(dataTaxPayDetailNow);

				now_sum += new BigDecimal(String.valueOf(lo.get(17))).doubleValue();
							
			}
				entity.setDataTaxPayDetailNows(dataTaxPayDetailNowSet);
				entity.setNowPay(String.valueOf(now_sum));
			// 去年同期实缴明细数据保存
			double pre_sum = 0.0d;
			Set<DataTaxPayDetailPrev> dataTaxPayDetailPrevSet = new HashSet<DataTaxPayDetailPrev>();
			for (int i = prevCn; i <listOb.size(); i++) {
				List<Object> lo = listOb.get(i);
				System.out.println(lo.size());
				if(lo.size()<2){
					break;
				}
				DataTaxPayDetailPrev dataTaxPayDetailPrev = new DataTaxPayDetailPrev();
				dataTaxPayDetailPrev.setYear(entity.getYear());
				dataTaxPayDetailPrev.setMonth(entity.getYear().substring(entity.getYear().length()-2));
				if(!entity.getOrgName().trim().equals(String.valueOf(lo.get(1)))){
					result = CommitResult.createErrorResult("公司名字与所选公司不符合，无法导入！");
					return result;
				}
				if(null==(isConfirm)){
					for (int j = 2; j < lo.size(); j++) {
						if(!String.valueOf(lo.get(j)).equals("")){	
							if(Double.parseDouble(String.valueOf(lo.get(j)))>1000){
								 result=CommitResult.createErrorResult("当前导入数据单位为万元，请确认数据是否无误");
								 result.setEntity("1");
								 return result;
							}
						}
					}
				}
				ExcelCellValueValidateResult v1 = excelValidate.validate(String.valueOf(lo.get(1)), "NotEmpty", "varChar", 255);
				if(v1.isResult()){
					dataTaxPayDetailPrev.setOrgName(String.valueOf(lo.get(1)));
				}else{
					result=CommitResult.createErrorResult("第" + (prevCn+2) + "行,第3列" + "数值有问题，请重新输写");
					return result;
				}

				// 根据公司名查公司编号
				String orgID = systemDao.getFinancenNodeID(
						String.valueOf(lo.get(1)), Base.financetype);
				dataTaxPayDetailPrev.setOrg(orgID);
				if (orgID != null) {
					HhOrganInfoTreeRelation node1 = systemDao
							.getTreeOrganInfoNode(Base.financetype, orgID);
					dataTaxPayDetailPrev.setParentorg(node1.getVcOrganId());
				}
				v1 = excelValidate.validate(String.valueOf(lo.get(2)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxPayDetailPrev.setBusinessTax(String.valueOf(lo.get(2)));
				}else{
					result=CommitResult.createErrorResult("第" + (prevCn+2) + "行,第4列" + "数值有问题，请重新输写");
					return result;
				}		
				v1 = excelValidate.validate(String.valueOf(lo.get(3)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxPayDetailPrev.setValueAddedTax(String.valueOf(lo.get(3)));
				}else{
					result=CommitResult.createErrorResult("第" + (prevCn+2) + "行,第5列" + "数值有问题，请重新输写");
					return result;
				}
				v1 = excelValidate.validate(String.valueOf(lo.get(4)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxPayDetailPrev.setConsumptionTax(String.valueOf(lo.get(4)));
				}else{
					result=CommitResult.createErrorResult("第" + (prevCn+2) + "行,第6列" + "数值有问题，请重新输写");
					return result;
				}
				v1 = excelValidate.validate(String.valueOf(lo.get(5)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxPayDetailPrev.setCesTax(String.valueOf(lo.get(5)));
				}else{
					result=CommitResult.createErrorResult("第" + (prevCn+2) + "行,第7列" + "数值有问题，请重新输写");
					return result;
				}
				v1 = excelValidate.validate(String.valueOf(lo.get(6)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxPayDetailPrev.setImportVat(String.valueOf(lo.get(6)));
				}else{
					result=CommitResult.createErrorResult("第" + (prevCn+2) + "行,第8列" + "数值有问题，请重新输写");
					return result;
				}
				v1 = excelValidate.validate(String.valueOf(lo.get(7)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxPayDetailPrev.setTariff(String.valueOf(lo.get(7)));
				}else{
					result=CommitResult.createErrorResult("第" + (prevCn+2) + "行,第9列" + "数值有问题，请重新输写");
					return result;
				}
				v1 = excelValidate.validate(String.valueOf(lo.get(8)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxPayDetailPrev.setEincomeTax(String.valueOf(lo.get(8)));
				}else{
					result=CommitResult.createErrorResult("第" + (prevCn+2) + "行,第10列" + "数值有问题，请重新输写");
					return result;
				}
				v1 = excelValidate.validate(String.valueOf(lo.get(9)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxPayDetailPrev.setWithholdTax(String.valueOf(lo.get(9)));
				}else{
					result=CommitResult.createErrorResult("第" + (prevCn+2) + "行,第11列" + "数值有问题，请重新输写");
					return result;
				}
				v1 = excelValidate.validate(String.valueOf(lo.get(10)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxPayDetailPrev.setPincomeTax(String.valueOf(lo.get(10)));
				}else{
					result=CommitResult.createErrorResult("第" + (prevCn+2) + "行,第12列" + "数值有问题，请重新输写");
					return result;
				}
				v1 = excelValidate.validate(String.valueOf(lo.get(11)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxPayDetailPrev.setHousingTax(String.valueOf(lo.get(11)));
				}else{
					result=CommitResult.createErrorResult("第" + (prevCn+2) + "行,第13列" + "数值有问题，请重新输写");
					return result;
				}
				v1 = excelValidate.validate(String.valueOf(lo.get(12)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxPayDetailPrev.setDeedTax(String.valueOf(lo.get(12)));
				}else{
					result=CommitResult.createErrorResult("第" + (prevCn+2) + "行,第14列" + "数值有问题，请重新输写");
					return result;
				}
				v1 = excelValidate.validate(String.valueOf(lo.get(13)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxPayDetailPrev.setLandTax(String.valueOf(lo.get(13)));
				}else{
					result=CommitResult.createErrorResult("第" + (prevCn+2) + "行,第15列" + "数值有问题，请重新输写");
					return result;
				}
				v1 = excelValidate.validate(String.valueOf(lo.get(14)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxPayDetailPrev.setLandVat(String.valueOf(lo.get(14)));
				}else{
					result=CommitResult.createErrorResult("第" + (prevCn+2) + "行,第16列" + "数值有问题，请重新输写");
					return result;
				}
				v1 = excelValidate.validate(String.valueOf(lo.get(15)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxPayDetailPrev.setStampTax(String.valueOf(lo.get(15)));
				}else{
					result=CommitResult.createErrorResult("第" + (prevCn+2) + "行,第17列" + "数值有问题，请重新输写");
					return result;
				}
				v1 = excelValidate.validate(String.valueOf(lo.get(16)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxPayDetailPrev.setOtherTax(String.valueOf(lo.get(16)));
				}else{
					result=CommitResult.createErrorResult("第" + (prevCn+2) + "行,第18列" + "数值有问题，请重新输写");
					return result;
				}
				v1 = excelValidate.validate(String.valueOf(lo.get(17)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxPayDetailPrev.setSum(String.valueOf(lo.get(17)));
				}else{
					result=CommitResult.createErrorResult("第" + (prevCn+2) + "行,第19列" + "数值有问题，请重新输写");
					return result;
				}
				dataTaxPayDetailPrev.setIsdel(0);
				dataTaxPayDetailPrev.setDataTaxPay(entity);
				dataTaxPayDetailPrevSet.add(dataTaxPayDetailPrev);
				
				pre_sum += new BigDecimal(String.valueOf(lo.get(17))).doubleValue();
			}
				entity.setDataTaxPayDetailPrevs(dataTaxPayDetailPrevSet);
				entity.setPrevPay(String.valueOf(pre_sum));
		} else {
			entity.setIsdel(0);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
		}

		commonDao.saveOrUpdate(entity);
		// 工作提醒内容追加
		if (entity.getStatus().equals(Base.examstatus_2)) {
			portalMsgService.savePortalMsg(new PortalMsg("纳税数据需要审核", "财务", df
					.format(new Date()), 0, 0, 0, entity.getCreatePersonName(),
					entity.getCreateDate(), entity.getLastModifyPersonName(),
					entity.getLastModifyDate(), entity.getOrg(), systemService
							.getParentBynNodeID(entity.getOrg(),
									Base.financetype), "bimWork_nssjsh_exam",
					"data_taxPay", entity.getId().toString()));
		}
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}

	private CommitResult checkVales(String a) {
		CommitResult result=new CommitResult();
		String reg = "^-?([1-9]\\d*|0)(\\.\\d+)?$";
		if(a.matches(reg)){
			result.setResult(true);
			return result;
		}else{
			result=CommitResult.createErrorResult("海南省纳税额(万元)此数值有问题，请重新输入");
			return result;
		}
	}


	/**
	 * 删除
	 * 
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteTaxPay(Integer id, HhUsers use) {

		DataTaxPay entity = taxPayDao.getTaxPay(id);
		CommitResult result = new CommitResult();
		if (!taxPayDao.checkCanEdit(entity)) {
			result = CommitResult.createErrorResult("该数据已经被上报不能修改");
			return result;
		}
		if (entity != null) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entity.setIsdel(1);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
			commonDao.delete(entity);
			// 工作提醒内容删除
			portalMsgService.updatePortalMsg("data_taxPay", entity.getId()
					.toString());
		}
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}

	/**
	 * 审核校验判断是否还能够审核，可能别人已经审核过了
	 * 
	 * @param entityID
	 *            采购ID
	 * @return
	 */
	public CommitResult saveTaxPayExamineCheck(Integer entityID) {
		CommitResult result = new CommitResult();
		DataTaxPay entity = taxPayDao.getTaxPay(entityID);
		if (entity == null) {
			result = CommitResult.createErrorResult("该数据已被删除");
			return result;
		}
	/*	if (!entity.getStatus().equals(Base.examstatus_2)) {
			result = CommitResult.createErrorResult("该信息已不用审核");
			return result;
		}*/
		result.setResult(true);
		return result;
	}

	/**
	 * 审核
	 * 
	 * @param entityID
	 *            实体ID
	 * @param examStr
	 *            审核备注
	 * @param examResult
	 *            审核意见
	 * @param use
	 * @return
	 */
	public CommitResult saveTaxPayExamine(Integer entityID, String examStr,
			Integer examResult, HhUsers use) {

		DataTaxPay entity = getTaxPay(entityID);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result = new CommitResult();
		result = saveTaxPayExamineCheck(entityID);
		if (!result.isResult()) {
			return result;
		}
		// 审核不通过
		if (examResult.equals(Base.examresult_1)) {
			entity.setStatus(Base.examstatus_3);
			// 审核通过
		} else if (examResult.equals(Base.examresult_2)) {
			entity.setStatus(Base.examstatus_4);
		}
		entity.setAuditorDate(df.format(new Date()));
		entity.setAuditorPersonId(use.getVcEmployeeId());
		entity.setAuditorPersonName(use.getVcName());
		// 保存实体信息
		commonDao.saveOrUpdate(entity);
		Integer examineentityid = entity.getId();
		// 保存审核信息
		examineService.saveExamine(examineentityid, Base.examkind_taxpay, use,
				examStr, examResult);
		// 工作提醒内容删除
		portalMsgService.updatePortalMsg("data_taxPay", entity.getId()
				.toString());
		result.setResult(true);
		return result;
	}

	/**
	 * 保存校验
	 * 
	 * @param entity
	 * @return
	 */
	private CommitResult saveTaxPayCheck(DataTaxPay entity) {
		CommitResult result = new CommitResult();
		if (!taxPayDao.saveTaxPayRepeatCheck(entity)) {
			result = CommitResult.createErrorResult(entity.getYear() + "年"
					+ entity.getMonth() + "月已经有数据，请先删除再导入");
			return result;
		}
		if (!taxPayDao.checkCanEdit(entity)) {
			result = CommitResult.createErrorResult("该数据已经被上报不能修改");
			return result;
		}
		result.setResult(true);
		return result;
	}

	/**
	 * 汇总数据
	 * 虚拟公司数据存在
	 */
	@Override
	public List<DataTaxPay> savefindCollectList(DataTaxPay payCount) {
		// TODO Auto-generated method stub
		List<DataTaxPay> list = taxPayDao.getHrFormsListCollectView(payCount);	
		return list;
		
	}
	/**
	 * 汇总数据
	 * 虚拟公司不存在
	 */
	@Override
	public List<DataTaxPay> savefindCollectList2(DataTaxPay entity) {
		// TODO Auto-generated method stub
		
		List<DataTaxPay> list = taxPayDao.getHrFormsListCollectView2(entity);	
		return list;
	}
	
	@Override
	public void saveself(DataTaxPay entity) {
		// TODO Auto-generated method stub
		commonDao.saveOrUpdate(entity);
		
	}

	
	/**
	 * 汇总时保存关联表的虚拟公司的汇总数据
	 * 虚拟公司数据存在时
	 */
	@Override
	public void savecollect(DataTaxPay entity) {
		// TODO Auto-generated method stub
		int newNowId = taxPayDao.getNowId(entity);
		int newPreId = taxPayDao.getPrveId(entity);
		List<DataTaxPayDetailNow> list2 = taxPayDao.getnewListNow(entity,newNowId);
		List<DataTaxPayDetailPrev> list3 = taxPayDao.getnewListPre(entity,newPreId);
		
		if(list2.size()!=0){
			for(DataTaxPayDetailNow entity1 : list2 ){
				commonDao.saveOrUpdate(entity1);
			}			
		}
		
		if(list3.size()!=0){
			for(DataTaxPayDetailPrev entity1 : list3 ){
				commonDao.saveOrUpdate(entity1);
			}		
		}
		
	}
	
	/**
	 * 汇总时保存关联表的虚拟公司的汇总数据
	 * 虚拟公司数据不存在时
	 */
	@Override
	public void savecollect2(DataTaxPay entity) {
		// TODO Auto-generated method stub
		List<DataTaxPayDetailNow> list2 = taxPayDao.getnewListNow2(entity);
		List<DataTaxPayDetailPrev> list3 = taxPayDao.getnewListPre2(entity);
		
		if(list2.size()!=0){
			for(DataTaxPayDetailNow entity1 : list2 ){
				commonDao.saveOrUpdate(entity1);
			}			
		}
		
		if(list3.size()!=0){
			for(DataTaxPayDetailPrev entity1 : list3 ){
				commonDao.saveOrUpdate(entity1);
			}		
		}
	}
	
	/**
	 * 保存汇总数据
	 */
	public CommitResult saveCollectList(DataTaxPay entity, HhUsers use,String op) {
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DataHrHeadcount dhh = new DataHrHeadcount();
		DataHrLaborproduction dhl = new DataHrLaborproduction();
		CommitResult result=new CommitResult();
		
		if(entity.getId()!=null){
			entity.setIsdel(0);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
			commonDao.saveOrUpdate(entity);	
			result.setResult(true);
		}else{
			result = CommitResult.createErrorResult("数据不存在，请重新选择后进行操作！");
		}

		return result;
		
	}
	
	

	@Override
	public Object saveReportCollectList(DataTaxPay entity, HhUsers use,
			String string) {
		// TODO Auto-generated method stub
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DataHrHeadcount dhh = new DataHrHeadcount();
		DataHrLaborproduction dhl = new DataHrLaborproduction();
		CommitResult result=new CommitResult();
		
		if(entity.getId()!=null){
			entity.setIsdel(0);
			entity.setReportDate(df.format(new Date()));
			entity.setReportPersonId(use.getVcEmployeeId());
			entity.setReportPersonName(use.getVcName());
			
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
			entity.setStatus(Base.examstatus_2);
			commonDao.saveOrUpdate(entity);	

			result.setResult(true);
		}else{
			result = CommitResult.createErrorResult("数据不存在，请重新选择后进行操作！");		
		}

		return result;
	}

	@Override
	public int getStatus(Integer id) {
		// TODO Auto-generated method stub
		int status = taxPayDao.getStatus(id);
		return status;
	}

	@Override
	public List<DataTaxPayDetailNow> getDetailNow(DataTaxPay entityview,Integer id) {
		// TODO Auto-generated method stub
		List<DataTaxPayDetailNow>  list  = taxPayDao.getDetailNow(entityview,id);
		return  list;
	}

	@Override
	public List<DataTaxPayDetailPrev> getDetailPre(DataTaxPay entityview,Integer id) {
		// TODO Auto-generated method stub
		List<DataTaxPayDetailPrev>  list  = taxPayDao.getDetailPre(entityview,id);
		return  list;
	}

	@Override
	public List<DataTaxPay> getTaxSaveId(DataTaxPay entity) {
		// TODO Auto-generated method stub
		List<DataTaxPay> list = taxPayDao.getTaxSaveId(entity);
		return list;
	}

	
	@Override
	public int getSumDataInfo(DataTaxPay beanIn,DataTaxPay beanOut){
		
		if(null==beanIn || "".equals(beanIn.getOrg()) || null == beanIn.getOrg())
			return -1;
			
		/*HhOrganInfoTreeRelation hhOrganInfoTreeRelation =systemDao.getTopTreeOrganInfoNodeStr(Base.financetype,beanIn.getOrg());
			
		if(null == hhOrganInfoTreeRelation || null == hhOrganInfoTreeRelation.getVcOrganId())
			return -1;
			
		beanIn.setParentorg(hhOrganInfoTreeRelation.getVcOrganId());*/

		List<DataTaxPay> list=this.savefindCollectList2(beanIn);
		
        DecimalFormat df = new DecimalFormat("#.00");
		
		for(Object obj : list) {
			Object[] item = (Object[])obj;
			beanOut.setNowPay(item[0]==null?"0":df.format(item[0]).toString());
			beanOut.setPrevPay(item[1]==null?"0":df.format(item[1]).toString());
		}				
		
		return 0;
	}
	
		
	@Override
	public MsgPage getTaxNoCreateCompanyList(String reportTime, String authdata,
			String org, Integer formKind, Integer CompanyKind,
			Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = taxPayDao.getTaxNoCreateCompanyList(reportTime, authdata, org, formKind, CompanyKind, null, null).size();
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<Object[]> list = taxPayDao.getTaxNoCreateCompanyList(reportTime, authdata, org, formKind, CompanyKind, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}
	/* (non-Javadoc)
	 * @see com.softline.service.report.ITaxPayService#getAdjustAccountRemindCompanyList(java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public MsgPage getTaxNoRemindCompanyList(String reportTime, String authdata,
			String org, Integer formKind, Integer CompanyKind,
			Integer curPageNum, Integer pageSize,String isShowAll) {
		MsgPage msgPage = new MsgPage(); 
		if("all".equals(isShowAll)){
			//总记录数
			Integer totalRecords = taxPayDao.getTaxNoRemindCompanyList(reportTime, authdata, org, formKind, CompanyKind, null, null).size();
			//当前页开始记录
			int offset = msgPage.countOffset(curPageNum,pageSize);  
			//分页查询结果集
			List<Object[]> list = taxPayDao.getTaxNoRemindCompanyList(reportTime, authdata, org, formKind, CompanyKind, null, null);
			msgPage.setPageNum(curPageNum);
			msgPage.setPageSize(pageSize);
			msgPage.setTotalRecords(totalRecords);
			msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
			msgPage.setList(list);  	
		}else{
			//总记录数
			Integer totalRecords = taxPayDao.getTaxNoRemindCompanyList(reportTime, authdata, org, formKind, CompanyKind, null, null).size();
			//当前页开始记录
			int offset = msgPage.countOffset(curPageNum,pageSize);  
			//分页查询结果集
			List<Object[]> list = taxPayDao.getTaxNoRemindCompanyList(reportTime, authdata, org, formKind, CompanyKind, offset, pageSize);
			msgPage.setPageNum(curPageNum);
			msgPage.setPageSize(pageSize);
			msgPage.setTotalRecords(totalRecords);
			msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
			msgPage.setList(list);    
		}
        return msgPage;
	}
	
	@Override
	public List getTaxNoCreateCompanyList(String reportTime, String authdata,
			String org) {
		List<Object[]> list = taxPayDao.getTaxNoCreateCompanyList(reportTime,authdata, org,null, null,null,null);
		return list;
	}


	public List<DataTaxPay> getSumDataList(DataTaxPay entity) {
	
		return taxPayDao.getSumDataLists(entity);
	}


}
