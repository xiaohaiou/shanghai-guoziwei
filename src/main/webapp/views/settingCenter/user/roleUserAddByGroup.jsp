<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
		<title>角色人员分配</title>
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/font_1/iconfont.css"/>" />
		<link href="<c:url value="/settingCenter/css/window.css"/>" rel="stylesheet" />
		<!-- 库|插件 -->
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/remodal.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/remodal-default-theme.css"/>">
		
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/bootstrap_self.css"/>">

		
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/remodal.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/remodal-default-theme.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/style.min.css"/>" />
		
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/iframe.css"/>">
		<style type="text/css">
			.btn-info{
				background:#FFF;
				border:none;
				color:#337ab7;			
			}	
			.btn-info:hover, .btn-info:active, .btn-info:focus{
				background:#FFF;
				border:none;
				color:#337ab7;		
			}	
		</style>
		
</head>
<body>
<!-- 员工角色分配 -->
　　<div class = "model_content people_model_content">
		<h4><span class="glyphicon glyphicon-user"></span> 员工角色分配</h4>
		<div style="overflow:auto;overflow-y:hiddens" class="model_body model_shadow">
			<form id="form2"  method="post" action="${pageContext.request.contextPath}/sys/role/_roleUserByGroupSaveOrUpdate?type=roleUser">
				<input type="hidden" name="pageNum" id="pageNum" value="${pageNum }">
				<input type="hidden" name="qRoleName" id="qRoleName" value="${qRoleName }">
				<input type="hidden" name="qRoleStatus" id="qRoleStatus" value="${qRoleStatus }">
				<input type="hidden" name="roleId" id="roleId" value="${hhRole.id }">
				<input type="hidden" name="sysId" value="${hhRole.sysId }">
				<input type="hidden" name="vcAccount" value="${vcAccount }">
				<input type="hidden" id="vcEmployeeIds" name="vcEmployeeIds">
				<div class="model_body row">
					<div>
						<div class="col-md-2 col-md-offset-1"><label>角色编号:</label><input type="text" value="${hhRole.roleNum }" disabled=""></div>
						<div class="col-md-2 col-md-offset-1"><label>角色名称:</label><input type="text" value="${hhRole.roleName }" disabled=""></div>
						<div class="col-md-2 col-md-offset-1"><label>角色描述:</label><input type="text" value="${hhRole.roleDescription }" disabled=""></div>
					</div>
					<div class="tablebox">
						<table>
							<tr class="table_header">
								<th>选择</th>
								<th>用户组名称</th>
							</tr>
							<c:if test="${!empty requestScope.groupList }">
								${groupHtml }
							</c:if>
							<c:if test="${empty requestScope.groupList}">
								<tr>
									<td colspan="2" align="center">
										无用户组
									</td>
								</tr>
							</c:if>
						</table>
					</div>
				</div>
			</form>
		</div>
		<div class="model_btn">
			<button class="btn btn-default model model_btn_close">关闭</button>
			<button class="btn btn-primary model_btn_ok" onclick="submitUsersRoleByGroup()">提交</button>
		</div>
　　</div>
	
	<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
    <script src="<c:url value="/js/bootstrap.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/js/bootstrap-treeview.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/js/iframe.js"/>" type="text/javascript"></script>
	
	
	<script src="<c:url value="/js/circles.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/remodal.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/jstree.min.js"/> "type="text/javascript"></script>
	
	<script src="<c:url value="/js/window.js"/>"></script>
	<script type="text/javascript">
		function submitUsersRoleByGroup(){
	        $("#form2").submit();
		}
		
		//关闭弹窗
		$(".model_btn_close").click(function () {
		　　$(".bg_model").hide();
			$(".bg_model").empty();
		});
	</script>
</body>

</html>
