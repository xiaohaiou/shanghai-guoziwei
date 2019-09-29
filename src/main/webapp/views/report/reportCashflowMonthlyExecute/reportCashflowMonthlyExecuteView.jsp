<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>现金流月执行数据</title>
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
				<span class="glyphicon glyphicon-pencil"></span>上报现金流月执行数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>查看现金流月执行数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
		
	</c:choose>
	<div class="label_inpt_div">
		<form:form id="form2" modelAttribute="entityview" >
			<div class="model_part">
				<label class="w20">单位名称:</label>
				<label class="w25 setleft">${ entityview.orgname}</label> 
				<label class="w20">所属核心企业:</label>
				<label class="w25 setleft">${ entityview.coreCompName}</label> 
				<label class="w20">年份:</label>
				<label class="w25 setleft">${ entityview.year}</label>
				<label class="w20">月份:</label>
				<label class="w25 setleft">${ entityview.month}</label>
			</div>
			<h3 class="data_title1">经营性现金流(金额：万元)</h3>
			<div class="model_part">
				<label class="w20">经营性流入:</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.commercialInto}"  pattern="#,##0.00" /></label> 
				<label class="w20">经营性流出:</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.commercialOut}"  pattern="#,##0.00" /></label> 
				<label class="w20">经营性在建工程:</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.commercialProject}"  pattern="#,##0.00" /></label>
				<label class="w20">经营性净流量:</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.commercialNetInflow}"  pattern="#,##0.00" /></label>
			</div> 
			<h3 class="data_title1">筹资性现金流(金额：万元)</h3>
			<div class="model_part">
				<label class="w20">筹资性流入:</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.financingInto}"  pattern="#,##0.00" /></label> 
				<label class="w20">归还本金金额:</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.returnCapitalAmounts}"  pattern="#,##0.00" /></label> 
				<label class="w20">归还利息金额:</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.returnInterestAmounts}"  pattern="#,##0.00" /></label> 
				<label class="w20">归还票据金额:</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.returnNoteAmounts}"  pattern="#,##0.00" /></label> 
				<label class="w20">归还租金金额:</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.returnRentAmounts}"  pattern="#,##0.00" /></label> 
				<label class="w20">筹资性流出:</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.financingOut}"  pattern="#,##0.00" /></label> 
				<label class="w20">筹资性净流量:</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.financingNetInflow}"  pattern="#,##0.00" /></label>
				<label class="w20"></label>
				<label class="w25 setleft"></label>
			</div>
			<h3 class="data_title1">投资性现金流(金额：万元)</h3>
			<div class="model_part">
				<label class="w20">投资性流入:</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.investmentInto}"  pattern="#,##0.00" /></label> 
				<label class="w20">在建工程:</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.constructionInProcess}"  pattern="#,##0.00" /></label> 
				<label class="w20">飞机投资:</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.airplaneInvest}"  pattern="#,##0.00" /></label>
				<label class="w20">股权投资:</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.stockEquityInvest}"  pattern="#,##0.00" /></label> 
				<label class="w20">土地储备:</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.landReserve}"  pattern="#,##0.00" /></label> 
				<label class="w20">固定资产购置:</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.fixedAssetsPurchase}"  pattern="#,##0.00" /></label>
				<label class="w20">金融性投资:</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.financeInvest}"  pattern="#,##0.00" /></label>
				<label class="w20">其它投资性支出:</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.elseInvestExpense}"  pattern="#,##0.00" /></label> 
				<label class="w20">投资性流出:</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.investOut}"  pattern="#,##0.00" /></label> 
				<label class="w20">投资性净流量:</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.investNetInflow}"  pattern="#,##0.00" /></label>
			</div>
			<h3 class="data_title1">拆借或贡献现金流(金额：万元)</h3>
			<div class="model_part">
				<label class="w20">拆借:</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.lendingOrContributionInto}"  pattern="#,##0.00" /></label> 
				<label class="w20">贡献:</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.lendingOrContributionOut}"  pattern="#,##0.00" /></label> 
				<label class="w20">资金拆借净流量:</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.lendingOrContributionNetInflow}"  pattern="#,##0.00" /></label>
				<label class="w20"></label>
				<label class="w25 setleft"></label>
			</div> 
			<h3 class="data_title1">现金流合计(金额：万元)</h3>
			<div class="model_part">
				<label class="w20">现金流入:</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.cashInto}"  pattern="#,##0.00" /></label> 
				<label class="w20">现金流出:</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.cashOut}"  pattern="#,##0.00" /></label> 
				<label class="w20">现金净流量:</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.cashNetInflow}"  pattern="#,##0.00" /></label>
				<label class="w20"></label>
				<label class="w25 setleft"></label>
				<label class="w20" style="vertical-align:top;">备注:</label>
				<label class="w75 setleft" style="word-wrap:break-word;">${ entityview.remark}</label>
				<input type="hidden" value="${entityview.id }" name="entityid" id="entityid">
			</div> 
			
			<h3 class="data_title1">筹资性流入明细列表</h3>
			<div class="model_part">
				<div id="reportMonthlyFinancingIntoDetailList">
				
				</div>
			</div>
			
			<h3 class="data_title1">筹资性流出明细列表</h3>
			<div class="model_part">
				<div id="reportMonthlyFinancingOutDetailList">
				
				</div>
			</div>
			
			<h3 class="data_title1">投资性流出明细列表</h3>
			<div class="model_part">
				<div id="reportMonthlyInvestmentOutDetailList">
				
				</div>
			</div>
			
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
	var data1 = ${data1};
	if(data1 == 1){
		data1 = [];
	}
	
	var data2 = ${data2};
	if(data2 == 1){
		data2 = [];
	}
	
	var data3 = ${data3};
	if(data3 == 1){
		data3 = [];
	}
	
	function initNodeList(v,pageNums,op,data){
		var stringList = "";
		if(data.length != 0){
			stringList = JSON.stringify(data);
		}
		$.ajax({
			data:{
				stringList:stringList,
				pageNums:pageNums,
				op:op
			},
			url:"${pageContext.request.contextPath}/"+v+"/viewList",
			type:"POST",
			dataType:"text",
			async:false,
			success:function(data){
				$("#"+v+"List").children().remove();
				$("#"+v+"List").append(data);
			}
		});	
	}
	initNodeList('reportMonthlyFinancingIntoDetail',1,"",data1);//初始化筹资性流入明细列表
	initNodeList('reportMonthlyFinancingOutDetail',1,"",data2);//初始化筹资性流出明细列表
	initNodeList('reportMonthlyInvestmentOutDetail',1,"",data3);//初始化投资性流出明细列表
</script>
<script type="text/javascript">
		
		$("#commit-2").click(function(){
		
			exam(${entityview.id});
			return false;
		})
		
		
		function exam(id){
		
			
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			
		
			var url = "/shanghai-gzw/reportCashflowMonthlyExecute/listandreport?id=" + id;
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