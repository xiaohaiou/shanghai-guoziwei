<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>资金头寸审核页面</title>
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
				<span class="glyphicon glyphicon-pencil"></span>审核资金头寸数据
				<i class="iconfont icon-guanbi"></i>
			</h4>

	<div class="model_body">
		<form:form id="form2" modelAttribute="entity" >
			<form:hidden path="id" id="entityid"/>
			<div class="label_inpt_div"><div class="model_part">
				<label class="w20">时间:</label>
				<label class="w25 setleft" >${entity.date}</label> 
				<label class="w20">单位:</label>
				<label class="w25 setleft" >${entity.orgname}</label> 
				<label class="w20">当日余额（亿元）:</label>
				<label class="w25 setleft" >${entity.dailyBalance}</label> 
			</div>
			
			<h3 class="data_title1">境内头寸</h3>
				<div class="model_part">
					<label class="w20"><font color=red></font>境内头寸（亿元）:</label>
					<label class="w25 setleft" >${entity.dataFundPositionRmbs.cashR}</label> 
					<label class="w20"><font color=red></font>境内可用头寸（亿元）:</label>
					<label class="w25 setleft" >${entity.dataFundPositionRmbs.availableCashR}</label> 
					<label class="w20"><font color=red></font>境内不可用头寸（亿元）:</label>
					<label class="w25 setleft" >${entity.dataFundPositionRmbs.navailableCashR}</label> 
				</div>	
				<div class="model_part">	
					<label class="w20"><font color=red></font>境内财务公司头寸（亿元）:</label>
					<label class="w25 setleft" >${entity.dataFundPositionRmbs.fcCashR}</label> 
					<label class="w20"><font color=red>*</font>境内财务公司可用头寸（亿元）:</label>
					<label class="w25 setleft" >${entity.dataFundPositionRmbs.fcAvailableCashR}</label> 
					<label class="w20"><font color=red>*</font>境内财务公司不可用头寸（亿元）:</label>
					<label class="w25 setleft" >${entity.dataFundPositionRmbs.fcNavailableCashR}</label> 
					
					<label class="w20"><font color=red>*</font>境内银行可用头寸（亿元）:</label>
					<label class="w25 setleft" >${entity.dataFundPositionRmbs.BAvailableCashR}</label> 
					<label class="w20"><font color=red></font>境内银行不可用头寸（亿元）:</label>
					<label class="w25 setleft" >${entity.dataFundPositionRmbs.BNavailableCashR}</label> 
					<label class="w20"><font color=red>*</font>境内银行一类不可用头寸（亿元）:</label>
					<label class="w25 setleft" >${entity.dataFundPositionRmbs.BNavailableCashOneR}</label> 
					<label class="w20"><font color=red>*</font>境内银行二类不可用头寸（亿元）:</label>
					<label class="w25 setleft" >${entity.dataFundPositionRmbs.BNavailableCashTwoR}</label> 
					
				</div>
				<h3 class="data_title1">境外头寸币种明细</h3>
					<div class="model_part">
						<div class="tablebox">
							<table id="czTableId">
								<tr class="table_header">
									<th>序号</th>
									<th>境外头寸币种</th>
									<th>境外头寸（亿元）</th>
									<th>境外可用头寸（亿元）</th>
									<th>境外不可用头寸（亿元）</th>
									<th>境外财务公司头寸（亿元）</th>
									<th>境外财务公司可用头寸（亿元）</th>
									<th>境外财务公司不可用头寸（亿元）</th>
									<th>境外银行可用头寸（亿元）</th>
									<th>境外银行不可用头寸（亿元）</th>
									<th>境外银行一类不可用头寸（亿元）</th>
									<th>境外银行二类不可用头寸（亿元）</th>
								</tr>
							</table>
						</div>
						<div class="clearfix"> 
							<ul id="pageczTableId" class="pagination" style="line-height:34px">
									
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
			$.post("/shanghai-gzw/reportFundPosition/commitexam",{entityid:$("#entityid").val(),examStr:$("#examStr").val(),examResult:50116},function(data){
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
			$.post("/shanghai-gzw/reportFundPosition/commitexam",{entityid:$("#entityid").val(),examStr:$("#examStr").val(),examResult:50117},function(data){
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
		
		//分页
		paging(0,1);
		
		//添加tableHtml
		function addTableHtml(trNum,data){
			var tr="";
		    if(data!=null){
				tr+=loadCurrency(trNum,tr,data.currencyO);		//操作单位//境外头寸币种
				tr+="<td>"+f4(data.cashO)+"</td>";				//境外头寸（亿元）
				tr+="<td>"+f4(data.availableCashO)+"</td>";		//境外可用头寸（亿元）
				tr+="<td>"+f4(data.navailableCashO)+"</td>";		//境外不可用头寸（亿元）
				tr+="<td>"+f4(data.fcCashO)+"</td>";				//境外财务公司头寸（亿元）
				tr+="<td>"+f4(data.fcAvailableCashO)+"</td>";	//境外财务公司可用头寸（亿元）
				tr+="<td>"+f4(data.fcNavailableCashO)+"</td>";	//境外财务公司不可用头寸（亿元）
				tr+="<td>"+f4(data.bAvailableCashO)+"</td>";		//境外银行可用头寸（亿元）
				tr+="<td>"+f4(data.bNavailableCashO)+"</td>";	//境外银行不可用头寸（亿元）
				tr+="<td>"+f4(data.bNavailableCashOneO)+"</td>";	//境外银行一类不可用头寸（亿元）
				tr+="<td>"+f4(data.bNavailableCashTwoO)+"</td>";	//境外银行二类不可用头寸（亿元）
				$("#czTableId").append(tr);
			}else{
				tr+=loadCurrency(trNum,tr,null);//操作单位//境外头寸币种
				tr+="<td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td>";	
				$("#czTableId").append(tr);
			}
			
		}
		
		//序号，币种
		function loadCurrency(trNum,tr,currency){
			var currencyKind = eval("(" + '${currencyKind}' + ")");
			var currencyKindSize = currencyKind.length;
			tr+="<tr><td class='trNum'>"+trNum+"</td>";
			for(var i=0;i<currencyKindSize;i++){
				if(currencyKind[i].id==currency){
					tr+="<td>"+currencyKind[i].description+"</td>";
				}else{
					continue;
				}
			}
			return tr;
		}
		
		//加载数据
		//loadData();
		function loadData(){
			var entity = eval("(" + '${entityList}' + ")");
			if(entity==undefined||entity==null){
				return;
			}
			var data = {};
			if(entity.dataFundPositionOthers!==undefined){
				if(entity.dataFundPositionOthers.length<1){
					return;
				}
				 for(var i = 0;i<entity.dataFundPositionOthers.length;i++){
					addTableHtml(i+1,entity.dataFundPositionOthers[i]);
				} 	
			}
			pagequery(1,"czTableId");
			/* noData();
			var trNum = $("#czTableId tr").length-1;
			if(trNum%5==0){
				page(parseInt(trNum/5));
			}else{
				page(parseInt(trNum/5)+1);
			} */
		};
	</script>
</html>