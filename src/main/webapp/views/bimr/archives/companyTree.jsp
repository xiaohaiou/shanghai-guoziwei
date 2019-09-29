<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>企业风控档案</title>
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
<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">
	 
</head>
<body>
	<h4>企业风控档案</h4>
	<div class="table_content">
		<form id="form2" class="row">
			<div class="row" style="height:500px;">
				<div class="col-sm-6 col-md-6 col-lg-6">
					<div class="portlet box white" style=" border:none;">
						<!-- BEGIN EXAMPLE TABLE PORTLET-->
						<div class="portlet-body search-list" style="height:500px">
							<div class="scroller" style="height:500px" data-always-visible="1" data-rail-visible="1" data-rail-color="blue" data-handle-color="blue">
							<ul id="div_company_tree" class="ztree"></ul>
							</div>
						</div>
					</div>
					
				</div>
				<div id ="div_company_content" style="width:48%;float:right;">
				</div>   
			</div>
		</form>
	</div>
	<jsp:include page="/views/system/modal.jsp" />
</body>

<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script src="<c:url value="/js/jquery.slimscroll.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/select/bootstrap.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/select/bootstrap-select.js"/>"></script>

<script type="text/javascript">

    function initScroll(){
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
    }
	
	function zTreeOnClick(event, treeId, treeNode) {
		var url = "${pageContext.request.contextPath}/bimr/archives/companyTree/companyContent";
		var params = {compId: treeNode.id};
		
		$.get(url, params, function(data){
				$("#div_company_content").html(data);
		});
	};

    function loadTree(){
		var setting = {
				view:{
					showIcon: false
				},
				data:{
					simpleData: {
						enable: true,
						idKey: "id",
						pIdKey: "pId",
						rootPId: 0
					}
				},
				callback:{
					onClick: zTreeOnClick
				}
		};
		
		var  data = ${companyTree};
    	zTreeObj = $.fn.zTree.init($("#div_company_tree"), setting, data);
    }
    
	$(function() {
		initScroll();
		loadTree();
	});
</script>
</html>