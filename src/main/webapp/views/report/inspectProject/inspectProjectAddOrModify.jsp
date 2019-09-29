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
	<link rel="stylesheet" href="<c:url value="/css/bootstrap-select.css"/>">
	<!-- 库|插件 -->
	<!-- 新加样式 -->
	<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/workbench_model.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">

	<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	<style>
	.v-confirm-tip{
		display:none;
	}
	.model_part > *{
		margin-bottom:8px;
	}
	</style>
</head>
<body>
	<c:choose>
		<c:when test="${op eq 'modify'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>修改稽核项目基本信息
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增稽核项目基本信息
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
	
	<form:form id="form2" modelAttribute="entity" method="post" >
	<form:hidden path="id"/>
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20"><span class="required"> * </span>年份:</label>
				<input class="w25 year" type="text" name="year" id="year" value="${entity.year }" check="NotEmpty_string_._._._._." readonly="true" />
				<label class="w20"><span class="required"> * </span>稽核类型:</label>
				<span class="w25">
					<select name="inspectType" id="inspectType" class="selectpicker"  onchange="getPlanCompanys();">
						<c:forEach items="${requestScope.inspectType}" var="result">
							<option value="${result.id }"
								<c:if test="${entity.inspectType == result.id}">selected="selected"</c:if>>
								${result.description}</option>
						</c:forEach>
					</select> 
				</span>
				
				
				<label class="w20"><span class="required"> * </span>稽核公司:</label>
				<span  class="w25"> 
					<input name="compName" id="compName" value="${entity.compName }" type="hidden">
					<select name="compId" id="compId" class="selectpicker" data-live-search="true" onchange="jhCompanyChange();">
						<option value="">--请选择--</option>					
					</select> 
			    </span>
			    <label class="w20"><span class="required"> * </span>稽核单位所属核心企业:</label>
				<span class="w25">
					<select name="belongCompId" id="belongCompId" class="selectpicker" >
						<c:forEach items="${requestScope.coreCompSelect}" var="result">
								<option value="${result.id.nnodeId }"  
								<c:if test="${entity.belongCompId eq result.id.nnodeId}">selected="selected"</c:if>>
								${result.vcFullName }</option>
						</c:forEach>
					</select>
					<input type="hidden" id="belongCompName" name="belongCompName" value="${entity.belongCompName }">
				</span>
			    
				
				<label class="w20"><span class="required"> * </span>财务事项类别:</label>
				<span class="w25">
					<select name="itemType" id="itemType" class="selectpicker" >
						<c:forEach items="${requestScope.itemType}" var="result">
							<option value="${result.id }"
								<c:if test="${entity.itemType == result.id}">selected="selected"</c:if>>
								${result.description}</option>
						</c:forEach>
					</select> 
				</span>
				
				<label class="w20"><span class="required"> * </span>稽核财务事项名称:</label>
				<input class="w25" name="itemName" id="itemName" check="NotEmpty_string_._._._._." value="${entity.itemName}"/>
				
				<label class="w20"><span class="required"> * </span>稽核事项负责人:</label>
				<input class="w25" name="itemPerson" id="itemPerson" check="NotEmpty_string_._._._._." value="${entity.itemPerson }" readonly="true" onclick="mload('${pageContext.request.contextPath}/inspectproject/_selectpeople?eleId=itemPerson')"/>
				<input type="hidden" id="itemPersonId" name="itemPersonId">
				
				<label class="w20"><span class="required"> * </span>稽核事项开始日期:</label>
				<input class="w25 time" name="startTime" id="startTime" check="NotEmpty_string_._._._._." value="${entity.startTime}" readonly="true"/>
				
				<label class="w20"><span class="required"> * </span>稽核事项结束日期:</label>
				<input class="w25 time" name="endTime" id="endTime" check="NotEmpty_string_._._._._." value="${entity.endTime}" readonly="true"/>
				<br>
				<label class="w20">备注:</label>
				<textarea class="w70" rows="5" cols="" id="remark" name="remark" style="height:5em;" maxlength="500" >${entity.remark}</textarea>
				<br/>
				<label class="w20">附件:</label>
				<input class="w25" type="file" id="file1" name="file1"  />
				<br>
				<c:if test="${!empty entity.fileOne.fileName}">
						<label class="w20">已上传文件:</label>
						<span>
							<a
								href="${pageContext.request.contextPath}/common/download?_url=${entity.fileOne.filePath }&_name=${entity.fileOne.fileName }"
								data-original-title="" title="">${entity.fileOne.fileName }
							</a>
						</span>
				</c:if>
			</div>
			<div class="model_btn">
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
<script type="text/javascript" src="<c:url value="/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/select/bootstrap-select.min.js"/>"></script>

<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>
<script type="text/javascript">
		
		
		$('input.year').jeDate(
			{
				format:"YYYY"
			}
		)
		
		$('input.time').jeDate(
			{
				format:"YYYY-MM-DD"
			}
		)
				
		function commit1(){
			if(!vaild.all())
			{
				return false;
			}
			if($("#compId").val() == '' ||  $("#compId").val() == undefined){
				win.generalAlert("稽核公司必填！");
				return false;
			}
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			
			debugger;
			var belongCompName=$("#belongCompId").find("option:selected").text();
			$("#belongCompName").val(belongCompName);
			
			var formData = new FormData($("#form2")[0]);
			var url = "${pageContext.request.contextPath}/inspectproject/saveOrUpdate";
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
			     	var commitresult=JSON.parse(data);
			     	if(commitresult.result==true)
						 win.successAlert('保存成功！',function(){
								parent.location.reload(true);
								parent.mclose();
						 });
					else{
						win.errorAlert(commitresult.exceptionStr);
					}
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
		
		getPlanCompanys();
		
		/**
		 *根据年份和稽核类型获取稽核公司
		 *
		 */
		function getPlanCompanys(){
			var year = $("#year").val();
			var inspectType = $("#inspectType").val();
			$.ajax({
				url:"${pageContext.request.contextPath}/inspectproject/getReportInspectProjectPlan",
				data:{year:year,inspectType:inspectType},
				dataType:'json',
				success:function(data){
					var selectId = '${entity.compId}';
					$("#compId").children(':first').nextAll().remove();
					for(var i = 0; i < data.length; i++){
						if(selectId == data[i].id.nnodeId)
							$("#compId").append("<option value='"+data[i].id.nnodeId+"' selected='selected'>"+data[i].vcFullName+"</option>");
						else
							$("#compId").append("<option value='"+data[i].id.nnodeId+"'>"+data[i].vcFullName+"</option>");
					}
					$("#compId").selectpicker('refresh');
				},
				error:function(){
					win.errorAlert("获取稽核公司失败！");
				}
			});
		}
		function jhCompanyChange(){
			$("#compName").val($("#compId option:selected").text());
		}
	</script>
</html>