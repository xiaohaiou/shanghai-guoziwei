<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>保密风险事件数量查询页面</title>
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
		<h4>保密风险事件数据查询</h4>
		<div class="table_content">
		
		<form:form id="form1" class="row"  >
			<span class="col-md-4">
				<span class="search_title">年份：</span>
				<input type="text" value="${entity.year }" name="year" readonly="true" class="time"/>
			</span>
			<span class="col-md-4">
				<span class="search_title">季度：</span>
				<select  name="season" class="selectpicker"  >
				<option value="">全部</option>
					<c:forEach items="${requestScope.seasontype}" var="result">
							<option value="${result.id }"  <c:if test="${entity.season == result.id}">selected="selected"</c:if>>${result.description }</option>
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
				<c:if test="${fn:contains(gzwsession_code,',bimWork_bmfxsjsjsb_query,')==true }">
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
						<th>事件标题</th>
						<th>创建人</th>
						<th>上报人</th>
						<th>审核人</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
					<c:if test="${!empty requestScope.msgPage.list }">
					<c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
						<c:forEach items="${ requestScope.msgPage.list}" var="riskevent" varStatus="status">
							<tr>
								<td style="text-align: center;">
									${recordNumber + status.index + 1 }
								</td>
								<td style="text-align: center;">
									${riskevent.year }${riskevent.seasonName }
								</td>
								<td style="text-align: left;">
									${riskevent.riskCompName }
								</td>
								<td style="text-align: left;">
									${riskevent.eventTitle }
								</td>
								<td style="text-align: center;">
									${riskevent.createPersonName }
								</td>
								<td style="text-align: center;">
									${riskevent.reportPersonName }
								</td>
								<td style="text-align: center;">
									${riskevent.auditorPersonName }
								</td>
								<td>
									<c:if test="${ riskevent.status==50112}">
										<span style="color:#3366ff">${riskevent.statusName }</span>
									</c:if>
									<c:if test="${ riskevent.status==50113}">
										<span style="color:#00a59d">${riskevent.statusName }</span>
									</c:if>
									<c:if test="${ riskevent.status==50114}">
										<span style="color:#ff399d">${riskevent.statusName }</span>
									</c:if>
									<c:if test="${ riskevent.status==50115}">
										<span style="color:#00cc66">${riskevent.statusName }</span>
									</c:if>
								</td>
								<td>
									<c:if test="${fn:contains(gzwsession_code,',bimWork_bmfxsjsjsb_query,')==true }">
										<a href="javascript:void(0)" onclick="mload('${pageContext.request.contextPath}/queryPage/adRiskevent/view?op=check&id=${riskevent.id}')">查看</a>
									</c:if>
									<%-- <c:if test="${fn:contains(gzwsession_code,',bimWork_bmfxsjsjsb_delete,')==true }">
										<c:if test="${riskevent.status==50114 || riskevent.status==50112 }">
											<a href="javascript:;" onclick="del(${riskevent.id})">删除</a>
										</c:if>
									</c:if> --%>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.msgPage.list}">
						<tr>
							<td colspan="10" align="center">
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
					var url = "${pageContext.request.contextPath}/adRiskevent/delete?id=" + id;
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