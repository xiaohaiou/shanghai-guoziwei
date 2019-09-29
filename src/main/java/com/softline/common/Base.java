package com.softline.common;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.softline.util.Coder;

public class Base {
	
	//public static String HRTop="26655";
	//public static String HRTopPrefix="0-1-26655-";
	//public static String HROutPrefix="0-1-26655--1";
	//public static String HRTopCompany="26658";
	
	public static String HRTop="855579";
	public static String HRTopCompany="855580";
	public static String HRTopPrefix="0-1-855579-";
	public static String HROutPrefix="0-1-855579--1";
	//董事会领导
	public static String HRTopLeader = "0-1-855579-855580-855970-";
	
	public static String HRTopName="海航物流";
	public static String HRTopSImpleName="物流总部";
	
	//年度重点工作 指标名称
	public static final Integer indexType=500;
	
	//铻嶈祫椤圭洰杩涘睍 椤圭洰杩涘睍
	public static final Integer projectProgress=300;
	//铻嶈祫椤圭洰杩涘睍 椤圭洰杩涘睍
	public static final Integer projectProgressFalse=300;
	//铻嶈祫椤圭洰杩涘睍 椤圭洰杩涘睍-
	public static final Integer projectProgressTrue=301;
	
	//融资项目进展 融资类别
	public static final Integer financingCategory=350;
	
	//融资项目进展 融资模式
	public static final Integer financingPattern=400;
	
	//融资下账模块的 债务类型
	public static final Integer debtType=67;
	
	//币种
	public static final Integer currencyKind=69;
	
	//融资下账模块的 负债种类
	public static final Integer liabilityTypes=100;
	
	//公共 是否
	public static final Integer truefalse=108;
	public static final Integer truefalse_true=108;
	
	//融资下账 境内/境外融资
	public static final Integer domesticOverseas=110;
	
	
	//筹资性流入明细列表 续作/新增
	public static final Integer sequelNew=112;
	
	//筹资性流入明细列表 贷款类型
	public static final Integer loanType=114;
	
	//筹资性流出明细列表 融资类型
	public static final Integer financingType=150;
		
	//筹资性流出明细列表 融资类型明细
	public static final Integer financingTypeDetail=200;
	
	//投资性流出明细列表 投资类型
	public static final Integer investType=250;
	
	//分页
	//分页大小
	public static final Integer pagesize=10;

	// 功能分类：查询
	public static final Integer fun_search=40400;
	// 功能分类：审核
	public static final Integer fun_examine=40405;

	
	//报表中机构的属性种类
	public static final int report_organal=50500;
	//实体
	public static final Integer report_organal_entity=50500;
	//虚拟
	public static final Integer report_organal_invent=50501;
	//壳公司
	public static final Integer report_organal_ke=50502;
	
	//类型表
	//报表名
	public static final int reportformkind=50010;
	//报表组类型
	public static final int reportgroupkind=52001;
	//预算执行
	public static final int reportgroup_BudgetEnforcement=52001;
	
	//报表组属性
	public static final int reportgroup_type=52004;
	//报表组属性单户
	public static final int reportgroup_type_simple=52004;
	//报表组属性汇总
	public static final int reportgroup_type_collect=52005;
	//核算
	public static final int reportgroup_adjust=52002;
	//资金
	public static final int reportgroup_finance=52003;
	
	//报表频度
	public static final int reportformfre=50011;
	//年报
	public static final Integer yearly=50106;
	//季报
	public static final Integer seasonly=50105;
	//月报
	public static final Integer monthly=50107;
	
	
	//报表审核
	//报表上报
	public static final Integer reportappear=50100;
	
	//未填报公司种类
	public static final Integer reportUnfill_type_budget = 51111; 	//预算
	public static final Integer reportUnfill_type_account = 51112;	//核算
	public static final Integer reportUnfill_type_tax = 51113;		//税务
		
