<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>审核存量负债数据</title>
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
		<span class="glyphicon glyphicon-pencil"></span>审核存量负债数据
		<i class="iconfont icon-guanbi"></i>
	</h4>

	<div class="label_inpt_div">
		<form:form id="form2" modelAttribute="entityview" >
			<form:hidden path="id"/>
			<div class="model_part">
				<label class="w20">单位名称:</label>
				<label class="w25 setleft">${ entityview.orgname}</label> 
				<label class="w20"></label>
				<label class="w25 setleft"></label> 
				<label class="w20">年份:</label>
				<label class="w25 setleft">${ entityview.year}</label>
				<label class="w20">月份:</label>
				<label class="w25 setleft">${ entityview.month}</label>
			</div>
			<h3 class="data_title1">境内人民币负债占比</h3>
			<div class="model_part">
				<label class="w20">境内存量负债(亿元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.domesticStockLiabilities}"  pattern="#,##0.0000" /></label> 
				<label class="w20">境内人民币负债(亿元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.domesticRmbLiabilities}"  pattern="#,##0.0000" /></label> 
				<label class="w20">境内人民币负债占比(%):</label>
				<label class="w25 setleft">${ entityview.proportionRmbLiabilities}</label>
				<label class="w20"></label>
				<label class="w25 setleft"></label>
			</div>
			<h3 class="data_title1">境外负债按币种分类明细</h3> 
			<div class="model_part">
				<div id="reportOverseasLiabilitiesDetailList">
				
				</div>
			</div>
			<h3 class="data_title1">境外负债占比</h3> 
			<div class="model_part">
				<label class="w20">境外存量负债(亿元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.overseasStockLiabilities}"  pattern="#,##0.0000" /></label> 
				<label class="w20">整体存量负债(亿元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.totalStockLiabilities}"  pattern="#,##0.0000" /></label> 
				<label class="w20">境外负债占比(%):</label>
				<label class="w25 setleft">${ entityview.proportionForeignLiabilities}</label> 
				<label class="w20"></label>
				<label class="w25 setleft"></label>
			</div>
			<h3 class="data_title1">整体存量负债按负债类型分类</h3>
			<div class="model_part">
				<label class="w20">银行贷款类存量负债(亿元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.bankLoanStockLiabilities}"  pattern="#,##0.0000" /></label>
				<label class="w20">信托类存量负债(亿元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.entrustStockLiabilities}"  pattern="#,##0.0000" /></label>
				<label class="w20">票据类存量负债(亿元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.billStockLiabilities}"  pattern="#,##0.0000" /></label>
				<label class="w20">基金类存量负债(亿元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.fundStockLiabilities}"  pattern="#,##0.0000" /></label>
				<label class="w20">债券类存量负债(亿元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.bondStockLiabilities}"  pattern="#,##0.0000" /></label>
				<label class="w20">融资租赁类存量负债(亿元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.financingRentStockLiabilities}"  pattern="#,##0.0000" /></label>
				<label class="w20">其它负债类存量负债(亿元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.othersStockLiabilities}"  pattern="#,##0.0000" /></label>
				<label class="w20"></label>
				<label class="w25 setleft"></label>
			</div>
			<h3 class="data_title1">存量负债成本</h3>
			<div class="model_part">
				<label class="w20">存量负债成本(%):</label>
				<label class="w25 setleft">${ entityview.costStockLiabilities}</label> 
				<label class="w20"></label>
				<label class="w25 setleft"></label>
			</div>
			<h3 class="data_title1">长期负债占比</h3>
			<div class="model_part">
				<label class="w20">长期负债(亿元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.longTermLiabilities}"  pattern="#,##0.0000" /></label> 
				<label class="w20">长期负债占比(%):</label>
				<label class="w25 setleft">${ entityview.proportionLongTermLiabilities}</label> 
				<input type="hidden" value="${entityview.id }" name="entityid" id="entityid">
			</div>
			<h3 class="data_title1">创建上报信息</h3>
			<div class="model_part">
				<label class="w20">数据创建人:</label>
				<label class="w25 setleft">${ entityview.createPersonName}</label>
				<label class="w20">创建时间:</label>
				<label class="w25 setleft">${ entityview.createDate}</label>
				<label class="w20">数据上报人:</label>
				<label class="w25 setleft">${ entityview.reportPersonName}</label>
				<label class="w20">上报时间:</label>
				<label class="w25 setleft">${ entityview.reportDate}</label>
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
			<div class="model_part">
				<label class="w20" style="vertical-align: top;"><span class="required"> * </span>审核意见:</label>
				<span class="w70 "><textarea  rows="5" cols="50" name="examStr" id="examStr"></textarea></span>
			</div>
			<div class="model_btn">
			    <c:if test="${entityview.status==50113}">
				     <button class="btn btn-primary model_btn_ok" id="commit-1">通过</button>
				</c:if>
				<button class="btn btn-primary model_btn_ok" id="commit-2">退回</button>
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
	initNodeList('reportOverseasLiabilitiesDetail',1,"",data1);//初始化境外负债按币种分类明细表

</script>
<script type="text/javascript">
		
		function check()
		{
			if($("#examStr").val().length>=600)
			{
				win.errorAlert("审核意见不能输入超过600字");
				return false;
			}
			if($("#examStr").val()=="")
			{
				win.errorAlert("请输入审核意见");
				return false;
			}
			return true;
		}
		
		
		
		$("#commit-1").click(function(){
			if(check())
			{
				$.post("/shanghai-gzw/reportStockLiabilities/commitexam",{entityid:$("#entityid").val(),examStr:$("#examStr").val(),examResult:50116},function(data){
	    		var commitresult=JSON.parse(data);
						if(commitresult.result==true)
							win.successAlert('保存成功！',function(){
									parent.location.reload(true);
									parent.mclose();
							});
						else
						{
							win.errorAlert(commitresult.exceptionStr,function(){parent.location.reload(true);});
							
						}
		 		 });
		 	}
			return false;
		})
		$("#commit-2").click(function(){
			if(check())
			{
				$.post("/shanghai-gzw/reportStockLiabilities/commitexam",{entityid:$("#entityid").val(),examStr:$("#examStr").val(),examResult:50117},function(data){
		    		var commitresult=JSON.parse(data);
							if(commitresult.result==true)
								win.successAlert('退回成功！',function(){
										parent.location.reload(true);
										parent.mclose();
								});
							else
							{
								win.errorAlert(commitresult.exceptionStr,function(){parent.location.reload(true);});
								
							}
		 		 });
		 	}
	 		 return false;
		
		})
	
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