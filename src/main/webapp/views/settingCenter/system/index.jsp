<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>后台管理中心（体验版）</title>
	<!-- 库|插件 -->
	<link rel="stylesheet" href="<c:url value="/css/remodal.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/remodal-default-theme.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/flexslider.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/font/iconfont.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/index.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/header.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/swiper-3.3.1.min.css"/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/banner.css"/>"/>
	<style>
		.schedule-list .detail{
			width: calc(100% - 150px);		
		}
		
		.schedule-list>li>.detail>li {
		    color: #666;
		    font-size: 16px;
		    line-height: 16px;
		    padding-left: 20px;
		    width: 100%;
		    white-space: nowrap;
		    overflow: hidden;
		    text-overflow: ellipsis;
		}
		
	</style>
</head>

<body class="hn_page">
	<script type="text/javascript">
		var to_def_img = function(el){
			el.src = "<c:url value="/img/head.png"/>";
		}
	</script>
	<input type="hidden" value="${week}" id="curWeek"/>
	
	<div no=text class="l_top l_top_bg2">
	
	
		<img src="<c:url value="/img/hn_logo1.png"/>" alt="header" class="l_logo">
		<p class="l_logo_p1">运营管控平台(体验版)</p>
		<p class="l_logo_p2">OPERATION CONTROL PLATFORM</p>
		

		<div class="menu time"></div>
		
		<div class="menu user" no="text">
			<div>
				<div class="name">${portal_users_session.vcName}</div>
				<!-- <div class="title">${portal_users_session.vcPostName}</div> -->
			</div>
			
			<div class="avatar">
					<img src="${portal_users_session.imgPath}" onerror="to_def_img(this)" alt="头像">
			</div>
			
		</div>
		
		<a class="menu i i_btn" no=text href="/bim_show/index.jsp?vcEmployeeId=${portal_users_session.vcEmployeeId}&pageId=1">
			<img icon src="<c:url value="/img/asset.png"/>" alt="运营管控平台">
		</a>
		
		<a class="menu i i_btn" no=text href="<c:url value="/manager/_frame"/>">
			<i class="iconfont icon-menhuhoutai"></i>
		</a>
		
		<a class="menu i i_btn" no=text href="${pageContext.request.contextPath}/system/_exit">
			<i class="iconfont icon-logout"></i>
		</a>
		
	</div>
	<div class="swiper-container container1">
		<div class="swiper-wrapper wrapper1">
			<div class="swiper-slide">
	        	<img src="<c:url value="/img/banner.jpg"/>"/>
	        </div>
	        <div class="swiper-slide">
	        	<img src="<c:url value="/img/banner1.jpg"/>"/>
	        </div>
	        <div class="swiper-slide">
	        	<img src="<c:url value="/img/banner3.jpg"/>"/>
	        </div>
		</div>
		
		<!-- 如果需要导航按钮 -->
	    <div class="swiper-button-prev swiper-button-white"></div>
	    <div class="swiper-button-next swiper-button-white"></div>
		<!-- 如果需要分页器 -->
		<div class="swiper-pagination pagination1" style="text-align: left;margin-left: 50px;"></div>
	</div>
	<div class="banner">
		<section class="live-msg" no=text>
			<span>最新动态：&nbsp;&nbsp;&nbsp;&nbsp;</span>
			<span class="live" live-msg="scroll-direction:left" no=text></span>
			<ul class="msg" id="newsList">
				
			</ul>
		</section>
	</div>
	<article>
		<div class="r1" no=text>
			<!-- 行 -->
			<div class="c1">
				<section class="order tab" style="height:1026px">
					<header>
						<span class=title>指令中心</span><i><a href="/bim_task/system/index?vcEmployeeId=${portal_users_session.vcEmployeeId}&taskOrInstruction=instruction"><img src="<c:url value="/css/icon/more.png"/>" alt="更多"/></a></i><span class="click new bg-blue" onclick="mload('${pageContext.request.contextPath}/task/instruction/_add')">+&nbsp;指令下达 </span>
					</header>
					<ul id="taskUl" class="body">
						<c:if test="${empty requestScope.taskList}">
							<c:forEach var="tl" items="${taskList}">
								<li no=text onclick="getTaskDetail(${portal_users_session.vcEmployeeId},${tl.id});">
									<div class="content">
										<div class="title">任务名称:<c:out value="${tl.taskName}"/></div>
										<div class="info">任务内容：<span class="text"><c:out value="${tl.taskDescription}"/></span></div>
										<div class="time">任务截至时间：<c:out value="${tl.deadLine}"/></div>
									</div>
									<div circle circle-data="${tl.taskCompletion}" class="circle"></div>
								</li>
							</c:forEach>
							<div class="btn-more"><a href="/bim_task/system/index?vcEmployeeId=${portal_users_session.vcEmployeeId}&taskOrInstruction=instruction">查看全部 </a></div>
						</c:if>
					</ul>
				</section>
			</div>
			<div class="c2">
				<section class="schedule tab" style="height:725px">
					<header><span class=title>工作日程</span><span class="click new bg-green" onclick="mload('${pageContext.request.contextPath}/work/_add')">+&nbsp;新增日程</span></header>
					<time></time>
					<div class="body">
						<div class="week-select">
							<span class="time"><span id="nian">${year}</span>年第<span id="zhou">${week}</span>周</span>
							<span class="prev btn" onclick="prevWork()">&lt;</span>
							<span class="next btn" onclick="nextWork()">&gt;</span>
							<span class="now btn" onclick="curWork()">本周</span>
						</div>
						<ul class="schedule-list" id="works">
						</ul>
					</div>
					<div class="btn-more" onclick="mload('${pageContext.request.contextPath}/work/_query')">查看全部</div>
				</section>
				<!-- 
				<section class="review tab" style="height:500px">
					<header>
						<span class="title">管理干部积分中心</span><br>
						<span class="click new bg-red" onclick="inStruct();">
							<div class="title">个人年度积分申请</div>
						</span>
					</header>
					<ul class=body>
						<li>
							<div class="title">海航实业XXX2017年履历积分评定<span class="btn bg-blue">评分Score</span></div>
							<div class="review-card" no=text>
								<div class="avatar">
									<img src="<c:url value="/img/head.png"/>" alt="头像">
								</div>
								<div class="info">
									<div class="name">XXX</div>
									<div class="title grey">海航实业 xxxxx</div>
									<table class="detail">
										<tr>
											<td>职位：<span class="grey">xxxxx</span></td>
											<td>性别：<span class="grey">男</span></td>
										</tr>
										<tr>
											<td>年龄：<span class="grey">xxxxx</span></td>
											<td>学历：<span class="grey">xxxxx</span></td>
										</tr>
										<tr>
											<td>工作年限：<span class="grey">xxxxx</span></td>
											<td>其他：<span class="grey">其他</span></td>
										</tr>
									</table>
									<img class="sign" src="<c:url value="/img/sign.png"/>" alt="指标">
								</div>
							</div>
						</li>
						<li><div class="title">海航XXXX</div><span class="btn bg-green">审核 Review</span></li>
						<li><div class="title">海航XXXX</div><span class="btn bg-green">审核 Review</span></li>
						<li><div class="title">海航XXXX</div><span class="btn bg-green">审核 Review</span></li>
					</ul>
				</section> -->
				<section class="command tab" style="height:274px">
					<header><span class=title>数据共享服务中心</span></header>
					<ul class="body" no=text>
						<li class="imp" onclick="jiekoushuliang()">
							<i class="bg-blue">126</i>
							<div class="info">
								<div class="title">接口数量</div>
							</div>
						</li>
						<li class="imp" onclick="jiekoushenqing()">
							<i class="bg-green">126</i>
							<div class="info">
								<div class="title">接口申请</div>
							</div>
						</li>
					</ul>
				</section>
			</div>
			<div class="c3">
				<section class="alert">
					<header><span class="title">预警中心</span></header>
					<ul no=text>
						<li>
							<div class="num" onclick="alarmKPI(${portal_users_session.vcEmployeeId},${alarmCount})">${alarmCount}</div>
							<div class="hr"></div>
							<div class="name">KPI预警</div>
							<i><img src="<c:url value="/css/icon/KPI.png"/>" alt="KPI预警"></i>
						</li>
						<li>
							<div class="num" id="jd" onclick="alarmTime(${portal_users_session.vcEmployeeId})"></div>
							<div class="hr"></div>
							<div class="name">进度预警</div>
							<i><img src="<c:url value="/css/icon/jd.png"/>" alt="进度预警"></i>
						</li>
						<li>
							<div class="num" onclick="alterNum(${portal_users_session.vcEmployeeId},${alterNum})">${alterNum}</div>
							<div class="hr"></div>
							<div class="name">指令超时</div>
							<i><img src="<c:url value="/css/icon/zlcs.png"/>"  alt="指令超时"></i>
						</li>
					</ul>
				</section>
				<section class="notice tab" style="height:224px;">
					<header>
						<span class="title">公告通知</span><span class="notice-switch" no=text>
							<span class="prev btn" onclick="prev()">&lt;</span>
							<span class="next btn" onclick="next()">&gt;</span>
						</span>
					</header>
					<ul id="msgUl" class="body">
						<c:if test="${!empty requestScope.msgList }">
							<c:forEach items="${ requestScope.msgList}" var="msg" varStatus="status">
								<c:if test="${status.index == 0 }">
									<li>
										<a href="javascript:;" onclick="mload('${pageContext.request.contextPath}/portal/msg/_view?id=${msg.id }');" class="title"><div><c:out value="${msg.title }"></c:out></div></a>
										<div class="content"><c:out value="${msg.description }"></c:out></div>
									</li>
								</c:if>
								<c:if test="${status.index != 0 }">
									<li>
										<a href="javascript:;" onclick="mload('${pageContext.request.contextPath}/portal/msg/_view?id=${msg.id }');" class="title"><div> • <c:out value="${msg.title }"></c:out> </div></a>
									</li>
								</c:if>
							</c:forEach>
						</c:if>
						<input type="hidden" id="pageNum" name="pageNum" value="${msgPage.pageNum }">
						<input type="hidden" id="totalPage" name="totalPage" value="${msgPage.totalPage }">
					</ul>
				</section>
				<section class="application tab" style="height:548px;">
					<header><span class="title">海航实业 BIM 管理应用中心</span></header>
					<ul class="body">
							<a class="link management" href="/bim_show/sys/adConnect/_adConnect2?vcEmployeeId=${portal_users_session.vcEmployeeId}&pageId=1"><img class="img-big" src="<c:url value="/img/hhsy.png"/>" alt="海航实业运营管控平台"></a>
						</li>
						<li>
							<a class="link management" href="http://bima.hna.net" target="_blank"><img class="img-big" src="<c:url value="/img/hn3_09.jpg"/>" alt=""></a>
						</li>
						<li>
							<ul class="group">
								<li>
									<a class="link" href="http://omc.hnainfrastructure.com/hna_assistant/AdminMain/Index" target="blank">
									<img class="img" src="<c:url value="/img/hn3_03.jpg"/>" alt="icon">
									</a>
									<span>海航基础</span>
									<p>运营管控平台</p>
								</li>
								<li>
									<a class="link" href="http://10.71.114.21/Account/Login" target="blank">
									<img class="img" src="<c:url value="/img/hn3_05.jpg"/>" alt="icon">
									</a>
									<span>海航教育医疗</span>
									<p>运营管控平台</p>
								</li>
								<li>
									<img src="<c:url value="/img/hn3_07.jpg"/>">
									<span>更多</span>
								</li>
							</ul>
						</li>
						</ul>
					
				</section>
			</div>
		</div>
	</article>
	<section class="work-links">
		<span>友情链接：</span>
		<div class="container" no=text>
		<div class="prev"><img src="<c:url value="/css/icon/arrow-left.svg"/>" alt="前一组"></div>
		<div class="flexslider">
				<ul class="slides">
					<li>
						<a class="link" href="http://x.hna.net/cooperation/" target="_blank">
							<img src="<c:url value="/img/oa.gif"/>" alt="">
							<!-- <div class="name">海航集团办公协作平台</div> -->
						</a>
					</li>
					<li>
						<a class="link" no=text href="http://10.123.16.172:19000/workspace/index.jsp" target="_blank">
							<img src="<c:url value="/img/dichan.gif"/>" alt="">
							<!-- <div class="name">海航地产经营管理平台</div> -->
						</a>
					</li>
				</ul>
			</div>
			<div class="next"><img src="<c:url value="/css/icon/arrow-right.svg"/>" alt="后一组"></div>
	</section>
	
	<footer><address>业务单位：海航实业集团 | 系统版本：海航实业BIM管理数字化平台 | 建设单位：上海软中信息技术有限公司</address></footer>
	<div class="modal-container" data-remodal-id="modal" no=text>
		<iframe id="modal-frame" src="" style></iframe>
		<a href="javascript:mclose()" class="modal-close"><img src="../../css/icon/x.svg" alt=""></a>
	</div>
	<!-- 优先处理 -->
	<script type="text/javascript">
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
	<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/circles.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/remodal.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/jquery.flexslider-min.js"/>" charset="utf-8"></script><!-- 库 轮播-->
	<script src="<c:url value="/js/index.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/swiper-3.3.1.min.js"/>" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		var mySwiper = new Swiper ('.container1', {
		    loop: true,
		    autoplay : 3000,
		    // 如果需要分页器
		    pagination: '.swiper-pagination',
		    paginationClickable :true,
		    // 如果需要前进后退按钮
		    nextButton: '.swiper-button-next',
		    prevButton: '.swiper-button-prev',
		    autoplayDisableOnInteraction : false
		    
		})        
	</script>
	<script type="text/javascript">		
		// update time
		var time = $('body>header>.time');
		var set_time = function() {
			var now = new Date;
			var fmt = function(s) {
				return (s + '').length < 2 ? '0' + s : s
			}
			var time_string = now.getFullYear() + '年' +
				fmt(now.getMonth() + 1) + '月' +
				fmt(now.getDate()) + '日' +
				'&nbsp;&nbsp;' +
				fmt(now.getHours()) + ':' + fmt(now.getMinutes()) + ':' + fmt(now.getSeconds())
			time.html(time_string)
		}
		set_time();
		setInterval(set_time, 1e3)
		
		function getTaskDetail(employeeId,taskId){
			var url = "/bim_task/system/index?vcEmployeeId="+employeeId+"&taskOrInstruction=instruction&taskId="+taskId;
			window.location.href = url;
		}
		
		function alterNum(employeeId,alterNum){
			if(alterNum > 0){
				var date = new Date();
				var url = "/bim_task/system/index?vcEmployeeId="+employeeId+"&deadLine="+date;
				window.location.href = url;
			}
		}
		
		function alarmKPI(employeeId,alarmNum){
			if(alarmNum > 0){
				var url = "/bim_show/index.jsp?vcEmployeeId="+employeeId+"&pageId=2";
				window.location.href = url;
			}
		}
		
		function alarmTime(employeeId){
			var val = $("#jd").text();
			if(val > 0){
				var url = "/bim_show/index.jsp?vcEmployeeId="+employeeId+"&pageId=3";
				window.location.href = url;
			}
		}
		var strPath = window.document.location.pathname;
		var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
		
		function getManager(){
			var url = "";
			alert(postPath);
			if(postPath != "/bim_portal"){
				url = "/bim_portal/manager111/_frame";
			} else {
				url = postPath+"/manager111/_frame";
			}
			window.location.href = "";
		}
		
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
		
		$(document).ready(function(){
			//获取工作日历
			$.ajax({
			     url : "${pageContext.request.contextPath}/work/_list",  
			     type : "POST",
		         async: false,  
		         cache: false,  
		         contentType: false,  
		         processData: false,  
			     success : function(data) {
			     	$("#works").append(data);
			     },  
			     error : function(data) {
					//alert("加载失败！");
			     }  
			});
			//获取重点项目超时节点数量
			$.ajax({
			     url : "${pageContext.request.contextPath}/system/getAlarmTime",  
			     type : "POST",
		         async: false,  
		         cache: false,  
		         contentType: false,  
		         processData: false,  
			     success : function(data) {
			     	$("#jd").empty().append(data);
			     },  
			     error : function(data) {
					//alert("加载失败！");
			     }  
			});
			//获取消息、工商任务待处理滚动展示
			$.ajax({
			     url : "${pageContext.request.contextPath}/portal/news/_newsList",  
			     type : "POST",
		         async: false,  
		         cache: false,  
		         contentType: false,  
		         processData: false,  
			     success : function(data) {
			     	$("#newsList").append(data);
			     	LiveMsg($(".live-msg"))
			     },  
			     error : function(data) {
					//alert("加载失败！");
			     }  
			});
		});
	</script>
	<script type="text/javascript">
		function prev(){
			var params = {};
			var tempPageNum = document.getElementById("pageNum").value; 
			var totalPage = document.getElementById("totalPage").value;
			var pageNum = parseInt(tempPageNum) - 1;
			if(pageNum == 0){
				pageNum = 1;
			}
  			params.pageNum = pageNum; 
	     	
		    $.ajax({  
		        async:false,  
		        type: "POST",  
		        url: "${pageContext.request.contextPath}/portal/msg/_list",
		        data:params,  
		        dataType:"json",  
		        success:function(data){ 
		            $("#msgUl").empty().append(data);
		        },  
		        error:function(data){  
		        }
		    }); 
		}
		function next(){
			var params = {};
			var tempPageNum = document.getElementById("pageNum").value; 
			var totalPage = document.getElementById("totalPage").value;
			var pageNum = parseInt(tempPageNum) + 1;
			if(pageNum >= totalPage){
				pageNum = totalPage;
			}
  			params.pageNum = pageNum; 
	     	
		    $.ajax({  
		        async:false,  
		        type: "POST",  
		        url: "${pageContext.request.contextPath}/portal/msg/_list",
		        data:params,  
		        dataType:"json",  
		        success:function(data){ 
		            $("#msgUl").empty().append(data);
		        },  
		        error:function(data){  
		        }
		    }); 
		}
		
		function prevWork(){
			var week = ($("#zhou").text()*1-1);
			var year = ${year};
			$.post("${pageContext.request.contextPath}/work/_list",{weeks:week,years:year}, function(data) {
				if (data.length > 0) {
					$("#works").empty().append(data);
					$("#zhou").empty().append(week);
				}
			});
		}
		
		function nextWork(){
			var week = ($("#zhou").text()*1+1);
			var year = ${year};
			$.post("${pageContext.request.contextPath}/work/_list",{weeks:week,years:year}, function(data) {
				if (data.length > 0) {
					$("#works").empty().append(data);
					$("#zhou").empty().append(week);
				}
			});
		}
		
		function curWork(){
			var week = document.getElementById("curWeek").value;
			var year = ${year};
			$.post("${pageContext.request.contextPath}/work/_list",{weeks:week,years:year}, function(data) {
				if (data.length > 0) {
					$("#works").empty().append(data);
					$("#zhou").empty().append(week);
				}
			});
		}
		
		function inStruct(){
			alert("功能建设中！");
			return false;
		}
		
				// 友情链接滚动
		$('.work-links .container').on('click', '.prev', function() {
			$('.work-links .container .flexslider').flexslider("prev");
		}).on('click', '.next', function() {
			$('.work-links .container .flexslider').flexslider("next");
		}).find('.flexslider').flexslider({
			animation: "slide",
			itemWidth: 235,
			itemMargin: 60,
			minItems: 1,
			maxItems: 5
		});
		
		function jiekoushuliang(){
			var url = "${pageContext.request.contextPath}/views/apply/shuliang.jsp";
			window.open(url);
		}
		
		function jiekoushenqing(){
			var url = "${pageContext.request.contextPath}/views/apply/jiekou.jsp";
			window.open(url);
		}
	</script>
</body>

</html>
