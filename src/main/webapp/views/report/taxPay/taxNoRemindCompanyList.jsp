<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>税务未填报公司查询</title>
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
		<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
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
		<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	</head>
	<body>
		<h4>税务未填报公司查询</h4>
		<c:if test="${backInfo==true}">
			<h7 style="color:#ff0000;">指令系统中无法获取以下公司员工号邮箱！</h7>		
		</c:if>
		<div class="table_content">
		<form id="form1"  class="row"  >
			<span class="col-md-12" style="text-align: right">
				<c:if test="${fn:contains(gzwsession_code,',bimWork_swwtbgscx_wtb_remind,')==true }">	<!--bimWork_swwtbgscx_wtb_remind  -->
					<button id="backBtn" onclick="commondOn()" class="form_btn" style="width:100px;">下达指令</button>
				</c:if>
				<button id="backBtn" onclick="back()" class="form_btn" style=" width:80px; ">返回</button>	
			</span>
			<input type="hidden" name="reportTime" value="${reportTime}"/>
			<input type="hidden" name="remindKind" value="${remindKind}"/>
			<input type="hidden" name="remindOrg" value="${remindOrg}"/>
			<input type="hidden" name="pageNums" value="${pageNums}"/>
		</form>
		<form id="form_"></form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th>序号</th>
						<th width="20%">公司全称</th>
						<th width="20%">公司简称</th>
						<th>负责人</th>						
						<th>负责人账号</th>						
						<th width="20%">负责人邮箱</th>		
						<th width="10%">最后提醒时间</th>
					</tr>
					<c:if test="${not empty requestScope.msgPage.list}">
						<c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
						<c:forEach items="${ requestScope.msgPage.list}" var="sys" varStatus="status1">
							<tr>
								<td>
									${recordNumber+ status1.index + 1 }
								</td>
							
								<td>
									${sys[0]}
								</td>
								<td>
									${sys[1]}
								</td>
								<td style="text-align: center">
									${sys[4]}
								</td>
								<td style="text-align: center">
									${sys[5]}
								</td>
								<td style="text-align: center">
									${sys[6]}
								</td>	
								<td style="text-align: center">
									${sys[8]}
								</td>	
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.msgPage.list}">
						<tr>
							<td colspan="2" align="center">
								查询无记录
							</td>
						</tr>
					</c:if>
				</table>
				<jsp:include page="../../system/page.jsp"/>
			</div>
		</div>
	</body>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
	<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
	<script type="text/javascript">
	
		function back(){
			$("input[name='reportTime']").val('${reportTime}');
			var form = document.forms[0];
			form.action = "${pageContext.request.contextPath}/taxPay/taxNoCreateCompanyList";
			form.method = "post";
			form.submit();		
		}
		
		function commondOn(){
			$("input[name='reportTime']").val('${reportTime}');
			var form = document.forms[0];
			form.action = "${pageContext.request.contextPath}/taxPay/commondDown";
			form.method = "post";
			form.submit();	
		}
	
		//清空
		$('.clear').on('click',function(){
			$(this).siblings('input[name="organalname"]').val('');
			$("#organalID").val("");
			$("#checkedCompanyName").attr('title','');
		});
	
	</script>
</html>