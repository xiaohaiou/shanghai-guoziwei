<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title></title>
	<!-- 库|插件 -->
	<link rel="stylesheet" href="<c:url value="/css/remodal.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/remodal-default-theme.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/selectize.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
	<!-- 页面 -->
	<link rel="stylesheet" href="<c:url value="/css/modal.css"/>">
	<!-- 库 -->
	<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/ajaxfileupload.js"/>"></script>
	<script src="<c:url value="/js/remodal.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/standalone-selectize.min.js"/>" charset="utf-8"></script><!-- 库 select-->
	<script src="<c:url value="/js/jquery.jedate.min.js"/>" charset="utf-8"></script><!-- 库 time-->
</head>
<body>
	<header no=text>
		<span class="title">公告通知</span>
		<a href="javascript:window.parent.mclose()" class="exit"><img src="../../css/icon/x.svg" alt=""></a>
	</header>
	<form id="form1" class="body form" action="index.html" method="post">
		<div class="form-group" no=text>
			<div class="title">公告主题：</div>
			<div class="input">
				<input type="text" id="title" name="title" maxlength="100" value="${portalMsg.title }" />
			</div>
		</div>
		<div class="form-group" no=text>
			<div class="title">发布时间：</div>
			<div class="input">
				<input class="time" readonly type="text" id="date" name="date" maxlength="100" value="${portalMsg.date }" >
			</div>
		</div>
		<%-- <div class="form-group" no=text>
			<div class="title">公告类别：</div>
			<div class="input">
				<select class="selectize" name="type">
					<option value="">请选择</option>
					<option value="1" <c:if test="${portalMsg.type == 1 }">selected = "selected"</c:if> >财务公告</option>
					<option value="2" <c:if test="${portalMsg.type == 2 }">selected = "selected"</c:if> >人事公告</option>
				</select>
			</div>
		</div> --%>
		<div class="form-group" no=text>
			<div class="title">公告内容：</div>
			<div class="input">
				<textarea name="description" id="description" rows="12" cols="80" maxlength="1000" >${portalMsg.description  }</textarea>
			</div>
		</div>
		<!-- <div class="form-group" no=text>
			<div class="title">任务附件：</div>
			<div class="input">
				<input type="file" class="file-input"  id="file1" name="file1" />
			</div>
		</div> -->
		<div class="form-group" no=text style="text-align:right;">
			<input type="hidden" name="id" value="${portalMsg.id}" />
			<%-- <input type="hidden" name="date" value="${portalMsg.date}" /> --%>
			<input type="hidden" name="type" value="${portalMsg.type}" />
			<input type="hidden" name="isIssue" value="${portalMsg.isIssue}" />
			<input type="hidden" name="delFlag" value="${portalMsg.delFlag}" />
			<input type="hidden" name="creator" value="${portalMsg.creator}" />
			<input type="hidden" name="createTime" value="${portalMsg.createTime}" />
			<div onclick="checkSubmit();" class="btn bg-red">公告发布</div>
		</div>
	</form>
	
	<!-- <div class="modal-container" data-remodal-id="modal" no=text>
		<iframe id="modal-frame" src="" style></iframe>
		<a href="javascript:mclose()" class="modal-close"><img src="../../css/icon/x.svg" alt=""></a>
	</div> -->
	
	<!-- 优先处理 -->
	<script type="text/javascript">
		// 清理空格
		var toArray = Array.from || function(arrayLike){
			return [].slice.call(arrayLike)
		};
		toArray(document.body.querySelectorAll('[no~=text]')).forEach(function(el) {
			toArray(el.childNodes).forEach(function(e) {
				if (e.nodeType == 3) {
					el.removeChild(e)
				}
			})
		})
		// resize img
		toArray(document.body.querySelectorAll('.avatar>img')).forEach(function(el) {
			if (el.naturalWidth < el.naturalHeight) {
				el.style.width = "100%";
				el.style.height = "auto";
			}
		})
	</script>
	
	<script>
		// modal
		window.modal_load = window.mload = function(url, callback) {
			if (url) {
				var ifm = document.getElementById('modal-frame');
					$('>.modal-close',ifm.parentNode).css('display','block')
				ifm.onload = function() {
					ifm.onload = undefined;
					$('>.modal-close',ifm.parentNode).css('display','')
					if (callback) {
						setTimeout(callback.bind(ifm.contentWindow))
					}
				};
				ifm.src = url;
				$('[data-remodal-id=modal]').remodal({
					closeOnEscape: false,
					closeOnOutsideClick: false,
				}).open();
			}
		}
		window.modal_close = window.mclose = function() {
			var ifm = $('[data-remodal-id=modal] iframe')[0];
			$('[data-remodal-id=modal]').remodal().close();
			ifm.src = '';
			ifm.style.height = '';
		}
		
		function checkSubmit() {
			var form = document.forms[0];
			if (form.title.value == '') {
				alert('公告主题不能为空！');
				return false;
			}
			if(form.title.value.length > 50){
				alert('公告主题最多填写50个字！');
				return false;
			}
			if (form.date.value == '') {
				alert('发布时间不能为空！');
				return false;
			}
			/* if (form.type.value == '') {
				alert('公告类别不能为空！');
				return false;
			} */
			if (form.description.value == '') {
				alert('公告内容不能为空！');
				return false;
			}
			if(form.description.value.length > 255){
				alert('公告内容最多填写255个字！');
				return false;
			}
			var formData = new FormData(form);
			var url = "${pageContext.request.contextPath}/portal/msg/_save1";
			$.ajax({  
			     url : url,  
			     type : "POST",  
			     data: formData,
		         async: false,  
		         cache: false,  
		         contentType: false,  
		         processData: false,  
			     success : function(data) {
			     	if(data.toString() == "success"){
				     	/* var pageNum = $("#pageNum", window.parent.document).val();
				     	var params = {};  
	    				params.pageNum = pageNum; 
				     	
					    $.ajax({  
					        async:false,  
					        type: "POST",  
					        url: "${pageContext.request.contextPath}/portal/msg/_list",
					        data:params,  
					        dataType:"json",  
					        success:function(data){ 
					            $("#msgUl", window.parent.document).empty().append(data);
					        },  
					        error:function(data){  
					        }  
					    }); 
						window.parent.circles_init();
						window.parent.mclose(); */
						window.parent.location.reload();
			     	}else{
			     		alert("公告发布失败！");
			     	}
			     },  
			     error : function(data) {
			     	alert("公告发布失败！");
			     }  
			}); 
			
			/* $.ajaxFileUpload({
				url: "${pageContext.request.contextPath}/task/instruction/_save", 
				type: 'post',
				data : $("#form1").serializeArray() ,
				async: false,
				secureuri: false, //一般设置为false
				fileElementId: ['file1'], // 上传文件的id、name属性名
				dataType: 'JSON', //返回值类型，一般设置为json、application/json 这里要用大写 不然会取不到返回的数据
				success: function(data){ 
					alert(1233444);
					window.parent.mclose();
					parent.window.location.reload();
				},
				error: function(data){ 
					alert(6787);
					window.parent.mclose();
					parent.window.location.reload();
				}
			}); */
		}
		
		// 初始化 selectize
		$('.selectize').selectize({});

		// 初始化 时间
		$('.input input.time').jeDate({
			format:"YYYY-MM-DD",
		})
	</script>
</body>
</html>