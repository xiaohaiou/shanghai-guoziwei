<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>合规调查归档审核</title>
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
		<span class="glyphicon glyphicon-pencil"></span>合规调查归档审核
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="entity" >
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20">举报投诉、专项调查项目名称:</label>
				<label class="w25 setleft">${ entity.projectName}</label>
				
				<label class="w20">举报投诉、专项调查项目编号:</label>
				<label class="w25 setleft">${ entity.projectCode}</label>
				
				<label class="w20">调查类型:</label>
				<label class="w25 setleft">${ entity.investigationTypeName}</label>
				
				<label class="w20">调查承办企业名称:</label>
				<label class="w25 setleft">${ entity.investigationName}</label>
				
				<label class="w20">调查承办部门:</label>
				<label class="w25 setleft">${ entity.investigationDep}</label>
				
				<label class="w20">调查涉及企业名称:</label>
				<label class="w25 setleft">${ entity.compName}</label>
				
				<label class="w20">调查涉及经营管理事项:</label>
				<label class="w25 setleft">${ entity.itemName}</label>
				
				<label class="w20">调查来源:</label>
				<label class="w25 setleft">${ entity.investigationFromName}</label>
				
				<label class="w20">是否是集团内部投诉人:</label>
				<label class="w25 setleft">${ entity.isInside==0?"否":"是"}</label>
				
				<label class="w20">是否匿名:</label>
				<label class="w25 setleft">${ entity.isAnonymous==0?"否":"是"}</label>
				
				<label class="w20">举报投诉收到时间:</label>
				<label class="w25 setleft">${ entity.accusationTime}</label>
				
				<label class="w20">调查交办领导:</label>
				<label class="w25 setleft">${ entity.assignLeader}</label>
				
				<label class="w20">调查负责人:</label>
				<label class="w25 setleft">${ entity.responsiblePerson}</label>
				<br>
				<label class="w20">数据创建人:</label>
				<label class="w25 setleft">${ entity.createPersonName}</label>
				
				<label class="w20">数据创建时间:</label>
				<label class="w25 setleft">${ entity.createDate}</label>
			</div>
			
			<h3 class="data_title1">调查涉及人员</h3>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th >序号</th>
						<th >调查涉及人员</th>
						<th >现任职务</th>
						<th >员工编号</th>
					</tr>
					<c:if test="${!empty requestScope.personList }">
						<c:forEach items="${ requestScope.personList}" var="l"
							varStatus="status">
							<tr>
								<td style="text-align: center;">${status.index + 1 }</td>
								<td style="text-align: center;">${l.employeeName }</td>
								<td style="text-align: left;">${l.employeePostion }</td>
								<td style="text-align: left;">${l.employeeNumber}</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
			
			<h3 class="data_title1">报告办结信息</h3>
			<div class="model_part">
				<label class="w20">调查报告公文编号:</label>
				<label class="w25 setleft">${bjInfo.officeId}</label>
				<label class="w20">报告名称:</label>
				<label class="w25 setleft">${bjInfo.name}</label>
				<label class="w20">呈报人:</label>
				<label class="w25 setleft">${bjInfo.submitPersonName}</label>
				<label class="w20">报告呈报时间:</label>
				<label class="w25 setleft">${bjInfo.submitTime}</label>
				<label class="w20">报告最终审批人:</label>
				<label class="w25 setleft">${bjInfo.auditPersonName}</label>
				<label class="w20">最终审批人批示意见:</label>
				<label class="w25 setleft">${bjInfo.auditContent}</label>
				<label class="w20">附件:</label>
				<label class="w25 setleft">
				
					<c:if test="${not empty bjfile}">
						<a href="javascript:void(0)" onclick="downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(indictmentFile.filePath,"\\","\\\\")}&_name=${bjfile.fileName}')" target="_blank">${bjfile.fileName}</a>
					</c:if>
				
				</label>
			</div>
			
			<h3 class="data_title1">问责人员维护</h3>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th >序号</th>
						<th >建议问责人员</th>
						<th >被问责事项</th>
						<th >建议问责落实情况</th>
						<th >处分问责呈报公文编号</th>
						<th >处分问责办文编号</th>
					</tr>
					<c:if test="${!empty requestScope.situationPersonList }">
						<c:forEach items="${ requestScope.situationPersonList}" var="l" varStatus="status">
							<tr>
								<td style="text-align: center;">${status.index + 1 }</td>
								<td style="text-align: left;">${l.accountabilityPerson }</td>
								<td style="text-align: left;">${l.accountabilityItem }</td>
								<td style="text-align: left;">${l.suggestInfo }</td>
								<td style="text-align: left;">${l.submitOfficeId }</td>
								<td style="text-align: left;">${l.officeId }</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
			
			<h3 class="data_title1">合规整改问题维护</h3>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th >序号</th>
						<th >提出合规建议情况</th>
						<th >整改责任单位</th>
						<th >整改责任人</th>
						<th >整改完成预计时限</th>
						<th >整改工作进展</th>
						<th >整改完成状态</th>
						<th >操作</th>
					</tr>
					<c:if test="${!empty requestScope.suggestList}">
						<c:forEach items="${ requestScope.suggestList}" var="l" varStatus="status">
							<tr>
								<td style="text-align: center;">${status.index + 1 }</td>
								<td style="text-align: center;">${l.suggest }</td>
								<td style="text-align: left;">${l.accountabilityDep }</td>
								<td style="text-align: left;">${l.accountabilityPerson}</td>
								<td style="text-align: left;">${l.changeLastTime }</td>
								<td style="text-align: left;">${l.changeProgress}</td>
								<td style="text-align: left;">
									<c:if test="${l.changeStatus == 0}">未整改</c:if>
									<c:if test="${l.changeStatus == 1}">已整改</c:if>
									<c:if test="${l.changeStatus == 2}">整改中</c:if>
								</td>
								<td style="text-align: left;">
								<a href="javascript:void(0)" onclick="mload('${mapurl}/correctProblemView?id=${l.id }')">查看</a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
			
			<c:choose>
				<c:when test="${type=='examine' }">
				
					<div class="model_part">
						<label class="w20"><span class="required"> * </span>审核意见:</label>
						<textarea class="w70" rows="5" cols="" id="examineContent" name="examineContent" style="height:5em;" maxlength="500" check="NotEmpty_string_._._._._.">${examineList.examinestr}</textarea>
					</div>
				</c:when>
				<c:otherwise>
					<div class="model_part">
						<label class="w20"><span class="required"> * </span>审核意见:</label>
						<textarea class="w70" rows="5" cols="" id="examineContent" name="examineContent" style="height:5em;" maxlength="500" readonly="readonly" >${examineList.examinestr}</textarea>
					</div>
				</c:otherwise>
			</c:choose>
		
			
			<div class="model_btn">
				<c:if test="${type=='examine' }">
					<button class="btn btn-primary model_btn_ok" id="commit-1" >通过</button>
					<button class="btn btn-primary model_btn_ok" id="commit-2" >退回</button>
				</c:if>
				<input type="hidden" value="${entity.id }" id="id" name="id">
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
		$("#commit-1").click(function(){
			return changeStatus(50116);//通过
		});
		
		$("#commit-2").click(function(){
			return changeStatus(50117);//退回
		});
		function changeStatus(status){
			var examStr=$("#examineContent").val();
			if(!vaild.all())
			{
				return false;
			}
			var url = "${pageContext.request.contextPath}/bimr/compliance/saveCorrectArchive?examResult="+status+"&examStr="+examStr;
			//var formData = new FormData($("#form2")[0]);
			var params = $('#form2').serialize();
			$.post(url, params ,function(data) {
			
				if(data.result==true){
				
					win.successAlert('审核成功！',function(){
						parent.location.reload(true);
						parent.mclose();
						
					});
				}else{
					win.errorAlert('审核失败！');
				}
			
			});
				
			return false;
		}
</script>
</html>