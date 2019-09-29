package com.softline.service.task;

import com.softline.client.task.HhUsers;
import com.softline.client.task.TaskInstruction;

public interface ITaskService {

	public String save(TaskInstruction instruction, HhUsers users);
	
	public String save(TaskInstruction instruction, 
			HhUsers users,String sBSlctPersons, String sBAccountNameDeps);
}
