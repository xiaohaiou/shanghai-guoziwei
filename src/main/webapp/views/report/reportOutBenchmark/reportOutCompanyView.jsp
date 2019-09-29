<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>外部对标数据</title>
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
	<c:choose>
		<c:when test="${op eq 'report'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>上报外部对标数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>查看外部对标数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
		
	</c:choose>
<div class="label_inpt_div">
		<form:form id="form2" modelAttribute="entityview" >

				
				
				<div class="model_part">
				<label class="w20">年份:</label>
				<label class="w25 setleft">${ entityview.year}</label> 
				<label class="w20">季度:</label>
				        <c:if test="${entityview.season eq '50108'}">一季度</c:if>
						<c:if test="${entityview.season eq '50109'}">二季度</c:if>
						<c:if test="${entityview.season eq '50110'}">三季度</c:if>
						<c:if test="${entityview.season eq '50111'}">四季度</c:if>
				<br/>
				<label class="w20">单位名称:</label>
				<label class="w25 setleft">${ entityview.orgname}</label>
				<label class="w20">基本每股收益(元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.basicPerEarning}"   /></label> 
				<label class="w20">每股净资产(元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.perNetAsset}" /></label> 
				<label class="w20">营业总收入(亿元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.totalOperatingIncome}"  /></label> 
				<label class="w20">毛利润(亿元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.grossProfit}"/></label> 
				<label class="w20">归属净利润(亿元):</label>
				<label class="w25 setleft">${ entityview.attributableNetProfit}</label> 
				<label class="w20">收入同比增长(%):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.revenueIncrease}" /></label> 
				<label class="w20">收入滚动环比增长(%):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.revenueGrowth}" /></label> 
				<label class="w20">净资产收益率(%):</label>
				<label class="w25 setleft">${ entityview.netAssetYield}</label> 
				<label class="w20">毛利率(%):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.grossInterestRate}"   /></label> 
				<label class="w20">净利率(%):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.netInterestRate}"  /></label> 
			    <label class="w20">总资产周转率(%):</label>
				<label class="w25 setleft">${ entityview.totalAssetTurnover}</label> 

				<label class="w20">存货周转天数(天):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.inventoryTurnoverDays}" /></label> 
			
				<label class="w20">应收账款天数(天):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.accountReceivableDays}" /></label> 
				<label class="w20">资产负债率(%):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.accountLiabilityRatio}" /></label> 
				<label class="w20">流动比率(%):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.flowRate}" /></label> 
				<input type="hidden" value="${entityview.id }" name="entityid" id="entityid">
				</div>
				
				<%-- <c:choose>
					<c:when test="${op eq 'report'}">
						
					</c:when>
					<c:otherwise> --%>
					<h3 class="data_title1">创建上报信息</h3>
							
							<div class="model_part">
								<label class="w20">数据创建人:</label>
								<label class="w25 setleft">${ entityview.createPersonName}</label>
								<label class="w20">创建时间:</label>
								<label class="w25 setleft">${ entityview.createDate}</label>
								<c:if test="${ entityview.status!=50112}">
									<label class="w20">数据上报人:</label>
									<label class="w25 setleft">${ entityview.reportPersonName}</label>
									<label class="w20">上报时间:</label>
									<label class="w25 setleft">${ entityview.reportDate}</label>
								</c:if>
							</div>
							
					<%-- </c:otherwise>
				</c:choose> --%>
			
			
			
			<c:if test="${ !empty entityExamineviewlist  }">
			<h3 class="data_title1">审核意见</h3>
			
			<div class="model_part">
			<c:forEach items="${ requestScope.entityExamineviewlist}" var="sys" varStatus="status">
			
        		<label class="w20">审核人:</label>
				<label class="w25 setleft">${ sys.createPersonName}</label> 
				<label class="w20">审核时间:</label>
				<label class="w25 setleft">${ sys.createDate}</label> 
				<label class="w20">审核结果:</label>
				<label class="w25 setleft">${ sys.examresultName}</label> 
				<label class="w20"></label>
				<label class="w25 setleft"></label> 
				<label class="w20" style="vertical-align:top;">审核意见:</label>
				<label class="w75 setleft" style="word-wrap:break-word;">${ sys.examinestr}</label> 
		
			</c:forEach>
			</div>
			
			</c:if>
			<div class="model_btn">
				
				<c:if test="${op eq 'report' }">
				<button class="btn btn-primary model_btn_ok" id="commit-2">上报</button>
				
				</c:if>
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
	</form:form>
</div>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript">
		
	 	$("#commit-2").click(function(){
			exam(${entityview.id});
			return false;
		}) 
		
		
		function exam(id){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			
		    
			var url = "/shanghai-gzw/reportOutBenchmark/saveandreport?op=report&id=" + id;
			$.post(url, function(data) {
				
				var commitresult=JSON.parse(data);
				$.unblockUI();
				if(commitresult.result==true)
					
					win.successAlert('上报成功！',function(){
						
						parent._query();
						parent.mclose();
					});
				else
					{
						win.errorAlert(commitresult.exceptionStr);
					}
					
			});
			
		
			
		}
	
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