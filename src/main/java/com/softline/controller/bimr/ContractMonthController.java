package com.softline.controller.bimr;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.util.HSSFColor.BLUE;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.softline.entity.HhBase;
import com.softline.entity.HhUsers;
import com.softline.entity.SysExamine;
import com.softline.entity.bimr.BimrContractMonth;
import com.softline.entity.bimr.BimrContractMonthList;
import com.softline.entity.bimr.BimrTrain;
import com.softline.entityTemp.CommitResult;
import com.softline.service.bimr.IContractMonthService;
import com.softline.service.bimr.impl.ContractMonthService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

/**
 * 
 * hwx	合同导入
 * 
 * */
@Controller
@RequestMapping("/bimr/contractmonth")
public class ContractMonthController {
	static final String K_MSG_PAGE = "msgPage";

	static final String K_SEARCHURL = "searchurl";

	static final String K_PAGE_NUMS = "pageNums";

	static final String K_shanghai_gzw_SESSION_COMPANY = "gzwsession_company";

	private static final Logger LOGGER = Logger.getLogger(ContractModelController.class);

	@Resource(name = "baseService")
	private IBaseService baseService;

	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;

	@Resource(name = "systemService")
	private ISystemService systemService;

	@Resource(name = "contractMonthService")
	private IContractMonthService contractMonthService;
	
	@Autowired
	private IExamineService examineService;

	@ModelAttribute
	public void getCommodity(
			@RequestParam(value = "id", required = false) Integer id,
			Map<String, Object> map, HttpServletRequest request) {
		if (id != null) {
			BimrContractMonth entityview = contractMonthService
					.GetBimrContractMonthById(id);
			map.put("bimrContractMonth", entityview);
		}
	}

	/**
	 * 跳转到审核画面
	 * 
	 * @return
	 */
	@RequestMapping("/import")
	public String _examinelist(String compId, Integer year, Integer month,
			Integer status, String compName, HttpServletRequest request,
			Map<String, Object> map,String type) throws IOException {
		map.put("type", type);
		
		// 公司下拉框数据加载
		String authCompanys = getSessionAuthCompany(request);
		map.put("allCompanyTree",
				JSON.toJSONString(selectUserService.getHrOrganal(authCompanys)));

		// 审核状态
		List<HhBase> contractStatus = baseService.getBases(Base.examstatustype);
		map.put("contractStatus", contractStatus);

		// 路径
		String mapurl = request.getContextPath() + "/bimr/contractmonth/import";
		map.put("mapurl", mapurl);
		// 当前页码
		String curPageNum = request.getParameter(K_PAGE_NUMS);
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		/*if("look".equals(type)){
			status = Base.examstatus_4;
		}*/

		BimrContractMonth qryParams = new BimrContractMonth();
		if (Common.notEmpty(compId)) {
        	if (compId.split(",").length==1) {
        		qryParams.setCompId(systemService.getDataauth(compId));
			}else {
				qryParams.setCompId(compId);
			}
        }else {
			qryParams.setCompId(compId);
		}
		qryParams.setYear(year);
		qryParams.setMonth(month);
		qryParams.setStatus(status);
		qryParams.setDataAuth(systemService.getDataauth(authCompanys));

		// 当前页的信息取得
		MsgPage msgPage = contractMonthService.GetContractMonths(qryParams, pageNum, Base.pagesize);
		map.put(K_MSG_PAGE, msgPage);

		String searchURL = "/shanghai-gzw/bimr/contractmonth/import";
		if(Common.notEmpty(type)){
			// 合同导入、审核、查询，分类型翻页。
			searchURL += "?type="+type;
		}
		map.put(K_SEARCHURL, searchURL);

		// 参数回绑定
		map.put("compName", compName);
		map.put("compId", compId);
		map.put("year", year);
		map.put("month", month);
		map.put("status", status);
		if(type == null){
			type = "import";
		}
		
		if("import".equals(type))
			return "/bimr/contractMonth/contractMonthImport";
		else if("examine".equals(type))
			return "/bimr/contractMonth/contractMonthExamine";
		else
			return "/bimr/contractMonth/contractMonthLook";
	}

	
	
	
	@RequestMapping("/export")
	public void _examinelistexport(String compId, Integer year, Integer month,
			Integer status, String compName, HttpServletRequest request,HttpServletResponse response,
			Map<String, Object> map,String type) throws IOException {
		
		
		// 公司下拉框数据加载
		String authCompanys = getSessionAuthCompany(request);
		map.put("allCompanyTree",
				JSON.toJSONString(selectUserService.getHrOrganal(authCompanys)));

		

		BimrContractMonth qryParams = new BimrContractMonth();
		if (Common.notEmpty(compId)) {
			qryParams.setCompId(systemService.getDataauth(compId));
		}else {
			qryParams.setCompId(compId);
		}
		qryParams.setYear(year);
		qryParams.setMonth(month);
		qryParams.setStatus(Base.examstatus_4);
		qryParams.setDataAuth(systemService.getDataauth(authCompanys));
		
		// 当前页的信息取得
		//MsgPage msgPage = contractMonthService.GetContractMonths(qryParams, pageNum, Base.pagesize);
		
		List<BimrContractMonth> list=contractMonthService.getContractMonthListExport(qryParams);
		
		List<BimrContractMonthList> exportList=new ArrayList<BimrContractMonthList>();
		
		for (int i = 0; i < list.size(); i++) {
			List<BimrContractMonthList> newList=contractMonthService.getBimrContractMonthListExport(list.get(i).getId());
			exportList.addAll(newList);
		}
		
		XSSFWorkbook workBook=contractMonthService.getExportWorkBook(exportList);

        
		 
		 
        // 清空response
	     response.reset();
	     String _name = "合同台账数据.xlsx";
	     OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
	     String codedfilename = null;
			String agent = request.getHeader("USER-AGENT");
			if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
					&& -1 != agent.indexOf("Trident")) {// ie

				String name = java.net .URLEncoder.encode(_name, "UTF8");

				codedfilename = name;
			} else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等

