<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<style>
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
.sznew2 .sznew2_1{
	width:34%;
}
.sznew2 .sznew2_2{
	width:65%;
	float:right;
}
.sznew2 .sznew2_1 .module_body>div,.sznew2 .sznew2_2 .module_body>div{
	width:100% !important;
	min-height:350px;
}
</style>
<head>
	<meta charset="UTF-8">
	<title>社会责任</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-theme.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fonts/iconfont.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/lin.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/yusuan2.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index_second.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/home/homeTB/css/remodal.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/home/homeTB/css/remodal-default-theme.css">
	<style type="text/css">
		.sz1 .module_body>div{
			height:170px;
		}
		.sz2 .module_body>div{
			height:330px;
		}
		.sz1_div1>div{
			padding: 0 2em;
			width:30%;
		}
		.sz1_div1>.sz_div3{
			width:69%;
		}
		.sz1_div1>div:last-child{
			padding:0 1em;
		}
		.sz1_text1{
			margin-top: 2em;
		}
		.sznew1>div{
		    width: 100%;
		}
		.sznew1 .module_body>div:first-child{
		    display: inline-block;
		    width: 30%;
		    padding-right: 1em;
   			vertical-align: top;
		}
		.sznew1 .module_body>div:nth-child(2){
	        display: inline-block;
		    width: 69%;
		    vertical-align: top;
		    border-left: 1px solid #e6e6e6;
		}
		.white-bg span.title{
			border-bottom:none;
		}
		.white-bg>.title{
			box-shadow:none;
		}
	</style>
