<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>查看资源目录清单</title>
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
</head>
<body>
	<h4>
		<span class="glyphicon glyphicon-pencil"></span>查看资源目录清单
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="entity" >
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20">资源上报单位名称:</label>
				<label class="w25 setleft">${ entity.compName}</label>
				
				<label class="w20">资源编号:</label>
				<label class="w25 setleft">${ entity.eventNum}</label>
				
				<label class="w20">资源上报时间:</label>
				<label class="w25 setleft">${ entity.reportTime}</label>
				
				<label class="w20">资源关键标题:</label>
				<label class="w25 setleft">${ entity.eventTitle}</label>
				
				<label class="w20">资源具体描述:</label>
				<label class="w70 setleft">${ entity.eventDescribe}</label>
				
				<label class="w20">资源使用情况:</label>
				<label class="w25 setleft">${ entity.eventForRisk}</label>
				
				<%-- <label class="w20">采取措施:</label>
				<label class="w25 setleft">${ entity.adoptStrategyName}</label> --%>
				
				<label class="w20">资源状态:</label>
				<label class="w25 setleft">${ entity.copingStrategyName}</label>
				
				<label class="w20">是否需要提醒资源使用状态:</label>
				<label class="w25 setleft">${ entity.isImportant==0?'否':'是'}</label>
				
				<label class="w20">上报单位所在省份:</label>
				<label class="w25 setleft">${ entity.province}</label>
				
			<c:if test="${entity.ishighunit == 0}">
				<label class="w20">是否提请上级单位统筹:</label>
				<label class="w25 setleft">否</label>
				
				<label class="w20">拟采取的资源使用措施:</label>
				<label class="w25 setleft">${entity.planmeasure}</label>
				
				<label class="w20">预计资源全部使用的时间:</label>
				<label class="w25 setleft">
					<c:if test="${entity.plancloesetime == 0}">半个月内</c:if>
					<c:if test="${entity.plancloesetime == 1}">一个月内</c:if>
					<c:if test="${entity.plancloesetime == 2}">季度内</c:if>
					<c:if test="${entity.plancloesetime == 3}">半年度内</c:if>
					<c:if test="${entity.plancloesetime == 4}">年度内</c:if>
					<c:if test="${entity.plancloesetime == 5}">不确定</c:if>
				</label>
			</c:if>
			<c:if test="${entity.ishighunit == 1}">
				<label class="w20">是否提请上级单位统筹:</label>
				<label class="w25 setleft">是</label>
				
				<label class="w20">处理方式:</label>
				<label class="w25 setleft">
					<c:if test="${entity.highunitmeasure == 83000}">在专业公司层面协调统筹</c:if>
					<c:if test="${entity.highunitmeasure == 83001}">在核心企业层面协调统筹</c:if>
					<c:if test="${entity.highunitmeasure == 83002}">在产业集团层面协调统筹</c:if>
				</label>
			</c:if>
			
			<c:if test="${entity.copingStrategy != 82002}">
				<label class="w20">资源使用跟踪人:</label>
				<label class="w25 setleft">${entity.trackpersonname}</label>
	
			</c:if>
				<%-- <label class="w20">责任单位:</label>
				<label class="w25 setleft">${ entity.responsibleCompName}</label>
				
				<label class="w20">责任部门:</label>
				<label class="w25 setleft">${ entity.responsibleDep}</label>
				
				
				<label class="w20">责任人员:</label>
				<label class="w25 setleft">${ entity.responsiblePerson}</label> --%>
				<%--
			    <label class="w20">应对完成时间:</label>
			    <label class="w25 setleft">${ entity.completeTime}</label>
				
				<label class="w20">备注:</label>
				<label class="w25 setleft">${ entity.remark}</label>
				--%>
			</div>
			<c:if test="${!empty examineList}">
				<div class="model_part">
					<table>
					<caption>历史资源使用审核记录</caption>
					<tr>
						<th width="15%">审核时间</th>
						<th width="10%">审核人</th>
						<th width="10%">审核结果</th>
						<th width="60%">审核意见</th>
					</tr>
						<c:forEach items="${examineList}" var="examine" varStatus="status">
							<tr>
								<td>${examine.createDate }</td>
								<td>${examine.createPersonName }</td>
								<c:if test="${examine.examresult eq '50116'}">
									<td>审核通过</td>
								</c:if>
								<c:if test="${examine.examresult eq '50117'}">
									<td>审核不通过</td>
								</c:if>
								<td>${examine.examinestr }</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</c:if>
			
			<c:if test="${!empty feedbackexamineList}">
				<div class="model_part">
					<table>
					<caption>历史资源使用反馈审核记录</caption>
					<tr>
						<th width="15%">审核时间</th>
						<th width="10%">审核人</th>
						<th width="10%">审核结果</th>
						<th width="60%">审核意见</th>
					</tr>
						<c:forEach items="${feedbackexamineList}" var="examine" varStatus="status">
							<tr>
								<td>${examine.createDate }</td>
								<td>${examine.createPersonName }</td>
								<c:if test="${examine.examresult eq '50116'}">
									<td>审核通过</td>
								</c:if>
								<c:if test="${examine.examresult eq '50117'}">
									<td>审核不通过</td>
								</c:if>
								<td>${examine.examinestr }</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</c:if>
			
			<h3 class="data_title1">即时反馈&nbsp;&nbsp;</h3>
				<div class="tablebox">
					<table id="personTab">
						<tr class="table_header">
							<th >反馈日期</th>
							<th >目前资源具体情况</th>
							<th >资源使用现状</th>
							<c:if test="${entity.ishighunit == 0}">
							<th >资源状态目前落实情况</th>
							</c:if>
						</tr>
						<c:if test="${!empty requestScope.feedbackList }">
							<c:forEach items="${ requestScope.feedbackList}" var="l"
								varStatus="status">
								<tr>
									<td style="text-align: center;">${l.feedbacktime }</td>
									<td style="text-align: center;">
										<c:if test="${l.status == 0}">好转改观</c:if>
										<c:if test="${l.status == 1}">保持关注</c:if>
										<c:if test="${l.status == 2}">恶化蔓延</c:if>
									</td>
									<td style="text-align: center;">
										${l.nowdetailsituation }
									</td>
									<c:if test="${entity.ishighunit == 0}">
										<td style="text-align: center;">
											${l.measuresituation} 
										</td>
									</c:if>
								</tr>
							</c:forEach>
						</c:if>
					</table>
				</div>	
				<c:if test="${entity.ishighunit == 1}">
				<h3 class="data_title1">应对措施</h3>
				<div class="tablebox">
					<table id="personTab">
						<tr class="table_header">
							<th >反馈日期</th>
							<th >资源状态应对措施</th>
							<th >预计措施达成时间</th>
							<th >责任部门</th>
							<th >目前应对措施落实完成情况</th>
						</tr>
						<c:if test="${!empty requestScope.adoptstrategyFeedbackList }">
							<c:forEach items="${ requestScope.adoptstrategyFeedbackList}" var="l"
								varStatus="status">
								<tr>
									<td style="text-align: center;">${l.feedbacktime }</td>
									<td style="text-align: center;">
									    ${l.adoptStrategyname}
									</td>
									<td style="text-align: center;">${l.planfinishtime }</td>
									<td style="text-align: center;">${l.responsibleCompName }</td>
									<td style="text-align: center;">${l.nowfinishsituation }</td>							
								</tr>
							</c:forEach>
						</c:if>
					</table>
				</div>
			</c:if>	
			<div class="model_btn">
				
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
		</div>
		<jsp:include page="/views/system/modal.jsp" />
	</form:form>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>
<script type="text/javascript">
		//关闭弹窗
		$(".model_btn_close").click(function () {
			parent.mclose();
			return false;
		});
		
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		
		function commit1(){
			if(!vaild.all())
			{
				return false;
			}
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			
			var entityBasicInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/inspectproject/saveOrUpdateOrder";
			$.post(url, entityBasicInfo, function(data) {
				$.unblockUI();
				var commitresult=JSON.parse(data);
				if(commitresult.result==true){
				
					win.successAlert('保存成功！',function(){
							parent.location.reload();
							parent.mclose();
					});
				}
				else
				{
					win.errorAlert(commitresult.exceptionStr);
				}
			});
			return false;
		}
		
		$("#commit-1").click(function(){
			return commit1();
		});
</script>
</html>