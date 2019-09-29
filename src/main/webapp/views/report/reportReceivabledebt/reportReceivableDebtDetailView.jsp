<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>查看应收债权(内部)信息</title>
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
			<c:if test="${entityview.type ==0}">
				<span class="glyphicon glyphicon-pencil"></span>查看应收债权(内部)信息
			</c:if>	
			<c:if test="${entityview.type ==1}">
				<span class="glyphicon glyphicon-pencil"></span>查看应收债权(外部)信息
			</c:if>		
				<i class="iconfont icon-guanbi"></i>
			</h4>


		<form:form id="form2" modelAttribute="entityview" >
			<form:hidden path="id"/>
				
				<div class="label_inpt_div">
					<h3 class="data_title1">基本信息</h3>
					<div class="model_part">
						<label class="w20">产业集团:</label>
						<label class="w25 setleft">海航物流</label> 
						<label class="w20">所属核心企业:</label>
						<label class="w25 setleft">${entityview.coreorgname}</label> 
						
						<label class="w20">填报公司名称:</label>
						<label class="w25 setleft">${entityview.debtorgname}</label> 
						<label class="w20">对方公司全称:</label>
						<label class="w25 setleft">${entityview.oppositeorgname}</label> 
						
						<label class="w20">催收责任人:</label>
						<label class="w25 setleft">${entityview.collectionperson}</label> 
						<label class="w20">分管领导:</label>
						<label class="w25 setleft">${entityview.leadership}</label> 
						
						<label class="w20">入帐科目:</label>
						<label class="w25 setleft">${ entityview.accountingsubject}</label> 
						<label class="w20"></label>
						<label class="w25 setleft"></label> 
					</div>
					
					<h3 class="data_title1">期初信息(单位：元)</h3>
					<div class="model_part">
						<label class="w20">期初日期:</label>
						<label class="w25 setleft">${entityview.year}-${entityview.month}-1</label> 
						<label class="w20">期初余额:</label>
						<label class="w25 setleft">${entityview.beginningbalance}</label> 
						
						<label class="w20">信用期内金额:</label>
						<label class="w25 setleft">${entityview.beginningcreditperiodbalance}</label> 
						<label class="w20">超期额(1-30天):</label>
						<label class="w25 setleft">${entityview.beginningbalancethirtydays}</label> 
						
						<label class="w20">超期额(31-60天):</label>
						<label class="w25 setleft">${entityview.beginningbalancesixtydays}</label> 
						<label class="w20">超期额(61-90天):</label>
						<label class="w25 setleft">${entityview.beginningbalanceninetydays}</label> 
						
						<label class="w20">超期额(91天以上):</label>
						<label class="w25 setleft">${ entityview.beginningbalanceoverdays}</label> 
						<label class="w20">本月新增:</label>
						<label class="w25 setleft">${ entityview.monthnewaccout}</label> 
						
						<label class="w20">本月收回(信用期内):</label>
						<label class="w25 setleft">${ entityview.monthreturnaccout}</label> 
						<label class="w20">本月收回(超期):</label>
						<label class="w25 setleft">${ entityview.momthreturnoveraccout}</label> 
					</div>
				
					<h3 class="data_title1">期末信息(单位：元)</h3>
					<div class="model_part">
						<label class="w20">期末日期:</label>
						<label class="w25 setleft">${entityview.year}-${entityview.month}-${entityview.day}</label> 
						<label class="w20">期末余额:</label>
						<label class="w25 setleft">${entityview.endingbalance}</label> 
						
						<label class="w20">信用期内金额:</label>
						<label class="w25 setleft">${entityview.endingcreditperiodbalance}</label> 
						<label class="w20">超期额(1-30天):</label>
						<label class="w25 setleft">${entityview.endingbalancethirtydays}</label> 
						
						<label class="w20">超期额(31-60天):</label>
						<label class="w25 setleft">${entityview.endingbalancesixtydays}</label> 
						<label class="w20">超期额(61-90天):</label>
						<label class="w25 setleft">${entityview.endingbalanceninetydays}</label> 
						
						<label class="w20">超期额(91天-1年):</label>
						<label class="w25 setleft">${ entityview.endingbalanceoverdays}</label> 
						<label class="w20">超期额(1-2年):</label>
						<label class="w25 setleft">${ entityview.endingbalancetwoyears}</label> 
						
						<label class="w20">超期额(2-3年):</label>
						<label class="w25 setleft">${ entityview.endingbalancethreeyears}</label> 
						<label class="w20">超期额(3-5年):</label>
						<label class="w25 setleft">${ entityview.endingbalancefiveyears}</label> 
						
						<label class="w20">超期额(5年以上):</label>
						<label class="w25 setleft">${ entityview.endingbalanceoveryears}</label> 
						<label class="w20">产生原因/备注:</label>
						<label class="w25 setleft">${ entityview.reasonorremarks}</label> 
						
						<label class="w20">债权所属类别:</label>
						<label class="w25 setleft">${ entityview.debttype}</label> 
						<label class="w20">保证金起始期限:</label>
						<label class="w25 setleft">${ entityview.cashdepositdeadline}</label> 
						
						<label class="w20">催收措施:</label>
						<label class="w25 setleft">${ entityview.collectionmeasures}</label> 
						<label class="w20">预计收回时间:</label>
						<label class="w25 setleft">${ entityview.planreturntime}</label> 
					</div>
					
					<h3 class="data_title1">超期应收账款法律诉讼</h3>
					<div class="model_part">
						<label class="w20">原始资料是否收集完毕:</label>
						<label class="w25 setleft">${entityview.isFinish}</label> 
						<label class="w20">催收函是否发出:</label>
						<label class="w25 setleft">${entityview.isSend}</label> 
						
						<label class="w20">是否有回函/还款承诺:</label>
						<label class="w25 setleft">${entityview.isPromise}</label> 
						<label class="w20">是否已经报案件移交公文:</label>
						<label class="w25 setleft">${entityview.isTurnover}</label> 
					
					</div>
				<input type="hidden" value="${entityview.id}" name="entityid" id="entityid">

			
			
			<div class="model_btn">
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

	
		
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		//关闭弹窗
		$(".model_btn_close").click(function () {
			parent.mclose();
			return false;
		});
	</script>
</html>