	//季度  hhbase 的type
	public static final Integer seasontype=50108;
	// 媒体类型 hhbase的type
	public static final Integer mediaType=40000;
	// 审核状态 hhbase 的type
	public static final Integer examstatustype=50112;
	// 品牌类别 hhbase 的type
	public static final Integer brandType=40100;
	// 品牌位阶 hhbase 的type
	public static final Integer brandLevel=40200;
	// 品牌属性 hhbase 的type
	public static final Integer brandProperty=40300;
	//审计项目性质 hhbase 的type
	public static final Integer auditProjectProperty = 80820;
	
	
	//审计项目性质 hhbase 的type
	public static final Integer projectStatusProgress = 81080;
		
	// 节税目标分解明细类型
	/** 纳税筹划 */
	public static final Integer taxPlan = 40500;
	/** 优惠政策申请 */
	public static final Integer favouredPolicy = 40501;
	/** 税收返还 */
	public static final Integer taxReturn = 40502;
	/** 非税返还 */
	public static final Integer inTaxReturn = 40503;
	
	//填报数据频度
	public static final Integer fre_type=50129;
	
	//财务树类型
	public static final Integer financetype=50000;
	/**
	 *审核状态 未上报
	 */
	public static final Integer examstatus_1=50112;
	/**
	 *审核状态 待审核
	 */
	public static final Integer examstatus_2=50113;
	/**
	 *审核状态 被退回
	 */
	public static final Integer examstatus_3=50114;
	/**
	 *审核状态 已审核
	 */
	public static final Integer examstatus_4=50115;

	/**
	 * 项目状态-未启用.
	 */
	public static final Integer PROJECT_NO_ENABLE = 50223;
	/**
	 * 项目状态-已启用.
	 */
	public static final Integer PROJECT_ENABLE = 50224;
	/**
	 * 项目状态-在办中
	 */
	public static final Integer PROJECT_IS_WORKING = 50225;
	/**
	 * 项目状态-整改中
	 */
	public static final Integer PROJECT_IS_RECTIFYING = 50226;
	/**
	 * 项目状态-办结申请中
	 */
	public static final Integer PROJECT_IS_APPLYING = 50227;
	/**
	 * 项目状态-已退回
	 */
	public static final Integer PROJECT_IS_RETURN = 50228;
	/**
	 * 项目状态-已完成
	 */
	public static final Integer PROJECT_IS_OVER = 50229;
	/**
	 * 项目状态-已完成
	 */
	public static final Integer PROJECT_FINISH_APPLY = 50230;
	/**
	 * 项目状态-已完成
	 */
	public static final Integer PROJECT_FINISH = 50231;

	/**
	 * 是子项目.
	 */
	public static final Integer IS_CHILD_PROJECT = 1;
	/**
	 * 非子项目.
	 */
	public static final Integer IS_NOT_CHILD_PROJECT = 0;

	public static final String  examCanEdit=examstatus_1+","+examstatus_3;
	
	public static final Integer mainFinancialIndicatorReportType=50134;
	
	//审核意见
	/**
	 *审核意见 不通过
	 */
	public static final Integer examresult_1=50117;
	/**
	 *审核意见 通过
	 */
	public static final Integer examresult_2=50116;
	
	//审核种类
	/**
	 * 管理口径核算审核
	 */
	public static final Integer examkind_reportmanageadjust=50128;
	/**
	 * 境外资金池建设审核
	 */
	public static final Integer examkind_reportoverseascapitalpool=50138;
	/**
	 * 资金头寸审核
	 */
	public static final Integer examkind_reportfundposition=50139;
	/**
	 * 节税任务数据审核
	 */
	public static final Integer examkind_taxtask=50140;
	/**
	 * 纳税数据审核
	 */
	public static final Integer examkind_taxpay=50141;
	/**
	 * 节税数据审核
	 */
	public static final Integer examkind_taxsave=50142;
	/**
	 * 人事数据审核
	 */
	public static final Integer examkind_hr=50144;
	/**
	 * 融资下账审核
	 */
	public static final Integer examkind_financeaccount=50137;
	
	/**
	 * 外部对标审核
	 */
	public static final Integer examkind_reportoutcompany=50238;
	
	/**
	 * 融资下账审核(共享)
	 */
	public static final Integer examkind_reportFinancingShare=60137;
	
