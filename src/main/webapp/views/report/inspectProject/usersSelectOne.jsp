<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
	<!-- 库|插件 -->
	<link rel="stylesheet" href="<c:url value="/css/remodal.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/remodal-default-theme.css"/>">

	<link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value="/css/font/iconfont.css"/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value="/css/custom.css"/>" rel="stylesheet" type="text/css" />
	<!-- 页面 -->
	<link rel="stylesheet" href="<c:url value="/css/modal.css"/>">
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
	</style>
</head>

<body>
	<header no=text>
		<span class="title">人员选择</span> <a
			href="javascript:window.parent.mclose()" class="exit"><img
			src="../../css/icon/x.svg" alt="">
		</a>
	</header>
	<form class="body form" action="index.html" method="post">
		<div class="inline" no=text>
			
			<div class="form-group inline w n85" no=text>
				<div class="title" style="text-align:center;">人员：</div>
				<div class="input">
					<input type="text" id="filterName" name="filterName" maxlength="50" />
				</div>
			</div>
			<div class="form-group inline w n15 tcenter" no=text>
				<div id="searchBtn" class="btn bg-green">查询</div>
			</div>
		</div>
		<div class="inline" no=text>
			<div class="form-group w n50" no=text>
				<div class="role-permission" style="height:350px;" >
					<ul class="checkbox-group role-group">
						${shtml}
					</ul>
				</div>	
			</div>
			<div class="form-group w n50" no=text>
				<div class="scroller w n50" style="height:350px;" data-always-visible="1"
					data-rail-visible="1" data-rail-color="red" data-handle-color="red">
					<div id="con" style="padding:6px;height:100%; overflow-x:auto;border:1px solid #ffcc00;background:#fffff7"></div>
				</div>
			</div>
		</div>
		<div class="inline" no=text>
			<div class="form-group inline w n100" no=text style="white-space: nowrap;">
				<div class="title" style="white-space: nowrap;">已选中人：</div>
				<div class="input" >
					<input type="hidden" id="vcEmployeeId" name="vcEmployeeId" class="form-control input-sm" >
					<input type="text" id="vcName" name="vcName" class="form-control input-sm" readonly="readonly">
					<textarea id="selectUsers" readonly name="selectUsers"  style="height:80px;min-height:80px; display:none;"></textarea>
					<textarea id="selectUsers1" readonly name="selectUsers1"  style="height:80px;min-height:80px;display:none;"></textarea>
				</div>
			</div>
		</div>
		<div class="inline tright" no=text>
			<input type="hidden" id="selectedCompanyID" name="selectedCompanyID">
			<input type="hidden" id="selectedPersonID" name="selectedPersonID">
			<div class="form-group inline w n10 tcenter" no=text>
				<div onclick="parent.mclose()" class="btn bg-green">关闭 </div>
			</div>
			<div class="form-group inline w n10 tcenter" no=text>
				<div id="ensure" class="btn bg-green">选择 </div>
			</div>
		</div>
	</form>

	<div class="modal-container" data-remodal-id="modal" no=text>
		<a href="javascript:mclose()" class="modal-close"><img
			src="icon/x.svg" alt="">
		</a>
		<iframe id="modal-frame" src=""></iframe>
	</div>

	<!-- 库 -->
	<!-- 库 modal-->
	<script type="text/javascript" src="<c:url value="/js/jquery-3.1.1.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jstree.min.js"/>"></script>
	<!-- 优先处理 -->
	<script type="text/javascript">
		// 清理空格
		var toArray = Array.from || function(arrayLike) {
			return [].slice.call(arrayLike)
		};
		toArray(document.body.querySelectorAll('[no~=text]')).forEach(
				function(el) {
					toArray(el.childNodes).forEach(function(e) {
						if (e.nodeType == 3) {
							el.removeChild(e)
						}
					})
				})
		// resize img
		toArray(document.body.querySelectorAll('.avatar>img')).forEach(
				function(el) {
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
				$('>.modal-close', ifm.parentNode).css('display', 'block')
				ifm.onload = function() {
					ifm.onload = undefined;
					$('>.modal-close', ifm.parentNode).css('display', '')
					if (callback) {
						setTimeout(callback.bind(ifm.contentWindow))
					}
				};
				ifm.src = url;
				$('[data-remodal-id=modal]').remodal({
					closeOnEscape : false,
					closeOnOutsideClick : false,
				}).open();
			}
		}
		window.modal_close = window.mclose = function() {
			var ifm = $('[data-remodal-id=modal] iframe')[0];
			$('[data-remodal-id=modal]').remodal().close();
			ifm.src = '';
			ifm.style.height = '';
		}
		$(document.body).on('opening', '.remodal', function() {
			// document.body.style.overflow = "hidden"
		})
		$(document.body).on('closed', '.remodal', function() {
			// document.body.style.overflow = ""
		})
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".departmentLabel >i.iconfont").click(function(){
				$(this).parent(".departmentLabel").siblings("ul.level-2").toggleClass("active");
				$(this).toggleClass("icon-tree-close-2 icon-tree-open-2");
			});
			
			$("#searchBtn").click(function() {
				console.log("search");
				searchdata();
			});
			
			/*$("#con").on("click", ".selectone", function() {
				$(this).addClass("selectedOne");
				$(this).siblings.each(function(){
					$(this).removeClass("selectedOne");
				});
				//$(this).toggleClass("selectedOne");
			});*/
			
			
			
			
		});
		function searchdata() {
				if ($("#selectedCompanyID").val() == ""
						&& $("#filterName").val() == "") {
					win.generalAlert("请选择公司或者输入人名查询条件");
					return false;
				}
				var url = "${pageContext.request.contextPath}/task/instruction/_searchPerson";
	
				$.ajax({
					url : url,
					type : "POST",
					dataType : 'json',
					data : {
						id : $("#selectedCompanyID").val(),
						name : $("#filterName").val()
					},
					success : function(result) {
						$("#con").empty().append(result)
					}
				});

			}
			
			var personMap={};
			function getUsers(vcEmployeeId,vcAccount,vcName,vcFullName){
				//debugger;
				if(vcEmployeeId == ''){
					vcEmployeeId = " ";
				}
				if(vcAccount == ''){
					vcAccount = " ";
				}
				if(vcName == ''){
					vcName = " ";
				}
				if(vcFullName == ''){
					vcFullName = " ";
				}
				
				var count=0;
				$.each(personMap,function(i,n){
					//debugger;
					if(vcEmployeeId==i){ //点击相同的人员 就删掉
						delete(personMap[i]);
						count++;
					}
				})
				
				if(count==0)
					personMap[vcEmployeeId]=vcName;
				
				var nameStr="";
				var idStr="";
				$.each(personMap,function(i,n){
					nameStr+=n+",";
					idStr+=i+",";
				})
				
				if(nameStr!=""){
					nameStr=nameStr.substring(0,nameStr.lastIndexOf(','));
					idStr=idStr.substring(0,idStr.lastIndexOf(','));
				}	
					
				//console.log(personMap);
				
				$("#vcEmployeeId").val(idStr);
				$("#vcName").val(nameStr);
			}
		function itemClick(v){
			var url = "${pageContext.request.contextPath}/task/instruction/_searchPerson";

			$.ajax({
				url : url,
				type : "POST",
				dataType : 'json',
				data : {
					id : v,
					name : ""
				},
				success : function(result) {
					$("#con").empty().append(result)
				}
			});
		}
		
		$("#ensure").on("click",
				function() {
					var eleId='#'+'${eleId}';
					var val = $("#vcName").val().toString();
					var val1 = $("#vcEmployeeId").val().toString();
					$(eleId, window.parent.document).focus();
					$(eleId, window.parent.document).val(val);
					$(eleId, window.parent.document).blur();
					$(eleId +"Id", window.parent.document).val(val1);
					window.parent.modal_close();
				});
	</script>
</body>

</html>
