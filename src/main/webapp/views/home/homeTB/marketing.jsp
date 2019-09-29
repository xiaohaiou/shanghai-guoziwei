<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>经营指标</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-theme.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/views/home/homeTB/fonts/iconfont.css">

	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index_second.css">
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<style>
		.jyzb1_1{
			width:58.5%;
		}
		.jyzb1_1_1{
			display:inline-block;
			width: 24.5%;
		    vertical-align: top;
	        padding: 0 20px;
		}
		.jyzb1_1_2{
			display:inline-block;
			width:74.5%;
		    vertical-align: top;
		}
		.jyzb2_table,.jyzb2_table{
			border-left:1px solid #e6e6e6;
			width:71%;
			padding-left:2%;
			margin-right:2%;
			min-height:330px;
		}
		.ll2_div3{
			margin-top:0;
			height:25em !important;
		}
		.jyzb2_date{
			padding-left:0;
		}
		.jyzb2_table table th:first-child, .jyzb2_table table tr>td:first-child{
		    width: 4em;
		    min-width: 3em;
		}
		.jyzb2_table table th:nth-child(2), .jyzb2_table table tr>td:nth-child(2){
		    text-align:center;
		}
		@media screen and (max-width: 1750px){
			.ll2_div3 {
			    width: 100%;
			}
		}
	</style>
