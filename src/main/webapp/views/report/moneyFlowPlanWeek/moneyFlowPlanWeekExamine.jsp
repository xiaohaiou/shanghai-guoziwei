<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>现金流周计划审核页面</title>
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
<style>
	.search_title {
	    font-weight:bold;
	}
	#company{
		width:16em;
	}
	@media screen and (max-width: 1630px){
		.clear {
		    right: -16em;
		    width: auto;
		    top: 2px
		}
	}
	.tablebox{
		overflow-x: auto;
	}
	.tablebox>table{
	    width: 150em;
	}
</style>
</head>
<body onload="loadData()">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>审核现金流周计划数据
				<i class="iconfont icon-guanbi"></i>
			</h4>

	<div class="model_body">
		<form:form id="form2" modelAttribute="entity" >
			<form:hidden id="entityid" path="pid"/>
			<div class="label_inpt_div"><div class="model_part">
				<label class="w20">单位:</label>
				<label class="w25 setleft" >${entity.orgname}</label> 
				<label class="w20">所属核心企业:</label>
				<label class="w25 setleft" >${entity.parentCompany}</label> 
				<label class="w20">时间:</label>
				<label class="w25 setleft" >${entity.date}</label> 
				<label class="w20">周数:</label>
				<label class="w25 setleft" >${entity.week}</label> 
				<label class="w20">周开始时间:</label>
				<label class="w25 setleft" ><fmt:formatDate value="${entity.weekStart}" pattern="yyyy-MM-dd"/></label> 
				<label class="w20">周结束时间:</label>
				<label class="w25 setleft" ><fmt:formatDate value="${entity.weekEnd}" pattern="yyyy-MM-dd"/></label> 
			</div>
			<h3 class="data_title1">经营性现金流</h3>
					<div class="model_part">
						<label class="w20"><font color=red>*</font>经营性流入（万元）:</label>
						<label class="w25 setleft" >${entity.operationalInflow}</label> 
						<label class="w20"><font color=red>*</font>经营性流出（万元）:</label>
						<label class="w25 setleft" >${entity.operationalOutflow}<%-- <fmt:formatNumber value="${entity.operationalOutflow}" pattern="#.####" minFractionDigits="4" ></fmt:formatNumber> --%></label> 
						<label class="w20"><font color=red>*</font>经营性净流量（万元）:</label>
						<label class="w25 setleft" >${entity.operationalNetFlow}<%-- <fmt:formatNumber value="${entity.operationalNetFlow}" pattern="#.####" minFractionDigits="4" ></fmt:formatNumber> --%></label> 
					</div>
				
				<h3 class="data_title1">筹资性现金流</h3>
					<div class="model_part">
						<label class="w20"><font color=red>*</font>筹资性流入（万元）:</label>
						
						<label class="w25 setleft" >${entity.capitalInflow}<%-- <fmt:formatNumber value="${entity.capitalInflow}" pattern="#.####" minFractionDigits="4" ></fmt:formatNumber> --%></label> 
						<label class="w20"><font color=red>*</font>筹资性流出（万元）:</label>
						
						<label class="w25 setleft" >${entity.capitalOutflow}<%-- <fmt:formatNumber value="${entity.capitalOutflow}" pattern="#.####" minFractionDigits="4" ></fmt:formatNumber> --%></label> 
						<label class="w20"><font color=red>*</font>筹资性净流量（万元）:</label>
						
						<label class="w25 setleft" >${entity.capitalNetFlow}<%-- <fmt:formatNumber value="${entity.capitalNetFlow}" pattern="#.####" minFractionDigits="4" ></fmt:formatNumber> --%></label> 
					</div>
				<h3 class="data_title1">投资性现金流</h3>
					<div class="model_part">
						<label class="w20"><font color=red>*</font>投资性流入（万元）:</label>
						<label class="w25 setleft" >${entity.investmentInflow}<%-- <fmt:formatNumber value="${entity.investmentInflow}" pattern="#.####" minFractionDigits="4" ></fmt:formatNumber> --%></label> 
						<label class="w20"><font color=red>*</font>投资性流出 （万元）:</label>
						<label class="w25 setleft" >${entity.investmentOutflow}<%-- <fmt:formatNumber value="${entity.investmentOutflow}" pattern="#.####" minFractionDigits="4" ></fmt:formatNumber> --%></label> 
						<label class="w20"><font color=red>*</font>投资性净流量（万元）:</label>
						<label class="w25 setleft" >${entity.investmentNetFlow}<%-- <fmt:formatNumber value="${entity.investmentNetFlow}" pattern="#.####" minFractionDigits="4" ></fmt:formatNumber> --%></label> 
					</div>
				<h3 class="data_title1">现金流合计</h3>
					<div class="model_part">
						<label class="w20"><font color=red>*</font>现金流入（万元）:</label>
						<label class="w25 setleft" >${entity.cashInflow}<%-- <fmt:formatNumber value="${entity.cashInflow}" pattern="#.####" minFractionDigits="4" ></fmt:formatNumber> --%></label> 
						<label class="w20"><font color=red>*</font>现金流出（万元）:</label>
						<label class="w25 setleft" >${entity.cashOutflow}<%-- <fmt:formatNumber value="${entity.cashOutflow}" pattern="#.####" minFractionDigits="4" ></fmt:formatNumber> --%></label> 
						<label class="w20"><font color=red>*</font>现金净流量（万元）:</label>
						<label class="w25 setleft" >${entity.netCashFlow}<%-- <fmt:formatNumber value="${entity.netCashFlow}" pattern="#.####" minFractionDigits="4" ></fmt:formatNumber> --%></label> 
						<label class="w20"><font color=red>*</font>期初可用头寸（万元）:</label>
						<label class="w25 setleft" >${entity.initialAvailableCash}<%-- <fmt:formatNumber value="${entity.initialAvailableCash}" pattern="#.####" minFractionDigits="4" ></fmt:formatNumber> --%></label> 
						<label class="w20"><font color=red>*</font>期末可用头寸（万元）:</label>
						<label class="w25 setleft" >${entity.finalAvailableCash}<%-- <fmt:formatNumber value="${entity.finalAvailableCash}" pattern="#.####" minFractionDigits="4" ></fmt:formatNumber> --%></label> 
					</div>
					<div class="model_part">
						<label class="w20">备注:</label>
						<label class="w70 setleft" >${entity.remarks}</label> 
					</div>
				<h3 class="data_title1">筹资性流入明细列表</h3>
					<div class="model_part">
						<div class="tablebox">
							<table id="czlrTableId">
								<tr class="table_header">
									<th>序号</th>
									<th>承贷主体</th>
									<th>贷款银行</th>
									<th>贷款金额（万元）</th>
									<th>计划下账日期</th>
									<th>融资类型</th>
									<th>新增或续作</th>
									<th>下账币种</th>
									<th>综合成本（%）</th>
									<th>是否权益性融资</th>
								</tr>
							</table>
						</div>
						<div class="clearfix"> 
							<ul id="pageczlrTableId" class="pagination" style="line-height:34px">
									
							</ul>
						</div>
					</div>
					<h3 class="data_title1">筹资性流出明细列表</h3>
					<div class="model_part">
						<div class="tablebox">
							<table id="czlcTableId">
								<tr class="table_header">
									<th>序号</th>
									<th>操作单位</th>
									<th>还贷日期</th>
									<th>金融机构</th>
									<th>还贷类型</th>
									<th>还款金额(万元)</th>
									<th>是否续作</th>
									<th>还款币种</th>
									<th>保障还款项目</th>
									<th>是否有保障还款计划</th>
								</tr>
							</table>
						</div>
						<div class="clearfix"> 
							<ul id="pageczlcTableId" class="pagination" style="line-height:34px">
									
							</ul>
						</div>
					</div>
					
					<h3 class="data_title1">投资性流出明细列表</h3>
					<div class="model_part">
						<div class="tablebox">
							<table id="tzlcTableId">
								<tr class="table_header">
									<th>序号</th>
									<th>投资单位</th>
									<th>投资日期</th>
									<th>投资项目名称</th>
									<th>投资金额（万元）</th>
									<th>投资币种</th>
									<th>专项资金保障方案</th>
									<th>投资类型</th>
									<th>是否有保障还款计划</th>
								</tr>
							</table>
						</div>
						<div class="clearfix"> 
							<ul id="pagetzlcTableId" class="pagination" style="line-height:34px">
									
							</ul>
						</div>
					</div>
					<div class="model_part">
						<label class="w20" style="vertical-align: top;"><font color=red>*</font>审核意见:</label>
						<span class="w70"><textarea  rows="5" cols="40" name="examStr" id="examStr"></textarea>
							<span id="textareaLabel" style="color:red;display:block"></span>
						</span>
					</div>
					<div class="model_btn">
				    	<c:if test="${status==50113}">
						     <button class="btn btn-primary model_btn_ok" id="commit-1">通过</button>
						</c:if>
						<button class="btn btn-primary model_btn_ok" id="commit-2">退回</button>
						<button class="btn btn-default model model_btn_close">关闭</button>
					</div>
				</div>
		</form:form>
	</div>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/paging.js"/>"></script>
