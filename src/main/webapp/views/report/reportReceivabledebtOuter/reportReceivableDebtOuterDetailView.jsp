<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>查看应收债权(外部)信息</title>
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
			<span class="glyphicon glyphicon-pencil"></span>查看应收债权(外部)信息
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
						<label class="w25 setleft">${entityview.orgname}</label> 
						<label class="w20">欠款单位:</label>
						<label class="w25 setleft">${entityview.debtOrgname}</label> 
						<label class="w20">欠款类型：</label>
						<label class="w25 setleft">${entityview.debtTypeName}</label> 
						<label class="w20">具体事由：</label>
						<label class="w25 setleft">${entityview.jtsy}</label> 
						<label class="w20">欠款金额（万元）：</label>
						<label class="w25 setleft">
							<fmt:formatNumber value="${entityview.loanMoney}"  pattern="#,##0.00" />
						</label> 
						<label class="w20">其中超期欠款金额（万元）：</label>
						<label class="w25 setleft">
							<fmt:formatNumber value="${entityview.cqLoanMoney}"  pattern="#,##0.00" />
						</label>
						<label class="w20">欠款时间：</label>
						<label class="w25 setleft">
							${entityview.loanTime}
						</label>
						<label class="w20">是否超期：</label>
						<label class="w25 setleft">
							<c:if test="${ entityview.isOvertime == 1}">是</c:if>
							<c:if test="${ entityview.isOvertime == 0}">否</c:if>
						</label>
						<label class="w20">已超期时限（月）：</label>
						<label class="w25 setleft">
							${entityview.overtimeMonth }
						</label>
						<label class="w20">是否催收：</label>
						<label class="w25 setleft">
							<c:if test="${ entityview.isCuishou == 1}">是</c:if>
							<c:if test="${ entityview.isCuishou == 0}">否</c:if>
						</label>
						<label class="w20">未催收原因：</label>
						<label class="w25 setleft">
							${ entityview.noCuishoureason}
						</label>
						<label class="w20">回款计划：</label>
						<label class="w25 setleft">
							${ entityview.hkjh}
						</label>
						<label class="w20">是否进入法律程序：</label>
						<label class="w25 setleft">
							<c:if test="${ entityview.isInlaw == 1}">是</c:if>
							<c:if test="${ entityview.isInlaw == 0}">否</c:if>
						</label>
						<label class="w20">本周回收金额（万元）：</label>
						<label class="w25 setleft">
							<fmt:formatNumber value="${entityview.weekBackMoney}"  pattern="#,##0.00" />
						</label>
						<label class="w20">累计收回金额（万元）：</label>
						<label class="w25 setleft">
							<fmt:formatNumber value="${entityview.totalBackMoney}"  pattern="#,##0.00" />
						</label>
						<label class="w20">预计坏账：</label>
						<label class="w25 setleft">
							${entityview.yjhz}
						</label>
						<label class="w20">催收责任人:</label>
						<label class="w25 setleft">${entityview.cuishouPerson}</label> 
						<label class="w20">进展描述:</label>
						<label class="w25 setleft">${entityview.jzDescription}</label> 
						<label class="w20">备注:</label>
						<label class="w25 setleft">${entityview.remark}</label>
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