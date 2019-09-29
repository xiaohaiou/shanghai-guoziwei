<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>页面注册新增、编辑页面</title>
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/remodal.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/remodal-default-theme.css"/>">
		
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/iframe.css"/>">
	</head>
	<body>
　　		<div class = "model_content">
			<c:choose>
				<c:when test="${op eq 'modify'}">
					<h4><span class="glyphicon glyphicon-pencil"></span>编辑</h4>
				</c:when>
				<c:otherwise>
					<h4><span class="glyphicon glyphicon-pencil"></span>新增</h4>
				</c:otherwise>
			</c:choose>
			<div style="overflow:hidden;overflow-x:hidden" class="model_body">
				<form id="form2" action="${pageContext.request.contextPath}/page/register/_saveOrUpdate" method="post">
					<input type="hidden" id="pageNums" name="pageNums" value = "${pageNums }">
					<input type="hidden" id="qPageNum" name="qPageNum" value = "${qPageNum }">
					<input type="hidden" id="qPageName" name="qPageName" value = "${qPageName }">
					<input type="hidden" id="qModelId" name="qModelId" value = "${qModelId }">
					<input type="hidden" id="id" name="id" value = "${theRegister.id }">
					<input type="hidden" id="createTime" name="createTime" value = "${theRegister.createTime }">
					<input type="hidden" id="creator" name="creator" value ="${theRegister.creator }">
					<div class="row"><label class="col-md-1">所属系统:</label>
						<select id="sysId" name="sysId" onchange="getModelRegistedList(this)">
							<option value="">---请选择---</option>
							<c:forEach items="${ requestScope.sysRegistedList}" var="sys">
								<option value="${sys.id }" <c:if test="${sys.id eq theRegister.sysId }">selected="selected"</c:if>>${sys.sysName }</option>
							</c:forEach>
						</select>
					</div>
					<div class="row"><label class="col-md-1">所属模块:</label>
						<select id="modelId" name="modelId">
							<option value="">---请选择---</option>
							<c:forEach items="${ requestScope.modelRegistedList}" var="model">
								<option value="${model.id }" <c:if test="${model.id eq theRegister.modelId }">selected="selected"</c:if>>${model.modelName }</option>
							</c:forEach>
						</select>
					</div>
					<div class="row"><label class="col-md-1">所属分类:</label>
						<select id="pageType" name="pageType">
							<option value="">---请选择---</option>
							<c:forEach items="${ requestScope.pageType}" var="model">
								<option value="${model.id }" <c:if test="${model.id eq theRegister.pageType }">selected="selected"</c:if>>${model.description }</option>
							</c:forEach>
						</select>
					</div>
					<div class="row"><label class="col-md-1">页面编号:</label><input type="text" name="pageNum" id="pageNum" value="${theRegister.pageNum }" onblur="checkPageNum(this)"></div>
					<div class="row"><label class="col-md-1">页面名称:</label><input type="text" name="pageName" id="pageName" value="${theRegister.pageName }"></div>
					<div class="row"><label class="col-md-1">页面描述:</label><div class="jsms_textarea"><textarea name="pageDescription" id="pageDescription">${theRegister.pageDescription }</textarea></div></div>
				</form>
			</div>
			<div class="model_btn">
				<button class="btn btn-default model model_btn_close">关闭</button>
				<button class="btn btn-primary model_btn_ok" onclick="submitPage()">提交</button>
			</div>
		</div>
	</body>
	<script src="<c:url value="/settingCenter/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/settingCenter/js/circles.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/settingCenter/js/remodal.min.js"/>" charset="utf-8"></script>
	<link href="<c:url value="/settingCenter/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/settingCenter/js/window.js"/>"></script>
	<script type="text/javascript">
		function submitPage(){
			var pageNum = $("#pageNum").val();
			if(pageNum.indexOf(",") > 0 || pageNum.indexOf("，") > 0){
				win.errorAlert("页面编码中不可以有“，”号，请重新输入页面编码");
				return false;
			}
			if($("#sysId").val() == "") {
				win.errorAlert("所属系统不能为空");
				return false;
			}
			if($("#modelId").val() == "") {
				win.errorAlert("所属模块不能为空");
				return false;
			}
			if($("#pageType").val() == "") {
				win.errorAlert("所属分类不能为空");
				return false;
			}
			if(pageNum == "") {
				win.errorAlert("页面编码不能为空");
				return false;
			}
			if($("#pageName").val() == "") {
				win.errorAlert("页面名称不能为空");
				return false;
			}
			if($("#pageDescription").val() == "") {
				win.errorAlert("页面描述不能为空");
				return false;
			}
			$.ajax({
		    	url:'${pageContext.request.contextPath}/page/register/_saveOrUpdate',
		    	async:false,
		    	data:$("#form2").serialize(),
		    	dataType:'text',
		    	success:function(data){
		    		console.log(data);
		    		if(data == 'success'){
		    			parent.win.successAlert('保存成功！');
		    			window.location.href="${pageContext.request.contextPath}/page/register/_query?qPageNum=${qPageNum }&qPageName=${qPageName}&pageNums=${pageNums}&qModelId=${qModelId}"
		    		}
		    	}
		    	
		    })
		} 
		
		//获取此系统下，注册过的模板
		function getModelRegistedList(obj){
			var sysId = obj.value;
			var sHtml = "<option value=''>---请选择---</option>";
			if(sysId != ""){
				$.ajax({
				  type:'POST',
				  url:"${pageContext.request.contextPath}/page/register/_getModels",
				  data:{"sysId":sysId},
				  success:function(data){
				  	if(data.length > 0){//先判断是否有该系统下对应的注册模板，如果有，加入对应页面的select里面
				  		for(var i=0;i<data.length;i++){
				  		sHtml+="<option value='"+data[i].id+"'>"+data[i].modelName+"</option>";
				  		}
				  	}
				  	$("#modelId").html(sHtml);
				  }
				});
			}else{
				$("#modelId").html(sHtml);
			}
		}
		
		function checkPageNum(obj){
			var pageNum = obj.value;
			var id = $("#id").val();
			$.ajax({
			  type:'POST',
			  url:"${pageContext.request.contextPath}/page/register/_checkPageNum",
			  data:{pageNum:pageNum,
			  		id:id},
			  success:function(data){
			  	var jsonObj = eval("(" + data + ")"); 
			  	if(jsonObj.num > 0){
			  		win.errorAlert("页面编号不唯一！请重新输入！");
			  		$("#pageNum").val("");
			  	}
			  }
			});
		}
		
		//关闭弹窗
		$(".model_btn_close").click(function () {
		　　$(".bg_model").hide();
			$(".bg_model").empty();
		});
		
		$(function(){
			$("#pageType").val('${theRegister.pageType}');
		})
	</script>
</html>