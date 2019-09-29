package com.softline.controller.report;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.ExcelTool;
import com.softline.entity.HhBase;
import com.softline.entity.HhUsers;
import com.softline.entity.SysExamine;
import com.softline.entity.taxTask.DataTaxTask;
import com.softline.entity.taxTask.DataTaxTaskDetailFavouredPolicy;
import com.softline.entity.taxTask.DataTaxTaskDetailInTaxReturn;
import com.softline.entity.taxTask.DataTaxTaskDetailTaxPlan;
import com.softline.entity.taxTask.DataTaxTaskDetailTaxReturn;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.ITaxTaskService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/taxTask")
/**
 * 
 * @author zy
 *
 */
public class TaxTaskController {

	@Resource(name = "taxTaskService")
	private ITaxTaskService taxTaskService;
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "examineService")
	private IExamineService examineService;
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	@Resource(name = "systemService")
	private ISystemService systemService;

	@ModelAttribute
	public void getCommodity(
			@RequestParam(value = "id", required = false) Integer id,
			Map<String, Object> map, HttpServletRequest request) {
		if (id != null) {
			DataTaxTask entityview = taxTaskService.getTaxTask(id);
			map.put("taxTask", entityview);
		}
	}

	/**
	 * 数据填报查询画面
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public String _list(DataTaxTask entity, HttpServletRequest request,
			Map<String, Object> map) throws IOException {
		// 树列表信息
		HttpSession session = request.getSession();
		// 数据权限
		String str = (String) session
				.getAttribute("gzwsession_financecompany");
		entity.setParentorg(str);
		// entity.setOrg(systemService.getAllChildrenFinanceOrganal(str,Base.financetype));
		map.put("allCompanyTree", JSON.toJSONString(selectUserService
				.getOtherOrganal(str, Base.financetype)));
		// 路径
		String mapurl = request.getContextPath() + "/taxTask";
		map.put("mapurl", mapurl);
		// 当前页码
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		// 当前页信息取得
		MsgPage msgPage = taxTaskService.getTaxTaskListView(entity, pageNum,
				Base.pagesize, Base.fun_search);
		map.put("msgPage", msgPage);
		map.put("searchurl", "/shanghai-gzw/taxTask/list");
		map.put("entityview", entity);
		// 审核状态信息
		addData(map);
		return "/report/taxTask/taxTaskList";
	}

	/**
	 * 弹出查看画面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String _view(Integer id, HttpServletRequest request,
			Map<String, Object> map, String op) throws IOException {

		DataTaxTask entityview;
		List<SysExamine> a = new ArrayList<SysExamine>();
		if (id == null) {
			entityview = new DataTaxTask();
			Date date = new Date();
			DateFormat df = new SimpleDateFormat("yyyy");
			entityview.setYear(Integer.parseInt(df.format(date)));
		} else {
			// 数据明细
			entityview = taxTaskService.getTaxTask(id);
			
			//首先判断该公司是否为虚拟公司
			int status = taxTaskService.getStatus(id);
			if(status==50501){
				List<DataTaxTaskDetailFavouredPolicy> list1 = taxTaskService.getDetailFav(entityview, id);
				List<DataTaxTaskDetailInTaxReturn> list2 = taxTaskService.getDetailInt(entityview,id);
				List<DataTaxTaskDetailTaxPlan> list3 = taxTaskService.getDetailPlan(entityview,id);
				List<DataTaxTaskDetailTaxReturn> list4 = taxTaskService.getDetailRet(entityview,id);
				Set<DataTaxTaskDetailFavouredPolicy> result1 = new HashSet(list1);
				Set<DataTaxTaskDetailInTaxReturn> result2 = new HashSet(list2);
				Set<DataTaxTaskDetailTaxPlan> result3 = new HashSet(list3);
				Set<DataTaxTaskDetailTaxReturn> result4 = new HashSet(list4);
				entityview.setDataTaxTaskDetailFavouredPolicies(result1);
				entityview.setDataTaxTaskDetailInTaxReturns(result2);
				entityview.setDataTaxTaskDetailTaxPlans(result3);
				entityview.setDataTaxTaskDetailTaxReturns(result4);
								
			}
			
			// 审核信息
			a = examineService.getListExamine(entityview.getId(),
					Base.examkind_taxtask);
		}
		map.put("entityview", entityview);
		map.put("entityList", JSON.toJSONString(entityview));
		map.put("entityExamineviewlist", a);
		return "/report/taxTask/taxTaskView";
	}

	/**
	 * 弹出新增/修改画面
	 * 
	 * @return
	 */
	@RequestMapping("/newmodify")
	public String _newmodify(DataTaxTask entity, HttpServletRequest request,
			Map<String, Object> map) throws IOException {
		// 树列表信息
		HttpSession session = request.getSession();
		// 数据权限
		String str = (String) session
				.getAttribute("gzwsession_financecompany");
		entity.setParentorg(str);
		map.put("allCompanyTree", JSON.toJSONString(selectUserService
				.getOtherOrganal(str, Base.financetype)));
		DataTaxTask entityview;
		// 新增的场合
		if (entity.getId() == null) {
			entityview = new DataTaxTask();
			DateFormat df = new SimpleDateFormat("yyyy");
			Date date = new Date();
			entityview.setYear(Integer.parseInt(df.format(date)));
			// 修改的场合
		} else {
			entityview = taxTaskService.getTaxTask(entity.getId());
		}
		map.put("entityview", entityview);
		addData(map);

		return "/report/taxTask/taxTaskNewModify";
	}

	/**
	 * 弹出上报画面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/postexam")
	public String _postexam(Integer id, HttpServletRequest request,
			Map<String, Object> map, String op) throws IOException {
		DataTaxTask entityview;
		List<SysExamine> a = new ArrayList<SysExamine>();
		if (id == null) {
			entityview = new DataTaxTask();
			Date date = new Date();
			DateFormat df = new SimpleDateFormat("yyyy");
			entityview.setYear(Integer.parseInt(df.format(date)));
		} else {
			// 数据明细
			entityview = taxTaskService.getTaxTask(id);
			// 审核信息
			a = examineService.getListExamine(entityview.getId(),
					Base.examkind_taxtask);
			map.put("entityExamineviewlist", a);
		}
		map.put("entityview", entityview);
		map.put("entityList", JSON.toJSONString(entityview));
		map.put("op", op);
		return "/report/taxTask/taxTaskView";
	}

	/**
	 * 数据填报画面的删除功能
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _delete(Integer id, HttpServletRequest request,
			Map<String, Object> map) throws IOException {
		HttpSession session = request.getSession();
		HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
		CommitResult result = taxTaskService.deleteTaxTask(id, use);
		String data = "";
		data = JSONArray.toJSONString(result);
		return data;
	}

	/**
	 * 导入画面的导入功能
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _save(DataTaxTask taxTask,
			@RequestParam("excelFile") MultipartFile file,
			HttpServletRequest request, Map<String, Object> map,String isConfirm)
			throws Exception {
		CommitResult result = new CommitResult();
		// 输入流
		InputStream in = null;
		// excel解析得到的数据
		List<List<Object>> listOb = null;
		try {
			in = file.getInputStream();
			// 解析excel数据
			listOb = new ExcelTool().getBankListByExcel(in,
					file.getOriginalFilename());
			HttpSession session = request.getSession();
			HhUsers use = (HhUsers) session
					.getAttribute("gzwsession_users");
			taxTask.setStatus(Base.examstatus_1);
			result = taxTaskService.saveTaxTask(taxTask, listOb, use,isConfirm);
		} catch (Exception e) {
			e.printStackTrace();
			result = CommitResult.createErrorResult("数据导入异常！");
		}

		String data = "";
		data = JSONArray.toJSONString(result);
		return data;
	}

	/**
	 * 新增/修改画面的保存并上报功能
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveandreport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _saveandreport(DataTaxTask taxTask,
			@RequestParam("excelFile") MultipartFile file,
			HttpServletRequest request, Map<String, Object> map,String isConfirm)
			throws IOException {
		CommitResult result = new CommitResult();
		// 输入流
		InputStream in = null;
		// excel解析得到的数据
		List<List<Object>> listOb = null;
		try {
			HttpSession session = request.getSession();
			HhUsers use = (HhUsers) session
					.getAttribute("gzwsession_users");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			taxTask.setReportDate(df.format(new Date()));
			taxTask.setReportPersonId(use.getVcEmployeeId());
			taxTask.setReportPersonName(use.getVcName());
			taxTask.setStatus(Base.examstatus_2);
			// 解析excel数据
			in = file.getInputStream();
			listOb = new ExcelTool().getBankListByExcel(in,
					file.getOriginalFilename());
			// 导入数据
			result = taxTaskService.saveTaxTask(taxTask, listOb, use,isConfirm);
		} catch (Exception e) {
			e.printStackTrace();
			result = CommitResult.createErrorResult("数据导入异常！");
		}
		String data = "";
		data = JSONArray.toJSONString(result);
		return data;
	}

	/**
	 * 上报功能
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/report", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _report(Integer id, HttpServletRequest request,String isConfirm)
			throws IOException {
		HttpSession session = request.getSession();
		HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
		DataTaxTask entity = taxTaskService.getTaxTask(id);
		String data = "";
		// 未上报和已退回可以再点上报
		if (entity.getStatus().equals(Base.examstatus_1)
				|| entity.getStatus().equals(Base.examstatus_3)) {
			entity.setStatus(Base.examstatus_2);
			Date date = new Date();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entity.setReportDate(df.format(date));
			entity.setReportPersonId(use.getVcEmployeeId());
			entity.setReportPersonName(use.getVcName());
			CommitResult result = taxTaskService.saveTaxTask(entity, null, use, isConfirm);
			data = JSONArray.toJSONString(result);
		} else {
			CommitResult result = new CommitResult();
			result = CommitResult.createErrorResult("该信息已被上报！");
			data = JSONArray.toJSONString(result);
		}
		return data;
	}

	/**
	 * 跳转到审核画面
	 * 
	 * @return
	 */
	@RequestMapping("/examinelist")
	public String _examinelist(DataTaxTask entity, HttpServletRequest request,
			Map<String, Object> map) throws IOException {
		// 路径
		String mapurl = request.getContextPath() + "/taxTask";
		map.put("mapurl", mapurl);
		// 当前页码
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		// 数据权限
		HttpSession session = request.getSession();
		String str = (String) session
				.getAttribute("gzwsession_financecompany");
		entity.setParentorg(str);
		map.put("allCompanyTree", JSON.toJSONString(selectUserService
				.getOtherOrganal(str, Base.financetype)));
		// 当前页的信息取得
		MsgPage msgPage = taxTaskService.getTaxTaskListView(entity, pageNum,
				Base.pagesize, Base.fun_examine);
		map.put("msgPage", msgPage);
		map.put("searchurl", "/shanghai-gzw/taxTask/examinelist");
		map.put("entityview", entity);
		// 审核状态信息
		addData(map);
		return "/report/taxTask/taxTaskExamineList";
	}

	/**
	 * 跳转到数据审核画面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/exam")
	public String _exam(Integer id, HttpServletRequest request,
			Map<String, Object> map, String op) throws IOException {
		DataTaxTask entityview;
		List<SysExamine> a = new ArrayList<SysExamine>();
		if (id == null) {
			entityview = new DataTaxTask();
			Date date = new Date();
			DateFormat df = new SimpleDateFormat("yyyy");
			entityview.setYear(Integer.parseInt(df.format(date)));
		} else {
			// 数据明细
			entityview = taxTaskService.getTaxTask(id);
			// 审核信息
			a = examineService.getListExamine(entityview.getId(),
					Base.examkind_taxtask);
		}
		Integer status = 0;
		status = entityview.getStatus();
		map.put("entityview", entityview);
		map.put("entityList", JSON.toJSONString(entityview));
		map.put("entityExamineviewlist", a);
		map.put("status", status);
		return "/report/taxTask/taxTaskExamine";
	}

	/**
	 * 审核功能（通过/退回）
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _commitexam(Integer entityid, String examStr,
			Integer examResult, HttpServletRequest request,
			Map<String, Object> map, String op) throws IOException {
		HttpSession session = request.getSession();
		HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
		CommitResult result = taxTaskService.saveTaxTaskExamine(entityid,
				examStr, examResult, use);
		String data = "";
		data = JSONArray.toJSONString(result);
		return data;
	}

	@ResponseBody
	@RequestMapping(value = "/hasCheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String hasCheck(DataTaxTask entity, String operate,
			HttpServletRequest request, Map<String, Object> map) {
		CommitResult result = new CommitResult();
		result.setResult(true);
		if (entity == null) {
			result = CommitResult.createErrorResult("该数据已被删除");
		}
		return JSONArray.toJSONString(result);
	}

	// 获取下拉框内容
	@ResponseBody
	@RequestMapping(value = "/_childtype", method = RequestMethod.POST)
	/**
	 * 返回类型的json
	 * @param val 类型ID
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	public void getVal(Integer val, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		List<HhBase> baseList = baseService.getChildBases(val);
		String json = JSONArray.toJSONString(baseList);
		response.getWriter().write(json);
	}

	/**
	 * 审核状态下拉框信息追加
	 * 
	 */
	private void addData(Map<String, Object> map) {
		List<HhBase> examstatustype = baseService.getBases(Base.examstatustype);
		map.put("examstatustype", examstatustype);
	}
	
	/**
	 * 汇总页面
	 */
	@RequestMapping("/collect")
	public String _collect(DataTaxTask entity, String organalID, HttpServletRequest request ,Map<String, Object> map) {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str=(String) session.getAttribute("gzwsession_financecompany");	 
		String mapurl = request.getContextPath() + "/taxTask";
		map.put("mapurl", mapurl);
		//树列表信息
		
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str, Base.financetype)));
		
		
	    map.put("monthDate", Base.monthDate);
	 /*   addData(map);*/
	    String lists = null;
	    if(Common.notEmpty(entity.getOrg())){
		     lists = systemService.getPublicNoVirtualCompany(entity.getOrg());
	    }
        entity.setOrg(lists);
