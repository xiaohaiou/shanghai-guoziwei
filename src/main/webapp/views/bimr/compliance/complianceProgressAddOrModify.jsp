<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>审计项目周进展</title>
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
<body style="height:500px;">
	<c:choose>
		<c:when test="${op eq 'modify'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>修改周进展
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增周进展
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
	
	<form:form id="form2" modelAttribute="entity" method="post" >
		<div class="label_inpt_div">
			<h3 class="data_title1">基本信息</h3>
			<div class="model_part">
				
				<label class="w20"><span class="required"> * </span>周数:</label>
				<span class="w25">
					<select name="week" id="week" class="selectpicker">
						
					</select> 
				</span>
				<br/>
				<label class="w20"><span class="required"> * </span>调查反映风险事项描述:</label>
				<textarea class="w70" rows="5" cols="" id="content" name="content" style="height:5em;" maxlength="500" check="NotEmpty_string_._._._._.">${entity.content}</textarea>
				<label class="w20">附件:</label>
				<input class="w25" type="file" id="file1" name="file1"  />
				<br>
				<c:if test="${!empty entity.fileOne.fileName}">
					<label class="w20">已上传文件:</label>
					<label class="w70 setleft"><a href="${pageContext.request.contextPath}/common/download?_url=${entity.fileOne.filePath }&_name=${entity.fileOne.fileName }"
								data-original-title="" title="">${entity.fileOne.fileName }</a></label>
				</c:if>
			</div>
			
			
			<div class="model_btn">
				<input type="hidden" value="${entity.complianceId }" name="complianceId" id="complianceId">
				<input type="hidden" value="${entity.id }" name="progressId" id="progressId">
				<button class="btn btn-primary model_btn_ok" id="commit-1" >保存</button>
				<button class="btn btn-default model model_btn_close">关闭</button>
				
			</div>
		</div>
		
	</form:form>
	<jsp:include page="/views/system/modal.jsp" />
	<!-- <style>
		[data-remodal-id=modal] iframe {
			width:80%;
			height:500px;
		}
	</style> -->
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>

<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
<script type="text/javascript">
		
				
		function commit1(){
			if(!vaild.all())
			{
				return false;
			}
			$.blockUI({ message: '提交中', css: { width: '275px' } });
		
			var formData = new FormData($("#form2")[0]);
			var url = "${pageContext.request.contextPath}/bimr/compliance/saveOrUpdateProgress";
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
			     	$.unblockUI();
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
		
		$("#commit-1").click(commit1);
		
		
		
	
		$(function(){
			for(var i=1;i<=53;i++){
				$("#week").append("<option value='"+i+"'>"+i+"</option>");
			}
		
			if("${entity.week}"!="")
				$("#week").val("${entity.week}")
		});
		
		
	</script>
</html>