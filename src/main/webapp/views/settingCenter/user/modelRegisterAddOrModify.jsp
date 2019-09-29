<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>模块注册新增、编辑页面</title>
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
				<form id="form2" action="${pageContext.request.contextPath}/model/register/_saveOrUpdate" method="post">
					<input type="hidden" id="pageNum" name="pageNum" value = "${pageNum }">
					<input type="hidden" id="qModelNum" name="qModelNum" value = "${qModelNum }">
					<input type="hidden" id="qModelName" name="qModelName" value = "${qModelName }">
					<input type="hidden" id="qSysId" name="qSysId" value = "${qSysId }">
					<input type="hidden" id="id" name="id" value = "${theRegister.id }">
					<input type="hidden" id="createTime" name="createTime" value = "${theRegister.createTime }">
					<input type="hidden" id="creator" name="creator" value ="${theRegister.creator }">
					<div class="row"><label class="col-md-1">所属系统:</label>
						<select id="sysId" name="sysId">
							<option value="">---请选择---</option>
							<c:forEach items="${ requestScope.sysRegistedList}" var="sys">
								<option value="${sys.id }" <c:if test="${sys.id eq theRegister.sysId }">selected="selected"</c:if>>${sys.sysName }</option>
							</c:forEach>
						</select>
					</div>
					<div class="row"><label class="col-md-1">模块编号:</label><input type="text" id="modelNum" name="modelNum" value="${theRegister.modelNum }" onblur="checkModelNum(this)"></div>
					<div class="row"><label class="col-md-1">模块名称:</label><input type="text" id="modelName" name="modelName" value="${theRegister.modelName }"></div>
					<div class="row"><label class="col-md-1">模块描述:</label><div class="jsms_textarea"><textarea id="modelDescription" name="modelDescription">${theRegister.modelDescription }</textarea></div></div>
				</form>
			</div>
			<div class="model_btn">
				<button class="btn btn-default model model_btn_close">关闭</button>
				<button class="btn btn-primary model_btn_ok" onclick="submitModel()">提交</button>
			</div>
		</div>
	</body>
	<script src="<c:url value="/settingCenter/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/settingCenter/js/circles.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/settingCenter/js/remodal.min.js"/>" charset="utf-8"></script>
	<link href="<c:url value="/settingCenter/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/settingCenter/js/window.js"/>"></script>
	<script type="text/javascript">
		function submitModel(){
			var modelNum = $("#modelNum").val();
			if(modelNum.indexOf(",") > 0 || modelNum.indexOf("，") > 0){
				win.errorAlert("模块编码中不可以有“，”号，请重新输入模块编码");
				return false;
			}
			if($("#sysId").val() == "") {
				win.errorAlert("所属系统不能为空");
				return false;
			}
			if(modelNum == "") {
				win.errorAlert("模块编号不能为空");
				return false;
			}
			if($("#modelName").val() == "") {
				win.errorAlert("模块名称不能为空");
				return false;
			}
			if($("#modelDescription").val() == "") {
				win.errorAlert("模块描述不能为空");
				return false;
			}
			$.ajax({
		    	url:'${pageContext.request.contextPath}/model/register/_saveOrUpdate',
		    	async:false,
		    	data:$("#form2").serialize(),
		    	dataType:'text',
		    	success:function(data){
		    		console.log(data);
		    		if(data == 'success'){
		    			parent.win.successAlert('保存成功！');
		    			window.location.href="${pageContext.request.contextPath}/model/register/_query?qModelNum=${qModelNum }&qModelName=${qModelName}&pageNum=${pageNum}"
		    		}
		    	}
		    	
		    })
		} 
		
		function checkModelNum(obj){
			var modelNum = obj.value;
			var id = $("#id").val();
			$.ajax({
			  type:'POST',
			  url:"${pageContext.request.contextPath}/model/register/_checkModelNum",
			  data:{modelNum:modelNum,
			  		id:id},
			  success:function(data){
			  	var jsonObj = eval("(" + data + ")"); 
			  	if(jsonObj.num > 0){
			  		win.errorAlert("模板编号不唯一！请重新输入！");
			  		$("#modelNum").val("");
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