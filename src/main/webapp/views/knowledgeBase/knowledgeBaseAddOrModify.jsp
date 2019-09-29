<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>知识库新增、编辑页面</title>
	<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
	<link rel="stylesheet" href="<c:url value="/css/font/iconfont.css"/>" />
	<!-- 库|插件 -->
	<!-- 新加样式 -->
	<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/workbench_model.css"/>">
	<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
</head>
<body>
	<c:choose>
		<c:when test="${op eq 'modify'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>修改知识库
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增知识库
			</h4>
		</c:otherwise>
	</c:choose>
	<div class="model_body">
		<form:form id="form2" modelAttribute="knowledgeBase" method="post" enctype="multipart/form-data">
			<form:hidden path="id"/>
			<form:hidden path="createPersonName" />
			<form:hidden path="createPersonId" />
			<form:hidden path="createDate" />
			<form:hidden path="lastModifyPersonName" />
			<form:hidden path="lastModifyPersonId" />
			<form:hidden path="lastModifyDate" />
			<div class="label_inpt_div">
				<label class="w20"><span class="required"> * </span>标题:</label>
				<input class="w70" style="width:71%;" type="text" name="title" id="title" maxlength="50" placeholder="请输入标题,50字符以内" value="${knowledgeBase.title}"/><br>
				<label class="w20"><span class="required"> * </span>文号:</label>
				<input class="w70" style="width:71%;" type="text" name="no" id="no" maxlength="50" placeholder="请输入文号,50字符以内" value="${knowledgeBase.no}"/><br>
				<label class="w20"><span class="required"> * </span>类型:</label>
				<select name="typeId" id="typeId" class="selectpicker w25" >
				<option value="">全部</option>
					<c:forEach items="${requestScope.type}" var="result">
							<option value="${result.id}"  <c:if test="${knowledgeBase.typeId == result.id}">selected="selected"</c:if>>${result.description}</option>
						</c:forEach>
				</select>
				<label class="w20"><span class="required"> * </span>区域:</label>
				<select name="areaId" id="areaId" class="selectpicker w25" >
				<option value="">全部</option>
					<c:forEach items="${requestScope.area}" var="result">
							<option value="${result.id}"  <c:if test="${knowledgeBase.areaId == result.id}">selected="selected"</c:if>>${result.description}</option>
						</c:forEach>
				</select>
				<label class="w20"><span class="required"> * </span>时间:</label>
				<input type="text" name="doDate" id="doDate" value="${knowledgeBase.doDate}" placeholder="请输入时间" readonly="readonly" class="w25 time"/><br>
				<label class="w20"><span class="required"> * </span>附件上传:</label>
				<input class="w25" type="file" id="pjFile" name="pjFile" check="NotEmpty_._._._._._." />
				<span><a href="${pageContext.request.contextPath}/common/download?_url=${file_Path}&_name=${file_Name}">${file_Name==null ? "" :file_Name}</a></span>
			</div>
		</form:form>
	</div>
	<div class="row model_btn">
		<button class="btn btn-primary model_btn_ok" id="commit-1" >保存</button>
		<button class="btn btn-default model model_btn_close">关闭</button>
	</div>
	<jsp:include page="../system/modal.jsp"/>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>
<script type="text/javascript">
		$('input.time').jeDate({
			format:"YYYY-MM-DD"
		});
		function checkcommit() {
			if($("#title").val()=="") {
				win.errorAlert("标题不能为空");
				return false;
			}
			if($("#no").val()=="") {
				win.errorAlert("文号不能为空");
				return false;
			}
			if($("#typeId").val()=="") {
				win.errorAlert("类型不能为空");
				return false;
			}
			if($("#areaId").val()=="") {
				win.errorAlert("区域不能为空");
				return false;
			}
			if($("#doDate").val()=="") {
				win.errorAlert("时间不能为空");
				return false;
			}
			var tag = $("#pjFile")[0];
			if(fileChange(tag)){
				return true;
			}else{
				return false;
			}
			return true;
		};	
		$("#commit-1").click(function(){
			var op = '${op}';
			if(op == 'add'){
				if(!checkcommit() || !vaild.all()) {
					return false;
				}
			}
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			var formData = new FormData($("#form2")[0]);
			var url = "${pageContext.request.contextPath}/knowledgeBase/saveOrUpdate";
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
		});
		//关闭弹窗
		$(".model_btn_close").click(function () {
			parent.mclose();
			return false;
		});
		//验证文件类型和大小
		function fileChange(target){  
			//检测上传文件的类型 
			var imgName = document.all.pjFile.value;
		    var ext,idx;   
		    if (imgName == ''){  
	        	win.generalAlert("请选择需要上传的文件!");  
		        return false; 
		    }
		    //检测上传文件的大小        
		    var isIE = /msie/i.test(navigator.userAgent) && !window.opera;  
		    var fileSize = 0;           
		    if (isIE && !target.files){       
		        var filePath = target.value;       
		        var fileSystem = new ActiveXObject("Scripting.FileSystemObject");          
		        var file = fileSystem.GetFile (filePath);       
		        fileSize = file.Size;      
		    } else {      
		        fileSize = target.files[0].size;       
		    }     
		    var size = fileSize / 1024*1024;   
		    if(size>(1024*1024*30)){ 
		    	 win.generalAlert("上传文件不能大于31M!"); 
		      	return false; 
		    }
		    return true;
		};
	</script>
</html>