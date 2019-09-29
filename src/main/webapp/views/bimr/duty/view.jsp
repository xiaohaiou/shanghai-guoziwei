<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>员工问责查看及上报页面</title>
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
				<span class="glyphicon glyphicon-pencil"></span>查看员工问责信息
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>上报员工问责信息
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
	<form:form id="form2" modelAttribute="bimrDuty" method="post">
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20">问责人员:</label>
				<label class="w25 setleft">${ bimrDuty.person}</label><br>
				
				<label class="w20">问责人员所在企业:</label>
				<label class="w25 setleft">${ bimrDuty.personCompany}</label><br>
				
				<label class="w20">问责人员职位:</label>
				<label class="w25 setleft">${ bimrDuty.personPost}</label><br>
				
				<label class="w20">发生时间:</label>
				<label class="w25 setleft">${ bimrDuty.happenDate}</label><br>
				
				<label class="w20">问责来源:</label>
				<label class="w25 setleft">
					<c:if test="${bimrDuty.source eq 1}">风险事项报告</c:if>
					<c:if test="${bimrDuty.source eq 2}">案件事项</c:if>
					<c:if test="${bimrDuty.source eq 3}">合同事项</c:if>
					<c:if test="${bimrDuty.source eq 4}">审计事项</c:if>
					<c:if test="${bimrDuty.source eq 5}">合规事项</c:if>
					<c:if test="${bimrDuty.source eq 6}">其它事项</c:if>
				</label><br>
				
				<label class="w20">问责原因:</label>
				<label class="w25 setleft" style="word-wrap:break-word;word-break:break-all;">${ bimrDuty.reason}</label><br>
				
				<label class="w20">建议问责落实情况:</label>
				<label class="w25 setleft" style="word-wrap:break-word;word-break:break-all;">${ bimrDuty.proposal}</label><br>
				
				<label class="w20">处分问责呈报公文编号:</label>
				<label class="w25 setleft">${ bimrDuty.bumfNum}</label><br>
				
				<label class="w20">处分问责办文编号:</label>
				<label class="w25 setleft">${ bimrDuty.articleNum}</label><br>
				
				<label class="w20">问责结果:</label>
				<label class="w25 setleft">${ bimrDuty.accountableResult}</label><br>
				
				<label class="w20">附件:</label>
				<label class="w25 setleft"><a href="${pageContext.request.contextPath}/common/download?_url=${file_Path}&_name=${file_Name}">${file_Name==null ? "" :file_Name}</a></label><br>
				
			</div>
			<h3 class="data_title1">创建上报信息</h3>
			<div class="model_part">
				<label class="w20">数据创建人:</label>
				<label class="w25 setleft">${ bimrDuty.createPersonName}</label>
				<label class="w20">创建时间:</label>
				<label class="w25 setleft">${ bimrDuty.createDate}</label><br>
				<c:if test="${bimrDuty.status != 50112}">
					<label class="w20">数据上报人:</label>
					<label class="w25 setleft">${ bimrDuty.reportPersonName}</label>
					<label class="w20">上报时间:</label>
					<label class="w25 setleft">${ bimrDuty.reportDate}</label>
				</c:if>
			</div>
			<c:if test="${!empty requestScope.examineList}">
				<h3 class="data_title1">审核意见列表</h3>
				<c:forEach items="${requestScope.examineList }" var="entityExamineview">
					<div class="model_part">
						<label class="w20">审核人:</label>
						<label class="w25 setleft">${ entityExamineview.createPersonName}</label> 
						<label class="w20">审核时间:</label>
						<label class="w25 setleft">${ entityExamineview.createDate}</label> 
						<label class="w20">审核结果:</label>
						<label class="w25 setleft">${ entityExamineview.examresultName}</label><br> 
						<label class="w20">审核意见:</label>
						<label class="w75 setleft" style="word-wrap:break-word;">${ entityExamineview.examinestr}</label> 
					</div>
				</c:forEach>
			</c:if>
			<div class="row model_btn">
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
		$("#commit-3").click(function(){
			exam(${bimrDuty.id});
			return false;
		});
		function exam(id){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			var url = "/shanghai-gzw/bimr/duty/reported?id=" + id;
			$.post(url, function(data) {
				$.unblockUI();
				if(data == "success"){
					win.successAlert('上报成功！',function(){
						parent.location.reload();
						parent.mclose();
					});
				}else if(data == "false"){
					win.successAlert('该数据已经被上报，不能再执行当前操作',function(){
						parent.location.reload();
						parent.mclose();
					});
				}	
			});
		};
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