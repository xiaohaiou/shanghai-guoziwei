<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=1580, user-scalable=no"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
<!--meta name="viewport" content="width=device-width, initial-scale=0.75, maximum-scale=0.75, user-scalable=no" /-->
<meta name="x5-orientation" content="landscape">
<title>海航物流运营管控平台</title>
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
	href="${pageContext.request.contextPath}/css/remodal.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/remodal-default-theme.css">
	
</head>

  
  <body>
 <jsp:include page="hometitle.jsp" flush="true" /> 
	<style>
		body{
			margin: 0;
			padding: 0;
			background: url(../../img/bg-video.jpg) no-repeat center center fixed;
			background-size: 100%;
			color: #D8D8D8;
			letter-spacing: 2px;
			font-family: 微软雅黑;

			width:100%;
    		height:100%;
    		background-size:100% 100%;
    		position:absolute;
		}
		section{
			/* height: 15rem; */
			background: linear-gradient(rgba(255,255,255,0.05), rgba(0,0,0,0));
			padding: 0 10rem 3rem;
		}
		section a{
			color: #D8D8D8;
			text-decoration: none;
		}
		section p{
			margin: 0;
			margin-bottom: 1.5rem;
			padding-top: 1.7rem;
			font-size: 25px;
			line-height: 2em;
		}
		section span{
			display: inline-block;
			width: 8em;
			text-align: center;
			font-size: 18px;
			background: rgba(166,166,166,0.1);
			padding: 1.5rem 0;
			margin: 0 1rem 1rem 0;
			border-radius: 5px;
		}
		section span:hover{
			color:#FFF;
			background:#03B1E7;		
		}
		.p1 p{
			letter-spacing: 4px;
			font-size: 40px;
			padding-top: 2.5rem;
		}
		.p1 span{
			width: 10em;
			font-size: 22px;
			margin-right: 2rem;
		}
        </style>
    <section class="p1">
    	<p>
    		海航物流 BIM 管理数字化平台
    	</p>
    	<c:if test="${fn:contains(sysNums,'bim_show')==true}">
    		<a href="/bim_portal/ssoout/outsystem?url=/bim_show/index/index?pageId=1"><span>运营管控驾驶舱</span></a>
    	</c:if>
    	<c:if test="${fn:contains(sysNums,'bim_show')!=true}">
    		<a href=""><span>运营管控驾驶舱</span></a>
    	</c:if>
    	<c:if test="${fn:contains(sysNums,'bim_work')==true}">
    		<a href="/bim_portal/ssoout/outsystem?url=/bim_work/system/ladp_portal"><span>工作台</span></a>
    	</c:if>
    	<c:if test="${fn:contains(sysNums,'bim_work')!=true}">
    		<a href=""><span>工作台</span></a>
    	</c:if>
    </section>
    <section class="p2">
    	<p>
    		职能体系
    	</p>
    	<a href="#"><span>行政办公</span></a>
    	<a href="#"><span>人力资源</span></a>
    	<a href="#"><span>财务管理</span></a>
    	<a href="#"><span>大额资产管理</span></a>
    	<a href="#"><span>社会责任</span></a>
    	<a href="#"><span>采购管理</span></a>
    	<a href="#"><span>安全生产</span></a>
    	<a href="#"><span>风险控制</span></a>
    </section>
    <section class="p3">
    	<p>
    		业态
    	</p>
    	<a href="#"><span>海航基础</span></a>
    	<a href="#"><span>供销大集</span></a>
    	<a href="#"><span>海航金融</span></a>
    	<a href="#"><span>海航资产</span></a>
    	<a href="#"><span>教育医疗</span></a>
    </section>
  </body>
</html>


