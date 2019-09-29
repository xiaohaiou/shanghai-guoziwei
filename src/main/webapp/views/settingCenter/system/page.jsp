<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%-- <!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>页面注册列表页面</title>
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/css/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<link rel="stylesheet" href="<c:url value="/css/remodal.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/remodal-default-theme.css"/>">
		
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
		
	</head>
	<body>
		<h4>页面注册</h4>
		<div class="table_content">
			<form id="form1" class="row" action="${pageContext.request.contextPath}/page/register/_query" method="post">
				<span class="col-md-3">页面编号：<input type="text" name="qPageNum" value="${qPageNum }"></span>
				<span class="col-md-3">页面名称：<input type="text" name="qPageName" value="${qPageName }"></span>
				<span class="col-md-3">所属系统：
					<select name="qSysId">
						<option value="">---请选择---</option>
						<c:forEach items="${ requestScope.sysRegistedList}" var="sys">
							<option value="${sys.id }" <c:if test="${sys.id eq qSysId }">selected="selected"</c:if>>${sys.sysName }</option>
						</c:forEach>
					</select>
				</span>
				<div class="form_div col-md-3">
					<input type="button" value="查询" class="form_btn" onclick="query()">
					<c:if test="${fn:contains(rolePagecodeNums,'page_add')==true}">
						<input type="button" value="新增" class="form_btn" id="model_add" onclick="openDialog('${pageContext.request.contextPath}/page/register/_addOrModify?op=add')">
					</c:if>
				</div>
			</form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th>序号</th>
						<th>页面编号</th>
						<th>页面名称</th>
						<th>所属系统</th>
						<th>页面描述</th>
						<th>操作</th>
					</tr>
					<c:if test="${!empty requestScope.pageRegisterList }">
						<c:forEach items="${ requestScope.pageRegisterList}" var="page" varStatus="status">
							<tr>
								<td>
									${status.index + 1 }
								</td>
								<td>
									${page.pageNum }
								</td>
								<td>
									${page.pageName }
								</td>
								<td>
									${page.sysName }
								</td>
								<td>
									${page.pageDescription }
								</td>
								<td>
									<c:if test="${fn:contains(rolePagecodeNums,'page_edit')==true}">
										<a href="javascript:void(0)" onclick="openDialog('${pageContext.request.contextPath}/page/register/_addOrModify?op=modify&id=${page.id}')" >编辑</a>
									</c:if>
									<c:if test="${fn:contains(rolePagecodeNums,'page_del')==true}">
										<a href="javascript:;" onclick="del(${page.id})">删除</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.pageRegisterList}">
						<tr>
							<td colspan="5" align="center">
								查询无记录
							</td>
						</tr>
					</c:if>
				</table> --%>
				<div class="clearfix" style="float: right;"> 
					<ul class="pagination" style="line-height:34px">
					&nbsp;	${msgPage.totalPage}页 /共${msgPage.totalRecords}条
						<li class="previous"><a href="javascript:;" onclick="prev()">«</a></li>
						
						<c:if test="${msgPage.totalPage>6}">
							<c:if test="${msgPage.pageNum<=6/2}">
								<c:forEach var="x" begin="1" end="6">
									<c:if test="${msgPage.pageNum==x}">
										<li class="active"><a >${x}</a></li>
									</c:if>
									<c:if test="${msgPage.pageNum!=x}">
										<li><a href="javascript:;" onclick="page(${x});">${x}</a></li>
									</c:if>
								</c:forEach>
							</c:if>
							<c:if test="${msgPage.pageNum>6/2&&msgPage.pageNum<=msgPage.totalPage - 6/2}">
								<c:forEach var="x" begin="${msgPage.pageNum +1 - 6/2}" end="${msgPage.pageNum + 6/2}">
									<c:if test="${msgPage.pageNum==x}">
										<li class="active"><a >${x}</a></li>
									</c:if>
									<c:if test="${msgPage.pageNum!=x}">
										<li><a href="javascript:;" onclick="page(${x});">${x}</a></li>
									</c:if>
								</c:forEach>
							</c:if>
							<c:if test="${msgPage.pageNum>msgPage.totalPage - 6/2}">
								<c:forEach var="x" begin="${msgPage.totalPage +1 - 6}" end="${msgPage.totalPage}">
									<c:if test="${msgPage.pageNum==x}">
										<li class="active"><a >${x}</a></li>
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
									<li class="active"><a >${x}</a></li>
								</c:if>
								<c:if test="${msgPage.pageNum!=x}">
									<li><a href="javascript:;" onclick="page(${x});">${x}</a></li>
								</c:if>
							</c:forEach>
						</c:if>
						
						<li class="next"><a href="javascript:;" onclick="next()">»</a></li>
					</ul>
				</div>
				<%-- <input type="hidden" id="pageNum" name="pageNums" value="${msgPage.pageNum }">
				<input type="hidden" id="totalPage" name="totalPage" value="${msgPage.totalPage }">
			</div>
		</div>
		<!-- 弹窗div -->
		<div class = "bg_model" id="tanchuang">
			
		</div>
	</body> --%>
	<script type="text/javascript">
		
		var tempPageNum = ${msgPage.pageNum };
		var totalPage = ${msgPage.totalPage };
		var searchurl='${searchurl}'
		function prev(){
			//var entity = $('#form1').serialize();
			var params = {};
			var pageNum = parseInt(tempPageNum) - 1;
			if(pageNum == 0){
				pageNum = 1;
			}
  			params.pageNum = pageNum; 
	     	/* var url = searchurl+"?pageNums="+pageNum+"&"+entity;
		    window.location.href=url; */
		    pagequery(pageNum);
		}
		function next(){
			//var entity = $('#form1').serialize();
			var params = {};
			var pageNum = parseInt(tempPageNum) + 1;
			if(pageNum >= totalPage){
				pageNum = totalPage;
			}
  			params.pageNum = pageNum; 
		   /*  var url = searchurl+"?pageNums="+pageNum+"&"+entity;
		    window.location.href=url; */
		    pagequery(pageNum);
		}
		function page(pageNum){
			//var entity = $('#form1').serialize();
			/* var url = searchurl+"?pageNums="+pageNum+"&"+entity;
		    window.location.href=url; */
		    pagequery(pageNum);
		}
		function pagequery(pageNum)
		{
			var url="";
			if(searchurl.indexOf('?')>0)
			    url = searchurl+"&pageNums="+pageNum;
			else
				url = searchurl+"?pageNums="+pageNum;
				
			var form = document.forms[0];
			form.action =url;
			form.method = "post";
			form.submit();	
		}
		
	</script>
</html>