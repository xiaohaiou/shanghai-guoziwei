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
		<title>应收债权(内部)汇总查询</title>
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
		<c:if test="${entityview.type ==0}">
			<h4>应收债权(内部)汇总查询</h4>
		</c:if>	
		<c:if test="${entityview.type ==1}">
			<h4>应收债权(外部)汇总查询</h4>
		</c:if>
		<div class="table_content">
		
		<form id="form1"  class="row">
		
			<span class="col-md-4">
				<span class="search_title">时间：</span>
					<input  type="text" style="text-align: center" name="date" value="${entityview.date }" readonly="true" class="time" />
			</span>
			<span class="col-md-4">
				<span class="search_title">核心企业：</span>
					<select  name="coreorg" class="selectpicker" id="selectpicker1">
						<option value=""  >全部</option>
						<c:forEach items="${requestScope.coreCompSelect}" var="result">
								<option value="${result.id.nnodeId }"  <c:if test="${entityview.coreorg eq result.id.nnodeId}">selected="selected"</c:if>>${result.vcFullName }</option>
						</c:forEach>
					</select>
			</span>
			<span class="col-md-4">
				<span class="search_title" style="width: 9em">债权单位名称：</span>
				<input type="hidden" id="organalID" name="debtorg" value="${entityview.debtorg }" >
				<textarea  id="allCompanyTree" style="display:none;">${allCompanyTree}</textarea>
				<input name="debtorgname" id="checkedCompanyName" value="${entityview.debtorgname }" readonly title="${entityview.debtorgname }" style="width: calc(100% - 10em);">
				
				<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
					<ul id="com_ztree" class="ztree">
	
					</ul>
			   </div>
			    <i class="clear iconfont icon-guanbi"></i>
		   </span> 
			<div class="form_div col-md-12">
				<c:if test="${entityview.type ==0}">
					<c:if test="${fn:contains(gzwsession_code,',bimWork_yszhzcx_nb_query,')==true }">	
						<input type="button" value="查询" class="form_btn" id="qrybtn" onclick="_query()">
					</c:if>
				</c:if>	
				<c:if test="${entityview.type ==1}">
					<c:if test="${fn:contains(gzwsession_code,',bimWork_yszhzcx_wb_query,')==true }">	
						<input type="button" value="查询" class="form_btn" id="qrybtn" onclick="_query()">
					</c:if>
				</c:if>	
					

			</div>
		</form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th>序号</th>
						<th>上报年月</th>
						<th>所属业态</th>
						<th>债权单位名称</th>
						<th>期初余额(元)</th>
						<th>期末余额(元)</th>
						<th>期末超期金额(元)</th>
						<th>坏账金额(元)</th>
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
									${sys.year}-${sys.month }
								</td>
								<td>
									${sys.coreorgname }
								</td>
								<td>
									${sys.debtorgname }
								</td>
								<td>
									<fmt:formatNumber value="${sys.beginningbalance }"  pattern="#,##0.00" />
								</td>
								<td>
									<fmt:formatNumber value="${sys.endingbalance }"  pattern="#,##0.00" />
								</td>
								<td style="text-align: center">
									<fmt:formatNumber value="${sys.overmoney }"  pattern="#,##0.00" />
								</td>
								<td style="text-align: center">
									<fmt:formatNumber value="${sys.badmoney }"  pattern="#,##0.00" />
								</td>
								<td>
									<c:if test="${entityview.type ==0}">
										<c:if test="${fn:contains(gzwsession_code,',bimWork_yszhzcx_nb_query,')==true }">	
										<a href="javascript:void(0)" onclick="checkdata('${mapurl}/view?op=modify&id=${sys.groupid}')" >查看</a>
										 </c:if>
									 </c:if>
									 
									 <c:if test="${entityview.type ==1}">
										 <c:if test="${fn:contains(gzwsession_code,',bimWork_yszhzcx_wb_query,')==true }">	
										<a href="javascript:void(0)" onclick="checkdata('${mapurl}/view?op=modify&id=${sys.groupid}')" >查看</a>
										 </c:if>
									 </c:if>	
									 	
									
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.msgPage.list}">
						<tr>
							<td colspan="9" align="center">
								查询无记录
							</td>
						</tr>
					</c:if>
				</table>
				<jsp:include page="page.jsp"/>
			</div>
		</div>
		<jsp:include page="../../system/modal.jsp"/>
	</body>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/js/listPageCompanyTreeSelect.js"/>" type="text/javascript"></script>
	<script type="text/javascript">
	
		function exportfile()
		{
			document.getElementById('exportfile').click();
		}
	
		function checkdata(url)
		{
			mload(url);
		}
		
		$(' input.time').jeDate(
			{
				format:"YYYY-MM"
			}
		)
		
		function _query()
		{
			debugger;
			var form = document.forms[0];
			form.action = "${searchurl}";
			form.method = "post";
			form.submit();		
		}




		
		
	</script>
</html>