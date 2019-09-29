<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="<c:url value="/css/heaader.css"/>" />
<style>
    	a{
				color: #4f5358;
			}
			.menu.i{
				height:70px;
			}
			
			.i_btn{
				overflow: visible;
				color: #4f5358;
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
			
			
			/*下方的时钟*/
			
			.area_clock{
				width: 130px;
				height: 180px;
				background: #1d2228;
				float: left;
				position: relative;
			}
			
			
			.i_btn>.grnum {	
		    display: block;
		    position: absolute;
		    top: 12px;
		    right: 6px;
		    font-size: 12px;
		    height: 16px;
		    line-height: 15px;
		    padding: 0 4px;
		    color: #fff;
		    background: green;
		    border: 1px solid #fff;
		    border-radius: 6px;
		}
		.avatar>img{
			width:100%;
			height:initial
		}
	    
</style>

	<script type="text/javascript">
		var to_def_img = function(el) {
			//el.src = "<c:url value="/img/head.png"/>";
			//el.src="http://file.hna.net/image/headimage/emp/1000040000/1000043612.jpg";
		};
	</script>
	<script>
		// 整页面缩放 仅针对 pc 模拟移动设备viewpoint标签效果
		/*if(!/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)){
			// 设计宽度  1920
			var resize_to_width = 1920;
			var doc = document.documentElement;
			doc.style.height = '100%'
			doc.style.width = '100%'
			// 手动计算缩放比例, 使之适应部分缩放范围
			var calc_scale = function(){
				// 清除滚动条
				doc.style.overflow = 'hidden'
				if(doc.offsetWidth > resize_to_width*2 || doc.offsetWidth < 1024){
					doc.style.overflow = 'auto'
					return
				}
				var scale = doc.offsetWidth/resize_to_width
				
				var body = document.body;
				body.style.width = resize_to_width + 'px';
				body.style['transform-origin'] = '0 0'
				body.style.transform = 'scale('+scale+')'
				
				// 调整高度 仅当页面为整屏幕设计
				var should_be_height = resize_to_width*(doc.offsetHeight/doc.offsetWidth);
				body.style.height = should_be_height + 'px';
				
				// 恢复滚动条
				doc.style.overflow = 'auto'
			}
			window.addEventListener('load',calc_scale);
			window.addEventListener('resize',calc_scale);
			calc_scale()
		}*/
	</script>

<header>
	<div no=text class="l_top l_top_bg2">
		<p class="l_logo_p1" style="left:16px;">上海市国资委国资监管风控大数据平台</p>
		<p class="l_logo_p2" style="left:16px;">SHANGHAI SASAC BIG DATA</p>
		<div class="menu time"></div>
		<div class="menu user" no="text">
			<div>
				<div class="name">${gzwsession_users.vcName}</div>
			</div>
			<div class="avatar">
					<img src="<c:url value="http://file.hna.net/image/headimage/emp/${fn:substring(gzwsession_users.vcEmployeeId,0,6)}0000/${gzwsession_users.vcEmployeeId}.jpg"/>" onerror="to_def_img(this)" alt="头像">
			</div>
		</div> 
		<a class="menu i i_btn" no=text
			href="${pageContext.request.contextPath}/index/index?pageId=1" title="主题分析">
			<i class="iconfont icon-shouye"></i>
		</a> 
		<c:if test="${fn:contains(gzwsession_page, ',bimWork_title,') && (fn:contains(gzwsession_code, ',bimWork_s6,')|| fn:contains(gzwsession_code, ',bimWork_s5,')|| fn:contains(gzwsession_code, ',bimWork_bima,')|| fn:contains(gzwsession_code,',bimWork_datachange,') || fn:contains(gzwsession_code, ',bimWork_s1,') || fn:contains(gzwsession_code, ',bimWork_s3,') || fn:contains(gzwsession_code, ',bimWork_s4,') || fn:contains(gzwsession_code, ',bimWork_s2,'))}">
		<a id="otherSystem" class="menu i i_btn show_nav_box" no=text title="系统集成中心" href="${pageContext.request.contextPath}/ssoout/outsystem?isopen=true&url=/bim_show/index/bimIndex" target = "_blank">
			<i class="iconfont icon-yyzx"></i>
			<div class="sanjiao"></div>
		</a>
		</c:if>
		<a class="menu i i_btn" no=text href="${pageContext.request.contextPath}/system/ladp_portal" title="工作台">
			<i class="iconfont icon-gongzuotai"></i>
		</a>
		<a class="menu i i_btn" no=text href="${pageContext.request.contextPath}/suggestion/_list" title="意见反馈" target="_blank">
					<i class="iconfont icon-fankui"></i>
		</a>
		
		<a class="menu i i_btn exit" id="menhuurl" href="/shanghai-gzw/system/_exit">
			<i class="iconfont icon-logout" title="退出"></i>
		</a>
	</div>
</header>

	<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
<script type="text/javascript">


		//调整头像大小
		var avatar_resize = function(el){
			if (el.naturalWidth < el.naturalHeight) {
				el.style.width = "100%";
				el.style.height = "auto";
			}
		}
		// 清理空格
		var toArray = Array.from || function(arrayLike) {
			return [].slice.call(arrayLike);
		};
		
		toArray(document.body.querySelectorAll('.avatar>img')).forEach(function(el) {
			avatar_resize(el)
			el.onload = function(){
				avatar_resize(el);
				el.onload = undefined;
			}
		})
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
			if($.inArray("bs_os1", ButtonList)==-1){
				$('#nav1').hide();
			}
			if($.inArray("bs_os2", ButtonList)==-1){
				$('#nav2').hide();
			}
			if($.inArray("bs_os3", ButtonList)==-1){
				$('#nav3').hide();
			}
		})
		
		$('.show_nav_box').on('mouseleave', function () {	
			$('#nav_box').hide();
		})
</script>
