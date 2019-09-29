package com.softline.controller.bimr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.softline.common.Base;
import com.softline.common.CompanyTree;
import com.softline.entity.HhBase;
import com.softline.entity.HhUsers;
import com.softline.entity.SysExamine;
import com.softline.entity.bimr.BimrRiskCatalog;
import com.softline.entity.bimr.BimrRiskEvent;
import com.softline.entityTemp.CommitResult;
import com.softline.service.bimr.IRiskCatalogService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.IBaseService;
import com.softline.service.system.IExamineService;
import com.softline.util.MsgPage;

/**
 * 风险目录显示控制
 * 
 * @author liu
 */
@Controller
@RequestMapping("/bimr/riskCatalog")
public class RiskCatalogController {

	@Autowired
	private IRiskCatalogService riskCatalogService;
	
	@Autowired
	@Qualifier("examineService")
	private IExamineService examineService;	
	@Resource(name = "selectUserService")
    private ISelectUserService selectUserService;
	@Resource(name = "baseService")
	private IBaseService baseService;
	@RequestMapping("list")
	public String getRiskCatalogList(
			@RequestParam(required = false) String name, @RequestParam(required = false) Integer status,
			HttpServletRequest request, Map<String, Object> map){
		
		// 当前页码
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage = riskCatalogService.getRiskCatalogListView(name, status, pageNum, Base.pagesize);
		map.put("msgPage", msgPage);
		// 路径
		String mapurl = request.getContextPath() + "/bimr/riskCatalog";
		map.put("mapurl", mapurl);
		map.put("name", name);
		map.put("status", status);
		map.put("searchurl", request.getContextPath() + "/bimr/riskCatalog/list");
				
		return "/bimr/riskctg/riskCatalogList";
	}
	
	@RequestMapping(value = "view")
	public String getRiskCatalogView(@RequestParam Integer id, 
			@RequestParam(defaultValue = "view")String type, HttpServletRequest request, Map<String, Object> map){
		
		List<BimrRiskCatalog> entities = new ArrayList<BimrRiskCatalog>(3);
		buildRiskCatalogFull(entities, id);
		map.put("entities", entities);
		map.put("entitiesCount", entities.size());
		String mapurl = request.getContextPath() + "/bimr/riskCatalog";
		map.put("mapurl", mapurl);
		List<SysExamine> auditList = examineService.getListExamine(id, Base.examkind_risk_catalog);
		map.put("auditList", auditList);
		
		String page = "/bimr/riskctg/riskCatalogView";
		if("submit".equals(type)){
			page = "/bimr/riskctg/riskCatalogSubmit";
		}
		if("audit".equals(type)){
			page = "/bimr/riskctg/riskCatalogAuditEdit";
		}
		return page;
	}
	
	private void buildRiskCatalogFull(List<BimrRiskCatalog> entities, Integer id){
		BimrRiskCatalog t = riskCatalogService.getRiskCatalog(id);
		if(t == null){
			return ;
		}
		
		entities.add(0, t);
		if(t.getParentId() != null){
			buildRiskCatalogFull(entities, t.getParentId());
		}
	}
	
