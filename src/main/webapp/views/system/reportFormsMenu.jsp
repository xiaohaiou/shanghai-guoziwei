<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>工作台</title>
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/css/heaader.css"/>" />
		<!-- 库|插件 -->
		<link rel="stylesheet" href="<c:url value="/css/remodal.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/remodal-default-theme.css"/>">
		<link rel="stylesheet" type="text/css" href="<c:url value="/font/iconfont.css"/>">
		<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
		
		<style type="text/css">
			body{
				overflow:hidden;
			}
			/* 二级菜单 */
			.l_top{
				overflow: visible;
			}
			
			.show_nav_box{
				overflow: visible;
			}
			#nav_box{
				display: block;
				position: absolute;
				right: 0;
				top: 70px;
				border-top: 1px solid #222c3c;
				width: 290px;
				background: #161c22;
				display: none;
				z-index: 100;
			}
			
			.one_nav{
				display: block;
				width: 290px;
				height: 70px;
				border-bottom: 1px solid #3a3c3f;
				line-height: 70px;
				position: relative;
			}
			
			.one_nav:nth-last-of-type(1){
				border: none;
			}
			
			.one_nav>img{
				height: 30px;
				max-width: 46px;
				position: absolute;
				top: 0;
				bottom: 0;
				left: 20px;
				margin: auto;
			}
			
			.one_nav>span{
				display: inline-block;
				width:220px;
				color: #9e9fa1;
				font-size: 16px;
				position: absolute;
				left: 70px;
				overflow: hidden;
				text-overflow: ellipsis;
				white-space: nowrap;
				text-align: left;
			}
			
			.one_nav:hover{
				background: #cd1c0d;
				transition: all 0.1s linear;	
			}
			
			.one_nav:hover>span{
				color: #FFF;
				transition: all 0.1s linear;	
			}
			/*modal*/
			.remodal-wrapper {
				padding: 0px;
			}
			
			.modal-container {
				position: relative;
				padding: 0;
				margin: 0;
				height: 100%;
				background-color: rgba(0, 0, 0, 0);
				max-width: 900px;
			}
			
			[data-remodal-id=modal] iframe {
				position: relative;
				width: 100%;
				max-height: 100%;
			}
			
			.modal-close {
				display: none;
				position: absolute;
				height: 20px;
				right: 12px;
				top: 12px;
			}
			
			.modal-close>img {
				height: 100%;
			}
			
			.remodal-overlay.night-theme {
				background-color: #f44336;
			}
			
			.remodal.night-theme {
				background: #fff;
			}
			
			/* 左侧二级菜单 */
			.left_menu{
				padding-top:0;
			}
			.left_menu>li{
				position:relative;			
			}
			.left_menu>li>ul{
			    background: #1d3654;
			    line-height: 2em;
			}
			.left_menu>li>ul>li{
				text-align:left;
			}
			.left_menu a {
			    padding: 10px 20px;
			    display: block;
			    width: 12em;
			    overflow: hidden;
			    text-overflow: ellipsis;
			    white-space: nowrap;
			    color: #cacaca;
			}
			.left_menu>li>ul>li>a:hover{
				background-color: #cd1b0d;
			    color:#fff;;
			}
			.left_menu li a.active{
				background-color: #121c25;
				color:#aaa;
			}
			.left_menu>li>a.active,.left_menu>li>ul>li>a.active{
			    background-color: #cd1b0d;
			    color:#fff;
			}
			.left_menu li a.active span{
				height:inherit;
			}
			.slimScrollDiv{
				display:inline;
			}
			.up{
				position: absolute;
				color: #fff;
				right: 10px;
				top: 5px;
				z-index: 999;
			}
			.up>i{
				font-size: 20px !important;
				height:36px !important;
				line-height:40px !important;
			}
			.down{
				position: absolute;
				color: #fff;
				right: 10px;
				bottom: 10px;
				z-index: 999;
			}
			.down>i{
				font-size: 20px !important;
				height:36px !important;
				line-height:40px !important;
			}
			#left_menu ul>span{
				cursor: pointer;
			}
			#left_menu ul>li{
				cursor: default;
			}
			.left_menu, .left_tab {
			    left: 0px;
			    width: 12em;
			    background: #1d3654;
			    top: 70px;
			    height: calc(100vh - 70px) !important;
			    z-index: 9;
			    margin: 0px;
			}
			.right-content {
			    margin-top: 0px;
		        position: relative;
			    display: inline-block;
			    width: calc(100vw - 168px);
			    margin-left: 0 !important;
			    float: right;
			}
			#left_panel{
			    width: 12em;
			    height: calc(100vh - 70px);
			    display: inline-block;
		        background: #1d3654;
			}
		</style>
		<!-- <script type="text/javascript">
			var to_def_img = function(el){
				el.src = "<c:url value="/img/head.png"/>";
			}
		</script> -->
		<script>
		var resize_iframe_height_ord = function(ifm){
			ifm.style.height = ifm.contentDocument.body.scrollHeight+'px';
		}
		var resize_iframe_height = function(ifm){
			ifm.style.height = Math.max(ifm.contentDocument.body.scrollHeight,$('.sec-maincontent').height())+'px';
		}
		
		$(document).ready(function(){
			$('.left_menu a[target]').eq(0).trigger('click');
			$('#mainFrame').attr('src',$('.left_menu a[target]')[0].href);
		});
		
	
		
		

	</script>
	</head>
		<body class="hn_grey">
		
		<jsp:include page="/views/public/title.jsp"></jsp:include>
	
		
			<div class="container-fluid sec-maincontent">
			<div id="left_panel">
			<ul class="left_menu" id="left_menu" style="height:auto !important">
				<c:if test="${ menu==1 }">
				<c:if test="${fn:contains(gzwsession_page,',bimWork_cgsjsb,')==true || fn:contains(gzwsession_page,',bimWork_cgsjsh,')==true}">
					 <li>
