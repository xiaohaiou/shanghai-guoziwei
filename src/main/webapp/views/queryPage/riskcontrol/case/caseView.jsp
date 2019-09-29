<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>案件信息查询页面</title>
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
	<h4>
		<span class="glyphicon glyphicon-pencil"></span>查看案件信息
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="riskControlCase" method="post">
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20">发生案件所属业态:</label>
				<label class="w25 setleft">${ riskControlCase.caseBelongCompanyName}</label>
				<label class="w20">案件发生实体企业:</label>
				<label class="w25 setleft">${ riskControlCase.caseSubordinateCompanyName}</label><br>
				<label class="w20">原告或申诉人(申请执行人):</label>
				<label class="w25 setleft">${ riskControlCase.plaintiff}</label>
				<label class="w20">被告或被诉人(被执行人):</label>
				<label class="w25 setleft">${ riskControlCase.defendant}</label><br>
				<label class="w20">案件类型(案由):</label>
				<label class="w25 setleft">${ riskControlCase.caseTypeName}</label>
				<label class="w20">案发时间:</label>
				<label class="w25 setleft">${ riskControlCase.caseHappenDate}</label><br>
				<label class="w20">诉讼标的额(万元):</label>
				<label class="w25 setleft">${ riskControlCase.litigationAmountMoney}</label>
				<label class="w20">目前状态:</label>
				<label class="w25 setleft">${ riskControlCase.currentStateName}</label>
			</div>
			<h3 class="data_title1">创建上报信息</h3>
			<div class="model_part">
				<label class="w20">数据创建人:</label>
				<label class="w25 setleft">${ riskControlCase.createPersonName}</label>
				<label class="w20">创建时间:</label>
				<label class="w25 setleft">${ riskControlCase.createDate}</label><br>
				<c:if test="${riskControlCase.status != 50112}">
					<label class="w20">数据上报人:</label>
					<label class="w25 setleft">${ riskControlCase.reportPersonName}</label>
					<label class="w20">上报时间:</label>
					<label class="w25 setleft">${ riskControlCase.reportDate}</label>
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
	<jsp:include page="../../../system/modal.jsp"/>
</body>
<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript">	
		$("#commit-3").click(function(){
			exam(${riskControlCase.id});
			return false;
		});
		function exam(id){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			var url = "/shanghai-gzw/riskcontrol/case/reported?id=" + id;
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
	</script>
</html>