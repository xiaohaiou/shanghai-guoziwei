﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-status-bar-style"content="black"/>
<!--meta name="viewport" content="width=device-width, initial-scale=0.75, maximum-scale=0.75, user-scalable=no" /-->
<meta name="x5-orientation"content="landscape">
<title>海航实业BIM运营管控平台</title>
<link href="${pageContext.request.contextPath}/style/lin.css"
	rel="stylesheet" type="text/css">
<!-- 
		<link rel="${pageContext.request.contextPath}/views/home/stylesheet" href="style/bootstrap.min.css" />
		 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/style/bootstrap.min.css" />


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/style/font/iconfont.css" />
<!-- 库|插件 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/views/home/homeTB/css/remodal.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/views/home/homeTB/css/remodal-default-theme.css">

</head>

<script>
	var docresize= function(){
		if (window.innerWidth)winWidth = window.innerWidth;
		else if ((document.body) && (document.body.clientWidth))winWidth = document.body.clientWidth;
		var Tp=document.getElementsByClassName("l_top")[0];
		var oChartContentH=document.getElementById("d1");
		var Ocol3=document.getElementById("d2");
		var Ocol4=document.getElementById("d3");
		var Ocol5=document.getElementById("d4");
		var h1=document.body.clientHeight;
		var h4=document.getElementById("myCarousel").clientHeight;
		var h5=Tp.clientHeight;
		var Cx=h1-h4-h5;
		//alert(h5);
		var x=(Cx - Ocol3.clientHeight)/2;
		var y=(Cx - Ocol4.clientHeight)/2;
		//alert(x);alert(y);
		if(winWidth<=1650){
			console.log("a");
			Ocol3.style.marginTop=Ocol5.style.marginTop=10 + 'px';
			Ocol4.style.marginTop=35 + 'px';
			return
		}else{
			Ocol3.style.marginTop=Ocol5.style.marginTop=x + 20 + 'px';
			Ocol4.style.marginTop=y + 'px';
		}
	};
	window.addEventListener('load',docresize);
	window.addEventListener('resize',docresize);
	var carouNum = 0;
	var butList = '${bimShow_ButtonList}';
	if (butList.indexOf("&bs_ip4&") >= 0){
		carouNum++;
	}
	if (butList.indexOf("&bs_ip5&") >= 0){
		carouNum++;
	}
	if (butList.indexOf("&bs_ip6&") >= 0){
		carouNum++;
	}
	if (butList.indexOf("&bs_ip7&") >= 0){
		carouNum++;
	}
	if (butList.indexOf("&bs_ip8&") >= 0){
		carouNum++;
	}
	if (butList.indexOf("&bs_ip9&") >= 0){
		carouNum++;
	}
	if (butList.indexOf("&bs_ip10&") >= 0){
		carouNum++;
	}
	if (butList.indexOf("&bs_ip11&") >= 0){
		carouNum++;
	}
</script>
<script type="text/javascript">
	var basePath = '${pageContext.request.contextPath}';
</script>
<style>
.lightgreen {
	color: #47bbb2;
}

.cash {
	text-align: center;
	margin-top: 25px;
}
.chart-content.row.abc{
   height:calc(100% - 410px) !important;
}
.cash i {
	font-size: 52px;
	display: block;
	line-height: 45px !important;
}

.cash p {
	color: #f0f0f0;
	margin-top: 10px;
}

.exp1 {
	font-size: 12px;
}

/*flexslider*/
.flexslider {
	width: 100%;
	background-color: rgba(0, 0, 0, 0);
	margin-bottom: 0;
	border: 0;
}

.flex-control-nav {
	display: none;
}

.flex-direction-nav {
	display: none;
}

.chart-text span.chartnum {
	font-size: 16px;
	line-height: 25px;
}

/* #jh_pie div,#cb_pie div {
	margin-left: calc(( 100% -   102px)/2 ) !important;
} */

.carousel-control {
	width: 30px !important;
	z-index: 9999;
	height: 70%;
}

/*modal*/
.remodal-wrapper {
	padding: 0px;
}

.modal-page.modal-container {
	position: relative;
	padding: 0;
	margin: 0;
	height: 100%;
	background-color: rgba(0, 0, 0, 0);
	max-width: 900px;
}

.modal-page[data-remodal-id=page] iframe {
	position: relative;
	width: 100%;
	max-height: 100%;
	padding: 0;
	margin: 0;
	outline: 0;
	border: 0;
}

