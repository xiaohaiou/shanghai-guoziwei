package com.softline.controller.report;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFCell;
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
import com.softline.entity.HhBase;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.HhUsers;
import com.softline.entity.HrPersonOrganization;
import com.softline.entity.HrPersongroup;
import com.softline.entity.HrPersonitem;
import com.softline.entity.ReportPersonlloaninfoNew;
import com.softline.entity.SysExamine;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IHrPersongroupService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/hrPersongroup")
/**
 * 
 * @author tch
 *
 */
public class HrPersongroupController {

	@Resource(name = "hrPersongroupService")
	private IHrPersongroupService hrPersongroupService;
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "examineService")
	private IExamineService examineService;	
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	
	
	@ModelAttribute
	public void getCommodity(@RequestParam(value = "id", required = false) Integer id,Map<String, Object> map, HttpServletRequest request) {
		if (id != null) {
			HrPersongroup entityview=hrPersongroupService.getHrPersongroup(id);
			map.put("hrPersongroup", entityview);
		}
	}
	
	
	//审核的列表页面
	@RequestMapping("/examinelist")
	public String _examinelist(HrPersongroup entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/hrPersongroup";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str, Base.financetype)) );
        entity.setGetOperateType(Base.fun_examine);
        Integer pageNum = Integer.valueOf(curPageNum);
        //数据权限
      
        entity.setParentorg(str);
        MsgPage msgPage=hrPersongroupService.getHrPersongroupListView(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/hrPersongroup/examinelist");
	    map.put("entityview", entity);
		addData(map);
		return "/report/hrPersongroup/hrPersongroupExamineList";
	}
	
	
	//维护的列表页面
	@RequestMapping("/list")
	public String _list(HrPersongroup entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/hrPersongroup";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str, Base.financetype)) );
        Integer pageNum = Integer.valueOf(curPageNum);
        //数据权限
        entity.setParentorg(str);
        MsgPage msgPage=hrPersongroupService.getHrPersongroupListView(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/hrPersongroup/list");
	    map.put("entityview", entity);
		addData(map);
		return "/report/hrPersongroup/hrPersongroupList";
	}
	
	//跳转新增修改页面
	@RequestMapping("/newmodify")
	public String _newmodify(HrPersongroup entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		map.put("op", op);
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str, Base.financetype)) );
		
		HrPersongroup entityview;
		if(entity.getId()==null)
		{	
			entityview=new HrPersongroup();
			DateFormat df = new SimpleDateFormat("yyyy");
			Date date=new Date();
			entityview.setYear(Integer.parseInt (df.format(date)));
		}
		else
		{	
			entityview=hrPersongroupService.getHrPersongroup(entity.getId());
			List<SysExamine> a=new ArrayList<SysExamine>();
			a= examineService.getListExamine(entityview.getId(), Base.examkind_hr);
			map.put("entityExamineviewlist", a);
		}
		map.put("entityview", entityview);	
		addData(map);
		
		return "/report/hrPersongroup/hrPersongroupNewModify";
	}
	
	//跳转新增修改页面
	@RequestMapping("/view")
	public String _view(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		
		HrPersongroup entityview;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entityview=new HrPersongroup();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
		{
			entityview=hrPersongroupService.getHrPersongroup(id);
		    a= examineService.getListExamine(entityview.getId(), Base.examkind_hr);
		}	
		map.put("entityview", entityview);
		map.put("entityExamineviewlist", a);
		return "/report/hrPersongroup/hrPersongroupView";
	}
	
	//编辑新增页面的保存
	@ResponseBody
	@RequestMapping(value ="/save", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _save(HrPersongroup hrPersongroup,MultipartFile itemFile,MultipartFile organizationFile,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		CommitResult result=new CommitResult();
		if(hrPersongroup==null)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
		}
		else
		{
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			hrPersongroup.setStatus(Base.examstatus_1);
			result= hrPersongroupService.saveHrPersongroup(hrPersongroup, use,itemFile,organizationFile);
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	//编辑新增页面的上报
	@ResponseBody
	@RequestMapping(value ="/saveandreport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _saveandreport(HrPersongroup entity,MultipartFile itemFile,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		CommitResult result=new CommitResult();
		if(entity!=null)
		{
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entity.setReportDate(df.format(new Date()));
			entity.setReportPersonId(use.getVcEmployeeId());
			entity.setReportPersonName(use.getVcName());
			entity.setStatus(Base.examstatus_2);
			result= hrPersongroupService.saveHrPersongroup(entity, use,itemFile,null);
		}
		else
		{
			
			result=CommitResult.createErrorResult("该数据已被删除");
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	//查询列表页面的上报
	@RequestMapping(value ="/postexam")
	public String _postexam(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HrPersongroup entityview;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entityview=new HrPersongroup();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
		{
			entityview=hrPersongroupService.getHrPersongroup(id);
		    a= examineService.getListExamine(entityview.getId(), Base.examkind_hr);
		    map.put("entityExamineviewlist", a);
		}	
		map.put("entityview", entityview);
		
		map.put("op", op);
		return "/report/hrPersongroup/hrPersongroupView";
	}
	
	
	//审核
	@RequestMapping(value ="/exam")
	public String _exam(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		
		HrPersongroup entityview;
		if(id==null)
		{
			entityview=new HrPersongroup();
			Date date=new Date();
			entityview.setYear(date.getYear());
		}
		else
			entityview=hrPersongroupService.getHrPersongroup(id);
		Integer status = entityview.getStatus();
		map.put("status", status);
		map.put("entityview", entityview);	
		return "/report/hrPersongroup/hrPersongroupExamine";
	}
	
	//审核提交
	@ResponseBody
	@RequestMapping(value ="/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _commitexam(Integer entityid,String examStr,Integer examResult,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");

		CommitResult result= hrPersongroupService.saveHrPersongroupExamine(entityid, examStr, examResult, use);
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	//添加信息
	private void addData(Map<String, Object> map)
	{
		List<HhBase> examstatustype= baseService.getBases(Base.examstatustype);
		
		map.put("examstatustype",examstatustype);
		
		
	}
	
	@ResponseBody
	@RequestMapping(value ="/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String reportgroupdelete(Integer id,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		CommitResult result=hrPersongroupService.deleteHrPersongroup(id, use);
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/hasCheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String hasCheck(HrPersongroup entity,String operate,HttpServletRequest request ,Map<String, Object> map){
		CommitResult result=new CommitResult();
		result.setResult(true);
		if(entity==null)
		{
			result= CommitResult.createErrorResult("该数据已被删除");
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
	 * 进入projectDetail.jsp页面中的nodeList
	 * @param pjId
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/nodeList")
	public String _nodeViewList(HrPersongroup entity,Map<String, Object> map,HttpServletRequest request){
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage =  hrPersongroupService.getHrPersonitemListView(entity, Integer.parseInt(curPageNum), Base.pagesize);
		map.put("msgPage", msgPage);
		String view = request.getParameter("view");
		map.put("view", view);
		map.put("flag","nodeList");
		//分页
	
		
		return "/report/hrPersongroup/hrpersonItemList";
	}
	
	/**
	 * 进入projectDetail.jsp页面中的nodeLists
	 * @param pjId
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/nodeLists")
	public String _nodeViewLists(HrPersongroup entity,Map<String, Object> map,HttpServletRequest request){
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage =  hrPersongroupService.getHrPersonorganizationListView(entity, Integer.parseInt(curPageNum), Base.pagesize);
		map.put("msgPage", msgPage);
		String view = request.getParameter("view");
		map.put("view", view);
		map.put("flag","nodeLists");
		//分页
	
		
		return "/report/hrPersongroup/hrpersonOrganizationList";
	}
	
	/**
	 * 财务人员填报导出功能
	 * @param entity
	 * @param request
	 * @param map
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("hrpgExport")
	public void hrpgExprot(HrPersongroup entity,String organalID,HttpServletRequest request, Map<String, Object> map,HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
	     //数据权限
        entity.setParentorg(str);
//        entity.setOrg(organalID);
        List<HrPersongroup> list = hrPersongroupService.getHrPersongroup(entity);
        List<HrPersonitem> exportList = new ArrayList<HrPersonitem>();
        List<HrPersongroup>  exportLists = new ArrayList<HrPersongroup>();
        for (int i = 0; i < list.size(); i++) {
        	HrPersongroup export = list.get(i);
			List<HrPersonitem> newList = hrPersongroupService.getHrPersonitem(list.get(i).getId());
			exportList.addAll(newList);
			exportLists.add(export);
			}
        XSSFWorkbook workBook=hrPersongroupService.getHrPeresonitemList(exportLists,exportList);
        response.reset();
	     String _name = "财务人员资质数据.xlsx";
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
	 * 财务人员编制导出功能
	 * @param entity
	 * @param request
	 * @param map
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("hrpgExports")
	public void hrpgExprots(HrPersongroup entity,String organalID,HttpServletRequest request, Map<String, Object> map,HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
	     //数据权限
        entity.setParentorg(str);
      /*  entity.setOrg(organalID);*/
        
        List<HrPersongroup> list = hrPersongroupService.getHrPersongroup(entity);
		List<HrPersonOrganization> exportList = new ArrayList<HrPersonOrganization>();
		List<HrPersongroup>  exportLists =  new ArrayList<HrPersongroup>();
		for (int i = 0; i < list.size(); i++) {
			HrPersongroup hrperson = list.get(i);
			List<HrPersonOrganization> newList = hrPersongroupService.getHrPersonorganization(list.get(i).getId());
			exportList.addAll(newList);
			exportLists.add(hrperson);
			}
		XSSFWorkbook workBook=hrPersongroupService.getHrPeresonorganizationList(exportLists,exportList);
        
  /*      List<HrPersonitem> list = hrPersongroupService.getPersonlloaninfoNewList(entity);*/
//         XSSFWorkbook workBook = hrPersongroupService.getPersonlloaninfoNewList(entity);
		// 清空response
	     response.reset();
	     String _name = "财务人员编制数据.xlsx";
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
}
