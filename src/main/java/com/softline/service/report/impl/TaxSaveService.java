package com.softline.service.report.impl;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.dao.report.ITaxSaveDao;
import com.softline.dao.system.ICommonDao;
import com.softline.dao.system.ISystemDao;
import com.softline.entity.DataHrHeadcount;
import com.softline.entity.DataHrLaborproduction;
import com.softline.entity.DataTaxSave;
import com.softline.entity.DataTaxSaveDetail;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entity.PortalMsg;
import com.softline.entityTemp.CommitResult;
import com.softline.entityTemp.DataTaxSaveView;
import com.softline.service.report.ITaxSaveService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IPortalMsgService;
import com.softline.service.system.ISystemService;
import com.softline.util.ExcelCellValueValidate;
import com.softline.util.ExcelCellValueValidateResult;
import com.softline.util.MsgPage;

@Service("taxSaveService")
/**
 * 
 * @author zy
 *
 */
public class TaxSaveService implements ITaxSaveService {

	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;

	@Autowired
	@Qualifier("taxSaveDao")
	private ITaxSaveDao taxSaveDao;

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
	public MsgPage getTaxSaveListView(DataTaxSave entity, Integer curPageNum,
			Integer pageSize, Integer fun) {
		MsgPage msgPage = new MsgPage();
		// 总记录数
		Integer totalRecords = taxSaveDao.getTaxSaveListCount(entity, fun);
		// 当前页开始记录
		int offset = msgPage.countOffset(curPageNum, pageSize);
		// 分页查询结果集
		List<DataTaxSave> list = taxSaveDao.getTaxSaveList(entity, offset,
				pageSize, fun);
		// 数据格式转换
		List<DataTaxSaveView> View = new ArrayList<DataTaxSaveView>();
		String dataTaxSaveTemp;
		for (DataTaxSave dataTaxSave : list) {
			DataTaxSaveView item = new DataTaxSaveView();
			item.setId(dataTaxSave.getId());
			item.setYear(dataTaxSave.getYear());
			item.setMonth(dataTaxSave.getMonth());
			item.setOrg(dataTaxSave.getOrg());
			item.setOrgName(dataTaxSave.getOrgName());
			item.setTaxPlan(dataTaxSave.getTaxPlan());
			item.setAccTaxPlan(dataTaxSave.getAccTaxPlan());
			item.setFavouredPolicy(dataTaxSave.getFavouredPolicy());
			item.setAccFavouredPolicy(dataTaxSave.getAccFavouredPolicy());
			item.setTaxReturn(dataTaxSave.getTaxReturn());
			item.setAccTaxReturn(dataTaxSave.getAccTaxReturn());
			item.setInTaxReturn(dataTaxSave.getInTaxReturn());
			item.setAccInTaxReturn(dataTaxSave.getAccInTaxReturn());
			item.setRemark(dataTaxSave.getRemark());
			item.setStatusName(dataTaxSave.getStatusName());
			item.setTaxSave(dataTaxSave.getTaxSave());
			// 汇总年度累计节税值	
			dataTaxSaveTemp = taxSaveDao.getAccTaxSave(dataTaxSave);			
			if (Common.notEmpty(dataTaxSaveTemp)) {
				item.setAccTaxSave(Double.valueOf(dataTaxSaveTemp));
			} else {
				item.setAccTaxSave(new Double(0));
			}
			
			if (Common.notEmpty(dataTaxSave.getTaxTask())) {
				item.setTaxTask(Double.valueOf(dataTaxSave.getTaxTask()));
			} else {
				item.setTaxTask(new Double(0));
			}
			item.setDataTaxSaveDetails(dataTaxSave.getDataTaxSaveDetails());
			item.setStatus(dataTaxSave.getStatus());
			item.setIsdel(dataTaxSave.getIsdel());
			item.setReportDate(dataTaxSave.getReportDate());
			item.setReportPersonId(dataTaxSave.getReportPersonId());
			item.setReportPersonName(dataTaxSave.getReportPersonName());
			item.setAuditorDate(dataTaxSave.getAuditorDate());
			item.setAuditorPersonId(dataTaxSave.getAuditorPersonId());
			item.setAuditorPersonName(dataTaxSave.getAuditorPersonName());
			item.setCreateDate(dataTaxSave.getCreateDate());
			item.setCreatePersonId(dataTaxSave.getCreatePersonId());
			item.setCreatePersonName(dataTaxSave.getCreatePersonName());
			item.setLastModifyDate(dataTaxSave.getLastModifyDate());
			item.setLastModifyPersonId(dataTaxSave.getLastModifyPersonId());
			item.setLastModifyPersonName(dataTaxSave.getLastModifyPersonName());
			View.add(item);
		}
		msgPage.setPageNum(curPageNum);
		msgPage.setPageSize(pageSize);
		msgPage.setTotalRecords(totalRecords);
		msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
		msgPage.setList(View);
		return msgPage;
	}

