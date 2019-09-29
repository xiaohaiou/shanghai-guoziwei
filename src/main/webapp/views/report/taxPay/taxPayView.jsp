<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>纳税数据查看页面</title>
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
	.tablebox{
	    width: 100%;
    	overflow-x: auto;
	}
	.tablebox table{
		width: 200%;
	}
</style>
</head>
<body>
	<c:choose>
		<c:when test="${op eq 'report'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>上报纳税数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>查看纳税数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
	<div class="label_inpt_div">
		<form:form id="form2" modelAttribute="entityview" >
			<div class="model_part">
				<label class="w20">纳税数据上报单位:</label>
				<label class="w25 setleft">${ entityview.orgName}</label>
				<label class="w20"></label>
				<label class="w25 setleft"></label>
				<label class="w20">年月:</label>
				<label class="w25 setleft">${ entityview.year}</label> 
				<label class="w20">本期实缴(万元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.nowPay}" pattern="#,##0.00" /></label>
				<%-- <label class="w20">本年累计实缴(万元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.accNowPay}" pattern="#,##0.00" /></label> --%>
				<label class="w20">去年同期实缴(万元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.prevPay}" pattern="#,##0.00" /></label>
				<%-- <label class="w20">去年同期累计实缴(万元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.accPrevPay}" pattern="#,##0.00" /></label> --%>
				<%-- <label class="w20">同期增减比(%):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.moM}" pattern="#,##0.00" /></label>
				<label class="w20">同期累计增减比(%):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.accMoM}" pattern="#,##0.00" /></label> --%>
				<label class="w20">其中：海南省本期纳税额(万元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.hnTax}" pattern="#,##0.00" /></label>
				<label class="w20">备注:</label>
				<label class="w25 setleft">${ entityview.remark}</label>
			</div>
			<h3 class="data_title1">本期实缴分税种明细(单位：万元)</h3>
			<div class="model_part">
				<div class="tablebox">
					<table id="nowTableId">
						<tr class="table_header">
							<th>纳税机构名称</th>
							<th>营业税</th>
							<th>增值税</th>
							<th>消费税</th>
							<th>城建税及教育费附加</th>
							<th>进口增值税</th>
							<th>进口关税</th>
							<th>企业所得税</th>
							<th>预提所得税</th>
							<th>个人所得税</th>
							<th>房产税</th>
							<th>契税</th>
							<th>土地使用税</th>
							<th>土地增值税</th>
							<th>印花税</th>
							<th>其他税费</th>
							<th>合计</th>
						</tr>
					</table>
				</div>
				<div class="clearfix"> 
					<ul id="pagenowTableId" class="pagination" style="line-height:34px">
					</ul>
				</div>
			</div>
 			<h3 class="data_title1">去年同期实缴分税种明细(单位：万元)</h3>
			<div class="model_part">
				<div class="tablebox">
					<table id="prevTableId">
						<tr class="table_header">
							<th>纳税机构名称</th>
							<th>营业税</th>
							<th>增值税</th>
							<th>消费税</th>
							<th>城建税及教育费附加</th>
							<th>进口增值税</th>
							<th>进口关税</th>
							<th>企业所得税</th>
							<th>预提所得税</th>
							<th>个人所得税</th>
							<th>房产税</th>
							<th>契税</th>
							<th>土地使用税</th>
							<th>土地增值税</th>
							<th>印花税</th>
							<th>其他税费</th>
							<th>合计</th>
						</tr>
					</table>
				</div>
				<div class="clearfix"> 
					<ul id="pageprevTableId" class="pagination" style="line-height:34px">
					</ul>
				</div>
			</div>
