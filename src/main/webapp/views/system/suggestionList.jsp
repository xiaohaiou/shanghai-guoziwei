<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>意见反馈</title>
<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
<!-- 库|插件 -->
<!-- 新加样式 -->
<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
<link rel="stylesheet" href="<c:url value="/css/header.css"/>">
<style type="text/css">
body {
	padding: 0;
}

.body {
	padding: 1em 3em;
}

.tablebox table {
	table-layout: fixed;
	border-collapse: collapse;
}

.tablebox th:nth-child(3),.tablebox td:nth-child(3) {
	width: 50%;
	word-wrap: break-word;
	max-width: 50%;
	line-height: 2em;
}

#form2 {
	padding: 0 20px;
	margin-top: 0px;
}

.model_btn {
	bottom: 20px;
	position:static;
	right:auto;
	text-align:right;
	padding:0 5%;
}

textarea {
	display: block;
	margin: 0 auto;
	width: 90%;
	resize: none;
}

label {
	line-height: 3em;
	min-width:84px;
}

.model_body {
	height: 70%;
}
h4{
	font-size: 18px;
	line-height:1.1;
	margin: 8px;
}
input[type=file] {
    display:inline-block;
}
.div-line{
	border-top:1px solid rgb(169, 169, 169);
	margin:12px 5% 0px 5%;
}
.label_inpt_div>textarea{
    display: inline-block;
    vertical-align: text-top;
    margin-bottom: 10px;
}
</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
			var to_def_img = function(el) {
				//el.src = "<c:url value="/img/head.png"/>";
				/* el.src="http://file.hna.net/image/headimage/emp/1000040000/1000043612.jpg"; */
			};
		</script>
</head>
<body style="width:1888px;">
	<jsp:include page="/views/public/title.jsp"></jsp:include>
	<div class="body">
		<div class="table_content" style="margin: 10px 0 30px;padding-bottom: 20px;">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>填写反馈意见
			</h4>
			<form id="form2">
				<div class="label_inpt_div">
					<label class="w20"><span class="required"> * </span>意见描述:</label>
					<textarea class="w70" rows="4" name="description">${suggestion.description }</textarea>
					<br> <label class="w20">附件:</label> <input class="w25"
						type="file" id="file" name="file" />
				</div>
			</form>
			<div class="model_btn">
				<input type="hidden" name="id" value="${suggestion.id }" />
				<button class="btn btn-primary model_btn_ok" id="commit-1">提交</button>
			</div>
		</div>
		<div class="table_content">
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th style="width:5%">序号</th>
						<th style="width:10%">创建人</th>
						<th style="width:10%">创建时间</th>
						<th style="width:50%">描述</th>
						<th style="width:15%">附件</th>
					</tr>
					<c:if test="${!empty requestScope.msgPage.list }">
						<c:forEach items="${ requestScope.msgPage.list}" var="sys"
							varStatus="status">
							<tr>
								<td>${(msgPage.pageNum-1)*10+status.count}</td>
								<td>${sys.createName}</td>
								<td>${sys.createTime}</td>
								<td style="text-align:left;word-wrap:break-word;"><c:out value="${sys.description}"></c:out></td>
								<td><c:if test="${not empty sys.fileName}">
										<a href="javascript:;"
											onclick="downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(sys.filePath,'\\','\\\\')}&_name=${sys.fileName }')"><c:out value="${sys.fileName}"></c:out></a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.msgPage.list}">
						<tr>
							<td colspan="8" align="center">查询无记录</td>
						</tr>
					</c:if>
				</table>
				<jsp:include page="page.jsp" />
			</div>
		</div>
	</div>
	<jsp:include page="modal.jsp" />
</body>
<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script src="<c:url value="/js/validation.js"/>"></script>
<script type="text/javascript">
		// 清理空格
		var toArray = Array.from || function(arrayLike) {
			return [].slice.call(arrayLike);
		};
		toArray(document.body.querySelectorAll('[no~=text]')).forEach(
				function(el) {
					toArray(el.childNodes).forEach(function(e) {
						if (e.nodeType == 3) {
							el.removeChild(e);
						}
					});
				});
	
		// 调整头像大小
		var avatar_resize = function(el) {
			if (el.naturalWidth < el.naturalHeight) {
				el.style.width = "100%";
				el.style.height = "auto";
			}
		};
		toArray(document.body.querySelectorAll('.avatar>img')).forEach(function(el) {
			avatar_resize(el);
			el.onload = function() {
				avatar_resize(el);
				el.onload = undefined;
			};
		});
		function _query()
		{
			var form = document.forms[0];
			form.action = "${searchurl}";
			form.method = "post";
			form.submit();		
		}

		function del(id){
			win.confirm('确定要删除？',function(r){
				if(r){
					var url = "${pageContext.request.contextPath}/suggestion/_del?id=" + id;
					$.post(url, function(data) {
						if(data == 'success'){
							win.successAlert('删除成功！',function(){
								_query();
							 });
						}
					});
				}
			});
		}
		
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
			     	alert("下载失败！");
			     }  
			});
		}
		
		function checkcommit()
		{	
			var form = document.getElementById("form2");
			if(isEmpty(form.description.value)){
				win.infoAlert("意见描述不能为空！");
				return false;
			}
			if(form.description.value.length > 1000){
				win.infoAlert("意见描述不能超过1000个字符！");
				return false;
			}
			var tag = $("#file")[0];
			if(!fileChange(tag)){
				return false;
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
					//var entityBasicInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/suggestion/_save";
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
				     if(data=='success'){
						 win.successAlert('保存成功！',function(){
							 window.location.reload(true);
						 });
				     }else{
				    	 win.generalAlert("只能上传.pdf  .png  .jpeg  .docx .jpg .doc类型的文件!"); 
				     }
			     },  
			     error : function(data) {
			     	$.unblockUI();
			     }  
			}); 
				

			return false;
		})
		
		//验证文件类型和大小
		function fileChange(target){  
			//检测上传文件的类型 
			var imgName = document.all.file.value;
		    if(imgName != ''){
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
		    
		    }else{
		    		return true;
		    } 
		}     
	</script>
</html>