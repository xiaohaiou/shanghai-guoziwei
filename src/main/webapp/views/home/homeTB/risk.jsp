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
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fonts/iconfont.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/lin.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index_second.css">
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<style>
	.cg_div1>div>div{
		height:200px;
	}
	.module_body{
		height:400px;
	}
	.rl1_2{
	    height: inherit;
	}
	.module_body{
		height:inherit;
	} 
	.fx1_div1>div{
		display:block;
		text-align:center;
		font-size:20px;
	}
	#Auditpie{
		margin-bottom:2em;
	}
	.fx3_1{
		width:49%;
		margin-right:1%;
	}
	.fx3_2{
		width:49.6%;
	}
	.fx3_chart1, .fx3_chart2{
		width:100%;
		border-right:none;	
	}
	.fx1_div3{
		min-height:300px;
	}
	.fx1_div3 table tr{
		line-height:2.5em;
	}
	.fx2_div3 table{
		margin-top: 20px;
    	line-height: 2.5em;
	}
	</style>
</head>
<body >
	<section class="title">
		<h3>风险控制</h3>
		<p class="select_date">
		<form>
			机构选择：
			<select class="select_com" style="width:15em" name="organal" id="org" >	
				<c:forEach items="${requestScope.organallist}" var="result">
					<option value="${result.nnodeId }"  <c:if test="${organal == result.nnodeId}">selected="selected"</c:if>>${result.vcFullName }</option>
				</c:forEach>
			</select>
			时间选择：
			<input class="form-control" size="16" type="text" value="" name="date" readonly value="${date }">
			<input type="hidden" id="dtp_input2" value="" />
			<input type="button" class="title_btn" value="查询" onclick="_query()">
		
		</form>
		</p>
	</section>
	<section class="fx1">
		<div class="module_div fx1_1">
			<div class="module_head">
				<span>${organname }审计信息</span>
				<c:if test="${fn:contains(bimShow_ButtonList, '&bs_sp12&')}">
					<a class="zhiling" onclick="sendorder(7,'SHOWTAB_M30','审计')">+ 指令下达</a>
				</c:if>
				<span>${date}</span>
			</div>
			<div class="module_body">
				<div class="fx1_div1">
					<p class="fx1_title1"><span class="font_b" id="allAmount"></span>审计项目数</p>
					
					<div>
						<p>发现问题数</p>
						<p class="font_r" id="problemAmount"></p>
					</div>
					<div>
						<p>审计问责人员数量</p>
						<p class="font_r" id="callPerson"></p>
					</div>
				</div>
				<div class="fx1_div2">
					<div id="Auditpie" style="height:200px">
					</div>
				
					<p class="fx1_title1">审计问题整改完成率</p>
					<p class="font_g fx1_per1" id="Auditrate"></p>
				</div>
				<div class="fx1_div3">
					<table>
						<tr>
							<th style="text-align: center">序号</th>
							<th style="text-align: center">机构</th>
							<th style="text-align: center">审计项目数</th>
							<th style="text-align: center">问责人员数量</th>
							<th style="text-align: center">审计问题整改率</th>
						</tr>
						<c:if test="${!empty Audit }">
							<c:forEach var="tl" items="${Audit}" varStatus="status1">
								<tr>
									<td style="text-align: center">${status1.index + 1 }</td>
									<td>${tl[0]}</td>
									<td style="text-align: right"><fmt:formatNumber value="${tl[1]}" pattern="#,##0" /><c:if test="${  empty tl[1] }">-</c:if>个  </td>
									<td style="text-align: right"><fmt:formatNumber value="${tl[2]}" pattern="#,##0" /><c:if test="${  empty tl[2] }">-</c:if>个 </td>
									<td style="text-align: right">  <fmt:formatNumber value="${tl[3]}" pattern="#,##0.00" /> <c:if test="${  empty tl[3] }">-</c:if>%</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty Audit }">
						<td  style="text-align: center" colspan="5">无数据</td>
						</c:if>
					</table>
				</div>
				<div class="fx1_div4" >
					
					<div id="Auditline" style="height:400px"></div>
				</div>
			</div>
		</div>
	</section>
	<section class="fx2">
		<div class="module_div fx2_1">
		<div class="module_head">
				<span>${organname }合同信息</span>
				<c:if test="${fn:contains(bimShow_ButtonList, '&bs_sp12&')}">
					<a class="zhiling" onclick="sendorder(7,'SHOWTAB_M35','合同')">+ 指令下达</a>
				</c:if>
				<span>${date}</span>
			</div>
			<div class="module_body">
				<div class="fx2_div1">
					<div class="fx2_div11">
						<p class="fx1_title1"><span class="font_g" id="contractAllAmount"></span>正在履行合同数</p>
						<div>
							<p>合同标的发生总金额</p>
							<p class="font_g" id="contractAmountMoney"></p>
						</div>
						<div>
							<p>涉诉合同份数</p>
							<p class="font_r" id="appealContract"></p>
						</div>
						<div>
							<p>涉诉案件标的金额</p>
							<p class="font_r" id="appealContractAmount"></p>
						</div>
					</div>
					<div class="fx2_div12">
						<div class="fx2_div13">
							<p class="fx1_title1"><span class="font_g" id="breakContract"></span>合同违约数</p>
							<div>
								<p>我方违约数</p>
								<p class="font_g" id="mebreakContract"></p>
							</div>
							<div>
								<p>对方违约数</p>
								<p class="font_r" id="otherbreakContract"></p>
							</div>
						</div>
						<div class="fx2_div14">
							<div>
								<p>逾期应收款</p>
								<p class="font_r" id="overdueReceivable"></p>
							</div>
							<div>
								<p>逾期应付款</p>
								<p class="font_r" id="overdueDue"></p>
							</div>
							<div>
								<p>应收违约金</p>
								<p class="font_r" id="damagesReceivable"></p>
							</div>
							<div>
								<p>应付违约金</p>
								<p class="font_r" id="damagesPayable"></p>
							</div>
						</div>
					</div>
				</div>
				<div class="fx2_div2">
				
					<div id="contractMonthData">
					
					</div>
				</div>
				<div class="fx2_div3">
					<%-- <p class="fx2_title1" style="font-size:18px;color:#333">${organname }合同违约数<a class="zhiling" onclick="sendorder(7,'SHOWTAB_M34','合同违约数')">+ 指令下达</a></p> --%>
					<div id="breakcontract">
					
					</div>
					
				</div>
				<div class="fx2_div3" >
					<table>
						<tr>
							<th style="text-align: center">序号</th>
							<th style="text-align: center">机构</th>
							<th style="text-align: center">正在履行合同数</th>
							<th style="text-align: center">合同标的发生总金额</th>
							<th style="text-align: center">涉诉合同份数</th>
							<th style="text-align: center">涉诉案件标的金额</th>
							<th style="text-align: center">合同违约数</th>
							<th style="text-align: center">我方违约数</th>
							<th style="text-align: center">对方违约数</th>
							<th style="text-align: center">逾期应收款</th>
							<th style="text-align: center">逾期应付款</th>
							<th style="text-align: center">应收违约金</th>
							<th style="text-align: center">应付违约金</th>
						</tr>
						<c:if test="${!empty Contract }">
							<c:forEach var="tl" items="${Contract}" varStatus="status1">
								<tr>
								<td style="text-align: center">${status1.index + 1 }</td>
									<td>${tl[0]}</td>
									<td style="text-align: right">${tl[7]} <c:if test="${empty tl[7] }">-</c:if>份</td>
									<td style="text-align: right">${tl[8]/10000} <c:if test="${empty tl[8] }">-</c:if>亿元</td>
									<td style="text-align: right">${tl[9]} <c:if test="${empty tl[9] }">-</c:if>份</td>
									<td style="text-align: right">${tl[10]/10000} <c:if test="${empty tl[10] }">-</c:if>亿元</td>
									<td style="text-align: right">${tl[1]+tl[2]}份  <c:if test="${(empty tl[1]) &&( empty tl[2]) }">-</c:if></td>
									<td style="text-align: right">${tl[1]} <c:if test="${empty tl[1]}">-</c:if>份</td>
									<td style="text-align: right">${tl[2]} <c:if test="${empty tl[2] }">-</c:if>份</td>
									<td style="text-align: right">${tl[3]/10000} <c:if test="${empty tl[3] }">-</c:if>亿元</td>
									<td style="text-align: right">${tl[4]/10000} <c:if test="${empty tl[4] }">-</c:if>亿元</td>
									<td style="text-align: right">${tl[5]/10000} <c:if test="${empty tl[5] }">-</c:if>亿元</td>
									<td style="text-align: right">${tl[6]/10000} <c:if test="${empty tl[6] }">-</c:if>亿元</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty Contract }">
						<td  style="text-align: center" colspan="13">无数据</td>
						</c:if>
					</table>
				</div>
				
				
			</div>
		</div>
	</section>
	<section class="fx3">

		<div class="module_div fx3_1" >
			<div class="module_head">
				<span>${organname }在办合规调查数</span>
				<c:if test="${fn:contains(bimShow_ButtonList, '&bs_sp12&')}">
					<a class="zhiling" onclick="sendorder(7,'SHOWTAB_M36','在办合规调查数')">+ 指令下达</a>
				</c:if>
				<span>${date}</span>
			</div>
			<div class="module_body">
				<div class="fx3_chart1" >
					<div style="height:300px" id="InquireData">
					
				</div>
				</div>
				
			</div>
		</div>
		<div class="module_div fx3_2" >
			<div class="module_head">
				<span>${organname }案件发生数</span>
				<c:if test="${fn:contains(bimShow_ButtonList, '&bs_sp12&')}">
					<a class="zhiling" onclick="sendorder(7,'SHOWTAB_S37','案件发生数')">+ 指令下达</a>
				</c:if>
				<span>${date}</span>
			</div>
			<div class="module_body">
		
				<div class="fx3_chart2" >
						<div  id="EventData" style="height:300px">
					
				</div>
				</div>
			</div>
		</div>	
				
			
	</section>
	<jsp:include page="../../system/modal.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/views/home/homeTB/js/risk.js"></script>
	
	<script type="text/javascript">
	
	
	var organName='${organname}'
		function _query()
		{
			var form = document.forms[0];
			form.action = "${pageContext.request.contextPath}/risk/risk";
			form.method = "post";
			form.submit();		
		}
		
	   // 获取当前时间
	    var pre_date;
	    function show(){
	   /*  	if($('.form-control').val()=="")
			{
			   var mydate = new Date();
			    if(mydate.getUTCMonth()==0){
			    	var str = "" + (mydate.getFullYear()-1) + "年";
				    str += "12月";
				    pre_date = (mydate.getFullYear()-1)+"-"+"12";
			    }else{
			    	console.log(mydate.getUTCMonth())
				    var str = "" + mydate.getFullYear() + "年";
				    str += ("0"+(mydate.getMonth())).slice(-2) + "月";
				    pre_date = mydate.getFullYear()+"-"+("0"+(mydate.getMonth())).slice(-2);
			    }
			    // console.log(pre_date)
			    $('.form-control').val(str);
			 }
			 else */
			  $('.form-control').val('${date}');
			  pre_date =$('.form-control').val().replace("年",'-').replace("月","");	
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
	    riskinit();
		// 设置part1高度统一
		function get_height(){
			var h1 = $('.fx2_div1').height();
			var h2 = $('.fx2_div2').height();
			var h3 = Math.max(h1,h2)
			$('.fx2_1>div>div').height(h3);
			// console.log($('.fx_div2').height())
			$('.fx1_div2').height($('.fx1_div3').height());
			$('.fx1_div1').height($('.fx1_div3').height());
			// $('.xz2_div3').height($('.xz2_div1').height());
			// $('.xz3_div1').height($('.xz3_div2').height());
		}
		get_height();
		
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