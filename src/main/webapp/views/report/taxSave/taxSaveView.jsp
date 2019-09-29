<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>节税金额查看页面</title>
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
				<span class="glyphicon glyphicon-pencil"></span>上报节税金额数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>查看节税金额数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
	<div class="label_inpt_div">
		<form:form id="form2" modelAttribute="entityview" >
			<input type="hidden" value="${entityview.id }" name="id" id="entityid">
			<h3 class="data_title1">月度节税基本信息</h3>
			<div class="model_part">
				<label class="w20">单位名称:</label>
				<label class="w25 setleft">${ entityview.orgName}</label>
				<label class="w20"></label>
				<label class="w25 setleft"></label>
				<label class="w20">年月:</label>
				<label class="w25 setleft">${ entityview.year}</label>
				<label class="w20">备注:</label>
				<label class="w75 setleft">${ entityview.remark}</label> 
			</div>
			<h3 class="data_title1">月度节税明细数据</h3>
			<div class="model_part">
				<div class="tablebox" style="width:100%;overflow-x:auto;">
					<table id="ttTableId" style="width: 200%;">
						<tr class="table_header">
							<th>节税单位</th>
							<th>非税财政返还本期节税(万元)</th>
					<!-- 		<th>非税财政返还累计节税(万元)</th> -->
							<th>税财政返还本期节税(万元)</th>
						<!-- 	<th>税财政返还累计节税(万元)</th> -->
							<th>纳税筹划本期节税(万元)</th>
					<!-- 		<th>纳税筹划累计节税(万元)</th> -->
							<th>优惠政策及减免税申请本期节税(万元)</th>
						<!-- 	<th>优惠政策及减免税申请累计节税(万元)</th> -->
							<th>本期节税(万元)</th>
						<!-- 	<th>累计节税(万元)</th> -->
						</tr>
					</table>
				</div>
				<div class="clearfix"> 
					<ul id="pagettTableId" class="pagination" style="line-height:34px">
					</ul>
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
			<c:if test="${ !empty entityExamineviewlist }">
				<h3 class="data_title1">审核意见</h3>
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
			var url = "/shanghai-gzw/taxSave/report?id=" + id;
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
			var detial = entityList.dataTaxSaveDetails;
			for(var i = 0;i<detial.length;i++){
				addTableHtml(detial[i],"ttTableId",(i+1));
			}
			//刷新页码
			pagequery(1,"ttTableId");
		}
		loadData();
		//添加tableHtml
		function addTableHtml(data,tableId,num){
			if(data != null){
				var tr = '';
				tr +="<tr><td>"+data.orgName+"</td>";
				tr += "<td>"+fix(data.inTaxReturn,2)+"</td>";
			/* 	tr += "<td>"+fix(data.accInTaxReturn,2)+"</td>"; */
				tr += "<td>"+fix(data.taxReturn,2)+"</td>";
		/* 		tr += "<td>"+fix(data.accTaxReturn,2)+"</td>"; */
				tr += "<td>"+fix(data.taxPlan,2)+"</td>";
/* 				tr += "<td>"+fix(data.accTaxPlan,2)+"</td>"; */
				tr += "<td>"+fix(data.favouredPolicy,2)+"</td>";
			/* 	tr += "<td>"+fix(data.accFavouredPolicy,2)+"</td>"; */
				tr += "<td>"+fix(data.taxSave,2)+"</td>";
			/* 	tr += "<td>"+fix(data.accTaxSave,2)+"</td>"; */
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