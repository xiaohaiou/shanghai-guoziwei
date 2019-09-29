<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>民事案件详情</title>
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
			<span class="glyphicon glyphicon-pencil"></span>民事案件详情
			<i class="iconfont icon-guanbi"></i>
		</h4>
		<form:form id="form1" modelAttribute="entity" method="post">
			<form:hidden path="id" value="${entity.id}"/>
			<div class="label_inpt_div">
				<div class="model_part">
					<label class="w20">案件编号：</label>
					<label class="w25 setleft">${entity.caseNum}</label>
					
					<label class="w20">承办单位：</label>
					<label class="w25 setleft">${entity.vcCompanyName}</label>
						
					<label class="w20">原告/申请人/第三方：</label>
					<label class="w25 setleft">${entity.plaintiff}</label>
						
					<label class="w20">被告/被申请人/第三人：</label>
					<label class="w25 setleft">${entity.defendant}</label>
					
					<label class="w20">我方诉讼地位（主诉/被诉）：</label>
					<label class="w25 setleft">
						<c:if test="${entity.litigation eq '1'}">主诉</c:if>
						<c:if test="${entity.litigation eq '2'}">被诉</c:if>
						<c:if test="${entity.litigation eq '3'}">申请人</c:if>
						<c:if test="${entity.litigation eq '4'}">被申请人</c:if>
					</label>
					
					<label class="w20">受理法院/仲裁机构：</label>
					<label class="w25 setleft">${entity.admissibleCourt}</label>
					
					<label class="w20">受案单位所在地(填写具体省份)：</label>
					<label class="w25 setleft">${entity.province}</label>
					
					<label class="w20">承办法官/仲裁员：</label>
					<label class="w25 setleft">${entity.judge}</label>
					
					<label class="w20">涉案金额（万元）：</label>
					<label class="w25 setleft">${entity.amount}</label>
					
					<label class="w20">案件类型：</label>
					<label class="w25 setleft">
						<c:if test="${entity.type eq '1'}">合同纠纷</c:if>
						<c:if test="${entity.type eq '2'}">劳动纠纷</c:if>
						<c:if test="${entity.type eq '3'}">侵权纠纷</c:if>
						<c:if test="${entity.type eq '4'}">行政纠纷</c:if>
						<c:if test="${entity.type eq '5'}">其他纠纷</c:if>
					</label>
					
					
					<c:choose>
						<c:when test="${entity.type eq '1'}">
						<label class="w20">案由：</label>
							<c:forEach items="${reasonlist}" var="r">
								<c:if test="${r.num == entity.reason }"><label class="w25 setleft">${r.description}</label></c:if>
							</c:forEach>
						</c:when>
						
						<c:otherwise>
						<label class="w20">案由：</label>
							<label class="w25 setleft">${entity.reason}</label>
						</c:otherwise>
					</c:choose>
					
					<br>
					
					
					<label class="w20">基本案情：</label>
					<label class="w25 setleft">${entity.baseMessage}</label>
					
					<label class="w20">是否为重大案件(根据集团重大案件标准)：</label>
					<label class="w25 setleft">
						<c:if test="${entity.isMajorCase eq '0'}">否</c:if>
						<c:if test="${entity.isMajorCase eq '1'}">是</c:if>
					</label>
					
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
					
					<label class="w20">调处阶段：</label>
					<label class="w25 setleft">
						<c:if test="${entity.mediatingStage eq '1'}">一审</c:if>
						<c:if test="${entity.mediatingStage eq '2'}">二审</c:if>
						<c:if test="${entity.mediatingStage eq '3'}">再审</c:if>
						<c:if test="${entity.mediatingStage eq '4'}">执行</c:if>
						<c:if test="${entity.mediatingStage eq '5'}">结案</c:if>
					</label>
					
					<label class="w20">最新进展：</label>
					<label class="w25 setleft">${entity.lastProgress}</label>
					
					<label class="w20">合作开始时间（示例：XX年XX月XX日）：</label>
					<label class="w25 setleft">${entity.cooperationDate}</label>
					
					<label class="w20">纠纷发生时间（示例：XX年XX月XX日）：</label>
					<label class="w25 setleft">${entity.disputeDate}</label>
					
					<label class="w20">业务部门解决纠纷的过程：</label>
					<label class="w25 setleft">${entity.dealDisputeProcess}</label>
					
					<label class="w20">成诉原因：</label>
					<label class="w25 setleft">${entity.caseCause}</label>
					
					<label class="w20">案发时间（示例：XX年XX月XX日）：</label>
					<label class="w25 setleft">${entity.caseDate}</label>
					
					<label class="w20">案龄(年)：</label>
					<label class="w25 setleft">${entity.caseAge}</label>
					
					<label class="w20">承办法务：</label>
					<label class="w25 setleft">${entity.lawWork}</label>
					
					<label class="w20">案件调处责任人：</label>
					<label class="w25 setleft">${entity.responsiblePerson}</label>
					
					<label class="w20">外聘律所及律师：</label>
					<label class="w25 setleft">${entity.externalLaws}</label>
					
					<label class="w20">律师费总额（人民币万元）：</label>
					<label class="w25 setleft">${entity.lawsAmount}</label>
					
					<label class="w20">已支付金额（人民币万元）：</label>
					<label class="w25 setleft">${entity.payAmount}</label>
					
					<label class="w20">对方外聘律所及律师：</label>
					<label class="w25 setleft">${entity.foreignLaw}</label>
					
					<label class="w20">调处时间计划表（说明一审、二审的调处时间计划）：</label>
					<label class="w25 setleft">${entity.planTime}</label>
					
					<label class="w20">预测结果：</label>
					<label class="w25 setleft">${entity.predictionResluts}</label>
					
					<label class="w20">预计结果：</label>
					<label class="w25 setleft">${entity.expectedResults}</label>
					
					<label class="w20">是否问责：</label>
					<label class="w25 setleft">
						<c:if test="${entity.isAccountability eq '0'}">否</c:if>
						<c:if test="${entity.isAccountability eq '1'}">是</c:if>
					</label>
					
					<label class="w20">是否进行/完成成因分析：</label>
					<label class="w25 setleft">
						<c:if test="${entity.isAnalysis eq '0'}">否</c:if>
						<c:if test="${entity.isAnalysis eq '1'}">是</c:if>
					</label>
					
					<label class="w20">案件成因(实业)：</label>
					<label class="w25 setleft">${entity.analysisCause}</label>
					
					<label class="w20">拟问责建议（若无需问责，请说明具体原因）（实业）：</label>
					<label class="w25 setleft">${entity.accountabilitySuggest}</label>
					
					<label class="w20">问责进展情况（含问责人数及名单）（实业）：</label>
					<label class="w25 setleft">${entity.accountabilityResults}</label>
					
					<label class="w20">协办人(实业)：</label>
					<label class="w25 setleft">${entity.assistingPeople}</label>
					
					<label class="w20">双方保全情况(实业)：</label>
					<label class="w25 setleft">${entity.bothPreserveSituation}</label>
					
					<label class="w20">一案双处实施情况 （实业）：</label>
					<label class="w25 setleft">${entity.twoCasesImplementation}</label>
					
					<label class="w20">办案思路、调处重点及工作计划(实业)：</label>
					<label class="w25 setleft">${entity.caseThoughtFocalPoint}</label>
					
					<label class="w20">判决结果：</label>
					<label class="w25 setleft">${entity.verdictResult}</label>
					
					<label class="w20">判决/调解金额（人民币万元）：</label>
					<label class="w25 setleft">${entity.verdictAmount}</label>
					
					<label class="w20">已执行款项（人民币万元）：</label>
					<label class="w25 setleft">${entity.implementMoney}</label>
					
					<label class="w20">避免/挽回经济损失（人民币万元）：</label>
					<label class="w25 setleft">${entity.saveLoss}</label>
					
					<label class="w20">案件移交公文编号（实业）：</label>
					<label class="w25 setleft">${entity.transferredNumber}</label>
					
					<label class="w20">案件结案公文编号（实业）：</label>
					<label class="w25 setleft">${entity.closingNumber}</label>
					
					<label class="w20">裁判/调解/和解主文（实业）：</label>
					<label class="w25 setleft">${entity.reconciliationDocument}</label>
					
					<label class="w20">规划结案时间：</label>
					<label class="w25 setleft">${entity.casePlanDate}</label>
					
					<label class="w20">备注(实业)：</label>
					<label class="w25 setleft">${entity.remark1}</label>
					
					<br>
					<label class="w20">起诉状:</label>
				
					<label class="w70 setleft"> 
					<c:if test="${not empty entity.indictment}">
					<c:if test="${not empty indictmentFile}">
						<c:forEach items="${indictmentFile}" var="indictmentFile" varStatus="index">
						<span id="${index.index+1}" >${index.index+1}.	<a href="javascript:void(0)" onclick="downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(indictmentFile.filePath,"\\","\\\\")}&_name=${indictmentFile.fileName}')" target="_blank">${indictmentFile.fileName}</a> </span>
						</c:forEach>
					</c:if>
				</c:if></label>
				<br>
				<label class="w20">判决书/调解书:</label>
				<label class="w70 setleft"> 
					<c:if test="${not empty entity.judgment}">
					<c:if test="${not empty judgmentFile}">
