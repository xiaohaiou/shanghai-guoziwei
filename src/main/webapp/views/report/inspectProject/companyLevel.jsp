<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>年度稽核计划管理</title>
<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
<link rel="stylesheet"
	href="<c:url value="/js/jstree/dist/font/components.css"/>"
	type="text/css"></link>
<link rel="stylesheet"
	href="<c:url value="/js/jstree/dist/font/iconfont.css"/>"
	type="text/css"></link>
<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
<!-- 库|插件 -->
<!-- 新加样式 -->
<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">

<link rel="stylesheet" href="<c:url value="/css/bootstrap-select.css"/>">

<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/zTreeStyle.css"/>">

<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
</head>

<style>
.search-list>* {
	line-height: 40px;
}

.bootstrap-select>.dropdown-toggle {
	width: 350px;
	padding-right: 25px;
	z-index: 1;
}
body{
	padding: 1em 4em !important;
}
.rightDataClass{
	height: 20px;
}
</style>
<body>


	<h4>年度稽核计划管理</h4>
	<!-- 这里是点进去菜单所展示的名字 -->


	<div class="table_content">


		<form id="form2" class="row">
			<span class="col-md-4"><span class="search_title">年份：</span> <input type="text" name="year" id="year"
				value="${entity.year}" readonly="true" class="time" /> 
			</span> 
			<span class="col-md-4"><span class="search_title">稽核类型：</span>
				<select name="inspectType" id="inspectType" class="selectpicker">
					
					<c:forEach items="${requestScope.inspectType}" var="result">
						<option value="${result.id }"
							<c:if test="${entity.inspectType == result.id}">selected="selected"</c:if>>
							${result.description}</option>
					</c:forEach>
				</select> 
			</span>
			&nbsp;&nbsp;
			<c:if test="${fn:contains(gzwsession_code,',bimWork_ndjhjhgl_query,')==true }">
				<input type="button" value="查询" class="form_btn" id="qrybtn">
			</c:if>
			<c:if test="${fn:contains(gzwsession_code,',bimWork_ndjhjhgl_save,')==true }">
				<input type="button" value="保存" class="form_btn" id="savebtn">
			</c:if>
			<input type="hidden" id="compIdStr" name="compIdStr">
			<input type="hidden" id="type" name="type" value="${type }">


			<div class="row" style="height:500px;">

				<div class="col-sm-6 col-md-6 col-lg-6">

					<div class="portlet box white" style=" border:none;">
						<!-- BEGIN EXAMPLE TABLE PORTLET-->
						<div class="portlet-body search-list" style="height:500px">
							<div class="scroller" style="height:500px"
								data-always-visible="1" data-rail-visible="1"
								data-rail-color="blue" data-handle-color="blue">

								<div id="tree_3" class="tree-demo"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-6 col-lg-6">
					<div class="portlet box white" style=" border:none;">
						<!-- BEGIN EXAMPLE TABLE PORTLET-->
						<div class="portlet-body search-list" style="height:500px">
							<div class="scroller" style="height:500px"
								data-always-visible="1" data-rail-visible="1"
								data-rail-color="blue" data-handle-color="blue">
								<div class="tree-demo" id="rigthData">
									
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			
		</form>
	</div>
	<jsp:include page="/views/system/modal.jsp" />
</body>
<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script src="<c:url value="/js/jquery.slimscroll.min.js"/>"
	type="text/javascript"></script>
<link rel="stylesheet"
	src="<c:url value="/js/jstree/dist/themes/default/style.css"/>"
	type="text/css"></link>
<script src="<c:url value="/js/jstree/dist/jstree.js"/>"
	type="text/javascript"></script>
<script src="<c:url value="/js/select/bootstrap.min.js"/>"
	type="text/javascript"></script>
<script src="<c:url value="/js/select/bootstrap-select.js"/>"></script>

