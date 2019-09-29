<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>系统注册新增、编辑页面</title>
		<link rel="stylesheet" href="<c:url value="sysRegisterAddOrModify/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="sysRegisterAddOrModify/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="sysRegisterAddOrModify/css/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<link rel="stylesheet" href="<c:url value="sysRegisterAddOrModify/css/remodal.css"/>">
		<link rel="stylesheet" href="<c:url value="sysRegisterAddOrModify/css/remodal-default-theme.css"/>">
		
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="sysRegisterAddOrModify/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="sysRegisterAddOrModify/css/iframe.css"/>">
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
				<form id="form2" action="${pageContext.request.contextPath}/sys/register/_saveOrUpdate" method="post">
					<input type="hidden" id="qSysNum" name="qSysNum" value = "${qSysNum }">
					<input type="hidden" id="qSysName" name="qSysName" value = "${qSysName }">
					<input type="hidden" id="pageNum" name="pageNum" value = "${pageNum }">
					<input type="hidden" id="id" name="id" value = "${theRegister.id }">
					<input type="hidden" id="createTime" name="createTime" value = "${theRegister.createTime }">
					<input type="hidden" id="creator" name="creator" value ="${theRegister.creator }">
					<div class="row"><label class="col-md-2">系统编号:</label><input type="text" id="sysNum" name="sysNum" value="${theRegister.sysNum }" onblur="checkSysNum(this)"></div>
					<div class="row"><label class="col-md-2">系统名称:</label><input type="text" id="sysName" name="sysName" value="${theRegister.sysName }"></div>
					<div class="row"><label class="col-md-2">系统链接首页:</label><div class="jsms_textarea"><textarea id="sysUrlPage" name="sysUrlPage">${theRegister.sysUrlPage }</textarea></div></div>
					<div class="row"><label class="col-md-2">系统描述:</label><div class="jsms_textarea"><textarea id="sysDescription" name="sysDescription">${theRegister.sysDescription }</textarea></div></div>
				</form>
			</div>
			<div class="model_btn">
				<button class="btn btn-default model model_btn_close">关闭</button>
				<button class="btn btn-primary model_btn_ok" onclick="submitSys()">提交</button>
			</div>
		</div>
	</body>
	<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/circles.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/remodal.min.js"/>" charset="utf-8"></script>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script type="text/javascript">
		function submitSys(){
			console.log("sdfdsf");
			var sysNum = $("#sysNum").val();
			if(sysNum == "") {
				win.errorAlert("系统编号不能为空");
				return false;
			}
			if($("#sysName").val() == "") {
				win.errorAlert("系统名称不能为空");
				return false;
			}
			if($("#sysDescription").val() == "") {
				win.errorAlert("系统描述不能为空");
				return false;
			}
			if(sysNum.indexOf(",") > 0 || sysNum.indexOf("，") > 0){
				win.errorAlert("系统编码中不可以有“，”号，请重新输入系统编码");
				return false;
			}
		    $.ajax({
		    	url:'${pageContext.request.contextPath}/sys/register/_saveOrUpdate',
		    	async:false,
		    	data:$("#form2").serialize(),
		    	dataType:'text',
		    	success:function(data){
		    		console.log(data);
		    		if(data == 'success'){
		    			parent.win.successAlert('保存成功！');
		    			window.location.href="${pageContext.request.contextPath}/sys/register/_query?qSysNum=${qSysNum }&qSysName=${qSysName}&pageNum=${pageNum}"
		    		}
		    	}
		    	
		    })
		} 
		
		function checkSysNum(obj){
			var sysNum = obj.value;
			var id = $("#id").val();
			$.ajax({
			  type:'POST',
			  url:"${pageContext.request.contextPath}/sys/register/_checkSysNum",
			  data:{
			  		sysNum:sysNum,
			  		id:id
			  		},
			  success:function(data){
			  	var jsonObj = eval("(" + data + ")"); 
			  	if(jsonObj.num > 0){
			  		win.errorAlert("系统编号不唯一！请重新输入！");
			  		$("#sysNum").val("");
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