/*	    if(entity.getOrg()!=null){
	    	
			entity.setTaxTask("0");
			entity.setTaxPlan("0");
			entity.setFavouredPolicy("0");
			entity.setTaxReturn("0");
			entity.setInTaxReturn("0");
			
			//获取Parentorg[其实是自身的org]
			String org = entity.getOrg();
			String Parentorg = taxTaskService.getParentOrg(org);
			entity.setParentorg(Parentorg);
			
			//判断是否为第一次汇总，若为第一次汇总则为新增，否则执行修改数据
			List<DataTaxTask> list2  = taxTaskService.getTaxTaskId(entity);
			if(list2.size()==0){
			    List<DataTaxTask> list= taxTaskService.savefindCollectList2(entity);
			    List<DataTaxTask> lists=taxTaskService.getSumDataList(entity);
			    List<DataTaxTask> result = new ArrayList<DataTaxTask>();
			    for(Object obj : list) {
			    	Object[] item = (Object[])obj;
					entity.setYear(entity.getYear());	
					entity.setOrg(entity.getOrg());
					entity.setOrgName(entity.getOrgName());
					entity.setTaxTask(item[0]==null?"0":item[0].toString());
					entity.setTaxPlan(item[1]==null?"0":item[1].toString());
					entity.setFavouredPolicy(item[2]==null?"0":item[2].toString());
					entity.setTaxReturn(item[3]==null?"0":item[3].toString());
					entity.setInTaxReturn(item[4]==null?"0":item[4].toString());
					entity.setStatus(Base.examstatus_1);
					entity.setIsdel(0);
					entity.setReportPersonId(entity.getReportPersonId());
					entity.setReportPersonName(entity.getReportPersonName());
					entity.setReportDate(entity.getReportDate());
					entity.setAuditorDate(entity.getAuditorDate());
					entity.setAuditorPersonId(entity.getAuditorPersonId());
					entity.setAuditorPersonName(entity.getAuditorPersonName());
					entity.setCreatePersonId(use.getVcEmployeeId());
					entity.setCreatePersonName(use.getVcName());
					entity.setCreateDate(df.format(new Date()));
					entity.setLastModifyPersonId(entity.getLastModifyPersonId());
					entity.setLastModifyPersonName(entity.getLastModifyPersonName());
					entity.setLastModifyDate(entity.getLastModifyDate());
					entity.setParentorg(item[5]==null?"":item[5].toString());
					result.add(entity);
					//先保存自身汇总后的数据
//					taxTaskService.saveself(entity);
//					taxTaskService.savecollect2(entity);
			    }
				map.put("entityview", entity);	
				map.put("entityviews", lists);	  
			}else{				
		      List<DataTaxTask> list=taxTaskService.savefindCollectList(entity);
		      List<DataTaxTask> lists=taxTaskService.getSumDataList(entity);
		      List<DataTaxTask> result = new ArrayList<DataTaxTask>();
		  	for(Object obj : list) {
				Object[] item = (Object[])obj;
				if(item[0]==null){
					entity.setYear(entity.getYear());
					entity.setOrg(entity.getOrg());
					entity.setOrgName(entity.getOrgName());
					entity.setTaxTask("0");
					entity.setTaxPlan("0");
					entity.setFavouredPolicy("0");
					entity.setTaxReturn("0");
					entity.setInTaxReturn("0");
				
				}else{
					DataTaxPay entity1 = new DataTaxPay();
					entity.setId((Integer) (item[0]==null?"":(Integer)item[0]));
					entity.setYear((Integer) (item[1]==null?"":(Integer)item[1]));	
					entity.setOrg(item[2]==null?"":item[2].toString());
					entity.setOrgName(item[3]==null?"":item[3].toString());
					entity.setTaxTask(item[4]==null?"0":item[4].toString());
					entity.setTaxPlan(item[5]==null?"0":item[5].toString());
					entity.setFavouredPolicy(item[6]==null?"0":item[6].toString());
					entity.setTaxReturn(item[7]==null?"0":item[7].toString());
					entity.setInTaxReturn(item[8]==null?"0":item[8].toString());
					entity.setStatus((Integer) ((Integer)item[9]==null?"":(Integer)item[9]));
					entity.setIsdel((Integer) (item[10]==null? 0:(Integer)item[10]));
					entity.setReportPersonId(item[11]==null?"":item[11].toString());
					entity.setReportPersonName(item[12]==null?"":item[12].toString());
					entity.setReportDate(item[13]==null?"":item[13].toString());
					entity.setAuditorDate(item[14]==null?"":item[14].toString());
					entity.setAuditorPersonId(item[15]==null?"":item[15].toString());
					entity.setAuditorPersonName(item[16]==null?"":item[16].toString());
					entity.setCreatePersonId(item[17]==null?"":item[17].toString());
					entity.setCreatePersonName(item[18]==null?"":item[18].toString());
					entity.setCreateDate(item[19]==null?"":item[19].toString());
					entity.setLastModifyPersonId(item[20]==null?"":item[20].toString());
					entity.setLastModifyPersonName(item[21]==null?"":item[21].toString());
					entity.setLastModifyDate(item[22]==null?"":item[22].toString());
					entity.setParentorg(item[23]==null?"":item[23].toString());
					result.add(entity);
					//先保存自身汇总后的数据
//					taxTaskService.saveself(entity);
//					taxTaskService.savecollect(entity);
				}
			}	 		
			map.put("entityview", entity);	  
			map.put("entityviews", lists);
		 }    	 
	    }else{
	    	   map.put("entityview", entity);
	    }*/
        DataTaxTask  entityBackInfoBean = new DataTaxTask();
        taxTaskService.getSumDataInfo(entity,entityBackInfoBean);
        map.put("entityview", entityBackInfoBean);
        map.put("entity", entity);
        List<DataTaxTask> list = taxTaskService.getSumDataList(entity);
        map.put("entityviews", list);
		map.put("collecturl", "/shanghai-gzw/taxTask/collect");
		
		return "/report/taxTask/taxTaskCollect";
	}
	
	/**
	 * 汇总保存
	 */
	@RequestMapping(value="/saveOrUpdate", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveOrUpdate(DataTaxTask entity,HttpServletRequest request,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		entity.setStatus(Base.examstatus_1);
		entity = taxTaskService.getTaxTask(entity.getId());
		
		return JSONArray.toJSONString(taxTaskService.saveCollectList(entity, use,"save"));
	}
	
	
	/**
	 * 上报保存汇总数据
	 */
	@RequestMapping(value="/saveAndReport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveAndReport(DataTaxTask entity,HttpServletRequest request,String op) throws IOException {
		HttpSession session=request.getSession();
		CommitResult result = new CommitResult();
		String data = "";
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		if(entity.getStatus().equals(Base.examstatus_2)){
			result = CommitResult.createErrorResult("该数据已经上报!");
			data = JSONArray.toJSONString(result);
		}else{
			entity.setStatus(Base.examstatus_2);
			entity = taxTaskService.getTaxTask(entity.getId());
			data =JSONArray.toJSONString(taxTaskService.saveReportCollectList(entity, use,"saveR"));
		}
		
		return data;
	}
	
}
