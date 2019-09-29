<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>角色功能权限配置页面</title>
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/remodal.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/remodal-default-theme.css"/>">
		
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/iframe.css"/>">
		
		<style>
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
			/* 弹出框样式 */
			.model_t1,.model_t2,.model_t3{
				display:block;
				vertical-align: bottom;
				font-weight:bold
			}
			.model_t1>span,.model_t2>span,.model_t3>span{
				color:#666;
				font-size:14px;
				font-weight: normal;
			}
			.model_t1{
				font-size:16px;
				padding-left:1em;
			}
			.model_t2{
				font-size:16px;
				padding-left:3em;
			}
			.model_t3{
				font-size:16px;
				padding-left:5em;
			}
			.model_content .model_content_div{
				border:1px solid #DDD;
				background:#fafafa;		
				margin: 2em 1em 1em;
    			padding: 1em;	
			}
			.model_content .model_content_div ul{
				margin: 0 7em 1em;
			}
			.model_content .model_content_div li{
				display:inline-block;
				width:22%;
			}
			.char_text,.char_label{
				cursor: pointer;
			}
		</style>
		
	</head>
	<body>
　　		<div class = "model_content">
			<h4><span class="glyphicon glyphicon-pencil"></span>页面选择</h4>
			<div style="overflow:scroll;overflow-x:hidden" class="model_body">
				<form id="form2" action="${pageContext.request.contextPath}/sys/role/_saveRolePageFunction?type=roleFunction" method="post">
					<input type="hidden" name="pageNum" value="${pageNum }">
					<input type="hidden" name="qRoleName" value="${qRoleName }">
					<input type="hidden" name="vcAccount" value="${vcAccount }">
					<div class="row">
						<label class="col-md-1">角色名称:</label>
						<input type="text" id="roleName" name="roleName" value="${hhRole.roleName }" disabled="">
					</div>
					<div class="row">
						<label class="col-xs-12">页面选择：</label>
						<div class="model_content_div">
							${pageHtml }
						</div>
					</div>
					<input type="hidden" id="roleId" name="roleId" value="${hhRole.id }">
				</form>
			</div>
			<div class="model_btn">
				<button class="btn btn-default model model_btn_close">关闭</button>
				<button class="btn btn-primary model_btn_ok" onclick="saveRoleFunction()">提交</button>
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
		function saveRoleFunction(){
		    $("#form2").submit();
		} 
		//关闭弹窗
		$(".model_btn_close").click(function () {
		　　$(".bg_model").hide();
			$(".bg_model").empty();
		});
		$(".char_text").on("click",function(){
			$(this).siblings(".char_label").click();
		})
		
		$("[name=chkType]").on("click",function(){
			//alter("111");
			$(this).parent().next(".show").find(".char_label").click();
		})
	</script>
</html>