<script type="text/javascript">
		//意见审核
		$("#examStr").on('change',function(){
			$("#textareaLabel").text("");
		});
		$("#commit-1").click(function(){
			if($("#examStr").val()==""){
				$("#textareaLabel").text("审核意见不可为空");
				return false;
			}
			$.post("/shanghai-gzw/moneyFlowPlanWeek/commitexam",{entityid:$("#entityid").val(),examStr:$("#examStr").val(),examResult:50116},function(data){
	    		var commitresult=JSON.parse(data);
						if(commitresult.result==true)
							win.successAlert('操作成功！',function(){
									parent.location.reload(true);
									parent.mclose();
							});
						else
							win.errorAlert(commitresult.exceptionStr);
	 		 });
			 return false;
		})
		$("#commit-2").click(function(){
			if($("#examStr").val()==""){
				$("#textareaLabel").text("审核意见不可为空");
				return false;
			}
			$.post("/shanghai-gzw/moneyFlowPlanWeek/commitexam",{entityid:$("#entityid").val(),examStr:$("#examStr").val(),examResult:50117},function(data){
	    		var commitresult=JSON.parse(data);
						if(commitresult.result==true)
							win.successAlert('退回成功！',function(){
									parent.location.reload(true);
									parent.mclose();
							});
						else
							win.errorAlert(commitresult.exceptionStr);
	 		 });
	 		 return false;
		
		})
	
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
		
		//初始化详细数据
		function loadData(){
			var entityList = eval("(" + '${entityList}' + ")");
			if(entityList==undefined||entityList==null){
				return;
			}
			var dataMoneyFlowWeekCi = entityList.dataMoneyFlowWeekCi;
			for(var i = 0;i<dataMoneyFlowWeekCi.length;i++){
				addTableHtml(dataMoneyFlowWeekCi[i],"czlrTableId",(i+1));
			}
			//刷新页码
			pagequery(1,"czlrTableId");
			
			var dataMoneyFlowWeekCo = entityList.dataMoneyFlowWeekCo;
			for(var i = 0;i<dataMoneyFlowWeekCo.length;i++){
				addTableHtml(dataMoneyFlowWeekCo[i],"czlcTableId",(i+1));
			}
			pagequery(1,"czlcTableId");
			
			var dataMoneyFlowWeekIo = entityList.dataMoneyFlowWeekIo;
			for(var i = 0;i<dataMoneyFlowWeekIo.length;i++){
				addTableHtml(dataMoneyFlowWeekIo[i],"tzlcTableId",(i+1));
			}
			pagequery(1,"tzlcTableId");
		}
		
		//添加tableHtml
		function addTableHtml(data,tableId,num){
			var financingType = eval("(" + '${financingType}' + ")");
			var sequelNew = eval("(" + '${sequelNew}' + ")");
			var currencyKind = eval("(" + '${currencyKind}' + ")");
			var truefalse = eval("(" + '${trueOrFalse}' + ")");
			var investType = eval("(" + '${investType}' + ")");
			if(tableId=="czlrTableId"&&data!=null){
				var tr="<tr><td>"+num+"</td>";//序号
				tr+="<td>"+data.commitmentShallSubject+"</td>";//承贷主体
				tr+="<td>"+data.lendingBank+"</td>";//贷款银行
				tr+="<td>"+data.loanAmount+"";//贷款金额
				tr+="<td>"+data.placeOrderDate+"</td>";//计划下账日期
				tr+=loadSelect(data,"dataMoneyFlowWeekCiTemp.financingType",financingType);//融资类型
				tr+=loadSelect(data,"dataMoneyFlowWeekCiTemp.newOrRenewed",sequelNew);//新增或续作
				tr+=loadSelect(data,"dataMoneyFlowWeekCiTemp.placeOrderCurrency",currencyKind);//下账币种
				tr+="<td>"+data.compositeCost+"</td>";//综合成本
				tr+=loadSelect(data,"dataMoneyFlowWeekCiTemp.equityFinancing",truefalse);//是否权益性融
				$("#czlrTableId").append(tr+"</tr>");
			}else if(tableId=="czlcTableId"&&data!=null){
				var tr="<tr><td>"+num+"</td>";//序号
				tr+="<td>"+data.theLoanBusiness+"</td>";//操作单位/还贷企业
				tr+="<td>"+data.dateOfPayment+"</td>";//还贷日期
				tr+="<td>"+data.financingInstitution+"</td>";//金融机构
				tr+=loadSelect(data,"dataMoneyFlowWeekCoTemp.paymentType",financingType);//还贷类型
				tr+="<td>"+data.repaymentAmount+"</td>";//还款金额
				tr+=loadSelect(data,"dataMoneyFlowWeekCoTemp.newOrRenewedCo",sequelNew);//是否续作
				tr+=loadSelect(data,"dataMoneyFlowWeekCoTemp.paymentCurrency",currencyKind);//下账币种
				tr+="<td>"+data.guaranteeRepaymentProject+"</td>";//金融机构
				tr+=loadSelect(data,"dataMoneyFlowWeekCoTemp.guaranteeRepaymentPlan",truefalse);//是否有保障还款计划
				$("#czlcTableId").append(tr+"</tr>");
			}else if(tableId=="tzlcTableId"&&data!=null){
				var tr="<tr><td>"+num+"</td>";//序号
				tr+="<td>"+data.unitOfInvestment+"</td>";//投资单位
				tr+="<td>"+data.dateOfInvestment+"</td>";//投资日期
				tr+="<td>"+data.nameOfInvestmentProject+"</td>";//投资项目名称
				tr+="<td>"+data.investmentAmount+"</td>";//投资金额(万元)
				tr+=loadSelect(data,"dataMoneyFlowWeekIoTemp.theInvestmentCurrency",currencyKind);//投资币种
				tr+="<td>"+data.specialFundGuaranteeScheme+"</td>";//专项资金保障方案
				tr+=loadSelect(data,"dataMoneyFlowWeekIoTemp.investmentType",investType);//投资类型
				tr+=loadSelect(data,"dataMoneyFlowWeekIoTemp.guaranteeRepaymentPlan",truefalse);//是否有保障还款计划
				$("#tzlcTableId").append(tr+"</tr>");
			}
		}
		function loadSelect(data,option,map){
			var tr = "";
			tr+="<td>";
			option = option.split(".")[1];
			for(var i=0;i<map.length;i++){
				if(data!=null&&map[i].id==data[option]){
					tr+=map[i].description;
				}else{
					continue;
				}
			}
			tr+="</td>";
			return tr;
		}
	</script>
</html>