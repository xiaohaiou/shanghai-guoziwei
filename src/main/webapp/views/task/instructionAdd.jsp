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
		<span class="title">指令下达 </span>
		<a href="javascript:window.parent.mclose()" class="exit"><img src="../../css/icon/x.svg" alt=""></a>
	</header>
	<form id="form1" class="body form" action="index.html" method="post">
		<div class="form-group" no=text>
			<div class="title">指令名称：</div>
			<div class="input">
				<input type="text" id="taskName" name="taskName" maxlength="100" value="${instruction.taskName }" />
			</div>
		</div>
		<div class="form-group" no=text>
			<div class="title">截止日期：</div>
			<div class="input">
				<input class="time" readonly type="text" id="deadLine" name="deadLine" maxlength="100" value="${instruction.deadLine }" >
			</div>
		</div>
		<%-- <div class="form-group" no=text>
			<div class="title">任务类别：</div>
			<div class="input">
				<select class="selectize" name="taskType">
					<option value="">请选择</option>
					<c:forEach items="${taskTypeList}" var="taskType">
						<option value="${taskType.id}" <c:if test="${instruction.taskType == taskType.id}"> selected = "selected" </c:if>><c:out value="${taskType.name}"/></option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group" no=text>
			<div class="title">紧急程度：</div>
			<div class="input">
				<select class="selectize" name="taskUrgency" >
					<option value="">请选择</option>
					<c:forEach items="${urgencyList}" var="urgency">
						<option value="${urgency.id}" <c:if test="${instruction.taskUrgency == urgency.id}"> selected = "selected" </c:if>><c:out value="${urgency.name}"/></option>
					</c:forEach>
				</select>
			</div>
		</div> --%>
		<div class="form-group" no=text>
			<div class="title">接受人：</div>
			<div class="input">
				<input type="hidden" id="slctPersons" name="slctPersons" maxlength="100" value="${instruction.slctPersons }" /> 
				<input type="hidden" id="accountNameDeps" name="accountNameDeps">
				<input type="text" id="vcName" name="vcName" value="${personNames}" >
				<!-- <a href="" class="btn btn-sm btn-search">选择</a> -->
				<a href="javascript:;" onclick="mload('${pageContext.request.contextPath}/task/instruction/_select')" class="btn" title="选择处理人"><img src="../../css/icon/+.svg" alt=""></a>
			</div>
		</div>
		<div class="form-group" no=text>
			<div class="title">任务描述：</div>
			<div class="input">
				<textarea name="taskDescription" id="taskDescription" rows="12" cols="80" maxlength="1000" >${instruction.taskDescription  }</textarea>
			</div>
		</div>
		<!-- <div class="form-group" no=text>
			<div class="title">任务附件：</div>
			<div class="input">
				<input type="file" class="file-input"  id="file1" name="file1" />
			</div>
		</div> -->
		<div class="form-group" no=text style="text-align:right;">
			<input type="hidden" name="id" value="${instruction.id}" />
			<input type="hidden" name="taskNode2" value="${instruction.taskNode2}" />
			<div onclick="checkSubmit();" class="btn bg-red">指令下达</div>
		</div>
	</form>
	
	<div class="modal-container" data-remodal-id="modal" no=text>
		<iframe id="modal-frame" src="" style></iframe>
		<a href="javascript:mclose()" class="modal-close"><img src="../../css/icon/x.svg" alt=""></a>
	</div>
	
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
			if (form.taskName.value == '') {
				alert('指令名称不能为空！');
				return false;
			}
			if (form.taskName.value.length > 30) {
				alert("指令名称最多50字!");
				return false;
			}
			/* if (form.taskType.value == '') {
				alert('任务类别不能为空！');
				return false;
			}
			if (form.taskUrgency.value == '') {
				alert('紧急程度不能为空！');
				return false;
			} */
			if (form.deadLine.value == '') {
				alert('截止日期不能为空！');
				return false;
			}
			if (form.slctPersons.value == '') {
				alert('接受人不能为空！');
				return false;
			}
			if (form.taskDescription.value.length > 2000) {
				alert("任务描述最多2000字!");
				return false;
			}
			
			var formData = new FormData(form);
			var url = "${pageContext.request.contextPath}/task/instruction/_save";
			$.ajax({  
			     url : url,  
			     type : "POST",  
			     data: formData,
		         async: false,  
		         cache: false,  
		         contentType: false,  
		         processData: false,  
			     success : function(data) {
			     	$.ajax({
					     url : "${pageContext.request.contextPath}/task/instruction/_list",  
					     type : "POST",  
					     data: formData,
				         async: false,  
				         cache: false,  
				         contentType: false,  
				         processData: false,  
					     success : function(data) {
					     	$("#taskUl", window.parent.document).empty().append(data);
					     },  
					     error : function(data) {
					     	
					     }  
					});
					window.parent.circles_init();
					window.parent.mclose();
			     },  
			     error : function(data) {
			     	
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