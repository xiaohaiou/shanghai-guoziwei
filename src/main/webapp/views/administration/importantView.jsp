<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>企业财务数据查看及上报页面</title>
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
	<style type="text/css">
		.w75 {
			word-wrap: break-word;
		}
		.w25 {
			word-wrap: break-word;
		}
	</style>
</head>
<body>
	<c:choose>
		<c:when test="${op eq 'check'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>查看企业财务数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>上报企业财务数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
	
	<form:form id="form2" modelAttribute="theEntity" method="post">
		<input type="hidden" id="lastModifyDate" value="${lastModifyDate}"/>
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20">年份:</label>
				<label class="w25 setleft">${ theEntity.year}</label> 
				<label class="w20">月份:</label>
				<label class="w25 setleft">${ theEntity.month}</label>
				<label class="w20">企业财务数据单位:</label>
				<label class="w25 setleft">${ theEntity.importantCompName}</label>
				<label class="w20">企业财务数据所属企业:</label>
				<label class="w25 setleft">${ theEntity.coreCompName}</label>
				<label class="w20">企业财务数据类别:</label>
				<label class="w25 setleft">${ theEntity.importantTypeName}</label>
				<label class="w20">企业财务数据时间:</label>
				<label class="w25 setleft">${ theEntity.importantDate}</label>
				<label class="w20">企业财务数据核心企业时间:</label>
				<label class="w25 setleft">${ theEntity.reportedCoreDate}</label>
				<label class="w20">企业财务数据时间2:</label>
				<label class="w25 setleft">${ theEntity.reportedDepDate}</label>
				<label class="w20">企业财务数据时间3:</label>
				<label class="w25 setleft">${ theEntity.reportedGroupDate}</label>
				<label class="w20">企业财务数据标题:</label>
				<label class="w25 setleft">${ theEntity.importantTitle}</label>
			</div>
			<h3 class="data_title1">操作信息</h3>
			<div class="model_part">
				<label class="w20">创建人:</label>
				<label class="w25 setleft">${ theEntity.createPersonName}</label>
				<label class="w20">创建时间:</label>
				<label class="w25 setleft">${ theEntity.createDate}</label>
				<c:if test="${theEntity.status != 50112}">
					<label class="w20">上报人:</label>
					<label class="w25 setleft" >${theEntity.reportPersonName}</label> 
					<label class="w20">上报时间:</label>
					<label class="w25 setleft" >${theEntity.reportDate}</label> 
				</c:if>
			</div>
			<c:if test="${!empty requestScope.examineList}">
				<h3 class="data_title1">审核意见</h3>
				<c:forEach items="${requestScope.examineList }" var="entityExamineview">
					<div class="model_part">
						<label class="w20">审核人:</label>
						<label class="w25 setleft">${ entityExamineview.createPersonName}</label> 
						<label class="w20">审核时间:</label>
						<label class="w25 setleft">${ entityExamineview.createDate}</label> 
						<label class="w20">审核结果:</label>
						<label class="w25 setleft">${ entityExamineview.examresultName}</label> 
						<label class="w20"></label>
						<label class="w25 setleft"></label> 
						<label class="w20" style="vertical-align:top;">审核意见:</label>
						<label class="w75 setleft" style="word-wrap:break-word;">${ entityExamineview.examinestr}</label>
					</div>
				</c:forEach>
			</c:if>
			<div class="model_btn">
				<button class="btn btn-primary model_btn_ok" id="commit-3" <c:if test="${op ne 'reported'}">style="display:none"</c:if>>上报</button>
				<button class="btn btn-default model model_btn_close">关闭</button>
				
			</div>
		</div>
	</form:form>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript">
			
		function commit3(){
			exam(${theEntity.id});
			return false;
		}
		
		var lastModifyDate=$("#lastModifyDate").val();
		function exam(id){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			var url = "/shanghai-gzw/adImportant/reported?id=" + id +"&lastModifyDate=" + lastModifyDate;
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
		
		//关闭弹窗
		$(".model_btn_close").click(function () {
		　	parent.mclose();
			return false;
		});
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		
		$("#commit-3").click(function(){
			var id = ${theEntity.id};
			if(id != null && id != ""){
				$.post("${pageContext.request.contextPath}/adImportant/checkDelete?id=" + id, function(data) {
					var commitresult=JSON.parse(data);
					if(commitresult.result){
						commit3();
					}else{
						win.errorAlert('此数据已被删除！',function(){
							parent.location.reload();
							parent.mclose();
						});
					}
				});
				return false;
			}else {
				commit3();
				return false;
			}
		});
	</script>
</html>