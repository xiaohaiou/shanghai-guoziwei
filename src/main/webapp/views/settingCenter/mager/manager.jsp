<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>后台管理中心</title>
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/font/iconfont.css"/>" />
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/heaader.css"/>" />
		<!-- 库|插件 -->
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/remodal.css"/>">
		<link rel="stylesheet" href="<c:url value="/settingCenter/css/remodal-default-theme.css"/>">
		<style type="text/css">
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
				position:absolute;
				left:84px;
				top:0;
				width: 10em;
				background: #121c25;
    			line-height: 2em;
			}
			.left_menu>li>ul>li{
				text-align:left;
			}
			.left_menu>li>ul>li>a{
				padding-left:1.5em;
				color: #aaa;
				font-size:14px;
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
			.hn_grey{
				overflow:auto;
			}
			.left_menu{
				height: auto !important;
			}
			.l_top {
			    overflow: visible;
			    z-index: 99;
			}
		</style>
		<script type="text/javascript">
			var to_def_img = function(el){
				el.src = "<c:url value="/img/head.png"/>";
			}
		</script>
	</head>
		<body class="hn_grey">
		<!-- 
		<div no=text class="l_top l_top_bg2">

			<img src="<c:url value="/img/hn_wl_logo1.png"/>" alt="header" class="l_logo">
			<p class="l_logo_p1">上海市国资委国资监管风控大数据平台</p>
			<p class="l_logo_p2">SHANGHAI SASAC BIG DATA</p>
			
			<div class="menu user" no="text">
				<div>
					<div class="name">${gzwsession_users.vcName}</div>
				</div>
				
				<div class="avatar">
						<img src="${gzwsession_users.employeeImg}" onerror="to_def_img(this)" alt="头像">
				</div>
				
			</div>
			<a class="menu i i_btn" id="menhuurl" href="" onclick="javascript:window.opener=null;window.open('','_self');window.close();">
			  <i class="iconfont icon-guanbi" title="退出"></i>
			</a>
			
		</div>
		 -->
		<jsp:include page="/views/public/title.jsp"></jsp:include>
		<div class="container-fluid sec-maincontent">
			<ul class="left_menu" id="left_menu">
			<c:if test="${fn:contains(bim_work_session_page,',work_xtzc,')==true || fn:contains(bim_work_session_page,',work_mkzc,')==true ||
				  fn:contains(bim_work_session_page,',work_ymzc,')==true || fn:contains(bim_work_session_page,',work_gnzc,')==true ||
				  fn:contains(bim_work_session_page,',work_jsgl,')==true || fn:contains(bim_work_session_page,',work_mrmkpz,')==true ||
				  fn:contains(bim_work_session_page,',work_dlrz,')==true || fn:contains(bim_work_session_page,',work_yhgl,')==true}">
				<c:if test="${fn:contains(bim_work_session_page,',work_xtzc,')==true}">
					<li>
						<a href="${pageContext.request.contextPath}/sys/register/_query" target="mainFrame" class="">
							<i class="iconfont icon-menhu"></i>
							<span>系统注册</span>
						</a>
					</li>
				</c:if>
				<c:if test="${fn:contains(bim_work_session_page,',work_mkzc,')==true}">
				<li>
					<a href="${pageContext.request.contextPath}/model/register/_query" target="mainFrame" class="">
						<i class="iconfont icon-shzr"></i>
						<span>模块注册</span>
					</a>
				</li>
				</c:if>
				<c:if test="${fn:contains(bim_work_session_page,',work_ymzc,')==true}">
				<li>
					<a href="${pageContext.request.contextPath}/page/register/_query" target="mainFrame" class="">
						<i class="iconfont icon-xiugai"></i>
						<span>页面注册</span>
					</a>
				</li>
				</c:if>
				<c:if test="${fn:contains(bim_work_session_page,',work_gnzc,')==true}">
				<li>
					<a href="${pageContext.request.contextPath}/page/code/_query" target="mainFrame" class="">
						<i class="iconfont icon-xinzeng"></i>
						<span>功能注册</span>
					</a>
				</li>
				</c:if>
				<c:if test="${fn:contains(bim_work_session_page,',work_jsgl,')==true}">
				 <li>
                    <a href="javascript:void(0)" target="mainFrame">
                        <i class="iconfont icon-zichanguanli"></i>
                        <span>角色管理</span>
                    </a>
                </li>
				</c:if>
				<c:if test="${fn:contains(bim_work_session_page,',work_mrmkpz,')==true}">
				<li>
					<a href="${pageContext.request.contextPath}/model/user/_query"  target="mainFrame" class="">
						<i class="iconfont icon-menhuhoutai"></i>
						<span>默认模块配置</span>
					</a>
					
				</li>
				</c:if>
				<c:if test="${fn:contains(bim_work_session_page,',work_dlrz,')==true}">
				<li>
					<a href="${pageContext.request.contextPath}/portal/log/_query"  target="mainFrame" class="">
						<i class="iconfont icon-tuoyuan1kaobei"></i>
						<span>登录日志</span>
					</a>
				</li>
				</c:if>
				<c:if test="${fn:contains(bim_work_session_page,',work_yhgl,')==true}">
				<li>
					<a href="${pageContext.request.contextPath}/portal/user/_query"  target="mainFrame" class="">
						<i class="iconfont icon-tuoyuan1kaobei"></i>
						<span>用户管理</span>
					</a>
				</li>
				</c:if>
			</c:if>
			</ul>
			<div class="right-content">
				<iframe onload="resize_iframe_height(this)" id="mainFrame" name="mainFrame" scrolling="no" src="" frameborder="0" style="min-height: calc(100vh - 70px);padding: 0px; width: 100%;display:block;max-height: calc(100vh - 70px); height="calc(100vh-70px);"">
				</iframe>
			</div>
		</div>
	</body>
	<div class="modal-container" data-remodal-id="modal" no=text>
		<iframe id="modal-frame" src="" style></iframe>
		<a href="javascript:mclose()" class="modal-close"><img src="../../settingCenter/css/icon/x.svg" alt=""></a>
	</div>
	<script src="<c:url value="/settingCenter/js/jquery-1.8.3.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/settingCenter/js/circles.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/settingCenter/js/remodal.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/settingCenter/js/jquery.mousewheel.min.js"/>" charset="utf-8"></script>
	<%-- <script type="text/javascript" src="<c:url value="/js/mCustomScrollbar.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.slimscroll.min.js"/>"></script> --%>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script>
	$('#left_menu').on('mousewheel', function(event, delta) {
	    if($(".container-fluid").height()>=$("#left_menu").height()){
        	//console.log("123");
	        return;
	    }
	    var dir = delta > 0 ? 'Up' : 'Down';
	    console.log(delta)
	    var top = Number($('#left_menu').css('top').slice(0, -2));
	    // console.log(-$('#left_menu').height()+102*4);
	    if (dir == 'Down') {
	        if(top<=(-$('#left_menu').height()+89*7)){
	            return
	        }else{
	            top-=89;
	            $('#left_menu:not(:animated)').animate({
	                "top": top+"px"},
	                200, function() {
	                    //console.log("up",top);
	            });
	        }
	    } else {
	        if(top>=0){
	            return
	        }else{
	            top+=89;
	            $('#left_menu:not(:animated)').animate({
	                "top": top+"px"
	                },200, function() {
	                    //console.log("down",top);
	            });
	        }
	    }
	    return false;
	}); 
	</script>
	<script type="text/javascript">
		/* $(".left_menu").slimScroll({
            
        }) */
        /* $(".left_menu").mCustomScrollbar({
           /*  height: 'calc(100vh - 75px)',
             width: 'calc(10vw + 165px)', //可滚动区域宽度 
        }) */
		//左侧二级菜单显示
		$(".left_menu>li").hover(function() {
            if($(this)[0].className=="dis_check"){
                return;  //是否有权限进入
            }
            $(this).children('ul').removeClass('hidden');
        }, function() {
            $(this).children('ul').addClass('hidden');
        }); 
		
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
		

		// 调整头像大小
		var avatar_resize = function(el){
			if (el.naturalWidth < el.naturalHeight) {
				el.style.width = "100%";
				el.style.height = "auto";
			}
		}
		toArray(document.body.querySelectorAll('.avatar>img')).forEach(function(el) {
			avatar_resize(el)
			el.onload = function(){
				avatar_resize(el);
				el.onload = undefined;
			}
		})
	</script>
	<script type="text/javascript">
		// modal
		window.modal_load = window.mload = function(url,callback){
			if(url){
				var ifm = document.getElementById('modal-frame');
					$('>.modal-close',ifm.parentNode).css('display','block')
				ifm.onload = function(){
					ifm.onload = undefined;
					$('>.modal-close',ifm.parentNode).css('display','')
					var h = ifm.contentDocument.body.scrollHeight;
					ifm.style.height = h+'px'
					modal_resize();
					if(callback){
						setTimeout(callback.bind(ifm.contentWindow))
					}
				};
				ifm.src = url;
				$('[data-remodal-id=modal]').remodal({
					closeOnEscape:false,
					closeOnOutsideClick:false,
				}).open();
			}
		}
		window.modal_close = window.mclose = function(){
			var ifm = $('[data-remodal-id=modal] iframe')[0];
			$('[data-remodal-id=modal]').remodal().close();
			ifm.src = '';
			ifm.style.height = '';
		}
		// modal - 居中
		var modal_resize = function(){
			var ifm = document.getElementById('modal-frame');
			var hc = ifm.offsetHeight;
			if(hc == 0) return;
			var h = parseInt(ifm.style.height) || 0
			var c = ifm.parentNode
			var padding = (c.offsetHeight - hc)/2
			if(hc == h){
				c.style['padding-top'] = padding + 'px'
				c.style['padding-bottom'] = padding + 'px'
			}else{
				c.style['padding-top'] = ''
				c.style['padding-bottom'] = ''
			}
		}
		$(window).resize(modal_resize);
	</script>
	<script type="text/javascript">
		/* $('.left_menu').on('click','li',function(){
			$(this).parent().find('li a').removeClass('active');
			$(this).find('a').addClass('active');
		}) */
		$('.left_menu').on('click','a',function(){
			$(this).parent().parent().find('li a').removeClass('active');
			$(this).addClass('active');
			$(this).parent().children('ul').children('li').eq(0).children('a').addClass('active');
			if($(this).parent().parent().parent('li')[0]){
				$('.left_menu>li>a').removeClass('active');
				$(this).parent().parent().parent('li').children('a').addClass('active');
			}
		});
	</script>
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
		
		function openNewWindow(url) {
			window.open(url, "_blank");
		}
		
		function winOpen(url,title){
			window.open(url,title,
				'height=600, width=1100, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');
		};
		
		function openzhilinwindow(){
		   // var nowDate= new Date();
		   // var formatdate=dateFtt("yyyy-MM-dd",nowDate) ;
		   // var url="/bim_task/system/index?vcEmployeeId=${portal_users_session.vcEmployeeId}&taskOrInstruction=dealing";
		   var url="/bim_task/system/index?systemCode=portal&token=${token}";
		   window.open(url, "_blank");
		}
		
		var navBox = true;
		
		$('.show_nav_box').on('click', function (event) {
			event.stopPropagation();
			if(navBox) {
				$('#nav_box').show();
				navBox = false;
			} else {
				$('#nav_box').hide();
				navBox = true;
			}
			
		})
		
		$('.show_nav_box').on('mouseover', function () {
			$('#nav_box').show();
		})
		
		$('.show_nav_box').on('mouseleave', function () {
			$('#nav_box').hide();
		})
	
	function isClose(){
			window.close();
		}
	</script>
</html>