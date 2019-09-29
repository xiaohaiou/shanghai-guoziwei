package com.softline.dao.warncenter;

import java.util.List;

import com.softline.entity.DataWorktableWarningCenter;

public interface IWarningCenterOneYearMonthDao {
	
	public List<DataWorktableWarningCenter> getInsertUnfilledDataWorkTable(String[] parameterArr);
	
	public List<DataWorktableWarningCenter> dataRemoveWorkTable(String[] parameterArr);
	
	public List<DataWorktableWarningCenter> dataRemoveLastYearWorkTable(String tableName,String date);

}
