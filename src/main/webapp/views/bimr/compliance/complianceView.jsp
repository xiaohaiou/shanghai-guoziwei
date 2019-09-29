<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>查看合规调查</title>
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
		<span class="glyphicon glyphicon-pencil"></span>查看合规调查
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
				
				<c:if test="${entity.investigationType==81000 }">
					<label class="w20">是否是集团内部投诉人:</label>
					<label class="w25 setleft">${ entity.isInside==0?"否":"是"}</label>
					
					<label class="w20">是否匿名:</label>
					<label class="w25 setleft">${ entity.isAnonymous==0?"否":"是"}</label>
				</c:if>
				
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
				<br/>
				<label class="w20">待调查事项:</label>
				<label class="w25 setleft">${entity.investigationMatters}</label>
				<br/>
				<label class="w20">投诉举报信:</label>
				<label class="w25 setleft"> 
				<c:if test="${not empty entity.indictment}">
					<c:if test="${not empty indictmentFile}">
						<a href="javascript:void(0)" onclick="downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(indictmentFile.filePath,"\\","\\\\")}&_name=${indictmentFile.fileName}')" target="_blank">${indictmentFile.fileName}</a>
					</c:if>
				</c:if>
				</label>
				<br>
			</div>
			<c:if test="${not empty requestScope.bjInfo}">
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
			</c:if>
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
		function downloadFile(url){
			$.ajax({  
			     url : url,  
			     type : "POST",  
		         success : function(data) {
					var iframe = document.createElement("iframe");  
					iframe.src = url;  
					iframe.style.display = "none";  
					document.body.appendChild(iframe);
			     },  
			     error : function(data) {  
			     	win.errorAlert("下载失败！");
			     }  
			});
		}
</script>
</html>