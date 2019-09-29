<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>合同信息查看及上报页面</title>
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
	<style type="text/css">
		.w75 {
			word-wrap: break-word;
		}
		.w25 {
			word-wrap: break-word;
		}
	</style>
</head>
<body>
	<c:choose>
		<c:when test="${op eq 'check'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>查看合同信息
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>上报合同信息
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
	<form:form id="form2" modelAttribute="riskControlContract" method="post">
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20">合同所属业态公司:</label>
				<label class="w25 setleft">${ riskControlContract.contractBelongCompanyName}</label>
				<label class="w20">合同所在实体企业:</label>
				<label class="w25 setleft">${ riskControlContract.contractSubordinateCompanyName}</label><br>
				<label class="w20">合同公文号:</label>
				<label class="w25 setleft">${ riskControlContract.contractDocumentNo}</label>
				<label class="w20">合同名称:</label>
				<label class="w25 setleft" style="word-wrap:break-word;word-break:break-all;">${ riskControlContract.contractName}</label><br>
				<label class="w20">合同类型:</label>
				<label class="w25 setleft">${ riskControlContract.contractTypeName}</label>
				<label class="w20">我方合同主体:</label>
				<label class="w25 setleft">${ riskControlContract.ourContractSubject}</label><br>
				<label class="w20">对方合同主体:</label>
				<label class="w25 setleft">${ riskControlContract.otherContractSubject}</label>
				<label class="w20">履行跟踪人:</label>
				<label class="w25 setleft">${ riskControlContract.trackingMan}</label><br>
				<label class="w20">合同标的总额(万元):</label>
				<label class="w25 setleft">${ riskControlContract.contractTotalAmount}</label>
				<label class="w20">是否属于重大合同:</label>
				<label class="w25 setleft"><c:if test="${riskControlContract.isMajorContract eq 1}">是</c:if><c:if test="${riskControlContract.isMajorContract eq 0}">否</c:if></label><br>
				<label class="w20">合同主要内容:</label>
				<label class="w25 setleft" style="word-wrap:break-word;word-break:break-all;">${ riskControlContract.contractContent}</label>
				<label class="w20">合同主要履约节点:</label>
				<label class="w25 setleft" style="word-wrap:break-word;word-break:break-all;">${ riskControlContract.contractNode}</label><br>
				<label class="w20">法务审核人:</label>
				<label class="w25 setleft">${ riskControlContract.legalPerson}</label>
				<label class="w20">合同签订日期:</label>
				<label class="w25 setleft">${ riskControlContract.contractSignDate}</label><br>
				<label class="w20">是否违约:</label>
				<label class="w25 setleft"><c:if test="${riskControlContract.isDefault eq 1}">是</c:if><c:if test="${riskControlContract.isDefault eq 0}">否</c:if></label><br>
				<div id="wyInfo">
					<label class="w20" style="text-align: right;">我方违约情况:</label>
					<label class="w25 setleft">${ riskControlContract.ourDefault}</label>
					<label class="w20" style="text-align: right;">对方违约情况:</label>
					<label class="w25 setleft">${ riskControlContract.otherDefault}</label><br>
					<label class="w20" style="text-align: right;">风险等级:</label>
					<label class="w25 setleft">${ riskControlContract.riskGrade}</label>
					<label class="w20" style="text-align: right;">风险处置方案及进展:</label>
					<label class="w25 setleft">${ riskControlContract.riskManage}</label><br>
					<label class="w20" style="text-align: right;">我方逾期应付款总额(万元):</label>
					<label class="w25 setleft">${ riskControlContract.ourOverPay}</label>
					<label class="w20" style="text-align: right;">我方违约产生的违约金总额(万元):</label>
					<label class="w25 setleft">${ riskControlContract.ourDefaultPay}</label><br>
					<label class="w20" style="text-align: right;">对方逾期应付款总额(万元):</label>
					<label class="w25 setleft">${ riskControlContract.otherOverPay}</label>
					<label class="w20" style="text-align: right;">对方违约产生的违约金总额(万元):</label>
					<label class="w25 setleft">${ riskControlContract.otherDefaultPay}</label>
				</div>
			</div>
			<h3 class="data_title1">创建上报信息</h3>
			<div class="model_part">
				<label class="w20">数据创建人:</label>
				<label class="w25 setleft">${ riskControlContract.createPersonName}</label>
				<label class="w20">创建时间:</label>
				<label class="w25 setleft">${ riskControlContract.createDate}</label><br>
				<c:if test="${riskControlContract.status != 50112}">
					<label class="w20">数据上报人:</label>
					<label class="w25 setleft">${ riskControlContract.reportPersonName}</label>
					<label class="w20">上报时间:</label>
					<label class="w25 setleft">${ riskControlContract.reportDate}</label>
				</c:if>
			</div>
			<c:if test="${!empty requestScope.examineList}">
				<h3 class="data_title1">审核意见列表</h3>
				<c:forEach items="${requestScope.examineList }" var="entityExamineview">
					<div class="model_part">
						<label class="w20">审核人:</label>
						<label class="w25 setleft">${ entityExamineview.createPersonName}</label> 
						<label class="w20">审核时间:</label>
						<label class="w25 setleft">${ entityExamineview.createDate}</label> 
						<label class="w20">审核结果:</label>
						<label class="w25 setleft">${ entityExamineview.examresultName}</label><br> 
						<label class="w20">审核意见:</label>
						<label class="w75 setleft" style="word-wrap:break-word;">${ entityExamineview.examinestr}</label> 
					</div>
				</c:forEach>
			</c:if>
			<div class="row model_btn">
				<button class="btn btn-primary model_btn_ok" id="commit-3" <c:if test="${op ne 'reported'}">style="display:none"</c:if>>上报</button>
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
		</div>
	</form:form>
</body>
<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript">	
		$("#commit-3").click(function(){
			exam(${riskControlContract.id});
			return false;
		});
		function exam(id){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			var url = "/shanghai-gzw/riskcontrol/contract/reported?id=" + id;
			$.post(url, function(data) {
				$.unblockUI();
				if(data == "success"){
					win.successAlert('上报成功！',function(){
						parent.location.reload();
						parent.mclose();
					});
				}else if(data == "false"){
					win.successAlert('该数据已经被上报，不能再执行当前操作',function(){
						parent.location.reload();
						parent.mclose();
					});
				}	
			});
		};
		//关闭弹窗
		$(".model_btn_close").click(function () {
			parent.mclose();
			return false;
		});
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		$(document).ready(function() {
			var isDefault = "${riskControlContract.isDefault}";
			if(isDefault == 1){
				$("#wyInfo").show();
			}else{
				$("#wyInfo").hide();
			}
		});
	</script>
</html>