	/**
	 * 融资下账审核(债券)
	 */
	public static final Integer examkind_reportFinancingBond=60137;
	
	/**
	 * 融资下账审核(创新)
	 */
	public static final Integer examkind_reportFinancingInnovate=60137;
	
	/**
	 * 本周融资下账审核(创新)
	 */
	public static final Integer examkind_reportFinancingWeekThis=60138;
	
	/**
	 * 下周融资下账审核(创新)
	 */
	public static final Integer examkind_reportFinancingWeekNext=60139;
	
	/**
	 * 存量负债审核
	 */
	public static final Integer examkind_stockliabilities=50143;
	/**
	 * 现金流周执行数据审核
	 */
	public static final Integer examkind_cashflowweeklyexecute=50145;
	/**
	 * 现金流月执行数据审核
	 */
	public static final Integer examkind_cashflowmonthlyexecute=50146;
	
	/**
	 *  境外资产占比数据填报
	 */
	public static final Integer examkind_reportOverseasAsset=50147;
	/**
	 * 主要财务指标
	 */
	public static final Integer examkind_mainfinanceindicator=50136;
	/**
	 * 审核采购
	 */
	public static final Integer examkind_purchase=50118;
	/**
	 * 审核志愿服务与员工关爱
	 */
	//public static final Integer examkind_voluntary=50119;
	/**
	 * 审核志愿服务
	 */
	public static final Integer examkind_voluntary=501191;
	/**
	 * 审核员工关爱
	 */
	public static final Integer examkind_employeecare=501192;
	/**
	 * 审核媒体信息
	 */
	public static final Integer examkind_media=50120;
	/**
	 * 审核品牌信息
	 */
	public static final Integer examkind_brand=50121;
	/**
	 * 审核新媒体信息
	 */
	public static final Integer examkind_newmedia=50122;
	/**
	 * 审核舆情信息
	 */
	public static final Integer examkind_consensus=50123;
	
	/*
	 * 审核hr_headCount
	 */
	public static final Integer examkind_hr_headCount=60001;
	/*
	 * 审核hr_laborCost
	 */
	public static final Integer examkind_hr_laborCost=60002;
	/*
	 * 审核hr_managerialstaff
	 */
	public static final Integer examkind_hr_managerialstaff=60003;
	/*
	 * 酷铺市场占有率examkind_six_rateKp
	 */
	public static final Integer examkind_six_rateKp=60004;
	/*
	 * 地产市场占有率examkind_six_rateKp
	 */
	public static final Integer examkind_six_rateDc=60005;
	/*
	 * 资金头寸examkind_hr_reportFundPosition
	 */
	public static final Integer examkind_hr_reportFundPosition=60006;
	/*
	 * 现金流月计划
	 */
	public static final Integer examkind_hr_moneyFlowPlanMonth=60007;
	/*
	 * 现金流周计划
	 */
	public static final Integer examkind_hr_moneyFlowPlanWeek=60008;
	//月份数据
	public static final String[] monthDate={"1","2","3","4","5","6","7","8","9","10","11","12"};
	
	/*
	 * 融资项目进展审批
	 */
	public static final Integer examkind_reportfinancingprojectprogress=50148;
	/*
	 * 公文审批及时率审批
	 */
	public static final Integer examkind_document=50124;
	/*
	 * 行政督办办结率审批
	 */
	public static final Integer examkind_supervise=50125;
	/*
	 * 保密风险事件审批
	 */
	public static final Integer examkind_riskevent=50126;
	/*
	 * 要情事件审批
	 */
	public static final Integer examkind_important=50127;
	
	/*
	 * 风险控制审计项目审批
	 */
	public static final Integer examkind_riskcontrol_auditProject=50128;
	/*
	 * 风险控制合同审批
	 */
	public static final Integer examkind_riskcontrol_contract=50129;
	/*
	 * 风险控制案件审批
	 */
	public static final Integer examkind_riskcontrol_case=50130;
	/*
	 * 风险控制台账审批
	 */
	public static final Integer examkind_riskcontrol_account=50131;
	
	/*
	 * 年度重点工作审批
	 */
	public static final Integer examkind_keywork=70072;
	
	
	/*
	 * 风险目录
	 */
	public static final Integer examkind_risk_catalog = 50132;
	
