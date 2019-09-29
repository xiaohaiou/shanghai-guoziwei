<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>审计项目相关信息审核页面</title>
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
	<style type="text/css">
		.w75 {
			word-wrap: break-word;
		}
		.w25 {
			word-wrap: break-word;
		}
	</style>
</head>
<body>
	<h4>
		<span class="glyphicon glyphicon-pencil"></span>审计项目相关信息审核
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="auditProject" method="post">
		<form:hidden path="id"/>
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20">审计单位:</label>
				<label class="w25 setleft">${ auditProject.auditDeptName}</label>
				<label class="w20">审计项目名称:</label>
				<label class="w25 setleft">${ auditProject.auditProjectName}</label><br>
				<label class="w20">审计类型一:</label>
				<label class="w25 setleft">${ auditProject.auditTypeName}</label>
				<label class="w20">审计类型二:</label>
				<label class="w25 setleft">${ auditProject.auditTypeName2}</label><br>
				<label class="w20">审计开始日期:</label>
				<label class="w25 setleft">${ auditProject.auditStartDate}</label>
				<label class="w20">审计结束日期:</label>
				<label class="w25 setleft">${ auditProject.auditEndDate}</label><br>
				<label class="w20">备注:</label>
				<label class="w70 setleft" style="word-wrap:break-word;">${ auditProject.remark}</label><br>
				<label class="w20">涉案金额（元）:</label>
				<label class="w25 setleft">${ auditProject.caseTotalAmount}</label>
				<label class="w20">挽回损失（元）:</label>
				<label class="w25 setleft">${ auditProject.saveLoss}</label><br>
				<label class="w20">审计处理和责任追究（人员和处分）:</label>
				<label class="w70 setleft" style="word-wrap:break-word;">${ auditProject.auditManage}</label><br>
				<label class="w20">审计是否发现问题:</label>
				<label class="w25 setleft"><c:if test="${auditProject.isQuestion eq 1}">是</c:if><c:if test="${auditProject.isQuestion eq 0}">否</c:if></label>
				<label class="w20">审计发现问题是否整改:</label>
				<label class="w25 setleft"><c:if test="${auditProject.isAbarbeitung eq 1}">是</c:if><c:if test="${auditProject.isAbarbeitung eq 0}">否</c:if></label>
			</div>
			<div id="sjfxwtslwh">
				<h3 class="data_title1">审计发现问题数量维护(合计${findQuestionNum}个)</h3>
				<div class="model_part">
					<label class="w20" style="text-align:right;">公司战略制定和执行方面问题数:</label>
					<label class="w25 setleft">${ auditProject.auditProjectFindQuestion.corporateStrategyMakeAndExecute}个</label>
					<label class="w20" style="text-align:right;">财务管控(资金资产安全等)方面问题数:</label>
					<label class="w25 setleft">${ auditProject.auditProjectFindQuestion.financialControl}个</label>
					<label class="w20" style="text-align:right;">人力资源管理方面问题数:</label>
					<label class="w25 setleft">${ auditProject.auditProjectFindQuestion.humanResourceManagement}个</label>
					<label class="w20" style="text-align:right;">采购管理方面问题数:</label>
					<label class="w25 setleft">${ auditProject.auditProjectFindQuestion.purchaseManagement}个</label>
					<label class="w20" style="text-align:right;">基础建设方面问题数:</label>
					<label class="w25 setleft">${ auditProject.auditProjectFindQuestion.infrastructure}个</label>
					<label class="w20" style="text-align:right;">项目投资(重组并购)方面问题数:</label>
					<label class="w25 setleft">${ auditProject.auditProjectFindQuestion.projectInvestment}个</label>
					<label class="w20" style="text-align:right;">生产组织和销售方面问题数:</label>
					<label class="w25 setleft">${ auditProject.auditProjectFindQuestion.productionOrganizationAndSales}个</label>
					<label class="w20" style="text-align:right;">行政事务管理问题数:</label>
					<label class="w25 setleft">${ auditProject.auditProjectFindQuestion.administrativeAffairs}个</label>
					<label class="w20" style="text-align:right;">外部环境和竞争问题数:</label>
					<label class="w25 setleft">${ auditProject.auditProjectFindQuestion.externalEnvironmentAndCompetition}个</label>
					<label class="w20" style="text-align:right;">其他方面问题数:</label>
					<label class="w25 setleft">${ auditProject.auditProjectFindQuestion.others}个</label>
				</div>
			</div>
			<div id="sjzgwtwh">
				<h3 class="data_title1">审计整改问题维护(合计${abarbeitungQuestionNum}个)</h3>
				<div class="model_part">
					<table id="abarbeitungQuestion" style="table-layout:fixed;">
						<thead>
							<tr class="table_header">
							<th>序号</th>
							<th>存在问题</th>
							<th>审计建议</th>
							<th>整改措施</th>
							<th>整改时限</th>
							<th>整改负责人</th>
							<th>备注</th>
							<th>整改完成状态</th>
							<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${!empty requestScope.auditProject.auditProjectAbarbeitungQuestionList}">
								<c:forEach items="${ requestScope.auditProject.auditProjectAbarbeitungQuestionList}" var="auditProjectAbarbeitungQuestion" varStatus="status">
									<tr>
										<td style="text-align:center;">
											${status.index + 1}
										</td>
										<td style="text-align:left;word-wrap:break-word;">
											${auditProjectAbarbeitungQuestion.existentialQuestion}
										</td>
										<td style="text-align:left;word-wrap:break-word;">
											${auditProjectAbarbeitungQuestion.auditSuggestion}
										</td>
										<td style="text-align:left;word-wrap:break-word;">
											${auditProjectAbarbeitungQuestion.abarbeitungMeasures}
										</td>
										<td style="text-align:center;word-wrap:break-word;">
											${auditProjectAbarbeitungQuestion.abarbeitungTimeLimit}
										</td>
										<td style="text-align:center;word-wrap:break-word;">
											${auditProjectAbarbeitungQuestion.abarbeitungOfficer}
										</td>
										<td style="text-align:left;word-wrap:break-word;">
											${auditProjectAbarbeitungQuestion.remark}
										</td>
										<td style="text-align:center;word-wrap:break-word;">
											<c:if test="${auditProjectAbarbeitungQuestion.abarbeitungStatus == 0}">
												未完成
											</c:if>
											<c:if test="${auditProjectAbarbeitungQuestion.abarbeitungStatus == 1}">
												已完成
											</c:if>
										</td>
										<td>
											<a href="javascript:void(0)" onclick="view(${auditProjectAbarbeitungQuestion.id},${status.index})">查看</a>
										</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
			<h3 class="data_title1">创建上报信息</h3>
			<div class="model_part">
				<label class="w20">数据创建人:</label>
				<label class="w25 setleft">${ auditProject.createPersonName}</label>
				<label class="w20">创建时间:</label>
				<label class="w25 setleft">${ auditProject.createDate}</label><br>
				<c:if test="${auditProject.status != 50112}">
					<label class="w20">数据上报人:</label>
					<label class="w25 setleft">${ auditProject.reportPersonName}</label>
					<label class="w20">上报时间:</label>
					<label class="w25 setleft">${ auditProject.reportDate}</label>
				</c:if>
			</div>
			<c:if test="${!empty requestScope.examineList}">
				<h3 class="data_title1">审核意见列表</h3>
				<c:forEach items="${requestScope.examineList }" var="entityExamineview">
					<div class="model_part">
						<label class="w20">审核人:</label>
						<label class="w25 setleft">${ entityExamineview.createPersonName}</label> 
						<label class="w20">审核时间:</label>
						<label class="w25 setleft">${ entityExamineview.createDate}</label>
						<label class="w20">审核结果:</label>
						<label class="w25 setleft">${ entityExamineview.examresultName}</label><br>  
						<label class="w20">审核意见:</label>
						<label class="w75 setleft">${ entityExamineview.examinestr}</label> 
					</div>
				</c:forEach>
			</c:if>
			<div class="model_part">
				<label class="w20" style="vertical-align: top;"><span class="required"> * </span>审核意见:</label>
				<span class="w70"><textarea  rows="5" cols="50" name="examStr" id="examStr"></textarea></span>
			</div>
			<div class="row model_btn">
				<button class="btn btn-primary model_btn_ok" id="commit-4" >通过</button>
				<button class="btn btn-primary model_btn_ok" id="commit-5" >退回</button>
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
		</div>
	</form:form>
	<jsp:include page="../../system/modal.jsp"/>
