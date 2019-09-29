<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>规章制度详细页面</title>
<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
<!-- 库|插件 -->
<!-- 新加样式 -->
<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
<link rel="stylesheet" href="<c:url value="/css/workbench_model.css"/>">
<script src="<c:url value="/js/jquery-1.8.3.min.js"/>" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">
		<style type="text/css">
			.selectone{
				white-space: nowrap;
				font-size: 14px;
				cursor: pointer;
			}
			.selectone:hover, .selectone.selectedOne{
				box-sizing: border-box;
				background-color: rgba(255,255,255,0.8);
				border-bottom:1px solid rgba(0,0,0,0.5);
			}
			.selectone>* {
				display:inline-block;
				min-width: 60px;
			}
			.selectedOne{
				border-color:#4a22cc;
				color: #4a22cc;
			}
			#com_ztree span {
				padding-left:0;
			}
		</style>
		<style>
			table{
				table-layout: fixed;
			}
			table td{
				line-height:2em;
				word-wrap:break-word;
			}
		</style>
</head>
<body>
	
	<h4>
		<span class="glyphicon glyphicon-pencil"></span>规章制度详细
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form id="form2">
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20">发文单位:</label>
				<label class="w25 setleft">${bylawInfo.orgNm}</label>
				<label class="w20">所属核心企业:</label>
				<label class="w25 setleft">${bylawInfo.coreOrgNm}</label>
				<label class="w20">规章制度标题:</label>
				<label class="w25 setleft">${bylawInfo.fileName}</label>
				<label class="w20">发文时间:</label>
				<label class="w25 setleft">${bylawInfo.fileEffectiveTime}</label>
				<label class="w20">附件:</label>
				<label class="w25 setleft">${bylawInfo.fileTitle}</label>
				<label class="w20">体系类别:</label>
				<label class="w25 setleft">${bylawInfo.deptName}</label>
				<label class="w20">是否涉及层级类别:</label>
				<label class="w25 setleft">
					<c:if test="${bylawInfo.haveLevel == 1}">是</c:if>
					<c:if test="${bylawInfo.haveLevel == 0}">否</c:if>
				</label>
				<label class="w20">是否建立关联:</label>
				<label class="w25 setleft">
					<c:if test="${bylawInfo.isLinked == 1}">是</c:if>
					<c:if test="${bylawInfo.isLinked == 0 || empty bylawInfo.isLinked}">否</c:if>
				</label>
				<label class="w20">关联层级类别:</label>
				<label class="w25 setleft">
					<c:choose>
						<c:when test="${bylawInfo.fileLevel == 1}">总则</c:when>
						<c:when test="${bylawInfo.fileLevel == 2}">办法</c:when>
						<c:when test="${bylawInfo.fileLevel == 3}">规定</c:when>
						<c:when test="${bylawInfo.fileLevel == 4}">细则</c:when>
					</c:choose>
				</label>
				<label class="w20">建立的关联描述:</label>
				<label class="w25 setleft">${bylawInfo.linkDiscription}</label>
			</div>
			<div class="model_btn">
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
		</div>
	<form>

</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/vaild.js"/>"></script>
<script src="<c:url value="/js/validation.js"/>"></script>
<script type="text/javascript">
	//关闭弹窗
	$(".model_btn_close").click(function () {
        window.parent.mclose();
		return false;
	});
	$(".icon-guanbi").click(function () {
		parent.mclose();
		return false;
	});
</script>
<script type="text/javascript">

</script>
</html>