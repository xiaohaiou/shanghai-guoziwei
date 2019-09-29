package com.softline.dao.report;

import com.softline.entity.ReportFormsUnFilledCompany;

public interface IReportRemindCompanyDao {
	
	public boolean isExitsReportFormsUnFilledCompany(ReportFormsUnFilledCompany bean);
	
	public ReportFormsUnFilledCompany getReportFormsUnFilledCompany(ReportFormsUnFilledCompany bean);
	
	public void deleteReportFormsUnFilledCompany(ReportFormsUnFilledCompany bean);
	
	public void setReportFormsUnFilledCompanyUnRemind(ReportFormsUnFilledCompany bean);
	
	public void setReportFormsUnFilledCompanyRemind(ReportFormsUnFilledCompany bean);
	
	public void setAllHavenDataRemind(String updateTime,Integer formKind);
	
	public void setAllHavenDataDisRemind(String updateTime,Integer formKind);
	
	public void addAllDataToRemindPlan(String reportTime, String authdata,Integer remindFormKind,String treeTab);

}