<%-- 						<a href="javascript:void(0)" onclick="downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(judgmentFile.filePath,"\\","\\\\")}&_name=${judgmentFile.fileName}')" target="_blank">${judgmentFile.fileName}</a>
 --%>					<c:forEach items="${judgmentFile}" var="judgmentFile" varStatus="index">
						<span id="${index.index+1}" >${index.index+1}.	<a href="javascript:void(0)" onclick="downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(judgmentFile.filePath,"\\","\\\\")}&_name=${judgmentFile.fileName}')" target="_blank">${judgmentFile.fileName}</a> </span>
						</c:forEach>
					</c:if>
				</c:if></label>
				<br>
				<label class="w20"> 其它:</label>
				<label class="w70 setleft">
					<c:if test="${not empty entity.otherFile}">
					<c:if test="${not empty oFile}">
<%-- 						<a href="javascript:void(0)" onclick="downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(oFile.filePath,"\\","\\\\")}&_name=${oFile.fileName}')" target="_blank">${oFile.fileName}</a>
 --%>					<c:forEach items="${oFile}" var="oFile" varStatus="index">
						<span id="${index.index+1}" >${index.index+1}.	<a href="javascript:void(0)" onclick="downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(oFile.filePath,"\\","\\\\")}&_name=${oFile.fileName}')" target="_blank">${oFile.fileName}</a> </span>
						</c:forEach>
					</c:if>
				</c:if></label>
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
	</script>
</html>