<!-- 	                    <a href="javascript:void(0)" target="mainFrame">
	                        <i class="iconfont icon-caigouguanli1"></i>
	                        <span>采购数据</span>
	                    </a> -->	
                   		<ul>
                   			<c:if test="${fn:contains(gzwsession_page,',bimWork_cgsjsb,')==true || fn:contains(gzwsession_page,',bimWork_cgsjsh,')==true}">
			                	<li class="js_sjsq"><a href="${pageContext.request.contextPath}/queryPage/purchase/list" target="mainFrame">采购数据查看</a></li>
			                </c:if>
							
	                    </ul>
	                </li>
				</c:if>
				</c:if>
				<c:if test="${ menu==3 }">
				<c:if test="${fn:contains(gzwsession_page,',bimWork_gwsjsb,')==true || fn:contains(gzwsession_page,',bimWork_gwsjsh,')==true || 
							  fn:contains(gzwsession_page,',bimWork_dbsjsb,')==true || fn:contains(gzwsession_page,',bimWork_dbsjsh,')==true ||
							  fn:contains(gzwsession_page,',bimWork_bmfxsjsjsb,')==true || fn:contains(gzwsession_page,',bimWork_bmfxsjsjsh,')==true ||
							  fn:contains(gzwsession_page,',bimWork_yqsjsjsb,')==true || fn:contains(gzwsession_page,',bimWork_yqsjsjsh,')==true}">
					<li>
						<!-- <a href="javascript:void(0)" target="mainFrame">
		                    <i class="iconfont icon-hangzheng"></i>
		                    <span>行政办公</span>
		                </a> -->
		                <ul>
			                <c:if test="${fn:contains(gzwsession_page,',bimWork_gwsjsb,')==true || fn:contains(gzwsession_page,',bimWork_gwsjsh,')==true}">
			                	<li class="js_gnsq"><a href="${pageContext.request.contextPath}/queryPage/adDocument/query" target="mainFrame">公文审批及时率数据查看</a></li>
			                </c:if>
							 <c:if test="${fn:contains(gzwsession_page,',bimWork_dbsjsb,')==true || fn:contains(gzwsession_page,',bimWork_dbsjsh,')==true}">
								<li class="js_gnsq"><a href="${pageContext.request.contextPath}/queryPage/adSupervise/query" target="mainFrame">行政督办办结率数据查看</a></li>
							</c:if>
							 <c:if test="${fn:contains(gzwsession_page,',bimWork_bmfxsjsjsb,')==true || fn:contains(gzwsession_page,',bimWork_bmfxsjsjsh,')==true}">
			               		<li class="js_gnsq"><a href="${pageContext.request.contextPath}/queryPage/adRiskevent/query" target="mainFrame">保密风险事件数据查看</a></li>
			               	</c:if>
							 <c:if test="${fn:contains(gzwsession_page,',bimWork_yqsjsjsb,')==true || fn:contains(gzwsession_page,',bimWork_yqsjsjsh,')==true}">
								<li class="js_gnsq"><a href="${pageContext.request.contextPath}/queryPage/adImportant/query" target="mainFrame">要情台账数据查看</a></li>
							</c:if>
		                </ul>
	                </li>
                </c:if>
                </c:if>
                <c:if test="${ menu==4 }">
                <c:if test="${fn:contains(gzwsession_page,',bimWork_headCountSb,')==true || fn:contains(gzwsession_page,',bimWork_headCountSh,')==true || 
							  fn:contains(gzwsession_page,',bimWork_laborCostSb,')==true || fn:contains(gzwsession_page,',bimWork_laborCostSh,')==true ||
							  fn:contains(gzwsession_page,',bimWork_managerialStaffSb,')==true || fn:contains(gzwsession_page,',bimWork_managerialStaffSh,')==true }">
	                 <li>
	                    <ul>
	               			<c:if test="${fn:contains(gzwsession_page,',bimWork_headCountSb,')==true  || fn:contains(gzwsession_page,',bimWork_headCountSh,')==true}">
	               				<li class="js_sjsq"><a href="${pageContext.request.contextPath}/queryPage/headCount/list" target="mainFrame">总人数与劳动生产率查看</a></li>
	                       	</c:if>
	               			<c:if test="${fn:contains(gzwsession_page,',bimWork_laborCostSb,')==true  || fn:contains(gzwsession_page,',bimWork_laborCostSh,')==true}">
	               				<li class="js_sjsq"><a href="${pageContext.request.contextPath}/queryPage/laborCost/list" target="mainFrame">人工成本与资源回报率查看</a></li>
	                       	</c:if>
	               			<c:if test="${fn:contains(gzwsession_page,',bimWork_managerialStaffSb,')==true  || fn:contains(gzwsession_page,',bimWork_managerialStaffSh,')==true}">
	               				<li class="js_sjsq"><a href="${pageContext.request.contextPath}/queryPage/managerialStaff/list" target="mainFrame">管理干部数查看</a></li>
	                       	</c:if>
	                    </ul>
	                </li>
                </c:if>
                </c:if>
                <c:if test="${ menu==5 }">
                <c:if test="${fn:contains(gzwsession_page,',bimWork_sixRateKpTb,')==true || fn:contains(gzwsession_page,',bimWork_sixRateKpSh,')==true || 
							  fn:contains(gzwsession_page,',bimWork_sixRateDcTb,')==true || fn:contains(gzwsession_page,',bimWork_sixRateDcSh,')==true
							    }">
	                 <li>
	                   <!--  <a href="javascript:void(0)" target="mainFrame">
	                        <i class="iconfont icon-renliziyuan"></i>
	                        <span>市场占有率</span>
	                    </a> -->
	                    <ul>
	                    	<c:if test="${fn:contains(gzwsession_page,',bimWork_sixRateKpTb,')==true || fn:contains(gzwsession_page,',bimWork_sixRateKpSh,')==true}">
	               				<li class="js_sjsq"><a href="${pageContext.request.contextPath}/queryPage/sixRateKp/list" target="mainFrame">酷铺市场占有率查看</a></li>
               				</c:if>
	               			<c:if test="${fn:contains(gzwsession_page,',bimWork_sixRateDcTb,')==true || fn:contains(gzwsession_page,',bimWork_sixRateDcSh,')==true}">
	               				<li class="js_sjsq"><a href="${pageContext.request.contextPath}/queryPage/sixRateDc/list" target="mainFrame">地产市场占有率查看</a></li>
	                       	</c:if>
	                    </ul>
	                </li>
                </c:if>
                </c:if>
                <c:if test="${ menu==6 }">
                <c:if test="${fn:contains(gzwsession_page,',bimWork_voluntarySb,')==true || fn:contains(gzwsession_page,',bimWork_voluntarySh,')==true || 
							  fn:contains(gzwsession_page,',bimWork_mediaSb,')==true || fn:contains(gzwsession_page,',bimWork_mediaSh,')==true ||
							  fn:contains(gzwsession_page,',bimWork_brandSb,')==true || fn:contains(gzwsession_page,',bimWork_brandSh,')==true ||
							  fn:contains(gzwsession_page,',bimWork_newMediaSb,')==true || fn:contains(gzwsession_page,',bimWork_newMediaSh,')==true ||
							 fn:contains(gzwsession_page,',bimWork_consensusSb,')==true || fn:contains(gzwsession_page,',bimWork_consensusSh,')==true }">
	                <li>
	                    <!-- <a href="javascript:void(0)" target="mainFrame">
	                        <i class="iconfont icon-shzr"></i>
	                        <span>社责数据</span>
	                    </a> -->
	                    <ul>
	                    	<c:if test="${fn:contains(gzwsession_page,',bimWork_voluntarySb,')==true || fn:contains(gzwsession_page,',bimWork_voluntarySh,')==true}">
								<li class="js_sjsq"><a href="${pageContext.request.contextPath}/queryPage/social/voluntary/list" target="mainFrame">志愿服务与员工关爱数据查看</a></li>
							</c:if>
							
							<c:if test="${fn:contains(gzwsession_page,',bimWork_mediaSb,')==true || fn:contains(gzwsession_page,',bimWork_mediaSh,')==true}">
								<li class="js_sjsq"><a href="${pageContext.request.contextPath}/queryPage/social/media/list" target="mainFrame">媒体数据查看</a></li>
							</c:if>
							
							<c:if test="${fn:contains(gzwsession_page,',bimWork_brandSb,')==true || fn:contains(gzwsession_page,',bimWork_brandSh,')==true}">
								<li class="js_sjsq"><a href="${pageContext.request.contextPath}/queryPage/social/brand/list" target="mainFrame">核心品牌数据查看</a></li>
							</c:if>
							
							<c:if test="${fn:contains(gzwsession_page,',bimWork_newMediaSb,')==true || fn:contains(gzwsession_page,',bimWork_newMediaSh,')==true}">
								<li class="js_sjsq"><a href="${pageContext.request.contextPath}/queryPage/social/newMedia/list" target="mainFrame">新媒体数据查看</a></li>
							</c:if>
							
							<c:if test="${fn:contains(gzwsession_page,',bimWork_consensusSb,')==true || fn:contains(gzwsession_page,',bimWork_consensusSh,')==true}">
								<li class="js_sjsq"><a href="${pageContext.request.contextPath}/queryPage/social/consensus/list" target="mainFrame">舆情数据查看</a></li>
							</c:if>
							
	                    </ul>
	                </li>
                </c:if>
                </c:if>
                <c:if test="${ menu==7 }">
                <c:if test="${fn:contains(gzwsession_page,',bimWork_auditProjectSb,')==true || fn:contains(gzwsession_page,',bimWork_auditProjectSh,')==true || 
					  		fn:contains(gzwsession_page,',bimWork_contractSb,')==true || fn:contains(gzwsession_page,',bimWork_contractSh,')==true ||
					  		fn:contains(gzwsession_page,',bimWork_caseSb,')==true || fn:contains(gzwsession_page,',bimWork_caseSh,')==true ||
					  		fn:contains(gzwsession_page,',bimWork_accountSb,')==true || fn:contains(gzwsession_page,',bimWork_accountSh,')==true}">
					 <li>
	                    <!-- <a href="javascript:void(0)" target="mainFrame">
	                        <i class="iconfont icon-fk"></i>
	                        <span>风险控制</span>
	                    </a> -->
	                    <ul>
	                    	<c:if test="${fn:contains(gzwsession_page,',bimWork_auditProjectSb,')==true || fn:contains(gzwsession_page,',bimWork_auditProjectSh,')==true}">
	                    		<li class="js_sjsq"><a href="${pageContext.request.contextPath}/queryPage/riskcontrol/auditProject/list" target="mainFrame">审计项目查看</a></li>
	                    	</c:if>
	                    	
	                    	<c:if test="${fn:contains(gzwsession_page,',bimWork_contractSb,')==true || fn:contains(gzwsession_page,',bimWork_contractSh,')==true}">
	                    		<li class="js_sjsq"><a href="${pageContext.request.contextPath}/queryPage/riskcontrol/account/list" target="mainFrame">合同查看</a></li>
	                    	</c:if>
	                    	
	                    	<c:if test="${fn:contains(gzwsession_page,',bimWork_caseSb,')==true || fn:contains(gzwsession_page,',bimWork_caseSh,')==true}">
	                    		<li class="js_sjsq"><a href="${pageContext.request.contextPath}/queryPage/riskcontrol/case/list" target="mainFrame">案件信息查看</a></li>
	                    	</c:if>
	                    	
	                    	<c:if test="${fn:contains(gzwsession_page,',bimWork_accountSb,')==true || fn:contains(gzwsession_page,',bimWork_accountSh,')==true}">
	                    		<li class="js_sjsq"><a href="${pageContext.request.contextPath}/queryPage/riskcontrol/contract/list" target="mainFrame">合规台账查看</a></li>
	                    	</c:if>
	                    	
	                    </ul>
	                </li>	  
			 	</c:if>
			 	</c:if>
			 	<c:if test="${ menu==8 }">
	           	<c:if test="${fn:contains(gzwsession_page,',bimWork_zdgcsjtb,')==true || fn:contains(gzwsession_page,',bimWork_zdgcsjsh,')==true}">
		            <li>
		                 <!-- <a href="javascript:void(0)" target="mainFrame">
		                     <i class="iconfont icon-zhongdiangongcheng"></i>
		                     <span>重点基建工程</span>
		                 </a> -->
		                 <ul>
		                 	<c:if test="${fn:contains(gzwsession_page,',bimWork_zdgcsjtb,')==true || fn:contains(gzwsession_page,',bimWork_zdgcsjsh,')==true}">
								<li class="js_sjsq"><a href="${pageContext.request.contextPath}/queryPage/project/_list" target="mainFrame">重点基建工程数据查看</a></li>
		                 	</c:if>
		                 	
				         </ul>
		             </li>
                </c:if>
                </c:if>
			</ul>
			
			</div>
			<div class="right-content">
				<c:if test="${ !empty  gzwsession_page}">
					<iframe id="mainFrame" name="mainFrame" scrolling="auto" src="" frameborder="0" style="height: calc(100vh - 70px);padding: 0px; width: 100%;display:block;">
					</iframe>
					<!-- <iframe onload="resize_iframe_height(this)" id="mainFrame" name="mainFrame" scrolling="auto" src="" frameborder="0" style="mheight: calc(100vh - 70px);padding: 0px; width: 100%;display:block;">
					</iframe> -->

				</c:if>
				<c:if test="${ empty  gzwsession_page}">
					暂无权限，请联系管理员
				</c:if>
			</div>
		</div>
		
		
	</body>
	<script src="<c:url value="/js/jquery.slimscroll.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/jquery.mousewheel.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/circles.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/remodal.min.js"/>" charset="utf-8"></script>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script type="text/javascript">
		//左侧二级菜单显示
		/* $(".left_menu>li").hover(function() {
            if($(this)[0].className=="dis_check"){
                return;  //是否有权限进入
            }
            $(this).children('ul').removeClass('hidden');
            	菜单向上显示
            var h1 = $(window).height();
            var h2 = $(this).children('ul').height();
            var h3 = $(this).children('ul').offset().top;
            if((h2+h3)>=h1){
            	if(h2>h3){
            		return;
            	}else{
	            	$(this).children('ul').css({
	            		"top":"inherit",
	            		"bottom": 0
	            	})
            	}
            }
        }, function() {
            $(this).children('ul').addClass('hidden');
        });  */
		
		// 清理空格
		var toArray = Array.from || function(arrayLike) {
			return [].slice.call(arrayLike)
		};
		toArray(document.body.querySelectorAll('[no~=text]')).forEach(function(el) {
			toArray(el.childNodes).forEach(function(e) {
				if (e.nodeType == 3) {
					el.removeChild(e)
				}
			})
		})
		
		function winOpen(url,title){
			window.open(url,title,
				'height=600, width=1100, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');
		};
		

		
	</script>
	
	<script type="text/javascript">
		/* $('.left_menu').on('click','li',function(){
			$(this).parent().find('li a').removeClass('active');
			$(this).find('a').addClass('active');
		}) */
		$('.left_menu').on('click','a',function(){
			$('.left_menu a').removeClass('active');
			$(this).parent().parent().find('li a').removeClass('active');
			$(this).addClass('active');
			$(this).parent().children('ul').children('li').eq(0).children('a').addClass('active');
			if($(this).parent().parent().parent('li')[0]){
				$('.left_menu>li>a').removeClass('active');
				$(this).parent().parent().parent('li').children('a').addClass('active');
			}
		});
		
		setTimeout(function(){document.getElementById('mainFrame').src = $('.left_menu ul a').eq(0).trigger('click').prop('href')}, 500)
		
		//滚动条
	/* 	function setScroll(){
			$(".left_menu").slimScroll({
				height: boxHeight,
				alwaysVisible: true,
			});
		}
		setScroll();
		$(window).on("resize",setScroll); */
		/* $(function() {
		    $("#left_menu").slimScroll({
		        width: '90px', //可滚动区域宽度
		        height: 'calc(100vh - 70px)', //可滚动区域高度
		        size: '10px', //组件宽度
		        color: '#000', //滚动条颜色
		        position: 'left', //组件位置：left/right
		        distance: '0px', //组件与侧边之间的距离
		        start: 'top', //默认滚动位置：top/bottom
		        opacity: 0, //滚动条透明度
		        alwaysVisible: true, //是否 始终显示组件
		        disableFadeOut: true, //是否 鼠标经过可滚动区域时显示组件，离开时隐藏组件
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
		}); */
		/* $('#left_menu ul').each(function(i){
			var num = $(this).children('li').length;
			if(num<=6){
				$(this).children('span').remove()
			}
		}) */
		//一级菜单滚动
		/* $('#left_menu').on('mousewheel', function(event, delta) {
            var dir = delta > 0 ? 'Up' : 'Down';
            var menu_li = $(this).children('.hidden').length;
            if (dir == 'Down') {
            	if($(window).height()>=($(this).height()+70)){
	                return;
	            }
                $(this).children('li').eq(menu_li).addClass('hidden')
            } else {
                $(this).children('li').eq(menu_li-1).removeClass('hidden')
            }
            return false;
        }); */
		//二级菜单滚动
		/* $('#left_menu ul').on('mousewheel', function(event, delta) {
            if($(this).children('li').length<=6){
                return;
            }
            var dir = delta > 0 ? 'Up' : 'Down';
            var menu_li = $(this).children('.hidden').length;
            if (dir == 'Down') {
            	if(($(this).children('li').length-menu_li)<=6){
	            	return
	            }
                $(this).children('li').eq(menu_li).addClass('hidden')
                
            } else {
                $(this).children('li').eq(menu_li-1).removeClass('hidden')
            }
            return false;
        }); */
       /*  $('#left_menu ul>span').on('click', function(event, delta) {
            var dir = $(this).attr('class')
            var menu_li = $(this).siblings('.hidden').length;
            console.log(dir,menu_li)
            if (dir == 'down') {
            	console.log(($(this).siblings('li').length))
            	if(($(this).siblings('li').length-menu_li)<=6){
	            	return
	            }
                $(this).siblings('li').eq(menu_li).addClass('hidden')
                
            } else {
                $(this).siblings('li').eq(menu_li-1).removeClass('hidden')
            }
            return false;
        }); */
	</script>
	
</html>