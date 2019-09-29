<%@ page language="java" contentType="text/html; charset=UTF-8"
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
		<!-- 库|插件 -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/views/home/homeTB/css/remodal.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/views/home/homeTB/css/remodal-default-theme.css">
	
	</head>
	<style>
	  .remodal-wrapper {
	padding: 0px;
	z-index:10000000;
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
	padding:0;
	margin:0;
	outline:0;
	border:0;
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

.white-bg>.title{
	position:relative;
}
.white-bg>.title>.new.click{
	position: absolute;
	right: 10px;
	font-size:14px;
	line-height: 18px;
	top: calc(50% - 10px);
	color: #fff;
	padding: 2px 6px;
	border-radius: 3px;
	cursor: pointer;
}

#box{
	background: red;
	position: fixed;
	right: 0;
	top: 70px;
	bottom: 0;
	left: 100px;
	z-index:100;
	}

.white-bg {
			    background-color: #fff;
			    border: solid 1px #e8e8e8;
			    margin-bottom: 15px;
			}
			
			.white-bg span.title {
			    display: block;
			    height: 42px;
			    line-height: 42px;
			    color: #646464;
			    border-bottom: solid 1px #E8E8E8;
			    font-size: 16px;
			    padding-left: 20px;
			}
			
			
			.l_chartbox{
				height: 530px;
				position: relative;
			}
			
			 .canvas_wraper{
				position: absolute;
				left: 20px;
				right: 300px;
				top: 30px;
				height: 500px;
			}
			
			#right{
				width: 300px;
				height: 500px;		
				background: #fff;
				position: absolute;
				right: 20px;
				top: 30px;			
			}
			
			.one_item{
				width: 300px;
				margin-bottom: 10px;
			}
						
			
			.one_item>p:nth-of-type(1){
				font-size: 30px;
				color: #656667;
				margin-bottom: 2px;
			}
			.one_item>p:nth-of-type(1)>span{
				font-size: 12px;
				color: #98999a;
			}
			.one_item>p:nth-of-type(2){
				font-size: 14px;
				color: #98999a;
				margin-bottom: 6px;
			}
			
			.progress{
				width: 300px;
				height: 10px;
			}
			
			.progress-bar-self{
				background: #cfcfcf;
			}

			.rightn{
				width: 300px;
				height: 500px;		
				background: #fff;
				position: absolute;
				right: 20px;
				top: 30px;			
			}
.white-bg {
			    background-color: #fff;
			    border: solid 1px #e8e8e8;
			    margin-bottom: 15px;
			}
			
			.white-bg span.title {
			    display: block;
			    height: 42px;
			    line-height: 42px;
			    color: #646464;
			    border-bottom: solid 1px #E8E8E8;
			    font-size: 16px;
			    padding-left: 20px;
			}
			
			
			.l_chartbox{
				height: 530px;
				position: relative;
			}
			
			 .canvas_wraper{
				position: absolute;
				left: 20px;
				right: 300px;
				top: 30px;
				height: 500px;
			}
			
			#right{
				width: 300px;
				height: 500px;		
				background: #fff;
				position: absolute;
				right: 20px;
				top: 30px;			
			}
			
			.one_item{
				width: 300px;
				margin-bottom: 10px;
			}
						
			
			.one_item>p:nth-of-type(1){
				font-size: 30px;
				color: #656667;
				margin-bottom: 2px;
			}
			.one_item>p:nth-of-type(1)>span{
				font-size: 12px;
				color: #98999a;
			}
			.one_item>p:nth-of-type(2){
				font-size: 14px;
				color: #98999a;
				margin-bottom: 6px;
			}
			
			

	</style>
		<body class="hn_grey" onload="init()">
		<!--<img src="img/home.jpg" alt="" width="1920" height="1080" usemap="#Map" border="0"/>
<map name="Map">
  <area shape="circle" coords="313,330,162" href="v.htm">
