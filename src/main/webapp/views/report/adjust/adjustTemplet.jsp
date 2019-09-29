<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>报表上传页面</title>
<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
<!-- 库|插件 -->
<!-- 新加样式 -->
<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
<link rel="stylesheet" href="<c:url value="/css/workbench_model.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">
<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
<style>
.inlineblock
{
	display:inline-block !important;
}

</style>

</head>

<body>
		<h4>
			<span class="glyphicon glyphicon-pencil"></span>核算模板下载
			<i class="iconfont icon-guanbi"></i>
		</h4>
	<div class="label_inpt_div">
		<form id="form2">
			<div class="model_part">
				<label class="w40">时间:</label>
				<span class="w45">
					<input  type="text" id="Date" name="Date" value="${Date}" readonly="true" class="time"/>
				</span>
				<span>
					<a href="javascript:;" onclick="query();" class="btn btn-primary model_btn_ok">查询</a>
				</span>
			</div>
	   </form>
			<div>
				<c:if test="${not empty templet}">
					<span>${ templet.fileName}</span>
				</c:if>
				<c:if test="${empty templet}">
					<span>该月份没有模板</span>
				</c:if>
			</div>
	   <div class="model_btn">
	   		<c:if test="${not empty templet}">
	   			<a href="javascript:;" class="btn btn-primary model_btn_ok"  onclick="exportout()">下载</a>
	   		</c:if>
			<button class="btn btn-default model model_btn_close">关闭</button>
		</div>
	</div>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript">
   	$(' input.time').jeDate(
		{
			format:"YYYY-MM",
			minDate:"2018-01-01"
		}
	)
	
	function exportout(){
		var form = document.forms[0];
		with (form) {
			form.action = '${pageContext.request.contextPath}/reportcommon/downloadtemplet?_url=${templet.filePath }&_name=${templet.fileName }';
			form.method = "post";
			form.submit();
		}
	}	
	function query(){
		var form = document.forms[0];
		with (form) {
			form.action = '${pageContext.request.contextPath}/reportadjust/adjustTemplet';
			form.method = "post";
			form.submit();
		}
	}
	
	$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		//关闭弹窗
		$(".model_btn_close").click(function () {
			parent.mclose();
			return false;
		});
</script>
</html>