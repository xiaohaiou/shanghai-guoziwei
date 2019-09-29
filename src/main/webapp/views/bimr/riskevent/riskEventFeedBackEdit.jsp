<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title> 编辑应对措施反馈</title>
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
		<span class="glyphicon glyphicon-pencil"></span>编辑应对措施反馈
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="entity">
		<div class="label_inpt_div">
			<div class="model_part">
				<input type="hidden" id="adoptstrategyid" name="adoptstrategyid" value="${entity.id}" >
				<label class="w20"><span class="required"> * </span>反馈时间:</label>
				<input class="w25 time" name="feedbacktime" id="feedbacktime" value="${entity.feedbacktime}"/>
			
				<div class="tablebox">
					<table id="personTab">
						<tr class="table_header">
							<th >风险应对措施</th>
							<th >预计措施达成时间</th>
							<th >责任部门</th>
							<th >目前应对措施落实完成情况</th>
						</tr>
								<tr>
									<td style="text-align: left;">
									  ${entity.adoptStrategyname }
										<input type="hidden" id="adoptStrategy"  name="adoptStrategy" value="${entity.adoptStrategy }" >
										<input type="hidden"  id="adoptStrategyname"  name="adoptStrategyname" value="${entity.adoptStrategyname }" >
									</td>
									<td style="text-align: left;">
										${entity.planfinishtime}
										<input type="hidden" id="planfinishtime"   name="planfinishtime" value="${entity.planfinishtime }" >
									</td>
									<td style="text-align: left;">
										${entity.responsibleCompName }
										<input type="hidden" id="responsibleCompName"   name="responsibleCompName" value="${entity.responsibleCompName }" >
									</td>	
									<td>
										<input id="nowfinishsituation"  name="nowfinishsituation" value="${entity.nowfinishsituation }" class="w25" style="width:100% !important; ">
									</td>
								</tr>
					</table>
				</div>
				
			
			</div>
			<div class="model_btn">
			    <button class="btn btn-default model model_btn_save" id="but_save">保存</button>
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
	$('input.time').jeDate(
			{
				format:"YYYY-MM-DD"
			}
	)
	function commit1(isSubmit){
		
		if(!vaild.all()){
			return false;
		}
		
		$.blockUI({ message: '提交中', css: { width: '275px' } });
		var adoptstrategyid = $("#adoptstrategyid").val();
		var formData = new FormData($("#form2")[0]);
		var url = "${pageContext.request.contextPath}/bimr/riskEvent/savefeedbackEdit?adoptstrategyid="+adoptstrategyid+"";
		$.ajax({  
		     url : url,  
		     type : "POST",  
		     data: formData,
	         async: false,  
	         cache: false,  
	         contentType: false,  
	         processData: false,  
		     success : function(data) {
		     	$.unblockUI();
		     	win.successAlert('保存成功！',function(){
					parent.mclose();
					parent.location.reload();	
			    });
		     },  
		     error : function(data) {
		     	$.unblockUI();
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