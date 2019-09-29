<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title> 新增即时反馈</title>
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
		<span class="glyphicon glyphicon-pencil"></span>新增即时反馈
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="entity">
		<div class="label_inpt_div">
			<div class="model_part">
				<input type="hidden" id="id" name="id" value="${entity.id}" >
				<input type="hidden" id="feedbackid" name="feedbackid" value="${feedback.id}" >
				<label class="w20"><span class="required"> * </span>反馈时间:</label>
				
				<input class="w25" name="feedbacktime" id="feedbacktime" value="${feedback.feedbacktime}" check="NotEmpty" readonly="readonly"/>
			
				<label class="w20">目前具体情况：</label>
				<input type="text" name="nowdetailsituation" id="nowdetailsituation" value="${feedback.nowdetailsituation}" class="w25" check="NotEmpty"/>
				
				<label class="w20">风险征兆事件现状:</label> 
				<span class="w25">
					<select name="feedbackstatus" id="feedbackstatus" class="selectpicker">
						<option value="0" <c:if test="${feedback.status == 0}">selected="selected"</c:if>>好转改观</option>
						<option value="1" <c:if test="${feedback.status == 1}">selected="selected"</c:if>>保持关注</option>
						<option value="2" <c:if test="${feedback.status == 2}">selected="selected"</c:if>>恶化蔓延</option>
					</select> 
				</span></br>
				
				<c:if test="${entity.ishighunit == 0}">
						<label class="w20"><span class="required"> * </span>风险应对措施目前落实情况:</label>
						<textarea class="w70" rows="5" cols="" id="measuresituation" name="measuresituation" style="height:5em;" maxlength="500" check="NotEmpty_string_._._._._.">${feedback.measuresituation}</textarea>
					</c:if>	
				
			
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
		var feedbackid = $("#feedbackid").val();
		var formData = new FormData($("#form2")[0]);
		var url = "${pageContext.request.contextPath}/bimr/riskEvent/savefeedback?feedbackid="+feedbackid+"";
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
 $(function(){
    var now = new Date(); 
    var h = now.getFullYear(); 
    var m = (now.getMonth() + 1>9) ? now.getMonth() + 1 : "0"+(now.getMonth() + 1); 
    var s = (now.getDate()>9)  ? now.getDate()  : "0"+now.getDate(); 
    if(null==$("#feedbacktime").val()||''==$("#feedbacktime").val()){
    	$("#feedbacktime").val(h+"-"+m+"-"+s);
    }
})
</script>
</html>