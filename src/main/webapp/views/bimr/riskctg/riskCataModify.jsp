<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title> 修改风险目录</title>
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
		<span class="glyphicon glyphicon-pencil"></span>修改风险目录
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="entity">
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20">一级目录:</label> 
				<span class="w25">
					<select name="level1Id" id="sel_level1Id" class="selectpicker">
						<c:forEach items="${ctgs1}" var="result" varStatus="status">
							<option value="${result.id }" <c:if test="${status.index == 0}">selected="selected"</c:if>>${result.name}</option>
						</c:forEach>
					</select> 
				</span></br>
				
				<c:if test="${level == 2}">
				    <label class="w20"><span class="required"> *  </span>二级目录:</label>
				    <input class="w25" name="name" id="inp_name" value="${entity.leveltwoname }"  check="NotEmpty_string_._._._._."/></br>
				    <label class="w20"><span class="required"> * </span>二级目录定义:</label>
				    <textarea class="w70" rows="5" cols="" id="tex_define" name="define" style="height:5em;" maxlength="200" check="NotEmpty_string_._._._._."></textarea></br>
				</c:if>
				
				<c:if test="${level == 3 }">
					<label class="w20"><span class="required"> * </span>二级目录:</label> 
					<span class="w25">
						<select name="level2Id" id="sel_level2Id" class="selectpicker">
						    <option>选择</option>
						</select> 
					</span></br>
					<label class="w20"><span class="required"> * </span>三级目录:</label>
				    <input class="w25" name="name" id="inp_name" check="NotEmpty_string_._._._._."/></br>
				    <label class="w20"><span class="required"> * </span>三级目录定义:</label>
				    <textarea class="w70" rows="5" cols="" id="tex_define" name="define" style="height:5em;" maxlength="200" check="NotEmpty_string_._._._._."></textarea></br>
				</c:if>
			</div>
			<div class="model_btn">
			    <button class="btn btn-default model model_btn_save" id="but_save">保存</button>
			    <button class="btn btn-default model model_btn_save" id="but_save_submit">保存并上报</button>
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

	function commit1(isSubmitAudit){
		
		if(!vaild.all()){
			return false;
		}
		
		$.blockUI({ message: '提交中', css: { width: '275px' } });
		var parentId = $("#sel_level${level-1}Id").val();
		var name = $("#inp_name").val();
		var define = $("#tex_define").val();
		var level = ${level};
		var params = {parentId: parentId, name: name, define: define, isSubmitAudit: isSubmitAudit, level: level};
	
		var url = "${pageContext.request.contextPath}/bimr/riskCatalog/save";
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
	
	<c:if test="${level == 3}">
	function setLevel2(id){
		$.get('${mapurl}/getChildren', {'parentId': id}, function(data){
			$("#sel_level2Id option").remove();
			$.each(data.entity, function(i, o){
				$("#sel_level2Id").append("<option value='"+o.id+"'>"+o.name+"</option>");
			});
		});
	}
	
	$(function(){
		var v = $('#sel_level1Id').val();
		setLevel2(v);
	});
	
	$('#sel_level1Id').change(function(){
		var v = $(this).val();
		setLevel2(v);
		 
	});
	</c:if>
	
	$("#but_save").click(function(){
		commit1(false);
		return false;
	});
	
	$("#but_save_submit").click(function(){
		commit1(true);
		return false;
	});
	 
</script>
</html>