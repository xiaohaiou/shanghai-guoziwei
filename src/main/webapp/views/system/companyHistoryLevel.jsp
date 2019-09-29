<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>财务历史树调整</title>
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
	</style>
	<body>
		<h4>财务历史树调整 </h4>
		<div class="table_content">
				<form id="form1"  class="row"  >
					<span class="col-md-7" >
						<input type="hidden" name="type" id="type" value="${type}"/>
						<table>
							<tr>
								<th>时间：</th>
								<td><input id="timeId" type="text" name="starttime"  class="time1" value="${time}" readonly="readonly"/></td>
								<th>公司名称：</th>
								<td><input type="text" id="filter"/></td>																
							</tr>
							<tr>
							</tr>
						</table>	
					</span>		
					<span class="col-md-5" style="text-align: center">
						<c:if test="${fn:contains(gzwsession_code,',bimWork_cwlsstz_tbdjsc,')==true }">	
							<input type="button" id="guhuaBTN" value="历史树同步到驾驶舱" class="form_btn"  onclick="_guhua();">
						</c:if>
						<c:if test="${fn:contains(gzwsession_code,',bimWork_cwlsstz_edit,')==true }">	
							<input type="button" id="editBTN" value="编辑" class="form_btn"  onclick="_edit()">
						</c:if>
						<input type="hidden" id="move" value="<c:if test="${fn:contains(gzwsession_code,',bimWork_cwlsstz_move,')==true }">dnd</c:if>" >
					</span>
				</form>

				 <div class="row" style="height:25px;">
				 
				 </div>
		
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
							<div class="portlet box white" style=" border:none;" id="operate" >
							<!-- BEGIN EXAMPLE TABLE PORTLET-->
							<form id="form2">
							<input type="hidden" name="selectnode" id="selectnode" value="">
							<input type="hidden" name="type" id="type" value="${type}">
							<div class="portlet-body search-list">
								<input type="hidden" name="nNodeID" id="nNodeID">
								<div class="col-sm-3" style="text-align: right">
									<lable >单位名称：</lable>
								</div>
								<div class="col-sm-9">
									<input type="text" name="vcFullName" id="vcFullName">
								</div>
								
								<div class="col-sm-3" style="text-align: right">
									<lable>单位简称：</lable>
								</div>
								<div class="col-sm-9">
									<input type="text" name="vcShortName" id="vcShortName">
								</div>
								
								<div class="col-sm-3" style="text-align: right">
									<lable>单位形式：</lable>
								</div>
								<div class="col-sm-9">
									<select  name="status" id="status" class="selectpicker">
										<c:forEach items="${requestScope.statustype}" var="result">
											<option value="${result.id }">${result.description }</option>
										</c:forEach>
									</select>
								</div>	
								
								<div class="col-sm-3" style="text-align: right">
									<lable>对应工商公司：</lable>
								</div>
								<div class="col-sm-9">
									<select id="bima" name="bima" class="form-control selectpicker" data-live-search="true" data-size="8" style="width:300px">
										<option value="" ></option>
										<c:forEach items="${requestScope.BimaCompany}" var="result">
											<option value="${result.bimaID }">${result.name }</option>
										</c:forEach>
				  					</select>
								</div>
								<div class="col-sm-12" style="text-align: center">
									<input type="button" value="保存" class="form_btn" id="savebtn" onclick="_save()">
								</div>
								
							</div>
							</form>
						</div>
					</div> 
                               
                 </div>
			
		</div>
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
	
		$("#operate").hide();
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
			console.log(url);
			var tree = $('#tree_3').jstree(true)
			s = tree.get_state();
			$.ajax({
				url: url,
				type: "POST",
				dataType: 'json',
				data:{"type":'${type}',"time":$("#timeId").val()},
				success: function(jsonString) {
					$("#tree_3").jstree(true).settings.core.data = jsonString;
					$("#tree_3").jstree(true).refresh();
				},
				error:function(){
					console.log('调用失败失败！');
				}
			});
			tree.set_state(s);
			$("#operate").hide();
		}

		


		var operate="";
		function _save()
		{
		
			var flag=true;
			if($("#vcFullName").val()=="")
			{
				win.errorAlert("单位名称不能为空");
				flag=false;
			}
			if($("#vcShortName").val()=="")
			{
				win.errorAlert("简称不能为空");
				flag=false;
			}
			if(!flag)
				return;
			switch(operate)
			{
				case "new":
					break;
				case "edit":
					_commitedit();
					break;
				default:
					break;
			}
		}
		
		function _commitedit()
		{
			var tree = $('#tree_3').jstree(true)
			s = tree.get_state()
			
			var input = $('[name=selectnode],#selectnode');
			var id=input.val();
			if(id=="")
			{
				win.errorAlert("请选择一个公司,作为该公司的父公司");
				return false;
			}
			var url = "${pageContext.request.contextPath}/treeHistory/_editnode";
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			var formData = new FormData($("#form2")[0]);
			$.ajax({  
			     url : url,  
			     type : "POST",  
			     data: {"nNodeID": id,"vcFullName":$("#vcFullName").val(),"vcShortName":$("#vcShortName").val(),"type":${type},"status":$("#status").val(),"bimaID":$("#bima").val(),"time":$("#timeId").val()},
			     success : function(data) {
			     $.unblockUI();
			     var commitresult=JSON.parse(data);
						if(commitresult.result==true)
							win.successAlert('保存成功！',function(){
									
							});
						else
							win.errorAlert(commitresult.exceptionStr);
							
						createTree("/shanghai-gzw/treeHistory/_getleveltree");
						tree.set_state(s)
			     },  
			     error : function(data) {
			     	$.unblockUI();
			     }  
			}); 
		}

		$(document).ready(function() {
				$('#tree_3').on('move_node.jstree', function(event, data) {
						var flag=true;
						if(data.node.state.disabled)
						{
							win.generalAlert("无该公司权限");
							flag= false;
						}
						 if(data.parent=='#' )
						{
							win.generalAlert("不能将公司移到该位置");							
							flag= false;
						}
						if(!flag)
						{
							createTree("/shanghai-gzw/treeHistory/_getleveltree");
							return false;
						}	
							
							
					    win.generalWait('正在保存操作结果，请稍后...', function() {
							var node = $("#tree_3").jstree("get_node", data.node.id);
							//获取前一个节点
							var a= data.new_instance.get_prev_dom(node);
							$.ajax({
									url : "/shanghai-gzw/treeHistory/_changelevel",
									type : "POST",
									data : {
												type:${type},
												oldParent : data.old_parent,
												id : data.node.id,
												newParent : data.parent,
												sort:data.position+1,
												time:$("#timeId").val()
											},
									success:function(jsonString) {
											var commitresult=JSON.parse(jsonString);
											if(commitresult.result != true){
												win.errorAlert(commitresult.exceptionStr);
												_query();
											}else
											{
												win.successAlert("保存成功");
												
											}
									}
							});
							return false;		
						});
				}).on("select_node.jstree deselect_node.jstree",function(){
					var input = $('[name=selectnode],#selectnode');
					var select = $("#tree_3").jstree(true).get_selected(true)[0]
					if(select) 
						input.val(select.id);
					else 
						input.val('');
				});
				$("#tree_3").jstree({
						core: {
							multiple:false,
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
				
					createTree("/shanghai-gzw/treeHistory/_getleveltree");
			});
		
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
			
			$('#filter').on('input propertychange',debounce(function(){
				var key_word = $('#filter').val();
				if(key_word)
					$("#tree_3").jstree(true).search(key_word);
				else
					$("#tree_3").jstree(true).clear_search();
			},300,true))
			
		
		function _edit()
		{
			
			operate="edit";
			
			var tree = $('#tree_3').jstree(true)
			s = tree.get_state()
			var input = $('[name=selectnode],#selectnode');
			var id=input.val();
			if(id=="")
			{
				win.errorAlert("请选择一个公司");
				return false;
			}
			$("#operate").show();
			$("#savebtn").val("编辑保存");
			$.ajax({
				url: "${pageContext.request.contextPath}/treeHistory/_getnode",
				type: "POST",
				dataType: 'json',
				data:{"type":'${type}',"selectnode":id,time:$("#timeId").val()},
				syn:true,
				success: function(jsonString) {
					$("#bima").selectpicker('val','' );
					/* $("#nNodeID").val(data.id.nnodeId); */
					$("#vcFullName").val(jsonString.vcFullName)
					$("#vcShortName").val(jsonString.vcShortName);
					$("#status").val(jsonString.status);
					$("#bima").selectpicker('val', jsonString.bimaId);
					
					$("#status option").each(function(){
				        if($(this).val() == jsonString.status){
				        	$(this).attr("selected","selected");
				        }
				        
				    });
				    $('.selectpicker').selectpicker('refresh');  
					
				}
			});
		}
		
		$(' input.time1').jeDate(
			{
				format:"YYYY-MM",
				isClear:false,
				isYes:false,
				choosefun:function(elem, val) {
					createTree("/shanghai-gzw/treeHistory/_getleveltree");
				}
			}
		)
		
		function _guhua(){
			$.blockUI({ message: '财务树固化中...', css: { width: '275px' } });
			var url = "${pageContext.request.contextPath}/treeHistory/saveGuhua";
			$.ajax({  
			     url : url,  
			     type : "POST",  
			     data: {time:$("#timeId").val()},
		         dataType:'text', 
			     success : function(data) {
				     $.unblockUI();
				     if(data=='success'){
						 win.successAlert('保存成功！',function(){
								parent.mclose();
						 });
				     }
			     },  
			     error : function(request,textStatus,errorThrown) {
			     	win.generalAlert("固化失败！");
			     	$.unblockUI();
			     }  
			}); 
			return false;
		}
	</script>
</html>