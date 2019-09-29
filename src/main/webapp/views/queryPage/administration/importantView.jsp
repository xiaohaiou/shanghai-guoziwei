<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>要情台账查询页面</title>
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
		<span class="glyphicon glyphicon-pencil"></span>查看要情台账
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="theEntity" method="post">
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20">年份:</label>
				<label class="w25 setleft">${ theEntity.year}</label> 
				<label class="w20">月份:</label>
				<label class="w25 setleft">${ theEntity.month}</label>
				<label class="w20">要情发生单位:</label>
				<label class="w25 setleft">${ theEntity.importantCompName}</label>
				<label class="w20">所属核心企业:</label>
				<label class="w25 setleft">${ theEntity.coreCompName}</label>
				<label class="w20">要情类别:</label>
				<label class="w25 setleft">${ theEntity.importantTypeName}</label>
				<label class="w20">要情发生时间:</label>
				<label class="w25 setleft">${ theEntity.importantDate}</label>
				<label class="w20">成员企业上报核心企业时间:</label>
				<label class="w25 setleft">${ theEntity.reportedCoreDate}</label>
				<label class="w20">核心企业上报物流总部时间:</label>
				<label class="w25 setleft">${ theEntity.reportedDepDate}</label>
				<label class="w20">物流总部上报集团总部时间:</label>
				<label class="w25 setleft">${ theEntity.reportedGroupDate}</label>
				<label class="w20">要情标题:</label>
				<label class="w25 setleft">${ theEntity.importantTitle}</label>
			</div>
			<h3 class="data_title1">创建上报信息</h3>
			<div class="model_part">
				<label class="w20">数据创建人:</label>
				<label class="w25 setleft">${ theEntity.createPersonName}</label>
				<label class="w20">创建时间:</label>
				<label class="w25 setleft">${ theEntity.createDate}</label>
				<label class="w20">数据上报人:</label>
				<label class="w25 setleft">${ theEntity.reportPersonName}</label>
				<label class="w20">上报时间:</label>
				<label class="w25 setleft">${ theEntity.reportDate}</label>
			</div>
			<c:if test="${!empty requestScope.examineList}">
				<h3 class="data_title1">审核意见</h3>
				<c:forEach items="${requestScope.examineList }" var="entityExamineview">
					<div class="model_part">
						<label class="w20">审核人:</label>
						<label class="w25 setleft">${ entityExamineview.createPersonName}</label> 
						<label class="w20">审核时间:</label>
						<label class="w25 setleft">${ entityExamineview.createDate}</label> 
						<label class="w20">审核意见:</label>
						<label class="w75 setleft">${ entityExamineview.examinestr}</label> 
					</div>
				</c:forEach>
			</c:if>
			<div class="row model_btn">
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