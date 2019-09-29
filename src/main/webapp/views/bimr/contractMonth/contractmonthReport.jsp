<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>上报检索信息</title>
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
<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/jquery.form.js"/>" charset="utf-8"></script>
</head>
<body>
		<div class="label_inpt_div">
			<!--合同记录 -->
			<jsp:include page="/views/bimr/contractMonthtemplet/contractMonth.jsp"/>
			<!--合同账单 -->
			<jsp:include page="/views/bimr/contractMonthtemplet/contractMonthList.jsp"/>
			
			<!-- 合同审核 --> 
			<jsp:include page="/views/bimr/contractMonthtemplet/contractMonthCheckList.jsp"/>
			
			<form:form id="form2" modelAttribute="entity" >
				<c:if test="${type=='examine' }">
					<div class="model_part">
						<label class="w20"><span class="required"> * </span>审核意见:</label>
						<textarea class="w70" rows="5" cols="" id="content" name="content" style="height:5em;" maxlength="500" check="NotEmpty_string_._._._._."></textarea>
						<input type="hidden" value="" id="inp_isPass" name="isPass">
					</div>
				</c:if>
				<div class="model_btn">
					<c:if test="${type=='examine' }">
						<button class="btn btn-primary model_btn_ok" id="but_auditPass" >通过</button>
						<button class="btn btn-primary model_btn_ok" id="but_auditRoll" >退回</button>
					</c:if>
					<c:if test="${type=='import' }">
						<button class="btn btn-primary model_btn_ok" id="but_submitAudit" >上报</button>
					</c:if>
					<input type="hidden" value="${entity.id }" id="id" name="id">
					<button class="btn btn-default model model_btn_close">关闭</button>
				</div>
			</form:form>
		</div>
		<jsp:include page="/views/system/modal.jsp"/>
	</body>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>
	<script type="text/javascript">
	$(function(){
		//关闭弹窗
		$(".model_btn_close").click(function () {
		　	parent.mclose();
			return false;
		});
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		
		function submitData(url,str){
			if(!vaild.all()){
				return false;
			}
			
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			var params = $('#form2').serialize();
			$.post(url, params ,function(data) {
				$.unblockUI();
				if(data.result){
					win.successAlert(str+'成功！',function(){
						parent.location.reload(true);
						parent.mclose();
						
					});
				}else{
					win.errorAlert(data.exceptionStr);
				}
			
			});
			return false;
		}
		
		$("#but_submitAudit").click(function(){
			var url = "${pageContext.request.contextPath}/bimr/contractmonth/report";
			submitData(url,"上报");
			return false;
		});
		
		$("#but_auditPass").click(function(){
			var url = "${pageContext.request.contextPath}/bimr/contractmonth/saveAudit";
			$('#inp_isPass').val('true');
			submitData(url,"审核");
			return false;
		});
		
		$("#but_auditRoll").click(function(){
			var url = "${pageContext.request.contextPath}/bimr/contractmonth/saveAudit";
			$('#inp_isPass').val('false');
			submitData(url,"审核");
			return false;
		});
	});
	</script>
</html>