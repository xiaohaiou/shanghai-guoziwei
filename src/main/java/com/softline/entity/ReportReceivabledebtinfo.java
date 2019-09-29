package com.softline.entity;

/**
 * ReportReceivabledebtinfo entity. @author MyEclipse Persistence Tools
 */

public class ReportReceivabledebtinfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer groupid;
	private Integer year;
	private Integer month;
	private String coreorg;
	private String coreorgname;
	private String debtorg;
	private String debtorgname;
	private String oppositeorg;
	private String oppositeorgname;
	private String collectionperson;
	private String leadership;
	private String accountingsubject;
	private String beginningbalance;
	private String beginningcreditperiodbalance;
	private String beginningbalancethirtydays;
	private String beginningbalancesixtydays;
	private String beginningbalanceninetydays;
	private String beginningbalanceoverdays;
	private String monthnewaccout;
	private String monthreturnaccout;
	private String momthreturnoveraccout;
	private String endingbalance;
	private String endingcreditperiodbalance;
	private String endingbalancethirtydays;
	private String endingbalancesixtydays;
	private String endingbalanceninetydays;
	private String endingbalanceoverdays;
	private String endingbalancetwoyears;
	private String endingbalancethreeyears;
	private String endingbalancefiveyears;
	private String endingbalanceoveryears;
	private String reasonorremarks;
	private String debttype;
	private String cashdepositdeadline;
	private String collectionmeasures;
	private String planreturntime;
	private String isFinish;
	private String isSend;
	private String isPromise;
	private String isTurnover;
	private Integer isdel;
	private String parentorg;
	private Integer type;
	// Constructors
	private String overmoney;
	private String badmoney;
	private String date;

	private String day;
	
	//formula
	private String authOrg;
	private Integer status;
	
	/** default constructor */
	public ReportReceivabledebtinfo() {
	}

	/** minimal constructor */
	public ReportReceivabledebtinfo(Integer year,Integer isdel) {
		this.year = year;
		this.isdel = isdel;
	}

	/** full constructor */
	public ReportReceivabledebtinfo(Integer groupid, Integer year,
			Integer month, String coreorg, String coreorgname, String debtorg, String debtorgname,
			String oppositeorg, String oppositeorgname,
			String collectionperson, String leadership,
			String accountingsubject, String beginningbalance,
			String beginningcreditperiodbalance,
			String beginningbalancethirtydays,
			String beginningbalancesixtydays,
			String beginningbalanceninetydays, String beginningbalanceoverdays,
			String monthnewaccout, String monthreturnaccout,
			String momthreturnoveraccout, String endingbalance,
			String endingcreditperiodbalance, String endingbalancethirtydays,
			String endingbalancesixtydays, String endingbalanceninetydays,
			String endingbalanceoverdays, String endingbalancetwoyears,
			String endingbalancethreeyears, String endingbalancefiveyears,
			String endingbalanceoveryears, String reasonorremarks,
			String debttype, String cashdepositdeadline,
			String collectionmeasures, String planreturntime, String isFinish,
			String isSend, String isPromise, String isTurnover,
			Integer isdel, String parentorg,Integer type,Integer status) {
		this.groupid = groupid;
		this.year = year;
		this.month = month;
		this.coreorg = coreorg;
		this.coreorgname = coreorgname;
		this.debtorg = debtorg;
		this.debtorgname = debtorgname;
		this.oppositeorg = oppositeorg;
		this.oppositeorgname = oppositeorgname;
		this.collectionperson = collectionperson;
		this.leadership = leadership;
		this.accountingsubject = accountingsubject;
		this.beginningbalance = beginningbalance;
		this.beginningcreditperiodbalance = beginningcreditperiodbalance;
		this.beginningbalancethirtydays = beginningbalancethirtydays;
		this.beginningbalancesixtydays = beginningbalancesixtydays;
		this.beginningbalanceninetydays = beginningbalanceninetydays;
		this.beginningbalanceoverdays = beginningbalanceoverdays;
		this.monthnewaccout = monthnewaccout;
		this.monthreturnaccout = monthreturnaccout;
		this.momthreturnoveraccout = momthreturnoveraccout;
		this.endingbalance = endingbalance;
		this.endingcreditperiodbalance = endingcreditperiodbalance;
		this.endingbalancethirtydays = endingbalancethirtydays;
		this.endingbalancesixtydays = endingbalancesixtydays;
		this.endingbalanceninetydays = endingbalanceninetydays;
		this.endingbalanceoverdays = endingbalanceoverdays;
		this.endingbalancetwoyears = endingbalancetwoyears;
		this.endingbalancethreeyears = endingbalancethreeyears;
		this.endingbalancefiveyears = endingbalancefiveyears;
		this.endingbalanceoveryears = endingbalanceoveryears;
		this.reasonorremarks = reasonorremarks;
		this.debttype = debttype;
		this.cashdepositdeadline = cashdepositdeadline;
		this.collectionmeasures = collectionmeasures;
		this.planreturntime = planreturntime;
		this.isFinish = isFinish;
		this.isSend = isSend;
		this.isPromise = isPromise;
		this.isTurnover = isTurnover;
		this.isdel = isdel;
		this.parentorg = parentorg;
		this.type = type;
		this.status=status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGroupid() {
		return this.groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return this.month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public String getCoreorg() {
		return this.coreorg;
	}

	public void setCoreorg(String coreorg) {
		this.coreorg = coreorg;
	}

	public String getCoreorgname() {
		return this.coreorgname;
	}

	public void setCoreorgname(String coreorgname) {
		this.coreorgname = coreorgname;
	}

	public String getDebtorg() {
		return debtorg;
	}

	public void setDebtorg(String debtorg) {
		this.debtorg = debtorg;
	}

	public String getDebtorgname() {
		return debtorgname;
	}

	public void setDebtorgname(String debtorgname) {
		this.debtorgname = debtorgname;
	}

	public String getOppositeorg() {
		return this.oppositeorg;
	}

	public void setOppositeorg(String oppositeorg) {
		this.oppositeorg = oppositeorg;
	}

	public String getOppositeorgname() {
		return this.oppositeorgname;
	}

	public void setOppositeorgname(String oppositeorgname) {
		this.oppositeorgname = oppositeorgname;
	}

	public String getCollectionperson() {
		return this.collectionperson;
	}

	public void setCollectionperson(String collectionperson) {
		this.collectionperson = collectionperson;
	}

	public String getLeadership() {
		return this.leadership;
	}

	public void setLeadership(String leadership) {
		this.leadership = leadership;
	}

	public String getAccountingsubject() {
		return this.accountingsubject;
	}

	public void setAccountingsubject(String accountingsubject) {
		this.accountingsubject = accountingsubject;
	}

	public String getBeginningbalance() {
		return this.beginningbalance;
	}

	public void setBeginningbalance(String beginningbalance) {
		this.beginningbalance = beginningbalance;
	}

	public String getBeginningcreditperiodbalance() {
		return this.beginningcreditperiodbalance;
	}

	public void setBeginningcreditperiodbalance(
			String beginningcreditperiodbalance) {
		this.beginningcreditperiodbalance = beginningcreditperiodbalance;
	}

	public String getBeginningbalancethirtydays() {
		return this.beginningbalancethirtydays;
	}

	public void setBeginningbalancethirtydays(String beginningbalancethirtydays) {
		this.beginningbalancethirtydays = beginningbalancethirtydays;
	}

	public String getBeginningbalancesixtydays() {
		return this.beginningbalancesixtydays;
	}

	public void setBeginningbalancesixtydays(String beginningbalancesixtydays) {
		this.beginningbalancesixtydays = beginningbalancesixtydays;
	}

	public String getBeginningbalanceninetydays() {
		return this.beginningbalanceninetydays;
	}

	public void setBeginningbalanceninetydays(String beginningbalanceninetydays) {
		this.beginningbalanceninetydays = beginningbalanceninetydays;
	}

	public String getBeginningbalanceoverdays() {
		return this.beginningbalanceoverdays;
	}

	public void setBeginningbalanceoverdays(String beginningbalanceoverdays) {
		this.beginningbalanceoverdays = beginningbalanceoverdays;
	}

	public String getMonthnewaccout() {
		return this.monthnewaccout;
	}

	public void setMonthnewaccout(String monthnewaccout) {
		this.monthnewaccout = monthnewaccout;
	}

	public String getMonthreturnaccout() {
		return this.monthreturnaccout;
	}

	public void setMonthreturnaccout(String monthreturnaccout) {
		this.monthreturnaccout = monthreturnaccout;
	}

	public String getMomthreturnoveraccout() {
		return this.momthreturnoveraccout;
	}

	public void setMomthreturnoveraccout(String momthreturnoveraccout) {
		this.momthreturnoveraccout = momthreturnoveraccout;
	}

	public String getEndingbalance() {
		return this.endingbalance;
	}

	public void setEndingbalance(String endingbalance) {
		this.endingbalance = endingbalance;
	}

	public String getEndingcreditperiodbalance() {
		return this.endingcreditperiodbalance;
	}

	public void setEndingcreditperiodbalance(String endingcreditperiodbalance) {
		this.endingcreditperiodbalance = endingcreditperiodbalance;
	}

	public String getEndingbalancethirtydays() {
		return this.endingbalancethirtydays;
	}

	public void setEndingbalancethirtydays(String endingbalancethirtydays) {
		this.endingbalancethirtydays = endingbalancethirtydays;
	}

	public String getEndingbalancesixtydays() {
		return this.endingbalancesixtydays;
	}

	public void setEndingbalancesixtydays(String endingbalancesixtydays) {
		this.endingbalancesixtydays = endingbalancesixtydays;
	}

	public String getEndingbalanceninetydays() {
		return this.endingbalanceninetydays;
	}

	public void setEndingbalanceninetydays(String endingbalanceninetydays) {
		this.endingbalanceninetydays = endingbalanceninetydays;
	}

	public String getEndingbalanceoverdays() {
		return this.endingbalanceoverdays;
	}

	public void setEndingbalanceoverdays(String endingbalanceoverdays) {
		this.endingbalanceoverdays = endingbalanceoverdays;
	}

	public String getEndingbalancetwoyears() {
		return this.endingbalancetwoyears;
	}

	public void setEndingbalancetwoyears(String endingbalancetwoyears) {
		this.endingbalancetwoyears = endingbalancetwoyears;
	}

	public String getEndingbalancethreeyears() {
		return this.endingbalancethreeyears;
	}

	public void setEndingbalancethreeyears(String endingbalancethreeyears) {
		this.endingbalancethreeyears = endingbalancethreeyears;
	}

	public String getEndingbalancefiveyears() {
		return this.endingbalancefiveyears;
	}

	public void setEndingbalancefiveyears(String endingbalancefiveyears) {
		this.endingbalancefiveyears = endingbalancefiveyears;
	}

	public String getEndingbalanceoveryears() {
		return this.endingbalanceoveryears;
	}

	public void setEndingbalanceoveryears(String endingbalanceoveryears) {
		this.endingbalanceoveryears = endingbalanceoveryears;
	}

	public String getReasonorremarks() {
		return this.reasonorremarks;
	}

	public void setReasonorremarks(String reasonorremarks) {
		this.reasonorremarks = reasonorremarks;
	}

	public String getDebttype() {
		return this.debttype;
	}

	public void setDebttype(String debttype) {
		this.debttype = debttype;
	}

	public String getCashdepositdeadline() {
		return this.cashdepositdeadline;
	}

	public void setCashdepositdeadline(String cashdepositdeadline) {
		this.cashdepositdeadline = cashdepositdeadline;
	}

	public String getCollectionmeasures() {
		return this.collectionmeasures;
	}

	public void setCollectionmeasures(String collectionmeasures) {
		this.collectionmeasures = collectionmeasures;
	}

	public String getPlanreturntime() {
		return this.planreturntime;
	}

	public void setPlanreturntime(String planreturntime) {
		this.planreturntime = planreturntime;
	}

	public String getIsFinish() {
		return this.isFinish;
	}

	public void setIsFinish(String isFinish) {
		this.isFinish = isFinish;
	}

	public String getIsSend() {
		return this.isSend;
	}

	public void setIsSend(String isSend) {
		this.isSend = isSend;
	}

	public String getIsPromise() {
		return this.isPromise;
	}

	public void setIsPromise(String isPromise) {
		this.isPromise = isPromise;
	}

	public String getIsTurnover() {
		return this.isTurnover;
	}

	public void setIsTurnover(String isTurnover) {
		this.isTurnover = isTurnover;
	}

	public Integer getIsdel() {
		return this.isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public String getParentorg() {
		return this.parentorg;
	}

	public void setParentorg(String parentorg) {
		this.parentorg = parentorg;
	}

	public String getOvermoney() {
		return overmoney;
	}

	public void setOvermoney(String overmoney) {
		this.overmoney = overmoney;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBadmoney() {
		return badmoney;
	}

	public void setBadmoney(String badmoney) {
		this.badmoney = badmoney;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getAuthOrg() {
		return authOrg;
	}

	public void setAuthOrg(String authOrg) {
		this.authOrg = authOrg;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}