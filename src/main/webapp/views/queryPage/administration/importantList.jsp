<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>要情台账数据查询页面</title>
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/css/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
		<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	</head>
	<body>
		<h4>要情台账数据查询</h4>
		<div class="table_content">
		
		<form:form id="form1" class="row"  >
			<span class="col-md-4">
				<span class="search_title">年份：</span>
				<input type="text" value="${entity.year }" name="year" readonly="true" class="time"/>
			</span>
			<span class="col-md-4">
				<span class="search_title">月份：</span>
				<select  name="month" class="selectpicker" >
				<option value=""  >全部</option>
					<option value="1"  <c:if test="${entity.month == 1}">selected="selected"</c:if>>1月</option>
					<option value="2"  <c:if test="${entity.month == 2}">selected="selected"</c:if>>2月</option>
					<option value="3"  <c:if test="${entity.month == 3}">selected="selected"</c:if>>3月</option>
					<option value="4"  <c:if test="${entity.month == 4}">selected="selected"</c:if>>4月</option>
					<option value="5"  <c:if test="${entity.month == 5}">selected="selected"</c:if>>5月</option>
					<option value="6"  <c:if test="${entity.month == 6}">selected="selected"</c:if>>6月</option>
					<option value="7"  <c:if test="${entity.month == 7}">selected="selected"</c:if>>7月</option>
					<option value="8"  <c:if test="${entity.month == 8}">selected="selected"</c:if>>8月</option>
					<option value="9"  <c:if test="${entity.month == 9}">selected="selected"</c:if>>9月</option>
					<option value="10"  <c:if test="${entity.month == 10}">selected="selected"</c:if>>10月</option>
					<option value="11"  <c:if test="${entity.month == 11}">selected="selected"</c:if>>11月</option>
					<option value="12"  <c:if test="${entity.month == 12}">selected="selected"</c:if>>12月</option>
				</select>
			</span>
			<span class="col-md-4">
				<span class="search_title">要情类别：</span>
				<select  name="importantType" class="selectpicker" >
					<option value=""  >全部</option>
					<c:forEach items="${requestScope.importantTypes}" var="result">
						<option value="${result.id }"  <c:if test="${entity.importantType == result.id}">selected="selected"</c:if>>${result.description }</option>
					</c:forEach>
				</select>
			</span>
			<span class="col-md-4">
				<span class="search_title">状态：</span>
				<select  name="status" class="selectpicker" >
					<option value=""  >全部</option>
					<c:forEach items="${requestScope.reportStatus}" var="result">
						<option value="${result.id }"  <c:if test="${entity.status == result.id}">selected="selected"</c:if>>${result.description }</option>
					</c:forEach>
				</select>
			</span>
			<div class="form_div col-md-12">
				<c:if test="${fn:contains(gzwsession_code,',bimWork_yqsjsjsb_query,')==true }">
					<input type="button" value="查询" class="form_btn" id="qrybtn">
				</c:if>
				<!-- <input type="button" value="导出" class="form_btn" id="qrybtn"> -->
			</div>
		</form:form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th>序号</th>
						<th>时间</th>
						<th>发生单位</th>
						<th>要情类别</th>
						<th style="width:20%">要情标题</th>
						<th>创建人</th>
						<th>上报人</th>
						<th>审核人</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
					<c:if test="${!empty requestScope.msgPage.list }">
					<c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
						<c:forEach items="${ requestScope.msgPage.list}" var="important" varStatus="status">
							<tr>
								<td style="text-align: center;">
									${recordNumber + status.index + 1 }
								</td>
								<td style="text-align: center;">
									${important.year }-${important.month }
								</td>
								<td style="text-align: left;">
									${important.importantCompName }
								</td>
								<td style="text-align: left;">
									${important.importantTypeName }
								</td>
								<td style="text-align: left;">
									${important.importantTitle }
								</td>
								<td style="text-align: center;">
									${important.createPersonName }
								</td>
								<td style="text-align: center;">
									${important.reportPersonName }
								</td>
								<td style="text-align: center;">
									${important.auditorPersonName }
								</td>
								<td>
									<c:if test="${ important.status==50112}">
										<span style="color:#3366ff">${important.statusName }</span>
									</c:if>
									<c:if test="${ important.status==50113}">
										<span style="color:#00a59d">${important.statusName }</span>
									</c:if>
									<c:if test="${ important.status==50114}">
										<span style="color:#ff399d">${important.statusName }</span>
									</c:if>
									<c:if test="${ important.status==50115}">
										<span style="color:#00cc66">${important.statusName }</span>
									</c:if>
								</td>
								<td>
									<c:if test="${fn:contains(gzwsession_code,',bimWork_yqsjsjsb_query,')==true }">
										<a href="javascript:void(0)" onclick="mload('${pageContext.request.contextPath}/queryPage/adImportant/view?op=check&id=${important.id}')">查看</a>
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
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script type="text/javascript">
	
		$('input.time').jeDate(
			{
				format:"YYYY"
			}
		)
	
		function _query()
		{
			var form = document.forms[0];
			form.action = "${searchurl}";
			form.method = "post";
			form.submit();		
		}

		function del(id){
			parent.win.confirm('确定要删除？',function(r){
				if(r){
					var url = "${pageContext.request.contextPath}/queryPage/adImportant/delete?id=" + id;
					$.post(url, function(data) {
						var commitresult=JSON.parse(data);
						if(commitresult.result){
							win.successAlert('删除成功！',_query);
						}
					
					});
				}
			});
		}
		
		$("#qrybtn").click(_query);
		
	
	</script>
</html>