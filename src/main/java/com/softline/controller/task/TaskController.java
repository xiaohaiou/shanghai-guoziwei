package com.softline.controller.task;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.softline.client.task.ITaskWebservice;
import com.softline.client.task.TaskInstruction;
import com.softline.client.task.TaskProgress;
import com.softline.client.task.TaskWebserviceService;
import com.softline.common.Base;
import com.softline.common.CompanyTree;
import com.softline.entity.HhUsers;
import com.softline.entity.SysUsers;
import com.softline.service.select.ISelectUserService;
import com.softline.service.task.ITaskService;

@Controller
@RequestMapping("/task/instruction")
public class TaskController {
	@Resource(name = "taskService")
	private ITaskService taskService;
	
	@Resource(name = "selectUserService")
	private ISelectUserService selectUserService;
	
	@ResponseBody
	@RequestMapping(value = "/_getTask", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String _getTask(HttpServletResponse response, HttpServletRequest request) throws IOException { 
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		TaskWebserviceService service = new TaskWebserviceService();
		Date date = new Date();
		String jsonString = "";
		ITaskWebservice it = service.getTaskWebservicePort();
		String un = it.getTaskInsructionList(user.getVcEmployeeId());
		JSONArray arr=JSON.parseArray(un);
		List taskList = new ArrayList();
		for(int i=0;i<arr.size();i++){
			TaskInstruction ti = new TaskInstruction();
			ti = JSON.parseObject(arr.get(i).toString(), TaskInstruction.class);
			taskList.add(ti);
		}
		if((!taskList.isEmpty()) && taskList.size()>0){
			StringBuffer taskListStr = new StringBuffer();
			for (int i = 0; i < taskList.size(); i++) {
				TaskInstruction temp = (TaskInstruction)taskList.get(i);
				DecimalFormat df = new DecimalFormat("##0");
				String jd = df.format(temp.getTaskCompletion()==null?0.0:temp.getTaskCompletion());
				TaskProgress progress = it.getFormalProgress(temp.getId());
				taskListStr.append("<div class=\"module_list\">");
				//part1
				taskListStr.append("<div class=\"list_avater\">");
				taskListStr.append("<a><img src=\"http://file.hna.net/image/headimage/emp/"+user.getVcEmployeeId().substring(0,6)+"0000/"+user.getVcEmployeeId()+".jpg\" height=\"102\" width=\"104\" onerror=\"to_def_img(this)\" title=\""+temp.getCreatorName()+"\"> </a>");
				taskListStr.append("</div>");
				//part2
				taskListStr.append("<div class=\"list_text\">");
				taskListStr.append("<p class=\"list_title\">");
				taskListStr.append("<span><a class=\"a_title\">"+temp.getTaskName()+"</a></span>");
				taskListStr.append("<i class=\"iconfont icon-shijian\"></i>");
				taskListStr.append("<span class=\"format\">"+progress.getCreatorTime()+"</span>");
				taskListStr.append("</p>");
				taskListStr.append("<p class=\"list_body\">");
				taskListStr.append("<a class=\"list_name\">"+progress.getCreateName()+"说：</a>");
				taskListStr.append("<span><a class=\"a_content\">"+progress.getProgressRemark()+"</a></span>");
				taskListStr.append("</p>");
				taskListStr.append("</div>");
				//part3
				taskListStr.append("<div class=\"list_progress\">");
//				taskListStr.append("<p>任务进度："+jd+"%</p>");
//				taskListStr.append("<p></p>");
//				taskListStr.append("<div class=\"progress_bar\"><div style=\"width:"+jd+"%\"></div>");
//				taskListStr.append("<div class=\"progress_bar\"><div style=\"width:"+jd+"%\"></div>");
//				taskListStr.append("</div>");
				taskListStr.append("<p>任务截至时间："+temp.getDeadLine()+"</p>");
				taskListStr.append("</div>");
				//part4
				taskListStr.append("<div class=\"list_btn\">");
				taskListStr.append("<a  href=\"javascript:;\" onclick=\"openNewWindow('"+request.getContextPath() +"/ssoout/outsystem?isopen=true&url=/bim_task/system/indexFirst?fromsystem=detail%26taskId="
									+ temp.getId() + "')\"><span>任务处置</span></a>");
				taskListStr.append("</div>");
				//end
				taskListStr.append("</div>");
			}
			jsonString = taskListStr.toString();
		}
		return jsonString;
	}
	
	@RequestMapping("initTaskPart")
	public String initTaskPart(HttpServletRequest request,Map<String, Object> map){
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		TaskWebserviceService service = new TaskWebserviceService();
		ITaskWebservice it = service.getTaskWebservicePort();
		
		TaskInstruction cond1 = new TaskInstruction();
		List<TaskInstruction> results1= it.getInstructionList01(cond1, user.getVcEmployeeId());
		map.put("num1", results1.size());
		
		TaskInstruction cond2 = new TaskInstruction();
		cond2.setIsOvertime(1);
		cond2.setIsTerminate(0);
		List<TaskInstruction> results2= it.getInstructionList01(cond2, user.getVcEmployeeId());
		map.put("num2", results2.size());
		
		
		List<TaskInstruction> results3= it.getTaskList01(cond1, user.getVcEmployeeId());
		map.put("num3", results3.size());
		
		List<TaskInstruction> results4= it.getTaskList01(cond2, user.getVcEmployeeId());
		map.put("num4", results4.size());
		
		return "workbench/taskPart";
	}
	
	@RequestMapping("taskList")
	public String taskList(TaskInstruction condition,HttpServletRequest request,Map<String, Object> map,Integer num,Integer pageNum){
		HhUsers user = (HhUsers) request.getSession().getAttribute("gzwsession_users");
		TaskWebserviceService service = new TaskWebserviceService();
		ITaskWebservice it = service.getTaskWebservicePort();

		String title = "";
		List<TaskInstruction> results = new ArrayList<TaskInstruction>();
		if(num == 1){
			results= it.getInstructionList01(condition, user.getVcEmployeeId());
			title = "已发送指令数";
		}else if(num == 2){
			condition.setIsOvertime(1);
			condition.setIsTerminate(0);
			results= it.getInstructionList01(condition, user.getVcEmployeeId());
			title = "逾期指令数";
		}else if(num == 3){
			results= it.getTaskList01(condition, user.getVcEmployeeId());
			title = "接收指令/任务数";
		}else if(num == 4){
			condition.setIsOvertime(1);
			condition.setIsTerminate(0);
			results= it.getTaskList01(condition, user.getVcEmployeeId());
			title = "逾期任务数";
		}
		if(pageNum == null){
			pageNum = 1;
		}
		int totalRecords = results.size();
		map.put("totalRecords", totalRecords);
		map.put("totalPage", getTotalCount(totalRecords));
		Integer start = (pageNum-1) *10;
		Integer end = start + 10;
		if(end.intValue() >  totalRecords){
			end = results.size();
		}
		results = results.subList(start, end);
		for(int i = 0; i < results.size(); i++){
			TaskInstruction temp = results.get(i);
			TaskProgress progress = it.getFormalProgress(temp.getId());
			temp.setTaskProgress(progress);
		}
		map.put("pageNum", pageNum);
		map.put("results", results); 
		map.put("title",title);
		map.put("instruction",condition);
		map.put("num",num);
		map.put("searchUrl", request.getContextPath()+"/task/instruction/taskList?1=1");
		return "workbench/showTasks";
	}
	
	/**
	 * 计算总页数
	 * @param total
	 * @return
	 */
	private Integer getTotalCount(Integer total){
		Integer result = total/10;
		Integer temp = total%10;
		if(temp > 0){
			result = result + 1;
		}
		return result;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/_searchPerson", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public void _searchPerson(String id,  String name, HttpServletResponse response, HttpServletRequest request) throws IOException { 
		List<HhUsers> selectPersonList = selectUserService.getAllPerson02(id, name);
		if((!selectPersonList.isEmpty())){
			StringBuffer hql = new StringBuffer();
			hql.append(" <ul>");
			for (HhUsers item : selectPersonList) {
				hql.append("<li> <a class=\"selectone\" id=\""+item.getVcEmployeeId()+"\" name=\""+item.getVcName()+"\" onclick=\"getUsers('"+item.getVcEmployeeId()+"','"+item.getVcAccount()+"','"+item.getVcName()+"','"+item.getVcFullName()+"')\" ><span account>"+item.getVcAccount()+"</span>&nbsp;<span name>"+item.getVcName()+"</span>&nbsp;<span fullName>"+item.getVcFullName()+"</span></a></li>");
			}
			hql.append(" </ul>");
			String jsonString = JSON.toJSONString(hql.toString());
			response.getWriter().write(jsonString);
		}
	}
	
	
	
}
