package com.softline.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class SettingCenterBase {
	// public static String HRTop="26655";
	// public static String HRTopPrefix="0-1-26655-";
	// public static String HROutPrefix="0-1-26655--1";
	// 董事会领导
	// public static String HRTopLeader = "0-1-26655-26658-843226-";

	public static String HRTop = "855579";
	public static String HRTopPrefix = "0-1-855579-";
	public static String HROutPrefix = "0-1-855579--1";
	// 董事会领导
	public static String HRTopLeader = "0-1-855579-855580-855970-";

	// HR Sdk导数据的key和地址
	public static String HrAppsecret = "uu5xkfzdekjp2s374c9ojl8f1587oons";// app密钥，查看个人中心app key
	public static String HrURL = "http://esb.hna.net/api";// 测试环境服务接口请用调用地址
	public static String HrAccessToken = "C43F224B83874F5859947B3058039FB5D1C3327B";// 应用票据，查看个人中心app key

	// HR Sdk导数据的方法
	public static String HrMethod_EmpDirectory = "EHR_HRMService_GetEmpDirectory";
	public static String HrMethod_EmpMessageInfo = "EHR_HRMService_GetEmpMessageInfo";
	public static String HrMethod_EmpPostRecord = "EHR_HRMService_GetEmpPostRecord";
	public static String HrMethod_OrganInfo = "EHR_HRMService_GetOrganInfo";
	public static Integer Hr_Type_OrganInfo = 1221;
	public static Integer Hr_Type_EmpDirectory = 1222;
	public static Integer Hr_Type_EmpMessageInfo = 1223;
	public static Integer Hr_Type_EmpPostRecord = 1224;
	// 时间处理类
	public static SimpleDateFormat sdftime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");

	// 非实体运营企业分类
	public static final int noEntityCompanyType = 53001;
	// 流程中设立类型
	public static final int processAdd = 1510;
	// 流程中注销类型
	public static final int processCancel = 1511;
	// 流程中变更类型
	public static final int processChange = 1512;
	// 流程中划出类型
	public static final int processrollOther = 50066;
	// 流程中转让类型
	public static final int processrollOut = 50067;
	// 并购
	public static final int processMerged = 50068;
	// 转入
	public static final int processRollIn = 50069;

	// 流程中划转
	public static final int processCheckTypeGs = 3;
	// 流程中股权变动
	public static final int processCheckTypeGq = 2;
	// 流程中企业设立、并购、转入
	public static final int processCheckTypeAll = 100;

	// 流程中董事的类别
	public static final int processDirectortype = 1008;
	// 流程中监事的类别
	public static final int processSupervisortype = 1009;
	// 流程中经营班子的类别
	public static final int processRuntype = 1010;

	// 流程中密级
	public static final int processSerect = 96;
	// 流程中缓急
	public static final int processImportant = 97;
	// 流程中运营情况
	public static final int processRun = 98;
	// 流程中审核
	public static final int processCheck = 99;

	// 流程中运营情况
	public static final int checksucess = 1537;
	// 流程中审核
	public static final int checkback = 1538;

	public static int rollType = -2;

	/**
	 * 流程的类别 一级审核中
	 */
	public static final Integer processNodeFirstAuditing = 1513;
	/**
	 * 流程的类别 一级审核通过
	 */
	public static final Integer processNodeFirstAuditApproved = 1514;
	/**
	 * 流程的类别 一级审核不通过
	 */
	public static final Integer processNodeFirstAuditNotApproved = 1515;
	/**
	 * 流程的类别 待分发
	 */
	public static final Integer processNodeHandout = 1516;
	/**
	 * 流程的类别 分发中
	 */
	public static final Integer processNodeHandingout = 1517;
	/**
	 * 流程的类别 分发退回
	 */
	public static final Integer processNodeHandoutBack = 1518;
	/**
	 * 流程的类别 二级审核待发起
	 */
	public static final Integer processNodeSecondAudit = 1519;
	/**
	 * 流程的类别 二级审核中
	 */
	public static final Integer processNodeSecondAuditing = 1520;
	/**
	 * 流程的类别 二级审核通过
	 */
	public static final Integer processNodeSecondAuditApproved = 1521;
	/**
	 * 流程的类别 二级审核不通过
	 */
	public static final Integer processNodeSecondAuditNotApproved = 1522;
	/**
	 * 流程的类别审核结束
	 */
	public static final Integer processNodeFinally = 1523;

	/**
	 * 流程的类别工商待办，待审核
	 */
	public static final Integer processNodeActionTaken = 1524;

	/**
	 * 流程的类别管理员审核中
	 */
	public static final Integer processNodeActionCheck = 1525;

	/**
	 * 流程的类别管理员审核退回
	 */
	public static final Integer processNodeActionBack = 1526;

	/**
	 * 流程的类别审核结束
	 */
	public static final Integer processNodeActionFinally = 1527;

	/**
	 * 业态管理员审核
	 */
	public static final Integer processNodeManagerCheck = 50053;
	/**
	 * 业态管理员审核中的股权
	 */
	public static final Integer processNodeGQManagerCheck = 50064;

	/**
	 * 业态管理员审核中的工商
	 */
	public static final Integer processNodeGSManagerCheck = 50065;

	public static final Integer processNodeManagerCheckBack = 50054;

	public static final Integer processNodeBusinessManagerCheck = 50055;

	public static final Integer processNodeBusinessManagerCheckBack = 50056;

	public static final Integer processNodeAllFinally = 50057;

	//
	/**
	 * 公司树的图片
	 */
	public static String[] icon = { "fa fa-file icon-state-danger", "fa fa-file icon-state-warning",
			"fa fa-file icon-state-success", "fa fa-file icon-state-info", "fa fa-file icon-state-warning",
			"fa fa-file icon-state-success", "fa fa-file icon-state-info", "fa fa-file icon-state-warning",
			"fa fa-file icon-state-success", "fa fa-file icon-state-info" };

	/**
	 * 企业地域境内境外
	 */
	public static final int DomesticType = 50000;

	/**
	 * 企业实体性质
	 */
	public static final int OperationalType = 50002;

	/**
	 * 企业实体所属行业
	 */
	public static final int IndustryType = 50004;

	/**
	 * 企业新设/并购
	 */
	public static final int MergedType = 50003;

	public static final int FullTimeType = 50001;

	/**
	 * 上市公司
	 */
	public static final int listedCompany = 73;

	/**
	 * 非上市公司
	 */
	public static final int notListedCompany = 74;

	/**
	 * 企业注册状态
	 */
	public static final int companyState = 10;

	/**
	 * 企业注册状态 注销
	 */
	public static final int cancelCompany = 24;

	/**
	 * 企业注册 划出
	 */
	public static final int rollCompany = 50070;

	/**
	 * 公司信息变更类型
	 */
	public static final int companychange = 25;

	/**
	 * 公司信息变更类型中的名称
	 */
	public static final int companyName = 99;

	/**
	 * 公司信息变更类型中的地址
	 */
	public static final int address = 100;

	/**
	 * 公司信息变更类型中的法人
	 */
	public static final int legalPerson = 101;

	/**
	 * 公司信息变更类型中的注册资本
	 */
	public static final int financialCapital = 103;

	/**
	 * 公司信息变更类型中的经营范围
	 */
	public static final int businessRange = 104;

	/**
	 * 公司信息变更类型中的股东
	 */
	public static final int stock = 105;

	/**
	 * 公司信息变更类型中的股东名称
	 */
	public static final int stockName = 106;

	/**
	 * 公司信息变更类型中的董事
	 */
	public static final int director = 107;

	/**
	 * 公司信息变更类型中的监事
	 */
	public static final int supervisor = 108;

	/**
	 * 公司信息变更类型中的监事
	 */
	public static final int senior = 108;

	/**
	 * 企业类型
	 */
	public static final int companyType = 11;

	/**
	 * 备案人员职务
	 */
	public static final int personposition = 27;

	/**
	 * 是否选择
	 */
	public static final int check = 28;

	/**
	 * 董监高类别
	 */
	public static final int manageType = 90;
	/**
	 * 董监高类别 中的董事
	 */
	public static final int managedirector = 1008;
	/**
	 * 董监高类别 中的监事
	 */
	public static final int managesupervisor = 1009;
	/**
	 * 董监高类别 中的经理
	 */
	public static final int managemanage = 1010;
	/**
	 * 董监高类别 中的法定代表人
	 */
	public static final int managelegal = 1011;
	/**
	 * 董监高类别 中的股东
	 */
	public static final int managestock = 1012;

	/**
	 * 备案人员类别
	 */
	public static final int backpersonpositionType = 27;

	/**
	 * 经营业态
	 */
	public static final int bussinessFormat = 19;

	/**
	 * 公司体系
	 */
	public static final int companySystem = 30;

	/**
	 * 币种
	 */
	public static final int capitalkind = 33;

	/**
	 * 公司权属
	 */
	public static final int companyOwnership = 31;

	/**
	 * 省市区域
	 */
	public static final int placearea = 26;

	/**
	 * 印章信息
	 */
	public static final int sealType = 36;

	/**
	 * 城市信息
	 */
	public static final int cityType = 37;

	/**
	 * 公司类别
	 */
	public static final int sealcompanyType = 38;

	// public static final int testcompanyID=20;

	/**
	 * 公司类别
	 */
	public static final int mapPersonType = 39;

	/**
	 * 实缴方式
	 */
	public static final int payedMethodType = 40;

	/**
	 * 认缴方式
	 */
	public static final int subscribedMethodType = 41;
	/**
	 * 股东类型枚举类型
	 */
	public static final int shareType = 17;
	/**
	 * 股东类型枚举类型
	 */
	public static final int lendPurposeType = 8000;
	/**
	 * 附件类型添加 start-----------------------------
	 */
	// 资产类型 100~199（存资产相关文件）
	public static final Integer zc_basicinfo_typeId = 100;
	public static final Integer zc_manageinfo_typeId = 101;
	public static final Integer zc_check_typeId = 102;// 借证审核
	public static final Integer zc_give_typeId = 103;// 还证申请
	public static final Integer zc_new_typeId = 104;// 权证新增
	public static final Integer zc_mess_typeId = 105;// 权证借阅

	// 股权类型 201~299（存股权相关文件）
	public static final Integer gq_zj_typeId = 200;// 股权增减资文件上传
	public static final Integer gq_change_typeId = 201;// 股权变更文件上传
	public static final Integer gq_new_typeId = 202;// 新增股东增资文件上传
	public static final Integer gq_newChange_typeId = 203;// 新增变更股东文件上传
	public static final Integer gq_listed_change_typeId = 204;// 上市公司股权变更文件上传
	public static final Integer gq_listed_pledgee_typeId = 205;// 上市公司股权变更文件上传
	public static final Integer gq_listed_goon_typeId = 206;// 上市公司股权变更文件上传
	public static final Integer gq_listed_gs_typeId = 207;// 临时表
	public static final Integer gq_listed_shangbao_typeId = 208;// 质押上报
	public static final Integer gq_listed_zysh_typeId = 209;// 质押审核
	public static final Integer gq_lc_zj_typeId = 210;// (流程)股权增减资文件上传
	public static final Integer gq_lc_change_typeId = 211;// (流程)股权变更文件上传
	public static final Integer gq_lc_new_typeId = 212;// (流程)新增股东增资文件上传
	public static final Integer gq_lc_newChange_typeId = 213;// (流程)新增变更股东文件上传

	// 知识库类别管理管理2000~2050(知识库相关文件)
	public static final Integer gs_knowledgeBase_typeId = 2000;
	public static final Integer gs_knowledgeBase_area_typeId = 2001;

	// 商标管理2300~(工商相关文件)
	public static final Integer gs_brand_typeId = 2300;// 商标文件上传
	public static final Integer gs_process = 50000;// 流程附件
	public static final int gs_cancelComProcess = 51000;// 流程附件
	public static final int gs_changeComProcess = 51002;// 流程附件
	public static final int gs_rollComProcess = 51003;// 流程附件
	public static final Integer gs_process_Businesslicense = 51001;// 营业执照

	/**
	 * 附件类型添加 end------------------------------
	 */

	/**
	 * 数据权限类型添加 start-----------------------------
	 */
	public static final Integer zc_Authority = 1;// 资产
	public static final Integer gq_Authority = 2;// 股权
	public static final Integer gs_Authority = 3;// 工商
	public static final Integer yz_Authority = 4;// 印章
	public static final Integer da_Authority = 5;// 档案
	/**
	 * 数据权限类型添加 end------------------------------
	 */

	/**
	 * 自定义导出类型 start-----------------------------
	 */
	public static final String temp_2003export = "export.xls";// 2003格式临时文件
	public static final String temp_2007export = "export.xlsx";// 2007格式临时文件

	// ----------------------------//
	public static final String pledgeeReport001 = "pledgeeReport001.xls";// 抵押详细模板名称
	public static final String monthlyReport001 = "monthlyReport001.xls";// 股权月报模板
	public static final String zc_monthlyReport001 = "zc_monthlyReport001.xls";// 资产月报模板
	public static final String zc_Report002 = "zc_Report002.xls";// 资产统计模板
	public static final String structChangeReport001 = "structChangeReport001.xls";// 股权变动统计模板
	public static final String zc_warrant_info = "zc_warrant_info.xls";// 权证管理信息模版
	public static final String gq_sotck01 = "gq_sotck01.xls";// 股权基本信息导出
	public static final String export_investInfo_1 = "export_investInfo_1.xls";// 对外投资表模板
	/**
	 * 自定义导出类型 end------------------------------
	 */

	/**
	 * 系统路径
	 */
	public static final String url_portal = "/bim_portal";
	public static final String url_show = "/bim_show";
	public static final String url_work = "/bim_work";

	public static final String url_task = "/bim_task";

	public static Map<String, String> getHeadersInfo(HttpServletRequest request) {

		Map<String, String> map = new HashMap<String, String>();

		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}

		return map;
	}

	/**
	 * PC 系统集成中心文件上传路径
	 */
	public static final String BIM_CENTER_FILE_PATH_PC = File.separator + "opt_1" + File.separator + "bimApplication"
			+ File.separator + "pc" + File.separator;

	/**
	 * APP 系统集成中心文件上传路径
	 */
	public static final String BIM_CENTER_FILE_PATH_APP = File.separator + "opt_1" + File.separator + "bimApplication"
			+ File.separator + "app" + File.separator;

	public static final Integer page_type = 1;// 页面分类

	/**
	 * 对接集团sso参数测试------------------------------------------
	 */
	public static final String JT_SSO_LOGIN = "https://iamlogin.hnaresearch.com/ngiam-rst/cas/login"; // 登入地址

	public static final String JT_SSO_VALIDATE = "https://iamlogin.hnaresearch.com/ngiam-rst/cas/serviceValidate";// 验证地址

	public static final String JT_SSO_REGISTER = "http://10.123.16.168:8080/bim_portal/IAMLogin/login"; // 系统注册地址，集团sso服务器重定向到改系统的地址

	/**
	 * 对接集团sso参数正式------------------------------------------
	 */
//	    public static final String JT_SSO_LOGIN = "https://login.hnagroup.com/ngiam-rst/cas/login";  //登入地址
	//
//		public static final String JT_SSO_VALIDATE = "https://login.hnagroup.com/ngiam-rst/cas/serviceValidate";//验证地址
	//
//		public static final String JT_SSO_REGISTER = "https://bimc.hna.net/bim_portal/IAMLogin/login"; //系统注册地址，集团sso服务器重定向到改系统的地址
}
