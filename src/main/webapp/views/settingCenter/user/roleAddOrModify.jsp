<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>角色新增、编辑页面</title>
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/remodal.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/remodal-default-theme.css"/>">
		
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/iframe.css"/>">
		<style type="text/css">
			.char_li{
				position: relative;
			}
			.char_check{
				display: none;
			}
			.char_label{
				/*position: absolute;
				top: 3px;*/
			
				display: inline-block;
				margin: 0;
				width: 11px;
				height: 11px;
				/*border: 1px solid #444;*/
				border-radius: 3px;
				
				box-shadow: 1px solid #444;
			}
			.char_check:checked+.char_label{
				display: inline-block;
				width: 11px;
				height: 11px;
				background: #63d0f1;
				border-radius: 3px;
				border: none;
				
				box-shadow: 0 0 0 1px #777;
			}
			.char_li span{
				padding: 0 18px;
				padding-left:0;
				margin-top: 5px;
			}
		</style>
	</head>
	<body>
		<!-- 角色编辑，以及角色新建模态 -->
　　		<div class = "model_content">
			<c:choose>
				<c:when test="${op eq 'modify' }">
					<h4><span class="glyphicon glyphicon-pencil"></span>编辑</h4>
				</c:when>
				<c:when test="${op eq 'check' }">
					<h4><span class="glyphicon glyphicon-pencil"></span>查看</h4>
				</c:when>
				<c:otherwise>
					<h4><span class="glyphicon glyphicon-pencil"></span>新增</h4>
				</c:otherwise>
			</c:choose>
			
			<div style="overflow:scroll;overflow-x:hidden" class="model_body">
				<form id="form2" action="${pageContext.request.contextPath}/sys/role/_roleSaveOrUpdate?type=role" method="post">
					<input type="hidden" name="qRoleName" id="qRoleName" value="${qRoleName}">
					<input type="hidden" name="pageNum" id="pageNum" value="${pageNum}">
					<input type="hidden" name="qRoleStatus" id="qRoleStatus" value="${qRoleStatus}">
					<input type="hidden" name="id" id="id" value="${theRole.id}">
					<input type="hidden" name="sysId" id="sysId" value="${theRole.sysId}">
					<input type="hidden" name="roleStatus" id="roleStatus" value="${theRole.roleStatus}">
					<input type="hidden" name="creator" id="creator" value="${theRole.creator }">
					<input type="hidden" name="createTime" id="createTime" value="${theRole.createTime }">
					<input type="hidden" name="vcAccount" value="${vcAccount }">
					<div class="row">
						<label class="col-md-1">角色名称:</label>
						<input type="text" id="roleName" <c:if test="${op eq 'check'}">disabled=""</c:if> name="roleName" value="${theRole.roleName }">
					</div>
					<div class="row">
						<label class="col-md-1">角色描述:</label>
						<div class="jsms_textarea">
							<textarea id="roleDescription" <c:if test="${op eq 'check'}">disabled=""</c:if> name="roleDescription">${theRole.roleDescription }</textarea>
						</div>
					</div>
					<c:if test="${op eq 'check'}">
						<div class="tablebox">
							<table>
								<tr class="table_header">
									<th>序号</th>
									<!-- <th>所属公司</th> -->
									<th>所属部门</th>
									<th>人员名称</th>
									<th>用户账户</th>
								</tr>
								<c:if test="${!empty requestScope.users }">
									<c:forEach items="${requestScope.users }" var="user" varStatus="status">
										<tr>
											<td>${status.index +1}</td>
											<td>${user.vcFullName}</td>
											<td>${user.vcName }</td>
											<td>${user.vcAccount }</td>											
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${empty requestScope.users}">
									<tr>
										<td colspan="3" align="center">
											未绑定人员
										</td>
									</tr>
								</c:if>
							</table>
						</div>
					</c:if>
				</form>
			</div>
			<div class="model_btn">
				<button class="btn btn-default model model_btn_close">关闭</button>
				<button <c:if test="${op eq 'check'}">style="display:none"</c:if> class="btn btn-primary model_btn_ok" onclick="saveRole()">提交</button>
			</div>
		</div>
	</body>
	<script src="<c:url value="/settingCenter/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
    <script src="<c:url value="/settingCenter/js/bootstrap.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/settingCenter/js/bootstrap-treeview.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/settingCenter/js/iframe.js"/>" type="text/javascript"></script>
	
	<script src="<c:url value="/settingCenter/js/circles.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/settingCenter/js/remodal.min.js"/>" charset="utf-8"></script>
	<link href="<c:url value="/settingCenter/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/settingCenter/js/window.js"/>"></script>
	<script type="text/javascript">
		function saveRole(){
			/**if($("#roleNum").val() == "") {
				win.errorAlert("角色编码不能为空");
				return false;
			}*/
			if($("#roleName").val() == "") {
				win.errorAlert("角色名称不能为空");
				return false;
			}
			if($("#roleDescription").val() == "") {
				win.errorAlert("角色描述不能为空");
				return false;
			}
		    $("#form2").submit();
		} 
		//关闭弹窗
		$(".model_btn_close").click(function () {
		　　$(".bg_model").hide();
			$(".bg_model").empty();
		});
	</script>
</html>