	/*
	 * 风险目录关联事件审核
	 */
	public static final Integer examkind_risk_Relevance = 70080;
	
	/*
	 * 风险事件审核
	 */
	public static final Integer examkind_risk_event = 70081;
	
	/*
	 * 风险事件跟踪审核
	 */
	public static final Integer examkind_risk_track = 70082;
	
	/*
	 * 风险事件反馈审核
	 */
	public static final Integer examkind_risk_feedback = 70083;
	
	//要情类别  hhbase 的type
	public static final Integer important_type=70001;
	//审计类型一
	public static final Integer audit_type=70003;
	//审计类型二
	public static final Integer audit_type2=70006;
	//合同类型
	public static final Integer contract_type=70014;
	//案件类型
	public static final Integer case_type=70020;
	//案件状态
	public static final Integer case_status=70025;
	//人员管理级别
	public static final Integer person_manage_level=70032;
	
	//融资下账数据类型
	public static final Integer report_finance_account_type=90000;
	
	//融资下账数据类型
	public static final Integer report_out_company_type=90002;
	
	
	/**
	 * 审计项目-审计项目性质
	 */
	public static final Integer audit_risk_oriented = 80026;

	public static final Integer audit_risk_managed = 80027;

	public static final Integer internal_control_evaluate = 80028;

	public static final Integer audit_economic_responsible = 80029;

	public static final Integer enterprise_tour = 80030;

	public static final Integer research_manage = 80031;
	
	/**
	 * 审核民事案件
	 */
	public static final Integer examkind_civilcase=50149;
	
	public static final Integer examkind_criminalcase=50150;
	
	/**
	 * 内部审计
	 * */
	public static final Integer examkind_insideProjectRectifyFeedBack=50250;
	
	/*
	 * 个人借款
	 */
	public static final Integer examkind_personalloan=70073;
	
	/*
	 * 个人借款(新)
	 */
	public static final Integer examkind_personalloan_1=7007311;
	
	/*
	 * 应收债权(内部)
	 */
	public static final Integer examkind_receivabledebtinner=70074;
	
	/*
	 * 应收债权(外部)
	 */
	public static final Integer examkind_receivabledebtout=70075;
	
	/*
	 * 稽核项目-财务事项类别
	 */
	public static final Integer inspect_project_item_type=80000;
	/*
	 * 稽核项目-稽核类型
	 */
	public static final Integer inspect_project_inspect_type=80001;
	/*
	 * 稽核项目
	 */
	public static final Integer inspect_project=80002;
	/*
	 * 稽核状态
	 */
	public static final Integer inspect_project_status=80003;
	
	//合规调查基本信息维护-调查类型
	public static final Integer compliance_investigation_type=81000;
	//合规调查基本信息维护-项目状态
	public static final Integer compliance_status=81010;
	//合规调查基本信息维护-调查来源
	public static final Integer compliance_investigation_from=81020;
	//举报投诉反映问题线索核查情况-问题线索是否属实
	public static final Integer compliance_situation_istruth=81030;
	//举报投诉反映问题线索核查情况-问题类型
	public static final Integer compliance_situation_problemtype=81040;
	//合规进度-附件
	public static final Integer compliance_progress=81050;
	
	//合规管理-审核合规调查整改信息维护归档
	public static final Integer compliance_exmine=810501;
	/*
	 * 稽核项目问题
	 */
	public static final Integer inspect_project_problem=80004;
	
	
	//员工问责 审计项目审核
	
	public static final Integer EmployeeAccountability_insideproject=82010;
	//员工问责 合规项目审核
	
	public static final Integer EmployeeAccountability_complience=82011;
	//员工问责 民事案件审核
	public static final Integer EmployeeAccountability_civicase=82012;
	//员工问责 刑事案件审核
		public static final Integer EmployeeAccountability_criminal=82013;
	//案件管理 案由
		public static final Integer CASE_REASON=90001;
		
		
		
		public static final int seasonquery=1;//按季度查询
		
