  package com.softline.controller.bylaw;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.common.CompanyTree;
import com.softline.entity.HhUsers;
import com.softline.entity.bylaw.BylawDept;
import com.softline.entity.bylaw.BylawInfo;
import com.softline.entity.bylaw.BylawInfoSynRecord;
import com.softline.entity.bylaw.BylawSynLock;
import com.softline.service.bylaw.IBylawDeptService;
import com.softline.service.bylaw.IBylawInfoService;
import com.softline.service.select.ISelectUserService;
import com.softline.service.system.ICommonService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Controller
@RequestMapping("/bylaw")
public class BylawController {
	
	@Resource(name = "bylawDeptService")
	private IBylawDeptService bylawDeptService;
	
	@Resource(name = "bylawInfoService")
	private IBylawInfoService bylawInfoService;
	
	@Resource(name = "commonService")
	private ICommonService commonService;
	
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	/**
	 * 获取列表信息
	 * @param condition
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping("bylawInfoList")
	public String getBylawInfoList(BylawInfo condition,Map<String ,Object> map, HttpServletRequest request){
		String curPageNum = request.getParameter("pageNums");
		HttpSession session=request.getSession();
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        //数据权限
        String str=(String) session.getAttribute("gzwsession_company");
        map.put("allCompanyTree", JSON.toJSONString(selectUserService.getHrOrganal(str)));
        String dataauth=systemService.getDataauth(str);
        if (Common.notEmpty(condition.getOrg())) {
        	if (condition.getOrg().split(",").length==1) {
        		condition.setOrg(systemService.getDataauth(condition.getOrg()));
			}
        }
        Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage =  bylawInfoService.getBylawInfos(dataauth,condition, pageNum, Base.pagesize);
		map.put("bylawInfo", condition);
		map.put("msgPage", msgPage);
		map.put("searchurl", request.getContextPath()+"/bylaw/bylawInfoList");
		return "/bylaw/bylawInfoList";
	}
	
	/**
	 * 进入详细页面
	 * @param id
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping("bylawDetail")
	public String getBylawInfoDetail(Integer id,Map<String ,Object> map, HttpServletRequest request){
		BylawInfo bylawInfo = (BylawInfo) commonService.findById(BylawInfo.class, id);
		if(bylawInfo.getIsLinked() != null && bylawInfo.getIsLinked() == 1)
			bylawInfo.setLinkDiscription(getLinkDiscription(bylawInfo, ""));
		map.put("bylawInfo", bylawInfo);
		return "/bylaw/bylawInfoDetail";
	}
	
	/**
	 * 进入新增编辑
	 * @param id
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping("bylawInfoEdit")
	public String bylawInfoEdit(Integer id,Map<String ,Object> map, HttpServletRequest request){
		BylawInfo bylawInfo = (BylawInfo) commonService.findById(BylawInfo.class, id);
		List<BylawDept> bylawDepts = bylawDeptService.getAllBylawDept();
		map.put("bylawInfo", bylawInfo);
		map.put("bylawDepts", bylawDepts);
		return "/bylaw/bylawInfoModify";
	}
	
	/**
	 * 替换现有关联
	 * @param id
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping("bylawChangeLink")
	public String bylawChangeLink(Integer id,Map<String ,Object> map, HttpServletRequest request){
		BylawInfo bylawInfo = (BylawInfo) commonService.findById(BylawInfo.class, id);
		//获取可以替换的规章制度
		List<BylawInfo> canChangeBylaws = bylawInfoService.getCanChangedBylawInfos(bylawInfo.getOrg());
		map.put("canChangeBylaws", canChangeBylaws);
		map.put("bylawInfo", bylawInfo);
		return "/bylaw/bylawChangeLink";
	}
	
	
	
	/**
	 * 替换现有关联
	 * @param id
	 * @param map
	 * @param request
	 * @param changeBylawInfoId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("changeLinkSave")
	public String changeLinkSave(String changeBylawInfoId,Integer id,Map<String ,Object> map, HttpServletRequest request){
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		return bylawInfoService.saveChangeLink(id, changeBylawInfoId,user);
	}
	
	/**
	 * 保存规章制度
	 * @param bylawInfo
	 * @param map
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("bylawclearLink")
	public String bylawclearLink(Integer id,Map<String ,Object> map, HttpServletRequest request){
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		return bylawInfoService.saveChangeLink(id, "jb",user);
	}
	
	
	/**
	 * 保存规章制度
	 * @param bylawInfo
	 * @param map
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("bylawInfoSave")
	public String bylawInfoSave(BylawInfo bylawInfo,Map<String ,Object> map, HttpServletRequest request){
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		bylawInfoService.saveBylawinfo(bylawInfo, user);
		return "success";
	}
	
	/**
	 * 编辑页面，选择关联的规章制度
	 * @param bylawInfo
	 * @param map
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "bylawInfos" , produces = "application/json;charset=UTF-8")
	public String bylawInfos(BylawInfo bylawInfo,Map<String ,Object> map, HttpServletRequest request){
		HttpSession session=request.getSession();
		bylawInfo.setFileLevel(bylawInfo.getFileLevel() - 1 );
		String str=(String) session.getAttribute("gzwsession_company");
	    String dataauth=systemService.getDataauth(str);
		List<BylawInfo> list = bylawInfoService.getBylawInfos(dataauth,bylawInfo);
		return JSON.toJSONString(list);
	}
	
	/**
	 * 已经建立了关联的规章制度调用该方法
	 * 递归拼接建立了关联的规章制度的描述
	 * @param bylawInfo 规章制度实体
	 * @return
	 */
	private String getLinkDiscription(BylawInfo bylawInfo,String result){
		if(bylawInfo.getParentId() == null && bylawInfo.getFileLevel()!= null && bylawInfo.getFileLevel() == 1){
			if(Common.notEmpty(result)){
				result = bylawInfo.getOrgNm()+"-->"+bylawInfo.getBylawTitle()+"-->"+result;
			}else{
				result = bylawInfo.getOrgNm()+"-->"+bylawInfo.getBylawTitle();
			}
		}else{
			if(bylawInfo.getParentId() != null){
				BylawInfo parent = (BylawInfo) commonService.findById(BylawInfo.class, bylawInfo.getParentId());
				if(Common.notEmpty(result)){
					result = bylawInfo.getBylawTitle() + "-->" + result;
				}else{
					result = bylawInfo.getBylawTitle();
				}
				if (null!=parent) {
					result = getLinkDiscription(parent, result);
				}
			}
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "synBylawInfo" , produces = "text/plain;charset=UTF-8")
	public String synBylawInfo(HttpServletRequest request){
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		return bylawInfoService.xsaveSynBylawInfo(user);
	}
	
	
	@RequestMapping("synRecords")
	public String getSynRecords(BylawInfoSynRecord  condition,Map<String ,Object> map, HttpServletRequest request){
		String curPageNum = request.getParameter("pageNums");
        if (curPageNum == null) {
        	curPageNum = "1";
        }
        Integer pageNum = Integer.valueOf(curPageNum);
		MsgPage msgPage =  bylawInfoService.getBylawInfoSynRecords(condition, pageNum, Base.pagesize);
		map.put("bylawInfoSynRecord", condition);
		map.put("msgPage", msgPage);
		map.put("searchurl", request.getContextPath()+"/bylaw/synRecords");
		return "/bylaw/bylawInfoSynRecords";
	}
	
	
	@RequestMapping("bylawInfoTree")
	public String bylawInfoTree(Map<String ,Object> map){
//		CompanyTree leveltree = selectUserService.getHRTree();
//		leveltree.setIcon("active");
//		String shtml = getTreeHTML(leveltree);
		String shtml = bylawInfoService.getBylawInfoTreeHtml();
//		System.out.println(shtml);
		map.put("shtml", shtml);
		
		return "/bylaw/bylawInfoTree";
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
	
	/**
	 * 在规章制度树中进入详细页面
	 * @param id
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping("treeBylawInfoDetail")
	public String treeBylawInfoDetail(Integer id,Map<String ,Object> map, HttpServletRequest request){
		BylawInfo bylawInfo = (BylawInfo) commonService.findById(BylawInfo.class, id);
		if(bylawInfo.getIsLinked() != null && bylawInfo.getIsLinked() == 1)
			bylawInfo.setLinkDiscription(getLinkDiscription(bylawInfo, ""));
		map.put("bylawInfo", bylawInfo);
		return "/bylaw/treebylawInfoDetail";
	}
	
	/**
	 * 进入同步规章制度页面，选择同步时间，进行同步操作
	 * @return
	 */
	@RequestMapping("synPage")
	public String synBylawInfoPage(){
		return "/bylaw/synPage";
	}
	
	/**
	 * 按年份同步数据
	 * @param year
	 * @param map
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "opSynInYear",produces = "text/plain;charset=UTF-8")
	public String opSynInYear(String year,Map<String ,Object> map, HttpServletRequest request){
		BylawSynLock lock = (BylawSynLock) commonService.findById(BylawSynLock.class, 1);
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		if(lock.getStatus() == 0){
			try{
				lock.setStatus(1);
				commonService.saveOrUpdate(lock);
				bylawInfoService.saveSynBylawInfoInYar(year, user);
			}finally{
				lock.setStatus(0);
				commonService.saveOrUpdate(lock);
			}
			return "success";
		}else{
			return "dealing";//处理中
		}
	}
	
}
