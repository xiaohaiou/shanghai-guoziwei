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
</style>
<head>
	<meta charset="UTF-8">
	<title>人力资源</title>
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
/* 		.module_div{
			height:400px;
		} */
		.rl1 .rl1_1part{
			width:100%;
		}
		.rl1 .rl1_1part .module_body>div{
			width:33%;
		}
		.rl1 .rl1_2part{
			width:100%;
		}
		.rl1_div3,.rl1_div4{
			width:49.5%;
		}
		.rl1_div4>div{
			display:inline-block;
			width:49%;
		}
		.rl1_div2{
			width:24%;
		}
		.rl1_div1{
			width:50%;
		}
		.hr_div_add1,.hr_div_add2{
			height:400px;
			margin-right:1%;
		}
		.hr_div_add2{
			margin-right:0 !important;
		}
	</style>
</head>
<body onload="hrInit()">

	<section class="title">
		<h3>人力资源</h3>
		<p class="select_date">
			机构选择：
			<select id="orgnmId" class="select_com" >
				<c:forEach items="${sessionScope.nextOrgList}" var="result">
					<option value="${result[0]}">${result[1]}</option>
				</c:forEach>
			</select>
			&nbsp;&nbsp;
			时间选择：
			<input class="form-control" size="16" type="text" value="" readonly="true">
			<input type="hidden" id="dtp_input2" value="" />
			<input type="button" class="title_btn" value="查询" onClick="_query()">
			<a></a>
		</p>
	</section>
	<section class="rl1">
		<!-- <div class="module_div rl1_1 rl1_1part">
			<div class="module_head">
				<span> </span>
			</div>
			<div class="module_body">
				<div class="rl1_div1">
					<p><span class="font_b" id = "zrsTitleId">人</span>总人数</p>
					<div id="hr_echarts_one_zrs" style="height:300px"></div>
				</div>
				<div class="rl1_div2">
					<p><span class="font_b" id = "glgbTitleId">人</span>管理干部人数性别占比</p>
					<div id="hr_echarts_one_gl" style="height:300px"></div>
				</div>
				<div class="rl1_div2">
					<p><span class="font_b" id = "glgbMTitleId">人</span>管理干部人数M序列占比</p>
					<div id="hr_echarts_one_Mgl" style="height:300px"></div>
				</div>
			</div>
		</div> -->
		<div class="module_div rl1_2 rl1_2part">
			<div class="module_head">
				<span id="titleOneId">劳动生产率</span>
				<span id="titleTimeOneId"></span>
				<c:if test="${fn:contains(bimShow_ButtonList, '&bs_sp12&')}">
					<a class="zhiling" onclick="sendorder(4,'SHOWTAB_M1','劳动生产率')">+ 指令下达</a>
				</c:if>
			</div>
			<div class="module_body">
				<div class="rl1_div3">
					<div class="rl1_div32">
						<div>
							<p>人均收入：<span class="font_b" id="rjsrId">-万元</span></p>
							<p><i class="iconfont icon-xiayiye"></i>去年同期：<span id="srtbId">-万</span></p>
							<p><i class="iconfont icon-xiayiye"></i>上月：<span id="srhbId">-万</span></p>
						</div>
						<div>
							<p>人均利润：<span class="font_b" id="rjlr">万元</span></p>
							<p><i class="iconfont icon-xiayiye"></i>去年同期：<span id="lrtbId">-万</span></p>
							<p><i class="iconfont icon-xiayiye"></i>上月：<span id="lrhbId">-万</span></p>
						</div>
					</div>
				</div>
				<div class="rl1_div4" style="">
					<div id="hr_echarts_two_sr" style="height:250px"></div>
					<div id="hr_echarts_two_lr" style="height:250px"></div>
				</div>
				<div id="hr_echarts_four" style="height:400px;width:100%"></div>
			</div>
		</div>
		<div class="module_div rl1_2 rl1_2part">
			<div class="module_head">
				<span id="titleTwoId">总人数</span>
				<span id="titleTimeTwoId"></span>
				<c:if test="${fn:contains(bimShow_ButtonList, '&bs_sp12&')}">
					<a class="zhiling" onclick="sendorder(4,'SHOWTAB_M18','总人数')">+ 指令下达</a>
				</c:if>
			</div>
			<div class="module_body" style="height:85%">
				<div class="rl1_div1">
					<p><span class="font_b" id = "zrsTitleId"></span><span class="orgNameClass"></span>当月总人数</p>
					<div id="hr_echarts_one_zrs" style="height:300px"></div>
				</div>
				<div class="rl1_div4">
					<div id="hr_echarts_three" style="height:330px;width:800px"></div>
				</div>
			</div>
		</div>
				<!-- <div class="module_div ">
					<div class="module_head">
						<span>总人数</span>
						<a class="zhiling" onclick="sendorder(4,'SHOWTAB_M18','总人数')">+ 指令下达</a>
					</div>
					<div class="module_body" style="height:85%">
						<div id="hr_echarts_three" style="height:100%"></div>
					</div>
				</div>
				<div class="module_div">
					<div class="module_head">
						<span>管理干部人数</span>
						<a class="zhiling" onclick="sendorder(4,'SHOWTAB_M19','管理干部人数')">+ 指令下达</a>
					</div>
					<div class="module_body" style="height:85%">
						<div id="hr_echarts_five" style="height:100%"></div>
					</div>
				</div> -->
		
		<div class="module_div rl1_2 rl1_2part">
			<div class="module_head">
				<span id="titleThreeId">管理干部人数</span>
				<span id="titleTimeThreeId"></span>
				<c:if test="${fn:contains(bimShow_ButtonList, '&bs_sp12&')}">
					<a class="zhiling" onclick="sendorder(4,'SHOWTAB_M19','管理干部人数')">+ 指令下达</a>
				</c:if>
			</div>
			<div class="module_body" style="height:85%">
				<div class="rl1_div2">
					<p><span class="font_b" id = "glgbTitleId">人</span><span class="orgNameClass"></span>当月管理干部人数性别占比</p>
					<div id="hr_echarts_one_gl" style="height:280px"></div>
				</div>
				<div class="rl1_div2">
					<p><span class="font_b" id = "glgbMTitleId">人</span><span class="orgNameClass"></span>当月管理干部人数M序列占比</p>
					<div id="hr_echarts_one_Mgl" style="height:280px"></div>
				</div>
				<div class="rl1_div1" style="padding-left: 2%;">
					<div id="hr_echarts_five" style="height:280px"></div>
				</div>
			</div>
		</div>

		<div class="module_div hr_div_add1">
			<div class="module_head">
				<span id="titleFourId">人工成本执行进度</span>
				<span id="titleTimeFourId"></span>
				<c:if test="${fn:contains(bimShow_ButtonList, '&bs_sp12&')}">
					<a class="zhiling" onclick="sendorder(4,'SHOWTAB_M20','人工成本执行进度')">+ 指令下达</a>
				</c:if>
			</div>
			<div class="module_body" style="height:85%">
				<div id="hr_echarts_six" style="height:100%"></div>
			</div>
		</div>
		<div class="module_div hr_div_add2">
			<div class="module_head">
				<span id="titleFiveId">人工资源回报率</span>
				<span id="titleTimeFiveId"></span>
				<c:if test="${fn:contains(bimShow_ButtonList, '&bs_sp12&')}">
					<a class="zhiling" onclick="sendorder(4,'SHOWTAB_M21','人工资源回报率')">+ 指令下达</a>
				</c:if>
			</div>
			<div class="module_body" style="height:85%">
				<div id="hr_echarts_seven" style="height:100%"></div>
			</div>
		</div>
		
	</section>
	
	<div class="modal-page modal-container" data-remodal-id="page" no=text>
		<iframe id="modal-frame" src="" style></iframe>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/echart/echarts.min.js" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/views/home/homeTB/js/remodal.min.js" charset="utf-8"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/views/home/homeTB/js/hr.js"></script>
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
			//$('.module_div').height($('.rl1_2').height());
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