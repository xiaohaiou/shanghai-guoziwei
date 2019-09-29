<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>查看检索信息</title>
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
		<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/js/jquery.form.js"/>" charset="utf-8"></script>
	</head>
	<body>
		<h4>
			<span class="glyphicon glyphicon-pencil"></span>查看检索信息
			<i class="iconfont icon-guanbi"></i>
		</h4>
		<div class="label_inpt_div">
			<div class="model_part">
			   	<form id="form1" class="row">
			       <input type="hidden" name="id" value="${id}"/> 
			   	</form>
				<!--合同记录 -->
				<jsp:include page="/views/bimr/contractMonthtemplet/contractMonth.jsp"/>
				<!--合同账单 -->
				<jsp:include page="/views/bimr/contractMonthtemplet/contractMonthList.jsp"/>
				
				<!-- 合同审核 --> 
				<jsp:include page="/views/bimr/contractMonthtemplet/contractMonthCheckList.jsp"/>
			</div>
			<div class="model_btn">
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
		</div>
		<jsp:include page="/views/system/modal.jsp"/>
	</body>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
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