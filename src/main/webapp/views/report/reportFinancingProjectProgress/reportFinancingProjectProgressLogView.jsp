<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>融资项目进展更新日志数据</title>
<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
<!-- 库|插件 -->
<!-- 新加样式 -->
<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
<link rel="stylesheet" href="<c:url value="/css/workbench_model.css"/>">
<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	<style type="text/css">
		.tablebox table tr:first-child {
		    background: rgba(0, 0, 0, 0);
		}
		.tablebox table tr:first-child:hover {
		    background: #f2f8ff;
		}
	</style>
</head>
<body>
	<h4>
		<span class="glyphicon glyphicon-pencil"></span>查看融资项目进展更新日志
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<div class="label_inpt_div">
		<form:form id="form2" modelAttribute="entityview" >
			<h3 class="data_title1">融资项目基本信息
			</h3>
			<div class="model_part">
				<label class="w20">单位名称:</label>
				<label class="w25 setleft">${ entityview.coreOrgname}</label> 
				<label class="w20">类别:</label>
				<label class="w25 setleft">${ entityview.categoryName}</label> 
				<label class="w20">操作主体:</label>
				<label class="w25 setleft">${ entityview.operateOrgname}</label>
				<label class="w20">融资主体:</label>
				<label class="w25 setleft">${ entityview.financingOrgname}</label>
				<label class="w20">模式:</label>
				<label class="w25 setleft">${ entityview.pattern}</label> 
				<label class="w20">金融机构:</label>
				<label class="w25 setleft">${ entityview.financialInstitution}</label> 
				<label class="w20">规模(亿元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.scale}"  pattern="#,##0.0000" /></label>
				<label class="w20">预计下账时间:</label>
				<label class="w25 setleft">${entityview.expectAccountDate }</label>
				<label class="w20">项目进展:</label>
				<label class="w25 setleft">${ entityview.projectProgressName}</label> 
			</div>
			<h3 class="data_title1">融资项目进展更新日志
			</h3>
			<div class="model_part">
				<div id="reportFinancingProjectProgressLogDetailList">
				
				</div>
			</div>
			<div class="model_btn">
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
		</form:form>
	</div>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript">
		var data1 = ${data1};
		if(data1 == 1){
			data1 = [];
		}
		
		function initNodeList(v,pageNums,op,data){
		var stringList = "";
		if(data.length != 0){
			stringList = JSON.stringify(data);
		}
		$.ajax({
			data:{
				stringList:stringList,
				pageNums:pageNums,
				op:op
			},
			url:"${pageContext.request.contextPath}/"+v+"/viewList",
			type:"POST",
			dataType:"text",
			async:false,
			success:function(data){
				$("#"+v+"List").children().remove();
				$("#"+v+"List").append(data);
			}
		});	
	}
	initNodeList('reportFinancingProjectProgressLogDetail',1,"",data1);//初始化筹资性流入明细列表
	
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