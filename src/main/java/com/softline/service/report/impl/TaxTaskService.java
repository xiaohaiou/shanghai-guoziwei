package com.softline.service.report.impl;

import java.text.DateFormat;
import java.text.DecimalFormat;
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
import com.softline.dao.report.ITaxTaskDao;
import com.softline.dao.system.ICommonDao;
import com.softline.dao.system.ISystemDao;
import com.softline.entity.DataHrHeadcount;
import com.softline.entity.DataHrLaborproduction;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entity.PortalMsg;
import com.softline.entity.taxTask.DataTaxTask;
import com.softline.entity.taxTask.DataTaxTaskDetailFavouredPolicy;
import com.softline.entity.taxTask.DataTaxTaskDetailInTaxReturn;
import com.softline.entity.taxTask.DataTaxTaskDetailTaxPlan;
import com.softline.entity.taxTask.DataTaxTaskDetailTaxReturn;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.ITaxTaskService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IPortalMsgService;
import com.softline.service.system.ISystemService;
import com.softline.util.ExcelCellValueValidate;
import com.softline.util.ExcelCellValueValidateResult;
import com.softline.util.MsgPage;

@Service("taxTaskService")
/**
 * 
 * @author zy
 *
 */
public class TaxTaskService implements ITaxTaskService {

	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;

