<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>风险目录管理树</title>
<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
<link rel="stylesheet" href="<c:url value="/css/font/iconfont.css"/>" />
<!-- 库|插件 -->
<!-- 新加样式 -->
<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">
	 
</head>
<body>
	<h4>风险目录管理树</h4>
	<div class="table_content">
		<form id="form2" class="row">
		    <div class="row">
			<span class="col-md-4"><span class="search_title">目录名称：：</span> 
			<input type="text" name="name" id="inp_name"/></span> 
			<c:if test="${fn:contains(gzwsession_code,',bimWork_fxmlgls_query,')==true}">
			     <input type="button" value="查询" class="form_btn" id="btn_query">
			</c:if>
			</div>
			<div class="row" style="height:500px;">
				<div class="col-sm-6 col-md-6 col-lg-6">
					<div class="portlet box white" style=" border:none;">
						<!-- BEGIN EXAMPLE TABLE PORTLET-->
						<div class="portlet-body search-list" style="height:500px">
							<div class="scroller" style="height:500px" data-always-visible="1" data-rail-visible="1" data-rail-color="blue" data-handle-color="blue">
							<ul id="div_catalog_tree" class="ztree"></ul>
							</div>
						</div>
					</div>
					
				</div>
				<div style="tablebox">
					<table style="width:48%;height:500px;">
					  <tr>
					    <td style="width:30%;height:30%;background-color:#D8D8D8;border:1px solid #008B8B;"><span id="span_name_label">风险目录名称</span></td>
					    <td style="width:70%;height:30%;border:1px solid #008B8B;text-align:left;padding:5px;"><span id="span_name_value"></span></td>
					  </tr>
					  <tr>
					    <td style="width:30%;height:70%;background-color:#D8D8D8;border:1px solid #008B8B;"><span id="span_define_label">风险目录定义</span></td>
					    <td style="width:70%;height:70%;border:1px solid #008B8B;text-align:left;padding:5px;"><span id="span_define_value"></span></td>
					  </tr>
					</table>
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
    
	function getFontCss(treeId, treeNode) {
		return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
	}
	
	function zTreeOnClick(event, treeId, treeNode) {
		var url = "${pageContext.request.contextPath}/bimr/riskCatalog/get";
		var params = {id: treeNode.id};
		
		$.get(url, params, function(data){
			if(!data.result){
				win.errorAlert(data.exceptionStr);
				return;
			}
			var level = data.entity.level;
			var levelZhCn= level == 1? '一' : level == 2? '二' : '三';
			$("#span_name_label").html(levelZhCn+'风险目录名称');
			$("#span_name_value").html(data.entity.name);
			$("#span_define_label").html(levelZhCn+'风险目录定义');
			$("#span_define_value").html(data.entity.define);
		});
	};

    function loadTree(){
    	var url = "${pageContext.request.contextPath}/bimr/riskCatalog/getTree";
		var params = {isFull: true};
			
		var setting = {
				view:{
					fontCss: getFontCss,
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
			
		$.blockUI({ message: '请等待正在加载风险目录树...', css: { width: '300px' } });
		
		$.get(url, params, function(data){
			if(!data.result){
				$.unblockUI();
				win.errorAlert(data.exceptionStr);
				return;
			}
			
			zTreeObj = $.fn.zTree.init($("#div_catalog_tree"), setting, data.entity);
			$.unblockUI();
		});
    }
    
	$(function() {
		initScroll();
		loadTree();
	});
	
	var selectNodes=[];
	var perOpenNodes = [];
	var openNodes = [];
	
	function updateNodesHighlight(treeObj, highlight) {
		for( var i=0, l=selectNodes.length; i<l; i++) {
			selectNodes[i].highlight = highlight;
			treeObj.updateNode(selectNodes[i]);
		}
	}
	
	function openQueryNode(treeObj, node){
		var parent = node.getParentNode();
		var isChild = false;
		
		while(parent){
			isChild = true;
			treeObj.expandNode(parent, true, false, false, null);
			pushOpeNodes(parent);
			parent = parent.getParentNode();
		}
	}
	
	function pushOpeNodes(node){
		if(!hasNode(node.id, openNodes)){
			openNodes.push(node);
		}
	}
	
	function hasNode(id, nodes){
		var has = false;
		for(var i = 0, l = nodes.length; i < l; i++ ){
			if(id == nodes[i].id){
				has = true;
				break;
			}
		}
		return has;
	}
	
	function openQueryNodeAll(treeObj){
		openNodes = [];
		for(var i = 0, l = selectNodes.length; i< l; i++) {
			openQueryNode(treeObj, selectNodes[i]);
		}
	}
	
	function copyOpenNodesToPre(){
		perOpenNodes = [];
		for(var i =0, l = openNodes.length; i < l; i++){
			perOpenNodes.push(openNodes[i]);
		}
	}
	
	function closePerExpandNodes(treeObj){
		for(var i= 0, l= perOpenNodes.length; i< l; i++){
			var node = perOpenNodes[i];
			var has = hasNode(node.id, openNodes);
			if(!has){
				treeObj.expandNode(node, false, false, false, null);
			}
		}
	}
	
	$("#btn_query").click(function(){
		var treeObj = $.fn.zTree.getZTreeObj("div_catalog_tree");
		copyOpenNodesToPre();
		updateNodesHighlight(treeObj, false);
		var name = $("#inp_name").val();
		if(name){
			selectNodes = treeObj.getNodesByParamFuzzy("name", name, null);
			updateNodesHighlight(treeObj, true);
			openQueryNodeAll(treeObj);
		}
		if(selectNodes.length > 0){
			treeObj.selectNode(selectNodes[0], false, true);
			zTreeOnClick(null, null, selectNodes[0]);
			closePerExpandNodes(treeObj);
		}
	});
	
</script>
</html>