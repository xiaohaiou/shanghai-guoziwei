<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>主要财务指标数据</title>
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
				<span class="glyphicon glyphicon-pencil"></span>上报主要财务指标数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>查看主要财务指标数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
		
	</c:choose>
<div class="label_inpt_div">
		<form:form id="form2" modelAttribute="entityview" >

				
				<form:hidden path="id"/>
				
				<div class="model_part">
				<label class="w20">单位名称:</label>
				<label class="w25 setleft">${entityview.orgname }</label> 
				
				<label class="w20">年份:</label>
				<label class="w25 setleft">${entityview.year }</label> 
				
				
				
				<label class="w20">负责人:</label>
				<label class="w25 setleft">${entityview.chargePerson }</label> 
				<label class="w20">电话:</label>
				<label class="w25 setleft">${entityview.phoneNumber }</label> 
				<label class="w20">注册地:</label>
				<label class="w25 setleft">${entityview.address }</label> 
				
				
				<label class="w20">主运营地:</label>
				<label class="w25 setleft">${entityview.mainCamp }</label> 
				<label class="w20">会计师事务所:</label>
				<label class="w25 setleft">${entityview.accountingFirm }</label> 
				
				<label class="w20">报表类型:</label>
				<label class="w25 setleft">${entityview.reportTypeName }</label> 
				
				<label class="w20"></label>
				<span class="w25"></span>
				
				
				<label class="w20">总资产 (元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${entityview.totalAssets}"  pattern="#,##0.00" /></label> 
				
				<label class="w20">总负债(元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${entityview.totalLiabilities}"  pattern="#,##0.00" /></label> 
				
				<label class="w20">所有者权益(元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${entityview.ownerEquity}"  pattern="#,##0.00" /></label> 
				
				
				
				<label class="w20">资产负债率(%):</label>
				<label class="w25 setleft">
					<c:if test="${not empty entityview.assetLiabilityRatio }">
						<fmt:formatNumber value="${entityview.assetLiabilityRatio}"  pattern="#,##0.00" />
					</c:if>
					<c:if test="${empty entityview.assetLiabilityRatio && not empty entityview.totalAssets && not empty entityview.totalLiabilities && entityview.totalAssets != '0'}">
						<fmt:formatNumber value="${entityview.totalLiabilities/entityview.totalAssets*100}"  pattern="#,##0.00" />
					</c:if>
				</label> 
				<label class="w20">营业总收入(元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${entityview.totalRevenue }"  pattern="#,##0.00" /></label> 
				
				<label class="w20">营业成本(元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${entityview.operatingCost }"  pattern="#,##0.00" /></label> 
				
				<label class="w20">利润总额(元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${entityview.totalProfit }"  pattern="#,##0.00" /></label> 
				
				<label class="w20">净利润(元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${entityview.netProfit }"  pattern="#,##0.00" /></label> 
				
				<label class="w20">货币资金(元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${entityview.monetaryFund }"  pattern="#,##0.00" /></label> 
				
				<label class="w20">存货(元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${entityview.stock }"  pattern="#,##0.00" /></label> 
				
				<label class="w20">其他应收款(元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${entityview.otherReceivables }"  pattern="#,##0.00" /></label> 
				
			
				<label class="w20">投资型房地产(元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${entityview.investmentTypeRealEstate }"  pattern="#,##0.00" /></label> 
				
				<label class="w20">固定资产(元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${entityview.fixedAssets }"  pattern="#,##0.00" /></label> 
				
				<label class="w20">在建工程(元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${entityview.underConstructionProject }"  pattern="#,##0.00" /></label> 
				
				<label class="w20">无形资产(元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${entityview.intangibleAssets }"  pattern="#,##0.00" /></label> 
				
				<label class="w20">其他应付款(元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${entityview.otherPayable }"  pattern="#,##0.00" /></label> 
			
				<label class="w20">短期借款(元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${entityview.shortTermLoan }"  pattern="#,##0.00" /></label> 
				<label class="w20">应付票据:</label>
				<label class="w25 setleft"><fmt:formatNumber value="${entityview.notessPayable }"  pattern="#,##0.00" /></label> 
			
				
				<label class="w20">一年内到期的非流动负债(元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${entityview.nonCurrentLiabilities }"  pattern="#,##0.00" /></label> 
				
				<label class="w20">长期借款(元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${entityview.longTermLoan }"  pattern="#,##0.00" /></label> 
			
				<label class="w20">应付债券:</label>
				<label class="w25 setleft"><fmt:formatNumber value="${entityview.bondsPayable }"  pattern="#,##0.00" /></label> 
				<label class="w20">长期应付款(元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${entityview.longTermPayables }"  pattern="#,##0.00" /></label> 
				
				<label class="w20">未分配利润(元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${entityview.undistributedProfit }"  pattern="#,##0.00" /></label> 
				
				
				<label class="w20">归属母公司的所有者权益(元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${entityview.ownerEquityAttributableToTheParentCompany }"  pattern="#,##0.00" /></label> 
				<label class="w20">少数股东权益(元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${entityview.minorityShareholdersEquity }"  pattern="#,##0.00" /></label> 
				
				<label class="w20">营业税金及附加(元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${entityview.businessTaxesAndSurcharges }"  pattern="#,##0.00" /></label> 
				<label class="w20">销售费用(元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${entityview.sellingExpenses }"  pattern="#,##0.00" /></label> 
				
				<label class="w20">管理费用(元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${entityview.managementExpenses }"  pattern="#,##0.00" /></label> 
				
				<label class="w20">财务费用(元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${entityview.financialExpenses }"  pattern="#,##0.00" /></label> 
				
	
				<label class="w20">公允价值变动收益(元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${entityview.fairValueChangeIncome }"  pattern="#,##0.00" /></label> 
				
				<label class="w20">投资收益(元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${entityview.incomeFromInvestment }"  pattern="#,##0.00" /></label> 
				
				<label class="w20">所得税费用(元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${entityview.incomeTaxExpense }"  pattern="#,##0.00" /></label>
				<label class="w20">实收资本(元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${entityview.paidCapital}"  pattern="#,##0.00" /></label> 
				<label class="w20"></label>
				<label class="w25 setleft"></label>
				<c:if test="${!empty sszb}">
					<c:forEach items="${sszb}" var="itemsys" varStatus="status">
						<label class="w20">股东名称${status.index+1 }:</label>
						<label class="w25 setleft">${itemsys.name}</label>
						
						<label class="w20">持股比例(%):</label>
						<label class="w25 setleft">${itemsys.precent}</label>
					</c:forEach>
				</c:if>
				<label class="w20">注册资本(元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${entityview.registeredCapital }"  pattern="#,##0.00" /></label>
				<label class="w20"></label>
				<label class="w25 setleft"></label>
				<c:if test="${!empty stock}">
					<c:forEach items="${stock}" var="itemsys" varStatus="status">
						<label class="w20">股东名称${status.index+1 }:</label>
						<label class="w25 setleft">${itemsys.name}</label>
						
						<label class="w20">持股比例(%):</label>
						<label class="w25 setleft">${itemsys.precent}</label>
					</c:forEach>
				</c:if>
			
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
			
		
			var url = "/shanghai-gzw/mainFinancialIndicators/_report?id=" + id;
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
	
		var example = $(".expmale");
		example.removeClass("example").remove();
		$(".btn.new").click(function(){
			$("table tbody").append(example.clone());
		});
		$("body").on("click","i.del.btn",function(){
			$(this).closest("tr").remove();
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
	</script>
</html>