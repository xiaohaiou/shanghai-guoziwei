<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>工作台预警提醒</title>
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/css/font/iconfont.css"/>" />
		<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/workbench_model.css"/>">
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">
		<style>
			#form1 input,#form1 select{
			    width: calc(90% - 10em);
			}
			.table_content .tablebox{
				margin-top: 40px;
			}
			.tablebox {
				margin-top:16px;
				width: 95%;		
				position:relative;left:50px;
		    }
		    ._a{	
		    	position:relative;left:40px;
		    	font: italic small-caps 600 6pts/9pts 宋体; 
		    	color: #0033FF;
		    }
		</style>
		<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	</head>
	<body class="hn_grey">
		<jsp:include page="/views/public/title.jsp"></jsp:include>
		<div class="table_content">
		<form:form id="form1" class="row">
			<span class="col-md-4">
				<span class="search_title">指标：</span>
				<select name="model_id" id="model_id">
					<option value="">全部</option>
					<c:forEach items="${sessionScope.warnCenterDataOptions}" var="result">
						<option value="${result[0]}" <c:if test="${model_id eq result[0] }" >selected=selected</c:if>>${result[1]}</option>
					</c:forEach>
				</select>  
			</span>
			<span class="col-md-3">
				<span class="search_title">预警时间：</span>
				<input type="text" class="time" value="${time}" name="time" id="time" readonly align="center"/> 
			</span>
			<span class="col-md-3">
				<span class="search_title">预警原因：</span>
				<select name="status" id="status" value="${status}">
					<option value="">全部</option>  
					<option value="51111" <c:if test="${51111 eq status}">selected</c:if> >未及时填报</option>          
				  	<option value="50112" <c:if test="${50112 eq status}">selected</c:if> >未及时上报</option>     
				  	<option value="50113" <c:if test="${50113 eq status}">selected</c:if> >未及时审核</option>  
				</select>  
			</span>			
			<span class="form_div col-md-2">
				<input type="button" value="查询" class="form_btn" id="queryBtn" style="padding-left: 18px !important;">
			</span>
			<c:if test="${fn:contains(gzwsession_code,',bimWork_yblrzxzTb_warn_record,')==true}"><!-- bimWork_yblrzxzTb_warn_record -->
				<span class="col-md-6">
					<a onclick="_record()" class="_a" style="cursor: pointer;">预警记录</a>
				</span>
			</c:if>				
		</form:form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th style="width:5%;">序号</th>
						<th style="width:15%;">指标</th>
						<th style="width:15%;">单位名称</th>
						<th style="width:10%;">时间</th>						
						<th style="width:10%;">预警原因</th>		
						<th style="width:10%;">处理</th>																		
					</tr>
					<c:if test="${!empty requestScope.msgPage.list }">
					<c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
						<c:forEach items="${ requestScope.msgPage.list}" var="msg" varStatus="status">
							<tr>
								<td style="text-align: center;">
									${recordNumber + status.index + 1 }
								</td>
								<td style="text-align: center;">
									${msg.model_name}
								</td>
								<td style="text-align: center;">
									${msg.orgname}
								</td>
								<td style="text-align: center;">
									<c:if test="${msg.month!=null}">
										${msg.year}-${msg.month}																	
									</c:if>
									<c:if test="${msg.month == null}">
										${msg.year}																	
									</c:if>															
								</td>								
								<td style="text-align: center;">
									<c:if test="${ msg.status==51111}">
										<span style="color:#3366ff">未及时填报</span>
									</c:if>								
									<c:if test="${ msg.status==50112}">
										<span style="color:#3366ff">未及时上报</span>
									</c:if>
									<c:if test="${ msg.status==50113}">
										<span style="color:#ff9933">未及时审核</span>
									</c:if>
									<c:if test="${ msg.status==50114}">
										<span style="color:#ff399d">已退回</span>
									</c:if>
									<c:if test="${ msg.status==50115}">
										<span style="color:#00cc66">已审核</span>
									</c:if>
								</td>	
								<td style="text-align: center;">
									<a href="${msg.url}" target="_blank">处理</a>
								</td>																							
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.msgPage.list}">
						<tr>
							<td colspan="5" align="center">
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
	<script type="text/javascript">
		$('input.time').jeDate({
			format:"YYYY-MM"
		});
		$("#queryBtn").click(function() {
			_query();	
		});
		function _query() {
			var form = document.forms[0];
			form.action = "${searchurl}";
			form.method = "post";
			form.submit();
		};
		
		function _record(){
			var url = "/shanghai-gzw/warncenter/recordlist";
			mload(url);
		}
	</script>
</html>