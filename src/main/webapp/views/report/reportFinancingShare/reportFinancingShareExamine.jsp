<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>共享类融资项目进展数据</title>
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
<style type="text/css">
	.tablebox table tr:first-child {
	    background: rgba(0, 0, 0, 0);
	}
	.tablebox table tr:first-child:hover {
	    background: #f2f8ff;
	}
</style>
</head>
<body>
	<h4>
		<span class="glyphicon glyphicon-pencil"></span>审核共享类融资项目进展数据
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<div class="label_inpt_div">
		<form:form id="form2" modelAttribute="entityview" >
			<form:hidden path="id"/>
			<div class="model_part">
				<label class="w20">单位名称:</label>
				<label class="w25 setleft">${ entityview.coreOrgname}</label> 
				<label class="w20">类别:</label>
				<label class="w25 setleft">
					<c:forEach items="${requestScope.financingCategory}" var="result">
						<c:if test="${ sys.category==result.id}">
							${result.description }
						</c:if>
					</c:forEach>
					<%-- ${ entityview.categoryName} --%></label> 
				<label class="w20" style="vertical-align:top;">抵质押信息:</label>
				<label class="w75 setleft" style="word-wrap:break-word;">${ entityview.hypothecationInformation}</label>
				<label class="w20">模式:</label>
				<label class="w25 setleft">${ entityview.pattern}</label> 
				<label class="w20">金融机构:</label>
				<label class="w25 setleft">${ entityview.financialInstitution}</label> 
				<label class="w20">规模(亿元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.scale}"  pattern="#,##0.0000" /></label>
				<label class="w20">期限(月):</label>
				<label class="w25 setleft">${ entityview.term}</label>  
				<label class="w20">综合成本(%):</label>
				<label class="w25 setleft">${ entityview.comprehensiveFinancingCostRatio}</label> 
				<label class="w20"></label>
				<label class="w25 setleft"></label>
				<label class="w20">融资结构:</label>
				<label class="w75 setleft" style="word-wrap:break-word;">${ entityview.financingStructure}</label> 
				<label class="w20">用途介绍:</label>
				<label class="w75 setleft" style="word-wrap:break-word;">${entityview.purposeToIntroduce }</label>
				<label class="w20">具体进展:</label>
				<label class="w75 setleft" style="word-wrap:break-word;">${entityview.specificProgress }</label>
				<label class="w20">具体需求及建议:</label>
				<label class="w75 setleft" style="word-wrap:break-word;">${entityview.spSuggestions }</label>
				<label class="w20">已下账金额(亿元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.alreadyAccountAmounts}"  pattern="#,##0.0000" /></label> 
				<label class="w20">责任人:</label>
				<label class="w25 setleft">${ entityview.personLiable}</label>
				<label class="w20">经办人:</label>
				<label class="w25 setleft">${ entityview.operator}</label> 
				<label class="w20">经办人联系方式:</label>
				<label class="w25 setleft">${ entityview.operatorNum}</label>
				<input type="hidden" value="${entityview.id }" name="entityid" id="entityid">
			</div>
			<h3 class="data_title1">附件列表
			</h3>
			<div class="model_part">
				<div class="tablebox">
					<table id="enclosureTb">
						<thead>
							<tr class="table_header">
								<th>附件序号</th>
								<th>附件选择</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${not empty requestScope.enclosureList }">
								<c:forEach items="${requestScope.enclosureList }" var="node" varStatus="status">
									<tr>
										<td class="enclosure">附件${status.count }</td>
										<td>
											<a href="${pageContext.request.contextPath}/${node.enclosureAddress}" target="_blank">${node.enclosureName }</a>
										</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty requestScope.enclosureList}">
								<tr>
									<td class="no-enclosure" colspan="2" align="center">
										无附件
									</td>
								</tr>
							</c:if>
						</tbody>
					</table>
				</div>
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
				<button class="btn btn-primary model_btn_ok" id="commit-1">通过</button>
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
				$.post("/shanghai-gzw/reportFinancingShare/commitexam",{entityid:$("#entityid").val(),examStr:$("#examStr").val(),examResult:50116},function(data){
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
				$.post("/shanghai-gzw/reportFinancingShare/commitexam",{entityid:$("#entityid").val(),examStr:$("#examStr").val(),examResult:50117},function(data){
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