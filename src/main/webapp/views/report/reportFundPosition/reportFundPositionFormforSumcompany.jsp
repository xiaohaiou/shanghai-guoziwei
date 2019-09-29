<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>资金头寸数据汇总</title>
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/workbench_model.css"/>">		
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">
		<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	</head>
	<body>
		<h4>
			资金头寸单位数据汇总
			<i class="iconfont icon-guanbi"></i>	
		</h4>
		
		<div class="tablebox">
			<table>
				<tr class="table_header">
					<th>序号</th>
					<th>时间</th>
					<th>单位名称</th>
					<th>当日余额</th>
					<th>境内头寸</th>
					<!-- <th>境外头寸</th> -->
					<th>创建人</th>
					<th>上报人</th>
					<th>审核人</th>
					<th>状态</th>
				</tr>
				<c:if test="${!empty requestScope.msgPage.list}">
					<c:forEach items="${ requestScope.msgPage.list}" var="sys" varStatus="status">
						<tr>
							<td style="text-align: center;">
								${(requestScope.msgPage.pageNum-1)*10+ status.index + 1 }
							</td>
							<td style="text-align: center;">
								${sys.date}
							</td>
							<td style="text-align: left;">
								${sys.orgname}
							</td>
							<td style="text-align: right;">
								<fmt:formatNumber value="${sys.dailyBalance}"  pattern="#,##0.0000" />亿元
							</td>
							<td style="text-align: right;">
								<fmt:formatNumber value="${sys.dataFundPositionRmbs.cashR}"  pattern="#,##0.0000" />亿元
							</td>
							<%-- <td style="text-align: right;">
								${sys.overseasCash}亿美元
							</td> --%>
							<td style="text-align: center;">
								${sys.createPersonName}
							</td>
							<td style="text-align: center;">
								${sys.reportPersonName}
							</td>
							<td style="text-align: center;">
								${sys.auditorPersonName}
							</td>
							<td>
								<c:if test="${ sys.status==50112}">
									<span style="color:#3366ff">未上报</span>
								</c:if>
								<c:if test="${ sys.status==50113}">
									<span style="color:#00a59d">待审核</span>
								</c:if>
								<c:if test="${ sys.status==50114}">
									<span style="color:#ff399d">已退回</span>
								</c:if>
								<c:if test="${ sys.status==50115}">
									<span style="color:#00cc66">已审核</span>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty requestScope.msgPage.list}">
					<tr>
						<td colspan="14" align="center">
							查询无记录
						</td>
					</tr>
				</c:if> 
			</table>
			<jsp:include page="../../system/page.jsp"/>
		</div>
		<jsp:include page="../../system/modal.jsp"/>
	</body>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
	<script type="text/javascript">
		
		//关闭弹窗		
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		
		$(".model_btn_close").click(function () {
			parent.mclose();
			return false;
		});
		
		//清空
		$('.clear').on('click',function(){
			$(this).siblings('input[name="orgname"]').val('');
			$("#organalId").val("");
			$("#company").attr('title','');
		})
	
	</script>
</html>