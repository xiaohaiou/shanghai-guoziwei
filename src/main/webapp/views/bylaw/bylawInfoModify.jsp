<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>规章制度新增编辑页面</title>
<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
<!-- 库|插件 -->
<!-- 新加样式 -->
<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
<link rel="stylesheet" href="<c:url value="/css/workbench_model.css"/>">
<script src="<c:url value="/js/jquery-1.8.3.min.js"/>" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">
		<style type="text/css">
			.selectone{
				white-space: nowrap;
				font-size: 14px;
				cursor: pointer;
			}
			.selectone:hover, .selectone.selectedOne{
				box-sizing: border-box;
				background-color: rgba(255,255,255,0.8);
				border-bottom:1px solid rgba(0,0,0,0.5);
			}
			.selectone>* {
				display:inline-block;
				min-width: 60px;
			}
			.selectedOne{
				border-color:#4a22cc;
				color: #4a22cc;
			}
			#com_ztree span {
				padding-left:0;
			}
		</style>
		<style>
			table{
				table-layout: fixed;
			}
			table td{
				line-height:2em;
				word-wrap:break-word;
			}
		</style>
</head>
<body>
	
	<h4>
		<span class="glyphicon glyphicon-pencil"></span>规章制度关联类别
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form id="form2">
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20">发文单位:</label>
				<label class="w25 setleft">${bylawInfo.orgNm}</label>
				<label class="w20">所属核心企业:</label>
				<label class="w25 setleft">${bylawInfo.coreOrgNm}</label>
				<label class="w20">规章制度标题:</label>
				<label class="w25 setleft">${bylawInfo.fileName}</label>
				<label class="w20"><span class="required"> * </span>体系类别:</label>
				<label class="w25 setleft">
					<c:forEach items="${bylawDepts}" var="dept">
						 <c:if test="${bylawInfo.deptId == dept.deptId }">${dept.deptNm}</c:if>
					</c:forEach>
				</label>
				<%-- <select name="deptId" class="w25" id="deptId" onchange="changeDept();">
					<c:forEach items="${bylawDepts}" var="dept">
						<option value="${dept.deptId}" <c:if test="${bylawInfo.deptId == dept.deptId }">selected="selected"</c:if>>${dept.deptNm}</option>
					</c:forEach>
				</select> --%>
				<label class="w20">是否涉及层级类别:</label>
				<select name="haveLevel" class="w25"  id="haveLevel" onchange="changeHaveLevel();">
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
				<label class="w20" id="fileLevelLable"><span class="required"> * </span>关联层级类别:</label>
				<select name="fileLevel" class="w25" id="fileLevel" onchange="changeFileLevel();">
					<option value="">请选择</option>
					<option value="1" <c:if test="${bylawInfo.fileLevel == 1 }">selected="selected"</c:if>>总则</option>
					<option value="2" <c:if test="${bylawInfo.fileLevel == 2 }">selected="selected"</c:if>>办法</option>
					<option value="3" <c:if test="${bylawInfo.fileLevel == 3 }">selected="selected"</c:if>>规定</option>
					<option value="4" <c:if test="${bylawInfo.fileLevel == 4 }">selected="selected"</c:if>>细则</option>
				</select>
				<label class="w20" id="parentIdLable"><span class="required"> * </span>上一级层级类别:</label>
				<select name="parentId" class="w25" id="parentId">
					
				</select>
				<label class="w20">标题:</label>
				<input type="text" name="bylawTitle" value="${bylawInfo.bylawTitle == null?bylawInfo.fileName:bylawInfo.bylawTitle}"/>
			</div>
			<div class="model_btn">
				<input type="hidden" name="id" value="${bylawInfo.id}"/>
				<button class="btn btn-primary model_btn_ok" id="commit-1">保存</button>
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
		</div>
	<form>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/vaild.js"/>"></script>
