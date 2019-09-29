<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>风险管理二级风险TOP排名</title>
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
	<h4>风险管理二级风险TOP排名</h4>
	<div class="table_content">
		<form id="form1" class="row">
			
			<span class="col-md-4"><span class="search_title">一级目录：</span>
				<select name="leveloneid" class="selectpicker" id="leveloneid">
					<option value="">全部</option>
					<option value="2">市场风险</option>
					<option value="3">战略风险</option>
					<option value="4">法律风险</option>
					<option value="5">财务风险</option>
					<option value="6">运营风险</option>
				</select> 
			</span> 
			
			 <span class="col-md-4">
				<span class="search_title">上报日期: </span>
				<input type="text" value="${entity.createDate}" name="createDate" id="createDate" readonly="readonly" class="time time_short"/>
			</span>
			
			<br>
			<br>
			<div class="form_div col-md-12">
			      <c:if test="${fn:contains(gzwsession_code,',bimWork_fxglejfxtoppm_query,')==true}">
				      <input type="button" value="查询" class="form_btn" id="qrybtn" onclick="_query()">
				  </c:if>
			</div>
		</form>
		<div class="tablebox">
			<table>
				<tr class="table_header">
					<th style="width:3%" >序号</th>
					<th style="width:10%" >日期</th>
					<th style="width:10%" >一级风险目录名称</th>
					<th style="width:15%" >二级风险目录名称</th>
					<th style="width:15%" >风险描述</th>
					<th style="width:10%" >风险成因分析</th>
					<th style="width:10%" >关联风险事件(条)</th>
				</tr>
				
				<c:if test="${!empty requestScope.msgPage.list }">
					<c:set var="recordNumber" value="${(requestScope.msgPage.pageNum - 1) * 10 }" />
					<c:forEach items="${ requestScope.msgPage.list}" var="l" varStatus="status">
						<tr>
							<td style="text-align: center;">${recordNumber+ status.index + 1 }</td>
							<td style="text-align: center;">${l.relevanceTime}</td>
							<td style="text-align: center;">${l.oneName}</td>
							<td style="text-align: center;">${l.secondName}</td>
							<td style="text-align: center;">${l.secondDefinition}</td>
							<td style="text-align: center;"></td>
							<td style="text-align: center;"><a href="javascript:void(0)" onclick="openwin('${mapurl}/secondlistforDetail?leveltwoid=${l.secondid}','${l.relevanceTime}')">${l.count}</a></td>

						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty requestScope.msgPage.list}">
					<tr>
						<td colspan="7" align="center">查询无记录</td>
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
		$('input.time').jeDate({
			format:"YYYY-MM"
		});
		function _query(){
			var form = document.forms[0];
			form.action = "${searchurl}";
			form.method = "post";
			form.submit();
		}
		
		function del(id){
			parent.win.confirm('确定要删除吗？',function(r){
				if(r){
					var url = "${pageContext.request.contextPath}/bimr/riskEvent/delete";
					var param = {id: id};
					$.post(url, param, function(data) {
						win.successAlert('删除成功！',_query);
					});
				}
			});
		}
		
		function openwin(url,createDate){
		    var str= url+"&createDate="+createDate.substring(0,7);
			window.open(str,'_blank')
		}
		
		$(function () {
			if('${entity.leveloneid}'!='')
				$("#leveloneid").val('${entity.leveloneid}');
		});

	</script>
</html>