</body>
<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript">	
		function view(id,nRow){
			var abarbeitungStatus = $("#abarbeitungQuestion tbody").find("tr").eq(nRow).children('td').eq(7).html();
		  	var abarbeitungOfficer = $("#abarbeitungQuestion tbody").find("tr").eq(nRow).children('td').eq(5).html();
		  	var abarbeitungTimeLimit = $("#abarbeitungQuestion tbody").find("tr").eq(nRow).children('td').eq(4).html();
		  	var existentialQuestion = $("#abarbeitungQuestion tbody").find("tr").eq(nRow).children('td').eq(1).html();
		  	var auditSuggestion = $("#abarbeitungQuestion tbody").find("tr").eq(nRow).children('td').eq(2).html();
		  	var abarbeitungMeasures = $("#abarbeitungQuestion tbody").find("tr").eq(nRow).children('td').eq(3).html();
		  	var remark = $("#abarbeitungQuestion tbody").find("tr").eq(nRow).children('td').eq(6).html();
			mload("${pageContext.request.contextPath}/riskcontrol/auditProject/viewAbarbeitungQuestion?op=check&id="+id+"&nRow="+nRow
					+"&abarbeitungStatus="+abarbeitungStatus+"&abarbeitungOfficer="+abarbeitungOfficer+"&abarbeitungTimeLimit="+abarbeitungTimeLimit
					+"&existentialQuestion="+existentialQuestion+"&auditSuggestion="+auditSuggestion+"&abarbeitungMeasures="+abarbeitungMeasures
					+"&remark="+remark+"");
		}
		$("#commit-4").click(function(){
			if(!checkcommit()) {
				return false;
			}
			$.post("/shanghai-gzw/riskcontrol/auditProject/commitexam",{entityid:$("#id").val(),examStr:$("#examStr").val(),examResult:50116},function(data){
				if(data == "success"){
					win.successAlert('保存成功！',function(){
						parent.location.reload(true);
						parent.mclose();
					});
				}else if(data == "false"){
					win.errorAlert('该数据已被删除！',function(){
						parent.location.reload(true);
						parent.mclose();
					});
				}else{
					win.errorAlert('该数据已被操作！',function(){
						parent.location.reload(true);
						parent.mclose();
					});
				}
	 		 });
			 return false;
		});
		$("#commit-5").click(function(){
			if(!checkcommit()) {
				return false;
			}
			$.post("/shanghai-gzw/riskcontrol/auditProject/commitexam",{entityid:$("#id").val(),examStr:$("#examStr").val(),examResult:50117},function(data){
				if(data == "success"){
					win.successAlert('退回成功！',function(){
						parent.location.reload(true);
						parent.mclose();
					});
				}else if(data == "false"){
					win.errorAlert('该数据已被删除！',function(){
						parent.location.reload(true);
						parent.mclose();
					});
				}else{
					win.errorAlert('该数据已被操作！',function(){
						parent.location.reload(true);
						parent.mclose();
					});
				}		
	 		 });
	 		 return false;
		});
		//关闭弹窗
		$(".model_btn_close").click(function () {
			parent.mclose();
			return false;
		});
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		function checkcommit() {
			if($("#examStr").val()=="") {
				win.errorAlert("审核意见不能为空");
				return false;
			}
			return true;
		};	
		$(document).ready(function() {
			var isQuestion = '${auditProject.isQuestion}';
			var isAbarbeitung = '${auditProject.isAbarbeitung}';
			if(isQuestion == 1){
				$("#sjfxwtslwh").show();
			}else{
				$("#sjfxwtslwh").hide();
			}
			if(isAbarbeitung == 1){
				$("#sjzgwtwh").show();
			}else{
				$("#sjzgwtwh").hide();
			}
		});
	</script>
</html>