.modal-page .modal-close {
	display: none;
	position: absolute;
	height: 20px;
	right: 12px;
	top: 12px;
}

.modal-page .modal-close>img {
	height: 100%;
}

.white-bg>.title {
	position: relative;
}

.white-bg>.title>.new.click {
	position: absolute;
	right: 10px;
	font-size: 14px;
	line-height: 18px;
	top: calc(50% -   10px);
	color: #fff;
	padding: 2px 6px;
	border-radius: 3px;
	cursor: pointer;
}

.chart-content {
	position: relative;
}

#html5_3d_animation {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	z-index: -1;
}

</style>
<body class="hn_page" onload="initData();">

	<!--<img src="img/home.jpg" alt="" width="1920" height="1080" usemap="#Map" border="0"/>

<map name="Map">

  <area shape="circle" coords="313,330,162" href="v.htm">

</map>-->
	<jsp:include page="hometitle.jsp" flush="true" />
	<!-- 避免样式被include jsp冲掉 解决滑动点击区域过大的问题 -->
	<style>
.carousel-control {
	width: 30px !important;
	z-index: 9999;
	height: 70%;
}

.block-middle {
	height: calc(100% -   166px);
}

.candilar-box,.candilar-box table {
	height: 100%;
}

.number-bigsize h1 {
	font-size: 60px;
	color: #fff;
	font-weight: 500;
	float: left;
	position: relative;
	white-space: nowrap;
}
#myCarousel{
	width: 100%;
}

