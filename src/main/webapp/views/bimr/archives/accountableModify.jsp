<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>修改员工问责</title>
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
		<span class="glyphicon glyphicon-pencil"></span>修改员工问责人员
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="entity" method="post">
		<div class="label_inpt_div">
			<div class="model_part">
				<input type="hidden" name="id" value="${entity.id}"/>
				<input type="hidden" name="projectId" value="${entity.projectId}"/>
				<input type="hidden" name="projectType" value="${entity.projectType}"/>
				<label class="w20"><span class="required"> * </span>被问责人员:</label>
				<input type="text" name="accountabilityPersion" id="accountabilityPersion" value="${entity.accountabilityPersion}" class="w25" check="NotEmpty_string_30_._._._." />
				<label class="w20"><span class="required"> * </span>被问责人员Id:</label>
				<input type="text" name="accountabilityPersionId" id="accountabilityPersionId" value="${entity.accountabilityPersionId}" class="w25" check="NotEmpty_string_30_._._._." />
				<input type="hidden" name="projecttype" value="${entity.projectType}"/>
				<br>
				
				<c:if test="${entity.projectType == 1 }">
					<input type="text"  value="${entity.questionTopic }" name="questionTopic" id="questionTopic" readonly="readonly" style="margin-left: 10%;"/>
					<input type="hidden" value="${entity.questionId }" name="questionId"/>
						<select class="w20"  name="reponsibilityLevel" id="reponsibilityLevel" class="selectpicker" style="margin-left: 15px;">
							<option value="0" <c:if test="${entity.reponsibilityLevel == 0 }">selected</c:if>>主要责任</option> 
							<option value="1" <c:if test="${entity.reponsibilityLevel == 1 }">selected</c:if>>次要责任</option>
						</select>
				</c:if>
			</div>
			<div class="model_btn">
				<button class="btn btn-primary model_btn_ok" id="commit-1" >保存</button>
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
		    var _url = "${pageContext.request.contextPath}/bimr/archives/companyTree/saveCaseAccountable";
			var formData = new FormData($("#form2")[0]);
            
		    $.ajax({  
			     url : _url,  
			     type : "POST",  
			     data:formData,
		         async: false,  
		         cache: false,  
		         contentType: false,  
		         processData: false, 
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