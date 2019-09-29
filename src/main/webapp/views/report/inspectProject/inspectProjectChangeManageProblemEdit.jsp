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
	<form:form id="form2" modelAttribute="entity" enctype="multipart/form-data">
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20">存在问题:</label>
				<label class="w25 setleft">${ entity.problem}</label>
				</br>
				<label class="w20">稽核建议:</label>
				<label class="w70 setleft">${ entity.changeContent}</label>
				</br>
				<label class="w20">是否整改:</label>
				<label class="w25 setleft">${ entity.isChange==0?"否":"是"}</label>
				</br>
				<label class="w20">整改负责人:</label>
				<label class="w25 setleft">${ entity.accountabilityPerson}</label>
				</br>
				<label class="w20">整改措施:</label>
				<textarea class="w70" rows="5" id="changeMeasure" name="changeMeasure" style="height:5em;" maxlength="255">${entity.changeMeasure }</textarea>
				</br>
				<label class="w20">整改时限:</label>
				<input class="w25 time" name="changeLastTime" id="changeLastTime"  value="${entity.changeLastTime}" readonly="true"/>
				</br>
				<label class="w20">整改完成状态:</label>
				<span  class="w25"> 
					<select name="isFinish" id="isFinish" class="selectpicker">
						<option value="0" <c:if test = "${entity.isFinish == 0}">selected="selected"</c:if>>整改中</option>
						<option value="1" <c:if test = "${entity.isFinish == 1}">selected="selected"</c:if>>整改完成</option>
					</select>
				</span>
				</br>
				<label class="w20">整改进度描述:</label>
				<textarea class="w70" rows="5" id="changeProgress" name="changeProgress" style="height:5em;" maxlength="255">${entity.changeProgress }</textarea>    
				</br>  
	            <label class="w20">附件:</label>
				<input class="w25" type="file" id="file1" name="file1"  />
				<c:if test="${!empty entity.fileOne.fileName}">
					<div class="form-group">
						<label class="control-label col-md-2 " style="padding-top:0px;">已上传文件：</label>
						<div class="col-md-10 ">
							<a href="${pageContext.request.contextPath}/common/download?_url=${entity.fileOne.filePath }&_name=${entity.fileOne.fileName }"
								data-original-title="" title="">${entity.fileOne.fileName }
							</a>
						</div>
					</div>
				</c:if>
				</br>
			</div>
			<div class="model_btn">
			    <button class="btn btn-default model model_btn_ok" id="btn_save">保存</button>
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
		</div>
		<jsp:include page="/views/system/modal.jsp" />
		<input type="hidden" id="inp_id" name="id" value="${entity.id}"  />
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
		var formData = new FormData($("#form2")[0]);
		var url = "${pageContext.request.contextPath}/inspectproject/updateChangeManageProblem";
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
						parent.location.reload(true);
						parent.mclose();
				 });
		     },  
		     error : function(data) {
		     	$.unblockUI();
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