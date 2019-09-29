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
	
	</style>
</head>
<body >
	<section class="title">
		<h3>采购管理</h3>
		<p class="select_date">
		
			时间选择：
			<input class="form-control" size="16" type="text" value="" readonly >
			<input type="hidden" id="dtp_input2" value="" />
			
			<input type="button" class="title_btn" value="查询" onclick="pruchaseInit()">
		</p>
	</section>
	<section class="rl1">
		<!-- <div class="module_div rl1_2">
			<div class="module_head">
				<span>累计完成采购金额</span>
			</div>
			<div class="module_body ">
				<p class="ll2_per1" id="all"></p>
				<div class="cg_div1">
				    <div>
						<div id="relative1" >
							
						</div>
						<p class="cg_p1" id="titlerelative0"></p>
						<p>采购金额环比</p>
					</div>
					<div>
						<div id="relative2" >
							
						</div>
						<p class="cg_p1" id="titlerelative1"></p>
						<p>工程类供应商数据环比</p>
					</div>
					<div>
						<div id="relative3">
							
						</div>
						<p class="cg_p1" id="titlerelative2"></p>
						<p>工程类供应商数据环比</p>
					
				    </div>
			</div>
		</div>
		</div> -->
		<div class="module_div">
			<div class="module_head">
				<span>${organName}累计完成采购金额</span>
				<c:if test="${fn:contains(bimShow_ButtonList, '&bs_sp12&')}">
					<a class="zhiling" onclick="sendorder(3,'SHOWTAB_M16','累计完成采购金额')">+ 指令下达</a>
				</c:if>
				<span id="1" class="showtimespan"></span>
			</div>
			<div class="module_body"  id="moneyamount">
				
			</div>
		</div>
		<div class="module_div">
			<div class="module_head">
				<span>${organName}库内供应商数量</span>
				<c:if test="${fn:contains(bimShow_ButtonList, '&bs_sp12&')}">
					<a class="zhiling" onclick="sendorder(3,'SHOWTAB_M15','库内供应商数量')">+ 指令下达</a>
				</c:if>
				<span class="showtimespan"></span>
			</div>
			<div class="module_body"  id="supply">
				
			</div>
		</div>
		<div class="module_div">
			<div class="module_head ">
				<span>${organName}工程采购成本节约率</span>
				<c:if test="${fn:contains(bimShow_ButtonList, '&bs_sp12&')}">
					<a class="zhiling" onclick="sendorder(3,'SHOWTAB_M17','工程采购成本节约率')">+ 指令下达</a>
				</c:if>
				<span class="showtimespan"></span>
			</div>
			<div class="module_body "  id="saverate">
				
			</div>
		</div>
	</section>
	<jsp:include page="../../system/modal.jsp"/>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/views/home/homeTB/js/purchase.js"></script>

	<script type="text/javascript">
		
	   // 获取当前时间
	     var pre_date;
	     function show(){
		    var mydate = new Date();
		    if(mydate.getUTCMonth()==0){
		    	var str = "" + (mydate.getFullYear()-1) + "年";
			    pre_date = (mydate.getFullYear()-1);
		    }else{
		    	console.log(mydate.getUTCMonth())
			    var str = "" + mydate.getFullYear() + "年";
			    
			    pre_date = mydate.getFullYear();
		    }
		    // console.log(pre_date)
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
			startView: 4,
			minView: 4,
			forceParse: 0,
			format: 'yyyy年'
	    });
	    $('.form-control').datetimepicker( 'update', pre_date )
	    pruchaseInit();
		// 设置part1高度统一
		function get_height(){
			$('.module_div').height($('.rl1_2').height());
			// $('.xz2_div2').height($('.xz2_div1').height());
			// $('.xz2_div3').height($('.xz2_div1').height());
			// $('.xz3_div1').height($('.xz3_div2').height());
		}
		get_height();
		
		
			  //下达指令
	   //typeid 模块序号
	   //mapId 查询具体指标或者表序号 参考def_task
		function sendorder(typeid,mapId,charttitle){
			var url='${pageContext.request.contextPath}'+"/task/instruction/_add?type="+typeid+"&mapId="+mapId+"&taskName="+('${organName}'+$('.form-control').val().slice(0,4)+charttitle).replace(/\%/g,'%25');
			mload(url);       
		}
		
	</script>
</body>
</html>