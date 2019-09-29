<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>查看合规整改问题维护</title>
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
		<span class="glyphicon glyphicon-pencil"></span>查看合规整改问题维护
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="entity">
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20">提出合规建议情况:</label> 
				<label class="w25 setleft">${entity.suggest}</label></br>
				<label class="w20">整改跟踪人:</label> 
				<label class="w25 setleft">${entity.followPerson}</label></br>
				<label class="w20">整改对接人:</label>
				<label class="w25 setleft">${entity.abutmentPerson}</label></br>
				<label class="w20">整改责任人:</label>
				<label class="w25 setleft">${entity.accountabilityPerson}</label></br>
				<label class="w20">整改责任单位:</label>
				<label class="w25 setleft">${entity.accountabilityDep}</label></br>
				<label class="w20">整改完成预计时限:</label>
				<label class="w25 setleft">${entity.changeLastTime}</label></br>
				<label class="w20">整改完成状态:</label>
				<td style="text-align: left;">
				<c:if test="${entity.changeStatus == 0}">未整改</c:if>
				<c:if test="${entity.changeStatus == 1}">已整改</c:if>
				<c:if test="${entity.changeStatus == 2}">整改中</c:if></td></br>
				<label class="w20">整改工作进展:</label>
				<label class="w25 setleft">${entity.changeProgress}</label>
			</div>
			</br>
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
	$(".model_btn_close").click(function() {
		parent.mclose();
		return false;
	});
	$(".icon-guanbi").click(function () {
		parent.mclose();
		return false;
	});
	 
</script>
</html>