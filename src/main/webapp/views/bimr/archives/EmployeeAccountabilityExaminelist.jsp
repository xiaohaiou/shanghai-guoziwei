<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>员工问责填报审核</title>
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
				<link rel="stylesheet" href="<c:url value="/css/font/iconfont.css"/>" />
		<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">
		<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
		<style type="text/css">
			.search_title {
			    display: inline-block;
			    width: 10em;
			    padding: 0 !important;
			    text-align: right;
			}
			body{
				padding:0;
			}
			h4{
				margin: 20px 40px;
			}
			.table_content {
			    margin: 0 30px;
			}
			td {
			overflow: hidden;
			white-space: nowrap;
			text-overflow: ellipsis;
			}
		</style>
	</head>
	<body class="hn_grey">
		<h4>员工问责填报审核</h4>
		<div class="table_content">
		
		<form id="form1"  class="row"  >
			<span class="col-md-4">项目名称：
				<input  type="text" name="projectName" id=projectName value="${entity.projectName}"  />
			</span>
			
			<span class="col-md-4">类别：
				<select  name="typename" id="typename" class="selectpicker" >
					<option value="" <c:if test="${entity.typename eq ''}">selected</c:if>>全部</option>
					<option value="审计项目" <c:if test="${entity.typename eq '审计项目'}">selected</c:if>>审计项目</option>
					<option value="合规项目" <c:if test="${entity.typename eq '合规项目'}">selected</c:if>>合规项目</option>
					<option value="民事案件" <c:if test="${entity.typename eq '民事案件'}">selected</c:if>>民事案件</option>
					<option value="刑事案件" <c:if test="${entity.typename eq '刑事案件'}">selected</c:if>>刑事案件</option>
				</select>
			</span>
			
			<div class="form_div col-md-12">	
		      	<c:if test="${fn:contains(gzwsession_code,',bimWork_ygwztbsh_query,')==true }">	
						<input type="button" value="查询" class="form_btn" id="qrybtn" onclick="query()">
				</c:if>		
			</div>
		</form>
		
		
			<div class="tablebox">
				<table style="width: 100%;table-layout: fixed;">
					<tr class="table_header">
						
						<th >序号</th>
						<th >类别</th>
						<th >项目编号</th>
						<th >项目名称</th>
						<th >操作</th>
						
					</tr>
					<c:if test="${!empty requestScope.msgPage.list }">
					<c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
						<c:forEach items="${ requestScope.msgPage.list}" var="e" varStatus="status">
							<tr>
							
								<td style="text-align:center;">
									${recordNumber + status.index + 1 }
								</td>
								<td style="text-align:center;">
								   ${e.typename}
								</td>
								<td title="${e.projectCode}" style="text-align:center;">
									 ${e.projectCode}
								</td>
								<td style="text-align:center;" title="${e.projectName}">
									 ${e.projectName}
								</td>
								
								<td>
							    	<c:if test="${fn:contains(gzwsession_code,',bimWork_ygwztbsh_show,')==true }">	
									     <a href="javascript:void(0)" onclick="mload('${pageContext.request.contextPath}/bimr/archives/companyTree/EmployeeAccountabilityCheckView?id=${e.id}&projecttype=${e.projecttype }&showtype=show')">查看</a>
									</c:if>
									<c:if test="${fn:contains(gzwsession_code,',bimWork_ygwztbsh_examine,')==true }">	
									     <c:if test="${e.status==1}">
											<a href="javascript:void(0)" onclick="mload('${pageContext.request.contextPath}/bimr/archives/companyTree/EmployeeAccountabilityCheckView?id=${e.id}&projecttype=${e.projecttype }')">审核</a>
										 </c:if>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.msgPage.list}">
						<tr>
							<td colspan="11" align="center">
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
	<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script type="text/javascript">
	
		
	
		function query() {
		
			var form = document.forms[0];
			form.action = "${pageContext.request.contextPath}/bimr/archives/companyTree/EmployeeAccountabilityExaminelist?t=query";
			form.method = "post";
			form.submit();
		}
		
	</script>
</html>