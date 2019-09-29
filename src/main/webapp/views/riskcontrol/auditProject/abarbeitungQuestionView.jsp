<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>审计项目整改问题查看页面</title>
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
	<c:choose>
		<c:when test="${op eq 'check'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>查看审计项目整改问题
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
	</c:choose>
	<form id="form2">
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20">审计项目名称:</label>
				<label class="w25 setleft">${ auditProjectAbarbeitungQuestion.auditProjectName}</label>
				<label class="w20">整改完成状态:</label>
				<label class="w25 setleft"><c:if test="${auditProjectAbarbeitungQuestion.abarbeitungStatus eq 1}">已完成</c:if><c:if test="${auditProjectAbarbeitungQuestion.abarbeitungStatus eq 0}">未完成</c:if></label><br>
				<label class="w20">整改负责人:</label>
				<label class="w25 setleft">${ auditProjectAbarbeitungQuestion.abarbeitungOfficer}</label>
				<label class="w20">整改时限:</label>
				<label class="w25 setleft">${ auditProjectAbarbeitungQuestion.abarbeitungTimeLimit}</label>
				<label class="w20">存在问题:</label>
				<label class="w25 setleft" style="word-wrap:break-word;">${ auditProjectAbarbeitungQuestion.existentialQuestion}</label>
				<label class="w20">审计建议:</label>
				<label class="w25 setleft" style="word-wrap:break-word;">${ auditProjectAbarbeitungQuestion.auditSuggestion}</label>
				<label class="w20">整改措施:</label>
				<label class="w25 setleft" style="word-wrap:break-word;">${ auditProjectAbarbeitungQuestion.abarbeitungMeasures}</label>
				<label class="w20">备注:</label>
				<label class="w25 setleft" style="word-wrap:break-word;">${ auditProjectAbarbeitungQuestion.remark}</label>
			</div>
			<div class="row model_btn">
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
		</div>
	</form>
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