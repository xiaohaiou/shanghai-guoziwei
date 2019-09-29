package com.softline.service.task.impl;

import org.springframework.stereotype.Service;

import com.softline.client.task.HhUsers;
import com.softline.client.task.ITaskWebservice;
import com.softline.client.task.TaskInstruction;
import com.softline.client.task.TaskWebserviceService;
import com.softline.service.task.ITaskService;

@Service("taskService")
public class TaskService implements ITaskService {
	@Override
	public String save(TaskInstruction instruction, HhUsers users) {
		//String instructionStr = JSON.toJSONString(instruction);
		TaskWebserviceService service = new TaskWebserviceService();
		ITaskWebservice it = service.getTaskWebservicePort();
		String flag = it.saveTaskInstruction1(instruction, users);
		return flag;
	}
	
	@Override
	public String save(TaskInstruction instruction, HhUsers users,
			String sBSlctPersons, String sBAccountNameDeps) {
		TaskWebserviceService service = new TaskWebserviceService();
		ITaskWebservice it = service.getTaskWebservicePort();
		String flag = it.saveTaskInstruction2(instruction, users,sBSlctPersons,sBAccountNameDeps);
		return flag;
	}

}
