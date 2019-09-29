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
<link rel="stylesheet"
	href="<c:url value="sysRegisterAddOrModify/css/lin_1.css"/>">
<link rel="stylesheet"
	href="<c:url value="sysRegisterAddOrModify/css/bootstrap.min.css"/>" />
<link rel="stylesheet"
	href="<c:url value="sysRegisterAddOrModify/css/font/iconfont.css"/>" />
<!-- 库|插件 -->
<link rel="stylesheet"
	href="<c:url value="sysRegisterAddOrModify/css/remodal.css"/>">
<link rel="stylesheet"
	href="<c:url value="sysRegisterAddOrModify/css/remodal-default-theme.css"/>">

<!-- 新加样式 -->
<link rel="stylesheet"
	href="<c:url value="sysRegisterAddOrModify/css/bootstrap_self.css"/>">
<link rel="stylesheet"
	href="<c:url value="sysRegisterAddOrModify/css/iframe.css"/>">
</head>
<body>
	<div class="model_content">
		<c:choose>
			<c:when test="${op eq 'modify'}">
				<h4>
					<span class="glyphicon glyphicon-pencil"></span>编辑
				</h4>
			</c:when>
			<c:otherwise>
				<h4>
					<span class="glyphicon glyphicon-pencil"></span>新增
				</h4>
			</c:otherwise>
		</c:choose>
		<div style="overflow: hidden; overflow-x: hidden" class="model_body">
			<form id="form2"
				action="${pageContext.request.contextPath}/portal/user/_saveOrUpdate"
				method="post">
				<input type="hidden" id="qVcAccount" name="qVcAccount"
					value="${qVcAccount }"> <input type="hidden" id="qVcName"
					name="qVcName" value="${qVcName }"> <input type="hidden"
					id="pageNum" name="pageNum" value="${pageNum }"> <input
					type="hidden" id="id" name="id" value="${thisUsers.id }"> <input
					type="hidden" id="createDate" name="createDate"
					value="${thisUsers.createDate }"> <input type="hidden"
					id="createPersonId" name="createPersonId"
					value="${thisUsers.createPersonId }"> <input type="hidden"
					id="createPersonName" name="createPersonName"
					value="${thisUsers.createPersonName }">
				<div class="row">
					<label class="col-md-2">账号:</label><input type="text"
						id="vcAccount" name="vcAccount" value="${thisUsers.vcAccount }"
						onblur="checkAcAccount()" /><label type="hidden" id="checkAccount"></label>
				</div>
				<div class="row">
					<label class="col-md-2">姓名:</label><input type="text" id="vcName"
						name="vcName" value="${thisUsers.vcName }">
				</div>
				<div class="row">
					<label class="col-md-2">性别:</label><input type="radio" name="csex" id="csex"
						value="1" <c:if test='${thisUsers.csex == 1||thisUsers.csex==null}'>checked</c:if> /> 男
					<input type="radio" name="csex" value="0"
						<c:if test='${thisUsers.csex == 0}'>checked</c:if> />女
				</div>
				<div class="row">
					<label class="col-md-2">工号:</label><input id="vcEmployeeId"
						name="vcEmployeeId" value="${thisUsers.vcEmployeeId }"
						onblur="checkAcAccount()" />
				</div>
				<div class="row">
					<label class="col-md-2">所属公司:</label><input id="nnodeId"
						name="nnodeId" value="${thisUsers.nnodeId }" />
				</div>
				<div class="row">
					<label class="col-md-2">密码:</label><input id="password"
						name="password" value="${thisUsers.password }"
						onblur="checkpassword()" />
				</div>
				<input type="hidden" id="vcOrganId" name="vcOrganId"
					value="${thisUsers.vcOrganId }"> <input type="hidden"
					id="vcFullName" name="vcFullName" value="${thisUsers.vcFullName }">
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
	function checkpassword() {
		var password = $('#password').val();
		var   re =/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[^]{8,24}$/;
        var result=  re.test(password);
        if(result){
        	$(".btn-primary").attr("disabled", false);
        }else{ 
        	alert("密码至少包含大写字母，小写字母，数字，且不少于8位");
        	$(".btn-primary").attr("disabled", true);
        }
	}
	function checkAcAccount() {
		var vcAccount = $('#vcAccount').val();
		var id = $("#id").val();
		var vcEmployeeId = $("#vcEmployeeId").val();
		$
				.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath}/portal/user/_validateAccountAndEmployeeIdUnique",
					data : {
						vcAccount : vcAccount,
						id : id,
						vcEmployeeId : vcEmployeeId
					},
					success : function(data) {
						if (data === "account_not_unique") {
							$(".btn-primary").attr("disabled", true);
							win.errorAlert("帐号不唯一！请重新输入！");
						} else if (data === "employeeId_not_unique") {
							$(".btn-primary").attr("disabled", true);
							win.errorAlert("工号不唯一！请重新输入！");
						} else {
							$(".btn-primary").attr("disabled", false);
						}
					}
				});
	}
	function submitSys() {
		var sysNum = $("#vcAccount").val();
		var sysName = $("#vcName").val();
		var sysvcEmployeeId = $("#vcEmployeeId").val();
		var syscsex = $("#csex").val();
		var sysnnodeId = $("#nnodeId").val();
		var sysPassword = $("#password").val();
		
		if (sysNum == '' || sysNum == undefined || sysNum == null) {
			win.errorAlert("账号不能为空");
			return false;
		}
		if (sysName == '' || sysName == undefined || sysName == null) {
			win.errorAlert("姓名不能为空");
			return false;
		}
		if (sysvcEmployeeId == '' || sysvcEmployeeId == undefined || sysvcEmployeeId == null) {
			win.errorAlert("工号不能为空");
			return false;
		}
		if (syscsex == '' || syscsex == undefined || syscsex == null) {
			win.errorAlert("性别不能为空");
			return false;
		}
		if (sysnnodeId == '' || sysnnodeId == undefined || sysnnodeId == null) {
			win.errorAlert("所属公司不能为空");
			return false;
		}
		if (sysPassword == '' || sysPassword == undefined || sysPassword == null) {
			win.errorAlert("密码不能为空");
			return false;
		}
		$("#form2").submit();
	}

	function checkSysNum(obj) {
		var sysNum = obj.value;
		var id = $("#id").val();
		$
				.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath}/portal/user/_checkSysNum",
					data : {
						sysNum : sysNum,
						id : id
					},
					success : function(data) {
						var jsonObj = eval("(" + data + ")");
						if (jsonObj.num > 0) {
							win.errorAlert("系统编号不唯一！请重新输入！");
							$("#sysNum").val("");
						}
					}
				});
	}

	//关闭弹窗
	$(".model_btn_close").click(function() {
		$(".bg_model").hide();
		$(".bg_model").empty();
	});
</script>
</html>