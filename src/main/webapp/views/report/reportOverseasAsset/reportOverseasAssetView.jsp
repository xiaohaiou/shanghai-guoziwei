<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>境外资产占比数据</title>
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
				<span class="glyphicon glyphicon-pencil"></span>上报境外资产占比数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>查看境外资产占比数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
		
	</c:choose>
<div class="label_inpt_div">
		<form:form id="form2" modelAttribute="entityview" >

				
				
				<div class="model_part">
				
				
				<label class="w20">年月:</label>
				<label class="w25 setleft">${ entityview.date}</label> 
				<label class="w20">单位名称:</label>
				<label class="w25 setleft">${ entityview.orgname}</label> 
				
				
				<label class="w20">境外资产(亿元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.outasset}"  pattern="#,##0.0000" /></label> 
			
				<label class="w20">境内境外总资产(亿元):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ entityview.insideasset}"  pattern="#,##0.0000" /></label> 
				<label class="w20">境外资产占比(%):</label>
				<label class="w25 setleft"><fmt:formatNumber value="${ 100*entityview.outasset/entityview.insideasset}"  pattern="#,##0.00" /></label> 
				
		
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
			
		
			var url = "/shanghai-gzw/reportOverseasAsset/saveandreport?id=" + id;
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