<!-- 			<h3 class="data_title1">本年累计实缴分税种明细(单位：万元)</h3>
			<div class="model_part">
				<div class="tablebox">
					<table id="accNowTableId">
						<tr class="table_header">
							<th>纳税机构名称</th>
							<th>营业税</th>
							<th>增值税</th>
							<th>消费税</th>
							<th>城建税及教育费附加</th>
							<th>进口增值税</th>
							<th>进口关税</th>
							<th>企业所得税</th>
							<th>预提所得税</th>
							<th>个人所得税</th>
							<th>房产税</th>
							<th>契税</th>
							<th>土地使用税</th>
							<th>土地增值税</th>
							<th>印花税</th>
							<th>其他税费</th>
							<th>合计</th>
						</tr>
					</table>
				</div>
				<div class="clearfix"> 
					<ul id="pageaccNowTableId" class="pagination" style="line-height:34px">
					</ul>
				</div>
			</div> -->
<!-- 			<h3 class="data_title1">去年同期累计实缴分税种明细(单位：万元)</h3>
			<div class="model_part">
				<div class="tablebox">
					<table id="accPrevTableId">
						<tr class="table_header">
							<th>纳税机构名称</th>
							<th>营业税</th>
							<th>增值税</th>
							<th>消费税</th>
							<th>城建税及教育费附加</th>
							<th>进口增值税</th>
							<th>进口关税</th>
							<th>企业所得税</th>
							<th>预提所得税</th>
							<th>个人所得税</th>
							<th>房产税</th>
							<th>契税</th>
							<th>土地使用税</th>
							<th>土地增值税</th>
							<th>印花税</th>
							<th>其他税费</th>
							<th>合计</th>
						</tr>
					</table>
				</div>
				<div class="clearfix"> 
					<ul id="pageaccPrevTableId" class="pagination" style="line-height:34px">
					</ul>
				</div>
			</div> -->
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
			var url = "/shanghai-gzw/taxPay/report?id=" + id;
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
			// 本期实缴
			var dataNow = entityList.dataTaxPayDetailNows;
			for(var i = 0;i<dataNow.length;i++){
				addTableHtml(dataNow[i],"nowTableId",(i+1));
			}
			pagequery(1,"nowTableId");
			// 去年同期实缴
			var dataPrev = entityList.dataTaxPayDetailPrevs;
			for(var i = 0;i<dataPrev.length;i++){
				addTableHtml(dataPrev[i],"prevTableId",(i+1));
			}
			pagequery(1,"prevTableId");
			// 本年累计实缴
			var dataAccNow = entityList.dataTaxPayDetailAccNows;
			for(var i = 0;i<dataAccNow.length;i++){
				addTableHtml(dataAccNow[i],"accNowTableId",(i+1));
			}
			pagequery(1,"accNowTableId");
			// 去年同期累计实缴
			var dataAccPrev = entityList.dataTaxPayDetailAccPrevs;
			for(var i = 0;i<dataAccPrev.length;i++){
				addTableHtml(dataAccPrev[i],"accPrevTableId",(i+1));
			}
			pagequery(1,"accPrevTableId");
		}
		loadData();
		//添加tableHtml
		function addTableHtml(data,tableId,num){
			if(data != null){
				var tr = '';
				tr +="<tr><td>"+data.orgName+"</td>";
				tr += "<td>"+fix(data.businessTax,2)+"</td>";
				tr += "<td>"+fix(data.valueAddedTax,2)+"</td>";
				tr += "<td>"+fix(data.consumptionTax,2)+"</td>";
				tr += "<td>"+fix(data.cesTax,2)+"</td>";
				tr += "<td>"+fix(data.importVat,2)+"</td>";
				tr += "<td>"+fix(data.tariff,2)+"</td>";
				tr += "<td>"+fix(data.eincomeTax,2)+"</td>";
				tr += "<td>"+fix(data.withholdTax,2)+"</td>";
				tr += "<td>"+fix(data.pincomeTax,2)+"</td>";
				tr += "<td>"+fix(data.housingTax,2)+"</td>";
				tr += "<td>"+fix(data.deedTax,2)+"</td>";
				tr += "<td>"+fix(data.landTax,2)+"</td>";
				tr += "<td>"+fix(data.landVat,2)+"</td>";
				tr += "<td>"+fix(data.stampTax,2)+"</td>";
				tr += "<td>"+fix(data.otherTax,2)+"</td>";
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