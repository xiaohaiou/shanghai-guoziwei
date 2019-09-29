<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>合同范本查看页面</title>
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
				<span class="glyphicon glyphicon-pencil"></span>查看合同范本信息
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>上报合同范本信息
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
	<form:form id="form2" modelAttribute="bimrContractModel" method="post">
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20">单位名称:</label>
				<label class="w25 setleft">${ bimrContractModel.companyName}</label><br>
				<label class="w20">是否内部授课人:</label>
				<label class="w25 setleft">
					<c:if test="${bimrContractModel.contractType eq 1}">租赁合同</c:if>
					<c:if test="${bimrContractModel.contractType eq 2}">买卖合同</c:if>
					<c:if test="${bimrContractModel.contractType eq 3}">财务合同</c:if>
					<c:if test="${bimrContractModel.contractType eq 4}">技术合同</c:if>
					<c:if test="${bimrContractModel.contractType eq 5}">融资租赁合同</c:if>
				</label><br>
				<label class="w20">pdf附件:</label>
				<label class="w25 setleft"><a href="${pageContext.request.contextPath}/common/download?_url=${file_Path1}&_name=${file_Name1}">${file_Name1==null ? "" :file_Name1}</a></label><br>
				<label class="w20">空白模板:</label>
				<label class="w25 setleft"><a href="${pageContext.request.contextPath}/common/download?_url=${file_Path2}&_name=${file_Name2}">${file_Name2==null ? "" :file_Name2}</a></label><br>
			</div>
			<h3 class="data_title1">创建信息</h3>
			<div class="model_part">
				<label class="w20">数据创建人:</label>
				<label class="w25 setleft">${ bimrContractModel.createPersonName}</label>
				<label class="w20">创建时间:</label>
				<label class="w25 setleft">${ bimrContractModel.createDate}</label><br>
			</div>
			
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