</style>

	<c:if test="${fn:contains(bimShow_ButtonList,'&bs_ip12&')}">
	<a class="sent-order" href="javascript:;" title="指令开关">
		<i class="iconfont icon-qizi"></i>
	</a>
	</c:if>
	<div class="container-fluid main-content">
		<div class="chart-content row" id="d1">
			 <!--  <canvas id="html5_3d_animation">Internet Explorer 8 Not Supported</canvas>-->
			<div class="col-sm-3" id="d2">
				<div class="vedio-box">
					<a class="orderbtn larger" href="javascript:void(0);" style="margin-top: -40px">
						<i class="iconfont icon-qizi" onclick="sendorder(100,4,'重点基建工程')"></i>
					</a>
					<a href="javascript:void(0);" onclick="turnToProject()">
						<img src="${pageContext.request.contextPath}/img/vdeio-img.png">
					</a>
					<span class="Charttitle ab-pos">
						重点基建工程<sub>&nbsp;</sub>
					</span>
				</div>
			</div>
			<div class="col-sm-6"  id="d3">
				<div class="circle-box row">
					<div class="col-sm-4">
						<a class="orderbtn" href="javascript:;">
							<i class="iconfont icon-qizi" onclick="sendorder(13,1,'劳动生产率')"></i>
						</a>
						<div class="chart-switch" style="" id="DeptShow_1" onclick="trunToChartShow(this)">
							<div class="chart-list circle-xz">
								<div class="chartcircle">
									<div id="SPECCircle_1" style="width: 100%; height: 150px;"></div>
								</div>
								<span id="ldscl" class="Charttitle">
									劳动生产率<sub></sub>
								</span>
							</div>
							<div class="circularbox">
								<div class="circular">
									<svg viewBox="0 0 100 100">
										<path id="cil2" fill="none"
											stroke="" stroke-miterlimit="10"
											d="M8.247,9.25c0,0,34.507,1.015,34.507,34"></path>
										<text>
											<textPath id="rjsr" class="text-content" xlink:href="#cil2" startOffset="0">
											</textPath>
										</text>
									</svg>
								</div>
								<div class="circular2 ">
									<svg viewBox="0 0 100 100">
										<path fill="none" id="cil3"
											stroke="" stroke-miterlimit="10"
											d="M8.375,43.25C8.375,24.042,23.973,8.5,43.25,8.5"></path>
										<text>
											<textPath id="rjlr" class="text-content1" xlink:href="#cil3" startOffset="16">
											</textPath>
										</text>
									</svg>
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<a class="orderbtn" href="javascript:;">
							<i class="iconfont icon-qizi" onclick="sendorder(13,2,'当前预估利润')"></i>
						</a>
						<div id="DeptShow_1" onclick="trunToChartShow(this)">
							<div id="Circle_1" style="width: 100%; height: 120px;"></div>
						</div>
						<span id="Span_Circle_1" class="Charttitle">资产负债率</span>
					</div>
					<div class="col-sm-4">
						<a class="orderbtn" href="javascript:;"> <i
							class="iconfont icon-qizi" onclick="sendorder(13,3,'当前预估利润')"></i>
						</a>
						<div id="DeptShow_1" onclick="trunToChartShow(this)">
							<div id="Circle_2" style="width: 100%; height: 120px;"></div>
						</div>
						<span id="Span_Circle_2" class="Charttitle">资产周转率</span>
					</div>
				</div>
				<div class="clearfix"></div>
				<div class="circle-box row">
					<div class="col-sm-4">
						<a class="orderbtn" href="javascript:;">
							<i class="iconfont icon-qizi" onclick="sendorder(13,4,'当前预估利润')"></i>
						</a>
						<div id="DeptShow_1" onclick="trunToChartShow(this)">
							<div id="Circle_3" style="width: 100%; height: 120px;"></div>
						</div>
						<span class="Charttitle" id="Span_Circle_3">
							资本利润率<sub></sub>
						</span>
					</div>
					<div class="col-sm-4">
						<a class="orderbtn" href="javascript:;">
							<i class="iconfont icon-qizi" onclick="sendorder(13,5,'当前预估利润')"></i>
						</a>
						<div id="DeptShow_1" onclick="trunToChartShow(this)">
							<div id="Circle_4" style="width: 100%; height: 120px;"></div>
						</div>
						<span class="Charttitle" id="Span_Circle_4">
							净资产收益率<sub></sub>
						</span>
					</div>
					<div class="col-sm-4">
						<a class="orderbtn" href="javascript:;"> <i
							class="iconfont icon-qizi" onclick="sendorder(13,6,'市场占有率')"></i>
						</a>
						<div class="chart-switch" style="" id="DeptShow_1"
							onclick="trunToChartShow(this)">
							<div class="chart-list circle-xz">
								<div class="chartcircle">
									<div id="SPECCircle_2" style="width: 100%; height: 150px;"></div>
								</div>
								<span id="sczyl" class="Charttitle">
									市场占有率<sub></sub>
								</span>
							</div>
							<div class="circularbox">
								<div class="circular">
									<svg viewBox="0 0 100 100">
										<path id="cil2" fill="none"
											stroke="" stroke-miterlimit="10"
											d="M8.247,9.25c0,0,34.507,1.015,34.507,34"></path>
										<text>
											<textPath id="kpzyl" class="text-content" xlink:href="#cil2" startOffset="0">
											</textPath>
										</text>
									</svg>
								</div>
								<div class="circular2 ">
									<svg viewBox="0 0 100 100">
										<path fill="none" id="cil3"
											stroke="" stroke-miterlimit="10"
											d="M8.375,43.25C8.375,24.042,23.973,8.5,43.25,8.5"></path>
										<text>
											<textPath id="hhzyl" class="text-content1" xlink:href="#cil3" startOffset="8">
											</textPath>
										</text>
									</svg>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="col-sm-3" id="d4">
				<a class="orderbtn larger" href="javascript:;" style="margin-top: -20px"> <i
					class="iconfont icon-qizi" onclick="sendorder(13,7,'经营指标')"></i> </a>
				<div class="" style="width: 100%; height: 432px; position: relative;">
					<div class="chart-text" id="DeptShow_8" onclick="trunToChartShow(this)">
						<span class="Charttitle">累计利润/年预算利润</span>
						<span id="jinyin_profit" style="font-size: 16px;line-height:25px;" class="chartnum blue">
							0 /0<sub>亿</sub>
						</span>
						<span class="Charttitle">累计收入/年预算收入</span>
						<span id="jinyin_income" style="font-size: 16px;line-height:25px;" class="chartnum yellow">
							0 /0<sub>亿</sub>
						</span>
					</div>
					<div id="Dou_Circle_1"
						style="width: 100%; height: 332px; left: 0px; top:12px"></div>
					<span id="DeptTime_8" class="Charttitle ab-pos">经营指标(亿元)</span>
				</div>
			</div>

			<div class="curve-graph" id="bllinech"
				style="position: absolute; width: 100%; height: 200px;bottom: 0px;left: 0px;">
			</div>

		</div>
	</div>
	<div id="myCarousel" class="row slide">
		<!-- 轮播（Carousel）项目 -->
		<div class="my-carousel-inner">
			<div class="item active">
				<div class="block-content clearfix">
					<c:if test="${fn:contains(bimShow_ButtonList,'&bs_ip4&')}">
					<div class="col-sm-3 col-md-3 col-xs-12 col-lg-3 block-box">
						<div class="block-list">
							<div class="block-header clearfix">
								<div id="xzbjl" class="col-sm-6 number-bigsize">
									<h1>
										-<sub>%</sub>
										<sup>行政督办办结率</sup>
									</h1>
									<span class="title"></span>
								</div>
								<div class="col-sm-6">
									<div id="xzdbbjl" style="width: 100%; height: 100px;"></div>
								</div>
							</div>
							<div id="SHOWVIEW_M73_CURVE" style="width: 100%; height: calc(100% - 166px);">
							</div>
							<div class="block-bottom">
								<a class="col-sm-9 bottom-link" href="javascript:void(0);"
									id="DeptShow_2" onclick="trunToChartShow(this)">
									<span class="block-name">行政办公&nbsp;&nbsp;
										<font size="1" id="DeptTime_2"></font>
									</span>
									<span class="line"></span>
								</a>
								<a href="javascript:void(0);" class="orderbtn col-sm-3">
									<i class="iconfont icon-qizi" onclick="sendorder(13,8,'行政督办办结率')"></i>
								</a>
							</div>
						</div>
					</div>
					</c:if>
					<c:if test="${fn:contains(bimShow_ButtonList,'&bs_ip5&')}">
					<div class="col-sm-3 col-md-3 col-xs-12 col-lg-3 block-box">
						<div class="block-list">
							<div class="block-header clearfix">
								<div class="col-sm-12 number-bigsize">
									<div id="zrs">
										<h1>
											-<sub>万</sub><sup>总人数</sup>
											<!--  <span class="triangle"></span>-->
										</h1>
									</div>
									<div class="piebox">
										<div id="rs_pie" style="width: 100%; height: 100px;"></div>
									</div>
								</div>
							</div>
							<div id="SHOWVIEW_M74_CURVE" style="width: 100%; height:  calc(100% - 166px);">
							</div>
							<div class="block-bottom">
								<a class="col-sm-9 bottom-link" href="javascript:void(0);"
									id="DeptShow_4" onclick="trunToChartShow(this)">
									<span class="block-name">人力资源&nbsp;&nbsp;
										<font size="1" id="DeptTime_4"></font>
									</span>
									<span class="line"></span>
								</a>
								<a href="javascript:;" class="orderbtn col-sm-3" href="javascript:;">
									<i class="iconfont icon-qizi" onclick="sendorder(13,9,'总人数')"></i>
								</a>
							</div>
						</div>
					</div>
					</c:if>
					<c:if test="${fn:contains(bimShow_ButtonList,'&bs_ip6&')}">
					<div class="col-sm-3 col-md-3 col-xs-12 col-lg-3 block-box">
						<div class="block-list">
							<div class="block-header clearfix">
								<div id="caiwu" class="col-sm-8 number-bigsize">
									<h1>
										-<sub>亿</sub><sup>总资产</sup>
										<!--  <span class="triangle"></span>-->
									</h1>
									<!-- <span class="title"></span>-->
								</div>
							</div>
							<div id="SHOWTAB_M71_CURVE" style="width: 100%; height: calc(100% - 146px);">
							</div>
							<div class="block-bottom">
								<a class="col-sm-9 bottom-link" id="DeptShow_10" href="/bim_show/views/home/homeTB/unConstrut.jsp">
									<!-- id="DeptShow_10" onclick="trunToChartShow(this)"> -->
									<span class="block-name">财务管理&nbsp;&nbsp;
										<font size="1" id="DeptTime_10"></font>
									</span>
									<span class="line"></span>
								</a>
								<a href="javascript:;" class="orderbtn col-sm-3" href="javascript:;">
									<i class="iconfont icon-qizi" onclick="sendorder(13,10,'总资产')"></i>
								</a>
							</div>
						</div>
					</div>
					</c:if>
					<c:if test="${fn:contains(bimShow_ButtonList,'&bs_ip7&')}">
					<div class="col-sm-3 col-md-3 col-xs-12 col-lg-3 block-box">
						<div class="block-list">
							<div id="zrs" class="col-sm-12 number-bigsize">
								<h1>
									987.41<sub>亿元</sub><sup>大额资产原值</sup>
									<!-- <span class="triangle"></span> -->
								</h1>
								<!-- <a  href="https://bima.hna.net/hhang_web/sys/login/BIM_home_page?vcEmployeeId=${sessionScope.DESvemployeeId}" target="_blank"
									class="a-link">BIMA驾驶舱</a> -->
							</div>
							<img src="${pageContext.request.contextPath}/img/map.png"
								style="width:100%;height:73%">
							<div class="block-bottom">
								<a class="col-sm-9 bottom-link" onclick="goZichan()" href="javascript:void(0);">
									<span class="block-name">大额资产管理&nbsp;&nbsp;
										<font size="1">2017年9月</font>
									</span>
									<span class="line"></span>
								</a> 
								<a href="${pageContext.request.contextPath}/ssoout/outsystem?isopen=true&url=https://bima.hna.net/hhang_web/sys/login/NewBIM_home_page" target="_blank"
									style="width:auto;position:absolute;left:60%;white-space:nowrap;border-radius: 30px;padding: 2px 10px;background-color: #203c4e;color: rgba(255,255,255,0.5);" class="a-link">
									BIMA驾驶舱
								</a>
								<a href="javascript:;" class="orderbtn col-sm-3" style="margin-top: -5px">
									<i class="iconfont icon-qizi" onclick="sendorder(13,11,'大额资产原值')"></i>
								</a>
							</div>
						</div>
					</div>
					</c:if>
				</div>
			</div>
			<div class="item">
				<div class="block-content item active clearfix">
					<c:if test="${fn:contains(bimShow_ButtonList,'&bs_ip8&')}">
					<div class="col-sm-3 col-md-3 col-xs-12 col-lg-3 block-box">
						<div class="block-list">
							<div class="block-header clearfix">
								<div id="zycsc" class="col-sm-8 number-bigsize">
									<h1>
										-<sub>小时</sub><sup>志愿服务活动时长</sup>
										<!-- <span class="triangle"></span> -->
									</h1>
									<!-- span class="title"></span> -->
								</div>
							</div>
							<div id="SHOWTAB_W23_CURVE" style="width: 100%; height:  calc(100% - 146px);">
							</div>
							<div class="block-bottom">
								<a class="col-sm-9 bottom-link" href="javascript:void(0);"
									id="DeptShow_5" onclick="trunToChartShow(this)">
									<span class="block-name">社会责任&nbsp;&nbsp;
										<font size="1" id="DeptTime_5"></font>
									</span>
									<span class="line"></span>
								</a>
								<a href="javascript:;" class="orderbtn col-sm-3" href="javascript:;">
									<i class="iconfont icon-qizi" onclick="sendorder(13,12,'志愿服务活动时长')"></i>
								</a>
							</div>
						</div>
					</div>
					</c:if>
					<c:if test="${fn:contains(bimShow_ButtonList,'&bs_ip9&')}">
					<div class="col-sm-3 col-md-3 col-xs-12 col-lg-3 block-box">
						<div id="caigou" class="block-list">
							<div class="block-header clearfix">
								<div id="ljcg" class="col-sm-12 number-bigsize">
									<h1>
										-<sub>亿</sub><sup>累计完成采购金额</sup>
									</h1>
								</div>
							</div>
							<div class="block-middle clearfix">
								<div class="col-sm-6">
									<div id="jh_pie" style="width: 100%; height: 102px;"></div>
									<span id="jh_span" class="number-pecent">-<sub>%</sub>
									</span> <span class="Charttitle">采购金额环比</span>
								</div>
								<div class="col-sm-6">
									<div id="cb_pie" style="width: 100%; height: 102px;"></div>
									<span id="cb_span" class="number-pecent">-<sub>%</sub>
									</span> <span class="Charttitle">工程招采成本节约率 </span>
								</div>
							</div>
							<div class="block-bottom">
								<a class="col-sm-9 bottom-link" href="javascript:void(0);"
									id="DeptShow_3" onclick="trunToChartShow(this)">
									<span class="block-name">采购管理&nbsp;&nbsp;
										<font size="1" id="DeptTime_3"></font>
									</span>
									<span class="line"></span>
								</a>
								<a href="javascript:;" class="orderbtn col-sm-3" href="javascript:;">
									<i class="iconfont icon-qizi" onclick="sendorder(13,13,'累计完成采购金额')"></i>
								</a>
							</div>
						</div>
					</div>
					</c:if>
