<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html >
<head>
	<meta charset="utf-8">
		<title>功能人员列表</title>
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/font_1/iconfont.css"/>" />
		<link rel="stylesheet" href="<c:url value="/settingCenter/font/iconfont.css"/>" />
		<link href="<c:url value="/settingCenter/css/window.css"/>" rel="stylesheet" />
		
		<!-- 库|插件 -->
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/style.min.css"/>" />
		
		<!-- 树 -->
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/iframe.css"/>">
		<style type="text/css">
		.people_select label{
				line-height:3em;
			}
			.people_select_list{
			    width: 80%;
    			margin: 0 auto;
			}
			.clearboth{
				clear:both;
				padding:0 15px !important;
				margin-bottom:15px;
			}
			.clearboth input{
				width:14em;
				margin-left:1em;
			}
			h4>i.icon-guanbi {
    		float: right;
    		margin-right: 20px;
    		font-size: 20px;
    		cursor: pointer;
			}
		</style>
		
</head>
<body>
<!-- 员工角色分配 -->
　　<div class = "">
		<h4 style="margin-top: -15px;"><span class="glyphicon glyphicon-user"></span> 人员列表<i class="iconfont icon-guanbi"></i></h4>
		<div style="overflow:hiddens;margin-bottom: 35px;margin-top: -10px;" class="model_body model_shadow">
			<form id="form2"  method="post" >
				<input type="hidden" name="modelId" id="modelId" value="${registerModel.id }">
				<div class="model_body row">
					<div class="clearboth col-md-12" style="margin-top: 20px;">
						<div class="col-md-4"><label>功能名称:</label><input type="text" value="${registerModel.codeName }" disabled=""></div>
						<div class="col-md-4"><label>所属页面:</label><input type="text" value="${registerModel.pageName }" disabled=""></div>
						<div class="col-md-4"><label>所属模块:</label><input type="text" value="${registerModel.modelName}" disabled=""></div>
					</div>
					<div class="people_select row" id="tree_user">
						<label class="col-md-12" style="margin-left: 15px;">
						人员姓名:<input type="text" name="vcName" id="vcName" value="${requestScope.vcName }">
						人员账号:<input type="text" name="vcAccount" id="vcAccount" value="${requestScope.vcAccount }">
						<a onclick="submitUsersModelBySearch()">搜索</a>
						<a onclick="exportPerson()">导出</a></label>
			</form>			
						
						<div class="people_select_list" style="width: 98%;">
		  <div class="tablebox">
				<table>
					<tr class="table_header">
						<th>序号</th>
						<th>姓名</th>
						<th>账号</th>
						<th>所属单位</th>
					</tr>
					<c:if test="${!empty requestScope.PageCodePersonList }">
						<c:set var="recordNumber" value="${(msgPage.pageNum - 1)*10 }"></c:set>
						<c:forEach items="${ requestScope.PageCodePersonList}" var="person" varStatus="status">
							<tr>
								<td>
									${recordNumber + status.index + 1 }
								</td>
								<td>
									${person[0] }
								</td>
								<td>
									${person[1] }
								</td>
								<td>
									${person[2]}
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.PageCodePersonList}">
						<tr>
							<td colspan="4" align="center">
								查询无记录
							</td>
						</tr>
					</c:if>
				</table>
				<jsp:include page="/views/system/page.jsp" />
				
			
		</div>
		<div class="model_btn" style="margin-bottom: 1%;">
			<button class="btn btn-default model model_btn_close">关闭</button>
		    </div>
　　</div>
	
	<script src="<c:url value="/settingCenter/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
    <script src="<c:url value="/settingCenter/js/bootstrap.min.js"/>" type="text/javascript"></script>
	
	
	<script src="<c:url value="/js/window.js"/>"></script>
	<script type="text/javascript">
		function submitUsersModelBySearch(){
			/* var vcEmployeeIds = "";
			//提交时，将已选择用户的vcEmployeeId进行字符串相加，并以“，”相隔
			for(var i=0;i<$("#list_choosed li").length;i++){
				vcEmployeeIds += $("#list_choosed li")[i].getAttribute('value') + ",";
			}
		    $("#vcEmployeeIds").val(vcEmployeeIds); */
		    var form = document.forms[0];
		/*     form.action="${pageContext.request.contextPath}/page/register/pagePersonQuery" */
	       form.submit();
		}
		function exportPerson(){
			var form = document.forms[0];
		form.action = "${pageContext.request.contextPath}/page/code/pageCodePersonExport";
		form.method = "post";
		form.submit();
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
		
		
	</script>
</body>

</html>
