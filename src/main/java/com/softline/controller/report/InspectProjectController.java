package com.softline.controller.report;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jxls.exception.ParsePropertyException;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import com.softline.common.CompanyLevelTree;
import com.softline.common.CompanyTree;
import com.softline.entity.HhBase;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entity.ReportInspectProject;
import com.softline.entity.ReportInspectProjectOrder;
import com.softline.entity.ReportInspectProjectPlan;
import com.softline.entity.ReportInspectProjectProblem;
import com.softline.entity.SysExamine;
import com.softline.entity.bimr.BimrCivilcaseWeek;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IInspectProjectService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IPortalMsgService;
import com.softline.service.system.ISystemService;
import com.softline.service.system.ITreeService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/inspectproject")
/**
 * 
 * @author tch
 *
 */
public class InspectProjectController {

	@Resource(name = "inspectProjectService")
	private IInspectProjectService inspectProjectService;
	
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "examineService")
	private IExamineService examineService;	
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	@Resource(name = "systemService")
	private ISystemService systemService;
	@Resource(name = "potalMsgService")
	private IPortalMsgService potalMsgService;
	@Resource(name = "treeService")
	private ITreeService treeService;
	
	@ModelAttribute
	public void getCommodity(@RequestParam(value = "id", required = false) Integer id,
							@RequestParam(value = "problemId", required = false) Integer problemId,
							@RequestParam(value = "orderId", required = false) Integer orderId
							,Map<String, Object> map, HttpServletRequest request) {
		if (id != null) {
			ReportInspectProject entityview=inspectProjectService.getInspectProjectById(id);
			map.put("reportInspectProject", entityview);
		}
		if (problemId != null) {
			ReportInspectProjectProblem entityview=inspectProjectService.getReportInspectProjectProblemById(problemId);
			map.put("reportInspectProjectProblem", entityview);
		}
		if (orderId != null) {
			ReportInspectProjectOrder entityview=inspectProjectService.getReportInspectProjectOrderById(orderId);
			map.put("reportInspectProjectOrder", entityview);
		}
	}
	
	/**
	 * 跳转到审核画面
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public String _examinelist(ReportInspectProject entity,String type,String stype,
			HttpServletRequest request, Map<String, Object> map)
			throws IOException {
		map.put("type", type);
		map.put("stype", stype); //leader领导查询   common一般查询
		
		
		
		//稽核类型
		List<HhBase> inspectType = baseService.getBases(Base.inspect_project_inspect_type);
		map.put("inspectType", inspectType);
		
		//稽核状态
		List<HhBase> inspectStatus = baseService.getBases(Base.inspect_project_status);
		map.put("inspectStatus", inspectStatus);
		
		//财务事项类型
		List<HhBase> itemType = baseService.getBases(Base.inspect_project_item_type);
		map.put("itemType", itemType);
		// 路径
		String mapurl = request.getContextPath() + "/inspectproject";
		map.put("mapurl", mapurl);
		
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		String json=JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype));
		map.put("allCompanyTree", json);
		
		//权限
		 entity.setParentorg(str);
		
		// 当前页码
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		// 当前页的信息取得
		MsgPage msgPage = inspectProjectService.getInspectProjectListView(
				entity, pageNum, Base.pagesize);
		map.put("msgPage", msgPage);
		map.put("searchurl", "/shanghai-gzw/inspectproject/list?type="+type+"&stype="+stype);
		map.put("entityview", entity);
		
		
		//type basic基本信息维护  manage稽核财务事项信息维护  order领导查询
		if(type.equals("basic"))
			return "/report/inspectProject/inspectProjectList";
		else if(type.equals("manage"))
			return "/report/inspectProject/inspectProjectManage";
		else
			return "/report/inspectProject/inspectProjectOrderList";
			
	}

	@RequestMapping(value = "/moneyExport")
	public void moneyExport1(ReportInspectProject entity,
			HttpServletRequest request, Map<String, Object> map,
			HttpServletResponse response) throws IOException,
			ParsePropertyException, InvalidFormatException {
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		entity.setParentorg(str); //添加权限
		List<ReportInspectProject> list = inspectProjectService
				.getshixiangQry(entity);

		XSSFWorkbook workBook = new XSSFWorkbook();
		// 首先我们需要创建一个工作簿
		XSSFCell cell = null;
		// 设置单元格为空
		//
		XSSFSheet sheet = workBook.createSheet();
		// 定义表名为123
		XSSFRow row = sheet.createRow((int) 0);
		// 创建行
		// 下面是表头属性

		String[] header = { "序号","年份","稽核公司", "稽核财务事项名称 ", "稽核事项负责人 ", "状态标识 "};
		int[] columnWidth = {256*4,256*6,256*40,256*30,256*15,256*10};

		for (int i = 0; i < header.length; i++) {
			cell = row.createCell((short) i);
			cell.setCellValue(header[i]);
		}
		for (int i = 0; i < list.size(); i++) {
			// 所有的数据的长度
			int k = 0;
			row = sheet.createRow((int) i + 1);
			ReportInspectProject value = list.get(i);
			row.createCell((short) k++).setCellValue((i+1)+ ""); //序号
			row.createCell((short) k++).setCellValue(value.getYear() + "");// 年份
			row.createCell((short) k++).setCellValue(value.getCompName() + "");//稽核公司
			row.createCell((short) k++).setCellValue(value.getItemName());//稽核财务事项名称
			row.createCell((short) k++).setCellValue(value.getItemPerson());//稽核事项负责人
			row.createCell((short) k++).setCellValue(value.getStatusName() + "");//状态标识 
		}
		
		//调整列宽度
		for (int i = 0; i < header.length; i++) {
			sheet.setColumnWidth((short)i,columnWidth[i]);
		}

		// 清空response
		response.reset();
		String _name = "稽核财务事项办理维护.xlsx";
		OutputStream toClient = new BufferedOutputStream(
				response.getOutputStream());
		String codedfilename = null;
		String agent = request.getHeader("USER-AGENT");
		if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
				&& -1 != agent.indexOf("Trident")) {// ie

			String name = java.net.URLEncoder.encode(_name, "UTF8");

			codedfilename = name;
		} else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等

			codedfilename = new String(_name.getBytes("UTF-8"), "iso-8859-1");
		}
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ codedfilename);
		workBook.write(toClient);
		toClient.flush();
		toClient.close();

	}
	
	
	@RequestMapping(value="/leaderShExport")
	public void leaderShExport1(ReportInspectProject entity,HttpServletRequest request ,Map<String, Object> map,HttpServletResponse response) throws IOException, ParsePropertyException, InvalidFormatException {

	        List<ReportInspectProject> list=inspectProjectService.getshixiangQry(entity);
	       
	    
			 XSSFWorkbook workBook = new XSSFWorkbook();
//			 首先我们需要创建一个工作簿
			 XSSFCell cell = null;
//			 设置单元格为空
//			
			 XSSFSheet sheet = workBook.createSheet("123");
//			 定义表名为123
			 XSSFRow row = sheet.createRow((int) 0);  
//			 创建行
//			 下面是表头属性

			 String [] header={"年份","稽核类型 ","稽核事项所属单位 ","稽核事项所属核心企业 ",
					 			"财务事项类别 ","稽核财务事项名称","稽核事项负责人","稽核事项开始日期","稽核事项结束日期" 
			 };
		
		for (int i = 0; i < header.length; i++) {
			cell = row.createCell((short) i);
			cell.setCellValue(header[i]);
		}
			 for (int i = 0; i < list.size(); i++) { 
//				 所有的数据的长度
				 	int k=0;
				     row = sheet.createRow((int) i + 1);
				     ReportInspectProject value = list.get(i);
				     row.createCell((short)k++).setCellValue(value.getYear()+"");
//			    	 年份1
				     row.createCell((short)k++).setCellValue(value.getInspectType()+"");
//			    	 稽核类型2
				     row.createCell((short)k++).setCellValue(value.getCompId());
//				     稽核事项所属单位3
				     row.createCell((short)k++).setCellValue(value.getBelongCompId());
//				   稽核事项所属核心企业   4
				 
				     row.createCell((short)k++).setCellValue(value.getItemType()+"");
//				     财务事项类别5
				     row.createCell((short)k++).setCellValue(value.getItemName());
//				     稽核财务事项名称6
				     row.createCell((short)k++).setCellValue(value.getItemPerson());
//				     稽核事项负责人7
				     row.createCell((short)k++).setCellValue(value.getStartTime());
//				    稽核事项开始日期8
				     row.createCell((short)k++).setCellValue(value.getEndTime());
//				     稽核事项结束日期9
				   
		   

			 }
			 		
			
			 
			
				     
				     
				     
				     // 清空response
				        response.reset();
				        String _name = "领导审核公司稽核事项.xlsx";
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
	
	@RequestMapping("addOrModify")
	public String addOrModify(ReportInspectProject entity, Map<String, Object> map, HttpServletRequest request, String op) throws IOException {
		//稽核类型
		List<HhBase> inspectType = baseService.getBases(Base.inspect_project_inspect_type);
		map.put("inspectType", inspectType);
		
		//财务事项类型
		List<HhBase> itemType = baseService.getBases(Base.inspect_project_item_type);
		map.put("itemType", itemType);
		
		HttpSession session=request.getSession();
		map.put("op", op);
		
		if(entity.getId()==null) {
			entity=new ReportInspectProject();
			DateFormat df = new SimpleDateFormat("yyyy");
			Date date=new Date();
			entity.setYear(Integer.parseInt(df.format(date)));
		}
		map.put("entity", entity);
		
		String str=(String) session.getAttribute("gzwsession_financecompany");
		String json=JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype));
		map.put("allCompanyTree", json);
		
		//核心企业
		List<HhOrganInfoTreeRelation> coreComp=systemService.getBusinessCompany(systemService.getAllChildrenFinanceOrganal(str,Base.financetype), Base.financetype);
		map.put("coreCompSelect", coreComp);
		
		//map.put("organalID", organalID);
		return "/report/inspectProject/inspectProjectAddOrModify";
	}
	
	/**
	 * 修改稽核项目基本信息页面获取稽核公司
	 * @param map
	 * @param request
	 * @param entity
	 */
	@ResponseBody
	@RequestMapping(value ="/getReportInspectProjectPlan")
	public List<HhOrganInfoTreeRelation>  getReportInspectProjectPlan(Map<String, Object> map, HttpServletRequest request,ReportInspectProjectPlan entity){
		List<HhOrganInfoTreeRelation> result = new ArrayList<HhOrganInfoTreeRelation>();
		HttpSession session=request.getSession();
		String str = (String) session.getAttribute("gzwsession_financecompany");
		System.out.println(str);
		entity = inspectProjectService.getReportInspectProjectPlan(entity);
		if(entity !=null){
			String compIds = entity.getCompIdStr();
			if(Common.notEmpty(compIds)){
				String[] compIdArray = compIds.split(",");
				for(int i = 0; i < compIdArray.length; i++){
					HhOrganInfoTreeRelation tree = systemService.getTreeOrganInfoNode(Base.financetype, compIdArray[i]);
					if(tree != null && isAuthorityInjh(str,tree)){
						result.add(tree);
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * 判断是否有稽核公司权限
	 * @param str
	 * @param entity
	 * @return
	 */
	private boolean isAuthorityInjh(String str,HhOrganInfoTreeRelation entity){
		boolean result = false;
		if(Common.notEmpty(str)){
			String[] strs = str.split(",");
			for(int i = 0; i < strs.length; i++){
				if(entity.getVcOrganId().contains(strs[i])){
					result = true;
					break;
				}
			}
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value ="/saveOrUpdate", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String saveOrUpdate(ReportInspectProject entity, HttpServletRequest request,
			@RequestParam(value="file1",required=false) MultipartFile file1) throws IOException {
		CommitResult result=new CommitResult();
		if(entity==null)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
		}
		else
		{
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			result= inspectProjectService.saveInspectProject(entity, use,file1);
		}
		
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String delete(Integer id, String lastModifyDate, HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
		ReportInspectProject entity = inspectProjectService.getInspectProjectById(id);
		entity.setLastModifyDate(lastModifyDate);
		CommitResult result=inspectProjectService.deleteInspectProject(entity, use);
		if(result.isResult())
			potalMsgService.updatePortalMsg("report_inspect_project", id.toString());
		String data="";
		data=JSONArray.toJSONString(result); 
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/updatestatus", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String updateStatus(Integer id, HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
		ReportInspectProject entity = inspectProjectService.getInspectProjectById(id);
		CommitResult result=inspectProjectService.updateStatus(entity, use);
		if(result.isResult())
			potalMsgService.updatePortalMsg("report_inspect_project", id.toString());
		String data="";
		data=JSONArray.toJSONString(result); 
		return data;
	}
	
	/**
	 * 弹出查看/上报画面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String _view(ReportInspectProject entity,String type,String stype, HttpServletRequest request,
			Map<String, Object> map) throws IOException {
		map.put("type", type);
		map.put("stype", stype);
		map.put("entity", entity);
		
		String title="查看稽核财务事项";
		if(type.equals("basic")){
		}	
		else if(type.equals("manage")){
		}	
		else{
			if(stype!=null && stype.equals("leader"))
				title="领导指示";
		}	
		map.put("title", title);
		
		List<ReportInspectProjectProblem> problemList=inspectProjectService.getInspectProjectProblem(entity.getId());
		map.put("problemList", problemList);
		
		List<ReportInspectProjectOrder> orderList=inspectProjectService.getInspectProjectOrder(entity.getId());
		map.put("orderList", orderList);
		
		return "/report/inspectProject/inspectProjectView";
	}
	
	@RequestMapping("/manageedit")
	public String manageEdit(ReportInspectProject entity, HttpServletRequest request,
			Map<String, Object> map) throws IOException {
		
		map.put("entity", entity);
		
		List<ReportInspectProjectProblem> problemList=inspectProjectService.getInspectProjectProblem(entity.getId());
		map.put("problemList", problemList);
		
		List<ReportInspectProjectOrder> orderList=inspectProjectService.getInspectProjectOrder(entity.getId());
		map.put("orderList", orderList);
		
		String mapurl = request.getContextPath() + "/inspectproject";
		map.put("mapurl", mapurl);
		
		return "/report/inspectProject/inspectProjectManageEdit";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("problemList")
	public String problemList(ReportInspectProjectProblem entity, HttpServletRequest request, Map<String, Object> map){
		
		setInspectTypes(map);
		
		HttpSession session=request.getSession();
		
		// 当前页码
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		// 当前页的信息取得
		String str=(String) session.getAttribute("gzwsession_financecompany");
		MsgPage msgPage = inspectProjectService.getInspectProjectProblemListView(
				entity, str, false, pageNum, Base.pagesize);
		setInspectTypeNames(msgPage.getList());
		
		map.put("msgPage", msgPage);
		map.put("searchurl", "/shanghai-gzw/inspectproject/problemList");
		map.put("entity", entity);
		String mapurl = request.getContextPath() + "/inspectproject";
		map.put("mapurl", mapurl);
		
		String json=JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype));
		map.put("allCompanyTree", json);
		
		return "/report/inspectProject/inspectProjectProblemList";
	}
	
	private void setInspectTypes(Map<String, Object> map){
		List<HhBase> inspectType = baseService.getBases(Base.inspect_project_inspect_type);
		map.put("inspectType", inspectType);
	}
	
	private void setInspectTypeNames(List<ReportInspectProjectProblem> data){
		List<HhBase> hhBases = baseService.getBases(Base.inspect_project_inspect_type);
		Integer preInspectType = -1;
		String preInspectTypeName = "";
		for(ReportInspectProjectProblem o: data){
			if(preInspectType.intValue() == o.getInspectType().intValue()){
				o.setInspectTypeName(preInspectTypeName);
				continue;
			}
			
			o.setInspectTypeName(getInspectTypeName(o.getInspectType(), hhBases));
			preInspectType = o.getInspectType();
			preInspectTypeName = o.getInspectTypeName();
		}
	}
	
	private String getInspectTypeName(Integer inspectType, List<HhBase> hhBases){
		for(HhBase hhBase : hhBases){
			if(hhBase.getId().intValue() == inspectType.intValue()){
				return hhBase.getDescription();
			}
		}
		return "";
	}
	
	@RequestMapping("addOrModifyProblem")
	public String addOrModifyProblem(ReportInspectProjectProblem entity, Map<String, Object> map, HttpServletRequest request, String op) throws IOException {
		
		map.put("entity", entity);
		map.put("op", op);
		
		//map.put("organalID", organalID);
		return "/report/inspectProject/inspectProjectProblemAddOrModify";
	}
	
	@ResponseBody
	@RequestMapping(value ="/saveOrUpdateProblem", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String saveOrUpdateProblem(ReportInspectProjectProblem entity, HttpServletRequest request) throws IOException {
		CommitResult result=new CommitResult();
		if(entity==null)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
		}
		else
		{
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			result= inspectProjectService.saveInspectProjectProblem(entity, use);
		}
		
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@RequestMapping("/problemview")
	public String problemview(@RequestParam Integer problemId, Map<String, Object> map) throws IOException {
		
		ReportInspectProjectProblem t = inspectProjectService.getReportInspectProjectProblemById(problemId);
		map.put("entity", t);
		return "/report/inspectProject/inspectProjectProblemView";
	}
	
	@ResponseBody
	@RequestMapping(value ="/deleteproblem", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String deleteProblem(ReportInspectProjectProblem entity, HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
		
		CommitResult result=inspectProjectService.deleteInspectProjectProblem(entity, use);
		if(result.isResult())
			potalMsgService.updatePortalMsg("report_inspect_project", entity.getId().toString());
		String data="";
		data=JSONArray.toJSONString(result); 
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/saveOrUpdateOrder", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String saveOrUpdateOrder(ReportInspectProjectOrder entity, HttpServletRequest request) throws IOException {
		CommitResult result=new CommitResult();
		if(entity==null)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
		}
		else
		{
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			result= inspectProjectService.saveInspectProjectOrder(entity, use);
		}
		
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@RequestMapping("/orderview")
	public String orderView(ReportInspectProjectOrder entity, HttpServletRequest request,
			Map<String, Object> map) throws IOException {
		
		map.put("entity", entity);
		return "/report/inspectProject/inspectProjectOrderView";
	}
	
	@RequestMapping("/orderreport")
	public String orderReport(ReportInspectProjectOrder entity, HttpServletRequest request,
			Map<String, Object> map) throws IOException {
		
		map.put("entity", entity);
		return "/report/inspectProject/inspectProjectOrderReport";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("problemTimeOutList")
	public String problemTimeoutList(ReportInspectProjectProblem entity, HttpServletRequest request, Map<String, Object> map){
		
		setInspectTypes(map);
		
		HttpSession session=request.getSession();
				
		// 当前页码
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		String str=(String) session.getAttribute("gzwsession_financecompany");
		// 当前页的信息取得
		MsgPage msgPage = inspectProjectService.getInspectProjectProblemListView(entity, str, true, pageNum, Base.pagesize);
		
		setInspectTypeNames(msgPage.getList());
		setProblemTimeout(msgPage.getList());
				
		map.put("msgPage", msgPage);
		map.put("searchurl", "/shanghai-gzw/inspectproject/problemTimeOutList");
		map.put("entity", entity);
		String mapurl = request.getContextPath() + "/inspectproject";
		map.put("mapurl", mapurl);
		
		String json=JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype));
		map.put("allCompanyTree", json);
				
		return "/report/inspectProject/inspectProjectProblemTimeoutList";
	}
	
	private void setProblemTimeout(List<ReportInspectProjectProblem> data){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		long timeoutTime = System.currentTimeMillis();
		for(ReportInspectProjectProblem o: data){
			if(o.getIsFinish() == 1 || StringUtils.isBlank(o.getChangeLastTime())){
				o.setIsChangeTimeout(0);
			}else{
				o.setIsChangeTimeout(parseTime(dateFormat, o.getChangeLastTime()) > timeoutTime? 0 : 1);
			}
		}
	}
	
	private long parseTime(DateFormat dateFormat, String v){
		try{
			return dateFormat.parse(v).getTime();
		}catch (ParseException e) {
			return System.currentTimeMillis();
		}
	}
	
	@RequestMapping("problemStatisticList")
	public String problemStatisticList(ReportInspectProject entity, @RequestParam(value="pageNums", defaultValue="1") Integer pageNum, 
			HttpServletRequest request, Map<String, Object> map){
		
        setInspectTypes(map);
		
		// 路径
		String mapurl = request.getContextPath() + "/inspectproject";
		map.put("mapurl", mapurl);
		
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		MsgPage msgPage = inspectProjectService.getInspectProjectProblemStatisticListView(
				entity.getYear(), entity.getInspectType(), entity.getCompId(), str, pageNum, Base.pagesize);
		
		List<HhBase> itemTypes = baseService.getBases(Base.inspect_project_item_type);
		map.put("itemType", itemTypes);
		map.put("msgPage", formatProblemStatisticMsgPage(itemTypes, baseService.getBases(Base.inspect_project_inspect_type), msgPage));
		map.put("searchurl", "/shanghai-gzw/inspectproject/problemStatisticList");
		map.put("entity", entity);


		
		String json=JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype));
		map.put("allCompanyTree", json);
					
		return "/report/inspectProject/inspectProjectProblemStatisticList";
	}
	
	/**
	 * 年度稽核公司发现问题统计导出功能 
	 * @param entity
	 * @param request
	 * @param map
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("ndjhgsfxwttjExprot")
	public void ndjhgsfxwttjExprot(ReportInspectProject entity,HttpServletRequest request, Map<String, Object> map,HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		List<HhBase> itemTypes = baseService.getBases(Base.inspect_project_item_type);
		List<Object[]> list = inspectProjectService.getInspectProjectProblemStatisticListView(
				entity.getYear(), entity.getInspectType(), entity.getCompId(), str);
		List<List<Object>> lists = formatProblemStatisticMsgPage(itemTypes, baseService.getBases(Base.inspect_project_inspect_type), list);
		XSSFWorkbook workBook = new XSSFWorkbook();
		// 首先我们需要创建一个工作簿
		XSSFCell cell = null;
		// 设置单元格为空
		//
		XSSFSheet sheet = workBook.createSheet();
		// 定义表名为123
		XSSFRow row = sheet.createRow((int) 0);
		
		// 创建头
		row.createCell(0).setCellValue("序号");
		row.createCell(1).setCellValue("年份");
		row.createCell(2).setCellValue("稽核类型");
		row.createCell(3).setCellValue("稽核公司名称");
		for(int i = 0; i < itemTypes.size(); i++){
			row.createCell(4+i).setCellValue(itemTypes.get(i).getDescription() + "发现问题个数(个)");
		}
		row.createCell(4+itemTypes.size()).setCellValue("小计");
		
		for (int i = 0; i < lists.size(); i++) {
			//所有的数据的长度
			int k = 0;
			row = sheet.createRow(i + 1);
			row.createCell((short) k++).setCellValue((i+1)+ ""); //序号
			List<Object> dd= lists.get(i);
			for(int j = 0; j < dd.size(); j++){
				Object[] aa = (Object[])dd.get(j);
				row.createCell((short) k++).setCellValue(aa[0] + "");
			}
			
		}

		// 清空response
		response.reset();
		String _name = "年度稽核公司发现问题统计.xlsx";
		OutputStream toClient = new BufferedOutputStream(
				response.getOutputStream());
		String codedfilename = null;
		String agent = request.getHeader("USER-AGENT");
		if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
				&& -1 != agent.indexOf("Trident")) {// ie

			String name = java.net.URLEncoder.encode(_name, "UTF8");

			codedfilename = name;
		} else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等

			codedfilename = new String(_name.getBytes("UTF-8"), "iso-8859-1");
		}
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ codedfilename);
		workBook.write(toClient);
		toClient.flush();
		toClient.close();
	}
	
	
	
	private MsgPage formatProblemStatisticMsgPage(List<HhBase> itemTypes, List<HhBase> inspectTypes, MsgPage msgPage){
		final int colSize = 4 + itemTypes.size();
		List<List<Object>> rows = new ArrayList<List<Object>>(msgPage.getList().size());
		for(Object o: msgPage.getList()){
			Object[] array = (Object[])o;
			List<Object> cols = new ArrayList<Object>(colSize);
			cols.add(new Object[]{array[0]});
			cols.add(new Object[]{getInspectTypeName((Integer)array[1], inspectTypes)});
			cols.add(new Object[]{array[3]});
			fillProblemStatisticNumber(cols, itemTypes, (Integer)array[1], (String)array[2], (String)array[4], (String)array[5]);
			rows.add(cols);
		}
		msgPage.setList(rows);
		return msgPage;
	}
	
	/**
	 * 年度稽核公司发现问题统计,中间处理数据的方法
	 * @param itemTypes
	 * @param inspectTypes
	 * @param list
	 * @return
	 */
	private List<List<Object>> formatProblemStatisticMsgPage(List<HhBase> itemTypes, List<HhBase> inspectTypes, List<Object[]> list){
		final int colSize = 4 + itemTypes.size();
		List<List<Object>> rows = new ArrayList<List<Object>>(list.size());
		for(Object o: list){
			Object[] array = (Object[])o;
			List<Object> cols = new ArrayList<Object>(colSize);
			cols.add(new Object[]{array[0]});
			cols.add(new Object[]{getInspectTypeName((Integer)array[1], inspectTypes)});
			cols.add(new Object[]{array[3]});
			fillProblemStatisticNumber(cols, itemTypes, (Integer)array[1], (String)array[2], (String)array[4], (String)array[5]);
			rows.add(cols);
		}
		return rows;
	}
	
	private void fillProblemStatisticNumber(List<Object> cols, List<HhBase> itemTypes, 
			Integer inspectType, String compId, String itemTypesStr, String numbersStr){
		
		int[] types = convertInt(itemTypesStr);
		int[] typeNumbers = convertInt(numbersStr);
		int sum = 0;
		for(int i = 0; i < itemTypes.size(); i++){
			int itemType = itemTypes.get(i).getId();
			int n = getStatisticNumber(itemType, types, typeNumbers);
			if(n == 0){
				cols.add(new Object[]{n});
			}else{
				Integer year = (Integer)((Object[])cols.get(0))[0];
				cols.add(new Object[]{n, buildProblemStatisticViewURLParams(year, inspectType, compId, itemType)});
			}
			sum = sum + n;
		}
		cols.add(new Object[]{sum});
	}
	
	private int[] convertInt(String v){
		String[] array = StringUtils.split(v, ",");
		int[] vs = new int[array.length];
		for(int i = 0; i < array.length; i++){
			vs[i] = Integer.valueOf(array[i]);
		}
		return vs;
	}
	
	private int getStatisticNumber(int itemType, int[] types, int[] typeNumbers){
		for(int i = 0; i < types.length; i++){
			if(itemType == types[i]){
				return typeNumbers[i];
			}
		}
		return 0;
	}
	
	private String buildProblemStatisticViewURLParams(Integer year, Integer inspectType, String compId, Integer itemType){
		return String.format("year=%d&inspectType=%d&compId=%s&itemType=%d", year, inspectType, compId, itemType);
	}
	
	@RequestMapping("problemStatisticView")
	public String problemStatisticView(
			@RequestParam Integer year, @RequestParam Integer inspectType, 
			@RequestParam String compId,  @RequestParam Integer itemType,
			HttpServletRequest request, Map<String, Object> map)throws IOException{
		
		ReportInspectProject entity = inspectProjectService.getInspectProjectByStatistic(year, inspectType, compId, itemType);
		map.put("entity", entity);
		
		List<ReportInspectProjectProblem> problemList=inspectProjectService.getInspectProjectProblem(entity.getId());
		map.put("problemList", problemList);
		
		return "/report/inspectProject/inspectProjectProblemStatisticView";
	}
	
	@RequestMapping("changeManageList")
	public String changeManageList(ReportInspectProject entity, HttpServletRequest request, Map<String, Object> map){
		
		setInspectTypes(map);
		
		// 路径
		String mapurl = request.getContextPath() + "/inspectproject";
		map.put("mapurl", mapurl);
				
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		entity.setParentorg(str);	
		
		// 当前页码
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		
		// 当前页的信息取得
		MsgPage msgPage = inspectProjectService.getInspectProjectChangeListView(entity, pageNum, Base.pagesize);
		map.put("msgPage", msgPage);
		map.put("searchurl", "/shanghai-gzw/inspectproject/changeManageList");
		map.put("entity", entity);
				
		String json=JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype));
		map.put("allCompanyTree", json);
				
		return "/report/inspectProject/inspectProjectChangeManageList";
	}


	/**
	 * 年度问题整改清单导出功能
	 * @param entity
	 * @param request
	 * @param map
	 * @param response
	 * @throws IOException
	 * @throws ParsePropertyException
	 * @throws InvalidFormatException
	 */
	@RequestMapping(value = "/gsFindWtExport")
	public void gsFindWtExport1(ReportInspectProjectProblem entity,
			HttpServletRequest request, Map<String, Object> map,
			HttpServletResponse response) throws IOException,
			ParsePropertyException, InvalidFormatException {
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		List<ReportInspectProjectProblem> list = inspectProjectService
				.getshixiangQrypro(entity,str,true);
		setInspectTypeNames(list);
		setProblemTimeout(list);
		XSSFWorkbook workBook = new XSSFWorkbook();// 首先我们需要创建一个工作簿
		XSSFCell cell = null;// 设置单元格为空
		XSSFSheet sheet = workBook.createSheet();// 定义表名为123
		XSSFRow row = sheet.createRow((int) 0);// 创建行
		// 下面是表头属性
		String[] header = { "序号", "年份 ", "稽核类型", "公司名称", "稽核财务事项名称", "存在问题",
				"整改是否完成", "整改是否超时","建议整改措施" };
		
		for (int i = 0; i < header.length; i++) {
			cell = row.createCell((short) i);
			cell.setCellValue(header[i]);
		}
		for (int i = 0; i < list.size(); i++) {
			// 所有的数据的长度
			int k = 0;
			row = sheet.createRow((int) i + 1);
			ReportInspectProjectProblem value = list.get(i);
			row.createCell((short) k++).setCellValue((i+1)+"");//序号
			row.createCell((short) k++).setCellValue(value.getYear()+"");//年份
			row.createCell((short) k++).setCellValue(value.getInspectTypeName()+"");//稽核类型
			row.createCell((short) k++).setCellValue(value.getCompName()+"");//公司名称
			row.createCell((short) k++).setCellValue(value.getItemName()+"");//稽核财务事项名称
			row.createCell((short) k++).setCellValue(value.getProblem()+"");//存在问题
			row.createCell((short) k++).setCellValue((value.getIsFinish()!=null && value.getIsFinish()== 1)?"是":"否");//整改是否完成item.isFinish == 0 ? "否" :"是"
			row.createCell((short) k++).setCellValue((value.getIsChangeTimeout()!=null && value.getIsChangeTimeout() == 1)?"是":"否");//整改是否超时 item.isChangeTimeout==0? "否" :"是"
			row.createCell((short) k++).setCellValue(value.getChangeContent()+"");//建议整改措施
		}

		// 清空response
		response.reset();
		String _name = "年度稽核公司发现问题清单.xlsx";
		OutputStream toClient = new BufferedOutputStream(
				response.getOutputStream());
		String codedfilename = null;
		String agent = request.getHeader("USER-AGENT");
		if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
				&& -1 != agent.indexOf("Trident")) {// ie

			String name = java.net.URLEncoder.encode(_name, "UTF8");

			codedfilename = name;
		} else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等

			codedfilename = new String(_name.getBytes("UTF-8"), "iso-8859-1");
		}
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ codedfilename);
		workBook.write(toClient);
		toClient.flush();
		toClient.close();

	}
	
	@RequestMapping(value="/findWtExport")
	public void findWtExport1(ReportInspectProjectProblem entity,HttpServletRequest request ,Map<String, Object> map,HttpServletResponse response) throws IOException, ParsePropertyException, InvalidFormatException {
	        HttpSession session=request.getSession();
			String str=(String) session.getAttribute("gzwsession_financecompany");
			List<ReportInspectProjectProblem> list = inspectProjectService
					.getshixiangQrypro(entity,str,false);
			setInspectTypeNames(list);
			 XSSFWorkbook workBook = new XSSFWorkbook();
//			 首先我们需要创建一个工作簿
			 XSSFCell cell = null;
//			 设置单元格为空
//			
			 XSSFSheet sheet = workBook.createSheet("123");
//			 定义表名为123
			 XSSFRow row = sheet.createRow((int) 0);  
//			 创建行
//			 下面是表头属性
			
			 String [] header={"存在问题","是否问责 ","问责人员","是否整改",
					 			"整改跟踪人","整改对接人","整改时限","稽核建议"
			 };
		
		for (int i = 0; i < header.length; i++) {
			cell = row.createCell((short) i);
			cell.setCellValue(header[i]);
		}
			 for (int i = 0; i < list.size(); i++) { 
//				 所有的数据的长度
				 	int k=0;
				     row = sheet.createRow((int) i + 1);
				     ReportInspectProjectProblem value = list.get(i);
				     row.createCell((short)k++).setCellValue(value.getProblem());
//			    	 存在问题1
				     row.createCell((short)k++).setCellValue(value.getIsAccountability()+"");
//			    	 是否问责2
			     row.createCell((short)k++).setCellValue(value.getAccountabilityPerson());
//					    问责人员3			 
				     row.createCell((short)k++).setCellValue(value.getIsChange()+"");
//					   是否整改 4
				     row.createCell((short)k++).setCellValue(value.getChangeFollowPerson());
//				     整改跟踪人5
				     row.createCell((short)k++).setCellValue(value.getChangeAbutmentPerson());
//				     整改对接人6
				     row.createCell((short)k++).setCellValue(value.getChangeLastTime());
//				     整改时限7
				     row.createCell((short)k++).setCellValue(value.getChangeContent());
//				     稽核建议8

		   

			 }
			 		
			
			 
			
				     
				     
				     
				     // 清空response
				        response.reset();
				        String _name = "年度稽核整改问题清单.xlsx";
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
	@RequestMapping("/changeManageView")
	public String changeManageEdit(ReportInspectProject entity,
			@RequestParam(defaultValue="false") Boolean isEdit, HttpServletRequest request,
			Map<String, Object> map) throws IOException {
		
		manageEdit(entity, request, map);
		return isEdit ? 
				"/report/inspectProject/inspectProjectChangeManageEdit" :
					"/report/inspectProject/inspectProjectChangeManageView";
	}
	
	@RequestMapping(value = "saveChangeManageEdit", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public CommitResult saveChangeManageEdit(@RequestParam Integer id, HttpServletRequest request) {
		CommitResult result = new  CommitResult();
		ReportInspectProject t = inspectProjectService.getInspectProjectById(id);
		if(t.getStatus() == 80044){
			result.setResult(true);
			return result;
		}
		
		if(t.getStatus() != 80043){
			result.setResult(false);
			result.setExceptionStr("项目未办结确认");
			return result;
		}
		
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		t.setStatus(80044);
		return inspectProjectService.saveInspectProject(t, use ,null);
	}
	
	@RequestMapping(value="changeManageProblemEdit")
	public String changeManageProblemEdit(ReportInspectProjectProblem entity, 
			@RequestParam(defaultValue="false")Boolean isEdit,
			HttpServletRequest request, Map<String, Object> map){
		
		map.put("entity", entity);
		return isEdit ? 
				"/report/inspectProject/inspectProjectChangeManageProblemEdit": 
					"/report/inspectProject/inspectProjectChangeManageProblemView";
	}
	
	@RequestMapping(value="updateChangeManageProblem", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public CommitResult updateChangeManageProblem(ReportInspectProjectProblem entity, 
			@RequestParam(value="file1",required=false) MultipartFile file1,
			HttpServletRequest request){
		
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		
		return inspectProjectService.updateChangeManageProblem(entity, use, file1);
	}
	
	@RequestMapping(value = "saveChangeManageArchive", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public CommitResult saveChangeManageArchive(@RequestParam Integer id, HttpServletRequest request){
		CommitResult result = new  CommitResult();
		ReportInspectProject t = inspectProjectService.getInspectProjectById(id);
		if(t.getStatus() != 80043 && t.getStatus() != 80044){
			result.setResult(false);
			result.setExceptionStr("项目未办结确认");
			return result;
		}
		
		HttpSession session=request.getSession();
		HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
		t.setStatus(80045);
		return inspectProjectService.saveInspectProject(t, user ,null);
	}
	
	@RequestMapping("applyCheckList")
	public String applyCheckList(ReportInspectProject entity, HttpServletRequest request, Map<String, Object> map){
		
        setInspectTypes(map);
		
		// 路径
		String mapurl = request.getContextPath() + "/inspectproject";
		map.put("mapurl", mapurl);
				
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		entity.setParentorg(str);
				
		// 当前页码
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		// 当前页的信息取得
		if(entity.getStatus() == null){
			entity.setStatus(80041);
		}
		MsgPage msgPage = inspectProjectService.getInspectProjectApplyCheckListView(entity, pageNum, Base.pagesize);
		map.put("msgPage", msgPage);
		map.put("searchurl", "/shanghai-gzw/inspectproject/applyCheckList");
		map.put("entity", entity);
				
		
		String json=JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype));
		map.put("allCompanyTree", json);
		
		return "/report/inspectProject/inspectProjectApplyCheckList";
	}
	
	@RequestMapping("applyCheckView")
	public String applyCheckView(@RequestParam Integer id, 
			@RequestParam(required=false, defaultValue="false")Boolean isEdit,
			HttpServletRequest request, Map<String, Object> map){
		
			map.put("entity", inspectProjectService.getInspectProjectById(id));
			
			List<ReportInspectProjectProblem> problemList=inspectProjectService.getInspectProjectProblem(id);
			map.put("problemList", problemList);
			
			List<ReportInspectProjectOrder> orderList=inspectProjectService.getInspectProjectOrder(id);
			map.put("orderList", orderList);
			
			List<SysExamine> checkList = examineService.getListExamine(id, Base.inspect_project);
			map.put("checkList", checkList);
			
			return isEdit? 
					"/report/inspectProject/inspectProjectApplyCheckEdit" :
						"/report/inspectProject/inspectProjectApplyCheckView";
	}
	
	
	@ResponseBody
	@RequestMapping(value ="saveInspectProjectCheck", method = RequestMethod.POST,  produces = "application/json;charset=UTF-8")
	public CommitResult saveInspectProjectCheck(
			@RequestParam Integer inspectProjectId, @RequestParam Integer isPass, 
			@RequestParam String content, HttpServletRequest request) {
		
		HttpSession session=request.getSession();
		HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
		return inspectProjectService.saveInspectProjectCheck(inspectProjectId, isPass, content, user);
	}
	
	@RequestMapping("leaderCheckList")
	public String leaderCheckList(ReportInspectProject entity, HttpServletRequest request, Map<String, Object> map){
		
		map.put("entity", entity);
        
		setInspectTypes(map);
		
		// 路径
		String mapurl = request.getContextPath() + "/inspectproject";
		map.put("mapurl", mapurl);
				
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		entity.setParentorg(str);		
		// 当前页码
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		// 当前页的信息取得
		entity.setStatus(5);
		MsgPage msgPage = inspectProjectService.getInspectProjectLeaderCheckListView(
				entity.getYear(), entity.getInspectType(), entity.getCompId(), entity.getParentorg(), pageNum,  Base.pagesize);
		
		List<HhBase> inspectTypes = baseService.getBases(Base.inspect_project_inspect_type);
		for(Object o: msgPage.getList()){
			Object[] array = (Object[])o;
			array[2] = getInspectTypeName((Integer)array[1], inspectTypes);
		}
		
		map.put("msgPage", msgPage);
		map.put("searchurl", "/shanghai-gzw/inspectproject/leaderCheckList");
		map.put("entityview", entity);
				
		
		String json=JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype));
		map.put("allCompanyTree", json);
		
		return "/report/inspectProject/inspectProjectLeaderCheckList";
	}
	
	/**
	 * @param year
	 * @param inspectType
	 * @param compId
	 * @param compName
	 * @param isEdit
	 * @param request
	 * @param map
	 * @return
	 * 
	 * 	查询相关信息的时候，没有设置数据权限，导致查询不到，修复bug.
	 * 	 		author zl  2018/8/13
	 */
	@RequestMapping("leaderCheckView")
	public String leaderCheckView(
			@RequestParam Integer year, @RequestParam Integer inspectType, 
			@RequestParam String compId, @RequestParam String compName, 
			@RequestParam(defaultValue="false") Boolean isEdit,
			HttpServletRequest request, Map<String, Object> map,
			HttpServletResponse response){

		map.put("compName", compName);
		// 路径
		String mapurl = request.getContextPath() + "/inspectproject";
		map.put("mapurl", mapurl);
		
		// 当前页的信息取得
		ReportInspectProject entity = new  ReportInspectProject();
		entity.setYear(year);
		entity.setInspectType(inspectType);
		entity.setCompId(compId);
		//设置数据权限
		String str = String.valueOf(request.getSession().getAttribute("gzwsession_financecompany"));
		entity.setParentorg(str);		
		MsgPage msgPage = inspectProjectService.getInspectProjectApplyCheckListView(entity, 1, 500);
		map.put("msgPage", msgPage);
		map.put("searchurl", "/shanghai-gzw/inspectproject/leaderCheckView");
		map.put("entityview", entity);
		
		return isEdit ? 
				"/report/inspectProject/inspectProjectLeaderCheckEdit": 
					"/report/inspectProject/inspectProjectLeaderCheckView";
	}
	
	@RequestMapping(value="saveInspectProjectLeaderCheck", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public CommitResult saveInspectProjectLeaderCheck(
			@RequestParam(value="ids[]") List<Integer> ids, 
			@RequestParam(value="isPasses[]") List<Integer> isPasses,
			@RequestParam String content,HttpServletRequest request) {
		
		HttpSession session=request.getSession();
		HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
		return inspectProjectService.saveInspectProjectLeaderCheck(
				ids, isPasses, content, user);
	}
	
	@RequestMapping(value = "/leveltree")
	public String leveltree(ReportInspectProjectPlan entity,Integer type,Map<String ,Object> map,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException {
			map.put("type", type);
			
			//稽核类型
			List<HhBase> inspectType = baseService.getBases(Base.inspect_project_inspect_type);
			map.put("inspectType", inspectType);
			
		    List<HhBase> statustype= baseService.getBases(Base.report_organal);
	    	map.put("statustype",statustype);
			SimpleDateFormat sf =new SimpleDateFormat("yyyy");
	    	if(entity.getYear()==null){
		    	Calendar c=Calendar.getInstance();
		    	//Integer year= c.getTime().getYear();
		    	entity.setYear(c.get(Calendar.YEAR));
	    	}
	    	
			map.put("BimaCompany","");
			map.put("entity",entity);
	    	
			return "/report/inspectProject/companyLevel";
	}
	
	@ResponseBody
	@RequestMapping(value ="/saveOrUpdatePlan", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String saveOrUpdateOrder(ReportInspectProjectPlan entity, HttpServletRequest request) throws IOException {
		CommitResult result=new CommitResult();
		
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		result= inspectProjectService.saveInspectProjectPlan(entity, use);
		
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	/**
	 * 生成公司组织架构树
	 * 
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/_getleveltree", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public void basicInfo_getleveltree(ReportInspectProjectPlan entity,String userId, Integer type, String usersEmployeeId,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		
		HhUsers user = (HhUsers) request.getSession().getAttribute(
				"gzwsession_users");
		String str=(String) request.getSession().getAttribute("gzwsession_financecompany");
		
		CompanyLevelTree leveltree = createTree(type,entity,str);
		String json = JSONArray.toJSONString(leveltree);
		response.getWriter().write(json);
	}

	private CompanyLevelTree  createTree(Integer type,ReportInspectProjectPlan entity,String str){
		
		CompanyLevelTree leveltree = treeService.getTreeByStr(type, str);
		/*entity.setYear(2018);
		entity.setInspectType(80010);*/
		
		entity=inspectProjectService.getReportInspectProjectPlan(entity);
		//String compIds="d4985136e97d11e7968906a2160000ae";
		if(entity!=null)
			treeSelected(leveltree,entity.getCompIdStr());
		
 		return leveltree;
	}
	
	private void treeSelected(CompanyLevelTree tree ,String compIds){
		if(compIds.contains(tree.id.toString())){
			tree.state.setOpened(false);
			tree.state.setSelected(true);
		}
		if(tree.children!=null && tree.children.size()>0)
			for (CompanyLevelTree child : tree.children) {
				treeSelected(child,compIds);
			}
		
	}
	
	/**
	 * 进入选择人员页面
	 * @param map
	 * @param request
	 * @param vcEmployeeId
	 * @param type
	 * @param usersEmployeeId
	 * @return
	 */
	@RequestMapping("_selectpeople")
	public String _selectPeople(Map<String, Object> map, HttpServletRequest request,String eleId){
		CompanyTree leveltree = selectUserService.getHRTree();
		leveltree.setIcon("active");
		String shtml = getTreeHTML(leveltree);
		map.put("shtml", shtml);
		map.put("eleId", eleId);
		return "/report/inspectProject/usersSelectOne";
	}
	
	private String getTreeHTML(CompanyTree cTree){
		StringBuffer buffer = new StringBuffer();
		List<CompanyTree> sonList = cTree.getChildren();
		buffer.append("<li class=\"line-list\">");
			if(sonList.size()>0){
				if("active".equals(cTree.getIcon())){
					buffer.append("<span class=\"departmentLabel\" treeId="+cTree.getId()+"\"><i class=\"iconfont icon-tree-open-2\"></i><a href='#n' onclick = \"itemClick('"+cTree.getId()+"')\">"+cTree.getText()+"</a></span>");
				}else{
					buffer.append("<span class=\"departmentLabel\" treeId="+cTree.getId()+"\"><i class=\"iconfont icon-tree-close-2\"></i><a href='#n' onclick = \"itemClick('"+cTree.getId()+"')\">"+cTree.getText()+"</a></span>");
				}
				buffer.append("<ul class=\"level-2 "+cTree.getIcon()+"\">");
				for(int i=0;i<sonList.size();i++){
					CompanyTree son = sonList.get(i);
					buffer.append(getTreeHTML(son));
				}
				buffer.append("</ul>");
			}else{
				buffer.append("<label class=\"checkboxList\" treeId="+cTree.getId()+"><a href='#n' onclick = \"itemClick('"+cTree.getId()+"')\">"+cTree.getText()+"</a></label>");
			}
		buffer.append("</li>");
		return buffer.toString();
	}
}
