<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>权限控制新增、编辑页面</title>
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
		<c:if test="${ op eq 'add' }">
			<span class="glyphicon glyphicon-pencil"></span>新增重点基建工程权限控制
		</c:if>
		<c:if test="${ op eq 'edit' }">
			<span class="glyphicon glyphicon-pencil"></span>编辑重点基建工程权限控制
		</c:if>
		<i class="iconfont icon-guanbi"></i>
	</h4>
	
		<form id="form2">
			<div class="label_inpt_div">
				<label class="w20"><span class="required">*</span>项目名称:</label>
				<select name="pjId" class="w25">
					<option value="">--请选择--</option>
					<c:forEach items="${projects}" var="project">
						<option value="${project.id}" <c:if test="${authorityPerson.pjId == project.id }">selected="selected"</c:if> >${project.pjName}</option>
					</c:forEach>
				</select>
				<label class="w20"><span class="required">*</span>权限类型:</label>
				<select name="personType" class="w25">
					<option value="">--请选择--</option>
					<option value="0" <c:if test="${authorityPerson.personType == 0}">selected="selected"</c:if> >上报人</option>
					<option value="1" <c:if test="${authorityPerson.personType == 1}">selected="selected"</c:if> >审核人</option>
					
				</select>
				<label class="w20"><span class="required">*</span>权限人:</label>
				<input class="w25" type="text" id="personName" name="personName" value="${authorityPerson.personName }" readonly="readonly" />
				<%-- <a href="javascript:;" onclick="mload('${pageContext.request.contextPath}/project/authorityPerson/_select')" class="btn" title="选择权限人"><img src="${pageContext.request.contextPath}/css/icon/+.svg" alt=""></a> --%>
				<a href="javascript:;" onclick="mload('${pageContext.request.contextPath}/project/authorityPerson/_select')" class="btn" title="选择权限人" style="height:10px;width:10px;line-height:0;font-size:30px;">+</a>
				<div class="model_btn">
					<input type="hidden" name="personVcEmployeeId" id="personVcEmployeeId" value="${authorityPerson.personVcEmployeeId}"/>
					<input type="hidden" name="id" value="${authorityPerson.id }" />
					<button class="btn btn-primary model_btn_ok" id="commit-1">保存</button>
					<button class="btn btn-default model model_btn_close">关闭</button>
				</div>
			</div>
		<form>
	<jsp:include page="../system/modal.jsp"/>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
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
		
		function checkcommit()
		{
			var form = document.getElementById("form2")
			if(isEmpty(form.pjId.value)){
				form.pjId.focus();
				win.generalAlert("项目名称不能为空！");
				return false;
			}
			if(isEmpty(form.personType.value)){
				form.personType.focus();
				win.generalAlert("权限类型不能为空！");
				return false;
			}
			if(isEmpty(form.personName.value)){
				form.personName.focus();
				win.generalAlert("权限人不能为空！");
				return false;
			}
			return true;
		}
		
		
		$("#commit-1").click(function(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!checkcommit())
			{
					$.unblockUI();
					return false;
			}
					//var entityBasicInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/project/authorityPerson/_save";
			
			var formData = new FormData($("#form2")[0]);
			console.log(formData);
			$.ajax({  
			     url : url,  
			     type : "POST",  
			     data: formData,
		         async: false,  
		         cache: false,  
		         contentType: false,  
		         processData: false, 
			     success : function(data) {
				     $.unblockUI();
					 win.successAlert('保存成功！',function(){
					 		parent.location.reload(true);
					 		parent.mclose();
					 });
			     },  
			     error : function(data) {
			     	win.generalAlert("保存失败！");
			     	console.log(data)
			     	$.unblockUI();
			     }  
			}); 
				

			return false;
		
		});
		
		
</script>
</html>