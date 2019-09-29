<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>项目节点查看页面</title>
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
<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
<style>
	table{
		table-layout: fixed;
	}
	table td{
		line-height:2em;
	}
</style>
</head>
<body>
	<h4>
		<span class="glyphicon glyphicon-pencil"></span>查看项目节点
		<i class="iconfont icon-guanbi"></i>
	</h4>
		<form id="form2">
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20"><span class="required"></span>项目名称:</label>
				<label class="w25 setleft">${project.pjName}</label>
				<label class="w20">节点名称:</label>
				<label class="w25 setleft">${node.pnName }</label>
				<label class="w20">计划完成日期:</label>
				<label class="w25 setleft">${node.pnCompletionTime }</label>
				<label class="w20">实际完成日期:</label>
				<label class="w25 setleft">${node.pnFinishTime }</label>
				<label class="w20">节点进度:</label>
				<label class="w25 setleft"><fmt:formatNumber  value="${node.pnProgress }" pattern="##0"/>%</label>
				<label class="w20">节点显示顺序:</label>
				<label class="w25 setleft">${node.pnOrder }</label>
				<label class="w20">节点状态:</label>
				<label class="w25 setleft">
					<c:if test="${node.pnStatus == 0 }">未开始</c:if>
					<c:if test="${node.pnStatus == 1 }">节点进行中</c:if>
					<c:if test="${node.pnStatus == 2 }">节点完成</c:if>
				</label>
				<label class="w20">是否要求:</label>
					<label class="w25 setleft">
					<c:if test="${node.pnIsrequired == 0 }">否</c:if>
					<c:if test="${node.pnIsrequired == 1 }">是</c:if>
					</label>
				<label class="w20">创建人:</label>
				<label class="w25 setleft">${node.createPersonName}</label>
				<label class="w20">创建时间:</label>
				<label class="w25 setleft">${node.createDate}</label>
				<c:if test="${contract.reportStatus == 1 && (not empty node.reportPersonName) && (empty node.approveId)}">
					<label class="w20">上报人:</label>
					<label class="w25 setleft">${node.reportPersonName}</label>
					<label class="w20">上报时间:</label>
					<label class="w25 setleft">${node.reportTime}</label>
				</c:if>
			</div>
			<h3 class="data_title1">上报审核历史信息</h3>
			<div class="model_part" id="history">
				<div class="tablebox">
					<table >
						<tr class="table_header" id="historyTr">
							<th style="width:5%">序号</th>
							<th style="width:10%">上报人</th>
							<th style="width:15%">上报时间</th>
							<th style="width:10%">审核人</th>
							<th style="width:15%">审核时间</th>
							<th style="width:10%">审核结果</th>	
							<th style="width:35%">审核备注</th>
						</tr>
						<c:if test="${not empty histories }">
							<c:forEach items="${histories }" var="history" varStatus="status">
								<tr>
									<td>${status.count}</td>
									<td style="text-align:left;">${history.reportPersonName}</td>
									<td>${history.reportTime}</td>
									<td style="text-align:left;">${history.approve.createPersonName}</td>
									<td>${history.approve.createDate}</td>
									<td>
										${history.approve.approveResult == '1'?'通过':'退回' }
									</td>
									<td style="word-wrap:break-word;text-align: left;">${history.approve.remark}</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty histories }">
							<tr>
								<td colspan="7" align="center">
									查询无记录
								</td>
							</tr>
						</c:if>	
					</table>
				</div>
			</div>
			<div class="model_btn">
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
		</div>
		</form>
</body>
<script type="text/javascript">
	//关闭弹窗
	$(".model_btn_close").click(function () {
        window.parent.mclose();
		return false;
	});
	$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
</script>
</html>