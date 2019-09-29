<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>干部员工合规档案查询</title>
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/css/font/iconfont.css"/>" />
		<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
		<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	</head>
	<body>
		<h4>干部员工合规档案查询</h4>
		<div class="table_content">
		<form:form id="form1" class="row"  >
			<span class="col-md-4">
				<span class="search_title">问责人员：</span>
				<input type="text" value="${entity.person}" name="person" id="person"/> 
			</span>
			
			<div class="form_div col-md-12">
					<c:if test="${fn:contains(gzwsession_code,',bimWork_gbyghgdacx_query,')==true}">
					<input type="button" value="查询" class="form_btn" id="queryBtn" onclick="_query()">
				 </c:if>
				 <c:if test="${fn:contains(gzwsession_code,',bimWork_gbyghgdacx_query,')==true}">
					<input type="button" value="导出" class="form_btn" id="exportbtn"  onclick="_export()">
				 </c:if>
				 <%-- <c:if test="${fn:contains(gzwsession_code,',bimWork_gbyghgdacx_show,')==true}">
									    <a href="javascript:void(0)" onclick="mload('${mapurl}/empArchivesExport?personId=${duty.personId}&id=${duty.id}')">导出</a>
									</c:if> --%>
			</div>
		</form:form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th style="width:5%;">序号</th>
						<th style="width:10%;">员工姓名</th>
						<th style="width:20%;">员工号</th>
						<th style="width:25%;">现任职单位</th>
						<th style="width:20%;">任职职务</th>
						<th>操作</th>
					</tr>
					<c:if test="${!empty requestScope.msgPage.list }">
					<c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
						<c:forEach items="${ requestScope.msgPage.list}" var="duty" varStatus="status">
							<tr>
								<td style="text-align: center;">
									${recordNumber + status.index + 1 }
								</td>
								<td style="text-align: center;">
									${duty.person}
								</td>
								<td style="text-align: center;">
									${duty.personId}
								</td>
								<td style="text-align: center;">
									${duty.personCompany }
								</td>
								
								<td style="text-align: center;">
									${duty.personPost }
								</td>
								<td style="text-align: center;">
									<c:if test="${fn:contains(gzwsession_code,',bimWork_gbyghgdacx_show,')==true}">
									    <a href="javascript:void(0)" onclick="mload('${mapurl}/archivesView?personId=${duty.personId}&id=${duty.id}')">查看</a>
									</c:if>
									
									
									
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
				<jsp:include page="../../system/page.jsp"/>
			</div>
		</div>
		<jsp:include page="../../system/modal.jsp"/>
	</body>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script type="text/javascript">
		$('input.time').jeDate({
			format:"YYYY-MM-DD"
		});
		function _export()
		{
		
		 var form = document.forms[0];
			form.action = "${pageContext.request.contextPath}/bimr/empArchives/empArchivesExport";
			form.method = "post";
			form.submit();
		}
		function _query() {
			var form = document.forms[0];
			form.action = "${searchurl}";
			form.method = "post";
			form.submit();
		};
		$("#queryBtn").click(function() {
			if(checkTime($("#startDate").val(),$("#endDate").val()) == false){
				return false;
			}
			_query();		
		});
	</script>
</html>