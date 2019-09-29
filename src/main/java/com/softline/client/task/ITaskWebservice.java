package com.softline.client.task;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.3-hudson-390-
 * Generated source version: 2.0
 * 
 */
@WebService(name = "ITaskWebservice", targetNamespace = "http://webService.softline.com/")
public interface ITaskWebservice {

	/**
	 * 
	 * @param arg1
	 * @param arg0
	 * @return returns java.lang.String
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "saveTaskInstruction", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.SaveTaskInstruction")
	@ResponseWrapper(localName = "saveTaskInstructionResponse", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.SaveTaskInstructionResponse")
	public String saveTaskInstruction(
			@WebParam(name = "arg0", targetNamespace = "") String arg0,
			@WebParam(name = "arg1", targetNamespace = "") String arg1);

	/**
	 * 
	 * @param arg0
	 * @return returns java.lang.String
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "updateHhPagecode", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.UpdateHhPagecode")
	@ResponseWrapper(localName = "updateHhPagecodeResponse", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.UpdateHhPagecodeResponse")
	public String updateHhPagecode(
			@WebParam(name = "arg0", targetNamespace = "") HhPagecode arg0);

	/**
	 * 
	 * @param arg1
	 * @param arg0
	 * @return returns java.lang.String
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "saveTaskInstruction1", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.SaveTaskInstruction1")
	@ResponseWrapper(localName = "saveTaskInstruction1Response", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.SaveTaskInstruction1Response")
	public String saveTaskInstruction1(
			@WebParam(name = "arg0", targetNamespace = "") TaskInstruction arg0,
			@WebParam(name = "arg1", targetNamespace = "") HhUsers arg1);

	/**
	 * 
	 * @param arg0
	 * @return returns com.softline.client.task.TaskProgress
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "getFormalProgress", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.GetFormalProgress")
	@ResponseWrapper(localName = "getFormalProgressResponse", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.GetFormalProgressResponse")
	public TaskProgress getFormalProgress(
			@WebParam(name = "arg0", targetNamespace = "") Integer arg0);

	/**
	 * 
	 * @param arg1
	 * @param arg0
	 * @return returns java.util.List<com.softline.client.task.TaskInstruction>
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "getTaskList01", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.GetTaskList01")
	@ResponseWrapper(localName = "getTaskList01Response", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.GetTaskList01Response")
	public List<TaskInstruction> getTaskList01(
			@WebParam(name = "arg0", targetNamespace = "") TaskInstruction arg0,
			@WebParam(name = "arg1", targetNamespace = "") String arg1);

	/**
	 * 
	 * @param arg0
	 * @return returns java.lang.String
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "getTaskInsructionList", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.GetTaskInsructionList")
	@ResponseWrapper(localName = "getTaskInsructionListResponse", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.GetTaskInsructionListResponse")
	public String getTaskInsructionList(
			@WebParam(name = "arg0", targetNamespace = "") String arg0);

	/**
	 * 
	 * @param arg0
	 * @return returns java.util.List<com.softline.client.task.TaskInstruction>
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "getTaskInstructionList1", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.GetTaskInstructionList1")
	@ResponseWrapper(localName = "getTaskInstructionList1Response", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.GetTaskInstructionList1Response")
	public List<TaskInstruction> getTaskInstructionList1(
			@WebParam(name = "arg0", targetNamespace = "") String arg0);

	/**
	 * 
	 * @param arg0
	 * @return returns java.lang.String
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "getInstructionList", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.GetInstructionList")
	@ResponseWrapper(localName = "getInstructionListResponse", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.GetInstructionListResponse")
	public String getInstructionList(
			@WebParam(name = "arg0", targetNamespace = "") String arg0);

	/**
	 * 
	 * @param arg2
	 * @param arg1
	 * @param arg0
	 * @return returns java.lang.String
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "updateRole", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.UpdateRole")
	@ResponseWrapper(localName = "updateRoleResponse", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.UpdateRoleResponse")
	public String updateRole(
			@WebParam(name = "arg0", targetNamespace = "") String arg0,
			@WebParam(name = "arg1", targetNamespace = "") String arg1,
			@WebParam(name = "arg2", targetNamespace = "") SysRole arg2);

	/**
	 * 
	 * @return returns com.softline.client.task.TaskInstruction
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "getTaskInstruction", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.GetTaskInstruction")
	@ResponseWrapper(localName = "getTaskInstructionResponse", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.GetTaskInstructionResponse")
	public TaskInstruction getTaskInstruction();

	/**
	 * 
	 * @param arg0
	 * @return returns java.util.List<com.softline.client.task.TaskInstruction>
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "getTaskList1", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.GetTaskList1")
	@ResponseWrapper(localName = "getTaskList1Response", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.GetTaskList1Response")
	public List<TaskInstruction> getTaskList1(
			@WebParam(name = "arg0", targetNamespace = "") String arg0);

	/**
	 * 
	 * @return returns com.softline.client.task.SysFile
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "getSysFile", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.GetSysFile")
	@ResponseWrapper(localName = "getSysFileResponse", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.GetSysFileResponse")
	public SysFile getSysFile();

	/**
	 * 
	 * @param arg1
	 * @param arg0
	 * @return returns java.lang.String
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "getAlertTaskInsructionList", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.GetAlertTaskInsructionList")
	@ResponseWrapper(localName = "getAlertTaskInsructionListResponse", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.GetAlertTaskInsructionListResponse")
	public String getAlertTaskInsructionList(
			@WebParam(name = "arg0", targetNamespace = "") String arg0,
			@WebParam(name = "arg1", targetNamespace = "") String arg1);

	/**
	 * 
	 * @param arg0
	 * @return returns java.util.List<com.softline.client.task.TaskInstruction>
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "getTaskInsructionList03", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.GetTaskInsructionList03")
	@ResponseWrapper(localName = "getTaskInsructionList03Response", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.GetTaskInsructionList03Response")
	public List<TaskInstruction> getTaskInsructionList03(
			@WebParam(name = "arg0", targetNamespace = "") TaskInstruction arg0);

	/**
	 * 
	 * @param arg1
	 * @param arg0
	 * @return returns java.lang.String
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "addUserRole", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.AddUserRole")
	@ResponseWrapper(localName = "addUserRoleResponse", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.AddUserRoleResponse")
	public String addUserRole(
			@WebParam(name = "arg0", targetNamespace = "") String arg0,
			@WebParam(name = "arg1", targetNamespace = "") Integer arg1);

	/**
	 * 
	 * @param arg0
	 * @return returns java.util.List<com.softline.client.task.TaskInstruction>
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "getTrackInstructions", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.GetTrackInstructions")
	@ResponseWrapper(localName = "getTrackInstructionsResponse", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.GetTrackInstructionsResponse")
	public List<TaskInstruction> getTrackInstructions(
			@WebParam(name = "arg0", targetNamespace = "") TaskInstruction arg0);

	/**
	 * 
	 * @param arg3
	 * @param arg2
	 * @param arg1
	 * @param arg0
	 * @return returns java.lang.String
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "saveTaskInstruction2", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.SaveTaskInstruction2")
	@ResponseWrapper(localName = "saveTaskInstruction2Response", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.SaveTaskInstruction2Response")
	public String saveTaskInstruction2(
			@WebParam(name = "arg0", targetNamespace = "") TaskInstruction arg0,
			@WebParam(name = "arg1", targetNamespace = "") HhUsers arg1,
			@WebParam(name = "arg2", targetNamespace = "") String arg2,
			@WebParam(name = "arg3", targetNamespace = "") String arg3);

	/**
	 * 
	 * @param arg0
	 * @return returns java.lang.String
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "saveSysFile", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.SaveSysFile")
	@ResponseWrapper(localName = "saveSysFileResponse", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.SaveSysFileResponse")
	public String saveSysFile(
			@WebParam(name = "arg0", targetNamespace = "") String arg0);

	/**
	 * 
	 * @param arg0
	 * @return returns java.lang.String
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "getTaskInstructionList01", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.GetTaskInstructionList01")
	@ResponseWrapper(localName = "getTaskInstructionList01Response", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.GetTaskInstructionList01Response")
	public String getTaskInstructionList01(
			@WebParam(name = "arg0", targetNamespace = "") String arg0);

	/**
	 * 
	 * @param arg0
	 * @return returns java.lang.String
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "updateHhPage", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.UpdateHhPage")
	@ResponseWrapper(localName = "updateHhPageResponse", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.UpdateHhPageResponse")
	public String updateHhPage(
			@WebParam(name = "arg0", targetNamespace = "") HhPage arg0);

	/**
	 * 
	 * @param arg0
	 * @return returns java.util.List<com.softline.client.task.CodeEnum>
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "getCodeEnumList1", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.GetCodeEnumList1")
	@ResponseWrapper(localName = "getCodeEnumList1Response", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.GetCodeEnumList1Response")
	public List<CodeEnum> getCodeEnumList1(
			@WebParam(name = "arg0", targetNamespace = "") String arg0);

	/**
	 * 
	 * @param arg0
	 * @return returns java.lang.String
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "getCodeEnumList", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.GetCodeEnumList")
	@ResponseWrapper(localName = "getCodeEnumListResponse", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.GetCodeEnumListResponse")
	public String getCodeEnumList(
			@WebParam(name = "arg0", targetNamespace = "") String arg0);

	/**
	 * 
	 * @param arg1
	 * @param arg0
	 * @return returns java.util.List<com.softline.client.task.TaskInstruction>
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "getInstructionList01", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.GetInstructionList01")
	@ResponseWrapper(localName = "getInstructionList01Response", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.GetInstructionList01Response")
	public List<TaskInstruction> getInstructionList01(
			@WebParam(name = "arg0", targetNamespace = "") TaskInstruction arg0,
			@WebParam(name = "arg1", targetNamespace = "") String arg1);

	/**
	 * 
	 * @param arg0
	 * @return returns java.util.List<com.softline.client.task.TaskInstruction>
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "getInstructionList1", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.GetInstructionList1")
	@ResponseWrapper(localName = "getInstructionList1Response", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.GetInstructionList1Response")
	public List<TaskInstruction> getInstructionList1(
			@WebParam(name = "arg0", targetNamespace = "") String arg0);

	/**
	 * 
	 * @param arg0
	 * @return returns java.lang.String
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "getTaskList", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.GetTaskList")
	@ResponseWrapper(localName = "getTaskListResponse", targetNamespace = "http://webService.softline.com/", className = "com.softline.client.task.GetTaskListResponse")
	public String getTaskList(
			@WebParam(name = "arg0", targetNamespace = "") String arg0);

}
