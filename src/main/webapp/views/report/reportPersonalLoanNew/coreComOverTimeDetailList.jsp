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
		<title>个人借款信息汇总查询</title>
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		
		<!-- 库|插件 -->
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
		<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>">
		<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">
		<style type="text/css">
			.selectone{
				white-space: nowrap;
				font-size: 14px;
				cursor: pointer;
			}
			.selectone:hover, .selectone.selectedOne{
				box-sizing: border-box;
				background-color: rgba(255,255,255,0.8);
				border-bottom:1px solid rgba(0,0,0,0.5);
			}
			.selectone>* {
				display:inline-block;
				min-width: 60px;
			}
			.selectedOne{
				border-color:#4a22cc;
				color: #4a22cc;
			}
			#com_ztree span {
				padding-left:0;
			}
		</style>
	
	</head>
	<body>
		<h4>个人借款信息汇总查询</h4>
		<div class="table_content">
		
		<form id="form1"  class="row">
		
			<span class="col-md-4">
				<span class="search_title">年份：</span>
					<input  type="text" style="text-align: center" name="year" value="${entityview.year }" readonly="true" class="time" />
			</span>
			
			<%-- <span class="col-md-4"><span class="search_title">时间：</span> 
			<input type="text" name="yearStart" 
				value="${yearStart}" readonly="true" class="time" style="width:calc(50% - 8em);"/> 
			
			- 
				<input type="text" name="yearEnd"
				value="${yearEnd}" readonly="true" class="time" style="width:calc(50% - 8em);"/>
			</span> --%>
			<span class="col-md-4">
				<span class="search_title">周数：</span>
					<input  type="text" style="text-align: center" name="week" value="${entityview.week }"/>
			</span>
			<span class="col-md-4">
				<span class="search_title">核心企业：</span>
					<select  name="coreorg" class="selectpicker" id="selectpicker1" onchange="changeOrgname(this.value)">
						<c:forEach items="${requestScope.coreCompSelect}" var="result">
								<option value="${result.id.nnodeId }"  <c:if test="${entityview.coreorg eq result.id.nnodeId}">selected="selected"</c:if>>${result.vcFullName }</option>
						</c:forEach>
					</select>
			</span>
			
			<%-- <span class="col-md-4"><span class="search_title">时间：</span> 
			<input type="text" name="DateStart" 
				value="${DateStart}" readonly="true" class="time1" style="width:calc(50% - 8em);"/> 
			
			- 
				<input type="text" name="DateEnd"
				value="${DateEnd}" readonly="true" class="time1" style="width:calc(50% - 8em);"/>
			</span> --%>

			<div class="form_div col-md-12">
					<c:if test="${fn:contains(gzwsession_code,',bimWork_grjkxxhz_query,')==true }">	
						<input type="button" value="查询" class="form_btn" id="qrybtn" onclick="_query()">
					</c:if>
					<c:if test="${fn:contains(gzwsession_code,',bimWork_grjkxxhz_export,')==true }">	
						<input type="button" value="导出" class="form_btn" id="exportbtn" onclick="_export()">
					</c:if>
			</div>
		</form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th>序号</th>
						<th>上报期间</th>
						<th>所属核心企业</th>
						<th>公司名称</th>
						<th>借款金额合计(万元)</th>
						<th>超期金额合计(万元)</th>
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
									${sys.year}年第${sys.week }周
								</td>
								<td>
									${sys.coreorgname }
								</td>
								<td style="text-align: center">
									${sys.orgname }
								</td>
								<td style="text-align: center">
									<fmt:formatNumber value="${sys.loantotalmoney }"  pattern="#,##0.00" />
								</td>
								<td style="text-align: center">
									<fmt:formatNumber value="${sys.loantotalovermoney }"  pattern="#,##0.00" />
								</td>
								<td>
									<c:if test="${fn:contains(gzwsession_code,',bimWork_grjkxxhz_query,')==true }">
									<a href="javascript:void(0)" onclick="checkdata('${mapurl}/view?op=modify&id=${sys.groupid}')" >查看</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.msgPage.list}">
						<tr>
							<td colspan="7" align="center">
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
	<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
	
	<script type="text/javascript">

		
		$(' input.time').jeDate(
			{
				format:"YYYY"
			}
		)
		$(' input.time1').jeDate(
			{
				format:"YYYY-MM-DD"
			}
		)
	
 		function checkdata(url)
		{
			debugger;
			mload(url);
		}

	 	//清空
		$('.clear').on('click',function(){
			$(this).siblings('input[name="orgname"]').val('');
			$("#org").val("");
			$("#checkedCompanyName").attr('title','');
		}); 

		
		function _query()
		{
			var form = document.forms[0];
			form.action = "${searchurl}";
			form.method = "post";
			form.submit();		
		}
		
		function _export(){
			var form = document.forms[0];
			form.action = "${pageContext.request.contextPath}/reportpersonalLoanNew/coreComOvertimeExport";
			form.method = "post";
			form.submit();
		}
		
		
	</script>
</html>