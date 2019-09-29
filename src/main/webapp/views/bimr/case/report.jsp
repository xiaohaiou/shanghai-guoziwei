<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>民事案件填报</title>
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
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/zTreeStyle.css"/>">
<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
</head>
<body>
	<h4>
		<span class="glyphicon glyphicon-pencil"></span>报表管理 <i
			class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="entity" method="post">
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20" style="margin-left: 10%"><span class="required" > * </span>年份：</label> <input class="time" type="text" name="year" id="year" style="width: 15%;" value="${year}" check="NotEmpty_string_._._._._." readonly="true" class="time" />
				 <label class="w20">
				 
				 <span
					class="required"> * </span>周:</label> 
					<select class=" week choose_date" name="week" id="week"  style="width: 15%;">
								<c:forEach var="num" begin="1" end="53">
									<option>${num }</option>
								</c:forEach>
								
				</select>
					
					<!-- <input type="text" name="week"
					id="week" value="" class="w25" check="NotEmpty_string_20_._._._." /> --><br>
				<br>
			</div>
			<div class="model_btn">
				<button class="btn btn-primary model_btn_ok" id="commit-1">生成报表</button>
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
		</div>
	</form:form>
</body>
<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/vaild.js"/>"
	charset="utf-8"></script>
<script type="text/javascript">
	$(function() {
		
		var week = getWeekNumber();
		var year = new Date().getFullYear();
		$("#year").val(year);
		$("#week").val(week);
	})
	$(".icon-guanbi").click(function() {
		parent.mclose();
		return false;
	});
	//关闭弹窗
	$(".model_btn_close").click(function() {
		window.parent.mclose();
		return false;
	});
	$('input.time').jeDate({
			format:"YYYY"
		});
	$("#commit-1")
			.click(
					function() {
						if (!vaild.all()) {
							return false;
						}

						$
								.ajax({
									url : "${pageContext.request.contextPath}/bimr/case/validateReport",
									type : "POST",
									data : {
										"year" : $("#year").val(),
										"week" : $("#week").val()
									},
									async : false,
									cache : false,
									dataType : "json",
									success : function(data) {
										if (data == "exist") {
										win.errorAlert("该周报已生成！");
											return false;
										}
										if (data == "success") {
											win.successAlert('周报生成成功！',
													function() {
														window.parent.location
																.reload();
														window.parent.mclose();

													});
										}
									},
									error : function(XMLHttpRequest,
											textStatus, errorThrown) {
										alert(XMLHttpRequest.status);
										alert(XMLHttpRequest.readyState);
										alert(textStatus);
									}
								});
						return false;
					});

	function getWeekNumber() {
		var today = new Date();
		var firstDay = new Date(today.getFullYear(), 0, 1);
		var dayOfWeek = firstDay.getDay();
		var spendDay = 1;
		if (dayOfWeek != 0) {
			spendDay = 7 - dayOfWeek + 1;
		}
		firstDay = new Date(today.getFullYear(), 0, 1 + spendDay);
		var d = Math.ceil((today.valueOf() - firstDay.valueOf()) / 86400000);
		var result = Math.ceil(d / 7);
		return result + 1;
	}
</script>
</html>