<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>财务树查询</title>
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/js/jstree/dist/font/components.css"/>" type="text/css"></link>
		<link rel="stylesheet" href="<c:url value="/js/jstree/dist/font/iconfont.css"/>" type="text/css"></link>
		<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
		
		<link rel="stylesheet" href="<c:url value="/css/bootstrap-select.css"/>" >
		
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">

		<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	</head>
	
	<style>
	.search-list > * {
    	line-height: 40px;
	}
	.bootstrap-select > .dropdown-toggle {
	    width: 350px;
	    padding-right: 25px;
	    z-index: 1;
	}
	body{
    	padding: 1em 4em !important;
	}
	.scroller-bottom{
		background-color: transparent !important;
	}
	th{
		caption-side: top;
		width: auto;
		text-align: left;
		font: italic small-caps 600 12pts/18pts 宋体;
		 font-size: 15px;
	}
	td{
		caption-side: top;
		width: auto;
		text-align: center; 
		font: italic small-caps 600 12pts/18pts 宋体;
		font-size: 15px;
	}
	</style>
	<body>
	
		<h4>财务树查询</h4>
			
		<div class="table_content">
				<form id="form1"  class="row"  >
					<span class="col-md-4" >
						<table>
							<tr>
								<th colspan="2" style="color: #778899;">财务管理系统可查看公司数据范围</th>												
							</tr>						
							<tr id="trId">
								<th>公司名称：</th>
								<td><input type="text" id="filter3"/></td>																
							</tr>
							<tr>
								<th colspan="2">&nbsp</th>												
							</tr>								
						</table>	
					</span>	
					<span class="col-md-2"></span>
					<span class="col-md-4" >
						<input type="hidden" name="type" id="type" value="${type}"/>
						<table>
							<tr>
								<th colspan="3" style="color: #778899;">历史财务树查询(查看公司组织管理架构树)</th>	
							</tr>							
							<tr>
								<th>时间：</th>
								<td><input id="timeId" type="text" name="starttime" readonly class="time1"/></td>
								<td width="10%"><input type="button" id="timeBtnId" value="查询" /></td>												
							</tr>
							<tr id="trId">
								<th style="font-size:15px;">公司名称：</th>
								<td><input type="text" id="filter4"/></td>																
							</tr>							
						</table>	
					</span>	
				</form>
		
				 <div class="row">
           
           		   <div class="col-sm-6 col-md-6 col-lg-6">
						<div class="portlet box white" style=" border:none;">
							<!-- BEGIN EXAMPLE TABLE PORTLET-->
							<div class="portlet-body portlet-body1 search-list" style="height:500px">
								<div class="scroller" style="height:500px" data-always-visible="1" 
									data-rail-visible="1" data-rail-color="blue"
									data-handle-color="blue">
									
									<div id="tree_3" class="tree-demo"></div>
								</div>
							</div>
						</div>
					</div>
           
					<div class="col-sm-6 col-md-6 col-lg-6">
		
						<div class="portlet box white" style=" border:none;">
							<!-- BEGIN EXAMPLE TABLE PORTLET-->
							<div class="portlet-body portlet-body1 search-list" style="height:500px">
								<div class="scroller" style="height:500px" data-always-visible="1" 
									data-rail-visible="1" data-rail-color="blue"
									data-handle-color="blue">
									
									<div id="tree_4" class="tree-demo"></div>
								</div>
							</div>
						</div>
					</div>
                 </div>
		</div>
		<jsp:include page="../../system/modal.jsp"/>
	</body>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
	<script src="<c:url value="/js/jquery.slimscroll.min.js"/>" type="text/javascript"></script>
	<link rel="stylesheet" src="<c:url value="/js/jstree/dist/themes/default/style.css"/>" type="text/css"></link>
	<script src="<c:url value="/js/jstree/dist/jstree.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/js/select/bootstrap.min.js"/>" type="text/javascript"></script>
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
		
		var scrollerP = $('.portlet-body1').css('position','relative').append('<div class=\"scroller-top\" style="position:absolute;top:-25px;left:0;right:0;height:40px;display:block;background-color:rgba(0,0,0,0);"></div>'+'<div class=\"scroller-bottom\" style="position:absolute;bottom:-25px;left:0;right:0;height:40px;display:block;background-color:rgba(0,0,0,0);"></div>');
		var scroller = $('.portlet-body1').on('mouseenter',function(){ scrollerP.find('.scroller-top, .scroller-bottom').show() }).on('mouseleave',function(e){ scrollerP.find('.scroller-top, .scroller-bottom').hide() })
		var auto_move = false;
		var next_move = false;
		var move_up = function(){
			if(!auto_move || !next_move) return
			$('.scroller').slimScroll({scrollTo:Math.floor($('.scroller')[0].scrollTop)-2})
			requestAnimationFrame(move_up)
		};
		var move_down = function(){
			if(!auto_move || !next_move) return
			$('.scroller').slimScroll({scrollTo:Math.ceil($('.scroller')[0].scrollTop)+2})
			requestAnimationFrame(move_down)
		};
		scrollerP[0].addEventListener('mousedown',function(){
			auto_move = true
		},true)
		scrollerP[0].addEventListener('mouseup',function(){
			auto_move = false
		},true)
		$('.portlet-body1 .scroller-top').on('mouseenter',function(){
			next_move = true
			move_up()
		}).on('mouseleave',function(){
			next_move = false
		});
		$('.portlet-body1 .scroller-bottom').on('mouseenter',function(){
			next_move = true
			move_down()
		}).on('mouseleave',function(){
			next_move = false
		});
		$('.portlet-body1').find('.scroller-top, .scroller-bottom').on('mouseenter',function(){
			this.style['background-color'] = 'rgba(0,0,0,0.2)';
		}).on('mouseleave',function(e){
			this.style['background-color'] = 'rgba(0,0,0,0)'
		})
		
		
		
		function createTree(url) {
			var tree = $('#tree_3').jstree(true);
			var s = tree.get_state();
			
			$.ajax({
				url: url,
				type: "POST",
				dataType: 'json',
				data:{"type":'${type}'},
				success: function(jsonString) {
				//console.log(jsonString) 
					$("#tree_3").jstree(true).settings.core.data = jsonString;
					$("#tree_3").jstree(true).refresh();
				}
			});
			tree.set_state(s);
		}

		var operate="";

		$(document).ready(function() {
				$("#tree_3").jstree({
						core: {
							multiple:true,
							themes: {
								responsive: !1,
								url: true
							},
							check_callback: !0,
						},
						types: {
							"default": {
								icon: "fa fa-folder icon-state-warning icon-lg"
							},
							file: {
								icon: "fa fa-file icon-state-warning icon-lg"
							}
						},
						state: {
							key: "demo2"
						},
						dnd : {
						            drop_target : false,
						            drag_target : false
						 },
						"checkbox": {
							"keep_selected_style": false
						},
						plugins: [   $("#move").val(), "search","state", "types", "wholerow"]
						
					})
				
					createTree("/shanghai-gzw/reportbudgetenforcement/_getleveltree");
					innitTree4();
			});
			
			function innitTree4(){
					$("#tree_4").jstree({
							core: {
								multiple:true,
								themes: {
									responsive: !1,
									url: true
								},
								check_callback: !0,
							},
							types: {
								"default": {
									icon: "fa fa-folder icon-state-warning icon-lg"
								},
								file: {
									icon: "fa fa-file icon-state-warning icon-lg"
								}
							},
							state: {
								key: "demo2"
							},
							dnd : false,
							"checkbox": {
								"keep_selected_style": false
							},
							plugins: [   $("#move").val(), "search","state", "types", "wholerow"]
							
					})
			}
		
			$('#tree_4').on('after_open.jstree after_close.jstree',function(){
				
				 $(".scroller").slimScroll();
			})
		
			$('#tree_3').on('after_open.jstree after_close.jstree',function(){
				
				 $(".scroller").slimScroll();
			})
			
			   var throttle = function (fn,delay, immediate, debounce) {
			   var curr = +new Date(),//当前事件
			       last_call = 0,
			       last_exec = 0,
			       timer = null,
			       diff, //时间差
			       context,//上下文
			       args,
			       exec = function () {
			           last_exec = curr;
			           fn.apply(context, args);
			       };
			   return function () {
			       curr= +new Date();
			       context = this,
			       args = arguments,
			       diff = curr - (debounce ? last_call : last_exec) - delay;
			       clearTimeout(timer);
			       if (debounce) {
			           if (immediate) {
			               timer = setTimeout(exec, delay);
			           } else if (diff >= 0) {
			               exec();
			           }
			       } else {
			           if (diff >= 0) {
			               exec();
			           } else if (immediate) {
			               timer = setTimeout(exec, -diff);
			           }
			       }
			       last_call = curr;
			   }
			};
			var debounce = function (fn, delay, immediate) {
			   return throttle(fn, delay, immediate, true);
			};
			
			$('#filter4').on('input propertychange',debounce(function(){
				var key_word = $('#filter4').val();
				if(key_word){
					$("#tree_4").jstree(true).search(key_word);				
				}else{
					$("#tree_4").jstree(true).clear_search();				
				}
			},300,true))
			
			$('#filter3').on('input propertychange',debounce(function(){
				var key_word = $('#filter3').val();
				if(key_word){
					$("#tree_3").jstree(true).search(key_word);				
				}else{
					$("#tree_3").jstree(true).clear_search();				
				}
			},300,true))
		
		$(' input.time1').jeDate(
			{
				format:"YYYY-MM",
			}
		)
		
		//获取历史财务树
		$("#timeBtnId").bind('click', function() {
			var time = $('#timeId').val();
			var url = "${pageContext.request.contextPath}/reportbudgetenforcement/_getleveltree";
			var tree = $('#tree_4').jstree(true);
			var s = tree.get_state();
			$.ajax({
				url: url,
				type: "POST",
				dataType: 'json',
				data:{"type":'${type}',"time":time},
				success: function(jsonString) {
					$("#tree_4").jstree(true).settings.core.data = jsonString;
					$("#tree_4").jstree(true).refresh();
				}
			});
			
			tree.set_state(s); 
		});

		
	</script>
</html>