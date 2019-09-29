<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>跟踪上报查看</title>
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
</head>
<body>
	<h4>
		<span class="glyphicon glyphicon-pencil"></span>跟踪上报查看
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="entity">
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20">采取策略:</label> 
				<span class="w25">
					<select name="takestrategy" id="takestrategy" class="selectpicker" disabled="disabled">
						<c:forEach items="${requestScope.adoptStrategyList}" var="result">
							<option value="${result.id }" <c:if test="${track.takestrategy == result.id}">selected="selected"</c:if>>
								${result.adoptStrategy}</option>
						</c:forEach>
					</select> 
				</span></br>
				<label class="w20"><span class="required"> * </span>备注:</label>
				    <textarea class="w70" rows="5" cols="" id="takestrategyremark" name="takestrategyremark" style="height:5em;" maxlength="500" check="NotEmpty_string_._._._._." readonly="readonly">${track.takestrategyremark}</textarea>
			
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
				<label class="w75 setleft" style="word-wrap:break-word;">${sys.examinestr}</label> 
			</c:forEach>
			</div>
			</c:if>
			<div class="model_btn">
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
		</div>
		<jsp:include page="/views/system/modal.jsp" />
	</form:form>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>
<script type="text/javascript">

	function commit1(isSubmit){
		
		if(!vaild.all()){
			return false;
		}
		
		$.blockUI({ message: '提交中', css: { width: '275px' } });
		var text =$("#takestrategy").find("option:selected").text().trim();
		$("#takestrategytxt").val(text);
		var id = $("#id").val();
		var trackid = $("#trackid").val();
		var ts = $("#takestrategy").val();
		var tstxt = $("#takestrategytxt").val();
		var tsremark = $("#takestrategyremark").val();
		var params = {id: id,trackid:trackid, ts: ts, tstxt: tstxt,tsremark: tsremark,isSubmit: isSubmit,};
	
		var url = "${pageContext.request.contextPath}/bimr/riskEvent/savetrack";
		$.post(url, params, function(data) {
		
			$.unblockUI();
			if(data){
				win.successAlert('保存成功！',function(){
					parent.mclose();
					parent.location.reload();	
			    });
			}else{
			    win.errorAlert(data.exceptionStr);
			}  
		});
	}
	
	//关闭弹窗
	$(".model_btn_close").click(function() {
		parent.mclose();
		return false;
	});
	$(".icon-guanbi").click(function () {
		parent.mclose();
		return false;
	});
	

	
	$("#but_save").click(function(){
		commit1(false);
		return false;
	});
	
	$("#but_submit").click(function(){
		commit1(true);
		return false;
	});
	 
</script>
</html>