	/**
	 * 单个ID查询
	 * 
	 * @param id
	 *            查询ID
	 * @return
	 */
	public DataTaxSave getTaxSave(Integer id) {
		return taxSaveDao.getTaxSave(id);
	}

	/**
	 * 保存更新
	 * 
	 * @param entity
	 * @param listOb
	 * @param use
	 * @return
	 */
	public CommitResult saveTaxSave(DataTaxSave entity,List<List<Object>> listOb, HhUsers use,String isConfirm) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 校验
		CommitResult result=new CommitResult();
		ExcelCellValueValidate excelValidate = new ExcelCellValueValidate();
		result = saveTaxSaveCheck(entity);
		if (!result.isResult()) {
			return result;
		}
		if (taxSaveDao.isvirtual(entity)){
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
			// 节税金额数据保存
			if (listOb.size() >= 3) {
				int sumNo = listOb.size() - 1;
/*				if(null==(isConfirm)){
					 for (int j = 1; j < listOb.get(sumNo).size(); j++) {
							if(Double.parseDouble(String.valueOf(listOb.get(sumNo).get(j)))>1000){
					                result=CommitResult.createErrorResult("当前导入数据单位为万元，请确认数据是否无误");
					                result.setEntity("1");
									return result;
							}
						}
				}*/
				ExcelCellValueValidateResult v1 = excelValidate.validate(String.valueOf(listOb.get(sumNo).get(1)), "Empty", "double", 50);
				if(v1.isResult()){
					entity.setInTaxReturn(String.valueOf(listOb.get(sumNo).get(1)));
				}else{
					result=CommitResult.createErrorResult("第" + (sumNo+2) + "行,第2列" + "数值有问题，请重新输写");
					return result;
				}
				
//				entity.setAccInTaxReturn(String.valueOf(listOb.get(sumNo)
//						.get(2)));
				v1 = excelValidate.validate(String.valueOf(listOb.get(sumNo).get(2)), "Empty", "double", 50);
				if(v1.isResult()){
					entity.setTaxReturn(String.valueOf(listOb.get(sumNo).get(2)));
				}else{
					result=CommitResult.createErrorResult("第" + (sumNo+2) + "行,第3列" + "数值有问题，请重新输写");
					return result;
				}
				
//				entity.setAccTaxReturn(String.valueOf(listOb.get(sumNo).get(4)));
				v1 = excelValidate.validate(String.valueOf(listOb.get(sumNo).get(3)), "Empty", "double", 50);
				if(v1.isResult()){
					entity.setTaxPlan(String.valueOf(listOb.get(sumNo).get(3)));
				}else{
					result=CommitResult.createErrorResult("第" + (sumNo+2) + "行,第4列" + "数值有问题，请重新输写");
					return result;
				}
				
//				entity.setAccTaxPlan(String.valueOf(listOb.get(sumNo).get(6)));
				v1 = excelValidate.validate(String.valueOf(listOb.get(sumNo).get(4)), "Empty", "double", 50);
				if(v1.isResult()){
					entity.setFavouredPolicy(String.valueOf(listOb.get(sumNo).get(4)));
				}else{
					result=CommitResult.createErrorResult("第" + (sumNo+2) + "行,第5列" + "数值有问题，请重新输写");
					return result;
				}
				
//				entity.setAccFavouredPolicy(String.valueOf(listOb.get(sumNo)
//						.get(8)));
				v1 = excelValidate.validate(String.valueOf(listOb.get(sumNo).get(5)), "Empty", "double", 50);
				if(v1.isResult()){
					entity.setTaxSave(String.valueOf(listOb.get(sumNo).get(5)));
				}else{
					result=CommitResult.createErrorResult("第" + (sumNo+2) + "行,第6列" + "数值有问题，请重新输写");
					return result;
				}
				
//				entity.setAccTaxSave(String.valueOf(listOb.get(sumNo).get(10)));
				HhOrganInfoTreeRelation node = systemDao.getTreeOrganInfoNode(
						Base.financetype, entity.getOrg());
				entity.setParentorg(node.getVcOrganId());
				entity.setMonth(entity.getYear().substring(entity.getYear().length()-2));
			}
			// 节税金额明细数据保存
			Set<DataTaxSaveDetail> dataTaxSaveDetailSet = new HashSet<DataTaxSaveDetail>();
			for (int i = 2; i < (listOb.size()); i++) {
				List<Object> lo = listOb.get(i);
				DataTaxSaveDetail dataTaxSaveDetail = new DataTaxSaveDetail();
				dataTaxSaveDetail.setYear(entity.getYear());
				dataTaxSaveDetail.setMonth(entity.getYear().substring(entity.getYear().length()-2));
				if(!entity.getOrgName().trim().equals(String.valueOf(lo.get(0)))){
					result = CommitResult.createErrorResult("公司名字与所选公司不符合，无法导入！");
					return result;
				}
				ExcelCellValueValidateResult v1 = excelValidate.validate(String.valueOf(lo.get(0)), "NotEmpty", "varChar", 255);
				if(v1.isResult()){
					dataTaxSaveDetail.setOrgName(String.valueOf(lo.get(0)));
				}else{
					result=CommitResult.createErrorResult("第4行,第1列数值有问题，请重新输写");
					return result;
				}
				if(null==(isConfirm)){
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
				// 根据公司名查公司编号
				String orgID = systemDao.getFinancenNodeID(
						String.valueOf(lo.get(0)), Base.financetype);
				dataTaxSaveDetail.setOrg(orgID);
				if (orgID != null) {
					HhOrganInfoTreeRelation node1 = systemDao
							.getTreeOrganInfoNode(Base.financetype, orgID);
					dataTaxSaveDetail.setParentorg(node1.getVcOrganId());
				}
				v1 = excelValidate.validate(String.valueOf(lo.get(1)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxSaveDetail.setInTaxReturn(String.valueOf(lo.get(1)));
				}else{
					result=CommitResult.createErrorResult("第4行,第2列数值有问题，请重新输写");
					return result;
				}		
//				dataTaxSaveDetail.setAccInTaxReturn(String.valueOf(lo.get(2)));
				v1 = excelValidate.validate(String.valueOf(lo.get(2)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxSaveDetail.setTaxReturn(String.valueOf(lo.get(2)));
				}else{
					result=CommitResult.createErrorResult("第4行,第3列数值有问题，请重新输写");
					return result;
				}			
//				dataTaxSaveDetail.setAccTaxReturn(String.valueOf(lo.get(4)));
				v1 = excelValidate.validate(String.valueOf(lo.get(3)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxSaveDetail.setTaxPlan(String.valueOf(lo.get(3)));
				}else{
					result=CommitResult.createErrorResult("第4行,第4列数值有问题，请重新输写");
					return result;
				}
//				dataTaxSaveDetail.setAccTaxPlan(String.valueOf(lo.get(6)));
				v1 = excelValidate.validate(String.valueOf(lo.get(4)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxSaveDetail.setFavouredPolicy(String.valueOf(lo.get(4)));
				}else{
					result=CommitResult.createErrorResult("第4行,第5列数值有问题，请重新输写");
					return result;
				}

//				dataTaxSaveDetail.setAccFavouredPolicy(String.valueOf(lo.get(8)));
				v1 = excelValidate.validate(String.valueOf(lo.get(5)), "Empty", "double", 50);
				if(v1.isResult()){
					dataTaxSaveDetail.setTaxSave(String.valueOf(lo.get(5)));
				}else{
					result=CommitResult.createErrorResult("第4行,第6列数值有问题，请重新输写");
					return result;
				}
				
//				dataTaxSaveDetail.setAccTaxSave(String.valueOf(lo.get(10)));
				dataTaxSaveDetail.setIsdel(0);
				dataTaxSaveDetail.setDataTaxSave(entity);
				dataTaxSaveDetailSet.add(dataTaxSaveDetail);
			}
			if (listOb.size() >= 3) {
				entity.setDataTaxSaveDetails(dataTaxSaveDetailSet);
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
			portalMsgService.savePortalMsg(new PortalMsg("节税金额需要审核", "财务", df
					.format(new Date()), 0, 0, 0, entity.getCreatePersonName(),
					entity.getCreateDate(), entity.getLastModifyPersonName(),
					entity.getLastModifyDate(), entity.getOrg(), systemService
							.getParentBynNodeID(entity.getOrg(),
									Base.financetype), "bimWork_jsjesjsh_exam",
					"data_taxSave", entity.getId().toString()));
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
	public CommitResult deleteTaxSave(Integer id, HhUsers use) {

		DataTaxSave entity = taxSaveDao.getTaxSave(id);
		CommitResult result = new CommitResult();
		if (!taxSaveDao.checkCanEdit(entity)) {
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
			portalMsgService.updatePortalMsg("data_taxSave", entity.getId()
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
	public CommitResult saveTaxSaveExamineCheck(Integer entityID) {
		CommitResult result = new CommitResult();
		DataTaxSave entity = taxSaveDao.getTaxSave(entityID);
		if (entity == null) {
			result = CommitResult.createErrorResult("该数据已被删除");
			return result;
		}
		/*if (!entity.getStatus().equals(Base.examstatus_2)) {
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
	public CommitResult saveTaxSaveExamine(Integer entityID, String examStr,
			Integer examResult, HhUsers use) {

		DataTaxSave entity = getTaxSave(entityID);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result = new CommitResult();
		result = saveTaxSaveExamineCheck(entityID);
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
		examineService.saveExamine(examineentityid, Base.examkind_taxsave, use,
				examStr, examResult);
		// 工作提醒内容删除
		portalMsgService.updatePortalMsg("data_taxSave", entity.getId()
				.toString());
		result.setResult(true);
		return result;
	}

	/**
	 * 保存校验
	 * 
	 * @param entity
	 * 
	 * 	data_taxTask
	 *  1、增加导入保存修改，如果年度计划中没有年度节税目标则校验提示
	 *  		by zl 2018/8/10
	 * @return
	 */
	private CommitResult saveTaxSaveCheck(DataTaxSave entity) {
		CommitResult result = new CommitResult();
		if (!taxSaveDao.saveTaxSaveRepeatCheck(entity)) {
			result = CommitResult.createErrorResult(entity.getYear() + "年"
					+ entity.getMonth() + "月已经有数据，请先删除再导入");
			return result;
		}
		if (!taxSaveDao.checkCanEdit(entity)) {
			result = CommitResult.createErrorResult("该数据已经被上报不能修改");
			return result;
		}
		
		if(!taxSaveDao.IsExitsTaxTask(entity)){
			result = CommitResult.createErrorResult("未填写年度节税目标，请及时填写！");
			return result;
		}
		result.setResult(true);
		return result;
	}
	
	/**
	 * 汇总数据
	 * @param entity
	 * @return
	 */
	@Override
	public List<DataTaxSave> savefindCollectList(DataTaxSave payCount) {
		// TODO Auto-generated method stub
		List<DataTaxSave> list = taxSaveDao.getHrFormsListCollectView(payCount);
		return list;
	}
	
	
	@Override
	public List<DataTaxSave> savefindCollectList2(DataTaxSave entity) {
		// TODO Auto-generated method stub
		List<DataTaxSave> list = taxSaveDao.getHrFormsListCollectView2(entity);
		return list;
	}

	@Override
	public void saveself(DataTaxSave entity) {
		// TODO Auto-generated method stub
		commonDao.saveOrUpdate(entity);
		
	}

	/**
	 * 汇总
	 * 虚拟公司数据存在
	 */
	@Override
	public void savecollect(DataTaxSave entity) {
		// TODO Auto-generated method stub
		List<DataTaxSaveDetail> list2 = taxSaveDao.getnewListCollect(entity);
		if(list2.size()!=0){
			for(DataTaxSaveDetail entity1 : list2 ){
				commonDao.saveOrUpdate(entity1);
			}
			
		}
		
	}

	/**
	 * 汇总
	 * 虚拟公司数据不存在
	 */
	@Override
	public void savecollect2(DataTaxSave entity) {
		// TODO Auto-generated method stub
		List<DataTaxSaveDetail> list2 = taxSaveDao.getnewListCollect2(entity);
		if(list2.size()!=0){
			for(DataTaxSaveDetail entity1 : list2 ){
				commonDao.saveOrUpdate(entity1);
			}
			
		}
	}


	/**
	 * 判断是否为虚拟公司
	 */

	@Override
	public CommitResult isvirtual(DataTaxSave entity) {
		CommitResult result = new CommitResult();
		if (!taxSaveDao.isvirtual(entity)){
			result =CommitResult.createErrorResult("该公司非虚拟公司，无法汇总！请稍后再试！");
		}
		return result;
	}

	/**
	 * 保存汇总数据
	 */
	public CommitResult saveCollectList(DataTaxSave entity, HhUsers use,String op) {
		
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
	public Object saveReportCollectList(DataTaxSave entity, HhUsers use,
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
		int status = taxSaveDao.getStatus(id);
		return status;
	}


	@Override
	public List<DataTaxSaveDetail> getDetail(DataTaxSave entityview, Integer id) {
		// TODO Auto-generated method stub
		List<DataTaxSaveDetail>  list  = taxSaveDao.getDetail(entityview,id);
		return  list;
	}

	@Override
	public List<DataTaxSave> getTaxSaveId(DataTaxSave entity) {
		List<DataTaxSave> list = taxSaveDao.getTaxSaveId(entity);
		return list;
	}
	
	@Override
	public int getSumDataTaxSaveSonBeanData(DataTaxSave beanIn,DataTaxSave beanOut){
		
		HhOrganInfoTreeRelation hhOrganInfoTreeRelation = null;
		if(null==beanIn || "".equals(beanIn.getOrg()) || null == beanIn.getOrg())
			return -1;
			
	/*	hhOrganInfoTreeRelation =systemDao.getTopTreeOrganInfoNodeStr(Base.financetype,beanIn.getOrg());		
		if(null == hhOrganInfoTreeRelation || null == hhOrganInfoTreeRelation.getVcOrganId())
			return -1;
			
		beanIn.setParentorg(hhOrganInfoTreeRelation.getVcOrganId());*/
		List<DataTaxSave> list=taxSaveDao.getDataTaxSaveSonBeanSumData(beanIn);	
		DecimalFormat df = new DecimalFormat("#.00");
        
		/**
		 * taxSave
		 * inTaxReturn
		 * taxReturn
		 * taxPlan
		 * favouredPolicy      
		 */       
		for(Object obj : list) {
			Object[] item = (Object[])obj;
			beanOut.setTaxSave(item[0]==null?"0":df.format(item[0]).toString());
			beanOut.setInTaxReturn(item[1]==null?"0":df.format(item[1]).toString());
			beanOut.setTaxReturn(item[2]==null?"0":df.format(item[2]).toString());		
			beanOut.setTaxPlan(item[3]==null?"0":df.format(item[3]).toString());		
			beanOut.setFavouredPolicy(item[4]==null?"0":df.format(item[4]).toString());			
		}					
		return 0;
	}

	@Override
	public List<DataTaxSave> getSumDataList(DataTaxSave entity) {
		// TODO Auto-generated method stub
		return taxSaveDao.getSumDataList(entity);
	}


}
