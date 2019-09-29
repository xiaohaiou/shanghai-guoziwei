<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>合规台账审核页面</title>
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
		<span class="glyphicon glyphicon-pencil"></span>合规台账审核
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="riskControlAccount" method="post">
		<form:hidden path="id"/>
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20">投诉(专项调查)项目名称:</label>
				<label class="w25 setleft">${ riskControlAccount.complainProjectName}</label>
				<label class="w20">承办部门:</label>
				<label class="w25 setleft">${ riskControlAccount.undertakeDept}</label><br>
				<label class="w20">是否集团内部投诉:</label>
				<label class="w25 setleft"><c:if test="${riskControlAccount.isGroupInternalComplain eq 1}">是</c:if><c:if test="${riskControlAccount.isGroupInternalComplain eq 0}">否</c:if></label>
				<label class="w20">投诉渠道:</label>
				<label class="w25 setleft"><c:if test="${riskControlAccount.complainChannel eq 0}">电话</c:if><c:if test="${riskControlAccount.complainChannel eq 1}">邮件</c:if><c:if test="${riskControlAccount.complainChannel eq 2}">其他</c:if></label><br>
				<label class="w20">被投诉人员工编号:</label>
				<label class="w25 setleft">${ riskControlAccount.complainedPersonNo}</label>
				<label class="w20">投诉主要内容类型:</label>
				<label class="w25 setleft"><c:if test="${riskControlAccount.complainChannel eq 0}">工资拖欠</c:if><c:if test="${riskControlAccount.complainChannel eq 1}">辞退</c:if><c:if test="${riskControlAccount.complainChannel eq 2}">其他</c:if></label><br>
				<label class="w20">被投诉人单位:</label>
				<label class="w25 setleft">${ riskControlAccount.complainedPersonDeptName}</label>
				<label class="w20">是否匿名投诉:</label>
				<label class="w25 setleft"><c:if test="${riskControlAccount.isAnonymityComplain eq 1}">是</c:if><c:if test="${riskControlAccount.isAnonymityComplain eq 0}">否</c:if></label><br>
				<label class="w20">被投诉人姓名:</label>
				<label class="w25 setleft">${ riskControlAccount.complainedPersonName}</label>
				<label class="w20">投诉收到时间:</label>
				<label class="w25 setleft">${ riskControlAccount.complainReceiveDate}</label><br>
				<label class="w20">被投诉人职务:</label>
				<label class="w25 setleft">${ riskControlAccount.complainedPersonDuty}</label>
				<label class="w20">投诉处理时间:</label>
				<label class="w25 setleft">${ riskControlAccount.complainManageDate}</label><br>
				<label class="w20">被投诉人电话:</label>
				<label class="w25 setleft">${ riskControlAccount.complainedPersonPhone}</label>
				<label class="w20">投诉上报时间:</label>
				<label class="w25 setleft">${ riskControlAccount.complainEscalateDate}</label><br>
				<label class="w20">投诉最终审批人:</label>
				<label class="w25 setleft">${ riskControlAccount.complainFinalApprover}</label>
				<label class="w20">是否开展后续调查:</label>
				<label class="w25 setleft"><c:if test="${riskControlAccount.isFollowupInvestigation eq 1}">是</c:if><c:if test="${riskControlAccount.isFollowupInvestigation eq 0}">否</c:if><c:if test="${riskControlAccount.isFollowupInvestigation eq 2}">待定</c:if></label><br>
				<div id="jbdcnr">
					<label class="w20">举报调查具体内容:</label>
					<label class="w70 setleft">${ riskControlAccount.reportInvestigation}</label><br>
				</div>
				<c:if test="${riskControlAccount.isFollowupInvestigation ne 1}">
					<label class="w20">未开展后续调查原因:</label>
					<label class="w70 setleft">${ riskControlAccount.noFollowupInvestigationReason}</label>
				</c:if>
			</div>
			<div id="hgdcapydc">
				<h3 class="data_title1">合规调查安排与结果</h3>
				<div class="model_part">
					<label class="w20">调查负责人:</label>
					<label class="w25 setleft">${ riskControlAccount.investigateHeader}</label>
					<label class="w20">相关责任人:</label>
					<label class="w25 setleft">${ riskControlAccount.responsiblePerson}</label><br>
					<label class="w20">调查启动时间:</label>
					<label class="w25 setleft">${ riskControlAccount.surveyStartupTime}</label>
					<label class="w20">调查结束时间:</label>
					<label class="w25 setleft">${ riskControlAccount.surveyEndTime}</label><br>
					<label class="w20">调查涉及单位:</label>
					<label class="w25 setleft">${ riskControlAccount.investigateInvolveDeptName}</label>
					<label class="w20">被调查人员姓名:</label>
					<label class="w25 setleft">${ riskControlAccount.investigatePersonName}</label><br>
					<label class="w20">建议人员问责情况:</label>
					<label class="w25 setleft">${ riskControlAccount.suggestPersonBlameCondition}</label>
					<label class="w20">提出合规建议情况:</label>
					<label class="w25 setleft">${ riskControlAccount.raiseSuggestCondition}</label><br>
					<label class="w20">被调查人员职务:</label>
					<label class="w25 setleft">${ riskControlAccount.investigatePersonDuty}</label>
					<label class="w20">调查报告办文编号:</label>
					<label class="w25 setleft">${ riskControlAccount.investigateReportNo}</label><br>
					<label class="w20">被调查人员管理级别:</label>
					<label class="w25 setleft">${ riskControlAccount.investigatedPersonLevelName}</label>
					<label class="w20">报告所属产业集团:</label>
					<label class="w25 setleft">${ riskControlAccount.reportBelongCompanyName}</label><br>
					<label class="w20">被调查单位是否配合:</label>
					<label class="w25 setleft"><c:if test="${riskControlAccount.isInvestigatedCooperate eq 1}">是</c:if><c:if test="${riskControlAccount.isInvestigatedCooperate eq 0}">否</c:if><c:if test="${riskControlAccount.isInvestigatedCooperate eq 2}">待定</c:if></label>
					<label class="w20">报告名称:</label>
					<label class="w25 setleft">${ riskControlAccount.reportName}</label><br>
					<label class="w20">责任界定:</label>
					<label class="w25 setleft">${ riskControlAccount.responsibilityFor}</label>
					<label class="w20">调查结论:</label>
					<label class="w25 setleft">${ riskControlAccount.investigateConclusion}</label><br>
					<label class="w20">报告类型:</label>
					<label class="w25 setleft">${ riskControlAccount.reportType}</label>
					<label class="w20">报告撰写人:</label>
					<label class="w25 setleft">${ riskControlAccount.reportWriter}</label><br>
					<label class="w20">报告复核人:</label>
					<label class="w25 setleft">${ riskControlAccount.reportReviewer}</label>
					<label class="w20">报告提交时间:</label>
					<label class="w25 setleft">${ riskControlAccount.reportCommitTime}</label><br>
					<label class="w20">报告复核通过时间:</label>
					<label class="w25 setleft">${ riskControlAccount.reportReviewTime}</label>
					<label class="w20">报告最终审批人:</label>
					<label class="w25 setleft">${ riskControlAccount.reportFinalExaminer}</label><br>
					<label class="w20">调查发现其他违规事件:</label>
					<label class="w25 setleft">${ riskControlAccount.investigateFindOtherInfraction}</label>
					<label class="w20">是否涉及整改:</label>
					<label class="w25 setleft"><c:if test="${riskControlAccount.isInvolveAbarbeitung eq 1}">是</c:if><c:if test="${riskControlAccount.isInvolveAbarbeitung eq 0}">否</c:if><c:if test="${riskControlAccount.isInvolveAbarbeitung eq 2}">待定</c:if></label>
				</div>
			</div>
			<div id="zgqk">
				<h3 class="data_title1">整改情况</h3>
				<div class="model_part">
					<label class="w20">处分问责呈报公文编号:</label>
					<label class="w25 setleft">${ riskControlAccount.punishReportNo}</label>
					<label class="w20">整改通知书下发时间:</label>
					<label class="w25 setleft">${ riskControlAccount.abarbeitungNoticeTime}</label><br>
					<label class="w20">处分问责办文编号:</label>
					<label class="w25 setleft">${ riskControlAccount.punishReportNo2}</label>
					<label class="w20">是否整改验收:</label>
					<label class="w25 setleft"><c:if test="${riskControlAccount.isAbarbeitungAccept eq 1}">是</c:if><c:if test="${riskControlAccount.isAbarbeitungAccept eq 0}">否</c:if><c:if test="${riskControlAccount.isAbarbeitungAccept eq 2}">待定</c:if></label><br>
					<div id="sfzgys">
						<label class="w20">整改责任人:</label>
						<label class="w25 setleft">${ riskControlAccount.abarbeitungOfficer}</label>
						<label class="w20">整改验收通过时间:</label>
						<label class="w25 setleft">${ riskControlAccount.abarbeitungPassTime}</label><br>
						<label class="w20">要求整改完成时间:</label>
						<label class="w25 setleft">${ riskControlAccount.requireAbarbeitungTime}</label>
						<label class="w20">整改验收人:</label>
						<label class="w25 setleft">${ riskControlAccount.abarbeitungAccepter}</label><br>
						<label class="w20">建议问责落实情况:</label>
						<label class="w25 setleft">${ riskControlAccount.suggestWorkCondition}</label>
						<label class="w20">整改结果:</label>
						<label class="w25 setleft">${ riskControlAccount.abarbeitungResult}</label>
					</div>
				</div>
			</div>
			<h3 class="data_title1">创建上报信息</h3>
			<div class="model_part">
				<label class="w20">数据创建人:</label>
				<label class="w25 setleft">${ riskControlAccount.createPersonName}</label>
				<label class="w20">创建时间:</label>
				<label class="w25 setleft">${ riskControlAccount.createDate}</label><br>
				<c:if test="${riskControlAccount.status != 50112}">
					<label class="w20">数据上报人:</label>
					<label class="w25 setleft">${ riskControlAccount.reportPersonName}</label>
					<label class="w20">上报时间:</label>
					<label class="w25 setleft">${ riskControlAccount.reportDate}</label>
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
</body>
<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript">	
		$("#commit-4").click(function(){
			if(!checkcommit()) {
				return false;
			}
			$.post("/shanghai-gzw/riskcontrol/account/commitexam",{entityid:$("#id").val(),examStr:$("#examStr").val(),examResult:50116},function(data){
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
			$.post("/shanghai-gzw/riskcontrol/account/commitexam",{entityid:$("#id").val(),examStr:$("#examStr").val(),examResult:50117},function(data){
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
			if("${riskControlAccount.isFollowupInvestigation}" == 1){
				$("#hgdcapydc").show();
				if("${riskControlAccount.isInvolveAbarbeitung}" == 1){
					$("#zgqk").show();
				}else{
					$("#zgqk").hide();
				}
				if("${riskControlAccount.isAbarbeitungAccept}" == 1){
					$("#sfzgys").show();
				}else{
					$("#sfzgys").hide();
				}
			}else{
				$("#hgdcapydc").hide();
				$("#jbdcnr").hide();
				$("#sfzgys").hide();
				$("#zgqk").hide();
			}
		});
	</script>
</html>