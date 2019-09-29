<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>新增审计问责</title>
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
	<h4>
		<span class="glyphicon glyphicon-pencil"></span>新增审计问责人员
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="BimrPersionAccountability" method="post">
		<input type="hidden" value="${bimrInsideAuditProject.id}" id="projectId"/>
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20"><span class="required"> * </span>被问责人员:</label>
				<input type="text" name="accountablePeople" id="accountablePeople" value="" class="w25" check="NotEmpty_string_30_._._._." />
				<label class="w20"><span class="required"> * </span>被问责人员Id:</label>
				<input type="text" name="accountablePeopleID" id="accountablePeopleID" value="" class="w25" check="NotEmpty_string_30_._._._." />
				<label class="w20">审计项目名称:</label>
				<input class="w25" type="text" name="projectName" id="projectName" placeholder="审计项目名称" maxlength="50" value="${bimrInsideAuditProject.auditProjectName}" readonly="readonly" />
				<br>
				<label class="w20">关联问题:</label>
				<c:forEach items="${requestScope.newList}" var="q" varStatus="Status">
					<div style="margin-left: 18%;">
					<input type="checkbox"  value="${q.id }" name="problemTopic" id="problemTopic" style="height: 12px;"/>${q.problemTopic}
						<select class="w20"  name="responsibilityLevel" id="responsibilityLevel" class="selectpicker" style="margin-left: 15px;">
							<option value="0">主要责任</option>
							<option value="1">次要责任</option>
						</select>
					</div>
					<br>
				</c:forEach>
			</div>
			<div class="model_btn">
				<c:if test="${not empty requestScope.newList }">
				<button class="btn btn-primary model_btn_ok" id="commit-1" >保存</button>
				</c:if>
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

<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
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
		
		$('input.time').jeDate({
			format:"YYYY-MM-DD"
		});
		
		//审核通过按钮.
		$("#commit-1").click(function () {
            if(!vaild.all()) {
                return false;
            }
        var questioncollect = $("input:checkbox[name='problemTopic']:checked").map(function(index,elem) {
            return $(elem).val();
        }).get().join(',');
        var levelcollect=$("input:checkbox[name='problemTopic']:checked").siblings().map(function(index,elem) {
            return $(elem).val();
        }).get().join(',');
		    var _url = "${pageContext.request.contextPath}/bimr/archives/companyTree/saveAccountable";
			var projectId = $('#projectId').val();
            var op = $('#accountablePeople').val();
             var accountablePeopleID = $('#accountablePeopleID').val();
             
		    $.ajax({  
			     url : _url,  
			     type : "POST",  
			     data:{"projectId" : projectId, "accountablePeople" : op,"questioncollect":questioncollect,"levelcollect":levelcollect,"accountablePeopleID":accountablePeopleID},
		         async: false,  
		         cache: false,  
		         dataType:"text",  
			     success : function(data) {
				    if(data == "success"){
						win.successAlert("保存成功！",function(){
							window.parent.location.reload();
							window.parent.mclose();
							
						});
					}
			     },   
		         error: function(data) {
					win.successAlert("保存失败！",function(){
							window.parent.location.reload();
							window.parent.mclose();
					});		
				 },
			}); 
			return false;
		});
         
		
</script>
</html>