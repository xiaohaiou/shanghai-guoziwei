<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>六率指标</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-theme.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/font/iconfont.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/lin.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index_second.css">
	<style>
.ll6_div1,.ll6_div2{
	position:relative;
}
.ll6_div1>a,.ll6_div2>a{
	position:absolute;
	top:-15px;
	right:20px;
}
.ll6_div2>a{
	right:-20px;
}
.ll2_1 .module_body>div:nth-child(2){
	border-right:none !important;
}
.ll2_div3{
	height:320px !important;
	margin-top:0;
}
.ll2_div1 tr>td:first-child,.ll2_div1 tr>th:first-child{
	/* width:15% !important; */
	text-align:center;
}
.ll1_2p span,.ll1_3p span{
	float:right;
	margin-right:4em;
}
.ll2_div1 td, .ll2_div1 th {
    width: initial !important;
}
.ll2_div1{
	width:43%;
}
.ll2_div2{
	width:56%;
}
</style>
	<script src="<c:url value="/js/jquery.min.js"/>" charset="utf-8"></script>
</head>
<body>
	<section class="title">
		<h3>六率指标</h3>
		<p class="select_date">
			机构选择：
			<select id="org" class="select_com">
				<c:forEach items="${sessionScope.nextOrgList}" var="result">
					<option value="${result[0]}">${result[1]}</option>
				</c:forEach>
			</select>
			&nbsp;&nbsp;
			时间选择：
			<input class="form-control" size="16" type="text" value="" readonly="true">
			<input type="button" class="title_btn" onclick="changOrgAndDate()" value="查询">
		</p>
	</section>
	<section class="ll1">
		<div class="module_div ll1_1">
			<div class="module_head">
				<span id="rdsc_title">劳动生产率</span>
				<span id="rdsc_date"></span>
				<c:if test="${fn:contains(bimShow_ButtonList, '&bs_sp12&')}">
					<a class="zhiling" onclick="sendorder(1,'a00003','劳动生产率')">+ 指令下达</a>
				</c:if>
			</div>
			<div class="module_body">
				<div class="ll1_div1">
					<span id="ld_shouru">
						<p class="ll1_1p">人均收入:<span></span>万元</p>
						<p class="ll1_2p">劳动用工人数<span></span></p>
						<p class="ll1_2p">上月<span>万元</span></p>
						<p class="ll1_3p">去年同期<span>万元</span></p>
					</span>
					<div id="pie_1" style="height:300px">
					</div>
				</div>
				<div class="ll1_div2">
					<span id="ld_lirun">
						<p class="ll1_1p">人均利润:<span></span>万元</p>
						<p class="ll1_2p">人数<span></span></p>
						<p class="ll1_2p">上月<span>万元</span></p>
						<p class="ll1_3p">去年同期<span>万元</span></p>
					</span>
					<div id="pie_2" style="height:300px">
					</div>
				</div>
				<div class="ll1_div3">
					<span>
						<p id="ldsc_picTitle" class="ll1_1p">劳动生产率趋势图</p>
					</span>
					<div id="line_1" style="height:400px">
					</div>
				</div>
			</div>
		</div>
	</section>
	<section class="ll2">
		<div class="module_div ll2_1">
			<div class="module_head">
				<span id="zcfz_title">资产负债率</span>
				<span id="zcfz_date"></span>
				<c:if test="${fn:contains(bimShow_ButtonList, '&bs_sp12&')}">
					<a class="zhiling" onclick="sendorder(1,'a00004','资产负债率')">+ 指令下达</a>
				</c:if>
			</div>
			<div class="module_body">
				<div id="zcfz" class="ll2_div1">
					<p class="ll2_per1"><span>%</span></p>
					<table>
						<tr>
							<th></th>
							<th>当期</th>
							<th>去年同期</th>
							<th>上月期末</th>
							<th>去年期末</th>
						</tr>
						<tr>
							<td>总负债</td>
							<td>0</td>
							<td class="font_r"></td>
							<td class="font_g"></td>
							<td class="font_r"></td>
						</tr>
						<tr>
							<td>总资产</td>
							<td>0</td>
							<td class="font_r"></td>
							<td class="font_r"></td>
							<td class="font_r"></td>
						</tr>
						<tr>
							<td>资产负债率</td>
							<td>0%</td>
							<td class="font_g">%</td>
							<td class="font_r">%</td>
							<td class="font_g">%</td>
						</tr>
					</table>
				</div>
				<div class="ll2_div2">
					<p></p>
					<table id="zcfz_org">
						<tr>
							<th>序号</th>
							<th>机构</th>
							<th>当期总负债</th>
							<th>当期总资产</th>
							<th>当期资产负债率</th>
						</tr>
					</table>
				</div>
			</div>
			<div class="ll2_div3">
				<p id="zcfz_picTitle" class="ll1_1p">资产负债率趋势图</p>
				<div id="bar_1"  style="height:300px">
				</div>
			</div>
		</div>
	</section>
	<section class="ll2">
		<div class="module_div ll2_1">
			<div class="module_head">
				<span id="zczz_title">资产周转率</span>
				<span id="zczz_date"></span>
				<c:if test="${fn:contains(bimShow_ButtonList, '&bs_sp12&')}">
					<a class="zhiling" onclick="sendorder(1,'a00004','资产周转率')">+ 指令下达</a>
				</c:if>
			</div>
			<div class="module_body">
				<div id="zczz" class="ll2_div1">
					<pclass="ll2_per1"><span>%</span></p>
					<table>
						<tr>
							<th></th>
							<th>当期</th>
							<th>去年同期</th>
							<th>上月期末</th>
							<th>去年期末</th>
						</tr>
						<tr>
							<td>营业收入</td>
							<td>0</td>
							<td class="font_r"></td>
							<td class="font_g"></td>
							<td class="font_r"></td>
						</tr>
						<tr>
							<td>平均资产总额</td>
							<td>0</td>
							<td class="font_r"></td>
							<td class="font_r"></td>
							<td class="font_r"></td>
						</tr>
						<tr>
							<td>总资产周转率</td>
							<td>%</td>
							<td class="font_g">%</td>
							<td class="font_r">%</td>
							<td class="font_g">%</td>
						</tr>
					</table>
				</div>
				<div class="ll2_div2">
					<p></p>
					<table id="zczz_org">
						<tr>
							<th>序号</th>
							<th>机构</th>
							<th>当期营业收入</th>
							<th>当期平均资产总额</th>
							<th>当期总资产周转率</th>
						</tr>
					</table>
				</div>
				<div class="ll2_div3">
					<p id="zczz_picTitle" class="ll1_1p">资产周转率趋势图</p>
					<div id="bar_2"  style="height:300px">
					</div>
				</div>
			</div>
		</div>
	</section>
	<section class="ll2">
		<div class="module_div ll2_1">
			<div class="module_head">
				<span id="zblr_title">资本利润率</span>
				<span id="zblr_date"></span>
				<c:if test="${fn:contains(bimShow_ButtonList, '&bs_sp12&')}">
					<a class="zhiling" onclick="sendorder(1,'a00004','资本利润率')">+ 指令下达</a>
				</c:if>
			</div>
			<div class="module_body">
				<div id="zblr" class="ll2_div1">
					<p class="ll2_per1"><span>%</span></p>
					<table>
						<tr>
							<th></th>
							<th>当期</th>
							<th>去年同期</th>
							<th>上月期末</th>
							<th>去年期末</th>
						</tr>
						<tr>
							<td>净利润</td>
							<td>0</td>
							<td class="font_r"></td>
							<td class="font_g"></td>
							<td class="font_r"></td>
						</tr>
						<tr>
							<td>平均资本金</td>
							<td></td>
							<td class="font_r"></td>
							<td class="font_r"></td>
							<td class="font_r"></td>
						</tr>
						<tr>
							<td>资本利润率</td>
							<td>%</td>
							<td class="font_g">%</td>
							<td class="font_r">%</td>
							<td class="font_g">%</td>
						</tr>
					</table>
				</div>
				<div class="ll2_div2">
					<p></p>
					<table id="zblr_org">
						<tr>
							<th>序号</th>
							<th>机构</th>
							<th>当期净利润</th>
							<th>当期平均资产总额</th>
							<th>当期资本利润率</th>
						</tr>
					</table>
				</div>
				<div class="ll2_div3">
					<p id="zblr_picTitle" class="ll1_1p">资本利润率趋势图</p>
					<div id="bar_3"  style="height:300px">
					</div>
				</div>
			</div>
		</div>
	</section>
	<section class="ll2">
		<div class="module_div ll2_1">
			<div class="module_head">
				<span id="zcsy_title">净资产收益率</span>
				<span id="zcsy_date">2017年8月</span>
				<c:if test="${fn:contains(bimShow_ButtonList, '&bs_sp12&')}">
					<a class="zhiling" onclick="sendorder(1,'a00004','净资产收益率')">+ 指令下达</a>
				</c:if>
			</div>
			<div class="module_body">
				<div id="zcsy" class="ll2_div1">
					<p class="ll2_per1"><span>%</span></p>
					<table>
						<tr>
							<th></th>
							<th>当期</th>
							<th>去年同期</th>
							<th>上月期末</th>
							<th>去年期末</th>
						</tr>
						<tr>
							<td>净利润</td>
							<td></td>
							<td class="font_r"></td>
							<td class="font_g"></td>
							<td class="font_r"></td>
						</tr>
						<tr>
							<td>平均净资产</td>
							<td></td>
							<td class="font_r"></td>
							<td class="font_r"></td>
							<td class="font_r"></td>
						</tr>
						<tr>
							<td>净资产收益率</td>
							<td>%</td>
							<td class="font_g">%</td>
							<td class="font_r">%</td>
							<td class="font_g">%</td>
						</tr>
					</table>
				</div>
				<div class="ll2_div2">
					<p></p>
					<table  id="zcsy_org">
						<tr>
							<th>序号</th>
							<th>机构</th>
							<th>当期净利润</th>
							<th>当期平均净资产</th>
							<th>当期净资产收益率</th>
						</tr>
					</table>
				</div>
				<div class="ll2_div3">
					<p id="zcsy_picTitle" class="ll1_1p">净资产收益率趋势图</p>
					<div id="bar_4" style="height:300px">
					</div>
				</div>
			</div>
		</div>
	</section>
	<c:if test="${fn:contains(bimShow_orgID,'26655') || fn:contains(bimShow_orgID,'4010') || fn:contains(bimShow_orgID,'27534')}">
	<section id="sczy" class="ll6">
		<div class="module_div ll6_1">
			<div class="module_head">
				<span id="sczy_title">市场占有率</span>
				<span id="sczy_date"></span>
			</div>
			<div class="module_body">
				<c:if test="${fn:contains(bimShow_orgID,'26655') || fn:contains(bimShow_orgID,'4010')}">
					<div id="kupu" class="ll6_div1">
						<c:if test="${fn:contains(bimShow_ButtonList, '&bs_sp12&')}">
							<a class="zhiling" onclick="sendorder(1,'a00005','市场占有率')">+ 指令下达</a>
						</c:if>
						<div class="ll6_div1_1">
							<p class="ll6_title">市场占有率</p>
							<img src="${pageContext.request.contextPath}/yj-img/logo1.png">
							<p class="ll6_per" id="sczy_kp"><span class="font_g"></span>%</p>
							<p class="ll6_mon">当月值</p>
							<p id="sczy_last_kp" class="ll6_per2">同比<span class="font_r"></span>%</p>
						</div>
						<div class="ll6_div1_2">
							<p id="kp_picTitle" class="ll1_1p">酷铺市场占有率趋势图</p>
							<div id="sczy_line_1" style="height:300px">
							</div>
						</div>
					</div>
				</c:if>
				<c:if test="${fn:contains(bimShow_orgID,'26655') || fn:contains(bimShow_orgID,'27534')}">
					<div id="dichan" class="ll6_div2">
						<c:if test="${fn:contains(bimShow_ButtonList, '&bs_sp12&')}">
							<a class="zhiling" onclick="sendorder(1,'a00005','市场占有率')">+ 指令下达</a>
						</c:if>
						<div class="ll6_div1_1">
							<p class="ll6_title">市场占有率</p>
							<img src="${pageContext.request.contextPath}/yj-img/logo2.png">
							<p class="ll6_per" id="sczy_hhdc"><span class="font_g"></span>%</p>
							<p class="ll6_mon">当月值</p>
							<p id="sczy_last_hhdc" class="ll6_per2">同比<span class="font_r"></span>%</p>
						</div>
						<div class="ll6_div1_2">
							<p id="dc_picTitle" class="ll1_1p">海航地产市场占有率趋势图</p>
							<div id="sczy_line_2" style="height:300px">
							</div>
						</div>
					</div>
				</c:if>
			</div>
		</div>
	</section>
	</c:if>
	<jsp:include page="../../system/modal.jsp"/>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/echart/echarts.min.js" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/views/home/homeTB/js/sixRate.js"></script>
	<script type="text/javascript">
		var basePath = '${pageContext.request.contextPath}';
		// 组织机构级别
		var orgLevel = ${bimShow_orgLevel};

		var pre_date;
		// 获取当前时间
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
			language:  'zh-CN',
			weekStart: 1,
			todayBtn:  0,
			autoclose: 1,
			todayHighlight: 1,
			startView: 3,
			minView: 3,
			forceParse: 0,
			format: 'yyyy年mm月'
		});
		$('.form-control').datetimepicker( 'update', pre_date );
		// 设置part1高度统一
		function get_height(){
			$('.ll2_div2').height($('.ll2_div1').height());
			//$('.ll2_div3').height($('.ll2_div1').height());
		}
		get_height();
		// 画面初期化
		sixRateInit();
		//下达指令
		// type 模块序号
		// mapId 指标名称ID
		// taskName 标题名称
		function sendorder(typeid,mapId,charttitle){
			var url=basePath+"/task/instruction/_add?type="+typeid+"&mapId="+mapId+"&taskName="+(charttitle).replace(/\%/g,'%25');
			mload(url);
		};
	</script>
</body>
</html>