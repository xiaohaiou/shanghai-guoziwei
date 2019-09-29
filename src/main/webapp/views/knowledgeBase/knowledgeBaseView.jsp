<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>知识库查看页面</title>
	<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
	<link rel="stylesheet" href="<c:url value="/css/font/iconfont.css"/>" />
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
	<span class="glyphicon glyphicon-pencil"></span>查看知识库信息
	<div class="model_body">
		<form:form id="form2" modelAttribute="knowledgeBase" method="post">
			<div class="label_inpt_div">
				<label class="w20" style="word-wrap:break-word;word-break:break-all;">标题:</label>
				<label class="w75 setleft">${ knowledgeBase.title}</label><br>
				<label class="w20" style="word-wrap:break-word;word-break:break-all;">文号:</label>
				<label class="w75 setleft">${ knowledgeBase.no}</label><br>
				<label class="w20">类型:</label>
				<label class="w25 setleft">${ knowledgeBase.typeName}</label>
				<label class="w20">区域:</label>
				<label class="w25 setleft">${ knowledgeBase.areaName}</label><br>
				<label class="w20">时间:</label>
				<label class="w25 setleft">${ knowledgeBase.doDate}</label><br>
				<label class="w20">附件:</label>
				<label class="w75 setleft"><a href="${pageContext.request.contextPath}/common/download?_url=${file_Path}&_name=${file_Name}">${file_Name==null ? "" :file_Name}</a></label>
			</div>
		</form:form>
	</div>
	<div class="row model_btn">
		<button class="btn btn-default model model_btn_close">关闭</button>
	</div>
	<jsp:include page="../system/modal.jsp"/>
</body>
<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript">	
		//关闭弹窗
		$(".model_btn_close").click(function () {
		　	parent.mclose();
			return false;
		});
	</script>
</html>