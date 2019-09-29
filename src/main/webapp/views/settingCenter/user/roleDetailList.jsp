<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>角色查看页面</title>
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/remodal.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/remodal-default-theme.css"/>">
		
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/bootstrap_self.css"/>">
		
		<link rel="stylesheet" type="text/css" href="<c:url value="/settingCenter/css/zTreeStyle.css"/>">
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
			 .tablebox1 {
			    height: 90%;
			    position: relative;
			    padding-left: 15px;
			    padding-right: 15px;
			}
			.tablebox1 table tr:first-child {
			    border-bottom: 1px solid #eee;
			    background: rgb(220, 224, 224);
			    line-height: 4.5rem;
			}
			.tablebox1 table tr td {
			    font-size: 15px;
			}
			.tablebox1 table th{
				text-align:center;
			}
			td {
			    white-space: nowrap;
			    overflow: hidden;
			    text-overflow: ellipsis;
			}
			.tablebox1 table tr {
			    line-height: 4.0rem;
			    border-bottom: 1px solid #eee;
			    color: #666;
			}
		</style>
	</head>
	<body>
　　		<div class = "model_content">
			<div style="overflow:scroll;" class="model_body">
				<div class="tablebox1">
					<table style="table-layout: fixed">
						<tr class="table_header">
							<th width="5%" align="center">序号</th>
							<th width="20%" align="center">角色名称</th>
							<th width="15%" align="center">功能权限</th>
							<th width="20%" align="center">管理关系数据权限</th>
							<th width="20%" align="center">财务管理数据权限</th>
							<th width="20%" align="center">角色用户</th>
						</tr>
						<c:if test="${not empty roles}">
							<c:forEach items="${roles}" var="role" varStatus="status">
								<tr>
									<td>${status.count}</td>
									<td><a href="${pageContext.request.contextPath}/sys/role/_query?type=role&qRoleName=${role.roleName}" target="_blank">${role.roleName }</a></td>
									<td>
										<div id="tree${status.count}" class="ztree subTree">${role.authTreeData}</div>
									</td>
									<td>${role.companyNames }</td>
									<td>${role.financeCompanyNames}</td>
									<td style="word-wrap : break-word ;" title='<c:forEach items="${role.users}" var="user" >${user.vcName},</c:forEach>'>
										<c:forEach items="${role.users}" var="user" >
											${user.vcName}
										</c:forEach>
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
				</div>
			</div>
			<div class="model_btn">
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
		</div>
	</body>
	<script src="<c:url value="/settingCenter/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
    <script src="<c:url value="/settingCenter/js/bootstrap.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/settingCenter/js/bootstrap-treeview.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/settingCenter/js/iframe.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/settingCenter/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/settingCenter/js/circles.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/settingCenter/js/remodal.min.js"/>" charset="utf-8"></script>
	<script type="text/javascript">
		 
	
		//关闭弹窗
		$(".model_btn_close").click(function () {
		　　$(".bg_model").hide();
			$(".bg_model").empty();
		});
		$(function () {
		    
		    
		    $(".subTree").each(function(index,e){
		    	var com_data = eval("(" + $(this).text()+")");
		    	var com_ztree_setting = {
		    		treeId:"t"+index,
					data:{
						simpleData: {
							enable: true,
							idKey: "id",
							pIdKey: "pId",
							rootPId: 0
						}
					},
			    };
				$.fn.zTree.init($(this), com_ztree_setting, com_data);
			 });
		 })
	</script>
</html>