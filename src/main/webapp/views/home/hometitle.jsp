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
<title>海航实业运营管控平台</title>

<link href="${pageContext.request.contextPath}/style/lin.css"
	rel="stylesheet" type="text/css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/style/bootstrap.min.css" />

<link rel="stylesheet" href="<c:url value="/css/font/iconfont.css"/>">
<link rel="stylesheet" href="<c:url value="/css/header.css"/>">


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
			//el.src="http://file.hna.net/image/headimage/emp/1000040000/1000043612.jpg";
		};
	</script>

	<div no=text class="l_top">

		<img src="${pageContext.request.contextPath}/img/hn_logo1.png"
			alt="海航实业运营管控平台" class="l_logo" />
		<c:choose>
			<c:when test="${bimShow_orgNm == '海航实业'}">
				<p class="l_logo_p1">运营管控平台V1.0（物流总部）</p>
			</c:when>
			<c:otherwise>
				<p class="l_logo_p1">运营管控平台V1.0（${sessionScope.bimShow_orgNm}）</p>
			</c:otherwise>
		</c:choose>
		<p class="l_logo_p2">OPERATION CONTROL PLATFORM</p>


		<!--<ul class="menu menu_ul">
				<li><a href="/bim_show/views/home/homepage.jsp" class="cur">首页</a>
				</li>
				  
				<li class="hn_menu_li"><a href="javascript:void(0)" class="hn_menu_a">海航基础</a>
				</li>
				<li class="hn_menu_li"><a href="javascript:void(0)" class="hn_menu_a">供销大集</a>
				</li>
				<li class="hn_menu_li"><a href="javascript:void(0)" class="hn_menu_a">海航金融</a>
				</li>
				<li class="hn_menu_li"><a href="javascript:void(0)" class="hn_menu_a">海航资产</a>
				</li>
				<li class="hn_menu_li"><a href="javascript:void(0)" class="hn_menu_a">海航教育医疗</a>
				</li>
				
				<li><a id="menhuurl" href="http://10.123.16.168:8080/bim_portal/system/ladp_portal?vcEmployeeId=9999999901" class="menu a">返回门户</a>
				</li>
			</ul>-->

		<div class="menu time"></div>
		<div class="menu user" no="text">
			<div>
				<div class="name">${sessionScope.show_session_users.vcName}</div>
				<!--<div class="title">海航实业运行总裁</div>-->
			</div>
			<div class="avatar">
				<img
					src="<c:url value="http://file.hna.net/image/headimage/emp/${fn:substring(sessionScope.show_session_users.vcEmployeeId,0,6)}0000/${sessionScope.show_session_users.vcEmployeeId}.jpg"/>"
					onerror="to_def_img(this)" alt="头像">
			</div>
		</div>
		
		
		<a class="menu i i_btn" no=text
			href="/bim_show/index/index?pageId=1"
			 title="首页"><i
			class="iconfont icon-shouye"></i>
		</a>

		<!-- <c:if test="${fn:contains(bimShow_PageList,'&bimShow_title&') && fn:contains(bimShow_ButtonList,'&bimShow_order&')}">
			<a id="orderPage" class="menu i i_btn" no=text 
				href="${pageContext.request.contextPath}/ssoout/outsystem?isopen=true&url=/bim_task/system/indexFirst?taskOrInstruction=dealing"
				target = "_blank" title="指令中心">
				<i class="iconfont icon-zhiling"></i><div id="zlmnum" class="grnum">0</div>
			</a>
		</c:if> -->
		
		<a id="orderPage" class="menu i i_btn" no=text 
			href="${pageContext.request.contextPath}/ssoout/outsystem?isopen=true&url=/bim_task/system/indexFirst?taskOrInstruction=dealing"
			target = "_blank" title="指令中心">
			<i class="iconfont icon-zhiling"></i><div id="zlmnum" class="grnum">0</div>
		</a>
		
		<c:if test="${fn:contains(bimShow_PageList,'&bimShow_title&') && fn:contains(bimShow_ButtonList,'&bimShow_alarm&')}">
			<a id="alertInfo" class="menu i i_btn" no=text title="预警中心" 
				href="${pageContext.request.contextPath}/alarm/alarmIndex" target = "_blank">
				<i class="iconfont icon-yujing"></i>
			<div id="alarmnum" class="numxl">..</div>
			</a>
		</c:if>
		<c:if test="${fn:contains(bimShow_PageList,'&bimShow_title&') && ( fn:contains(bimShow_ButtonList, '&bimShow_supply&')|| fn:contains(bimShow_ButtonList,'&bimShow_bima&') || fn:contains(bimShow_ButtonList,'&bimShow_datachange&') || fn:contains(bimShow_ButtonList,'&bimShow_base&') || fn:contains(bimShow_ButtonList,'&bimShow_s3&') || fn:contains(bimShow_ButtonList,'&bimShow_s4&') || fn:contains(bimShow_ButtonList,'&bimShow_medical&'))}">
			<span id="otherSystem" class="menu i i_btn show_nav_box" no=text href="javascript:void(0);" title="系统集成中心">
				<i class="iconfont icon-yyzx"></i>
				<div id="nav_box">
					<c:if test="${fn:contains(bimShow_PageList,'&bimShow_title&') && fn:contains(bimShow_ButtonList,'&bimShow_bima&')}">
						<a id="nav1" class="one_nav" href="${pageContext.request.contextPath}/ssoout/outsystem?isopen=true&url=https://bima.hna.net/hhang_web/sys/login/NewBIM_home_page" target="_blank" title="海航实业资产管理BIM平台">
							<img src="${pageContext.request.contextPath}/img/BIM.png" alt="" />
							<span>海航实业资产管理BIM平台</span>
						</a>
					</c:if>
					<c:if test="${fn:contains(bimShow_PageList,'&bimShow_title&') && fn:contains(bimShow_ButtonList,'&bimShow_datachange&')}">
						<a id="nav1" class="one_nav" href="${pageContext.request.contextPath}/ssoout/outsystem?isopen=true&url=http://holdingdsp.hna.net/analysis/toIndex.do" target="_blank" title="海航实业BIM管理数据共享与交换平台">
							<img src="${pageContext.request.contextPath}/img/datachange.jpg" alt="" />
							<span>海航实业BIM管理数据共享与交换平台</span>
						</a>
					</c:if>
					<c:if test="${fn:contains(bimShow_PageList,'&bimShow_title&') && fn:contains(bimShow_ButtonList,'&bimShow_base&')}">
						<a id="nav2" class="one_nav" href="${pageContext.request.contextPath}/ssoout/outsystem?isopen=true&url=http://omc.hnainfrastructure.com/hna_assistant/AdminMain/Index" target="_blank" title="海航基础运营管控平台">
							<img src="${pageContext.request.contextPath}/img/hn.png" alt="" />
							<span>海航基础运营管控平台</span>
						</a>
					</c:if>
					<c:if test="${fn:contains(bimShow_PageList, '&bimShow_title&') && fn:contains(bimShow_ButtonList, '&bimShow_supply&')}">
							<a id="nav2" class="one_nav" href="/bim_show/views/home/homeTB/ModalunConstrut.jsp"
							title="供销大集运营管控平台" target="_blank">
								<img src="${pageContext.request.contextPath}/img/zhwu.png" alt="" />
								<span>供销大集运营管控平台</span>
							</a>
					</c:if>
					<c:if test="${fn:contains(bimShow_PageList,'&bimShow_title&') && fn:contains(bimShow_ButtonList,'&bimShow_medical&')}">
						<a id="nav3" class="one_nav" href="${pageContext.request.contextPath}/ssoout/outsystem?isopen=true&url=http://10.71.114.21/Account/Login" target="_blank" title="海航教育医疗运营管控平台">
							<img src="${pageContext.request.contextPath}/img/zhwu.png" alt="" /><span>海航教育医疗运营管控平台</span>
						</a>
					</c:if>
					<c:if test="${fn:contains(bimShow_PageList, '&bimShow_title&') && fn:contains(bimShow_ButtonList, '&bimShow_s3&')}">
							<a id="nav2" class="one_nav" href="${pageContext.request.contextPath}/ssoout/outsystem?isopen=true&url=http://10.123.16.172:19000/workspace/index.jsp"  
							title="海航地产经营管理平台" target="_blank">
								<img src="${pageContext.request.contextPath}/img/hn.png" alt="" />
								<span>海航地产经营管理平台</span>
							</a>
						</c:if>
						<c:if test="${fn:contains(bimShow_PageList, '&bimShow_title&') && fn:contains(bimShow_ButtonList, '&bimShow_s4&')}">
							<a id="nav2" class="one_nav" href="/bim_show/views/home/homeTB/ModalunConstrut.jsp" <%-- href="${pageContext.request.contextPath}/ssoout/outsystem?isopen=true&url=http://10.120.0.54:7001/product/faces/page/witp/login.jsp"   --%>
							title="海航地产运营管控平台" target="_blank">
								<img src="${pageContext.request.contextPath}/img/hn.png" alt="" />
								<span>海航地产运营管控平台</span>
							</a>
						</c:if>
					
					
				</div>
			</span>
		</c:if>
		<c:if test="${fn:contains(bimShow_PageList,'&bimShow_title&') && fn:contains(bimShow_ButtonList,'&bimShow_authority&')}">
			<a id="turnToPortal" class="menu i i_btn exit" no=text target="_blank"  href="${pageContext.request.contextPath}/ssoout/outsystem?isopen=true&url=/bim_portal/system/ladp_portal" title="后台管理中心">
				<i class="iconfont icon-menhuhoutai"></i>
			</a>
		</c:if>
		<c:if test="${fn:contains(bimShow_PageList,'&bimShow_title&') && fn:contains(bimShow_ButtonList,'&bimShow_work&')}">
			<a class="menu i i_btn" no=text href="${pageContext.request.contextPath}/ssoout/outsystem?isopen=true&url=/shanghai-gzw/system/ladp_portal" title="工作台" target="_blank">
					<i class="iconfont icon-gongzuotai"></i>
			</a>
		</c:if>
		 <a class="menu i i_btn exit" id="menhuurl" href="/bim_show/system/_exit"><i
			class="iconfont icon-logout" title="退出"></i>
		</a>

	</div>

	<!-- <header no=text >
	
			<img src="<c:url value="/img/hn_logo1.png"/>" alt="header" class="l_logo">
			<p class="l_logo_p1">运营管控平台(体验版)</p>
			<p class="l_logo_p2">OPERATION CONTROL PLATFORM</p>
	
			<div class="menu time"></div>
					
			<div class="menu user" no="text">
				<div>
					<div class="name">${sessionScope.show_session_users.vcName}</div>
				</div>
				<div class="avatar">
					<img src="<c:url value="http://file.hna.net/image/headimage/emp/${fn:substring(sessionScope.show_session_users.vcEmployeeId,0,6)}0000/${sessionScope.show_session_users.vcEmployeeId}.jpg"/>" onerror="to_def_img(this)" alt="头像">
					<img src="${pageContext.request.contextPath}/img/avatar.png" onerror="to_def_img(this)" alt="头像"> 
				</div>
			</div>
			<a class="menu i" no=text href="/bim_portal/system/ladp_portal?vcEmployeeId=${sessionScope.show_session_users.vcEmployeeId}"><img icon src="${pageContext.request.contextPath}/img/homepage.png" alt="home"></a>
			<a class="menu i" no=text href="${pageContext.request.contextPath}/views/home/homepage.jsp"><img icon src="${pageContext.request.contextPath}/img/home2th.png" alt="home2th"></a>
			<a class="menu i" no=text href="${pageContext.request.contextPath}/views/alarm/alarmIndex.jsp"><img icon src="${pageContext.request.contextPath}/img/notice.png" alt="提醒"><div id="alarmnum" class="num">3</div></a> 
			<a class="menu i exit" id="menhuurl" href="/bim_portal"><img icon src="${pageContext.request.contextPath}/img/exit.png" alt="离开"></a>
		
		</header> -->
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

	<script>
		var basePath = '${pageContext.request.contextPath}';
		// var mhhref='${sessionScope.VcEmployeeId}';
		// document.getElementById("menhuurl").href = "http://10.123.16.168:8080/bim_portal/system/ladp_portal?vcEmployeeId="+mhhref; 
		//alert(document.getElementById("menhuurl").href)
		checkAlarmNum();
		alterNum();
		function checkAlarmNum() {
			$.ajax({
				url : basePath + "/alarm/SumCount",
				type : "POST",
				success : function(data) {
					$('#alarmnum').text(data);
				},
				error : function(data) {
					alert("获取预警数量失败==="+JSON.stringify(data));
				}
			});
		}

		function alterNum(){
		    var employeeId='${sessionScope.show_session_users.vcEmployeeId}';
			$.ajax({
				url : basePath + "/alarm/getZLCount?employeeId="+employeeId,
				type : "POST",
				success : function(data) {
					$('#zlmnum').text(data);
				},
				error : function(data) {
					alert("获取指令数量失败==="+JSON.stringify(data));
				}
			});
		}
		
		
	</script>
	<script>
		//function openzhilinwindow(){
		   // var nowDate= new Date();
		   // var formatdate=dateFtt("yyyy-MM-dd",nowDate) ;
		   // var url="/bim_task/system/index?vcEmployeeId=${show_session_users.vcEmployeeId}&taskOrInstruction=dealing";
		   //var url="/bim_task/ssoout/outsystem?url=/bim_task/system/index?systemCode=show&token=${session_token}&taskOrInstruction=normal";
		    //window.open(url, "_blank");
		    //window.location.href = url;
		//}
		//5国
		/* var hourLi = document.getElementsByClassName('hour')[0];
		var minuteLi = document.getElementsByClassName('minute')[0];
		var secondLi = document.getElementsByClassName('second')[0];
		
		
		var hourLi1 = document.getElementsByClassName('hour1')[0];
		var minuteLi1 = document.getElementsByClassName('minute1')[0];
		var secondLi1 = document.getElementsByClassName('second1')[0];
		
	    var hourSmallLi1 = document.getElementsByClassName('hour_small')[0];
		var minuteSmallLi1 = document.getElementsByClassName('minute_small')[0];
		var secondSmallLi1 = document.getElementsByClassName('second_small')[0];
		
		var hourSmallLi2 = document.getElementsByClassName('hour_small')[1];
		var minuteSmallLi2 = document.getElementsByClassName('minute_small')[1];
		var secondSmallLi2 = document.getElementsByClassName('second_small')[1];
		
		 var hourSmallLi3 = document.getElementsByClassName('hour_small')[2];
		var minuteSmallLi3 = document.getElementsByClassName('minute_small')[2];
		var secondSmallLi3 = document.getElementsByClassName('second_small')[2];
		
		 var hourSmallLi4 = document.getElementsByClassName('hour_small')[3];
		var minuteSmallLi4 = document.getElementsByClassName('minute_small')[3];
		var secondSmallLi4 = document.getElementsByClassName('second_small')[3];
		
		var secondRotate;
		rotate();
		function rotate() {
			var date1 = new Date();
			var hour = date1.getHours();
			var minute = date1.getMinutes();
			var second = date1.getSeconds();
	
			secondLi.style.transform = 'rotate(' + second * 6 + 'deg)';
			minuteLi.style.transform = 'rotate(' + (minute * 6 + second * 6 / 60) +'deg)';
			hourLi.style.transform = 'rotate(' + (hour * 30 + minute * 30 / 60 + second * 30 / 3600) +'deg)';
			
			secondLi1.style.transform = 'rotate(' + second * 6 + 'deg)';
			minuteLi1.style.transform = 'rotate(' + (minute * 6 + second * 6 / 60) +'deg)';
			hourLi1.style.transform = 'rotate(' + (hour * 30 + minute * 30 / 60 + second * 30 / 3600) +'deg)';
			
			var nowDate= new Date();
			var DataArea=" 中国时区";
			
			var XWnd=getOtherCountryTime(600); //夏威夷时差
			var hourS1 = XWnd.getHours();
			var minuteS1 = XWnd.getMinutes();
			var secondS1 = XWnd.getSeconds();
			
			$('#xwyear').text(dateFtt("yyyy-MM-dd",XWnd))
			$('#xwtime').text(dateFtt("hh:mm:ss",XWnd))
			
			secondSmallLi1.style.transform = 'rotate(' + secondS1 * 6 + 'deg)';
			minuteSmallLi1.style.transform = 'rotate(' + (minuteS1 * 6 + secondS1 * 6 / 60) +'deg)';
			hourSmallLi1.style.transform = 'rotate(' + (hourS1 * 30 + minuteS1 * 30 / 60 + secondS1 * 30 / 3600) +'deg)';
			
			
			var BLnd=getOtherCountryTime(-120); //巴黎时间
			var hourS2 = BLnd.getHours();
			var minuteS2 = BLnd.getMinutes();
			var secondS2 = BLnd.getSeconds();
			
			$('#blyear').text(dateFtt("yyyy-MM-dd",BLnd))
			$('#bltime').text(dateFtt("hh:mm:ss",BLnd))
			
			secondSmallLi2.style.transform = 'rotate(' + secondS2 * 6 + 'deg)';
			minuteSmallLi2.style.transform = 'rotate(' + (minuteS2 * 6 + secondS2 * 6 / 60) +'deg)';
			hourSmallLi2.style.transform = 'rotate(' + (hourS2 * 30 + minuteS2 * 30 / 60 + secondS2 * 30 / 3600) +'deg)';
			
			var NInd=getOtherCountryTime(240); //纽约时间
			var hourS3 = NInd.getHours();
			var minuteS3 = NInd.getMinutes();
			var secondS3 = NInd.getSeconds();
			
			$('#nuyear').text(dateFtt("yyyy-MM-dd",NInd))
			$('#nutime').text(dateFtt("hh:mm:ss",NInd))
			
			secondSmallLi3.style.transform = 'rotate(' + secondS3 * 6 + 'deg)';
			minuteSmallLi3.style.transform = 'rotate(' + (minuteS3 * 6 + secondS3 * 6 / 60) +'deg)';
			hourSmallLi3.style.transform = 'rotate(' + (hourS3 * 30 + minuteS3 * 30 / 60 + secondS3 * 30 / 3600) +'deg)';
			
			var DJnd=getOtherCountryTime(-540); //东京时间
			var hourS4 = DJnd.getHours();
			var minuteS4 = DJnd.getMinutes();
			var secondS4 = DJnd.getSeconds();
			
			$('#djyear').text(dateFtt("yyyy-MM-dd",DJnd))
			$('#djtime').text(dateFtt("hh:mm:ss",DJnd))
			
			secondSmallLi4.style.transform = 'rotate(' + secondS4 * 6 + 'deg)';
			minuteSmallLi4.style.transform = 'rotate(' + (minuteS4 * 6 + secondS4 * 6 / 60) +'deg)';
			hourSmallLi4.style.transform = 'rotate(' + (hourS4 * 30 + minuteS4 * 30 / 60 + secondS4 * 30 / 3600) +'deg)';
			
			$('#clock').attr('title',dateFtt("yyyy-MM-dd hh:mm:ss",nowDate)+DataArea)
			$('#bjtime').text(dateFtt("yyyy-MM-dd hh:mm:ss",date1))
		}
		clearInterval(secondRotate);
		secondRotate = setInterval(rotate, 1000); */
	

	/**************************************时间格式化处理************************************/
	function dateFtt(fmt,date)   
	{    
	  var o = {   
	    "M+" : date.getMonth()+1,                 //月份   
	    "d+" : date.getDate(),                    //日   
	    "h+" : date.getHours(),                   //小时   
	    "m+" : date.getMinutes(),                 //分   
	    "s+" : date.getSeconds(),                 //秒   
	    "q+" : Math.floor((date.getMonth()+3)/3), //季度   
	    "S"  : date.getMilliseconds()             //毫秒   
	  };   
	  if(/(y+)/.test(fmt))   
	    fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));   
	  for(var k in o)   
	    if(new RegExp("("+ k +")").test(fmt))   
	  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
	  return fmt;   
	}
	
	/**
	  * 获取别的地区时间 offset与国际时间差 调整电脑时间getTimezoneOffset()算 返回当地时间对象
	  */
	/* function getOtherCountryTime(offset){
	   var nowDate= new Date();
	   var localTime = nowDate.getTime(); 
	   var localOffset =nowDate.getTimezoneOffset()* 60000; 
	   var utc = localTime + localOffset;      
	   var calctime = utc - (60000*offset); 
	   var nd= new Date(calctime);  
	   return nd;
	}
	
	var timeList=[600,-120,240,-540]; *///夏威夷时差 巴黎时间 纽约时间 东京时间
	
	/* function setSmallClock(){
         for(var i=0;i<timeList.length();i++){
             var hourLi = document.getElementsByClassName('hour_small')[i];
			 var minuteLi = document.getElementsByClassName('hour_small')[i];
			 var secondLi = document.getElementsByClassName('hour_small')[i];
			 
			 var countrygap=timeList[i];
			 var nd=getOtherCountryTime(countrygap);
			 
			 var hour = nd.getHours();
			 var minute = nd.getMinutes();
			 var second = nd.getSeconds();
			 
			secondLi.style.transform = 'rotate(' + second * 6 + 'deg)';
			minuteLi.style.transform = 'rotate(' + (minute * 6 + second * 6 / 60) +'deg)';
			hourLi.style.transform = 'rotate(' + (hour * 30 + minute * 30 / 60 + second * 30 / 3600) +'deg)';
         }	
	
	} */
		
	</script>
	
</body>

</html>