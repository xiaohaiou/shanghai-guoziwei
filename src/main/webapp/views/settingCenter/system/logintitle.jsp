<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>海航物流运营管控平台</title>
<%-- 
<link href="${pageContext.request.contextPath}/style/lin.css"
	rel="stylesheet" type="text/css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/style/bootstrap.min.css" />

<link rel="stylesheet" href="<c:url value="/css/font/iconfont.css"/>">
<link rel="stylesheet" href="<c:url value="/css/header.css"/>">
 --%>

</head>

<style>
    	a{
				color: #4f5358;
			}
			
			.i_btn{
				overflow: visible;
				color: #4f5358;
			}
			
			/* 二级菜单 */
			.l_top{
				overflow: visible;
				z-index:2;
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
				width: 390px;
				background: #161c22;
				display: none;
				z-index: 100;
			}
			
			.one_nav{
				display: block;
				width: 390px;
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
				width:320px;
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
			
			/*时钟·······································*/
			/* #clock_box{
				display: none;
				cursor: default;
				overflow: hidden;
				z-index:10;
			} */
			/*图表hover*/
			/* .i_btn:hover>#out{
				border-color: #fff;
				transition: all 0.1s linear;
			}
			
			.i_btn:hover>#out>li{
				background: #fff;
				transition: all 0.1s linear;
			} */
			/*图标*/
			/* #out{
				width: 34px;
				height: 34px;
				border: 2px solid #4f5358;
				border-radius: 50%;
				background: rgba(255,255,255,0);
				position: absolute;
				left: 0;
				right: 0;
				top: 0;
				bottom: 0;
				margin: auto;
				overflow: hidden;
			}
			
			.hour{
				width: 2px;
				height: 10px;
				background: #4f5358;
				position: absolute;
				left: 14px;
				top: 5px;
				transform-origin: bottom;
				overflow: hidden;
			}
			.minute{
				width: 2px;
				height: 15px;
				position: absolute;
				background: #4f5358;
				left: 14px;
				top: 0;
				transform-origin: bottom;
				overflow: hidden;
			}
			.second{
				width: 2px;
				height: 15px;
				position: absolute;
				background: #4f5358;
				left: 14px;
				top: 0px;
				transform-origin: bottom;
				overflow: hidden;
			}
			.dot{
				width: 4px;
				height: 4px;
				border-radius: 50%;
				background: #4f5358;
				position: absolute;
				top: 0;
				bottom: 0;
				left: 0;
				right: 0;
				margin: auto;
				overflow: hidden;
			} */
			
			
			/*时钟box*/
			/* #clock_box{
				background: #161c22;
				position: absolute;
				right: 0;
				top: 70px;
			} */
			
			/*上方大时钟*/
			/* .clock_top{
				width: 260px;
				height: 186px;
				text-align: center;
				background: #161c22;
				overflow: hidden;
				position: relative;
			}
			#out1{
				width: 84px;
				height: 84px;
				border: 2px solid #4f5358;
				border-radius: 50%;
				background: rgba(255,255,255,0);
				position: absolute;
				left: 0;
				right: 0;
				top: 30px;
				margin: auto;
				overflow: hidden;
			}
			
			.hour1{
				width: 2px;
				height: 20px;
				background: #4f5358;
				position: absolute;
				left: 39px;
				top: 20px;
				transform-origin: bottom;
				overflow: hidden;
			}
			.minute1{
				width: 2px;
				height: 40px;
				position: absolute;
				background: #4f5358;
				left: 39px;
				top: 0;
				transform-origin: bottom;
				overflow: hidden;
			}
			.second1{
				width: 2px;
				height: 40px;
				position: absolute;
				background: #4f5358;
				left: 39px;
				top: 0px;
				transform-origin: bottom;
				overflow: hidden;
			}
			.dot1{
				width: 4px;
				height: 4px;
				border-radius: 50%;
				background: #4f5358;
				position: absolute;
				top: 0;
				bottom: 0;
				left: 0;
				right: 0;
				margin: auto;
				overflow: hidden;
			}
			
			.time_explain,.area_time{
				position: absolute;
				left: 0;			
				right: 0;
				top: 132px;
				font-size: 16px;
				color: #aeaeae;
				margin: auto;
				line-height: 0;
				
			}
			
			.area_time{
				font-size: 12px;
				top: 156px;
				color: #686f7b;
			} */
			
			/*下方的时钟*/
			
			.area_clock{
				width: 130px;
				height: 180px;
				background: #1d2228;
				float: left;
				position: relative;
			}
			
			/*.area_top{
				height: 130px;
			}*/
			/*小时钟样式*/
			
			
		/* 	.out_small{
				width: 64px;
				height: 64px;
				border: 2px solid #4f5358;
				border-radius: 50%;
				background: rgba(255,255,255,0);
				position: absolute;
				left: 0;
				right: 0;
				top: 16px;
				margin: auto;
				overflow: hidden;
			}
			
			.hour_small{
				width: 2px;
				height: 14px;
				background: #4f5358;
				position: absolute;
				left: 29px;
				top: 16px;
				transform-origin: bottom;
				overflow: hidden;
			}
			.minute_small{
				width: 2px;
				height: 30px;
				position: absolute;
				background: #4f5358;
				left: 29px;
				top: 0;
				transform-origin: bottom;
				overflow: hidden;
			}
			.second_small{
				width: 2px;
				height: 30px;
				position: absolute;
				background: #4f5358;
				left: 29px;
				top: 0px;
				transform-origin: bottom;
				overflow: hidden;
			}
			.dot_small{
				width: 4px;
				height: 4px;
				border-radius: 50%;
				background: #4f5358;
				position: absolute;
				top: 0;
				bottom: 0;
				left: 0;
				right: 0;
				margin: auto;
				overflow: hidden;
			}
			
			.time_explain_small,.area_date_small,.area_time_small{
				position: absolute;
				left: 0;			
				right: 0;
				top: 100px;
				font-size: 16px;
				color: #aeaeae;
				margin: auto;
				line-height: 0;
				
			}
			
			.area_date_small{
				font-size: 12px;
				color: #686f7b;
				top: 124px;
			}
			
			.area_time_small{
				font-size: 12px;
				color: #686f7b;
				top: 146px;
			}
			 */
			/*上方小时钟的样式*/
			/* .area_top>.out_small{
				top: 30px;
			}
			
			.area_top>.time_explain_small{
				top: 114px;
			}
			
			.area_top>.area_date_small{
				top: 138px;
			}
			
			.area_top>.area_time_small{
				top: 160px;
			} */
			
			/*移入时钟，让时钟变白*/
		/* 	.clock_top:hover>#out1{
				border: 2px solid #fff;
			}
			
			.clock_top:hover>div>li{
				background: #fff;
			}
			
			.clock_top:hover span{
				color: #fff;
			}
			
			.area_clock:hover>div{
				border: 2px solid #fff;
			}
			
			.area_clock:hover>div>li{
				background: #fff;
			}
			
			.area_clock:hover span{
				color: #fff;
			}
			 */
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
		
	    
</style>
<body class="hn_page" onload="init()">
	<script type="text/javascript">
		var to_def_img = function(el) {
			//el.src = "<c:url value="/img/head.png"/>";
			el.src="http://file.hna.net/image/headimage/emp/1000040000/1000043612.jpg";
		};
	</script>

	<div no=text class="l_top">

		
		
	


		
		<div class="menu time"></div>
		
		<!--  -->
		
		

		
				 <a class="menu i i_btn exit" style="color:white" id="menhuurl" href="/bim_show/login.jsp"><i
						class="iconfont icon-logout" title="退出"></i>
					</a>
					<!--
					<a id="nav1" class="one_nav" href="${pageContext.request.contextPath}/ssoout/outsystem?isopen=true&url=http://holdingdsp.hna.net/analysis/toIndex.do" target="_blank" title="海航实业BIM管理数据共享与交换平台">
						<img src="${pageContext.request.contextPath}/img/datachange.jpg" alt="" />
						<span>海航物流BIM管理数据共享与交换平台</span>
					</a>
					<a id="nav2" class="one_nav" href="${pageContext.request.contextPath}/ssoout/outsystem?isopen=true&url=http://omc.hnainfrastructure.com/hna_assistant/AdminMain/Index" target="_blank" title="海航基础运营管控平台">
						<img src="${pageContext.request.contextPath}/img/hn.png" alt="" />
						<span>海航基础运营管控平台</span>
					</a>-->
					<!--
					<a id="nav3" class="one_nav" href="${pageContext.request.contextPath}/ssoout/outsystem?isopen=true&url=http://10.71.114.21/Account/Login" target="_blank" title="海航教育医疗运营管控平台">
						<img src="${pageContext.request.contextPath}/img/zhwu.png" alt="" /><span>海航教育医疗运营管控平台</span>
					</a>
					<a id="nav3" class="one_nav" href="${pageContext.request.contextPath}/ssoout/outsystem?isopen=true&url=http://10.123.16.172:19000/workspace/index.jsp" target="_blank" title="海航基础经营管控平台">
						<img src="${pageContext.request.contextPath}/img/zhwu.png" alt="" /><span>海航基础经营管控平台</span>
					</a>
				
					<a id="nav3" class="one_nav" href="${pageContext.request.contextPath}/ssoout/outsystem?isopen=true&url=http://10.74.246.247:9330/ccoop" target="_blank" title="供销大集">
						<img src="${pageContext.request.contextPath}/img/zhwu.png" alt="" /><span>供销大集</span>
					</a>
					<a class="one_nav" href="${pageContext.request.contextPath}/ssoout/outsystem?isopen=true&url=http://10.120.0.54:7001/product/faces/page/witp/ssoLogin.jsf" title="海航地产运营管理平台" target="_blank">
							<img icon src="${pageContext.request.contextPath}/img/hn.png" alt="">
							<span>海航地产运营管理平台</span>
					</a>
					<a class="one_nav" href="${pageContext.request.contextPath}/ssoout/outsystem?isopen=true&url=http://10.127.0.192:8080/hhfbim/main/index" title="海航金融运营管控平台" target="_blank">
							<img icon src="${pageContext.request.contextPath}/img/hn.png" alt="">
							<span>海航金融运营管控平台</span>
					</a>
					<a class="one_nav" href="${pageContext.request.contextPath}/ssoout/outsystem?isopen=true&url=http://10.70.73.49:8089/bim/index" title="海建BIM平台" target="_blank">
							<img icon src="${pageContext.request.contextPath}/img/zhwu.png" alt="">
							<span>海建BIM平台</span>
					</a>
					<a class="one_nav" href="${pageContext.request.contextPath}/ssoout/outsystem?isopen=true&url=http://10.127.26.182/scoreokr-web/index/index" title="管理干部履职积分系统" target="_blank">
							<img icon src="${pageContext.request.contextPath}/img/zhwu.png" alt="">
							<span>管理干部履职积分系统</span>-->
					<a class="one_nav" href="${pageContext.request.contextPath}/ssoout/outsystem?isopen=true&url=http://10.71.201.29:8900/login" title="海航现代物流BIM运营管控平台" target="_blank">
							<img icon src="${pageContext.request.contextPath}/img/zhwu.png" alt="">
							<span>海航现代物流BIM运营管控平台-测试系统</span>
					</a>
					<a class="one_nav" href="${pageContext.request.contextPath}/ssoout/outsystem?isopen=true&url=http://10.70.67.46:8900/login?url=operationmoniter/process/list" title="海航现代物流BIM运营管控平台" target="_blank">
							<img icon src="${pageContext.request.contextPath}/img/zhwu.png" alt="">
							<span>现代物流BIM中流程管理页面（即领导用户主页）</span>
					</a>
					<a class="one_nav" href="${pageContext.request.contextPath}/ssoout/outsystem?isopen=true&url=http://10.70.67.46:8900/login?url=monitor/aviationfreight" title="航空货运" target="_blank">
							<img icon src="${pageContext.request.contextPath}/img/zhwu.png" alt="">
							<span>航空货运</span>
					</a>
					<a class="one_nav" href="${pageContext.request.contextPath}/ssoout/outsystem?isopen=true&url=http://10.70.67.46:8900/login?url=monitor/storageinvest" title="仓储投资" target="_blank">
							<img icon src="${pageContext.request.contextPath}/img/zhwu.png" alt="">
							<span>地面物流</span>
					</a>
					<a class="one_nav" href="${pageContext.request.contextPath}/ssoout/outsystem?isopen=true&url=http://10.119.48.14:8088/portal/coe/pal/" title="海航现代物流流程管理平台" target="_blank">
							<img icon src="${pageContext.request.contextPath}/img/zhwu.png" alt="">
							<span>海航现代物流流程管理平台</span>
					</a>
	</div>

	
	<!--<img src="img/home.jpg" alt="" width="1920" height="1080" usemap="#Map" border="0"/>

<map name="Map">

  <area shape="circle" coords="313,330,162" href="v.htm">

</map>-->
<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
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
			
		});
		
		$('.show_nav_box').on('mouseover', function () {
			$('#nav_box').show();
		});
		
		$('.show_nav_box').on('mouseleave', function () {
			$('#nav_box').hide();
		});
		// 时钟
		var clockBox = true;
		
		$('.show_clock_box').on('click', function (event) {
			event.stopPropagation();
			if(clockBox) {
				$('#clock_box').show();
				clockBox = false;
			} else {
				$('#clock_box').hide();
				clockBox = true;
			}
		});

		$('.show_clock_box').on('mouseover', function () {
			$('#clock_box').show();
		});
		
		$('.show_clock_box').on('mouseleave', function () {
			$('#clock_box').hide();
		});

		// 清理空格
		var toArray = Array.from || function(arrayLike) {
			return [].slice.call(arrayLike);
		};
		toArray(document.body.querySelectorAll('[no~=text]')).forEach(
				function(el) {
					toArray(el.childNodes).forEach(function(e) {
						if (e.nodeType == 3) {
							el.removeChild(e);
						}
					});
				});

		// 调整头像大小
		var avatar_resize = function(el) {
			if (el.naturalWidth < el.naturalHeight) {
				el.style.width = "100%";
				el.style.height = "auto";
			}
		};
		toArray(document.body.querySelectorAll('.avatar>img')).forEach(function(el) {
			avatar_resize(el);
			el.onload = function() {
				avatar_resize(el);
				el.onload = undefined;
			};
		});
	</script>
	
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

	
	
	
</body>

</html>