package com.softline.entityTemp;

public class ReportHtmlView {
		
		private String tablestr;
		
		private String createPersonName;
		
		private String createDate;
		
		private String reportPersonName;
		
		private String reportDate;

		public String getTablestr() {
			return tablestr;
		}

		public void setTablestr(String tablestr) {
			this.tablestr = tablestr;
		}

		public String getCreatePersonName() {
			return createPersonName;
		}

		public void setCreatePersonName(String createPersonName) {
			this.createPersonName = createPersonName;
		}

		public String getCreateDate() {
			return createDate;
		}

		public void setCreateDate(String createDate) {
			this.createDate = createDate;
		}

		public String getReportPersonName() {
			return reportPersonName;
		}

		public void setReportPersonName(String reportPersonName) {
			this.reportPersonName = reportPersonName;
		}

		public String getReportDate() {
			return reportDate;
		}

		public void setReportDate(String reportDate) {
			this.reportDate = reportDate;
		}

		public ReportHtmlView(String tablestr, String createPersonName,
				String createDate, String reportPersonName, String reportDate) {
			super();
			this.tablestr = tablestr;
			this.createPersonName = createPersonName;
			this.createDate = createDate;
			this.reportPersonName = reportPersonName;
			this.reportDate = reportDate;
		}

		public ReportHtmlView() {
			super();
		}
		
		
}
