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
		<link rel="stylesheet" href="<c:url value="/css/scroll/jquery.mCustomScrollbar.css"/>" />
		<script>
		var toArray = Array.from || function(arrayLike) {
			return [].slice.call(arrayLike)
		};
		</script>
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
			.left_menu li{
				margin: 0;
			}
			.left_menu a {
			    padding: 10px 20px;
			    display: block;
			    width: 15em;
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
				border-bottom: 1px solid #172c44;
			}
			.left_menu li {
			    text-align: left;
			}
			.left_menu, .left_tab {
			    left: 0px;
			    width: 15em;
			    background: #1d3654;
			    top: 70px;
			    height: calc(100%) !important;
			    z-index: 9;
			    margin: 0px;
			}
			.right-content {
			    margin-top: 0px;
		        position: relative;
			    display: inline-block;
			    width: calc(100% - 168px);
			    margin-left: 0 !important;
			    float: right;
			}
			#left_panel{
			    width: 15em;
			    height: 100%;
			    display: inline-block;
		        background: #1d3654;
			}
			
			/* 二级菜单 */
			.l1{
				color: #ccc;
			    text-align: left !important;
			}
			.l1>span{
				padding: 10px 10px 10px 20px;
				display: inline-block;
				width: 100%;
				cursor: pointer;
			    /* border-bottom: 1px solid #172c44; */
			}
			.l1_active>span{
				border-bottom: none;
			}
			.l1:last-child>span{
				border-bottom: none;
			}
			.l1 ul{
				background: #172c44;
				height: 0;
				transition: all 0.2s linear;
				overflow: hidden;
			}
			.l1 ul li{
			    line-height: 40px;
   				border-bottom: 1px solid #394552 !important;
				text-align: left;
    			padding-left: 30px;
   			    margin-bottom: 0;
   			    transition: all 0.2s linear;
    			/* cursor: pointer !important; */
   			    overflow: hidden;
			}
			.l1 ul>li:last-child{
				border-bottom: none !important;
			}
			.l1 ul li:hover{
				background: #cd1b0d;
				color: #fff;
			}
			.l1 ul li:last-child{
   				border-bottom: none;
			}
			.l1_active ul{
				height: 200px;
				transition: all 0.2s linear;
			}
			.left_menu li i.iconfont {
			    display: inline;
			    height: auto;
			    line-height: 20px;
			    font-size: 20px;
			    color: #aaa;
			    transition: all 0.2s linear;
			    float: right;
			}
			.l1 li>span:first-child{
			    display: block;
			    width: 160px;
			    overflow: hidden;
			    text-overflow: ellipsis;
			    white-space: nowrap;
			    float: left;
			}
			.l1 a {
			    padding: 0;
			    display: block;
			    width: 100%;
			    overflow: hidden;
			    text-overflow: ellipsis;
			    white-space: nowrap;
			    color: #cacaca;
			}
			.l1 li a.active, .l1 li a:hover {
			    background-color: transparent;
			    color: #FFFFFF;
			    transition: all 0.2s linear;
			}
			.l2_active{
				background: #121c25;
			}
			.l2_active>.tri{
				width: 0;
			    height: 0;
			    border-top: 5px solid transparent;
			    border-right: 5px solid #f2f8ff;
			    border-bottom: 5px solid transparent;
			    float: right;
			    margin-top: 13px;
			}
			.left_menu li a.active {
			    color: #fff;
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
		<script type="text/javascript">
			var to_def_img = function(el){
				el.src = "<c:url value="/img/head.png"/>";
			};
		</script>
		
			<div class="container-fluid sec-maincontent">
			<div id="left_panel">
			<ul class="left_menu" id="left_menu">
				<c:if test="${ menu==4 }">
	                 <li>
	                    <ul>
	                    	<c:if test="${fn:contains(gzwsession_page,',Work_qyxxcj,')==true}">
	                    		<li class="js_sjsq"><a href="${pageContext.request.contextPath}/adDocument/query" target="mainFrame">企业信息采集</a></li>
	                    	</c:if>
	                    	<c:if test="${fn:contains(gzwsession_page,',Work_qyxxcjsh,')==true}">
	                    		<li class="js_sjsq"><a href="${pageContext.request.contextPath}/adDocument/examineQuery" target="mainFrame">企业信息采集审核</a></li>	                    	
	                    	</c:if>
	                    	<c:if test="${fn:contains(gzwsession_page,',Work_ysxxcj,')==true}">
	                    		<li class="js_sjsq"><a href="${pageContext.request.contextPath}/adSupervise/query" target="mainFrame">预算信息采集</a></li>
	                    	</c:if>
	                    	<c:if test="${fn:contains(gzwsession_page,',Work_ysxxcjsh,')==true}">
	                    		<li class="js_sjsq"><a href="${pageContext.request.contextPath}/adSupervise/examineQuery" target="mainFrame">预算信息采集审核</a></li>
	                    	</c:if>
	                    	<c:if test="${fn:contains(gzwsession_page,',Work_jgyjsjcj,')==true}">
	                    		<li class="js_sjsq"><a href="${pageContext.request.contextPath}/adRiskevent/query" target="mainFrame">监管预警数据采集</a></li>
	                    	</c:if>
	                    	<c:if test="${fn:contains(gzwsession_page,',Work_jgyjsjcjsh,')==true}">
	                    		<li class="js_sjsq"><a href="${pageContext.request.contextPath}/adRiskevent/examineQuery" target="mainFrame">监管预警数据采集审核</a></li>                    	
	                    	</c:if>
	                    	<c:if test="${fn:contains(gzwsession_page,',Work_qycwsjcj,')==true}">
	                    		<li class="js_sjsq"><a href="${pageContext.request.contextPath}/adImportant/query" target="mainFrame">企业财务数据采集</a></li>
	                    	</c:if>
	                    	<c:if test="${fn:contains(gzwsession_page,',Work_qycwsjcjsh,')==true}">
	                    		<li class="js_sjsq"><a href="${pageContext.request.contextPath}/adImportant/examineQuery" target="mainFrame">企业财务数据采集审核</a></li>	                    	
	                    	</c:if>
	                    	<c:if test="${fn:contains(gzwsession_page,',Work_zdsxsjcj,')==true}">
	                    		<li class="js_sjsq"><a href="${pageContext.request.contextPath}/hr/employeecare/list" target="mainFrame">重大事项数据采集</a></li>
	                    	</c:if>
	                    	<c:if test="${fn:contains(gzwsession_page,',Work_zdsxsjcjsh,')==true}">
	                    		<li class="js_sjsq"><a href="${pageContext.request.contextPath}/hr/employeecare/examinelist" target="mainFrame">重大事项数据采集审核</a></li>	                    		                    	
	                    	</c:if>
	                    	<c:if test="${fn:contains(gzwsession_page,',Work_cqxxcj,')==true}">
	                    		<li class="js_sjsq"><a href="${pageContext.request.contextPath}/headCount/list" target="mainFrame">产权信息采集</a></li>
	                    	</c:if>
	                    	<c:if test="${fn:contains(gzwsession_page,',Work_cqxxcjsh,')==true}">
	                    		<li class="js_sjsq"><a href="${pageContext.request.contextPath}/headCount/examinelist" target="mainFrame">产权信息采集审核</a></li>	                    	
	                    	</c:if>
	                    	<c:if test="${fn:contains(gzwsession_page,',Work_dshkpxxcj,')==true}">
	                    		<li class="js_sjsq"><a href="${pageContext.request.contextPath}/laborCost/list" target="mainFrame">董事会考评信息采集</a></li>
	                    	</c:if>
	                    	<c:if test="${fn:contains(gzwsession_page,',Work_dshkpxxcjsh,')==true}">
	                    		<li class="js_sjsq"><a href="${pageContext.request.contextPath}/laborCost/examinelist" target="mainFrame">董事会考评信息采集审核</a></li>	                    		                    	
	                    	</c:if>
	                    	<c:if test="${fn:contains(gzwsession_page,',Work_zcxxcj,')==true}">
	                    		<li class="js_sjsq"><a href="${pageContext.request.contextPath}/managerialStaff/list" target="mainFrame">资产信息采集</a></li>
	                    	</c:if>
	                    	<c:if test="${fn:contains(gzwsession_page,',Work_zcxxcjsh,')==true}">
	                    		<li class="js_sjsq"><a href="${pageContext.request.contextPath}/managerialStaff/examinelist" target="mainFrame">资产信息采集审核</a></li>	                    	
	                    	</c:if>
	                    	<c:if test="${fn:contains(gzwsession_page,',Work_xcsjcj,')==true}">
	                    		<li class="js_sjsq"><a href="${pageContext.request.contextPath}/sixRateKp/list" target="mainFrame">薪酬数据采集</a></li>
	                    	</c:if>
	                    	<c:if test="${fn:contains(gzwsession_page,',Work_xcsjcjsh,')==true}">
	                    		<li class="js_sjsq"><a href="${pageContext.request.contextPath}/sixRateKp/examinelist" target="mainFrame">薪酬数据采集审核</a></li>	                    	
	                    	</c:if>
	                    	<c:if test="${fn:contains(gzwsession_page,',Work_xfsjcj,')==true}">
	                    		<li class="js_sjsq"><a href="${pageContext.request.contextPath}/sixRateDc/list" target="mainFrame">信访数据采集</a></li>
	                    	</c:if>
	                    	<c:if test="${fn:contains(gzwsession_page,',Work_xfsjcjsh,')==true}">
	                    		<li class="js_sjsq"><a href="${pageContext.request.contextPath}/sixRateDc/examinelist" target="mainFrame">信访数据采集审核</a></li>                    	
	                    	</c:if>
	                    	<c:if test="${fn:contains(gzwsession_page,',Work_djgzxxcj,')==true}">
	                    		<li class="js_sjsq"><a href="${pageContext.request.contextPath}/social/voluntaryservice/list" target="mainFrame">党建工作信息采集</a></li>
	                    	</c:if>
	                    	<c:if test="${fn:contains(gzwsession_page,',Work_djgzxxcjsh,')==true}">
	                    		<li class="js_sjsq"><a href="${pageContext.request.contextPath}/social/voluntaryservice/examinelist" target="mainFrame">党建工作信息采集审核</a></li>                    	
	                    	</c:if>
	                    	<c:if test="${fn:contains(gzwsession_page,',Work_zxxxcj,')==true}">
	                    		<li class="js_sjsq"><a href="${pageContext.request.contextPath}/social/media/list" target="mainFrame">征信信息采集</a></li>
	                    	</c:if>
	                    	<c:if test="${fn:contains(gzwsession_page,',Work_zxxxcjsh,')==true}">
	                    		<li class="js_sjsq"><a href="${pageContext.request.contextPath}/social/media/examinelist" target="mainFrame">征信信息采集审核</a></li>
	                    	</c:if>
	                    </ul>
	                </li>
                </c:if>
				<c:if test="${ menu==5 }">
                     <li>
	                    <ul>
	                        <c:if test="${fn:contains(bim_work_session_page,',work_qyxxcjhztj,')==true}">
	                              <li class="js_sjsq"><a href="${pageContext.request.contextPath}/adDocument/query1" target="mainFrame">企业信息采集汇总统计</a></li>
	                        </c:if>
	                         <c:if test="${fn:contains(bim_work_session_page,',work_yssjhztj,')==true}">
	                              <li class="js_sjsq"><a href="${pageContext.request.contextPath}/adSupervise/query1" target="mainFrame">预算数据汇总统计</a></li>
	                        </c:if>
	                         <c:if test="${fn:contains(bim_work_session_page,',work_jgyjsjhztj,')==true}">
	                              <li class="js_sjsq"><a href="${pageContext.request.contextPath}/adRiskevent/query1" target="mainFrame">监管预警数据汇总统计</a></li>
	                        </c:if>
	                         <c:if test="${fn:contains(bim_work_session_page,',work_qycwsjhztj,')==true}">
	                               <li class="js_sjsq"><a href="${pageContext.request.contextPath}/adImportant/examineQueryGather" target="mainFrame">企业财务数据汇总统计</a></li>
	                        </c:if>
	                         <c:if test="${fn:contains(bim_work_session_page,',work_zdsxsjhztj,')==true}">
	                                <li class="js_sjsq"><a href="${pageContext.request.contextPath}/hr/employeecare/examinelistGather" target="mainFrame">重大事项数据汇总统计</a></li>
	                        </c:if>
	                         <c:if test="${fn:contains(bim_work_session_page,',work_cqxxhztj,')==true}">
	                                <li class="js_sjsq"><a href="${pageContext.request.contextPath}/headCount/proRightInfoCollect" target="mainFrame">产权信息汇总统计</a></li>
	                        </c:if>
	                         <c:if test="${fn:contains(bim_work_session_page,',work_dshkpxxhztj,')==true}">
	                               	<li class="js_sjsq"><a href="${pageContext.request.contextPath}/laborCost//hzexaminelist" target="mainFrame">董事会考评信息汇总统计</a></li>
	                        </c:if>
	                         <c:if test="${fn:contains(bim_work_session_page,',work_zcxxhztj,')==true}">
	                                <li class="js_sjsq"><a href="${pageContext.request.contextPath}/managerialStaff/hzexaminelist" target="mainFrame">资产信息汇总统计</a></li>
	                        </c:if>
	                         <c:if test="${fn:contains(bim_work_session_page,',work_xcshhztj,')==true}">
	                                <li class="js_sjsq"><a href="${pageContext.request.contextPath}/sixRateKp/hzexaminelist" target="mainFrame">薪酬数据汇总统计</a></li>
	                        </c:if>
	                         <c:if test="${fn:contains(bim_work_session_page,',work_xfsjhztj,')==true}">
	                                <li class="js_sjsq"><a href="${pageContext.request.contextPath}/sixRateDc/summarypetitionlist" target="mainFrame">信访数据汇总统计</a></li>	  
	                        </c:if>
	                         <c:if test="${fn:contains(bim_work_session_page,',work_djgzxxhztj,')==true}">
	                             	<li class="js_sjsq"><a href="${pageContext.request.contextPath}/social/voluntaryservice/summaryPartyworklist" target="mainFrame">党建工作信息汇总统计</a></li>	 
	                        </c:if>
	                         <c:if test="${fn:contains(bim_work_session_page,',work_zxxxhztj,')==true}">
                                    <li class="js_sjsq"><a href="${pageContext.request.contextPath}/social/media/summaryCreditInfolist" target="mainFrame">征信信息汇总统计</a></li>	
	                        </c:if>             		                    		                    	                	
	                    </ul>
	                </li>
                </c:if>
				<c:if test="${ menu==6 }">
                      <li>
	                    <ul>
	                        <c:if test="${fn:contains(bim_work_session_page,',work_qyxxsjjhqk,')==true}">
	                               <li class="js_sjsq"><a href="${pageContext.request.contextPath}/adDocument/query2" target="mainFrame">企业信息数据交换情况</a></li>
	                        </c:if>
	                        <c:if test="${fn:contains(bim_work_session_page,',work_ysxxsjjhqk,')==true}">
	                               <li class="js_sjsq"><a href="${pageContext.request.contextPath}/adSupervise/query2" target="mainFrame">预算信息数据交换情况</a></li>
	                        </c:if>
	                        <c:if test="${fn:contains(bim_work_session_page,',work_jgyjsjjhqk,')==true}">
	                               <li class="js_sjsq"><a href="${pageContext.request.contextPath}/adRiskevent/query2" target="mainFrame">监管预警数据交换情况</a></li>
	                        </c:if>
	                        <c:if test="${fn:contains(bim_work_session_page,',work_qycwsjjhqk,')==true}">
	                               <li class="js_sjsq"><a href="${pageContext.request.contextPath}/adImportant/examineQueryExchange" target="mainFrame">企业财务数据交换情况</a></li>
	                        </c:if>
	                        <c:if test="${fn:contains(bim_work_session_page,',work_zdsxsjjhqk,')==true}">
	                              <li class="js_sjsq"><a href="${pageContext.request.contextPath}/hr/employeecare/examinelistExchange" target="mainFrame">重大事项数据交换情况</a></li>
	                        </c:if>
	                        <c:if test="${fn:contains(bim_work_session_page,',work_cqxxhzsjjhqk,')==true}">
	                             <li class="js_sjsq"><a href="${pageContext.request.contextPath}/headCount/proRightInfoExchange" target="mainFrame">产权信息汇总数据交换情况</a></li>
	                        </c:if>
	                        <c:if test="${fn:contains(bim_work_session_page,',work_dshkpxxsjjhqk,')==true}">
	                             <li class="js_sjsq"><a href="${pageContext.request.contextPath}/laborCost//jhexaminelist" target="mainFrame">董事会考评信息数据交换情况</a></li>
	                        </c:if>
	                        <c:if test="${fn:contains(bim_work_session_page,',work_zcxxsjjhqk,')==true}">
	                             <li class="js_sjsq"><a href="${pageContext.request.contextPath}/managerialStaff/jhexaminelist" target="mainFrame">资产信息数据交换情况</a></li>	    
	                        </c:if>
	                        <c:if test="${fn:contains(bim_work_session_page,',work_xcsjjhqk,')==true}">
	                              <li class="js_sjsq"><a href="${pageContext.request.contextPath}/sixRateKp//jhexaminelist" target="mainFrame">薪酬数据数据交换情况计</a></li>	   
	                        </c:if>
	                        <c:if test="${fn:contains(bim_work_session_page,',work_xfsjjhqk,')==true}">
	                              <li class="js_sjsq"><a href="${pageContext.request.contextPath}/sixRateDc/datapetitionlist" target="mainFrame">信访数据数据交换情况</a></li>	   
	                        </c:if>
	                        <c:if test="${fn:contains(bim_work_session_page,',work_djgzxxsjjhqk,')==true}">
	                              <li class="js_sjsq"><a href="${pageContext.request.contextPath}/social/voluntaryservice/dataPartyworklist" target="mainFrame">党建工作信息数据交换情况</a></li>	 	   
	                        </c:if>
	                        <c:if test="${fn:contains(bim_work_session_page,',work_zxxxsjjhqk,')==true}">
	                              	<li class="js_sjsq"><a href="${pageContext.request.contextPath}/social/media/dataCreditInfolist" target="mainFrame">征信信息数据交换情况</a></li>
	                        </c:if>         
	                    </ul>
	                </li>
                </c:if>
               
				<c:if test="${ menu==8 }">
	           		<li>
	                    <ul>
	                        <c:if test="${fn:contains(bim_work_session_page,',work_zymlqd,')==true}">
	                                <li class="js_sjsq"><a href="${pageContext.request.contextPath}/bimr/riskEvent/feedbacklist?type=feedback" target="mainFrame">资源目录清单</a></li>
	                        </c:if>
	                        <c:if test="${fn:contains(bim_work_session_page,',work_zymlqdsh,')==true}">
	                                <li class="js_sjsq"><a href="${pageContext.request.contextPath}/bimr/riskEvent/feedbacklist?type=examine" target="mainFrame">资源目录清单审核</a></li>
	                        </c:if>
	                        <c:if test="${fn:contains(bim_work_session_page,',work_sjkb,')==true}">
	                                 <li class="js_sjsq"><a href="${pageContext.request.contextPath}/bimr/riskEvent/relevancelist?type=relevancelist" target="mainFrame">数据看板</a></li>
	                        </c:if>
	                        <c:if test="${fn:contains(bim_work_session_page,',work_sjkbsh,')==true}">
	                            	<li class="js_sjsq"><a href="${pageContext.request.contextPath}/bimr/riskEvent/relevancelist?type=relevanceexamine" target="mainFrame">数据看板审核</a></li>
	                        </c:if>
	                        <c:if test="${fn:contains(bim_work_session_page,',work_xxjs,')==true}">
	                               <li class="js_sjsq"><a href="${pageContext.request.contextPath}/bimr/contractmonth/import?type=import" target="mainFrame">信息检索</a></li>
	                        </c:if>
	                        <c:if test="${fn:contains(bim_work_session_page,',work_xxjssh,')==true}">
	                               <li class="js_sjsq"><a href="${pageContext.request.contextPath}/bimr/contractmonth/import?type=examine" target="mainFrame">信息检索审核</a></li>
	                        </c:if>						
	                    </ul>
	                </li>
                </c:if>
                <c:if test="${ menu==9 }"> 
                	<c:if test="${fn:contains(gzwsession_page,',bimWork_zxdt,')}">
                		<li>
		                	<ul>
		                		<c:if test="${fn:contains(gzwsession_page,',bimWork_zxdt,')}">
		                			<li class="js_sjsq"><a href="${pageContext.request.contextPath}/portal/news/list" target="mainFrame">动态维护</a></li>
		                		</c:if>
				         	</ul>
		             	</li>
                	</c:if>
                </c:if>
			</ul>
			
			</div>
			<div class="right-content">
				<c:if test="${ !empty  gzwsession_page}">
					<iframe id="mainFrame" name="mainFrame" scrolling="auto" src="" frameborder="0" style="height: 100%;padding: 0px; width: 100%;display:block;">
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
	
	<script>
		// 整页面缩放 仅针对 pc 模拟移动设备viewpoint标签效果
		if(!/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)){
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
		}
	</script>
	<script src="<c:url value="/js/mCustomScrollbar.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/jquery.mousewheel.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/circles.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/remodal.min.js"/>" charset="utf-8"></script>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script type="text/javascript">
	
		$("#left_menu").mCustomScrollbar({
			theme : "minimal"
		});
	
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
        
       	//二级菜单
       	$(".l1>span").on("click",function(){
       		var n = $(this).siblings("ul").children("li").length;
       		if(!$(this).parents("li").eq(0).hasClass("l1_active")){
       			$(this).parents("li").eq(0).addClass("l1_active");
       			$(this).siblings("ul").css("height",n*41+"px");
       			$(this).children("i").removeClass("icon-zhankai").addClass("icon-zhankai-copy");
       		}else{
       			$(this).parents("li").eq(0).removeClass("l1_active");
       			$(this).siblings("ul").css("height",0);
       			$(this).children("i").removeClass("icon-zhankai-copy").addClass("icon-zhankai")
       		}
       	})
       	$(".l1 li").on("click",function(){
       		if(!$(this).hasClass("l2_active")){
       			$(".l2_active").removeClass("l2_active");
       			$(".tri").remove();
       			$(this).addClass("l2_active").append("<span class=\"tri\"></span>");
       		}
       	})
	</script>
	
</html>