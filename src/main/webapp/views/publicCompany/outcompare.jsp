<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=1580, user-scalable=no"/>
	<title>上市公司数据分析</title>
	<link rel="stylesheet" type="text/css" href="../css/frame.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/bimf_second.css"/>">
	<link href="<c:url value="/css/bootstrap-datetimepicker.min.css"/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value="/css/iconfont.css"/>" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="<c:url value="/css/scroll/jquery.mCustomScrollbar.css"/>" />
	<style type="text/css">
	
	.tittle {
    text-align: left;
    font-weight: 900;
    line-height: 65px;
    color: #0efefb;
    font-size: 32px;
    display: inline-block;
}
	
	
	.dropdown-menu {
	    position: fixed;
	    top: 140px !important;
	    left: 0;
	    z-index: 1000;
	    display: none;
	    float: left;
	    min-width: 160px;
	    padding: 5px 0;
	    margin: 2px 0 0;
	    font-size: 14px;
	    text-align: left;
	    list-style: none;
	    background-color: #fff;
	    -webkit-background-clip: padding-box;
	    background-clip: padding-box;
	    border: 1px solid #ccc;
	    border: 1px solid rgba(0,0,0,.15);
	    border-radius: 4px;
	    -webkit-box-shadow: 0 6px 12px rgba(0,0,0,.175);
	    box-shadow: 0 6px 12px rgba(0,0,0,.175);
	}
	.managecompany{
		display:flex;
		align-items: stretch;
	}
	.managecompany.line{
		border-top: 1px solid #e6e6e6;
		padding-top:12px;
	}
	.inner2{
		float:none;
		display:block;
		height:auto;
		border-top:0;
	}
	.cash1_text{
		text-align:right;
	}
	</style>
	<style media="screen">
	.dchart1 .flex{
		display: flex;
		justify-content: center;
		align-items: center;
	}
	.dchart1 .flex-start{
		justify-content: flex-start;
	}
	.dchart1 .flex-grow{
		flex-grow: 1;
	}
	.dchart1 .flex-stretch{
		align-items: stretch;
	}

	.dchart1, .dchart1 *{ box-sizing: content-box;}
	.dcc1{ background-color:#bec0cc; }
	.dcmc1.dctmark:before{ background-color:#bec0cc; }
	.dcc2{ background-color:#ffb981; }
	.dcmc2.dctmark:before{ background-color:#ffb981; }

	.dctmark:before{
		content:'';
		width:9px;
		height:9px;
		display: inline-block;
		vertical-align: middle;
		border-radius: 50%;
		margin-right:6px;
	}
	.dctype{
		margin-top: 8px;
	}
	.dctype>*{
		margin: 0 12px;
	}
	.dchart1{
		width: 400px;
		padding:0 40px;
		color:#ddd;
		display: flex;
		flex-direction: column;
		align-items:stretch;white-space: nowrap;
	}
	.dcline{ position: relative; padding-bottom: 12px; }
	.dcline:before{
		left: 50%;
		content:'';
		width:1px;
		position: absolute;
		top:8px;
		bottom:0;
		background-color: #e1819d;
	}
	.dcinfo{  }
	.dcinfo>*{ padding: 2px 8px; }
	.dcinfo>:nth-child(1){ width: 50%; text-align: right; color: #ccc; }
	.dcinfo>:nth-child(2){ width: 50%; text-align: left; color: #ccc; }
	.dcitem{ margin-bottom:4px;height:20px;line-height: 20px;}
	.dcitem>*{}
	.dcitem>:nth-child(1){ width: 50%;text-align: left; color: #fff; display: flex; justify-content: flex-end;}
	.dcitem>:nth-child(2){ width: 50%;text-align: right; color: #fff; display: flex; justify-content: flex-start;}

	.dcil{
		position:relative;
		height: 100%;
		background-color: #bec0cc;
	}
	.dcir{
		position:relative;
		height: 100%;
		background-color: #fdb980;
	}

	.dcicompare{  display: inline-blcok; font-size: 16px; margin-left: 8px; }
	.dcicompare.up{ color: #2dd664; }
	.dcicompare.down{ color: #d43640; }
	.dcicompare.up:after{
		content:'';
		display: inline-block;
		margin-left: 4px;
		border-left: 8px solid rgba(0,0,0,0);
		border-right: 8px solid rgba(0,0,0,0);
		border-bottom: 10px solid #2dd664;
	}
	.dcicompare.down:after{
		content:'';
		display: inline-block;
		margin-left: 4px;
		border-left: 8px solid rgba(0,0,0,0);
		border-right: 8px solid rgba(0,0,0,0);
		border-top: 10px solid #d43640;
	}
	.dcvleft{
		position: absolute;
		right:100%;
	}
	.dcvright{
		position: absolute;
		left:100%;
	}
	.dcvalue{
		padding:0 8px;
	}
	</style>
	<style media="screen">
	.dchart2 .flex{
		display: flex;
		justify-content: center;
		align-items: center;
	}
	.dchart2 .flex-start{
		justify-content: flex-start;
	}
	.dchart2 .flex-grow{
		flex-grow: 1;
	}
	.dchart2 .flex-stretch{
		align-items: stretch;
	}

	.dchart2, .dchart2 *{ box-sizing: content-box;}
	.dc2c1{ background-color:#ffb981; }
	.dc2mc1.dctmark:before{ background-color:#ffb981; }
	.dc2c2{ background-color:#a4dee0; }
	.dc2mc2.dctmark:before{ background-color:#a4dee0; }

	.dchart2 .dctmark:before{
		content:'';
		width:9px;
		height:9px;
		display: inline-block;
		vertical-align: middle;
		border-radius: 50%;
		margin-right:6px;
	}
	.dchart2 .dctype{
		margin-top: 8px;
	}
	.dchart2 .dctype>*{
		margin: 0 12px;
	}
	.dchart2{
		padding-right:60px;
		color:#ddd;
		display: flex;
		flex-direction: column;
		align-items:stretch;
		white-space: nowrap;
	}

	.dchart2 .dcicompare{ position: absolute; right:0; font-size: 16px; margin-left: 8px; }
	.dchart2 .dcicompare.up{ color: #2dd664; }
	.dchart2 .dcicompare.down{ color: #d43640; }
	.dchart2 .dcicompare.up:after{
		content:'';
		display: inline-block;
		margin-left: 4px;
		border-left: 8px solid rgba(0,0,0,0);
		border-right: 8px solid rgba(0,0,0,0);
		border-bottom: 10px solid #2dd664;
	}
	.dchart2 .dcicompare.down:after{
		content:'';
		display: inline-block;
		margin-left: 4px;
		border-left: 8px solid rgba(0,0,0,0);
		border-right: 8px solid rgba(0,0,0,0);
		border-top: 10px solid #d43640;
	}

	.dc2item:nth-child(n+2){ margin-top:30px; }
	.dc2item>*{ text-align:left;position: relative; line-height: 20px;}
	.dc2item>:not(.dc2ititle){background-color: rgba(255,255,255,0.2);}
	.dc2item>:not(.dc2ititle)>*{height:20px}
	.dc2item>:nth-child(n+2){ margin-top:10px; }
	.dc2item .num{
		display: block;
		position: absolute;
		font-style: normal;
		top:0;
		left:calc(100% + 4px);
		color: #fff;
	}
	.datetimepicker.quarter .month {
	    width: 48%;
	}
	.datetimepicker.quarter .month:nth-child(n+5){
	    display: none;
	}
</style>
<style>
	.flex{ display:flex; align-items:center; }
	.c2bar{position:relative;width:50%;height:14px;background-color:rgba(255,255,255,0.2);color:#ddd}
	.c2barl{position:absolute;right:0;height:14px;background-color:#328dcb;}
	.c2barr{position:absolute;left:0;height:14px;background-color:#ff7000;}
	.c2barlt{position:absolute;left:6px;font-size:12px;line-height:14px;}
	.c2barrt{position:absolute;right:6px;font-size:12px;line-height:14px;}
</style>
<style media="screen">
		i.arrow-up{
			display: inline-block;
			width: 18px;
			height: 18px;
			background-image: url(<c:url value="/views/finance/bimf_second/font/arrow-up.svg"/>);
			background-repeat: no-repeat;
			background-position: center;
		}
		i.arrow-down{
			display: inline-block;
			width: 18px;
			height: 18px;
			background-image: url(<c:url value="/views/finance/bimf_second/font/arrow-down.svg"/>);
			background-repeat: no-repeat;
			background-position: center;
		}
		.mgsy,.mgjzc{
			position: relative;
		}
		.mgsy>p,.mgjzc>p{
		    position: absolute;
		    z-index: 999;
		    display: inline-block;
		    font-size: 12px;
		    background: rgba(0,0,0,0.2);
		    text-align: center;
		    width: auto !important;
		    padding: 5px 10px;
		    top: -40px;
		    right: 20px;
		    border-radius: 5px;
		    display: none;
		}
		 .top_switch {
   			 display: inline-block;
  			  position: absolute;
    		right: 2%;
    		line-height: 65px;
		}
		 .top_switch span {
    		background: #0b1c47;
    		border: 1px solid #099cb4;
    		padding: 9px 34px;
    		border-radius: 5px;
		}
		 .top_switch img {
   			 vertical-align: middle;
    		margin-left: 10px;
		}
	</style>
</head>
<body>

	<section class="head">
		<p>
			<span class="tittle">上市公司数据分析</span>
			<span class="top_switch">
			<span style="cursor: pointer;" onclick="turnToCode()">上市公司股价</span>
			<span >上市公司对标</span>
			<a href="javascript:window.opener=null;window.open('','_self');window.close();"><img style="height: auto; cursor: pointer;"  src="../img/exit.png" ></a>
		    </span>
			<!-- <a class="close" style="color:#ffffff"  href="javascript:window.opener=null;window.open('','_self');window.close();"><i class="iconfont icon-guanbi"></i></a> -->
		</p>
	</section>
	<section class="banner" style="background-image: url(/shanghai-gzw/img/bg_f.png);">
		<p class="banner_right">
		
			<input type="hidden" id="year" value="${year }" />
			<input type="hidden" id="season" value="${season }" />
			<select class="company" id="organl" onchange="publicorgChange();">
					<c:forEach items="${requestScope.Inselectorg}" var="result">
						<option value="${result.publicorg }">${result.publicorgNm }</option>
					</c:forEach>
			</select>
			对比企业
			<select class="company" id="organl2" >
					<c:forEach items="${requestScope.Outselectorg}" var="result">
						<option value="${result.outorg }" <c:if test="${selectedOut == result.outorg}">selected="selected"</c:if>>${result.outorgNm }</option>
					</c:forEach>
			</select>
			<input class="form-control choose_date" size="16" type="text" value="" id="season1" readonly >
			<input type="button" value="按季度" class="search" onclick="query('season')">
			<input class="form-control choose_date" size="16" type="text" value="${year }年" id="year1" readonly >
			<input type="button" value="按年度" class="search" onclick="query('year')">
		</p>
	</section>
	<section class="content" >
		<div>
			<div class="block block1 cash_div11">
				<p class="block_title"><span class="organName"></span>每股指标 <span class="selecttime"></span></p>
				<div class="block_body" style="display: flex;">
					
				<div class="" style="width:30%;">
					<div class="dchart1">
						<div class="dcline fs14">
							<div>
								<div class="flex flex-stretch dcinfo">
									<p w50p></p><p w50p>基本每股收益(元)</p>
								</div>
								<div class="flex flex-stretch dcitem">
									<div class="mgsy">
										<p>
											海航基础：<span name="publicorgname_1_a00449"></span>元<br>
											长江和记：<span name="outorgname_1_a00449"></span>元
										</p>
										<div class="dcil" style="width:">
											<div class="dcvleft">
												<span class="dcvalue"  norod name="publicorgname_1_a00449"></span>
											</div>
										</div>
									</div>
									<div>
										<div class="dcir" style="width:">
											<div class="dcvright">
												<span class="dcvalue"  norod name="outorgname_1_a00449"></span>
												<span class="dcicompare " id="compare_a00449_1"></span>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div>
								<div class="flex flex-stretch dcinfo">
									<p w50p>扣非每股收益(元)</p><p w50p></p>
								</div>
								<div class="flex flex-stretch dcitem">
									<div>
										<div class="dcil" style="width:">
											<div class="dcvleft">
												<span class="dcvalue" norod name="publicorgname_1_a00450"></span>
											</div>
										</div>
									</div>
									<div>
										<div class="dcir" style="width:">
											<div class="dcvright">
												<span class="dcvalue" norod name="outorgname_1_a00450"></span>
												<span class="dcicompare " id="compare_a00450_1"></span>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div>
								<div class="flex flex-stretch dcinfo">
									<p w50p></p><p w50p>每股净资产(元)</p>
								</div>
								<div class="flex flex-stretch dcitem">
									<div class="mgjzc">
										<p>
											海航基础：<span name="publicorgname_1_a00451"></span>元<br>
											长江和记：<span name="outorgname_1_a00451"></span>元
										</p>
										<div class="dcil" style="width:">
											<div class="dcvleft">
												<span  class="dcvalue" norod name="publicorgname_1_a00451"></span>
											</div>
										</div>
									</div>
									<div>
										<div class="dcir" style="width:">
											<div class="dcvright">
												<span  class="dcvalue"  norod name="outorgname_1_a00451"></span>
												<span class="dcicompare " id="compare_a00451_1"></span>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="flex dctype">
							<div class="dctmark dcmc1" name="publicorgname"></div>
							<div class="dctmark dcmc2" name="outorgname"></div>
						</div>
					</div>
				</div>
					<div class="" style="width:70%">
							<table >
								<tr >
									<td>每股指标</td><td name="publicorgname_1"></td><td name="outorgname_1"></td><td>偏离值</td><td name="publicorgname_2"></td><td name="outorgname_2"></td><td>偏离值</td><td name="publicorgname_3"></td><td name="outorgname_3"></td><td>偏离值</td>
								</tr>
								<tr >
									<td>基本每股收益（元）</td><td name="publicorgname_1_a00449"></td><td name="outorgname_1_a00449"></td><td name="compare"></td><td name="publicorgname_2_a00449"></td><td name="outorgname_2_a00449"></td><td name="compare"></td><td name="publicorgname_3_a00449"></td><td name="outorgname_3_a00449"></td><td name="compare"></td>
								</tr>
								<tr >
									<td>扣非每股收益（元）</td><td name="publicorgname_1_a00450"></td><td name="outorgname_1_a00450"></td><td name="compare"></td><td name="publicorgname_2_a00450"></td><td name="outorgname_2_a00450"></td><td name="compare"></td><td name="publicorgname_3_a00450"></td><td name="outorgname_3_a00450"></td><td name="compare"></td>
								</tr>
								<tr >
									<td>每股净资产（元）</td><td name="publicorgname_1_a00451"></td><td name="outorgname_1_a00451"></td><td name="compare"></td><td name="publicorgname_2_a00451"></td><td name="outorgname_2_a00451"></td><td name="compare"></td><td name="publicorgname_3_a00451"></td><td name="outorgname_3_a00451"></td><td name="compare"></td>
								</tr>
							</table>
					</div>
				</div>
				
			</div>
		</div>
		
		<div>
			<div class="block block1 cash_div11">
				<p class="block_title"><span class="organName"></span>成长能力指标  <span class="selecttime"></span></p>
				<div class="block_body" style="display: flex;">
					<div style="width:34%">
						<table>
							<tr >
								<td>成长能力指标</td><td name="publicorgname_1"></td><td name="outorgname_1"></td><td>偏离值</td>
							</tr>
							<tr >
								<td>营业总收入（亿）</td><td  name="publicorgname_1_a00452"></td><td  name="outorgname_1_a00452"></td><td name="compare"></td>
							</tr>
							<tr >
								<td>毛利润（亿）</td><td  name="publicorgname_1_a00453"></td><td  name="outorgname_1_a00453"></td><td name="compare"></td>
							</tr>
							<tr >
								<td>归属净利润（亿）</td><td  name="publicorgname_1_a00454"></td><td  name="outorgname_1_a00454"></td><td name="compare"></td>
							</tr>
							<tr >
								<td>扣非净利润（亿）</td><td  name="publicorgname_1_a00455"></td><td  name="outorgname_1_a00455"></td><td name="compare"></td>
							</tr>
							<tr >
								<td>收入同比增长（%）</td><td  name="publicorgname_1_a00456"></td><td  name="outorgname_1_a00456"></td><td name="compare"></td>
							</tr>
							<tr >
								<td>归属净利润同比增长（%）</td><td  name="publicorgname_1_a00457"></td><td  name="outorgname_1_a00457"></td><td name="compare"></td>
							</tr>
							<tr >
								<td>收入滚动环比增长（%）</td><td  name="publicorgname_1_a00458"></td><td  name="outorgname_1_a00458"></td><td name="compare"></td>
							</tr>
						
						</table>
					</div>
					<div style="width:33%;text-align: center" id="cznlzb_1">
						<div id="echart_1" style="width:100%;height:100%">
						
						
						
						</div>
					</div>
					<div style="width:34%;text-align: center;margin-left: 20px" id="cznlzb_2">
						<div class="dchart2">
							
							<div class="fs14 ">
								<div class="dc2item">
									<p class="dc2ititle">收入同比增长（%）<span class="dcicompare" id="horizontal_a00456_1"></span></p>
									<div><div class="dc2c1" style="width:"></div><i class="num" norod name="publicorgname_1_a00456"></i></div>
									<div><div class="dc2c2" style="width:"></div><i class="num" norod name="outorgname_1_a00456"></i></div>
								</div>
								<div class="dc2item">
									<p class="dc2ititle">归属净利润同比增长（%）<span class="dcicompare" id="horizontal_a00457_1"></span></p>
									<div><div class="dc2c1" style="width:"></div><i class="num" norod name="publicorgname_1_a00457"></i></div>
									<div><div class="dc2c2" style="width:"></div><i class="num" norod name="outorgname_1_a00457"></i></div>
								</div>
								<div class="dc2item">
									<p class="dc2ititle">收入滚动环比增长（%）<span class="dcicompare" id="horizontal_a00458_1"></span></p>
									<div><div class="dc2c1" style="width:"></div><i class="num" norod name="publicorgname_1_a00458"></i></div>
									<div><div class="dc2c2" style="width:"></div><i class="num" norod name="outorgname_1_a00458"></i></div>
								</div>
							</div>
							<div class="flex dctype">
								<div class="dctmark dcmc1" name="publicorgname"></div>
								<div class="dctmark dcmc2" name="outorgname"></div>
							</div>
						</div>
					</div>
					
				</div>
			
			</div>
		</div>
		<div>
			<div class="block block1 cash_div11">
				<p class="block_title"><span class="organName"></span>盈利能力指标  <span class="selecttime"></span></p>
				<div class="block_body" style="display: flex;">
					
					<div class="" style="width:25%;text-align: center">
							<div class="dchart2">
							
							<div class="fs14 ">
								<div class="dc2item">
									<p class="dc2ititle">净资产收益率（%）<span class="dcicompare" id="horizontal_a00459_1"></span></p>
									<div><div class="dc2c1" style="width:"></div><i class="num" norod name="publicorgname_1_a00459"></i></div>
									<div><div class="dc2c2" style="width:"></div><i class="num" norod name="outorgname_1_a00459"></i></div>
								</div>
								<div class="dc2item">
									<p class="dc2ititle">毛利率（%）<span class="dcicompare" id="horizontal_a00460_1"></span></p>
									<div><div class="dc2c1" style="width:"></div><i class="num" norod name="publicorgname_1_a00460"></i></div>
									<div><div class="dc2c2" style="width:"></div><i class="num" norod name="outorgname_1_a00460"></i></div>
								</div>
								<div class="dc2item">
									<p class="dc2ititle">净利率（%）<span class="dcicompare" id="horizontal_a00461_1"></span></p>
									<div><div class="dc2c1" style="width:"></div><i class="num" norod name="publicorgname_1_a00461"></i></div>
									<div><div class="dc2c2" style="width:"></div><i class="num" norod name="outorgname_1_a00461"></i></div>
								</div>
							</div>
							<div class="flex dctype">
								<div class="dctmark dcmc1" name="publicorgname"></div>
								<div class="dctmark dcmc2" name="outorgname"></div>
							</div>
						</div>
					</div>
					<div class="" style="width:75%">
							<table >
								<tr >
									<td>盈利能力指标</td><td name="publicorgname_1"></td><td name="outorgname_1"></td><td>偏离值</td><td name="publicorgname_2"></td><td name="outorgname_2"></td><td>偏离值</td><td name="publicorgname_3"></td><td name="outorgname_3"></td><td>偏离值</td>
								</tr>
								<tr >
									<td>净资产收益率（%）</td><td name="publicorgname_1_a00459"></td><td name="outorgname_1_a00459"></td><td name="compare"></td><td name="publicorgname_2_a00459"></td><td name="outorgname_2_a00459"></td><td name="compare"></td><td name="publicorgname_3_a00459"></td><td name="outorgname_3_a00459"></td><td name="compare"></td>
								</tr>
								<tr >
									<td>毛利率（%）</td><td name="publicorgname_1_a00460"></td><td name="outorgname_1_a00460"></td><td name="compare"></td><td name="publicorgname_2_a00460"></td><td name="outorgname_2_a00460"></td><td name="compare"></td><td name="publicorgname_3_a00460"></td><td name="outorgname_3_a00460"></td><td name="compare"></td>
								</tr>
								<tr >
									<td>净利率（%）</td><td name="publicorgname_1_a00461"></td><td name="outorgname_1_a00461"></td><td name="compare"></td><td name="publicorgname_2_a00461"></td><td name="outorgname_2_a00461"></td><td name="compare"></td><td name="publicorgname_3_a00461"></td><td name="outorgname_3_a00461"></td><td name="compare"></td>
								</tr>
							</table>
					</div>
				</div>
				
			</div>
		
			
		</div>
		<div>
			<div class="block block1 cash_div11">
				<p class="block_title"><span class="organName"></span>运营能力指标  <span class="selecttime"></span></p>
				<div class="block_body" style="display: flex;">
					
					
					<div class="" style="width:75%">
							<table >
								<tr >
									<td>运营能力指标</td><td name="publicorgname_1"></td><td name="outorgname_1"></td><td>偏离值</td><td name="publicorgname_2"></td><td name="outorgname_2"></td><td>偏离值</td><td name="publicorgname_3"></td><td name="outorgname_3"></td><td>偏离值</td>
								</tr>
								<tr >
									<td>总资产周转率（%）</td><td name="publicorgname_1_a00462"></td><td name="outorgname_1_a00462"></td><td name="compare"></td><td name="publicorgname_2_a00462"></td><td name="outorgname_2_a00462"></td><td name="compare"></td><td name="publicorgname_3_a00462"></td><td name="outorgname_3_a00462"></td><td name="compare"></td>
								</tr>
								<tr >
									<td>存货周转天数（%）</td><td name="publicorgname_1_a00463"></td><td name="outorgname_1_a00463"></td><td name="compare"></td><td name="publicorgname_2_a00463"></td><td name="outorgname_2_a00463"></td><td name="compare"></td><td name="publicorgname_3_a00463"></td><td name="outorgname_3_a00463"></td><td name="compare"></td>
								</tr>
								<tr >
									<td>应收账款天数（天）</td><td name="publicorgname_1_a00464"></td><td name="outorgname_1_a00464"></td><td name="compare"></td><td name="publicorgname_2_a00464"></td><td name="outorgname_2_a00464"></td><td name="compare"></td><td name="publicorgname_3_a00464"></td><td name="outorgname_3_a00464"></td><td name="compare"></td>
								</tr>
							</table>
					</div>
					<div class="" style="width:25%;text-align: center" >
						
						<div class="flex" style="padding-left:20px;white-space:nowrap;" id="ZCZZL">
							<div class="c2bar">
								<div class="c2barl" style="width:"></div>
								<span class="c2barlt" norod name="publicorgname_1_a00462"></span>
							</div>
							<p style="padding:0 8px;margin:0;">总资产周转率</p>
							<div class="c2bar">
								<div class="c2barr" style="width:" ></div>
								<span class="c2barrt" norod name="outorgname_1_a00462"></span>
							</div>
						</div>
						<div id="echart_2" style="width:100%;height:100%">
						
						
						
						</div>
					</div>
				</div>
				
			</div>
		
			
		</div>
		
		<div>
			<div class="block block1 cash_div11">
				<p class="block_title"><span class="organName"></span>财务风险指标  <span class="selecttime"></span></p>
				<div class="block_body" style="display: flex;">
					<div class="" style="width:30%">
						<div class="dchart1">
						<div class="dcline fs14">
							<div>
								<div class="flex flex-stretch dcinfo">
									<p w50p></p><p w50p>资产负债率(%)</p>
								</div>
								<div class="flex flex-stretch dcitem">
									<div>
										<div class="dcil" style="width:">
											<div class="dcvleft">
												<span class="dcvalue"  norod name="publicorgname_1_a00465"></span>
											</div>
										</div>
									</div>
									<div>
										<div class="dcir" style="width:">
											<div class="dcvright">
												<span class="dcvalue"  norod name="outorgname_1_a00465"></span>
												<span class="dcicompare " id="compare_a00465_1"></span>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div>
								<div class="flex flex-stretch dcinfo">
									<p w50p>流动比率(%)</p><p w50p></p>
								</div>
								<div class="flex flex-stretch dcitem">
									<div>
										<div class="dcil" style="width:">
											<div class="dcvleft">
												<span class="dcvalue" norod name="publicorgname_1_a00466"></span>
											</div>
										</div>
									</div>
									<div>
										<div class="dcir" style="width:">
											<div class="dcvright">
												<span class="dcvalue" norod name="outorgname_1_a00466"></span>
												<span class="dcicompare " id="compare_a00466_1"></span>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="flex dctype">
							<div class="dctmark dcmc1" name="publicorgname"></div>
							<div class="dctmark dcmc2" name="outorgname"></div>
						</div>
					</div>	
					</div>
					<div class="" style="width:70%">
							<table >
								<tr >
									<td>财务风险指标</td><td name="publicorgname_1"></td><td name="outorgname_1"></td><td>偏离值</td><td name="publicorgname_2"></td><td name="outorgname_2"></td><td>偏离值</td><td name="publicorgname_3"></td><td name="outorgname_3"></td><td>偏离值</td>
								</tr>
								<tr >
									<td>资产负债率（%）</td><td name="publicorgname_1_a00465"></td><td name="outorgname_1_a00465"></td><td name="compare"></td><td name="publicorgname_2_a00465"></td><td name="outorgname_2_a00465"></td><td name="compare"></td><td name="publicorgname_3_a00465"></td><td name="outorgname_3_a00465"></td><td name="compare"></td>
								</tr>
								<tr >
									<td>流动比率（%）</td><td name="publicorgname_1_a00466"></td><td name="outorgname_1_a00466"></td><td name="compare"></td><td name="publicorgname_2_a00466"></td><td name="outorgname_2_a00466"></td><td name="compare"></td><td name="publicorgname_3_a00466"></td><td name="outorgname_3_a00466"></td><td name="compare"></td>
								</tr>
								
							</table>
					</div>
					
				</div>
				
			</div>
			
		</div>
		
	</section>
	
	<script src="<c:url value="/js/jquery.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/echarts.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/bootstrap-datetimepicker.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/bootstrap-datetimepicker.zh-CN.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/outcompare.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/mCustomScrollbar.min.js"/>" charset="utf-8"></script>
	<style>
		.mCSB_dragger_bar{
			background-color: rgb(23, 177, 247) !important;
		}
		#mCSB_1_scrollbar_vertical{
			opacity: 1;
		}
	</style>
	<jsp:include page="../publicCompany/backgroud.sky.jsp"/>
	<jsp:include page="../publicCompany/modal.jsp"/>
	<script type="text/javascript">
		var basePath = '${pageContext.request.contextPath}';
		// 高度统一
		function setHeight(){
			var h1 = $('.cash_div11').height();
			var h2 = $('.cash_div12').height();
			var h3 = $('.cash_div13').height();
			h1 = Math.max(h1,h2,h3);
			var h4 = $('.cash_div21').height();
			var h5 = $('.cash_div22').height();
			h4 = Math.max(h4,h5);
			$('.cash_div11,.cash_div12,.cash_div13').height(h1);
			$('.cash_div21,.cash_div22').height(h4);
		}
		
		
		
		//日历控件
		$('#year1').datetimepicker({
			format: 'yyyy年',
		    language:  'zh-CN',
		    weekStart: 1,
		    todayBtn:  0,
			autoclose: 1,
			todayHighlight: 1,
			startView: 4,
			minView: 4,
			forceParse: 0
		});
		
		$.fn.datetimepicker.dates['zh-CN-qtrs'] = {
		    days: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"],
		    daysShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六", "周日"],
		    daysMin:  ["日", "一", "二", "三", "四", "五", "六", "日"],
		    months: ["一季度", "二季度", "三季度", "四季度", "", "", "", "", "", "", "", ""],
		    monthsShort: ["一季度", "二季度", "三季度", "四季度", "", "", "", "", "", "", "", ""],
		    clear: "清除",
		    meridiem: ["上午", "下午"]
		};
		
		//初始化选中当前季度
		var date = new Date();
		var year = '${year}';
		var month = '${season}';
		date.setFullYear(Number(year),Number(month)-1, 1);
	
		
	    $('#season1').datetimepicker({
	        clearBtn:true,
	        startView:3,
	        minView:3,
	        autoclose: 1,
	        format:'yyyy年MM',
	        language:'zh-CN-qtrs',
	        forceParse: 0,
	        initialDate: date
	    });
	    $('#season1').data('datetimepicker').picker.addClass('quarter');
	    
	    $('#season1').val('${year}年${upCaseSeason}季度');
	    
	    query('season');
    	
		//$('.form-control').datetimepicker( 'update', pre_date )
	   //下达指令
	   //typeid 模块序号
	   //mapId 查询具体指标或者表序号 参考def_task
		function sendorder(typeid,mapId,charttitle){
			var url='${pageContext.request.contextPath}'+"/task/instruction/_add?type="+typeid+"&mapId="+mapId+"&taskName="+(charttitle).replace(/\%/g,'%25');
			mload(url);       
		}
		$(".mgsy,.mgjzc").on("mouseenter",function(){
			$(this).children("p").css("display","inline-block");
		})
		$(".mgsy,.mgjzc").on("mouseleave",function(){
			$(this).children("p").css("display","none");
		})
		
		/**
		*	内部对标公司切换
		*/
		function publicorgChange(){
			var inData = eval("("+'${inSelectData}'+")");
			var organ1 = $("#organl").val();
			for(var i = 0; i < inData.length; i++ ){
				if(inData[i].publicorg == organ1){
					$("#organl2").val(inData[i].outorg);
				}
			}
		}
		function turnToCode(){
			var org=$("#organl").val();
			var outorg=$("#organl2").val();
			var season = $("#season").val();

			
				var year = $("#year1").val().replace("年", "");
				
				var temp = $("#season1").val().split("年");
				
				var year = temp[0];
				switch(temp[1]){
					case '一季度':
						season = 1;
						break;
					case '二季度':
						season = 2;
						break;
					case '三季度':
						season = 3;
						break;
					case '四季度':
						season = 4;
						break;	
				
				}
			
			window.location.href = '${pageContext.request.contextPath}/outcompare/outCompareCode?year='+year+'&season='+season;
		}
	</script>
	<!-- dark theme -->
	<style type="text/css">
		.dark-block {
			background-color:rgba(0,0,0,0.3);
			border-width:0;
		}
		
		.banner, .block{
			background-color:rgba(0,0,0,0.3);
			border-width:0;
			color:#ddd;
		}
		.block tr:hover{
			background-color: rgba(255,255,255,0.2);
		}
		.banner_right{
			color:#ddd;
		}
		.block{
			position:relative;
		}
	</style>
</body>
</html>