<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>刑事案件详情</title>
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
	</head>
	<body>
		<h4>
			<span class="glyphicon glyphicon-pencil"></span>刑事案件详情
			<i class="iconfont icon-guanbi"></i>
		</h4>
		<form:form id="form1" modelAttribute="entity" method="post">
			<form:hidden path="id" value="${entity.id}"/>
			<div class="label_inpt_div">
				<div class="model_part">
					<label class="w20">案件编号：</label>
					<label class="w25 setleft">${entity.caseNum}</label>
					
					<label class="w20">案件归属单位：</label>
					<label class="w25 setleft">${entity.vcCompanyName}</label>
						
					<label class="w20">犯罪嫌疑人：</label>
					<label class="w25 setleft">${entity.suspect}</label>
						
					<label class="w20">职务：</label>
					<label class="w25 setleft">${entity.post}</label>
					
					<label class="w20">涉嫌罪名：</label>
					<label class="w25 setleft">${entity.accusation}</label>
					
					<label class="w20">受案单位所在地(省份)：</label>
					<label class="w25 setleft">${entity.province}</label>
					
					<label class="w20">基本案情：</label>
					<label class="w25 setleft">${entity.baseMessage}</label>
					
					<label class="w20">涉案金额：</label>
					<label class="w25 setleft">${entity.amount}</label>
					
					<label class="w20">是否是清案遗留案件：</label>
					<label class="w25 setleft">
						<c:if test="${entity.isLeftoverCases eq '0'}">否</c:if>
						<c:if test="${entity.isLeftoverCases eq '1'}">是</c:if>
					</label>
					
					<label class="w20">是否是新增案件(回头看)：</label>
					<label class="w25 setleft">
						<c:if test="${entity.isNewadd eq '0'}">否</c:if>
						<c:if test="${entity.isNewadd eq '1'}">是</c:if>
					</label>
					
					<label class="w20">是否因人员优化工作引发案件：</label>
					<label class="w25 setleft">
						<c:if test="${entity.isStaffOptimization eq '0'}">否</c:if>
						<c:if test="${entity.isStaffOptimization eq '1'}">是</c:if>
					</label>
					
					<label class="w20">案件类型：</label>
					<label class="w25 setleft">${entity.type}</label>
					
					<label class="w20">最新进展：</label>
					<label class="w25 setleft">${entity.latestProgress}</label>
						
					<label class="w20">办案单位：</label>
					<label class="w25 setleft">${entity.caseHandlingUnit}</label>
						
					<label class="w20">办案人：</label>
					<label class="w25 setleft">${entity.caseHandlingPerson}</label>
						
					<label class="w20">集团内部承办单位：</label>
					<label class="w25 setleft">${entity.groupCompany}</label>
					
					<label class="w20">案发日期：</label>
					<label class="w25 setleft">${entity.caseDate}</label>
					
					<label class="w20">案龄(年)：</label>
					<label class="w25 setleft">${entity.caseAge}</label>
					
					<label class="w20">法务承办人：</label>
					<label class="w25 setleft">${entity.lawWork}</label>
						
					<label class="w20">是否已问责：</label>
					<label class="w25 setleft">
						<c:if test="${entity.isAccountability eq '0'}">否</c:if>
						<c:if test="${entity.isAccountability eq '1'}">是</c:if>
					</label>
						
					<label class="w20">是否进行成因分析：</label>
					<label class="w25 setleft">
						<c:if test="${entity.isAnalysisReason eq '0'}">否</c:if>
						<c:if test="${entity.isAnalysisReason eq '1'}">是</c:if>
					</label>
					<label class="w20">案件成因(物流)：</label>
					<label class="w25 setleft">${entity.analysisCause}</label>
					
					<label class="w20">问责结果(含问责文件编号，人数及人员名单)(物流)：</label>
					<label class="w25 setleft">${entity.accountabilityResults}</label>
					
					<label class="w20">协办人(物流)：</label>
					<label class="w25 setleft">${entity.assistingPeople}</label>
					
					<label class="w20">案件移交公文编号：</label>
					<label class="w25 setleft">${entity.transferredNumber}</label>
					<label class="w20">案件结案公文编号：</label>
					<label class="w25 setleft">${entity.closingNumber}</label>
					<label class="w20">规划结案时间：</label>
					<label class="w25 setleft">${entity.casePlanDate}</label>
					<label class="w20">备注1：</label>
					<label class="w25 setleft">${entity.remark1}</label>
					<label class="w20">备注2：</label>
					<label class="w25 setleft">${entity.remark2}</label>
					<label class="w20">备注3：</label>
					<label class="w25 setleft">${entity.remark3}</label>
					<label class="w20">备注4：</label>
					<label class="w25 setleft">${entity.remark4}</label>
					<br>
					<label class="w20">报案书:</label>
				
					<label class="w25 setleft"> 
					<c:if test="${not empty entity.reportment}">
					<c:if test="${not empty reportmentFile}">
						<a href="javascript:void(0)" onclick="downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(reportmentFile.filePath,"\\","\\\\")}&_name=${reportmentFile.fileName}')" target="_blank">${reportmentFile.fileName}</a>
					</c:if>
				</c:if></label>
				<br>
				<label class="w20">判决书:</label>
				<label class="w25 setleft"> 
					<c:if test="${not empty entity.judgment}">
					<c:if test="${not empty judgmentFile}">
						<a href="javascript:void(0)" onclick="downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(judgmentFile.filePath,"\\","\\\\")}&_name=${judgmentFile.fileName}')" target="_blank">${judgmentFile.fileName}</a>
					</c:if>
				</c:if></label>
				<br>
				<label class="w20"> 其它:</label>
				<label class="w25 setleft">
					<c:if test="${not empty entity.otherFile}">
					<c:if test="${not empty oFile}">
						<a href="javascript:void(0)" onclick="downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(oFile.filePath,"\\","\\\\")}&_name=${oFile.fileName}')" target="_blank">${oFile.fileName}</a>
					</c:if>
				</c:if></label>	
				</div>
				<h4><span style="font-weight: 1px;">刑事案件问责人员</span><a href="javascript:void(0)"
											onclick="mload('${pageContext.request.contextPath}/bimr/archives/companyTree/addCaseAccountable?projectId=${entity.id}&projectType=4')" class="form_btn">新增</a></h4>
			<div class="tablebox">
				<table style="width: 100%;">
					<tr class="table_header">
						<th >序号</th>
						<th >项目编号</th>
						<th >被问责人员</th>
						<th >状态</th>
						<th >操作</th>
					</tr>
					<c:if test="${not empty accountable_list }">
						<c:forEach items="${accountable_list}" var="p" varStatus="status">
							<tr>
								<td>${status.index + 1}</td>
								<td>${p.projectName}</td>
								<td>${p.accountabilityPersion}</td>
								
								<td><c:if test="${p.status==1}">未上报</c:if><c:if test="${p.status==2}">已上报</c:if><c:if test="${p.status==4}">已退回</c:if><c:if test="${p.status==3||p.status==5}">已审核</c:if></td>
								<td><c:if test="${p.status==3}">
								<a href="javascript:void(0)" onclick="mload('${pageContext.request.contextPath}/bimr/archives/companyTree/EmployeeAbilityInformation?accountablePeople=${p.accountabilityPersion}&accountablePeopleId=${p.accountabilityPersionId}&projecttype=4&id=${p.id}&projectId=${p.projectId }&questionId=0')">问责信息维护</a>
								</c:if>
								<c:if test="${p.status==5}">
								<a href="javascript:void(0)" onclick="mload('${pageContext.request.contextPath}/bimr/archives/companyTree/showDuty?accountableId=${p.id}')">查看</a>
								</c:if>
								<c:if test="${p.status==1}">
								<a href="javascript:void(0)" onclick="mload('${pageContext.request.contextPath}/bimr/archives/companyTree/updateAccountable?id=${p.id}')">修改</a>
								<a href="javascript:void(0)" onclick="del(${p.id})">删除</a>
								</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
				<c:if test="${!empty examineList}">
					<div class="model_part">
						<table>
						<caption>历史审核记录</caption>
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
				<div class="model_btn">
					<button class="btn btn-default model model_btn_close">关闭</button>
				</div>
			</div>
			<jsp:include page="../../system/modal.jsp"/>
		</form:form>
	</body>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script type="text/javascript">
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		//关闭弹窗
		$(".model_btn_close").click(function () {
		  parent.query();
	       //window.parent.location.reload();
	        window.parent.mclose();
			return false;
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
		function del(id){
        win.confirm('确定要删除？',function(r){
				if(r){
					var url = "${pageContext.request.contextPath}/bimr/archives/companyTree/deleteAccountable?id="+id;
					$.post(url, function(data) {
						if(data == "success") {
							win.successAlert('删除成功！');
							 window.location.reload();
						}else{
							win.errorAlert('该数据已被操作！',function(){
								 window.location.reload();
							});
						}
					});
				}
			});
        };
	</script>
</html>