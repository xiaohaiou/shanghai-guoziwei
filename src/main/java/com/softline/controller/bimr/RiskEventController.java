package com.softline.controller.bimr;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nfunk.jep.function.Str;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.entity.HhBase;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entity.SysExamine;
import com.softline.entity.bimr.BimrCompliance;
import com.softline.entity.bimr.BimrCompliancePerson;
import com.softline.entity.bimr.BimrRiskCatalog;
import com.softline.entity.bimr.BimrRiskEvent;
import com.softline.entity.bimr.BimrRiskEventAdoptstrategy;
import com.softline.entity.bimr.BimrRiskEventAdoptstrategyFeedback;
import com.softline.entity.bimr.BimrRiskEventFeedback;
import com.softline.entity.bimr.BimrRiskEventRelevance;
import com.softline.entity.bimr.BimrRiskEventRelevancetrack;
import com.softline.entityTemp.CommitResult;
import com.softline.service.bimr.IRiskCatalogService;
import com.softline.service.bimr.IRiskEventService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IPortalMsgService;
import com.softline.service.system.ISystemService;
import com.softline.service.system.impl.CommonService;
import com.softline.util.MsgPage;
import com.thoughtworks.xstream.mapper.Mapper.Null;

/**
 * 风险目录显示控制
 * 
 * @author liu
 */
@Controller
@RequestMapping("/bimr/riskEvent")
public class RiskEventController {

	@Autowired
	private IRiskEventService riskEventService;
	

	
	@Autowired
	@Qualifier("riskCatalogService")
	private IRiskCatalogService riskCatalogService;	
	
	@Autowired
	@Qualifier("examineService")
	private IExamineService examineService;	
	
	@Resource(name = "selectUserService")
    private ISelectUserService selectUserService;
	
	@Resource(name = "baseService")
	private IBaseService baseService;
	
	@Resource(name = "potalMsgService")
	private IPortalMsgService potalMsgService;
	
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	@Resource(name="commonService")
	private ICommonService commonService;
	//事件大状态   82100 事件待上报  82101  事件待审核  82102 事件已审核未跟踪 82106 事件已退回 82108 事件跟踪中 82109 事件跟踪结束
	//跟踪小状态  82103 跟踪待上报   82104 跟踪待审核   82105 跟踪已审核  82107  跟踪已退回
	@ModelAttribute
	public void getCommodity(@RequestParam(value = "id", required = false) Integer id,
							Map<String, Object> map, HttpServletRequest request) {
		if (id != null) {
			BimrRiskEvent entityview=riskEventService.getRiskEventById(id);
			map.put("bimrRiskEvent", entityview);
		}
	}
	
	
	
