<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>节税任务查看页面</title>
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
<style>
	table tr td:first-child{
		width: 8em;
	}
	table{
	    table-layout: fixed;
	}
</style>
</head>
<body>
	<c:choose>
		<c:when test="${op eq 'report'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>上报节税任务数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>查看节税任务数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
	<div class="label_inpt_div">
		<form:form id="form2" modelAttribute="entityview" >
			<div class="model_part">
				<label class="w20">年份:</label>
				<label class="w25 setleft">${ entityview.year}</label> 
				<label class="w20">节税任务上报单位:</label>
				<label class="w25 setleft">${ entityview.orgName}</label> 
				<label class="w20">本年节税目标(万元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.taxTask}" pattern="#,##0.00" /></label>
				<label class="w20"></label>
				<label class="w25 setleft"></label>
				<label class="w20">税收筹划年度目标(万元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.taxPlan}" pattern="#,##0.00" /></label> 
				<label class="w20">优惠政策申请年度目标(万元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.favouredPolicy}" pattern="#,##0.00" /></label> 
				<label class="w20">税收返还年度目标(万元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.taxReturn}" pattern="#,##0.00" /></label> 
				<label class="w20">非税返还年度目标(万元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.inTaxReturn}" pattern="#,##0.00" /></label> 
			</div>
			<h3 class="data_title1">税收筹划目标月度分解明细(万元)</h3>
			<div class="model_part">
				<div class="tablebox">
					<table id="ttTableId">
						<tr class="table_header">
							<th>节税任务单位</th>
							<th>1月</th>
							<th>2月</th>
							<th>3月</th>
							<th>4月</th>
							<th>5月</th>
							<th>6月</th>
							<th>7月</th>
							<th>8月</th>
							<th>9月</th>
							<th>10月</th>
							<th>11月</th>
							<th>12月</th>
							<th>合计</th>
						</tr>
					</table>
				</div>
				<div class="clearfix"> 
					<ul id="pagettTableId" class="pagination" style="line-height:34px">
					</ul>
				</div>
			</div>
			<h3 class="data_title1">优惠政策申请目标月度分解明细(万元)</h3>
			<div class="model_part">
				<div class="tablebox">
					<table id="fpTableId">
						<tr class="table_header">
							<th>节税任务单位</th>
							<th>1月</th>
							<th>2月</th>
							<th>3月</th>
							<th>4月</th>
							<th>5月</th>
							<th>6月</th>
							<th>7月</th>
							<th>8月</th>
							<th>9月</th>
							<th>10月</th>
							<th>11月</th>
							<th>12月</th>
							<th>合计</th>
						</tr>
					</table>
				</div>
				<div class="clearfix"> 
					<ul id="pagefpTableId" class="pagination" style="line-height:34px">
					</ul>
				</div>
			</div>
			<h3 class="data_title1">税收返还目标月度分解(万元)</h3>
			<div class="model_part">
				<div class="tablebox">
					<table id="trTableId">
						<tr class="table_header">
							<th>节税任务单位</th>
							<th>1月</th>
							<th>2月</th>
							<th>3月</th>
							<th>4月</th>
							<th>5月</th>
							<th>6月</th>
							<th>7月</th>
							<th>8月</th>
							<th>9月</th>
							<th>10月</th>
							<th>11月</th>
							<th>12月</th>
							<th>合计</th>
						</tr>
					</table>
				</div>
				<div class="clearfix"> 
					<ul id="pagetrTableId" class="pagination" style="line-height:34px">
					</ul>
				</div>
			</div>
			<h3 class="data_title1">非税返还目标月度分解明细(万元)</h3>
			<div class="model_part">
				<div class="tablebox">
					<table id="itrTableId">
						<tr class="table_header">
							<th>节税任务单位</th>
							<th>1月</th>
							<th>2月</th>
							<th>3月</th>
							<th>4月</th>
							<th>5月</th>
							<th>6月</th>
							<th>7月</th>
							<th>8月</th>
							<th>9月</th>
							<th>10月</th>
							<th>11月</th>
							<th>12月</th>
							<th>合计</th>
						</tr>
					</table>
				</div>
				<div class="clearfix"> 
					<ul id="pageitrTableId" class="pagination" style="line-height:34px">
					</ul>
				</div>
			</div>
			<input type="hidden" value="${entityview.id }" name="entityid" id="entityid">
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
			<c:if test="${ !empty entityExamineviewlist }">
			<h3 class="data_title1">审核意见列表</h3>
			<c:forEach items="${ requestScope.entityExamineviewlist}" var="sys" varStatus="status">
				<div class="model_part">
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
				</div>
			</c:forEach>
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
<script type="text/javascript" src="<c:url value="/js/paging.js"/>"></script>
<script type="text/javascript">
		// 上报
		var id = '${entityview.id}';
		$("#commit-2").click(function(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			var url = "/shanghai-gzw/taxTask/report?id=" + id;
			$.post(url, function(data) {
				var commitresult=JSON.parse(data);
				$.unblockUI();
				if(commitresult.result==true){
					win.successAlert('上报成功！',function(){
						parent._query();
						parent.mclose();
					});
				}else{
					win.errorAlert(commitresult.exceptionStr);
				}
			});
			return false;
		});
		// 初始化明细数据
		function loadData(){
			var entityList = eval("(" + '${entityList}' + ")");
			if(entityList == undefined || entityList == null){
				return;
			}
			var datatt = entityList.dataTaxTaskDetailTaxPlans;
			for(var i = 0;i<datatt.length;i++){
				addTableHtml(datatt[i],"ttTableId",(i+1));
			}
			pagequery(1,"ttTableId");
			var datafp = entityList.dataTaxTaskDetailFavouredPolicies;
			for(var i = 0;i<datafp.length;i++){
				addTableHtml(datafp[i],"fpTableId",(i+1));
			}
			pagequery(1,"fpTableId");
			var datatr = entityList.dataTaxTaskDetailTaxReturns;
			for(var i = 0;i<datatr.length;i++){
				addTableHtml(datatr[i],"trTableId",(i+1));
			}
			pagequery(1,"trTableId");
			var dataitr = entityList.dataTaxTaskDetailInTaxReturns;
			for(var i = 0;i<dataitr.length;i++){
				addTableHtml(dataitr[i],"itrTableId",(i+1));
			}
			pagequery(1,"itrTableId");
		}
		loadData();
		//添加tableHtml
		function addTableHtml(data,tableId,num){
			if(data != null){
				var tr = '';
				tr +="<tr><td>"+data.orgName+"</td>";
				tr += "<td>"+fix(data.month1,2)+"</td>";
				tr += "<td>"+fix(data.month2,2)+"</td>";
				tr += "<td>"+fix(data.month3,2)+"</td>";
				tr += "<td>"+fix(data.month4,2)+"</td>";
				tr += "<td>"+fix(data.month5,2)+"</td>";
				tr += "<td>"+fix(data.month6,2)+"</td>";
				tr += "<td>"+fix(data.month7,2)+"</td>";
				tr += "<td>"+fix(data.month8,2)+"</td>";
				tr += "<td>"+fix(data.month9,2)+"</td>";
				tr += "<td>"+fix(data.month10,2)+"</td>";
				tr += "<td>"+fix(data.month11,2)+"</td>";
				tr += "<td>"+fix(data.month12,2)+"</td>";
				tr += "<td>"+fix(data.sum,2)+"</td>";
				$("#"+tableId).append(tr+"</tr>");
			}
		}
		//关闭弹窗
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		
		$(".model_btn_close").click(function () {
			parent.mclose();
			return false;
		});
		// 数字整型
		function fix(num,no){
			if (isNaN(num) || num == '' || num == null){
				return '-';
			} else {
				var sum = parseFloat(num);
				return sum.toFixed(no);
			}
		}
	</script>
</html>