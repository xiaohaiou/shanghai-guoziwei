<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>员工问责新增、编辑及保存上报页面</title>
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
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">
	<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
</head>
<body>
	<c:choose>
		<c:when test="${op eq 'modify'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>修改员工问责信息
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增员工问责信息
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
	<form:form id="form2" modelAttribute="bimrDuty" method="post" enctype="multipart/form-data">
		<form:hidden path="id"/>
		<form:hidden path="createPersonName"/>
		<form:hidden path="createPersonId"/>
		<form:hidden path="createDate"/>
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20"><span class="required"> * </span>问责人员:</label>
				<input class="w25" type="text" name="person" id="person" placeholder="请输入问责人员姓名" maxlength="50" value="${bimrDuty.person}" check="NotEmpty_string_50_._._._."/><br>
				<label class="w20"><span class="required"> * </span>问责人员员工号:</label>
				<input class="w25" type="text" name="personId" id="personId" placeholder="请输入问责人员员工号" maxlength="50" value="${bimrDuty.personId}" check="NotEmpty_string_50_._._._."/><br>
				<label class="w20"><span class="required"> * </span>问责人员所在企业:</label>
				<input class="w25" type="text" name="personCompany" id="personCompany" placeholder="请输入问责人员所在企业" maxlength="50" value="${bimrDuty.personCompany}" check="NotEmpty_string_50_._._._."/><br>
				<label class="w20"><span class="required"> * </span>问责人员职位:</label>
				<input class="w25" type="text" name="personPost" id="personPost" placeholder="请输入问责人员职位" maxlength="50" value="${bimrDuty.personPost}" check="NotEmpty_string_50_._._._."/><br>
				<label class="w20"><span class="required"> * </span>发生时间:</label>
				<input type="text" name="happenDate" id="happenDate" value="${bimrDuty.happenDate}" readonly="readonly" class="w25 time"/><br>
				
				<label class="w20"><span class="required"> * </span>问责来源:</label>
				<select class="w25" name="source" id="source"  value="${bimrDuty.source}"> 
					<option value="1" <c:if test="${'1' eq bimrDuty.source}">selected</c:if> >风险事项报告</option>
					<option value="2" <c:if test="${'2' eq bimrDuty.source}">selected</c:if> >案件事项</option>
					<option value="3" <c:if test="${'3' eq bimrDuty.source}">selected</c:if> >合同事项</option>
					<option value="4" <c:if test="${'4' eq bimrDuty.source}">selected</c:if> >内部审计</option>
					<option value="5" <c:if test="${'5' eq bimrDuty.source}">selected</c:if> >合规调查</option>
					<option value="6" <c:if test="${'6' eq bimrDuty.source}">selected</c:if> >其它事项</option>
				</select><br>
				
				<label class="w20"><span class="required"> * </span>问责原因:</label>
				<textarea class="w70" rows="3" cols="5" type="text" name="reason" id="reason" placeholder="不超过500个字符" maxlength="500" check="NotEmpty_string_500_._._._." class="w25">${bimrDuty.reason}</textarea><br>
				
				<label class="w20"><span class="required"> * </span>建议问责落实情况:</label>
				<textarea class="w70" rows="3" cols="5" type="text" name="proposal" id="proposal" placeholder="不超过500个字符" maxlength="500" check="NotEmpty_string_500_._._._." class="w25">${bimrDuty.proposal}</textarea><br>
				
				<label class="w20"><span class="required"> * </span>问责结果:</label>
				<input class="w25" type="text" name="accountableResult" id="accountableResult" placeholder="请输入问责结果"  value="${bimrDuty.accountableResult}" check="NotEmpty"/><br>
				
				<label class="w20">处分问责呈报公文编号:</label>
				<input class="w25" type="text" name="bumfNum" id="bumfNum" placeholder="不超过50个字符" maxlength="50"  value="${bimrDuty.bumfNum}"/><br>
				
				<label class="w20">处分问责办文编号:</label>
				<input class="w25" type="text" name="articleNum" id="articleNum" placeholder="不超过50个字符" maxlength="50"  value="${bimrDuty.articleNum}"/><br>
								
				<label class="w20"><span class="required">  </span>附件:</label>
				<input class="w25" type="file" id="file" name="file" />
				<span><a href="${pageContext.request.contextPath}/common/download?_url=${file_Path}&_name=${file_Name}">${file_Name==null ? "" :file_Name}</a></span>
				
			</div>
			<div class="row model_btn">
				<button class="btn btn-primary model_btn_ok" id="commit-1" >保存</button>
				<button class="btn btn-primary model_btn_ok" id="commit-2" >保存并上报</button>
				<button class="btn btn-default model model_btn_close">取消</button>
			</div>
		</div>
	</form:form>
</body>
<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>
<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
<script type="text/javascript">	
		$('input.time').jeDate({
			format:"YYYY-MM-DD"
		});
		
		function checkcommit() {
			if($("#person").val()=="") {
				win.errorAlert("问责人员不能为空");
				return false;
			}
			if($("#happenDate").val()=="") {
				win.errorAlert("发生时间");
				return false;
			}
			if($("#source").val()=="") {
				win.errorAlert("问责来源不能为空");
				return false;
			}
			if($("#reason").val()=="") {
				win.errorAlert("问责原因不能为空");
				return false;
			}
			if($("#proposal").val()=="") {
				win.errorAlert("建议问责落实情况不能为空");
				return false;
			}
			/* if($("#bumfNum").val()=="") {
				win.errorAlert("处分问责呈报公文编号不能为空");
				return false;
			}
			if($("#articleNum").val()=="") {
				win.errorAlert("处分问责办文编号不能为空");
				return false;
			} */
			var file = $("#file")[0];
			if(fileChange(file)){
				return true;
			}else{
				return false;
			}
			return true;
		};
		
		
		$("#commit-1").click(function(){
			if(!checkcommit() || !vaild.all()) {
				return false;
			}
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			var formData = new FormData($("#form2")[0]);
			var url = "${pageContext.request.contextPath}/bimr/duty/saveOrUpdate";
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
		
		$("#commit-2").click(function(){
			if(!checkcommit()) {
				return false;
			}
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			var bimrDutyInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/bimr/duty/saveOrUpdateAndReported";
			$.post(url, bimrDutyInfo, function(data) {
				$.unblockUI();
				if(data == "success"){
					win.successAlert('保存成功！',function(){
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
			return false;
		});
		//关闭弹窗
		$(".model_btn_close").click(function () {
			parent.mclose();
			return false;
		});
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		$(document).ready(function() {
			var isDefault = $("#isDefault").val();
			if(isDefault == 1){
				$("#wyInfo").show();
			}else{
				$("#wyInfo").hide();
			}
		});
		//验证文件类型和大小
		function fileChange(target){ 
		    //检测上传文件的大小        
		    var isIE = /msie/i.test(navigator.userAgent) && !window.opera;  
		    var fileSize = 0;   
		    if(!target.files){
			    if (isIE){       
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
		    } 
		    return true;
		};
	</script>
</html>