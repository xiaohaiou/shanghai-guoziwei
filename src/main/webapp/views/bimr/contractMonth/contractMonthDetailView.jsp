<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>查看合同信息</title>
<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
<link rel="stylesheet" href="<c:url value="/css/font/iconfont.css"/>" />
<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
<!-- 库|插件 -->
<!-- 新加样式 -->
<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
<link rel="stylesheet" href="<c:url value="/css/workbench_model.css"/>">
<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
</head>
<body>
	<h4>
		<span class="glyphicon glyphicon-pencil"></span>查看合同信息
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="entity" >
		<div class="label_inpt_div">
			<div class="model_part">				
				<label class="w20">单位名称:</label>
				<label class="w25 setleft">${ entity.contractSubordinateCompany}</label>
			</div>	
		<h3 class="data_title1">合同基础信息（用印前）</h3>
			<div class="model_part">
				
				<label class="w20">核心企业:</label>
				<label class="w25 setleft">${ entity.contractBelongCompany}</label>
				
				<label class="w20">专业公司:</label>
				<label class="w25 setleft">${ entity.contractSubordinateCompany}</label>
				
				<label class="w20">合同公文号:</label>
				<label class="w25 setleft">${ entity.contractDocumentNo}</label>
				
				<label class="w20">合同名称:</label>
				<label class="w25 setleft">${ entity.contractName}</label>
				
				<label class="w20">我方合同主体:</label>
				<label class="w25 setleft">${ entity.ourContractSubject}</label>
				
				<label class="w20">对方合同主体:</label>
				<label class="w25 setleft">${ entity.otherContractSubject}</label>
				
				<label class="w20">是否属于集团内部企业间合同:</label>
				<label class="w25 setleft">
					<c:if test="${ entity.isCom == false}">否</c:if>
					<c:if test="${ entity.isCom == true}">是</c:if>
				</label>
				
				
				<label class="w20">项目名称:</label>
				<label class="w25 setleft">${ entity.projectName}</label>
				
				<label class="w20">合同类型:</label>
				<label class="w25 setleft">${ entity.contractTypeText}</label>
				
				<label class="w20">合同类别:</label>
				<label class="w25 setleft">${ entity.contractKindText}</label>
				
				<label class="w20">行业领域:</label>
				<label class="w25 setleft">${ entity.industrySector}</label>
				
				<label class="w20">合同履行跟踪人:</label>
				<label class="w25 setleft">${ entity.trackingMan}</label>
				
				<label class="w20">合同承办部门:</label>
				<label class="w25 setleft">${ entity.undertakeDepartment}</label>
				
				<label class="w20">合同标的金额:</label>
				<label class="w25 setleft">${ entity.contractTotalAmount}</label>
				
				<label class="w20">是否属于重大合同:</label>
				<label class="w25 setleft">${entity.isMajorContract?"是":"否" }</label>
				
				<label class="w20">主要内容:</label>
				<label class="w25 setleft">${entity.contractContent }</label>
				<br>
			<label class="w20">合同主要履约节点:</label>
				<label class="w25 setleft">${ entity.contractNode}</label>
				<label class="w20">法务审核人:</label>
				<label class="w25 setleft">${ entity.legalPerson}</label>
				
				<%-- <label class="w20">合同签订日期:</label>
				<label class="w25 setleft">${ entity.contractSignDate}</label> --%>
				<label class="w20">生效日期:</label>
				<label class="w25 setleft">${ entity.contractEffectiveDate}</label>
				
				<label class="w20">到期日期:</label>
				<label class="w25 setleft">${ entity.contractDeadlineDate}</label>
				
				<br>
			</div>
			
			
			<h3 class="data_title1">合同履行及风险处置情况（用印后）</h3>
			<div class="model_part">
				<h4 class="data_title1">履行进展</h4>
				<label class="w20">履约阶段:</label>
				<label class="w25 setleft">${ entity.performancePhase}</label>
				
				<label class="w20">是否存在违约/异常:</label>
				<label class="w25 setleft">${entity.isDefault?"是":"否" }</label>
				
				<h4 class="data_title1">我方违约情况</h4>
				
				<label class="w20">我方违约情况:</label>
				<label class="w25 setleft">${ entity.ourDefault}</label>
				
				<label class="w20">逾期违约条款约定:</label>
				<label class="w25 setleft">${ entity.overdueDefaultClauseAgreement}</label>
				
				<label class="w20">逾期应付(6个月以下):</label>
				<label class="w25 setleft">${ entity.overduePay1}</label>
				
				<label class="w20">逾期应付(6个月~1年):</label>
				<label class="w25 setleft">${ entity.overduePay2}</label>
				
				<label class="w20">逾期应付(1年~2年):</label>
				<label class="w25 setleft">${ entity.overduePay3}</label>
				
				<label class="w20">逾期应付(2年~3年):</label>
				<label class="w25 setleft">${ entity.overduePay4}</label>
				
				<label class="w20">逾期应付(3年以上):</label>
				<label class="w25 setleft">${ entity.overduePay5}</label>
				
				<label class="w20">逾期应付款总额:</label>
				<label class="w25 setleft">${ entity.ourOverPay}</label>
				
				<label class="w20">应付违约金总额:</label>
				<label class="w25 setleft">${ entity.ourDefaultPay}</label>
				
				<h4 class="data_title1">对方违约情况</h4>
				
				<label class="w20">对方违约情况:</label>
				<label class="w25 setleft">${ entity.otherDefault}</label>
				
				<label class="w20">逾期应收(6个月以下):</label>
				<label class="w25 setleft">${ entity.overdueReceivables1}</label>
				
				<label class="w20">逾期应收(6个月~1年):</label>
				<label class="w25 setleft">${ entity.overdueReceivables2}</label>
				
				<label class="w20">逾期应收(1年~2年):</label>
				<label class="w25 setleft">${ entity.overdueReceivables3}</label>
				
				<label class="w20">逾期应收(2年~3年):</label>
				<label class="w25 setleft">${ entity.overdueReceivables4}</label>
				
				<label class="w20">逾期应收(3年以上):</label>
				<label class="w25 setleft">${ entity.overdueReceivables5}</label>
				
				<label class="w20">逾期应收款总额:</label>
				<label class="w25 setleft">${ entity.otherOverPay}</label>
				
				<label class="w20">当期坏账损失:</label>
				<label class="w25 setleft">${ entity.currentBadLosses}</label>
				
				<label class="w20">对方违约产生的违约金总额:</label>
				<label class="w25 setleft">${ entity.otherDefaultPay}</label>
				
				<h4 class="data_title1">涉诉情况</h4>
				
				<label class="w20">是否涉诉:</label>
				<label class="w25 setleft">${entity.isInvolving?"是":"否" }</label>
				
				<label class="w20">涉诉案件标的金额:</label>
				<label class="w25 setleft">${ entity.involvingAccount}</label>
				
				<h4 class="data_title1">不正常履行情形及处置进展</h4>
				
				<label class="w20">风险等级:</label>
				<label class="w25 setleft">${ entity.riskGrade}</label>
				
				<label class="w20">是否属于重点关注合同:</label>
				<label class="w25 setleft">${entity.isFocus?"是":"否" }</label>
				
				<label class="w20">风险描述:</label>
				<label class="w25 setleft">${ entity.riskDescription}</label>
				
				<label class="w20">风险成因:</label>
				<label class="w25 setleft">${ entity.riskCause}</label>
				
				<label class="w20">风险类型:</label>
				<label class="w25 setleft">${ entity.riskType}</label>
				
				<label class="w20">预警日期及形式:</label>
				<label class="w25 setleft">${ entity.warningDateForm}</label>
				
				<label class="w20">风险应对策略:</label>
				<label class="w25 setleft">${ entity.riskSteategy}</label>
				
				<label class="w20">风险应对措施:</label>
				<label class="w25 setleft">${ entity.riskCountermeasures}</label>
				
				<label class="w20">风险处置方案及进展:</label>
				<label class="w25 setleft">${ entity.riskManage}</label>
				
				<label class="w20">风险是否排除及排除情况:</label>
				<label class="w25 setleft">${ entity.riskExcluded}</label>
				
				<label class="w20">业务责任人:</label>
				<label class="w25 setleft">${ entity.businessOwner}</label>
				
				<label class="w20">法务责任人:</label>
				<label class="w25 setleft">${ entity.legalResponsibilities}</label>
				
				<label class="w20">备注:</label>
				<label class="w25 setleft">${ entity.remark}</label>
				
			</div>
			
			<div class="model_btn">
				
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
		</div>
		<jsp:include page="/views/system/modal.jsp" />
	</form:form>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>
<script type="text/javascript">
		//关闭弹窗
		$(".model_btn_close").click(function () {
			parent.mclose();
			return false;
		});
		
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		
		
</script>
</html>