<script src="<c:url value="/js/validation.js"/>"></script>
<script type="text/javascript">
		//关闭弹窗
		$(".model_btn_close").click(function () {
	        window.parent.mclose();
			return false;
		});
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		function checkcommit()
		{	
			var form  = document.getElementById("form2");
			if(isEmpty(form.deptId.value)){
				form.deptId.focus();
				win.generalAlert("体系类别不能为空！");
				return false;
			}
			var haveLevel = $("#haveLevel").val();
			if(haveLevel != '0'){
				if(isEmpty(form.fileLevel.value)){
					form.fileLevel.focus();
					win.generalAlert("关联层级类别不能为空！");
					return false;
				}
				if(form.fileLevel.value != 1){
					if(isEmpty(form.parentId.value)){
						form.parentId.focus();
						win.generalAlert("上一级层级类别不能为空！");
						return false;
					}
				}
			}
			return true;
		}
		
		$("#commit-1").click(function(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!checkcommit())
			{
					$.unblockUI();
					return false;
			}
			var url = "${pageContext.request.contextPath}/bylaw/bylawInfoSave";
			var formData = new FormData($("#form2")[0]);
			console.log(formData);
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
			     	win.generalAlert("保存失败！");
			     	$.unblockUI();
			     }  
			}); 
			return false;
		})

</script>
<script type="text/javascript">
	/**
	** 是否涉及层级类别选择触发事件
	*/
	function changeHaveLevel(){
		var haveLevel = $("#haveLevel").val();
		if(haveLevel == '0'){
			$("#fileLevel").val('');
			$("#parentId").val('');
			$("#fileLevel").hide();
			$("#parentId").hide();
			$("#parentIdLable").hide();
			$("#fileLevelLable").hide();
		}else if(haveLevel == '1'){
			$("#fileLevel").val('${bylawInfo.fileLevel}');
			$("#parentId").val('${bylawInfo.parentId}');
			$("#fileLevel").show();
			$("#parentId").show();
			$("#parentIdLable").show();
			$("#fileLevelLable").show();
		}
		if($("#haveLevel").val() == 1){//总则
			$("#parentId").val('');
			$("#parentId").hide();
			$("#parentIdLable").hide();
		}else if(fileLevel != ''){
			$("#parentId").val('${bylawInfo.parentId}');
			getSuperLevelBylaw(fileLevel);
		}
	}
	
	//init
	function init(){
		
		if($("#haveLevel").val() == 0){
				$("#fileLevel").val('');
				$("#parentId").val('');
				$("#fileLevel").hide();
				$("#parentId").hide();
				$("#parentIdLable").hide();
				$("#fileLevelLable").hide();
			}
		if($("#fileLevel").val() == 1){//总则
			$("#parentId").val('');
			$("#parentId").hide();
			$("#parentIdLable").hide();
		}else {
			debugger;
			$("#parentId").show();
			$("#parentIdLable").show();
			if($("#fileLevel").val() != ''){
				getSuperLevelBylaw($("#fileLevel").val());
			}
		}
		
	}
	
	//初始页面加载
	init();
	
	/**
		体系类别变化
	*/
	function changeDept(){
		$("#parentId").val("");
		var fileLevel = $("#fileLevel").val();
		if(fileLevel != ''){
			getSuperLevelBylaw(fileLevel);
		}
	}
	
	/**
	*关联层级变化
	*/
	function changeFileLevel(){
		var fileLevel = $("#fileLevel").val();
		if(fileLevel == 1){//总则
			$("#parentId").val("");
			$("#parentId").hide();
			$("#parentIdLable").hide();
		}else{
			$("#parentId").val('${bylawInfo.parentId}');
			$("#parentId").show();
			$("#parentIdLable").show();
			if(fileLevel != ''){
				getSuperLevelBylaw(fileLevel);
			}
		}
	}
	
	
	/**
	 *获取上一层级的规章制度列表
	 */
	function getSuperLevelBylaw(fileLevel) {
		var deptId = $("#deptId").val();
		var id = '${bylawInfo.id}';
		var url = "${pageContext.request.contextPath}/bylaw/bylawInfos?fileLevel="
				+ fileLevel + "&org=${bylawInfo.org}&deptId=" + deptId;
		$.ajax({
				url : url,
				type : "GET",
				dataType : 'json',
				success : function(data) {
					var dataJson = eval(data);
					$("#parentId").empty();
					option = $("<option value=''>请选择</option>");
					$("#parentId").append(option);
					for ( var i = 0; i < dataJson.length; i++) {
						var entity = dataJson[i];
						var option;
						if (id != entity.id) {//不能选自己
							if ('${bylawInfo.parentId}' == entity.id) {
								option = $("<option value='"+entity.id+"' selected='seleted'>"
										+ entity.bylawTitle + "</option>");
							} else {
								option = $("<option value='"+entity.id+"'>"
										+ entity.bylawTitle + "</option>");
							}
							$("#parentId").append(option);
						}
					}
				}
			});
	}
</script>
</html>