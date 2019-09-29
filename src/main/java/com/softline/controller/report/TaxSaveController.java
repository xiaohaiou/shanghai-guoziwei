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
import com.softline.entity.DataTaxSave;
import com.softline.entity.DataTaxSaveDetail;
import com.softline.entity.HhBase;
import com.softline.entity.HhUsers;
import com.softline.entity.SysExamine;
import com.softline.entity.taxTask.DataTaxPay;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.ITaxSaveService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/taxSave")
/**
 * 
 * @author zy
 *
 */
public class TaxSaveController {

	@Resource(name = "taxSaveService")
	private ITaxSaveService taxSaveService;
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
			DataTaxSave entityview = taxSaveService.getTaxSave(id);
			map.put("taxSave", entityview);
		
		}
	}

	/**
	 * 数据填报查询画面
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public String _list(DataTaxSave entity, HttpServletRequest request,
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
		String mapurl = request.getContextPath() + "/taxSave";
		map.put("mapurl", mapurl);
		// 当前页码
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		// 当前页信息取得
		MsgPage msgPage = taxSaveService.getTaxSaveListView(entity, pageNum,
				Base.pagesize, Base.fun_search);
		map.put("msgPage", msgPage);
		map.put("searchurl", "/shanghai-gzw/taxSave/list");
		map.put("entityview", entity);
		// 审核状态信息
		addData(map);
		return "/report/taxSave/taxSaveList";
	}

	
	

	
	/**
	 * 弹出查看画面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String _view(Integer id, HttpServletRequest request,
			Map<String, Object> map, String op) throws IOException {

		DataTaxSave entityview;
		List<SysExamine> a = new ArrayList<SysExamine>();
		if (id == null) {
			entityview = new DataTaxSave();
		} else {

			// 数据明细
			entityview = taxSaveService.getTaxSave(id);
			//首先判断该公司是否为虚拟公司
			int status = taxSaveService.getStatus(id);
			if(status==50501){
				List<DataTaxSaveDetail> list = taxSaveService.getDetail(entityview,id);
				Set<DataTaxSaveDetail> result = new HashSet(list);
				entityview.setDataTaxSaveDetails(result);
								
			}
			// 审核信息
			a = examineService.getListExamine(entityview.getId(),
					Base.examkind_taxsave);
		}
		map.put("entityview", entityview);
		map.put("entityList", JSON.toJSONString(entityview));
		map.put("entityExamineviewlist", a);
		return "/report/taxSave/taxSaveView";
	}

	/**
	 * 弹出新增/修改画面
	 * 
	 * @return
	 */
	@RequestMapping("/newmodify")
	public String _newmodify(DataTaxSave entity, HttpServletRequest request,
			Map<String, Object> map) throws IOException {
		// 树列表信息
		HttpSession session = request.getSession();
		// 数据权限
		String str = (String) session
				.getAttribute("gzwsession_financecompany");
		entity.setParentorg(str);
		map.put("allCompanyTree", JSON.toJSONString(selectUserService
				.getOtherOrganal(str, Base.financetype)));
		DataTaxSave entityview;
		// 新增的场合
		if (entity.getId() == null) {
			entityview = new DataTaxSave();
			// 修改的场合
		} else {
			entityview = taxSaveService.getTaxSave(entity.getId());
		}
		map.put("entityview", entityview);
		addData(map);

		return "/report/taxSave/taxSaveNewModify";
	}

	/**
	 * 弹出上报画面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/postexam")
	public String _postexam(Integer id, HttpServletRequest request,
			Map<String, Object> map, String op) throws IOException {
		DataTaxSave entityview;
		List<SysExamine> a = new ArrayList<SysExamine>();
		if (id == null) {
			entityview = new DataTaxSave();
	
		} else {
			// 数据明细
			entityview = taxSaveService.getTaxSave(id);
			// 审核信息
			a = examineService.getListExamine(entityview.getId(),
					Base.examkind_taxsave);
			map.put("entityExamineviewlist", a);
		}
		map.put("entityview", entityview);
		map.put("entityList", JSON.toJSONString(entityview));
		map.put("op", op);
		return "/report/taxSave/taxSaveView";
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
		CommitResult result = taxSaveService.deleteTaxSave(id, use);
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
	public String _save(DataTaxSave taxSave,
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
			taxSave.setStatus(Base.examstatus_1);
			result = taxSaveService.saveTaxSave(taxSave, listOb, use,isConfirm);
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
	public String _saveandreport(DataTaxSave taxSave,
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
			taxSave.setReportDate(df.format(new Date()));
			taxSave.setReportPersonId(use.getVcEmployeeId());
			taxSave.setReportPersonName(use.getVcName());
			taxSave.setStatus(Base.examstatus_2);
			// 解析excel数据
			in = file.getInputStream();
			listOb = new ExcelTool().getBankListByExcel(in,
					file.getOriginalFilename());
			result = taxSaveService.saveTaxSave(taxSave, listOb, use,isConfirm);
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
		DataTaxSave entity = taxSaveService.getTaxSave(id);
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
			CommitResult result = taxSaveService.saveTaxSave(entity, null, use,isConfirm);
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
	public String _examinelist(DataTaxSave entity, HttpServletRequest request,
			Map<String, Object> map) throws IOException {
		// 路径
		String mapurl = request.getContextPath() + "/taxSave";
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
		MsgPage msgPage = taxSaveService.getTaxSaveListView(entity, pageNum,
				Base.pagesize, Base.fun_examine);
		map.put("msgPage", msgPage);
		map.put("searchurl", "/shanghai-gzw/taxSave/examinelist");
		map.put("entityview", entity);
		// 审核状态信息
		addData(map);
		return "/report/taxSave/taxSaveExamineList";
	}

	/**
	 * 跳转到数据审核画面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/exam")
	public String _exam(Integer id, HttpServletRequest request,
			Map<String, Object> map, String op) throws IOException {
		DataTaxSave entityview;
		List<SysExamine> a = new ArrayList<SysExamine>();
		if (id == null) {
			entityview = new DataTaxSave();

		} else {
			// 数据明细
			entityview = taxSaveService.getTaxSave(id);
			// 审核信息
			a = examineService.getListExamine(entityview.getId(),
					Base.examkind_taxsave);
		}
		Integer status = 0;
		status = entityview.getStatus();
		map.put("entityview", entityview);
		map.put("entityList", JSON.toJSONString(entityview));
		map.put("entityExamineviewlist", a);
		map.put("status", status);
		return "/report/taxSave/taxSaveExamine";
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
		CommitResult result = taxSaveService.saveTaxSaveExamine(entityid,
				examStr, examResult, use);
		String data = "";
		data = JSONArray.toJSONString(result);
		return data;
	}

	@ResponseBody
	@RequestMapping(value = "/hasCheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String hasCheck(DataTaxSave entity, String operate,
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
	
	//collect 汇总
	@RequestMapping("/collect")
	public String _collect(DataTaxSave entity, String organalID, HttpServletRequest request ,Map<String, Object> map) {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str=(String) session.getAttribute("gzwsession_financecompany");	 
		String mapurl = request.getContextPath() + "/taxSave";
		map.put("mapurl", mapurl);
		//树列表信息	
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str, Base.financetype)));		
	    map.put("monthDate", Base.monthDate);
	    DataTaxSave dataTaxSaveBeanOut = new DataTaxSave();
	    String lists = null;
	    if(Common.notEmpty(entity.getOrg())){
		     lists = systemService.getPublicNoVirtualCompany(entity.getOrg());
	    }
        entity.setOrg(lists);
	   
	    if(entity.getOrg()!=null){
////	    	//先判断该公司是否为虚拟公司，
////	    	CommitResult result1 = taxSaveService.isvirtual(entity);
////	    	if(result1!=null){
////	    		result1.setResult(true);
////	    		
////	    		return "/report/taxSave/taxSaveCollect";
////	    	}
//            entity.setInTaxReturn("0");
//			entity.setTaxReturn("0");
//			entity.setTaxPlan("0");
//			entity.setFavouredPolicy("0");
//			entity.setTaxSave("0");	
//				
//			//判断是否为第一次汇总，若为第一次汇总则为新增，否则执行修改数据
//			List<DataTaxSave> list2  = taxSaveService.getTaxSaveId(entity);
//			if(list2.size()==0){
//				List<DataTaxSave> list=taxSaveService.savefindCollectList2(entity);
//				List<DataTaxSave> result = new ArrayList<DataTaxSave>();
//				for(Object obj : list) {
//					 Object[] item = (Object[])obj;
//						entity.setYear(entity.getYear());
//						entity.setMonth(entity.getYear().substring(entity.getYear().length()-2));	
//						entity.setOrg(entity.getOrg());
//						entity.setOrgName(entity.getOrgName());
//						entity.setInTaxReturn(item[0]==null?"0":item[0].toString());
//						entity.setTaxReturn(item[1]==null?"0":item[1].toString());
//						entity.setTaxPlan(item[2]==null?"0":item[2].toString());
//						entity.setFavouredPolicy(item[3]==null?"0":item[3].toString());
//						entity.setTaxSave(item[4]==null?"0":item[4].toString());					
//						entity.setAccTaxSave("");
//						entity.setRemark(entity.getRemark());
//						entity.setStatus(Base.examstatus_1);
//						entity.setIsdel(0);
//						entity.setReportPersonId(entity.getReportPersonId());
//						entity.setReportPersonName(entity.getReportPersonName());
//						entity.setReportDate(entity.getReportDate());
//						entity.setAuditorDate(entity.getAuditorDate());
//						entity.setAuditorPersonId(entity.getAuditorPersonId());
//						entity.setAuditorPersonName(entity.getAuditorPersonName());
//						entity.setCreatePersonId(use.getVcEmployeeId());
//						entity.setCreatePersonName(use.getVcName());
//						entity.setCreateDate(df.format(new Date()));
//						entity.setLastModifyPersonId(entity.getLastModifyPersonId());
//						entity.setLastModifyPersonName(entity.getLastModifyPersonName());
//						entity.setLastModifyDate(entity.getLastModifyDate());
//						entity.setParentorg(item[5]==null?"":item[5].toString());
//						result.add(entity);
//						//先保存自身汇总后的数据
////						taxSaveService.saveself(entity);
////						//在保存关联表汇总后的数据
////						taxSaveService.savecollect2(entity);
//				}
//				  map.put("entityview", entity);
//			}else{	
//			  List<DataTaxSave> list=taxSaveService.savefindCollectList(entity);
//		 	  List<DataTaxSave> result = new ArrayList<DataTaxSave>();
//              for(Object obj : list) {
//				 Object[] item = (Object[])obj;
//				 if(item[0]==null){						
//					entity.setYear(entity.getYear());
//					entity.setMonth(entity.getYear().substring(entity.getYear().length()-2));
//					entity.setOrg(entity.getOrg());
//					entity.setOrgName(entity.getOrgName());
//					entity.setInTaxReturn("0");
//					entity.setTaxReturn("0");
//					entity.setTaxPlan("0");
//					entity.setFavouredPolicy("0");
//					entity.setTaxSave("0");	
//					map.put("entityview", entity);
//				 }else{
////					DataTaxSave entity1 = new DataTaxSave();
//					entity.setId((Integer) (item[0]==null?"":(Integer)item[0]));
//					entity.setYear(item[1]==null?"":item[1].toString());
//					entity.setMonth(item[2]==null?"":item[2].toString());	
//					entity.setOrg(item[3]==null?"":item[3].toString());
//					entity.setOrgName(item[4]==null?"":item[4].toString());
//					entity.setInTaxReturn(item[5]==null?"0":item[5].toString());
//					entity.setTaxReturn(item[6]==null?"0":item[6].toString());
//					entity.setTaxPlan(item[7]==null?"0":item[7].toString());
//					entity.setFavouredPolicy(item[8]==null?"0":item[8].toString());
//					entity.setTaxSave(item[9]==null?"0":item[9].toString());					
//					entity.setAccTaxSave(item[10]==null?"":item[10].toString());
//					entity.setRemark(item[11]==null?"":item[11].toString());
//					entity.setStatus((Integer) ((Integer)item[12]==null?"":(Integer)item[12]));
//					entity.setIsdel((Integer) (item[13]==null? 0:(Integer)item[13]));
//					entity.setReportPersonId(item[14]==null?"":item[14].toString());
//					entity.setReportPersonName(item[15]==null?"":item[15].toString());
//					entity.setReportDate(item[16]==null?"":item[16].toString());
//					entity.setAuditorDate(item[17]==null?"":item[17].toString());
//					entity.setAuditorPersonId(item[18]==null?"":item[18].toString());
//					entity.setAuditorPersonName(item[19]==null?"":item[19].toString());
//					entity.setCreatePersonId(item[20]==null?"":item[20].toString());
//					entity.setCreatePersonName(item[21]==null?"":item[21].toString());
//					entity.setCreateDate(item[22]==null?"":item[22].toString());
//					entity.setLastModifyPersonId(item[23]==null?"":item[23].toString());
//					entity.setLastModifyPersonName(item[24]==null?"":item[24].toString());
//					entity.setLastModifyDate(item[25]==null?"":item[25].toString());
//					entity.setParentorg(item[26]==null?"":item[26].toString());
//					result.add(entity);
//					//先保存自身汇总后的数据
////					taxSaveService.saveself(entity);
////					//在保存关联表汇总后的数据
////					taxSaveService.savecollect(entity);
//					}	
//				}				
//			    map.put("entityview", entity);	 
//			}
//		
//	    }else{
	    
	    	taxSaveService.getSumDataTaxSaveSonBeanData(entity,dataTaxSaveBeanOut);
	    }
	    List<DataTaxSave> list = taxSaveService.getSumDataList(entity);	
	    map.put("entityview", entity);
	    map.put("entity", dataTaxSaveBeanOut);
	    map.put("entityviews", list);
		map.put("collecturl", "/shanghai-gzw/taxSave/collect");	
	    addData(map);
		return "/report/taxSave/taxSaveCollect";
	}

	
	//汇总保存
	@RequestMapping(value="/saveOrUpdate", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveOrUpdate(DataTaxSave entity,HttpServletRequest request,String op) throws IOException {

		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		entity.setStatus(Base.examstatus_1);
		entity = taxSaveService.getTaxSave(entity.getId());
		return JSONArray.toJSONString(taxSaveService.saveCollectList(entity, use,"save"));
		
	
	}
	
	//汇总上报保存
	@RequestMapping(value="/saveAndReport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveAndReport(DataTaxSave entity,HttpServletRequest request,String op) throws IOException {
		HttpSession session=request.getSession();
		CommitResult result = new CommitResult();
		String data = "";
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		if(entity.getStatus().equals(Base.examstatus_2)){
			result = CommitResult.createErrorResult("该数据已经上报!");
			data = JSONArray.toJSONString(result);
			
		}else{
			entity.setStatus(Base.examstatus_2);
			entity = taxSaveService.getTaxSave(entity.getId());
			data = JSONArray.toJSONString(taxSaveService.saveReportCollectList(entity, use,"saveR"));
		}
		
		return data;
	}
}
