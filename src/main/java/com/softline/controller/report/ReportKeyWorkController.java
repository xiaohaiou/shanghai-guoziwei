package com.softline.controller.report;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
import com.softline.entity.HhBase;
import com.softline.entity.HhUsers;
import com.softline.entity.ReportKeywork;
import com.softline.entity.SysExamine;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IReportKeyWorkService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IPortalMsgService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/keywork")
public class ReportKeyWorkController {
	
	@Resource(name="reportKeyWorkService")
	private IReportKeyWorkService reportKeyWorkService;
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	@Resource(name = "systemService")
	private ISystemService systemService;
	@Resource(name = "examineService")
	private IExamineService examineService;	
	
	@Resource(name = "potalMsgService")
	private IPortalMsgService potalMsgService;
	
	@ModelAttribute
	public void getCommodity(@RequestParam(value = "id", required = false) Integer id,Map<String, Object> map, HttpServletRequest request) {
		if (id != null) {
			ReportKeywork entityview=reportKeyWorkService.getKeyworkbyID(id);
			map.put("reportKeywork", entityview);
		}
	}
	
	
	//维护的列表页面
	@RequestMapping("/list")
	public String _list(ReportKeywork entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/keywork";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		//核心业态下拉
		map.put("coreCompSelect",systemService.getBusinessCompany(systemService.getAllChildrenFinanceOrganal(str,Base.financetype), Base.financetype) );
		
        //数据权限
        entity.setParentorg(str);
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=reportKeyWorkService.getKeyWorkYear(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/keywork/list");
	    map.put("entityview", entity);
	    
	    //融资类别
		List<HhBase> indexType= baseService.getBases(Base.indexType);
		 map.put("indexType", indexType);
		return "/report/keywork/keyworkList";
	}
	
	
	@ResponseBody
	@RequestMapping(value ="/hasCheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String hasCheck(ReportKeywork entity,HttpServletRequest request ,Map<String, Object> map){
		CommitResult result=new CommitResult();
		result.setResult(true);
		if(entity==null)
		{
			result= CommitResult.createErrorResult("该数据已被删除");
		}
		return JSONArray.toJSONString(result);
	}	
	
	
	/**
	 * 跳转查看页面
	 * @param id
	 * @param request
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/view")
	public String _view(Integer id,HttpServletRequest request ,Map<String, Object> map, String op) throws IOException {
		ReportKeywork entity;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entity=new ReportKeywork();
		}
		else
		{
			entity=reportKeyWorkService.getKeyworkbyID(id);
		    a= examineService.getListExamine(entity.getId(), Base.examkind_keywork);
		}	
		map.put("entityview", entity);
		map.put("entityExamineviewlist", a);
		map.put("op", op);
		return "/report/keywork/keyWorkView";
	}
	
	//跳转新增修改页面
	@RequestMapping("/newmodify")
	public String _newmodify(ReportKeywork entity,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		map.put("op", op);
		HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		//核心业态下拉
		map.put("coreCompSelect",systemService.getBusinessCompany(systemService.getAllChildrenFinanceOrganal(str,Base.financetype), Base.financetype));

		ReportKeywork entityview;
		if(entity.getId()==null)
		{	
			entityview=new ReportKeywork();
			DateFormat df = new SimpleDateFormat("yyyy");
			Date date=new Date();
			entityview.setYear(Integer.valueOf((df.format(date))));
		}
		else
		{	
			entityview=reportKeyWorkService.getKeyworkbyID(entity.getId());
			List<SysExamine> a=new ArrayList<SysExamine>();
			a= examineService.getListExamine(entityview.getId(), Base.examkind_keywork);
			map.put("entityExamineviewlist", a);
		}
		map.put("entityview", entityview);	
		//融资类别
		List<HhBase> indexType= baseService.getBases(Base.indexType);
		 map.put("indexType", indexType);
		return "/report/keywork/keyWorkNewModify";
	}
	
	
	/**
	 * 保存数据
	 * @param entity
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/save", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String save(ReportKeywork entity,HttpServletRequest request,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		entity.setStatus(Base.examstatus_1); 
		return JSONArray.toJSONString(reportKeyWorkService.saveKeywork(entity, use ,"save"));
	}
	
	/**
	 * 修改数据
	 * @param entity
	 * @param request
	 * @return
	 * @throws IOException
	 */
	
	@RequestMapping(value="/update", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String update(ReportKeywork entity,HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		CommitResult result=new CommitResult();
		if (entity == null) {
			result=CommitResult.createErrorResult("该数据已被删除");
			return JSONArray.toJSONString(result);
		}else {
			ReportKeywork entityTemp=reportKeyWorkService.getKeyworkbyID(entity.getId());
			entityTemp.setYear(entity.getYear());
			entityTemp.setCoreOrg(entity.getCoreOrg());
			entityTemp.setIndextype(entity.getIndextype());
			entityTemp.setTargetcount(entity.getTargetcount());
			entityTemp.setStatus(Base.examstatus_1);
			return JSONArray.toJSONString(reportKeyWorkService.saveKeywork(entityTemp, use ,"update"));
		}
	}
	
	
	/**
	 * 保存并上报
	 * @param entity
	 * @param request
	 * @param map
	 * @param op
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value ="/saveReport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _saveReport(ReportKeywork entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		CommitResult result = new CommitResult();
		if(entity !=null){//编辑保存并上报， 判断是否删除 否则不能上报;
				entity.setStatus(Base.examstatus_2);
				result= reportKeyWorkService.saveKeywork(entity,use,"saveReport");

		}else{					
			result=CommitResult.createErrorResult("该数据已经删除，不能上报！");
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String delete(Integer id,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		CommitResult result=reportKeyWorkService.deleteKeywork(id,use);
		if(result.isResult())
		{
			potalMsgService.updatePortalMsg("report_keywork", id.toString());
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	//查询列表页面的上报
	@RequestMapping(value ="/postexam")
	public String _postexam(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		ReportKeywork entityview;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entityview=new ReportKeywork();
			Calendar calendar = Calendar.getInstance();
			entityview.setYear(calendar.get(Calendar.YEAR));
		}
		else
		{
			entityview=reportKeyWorkService.getKeyworkbyID(id);
		    a= examineService.getListExamine(entityview.getId(), Base.examkind_keywork);
		    map.put("entityExamineviewlist", a);
		}	
		map.put("entityview", entityview);
		map.put("op", op);
		return "/report/keywork/keyWorkView";
	}
	
	//审核的列表页面
	@RequestMapping("/examinelist")
	public String _examinelist(ReportKeywork entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/keywork";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        HttpSession session=request.getSession();
		String str=(String) session.getAttribute("gzwsession_financecompany");
		entity.setGetOperateType(Base.fun_examine);
		//核心业态下拉
		map.put("coreCompSelect",systemService.getBusinessCompany(systemService.getAllChildrenFinanceOrganal(str,Base.financetype), Base.financetype) );
        //数据权限
        entity.setParentorg(str);
		Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=reportKeyWorkService.getKeyWorkYear(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/keywork/examinelist");
	    map.put("entityview", entity);
	  //融资类别
  		List<HhBase> indexType= baseService.getBases(Base.indexType);
  		map.put("indexType", indexType);
		return "/report/keywork/keyWorkExamineList";
	}
	
	//审核
	@RequestMapping(value ="/exam")
	public String _exam(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		
		ReportKeywork entityview;
		if(id==null)
		{
			entityview=new ReportKeywork();
			Calendar calendar = Calendar.getInstance();
			entityview.setYear(calendar.get(Calendar.YEAR));
		}
		else
			entityview=reportKeyWorkService.getKeyworkbyID(id);
		map.put("entityview", entityview);	
		return "/report/keywork/keyworkExamine";
	}
	
	//审核提交
	@ResponseBody
	@RequestMapping(value ="/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _commitexam(Integer entityid,String examStr,Integer examResult,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");

		CommitResult result= reportKeyWorkService.saveKeyWorkExamine(entityid, examStr, examResult, use);
		if(result.isResult())
		{
			potalMsgService.updatePortalMsg("report_keywork", entityid.toString());
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
}
