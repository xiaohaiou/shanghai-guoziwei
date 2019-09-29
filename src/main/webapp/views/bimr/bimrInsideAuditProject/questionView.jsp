<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>查看内部审计项目相关问题</title>
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
		<span class="glyphicon glyphicon-pencil"></span>内部审计项目相关问题
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="entity" >
		<div class="label_inpt_div">
		  <h4><span style="font-weight: 1px;">查询审计项目相关问题</span></h4>
			<div class="model_part">
				<label class="w20">审计项目名称:</label>
				<label class="w25 setleft">${entity.projectName}</label><br>
<label class="w20">问题标题:</label>
				<label class="w70 setleft">${entity.problemTopic}</label><br>
				<label class="w20">存在的问题:</label>
				<label class="w70 setleft">${entity.problem}</label><br>
				
				<label class="w20">问题归属单位:</label>
				<label class="w70 setleft">${entity.problemAttrUnitName}</label><br>

				<label class="w20">是否为重大问题:</label>
				<label class="w70 setleft">${entity.isImportant==0?"否":"是"}</label><br>

				<label class="w20">是否涉嫌舞弊:</label>
				<label class="w70 setleft">${entity.isSuspected == 0?"否":"是"}</label><br>

				<label class="w20">是否问责:</label>
				<label class="w70 setleft">${entity.isBlamed==0?"否":"是"}</label><br>
				
				<label class="w20">审计建议:</label>
				<label class="w70 setleft">${entity.auditSuggest}</label><br>
				
				<label class="w20">是否需要整改:</label>
				<label class="w70 setleft">${entity.isRectify == 0 ?"否":"是"}</label><br>

				<label class="w20">整改跟踪人:</label>
				<label class="w70 setleft">${entity.rectifyTrackName}</label><br>
				
				<label class="w20">整改对接人:</label>
				<label class="w70 setleft">${entity.rectifyPicker}</label><br>

				<label class="w20">整改责任人:</label>
				<label class="w70 setleft">${entity.rectifyResponseName}</label><br>
				
				<label class="w20">整改责任部门:</label>
				<label class="w70 setleft">${entity.rectifyResponseDept}</label><br>

				<label class="w20">整改完成时限:</label>
				<label class="w70 setleft">${entity.rectifyTime}</label><br>
				
				<label class="w20">审计发现问题类型:</label>
				<label class="w70 setleft">${qtypes}</label><br>

				<label class="w20">风险动因类型:</label>
				<label class="w70 setleft">${rtypes}</label><br>
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