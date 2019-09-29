<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>项目办结申请</title>
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
	<style type="text/css">
		#com_ztree span {
			padding-left:0;
		}
	</style>
</head>
<body>
	<h4>
		<span class="glyphicon glyphicon-pencil"></span>项目办结申请
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2" modelAttribute="auditProject" method="post" enctype="multipart/form-data">
		<form:hidden path="id" value=""/>
		<div class="label_inpt_div">
			<div class="model_part">
				<input type="hidden" name="projectId" id="projectName" value="${auditProject.id}">
				
				<label class="w20"><span class="required"> * </span>审计报告及附件:</label>
				<button class="" id="selectFile" style="margin-bottom: 10px;">选择文件</button>
				<div id="files" style="display:none;"></div>
				<%-- <input class="w25" type="file" id="sjFile" name="sjFile" multiple="multiple"/>--%>
				<input class="w25" type="hidden" id="sjFileId" name="sjFileId" value="${auditProject.sjFileId}"/>
				<div style="float: right;width: 50%;">
				<c:if test="${not empty auditProject.sjFileId}">
					<c:if test="${not empty sjFiles}">
						<c:forEach items="${sjFiles}" var="sjFile" varStatus="index">
						<span id="${index.index+1}" >${index.index+1}.	<a href="javascript:void(0)" onclick="downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(sjFile.filePath,"\\","\\\\")}&_name=${sjFile.fileName}')" target="_blank">${sjFile.fileName}</a> 
						<i class=del style="cursor: pointer;" onclick="delfile(${index.index+1},'${sjFile.fileUuid}')">&times;</i></span>
						</c:forEach>
						
					</c:if>
				</c:if>
				</div>
				<br>
				
				<label class="w20">报批公文号:</label>
				<input type="text" name="docNum" id="docNum" value="${auditProject.docNum}" check="Empty_string_200_._._._.">
				<br>
				<label class="w20">邮件审核截图:</label>
				<input class="w25" type="file" id="mailFile" name="mailFile"/>
				<input class="w25" type="hidden" id="mailFileId" name="mailFileId" value="${auditProject.mailFileId}"/>
				<c:if test="${not empty auditProject.mailFileId}">
					<c:if test="${not empty mailFile}">
						<a href="javascript:void(0)" onclick="downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(mailFile.filePath,"\\","\\\\")}&_name=${mailFile.fileName}')" target="_blank">${mailFile.fileName}</a>
					</c:if>
				</c:if>
				<br>
			</div>
			<div class="row model_btn">
				<button class="btn btn-primary model_btn_ok" id="save" >保存</button>
				<button class="btn btn-primary model_btn_ok" id="commit" >提交办结</button>
				<button class="btn btn-default model model_btn_close">取消</button>
			</div>
		</div>
	</form:form>
	<jsp:include page="../../system/modal.jsp"/>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>
<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
<script type="text/javascript">
		//关闭弹窗
		$(".model_btn_close").click(function () {
			parent.mclose();
			return false;
		});
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});

		$("#save").click(function(){
		    if(!vaild.all()) {
				return false;
			}
			if($("#file-1").val() == ""||$("#file-1").val() == null||$("#file-1").val() == undefined){
			if($("#sjFileId").val()==null||$("#sjFileId").val()==""){
				win.errorAlert("审计附件不能为空！");
					return false;
				}
			}
			if(document.getElementById("mailFile").value == "" && document.getElementById("docNum").value == ""){
				if(document.getElementById("mailFileId").value == ""){
					win.errorAlert("报批公文号与邮件截图必须填写一样！");
					return false;
				}
			}
            var question = new FormData($("#form2")[0]);
			var url = "${pageContext.request.contextPath}/bimr/inside/saveCloseApply";
            $.ajax({
                url : url,
                type : "POST",
                data: question,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success : function(data) {
                    var r = data.result;
                    if (r == 1){
                        win.successAlert(data.message,function(){
                            parent.location.reload(true);
                            parent.mclose();
                        });
					}else{
                        win.errorAlert(data.message);
					}
                }
            });
			return false;
		});

		$("#commit").click(function(){
		    if(!vaild.all()) {
				return false;
			}
				if($("#file-1").val() == ""||$("#file-1").val() == null||$("#file-1").val() == undefined){
			if($("#sjFileId").val()==null||$("#sjFileId").val()==""){
				win.errorAlert("审计附件不能为空！");
					return false;
				}
			}
			if(document.getElementById("mailFile").value == "" && document.getElementById("docNum").value == ""){
				if(document.getElementById("mailFileId").value == ""){
					win.errorAlert("报批公文号与邮件截图必须填写一样！");
					return false;
				}
			}
            var question = new FormData($("#form2")[0]);
			var url = "${pageContext.request.contextPath}/bimr/inside/submitCloseApply";
            $.ajax({
                url : url,
                type : "POST",
                data: question,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success : function(data) {
                    var r = data.result;
                    if (r == 1){
                        win.successAlert(data.message,function(){
                            parent.location.reload(true);
                            parent.mclose();
                        });
					}else{
                        win.errorAlert(data.message);
					}
                }
            });
			return false;
		});
		function downloadFile(url){
			$.ajax({  
			     url : url,  
			     type : "POST",  
		         success : function(data) {
					var iframe = document.createElement("iframe");  
					iframe.src = url;  
					iframe.style.display = "none";  
					document.body.appendChild(iframe);
			     },  
			     error : function(data) {  
			     	win.errorAlert("下载失败！");
			     }  
			});
		}
		function delfile(e,f){
			var id= $("#projectName").val();
			
			var url = "${pageContext.request.contextPath}/bimr/inside/delfile?id="+id+"&uuid="+f;
			 $.ajax({
                url : url,
                type : "POST",
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success : function(data) {
                    var r = data.result;
                    if (r == 1){
                        win.successAlert(data.message,function(){
                        //$("#"+e).empty();
                           location.reload(true);
                           // parent.mclose();
                           return;
                        });
					}else{
                        win.errorAlert(data.message);
                        return;
					}
                }
            });
			
			
		}
		(function() {
					var count = 0;
					 var clicked = function() {
						var files = $(this).closest('#files')
						$(this).closest('label').remove()
						if (files.find('label').length == 0) {
							files.css('display', 'none');
						}
						return false;
					}
					var changed = function() {
						if (this.value) {
							$(this).closest('label')
								.removeClass('hidden')
								.find('span')
								.text(this.value.substring(this.value.replace(/\\/g, '/').lastIndexOf('/') + 1));
						}
						$('.file.hidden').remove();
					}
					$("#files").on('click', 'label.file i.del', clicked)
						.on('change', 'label.file input[type=file]', changed)
						.on('blur', 'label.file input[type=file]', changed)
				    $("#selectFile").click(function(){
						var files = $(this).parent().find('#files')	
						files.css('display', 'block')
						var id = 'file-' + (++count)
						var el = $('<label class="file hidden"><input class="file" style="display : none;" type="file" name="sjFile" id="' + id +
								'" value=""/><span></span><i class=del>&times;</i></label>')
							.appendTo(files)
							.trigger('click')
							return false;
					});
				})();
				
	</script>
</html>