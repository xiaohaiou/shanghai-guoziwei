<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>查看内部审计项目周报</title>
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
		<span class="glyphicon glyphicon-pencil"></span>内部审计项目周报
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="entity" >
		<div class="label_inpt_div">
		  <h4><span style="font-weight: 1px;">审计项目周报</span></h4>
			<div class="model_part">
				<label class="w20">审计项目名称:</label>
				<label class="w70 setleft">${entity.projectName}</label>
				
			

				<label class="w20">周开始日期:</label>
				<label class="w25 setleft">${entity.weekStartDate}</label>
				<label class="w20">周结束日期:</label>
				<label class="w25 setleft">${entity.weekEndDate}</label>
				
				<label class="w20">周审计累计发现问题数(个):</label>
				<label class="w25 setleft">${entity.problemCount}</label>
				<label class="w20">周建议问责人员累计数(人):</label>
				<label class="w25 setleft">${entity.accountPerson}</label><br>
				
				<label class="w20">项目计划结束日期:</label>
				<label class="w70 setleft">${entity.projectEndDate}</label><br>

				<label class="w20">项目状态:</label>
				<label class="w70 setleft">${entity.projectStatusProgressName}</label>
				<br>
				<label class="w20">本周进展:</label>
				<label class="w70 setleft">${entity.projectprogress}</label>
				
				<label class="w20">审计发现的重大问题或可疑事项:</label>
				<label class="w70 setleft">${entity.importantAndSuspicious}</label><br>

				<label class="w20">审计工作面临的困难和应对措施:</label>
				<label class="w70 setleft">${entity.difficultAndMeasures}</label><br>

				<label class="w20">下周工作计划:</label>
				<label class="w70 setleft">${entity.nextWeekPlan}</label>
			</div>
			<div class="model_btn">
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
		</div>
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