</map>-->
		  <jsp:include   page="../homepagetitle.jsp" flush="true"/> 
		  
		  <style>
		  .progress{
				width: 300px;
				height: 10px;
			}
			
			.progress-bar-self{
				background: #cfcfcf;
			}
		  
		  </style>
		  
		  <!--职能树开始  -->
		<div class="container-fluid sec-maincontent">
			<ul class="left_menu">
			<li>
				<a id="dept_1"  onclick="activeDeptIcon(1)"  href="javascript:void(0);" class="">
				<i class="iconfont icon-bt iconbg">
					<i class="iconfont icon-icon-test"></i></i>
					<span>六率指标</span>
				</a>
			</li>
			
			<li>
				<!--  <a id="dept_12"  href="<c:url value='/project/_list'/>" class="">-->
				 <a id="dept_11"  onclick="activeDeptIcon_2(12)" href="javascript:void(0);" class="">
					<i class="iconfont icon-zhongdiangongcheng"></i>
					<span>重点基建工程</span>
				</a>
			</li>
			
			
			<li>
				<a id="dept_8"  onclick="activeDeptIcon(8)"  href="javascript:void(0);" class="">
					<i class="iconfont icon-hangzheng"></i>
					<span>经营指标</span>
				</a>
			</li>
			<li>
				<a id="dept_12"  onclick="activeDeptIcon_1(12)" href="javascript:void(0);" class="">
					<i class="iconfont icon-zichanguanli"></i>
					<span>大额资产管理</span>
				</a>
			</li>
			<li>
				<a id="dept_2"  onclick="activeDeptIcon(2)"  href="javascript:void(0);" class="">
					<i class="iconfont icon-hangzheng"></i>
					<span>行政办公</span>
				</a>
			</li>
			<li>
				<a id="dept_4"  onclick="activeDeptIcon(4)"  href="javascript:void(0);" class="">
					<i class="iconfont icon-renliziyuan"></i>
					<span>人力资源</span>
				</a>
			</li>
			<li>
				<a id="dept_10"  onclick="activeDeptIcon(10)" href="javascript:void(0);" class="">
					<i class="iconfont icon-tixiguanli"></i>
					<span>财务管理</span>
				</a>
			</li>
			<li>
				<a id="dept_7"  onclick="activeDeptIcon(7)"  href="javascript:void(0);" class="">
					<i class="iconfont icon-fk"></i>
					<span>风险控制</span>
				</a>
			</li>
			<li>
				<a id="dept_5"  onclick="activeDeptIcon(5)" href="javascript:void(0);" class="">
					<i class="iconfont icon-shzr"></i>
					<span>社会责任</span>
				</a>
			</li>
			<li>
				<a id="dept_3"  onclick="activeDeptIcon(3)"  href="javascript:void(0);" class="">
					<i class="iconfont icon-caigouguanli1"></i>
					<span>采购管理</span>
				</a>
			</li>
			
