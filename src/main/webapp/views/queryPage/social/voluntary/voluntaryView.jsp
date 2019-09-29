<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>志愿服务与员工关爱数据详情查看页面</title>
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
		<span class="glyphicon glyphicon-pencil"></span>志愿服务与员工关爱数据详情
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="entityview" >
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20">年份:</label>
				<label class="w25 setleft">${ entityview.year}</label>
				<label class="w20">月份:</label>
				<label class="w25 setleft">${ entityview.month}</label>
			</div>
			<h3 class="data_title1">志愿服务活动量</h3>
			<div class="model_part">
				<label class="w20">志愿服务活动累计次数(次):</label>
				<label class="w25 setleft">${ entityview.serviceCount}</label>
				<label class="w20">志愿服务活动累计时长(小时):</label>
				<label class="w25 setleft">${ entityview.serviceDuration}</label>
				<label class="w20">志愿服务活动累计人次(人):</label>
				<label class="w25 setleft">${ entityview.servicePerson}</label>
			</div>
			<h3 class="data_title1">员工关爱活动覆盖度</h3>
			<div class="model_part">
				<label class="w20">员工关爱活动总数(次):</label>
				<label class="w25 setleft">${ entityview.careCount}</label>
				<label class="w20">员工关爱活动总参与人次(人):</label>
				<label class="w25 setleft">${ entityview.carePerson}</label>
				<label class="w20">员工关爱活动人均参与度(人次):</label>
				<label class="w25 setleft">${ entityview.careParticipation}</label>
			</div>
			<h3 class="data_title1">创建上报信息</h3>
			<div class="model_part">
				<label class="w20">数据创建人:</label>
				<label class="w25 setleft">${ entityview.createPersonName}</label>
				<label class="w20">创建时间:</label>
				<label class="w25 setleft">${ entityview.createDate}</label>
				<c:if test="${ !empty entityview.reportPersonName }">
					<label class="w20">数据上报人:</label>
					<label class="w25 setleft">${ entityview.reportPersonName}</label>
					<label class="w20">上报时间:</label>
					<label class="w25 setleft">${ entityview.reportDate}</label>
				</c:if>
			</div>
			<c:if test="${ !empty entityExamineviewlist }">
				<h3 class="data_title1">审核意见</h3>
				<c:forEach items="${ requestScope.entityExamineviewlist}" var="sys" varStatus="status">
				<div class="model_part">
					<label class="w20">审核人:</label>
					<label class="w25 setleft">${ sys.createPersonName}</label>
					<label class="w20">审核时间:</label>
					<label class="w25 setleft">${ sys.createDate}</label>
					<label class="w20">审核意见:</label>
					<label class="w75 setleft" style="word-wrap:break-word;">${ sys.examinestr}</label>
				</div>
				</c:forEach>
			</c:if>
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