<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>稽核项目</title>
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
</head>
<body>
	<c:choose>
		<c:when test="${op eq 'modify'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>修改稽核发现问题
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增稽核发现问题
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
	
	<form:form id="form2" modelAttribute="entity"  method="post">
	<%-- <form:hidden path="id"/> --%>
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20"><span class="required"> * </span>存在问题:</label>
				<textarea class="w70" rows="5" id="problem" name="problem" style="height:5em;" maxlength="500" check="NotEmpty_string_._._._._.">${entity.problem }</textarea>
				<br>
				
				
				<label class="w20"><span class="required"> * </span>是否问责:</label>
				<span  class="w25"> 
					<select name="isAccountability" id="isAccountability" class="selectpicker">
							<option value="0">否</option>
							<option value="1">是</option>
					</select>
			    </span>
			    <label class="w20">问责人员:</label>
				<input class="w25" name="accountabilityPerson" id="accountabilityPerson"  value="${entity.accountabilityPerson}" readonly="true" onclick="mload('${pageContext.request.contextPath}/inspectproject/_selectpeople?eleId=accountabilityPerson')"/>
			    <input type="hidden" id="accountabilityPersonId" name="accountabilityPersonId" value="${entity.accountabilityPersonId }">
			    
			    <label class="w20"><span class="required"> * </span>是否整改:</label>
				<span  class="w25"> 
					<select name="isChange" id="isChange" class="selectpicker">
							<option value="0">否</option>
							<option value="1">是</option>
					</select>
			    </span>
			    <label class="w20">整改跟踪人:</label>
				<input class="w25" name="changeFollowPerson" id="changeFollowPerson"  value="${entity.changeFollowPerson}" readonly="true" onclick="mload('${pageContext.request.contextPath}/inspectproject/_selectpeople?eleId=changeFollowPerson')"/>
				<input type="hidden" id="changeFollowPersonId" name="changeFollowPersonId" value="${entity.changeAbutmentPersonId}">
				
				<label class="w20">整改对接人:</label>
				<input class="w25" name="changeAbutmentPerson" id="changeAbutmentPerson"  value="${entity.changeAbutmentPerson}" readonly="true" onclick="mload('${pageContext.request.contextPath}/inspectproject/_selectpeople?eleId=changeAbutmentPerson')"/>
				<input type="hidden" id="changeAbutmentPersonId" name="changeAbutmentPersonId" value="${entity.changeAbutmentPersonId}">
				
				<label class="w20">整改时限:</label>
				<input class="w25 time" name="changeLastTime" id="changeLastTime"  value="${entity.changeLastTime}" readonly="true"/>
				<br>
				<label class="w20"><span class="required"> * </span>稽核建议:</label>
				<textarea class="w70" rows="5" cols="" id="changeContent" name="changeContent" style="height:5em;" maxlength="500" check="NotEmpty_string_._._._._.">${entity.changeContent}</textarea>
				
			</div>
			<div class="model_btn">
				<button class="btn btn-primary model_btn_ok" id="commit-1" >保存</button>
				<button class="btn btn-default model model_btn_close">关闭</button>
				<input type="hidden" name="inspectProjectId" id="inspectProjectId"  value="${entity.inspectProjectId}" />
				<input type="hidden" name="problemId" id="problemId"  value="${entity.id}" />
			</div>
		</div>
		
	</form:form>
	
	<jsp:include page="/views/system/modal.jsp" />
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>

<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
<script type="text/javascript">
		
		$('input.time').jeDate(
			{
				format:"YYYY-MM-DD"
			}
		)
				
		function commit1(){
			debugger;
			if(!vaild.all())
			{
				return false;
			}
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			
			var entityBasicInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/inspectproject/saveOrUpdateProblem";
			$.post(url, entityBasicInfo, function(data) {
					debugger;
					$.unblockUI();
					var commitresult=JSON.parse(data);
					if(commitresult.result==true)
						win.successAlert('保存成功！',function(){
								parent.mclose();
								parent.location.reload();
						});
					else
						{
							win.errorAlert(commitresult.exceptionStr);
						}
			});
			return false;
		}
		
		//关闭弹窗
		$(".model_btn_close").click(function () {
		　	parent.mclose();
			return false;
		});
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		
		$("#commit-1").click(function(){
			return commit1();
		});
		
		$(function(){
			$("#isAccountability").val('${entity.isAccountability}');
			$("#isChange").val('${entity.isChange}');
		})
	</script>
</html>