				codedfilename = new String(_name.getBytes("UTF-8"),
						"iso-8859-1");
			}
	     response.setContentType("application/octet-stream");
	     response.setHeader("Content-Disposition", "attachment;filename=" + codedfilename);
	     workBook.write(toClient);
	     toClient.flush();
	     toClient.close(); 
	}
	/**
	 * 获取 session 中的 K_gzwSESSION_COMPANY 设置。 
	 * @param request
	 * @return
	 * @since 2018-02-27
	 */
	String getSessionAuthCompany(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String str = (String) session.getAttribute(K_shanghai_gzw_SESSION_COMPANY);
		return str;
	}
	
	
	
	@RequestMapping("/detailLook")
	public String detailLook(BimrContractMonthList entity,HttpServletRequest request, Map<String, Object> map) throws IOException {
		
		String str = getSessionAuthCompany(request);
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)));

		//调查类型
		List<HhBase> investigationType = baseService.getBases(Base.compliance_investigation_type);
		map.put("investigationType", investigationType);
		
		//项目状态
		List<HhBase> complianceStatus = baseService.getBases(Base.compliance_status);
		map.put("complianceStatus", complianceStatus);
		
		// 路径
		String mapurl = request.getContextPath() + "/bimr/contractmonth";
		map.put("mapurl", mapurl);		
		String authCompanys = getSessionAuthCompany(request);
		String dataauth = systemService.getDataauth(authCompanys);
		entity.setDataauth(dataauth);
		if (Common.notEmpty(entity.getCompId())) {
        	if (entity.getCompId().split(",").length==1) {
        		entity.setCompId(systemService.getDataauth(entity.getCompId()));
			}
        }
		// 当前页码
		String curPageNum = request.getParameter(K_PAGE_NUMS);
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		// 当前页的信息取得
		MsgPage msgPage = contractMonthService.getContractMonthListView(entity, pageNum, Base.pagesize);
		map.put(K_MSG_PAGE, msgPage);
		map.put(K_SEARCHURL, "/shanghai-gzw/bimr/contractmonth/detailLook");
		map.put("entity", entity);
		
		return "/bimr/contractMonth/contractMonthDetailLook";
			
	}

	@RequestMapping("addOrModify")
	public String _addOrModify(Map<String, Object> map,
			HttpServletRequest request) throws IOException {

		String str = getSessionAuthCompany(request);
		map.put("allCompanyTree",
				JSON.toJSONString(selectUserService.getHrOrganal(str)));

		return "/bimr/contractMonth/contractMonthAdd";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public CommitResult save(@RequestParam String compId, 
			@RequestParam Integer year, @RequestParam Integer month,
			@RequestParam Integer status, @RequestParam String compName, 
			MultipartFile updatafile, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		HhUsers user = (HhUsers) session.getAttribute("gzwsession_users");

		if (updatafile == null){
			CommitResult result = new CommitResult();
			result.setResult(false);
			result.setExceptionStr("上传文件不能为空");
			return result;
		}
 
		InputStream inputStream = null;
		try{
			inputStream = updatafile.getInputStream();
			return contractMonthService.ImportData(inputStream, compId, year, month, status, compName, user);
		}catch (Exception e) {
			LOGGER.error("Import contract fail, error is " + e.getMessage(), e);
		}finally{
			if(inputStream != null){
				try{
					inputStream.close();
				}catch (IOException ex) {
					//none instance;
					LOGGER.error("Fail to close inputStream.", ex);
				}
			}
		}
		
		CommitResult result = new CommitResult();
		result.setResult(false);
		result.setExceptionStr("导入失败");
		return result;
	}

	@ResponseBody
	@RequestMapping("del")
	public String _del(Integer id, String lastModifyDate,
			Map<String, Object> map, HttpServletRequest request)
			throws IOException {

		CommitResult res = new CommitResult();
		res.setResult(contractMonthService.DelBimrContractMonthById(
				id,
				(HhUsers) request.getSession().getAttribute(
						"gzwsession_users")));
		String str = JSONArray.toJSONString(res);
		return str;
	}

	/**
	 * 上报
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("reportview")
	public String _reportview(BimrContractMonth entity, HttpServletRequest request,
			Map<String, Object> map,String type) {
		map.put("type", type);
		map.put("entity", entity);

		// 审核状态
		List<HhBase> contractStatus = baseService.getBases(Base.examstatustype);
		map.put("contractStatus", contractStatus);

		// 路径
		String mapurl = request.getContextPath() + "/bimr/contractmonth/reportview";
		List<String> conditions = new ArrayList<String>();
		if(entity.getId() != null){
			conditions.add("id="+entity.getId());
		}
		if(Common.notEmpty(type)){
				conditions.add( "type="+type);
		}
		if(conditions.size()>0){
			mapurl += "?" + conditions.get(0);
		}
		if(conditions.size()>1){
			mapurl += "&" + conditions.get(1);
		}
		map.put("mapurl", mapurl);

		// 当前页码
		String curPageNum = request.getParameter(K_PAGE_NUMS);
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		// 上报信息
		BimrContractMonth contractMonth = contractMonthService
				.GetBimrContractMonthById(entity.getId());
		map.put("contractMonth", contractMonth);
		// 合同信息
		MsgPage msgPage = contractMonthService.GetContractMonthsList(entity.getId(),
				pageNum, Base.pagesize);

		map.put(K_MSG_PAGE, msgPage);
		map.put(K_SEARCHURL, mapurl);
		
		// 审核信息
		List<SysExamine> contractMonthCheckList = examineService.getListExamine(
				ContractMonthService.EXAMINE_CONTRACT_MONTH_ID, entity.getId());
		map.put("contractMonthCheckList", contractMonthCheckList);
		
		return "/bimr/contractMonth/contractmonthReport";
	}

	/**
	 * 提交审核
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="/report", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public CommitResult submitAudit(@RequestParam Integer id, HttpServletRequest request) {
		HttpSession session = request.getSession();
		HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
		CommitResult result = contractMonthService.saveAuditSubmit(id, use);
		return result;
	}
	
	
	@RequestMapping(value="saveAudit", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public CommitResult saveAudit(@RequestParam Integer id, 
			@RequestParam String content, @RequestParam Boolean isPass, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
		return contractMonthService.saveAudit(id, content, isPass, use);
	}

	/**
	 * 跳转到审核画面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String _view(@RequestParam Integer id, 
			HttpServletRequest request, Map<String, Object> map) {
		
		map.put("id", id);

		// 审核状态
		List<HhBase> contractStatus = baseService.getBases(Base.examstatustype);
		map.put("contractStatus", contractStatus);

		// 路径
		String mapurl = request.getContextPath() + "/bimr/contractmonth/view";
		map.put("mapurl", mapurl);

		// 当前页码
		String curPageNum = request.getParameter(K_PAGE_NUMS);
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		// 上报信息
		BimrContractMonth contractMonth = contractMonthService.GetBimrContractMonthById(id);
		map.put("contractMonth", contractMonth);
		
		// 合同信息
		MsgPage msgPage = contractMonthService.GetContractMonthsList(id, pageNum, Base.pagesize);
		map.put(K_MSG_PAGE, msgPage);
		map.put(K_SEARCHURL, mapurl);
		
		// 审核信息
		List<SysExamine> contractMonthCheckList = examineService.getListExamine(
				ContractMonthService.EXAMINE_CONTRACT_MONTH_ID, id);
		map.put("contractMonthCheckList", contractMonthCheckList);
		 
		return "/bimr/contractMonth/contractmonthView";
	}
	
	@RequestMapping("/detailView")
	public String detailView(Integer id, HttpServletRequest request,
			Map<String, Object> map) throws IOException {
		
		BimrContractMonthList entity= contractMonthService.GetBimrContractMonthListById(id);
		map.put("entity", entity);
		if(entity!=null){
			BimrContractMonth entityParent= contractMonthService.GetBimrContractMonthById(entity.getId());
			map.put("entityParent", entityParent);
		}

		
		return "/bimr/contractMonth/contractMonthDetailView";
									
	}

}
