<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>提出合规建议情况</title>
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
				<span class="glyphicon glyphicon-pencil"></span>修改提出合规建议情况
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增提出合规建议情况
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
	
	<form:form id="form2" modelAttribute="entity" method="post" >
	<form:hidden path="id"/>
		<div class="label_inpt_div">
			<h3 class="data_title1">基本信息</h3>
			<div class="model_part">
				<label class="w20"><span class="required"> * </span>合规管理建议:</label>
				<textarea class="w70" rows="5" cols="" id="suggest" name="suggest" style="height:5em;" maxlength="500" check="NotEmpty_string_._._._._.">${entity.suggest}</textarea>
				
				<label class="w20"><span class="required"> * </span>是否需要落实整改:</label>
				<span class="w25">
					<select name="isChange" id="isChange" class="selectpicker">
						<option value="0">否</option>
						<option value="1">是</option>
					</select> 
				</span>
				<div id="forIsChange">
				<label class="w20"><span class="required"> * </span>整改跟踪人:</label>
				<input class="w25" name="followPerson" id="followPerson"  value="${entity.followPerson}" check="NotEmpty_string_._._._._."/>
				<input type="hidden" name="followPersonId" id="followPersonId"  value="${entity.followPersonId}"/>
				</div>
				
				
				
				
			</div>
			
			<div class="model_btn">
				<input type="hidden" value="${entity.complianceId }" name="complianceId" id="complianceId">
				<input type="hidden" value="${entity.id }" name="suggestId" id="suggestId">
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
		$('#followPerson').on('focus click',function(){
			mload('${pageContext.request.contextPath}/bimr/compliance/searchPerson?inputname=followPerson&inputid=followPersonId');
		});
				
		function commit1(){
			if($("#isChange").val() == 1){
				 $("#followPerson").attr("check","NotEmpty_string_._._._._.");
			}else{
			
				 $("#followPerson").removeAttr("check");
				 $("#followPerson").val("");
				 $("#followPersonId").val("");
			}
			if(!vaild.all())
			{
				return false;
			}
			$.blockUI({ message: '提交中', css: { width: '275px' } });
		
			var formData = new FormData($("#form2")[0]);
			var url = "${pageContext.request.contextPath}/bimr/compliance/saveOrUpdateSuggest";
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
			if("${entity.isChange}"!="")
				$("#isChange").val("${entity.isChange}");
				
			$("#isChange").change(function(){
				var value=$(this).val();
				if(value==0)
					$("#forIsChange").hide();
				else
					$("#forIsChange").show();
			});	
			$("#isChange").change();
		});
		
		
	</script>
</html>