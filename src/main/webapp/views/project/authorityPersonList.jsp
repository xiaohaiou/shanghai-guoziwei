<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>重点基建工程权限控制</title>
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
		<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">
		<style type="text/css">
			.selectone{
				white-space: nowrap;
				font-size: 14px;
				cursor: pointer;
			}
			.selectone:hover, .selectone.selectedOne{
				box-sizing: border-box;
				background-color: rgba(255,255,255,0.8);
				border-bottom:1px solid rgba(0,0,0,0.5);
			}
			.selectone>* {
				display:inline-block;
				min-width: 60px;
			}
			.selectedOne{
				border-color:#4a22cc;
				color: #4a22cc;
			}
			#com_ztree span {
				padding-left:0;
			}
		</style>
	</head>
	<body>
		<h4>重点基建工程权限控制</h4>
		<div class="table_content">
		
		<form id="form1"  class="row"  >
			<span class="col-md-4">项目名称：
				<select name="pjId">
					<option value="">--全部--</option>
					<c:forEach items="${projects}" var="project">
						<option value="${project.id}" <c:if test="${authorityPerson.pjId == project.id }">selected="selected"</c:if> >${project.pjName}</option>
					</c:forEach>
				</select>
			</span>
			<span class="col-md-4">权限类型：
				<select name="personType">
					<option value="">--全部--</option>
					<option value="0" <c:if test="${authorityPerson.personType == 0}">selected="selected"</c:if> >上报人</option>
					<option value="1" <c:if test="${authorityPerson.personType == 1}">selected="selected"</c:if> >审核人</option>
					
				</select>
			</span>
			<div class="form_div col-md-12">
				<input type="button" value="查询" class="form_btn" id="qrybtn" onclick="_query()">
				<input type="button" value="新增" class="form_btn" id="addbtn" onclick="mload('${pageContext.request.contextPath}/project/authorityPerson/_add')">
			</div>
		</form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th>序号</th>
						<th>项目名称</th>
						<th>权限类型</th>
						<th>权限人</th>
						<th>权限人工号</th>
						<th>操作</th>
					</tr>
					<c:if test="${!empty requestScope.msgPage.list }">
						<c:forEach items="${ requestScope.msgPage.list}" var="sys" varStatus="status">
							<tr>
								<td>${(msgPage.pageNum-1)*10+status.count}</td>
								<td style="text-align:left;">${sys.pjName}</td>
								<td style="text-align:left;">
									<c:if test="${sys.personType == 0}">上报人</c:if>
									<c:if test="${sys.personType == 1}">审核人</c:if>
								</td>
								<td>${sys.personName}</td>
								<td>${sys.personVcEmployeeId}</td>
								<td>
									<a  href="javascript:;" id="qrybtn2" onclick="editValidate('${sys.id}')">修改</a>
									<a  href="javascript:;" id="qrybtn" onclick="del('${sys.id}')">删除</a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.msgPage.list}">
						<tr>
							<td colspan="6" align="center">
								查询无记录
							</td>
						</tr>
					</c:if>
				</table>
				<jsp:include page="../system/page.jsp"/>
			</div>
		</div>
		<jsp:include page="../system/modal.jsp"/>
	</body>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
	<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
	<script type="text/javascript">
		function _query()
		{
			var form = document.forms[0];
			form.action = "${searchurl}";
			form.method = "post";
			form.submit();		
		}
		
		function del(id){
			win.confirm('确定要删除？',function(r){
				if(r){
					var url = "${pageContext.request.contextPath}/project/authorityPerson/_validate?id=" + id;
					$.post(url, function(data) {
						var project = eval("(" + data +")");
						if(project.isdel == 1){
							win.errorAlert("该数据已被删除",function(){
								_query();
							});
						}else{
							var url = "${pageContext.request.contextPath}/project/authorityPerson/_del?id=" + id;
							$.post(url, function(data) {
								if(data == 'success'){
									win.successAlert('删除成功！');
									_query();
								}
								
							});
						}
					});
					
				}
			});
		}
		function editValidate(id){
			var url = "${pageContext.request.contextPath}/project/authorityPerson/_validate?id=" + id;
			$.post(url, function(data) {
				var project = eval("(" + data +")");
				if(project.isdel == 1){
					win.errorAlert("该数据已被删除",function(){
						_query();
					});
				}else{
					mload('${pageContext.request.contextPath}/project/authorityPerson/_edit?id='+id);
				}
			});
		}
	</script>

</html>