	@RequestMapping("list")
	public String getRiskEventList(BimrRiskEvent entity,String type, HttpServletRequest request, Map<String, Object> map){
		if (!type.equals("list")) {//说明是从风险事件审核页面过来的
			entity.setOp("listexamine");
		}
		HttpSession session = request.getSession();
		String company = (String)session.getAttribute("gzwsession_company");
        map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(company)));
        HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
        //获取数据权限
        String dataauth=systemService.getDataauth(company);
        entity.setDataauth(dataauth);
        if (Common.notEmpty(entity.getCompId())) {
        	if (entity.getCompId().split(",").length==1) {
        		entity.setCompId(systemService.getDataauth(entity.getCompId()));
			}
        }
        //采取措施
        List<HhBase> copingStrategy = baseService.getBases(Base.risk_event_copingStrategy);
		map.put("copingStrategy", copingStrategy);
		
		//状态
        List<HhBase> status = baseService.getBases(Base.risk_event_status);
		map.put("status", status);
		
		// 当前页码
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage=null;
		String titletype=null;
		if(type.equals("examine1")){
			titletype="1";
			 msgPage = riskEventService.getRiskEventListView(entity, pageNum, Base.pagesize,83000);
		}else if(type.equals("examine2")){
			titletype="2";
			 msgPage = riskEventService.getRiskEventListView(entity, pageNum, Base.pagesize,83001);
		}else if (type.equals("examine3")) {
			titletype="3";
			 msgPage = riskEventService.getRiskEventListView(entity, pageNum, Base.pagesize,83002);
		}else if(type.equals("examine4")){
			titletype="4";
			 msgPage = riskEventService.getRiskEventMeasureListView(entity, pageNum, Base.pagesize, use.getVcEmployeeId());
		}else {
			msgPage = riskEventService.getRiskEventListView(entity, pageNum, Base.pagesize,null);
		}
		map.put("titletype", titletype);
		map.put("msgPage", msgPage);
		// 路径
		String mapurl = request.getContextPath() + "/bimr/riskEvent";
		map.put("mapurl", mapurl);
		map.put("entity", entity);
		map.put("searchurl", request.getContextPath() + "/bimr/riskEvent/list?type="+type);
		if("list".equals(type))	
			return "/bimr/riskevent/riskEventList";
		else
			return "/bimr/riskevent/riskEventExamineList";
	}
	
	/**
	 * 风险征兆事件反馈及审核
	 * @param entity
	 * @param type
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("feedbacklist")
	public String getFeedBackList(BimrRiskEvent entity,String type,HttpServletRequest request, Map<String, Object> map){
		if (type.equals("examine")) {//说明是审核页面过来的
			entity.setOp("feedbackexamine");
		}else {
			entity.setOp("feedback");
		}
		HttpSession session = request.getSession();
		String company = (String)session.getAttribute("gzwsession_company");
        map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(company)));
        
        //获取数据权限
        String dataauth=systemService.getDataauth(company);
        entity.setDataauth(dataauth);
        
        if (Common.notEmpty(entity.getCompId())) {
        	if (entity.getCompId().split(",").length==1) {
        		entity.setCompId(systemService.getDataauth(entity.getCompId()));
			}
        }
        //采取措施
        List<HhBase> copingStrategy = baseService.getBases(Base.risk_event_copingStrategy);
		map.put("copingStrategy", copingStrategy);
		
		//状态
        List<HhBase> status = baseService.getBases(Base.risk_event_status);
		map.put("status", status);
		
		// 当前页码
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage = riskEventService.getRiskFeedBackListView(entity, pageNum, Base.pagesize);
		map.put("msgPage", msgPage);
		// 路径
		String mapurl = request.getContextPath() + "/bimr/riskEvent";
		map.put("mapurl", mapurl);
		map.put("entity", entity);
		map.put("op", type);
		map.put("searchurl", request.getContextPath() + "/bimr/riskEvent/feedbacklist?type="+type);
		return "/bimr/riskevent/riskEventFeedBackList";
	}
	
	/**
	 * 风险征兆事件反馈及审核 页面上的 反馈审核
	 * @param entity
	 * @param trackid
	 * @param map
	 * @param request
	 * @param op
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("feedback")
	public String feedback(BimrRiskEvent entity,Integer trackid, Map<String, Object> map, HttpServletRequest request, String op) throws IOException {
		
		//采取措施
        List<HhBase> copingStrategy = baseService.getBases(Base.risk_event_copingStrategy);
		map.put("copingStrategy", copingStrategy);

		//处理方式
        List<HhBase> handleway = baseService.getBases(Base.risk_event_handleway);
		map.put("handleway", handleway);
		
		 //省份
        List<HhBase> province = baseService.getBases(Base.risk_event_province);
		map.put("province", province);
		
		//每月反馈
		List<BimrRiskEventFeedback> feedbackList=riskEventService.getRiskEventFeedbackList(entity.getId());
		map.put("feedbackList", feedbackList);
		
		List<BimrRiskEventAdoptstrategyFeedback> adoptstrategyFeedbackList=riskEventService.getAdoptstrategyFeedbcakList(entity.getId());
		map.put("adoptstrategyFeedbackList", adoptstrategyFeedbackList);
		
//		if (op.equals("feedbackexamine")) {//如果是审核
//			
//		}
		List<SysExamine> a=new ArrayList<SysExamine>();
		a= examineService.getListExamine(entity.getId(), Base.examkind_risk_feedback);
		map.put("entityExamineviewlist", a);
		
		map.put("entity", entity);
		// 路径
		String mapurl = request.getContextPath() + "/bimr/riskEvent";
		map.put("mapurl", mapurl);
		map.put("op", op);
		//map.put("organalID", organalID);
		if("feedbackexamine".equals(op)){
			return "/bimr/riskevent/riskEventFeedBackView";
		}
		else{
			return "/bimr/riskevent/riskEventFeedBack";
		}
	}

	/**
	 * 风险征兆事件反馈及审核 里面的新增每月反馈
	 * @param entity
	 * @param trackid
	 * @param map
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("feedbackadd")
	public String feedbackadd(BimrRiskEvent entity,Integer feedbackid, Map<String, Object> map, HttpServletRequest request) throws IOException {
		
		BimrRiskEventFeedback feedback = new BimrRiskEventFeedback();
		if (feedbackid !=null) {
			feedback = riskEventService.getBimrRiskEventFeedback(feedbackid);
		}
		map.put("entity", entity);
		map.put("feedback", feedback);
		// 路径
		String mapurl = request.getContextPath() + "/bimr/riskEvent";
		map.put("mapurl", mapurl);
		//map.put("organalID", organalID);
		return "/bimr/riskevent/riskEventFeedBackAdd";
	}
	
	
	
	//每月反馈的保存功能
	@ResponseBody
	@RequestMapping(value ="/savefeedback", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String savefeedback(BimrRiskEvent entity,Integer feedbackid,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		BimrRiskEventFeedback feedback = new BimrRiskEventFeedback();
		if (feedbackid !=null ) {
			feedback= riskEventService.getBimrRiskEventFeedback(feedbackid);
		}
		
		feedback.setEventid(entity.getId());
		feedback.setFeedbacktime(entity.getFeedbacktime());
		feedback.setNowdetailsituation(entity.getNowdetailsituation());
		feedback.setStatus(entity.getFeedbackstatus());
		feedback.setMeasuresituation(entity.getMeasuresituation());
		feedback.setIsDel(0);
		CommitResult result= riskEventService.saveFeedback(feedback,use);
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value ="/deletefeedback", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String deletefeedback(Integer feedbackid, HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
		BimrRiskEventFeedback feedback = new BimrRiskEventFeedback();
		if (feedbackid !=null ) {
			feedback= riskEventService.getBimrRiskEventFeedback(feedbackid);
		}
		CommitResult result=riskEventService.deleteFeedback(feedback, use);
		String data="";
		data=JSONArray.toJSONString(result); 
		return data;
	}
	
	/**
	 * 风险征兆事件反馈上报里应对措施删除
	 * @param entity
	 * @param trackid
	 * @param map
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value ="/deletefeedbacks", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String deletefeedbacks(Integer feedbackid, HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
		BimrRiskEventAdoptstrategyFeedback feedback = new BimrRiskEventAdoptstrategyFeedback();
		if (feedbackid !=null ) {
			feedback= riskEventService.getBimrRiskEventAdoptstrategyFeedback(feedbackid);
		}
		CommitResult result=riskEventService.deleteFeedbacks(feedback, use);
		String data="";
		data=JSONArray.toJSONString(result); 
		return data;
	}
	
	/**
	 * 风险征兆事件反馈及审核 里面的新增应对措施
	 * @param entity
	 * @param trackid
	 * @param map
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("feedbackadd2")
	public String feedbackadd2(BimrRiskEvent entity, Map<String, Object> map, HttpServletRequest request) throws IOException {
		List<BimrRiskEventAdoptstrategy> adoptStrategyList=riskEventService.getAdoptstrategy(entity.getId());
		map.put("adoptStrategyList", adoptStrategyList);
		map.put("entity", entity);
		// 路径
		String mapurl = request.getContextPath() + "/bimr/riskEvent";
		map.put("mapurl", mapurl);
		//map.put("organalID", organalID);
		return "/bimr/riskevent/riskEventFeedBackAdd2";
	}
	
	//每月反馈的保存功能
	@ResponseBody
	@RequestMapping(value ="/savefeedback2", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String savefeedback2(BimrRiskEvent entity,
			Integer[] adoptStrategy,String[] adoptStrategyname,String[] planfinishtime,String[] responsibleCompName,String[] nowfinishsituation,
			HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		List<BimrRiskEventAdoptstrategyFeedback> addoptstrategyList=new ArrayList<BimrRiskEventAdoptstrategyFeedback>();
		if(adoptStrategyname!=null){
			for(int i=0;i<adoptStrategyname.length;i++){
				if(adoptStrategyname[i] !=null){
					BimrRiskEventAdoptstrategyFeedback addoptstrategy=new BimrRiskEventAdoptstrategyFeedback();
					addoptstrategy.setEventId(entity.getId());
					addoptstrategy.setFeedbacktime(entity.getFeedbacktime());
			    	addoptstrategy.setAdoptStrategyname(adoptStrategyname[i]);
					addoptstrategy.setPlanfinishtime(planfinishtime[i]);
					addoptstrategy.setResponsibleCompName(responsibleCompName[i]);
					addoptstrategy.setNowfinishsituation(nowfinishsituation[i]);
					addoptstrategyList.add(addoptstrategy);
				}
			}
		}
		
		
		
		CommitResult result= riskEventService.saveAdoptstrategyFeedbackList(entity,addoptstrategyList,use);
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	/**
	 * 风险征兆事件反馈及审核 里面的应对措施的编辑页面
	 * @param entity
	 * @param trackid
	 * @param map
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("feedbackadd2Edit")
	public String feedbackadd2Edit(Integer adoptstrategyid, Map<String, Object> map, HttpServletRequest request) throws IOException {
		BimrRiskEventAdoptstrategyFeedback entity=riskEventService.getAdoptstrategyFeedback(adoptstrategyid);
		map.put("entity", entity);
		// 路径
		String mapurl = request.getContextPath() + "/bimr/riskEvent";
		map.put("mapurl", mapurl);
		//map.put("organalID", organalID);
		return "/bimr/riskevent/riskEventFeedBackEdit";
	}
	
	/**
	 * 风险征兆事件反馈及审核 里面的应对措施的编辑页面的保存
	 * @param entity
	 * @param adoptstrategyid
	 * @param request
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value ="/savefeedbackEdit", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String savefeedbackEdit(BimrRiskEvent entity,Integer adoptstrategyid,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		BimrRiskEventAdoptstrategyFeedback adoptstrategy = new BimrRiskEventAdoptstrategyFeedback();
		if (adoptstrategyid !=null ) {
			adoptstrategy= riskEventService.getAdoptstrategyFeedback(adoptstrategyid);
		}
		adoptstrategy.setFeedbacktime(entity.getFeedbacktime());
		adoptstrategy.setNowfinishsituation(entity.getNowfinishsituation());
		CommitResult result= riskEventService.saveAdoptstrategyFeedback(adoptstrategy,use);
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	/**
	 * 风险征兆事件反馈---反馈审核页面里的申请办结
	 * @param entity
	 * @param trackid
	 * @param request
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value ="/updatestautsforfeedback", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String updatestautsforfeedback(BimrRiskEvent entity,	HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		entity.setStatus(82108);//将事件状态设为事件跟踪中
		CommitResult result= riskEventService.updatestautsforfeedback(entity,use);
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	//审核提交
	@ResponseBody
	@RequestMapping(value ="/commitexamfeedback", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String commitexamfeedback(BimrRiskEvent entity,String examStr,Integer examResult,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");

		CommitResult result= riskEventService.saveFeedbackExamine(entity, examStr, examResult, use);
		if(result.isResult())
		{
			potalMsgService.updatePortalMsg("bimr_risk_event", entity.getId().toString());
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@RequestMapping("/searchPerson")
	public String searchPerson(HttpServletRequest request,String inputname,String inputid, Map<String, Object> map){
		map.put("inputname", inputname);
		map.put("inputid", inputid);
		return "/bimr/riskevent/PersonSearch";
	}
	
	@RequestMapping("examineTracklist")
	public String examineTracklist(BimrRiskEventRelevancetrack entity,String type,
			HttpServletRequest request, Map<String, Object> map){
		HttpSession session = request.getSession();
		String company = (String)session.getAttribute("gzwsession_company");
        map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(company)));
        //获取数据权限
        String dataauth=systemService.getDataauth(company);
        entity.setDataauth(dataauth);

		
		//状态
        List<HhBase> status = baseService.getBases(Base.risk_event_trackstatus);
		map.put("status", status);
		
		// 当前页码
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage = riskEventService.getExamineTracklist(entity, pageNum, Base.pagesize);
		map.put("msgPage", msgPage);
		// 路径
		String mapurl = request.getContextPath() + "/bimr/riskEvent";
		map.put("mapurl", mapurl);
		map.put("entity", entity);
		map.put("searchurl", request.getContextPath() + "/bimr/riskEvent/examineTracklist");
		return "/bimr/riskevent/riskEventExamineTrackList";
	}
	
	@RequestMapping("addOrModify")
	public String addOrModify(BimrRiskEvent entity, Map<String, Object> map, HttpServletRequest request, String op) throws IOException {
		//公司树
		HttpSession session = request.getSession();
		String company = (String)session.getAttribute("gzwsession_company");
        map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(company)));
        
        //采取措施
        List<HhBase> copingStrategy = baseService.getBases(Base.risk_event_copingStrategy);
		map.put("copingStrategy", copingStrategy);
		
		//处理方式
        List<HhBase> handleway = baseService.getBases(Base.risk_event_handleway);
		map.put("handleway", handleway);
		
		 //省份
        List<HhBase> province = baseService.getBases(Base.risk_event_province);
		map.put("province", province);
		
/*		List<BimrRiskEventAdoptstrategy> adoptStrategyList=riskEventService.getAdoptstrategy(entity.getId());
		map.put("adoptStrategyList", adoptStrategyList);*/
		
		map.put("op", op);
		
		map.put("entity", entity);
		
		
		//map.put("organalID", organalID);
		return "/bimr/riskevent/riskEventAddOrModify";
	}
	
	@ResponseBody
	@RequestMapping(value ="/saveOrUpdate", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String saveOrUpdate(BimrRiskEvent entity, HttpServletRequest request,Integer type) throws IOException {
		
		CommitResult result=new CommitResult();
		if(entity==null)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
		}
		else
		{
			if (entity.getIshighunit() == 0) {
				entity.setHighunitmeasure(null);
			}else {
				entity.setPlancloesetime(null);
				entity.setPlanmeasure("");
			}
			if (entity.getCopingStrategy() == 82002) {
				entity.setTrackpersonid(null);
				entity.setTrackpersonname("");
				entity.setHighunitmeasure(null);
				entity.setPlancloesetime(null);
				entity.setPlanmeasure("");
			}
			
			
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			result= riskEventService.saveRiskEvent(entity, use,type);
		}
		
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	
	@ResponseBody
	@RequestMapping(value ="/savePerson", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String savePerson(BimrRiskEvent entity, HttpServletRequest request,Map<String, Object> map) throws IOException {
		
		BimrRiskEvent bimrRiskEvent=riskEventService.getRiskEventById(entity.getId());
		
		bimrRiskEvent.setInsidetrackpersonname(entity.getInsidetrackpersonname());
		bimrRiskEvent.setInsidetrackpersonid(entity.getInsidetrackpersonid());
		CommitResult result=new CommitResult();
		
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			result= riskEventService.saveRiskEvent(bimrRiskEvent, use,null);
		
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	
	
	@ResponseBody
	@RequestMapping(value ="/saveAdoptstrategy", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String saveAdoptstrategy(BimrRiskEvent entity,
			String[] adoptStrategy,String[] responsibleCompName,String[] planfinishtime,
			
			HttpServletRequest request ,Map<String, Object> map) throws IOException {
		
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		List<BimrRiskEventAdoptstrategy> addoptstrategyList=new ArrayList<BimrRiskEventAdoptstrategy>();
		
		BimrRiskEvent bimrRiskEvent=riskEventService.getRiskEventById(entity.getId());
		if (bimrRiskEvent != null) {
			
			if(adoptStrategy!=null){
				for(int i=0;i<adoptStrategy.length;i++){
					if(adoptStrategy[i] !=null){
						BimrRiskEventAdoptstrategy addoptstrategy=new BimrRiskEventAdoptstrategy();
						addoptstrategy.setAdoptStrategyname(adoptStrategy[i]);
						addoptstrategy.setPlanfinishtime(planfinishtime[i]);
						addoptstrategy.setResponsibleCompName(responsibleCompName[i]);
						addoptstrategyList.add(addoptstrategy);
					}
				}
			}
		}
		List<BimrRiskEventAdoptstrategy> oldList=riskEventService.getAdoptstrategy(bimrRiskEvent.getId());
		for(BimrRiskEventAdoptstrategy l:oldList){
			commonService.delete(l);//物理删除
		}
		
		for(BimrRiskEventAdoptstrategy l:addoptstrategyList){
			l.setEventId(bimrRiskEvent.getId());
			l.setIsDel(0);
			commonService.saveOrUpdate(l);//新增
		}
		CommitResult result=new CommitResult();
		result.setResult(true);
		result.setEntity(bimrRiskEvent);
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@RequestMapping("/view")
	public String _view(BimrRiskEvent entity, HttpServletRequest request,
			Map<String, Object> map) throws IOException {
		 List<HhBase> province = baseService.getBases(Base.risk_event_province);
		 for (int i = 0; i < province.size(); i++) {
			if(entity.getProvince().equals(province.get(i).getNum()+"")){
				entity.setProvince(province.get(i).getDescription());
				break;
			}
		}
		  //获取案件历史审核记录
        List<SysExamine> examineList = examineService.getListExamine(entity.getId(), Base.examkind_risk_event);
        List<SysExamine> feedbackexamineList = examineService.getListExamine(entity.getId(), Base.examkind_risk_feedback);
        
        
            //每月反馈
      		List<BimrRiskEventFeedback> feedbackList=riskEventService.getRiskEventFeedbackList(entity.getId());
      		map.put("feedbackList", feedbackList);
      		
      		List<BimrRiskEventAdoptstrategyFeedback> adoptstrategyFeedbackList=riskEventService.getAdoptstrategyFeedbcakList(entity.getId());
      		map.put("adoptstrategyFeedbackList", adoptstrategyFeedbackList);
        
		map.put("entity", entity);
		map.put("examineList",examineList);
		map.put("feedbackexamineList",feedbackexamineList);
		return "/bimr/riskevent/riskEventView";
	}
	
	@ResponseBody
	@RequestMapping(value ="/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String delete(BimrRiskEvent entity, HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
		
		CommitResult result=riskEventService.deleteRiskEvent(entity, use);
		if(result.isResult())
			potalMsgService.updatePortalMsg("bimr_compliance", entity.getId().toString());
		String data="";
		data=JSONArray.toJSONString(result); 
		return data;
	}
	
	
	/**
	 * 风险事件上报结果查询
	 * @param entity
	 * @param type
	 * @param request
	 * @param map
	 * @return
	 */
/*	@RequestMapping("resultlist")
	public String resultlist(BimrRiskEvent entity,String type,HttpServletRequest request, Map<String, Object> map){
		
		HttpSession session = request.getSession();
		String company = (String)session.getAttribute("gzwsession_company");
        map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(company)));
        
        //获取数据权限
        String dataauth=systemService.getDataauth(company);
        entity.setDataauth(dataauth);
        
        //核心企业
  		List<HhOrganInfoTreeRelation> coreComp=systemService.getBusinessCompany(dataauth, null);
  		map.put("coreCompSelect", coreComp);
		
		// 当前页码
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage = riskEventService.getRiskEventResultListView(entity, pageNum, Base.pagesize);
		map.put("msgPage", msgPage);
		// 路径
		String mapurl = request.getContextPath() + "/bimr/riskEvent";
		map.put("mapurl", mapurl);
		map.put("entity", entity);
		map.put("searchurl", request.getContextPath() + "/bimr/riskEvent/resultlist");
		return "/bimr/riskevent/riskEventList";
	}*/
	
	/**
	 * 风险事件关联目录
	 * @param entity
	 * @param type
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("relevancelist")
	public String relevancelist(BimrRiskEvent entity,String type,
			HttpServletRequest request, Map<String, Object> map){
		if ("relevanceexamine".equals(type)) {//如果是从审核页面过来。
			entity.setOp("relevanceexamine");
		}else {
			entity.setOp("relevancelist");
		}
		HttpSession session = request.getSession();
		String company = (String)session.getAttribute("gzwsession_company");
        map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(company)));
        
        //获取数据权限
        String dataauth=systemService.getDataauth(company);
        entity.setDataauth(dataauth);
        if (Common.notEmpty(entity.getCompId())) {
        	if (entity.getCompId().split(",").length==1) {
        		entity.setCompId(systemService.getDataauth(entity.getCompId()));
			}
        }
		//状态
        List<HhBase> status = baseService.getBases(Base.risk_event_status);
		map.put("status", status);
		
		// 当前页码
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage = riskEventService.getRiskEventRelevanceListView(entity, pageNum, Base.pagesize);
		map.put("msgPage", msgPage);
		// 路径
		String mapurl = request.getContextPath() + "/bimr/riskEvent";
		map.put("mapurl", mapurl);
		map.put("entity", entity);
		map.put("op", type);
		map.put("searchurl", request.getContextPath() + "/bimr/riskEvent/relevancelist?type="+type);
		if("relevancelist".equals(type) || "relevancequery".equals(type))	
			return "/bimr/riskevent/riskEventRelevanceList";
		else
			return "/bimr/riskevent/riskEventRelevanceListExamine";
	}
	
	@RequestMapping("relevanceaddOrModify")
	public String relevanceaddOrModify(BimrRiskEvent entity, Map<String, Object> map, HttpServletRequest request, String op) throws IOException {
		
		List<BimrRiskCatalog> ctgs1 = riskCatalogService.getRiskCatalogChildren(null);
		map.put("ctgs1", ctgs1);

		//采取措施
        List<HhBase> copingStrategy = baseService.getBases(Base.risk_event_copingStrategy);
		map.put("copingStrategy", copingStrategy);

		 //省份
        List<HhBase> province = baseService.getBases(Base.risk_event_province);
		map.put("province", province);
		
		List<BimrRiskEventRelevance> relevanceList=riskEventService.getRelevanceList(entity.getId());
		map.put("relevanceList", relevanceList);

		map.put("entity", entity);
		// 路径
		String mapurl = request.getContextPath() + "/bimr/riskEvent";
		map.put("mapurl", mapurl);
		map.put("op", op);
		//map.put("organalID", organalID);
		return "/bimr/riskevent/riskEventRelevanceAddOrModify";
	}
	
	@RequestMapping(value="getChildren", method= RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public CommitResult getChildren(@RequestParam(required = false)Integer parentId){
		CommitResult result = new CommitResult();
		
		result.setResult(true);
		result.setEntity(riskCatalogService.getRiskCatalogChildren(parentId));
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value ="/relevancesaveOrUpdate", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String relevancesaveOrUpdate(BimrRiskEvent entity, HttpServletRequest request,
			String[] leveloneid,String[] leveltwoid,String[] levelthreeid,
			String[] levelonename,String[] leveltwoname,String[] levelthreename) throws IOException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=new CommitResult();
		if(entity==null)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
		}
		else
		{
			List<BimrRiskEventRelevance> relevanceList=new ArrayList<BimrRiskEventRelevance>();
			if(relevanceList!=null){
				for(int i=0;i<leveloneid.length;i++){
					BimrRiskEventRelevance item=new BimrRiskEventRelevance();
					item.setEventid(entity.getId());
					
					if(leveloneid[i] !=null && !leveloneid[i].equals("-1")) 
						item.setLeveloneid(Integer.parseInt(leveloneid[i]));
					if (levelonename[i]!=null && !levelonename[i].equals("")) 
						item.setLevelonename(levelonename[i]);
					
					if(leveltwoid[i] !=null && !leveltwoid[i].equals("-1"))  
						item.setLeveltwoid(Integer.parseInt(leveltwoid[i]));
					if (leveltwoname[i]!=null && !leveltwoname[i].equals("")) 
						item.setLeveltwoname(leveltwoname[i]);
					
					if(levelthreeid[i] !=null && !levelthreeid[i].equals("-1")) 
						item.setLevelthreeid(Integer.parseInt(levelthreeid[i]));
					if (levelthreename[i]!=null && !levelthreename[i].equals("")) 
						item.setLevelthreename(levelthreename[i]);
					item.setIsDel(0);
					//关联时间
					item.setCreateDate(df.format(new Date()));
					relevanceList.add(item);
				}
			}
			
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			result= riskEventService.saveRiskEventRelevance(entity, use,relevanceList);
		}
		
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	@RequestMapping("relevanceExamine")
	public String relevanceExamine(BimrRiskEvent entity, Map<String, Object> map, HttpServletRequest request, String op) throws IOException {
		
		List<BimrRiskCatalog> ctgs1 = riskCatalogService.getRiskCatalogChildren(null);
		map.put("ctgs1", ctgs1);
		
		//采取措施
        List<HhBase> copingStrategy = baseService.getBases(Base.risk_event_copingStrategy);
		map.put("copingStrategy", copingStrategy);

		
		
		 //省份
        List<HhBase> province = baseService.getBases(Base.risk_event_province);
		map.put("province", province);
		
		List<BimrRiskEventRelevance> relevanceList=riskEventService.getRelevanceList(entity.getId());
		map.put("relevanceList", relevanceList);
		
		List<SysExamine> a=new ArrayList<SysExamine>();
		a= examineService.getListExamine(entity.getId(), Base.examkind_risk_Relevance);
	    map.put("entityExamineviewlist", a);
		map.put("entity", entity);
		// 路径
		String mapurl = request.getContextPath() + "/bimr/riskEvent";
		map.put("mapurl", mapurl);
		map.put("op", op);
		//map.put("organalID", organalID);
		return "/bimr/riskevent/riskEventRelevanceExamine";
	}
	
	//审核提交
	@ResponseBody
	@RequestMapping(value ="/commitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _commitexam(BimrRiskEvent entity,String examStr,Integer examResult,HttpServletRequest request ,Map<String, Object> map,String op) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");

		CommitResult result= riskEventService.saveRelevanceExamine(entity, examStr, examResult, use);
		if(result.isResult())
		{
			potalMsgService.updatePortalMsg("bimr_risk_event_relevance", entity.getId().toString());
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	//风险事件上报功能
	@ResponseBody
	@RequestMapping(value ="/updatestauts", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String updatestauts(BimrRiskEvent entity,Integer stauts,HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");

		CommitResult result= riskEventService.updatestauts(entity,stauts,use);
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	//风险 事件审核 跟踪审核
	@RequestMapping("riskEventExamine")
	public String riskEventExamine(BimrRiskEvent entity,Integer trackid,Integer titletype, Map<String, Object> map, HttpServletRequest request, String op) throws IOException {
		
		List<BimrRiskCatalog> ctgs1 = riskCatalogService.getRiskCatalogChildren(null);
		map.put("ctgs1", ctgs1);
		
		//采取措施
        List<HhBase> copingStrategy = baseService.getBases(Base.risk_event_copingStrategy);
		map.put("copingStrategy", copingStrategy);

		//处理方式
        List<HhBase> handleway = baseService.getBases(Base.risk_event_handleway);
		map.put("handleway", handleway);
		
		 //省份
        List<HhBase> province = baseService.getBases(Base.risk_event_province);
		map.put("province", province);
		
		List<BimrRiskEventRelevance> relevanceList=riskEventService.getRelevanceList(entity.getId());
		map.put("relevanceList", relevanceList);
		
		List<BimrRiskEventAdoptstrategy> adoptStrategyList=riskEventService.getAdoptstrategy(entity.getId());
		map.put("adoptStrategyList", adoptStrategyList);
		
		List<SysExamine> a=new ArrayList<SysExamine>();
		if (op.equals("event")) {//如果是风险事件
			a= examineService.getListExamine(entity.getId(), Base.examkind_risk_event);
		}else {														//如果是风险跟踪
			
			BimrRiskEventRelevancetrack track = new BimrRiskEventRelevancetrack();
			if (trackid !=null) {
				 track = riskEventService.getBimrRiskEventRelevancetrack(trackid);
				 a= examineService.getListExamine(track.getId(), Base.examkind_risk_track);
			}
			
			 map.put("track",track);
		}
		map.put("titletype", titletype);
	    map.put("entityExamineviewlist", a);
		map.put("entity", entity);
		// 路径
		String mapurl = request.getContextPath() + "/bimr/riskEvent";
		map.put("mapurl", mapurl);
		map.put("op", op);
		//map.put("organalID", organalID);
		if(null!=titletype){
			return "/bimr/riskevent/riskEventAddPersonOrMeasure";
		}
		return "/bimr/riskevent/riskEventExamine";
	}
	
	
	
	//审核提交
	@ResponseBody
	@RequestMapping(value ="/riskeventcommitexam", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String riskeventcommitexam(BimrRiskEvent entity,
			String[] adoptStrategy,String[] responsibleCompName,String[] planfinishtime,
			String examStr,Integer examResult,String type,Integer trackid,
			HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		List<BimrRiskEventAdoptstrategy> addoptstrategyList=new ArrayList<BimrRiskEventAdoptstrategy>();
		if (entity != null) {
			
			if(adoptStrategy!=null){
				for(int i=0;i<adoptStrategy.length;i++){
					if(adoptStrategy[i] !=null){
						BimrRiskEventAdoptstrategy addoptstrategy=new BimrRiskEventAdoptstrategy();
						addoptstrategy.setAdoptStrategyname(adoptStrategy[i]);
						addoptstrategy.setPlanfinishtime(planfinishtime[i]);
						addoptstrategy.setResponsibleCompName(responsibleCompName[i]);
						addoptstrategyList.add(addoptstrategy);
					}
				}
			}
		}
		BimrRiskEventRelevancetrack track = null;
		if (trackid !=null) {
			 track = riskEventService.getBimrRiskEventRelevancetrack(trackid);
		}
		CommitResult result= riskEventService.saveriskeventExamine(entity,addoptstrategyList,examStr,examResult, use,type,track);
		if(result.isResult())
		{
			potalMsgService.updatePortalMsg("bimr_risk_event", entity.getId().toString());
		}
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
	//风险 跟踪上报页面
	@RequestMapping("trackaddList")
	public String trackaddList(BimrRiskEvent entity, Map<String, Object> map, HttpServletRequest request) throws IOException {
		
		List<BimrRiskEventRelevancetrack> relevancetrackList=riskEventService.getRelevancetrackList(entity.getId());
		map.put("relevancetrackList", relevancetrackList);
		map.put("entity", entity);
		// 路径
		String mapurl = request.getContextPath() + "/bimr/riskEvent";
		map.put("mapurl", mapurl);
		//map.put("organalID", organalID);
		return "/bimr/riskevent/riskEventTrackAddList";
	}
	
	//风险 跟踪上报页面里面的新增
	@RequestMapping("trackadd")
	public String trackadd(BimrRiskEvent entity,Integer trackid, Map<String, Object> map, HttpServletRequest request) throws IOException {
		
		List<BimrRiskEventAdoptstrategy> adoptStrategyList=riskEventService.getAdoptstrategy(entity.getId());
		map.put("adoptStrategyList", adoptStrategyList);
		BimrRiskEventRelevancetrack track = new BimrRiskEventRelevancetrack();
		if (trackid !=null) {
			 track = riskEventService.getBimrRiskEventRelevancetrack(trackid);
		}
		map.put("entity", entity);
		map.put("track", track);
		// 路径
		String mapurl = request.getContextPath() + "/bimr/riskEvent";
		map.put("mapurl", mapurl);
		//map.put("organalID", organalID);
		return "/bimr/riskevent/riskEventTrackAdd";
	}
	
	//跟踪上报的保存功能
	@ResponseBody
	@RequestMapping(value ="/savetrack", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String savetrack(BimrRiskEvent entity,Integer trackid,Integer ts,String tstxt,String tsremark, Boolean isSubmit,	
		HttpServletRequest request ,Map<String, Object> map) throws IOException {
		HttpSession session=request.getSession();
		HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
		BimrRiskEventRelevancetrack track = new BimrRiskEventRelevancetrack();
		if (trackid !=null ) {
			track= riskEventService.getBimrRiskEventRelevancetrack(trackid);
		}
		
		track.setEventid(entity.getId());
		track.setTakestrategy(ts);
		track.setTakestrategytxt(tstxt);
		track.setTakestrategyremark(tsremark);
		track.setStatus(82103);//将跟踪小状态设为跟踪待上报
		track.setIsDel(0);
		CommitResult result= riskEventService.savetrack(track,use);
		String data="";
		data=JSONArray.toJSONString(result); 			
		return data;
	}
	
		//跟踪上报的上报功能
		@ResponseBody
		@RequestMapping(value ="/updatestautsfortrack", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
		public String updatestautsfortrack(BimrRiskEvent entity,Integer trackid,	HttpServletRequest request ,Map<String, Object> map) throws IOException {
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			BimrRiskEventRelevancetrack track = riskEventService.getBimrRiskEventRelevancetrack(trackid);
			if (track !=null) {
				track.setStatus(82104);//将跟踪小状态设为跟踪待审核
			}
			entity.setStatus(82108);//将事件大状态设为事件跟踪中
			CommitResult result= riskEventService.updatestautsfortrack(entity,track,use);
			String data="";
			data=JSONArray.toJSONString(result); 			
			return data;
		}
		
		//跟踪结束
		@ResponseBody
		@RequestMapping(value ="/saveistrackover", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
		public String saveistrackover(BimrRiskEvent entity,HttpServletRequest request ,Map<String, Object> map) throws IOException {
			HttpSession session=request.getSession();
			HhUsers use=(HhUsers) session.getAttribute("gzwsession_users");
			CommitResult result= riskEventService.saveistrackover(entity,use);
			String data="";
			data=JSONArray.toJSONString(result); 			
			return data;
		}
	
		@ResponseBody
		@RequestMapping(value ="/deletetrack", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
		public String deletetrack(Integer trackid, HttpServletRequest request) throws IOException {
			HttpSession session = request.getSession();
			HhUsers use = (HhUsers) session.getAttribute("gzwsession_users");
			BimrRiskEventRelevancetrack track = new BimrRiskEventRelevancetrack();
			if (trackid !=null ) {
				track= riskEventService.getBimrRiskEventRelevancetrack(trackid);
			}
			CommitResult result=riskEventService.deleteTrack(track, use);
			if(result.isResult())
				potalMsgService.updatePortalMsg("bimr_risk_event_relevancetrack", track.getId().toString());
			String data="";
			data=JSONArray.toJSONString(result); 
			return data;
		}
		
		//风险 跟踪上报页面里面的查看
		@RequestMapping("trackView")
		public String trackView(Integer trackid, Map<String, Object> map, HttpServletRequest request) throws IOException {
			
			BimrRiskEventRelevancetrack track = new BimrRiskEventRelevancetrack();
			if (trackid !=null ) {
				track= riskEventService.getBimrRiskEventRelevancetrack(trackid);
			}
			map.put("track", track);
			List<BimrRiskEventAdoptstrategy> adoptStrategyList=riskEventService.getAdoptstrategy(track.getEventid());
			map.put("adoptStrategyList", adoptStrategyList);
			List<SysExamine> a=new ArrayList<SysExamine>();
			a= examineService.getListExamine(track.getId(), Base.examkind_risk_track);
		    map.put("entityExamineviewlist", a);
			return "/bimr/riskevent/riskEventTrackView";
		}
	
	/**
	 * 风险管理二级风险TOP排名
	 * @param entity
	 * @param type
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("secondlist")
	public String secondlist(BimrRiskEventRelevance entity,String type,HttpServletRequest request, Map<String, Object> map){

		HttpSession session = request.getSession();
		String company = (String)session.getAttribute("gzwsession_company");
        
        //获取数据权限
        String dataauth=systemService.getDataauth(company);
        entity.setDataauth(dataauth);
		
		// 当前页码
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage = riskEventService.getRiskEventSecondList(entity, pageNum, Base.pagesize);
		map.put("msgPage", msgPage);
		// 路径
		String mapurl = request.getContextPath() + "/bimr/riskEvent";
		map.put("mapurl", mapurl);
		map.put("entity", entity);
		map.put("op", type);
		map.put("searchurl", request.getContextPath() + "/bimr/riskEvent/secondlist");
			return "/bimr/riskevent/riskEventSecondTopList";
	}
	
	/**
	 * 查看风险事件列表
	 * @param entity
	 * @param type
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("secondlistforDetail")
	public String secondlistforDetail(BimrRiskEventRelevance entity,HttpServletRequest request, Map<String, Object> map){

		HttpSession session = request.getSession();
		String company = (String)session.getAttribute("gzwsession_company");
        
        //获取数据权限
        String dataauth=systemService.getDataauth(company);
        entity.setDataauth(dataauth);
		
		// 当前页码
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage = riskEventService.getRiskEventSecondListforDetail(entity, pageNum, Base.pagesize);
		map.put("msgPage", msgPage);
		// 路径
		String mapurl = request.getContextPath() + "/bimr/riskEvent";
		map.put("mapurl", mapurl);
		map.put("entity", entity);
		map.put("searchurl", request.getContextPath() + "/bimr/riskEvent/secondlistforDetail");
			return "/bimr/riskevent/riskEventTopListforDetail";
	}
}