		public static final int yearquery=2;//按年查询	
	//
	/**
	 * 公司树的图片
	 */
	public static String[] icon = { "fa fa-file icon-state-danger",
			"fa fa-file icon-state-warning", "fa fa-file icon-state-success",
			"fa fa-file icon-state-info","fa fa-file icon-state-warning", "fa fa-file icon-state-success",
			"fa fa-file icon-state-info","fa fa-file icon-state-warning", "fa fa-file icon-state-success",
			"fa fa-file icon-state-info" };
	
	//重大工程静态常量
	//重大工程 附件上传类型 
	public static final Integer PROJECT_FILE_TYPE = 3000; //重大工程的图片
	public static final Integer PROJECT_WK_PDF_TEMP = 3001;//周报PDF文件
	public static final Integer PJ_CONTRACT_TYPE = 30;//合同类型
	public static final Integer PJ_MONEY_UNIT = 31;//金额单位
	public static final Integer PC_PAY_TYPE = 32;//合同付款方式
	public static final Integer PC_PAY_COUNT = 33;//合同付款期数
	public static final Integer BIMR_CRIMINAL_CASE = 4002; //民事案件附件
	public static final Integer BIMR_CIVIL_CASE = 4001; //刑事案件附件
	public static final Integer BIMR_COMLPIANCE_REPORT = 4003; //新增合规调查投诉举报信附件
	public static final Integer INSIDE_AUDIT_PROJECT = 4000; //内部审计附件
	//知识库类型
	public static final Integer KNOWLEDGEBASE_TYPE = 34;
	public static final Integer KNOWLEDGESTOREHOUSE_TYPE = 36;
	//知识库地区
	public static final Integer KNOWLEDGEBASE_AREA = 35;
	//附件类型
	//报表导出文件
	public static final int reportFile=1;
	//报表组导出文件
	public static final int reportGroupFile=2;
	//报表组导入文件
	public static final int reportGroupExportIn=3;
	
	/** 合规培训资料附件 */
	public static final int TRAIN_LECTURER_ENCLOSURE = 37;
	/** 合规培训人员名单导入 */
	public static final int TRAIN_LIST_OF = 38;
	/** 合规培训审批 */
	public static final int TRAIN_EXAMINE = 50151;
	/** pdf附件 */
	public static final int CONTRACTMODEL_PDF = 39;
	/** 空白模板 */
	public static final int CONTRACTMODEL_BLANK = 40;
	/** 员工问责附件 */
	public static final int DUTY_ENCLOSURE = 41;
	/** 员工问责审批 */
	public static final int DUTY_EXAMINE = 50152;
	
	/** 合规管理-报告办结附件 */
	public static final int COMPLIANCE_REPORTFINISH = 666;
	
	
	
	/**
	 * 邮件接口地址
	 */
	//public static final String EMAIL_URL = "http://10.70.72.110/api/inner/ESBService"; //测试
	
	public static final String EMAIL_URL = "http://esb.hna.net/api";//正式
	
	/**
	 * 邮件接口方法
	 */
	public static final String EMAIL_METHOD = "Exchange_MailService_SendMail";
	
	/**
	 * app密钥，查看个人中心app key
	 */
	public static final String EMAIL_APPSECRET = "uu5xkfzdekjp2s374c9ojl8f1587oons";
	
	/**
	 * 应用票据：共享平台分配给用户的唯一令牌，唯一标识用户应用，有时间约束条件。
	 */
	public static final String EMAIL_ACCESSTOKEN = "C43F224B83874F5859947B3058039FB5D1C3327B";
	
	/**
	 * 邮箱帐号
	 */
	public static final String EMAIL_ACCOUNT = "bimc@hnaholding.com";
	
	/**
	 * 发件人的内网账号
	 */
	public static final String EMAIL_USERNAME = "bimc";
	
	/**
	 * 发件人的密码，需要base64编码
	 */
	//public static final String EMAIL_PWD = "ZWtpbmdAODMxNjA4";
			
	//public static final String EMAIL_PWD = "d2xiaW1kcCwuMjAxOA==";
	
	public static final String EMAIL_PWD = Coder.getBASE64("wlbimdp,.2018");
	