<!-- 			<li>
				<a id="dept_6"  onclick="activeDeptIcon(6)"  href="javascript:void(0);" class="">
					<i class="iconfont icon-aqsc1"></i>
					<span>安全生产</span>
				</a>
			</li> -->
			</ul>
			<!--职能树结束 -->
			<!--位置开始  -->
			<div class="right-content">
				<div class="top-title">
					<h1 id="logo_dept">行政办公</h1>
					<ul>
						<li>
							<a href="javascript:void(0);"><i class="iconfont icon-shouye-shouye"></i></a>/
						</li>
						<!--  
						<li>
							<a href="javascript:;">驾驶舱</a>/
						</li>
						-->
						<li id="loc_dept">
							行政办公
						</li>/
						<li id="loc_year">2017年</li>/
						<li id="loc_month">1月</li>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    <li >
                                                           年份: <select id="yearsel" name="yearsel" onchange="changtime()">
                                 <option value="2014">2014</option>
                                 <option value="2015">2015</option>
                                 <option value="2016">2016</option>
                                 <option value="2017">2017</option>
                              </select>
                        </li>
                        &nbsp;&nbsp;&nbsp;
                          <li >
                                                           月份: <select id="monthsel" name="monthsel" onchange="changtime()">
                                 <option value="1">1月</option>
                                 <option value="2">2月</option>
                                 <option value="3">3月</option>
                                 <option value="4">4月</option>
                                 <option value="5">5月</option>
                                 <option value="6">6月</option>
                                 <option value="7">7月</option>
                                 <option value="8">8月</option>
                                 <option value="9">9月</option>
                                 <option value="10">10月</option>
                                 <option value="11">11月</option>
                                 <option value="12">12月</option>
                              </select>
                        </li>
					</ul>
				</div>	
				
		<!--位置结束 -->
		<!-- 图形层 -->
				<div id="Chart_depit"  class="middle-content clearfix">
					<div class="col-sm-4">
						<div class="white-bg">
							<span class="title">行政督办效率</span>
							<div class="chartbox"><div id="cruve_10" style="width: 100%; height: 300px;"></div></div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="white-bg">
							<span class="title">行政督办效率</span>
							<div class="chartbox"><div id="cruve_11" style="width: 100%; height: 300px;"></div></div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="white-bg">
							<span class="title">行政督办效率</span>
							<div class="chartbox"><div id="cruve_12" style="width: 100%; height: 300px;"></div></div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="white-bg">
							<span class="title">行政督办效率</span>
							<div class="chartbox"><div id="cruve_13" style="width: 100%; height: 300px;"></div></div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="white-bg">
							<span class="title">行政督办效率</span>
							<div class="chartbox"><div id="cruve_14" style="width: 100%; height: 300px;"></div></div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="white-bg">
							<span class="title">海航实业品牌数量占比</span>
							<div class="chartbox"><div id="cruve_15" style="width: 100%; height: 300px;"></div></div>
						</div>
					</div>
					
				</div>
				<div id="Chart_depit1" class="middle-content clearfix" style="display:none"></div> 
	<!-- 图形层 结束-->
			     
				
			</div>
		</div>
		<div id=chart-modal no=text>
			<div class="bg-x"  onclick="$('#chart-modal').jqmHide();"><i class="chart-modal-close">×</i></div>
		    <div class="white-bg" style="position:relative;height: 100%;margin-left: 0;margin-bottom: 0;">
				<span class="title"><span id="level1_title">行政督办效率</span><span class="click new bg-blue" onclick="mload('${pageContext.request.contextPath}/task/instruction/_add')">+&nbsp;指令下达</span></span>
				<div class="chartbox"><div id="level1_chart" style="width: 100%;height:400px;"></div></div>
			</div>
		</div>
		<style>
			.white-bg>.title{
				text-align:center;
			}
			.bg-x {
			    position: fixed;
			    height: 160px;
			    width: 160px;
			    right: -80px;
			    top: -80px;
			    border-radius: 50%;
			    background-color: #21abd9;
			    cursor: pointer;
			}
			.bg-x:hover {
			    background-color: #21abd9;
			}
			i.chart-modal-close {
			    height: 60px;
			    width: 60px;
			    color: #fff;
			    font-size: 60px;
			    line-height: 60px;
			    position: absolute;
			    right: 49%;
			    top: calc(75% - 45px);
			    font-family: "微软雅黑",monospace;
			    font-style: normal;
			    cursor: pointer;
			}
			i.chart-modal-close:active{
				background-color:#ccc;
			}
			
			#chart-modal{
				width: 1200px;
				max-width: 1200px;
				z-index:100000;
				height: 480px;
				display:none;
				position:fixed;
				top:calc(50% - 240px);
				left:calc(50% - 600px);
			}
			.jqmOverlay { background-color: #000; }
		</style>
		
		<div class="remodal modal-container" data-remodal-id="modal2" no=text>
		33344455555
			<button data-remodal-action="close" class="remodal-close"></button>
		</div>
		
		<div class="modal-page modal-container" data-remodal-id="page" no=text>
			<iframe id="modal-frame" src="" style></iframe>
		</div>
		
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/echart/echarts.min.js" ></script>
	<!-- 
	<script type="text/javascript" src="${pageContext.request.contextPath}/views/home/homeTB/js/echart/sec-curve.js" ></script>
	 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/views/home/homeTB/js/home2th_old.js" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/views/home/homeTB/js/createChartEle.js" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.js" ></script>	
	<script src="${pageContext.request.contextPath}/views/home/homeTB/js/remodal.min.js" charset="utf-8"></script>
	<script src="${pageContext.request.contextPath}/js/jqModal.min.js" charset="utf-8"></script>

	<script>
    var basePath = '${pageContext.request.contextPath}';
    var TBData=${DePTBData}; //获取请求参数
    var DeptId='${DePTBID}';
    var WholeYear=${Years};
    var WholeMonth=${Months};
    var orgLevel='${sessionScope.bimShow_orgLevel}';
    var ButtonList=new Array();
    <c:forEach items="${sessionScope.bimShow_ButtonList}" var="b">
    ButtonList.push("${b}");
    </c:forEach>
    
   	$(document).on('opened','.remodal',function(){
   		echarts.getInstanceByDom(document.getElementById("level1_chart")).resize();
   	});
   	
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
				$('[data-remodal-id=page]').remodal({
					closeOnEscape:false,
					//closeOnOutsideClick:false,
				}).open();
			}
		}
		window.modal_close = window.mclose = function(){
			var ifm = $('[data-remodal-id=modal] iframe')[0];
			$('[data-remodal-id=page]').remodal().close();
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
				c.style['padding-bottom'] = '';
			}
		}
		$(window).resize(modal_resize);
		
		function activeDeptIcon_1(){
			var url = "/bim_show/zc/getZc?homeyear="+WholeYear+"&homemonth="+WholeMonth;
			window.location.href = url;
		}
		
		function activeDeptIcon_2(){
		  	var url = "/bim_show/project/_list?homeyear="+WholeYear+"&homemonth="+WholeMonth;
		  	window.location.href = url;
		}
		
		
		
	</script>
</html>
