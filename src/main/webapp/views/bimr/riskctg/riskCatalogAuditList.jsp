<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>风险目录数据审核</title>
<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
<link rel="stylesheet" href="<c:url value="/css/font/iconfont.css"/>" />
<!-- 库|插件 -->
<!-- 新加样式 -->
<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">
</head>
<body>
	<h4>风险目录数据审核</h4>
	<div class="table_content">
		<form id="form1" class="row">
			<span class="col-md-4">目录名称： <input type="text" name="name" value="${name}"  class="w25"/> </span> 
			<span class="col-md-4">目录定义： <input type="text" name="define" value="${define}"  class="w25"/> </span> 
			<span class="col-md-4"> 状态：
				<select name="status" class="selectpicker">
					<option value="">全部</option>
					<option value="1" <c:if test="${status == 1}">selected="selected"</c:if>>待审核</option>
					<option value="2" <c:if test="${status == 2}">selected="selected"</c:if>>已退回</option>
					<option value="3" <c:if test="${status == 3}">selected="selected"</c:if>>已审核</option>
				</select> 
			</span> 
			<br>
			<br>
			<div class="form_div col-md-12">
			     <c:if test="${fn:contains(gzwsession_code,',bimWork_fxmlsjsh_query,')==true}">
				      <input type="button" value="查询" class="form_btn" id="qrybtn" onclick="_query()">
				 </c:if>
				<!--<input type="button" value="导出" class="form_btn" id="exportbtn" onclick="mload('${mapurl}/export')">-->
			</div>
		</form>
		<div class="tablebox">
			<table>
				<tr class="table_header">
					<th style="width:3%" >序号</th>
					<th style="width:15%" >一级目录</th>
					<th style="width:15%" >二级目录</th>
					<th style="width:15%" >三级目录</th>
					<th style="width:10%" >创建人</th>
					<th style="width:10%" >上报人</th>
					<th style="width:10%" >审核人</th>
					<th style="width:8%" >状态</th>
					<th style="width:15%" >操作</th>
				</tr>
				
				<c:if test="${!empty requestScope.msgPage.list }">
					<c:set var="recordNumber" value="${(requestScope.msgPage.pageNum - 1) * 10 }" />
					<c:forEach items="${ requestScope.msgPage.list}" var="l" varStatus="status">
						<tr>
							<td style="text-align: center;">${recordNumber+ status.index + 1 }</td>
							<td style="text-align: left;">${l[2]}</td>
							<td style="text-align: left;">${l[4]}</td>
							<td style="text-align: left;">${l[6]}</td>
							<td style="text-align: left;">${l[8]}</td>
							<td style="text-align: left;">${l[9]}</td>
							<td style="text-align: left;">${l[10]}</td>
							<td style="text-align: left;">
								<c:if test="${l[7] == 0}"><span style="color:#0033FF">未上报</span></c:if>
								<c:if test="${l[7] == 1}"><span style="color:#FF9933">待审核</span></c:if>
								<c:if test="${l[7] == 2}"><span style="color:#993333">已退回</span></c:if>
								<c:if test="${l[7] == 3}"><span style="color:#99FF00">已审核</span></c:if>
							</td>
							<td style="text-align: left;">
							     <c:if test="${fn:contains(gzwsession_code,',bimWork_fxmlsjsh_show,')==true}">
								     <a href="javascript:void(0)" onclick="mload('${mapurl}/view?id=${l[0]}')">查看</a>
							      </c:if>
							      <c:if test="${fn:contains(gzwsession_code,',bimWork_fxmlsjsh_examine,')==true}">
										<c:if test="${l[7] == 1}">
											<a href="javascript:void(0)" onclick="mload('${mapurl}/view?id=${l[0]}&type=audit')">审核</a>
										</c:if>
								   </c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty requestScope.msgPage.list}">
					<tr>
						<td colspan="13" align="center">查询无记录</td>
					</tr>
				</c:if>
			</table>
			<jsp:include page="/views/system/page.jsp" />
		</div>
	</div>
	<jsp:include page="/views/system/modal.jsp" />
</body>
<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
<script type="text/javascript">
		function _query(){
			var form = document.forms[0];
			form.action = "${searchurl}";
			form.method = "post";
			form.submit();
		}
	</script>
</html>