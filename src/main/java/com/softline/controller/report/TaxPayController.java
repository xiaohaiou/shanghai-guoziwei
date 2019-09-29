package com.softline.controller.report;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jxls.exception.ParsePropertyException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.softline.client.task.TaskInstruction;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.ExcelTool;
import com.softline.dao.system.ISystemDao;
import com.softline.entity.HhBase;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entity.ReportFormsUnFilledCompany;
import com.softline.entity.SysExamine;
import com.softline.entity.taxTask.DataTaxPay;
import com.softline.entity.taxTask.DataTaxPayDetailNow;
import com.softline.entity.taxTask.DataTaxPayDetailPrev;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IReportRemindCompanyService;
import com.softline.service.report.ITaxPayService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.service.task.ITaskService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/taxPay")
/**
 * 
 * @author zy
 *
 */
public class TaxPayController {
	
	@Resource(name = "taxPayService")
	private ITaxPayService taxPayService;
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "examineService")
	private IExamineService examineService;
	@Resource(name = "taskService")
	private ITaskService taskService;
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	@Resource(name = "reportRemindCompanyService")
	private IReportRemindCompanyService reportRemindCompanyService;	
	@Resource(name = "systemService")
	private ISystemService systemService;

	@ModelAttribute
	public void getCommodity(
			@RequestParam(value = "id", required = false) Integer id,
			Map<String, Object> map, HttpServletRequest request) {
		if (id != null) {
			DataTaxPay entityview = taxPayService.getTaxPay(id);
			map.put("taxPay", entityview);
		}
	}

	/**
	 * 数据填报查询画面
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public String _list(DataTaxPay entity, HttpServletRequest request,
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
		String mapurl = request.getContextPath() + "/taxPay";
		map.put("mapurl", mapurl);
		// 当前页码
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		// 当前页信息取得
		MsgPage msgPage = taxPayService.getTaxPayListView(entity, pageNum,
				Base.pagesize, Base.fun_search);
		map.put("msgPage", msgPage);
		map.put("searchurl", "/shanghai-gzw/taxPay/list");
		map.put("entityview", entity);
		// 审核状态信息
		addData(map);
		return "/report/taxPay/taxPayList";
	}

	/**
	 * 弹出查看画面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String _view(Integer id, HttpServletRequest request,
			Map<String, Object> map, String op) throws IOException {

		DataTaxPay entityview;
		List<SysExamine> a = new ArrayList<SysExamine>();
		if (id == null) {
			entityview = new DataTaxPay();

		} else {
			// 数据明细
			entityview = taxPayService.getTaxPay(id);
			
			//首先判断该公司是否为虚拟公司
			int status = taxPayService.getStatus(id);
			if(status==50501){
				List<DataTaxPayDetailNow> list = taxPayService.getDetailNow(entityview, id);
				List<DataTaxPayDetailPrev> list2 = taxPayService.getDetailPre(entityview,id);
				Set<DataTaxPayDetailNow> result = new HashSet(list);
				Set<DataTaxPayDetailPrev> result2 = new HashSet(list2);
				entityview.setDataTaxPayDetailNows(result);
				entityview.setDataTaxPayDetailPrevs(result2);
								
			}
			
			// 审核信息
			a = examineService.getListExamine(entityview.getId(),
					Base.examkind_taxpay);
		}
		map.put("entityview", entityview);
		map.put("entityList", JSON.toJSONString(entityview));
		map.put("entityExamineviewlist", a);
		return "/report/taxPay/taxPayView";
	}

	/**
	 * 弹出新增/修改画面
	 * 
	 * @return
	 */
	@RequestMapping("/newmodify")
	public String _newmodify(DataTaxPay entity, HttpServletRequest request,
			Map<String, Object> map) throws IOException {
		// 树列表信息
		HttpSession session = request.getSession();
		// 数据权限
		String str = (String) session
				.getAttribute("gzwsession_financecompany");
		entity.setParentorg(str);
		map.put("allCompanyTree", JSON.toJSONString(selectUserService
				.getOtherOrganal(str, Base.financetype)));
		DataTaxPay entityview;
		// 新增的场合
		if (entity.getId() == null) {
			entityview = new DataTaxPay();
			// 修改的场合
		} else {
			entityview = taxPayService.getTaxPay(entity.getId());
		}
		map.put("entityview", entityview);
		addData(map);

		return "/report/taxPay/taxPayNewModify";
	}

	
	//collect 汇总
	@RequestMapping("/collect")
	public String _collect(DataTaxPay entity, String organalID, HttpServletRequest request ,Map<String, Object> map) {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str=(String) session.getAttribute("gzwsession_financecompany");	 
		String mapurl = request.getContextPath() + "/taxPay";
		map.put("mapurl", mapurl);
		//树列表信息		
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str, Base.financetype)));		
	    map.put("monthDate", Base.monthDate);
	    String lists = null;
	    if(Common.notEmpty(entity.getOrg())){
		     lists = systemService.getPublicNoVirtualCompany(entity.getOrg());
	    }
        entity.setOrg(lists);
	   
