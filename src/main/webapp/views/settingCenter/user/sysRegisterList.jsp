<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>系统注册列表页面</title>
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
		<h4>系统注册</h4>
		<div class="table_content">
			<form id="form1" class="row" action="${pageContext.request.contextPath}/sys/register/_query" method="post">
				<span class="col-md-3">系统编号：<input type="text" name="qSysNum" value="${qSysNum }"></span>
				<span class="col-md-3">系统名称：<input type="text" name="qSysName" value="${qSysName }"></span>
				<div class="form_div col-md-6">
					<input type="button" value="查询" class="form_btn" onclick="submitQuery()">
					<input type="button" value="新增" class="form_btn" id="model_add" onclick="openDialog('${pageContext.request.contextPath}/sys/register/_addOrModify?op=add&pageNum=${msgPage.pageNum }&qSysNum=${qSysNum }&qSysName=${qSysName }')">
				</div>
			</form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th>序号</th>
						<th>系统编号</th>
						<th>系统名称</th>
						<th>系统描述</th>
						<th>操作</th>
					</tr>
					<c:if test="${!empty requestScope.sysRegisterList }">
						<c:set var="recordNumber" value="${(msgPage.pageNum - 1)*10 }"></c:set>
						<c:forEach items="${ requestScope.sysRegisterList}" var="sys" varStatus="status">
							<tr>
								<td>
									${recordNumber + status.index + 1 }
								</td>
								<td>
									${sys.sysNum }
								</td>
								<td>
									${sys.sysName }
								</td>
								<td>
									${sys.sysDescription }
								</td>
								<td>
									<a href="javascript:void(0)" onclick="openDialog('${pageContext.request.contextPath}/sys/register/_addOrModify?op=modify&id=${sys.id}&&pageNum=${msgPage.pageNum }&qSysNum=${qSysNum }&qSysName=${qSysName }&randomId=<%=Math.random()%>')" >编辑</a>
									<a href="javascript:;" onclick="del(${sys.id})">删除</a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.sysRegisterList}">
						<tr>
							<td colspan="5" align="center">
								查询无记录
							</td>
						</tr>
					</c:if>
				</table>
				<div class="clearfix"> 
					<ul class="pagination" style="line-height:34px">
						&nbsp;	${msgPage.totalPage}页 /共${msgPage.totalRecords}条
						<li class="previous"><a href="javascript:;" onclick="prev()">«</a></li>
						
						<c:if test="${msgPage.totalPage>6}">
							<c:if test="${msgPage.pageNum<=6/2}">
								<c:forEach var="x" begin="1" end="6">
									<c:if test="${msgPage.pageNum==x}">
										<li class="active"><a>${x}</a></li>
									</c:if>
									<c:if test="${msgPage.pageNum!=x}">
										<li><a href="javascript:;" onclick="page(${x});">${x}</a></li>
									</c:if>
								</c:forEach>
							</c:if>
							<c:if test="${msgPage.pageNum>6/2&&msgPage.pageNum<=msgPage.totalPage - 6/2}">
								<c:forEach var="x" begin="${msgPage.pageNum +1 - 6/2}" end="${msgPage.pageNum + 6/2}">
									<c:if test="${msgPage.pageNum==x}">
										<li class="active"><a>${x}</a></li>
									</c:if>
									<c:if test="${msgPage.pageNum!=x}">
										<li><a href="javascript:;" onclick="page(${x});">${x}</a></li>
									</c:if>
								</c:forEach>
							</c:if>
							<c:if test="${msgPage.pageNum>msgPage.totalPage - 6/2}">
								<c:forEach var="x" begin="${msgPage.totalPage +1 - 6}" end="${msgPage.totalPage}">
									<c:if test="${msgPage.pageNum==x}">
										<li class="active"><a>${x}</a></li>
									</c:if>
									<c:if test="${msgPage.pageNum!=x}">
										<li><a href="javascript:;" onclick="page(${x});">${x}</a></li>
									</c:if>
								</c:forEach>
							</c:if>
						</c:if>
						<c:if test="${msgPage.totalPage<=6}">
							<c:forEach var="x" begin="1" end="${msgPage.totalPage}">
								<c:if test="${msgPage.pageNum==x}">
									<li class="active"><a>${x}</a></li>
								</c:if>
								<c:if test="${msgPage.pageNum!=x}">
									<li><a href="javascript:;" onclick="page(${x});">${x}</a></li>
								</c:if>
							</c:forEach>
						</c:if>
						
						<li class="next"><a href="javascript:;" onclick="next()">»</a></li>
					</ul>
				</div>
				<input type="hidden" id="pageNum" name="pageNum" value="${msgPage.pageNum }">
				<input type="hidden" id="totalPage" name="totalPage" value="${msgPage.totalPage }">
			</div>
		</div>
		<!-- 弹窗div -->
		<div class = "bg_model" id="tanchuang">
			
		</div>
	</body>
	<script src="<c:url value="/settingCenter/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/settingCenter/js/circles.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/settingCenter/js/remodal.min.js"/>" charset="utf-8"></script>
	<link href="<c:url value="/settingCenter/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/settingCenter/js/window.js"/>"></script>
	<script type="text/javascript">
		function submitQuery(){
			/* var form1 = $('#form1');
			form1.action = "${pageContext.request.contextPath}/sys/register/_query"; */
		    $("#form1").submit();
		    /* var entity = $('#form1').serialize();
			var url = "${pageContext.request.contextPath}/sys/register/_query?"+entity;
		    window.location.href=url; */
		} 
		
		function prev(){
			var entity = $('#form1').serialize();
			var params = {};
			var tempPageNum = document.getElementById("pageNum").value; 
			var totalPage = document.getElementById("totalPage").value;
			var pageNum = parseInt(tempPageNum) - 1;
			if(pageNum == 0){
				pageNum = 1;
			}
  			params.pageNum = pageNum; 
	     	var url = "${pageContext.request.contextPath}/sys/register/_query?pageNum="+pageNum+"&"+entity;
		    window.location.href=url;
		}
		function next(){
			var entity = $('#form1').serialize();
			var params = {};
			var tempPageNum = document.getElementById("pageNum").value; 
			var totalPage = document.getElementById("totalPage").value;
			var pageNum = parseInt(tempPageNum) + 1;
			if(pageNum >= totalPage){
				pageNum = totalPage;
			}
  			params.pageNum = pageNum; 
	     	
		    var url = "${pageContext.request.contextPath}/sys/register/_query?pageNum="+pageNum+"&"+entity;
		    window.location.href=url;
		}
		function page(pageNum){
			var entity = $('#form1').serialize();
			var url = "${pageContext.request.contextPath}/sys/register/_query?pageNum="+pageNum+"&"+entity;
		    window.location.href=url;
		}
		
		//弹窗方法
		function openDialog(url){
			$("#tanchuang").load(url);
		　　$(".bg_model").css("display","block");
		　　$(".bg_model").fadeTo(300,1);
		　　$("body").css({ "overflow": "hidden" });
		}
		
		function del(id){
			parent.win.confirm('确定要删除？',function(r){
				if(r){
					var url = "${pageContext.request.contextPath}/sys/register/_del?id=" + id;
					$.post(url, function(data) {
						if(data == 'success'){
							parent.win.successAlert('删除成功！');
						}else{
							parent.win.successAlert('此系统有注册的模块未删除，请先删除此系统注册的模块！删除失败！');
						}
						$("#form1").submit();
					});
				}
			});
		}
		
		
		$(function(){
			var save = "${save}";
			if(save == 'success'){
				parent.win.successAlert('保存成功！');
				save = "";
			}
		});
		
			
	</script>
</html>