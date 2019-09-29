﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=1920,user-scalable=no">
		<title>海航实业BIM运营管控平台</title>
		<link href="${pageContext.request.contextPath}/style/lin.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/style/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/style/font/iconfont.css" />
		<style>
			.sec-maincontent .right-content{
				margin-top:8% !important;
				margin-left:20% !important;
			}
			.undiv{
			    display: inline-block;
			    padding-left: 5em;
			    vertical-align: middle;
			}
			.unp1{
			    font-size: 70px;
   				color: #47a1c3;
			}
			.unp2{
				font-size: 50px;
    			color: #b1b1b1;
			}
		</style>
	</head>
	<body class="hn_grey">
		<jsp:include page="../homepagetitle.jsp" flush="true"/>
		<div class="container-fluid sec-maincontent">
			<div style="display: block;position: fixed;background-color: #121c25;z-index: -1;width: 84px;height: calc(100vh + 70px) !important;left: 0px;top: 0px;"></div>
			<ul class="left_menu">
				<c:if test="${fn:contains(bimShow_ButtonList,'&bs_sp2&')}">
				<li>
					<a id="DeptShow_1"	onclick="trunToChartShow(this)" class="">
						<i class="iconfont icon-bt iconbg"><i class="iconfont icon-icon-test"></i></i>
						<span>六率指标</span>
					</a>
				</li>
				</c:if>
				<c:if test="${fn:contains(bimShow_ButtonList,'&bs_sp1&')}">
				 <li>
					<a id="dept_12"  href="<c:url value='/project/_list'/>" class="" >
						<i class="iconfont icon-zhongdiangongcheng"></i>
						<span>重点基建工程</span>
					</a>
				</li>
				</c:if>
				<c:if test="${fn:contains(bimShow_ButtonList,'&bs_sp3&')}">
				<li>
					<a id="DeptShow_8"	onclick="trunToChartShow(this)" class="">
							<i class="iconfont icon-hangzheng"></i>
						<span>经营指标</span>
					</a>
				</li>
				</c:if>
				<c:if test="${fn:contains(bimShow_ButtonList,'&bs_sp7&')}">
				<li>
					<a id="dept_12"  href="javascript:void(0);" class="" onclick="activeDeptIcon_1();">
						<i class="iconfont icon-zichanguanli"></i>
						<span>大额资产管理</span>
					</a>
				</li>
				</c:if>
				<c:if test="${fn:contains(bimShow_ButtonList,'&bs_sp4&')}">
				<li>
					<a id="DeptShow_2" onclick="trunToChartShow(this)" href="#" class="">
						<i class="iconfont icon-hangzheng"></i>
						<span>行政办公</span>
					</a>
				</li>
				</c:if>
				<c:if test="${fn:contains(bimShow_ButtonList,'&bs_sp5&')}">
				<li>
					<a id="DeptShow_4" onclick="trunToChartShow(this)" href="#" class="">
						<i class="iconfont icon-renliziyuan"></i>
						<span>人力资源</span>
					</a>
				</li>
				</c:if>
				<c:if test="${fn:contains(bimShow_ButtonList,'&bs_sp6&')}">
				<li>
					<a id="DeptShow_10" href="/bim_show/views/home/homeTB/unConstrut.jsp" class="active">
						<i class="iconfont icon-tixiguanli"></i>
						<span>财务管理</span>
					</a>
				</li>
				</c:if>
				<c:if test="${fn:contains(bimShow_ButtonList,'&bs_sp11&')}">
				<li>
					<a  id="DeptShow_7" onclick="trunToChartShow(this)"  href="#" class="">
						<i class="iconfont icon-fk"></i>
						<span>风险控制</span>
					</a>
				</li>
				</c:if>
				<c:if test="${fn:contains(bimShow_ButtonList,'&bs_sp8&')}">
				<li>
					<a id="DeptShow_5" onclick="trunToChartShow(this)" href="#" class="">
						<i class="iconfont icon-shzr"></i>
						<span>社会责任</span>
					</a>
				</li>
				</c:if>
				<c:if test="${fn:contains(bimShow_ButtonList,'&bs_sp9&')}">
				<li>
					<a id="DeptShow_3"   onclick="trunToChartShow(this)" href="#" class="">
						<i class="iconfont icon-caigouguanli1"></i>
						<span>采购管理</span>
					</a>
				</li>
				</c:if>
			</ul>
			<div class="right-content" style="">
				<img src="${pageContext.request.contextPath}/views/home/homeTB/img/construt.png">
				<div class="undiv">
					<p class="unp1">页面建设中...</p>
					<p class="unp2">敬请期待</p>
				</div>
			</div>
		</div>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.js" ></script>	
	<script>
		function trunToChartShow(Obj){
			var deptid=$(Obj)[0].id;
			location.href = basePath + "/home/getTBDataByDept1?year=2017&month=9&deptid="+deptid;
		}
		function activeDeptIcon_1(){
			var url = "/bim_show/zc/getZc?homeyear=2017&homemonth=9";
			window.location.href = url;
		}
		
		function activeDeptIcon_2(){
			var url = "/bim_show/project/_list?homeyear=2017&homemonth=9";
			window.location.href = url;
		};
	</script>
</html>