<!-- 					<div class="col-sm-3 col-md-3 col-xs-12 col-lg-3 block-box">
						<div class="block-list">
							<div class="block-header clearfix">
								<div class="col-sm-6 number-bigsize" id="CAQD">
									<h1>
										0<sub>天</sub><sup>安全日</sup>
									</h1>
								</div>
								<div class="col-sm-6 jind-box">
									<span class="title"></span>
								</div>
							</div>
							<div class="block-middle clearfix">
								<div class="col-sm-12 candilar-box">
									<table id="aqjg" class="candilar">
									</table>
								</div>
							</div>
							<div class="block-bottom">
								<a class="col-sm-9 bottom-link" href="javascript:void(0);"
									id="DeptShow_6" onclick="trunToChartShow(this)">
									<span class="block-name">安全生产&nbsp;&nbsp;
										<font size="1" id="DeptTime_6"></font>
									</span>
									<span class="line"></span>
								</a>
								<a href="javascript:;" class="orderbtn col-sm-3" href="javascript:;">
									<i class="iconfont icon-qizi" onclick="sendorder(13,14,'连续安全工作日')"></i>
								</a>
							</div>
						</div>
					</div> -->
					<c:if test="${fn:contains(bimShow_ButtonList,'&bs_ip11&')}">
					<div id="dept_7" class="col-sm-3 col-md-3 col-xs-12 col-lg-3 block-box">
						<div class="block-list">
							<div class="block-header clearfix">
								<div id="sjwtzg" class="col-sm-6 number-bigsize">
									<h1>
										-<sub>%</sub><sup>审计问题整改率</sup>
										<!-- <span class="triangle"></span> -->
									</h1>
									<!--  span class="title"></span>-->
								</div>
							</div>
							<div class="block-middle clearfix">
								<div id="sjxms" class="col-sm-6">
									<div class="title">审计项目数</div>
									<div class="number-bigsize">
										-<sub>个</sub>
									</div>
								</div>
								<div id="fxwts" class="col-sm-6">
									<div class="title">发现问题数</div>
									<div class="number-bigsize">
										-<sub>个</sub>
									</div>
								</div>
								<div id="lvhts" class="col-sm-6">
									<div class="title">履行合同总数</div>
									<div class="number-bigsize">
										-<sub>个</sub>
									</div>
								</div>
								<div id="htwyl" class="col-sm-6">
									<div class="col-sm-7">
										<div class="title">合同违约数</div>
										<div class="number-bigsize">
											-
										</div>
									</div>
									<div class="col-sm-5 pieChart">
										<div id="htwyl_pie" style="width: 100%; height: 100px;"></div>
									</div>
								</div>
							</div>
							<div class="block-bottom">
								<a class="col-sm-9 bottom-link" href="javascript:void(0);"
									id="DeptShow_7" onclick="trunToChartShow(this)">
									<span class="block-name">风险控制&nbsp;&nbsp;
										<font size="1" id="DeptTime_7"></font>
									</span>
									<span class="line"></span>
								</a>
								<a href="javascript:;" class="orderbtn col-sm-3" href="javascript:;">
									<i class="iconfont icon-qizi" onclick="sendorder(13,15,'审计问题整改率')"></i>
								</a>
							</div>
						</div>
					</div>
					</c:if>
					<%-- <div class="col-sm-3 col-md-3 col-xs-12 col-lg-3 block-box">
						<!-- <div class="block-list"> -->
						<img src="${pageContext.request.contextPath}/views/home/img/dichan_home.png" alt="" height="100%" width="100%"> --%>
							<%-- <div class="block-header clearfix">
								<div class="col-sm-6 number-bigsize">
									<h1>
										<img src="${pageContext.request.contextPath}/yj-img/dichan_logo.png" alt="">
									</h1>
								</div>
							</div>
							<div class="block-middle clearfix">
								<img src="${pageContext.request.contextPath}/views/home/img/dichan_home.png" alt=""> --%>
								<!-- <div class="col-sm-6">
									<div class="title">经营指标完成率</div>
									<div class="number-bigsize">
										120<sub>%</sub>
									</div>
								</div>
								<div class="col-sm-6">
									<div class="title">销售指标完成率</div>
									<div class="number-bigsize">
										120<sub>%</sub>
									</div>
								</div>
								<div class="col-sm-6">
									<div class="title">利润指标完成率</div>
									<div class="number-bigsize">
										120<sub>%</sub>
									</div>
								</div>
								<div class="col-sm-6">
									<div class="col-sm-7">
										<div class="title">回款指标完成率</div>
										<div class="number-bigsize">
											120<sub>%</sub>
										</div>
									</div>
									<div class="col-sm-5 pieChart">
										<div style="width: 100%; height: 100px;"></div>
									</div>
								</div> -->
							<!-- </div>
							<div class="block-bottom">
								<a class="col-sm-9 bottom-link">
									<span class="block-name">
										地产经营指标监控
										<font size="1" ></font>
									</span>
									<span class="line"></span>
								</a>
								<a href="javascript:;" class="orderbtn col-sm-3" href="javascript:;">
									<i class="iconfont icon-qizi" onclick="sendorder(13,16,'海航地产经营指标')"></i>
								</a>
							</div> -->
						<!-- </div> -->
					<!-- </div> -->
				</div>
			</div>
		</div>
		<!-- 轮播（Carousel）导航 -->
		<div class="carousel-next right-btn"></div>
		<style>
			#myCarousel{
				position:absolute;
				overflow:hidden;
				margin:0;
				bottom:0px;
			}
			.my-carousel-inner{
				white-space: nowrap;
				position:relative;
			}
			.my-carousel-inner>*{
				width:100%;
				box-sizing:border-box;
				display:inline-block;
				white-space: nowrap;
				vertical-align: top;
			}
			.my-carousel-inner>*>*{
				white-space: normal;
			}
			.carousel-next{
				top:calc(50% - 75px);
				cursor: pointer;
			}
		</style>
	</div>
	<div class="modal-page modal-container" data-remodal-id="page" no=text>
		<iframe id="modal-frame" src="" style></iframe>
	</div>



	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery-1.11.0.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/echart/echarts.min.js"></script>
	<!--  
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/views/home/js/echart/curve.js"></script>
		-->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/views/home/js/echart/circle.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/views/home/js/echart/doubel-circle.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/views/home/js/echart/curve_1.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/views/home/js/echart/piechart.js"></script>
	<!--
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/views/home/js/echart/jind.js"></script>
		-->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/views/home/js/homePage.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/views/home/js/toucher.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/views/home/homeTB/js/remodal.min.js"
		charset="utf-8"></script>
	<script
		src="${pageContext.request.contextPath}/js/html5_3d_animation.js"
		charset="utf-8"></script>
	<script>
		var basePath = '${pageContext.request.contextPath}';
		var DESvemployeeId='${sessionScope.DESvemployeeId}';
		
		  // 判断浏览器类型
		function getOs() {
			var OsObject = "";
			if (navigator.userAgent.indexOf("MSIE") > 0) {
				return "MSIE";
			}
			if (isFirefox = navigator.userAgent.indexOf("Firefox") > 0) {
				return "Firefox";
			}
			if (isSafari = navigator.userAgent.indexOf("Safari") > 0) {
				return "Safari";
			}
			if (isCamino = navigator.userAgent.indexOf("Camino") > 0) {
				return "Camino";
			}
			if (isMozilla = navigator.userAgent.indexOf("Gecko/") > 0) {
				return "Gecko";
			}
		}
			//alert("您的浏览器类型为:"+getOs());  
			//var mq = getos();
			//alert(mq);
		if (getOs() == 'Safari') {
			$(function() {
				//alert('111');
				$(this).find('.chartcircle').each(function() {
					//alert('111');
					$(".chartcircle").addClass("uuu");
				});
			});
		}
		
		
	</script>
	<script>
	  	//var myTouch = util.toucher(document.getElementById('myCarousel'));   
	    //$('.carousel').carousel({
	    //	interval: 10000
	    //}).on('slid.bs.carousel', function () {
	    //    var chartele=document.getElementById("SHOWTAB_W23_CURVE");
		//	echarts.getInstanceByDom(chartele).resize();
		//})
		var carousel_condition = function(){
			var ctx = $(".my-carousel-inner");
			var el = ctx[0];
			var position = parseInt(el.style.left)/100 || el.offsetLeft/el.offsetWidth;
			var index = -Math.floor(position);
			var total = ctx.find(">*").length;
			return {
				silder:ctx,
				position:position,
				index:index,
				total:total,
			};
		};
		var carousel_next = function(){
			var cond = carousel_condition();
			var target = cond.index+1;
			if(target>=cond.total){
				target = 0;
			}else{
				target = cond.silder.find('>:nth-child('+(cond.index+2)+')').find('>*>*').length/4+cond.index
			}
			cond.silder.stop().animate({left:-target*100+"%"},1e3);
		};
		var carousel_prev = function(){
			var cond = carousel_condition();
			var target = cond.index-1;
			if(target<0){
				target = cond.total - 2 + cond.silder.find('>:nth-child('+cond.total+')').find('>*>*').length/4
			}
			cond.silder.stop().animate({left:-target*100+"%"},1e3);
		};
		$(".right-btn").on("click",carousel_next);
		
		if (carouNum > 4){
			var is_mouse_over = false;//开启底部滑动
			$("#myCarousel").on("mouseover",function(){
				is_mouse_over = true;
			}).on("mouseout",function(){
				is_mouse_over = false;
			});
			setInterval(function(){
				if(!is_mouse_over) carousel_next();
			},1e4);
		}
		if (carouNum<5){
			$(".right-btn").hide();
		}
	    var myTouch = util.toucher(document.getElementById('myCarousel')); 
	    myTouch.on('swipeLeft',function(e){  
	        carousel_next();  
	    }).on('swipeRight',function(e){  
	        carousel_prev();  
	    });
	</script>
	<script>
	//调整大额资产管理页面
	   function goZichan(){
	        if($.inArray("&bs_ip7&", ButtonList)==-1){
				alert("您没有这块的权限");
				return;
			}
	        if(!WholeYear) WholeYear=nowDate.getFullYear(); 
	        if(!WholeMonth) WholeMonth=nowDate.getMonth()-1; //默认前二个月
	      	var url = "/bim_show/zc/getZc?homeyear="+WholeYear+"&homemonth="+WholeMonth;
			window.location.href = url;
	   }
	   
	   //下达指令
	   //typeid 模块序号
	   //mapId 查询具体指标或者表序号 参考def_task
		function sendorder(typeid,mapId,charttitle){
			var url=basePath+"/task/instruction/_add?type="+typeid+"&mapId="+mapId+"&taskName="+(charttitle).replace(/\%/g,'%25');
			mload(url);       
		}
	   
	 /*   function openBim(){
	 
	      var url="http://bima.hna.net/"+DESvemployeeId;
	      window.open(url, "_blank");
	   } */
	   
	 	   	   
	</script>
	<script>
		$(document).ready(function() {
			$(".sent-order").click(function() { //alert("1111");
				$(this).toggleClass("active");
				$(".orderbtn").toggleClass("show");
				return false;
			});
			$(document).click(function() {
				$(".sent-order").removeClass("active");
				//$(".orderbtn").removeClass("show");
			});
		});
	</script>
	<script>
	  window.modal_load = window.mload = function(url,callback){
			if(url){
				var ifm = document.getElementById('modal-frame');
					$('>.modal-close',ifm.parentNode).css('display','block');
				ifm.onload = function(){
					ifm.onload = undefined;
					$('>.modal-close',ifm.parentNode).css('display','');
					var h = ifm.contentDocument.body.scrollHeight;
					ifm.style.height = h+'px';
					modal_resize();
					if(callback){
						setTimeout(callback.bind(ifm.contentWindow));
					}
				};
				ifm.src = url;
				$('[data-remodal-id=page]').remodal({
					closeOnEscape:false,
					//closeOnOutsideClick:false,
				}).open();
			}
		};
		window.modal_close = window.mclose = function(){
			var ifm = $('[data-remodal-id=modal] iframe')[0];
			$('[data-remodal-id=page]').remodal().close();
			ifm.src = '';
			ifm.style.height = '';
		};
		// modal - 居中
		var modal_resize = function(){
			var ifm = document.getElementById('modal-frame');
			var hc = ifm.offsetHeight;
			if(hc == 0) return;
			var h = parseInt(ifm.style.height) || 0;
			var c = ifm.parentNode;
			var padding = (c.offsetHeight - hc)/2;
			if(hc == h){
				c.style['padding-top'] = padding + 'px';
				c.style['padding-bottom'] = padding + 'px';
			}else{
				c.style['padding-top'] = '';
				c.style['padding-bottom'] = '';
			}
		};
		$(window).resize(modal_resize);
	</script>
	<script type="text/javascript">
	
		    setTimeout( function(){
				  //  drawStar();
				}, 0 * 1000 );	             
            
            function drawStar(){
                $("#html5_3d_animation").html5_3d_animation({
		            window_width: '1000',
		            window_height: '400',
		            window_background: '#222b3b',
		            star_count: '1000',
		            star_color: '#235e80',
		            star_depth: '100'
		        });
		        
		         var stop = setTimeout(function () {
	            	$("#html5_3d_animation").fadeOut(1000);
	            },1000);
            }
            
            //窗口关闭刷新指令数量
            	$(document).on('closed','.remodal',function(){
			   		alterNum(DESvemployeeId);
			   	});
		    
		    
		!function(){
			var contents = $('.my-carousel-inner>.item>.block-content');
			contents.find('>*').each(function(i){
				var g = Math.floor(i/4);
				this.parentNode.removeChild(this);
				contents[g].appendChild(this);
			});
		}();
		</script>

</body>

</html>