//	    if(entity.getOrg()!=null){
//	      entity.setNowPay("0");
//		  entity.setPrevPay("0");
//			//判断是否为第一次汇总，若为第一次汇总则为新增，否则执行修改数据
//		  List<DataTaxPay> list2  = taxPayService.getTaxSaveId(entity);
//		  if(list2.size()==0){
//			  List<DataTaxPay> list=taxPayService.savefindCollectList2(entity);
//			  List<DataTaxPay> result = new ArrayList<DataTaxPay>();
//			  for(Object obj : list) {
//				  Object[] item = (Object[])obj;
//					entity.setYear(entity.getYear());
//					entity.setMonth(entity.getYear().substring(entity.getYear().length()-2));		
//					entity.setOrg(entity.getOrg());
//					entity.setOrgName(entity.getOrgName());
//					entity.setNowPay(item[0]==null?"0":item[0].toString());
//					entity.setPrevPay(item[1]==null?"0":item[1].toString());
//					entity.setMoM(entity.getMoM());
//					entity.setAccNowPay(entity.getAccNowPay());
//					entity.setAccPrevPay(entity.getAccPrevPay());
//					entity.setAccMoM(entity.getAccMoM());
//					entity.setHnTax(entity.getHnTax());
//					entity.setRemark(entity.getRemark());
//					entity.setStatus(Base.examstatus_1);
//					entity.setIsdel(0);
//					entity.setReportPersonId(entity.getReportPersonId());
//					entity.setReportPersonName(entity.getReportPersonName());
//					entity.setReportDate(entity.getReportDate());
//					entity.setAuditorDate(entity.getAuditorDate());
//					entity.setAuditorPersonId(entity.getAuditorPersonId());
//					entity.setAuditorPersonName(entity.getAuditorPersonName());
//					entity.setCreatePersonId(use.getVcEmployeeId());
//					entity.setCreatePersonName(use.getVcName());
//					entity.setCreateDate(df.format(new Date()));
//					entity.setLastModifyPersonId(entity.getLastModifyPersonId());
//					entity.setLastModifyPersonName(entity.getLastModifyPersonName());
//					entity.setLastModifyDate(entity.getLastModifyDate());
//					entity.setParentorg(item[2]==null?"":item[2].toString());
//					result.add(entity);
////						//先保存自身汇总后的数据
////						taxPayService.saveself(entity);
////						//在保存关联表汇总后的数据
////						taxPayService.savecollect2(entity);
//			  }
//				map.put("entityview", entity);	 
//		  }else{
//		    List<DataTaxPay> list=taxPayService.savefindCollectList(entity);
//		    List<DataTaxPay> result = new ArrayList<DataTaxPay>();
//		    for(Object obj : list) {
//				Object[] item = (Object[])obj;
//				if(item[0]==null){
//					entity.setYear(entity.getYear());
//					entity.setMonth(entity.getYear().substring(entity.getYear().length()-2));
//					entity.setOrg(entity.getOrg());
//					entity.setOrgName(entity.getOrgName());
//					entity.setNowPay("0");
//					entity.setPrevPay("0");
//				
//				}else{
//					/*DataTaxPay entity1 = new DataTaxPay();*/
//					entity.setId((Integer) (item[0]==null?"":(Integer)item[0]));
//					entity.setYear(item[1]==null?"":item[1].toString());
//					entity.setMonth(item[2]==null?"":item[2].toString());
//					entity.setOrg(item[3]==null?"":item[3].toString());
//					entity.setOrgName(item[4]==null?"":item[4].toString());
//					entity.setNowPay(item[5]==null?"0":item[5].toString());
//					entity.setPrevPay(item[6]==null?"0":item[6].toString());
//					entity.setMoM(item[7]==null?"":item[7].toString());
//					entity.setAccNowPay(item[8]==null?"":item[8].toString());
//					entity.setAccPrevPay(item[9]==null?"":item[9].toString());
//					entity.setAccMoM(item[10]==null?"":item[10].toString());
//					entity.setHnTax(item[11]==null?"":item[11].toString());
//					entity.setRemark(item[12]==null?"":item[12].toString());
//					entity.setStatus((Integer) ((Integer)item[13]==null?"":(Integer)item[13]));
//					entity.setIsdel((Integer) (item[14]==null? 0:(Integer)item[14]));
//					entity.setReportPersonId(item[15]==null?"":item[15].toString());
//					entity.setReportPersonName(item[16]==null?"":item[16].toString());
//					entity.setReportDate(item[17]==null?"":item[17].toString());
//					entity.setAuditorDate(item[18]==null?"":item[18].toString());
//					entity.setAuditorPersonId(item[19]==null?"":item[19].toString());
//					entity.setAuditorPersonName(item[20]==null?"":item[20].toString());
//					entity.setCreatePersonId(item[21]==null?"":item[21].toString());
//					entity.setCreatePersonName(item[22]==null?"":item[22].toString());
//					entity.setCreateDate(item[23]==null?"":item[23].toString());
//					entity.setLastModifyPersonId(item[24]==null?"":item[24].toString());
//					entity.setLastModifyPersonName(item[25]==null?"":item[25].toString());
//					entity.setLastModifyDate(item[26]==null?"":item[26].toString());
//					entity.setParentorg(item[27]==null?"":item[27].toString());
////						entity.setSumNowpay(item[28]==null?"":item[28].toString());
////						entity.setSumPrepay(item[29]==null?"":item[29].toString());
//					result.add(entity);
////						//先保存自身汇总后的数据
////						taxPayService.saveself(entity);
////						//在保存关联表汇总后的数据
////						taxPayService.savecollect(entity);
//				}		
//			}	 		
//			map.put("entityview", entity);	  
//		  }    	 
//	    }else{
//	    	   map.put("entityview", entity);
//	    }
	   
	   DataTaxPay entityBackInfoBean = new DataTaxPay();
	   
	   taxPayService.getSumDataInfo(entity,entityBackInfoBean);
	  
	   map.put("entityview", entity);
	   map.put("entityBackInfo", entityBackInfoBean);
	   List<DataTaxPay> list = taxPayService.getSumDataList(entity);
	   map.put("entityviews", list);
	   map.put("collecturl", "/shanghai-gzw/taxPay/collect");
	   
	   addData(map);	
	   return "/report/taxPay/taxPayCollect";
	}
	
		/**
		 * 保存汇总数据
		 */
	@RequestMapping(value="/saveOrUpdate", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveOrUpdate(DataTaxPay entity,HttpServletRequest request,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		entity.setStatus(Base.examstatus_1);
		entity = taxPayService.getTaxPay(entity.getId());
		return JSONArray.toJSONString(taxPayService.saveCollectList(entity, use,"save"));
	}	
	
	/**
	 * 上报保存汇总数据
	 */
	@RequestMapping(value="/saveAndReport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveAndReport(DataTaxPay entity,HttpServletRequest request,String op) throws IOException {
		HttpSession session=request.getSession();
		CommitResult result = new CommitResult();
		String data = "";
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		if(entity.getStatus().equals(Base.examstatus_2)){
			result = CommitResult.createErrorResult("该数据已经上报!");
			data = JSONArray.toJSONString(result);
		}else{
			entity.setStatus(Base.examstatus_2);
			entity = taxPayService.getTaxPay(entity.getId());
			data = JSONArray.toJSONString(taxPayService.saveReportCollectList(entity, use,"saveR"));
		}

		return data;
	}	
		
	
	/**
	 * 弹出上报画面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/postexam")
	public String _postexam(Integer id, HttpServletRequest request,
			Map<String, Object> map, String op) throws IOException {
		DataTaxPay entityview;
		List<SysExamine> a = new ArrayList<SysExamine>();
		if (id == null) {
			entityview = new DataTaxPay();
		} else {
			// 数据明细
			entityview = taxPayService.getTaxPay(id);
			// 审核信息
			a = examineService.getListExamine(entityview.getId(),
					Base.examkind_taxpay);
			map.put("entityExamineviewlist", a);
		}
		map.put("entityview", entityview);
		map.put("entityList", JSON.toJSONString(entityview));
		map.put("op", op);
		return "/report/taxPay/taxPayView";
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
		CommitResult result = taxPayService.deleteTaxPay(id, use);
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
	public String _save(DataTaxPay taxPay,@RequestParam("excelFile") MultipartFile file,HttpServletRequest request, Map<String, Object> map,String isConfirm)throws Exception {
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
			taxPay.setStatus(Base.examstatus_1);
			result = taxPayService.saveTaxPay(taxPay, listOb, use,isConfirm);
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
	public String _saveandreport(DataTaxPay taxPay,
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
			taxPay.setReportDate(df.format(new Date()));
			taxPay.setReportPersonId(use.getVcEmployeeId());
			taxPay.setReportPersonName(use.getVcName());
			taxPay.setStatus(Base.examstatus_2);
			// 解析excel数据
			in = file.getInputStream();
			listOb = new ExcelTool().getBankListByExcel(in,
					file.getOriginalFilename());
			// 导入数据
			result = taxPayService.saveTaxPay(taxPay, listOb, use, isConfirm);
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
		DataTaxPay entity = taxPayService.getTaxPay(id);
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
			CommitResult result = taxPayService.saveTaxPay(entity, null, use, isConfirm);
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
	public String _examinelist(DataTaxPay entity, HttpServletRequest request,
			Map<String, Object> map) throws IOException {
		// 路径
		String mapurl = request.getContextPath() + "/taxPay";
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
		MsgPage msgPage = taxPayService.getTaxPayListView(entity, pageNum,
				Base.pagesize, Base.fun_examine);
		map.put("msgPage", msgPage);
		map.put("searchurl", "/shanghai-gzw/taxPay/examinelist");
		map.put("entityview", entity);
		// 审核状态信息
		addData(map);
		return "/report/taxPay/taxPayExamineList";
	}

	/**
	 * 跳转到数据审核画面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/exam")
	public String _exam(Integer id, HttpServletRequest request,
			Map<String, Object> map, String op) throws IOException {
		DataTaxPay entityview;
		List<SysExamine> a = new ArrayList<SysExamine>();
		if (id == null) {
			entityview = new DataTaxPay();
		} else {
			// 数据明细
			entityview = taxPayService.getTaxPay(id);
			// 审核信息
			a = examineService.getListExamine(entityview.getId(),
					Base.examkind_taxpay);
		}
		Integer status=0;

		status=entityview.getStatus();

		map.put("entityview", entityview);
		map.put("entityList", JSON.toJSONString(entityview));
		map.put("entityExamineviewlist", a);
		map.put("status", status);
		return "/report/taxPay/taxPayExamine";
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
		CommitResult result = taxPayService.saveTaxPayExamine(entityid,
				examStr, examResult, use);
		String data = "";
		data = JSONArray.toJSONString(result);
		return data;
	}

	@ResponseBody
	@RequestMapping(value = "/hasCheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String hasCheck(DataTaxPay entity, String operate,
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
	 * 税务未创建公司查询页面
	 * @param reportTime  上报时间
	 * @param organalID   选择的公司ID
	 * @param organalname 公司名称
	 * @param formKind    报表类型（单体OR合并）
	 * @param CompanyKind 公司类型（实体  虚拟 壳公司）
	 * @param request
	 * @param map
	 * @return
	 * 
	 * 	新增页面显示字段 ,添加方法判断是否新增未提醒公司到提醒列表
	 * 			 zl
	 */
	@RequestMapping("taxNoCreateCompanyList")
	public String taxNoCreateCompanyList(String reportTime,String organalID,String organalname,Integer formKind,Integer CompanyKind,HttpServletRequest request ,Map<String, Object> map){
		String mapurl=request.getContextPath()+ "/taxPay";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null || "".equals(curPageNum)) {
        	curPageNum = "1";
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM");
        if(!Common.notEmpty(reportTime)){
        	reportTime = df.format(new Date());
        }
        if(formKind == null){
        	formKind = Base.reportgroup_type_simple;
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        HttpSession session=request.getSession();
        String str=(String) session.getAttribute("gzwsession_financecompany");
        map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)));
        /*
         * remindKind 通过remindKind 判断提醒公司的操作
         *  1、add 添加单个公司提醒    2、remove 删除单个公司提醒    3、addAll 添加所有公司提醒
         *  4、removeAll 删除所有提醒公司
         *  
         *  remindOrg 提醒公司编号 
         */
        String remindKind = request.getParameter("remindKind");
        String remindOrg  = request.getParameter("remindOrg");
        if(Common.isNumber(remindKind) && 
        		Common.notEmpty(remindOrg) && Common.notEmpty(reportTime)){
        	ReportFormsUnFilledCompany reportFormsUnFilledCompany = new ReportFormsUnFilledCompany();
        	reportFormsUnFilledCompany.setFormKind(Base.reportUnfill_type_tax);
        	reportFormsUnFilledCompany.setUpdatetime(reportTime);
        	reportFormsUnFilledCompany.setnNodeID(remindOrg);
       		switch(Integer.valueOf(remindKind)){
       			case 1:
       				reportRemindCompanyService.saveUnfilledCompany(reportFormsUnFilledCompany);
       				break;
       			case 2:
       				reportRemindCompanyService.deleteReportFormsUnFilledCompany(reportFormsUnFilledCompany);
       				break;
       			case 3:
     				//查询的当前财务树   hh_organInfo_tree_relation
       				String treeTab = "hh_organInfo_tree_relation";
       				reportRemindCompanyService.addAllDataToRemindPlan(reportTime,str, Base.reportUnfill_type_tax,treeTab);
       				break;
       			case 4:
       				reportRemindCompanyService.setAllDataDisRemindplan(reportTime, Base.reportUnfill_type_tax);
       				break;
       			default:
       				break;
        	}
        }
        MsgPage msgPage=taxPayService.getTaxNoCreateCompanyList(reportTime, str, organalID, formKind, CompanyKind, pageNum, Base.pagesize);
        map.put("msgPage", msgPage);
	    map.put("reportTime",reportTime);
	    map.put("organalID",organalID);
	    map.put("organalname", organalname);
	    map.put("formKind", formKind);
	    map.put("pageNums", curPageNum);
	    map.put("CompanyKind", CompanyKind);
	    map.put("searchurl", "/shanghai-gzw/taxPay/taxNoCreateCompanyList");
		return "/report/taxPay/taxNoCreateCompanyList";
	}
	
	@RequestMapping("/remindCompanyList")
	public String remindCompanyList(String reportTime,String organalID,String organalname,
							Integer formKind,Integer CompanyKind,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/reportadjust";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null || "".equals(curPageNum)) {
        	curPageNum = "1";
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM");
        if(!Common.notEmpty(reportTime)){
        	reportTime = df.format(new Date());
        }
        if(formKind == null){
        	formKind = Base.reportgroup_type_simple;
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        HttpSession session=request.getSession();
        String str=(String) session.getAttribute("gzwsession_financecompany");
        MsgPage msgPage=taxPayService.getTaxNoRemindCompanyList(reportTime, str, organalID, formKind, CompanyKind, pageNum, Base.pagesize,null);
        map.put("msgPage", msgPage);
        map.put("pageNums", curPageNum);
  	    map.put("reportTime",reportTime);
  	    map.put("organalID",organalID);
  	    map.put("organalname", organalname);
  	    map.put("formKind", formKind);
  	    map.put("CompanyKind", CompanyKind);  
		return "/report/taxPay/taxNoRemindCompanyList";
	}
	
	@RequestMapping("commondDown")
	public String commondDown(String reportTime,String organalID,String organalname,
												Integer formKind,Integer CompanyKind,HttpServletRequest request ,Map<String, Object> map){
		String mapurl=request.getContextPath()+ "/remindCreateCompanyList";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null || "".equals(curPageNum)) {
        	curPageNum = "1";
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM");
        if(!Common.notEmpty(reportTime)){
        	reportTime = df.format(new Date());
        }
        if(formKind == null){
        	formKind = Base.reportgroup_type_simple;
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        HttpSession session=request.getSession();
        String str=(String) session.getAttribute("gzwsession_financecompany");
        MsgPage msgPage=taxPayService.getTaxNoRemindCompanyList(reportTime, str, organalID, formKind, CompanyKind, pageNum, Base.pagesize,"all");
        /*
         * 保存需要下达的指令
         * slctPersons     接收人   员工id
         * taskDescription 接收人
         * taskName        指令名称
         * deadLine        截止日期
         */
        TaskInstruction instruction = new TaskInstruction();
        String InstructionTitle ="税务未填报公司提醒("+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+")"; 
        List unEmailedCompanyList = new ArrayList<Object[]>(); //未提醒公司
        Map backAccounyInfo = new HashMap<String,Object>();//返回选中发送人账号和账号描述信息
        com.softline.client.task.HhUsers sysUsers = new com.softline.client.task.HhUsers();
        // 设置指令中心新加指令参数
        reportRemindCompanyService.setInstructionPara(instruction,session,sysUsers,InstructionTitle);
		//获取要下达指令员工账号信息，返回未获取到的信息	
        reportRemindCompanyService.getInstractionVcAccount_Taxpay(msgPage.getList(),unEmailedCompanyList,backAccounyInfo);		
		//保存发送指令任务
		if(null!=backAccounyInfo.get("sBSlctPersons")){
			instruction.setSlctPersons(String.valueOf(backAccounyInfo.get("sBSlctPersons")));
			taskService.save(instruction,
						     sysUsers,
						     String.valueOf(backAccounyInfo.get("sBSlctPersons")),
						     String.valueOf(backAccounyInfo.get("sBAccountNameDeps")));
		}		
		//更新发送公司的临时表，设置已发送的标志位
		reportRemindCompanyService.updateReportUnfilledCompanyTable(backAccounyInfo,reportTime,Base.reportUnfill_type_tax);
		MsgPage msgPageForShow=taxPayService.getTaxNoRemindCompanyList(reportTime, str, organalID, formKind, CompanyKind, pageNum, Base.pagesize,null);
	    map.put("msgPage", msgPageForShow);
	    map.put("reportTime", reportTime);
	    map.put("backInfo", true);
		return "/report/taxPay/taxNoRemindCompanyList";
	}
	
	@RequestMapping(value="/noCreateExport")
	public void noCreateexport(String reportTime,String organalID,String organalname,HttpServletRequest request ,Map<String, Object> map,HttpServletResponse response) throws IOException, ParsePropertyException, InvalidFormatException {
		 HttpSession session=request.getSession();
	     String str=(String) session.getAttribute("gzwsession_financecompany");
	     List result =  taxPayService.getTaxNoCreateCompanyList(reportTime, str, organalID);
		 XSSFWorkbook workBook = new XSSFWorkbook();
		 XSSFCell cell = null;
		 XSSFSheet sheet = workBook.createSheet("123");
		 XSSFRow row = sheet.createRow((int) 0);  
		 String [] header={"公司名称","公司简称","时间"};
		for (int i = 0; i < header.length; i++) {
			cell = row.createCell((short) i);
			cell.setCellValue(header[i]);

		}
		 for (int i = 0; i < result.size(); i++) {  
			 	int k=0;
			     row = sheet.createRow((int) i + 1);
			     Object[] value = (Object[]) result.get(i);
		//				     row.createCell((short)k++).setCellValue(i+1);
		     if (value[0]!=null) {
		    	 row.createCell((short)k++).setCellValue((String)value[0]);
		//					      单位名称
			}else {
				row.createCell((short)k++).setCellValue("");
			}
		     if (value[1]!=null) {
		    	 row.createCell((short)k++).setCellValue((String)value[1]);
		//					      单位名称
			}else {
				row.createCell((short)k++).setCellValue("");
			}
		     
		     if (reportTime!=null) {
		    	 row.createCell((short)k++).setCellValue(reportTime);
		//					      单位名称
			}else {
				row.createCell((short)k++).setCellValue("");
				}
		 }  
		     // 清空response
		    response.reset();
		    String _name = "未创建税务报表公司查询.xlsx";
		    OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
		    String codedfilename = null;
			String agent = request.getHeader("USER-AGENT");
			if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
					&& -1 != agent.indexOf("Trident")) {// ie
				String name = java.net .URLEncoder.encode(_name, "UTF8");
				codedfilename = name;
			} else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等
		
				codedfilename = new String(_name.getBytes("UTF-8"),"iso-8859-1");
			}
		    response.setContentType("application/octet-stream");
		    response.setHeader("Content-Disposition", "attachment;filename=" + codedfilename);
		        workBook.write(toClient);
		        toClient.flush();
		        toClient.close();  
		}
}
