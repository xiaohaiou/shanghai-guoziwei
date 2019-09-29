<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>功能注册新增、编辑页面</title>
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
				<form id="form2" action="${pageContext.request.contextPath}/page/code/_saveOrUpdate" method="post">
					<input type="hidden" id="pageNum" name="pageNum" value = "${pageNum }">
					<input type="hidden" id="qCodeName" name="qCodeName" value = "${qCodeName }">
					<input type="hidden" id="qModelId" name="qModelId" value = "${qModelId }">
					<input type="hidden" id="qPageId" name="qPageId" value = "${qPageId }">
					<input type="hidden" id="id" name="id" value = "${theRegister.id }">
					<div class="row"><label class="col-md-1">所属系统:</label>
						<select id="sysId" name="sysId" onchange="getModelRegistedList(this)">
							<option value="">---请选择---</option>
							<c:forEach items="${ requestScope.sysRegistedList}" var="sys">
								<option value="${sys.id }" <c:if test="${sys.id eq theRegister.sysId }">selected="selected"</c:if>>${sys.sysName }</option>
							</c:forEach>
						</select>
					</div>
					<div class="row"><label class="col-md-1">所属模块:</label>
						<select id="modelId" name="modelId" onchange="getPageRegistedList(this)">
							<option value="">---请选择---</option>
							<c:forEach items="${ requestScope.modelRegistedList}" var="model">
								<option value="${model.id }" <c:if test="${model.id eq theRegister.modelId }">selected="selected"</c:if>>${model.modelName }</option>
							</c:forEach>
						</select>
					</div>
					<div class="row"><label class="col-md-1">所属页面:</label>
						<select id="pageId" name="pageId">
							<option value="">---请选择---</option>
							<c:forEach items="${ requestScope.pageRegistedList}" var="page">
								<option value="${page.id }" <c:if test="${page.id eq theRegister.pageId }">selected="selected"</c:if>>${page.pageName }</option>
							</c:forEach>
						</select>
					</div>
					<div class="row"><label class="col-md-1">功能编码:</label><input type="text" id="code" name="code" value="${theRegister.code }" onblur="checkPagecodeNum(this)"></div>
					<div class="row"><label class="col-md-1">功能名称:</label><input type="text" id="codeName" name="codeName" value="${theRegister.codeName }"></div>
					<div class="row"><label class="col-md-1">功能排序:</label><input type="text" id="seqNo" name="seqNo" value="${theRegister.seqNo }"></div>
				</form>
			</div>
			<div class="model_btn">
				<button class="btn btn-default model model_btn_close">关闭</button>
				<button class="btn btn-primary model_btn_ok" onclick="submitCode()">提交</button>
			</div>
		</div>
	</body>
	<script src="<c:url value="/settingCenter/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/settingCenter/js/circles.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/settingCenter/js/remodal.min.js"/>" charset="utf-8"></script>
	<link href="<c:url value="/settingCenter/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/settingCenter/js/window.js"/>"></script>
	<script type="text/javascript">
		function submitCode(){
			var code = $("#code").val();
			if(code.indexOf(",") > 0  || code.indexOf("，") > 0){
				win.errorAlert("功能编码中不可以有“，”号，请重新输入功能编码");
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
			if($("#pageId").val() == "") {
				win.errorAlert("所属页面不能为空");
				return false;
			}
			if(code == "") {
				win.errorAlert("功能编号不能为空");
				return false;
			}
			if($("#codeName").val() == "") {
				win.errorAlert("功能名称不能为空");
				return false;
			}
			if($("#seqNo").val() == "") {
				win.errorAlert("功能排序不能为空");
				return false;
			}
			var reg = new RegExp("^[0-9]*$");
			if(!reg.test($("#seqNo").val())){
		    	win.errorAlert("功能排序只能输入数字，请重新输入");
				return false;
		    }
			$.ajax({
		    	url:'${pageContext.request.contextPath}/page/code/_saveOrUpdate',
		    	async:false,
		    	data:$("#form2").serialize(),
		    	dataType:'text',
		    	success:function(data){
		    		if(data == 'success'){
		    			parent.win.successAlert('保存成功！');
		    			window.location.href="${pageContext.request.contextPath}//page/code/_query?qCodeName=${qCodeName }&qModelId=${qModelId}&pageNum=${pageNum}&qPageId=${qPageId}"
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
			$("#pageId").html("<option value=''>---请选择---</option>");
		}
		
		//获取此模板下，注册过的页面
		function getPageRegistedList(obj){
			var modelId = obj.value;
			var sHtml = "<option value=''>---请选择---</option>";
			if(modelId != ""){
				$.ajax({
				  type:'POST',
				  url:"${pageContext.request.contextPath}/page/code/_getPages",
				  data:{"modelId":modelId},
				  success:function(data){
				  	if(data.length > 0){//先判断是否有该系统下对应的注册页面，如果有，加入对应页面的select里面
				  		for(var i=0;i<data.length;i++){
				  		sHtml+="<option value='"+data[i].id+"'>"+data[i].pageName+"</option>";
				  		}
				  	}
				  	$("#pageId").html(sHtml);
				  }
				});
			}else{
				$("#pageId").html(sHtml);
			}
		}
		
		function checkPagecodeNum(obj){
			var pagecodeNum = obj.value;
			var id = $("#id").val();
			$.ajax({
			  type:'POST',
			  url:"${pageContext.request.contextPath}/page/code/_checkPagecodeNum",
			  data:{pagecodeNum:pagecodeNum,
			  		id:id},
			  success:function(data){
			  	var jsonObj = eval("(" + data + ")"); 
			  	if(jsonObj.num > 0){
			  		win.errorAlert("功能编号不唯一！请重新输入！");
			  		$("#code").val("");
			  	}
			  }
			});
		}
		
		//关闭弹窗
		$(".model_btn_close").click(function () {
		　　$(".bg_model").hide();
			$(".bg_model").empty();
		});
	</script>
</html>