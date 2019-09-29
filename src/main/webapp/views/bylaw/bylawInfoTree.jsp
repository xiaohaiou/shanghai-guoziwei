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
<link href="<c:url value="/css/font/iconfont.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/css/custom.css"/>" rel="stylesheet" type="text/css" />
<!-- 库|插件 -->
<!-- 新加样式 -->
<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">


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
</style>
<body>


	<h4>规章制度树</h4>


	<div class="table_content">


			<form id="form2" class="row">
				<span class="col-md-4"><span class="search_title">规章制度标题：</span> 
					<input type="text" name="bylawTitle" id="bylawTitle"/>
				</span> 
				
				&nbsp;&nbsp;
				<c:if test="${fn:contains(gzwsession_code,',bimWork_gzzds_query,')==true }">
				     <input type="button" value="查询" class="form_btn" id="queryBtn">
				</c:if>
			</form>
			<div class="row" style="height:500px;">

				<div class="col-sm-6 col-md-6 col-lg-6">

					<div class="portlet box white" style=" border:none;">
						<!-- BEGIN EXAMPLE TABLE PORTLET-->
						<div class="portlet-body search-list" style="height:500px">
							<div class="scroller" style="height:500px"
								data-always-visible="1" data-rail-visible="1"
								data-rail-color="blue" data-handle-color="blue">
								<div class="form-group w n50" no=text>
									<div class="role-permission" style="height:100%" >
										<ul class="checkbox-group role-group" id="sTreeBody">
											${shtml}
										</ul>
									</div>	
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-6 col-lg-6" id="bylawinfoDetail">
					
				</div>
			</div>

			
		
	</div>
</body>
<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script src="<c:url value="/js/jquery.slimscroll.min.js"/>"
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
		    
		    $(".departmentLabel >i.iconfont").click(function(){
				$(this).parent(".departmentLabel").siblings("ul.level-2").toggleClass("active");
				$(this).toggleClass("icon-tree-close-2 icon-tree-open-2");
			});
		});

	/**
	*获取规章制度详细
	*/
	function getDetail(id){
		var url = "${pageContext.request.contextPath}/bylaw/treeBylawInfoDetail?id="+id;
		$.ajax({
			 url : url,  
		     type : "POST",  
	         async: false,  
	         cache: false,  
	         contentType: false,  
	         processData: false,  
		     success : function(data){
		     	$("#bylawinfoDetail").empty();
		     	$("#bylawinfoDetail").append(data);
		     }
		});
	}
	
	$("#queryBtn").on("click",function(){
		var title = $("#bylawTitle").val()+"";
		var as = $("#sTreeBody").find("a");
		$("ul.level-2").removeClass("active");
		$(".departmentLabel >i.iconfont").removeClass().addClass("iconfont icon-tree-close-2");
		as.attr("style","");
		if(title != ''){
			as.each(function(index,el){
				var dd = $(this).text()+"";
				if(dd.indexOf(title)!=-1){//找到某个节点
					$(this).attr("style","font-weight:bold");
					$(this).parent().parent("li").parents("li").each(function(){
						$(this).children(".departmentLabel").siblings("ul.level-2").addClass("active");
						$(this).children(".departmentLabel").children("i").removeClass().addClass("iconfont icon-tree-open-2");
					});
				}
			});
		}
		return false;
	});
</script>
</html>