	@RequestMapping(value="getChildren", method= RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public CommitResult getChildren(@RequestParam(required = false)Integer parentId){
		CommitResult result = new CommitResult();
		
		result.setResult(true);
		result.setEntity(riskCatalogService.getRiskCatalogChildren(parentId));
		return result;
	}
	
	@RequestMapping("edit")
	public String edit(Integer level, String op,Integer id,HttpServletRequest request, Map<String, Object> map){
		map.put("op", op);
		if(level != null){
			if(level != 2){
				level =3;
			}
		}
		BimrRiskCatalog t = new BimrRiskCatalog();
		List<BimrRiskCatalog> entities = new ArrayList<BimrRiskCatalog>();
		if(id != null){
			t = riskCatalogService.getRiskCatalog(id);
			entities = new ArrayList<BimrRiskCatalog>(3);
			buildRiskCatalogFull(entities, id);
			if(t != null){
				level=t.getLevel();
				map.put("entityview", t);
			}
			if(entities.size()==3){
				map.put("entity", entities.get(2).getParentId());
				map.put("entityId", entities.get(2).getId());
			}else{
				map.put("entityId", entities.get(1).getId());
			}
			map.put("entities", entities);
			map.put("entitiesCount", entities.size());
		}
		
		
		map.put("level", level);
		List<BimrRiskCatalog> ctgs1 = riskCatalogService.getRiskCatalogChildren(null);
		map.put("ctgs1", ctgs1);
		String mapurl = request.getContextPath() + "/bimr/riskCatalog";
		map.put("mapurl", mapurl);
		
		return "/bimr/riskctg/riskCatalogEdit";
	}
//	@RequestMapping("modify")
//	public String edit1(@RequestParam Integer level, 
//			HttpServletRequest request, Map<String, Object> map){
//		
//		if(level != 2){
//			level =3;
//		}
//		
//		map.put("level", level);
//		List<BimrRiskCatalog> ctgs1 = riskCatalogService.getRiskCatalogChildren(null);
//		map.put("ctgs1", ctgs1);
//		String mapurl = request.getContextPath() + "/bimr/riskCatalog";
//		map.put("mapurl", mapurl);
//		
//		return "/bimr/riskctg/riskCataModify";
//	}
//	
	@RequestMapping(value="save", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public CommitResult save(BimrRiskCatalog t, Boolean isSubmitAudit, HttpServletRequest request){
		HttpSession session=request.getSession();
		HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
		if(t.getId() != null){
			BimrRiskCatalog b = riskCatalogService.getRiskCatalog(t.getId());
			b.setDefine(t.getDefine());
			b.setName(t.getName());
			b.setParentId(t.getParentId());
			t = b;
		}
		
		return riskCatalogService.saveRiskCatalog(t, isSubmitAudit, user);
	}
	
	
	@RequestMapping(value = "submitAudit", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public CommitResult submitAudit(@RequestParam Integer id, HttpServletRequest request){
		HttpSession session=request.getSession();
		HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
		return riskCatalogService.submitAudit(id, user);
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public CommitResult delete(@RequestParam Integer id, HttpServletRequest request) {
		HttpSession session=request.getSession();
		HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
		return riskCatalogService.delete(id, user);
	}
	
	@RequestMapping("auditList")
	public String getRiskCatalogAuditList(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String define,
			@RequestParam(required = false) Integer status,
			HttpServletRequest request, Map<String, Object> map){
		
		// 当前页码
		String curPageNum = request.getParameter("pageNums");
		if (curPageNum == null) {
			curPageNum = "1";
		}
		Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage = riskCatalogService.getRiskCatalogAudidtListView(name, status, define, pageNum, Base.pagesize);
		map.put("msgPage", msgPage);
		// 路径
		String mapurl = request.getContextPath() + "/bimr/riskCatalog";
		map.put("mapurl", mapurl);
		map.put("name", name);
		map.put("status", status);
		map.put("searchurl", request.getContextPath() + "/bimr/riskCatalog/auditList");
				
		return "/bimr/riskctg/riskCatalogAuditList";
	}
	
	@RequestMapping(value="audit", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public CommitResult audit(@RequestParam Integer id, @RequestParam Boolean isPass, 
			@RequestParam String content, HttpServletRequest request) {
		
		HttpSession session=request.getSession();
		HhUsers user=(HhUsers) session.getAttribute("gzwsession_users");
		return riskCatalogService.audit(id, content, isPass ? 1 : 0, user);
	}
	
	@RequestMapping("treeView")
	public String treeView(){
		return "/bimr/riskctg/riskCatalogTree";
	}
	
	@RequestMapping(value="getTree", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public CommitResult getTree(@RequestParam(required = false) Integer parentId, @RequestParam(defaultValue="false")Boolean isFull){
		CommitResult result = new CommitResult();
		
		List<CompanyTree> nodes = new ArrayList<CompanyTree>();
		for(BimrRiskCatalog t: riskCatalogService.getRiskCatalogChildren(parentId)){
			if(isFull){
				CompanyTree node = convertNode(t);
				buildFullNode(node);
				nodes.add(node);
			}else{
				nodes.add(convertNode(t));
			}
		}
		result.setResult(true);
		result.setEntity(nodes);
		return result;
	}
	
	private void buildFullNode(CompanyTree node){
		Integer parentId = Integer.valueOf(node.getId());
		for(BimrRiskCatalog t: riskCatalogService.getRiskCatalogChildren(parentId)){
			CompanyTree child = convertNode(t);
			node.children.add(child);
			if(t.getLevel() < 3){
				buildFullNode(child);
			}
		}
	}
	
	private CompanyTree convertNode(BimrRiskCatalog c){
		CompanyTree t = new  CompanyTree();
		
		t.setId(String.valueOf(c.getId()));
		t.setName(c.getName());
		t.setpId(c.getParentId() == null? null :String.valueOf(c.getParentId()));
		
		return t;
	}
	
	@RequestMapping(value="get", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public CommitResult get(@RequestParam Integer id) {
		CommitResult result = new CommitResult();
		
		BimrRiskCatalog t = riskCatalogService.getRiskCatalog(id);
		if(t== null){
			result.setResult(false);
			result.setExceptionStr("风险目录不存在");
		}else{
			result.setResult(true);
			result.setEntity(t);
		}
		
		return result;
	}
//	
//	@RequestMapping("addOrModify")
//	public String addOrModify(BimrRiskEvent entity,int id, Map<String, Object> map, HttpServletRequest request, String op) throws IOException {
//		
//		
//		BimrRiskCatalog t = riskCatalogService.getRiskCatalog(id);
//		
//		
//		
//		//公司树
//		HttpSession session = request.getSession();
//		String company = (String)session.getAttribute("gzwsession_company");
//        map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(company)));
//        
//        //采取措施
//        List<HhBase> copingStrategy = baseService.getBases(Base.risk_event_copingStrategy);
//		map.put("copingStrategy", copingStrategy);
//		
//		//处理方式examkind_risk_catalog
//        List<HhBase> handleway = baseService.getBases(Base.risk_event_handleway);
//		map.put("handleway", handleway);
//		
//		 //省份
//        List<HhBase> province = baseService.getBases(Base.risk_event_province);
//		map.put("province", province);
//		
///*		List<BimrRiskEventAdoptstrategy> adoptStrategyList=riskEventService.getAdoptstrategy(entity.getId());
//		map.put("adoptStrategyList", adoptStrategyList);*/
//		
//		map.put("op", op);
//		
//		map.put("entity", entity);
//		
//		
//		//map.put("organalID", organalID);
//		return "/bimr/riskCatalog/riskCatalogEdit";
//	}
}
