<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>核心品牌数据详情查看页面</title>
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
		<span class="glyphicon glyphicon-pencil"></span>核心品牌数据详情
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="entityview" >
		<div class="label_inpt_div">
			<h3 class="data_title1">核心品牌信息</h3>
			<div class="model_part">
				<label class="w20">核心品牌名称:</label>
				<label class="w25 setleft">${ entityview.name}</label>
				<label class="w20">核心品牌类别:</label>
				<label class="w25 setleft">${ entityview.typeName}</label>
				<label class="w20">核心品牌位阶:</label>
				<label class="w25 setleft">${ entityview.levelName}</label>
				<label class="w20">年收入:</label>
				<label class="w25 setleft">${ entityview.income}</label>
				<label class="w20">年收入增长率:</label>
				<label class="w25 setleft">${ entityview.incomeIncrease}</label>
				<label class="w20">公司资产:</label>
				<label class="w25 setleft">${ entityview.fund}</label>
				<label class="w20">市场覆盖区域:</label>
				<label class="w25 setleft">${ entityview.coverageArea}</label>
				<label class="w20">行业地位:</label>
				<label class="w25 setleft">${ entityview.position}</label>
				<label class="w20">核心品牌属性:</label>
				<label class="w25 setleft">${ entityview.propertyName}</label>
				<label class="w20">核心品牌抽屉类别:</label>
				<label class="w25 setleft">${ entityview.drawerType}</label>
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
			<h3 class="data_title1">审核意见列表</h3>
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