	public static final String financeEmailInfoAccountTo = 
			"p-liu2@hna-logistics.com,qx.xue@hna-logistics.com,anm-liu@hna-logistics.com,"+
			"mh.jin@hna-logistics.com,ych.xue@hna-logistics.com,jx-zhang5@hna-logistics.com,"+
			"hj-mi@hnaholding.com,y-zhou6@hna-logistics.com,yaod.gao@hna-logistics.com,yxi.yang@hna-logistics.com,yue-wu1@hnaholding.com,ying.xiong@hnaholding.com";
	
	public static final String financeEmailInfoAccountCc = 
			"xj_xiang@hna-logistics.com,jiafu_wu@hna-logistics.com,yuhuan@hna-logistics.com,"+
			"yerong@hna-logistics.com,qiyuan_w@hna-logistics.com,lin.lin3@hna-logistics.com,"+
			"lukui@hna-logistics.com";
	
	public static final String financeEmailInfoAccountBcc = 
			"ying.xiong@hnaholding.com";
	
	
	

	/**
	 * 根据ID获取相应的审计发现问题类型.
	 * @return
	 */
	public static String getAuditQuestionTypes(Integer id){
		String ret = StringUtils.EMPTY;
		switch (id){
			case 0:
				ret = "人力资源管理方面的问题";
				break;
			case 1:
				ret = "采购管理方面的问题";
				break;
			case 2:
				ret = "基础建设方面的问题";
				break;
			case 3:
				ret = "项目投资(重组并购)方面的问题";
				break;
			case 4:
				ret = "生产组织和销售方面的问题";
				break;
			case 5:
				ret = "行政事务管理的问题";
				break;
			case 6:
				ret = "外部环境和竞争的问题";
				break;
			case 7:
				ret = "公司战略制定和执行方面问题";
				break;
			case 8:
				ret = "财务管控方面问题（资金资产安全等）";
				break;
			default:
				ret = "其他";
				break;
		}
		return ret;
	}

	/**
	 * 根据ID获取风险动因类型.
	 * @param id
	 * @return
	 */
	public static String getRiskDriverTypes(Integer id){
		String ret;
		switch (id){
			case 0:
				ret = "规章制度缺失或不完善";
				break;
			case 1:
				ret = "不相容岗位职责未分离,无监督牵制";
				break;
			case 2:
				ret = "干部员工作风问题,职业道德和操守问题";
				break;
			case 3:
				ret = "工作技能、业务能力和管理能力不足";
				break;
			case 4:
				ret = "外部政策和环境影响";
				break;
			default:
				ret = "其他";
				break;
		}
		return ret;
	}
	
	

	/**
	 * 规章制度同步相关常量
	 */
	public static final String  BYLAW_METHOD="Xplatform_RulesList_GetRulesList";//API接口名称，查看个人订单-接口名称
	public static final String BYLAW_Appsecret="ug1iq06827gtxmk2issfddsys7xj8dgc";//app密钥，查看个人中心appkey
	//public static final String BYLAW_URL="http://10.70.72.110/api/inner/ESBService";//测试环境服务接口请用调用地址
	public static final String BYLAW_URL="http://esb.hna.net/api";//正式环境服务接口请用调用地址
	public static final String BYLAW_AccessToken="017AF377245F787B7A6DA10F89117DC8F685ACC4";//应用票据，查看个人中心appkey
	public static final Integer BYLAW_START_TIME = 2000;//同步2000年至今的规章制度
	
	/**
	 * 公文审批及时率接口
	 * */
	public static final String DOC_METHOD = "OA_GetMacroStatistics_GetMacroStatistics";
	public static final String DOC_SYS_SOURCE = "HNALogistics";
	
	public static final String ESB_URL = "http://esb.hna.net/api";
	//public static final String ESB_URL = "http://10.70.72.110/api/inner/ESBService";
	
	public static final String SUPERVISE_METHOD = "HNAInspect_GetOrganFinishRate_GetOrganFinishRate";
	
	
	
	
	

