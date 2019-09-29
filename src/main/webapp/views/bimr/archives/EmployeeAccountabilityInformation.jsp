<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>问责信息维护</title>
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
			<span class="glyphicon glyphicon-pencil"></span>问责信息维护
			<i class="iconfont icon-guanbi"></i>
		</h4>
		<form:form id="form2" modelAttribute="entity" method="post">
			<form:hidden path="id" value="${entity.id}"/>
			<form:hidden path="createPersonId" value="${entity.createPersonId}"/>
			<form:hidden path="createPersonName" value="${entity.createPersonName}"/>
			<form:hidden path="createDate" value="${entity.createDate}"/>
			<div class="label_inpt_div">
				<div class="model_part">
					<input type="hidden" id="projectId" name="projectId" value="${entity.projectId}"/>
					<input type="hidden" id="questionId" name="questionId" value="${entity.questionId}"/>
					<input type="hidden" id="accountableId" name="accountableId" value="${entity.accountableId}"/>
					<input type="hidden" id="accountablePeopleId" name="accountablePeopleId" value="${accountablePeopleId}"/>
					<input type="hidden" id="projecttype" name="projecttype" value="${projecttype}"/>
					<input type="hidden" id="source" name="source" value="${entity.source}"/>
					<label class="w20"><span class="required"> * </span>员工姓名：</label>
						<input type="text" name="person" id="person" value="${entity.person}" class="w25" check="NotEmpty_string_30_._._._." readonly="true" />
					<label class="w20"><span class="required"> * </span>员工号：</label>
						<input type="text" name="personId" id="personId" value="${entity.personId}" class="w25" check="NotEmpty_string_30_._._._." readonly="true"/>
					<label class="w20"><span class="required"> * </span>现任职单位：</label>
						<input type="text" name="personCompany" id="personCompany" value="${entity.personCompany}" class="w25" check="NotEmpty_string_30_._._._."/>
					
					<label class="w20"><span class="required"> * </span>职位：</label>
						<input type="text" name="personPost" id="personPost" value="${entity.personPost}" class="w25"  check="NotEmpty_string_30_._._._."/>
					
					<label class="w20"><span class="required"> * </span>发生时间：</label>
						<input type="text" name="happenDate" id="happenDate" value="${entity.happenDate}" class="w25 time" readonly="readonly"  check="NotEmpty"/><br>
					
					<label class="w20"><span class="required"> * </span>问责原因：</label>
						<input type="text" name="reason" id="reason" value="${entity.reason}" class="w25"  check="NotEmpty_string_500_._._._."/>
					
					<label class="w20"><span class="required"> * </span>建议问责落实情况：</label>
						<input type="text" name="proposal" id="proposal" value="${entity.proposal}" class="w25"  check="NotEmpty_string_500_._._._."/>
					
					<label class="w20"><span class="required"> </span>处分问责呈报公文编号：</label>
						<input type="text" name="bumfNum" id="bumfNum" value="${entity.bumfNum}" class="w25" />
					
					<label class="w20"><span class="required"> </span>处分问责办文编号：</label>
						<input type="text" name="articleNum" id="articleNum" value="${entity.articleNum}" class="w25" />
						
					<label class="w20"><span class="required"> * </span>问责结果：</label>
						<input type="text" name="accountableResult" id="accountableResult" value="${entity.accountableResult}" class="w25" check="NotEmpty_string"/>

                	<label class="w20"><span class="required">  </span>附件:</label>
				<input class="w25" type="file" id="file" name="file" />
				<span><a href="${pageContext.request.contextPath}/common/download?_url=${file_Path}&_name=${file_Name}">${file_Name==null ? "" :file_Name}</a></span>
					<%-- <label class="w20">备注：</label>
						<input type="text" name="remark2" id="remark2" value="${entity.remark2}" class="w25" /> --%>
	
		
				<br>		
				</div>
				<div class="model_btn">
			     	<button class="btn btn-default model model_btn_close">关闭</button>
					<button class="btn btn-primary model_btn_ok" id="commit-1" >保存</button>
					<button class="btn btn-primary model_btn_ok" id="commit-2" >提交审核</button>
					
				</div>
			</div>
		</form:form>
		<jsp:include page="../../system/modal.jsp"/>
	</body>
<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>

<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
	<script type="text/javascript">
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		//关闭弹窗
		$(".model_btn_close").click(function () {
	        window.parent.mclose();
			return false;
		});
		
		$("#commit-1").click(function(){
			if(!vaild.all())
			{
				return false;
			}
			var projecttype=$("#projecttype").val();
			var formData = new FormData($("#form2")[0]);
			 var url = "${pageContext.request.contextPath}/bimr/archives/companyTree/saveOrUpdate";
		    $.ajax({  
			     url : url,  
			     type : "POST",  
			     data: formData,
		         async: false,  
		         cache: false,  
		         contentType: false,  
		         processData: false,  
			     success : function(data) {
				    if(data == "success"){
						win.successAlert('保存成功！',function(){
							window.parent.location.reload();
							window.parent.mclose();
							
						});
					}
			     },  
		         error: function(XMLHttpRequest, textStatus, errorThrown) {
						win.successAlert('保存失败！',function(){
							window.parent.location.reload();
							window.parent.mclose();
							
						});
				 }
			}); 
			return false;
		});
		
		$("#commit-2").click(function(){
			if(!vaild.all())
			{
				return false;
			}
			var formData = new FormData($("#form2")[0]);
			var url = "${pageContext.request.contextPath}/bimr/archives/companyTree/saveOrUpdateAndReported";
		    $.ajax({  
			     url : url,  
			     type : "POST",  
			     data: formData,
		         async: false,  
		         cache: false,  
		         contentType: false,  
		         processData: false,  
			     success : function(data) {
				    if(data == "success"){
						win.successAlert('提交审核成功！',function(){
							window.parent.location.reload();
							window.parent.mclose();
							
						});
					}
			     },  
		         error: function(XMLHttpRequest, textStatus, errorThrown) {
					 alert(XMLHttpRequest.status);
					 alert(XMLHttpRequest.readyState);
					 alert(textStatus);
				 }  
			}); 
			return false;
		});
		
		
		$('input.time').jeDate({
			format:"YYYY-MM-DD"
		});
		
	
	</script>
</html>