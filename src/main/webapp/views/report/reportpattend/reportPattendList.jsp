<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>报表样式维护</title>
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
		<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	</head>
	<body>
		<h4>报表维护</h4>
		<div class="table_content">
		
		<form id="form1"  class="row"  >
			<span class=" col-md-4">
			<span class="search_title">报表组类型：</span>
					<select  name="groupkindId" class="selectpicker" >
					<option value=""  >全部</option>
						<c:forEach items="${requestScope.reportgroup}" var="result">
								<option value="${result.id }"  <c:if test="${entityview.groupkindId == result.id}">selected="selected"</c:if>>${result.description }</option>
							</c:forEach>
					</select>
						
			</span>
			<span class="col-md-4">
			<span class="search_title">报表名称：</span>
						
						<select class="selectpicker " name="formkind">
									<option value=""  >全部</option>
									<c:forEach items="${requestScope.reportformkind}" var="result">
										<option value="${result.id }"  <c:if test="${entityview.formkind == result.id}">selected="selected"</c:if>>${result.description }</option>
									</c:forEach>
						</select>
			</span>
			<span class="col-md-4">
			<span class="search_title">报表组时间：</span>
					<input  type="text" name="groupTime" value="${entityview.groupTime }" readonly="true" class="time"/>
			</span>
			<span class="col-md-4">
			<span class="search_title">核算月：</span>
					<select class="selectpicker " name="month">	
							<option value="" >全部</option>
							<c:forEach begin="1" end="12" var="result">
								<option value="${result}"  <c:if test="${entityview.month == result}">selected="selected"</c:if>>${result}</option>
							</c:forEach>
					</select>
			</span>
			<span class="col-md-4">
			<span class="search_title">报表频度：</span>
					<select  name="fre" class="selectpicker" >
					<option value=""  >全部</option>
						<c:forEach items="${requestScope.reportformfre}" var="result">
								<option value="${result.id }"  <c:if test="${entityview.fre == result.id}">selected="selected"</c:if>>${result.description }</option>
							</c:forEach>
					</select>
					
			</span>
			<span class=" col-md-8">
			</span>
			<div class="form_div col-md-12">
					<c:if test="${fn:contains(gzwsession_code,',bimWork_bbys_query,')==true }">			
						<input type="button" value="查询" class="form_btn" id="qrybtn" onclick="_query()">
					</c:if>
			</div>
		</form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th>序号</th>
						<th>报表组种类</th>
						<th>报表组时间</th>
						<th>核算月</th>
						<th>报表名称</th>
						<th>报表频度</th>
						<th>报表属性</th>
						<th>已上传样式</th>
						<th>操作</th>
					</tr>
					<c:if test="${!empty requestScope.msgPage.list }">
					  <c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
						<c:forEach items="${ requestScope.msgPage.list}" var="sys" varStatus="status">
							<tr>
								<td>
									${recordNumber+ status.index + 1 }
								</td>
							
								<td>
									${sys.groupkindName }
								</td>
								<td>
									${sys.groupTime }
								</td>
								<td>
									${sys.month }
								</td>
								<td>
									${sys.formkindName }
								</td>
								<td>
									${sys.freName }
								</td>
								<td>
									${sys.typeName }
								</td>
								<td>
									<c:if test="${ empty sys.fileId }" >
									否
									</c:if>
									<c:if test="${ !empty sys.fileId }" >
									是
									</c:if>
								</td>
								<td>
									<c:if test="${fn:contains(gzwsession_code,',bimWork_bbys_new,')==true }">
										<a href="javascript:void(0)" onclick="mload('${pageContext.request.contextPath}/reportpattend/newmodify?op=modify&id=${sys.id}')" >上传样式</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.msgPage.list}">
						<tr>
							<td colspan="8" align="center">
								查询无记录
							</td>
						</tr>
					</c:if>
				</table>
				<jsp:include page="../../system/page.jsp"/>
			</div>
		</div>
		<jsp:include page="../../system/modal.jsp"/>
	</body>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script type="text/javascript">
	
		$(' input.time').jeDate(
			{
				format:"YYYY"
			}
		)
		
		$(' input.month').jeDate(
			{
				format:"YYYY-MM"
			}
		)
	
		function _query()
		{
			var form = document.forms[0];
			form.action = "${searchurl}";
			form.method = "post";
			form.submit();		
		}

		
	</script>
</html>