	/**
	 * 人力资源接口
	 * */
	public static final String ESB_URLS = "http://hr.hna.net/HrMonthlyReport";
	public static final String HEADCOUNT_METHOD = "HRM_HrMonthlyReport_I_F_IND_SUMMARY";
	public static final String HEADCOUNT_BUSINESS_METHOD = "HRM_HrMonthlyReport_I_F_COMP_SUMMARY";
	public static final String MANAGE_METHOD = "HR Service Platform_GetLogisticsMrg_GetLogisticsMrg";

	/**
	 * 风险事件
	 */
	public static final Integer risk_event_copingStrategy=82000; //应对措施
	public static final Integer risk_event_status=82100; //状态

	public static final Integer risk_event_trackstatus=82103; //状态
	
	public static final Integer risk_event_province=82200; //省份
	
	public static final Integer risk_event_handleway=83000; //应对措施
	/**
	 *  自定义导出类型 start-----------------------------
	 */
	public static final String audit_fill = "audit_fill.xls";//审计填报模板
	
	public static final String overseasassettemplate = "overseasassettemplate.xlsx";//境外资产占比数据填报导出模板
	
	public static final String overseasassettemplateexamine = "overseasassettemplate.xlsx";//境外资产占比数据审核导出模板
	
	public static final String reportmanageadjusttemplate = "reportmanageadjusttemplate.xlsx";//管理口径核算数据导出
	
	public static final String MainFinancialIndicatortemplate = "MainFinancialIndicatortemplate.xlsx";//主要财务指标数据导出
	/**
	 *  自定义导出类型 end------------------------------
	 */
	
	
	/**
	 * 每次调用生成唯一的Excel 2003 格式文件名。
	 * 作为调用使用者，需要负责在文件使用完后删除，以避免在服务端带来存储空间遗失。
	 * 删除临时文件，可以使用 deleteTempFile(String) 方法。
	 * @return expt<日期时间戳>.xls
	 * @see #deleteTempFile(String)
	 */
	public static String getTemp2k3ExcelFileName() {
		return "expt" + getTimeStmpRndmTag() + ".xls";
	}
	
	/**
	 *  
	 * @return (current time "yyMMddhhmmssSSS") + (1000 * Math.random())
	 */
	public static String getTimeStmpRndmTag() {
		return new SimpleDateFormat("yyMMddhhmmssSSS").format(getCurrentTime()) + (int) (1000 * Math.random());
	}
	
	/**
	 * 
	 * @return 当前时间戳字符串
	 * @author zxt
	 * @since 2017-12-20
	 * @see #getCurrentTimeStr()
	 * @see #getSdfTimStamp()
	 */
	public static String getCurrentTimeStampStr() {
		return getSdfTimStamp().format(getCurrentTime());
	}
	
	/**
	 * "yyyy-MM-dd HH:mm:ss.SSS"
	 * @return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
	 * @since 2017-11-17
	 */
	public static SimpleDateFormat getSdfTimStamp(){
		return getNewSimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	}
	
	/**
	 * 按给定格式，产生新的 SimpleDateFormat 实例。 
	 * @param formatTemplate 
	 * @return SimpleDateFormat 新实例
	 * @author zxt
	 * @since 2017-12-20
	 */
	public static SimpleDateFormat getNewSimpleDateFormat(String formatTemplate){
		return new SimpleDateFormat(formatTemplate);
	}

	/**
	 * @return Calendar.getInstance().getTime()
	 */
	public static Date getCurrentTime() {
		return Calendar.getInstance().getTime();
	}
	
	/**
	 * 删除给定文件。
	 * @param fileName 待删除文件名。
	 * @return 是否删除成功。
	 * @see #getTemp2k3ExcelFileName()
	 * @author zxt
	 * @since 2017-09-27
	 */
	public static boolean deleteTempFile(String fileName) {
		boolean rb = false;
		try {
			File f = new File(fileName);
			if (f.exists() && f.isFile()) {
				rb = f.delete();
			}
		} catch (Exception e) {
		}
		return rb;
	}
	

	/**
	 * 财务树顶级节点的nodeID
	 * 海航实业
	 */
	public static final String BIMF_TOP_NODE_ID = "d4985136e97d11e7968906a2160000ae"; 
}