	@Autowired
	@Qualifier("taxTaskDao")
	private ITaxTaskDao taxTaskDao;

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
	 */
	public MsgPage getTaxTaskListView(DataTaxTask entity, Integer curPageNum,
			Integer pageSize, Integer fun) {
		MsgPage msgPage = new MsgPage();
		// 总记录数
		Integer totalRecords = taxTaskDao.getTaxTaskListCount(entity, fun);
		// 当前页开始记录
		int offset = msgPage.countOffset(curPageNum, pageSize);
		// 分页查询结果集
		List<DataTaxTask> list = taxTaskDao.getTaxTaskList(entity, offset,
				pageSize, fun);
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
	public DataTaxTask getTaxTask(Integer id) {
		return taxTaskDao.getTaxTask(id);
	}

	/**
	 * 保存更新
	 * 
	 * @param entity
	 * @param listOb
	 * @param use
	 * @return
	 */
	public CommitResult saveTaxTask(DataTaxTask entity,List<List<Object>> listOb, HhUsers use,String isConfirm) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 校验
		CommitResult result=new CommitResult();
		ExcelCellValueValidate excelValidate = new ExcelCellValueValidate();
		result = saveTaxTaskCheck(entity);
		if (!result.isResult()) {
			return result;
		}
		if (taxTaskDao.isvirtual(entity)){
			result =CommitResult.createErrorResult("该公司为虚拟公司，无法导入！");
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
			// 合计行数
			int sumCn = 0;
			// 纳税筹划、优惠政策申请、税收返还、非税返还的行数
			int tpCn = 0, fpCn = 0, trCn = 0, itrCn = 0;
			// 小计的行数
			int subtotal1 = 0, subtotal2 = 0, subtotal3 = 0, subtotal4 = 0, subtotalCn = 0;
			// 对应行数取得
			for (int i = 0; i < listOb.size(); i++) {
				List<Object> lo = listOb.get(i);
				if (lo.get(0).equals("纳税筹划")) {
					tpCn = i;
					continue;
				}
				if (lo.get(0).equals("优惠政策申请")) {
					fpCn = i;
					continue;
				}
				if (lo.get(0).equals("税收返还")) {
					trCn = i;
					continue;
				}
				if (lo.get(0).equals("非税返还")) {
					itrCn = i;
					continue;
				}
				if (lo.get(0).equals("合计")) {
					sumCn = i;
					continue;
				}
				if (lo.get(0).equals("小计")) {
					subtotalCn++;
					if (subtotalCn == 1) {
						subtotal1 = i;
					} else if (subtotalCn == 2) {
						subtotal2 = i;
					} else if (subtotalCn == 3) {
						subtotal3 = i;
					} else if (subtotalCn == 4) {
						subtotal4 = i;
					}
					continue;
				}
			}
			// 节税任务数据保存
			ExcelCellValueValidateResult v1 = excelValidate.validate(String.valueOf(listOb.get(subtotal1).get(1)), "Empty", "double", 50);
			if(v1.isResult()){
				entity.setTaxPlan(String.valueOf(listOb.get(subtotal1).get(1)));
			}else{
				result=CommitResult.createErrorResult("第" + (subtotal1+2) + "行,第2列" + "数值有问题，请重新输写");
				return result;
			}
			v1 = excelValidate.validate(String.valueOf(listOb.get(subtotal2).get(1)), "Empty", "double", 50);
			if(v1.isResult()){
				entity.setFavouredPolicy(String.valueOf(listOb.get(subtotal2).get(1)));
			}else{
				result=CommitResult.createErrorResult("第" + (subtotal2+2) + "行,第2列" + "数值有问题，请重新输写");
				return result;
			}
			v1 = excelValidate.validate(String.valueOf(listOb.get(subtotal3).get(1)), "Empty", "double", 50);
			if(v1.isResult()){
				entity.setTaxReturn(String.valueOf(listOb.get(subtotal3).get(1)));
			}else{
				result=CommitResult.createErrorResult("第" + (subtotal3+2) + "行,第2列" + "数值有问题，请重新输写");
				return result;
			}
			v1 = excelValidate.validate(String.valueOf(listOb.get(subtotal4).get(1)), "Empty", "double", 50);
			if(v1.isResult()){
				entity.setInTaxReturn(String.valueOf(listOb.get(subtotal4).get(1)));
			}else{
				result=CommitResult.createErrorResult("第" + (subtotal4+2) + "行,第2列" + "数值有问题，请重新输写");
				return result;
			}
			v1 = excelValidate.validate(String.valueOf(listOb.get(sumCn).get(1)), "Empty", "double", 50);
			if(v1.isResult()){
				entity.setTaxTask(String.valueOf(listOb.get(sumCn).get(1)));
			}else{
				result=CommitResult.createErrorResult("第" + (sumCn+2) + "行,第2列" + "数值有问题，请重新输写");
				return result;
			}
			
			HhOrganInfoTreeRelation node = systemDao.getTreeOrganInfoNode(Base.financetype, entity.getOrg());
			entity.setParentorg(node.getVcOrganId());
			// 纳税筹划明细数据保存
			Set<DataTaxTaskDetailTaxPlan> dataTaxTaskDetailTaxPlanSet = new HashSet<DataTaxTaskDetailTaxPlan>();
			for (int i = (tpCn + 1); i < subtotal1; i++) {
				List<Object> lo = listOb.get(i);
				DataTaxTaskDetailTaxPlan dataTaxTaskDetailTaxPlan = new DataTaxTaskDetailTaxPlan();
				dataTaxTaskDetailTaxPlan.setYear(entity.getYear());
				if(!entity.getOrgName().trim().equals(String.valueOf(lo.get(0)))){
					result = CommitResult.createErrorResult("公司名字与所选公司不符合，无法导入！");
					return result;
				}
				v1 = excelValidate.validate(String.valueOf(lo.get(0)), "NotEmpty", "varChar", 255);
				if(v1.isResult()){
					dataTaxTaskDetailTaxPlan.setOrgName(String.valueOf(lo.get(0)));
				}else{
					result=CommitResult.createErrorResult("第" + (tpCn+3) + "行,第1列" + "数值有问题，请重新输写");
					return result;
				}
				

				// 根据公司名查公司编号
				String orgID = systemDao.getFinancenNodeID(
						String.valueOf(lo.get(0)), Base.financetype);
				dataTaxTaskDetailTaxPlan.setOrg(orgID);
				if (orgID != null) {
					HhOrganInfoTreeRelation node1 = systemDao
							.getTreeOrganInfoNode(Base.financetype, orgID);
					dataTaxTaskDetailTaxPlan.setParentorg(node1.getVcOrganId());
				}
				v1 = excelValidate.validate(String.valueOf(lo.get(1)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailTaxPlan.setSum(String.valueOf(lo.get(1)));
				}else{
					result=CommitResult.createErrorResult("第" + (tpCn+3) + "行,第2列" + "数值有问题，请重新输写");
					return result;
				}
				v1 = excelValidate.validate(String.valueOf(lo.get(2)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailTaxPlan.setMonth1(String.valueOf(lo.get(2)));
				}else{
					result=CommitResult.createErrorResult("第" + (tpCn+3) + "行,第3列" + "数值有问题，请重新输写");
					return result;
				}
				
				v1 = excelValidate.validate(String.valueOf(lo.get(3)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailTaxPlan.setMonth2(String.valueOf(lo.get(3)));
				}else{
					result=CommitResult.createErrorResult("第" + (tpCn+3) + "行,第4列" + "数值有问题，请重新输写");
					return result;
				}
				v1 = excelValidate.validate(String.valueOf(lo.get(4)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailTaxPlan.setMonth3(String.valueOf(lo.get(4)));
				}else{
					result=CommitResult.createErrorResult("第" + (tpCn+3) + "行,第5列" + "数值有问题，请重新输写");
					return result;
				}

				v1 = excelValidate.validate(String.valueOf(lo.get(5)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailTaxPlan.setMonth4(String.valueOf(lo.get(5)));
				}else{
					result=CommitResult.createErrorResult("第" + (tpCn+3) + "行,第6列" + "数值有问题，请重新输写");
					return result;
				}
				
				v1 = excelValidate.validate(String.valueOf(lo.get(6)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailTaxPlan.setMonth5(String.valueOf(lo.get(6)));
				}else{
					result=CommitResult.createErrorResult("第" + (tpCn+3) + "行,第7列" + "数值有问题，请重新输写");
					return result;
				}
				
				v1 = excelValidate.validate(String.valueOf(lo.get(7)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailTaxPlan.setMonth6(String.valueOf(lo.get(7)));
				}else{
					result=CommitResult.createErrorResult("第" + (tpCn+3) + "行,第8列" + "数值有问题，请重新输写");
					return result;
				}
				
				v1 = excelValidate.validate(String.valueOf(lo.get(8)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailTaxPlan.setMonth7(String.valueOf(lo.get(8)));
				}else{
					result=CommitResult.createErrorResult("第" + (tpCn+3) + "行,第9列" + "数值有问题，请重新输写");
					return result;
				}
				
				v1 = excelValidate.validate(String.valueOf(lo.get(9)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailTaxPlan.setMonth8(String.valueOf(lo.get(9)));
				}else{
					result=CommitResult.createErrorResult("第" + (tpCn+3) + "行,第10列" + "数值有问题，请重新输写");
					return result;
				}
				
				v1 = excelValidate.validate(String.valueOf(lo.get(10)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailTaxPlan.setMonth9(String.valueOf(lo.get(10)));
				}else{
					result=CommitResult.createErrorResult("第" + (tpCn+3) + "行,第10列" + "数值有问题，请重新输写");
					return result;
				}
				
				v1 = excelValidate.validate(String.valueOf(lo.get(11)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailTaxPlan.setMonth10(String.valueOf(lo.get(11)));
				}else{
					result=CommitResult.createErrorResult("第" + (tpCn+3) + "行,第12列" + "数值有问题，请重新输写");
					return result;
				}
				
				v1 = excelValidate.validate(String.valueOf(lo.get(12)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailTaxPlan.setMonth11(String.valueOf(lo.get(12)));
				}else{
					result=CommitResult.createErrorResult("第" + (tpCn+3) + "行,第13列" + "数值有问题，请重新输写");
					return result;
				}
				
				v1 = excelValidate.validate(String.valueOf(lo.get(13)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailTaxPlan.setMonth12(String.valueOf(lo.get(13)));
				}else{
					result=CommitResult.createErrorResult("第" + (tpCn+3) + "行,第14列" + "数值有问题，请重新输写");
					return result;
				}
				
				dataTaxTaskDetailTaxPlan.setIsdel(0);
				dataTaxTaskDetailTaxPlan.setDataTaxTask(entity);
				dataTaxTaskDetailTaxPlanSet.add(dataTaxTaskDetailTaxPlan);
			}
			if ((subtotal1 - tpCn) > 1) {
				entity.setDataTaxTaskDetailTaxPlans(dataTaxTaskDetailTaxPlanSet);
			}
			// 优惠政策申请明细数据保存
			Set<DataTaxTaskDetailFavouredPolicy> dataTaxTaskDetailFavouredPolicySet = new HashSet<DataTaxTaskDetailFavouredPolicy>();
			for (int i = (fpCn + 1); i < subtotal2; i++) {
				List<Object> lo = listOb.get(i);
				DataTaxTaskDetailFavouredPolicy dataTaxTaskDetailFavouredPolicy = new DataTaxTaskDetailFavouredPolicy();
				dataTaxTaskDetailFavouredPolicy.setYear(entity.getYear());
				if(!entity.getOrgName().trim().equals(String.valueOf(lo.get(0)))){
					result = CommitResult.createErrorResult("公司名字与所选公司不符合，无法导入！");
					return result;
				}
				v1 = excelValidate.validate(String.valueOf(lo.get(0)), "NotEmpty", "varChar", 255);
				if(v1.isResult()){
					dataTaxTaskDetailFavouredPolicy.setOrgName(String.valueOf(lo.get(0)));
				}else{
					result=CommitResult.createErrorResult("第" + (fpCn+3) + "行,第1列" + "数值有问题，请重新输写");
					return result;
				}
				

				// 根据公司名查公司编号
				String orgID = systemDao.getFinancenNodeID(
						String.valueOf(lo.get(0)), Base.financetype);
				dataTaxTaskDetailFavouredPolicy.setOrg(orgID);
				if (orgID != null) {
					HhOrganInfoTreeRelation node1 = systemDao
							.getTreeOrganInfoNode(Base.financetype, orgID);
					dataTaxTaskDetailFavouredPolicy.setParentorg(node1.getVcOrganId());
				}
				v1 = excelValidate.validate(String.valueOf(lo.get(1)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailFavouredPolicy.setSum(String.valueOf(lo.get(1)));
				}else{
					result=CommitResult.createErrorResult("第" + (fpCn+3) + "行,第2列" + "数值有问题，请重新输写");
					return result;
				}
				v1 = excelValidate.validate(String.valueOf(lo.get(2)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailFavouredPolicy.setMonth1(String.valueOf(lo.get(2)));
				}else{
					result=CommitResult.createErrorResult("第" + (fpCn+3) + "行,第3列" + "数值有问题，请重新输写");
					return result;
				}
				
				v1 = excelValidate.validate(String.valueOf(lo.get(3)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailFavouredPolicy.setMonth2(String.valueOf(lo.get(3)));
				}else{
					result=CommitResult.createErrorResult("第" + (fpCn+3) + "行,第4列" + "数值有问题，请重新输写");
					return result;
				}
				v1 = excelValidate.validate(String.valueOf(lo.get(4)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailFavouredPolicy.setMonth3(String.valueOf(lo.get(4)));
				}else{
					result=CommitResult.createErrorResult("第" + (fpCn+3) + "行,第5列" + "数值有问题，请重新输写");
					return result;
				}

				v1 = excelValidate.validate(String.valueOf(lo.get(5)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailFavouredPolicy.setMonth4(String.valueOf(lo.get(5)));
				}else{
					result=CommitResult.createErrorResult("第" + (fpCn+3) + "行,第6列" + "数值有问题，请重新输写");
					return result;
				}
				
				v1 = excelValidate.validate(String.valueOf(lo.get(6)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailFavouredPolicy.setMonth5(String.valueOf(lo.get(6)));
				}else{
					result=CommitResult.createErrorResult("第" + (fpCn+3) + "行,第7列" + "数值有问题，请重新输写");
					return result;
				}
				
				v1 = excelValidate.validate(String.valueOf(lo.get(7)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailFavouredPolicy.setMonth6(String.valueOf(lo.get(7)));
				}else{
					result=CommitResult.createErrorResult("第" + (fpCn+3) + "行,第8列" + "数值有问题，请重新输写");
					return result;
				}
				
				v1 = excelValidate.validate(String.valueOf(lo.get(8)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailFavouredPolicy.setMonth7(String.valueOf(lo.get(8)));
				}else{
					result=CommitResult.createErrorResult("第" + (fpCn+3) + "行,第9列" + "数值有问题，请重新输写");
					return result;
				}
				
				v1 = excelValidate.validate(String.valueOf(lo.get(9)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailFavouredPolicy.setMonth8(String.valueOf(lo.get(9)));
				}else{
					result=CommitResult.createErrorResult("第" + (fpCn+3) + "行,第10列" + "数值有问题，请重新输写");
					return result;
				}
				
				v1 = excelValidate.validate(String.valueOf(lo.get(10)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailFavouredPolicy.setMonth9(String.valueOf(lo.get(10)));
				}else{
					result=CommitResult.createErrorResult("第" + (fpCn+3) + "行,第10列" + "数值有问题，请重新输写");
					return result;
				}
				
				v1 = excelValidate.validate(String.valueOf(lo.get(11)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailFavouredPolicy.setMonth10(String.valueOf(lo.get(11)));
				}else{
					result=CommitResult.createErrorResult("第" + (fpCn+3) + "行,第12列" + "数值有问题，请重新输写");
					return result;
				}
				
				v1 = excelValidate.validate(String.valueOf(lo.get(12)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailFavouredPolicy.setMonth11(String.valueOf(lo.get(12)));
				}else{
					result=CommitResult.createErrorResult("第" + (fpCn+3) + "行,第13列" + "数值有问题，请重新输写");
					return result;
				}
				
				v1 = excelValidate.validate(String.valueOf(lo.get(13)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailFavouredPolicy.setMonth12(String.valueOf(lo.get(13)));
				}else{
					result=CommitResult.createErrorResult("第" + (fpCn+3) + "行,第14列" + "数值有问题，请重新输写");
					return result;
				}			
				dataTaxTaskDetailFavouredPolicy.setIsdel(0);
				dataTaxTaskDetailFavouredPolicy.setDataTaxTask(entity);
				dataTaxTaskDetailFavouredPolicySet
						.add(dataTaxTaskDetailFavouredPolicy);
			}
			if ((subtotal2 - fpCn) > 1) {
				entity.setDataTaxTaskDetailFavouredPolicies(dataTaxTaskDetailFavouredPolicySet);
			}
			// 税收返还明细数据保存
			Set<DataTaxTaskDetailTaxReturn> dataTaxTaskDetailTaxReturnSet = new HashSet<DataTaxTaskDetailTaxReturn>();
			for (int i = (trCn + 1); i < subtotal3; i++) {
				List<Object> lo = listOb.get(i);
				DataTaxTaskDetailTaxReturn dataTaxTaskDetailTaxReturn = new DataTaxTaskDetailTaxReturn();
				dataTaxTaskDetailTaxReturn.setYear(entity.getYear());
				if(!entity.getOrgName().trim().equals(String.valueOf(lo.get(0)))){
					result = CommitResult.createErrorResult("公司名字与所选公司不符合，无法导入！");
					return result;
				}
				v1 = excelValidate.validate(String.valueOf(lo.get(0)), "NotEmpty", "varChar", 255);
				if(v1.isResult()){
					dataTaxTaskDetailTaxReturn.setOrgName(String.valueOf(lo.get(0)));
				}else{
					result=CommitResult.createErrorResult("第" + (trCn+3) + "行,第1列" + "数值有问题，请重新输写");
					return result;
				}
				

				// 根据公司名查公司编号
				String orgID = systemDao.getFinancenNodeID(
						String.valueOf(lo.get(0)), Base.financetype);
				dataTaxTaskDetailTaxReturn.setOrg(orgID);
				if (orgID != null) {
					HhOrganInfoTreeRelation node1 = systemDao
							.getTreeOrganInfoNode(Base.financetype, orgID);
					dataTaxTaskDetailTaxReturn.setParentorg(node1.getVcOrganId());
				}
				v1 = excelValidate.validate(String.valueOf(lo.get(1)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailTaxReturn.setSum(String.valueOf(lo.get(1)));
				}else{
					result=CommitResult.createErrorResult("第" + (trCn+3) + "行,第2列" + "数值有问题，请重新输写");
					return result;
				}
				v1 = excelValidate.validate(String.valueOf(lo.get(2)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailTaxReturn.setMonth1(String.valueOf(lo.get(2)));
				}else{
					result=CommitResult.createErrorResult("第" + (trCn+3) + "行,第3列" + "数值有问题，请重新输写");
					return result;
				}
				
				v1 = excelValidate.validate(String.valueOf(lo.get(3)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailTaxReturn.setMonth2(String.valueOf(lo.get(3)));
				}else{
					result=CommitResult.createErrorResult("第" + (trCn+3) + "行,第4列" + "数值有问题，请重新输写");
					return result;
				}
				v1 = excelValidate.validate(String.valueOf(lo.get(4)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailTaxReturn.setMonth3(String.valueOf(lo.get(4)));
				}else{
					result=CommitResult.createErrorResult("第" + (trCn+3) + "行,第5列" + "数值有问题，请重新输写");
					return result;
				}

				v1 = excelValidate.validate(String.valueOf(lo.get(5)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailTaxReturn.setMonth4(String.valueOf(lo.get(5)));
				}else{
					result=CommitResult.createErrorResult("第" + (trCn+3) + "行,第6列" + "数值有问题，请重新输写");
					return result;
				}
				
				v1 = excelValidate.validate(String.valueOf(lo.get(6)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailTaxReturn.setMonth5(String.valueOf(lo.get(6)));
				}else{
					result=CommitResult.createErrorResult("第" + (trCn+3) + "行,第7列" + "数值有问题，请重新输写");
					return result;
				}
				
				v1 = excelValidate.validate(String.valueOf(lo.get(7)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailTaxReturn.setMonth6(String.valueOf(lo.get(7)));
				}else{
					result=CommitResult.createErrorResult("第" + (trCn+3) + "行,第8列" + "数值有问题，请重新输写");
					return result;
				}
				
				v1 = excelValidate.validate(String.valueOf(lo.get(8)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailTaxReturn.setMonth7(String.valueOf(lo.get(8)));
				}else{
					result=CommitResult.createErrorResult("第" + (trCn+3) + "行,第9列" + "数值有问题，请重新输写");
					return result;
				}
				
				v1 = excelValidate.validate(String.valueOf(lo.get(9)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailTaxReturn.setMonth8(String.valueOf(lo.get(9)));
				}else{
					result=CommitResult.createErrorResult("第" + (trCn+3) + "行,第10列" + "数值有问题，请重新输写");
					return result;
				}
				
				v1 = excelValidate.validate(String.valueOf(lo.get(10)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailTaxReturn.setMonth9(String.valueOf(lo.get(10)));
				}else{
					result=CommitResult.createErrorResult("第" + (trCn+3) + "行,第10列" + "数值有问题，请重新输写");
					return result;
				}
				
				v1 = excelValidate.validate(String.valueOf(lo.get(11)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailTaxReturn.setMonth10(String.valueOf(lo.get(11)));
				}else{
					result=CommitResult.createErrorResult("第" + (trCn+3) + "行,第12列" + "数值有问题，请重新输写");
					return result;
				}
				
				v1 = excelValidate.validate(String.valueOf(lo.get(12)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailTaxReturn.setMonth11(String.valueOf(lo.get(12)));
				}else{
					result=CommitResult.createErrorResult("第" + (trCn+3) + "行,第13列" + "数值有问题，请重新输写");
					return result;
				}
				
				v1 = excelValidate.validate(String.valueOf(lo.get(13)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailTaxReturn.setMonth12(String.valueOf(lo.get(13)));
				}else{
					result=CommitResult.createErrorResult("第" + (trCn+3) + "行,第14列" + "数值有问题，请重新输写");
					return result;
				}			
				
				dataTaxTaskDetailTaxReturn.setIsdel(0);
				dataTaxTaskDetailTaxReturn.setDataTaxTask(entity);
				dataTaxTaskDetailTaxReturnSet.add(dataTaxTaskDetailTaxReturn);
			}
			if ((subtotal3 - trCn) > 1) {
				entity.setDataTaxTaskDetailTaxReturns(dataTaxTaskDetailTaxReturnSet);
			}
			// 非税收返还明细数据保存
			Set<DataTaxTaskDetailInTaxReturn> dataTaxTaskDetailInTaxReturnSet = new HashSet<DataTaxTaskDetailInTaxReturn>();
			for (int i = (itrCn + 1); i < subtotal4; i++) {
				List<Object> lo = listOb.get(i);
				DataTaxTaskDetailInTaxReturn dataTaxTaskDetailInTaxReturn = new DataTaxTaskDetailInTaxReturn();
				dataTaxTaskDetailInTaxReturn.setYear(entity.getYear());
				if(!entity.getOrgName().trim().equals(String.valueOf(lo.get(0)))){
					result = CommitResult.createErrorResult("公司名字与所选公司不符合，无法导入！");
					return result;
				}
				v1 = excelValidate.validate(String.valueOf(lo.get(0)), "NotEmpty", "varChar", 255);
				if(v1.isResult()){
					dataTaxTaskDetailInTaxReturn.setOrgName(String.valueOf(lo.get(0)));
				}else{
					result=CommitResult.createErrorResult("第" + (itrCn+3) + "行,第1列" + "数值有问题，请重新输写");
					return result;
				}
				

				// 根据公司名查公司编号
				String orgID = systemDao.getFinancenNodeID(
						String.valueOf(lo.get(0)), Base.financetype);
				dataTaxTaskDetailInTaxReturn.setOrg(orgID);
				if (orgID != null) {
					HhOrganInfoTreeRelation node1 = systemDao
							.getTreeOrganInfoNode(Base.financetype, orgID);
					dataTaxTaskDetailInTaxReturn.setParentorg(node1.getVcOrganId());
				}
				v1 = excelValidate.validate(String.valueOf(lo.get(1)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailInTaxReturn.setSum(String.valueOf(lo.get(1)));
				}else{
					result=CommitResult.createErrorResult("第" + (itrCn+3) + "行,第2列" + "数值有问题，请重新输写");
					return result;
				}
				v1 = excelValidate.validate(String.valueOf(lo.get(2)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailInTaxReturn.setMonth1(String.valueOf(lo.get(2)));
				}else{
					result=CommitResult.createErrorResult("第" + (itrCn+3) + "行,第3列" + "数值有问题，请重新输写");
					return result;
				}
				
				v1 = excelValidate.validate(String.valueOf(lo.get(3)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailInTaxReturn.setMonth2(String.valueOf(lo.get(3)));
				}else{
					result=CommitResult.createErrorResult("第" + (itrCn+3) + "行,第4列" + "数值有问题，请重新输写");
					return result;
				}
				v1 = excelValidate.validate(String.valueOf(lo.get(4)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailInTaxReturn.setMonth3(String.valueOf(lo.get(4)));
				}else{
					result=CommitResult.createErrorResult("第" + (itrCn+3) + "行,第5列" + "数值有问题，请重新输写");
					return result;
				}

				v1 = excelValidate.validate(String.valueOf(lo.get(5)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailInTaxReturn.setMonth4(String.valueOf(lo.get(5)));
				}else{
					result=CommitResult.createErrorResult("第" + (itrCn+3) + "行,第6列" + "数值有问题，请重新输写");
					return result;
				}
				
				v1 = excelValidate.validate(String.valueOf(lo.get(6)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailInTaxReturn.setMonth5(String.valueOf(lo.get(6)));
				}else{
					result=CommitResult.createErrorResult("第" + (itrCn+3) + "行,第7列" + "数值有问题，请重新输写");
					return result;
				}
				
				v1 = excelValidate.validate(String.valueOf(lo.get(7)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailInTaxReturn.setMonth6(String.valueOf(lo.get(7)));
				}else{
					result=CommitResult.createErrorResult("第" + (itrCn+3) + "行,第8列" + "数值有问题，请重新输写");
					return result;
				}
				
				v1 = excelValidate.validate(String.valueOf(lo.get(8)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailInTaxReturn.setMonth7(String.valueOf(lo.get(8)));
				}else{
					result=CommitResult.createErrorResult("第" + (itrCn+3) + "行,第9列" + "数值有问题，请重新输写");
					return result;
				}
				
				v1 = excelValidate.validate(String.valueOf(lo.get(9)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailInTaxReturn.setMonth8(String.valueOf(lo.get(9)));
				}else{
					result=CommitResult.createErrorResult("第" + (itrCn+3) + "行,第10列" + "数值有问题，请重新输写");
					return result;
				}
				
				v1 = excelValidate.validate(String.valueOf(lo.get(10)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailInTaxReturn.setMonth9(String.valueOf(lo.get(10)));
				}else{
					result=CommitResult.createErrorResult("第" + (itrCn+3) + "行,第10列" + "数值有问题，请重新输写");
					return result;
				}
				
				v1 = excelValidate.validate(String.valueOf(lo.get(11)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailInTaxReturn.setMonth10(String.valueOf(lo.get(11)));
				}else{
					result=CommitResult.createErrorResult("第" + (itrCn+3) + "行,第12列" + "数值有问题，请重新输写");
					return result;
				}
				
				v1 = excelValidate.validate(String.valueOf(lo.get(12)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailInTaxReturn.setMonth11(String.valueOf(lo.get(12)));
				}else{
					result=CommitResult.createErrorResult("第" + (itrCn+3) + "行,第13列" + "数值有问题，请重新输写");
					return result;
				}
				
				v1 = excelValidate.validate(String.valueOf(lo.get(13)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxTaskDetailInTaxReturn.setMonth12(String.valueOf(lo.get(13)));
				}else{
					result=CommitResult.createErrorResult("第" + (itrCn+3) + "行,第14列" + "数值有问题，请重新输写");
					return result;
				}			
				
				dataTaxTaskDetailInTaxReturn.setIsdel(0);
				dataTaxTaskDetailInTaxReturn.setDataTaxTask(entity);
				dataTaxTaskDetailInTaxReturnSet
						.add(dataTaxTaskDetailInTaxReturn);
			}
			if ((subtotal4 - itrCn) > 1) {
				entity.setDataTaxTaskDetailInTaxReturns(dataTaxTaskDetailInTaxReturnSet);
			}
			if(null==(isConfirm)){
				for (int i = (tpCn + 1); i < subtotal1; i++) {
					List<Object> lo = listOb.get(i);
					 for (int j = 1; j < lo.size(); j++) {
						    if(!String.valueOf(lo.get(j)).equals("")){
						    	if(Double.parseDouble(String.valueOf(lo.get(j)))>1000){
					                result=CommitResult.createErrorResult("当前导入数据单位为万元，请确认数据是否无误");
					                result.setEntity("1");
									return result;
							}
						 }
							
					}
				}
				for (int i = (fpCn + 1); i < subtotal2; i++) {
					List<Object> lo = listOb.get(i);
					 for (int j = 1; j < lo.size(); j++) {
						 if(!String.valueOf(lo.get(j)).equals("")){
							if(Double.parseDouble(String.valueOf(lo.get(j)))>1000){
					                result=CommitResult.createErrorResult("当前导入数据单位为万元，请确认数据是否无误");
					                result.setEntity("1");
									return result;
							}
						 }
					}
				}
				for (int i = (trCn + 1); i < subtotal3; i++) {
					List<Object> lo = listOb.get(i);
					 for (int j = 1; j < lo.size(); j++) {
						 if(!String.valueOf(lo.get(j)).equals("")){
							if(Double.parseDouble(String.valueOf(lo.get(j)))>1000){
					                result=CommitResult.createErrorResult("当前导入数据单位为万元，请确认数据是否无误");
					                result.setEntity("1");
									return result;
							}
						 }
					}
				}
				for (int i = (itrCn + 1); i < subtotal4; i++) {
					List<Object> lo = listOb.get(i);
					 for (int j = 1; j < lo.size(); j++) {
						 if(!String.valueOf(lo.get(j)).equals("")){
							if(Double.parseDouble(String.valueOf(lo.get(j)))>1000){
					                result=CommitResult.createErrorResult("当前导入数据单位为万元，请确认数据是否无误");
					                result.setEntity("1");
									return result;
							}
						 }
					}
				}
			}
			
			// 上报的场合
		} else {
			entity.setIsdel(0);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
		}

		commonDao.saveOrUpdate(entity);
		// 工作提醒内容追加
		if (entity.getStatus().equals(Base.examstatus_2)) {
			portalMsgService.savePortalMsg(new PortalMsg("节税任务需要审核", "财务", df
					.format(new Date()), 0, 0, 0, entity.getCreatePersonName(),
					entity.getCreateDate(), entity.getLastModifyPersonName(),
					entity.getLastModifyDate(), entity.getOrg(), systemService
							.getParentBynNodeID(entity.getOrg(),
									Base.financetype), "bimWork_jsrwsjsh_exam",
					"data_taxTask", entity.getId().toString()));
		}
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}

	/**
	 * 删除
	 * 
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteTaxTask(Integer id, HhUsers use) {

		DataTaxTask entity = taxTaskDao.getTaxTask(id);
		CommitResult result = new CommitResult();
		if (!taxTaskDao.checkCanEdit(entity)) {
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
			portalMsgService.updatePortalMsg("data_taxTask", entity.getId()
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
	public CommitResult saveTaxTaskExamineCheck(Integer entityID) {
		CommitResult result = new CommitResult();
		DataTaxTask entity = taxTaskDao.getTaxTask(entityID);
		if (entity == null) {
			result = CommitResult.createErrorResult("该数据已被删除");
			return result;
		}
/*		if (!entity.getStatus().equals(Base.examstatus_2)) {
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
	public CommitResult saveTaxTaskExamine(Integer entityID, String examStr,
			Integer examResult, HhUsers use) {

		DataTaxTask entity = getTaxTask(entityID);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result = new CommitResult();
		result = saveTaxTaskExamineCheck(entityID);
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
		examineService.saveExamine(examineentityid, Base.examkind_taxtask, use,
				examStr, examResult);
		// 工作提醒内容删除
		portalMsgService.updatePortalMsg("data_taxTask", entity.getId()
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
	private CommitResult saveTaxTaskCheck(DataTaxTask entity) {
		CommitResult result = new CommitResult();
		if (!taxTaskDao.saveTaxTaskRepeatCheck(entity)) {
			result = CommitResult.createErrorResult(entity.getYear()
					+ "年已经有数据，请先删除再导入");
			return result;
		}
		if (!taxTaskDao.checkCanEdit(entity)) {
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
	public List<DataTaxTask> savefindCollectList(DataTaxTask payCount) {
		// TODO Auto-generated method stub
		List<DataTaxTask> list = taxTaskDao.getHrFormsListCollectView(payCount);
		return list;
		
	}
	
	/**
	 * 汇总
	 * 虚拟公司数据不存在
	 */
	@Override
	public List<DataTaxTask> savefindCollectList2(DataTaxTask entity) {
		// TODO Auto-generated method stub
		List<DataTaxTask> list = taxTaskDao.getHrFormsListCollectView2(entity);
		return list;
	}

	
	@Override
	public void saveself(DataTaxTask entity) {
		// TODO Auto-generated method stub
		commonDao.saveOrUpdate(entity);
		
	}

	/**
	 * 汇总
	 * 虚拟公司数据存在
	 */
	@Override
	public void savecollect(DataTaxTask entity) {
		// TODO Auto-generated method stub
		
		List<DataTaxTaskDetailFavouredPolicy> list2 = taxTaskDao.getnewListFav(entity);
		List<DataTaxTaskDetailInTaxReturn> list3 = taxTaskDao.getnewListIntax(entity);
		List<DataTaxTaskDetailTaxPlan> list4 = taxTaskDao.getnewListPlan(entity);
		List<DataTaxTaskDetailTaxReturn> list5 = taxTaskDao.getnewListReturn(entity);
		if(list2.size()!=0){
			for(DataTaxTaskDetailFavouredPolicy entity1 : list2 ){
				commonDao.saveOrUpdate(entity1);
			}
			
		}
		
		if(list3.size()!=0){
			for(DataTaxTaskDetailInTaxReturn entity1 : list3 ){
				commonDao.saveOrUpdate(entity1);
			}
			
		}
		
		if(list4.size()!=0){
			for(DataTaxTaskDetailTaxPlan entity1 : list4 ){
				commonDao.saveOrUpdate(entity1);
			}
			
		}
		
		if(list5.size()!=0){
			for(DataTaxTaskDetailTaxReturn entity1 : list5 ){
				commonDao.saveOrUpdate(entity1);
			}
			
		}
	}

	/**
	 * 汇总
	 * 虚拟公司数据不存在
	 */
	@Override
	public void savecollect2(DataTaxTask entity) {
		// TODO Auto-generated method stub
		List<DataTaxTaskDetailFavouredPolicy> list2 = taxTaskDao.getnewListFav2(entity);
		List<DataTaxTaskDetailInTaxReturn> list3 = taxTaskDao.getnewListIntax2(entity);
		List<DataTaxTaskDetailTaxPlan> list4 = taxTaskDao.getnewListPlan2(entity);
		List<DataTaxTaskDetailTaxReturn> list5 = taxTaskDao.getnewListReturn2(entity);
		if(list2.size()!=0){
			for(DataTaxTaskDetailFavouredPolicy entity1 : list2 ){
				commonDao.saveOrUpdate(entity1);
			}
			
		}
		
		if(list3.size()!=0){
			for(DataTaxTaskDetailInTaxReturn entity1 : list3 ){
				commonDao.saveOrUpdate(entity1);
			}
			
		}
		
		if(list4.size()!=0){
			for(DataTaxTaskDetailTaxPlan entity1 : list4 ){
				commonDao.saveOrUpdate(entity1);
			}
			
		}
		
		if(list5.size()!=0){
			for(DataTaxTaskDetailTaxReturn entity1 : list5 ){
				commonDao.saveOrUpdate(entity1);
			}
			
		}
	}
	
	/**
	 * 汇总保存
	 */
	@Override
	public Object saveCollectList(DataTaxTask entity, HhUsers use, String string) {
		// TODO Auto-generated method stub
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
			result.setEntity(entity);
		}else{
			result = CommitResult.createErrorResult("数据不存在，请重新选择后进行操作！");
		}


		return result;

	}

	/**
	 * 汇总上报
	 */
	@Override
	public Object saveReportCollectList(DataTaxTask entity, HhUsers use,
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
			result.setEntity(entity);
		}else{
			result = CommitResult.createErrorResult("数据不存在，请重新选择后进行操作！");
		}
		return result;
	}

	@Override
	public int getStatus(Integer id) {
		// TODO Auto-generated method stub
		int status = taxTaskDao.getStatus(id);
		return status;
	}

	@Override
	public List<DataTaxTaskDetailInTaxReturn> getDetailInt(
			DataTaxTask entityview, Integer id) {
		// TODO Auto-generated method stub
		List<DataTaxTaskDetailInTaxReturn>  list  = taxTaskDao.getDetailInt(entityview,id);
		return  list;
	}

	@Override
	public List<DataTaxTaskDetailFavouredPolicy> getDetailFav(
			DataTaxTask entityview, Integer id) {
		// TODO Auto-generated method stub
		List<DataTaxTaskDetailFavouredPolicy>  list  = taxTaskDao.getDetailFav(entityview,id);
		return  list;
	}

	@Override
	public List<DataTaxTaskDetailTaxPlan> getDetailPlan(DataTaxTask entityview,
			Integer id) {
		// TODO Auto-generated method stub
		List<DataTaxTaskDetailTaxPlan>  list  = taxTaskDao.getDetailPlan(entityview,id);
		return  list;
	}

	@Override
	public List<DataTaxTaskDetailTaxReturn> getDetailRet(
			DataTaxTask entityview, Integer id) {
		// TODO Auto-generated method stub
		List<DataTaxTaskDetailTaxReturn>  list  = taxTaskDao.getDetailRet(entityview,id);
		return  list;
	}

	@Override
	public List<DataTaxTask> getTaxTaskId(DataTaxTask entity) {
		// TODO Auto-generated method stub
		List<DataTaxTask>  list  = taxTaskDao.getTaxTaskId(entity);
		return list;
	}

	@Override
	public String getParentOrg(String org) {
		String  Parentorg  = taxTaskDao.getParentOrg(org);
		return Parentorg;
	}

	@Override
	public List<DataTaxTask> getSumDataList(DataTaxTask entity) {
		// TODO Auto-generated method stub
		return taxTaskDao.getSumDataList(entity);
	}

	@Override
	public int getSumDataInfo(DataTaxTask entity, DataTaxTask beanIn) {
		HhOrganInfoTreeRelation hhOrganInfoTreeRelation = null;
		if(null==entity || "".equals(entity.getOrg()) || null == entity.getOrg())
			return -1;
		List<DataTaxTask> list = taxTaskDao.getSumDataInfo(entity);
		DecimalFormat df = new DecimalFormat("#.00");
		for(Object obj : list) {
			Object[] item = (Object[])obj;
			beanIn.setTaxTask(item[0]==null?"0":df.format(item[0]).toString());
			beanIn.setTaxPlan(item[1]==null?"0":df.format(item[1]).toString());
			beanIn.setFavouredPolicy(item[2]==null?"0":df.format(item[2]).toString());		
			beanIn.setTaxReturn(item[3]==null?"0":df.format(item[3]).toString());		
			beanIn.setInTaxReturn(item[4]==null?"0":df.format(item[4]).toString());			
		}
		return 0;
	}




}
