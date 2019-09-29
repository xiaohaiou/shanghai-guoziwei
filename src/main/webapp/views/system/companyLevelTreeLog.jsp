<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="utf-8">
	<title>财务树修改记录</title>
	<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
	<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
	<!-- 库|插件 -->
	<!-- 新加样式 -->
	<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/workbench_model.css"/>">
	<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	<style type="text/css">
			.tablebox table tr:first-child {
			    background: rgba(0, 0, 0, 0);
			}
			.tablebox table tr:first-child:hover {
			    background: #f2f8ff;
			}
			.form_btn{
				text-align:center;
			}
	</style>
	
	</head>
	<body>
		<h4>
			<span class="glyphicon glyphicon-pencil"></span>财务树修改记录
			<i class="iconfont icon-guanbi"></i>
		</h4>
		<div class="table_content">
			<form id="form1"  class="row"  >
				<span class="col-md-4">
					<span class="search_title" style="padding: 0 !important;width: 8em;margin-left: -1em;">时间：</span>
					<input  type="text" value="${date1}" name="date1" readonly class="time1 time_short" />
					至
					<input  type="text" value="${date2}" name="date2" readonly class="time2 time_short" />
				</span>
				<span class="col-md-4">
				<span class="search_title" style="padding: 0 !important;width: 8em;margin-left: -1em;">操作类型：</span>	
					<select  name="operate_type" class="selectpicker">
						<option value=""  >全部</option>						
						<option value="新增节点" <c:if test="${'新增节点' eq operate_type}">selected="selected"</c:if>>新增节点</option>	
						<option value="编辑节点" <c:if test="${'编辑节点' eq operate_type}">selected="selected"</c:if>>编辑节点</option>																
						<option value="固化财务树" <c:if test="${'固化财务树' eq operate_type}">selected="selected"</c:if>>固化财务树</option>	
						<option value="公司位置变动" <c:if test="${'公司位置变动' eq operate_type}">selected="selected"</c:if>>公司位置变动</option>
						<!--
						 <c:forEach items="${requestScope.financingCategory}" var="result">
							<option value="${result.id }"  <c:if test="${entityview.category == result.id}">selected="selected"</c:if>>${result.description }</option>
						</c:forEach>
						-->
					</select>
				</span>		
				<span class="col-md-4">
					<span class="search_title" style="padding: 0 !important;width: 8em;margin-left: -1em;">单位：</span>
					<input  type="text" value="${orgName}" name="orgName"/>
				</span>			
					
				<div class="form_div col-md-12">
					<c:if test="${fn:contains(gzwsession_code,',bimWork_cwstz_guhua_operation_log_query,')==true }">	
						<button class="form_btn" id="qrybtn" onclick="_query()">查询</button>
					</c:if>
					<button class="form_btn model_btn_close">关闭</button>
				</div>
				<div class="form_div col-md-12"></div>
			</form>
				
			<div class="tablebox">			
				<table>
					<tr class="table_header">
						<th>序号</th>
						<th>时间</th>																	
						<th>单位名称</th>
						<th>操作时间</th>
						<th>操作类型</th>
						<th>操作描述</th>
					</tr>
					<c:if test="${!empty requestScope.msgPage.list }">
						<c:forEach items="${msgPage.list}" var="sys" varStatus="status">
							<tr>
								<td>
									${status.index + 1 }
								</td>
								<td style="text-align: center;">
									${sys.year}-${sys.month}-${sys.day}
								</td>	
								<td style="text-align: left;">
									${sys.vcFullName}
								</td>	
								<td style="text-align: center;">
									${sys.operate_time}
								</td>	
								<td style="text-align: center;">
									${sys.operate_type}
								</td>									
								<td style="text-align: center;">
									${sys.operate_desc}
								</td>																																																					
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.msgPage.list }">
						<tr>
							<td colspan="4" align="center">
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
	<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/js/listPageCompanyTreeSelect.js"/>" type="text/javascript"></script>
	
	<script type="text/javascript">
		
		$(' input.time1').jeDate(
			{
				format:"YYYY-MM-DD",
			}
		)
		
		$(' input.time2').jeDate(
			{
				format:"YYYY-MM-DD",
			}
		)
		
		function _query()
		{
			var form = document.forms[0];
			form.target = "_self";
			form.action = "${searchurl}";
			form.method = "post";
			form.submit();		
		}
		
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		
		//关闭弹窗
		$(".model_btn_close").click(function () {
			parent.mclose();
			return false;
		});
		
	</script>
</html>