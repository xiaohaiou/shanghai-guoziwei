<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>层级管理信息审核页面</title>
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
		<span class="glyphicon glyphicon-pencil"></span>层级管理信息审核
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="entityview" >
		<input type="hidden" value="${entityview.id }" name="entityid" id="entityid">
		<form:hidden path="createPersonId"/>
		<form:hidden path="createPersonName"/>
		<form:hidden path="createDate"/>
		<form:hidden path="reportPersonId"/>
		<form:hidden path="reportPersonName"/>
		<form:hidden path="reportDate"/>
		<form:hidden path="auditorPersonId"/>
		<form:hidden path="auditorPersonName"/>
		<form:hidden path="auditorDate"/>
		<div class="label_inpt_div">
			<h3 class="data_title1">层级信息</h3>
			<div class="model_part">
				<label class="w20">公司名称:</label>
				<label class="w25 setleft">${ entityview.name}</label>
				<label class="w20">层级名称:</label>
				<label class="w25 setleft">${ entityview.levelName}</label>
				<label class="w20">层级位阶:</label>
				<label class="w25 setleft">${ entityview.income}</label>
				<label class="w20">上级层级名称:</label>
				<label class="w25 setleft">${ entityview.incomeIncrease}</label>
				<label class="w20">是否存在子层级:</label>
				<label class="w25 setleft">${ entityview.fund}</label>
				<label class="w20">相同层级数量:</label>
				<label class="w25 setleft">${ entityview.coverageArea}</label>
				<label class="w20">层级类型:</label>
				<label class="w25 setleft">${ entityview.propertyName}</label>
			</div>
			<h3 class="data_title1">创建审核信息</h3>
			<div class="model_part">
				<label class="w20" style="vertical-align: top;">审核意见:</label>
				<span class="w70 ">
					<textarea  rows="5" cols="50" name="examStr" id="examStr"></textarea>
				</span>
			</div>
			<div class="model_btn">
				<c:if test="${entityview.status!=50115}">
					<button class="btn btn-primary model_btn_ok" id="commit-1">通过</button>
				</c:if>
				<button class="btn btn-primary model_btn_ok" id="commit-2">退回</button>
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
		
		$("#commit-1").click(function(){
			$.post("/shanghai-gzw/social/brand/commitexam",{entityid:$("#entityid").val(),examStr:$("#examStr").val(),examResult:50116},function(data){
				var commitresult=JSON.parse(data);
					if(commitresult.result==true){
						win.successAlert('保存成功！',function(){
							parent.location.reload(true);
							parent.mclose();
						});
					} else {
						win.errorAlert(commitresult.exceptionStr);
					}
	 		 });
			 return false;
		});
		$("#commit-2").click(function(){
			$.post("/shanghai-gzw/social/brand/commitexam",{entityid:$("#entityid").val(),examStr:$("#examStr").val(),examResult:50117},function(data){
				var commitresult=JSON.parse(data);
				if(commitresult.result==true){
					win.successAlert('保存成功！',function(){
									parent.location.reload(true);
									parent.mclose();
							});
				} else {
					win.errorAlert(commitresult.exceptionStr);
				}
	 		 });
	 		 return false;
		});
	
		var example = $(".expmale");
		example.removeClass("example").remove();
		$(".btn.new").click(function(){
			$("table tbody").append(example.clone());
		});
		$("body").on("click","i.del.btn",function(){
			$(this).closest("tr").remove();
		});
		
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