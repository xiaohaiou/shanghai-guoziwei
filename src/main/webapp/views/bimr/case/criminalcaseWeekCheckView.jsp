<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>刑事案件审核</title>
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
			<span class="glyphicon glyphicon-pencil"></span>刑事案件审核
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
						
					<label class="w20">犯罪嫌疑人：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.suspect ne null}">
							<c:if test="${entity_old.suspect ne entity.suspect}"><font color="red">${entity_old.suspect}</font><br></c:if>
						</c:if>
						${entity.suspect}
					</label>
					
					<label class="w20">所在单位：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.belongCompany ne null}">
							<c:if test="${entity_old.belongCompany ne entity.belongCompany}"><font color="red">${entity_old.belongCompany}</font><br></c:if>
						</c:if>
						${entity.belongCompany}
					</label>
						
					<label class="w20">职务：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.post ne null}">
							<c:if test="${entity_old.post ne entity.post}"><font color="red">${entity_old.post}</font><br></c:if>
						</c:if>
						${entity.post}
					</label>
					
					<label class="w20">涉嫌罪名：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.accusation ne null}">
							<c:if test="${entity_old.accusation ne entity.accusation}"><font color="red">${entity_old.accusation}</font><br></c:if>
						</c:if>
						${entity.accusation}
					</label>
					
					<label class="w20">受案单位所在地(省份)：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.province ne null}">
							<c:if test="${entity_old.province ne entity.province}"><font color="red">${entity_old.province}</font><br></c:if>
						</c:if>
						${entity.province}
					</label>
					
					<label class="w20">基本案情：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.baseMessage ne null}">
							<c:if test="${entity_old.baseMessage ne entity.baseMessage}"><font color="red">${entity_old.baseMessage}</font><br></c:if>
						</c:if>
						${entity.baseMessage}
					</label>
					
					<label class="w20">涉案金额：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.amount ne null}">
							<c:if test="${entity_old.amount ne entity.amount}"><font color="red">${entity_old.amount}</font><br></c:if>
						</c:if>
						${entity.amount}
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
					
					<label class="w20">案件类型：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.type ne null}">
							<c:if test="${entity_old.type ne entity.type}"><font color="red"><c:if test="${entity_old.type eq '1'}">重大刑事案件</c:if>
						<c:if test="${entity_old.type eq '2'}">一般刑事案件</c:if></font><br></c:if>
						</c:if>
						<c:if test="${entity.type eq '1'}">重大刑事案件</c:if>
						<c:if test="${entity.type eq '2'}">一般刑事案件</c:if>
					</label>
					
					<label class="w20">最新进展：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.province ne null}">
							<c:if test="${entity_old.province ne entity.province}"><font color="red">${entity_old.province}</font><br></c:if>
						</c:if>
					${entity.latestProgress}</label>
						
					<label class="w20">办案单位：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.caseHandlingUnit ne null}">
							<c:if test="${entity_old.caseHandlingUnit ne entity.caseHandlingUnit}"><font color="red">${entity_old.caseHandlingUnit}</font><br></c:if>
						</c:if>
						${entity.caseHandlingUnit}
					</label>
						
					<label class="w20">办案人：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.caseHandlingPerson ne null}">
							<c:if test="${entity_old.caseHandlingPerson ne entity.caseHandlingPerson}"><font color="red">${entity_old.caseHandlingPerson}</font><br></c:if>
						</c:if>
						${entity.caseHandlingPerson}
					</label>
						
					<label class="w20">集团内部承办单位：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.groupCompany ne null}">
							<c:if test="${entity_old.groupCompany ne entity.groupCompany}"><font color="red">${entity_old.groupCompany}</font><br></c:if>
						</c:if>
						${entity.groupCompany}
					</label>
					
					<label class="w20">案发日期：</label>
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
						
					<label class="w20">法务承办人：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.lawWork ne null}">
							<c:if test="${entity_old.lawWork ne entity.lawWork}"><font color="red">${entity_old.lawWork}</font><br></c:if>
						</c:if>
						${entity.lawWork}
					</label>
						
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
				<label class="w20">案件成因：</label>
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
					<label class="w20">案件移交公文编号：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.transferredNumber ne null}">
							<c:if test="${entity_old.transferredNumber ne entity.transferredNumber}"><font color="red">${entity_old.transferredNumber}</font><br></c:if>
						</c:if>
						${entity.transferredNumber}
					</label>
					<label class="w20">结案时间及结案报告公文号（实业）：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.closingNumber ne null}">
							<c:if test="${entity_old.closingNumber ne entity.closingNumber}"><font color="red">${entity_old.closingNumber}</font><br></c:if>
						</c:if>
						${entity.closingNumber}
					</label>
					<label class="w20">规划结案时间：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.casePlanDate ne null}">
							<c:if test="${entity_old.casePlanDate ne entity.casePlanDate}"><font color="red">${entity_old.casePlanDate}</font><br></c:if>
						</c:if>
						${entity.casePlanDate}
					</label>
				<label class="w20">备注（实业）：</label>
					<label class="w25 setleft">
						<c:if test="${entity_old.remark1 ne null}">
							<c:if test="${entity_old.remark1 ne entity.remark1}"><font color="red">${entity_old.remark1}</font><br></c:if>
						</c:if>
						${entity.remark1}
					</label>
					
					<br>
					<label class="w20">报案书:</label>
				
					<label class="w70 setleft"> 
					<c:if test="${not empty entity.reportment}">
					<c:if test="${not empty reportmentFile}">
						<%-- <a href="javascript:void(0)" onclick="downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(reportmentFile.filePath,"\\","\\\\")}&_name=${reportmentFile.fileName}')" target="_blank">${reportmentFile.fileName}</a> --%>
						<c:forEach items="${reportmentFile}" var="reportmentFile" varStatus="index">
						<span id="${index.index+1}" >${index.index+1}.	<a href="javascript:void(0)" onclick="downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(reportmentFile.filePath,"\\","\\\\")}&_name=${reportmentFile.fileName}')" target="_blank">${reportmentFile.fileName}</a> </span>
						</c:forEach>
					</c:if>
				</c:if></label>
				<br>
				<label class="w20">判决书:</label>
				<label class="w70 setleft"> 
					<c:if test="${not empty entity.judgment}">
					<c:if test="${not empty judgmentFile}">
						<%-- <a href="javascript:void(0)" onclick="downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(judgmentFile.filePath,"\\","\\\\")}&_name=${judgmentFile.fileName}')" target="_blank">${judgmentFile.fileName}</a> --%>
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
						<%-- <a href="javascript:void(0)" onclick="downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(oFile.filePath,"\\","\\\\")}&_name=${oFile.fileName}')" target="_blank">${oFile.fileName}</a> --%>
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
		 	var url = "${pageContext.request.contextPath}/bimr/case/commitexamCriminalcase";
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
			var url = "${pageContext.request.contextPath}/bimr/case/commitexamCriminalcase";
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