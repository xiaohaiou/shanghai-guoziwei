<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>个人借款台账明细</title>
<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />

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
			<span class="glyphicon glyphicon-pencil"></span>查看员工个人借款信息
			<i class="iconfont icon-guanbi"></i>
		</h4>

		<form:form id="form2" modelAttribute="entityview" >
				
				<div class="label_inpt_div">
				<h3 class="data_title1">基本信息</h3>
				<div class="model_part">
				<label class="w20">公司名称:</label>
				<label class="w25 setleft">${entityview.orgname}</label> 
				<label class="w20">所属核心企业</label>
				<label class="w25 setleft">${entityview.coreorgname}</label> 
				
				<label class="w20">借款人:</label>
				<label class="w25 setleft">${ entityview.responsibleperson}</label> 
				<label class="w20">借款用途:</label>
				<label class="w25 setleft">${ entityview.useway}</label> 

				<label class="w20">借款时间:</label>
				<label class="w25 setleft">${ entityview.loantime}</label> 
				<label class="w20">借款金额(万元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${entityview.loanmoney }"  pattern="#,##0.00" /></label> 
				
				<label class="w20">已借款期限(月):</label>
				<label class="w25 setleft">${ entityview.loanmonth}</label> 
				<label class="w20">借款公文号:</label>
				<label class="w25 setleft">${ entityview.loannum}</label> 
				
				<label class="w20">备注:</label>
				<label class="w70 setleft">${ entityview.remark}</label> 
				
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