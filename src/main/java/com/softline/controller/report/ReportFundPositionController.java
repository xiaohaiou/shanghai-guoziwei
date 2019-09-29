package com.softline.controller.report;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.softline.common.Base;
import com.softline.entity.HhBase;
import com.softline.entity.HhUsers;
import com.softline.entity.ReportFinancingAccount;
import com.softline.entity.SysExamine;
import com.softline.entity.fundPosition.DataFundPosition;
import com.softline.entity.fundPosition.DataFundPositionOther;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IReportFundPositionService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/reportFundPosition")
/**
 * 
 * @author ky_tian
 *
 */
public class ReportFundPositionController {

	@Resource(name = "reportFundPositionService")
	private IReportFundPositionService reportFundPositionService;
	@Resource(name = "baseService")
	private IBaseService baseService;
	@Resource(name = "examineService")
	private IExamineService examineService;	
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	/**
	 * 查询条件-结果
	 */
	@RequestMapping("/list")
	public String hrlist(DataFundPosition entity,HttpServletRequest request ,Map<String, Object> map,String time) {
		//树列表信息
		HttpSession session=request.getSession();
		//数据权限
		/*String dataauth=(String) session.getAttribute("gzwsession_AllFinancecompany");
	    if(!Common.notEmpty(entity.getOrg()))
	    	entity.setOrg(dataauth);*/
		String str=(String) session.getAttribute("gzwsession_financecompany");
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
		entity.setParentorg(str);
		//entity.setOrg(systemService.getAllChildrenFinanceOrganal(str,Base.financetype));
		String mapurl=request.getContextPath()+ "/reportFundPosition";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=reportFundPositionService.findPageList(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/reportFundPosition/list");
	    map.put("entityview", entity);
	    map.put("monthDate", Base.monthDate);
	    addData(map);
		return "/report/reportFundPosition/reportFundPositionFormList";
	}

	/**
	 * 获取DataFundPosition
	 */
	@RequestMapping("/get")
	@ResponseBody
	public DataFundPosition get(String id) {
		DataFundPosition dh=reportFundPositionService.get(Integer.valueOf(id));
		return dh;
	}

	/**
	 * 添加信息
	 */
	private void addData(Map<String, Object> map)
	{
		List<HhBase> examstatustype= baseService.getBases(Base.examstatustype);
		map.put("examstatustype",examstatustype);
	}

	/**
	 * 保存hr数据
	 */
	@RequestMapping(value="/saveOrUpdate", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveOrUpdate(DataFundPosition entity,HttpServletRequest request,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		entity.setStatus(Base.examstatus_1); 
		entity=this.setOtherData(entity);
		return JSONArray.toJSONString(reportFundPositionService.saveReportFundPosition(entity, use ,"save"));
	}
	
	/**
	 * 修改hr数据
	 */
	
	@RequestMapping(value="/update", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String update(DataFundPosition entity,HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		DataFundPosition entityTemp=reportFundPositionService.get(entity.getId());
		entityTemp.setStatus(Base.examstatus_1);
		entity=this.setData(entityTemp,entity);
		entity=this.setOtherData(entity);
		return JSONArray.toJSONString(reportFundPositionService.updateReportFundPosition(entity, use ,Base.examstatus_1));
	}
	
	/**
	 * 保存并上报
	 */
	@ResponseBody
	@RequestMapping(value ="/saveandreport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _saveandreport(DataFundPosition entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		DataFundPosition entityTemp = new DataFundPosition();
		CommitResult result = new CommitResult();
		if(entity.getId()!=null){
			entityTemp=reportFundPositionService.get(entity.getId());
			entity=this.setData(entityTemp,entity);
			entity=this.setOtherData(entity);
			entity.setStatus(Base.examstatus_2);
			result= reportFundPositionService.saveReportFundPosition(entity, use , "saveReport");
		}else{
			entity.setStatus(Base.examstatus_2);
			entity=this.setOtherData(entity);
			result= reportFundPositionService.saveReportFundPosition(entity, use , "saveReport");
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	/**
	 * 查询页上报
	 */
	@ResponseBody
	@RequestMapping(value ="/postexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _postexam(Integer id,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		DataFundPosition entity=reportFundPositionService.get(id);
		entity.setStatus(Base.examstatus_2);
		CommitResult result=reportFundPositionService.saveReportFundPosition(entity, use , "report");
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
		
	/**
	 * 新增弹窗
	 */
	@RequestMapping("/newmodify")
	public String hrNewModify(DataFundPosition entity,String organalID, HttpServletRequest request ,Map<String, Object> map) {
		HttpSession session=request.getSession();
		if(entity.getId()==null)
		{	
			entity=new DataFundPosition();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			entity.setDate(df.format(date));
			map.put("getCashBtn", "show");
		}
		else{
			map.put("op", "modify");
			map.put("monthDate", Base.monthDate);
			map.put("getCashBtn", "hide");
			entity=reportFundPositionService.get(entity.getId());
		}	
		if(organalID==null)
		{
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			organalID=use.getNnodeId();
		}
		String str=(String) session.getAttribute("gzwsession_financecompany");
		map.put("currencyKind",JSONArray.toJSONString(baseService.getBases(Base.currencyKind)));//币种
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
		map.put("entity", JSON.toJSONString(entity));	
		map.put("entityview",setOtherData(entity));	
		addData(map);
		return "/report/reportFundPosition/reportFundPositionFormNewModify";
	}
	
	/**
	 * 跳转新增修改页面
	 */
	@RequestMapping("/view")
	public String _view(Integer id,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		DataFundPosition entity;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entity=new DataFundPosition();
		}
		else
		{
			entity=reportFundPositionService.get(id);
		    a= examineService.getListExamine(entity.getId(), Base.examkind_hr_reportFundPosition);
		}	
		List<HhBase> currencyKind=baseService.getBases(Base.currencyKind);
		map.put("currencyKind",JSONArray.toJSONString(currencyKind));
		map.put("entityList", JSON.toJSONString(entity));	
		map.put("entity",setOtherData(entity));	
		map.put("entityExamineviewlist", a);
		map.put("op", "view");
		return "/report/reportFundPosition/reportFundPositionView";
	}
	
	/**
	 * 跳转上报页面
	 */
	@RequestMapping("/report")
	public String report(Integer id,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		String mapurl=request.getContextPath()+ "/reportFundPosition";
		map.put("mapurl", mapurl);
		DataFundPosition entity;
		List<SysExamine> a=new ArrayList<SysExamine>();
		if(id==null)
		{	
			entity=new DataFundPosition();
		   
		}
		else
		{
			entity=reportFundPositionService.get(id);
		}	
		a= examineService.getListExamine(entity.getId(), Base.examkind_hr_reportFundPosition);
		List<HhBase> currencyKind=baseService.getBases(Base.currencyKind);
		map.put("currencyKind",JSONArray.toJSONString(currencyKind));
		map.put("entityList", JSON.toJSONString(entity));	
		map.put("entity",setOtherData(entity));
		map.put("entityExamineviewlist", a);
		map.put("op", "report");
		return "/report/reportFundPosition/reportFundPositionView";
	}
	
	/**
	 * 审核的列表页面
	 */
	@RequestMapping("/examinelist")
	public String _examinelist(DataFundPosition entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		//树列表信息
		HttpSession session=request.getSession();
		//数据权限
		/*String dataauth=(String) session.getAttribute("gzwsession_AllFinancecompany");
	    if(!Common.notEmpty(entity.getOrg()))
	    	entity.setOrg(dataauth);*/
		String str=(String) session.getAttribute("gzwsession_financecompany");
		entity.setParentorg(str);
		systemService.getAllChildrenFinanceOrganal(str,Base.financetype);
		map.put("allCompanyTree", JSON.toJSONString(selectUserService.getOtherOrganal(str,Base.financetype)) );
		String mapurl=request.getContextPath()+ "/reportFundPosition";
		map.put("mapurl", mapurl);
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
        MsgPage msgPage=reportFundPositionService.findExaminePageList(entity,pageNum,Base.pagesize);
	    map.put("msgPage", msgPage);
	    map.put("searchurl", "/shanghai-gzw/reportFundPosition/examinelist");
	    map.put("entityview", entity);
	    map.put("monthDate", Base.monthDate);
		addData(map);
		return "/report/reportFundPosition/reportFundPositionExamineList";
	}
	
	/**
	 * 审核
	 */
	@RequestMapping(value ="/exam")
	public String _exam(Integer id,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		DataFundPosition entity;
		if(id==null)
		{
			entity=new DataFundPosition();
		}
		else
			entity=reportFundPositionService.get(id);
		Integer status=0;

		status=entity.getStatus();
		List<HhBase> currencyKind=baseService.getBases(Base.currencyKind);
		map.put("currencyKind",JSONArray.toJSONString(currencyKind));
		map.put("entityList", JSON.toJSONString(entity));	
		map.put("entity",setOtherData(entity));	
		map.put("status",status);
		return "/report/reportFundPosition/reportFundPositionExamine";
	}
	
	/**
	 * 审核提交
	 */
	@ResponseBody
	@RequestMapping(value ="/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _commitexam(Integer entityid,String examStr,Integer examResult,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		CommitResult result= reportFundPositionService.saveReportFundPositionExamine(entityid, examStr, examResult, use);
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public String reportgroupdelete(DataFundPosition entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		String data="";
		CommitResult result = new CommitResult();
		if(reportFundPositionService.get(entity)){
			result=reportFundPositionService.deleteReportFundPosition(entity.getId(), use);
		}else{
			result.setResult(false); 
		}
		data=JSONArray.toJSONString(result);
		return data;
	}
	/**
	 * chack
	 */
	@RequestMapping("/chackGet")
	@ResponseBody
	public String get(DataFundPosition entity) {
		CommitResult result = new CommitResult();
		String data="";
		if(reportFundPositionService.get(entity)){
			result.setResult(true); 
		}else{
			result.setResult(false); 
		}
		data=JSONArray.toJSONString(result);
		return data;
	}
	
	/**
	 * 获取近日头寸
	 */
	@RequestMapping("/getCash")
	@ResponseBody
	public DataFundPosition getCash(String org) {
		DataFundPosition entity = new DataFundPosition();
		entity=reportFundPositionService.getCash(org);
		return entity;
	}
	
	public DataFundPosition setData(DataFundPosition entityTemp,DataFundPosition entity){
		//entity.setId(entityTemp.getId());
		entity.setStatus(entityTemp.getStatus());
		//entity.setExamineTime(entityTemp.getExamineTime());
		entity.setIsdel(entityTemp.getIsdel());
		entity.setReportPersonName(entityTemp.getReportPersonName());
		entity.setReportPersonId(entityTemp.getReportPersonId());
		entity.setReportDate(entityTemp.getReportDate());
		entity.setAuditorPersonName(entityTemp.getAuditorPersonName());
		entity.setAuditorPersonId(entityTemp.getAuditorPersonId());
		entity.setAuditorDate(entityTemp.getAuditorDate());
		entity.setCreatePersonName(entityTemp.getCreatePersonName());
		entity.setCreatePersonId(entityTemp.getCreatePersonId());
		entity.setCreateDate(entityTemp.getCreateDate());
		entity.setLastModifyPersonId(entityTemp.getLastModifyPersonId());
		entity.setLastModifyPersonName(entityTemp.getLastModifyPersonName());
		entity.setLastModifyDate(entityTemp.getLastModifyDate());
		return entity;
	}
	
	public DataFundPosition setOtherData(DataFundPosition entity){
		if(entity.getDataFundPositionOthersTemp()!=null){
			List<DataFundPositionOther> dfpoList = new ArrayList<DataFundPositionOther>();
			dfpoList=entity.getDataFundPositionOthersTemp();
			for(int i = 0;i<dfpoList.size();i++){
				DataFundPositionOther df = new DataFundPositionOther();	
				df.setFcAvailableCashO(dfpoList.get(i).getFcAvailableCashO());
				df.setFcNavailableCashO(dfpoList.get(i).getFcNavailableCashO());
				df.setBAvailableCashO(dfpoList.get(i).getBAvailableCashO());
				df.setBNavailableCashOneO(dfpoList.get(i).getBNavailableCashOneO());
				df.setBNavailableCashTwoO(dfpoList.get(i).getBNavailableCashTwoO());
				df.setFcCashO(dfpoList.get(i).getFcCashO());
				df.setBNavailableCashO(dfpoList.get(i).getBNavailableCashO());
				df.setAvailableCashO(dfpoList.get(i).getAvailableCashO());
				df.setNavailableCashO(dfpoList.get(i).getNavailableCashO());
				df.setCashO(dfpoList.get(i).getCashO());
				df.setCurrencyO(dfpoList.get(i).getCurrencyO());
				entity.getDataFundPositionOthers().add(df);
			}
		}
		return entity;
	}
	
	public String f4(String s){
		DecimalFormat df = new DecimalFormat("#.0000");
		return df.format(Double.parseDouble(s))+"";
	}
	
	/**
	 * 跳转到各单位公司汇总子单位页面
	 */
	@RequestMapping("/sumCompanyList")
	public String sumCompanyList(Integer id,HttpServletRequest request ,Map<String, Object> map) throws IOException {
				
		String curPageNum = request.getParameter("pageNums");
		
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        
        Integer pageNum = Integer.valueOf(curPageNum);
        
        DataFundPosition entityview = new DataFundPosition();
				
		if(id!=null)
			entityview=reportFundPositionService.get(id);
		
		MsgPage msgPage = reportFundPositionService.findAllchildernCompany(entityview,pageNum,Base.pagesize);
		
	    map.put("msgPage", msgPage);
	    
		return "/report/reportFundPosition/reportFundPositionFormforSumcompany";
	}
	
	
	
}
