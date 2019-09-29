<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>修改整改问题进度</title>
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
		<span class="glyphicon glyphicon-pencil"></span>修改整改问题进度
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="entity">
		<div class="label_inpt_div">
		    <div class="model_part">
				<label class="w20"></span>存在问题:</label>
				<label class="w25 setleft">${ entity.problem}</label>
				</br>
				<label class="w20"></span>稽核建议:</label>
				<label class="w70 setleft">${ entity.changeContent}</label>
				</br>
				<label class="w20">是否整改:</label>
				<label class="w25 setleft">${ entity.isChange==0?"否":"是"}</label>
				</br>
				<label class="w20">整改负责人:</label>
				<label class="w25 setleft">${ entity.accountabilityPerson}</label>
				</br>
				<label class="w20">整改措施:</label>
				<label class="w70 setleft"> ${entity.changeMeasure}</label>
				</br>
				<label class="w20">整改时限:</label>
				<label class="w25 setleft">${entity.changeLastTime}</label>
				</br>
				<label class="w20">整改完成状态:</label>
				<label class="w25 setleft">${entity.isFinish == 1? "整改中":"整改完成"}</label>
				</br>
				<label class="w20">整改进度描述:</label>
				<label class="w70 setleft"> ${entity.changeProgress}</label>
				</br>  
	            <label class="w20">上传附件:</label>
				<c:if test="${!empty entity.fileOne.fileName}">
					<a href="${pageContext.request.contextPath}/common/download?_url=${entity.fileOne.filePath }&_name=${entity.fileOne.fileName }" data-original-title="" title="">${entity.fileOne.fileName }</a>
				</c:if>
				</br>
		    </div>
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
	function commit1(){
		
		if(!vaild.all()){
			return false;
		}
		
		var ids = new Array();
		var isPasses = new Array();
		$("[name=sel_check]").each(function(){
			ids.push($(this).attr("check_id"));
			isPasses.push($(this).val());
		});
		
		$.blockUI({ message: '提交中', css: { width: '275px' } });
		var content = $('#tex_content').val();
		var params = {'id': ${entity.id}}
	
		var url = "${pageContext.request.contextPath}/inspectproject/saveChangeManageEdit";
		$.post(url, params, function(data) {
			$.unblockUI();
			if(data.result==true){
				win.successAlert('保存成功！',function(){
					parent.mclose();
					parent.location.reload();	
			    });
			}else{
			    win.errorAlert(data.exceptionStr);
			}
		});
		return false;
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
	$("#btn_save").click(function(){
		commit1();
		return false;
	});
</script>
</html>