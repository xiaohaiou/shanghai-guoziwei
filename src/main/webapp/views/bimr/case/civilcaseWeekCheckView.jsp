<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>民事案件上报</title>
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
			<span class="glyphicon glyphicon-pencil"></span>民事案件审核
			<i class="iconfont icon-guanbi"></i>
		</h4>
		<form:form id="form1" modelAttribute="entity" method="post">
			<form:hidden path="id" value="${entity.id}"/>
			<input type="hidden" id="entityid" value="${entity.id}"/>
			<div class="label_inpt_div">
				<div class="model_part">
					<label class="w20">案件编号：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.caseNum ne null}">
							<c:if test="${entity_old.caseNum ne entity.caseNum}"><font color="red">${entity_old.caseNum}</font><br></c:if>
						</c:if>
						${entity.caseNum}
					</label>
					
					<label class="w20">案件归属单位：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.vcCompanyName ne null}">
							<c:if test="${entity_old.vcCompanyName ne entity.vcCompanyName}"><font color="red">${entity_old.vcCompanyName}</font><br></c:if>
						</c:if>
						${entity.vcCompanyName}
					</label>
						
					<label class="w20">原告/申请人/第三方：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.plaintiff ne null}">
							<c:if test="${entity_old.plaintiff ne entity.plaintiff}"><font color="red">${entity_old.plaintiff}</font><br></c:if>
						</c:if>
						${entity.plaintiff}
					</label>
						
					<label class="w20">被告/被申请人/第三人：</label>
					<label class="w25 setleft">
						<c:if test="${!empty entity_old.defendant  }">
							<c:if test="${entity_old.defendant ne entity.defendant}"><font color="red">${entity_old.defendant}</font><br></c:if>
						</c:if>
						${entity.defendant}
					</label>
					
					<label class="w20">我方诉讼地位(主诉/被诉)：</label>
					<label class="w25 setleft">
						<c:if test="${ !empty entity_old.litigation  }">
							<c:if test="${entity_old.litigation ne entity.litigation}"><font color="red">
								<c:if test="${entity_old.litigation eq '1'}">主诉</c:if>
								<c:if test="${entity_old.litigation eq '2'}">被诉</c:if>
								<c:if test="${entity_old.litigation eq '3'}">申请人</c:if>
								<c:if test="${entity_old.litigation eq '4'}">被申请人</c:if>
							</font><br></c:if>
						</c:if>
						<c:if test="${entity.litigation eq '1'}">主诉</c:if>
						<c:if test="${entity.litigation eq '2'}">被诉</c:if>
						<c:if test="${entity.litigation eq '3'}">申请人</c:if>
						<c:if test="${entity.litigation eq '4'}">被申请人</c:if>
					</label>
					
					<label class="w20">受理法院/仲裁机构：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.admissibleCourt ne null}">
							<c:if test="${entity_old.admissibleCourt ne entity.admissibleCourt}"><font color="red">${entity_old.admissibleCourt}</font><br></c:if>
						</c:if>
						${entity.admissibleCourt}
					</label>
					
					<label class="w20">受案单位所在地(填写具体省份)：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.province ne null}">
							<c:if test="${entity_old.province ne entity.province}"><font color="red">${entity_old.province}</font><br></c:if>
						</c:if>
						${entity.province}
					</label>
					
					<label class="w20">承办法官/仲裁员：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.judge ne null}">
							<c:if test="${entity_old.judge ne entity.judge}"><font color="red">${entity_old.judge}</font><br></c:if>
						</c:if>
						${entity.judge}
					</label>
					
					<label class="w20">涉案金额（万元）：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.amount ne null}">
							<c:if test="${entity_old.amount ne entity.amount}"><font color="red">${entity_old.amount}</font><br></c:if>
						</c:if>
						${entity.amount}
					</label>
					
					<label class="w20">案件类型：</label>
					<label class="w25 setleft">
						<c:if test="${entity.type eq '1'}">合同纠纷</c:if>
						<c:if test="${entity.type eq '2'}">劳动纠纷</c:if>
						<c:if test="${entity.type eq '3'}">侵权纠纷</c:if>
						<c:if test="${entity.type eq '4'}">行政纠纷</c:if>
						<c:if test="${entity.type eq '5'}">其他纠纷</c:if>
					</label>
					<label class="w20">案由：</label>
					<c:choose>
						<c:when test="${entity.type eq '1'}">
						
							<c:forEach items="${reasonlist}" var="r">
								<c:if test="${r.num == entity.reason }"><label class="w25 setleft">${r.description}</label></c:if>
							</c:forEach>
						</c:when>
						
						<c:otherwise>
						<label class="w25 setleft">
						<c:if test="${entity_old.reason ne null}">
							<c:if test="${entity_old.reason ne entity.reason}"><font color="red">${entity_old.reason}</font><br></c:if>
						</c:if>
						${entity.reason}
						</label>
						</c:otherwise>
					</c:choose>
					
					<br>
					
					
					<label class="w20">基本案情：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.baseMessage ne null}">
							<c:if test="${entity_old.baseMessage ne entity.baseMessage}"><font color="red">${entity_old.baseMessage}</font><br></c:if>
						</c:if>
						${entity.baseMessage}
					</label>
					
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
						<c:if test="${entity_old.mediatingStage ne null}">
							<c:if test="${entity_old.mediatingStage ne entity.mediatingStage}"><font color="red">							
								<c:if test="${entity_old.mediatingStage eq '1'}">一审</c:if>
								<c:if test="${entity_old.mediatingStage eq '2'}">二审</c:if>
								<c:if test="${entity_old.mediatingStage eq '3'}">再审</c:if>
								<c:if test="${entity_old.mediatingStage eq '4'}">执行</c:if>
								<c:if test="${entity_old.mediatingStage eq '5'}">结案</c:if>
							</font><br></c:if>
						</c:if>
						<c:if test="${entity.mediatingStage eq '1'}">一审</c:if>
						<c:if test="${entity.mediatingStage eq '2'}">二审</c:if>
						<c:if test="${entity.mediatingStage eq '3'}">再审</c:if>
						<c:if test="${entity.mediatingStage eq '4'}">执行</c:if>
						<c:if test="${entity.mediatingStage eq '5'}">结案</c:if>
					</label>
					
					<label class="w20">最新进展：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.lastProgress ne null}">
							<c:if test="${entity_old.lastProgress ne entity.lastProgress}"><font color="red">${entity_old.lastProgress}</font><br></c:if>
						</c:if>
						${entity.lastProgress}
					</label>
					
					<label class="w20">合作开始时间：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.cooperationDate ne null}">
							<c:if test="${entity_old.cooperationDate ne entity.cooperationDate}"><font color="red">${entity_old.cooperationDate}</font><br></c:if>
						</c:if>
						${entity.cooperationDate}
					</label>
					
					<label class="w20">纠纷发生时间：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.disputeDate ne null}">
							<c:if test="${entity_old.disputeDate ne entity.disputeDate}"><font color="red">${entity_old.disputeDate}</font><br></c:if>
						</c:if>
						${entity.disputeDate}
					</label>
					
					<label class="w20">业务部门解决纠纷的过程：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.dealDisputeProcess ne null}">
							<c:if test="${entity_old.dealDisputeProcess ne entity.dealDisputeProcess}"><font color="red">${entity_old.dealDisputeProcess}</font><br></c:if>
						</c:if>
						${entity.dealDisputeProcess}
					</label>
					
					<label class="w20">成诉原因：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.caseCause ne null}">
							<c:if test="${entity_old.caseCause ne entity.caseCause}"><font color="red">${entity_old.caseCause}</font><br></c:if>
						</c:if>
						${entity.caseCause}
					</label>
					
					<label class="w20">案发时间（示例：XX年XX月XX日）：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.caseDate ne null}">
							<c:if test="${entity_old.caseDate ne entity.caseDate}"><font color="red">${entity_old.caseDate}</font><br></c:if>
						</c:if>
						${entity.caseDate}
					</label>
					
					<label class="w20">案龄(年)：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.caseAge ne null}">
							<c:if test="${entity_old.caseAge ne entity.caseAge}"><font color="red">${entity_old.caseAge}</font><br></c:if>
						</c:if>
						${entity.caseAge}
					</label>
					
					<label class="w20">承办法务：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.lawWork ne null}">
							<c:if test="${entity_old.lawWork ne entity.lawWork}"><font color="red">${entity_old.lawWork}</font><br></c:if>
						</c:if>
						${entity.lawWork}
					</label>
					
					<label class="w20">案件调处责任人：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.responsiblePerson ne null}">
							<c:if test="${entity_old.responsiblePerson ne entity.responsiblePerson}"><font color="red">${entity_old.responsiblePerson}</font><br></c:if>
						</c:if>
						${entity.responsiblePerson}
					</label>
					
					<label class="w20">外聘律所及律师：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.externalLaws ne null}">
							<c:if test="${entity_old.externalLaws ne entity.externalLaws}"><font color="red">${entity_old.externalLaws}</font><br></c:if>
						</c:if>
						${entity.externalLaws}
					</label>
					
					<label class="w20">律师费总额（人民币万元）：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.lawsAmount ne null}">
							<c:if test="${entity_old.lawsAmount ne entity.lawsAmount}"><font color="red">${entity_old.lawsAmount}</font><br></c:if>
						</c:if>
						${entity.lawsAmount}
					</label>
					
					<label class="w20">已支付金额（人民币万元）：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.payAmount ne null}">
							<c:if test="${entity_old.payAmount ne entity.payAmount}"><font color="red">${entity_old.payAmount}</font><br></c:if>
						</c:if>
						${entity.payAmount}
					</label>
					
					<label class="w20">对方外聘律所及律师：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.foreignLaw ne null}">
							<c:if test="${entity_old.foreignLaw ne entity.foreignLaw}"><font color="red">${entity_old.foreignLaw}</font><br></c:if>
						</c:if>
						${entity.foreignLaw}
					</label>
					
					<label class="w20">调处时间计划表（说明一审、二审的调处时间计划）：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.planTime ne null}">
							<c:if test="${entity_old.planTime ne entity.planTime}"><font color="red">${entity_old.planTime}</font><br></c:if>
						</c:if>
						${entity.planTime}
					</label>
					
					<label class="w20">预测结果：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.predictionResluts ne null}">
							<c:if test="${entity_old.predictionResluts ne entity.predictionResluts}"><font color="red">${entity_old.predictionResluts}</font><br></c:if>
						</c:if>
						${entity.predictionResluts}
					</label>
					
					<label class="w20">预计结果：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.expectedResults ne null}">
							<c:if test="${entity_old.expectedResults ne entity.expectedResults}"><font color="red">${entity_old.expectedResults}</font><br></c:if>
						</c:if>
						${entity.expectedResults}
					</label>
					
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
					<label class="w25 setleft">
						<c:if test="${entity_old.analysisCause ne null}">
							<c:if test="${entity_old.analysisCause ne entity.analysisCause}"><font color="red">${entity_old.analysisCause}</font><br></c:if>
						</c:if>
						${entity.analysisCause}
					</label>
					
					<label class="w20">拟问责建议（若无需问责，请说明具体原因）（实业）：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.accountabilitySuggest ne null}">
							<c:if test="${entity_old.accountabilitySuggest ne entity.accountabilitySuggest}"><font color="red">${entity_old.accountabilitySuggest}</font><br></c:if>
						</c:if>
						${entity.accountabilitySuggest}
					</label>
					
					
					<label class="w20">问责进展情况（含问责人数及名单）（实业）：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.accountabilityResults ne null}">
							<c:if test="${entity_old.accountabilityResults ne entity.accountabilityResults}"><font color="red">${entity_old.accountabilityResults}</font><br></c:if>
						</c:if>
						${entity.accountabilityResults}
					</label>
					
					<label class="w20">协办人(实业)：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.assistingPeople ne null}">
							<c:if test="${entity_old.assistingPeople ne entity.assistingPeople}"><font color="red">${entity_old.assistingPeople}</font><br></c:if>
						</c:if>
						${entity.assistingPeople}
					</label>
					
					<label class="w20">双方保全情况(实业)：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.bothPreserveSituation ne null}">
							<c:if test="${entity_old.bothPreserveSituation ne entity.bothPreserveSituation}"><font color="red">${entity_old.bothPreserveSituation}</font><br></c:if>
						</c:if>
						${entity.bothPreserveSituation}
					</label>
					
					<label class="w20">一案双处实施情况 （实业）：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.twoCasesImplementation ne null}">
							<c:if test="${entity_old.twoCasesImplementation ne entity.twoCasesImplementation}"><font color="red">${entity_old.twoCasesImplementation}</font><br></c:if>
						</c:if>
						${entity.twoCasesImplementation}
					</label>
					
					
					<label class="w20">办案思路、调处重点及工作计划(实业)：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.caseThoughtFocalPoint ne null}">
							<c:if test="${entity_old.caseThoughtFocalPoint ne entity.caseThoughtFocalPoint}"><font color="red">${entity_old.caseThoughtFocalPoint}</font><br></c:if>
						</c:if>
						${entity.caseThoughtFocalPoint}
					</label>
					
					<label class="w20">判决结果：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.verdictResult ne null}">
							<c:if test="${entity_old.verdictResult ne entity.verdictResult}"><font color="red">${entity_old.verdictResult}</font><br></c:if>
						</c:if>
						${entity.verdictResult}
					</label>
					
					<label class="w20">判决/调解金额（人民币万元）：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.verdictAmount ne null}">
							<c:if test="${entity_old.verdictAmount ne entity.verdictAmount}"><font color="red">${entity_old.verdictAmount}</font><br></c:if>
						</c:if>
						${entity.verdictAmount}
					</label>
					
					<label class="w20">已执行款项（人民币万元）：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.implementMoney ne null}">
							<c:if test="${entity_old.implementMoney ne entity.implementMoney}"><font color="red">${entity_old.implementMoney}</font><br></c:if>
						</c:if>
						${entity.implementMoney}
					</label>
					
					<label class="w20">避免/挽回经济损失（人民币万元）：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.saveLoss ne null}">
							<c:if test="${entity_old.saveLoss ne entity.saveLoss}"><font color="red">${entity_old.saveLoss}</font><br></c:if>
						</c:if>
						${entity.saveLoss}
					</label>
					<label class="w20">案件移交公文编号（实业）：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.transferredNumber ne null}">
							<c:if test="${entity_old.transferredNumber ne entity.transferredNumber}"><font color="red">${entity_old.transferredNumber}</font><br></c:if>
						</c:if>
						${entity.transferredNumber}
					</label>
					<label class="w20">案件结案公文编号（实业）：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.closingNumber ne null}">
							<c:if test="${entity_old.closingNumber ne entity.closingNumber}"><font color="red">${entity_old.closingNumber}</font><br></c:if>
						</c:if>
						${entity.closingNumber}
					</label>
					<label class="w20">裁判/调解/和解主文（实业）：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.reconciliationDocument ne null}">
							<c:if test="${entity_old.reconciliationDocument ne entity.reconciliationDocument}"><font color="red">${entity_old.reconciliationDocument}</font><br></c:if>
						</c:if>
						${entity.reconciliationDocument}
					</label>
					<label class="w20">规划结案时间：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.casePlanDate ne null}">
							<c:if test="${entity_old.casePlanDate ne entity.casePlanDate}"><font color="red">${entity_old.casePlanDate}</font><br></c:if>
						</c:if>
						${entity.casePlanDate}
					</label>
					<br>
					<label class="w20">备注(实业)：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.remark1 ne null}">
							<c:if test="${entity_old.remark1 ne entity.remark1}"><font color="red">${entity_old.remark1}</font><br></c:if>
						</c:if>
						${entity.remark1}
					</label>
					
					<br>
				<label class="w20">起诉状:</label>
				<label class="w70 setleft"> 
					<c:if test="${not empty entity.indictment}">
					<c:if test="${not empty indictmentFile}">
						<c:forEach items="${indictmentFile}" var="indictmentFile" varStatus="index">
						<span id="${index.index+1}" >${index.index+1}.	<a href="javascript:void(0)" onclick="downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(indictmentFile.filePath,"\\","\\\\")}&_name=${indictmentFile.fileName}')" target="_blank">${indictmentFile.fileName}</a></span> 
						</c:forEach>					
					</c:if>
					</c:if></label>
				<br>
				<label class="w20">判决书/调解书:</label>
				<label class="w70 setleft"> 
					<c:if test="${not empty entity.judgment}">
					<c:if test="${not empty judgmentFile}">
						<c:forEach items="${judgmentFile}" var="judgmentFile" varStatus="index">
						<span id="${index.index+1}" >${index.index+1}.	<a href="javascript:void(0)" onclick="downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(judgmentFile.filePath,"\\","\\\\")}&_name=${judgmentFile.fileName}')" target="_blank">${judgmentFile.fileName}</a> </span>
						</c:forEach>
					</c:if>
				</c:if></label>
				<br>
				<label class="w20"> 其它:</label>
				<label class="w70 setleft">
					<c:if test="${not empty entity.otherFile}">
					<c:if test="${not empty oFile}">
						<c:forEach items="${oFile}" var="oFile" varStatus="index">
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
				
				<div class="model_part">
					<label class="w20" style="vertical-align: top;"><span class="required"> * </span>审核意见:</label>
					<textarea  rows="3" cols="50" name="examStr" id="examStr" check="NotEmpty_string_50_._._._."></textarea>
				</div>
				<div class="model_btn">
					<button class="btn btn-primary model_btn_ok" id="commit-1">通过</button>
					<button class="btn btn-primary model_btn_ok" id="commit-2">退回</button>
					<button class="btn btn-default model model_btn_close">关闭</button>
				</div>
			</div>
		</form:form>
	</body>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>
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
		//审核通过
		$("#commit-1").click(function(){
			if(!vaild.all())
			{
				return false;
			}
		 	var url = "${pageContext.request.contextPath}/bimr/case/commitexam";
		    $.ajax({  
			     url : url,  
			     type : "POST",  
			     data:{"entityid" : $("#entityid").val(), "examStr" : $("#examStr").val(), "examResult" : 50116},
		         async: false,  
		         cache: false,  
		         dataType:"json",  
			     success : function(data) {
				    if(data == "success"){
						win.successAlert('保存成功！',function(){
							window.parent.location.reload();
							window.parent.mclose();
							
						});
					}
			     },   
		         error: function(XMLHttpRequest, textStatus, errorThrown) {
					 alert(XMLHttpRequest.status);
					 alert(XMLHttpRequest.readyState);
					 alert(textStatus);
				 },
			}); 
			return false;
		})
		//审核不通过
		$("#commit-2").click(function(){
			if(!vaild.all())
			{
				return false;
			}
			var url = "${pageContext.request.contextPath}/bimr/case/commitexam";
		    $.ajax({  
			     url : url,  
			     type : "POST",  
			     data:{"entityid" : $("#entityid").val(), "examStr" : $("#examStr").val(), "examResult" : 50117},
		         async: false,  
		         cache: false,  
		         dataType:"json",  
			     success : function(data) {
				    if(data == "success"){
						win.successAlert('保存成功！',function(){
							window.parent.location.reload();
							window.parent.mclose();
							
						});
					}
			     },  
		         error: function(XMLHttpRequest, textStatus, errorThrown) {
					 alert(XMLHttpRequest.status);
					 alert(XMLHttpRequest.readyState);
					 alert(textStatus);
				 },  
			}); 
			return false;
		})
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