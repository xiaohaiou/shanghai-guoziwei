<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>查看稽核发现问题</title>
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
		<span class="glyphicon glyphicon-pencil"></span>查看领导指示
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="entity" >
		<div class="label_inpt_div">
			<h3 class="data_title1">领导指令</h3>
			<div class="model_part">
				<label class="w20">领导姓名:</label>
				<label class="w25 setleft">${ entity.orderSenderName}</label>
				<label class="w20">指示时间:</label>
				<label class="w25 setleft">${ entity.orderDate}</label>
				<br>
				<label class="w20">领导指示:</label>
				<label class="w70 setleft">${ entity.orderDescription}</label>
			</div>
			<h3 class="data_title1">回复领导指令</h3>
			<div class="model_part">
				<label class="w20">回复内容:</label>
				<textarea class="w70" rows="5" cols="" id="orderReportDescription" name="orderReportDescription" style="height:5em;" maxlength="500" ></textarea>
			</div>
			
			<div class="model_btn">
				<button class="btn btn-primary model_btn_ok" id="commit-1" >回复</button>
				<button class="btn btn-default model model_btn_close">关闭</button>
				<input type="hidden" value="${entity.id }" id="orderId" name="orderId">
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
		
		function commit1(){
			if(!vaild.all())
			{
				return false;
			}
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			
			var entityBasicInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/inspectproject/saveOrUpdateOrder";
			$.post(url, entityBasicInfo, function(data) {
				$.unblockUI();
				var commitresult=JSON.parse(data);
				if(commitresult.result==true){
				
					win.successAlert('保存成功！',function(){
							parent.location.reload();
							parent.mclose();
					});
				}
				else
				{
					win.errorAlert(commitresult.exceptionStr);
				}
			});
			return false;
		}
		
		$("#commit-1").click(function(){
			return commit1();
		});
</script>
</html>