</head>
<body onload="srInit()">
	<section class="title">
		<h3>社会责任</h3>
		<p class="select_date">
			<%-- <span id="orgnmId">${sessionScope.nextOrgList[0][1]}</span> --%>
			机构选择：
			<select id="orgnmId" class="select_com">
				<option value="26655">海航实业</option>
			</select>
			时间选择：
			<input class="form-control" size="16" type="text" value="" readonly="true">
			<input type="hidden" id="dtp_input2" value="" />
			<input type="button" class="title_btn" value="查询" onClick="_query()">
			<a></a>
		</p>
	</section>
	<section class="sznew1">
		<div class="module_div">
			<div class="module_head">
				<span>海航实业新媒体</span>
				<span id="titleTimeOneId"></span>
				<c:if test="${fn:contains(bimShow_ButtonList, '&bs_sp12&')}">
					<a class="zhiling" onclick="sendorder(13,12,'新媒体')">+ 指令下达</a>
				</c:if>
			</div>
			<div class="module_body">
				<div>
					<p class="sz1_title1"><span class="font_g" id="xmtydcsId">万次</span>新媒体阅读次数</p>
					<p class="sz1_text1"><i class="iconfont icon-xiayiye"></i>总渠道数：<span id="zqdsId">个</span></p>
					<p class="sz1_text1"><i class="iconfont icon-xiayiye"></i>总篇数：<span id="zpsId">篇</span></p>
				</div>
				<div id="sr_echarts_three" style="height:300px"></div>
			</div>
		</div>
	</section>
	<section class="sznew1">
		<div class="module_div">
			<div class="module_head">
				<span>海航实业志愿服务活动量</span>
				<span id="titleTimeTwoId"></span>
				<c:if test="${fn:contains(bimShow_ButtonList, '&bs_sp12&')}">
					<a class="zhiling" onclick="sendorder(5,'SHOWTAB_M25','志愿服务活动量')">+ 指令下达</a>
				</c:if>
			</div>
			<div class="module_body">
				<div>
					<p class="sz1_title1"><span class="font_g" id="zyfwhdlId">万次</span>志愿服务活动量</p>
					<p class="sz1_text1"><i class="iconfont icon-xiayiye"></i>志愿服务累计时长：<span id="zyfwljscId">小时</span></p>
					<p class="sz1_text1"><i class="iconfont icon-xiayiye"></i>志愿服务活动累计人次：<span id="zyfwljrcId">篇</span></p>
				</div>
				<div id="sr_echarts_four" style="height:300px"></div>
			</div>
		</div>
	</section>
	<section class="sznew1">
		<div class="module_div">
			<div class="module_head">
				<span>海航实业员工关爱活动覆盖度</span>
				<span id="titleTimeThreeId"></span>
				<c:if test="${fn:contains(bimShow_ButtonList, '&bs_sp12&')}">
					<a class="zhiling" onclick="sendorder(5,'SHOWTAB_M26','员工关爱活动覆盖度')">+ 指令下达</a>
				</c:if>
			</div>
			<div class="module_body">
				<div>
					<p class="sz1_title1"><span class="font_g"></span>员工关爱活动覆盖度</p>
					<p class="sz1_text1"><i class="iconfont icon-xiayiye"></i>活动累计人均参与度：<span id="ljcydId">人次</span></p>
					<p class="sz1_text1"><i class="iconfont icon-xiayiye"></i>活动人均参与度：<span id="rjcydId">人次</span></p>
				</div>
				<div id="sr_echarts_five" style="height:300px"></div>
			</div>
		</div>
	</section>
	<section class="sznew1">
		<div class="module_div">
			<div class="module_head">
				<span>海航实业媒体维护次数</span>
				<span id="titleTimeFourId"></span>
				<c:if test="${fn:contains(bimShow_ButtonList, '&bs_sp12&')}">
					<a class="zhiling" onclick="sendorder(5,'SHOWTAB_M27','媒体维护次数')">+ 指令下达</a>
				</c:if>
			</div>
			<div class="module_body">
				<div>
					<p class="sz1_title1"><span class="font_g" id="bhcsId">次</span>媒体拜会次数</p>
					<p class="sz1_text1"><i class="iconfont icon-xiayiye"></i>拜会媒体总人数：<span id="bhzrsId">人</span></p>
					<p class="sz1_text1"><i class="iconfont icon-xiayiye"></i>拜会媒体数量：<span id="bhmtsId">个</span></p>
				</div>
				<div id="sr_echarts_six" style="height:300px"></div>
			</div>
		</div>
	</section>
	<section class="sznew1 sznew2">
		<div class="module_div sznew2_1">
			<div class="module_head">
				<span>海航实业品牌</span>
				<span id="titleTimeFiveId"></span>
				<c:if test="${fn:contains(bimShow_ButtonList, '&bs_sp12&')}">
					<a class="zhiling" onclick="sendorder(5,'SHOWTAB_T22','海航实业品牌')">+ 指令下达</a>
				</c:if>
			</div>
			<div class="module_body">
				<div>
					<p class="sz1_title1"><span id="sumNumId" class="font_g">个</span>品牌</p>
					<div id="sr_echarts_two" style="height:290px"></div>
				</div>
			</div>
		</div>
		<div class="module_div sznew2_2">
			<div class="module_head">
				<span>海航实业舆情总数</span>
				<span id="titleTimeSixId"></span>
				<c:if test="${fn:contains(bimShow_ButtonList, '&bs_sp12&')}">
					<a class="zhiling" onclick="sendorder(5,'SHOWTAB_W23','海航实业舆情总数')">+ 指令下达</a>
				</c:if>
			</div>
			<div class="module_body">
				<div class="sz_div3" id="yqzsId" style="height:350px">
					<p class="sz1_title1"><span class="font_g">次</span>当月舆情总数</p>
					<table>
						<tr>
							<th>时间</th>
							<th>舆情总数(条)</th>
							<th>重点报告数(条)</th>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</section>
	<div class="modal-page modal-container" data-remodal-id="page" no=text>
		<iframe id="modal-frame" src="" style></iframe>
	</div>
	
	<!-- 品牌下钻 -->
	<div id=chart-modal no=text>
		<div class="bg-x"  onclick="$('#chart-modal').jqmHide();"><i class="chart-modal-close">×</i></div>
	    <div class="white-bg" style="position:relative;height: 100%;margin-left: 0;margin-bottom: 0;">
			<span class="title"><span id="level1_title"></span><%-- <span class="click new bg-blue" onclick="mload('${pageContext.request.contextPath}/task/instruction/_add')">+&nbsp;指令下达</span> --%></span>
			<div class="chartbox"><div id="level1_chart" style="width: 100%;height:400px;"></div></div>
			
		</div>
	</div>
	<!-- 下钻 -->
	<div id=chart-modal2 no=text>
		<div class="bg-x"  onclick="$('#chart-modal2').jqmHide();"><i class="chart-modal-close">×</i></div>
	    <div class="white-bg" style="position:relative;height: 100%;margin-left: 0;margin-bottom: 0;">
			<span class="title"><span></span><%-- <span class="click new bg-blue" onclick="mload('${pageContext.request.contextPath}/task/instruction/_add')">+&nbsp;指令下达</span> --%></span>
			<div class="chartbox"><div id="level2_chart" style="width: 100%;height:400px;"></div></div>
			
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
			
			#chart-modal,#chart-modal2{
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/echart/echarts.min.js" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/views/home/homeTB/js/remodal.min.js" charset="utf-8"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/views/home/homeTB/js/societyResponsibility.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jqModal.min.js" charset="utf-8"></script>
	<script type="text/javascript">
		var basePath = '${pageContext.request.contextPath}';
		// 获取当前时间
      var pre_date;
	    function show(){
		    var mydate = new Date();
		    if(mydate.getUTCMonth()==0){
		    	var str = "" + (mydate.getFullYear()-1) + "年";
			    str += "12月";
			    pre_date = (mydate.getFullYear()-1)+"-"+"12";
		    }else{
			    var str = "" + mydate.getFullYear() + "年";
			    str += ("0"+(mydate.getMonth()-1)).slice(-2) + "月";
			    pre_date = mydate.getFullYear()+"-"+("0"+(mydate.getMonth()-1)).slice(-2);
		    }
		    $('.form-control').val(str);
	   }
	   show();
		// 日历控件
		$('.form-control').datetimepicker({
			format: 'yyyy年mm月',
	        language:  'zh-CN',
	        weekStart: 1,
	        todayBtn:  0,
			autoclose: 1,
			todayHighlight: 1,
			startView: 3,
			minView: 3,
			forceParse: 0
	    });
	    $('.form-control').datetimepicker( 'update', pre_date )

		// 设置part1高度统一
		function get_height(){
			$('.module_div').height($('.rl1_2').height());
			// $('.xz2_div2').height($('.xz2_div1').height());
			// $('.xz2_div3').height($('.xz2_div1').height());
			// $('.xz3_div1').height($('.xz3_div2').height());
		}
		get_height();
		//下达指令
		// type 模块序号
		// mapId 指标名称ID
		// taskName 标题名称
		function sendorder(typeid,mapId,charttitle){
			var url=basePath+"/task/instruction/_add?type="+typeid+"&mapId="+mapId+"&taskName="+(charttitle).replace(/\%/g,'%25');
			mload(url);
		};
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
					};
				};
				ifm.src = url;
				$('[data-remodal-id=page]').remodal({
					closeOnEscape:false,
					//closeOnOutsideClick:false,
				}).open();
			};
		};
		window.modal_close = window.mclose = function(){
			var ifm = $('[data-remodal-id=modal] iframe')[0];
			$('#level1_chart').html("");
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
			};
		};
		$(window).resize(modal_resize);
	</script>
</body>
</html>