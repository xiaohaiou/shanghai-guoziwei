<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>合同信息审核页面</title>
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
		<span class="glyphicon glyphicon-pencil"></span>合同信息审核
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="riskControlContract" method="post">
		<form:hidden path="id"/>
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20">合同所属业态公司:</label>
				<label class="w25 setleft">${ riskControlContract.contractBelongCompanyName}</label>
				<label class="w20">合同所在实体企业:</label>
				<label class="w25 setleft">${ riskControlContract.contractSubordinateCompanyName}</label><br>
				<label class="w20">合同公文号:</label>
				<label class="w25 setleft">${ riskControlContract.contractDocumentNo}</label>
				<label class="w20">合同名称:</label>
				<label class="w25 setleft">${ riskControlContract.contractName}</label><br>
				<label class="w20">合同类型:</label>
				<label class="w25 setleft">${ riskControlContract.contractTypeName}</label>
				<label class="w20">我方合同主体:</label>
				<label class="w25 setleft">${ riskControlContract.ourContractSubject}</label><br>
				<label class="w20">对方合同主体:</label>
				<label class="w25 setleft">${ riskControlContract.otherContractSubject}</label>
				<label class="w20">履行跟踪人:</label>
				<label class="w25 setleft">${ riskControlContract.trackingMan}</label><br>
				<label class="w20">合同标的总额(万元):</label>
				<label class="w25 setleft">${ riskControlContract.contractTotalAmount}</label>
				<label class="w20">是否属于重大合同:</label>
				<label class="w25 setleft"><c:if test="${riskControlContract.isMajorContract eq 1}">是</c:if><c:if test="${riskControlContract.isMajorContract eq 0}">否</c:if></label><br>
				<label class="w20">合同主要内容:</label>
				<label class="w25 setleft">${ riskControlContract.contractContent}</label><br>
				<label class="w20">合同主要履约节点:</label>
				<label class="w25 setleft">${ riskControlContract.contractNode}</label><br>
				<label class="w20">法务审核人:</label>
				<label class="w25 setleft">${ riskControlContract.legalPerson}</label>
				<label class="w20">合同签订日期:</label>
				<label class="w25 setleft">${ riskControlContract.contractSignDate}</label><br>
				<label class="w20">是否违约:</label>
				<label class="w25 setleft"><c:if test="${riskControlContract.isDefault eq 1}">是</c:if><c:if test="${riskControlContract.isDefault eq 0}">否</c:if></label><br>
				<div id="wyInfo">
					<label class="w20" style="text-align:right;">我方违约情况:</label>
					<label class="w25 setleft">${ riskControlContract.ourDefault}</label>
					<label class="w20" style="text-align:right;">对方违约情况:</label>
					<label class="w25 setleft">${ riskControlContract.otherDefault}</label><br>
					<label class="w20" style="text-align:right;">风险等级:</label>
					<label class="w25 setleft">${ riskControlContract.riskGrade}</label>
					<label class="w20" style="text-align:right;">风险处置方案及进展:</label>
					<label class="w25 setleft">${ riskControlContract.riskManage}</label><br>
					<label class="w20" style="text-align:right;">我方逾期应付款总额(万元):</label>
					<label class="w25 setleft">${ riskControlContract.ourOverPay}</label>
					<label class="w20" style="text-align:right;">我方违约产生的违约金总额(万元):</label>
					<label class="w25 setleft">${ riskControlContract.ourDefaultPay}</label><br>
					<label class="w20" style="text-align:right;">对方逾期应付款总额(万元):</label>
					<label class="w25 setleft">${ riskControlContract.otherOverPay}</label>
					<label class="w20" style="text-align:right;">对方违约产生的违约金总额(万元):</label>
					<label class="w25 setleft">${ riskControlContract.otherDefaultPay}</label>
				</div>
			</div>
			<h3 class="data_title1">创建上报信息</h3>
			<div class="model_part">
				<label class="w20">数据创建人:</label>
				<label class="w25 setleft">${ riskControlContract.createPersonName}</label>
				<label class="w20">创建时间:</label>
				<label class="w25 setleft">${ riskControlContract.createDate}</label><br>
				<c:if test="${riskControlContract.status != 50112}">
					<label class="w20">数据上报人:</label>
					<label class="w25 setleft">${ riskControlContract.reportPersonName}</label>
					<label class="w20">上报时间:</label>
					<label class="w25 setleft">${ riskControlContract.reportDate}</label>
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
			$.post("/shanghai-gzw/riskcontrol/contract/commitexam",{entityid:$("#id").val(),examStr:$("#examStr").val(),examResult:50116},function(data){
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
			$.post("/shanghai-gzw/riskcontrol/contract/commitexam",{entityid:$("#id").val(),examStr:$("#examStr").val(),examResult:50117},function(data){
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
			var isDefault = "${riskControlContract.isDefault}";
			if(isDefault == 1){
				$("#wyInfo").show();
			}else{
				$("#wyInfo").hide();
			}
		});
	</script>
</html>