</head>
<body>
	<section class="title">
		<h3>经营指标</h3>
		<p class="select_date">机构选择：
			<select id="org" class="select_com">
				<c:forEach items="${sessionScope.nextOrgList}" var="result">
					<c:if test="${result[0] == myorg}">
						<option value="${result[0]}" selected="true">${result[1]}</option>
					</c:if>
					<c:if test="${result[0] != myorg}">
						<option value="${result[0]}">${result[1]}</option>
					</c:if>
				</c:forEach>
			</select>
			时间选择：<input class="form-control" size="16" type="text" value="" readonly>
			<input type="button" class="title_btn" value="查询" onclick="changetime()">
			<input type="hidden" id="dtp_input2" value="" />
		</p>
	</section>
	<section class="jyzb1">
		<div class="module_div jyzb1_1">
			<div class="module_head">
				<span id="title_1">${cashVal_M.orgnm}现金净流量 </span>
				<span id="date_1">${cashVal_M.date_y}年${cashVal_M.date_m}月</span>
				<c:if test="${fn:contains(bimShow_ButtonList, '&bs_sp12&')}">
					<a class="zhiling" onclick="sendorder(8,'SHOWTAB_M61','${cashVal_M.orgnm}${cashVal_M.date_y}年${cashVal_M.date_m}月现金流平衡')">+ 指令下达</a>
				</c:if>
			</div>
			<div class="module_body">
				<div class="jyzb1_1_1">
					<p class="jyzb1_text">当月现金净流量</p>
					<p class="jyzb1_num_g">${cashVal_M.a00084}亿元</p>
					<p class="jyzb1_text">当月现金流入</p>
					<p class="jyzb1_num_g">${cashVal_M.a00082}亿元</p>
					<p class="jyzb1_text">当月现金流出</p>
					<p class="jyzb1_num_r">${cashVal_M.a00083}亿元</p>
				</div>
				<div class="jyzb1_1_2">
					<div id="cash_chart" style="width:100%;height:250px">
					</div>
				</div>
			</div>
		</div>
		<div class="module_div jyzb1_2">
			<div class="module_head">
				<span id="title_2">${cashVal_W.orgnm}周现金净流量情况</span>
				<span id="date_2">${cashVal_M.date_y}年${cashVal_M.date_m}月</span>
				<c:if test="${fn:contains(bimShow_ButtonList, '&bs_sp12&')}">
					<a class="zhiling" onclick="sendorder(8,'SHOWTAB_M61','${cashVal_M.orgnm}${cashVal_M.date_y}年${cashVal_M.date_m}月周现金净流量情况')">+ 指令下达</a>
				</c:if>
			</div>
			<div class="module_body">
				<div class="jyzb1_table">
					<table>
						<tr>
							<td>周</td>
							<td>收入</td>
							<td>支出</td>
							<td>现金净流量</td>
						</tr>
						<c:if test="${!empty list_W_oneMonth}">
							<c:forEach var="tl" items="${list_W_oneMonth}">
								<tr>
									<td>${tl.date_w}</td>
									<td>${tl.a00085}亿元</td>
									<td>${tl.a00086}亿元</td>
									<td>${tl.a00087}亿元</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty list_W_oneMonth}">
							<tr>
								<td colspan="4">暂无数据</td>
							</tr>
						</c:if>
					</table>
				</div>
			</div>
		</div>		
	</section>
	<section class="jyzb2">
		<div class="module_div jyzb2_1">
			<div class="module_head">
				<span id="title_3">${cashVal_IM.orgnm}收入</span>
				<span id="date_3">${cashVal_IM.date_y}年${cashVal_IM.date_m}月</span>
				<c:if test="${fn:contains(bimShow_ButtonList, '&bs_sp12&')}">
					<span class="module_head_title"><a class="zhiling" onclick="sendorder(8,'SHOWTAB_M71','${cashVal_IM.orgnm}${cashVal_IM.date_y}年${cashVal_IM.date_m}月收入')">+ 指令下达</a></span>
				</c:if>
			</div>
			<div class="module_body">
				<div class="jyzb2_date">
					<c:if test="${!empty cashVal_IM}">
						<p class="jyzb2_title">完成率<span>${cashVal_IM.incomePre}</span><span>%</span></p>
						<div>
							<p>当期收入预算</p>
							<p class="font_g">${cashVal_IM.a00133}亿元</p>
						</div>
						<div>
							<p>实际收入</p>
							<p class="font_r">${cashVal_IM.a00001}亿元</p>
						</div>
						<div>
							<p>累计收入预算</p>
							<p class="font_g">${cashVal_IM.a00134}亿元</p>
						</div>
						<div>
							<p>累计完成收入</p>
							<p class="font_r">${cashVal_IM.a00074}亿元</p>
						</div>
					</c:if>
					<c:if test="${empty cashVal_IM}">
						<p class="jyzb2_title">完成率<span>-</span><span></span></p>
						<div>
							<p>当期收入预算</p>
							<p class="font_g">-</p>
						</div>
						<div>
							<p>实际收入</p>
							<p class="font_r">-</p>
						</div>
						<div>
							<p>累计收入预算</p>
							<p class="font_g">-</p>
						</div>
						<div>
							<p>累计完成收入</p>
							<p class="font_r">-</p>
						</div>
					</c:if>
				</div>
				<div class="jyzb2_table">
					<table>
						<tr>
							<th>序号</th>
							<th>公司</th>
							<th>月度实际</th>
							<th>月度预算</th>
							<th>预算完成率</th>
							<th>累计预算完成数</th>
							<th>累计预算</th>
							<th>累计预算完成率</th>
							<th>年度预算</th>
							<th>全年累计完成率</th>
						</tr>
						<c:if test="${!empty cashVal_IM_MULTI }">
							<c:forEach var="tl" items="${cashVal_IM_MULTI}" varStatus="status">
								<tr>
									<td>${status.index + 1}</td>
									<td>${tl.orgnm}</td>
									<td>${tl.a00001}亿元</td>
									<td>${tl.a00133}亿元</td>
									<td>${tl.incomePre}%</td>
									<td>${tl.a00074}亿元</td>
									<td>${tl.a00134}亿元</td>
									<td>${tl.incomeLPre}%</td>
									<td>${tl.a00135}亿元</td>
									<td>${tl.incomeSPre}%</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty cashVal_IM_MULTI }">
							<tr>
								<td colspan="10">暂无数据</td>
							</tr>
						</c:if>
					</table>
				</div>
			</div>
			<div class="jyzb2_chart">
				<p id="shouru" class="ll1_1p">${cashVal_IM.orgnm}${cashVal_IM.date_y}年收入趋势</p>
				<div id="income_chart" style="width:100%;height:80%">
				</div>
			</div>	
		</div>	
	</section>
	<section class="jyzb2">
		<div class="module_div jyzb2_1">
			<div class="module_head">
				<span id="title_4">${cashVal_IM.orgnm}利润</span>
				<span id="date_4">${cashVal_IM.date_y}年${cashVal_IM.date_m}月</span>
				<c:if test="${fn:contains(bimShow_ButtonList, '&bs_sp12&')}">
					<span class="module_head_title"><a class="zhiling" onclick="sendorder(8,'SHOWTAB_M71','${cashVal_IM.orgnm}${cashVal_IM.date_y}年${cashVal_IM.date_m}月利润')">+ 指令下达</a></span>
				</c:if>
			</div>
			<div class="module_body">
				<div class="jyzb2_date">
					<c:if test="${!empty cashVal_IM }">
						<p class="jyzb2_title">完成率<span>${cashVal_IM.profitPre}</span><span>%</span></p>
						<div>
							<p>当期利润预算</p>
							<p class="font_g">${cashVal_IM.a00136}亿元</p>
						</div>
						<div>
							<p>实际利润</p>
							<p class="font_r">${cashVal_IM.a00005}亿元</p>
						</div>
						<div>
							<p>累计利润预算</p>
							<p class="font_g">${cashVal_IM.a00137}亿元</p>
						</div>
						<div>
							<p>累计完成利润</p>
							<p class="font_r">${cashVal_IM.a00075}亿元</p>
						</div>
					</c:if>
					<c:if test="${empty cashVal_IM }">
						<p class="jyzb2_title">完成率<span>-</span><span></span></p>
						<div>
							<p>当期利润预算</p>
							<p class="font_g">-</p>
						</div>
						<div>
							<p>实际利润</p>
							<p class="font_r">-</p>
						</div>
						<div>
							<p>累计利润预算</p>
							<p class="font_g">-</p>
						</div>
						<div>
							<p>累计完成利润</p>
							<p class="font_r">-</p>
						</div>
					</c:if>
				</div>
				<div class="jyzb2_table">
					<table>
						<tr>
							<th>序号</th>
							<th>公司</th>
							<th>月度实际</th>
							<th>月度预算</th>
							<th>预算完成率</th>
							<th>累计预算完成数</th>
							<th>累计预算</th>
							<th>累计预算完成率</th>
							<th>年度预算</th>
							<th>全年累计完成率</th>
						</tr>
						<c:if test="${!empty cashVal_IM_MULTI }">
							<c:forEach var="tl" items="${cashVal_IM_MULTI}" varStatus="status">
								<tr>
									<td>${status.index + 1}</td>
									<td>${tl.orgnm}</td>
									<td>${tl.a00005}亿元</td>
									<td>${tl.a00136}亿元</td>
									<td>${tl.profitPre}%</td>
									<td>${tl.a00075}亿元</td>
									<td>${tl.a00137}亿元</td>
									<td>${tl.profitLPre}%</td>
									<td>${tl.a00138}亿元</td>
									<td>${tl.profitSPre}%</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty cashVal_IM_MULTI }">
							<tr>
								<td colspan="10">暂无数据</td>
							</tr>
						</c:if>
					</table>
				</div>
			</div>
			<div class="ll2_div3">
				<p id="lirun" class="ll1_1p">${cashVal_IM.orgnm}${cashVal_IM.date_y}年利润趋势</p>
				<div id="profit_chart" style="width:100%;height:85%">
				</div>
			</div>
		</div>	
	</section>
	<jsp:include page="../../system/modal.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/echart/echarts.min.js" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/views/home/homeTB/js/marketing.js"></script>
	<script>
		function get_height(){
			/* $('.jyzb1_3').height($('.jyzb1_1').height()); */
			$('.jyzb1_2').height($('.jyzb1_1').height());
			console.log($('.jyzb1_1').height())
		}
		get_height();
	</script>
	<script type="text/javascript">
		var myyears = ${myYear};
		var mymonths = ${myMonth};
		
		var company = ${company};
		var inList = ${inList};
		var outList = ${outList};
		var sList = ${sList};
		
		var inCList = ${inCList};
		var proCList = ${proCList};
		
		
		marketingInit();
	    
	     // 获取当前时间
	    var pre_date;
	    /* function show(){
		    var mydate = new Date();
		    var str = "" + mydate.getFullYear() + "年";
		    str += ("0"+(mydate.getMonth())).slice(-2) + "月";
		    pre_date = mydate.getFullYear()+"-"+("0"+(mydate.getMonth())).slice(-2);
		    // console.log(pre_date)
		    $('.form-control').val(str);
	   } */
	   function show(){
		    var mydate = new Date();
		    var str = "" + myyears + "年";
		    if(mymonths < 10){
		    	str += "0"+mymonths + "月";
		    	pre_date = myyears + "-0" + mymonths
		    }
		    else{
		    	str += ""+mymonths + "月";
		    	pre_date = myyears + "-" + mymonths
		    }
		    //pre_date = mydate.getFullYear()+"-"+("0"+(mydate.getMonth())).slice(-2);
		    // console.log(pre_date)
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
		
		/**
		 * 根据选择时间加载数据
		 */
		function changetime(){
			var marketingDate = $('.form-control').val();
			var markrtingOrgId = $('#org option:selected').val();
			var marketingYear;
			var marketingMonth;
			if (marketingDate == undefined || marketingDate == null){
				var mydate = new Date();
				marketingYear = "" + mydate.getFullYear();
				marketingMonth = "" + mydate.getMonth();
			} else {
				marketingYear = marketingDate.slice(0,4);
				marketingMonth = marketingDate.slice(5,7);
			}
			var url = "${pageContext.request.contextPath}/marketing/_getMarketing?year="+marketingYear+"&&month="+marketingMonth+"&&markrtingOrgId="+markrtingOrgId;
			window.location.href = url;
		}
		
		
		if(company == null || company == '' || company == undefined ){
			$('#title_1').html($('#org option:selected').text()+"现金净流量");
			$('#date_1').html($('.form-control').val());
			$('#title_2').html($('#org option:selected').text()+"周现金净流量情况");
			$('#date_2').html($('.form-control').val());
			$('#title_3').html($('#org option:selected').text()+"收入");
			$('#date_3').html($('.form-control').val());
			$('#title_4').html($('#org option:selected').text()+"利润");
			$('#date_4').html($('.form-control').val());
			$('#shouru').html($('#org option:selected').text()+$('.form-control').val().slice(0,4)+"收入趋势");
			$('#lirun').html($('#org option:selected').text()+$('.form-control').val().slice(0,4)+"利润趋势");
		}
	/*	<span id="title_1">${cashVal_M.orgnm}现金净流量 </span>
		<span id="date_1">${cashVal_M.date_y}年${cashVal_M.date_m}月</span>
		<span id="title_2">${cashVal_W.orgnm}周现金净流量情况</span>
		<span id="date_2">${cashVal_M.date_y}年${cashVal_M.date_m}月</span>
		<span id="title_3">${cashVal_IM.orgnm}收入</span>
		<span id="date_3">${cashVal_IM.date_y}年${cashVal_IM.date_m}月</span>
		<span id="title_4">${cashVal_IM.orgnm}利润</span>
		<span id="date_4">${cashVal_IM.date_y}年${cashVal_IM.date_m}月</span>*/
		
		
		//下达指令
	    //typeid 模块序号
	    //mapId 查询具体指标或者表序号 参考def_task
		function sendorder(typeid,mapId,charttitle){
			var url='${pageContext.request.contextPath}'+"/task/instruction/_add?type="+typeid+"&mapId="+mapId+"&taskName="+(charttitle).replace(/\%/g,'%25');
			mload(url);       
		}
	</script>
</body>
</html>