<script type="text/javascript">
	$(function() {
		    $(".scroller").slimScroll({
		        width: 'auto', //可滚动区域宽度
		        height: '100%', //可滚动区域高度
		        size: '10px', //组件宽度
		        color: '#000', //滚动条颜色
		        position: 'right', //组件位置：left/right
		        distance: '0px', //组件与侧边之间的距离
		        start: 'top', //默认滚动位置：top/bottom
		        opacity: .4, //滚动条透明度
		        alwaysVisible: true, //是否 始终显示组件
		        disableFadeOut: false, //是否 鼠标经过可滚动区域时显示组件，离开时隐藏组件
		        railVisible: true, //是否 显示轨道
		        railColor: '#333', //轨道颜色
		        railOpacity: .2, //轨道透明度
		        railDraggable: true, //是否 滚动条可拖动
		        railClass: 'slimScrollRail', //轨道div类名 
		        barClass: 'slimScrollBar', //滚动条div类名
		        wrapperClass: 'slimScrollDiv', //外包div类名
		        allowPageScroll: true, //是否 使用滚轮到达顶端/底端时，滚动窗口
		        wheelStep: 20, //滚轮滚动量
		        touchScrollStep: 200, //滚动量当用户使用手势
		        borderRadius: '7px', //滚动条圆角
		        railBorderRadius: '7px' //轨道圆角
		    });
		});

	function createTree() {
		/* var tree = $('#tree_3').jstree(true)
		s = tree.get_state() */
	
		var year =$("#year").val();
		var inspectType=$("#inspectType").val();
		var url = "/shanghai-gzw/inspectproject/_getleveltree?year="+year+"&inspectType="+inspectType;
		$.ajax({
			url : url,
			type : "POST",
			dataType : 'json',
			data : {
				"type" : '${type}'
			},
			success : function(jsonString) {
				console.log(jsonString);
				getRigthData(jsonString);
				$("#tree_3").jstree({
					core : {
						themes : {
							responsive : !1,
							url : true
						},
					
						check_callback : !0,
						data : jsonString
					//{"children":[{"children":[{"children":[],"id":37,"state":{"opened":false,"selected":false},"text":"上市公司E"},{"children":[],"id":38,"state":{"opened":false,"selected":false},"text":"上市公司F"},{"children":[],"id":34,"state":{"opened":true,"selected":true},"text":"上市公司B"},{"children":[],"id":23,"state":{"opened":true,"selected":true},"text":"海航3"},{"children":[],"id":35,"state":{"opened":true,"selected":true},"text":"上市公司C"},{"children":[],"id":36,"state":{"opened":true,"selected":true},"text":"上市公司D"},{"children":[],"id":8,"state":{"opened":false,"selected":false},"text":"上海软中123456"},{"children":[],"id":9,"state":{"opened":false,"selected":false},"text":"上市公司F"}],"id":28,"state":{"opened":true,"selected":true},"text":"海航机场"},{"children":[],"id":29,"state":{"opened":true,"selected":true},"text":"海航基础2"},{"children":[],"id":30,"state":{"opened":true,"selected":true},"text":"供销大集"},{"children":[{"children":[],"id":21,"state":{"opened":false,"selected":false},"text":"海航1"}],"id":31,"state":{"opened":true,"selected":true},"text":"海航投资"},{"children":[{"children":[],"id":22,"state":{"opened":true,"selected":true},"text":"海航2"},{"children":[],"id":24,"state":{"opened":true,"selected":true},"text":"海航11"}],"id":32,"state":{"opened":true,"selected":true},"text":"海航资产"}],"id":20,"state":{"opened":true,"selected":true},"text":"上市公司G"}
					},
					types : {
						"default" : {
							icon : "fa fa-folder icon-state-warning icon-lg"
						},
						file : {
							icon : "fa fa-file icon-state-warning icon-lg"
						}
					},
					state : {
						key : "demo2"
					},
					"checkbox" : {
						"keep_selected_style" : false
					},
					plugins : [ "wholerow","checkbox","search" ]
				})
			}
		});
		//tree.set_state(s);
	}
	
	/**
	 * 获取选中的节点
	*/
	function getRigthData(jsonString){
		if(jsonString.state.selected == true){
			$("#rigthData").append("<div class='rightDataClass'>" + jsonString.name + "</div>");
		}
		var kids = jsonString.children;
		for(var i = 0; i < kids.length; i++){
			getRigthData(kids[i]);
		}
	}

	$(document).ready(function() {
			createTree();
	})
	$("#savebtn").click(function(){
		var nodes=$("#tree_3").jstree("get_node");
		 $.each(nodes, function(i, n) { 
	      	ids += n+",";
	      });
	
		  var ids="";
	      var nodes=$("#tree_3").jstree("get_checked"); //使用get_checked方法 
	      $.each(nodes, function(i, n) { 
	      	ids += n+",";
	      }); 
	      //alert(ids);
	     
	     if(ids==""){
	     	win.errorAlert('请选择公司');
	     	return false;
	     }
	     $("#compIdStr").val(ids);	 
	      
		$.blockUI({ message: '提交中', css: { width: '275px' } });
		
		var entityBasicInfo = $('#form2').serialize();
		var url = "${pageContext.request.contextPath}/inspectproject/saveOrUpdatePlan";
		$.post(url, entityBasicInfo, function(data) {
			$.unblockUI();
			var commitresult=JSON.parse(data);
			if(commitresult.result==true){
				
				win.successAlert('保存成功！',function(){
					//parent.location.reload();
				});
			}
			else
			{
				win.errorAlert(commitresult.exceptionStr);
			}
		});
		return false;
	});	
	
	$("#qrybtn").click(function(){
		var form = document.forms[0];
		form.action = "${pageContext.request.contextPath}/inspectproject/leveltree";
		form.method = "post";
		form.submit();
	})
	
	$(' input.time').jeDate({format:"YYYY"});
</script>
</html>