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
<title>海航实业BIM运营管控平台</title>

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

	<div no=text class="l_top l_top_bg2" >

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
		<!--  -->
		
		<c:if test="${fn:contains(bimShow_PageList,'&bimShow_indexPage&')==true}">
			<a id="indexPage" class="menu i i_btn" no=text
				href="/bim_show/index/index?pageId=1" title="首页">
				<i class="iconfont icon-shouye"></i>
			</a>
		</c:if>
		
		<c:if test="${fn:contains(bimShow_PageList,'&bimShow_title&') && fn:contains(bimShow_ButtonList,'&bimShow_order&')}">
			<a id="orderPage" class="menu i i_btn" no=text 
				href="${pageContext.request.contextPath}/ssoout/outsystem?isopen=true&url=/bim_task/system/index?taskOrInstruction=dealing" 
				target = "_blank" title="指令中心">
				<i class="iconfont icon-zhiling"></i><div id="zlmnum" class="grnum">0</div>
			</a>
		</c:if>
		<c:if test="${fn:contains(bimShow_PageList,'&bimShow_title&') && fn:contains(bimShow_ButtonList,'&bimShow_alarm&')}">
			<a id="alertInfo" class="menu i i_btn" no=text title="预警中心" 
				href="${pageContext.request.contextPath}/alarm/alarmIndex" target = "_blank">
				<i class="iconfont icon-yujing"></i>
			<div id="alarmnum" class="numxl">..</div>
			</a>
		</c:if>
		<c:if test="${fn:contains(bimShow_PageList,'&bimShow_title&') && (fn:contains(bimShow_ButtonList, '&bimShow_supply&') || fn:contains(bimShow_ButtonList,'&bimShow_bima&')|| fn:contains(bimShow_ButtonList,'&bimShow_datachange&') || fn:contains(bimShow_ButtonList,'&bimShow_base&') || fn:contains(bimShow_ButtonList,'&bimShow_s3&') || fn:contains(bimShow_ButtonList,'&bimShow_s4&') || fn:contains(bimShow_ButtonList,'&bimShow_medical&'))}">
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
			<a id="turnToPortal" class="menu i i_btn exit" no=text  href="${pageContext.request.contextPath}/ssoout/outsystem?isopen=true&url=/bim_portal/system/ladp_portal" target="_blank" title="后台管理中心">
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
		//平台跳转
		$('.show_nav_box').on('click', function (event) {
			event.stopPropagation();
			if($('#nav_box').css('display') == 'none') {
				$('#nav_box').show();
			} else {
				$('#nav_box').hide();
			}
		});
		
		$(document).on('click', function () {
		$('#nav_box').hide();
		});

		$('.show_nav_box').on('mouseover', function () {
			$('#nav_box').show();
		});
		
		$('.show_nav_box').on('mouseleave', function () {
		$('#nav_box').hide();
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

		function checkAlarmNum() {
			$.ajax({
				url : basePath + "/alarm/SumCount",
				type : "POST",
				success : function(data) {
					$('#alarmnum').text(data);
				},
				error : function(data) {
					alert(data);
				}
			});
		}
		
		var employeeId='${sessionScope.show_session_users.vcEmployeeId}';
		alterNum(employeeId);
		function alterNum(employeeId){
		    var date = new Date();
			$.ajax({
				url : basePath + "/alarm/getZLCount?employeeId="+employeeId+"&today="+date,
				type : "POST",
				success : function(data) {
					$('#zlmnum').text(data);
				},
				error : function(data) {
					alert(data);
				}
			});
		}
	</script>
	<script>
		//function openzhilinwindow(){
		 /*    var nowDate= new Date();
		    var formatdate=dateFtt("yyyy-MM-dd",nowDate) ; 
		    var url="/bim_task/system/index?vcEmployeeId=${show_session_users.vcEmployeeId}&deadLine="+formatdate; */
		    //var url="/bim_task/system/index?vcEmployeeId=${show_session_users.vcEmployeeId}&taskOrInstruction=dealing";
		    //var url="/bim_task/ssoout/outsystem?url=/bim_task/system/index?taskOrInstruction=normal";
		    //window.open(url, "_blank");
		    //window.location.href = url;
		//}
		function dateFtt(fmt,date){
			var o = {
				"M+" : date.getMonth()+1,                 //月份
				"d+" : date.getDate(),                    //日
				"h+" : date.getHours(),                   //小时
				"m+" : date.getMinutes(),                 //分
				"s+" : date.getSeconds(),                 //秒
				"q+" : Math.floor((date.getMonth()+3)/3), //季度
				"S"  : date.getMilliseconds()             //毫秒
			};
			if(/(y+)/.test(fmt)){
				fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));
			}
			for(var k in o){
				if(new RegExp("("+ k +")").test(fmt)){
					fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
				}
			}
			return fmt;
		}
		
	</script>
	
</body>

</html>