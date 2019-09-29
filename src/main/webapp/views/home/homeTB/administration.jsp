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
		<link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/css/bootstrap-datetimepicker.min.css"/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/css/bootstrap-theme.min.css"/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/fonts/iconfont.css"/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/css/index_second.css"/>" rel="stylesheet" type="text/css" />
		
		<script src="<c:url value="/js/jquery.min.js"/>" charset="utf-8"></script>
		<style>
			.xz2_div2{
				width: 61%;
    			border-right: none;
			}
			.xz1_div4 {
				width:100%;
			}
			.xz1_2 {
			    width: 73.7%;
			}
			.xz2_div11>p>span{
				padding: 0 10px;
			}
			@media screen and (max-width:1530px){
				.xz1_2{
				    width: 73.6%;
				}
			}
		</style>
</head>
<body onload="initData();">
	<section class="title">
		<h3>行政办公</h3>
		<p>
			机构选择：
			<select class="select_com" style="width:15em" name="orgID" id="org">
				<c:forEach items="${sessionScope.nextOrgList}" var="result">
					<option value="${result[0]}">${result[1]}</option>
				</c:forEach>
			</select>
			&nbsp;&nbsp;
			<%-- <span id="org">${sessionScope.nextOrgList[0][1]}</span> --%>
			时间选择：
			<input class="form-control" size="16" type="text" value="" id="dateselect" readonly>
			<input type="button" class="title_btn" onclick="changOrgAndDate()" value="查询">
		</p>
		<input type="hidden" id="dtp_input2" value="" />
		<%-- <p class="select_date">
			单位选择：<select id="org" class="select_com" onchange="changOrg()">
				<c:forEach items="${sessionScope.nextOrgList}" var="result">
					<option value="${result[0]}">${result[1]}</option>
				</c:forEach>
			<select>
			&nbsp;&nbsp;
			<span>${sessionScope.nextOrgList[0][1]}</span>
			时间选择：<input class="form-control" size="16" id="queryTime" type="text" value="" readonly>
			<input type="hidden" id="dtp_input2" value="" />
			<a></a>
		</p> --%>
	</section>
	
	<section class="xz3">
		<div class="module_div xz3_1" id="document">
			<div class="module_head">
				<span>公文审批及时率</span>
				<span></span>
			</div>
			<div class="module_body">
				<div class="xz3_div1">
					<p class="ll2_per1 font_g">0<span>%</span></p>
					<p class="xz3_clear"></p>
					<p class="xz3_p1">公文总量：<span class="font_g">0</span></p>
					<p class="xz3_p1">超时公文总量：<span class="font_r">0</span></p>
					<p class="xz3_p2">平均公文流转时间(h/份): <span>0.00</span></p>
					<p class="xz3_p2">平均流转节点(个)：<span>0.00</span></p>
				</div>
				<div class="xz3_div2">
					<table>
						<tr>
							<th>序号</th>
							<th>机构名称</th>
							<th>公文总量</th>
							<th>超市公文总量</th>
							<th>公文审批及时率</th>
							<th>平均公文流转时间(小时)</th>
							<th>平均流转节点(个)</th>
						</tr>
						<tr class="tr_choosed">
							<td>1</td>
							<td>海航实业</td>
							<td>-</td>
							<td>-</td>
							<td class="font_r">-</td>
							<td>-</td>
							<td>-</td>
						</tr>
						<tr>
							<td>2</td>
							<td>物流总部</td>
							<td>-</td>
							<td>-</td>
							<td class="font_r">-</td>
							<td>-</td>
							<td>-</td>
						</tr>
						<tr>
							<td>3</td>
							<td>海航基础</td>
							<td>-</td>
							<td>-</td>
							<td class="font_r">-</td>
							<td>-</td>
							<td>-</td>
						</tr>
						<tr>
							<td>4</td>
							<td>供销大集</td>
							<td>-</td>
							<td>-</td>
							<td class="font_g">-</td>
							<td>-</td>
							<td>-</td>
						</tr>
						<tr>
							<td>5</td>
							<td>海航投资</td>
							<td>-</td>
							<td>-</td>
							<td class="font_g">-</td>
							<td>-</td>
							<td>-</td>
						</tr>
						<tr>
							<td>6</td>
							<td>教育医疗</td>
							<td>-</td>
							<td>-</td>
							<td class="font_g">-</td>
							<td>-</td>
							<td>-</td>
						</tr>
						<tr>
							<td>7</td>
							<td>实业国际</td>
							<td>-</td>
							<td>-</td>
							<td class="font_g">-</td>
							<td>-</td>
							<td>-</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="xz3_chart">
				<!-- <img src="img/xz_chart4.png" height="855" width="1354" alt=""> -->
			</div>
		</div>
	</section>
	
	<section class="xz1">
		<div class="module_div xz1_1" id="adSupervise">
			<div class="module_head" >
				<span>
					行政督办办结率
				</span>
				<span>
					
				</span>
			</div>
			<div class="module_body">
				<p class="ll2_per1 font_g">0<span>%</span></p>
				<div class="xz1_div1">
					<div>
						<p>当年总督办任务数</p>
						<p class="font_g">0</p>
					</div>
					<div>
						<p>推进中任务督办数</p>
						<p class="font_r">0</p>
					</div>
					<div>
						<p>办结任务督办数</p>
						<p class="font_g">0</p>
					</div>
				</div>
				<div id="superviseEcharts">
					<!-- <img src="img/xz_chart1.png" height="178" width="100%" alt=""> -->
				</div>
			</div>
		</div>
		<div class="module_div xz1_2" id="riskevent">
			<div class="module_head">
				<span>
					保密事件数
				</span>
				<span>
					
				</span>
			</div>
			<div class="module_body">
				<div class="xz1_div2">
					<p class="ll2_per1 font_r">1</p>
					<table>
						<tr>
							<th>序号</th>
							<th>机构名称</th>
							<th>第一季度</th>
							<th>第二季度</th>
							<th>第三季度</th>
							<th>第四季度</th>
						</tr>
						<tr class="tr_choosed">
							<td>1</td>
							<td>海航实业</td>
							<td>-</td>
							<td>-</td>
							<td>-</td>
							<td></td>
						</tr>
						<tr>
							<td>2</td>
							<td>物流总部</td>
							<td>-</td>
							<td>-</td>
							<td>-</td>
							<td></td>
						</tr>
						<tr>
							<td>3</td>
							<td>海航基础</td>
							<td>-</td>
							<td>-</td>
							<td>-</td>
							<td></td>
						</tr>
						<tr>
							<td>4</td>
							<td>供销大集</td>
							<td>-</td>
							<td>-</td>
							<td>-</td>
							<td></td>
						</tr>
						<tr>
							<td>5</td>
							<td>海航投资</td>
							<td>-</td>
							<td>-</td>
							<td>-</td>
							<td></td>
						</tr>
						<tr>
							<td>6</td>
							<td>教育医疗</td>
							<td>-</td>
							<td>-</td>
							<td>-</td>
							<td></td>
						</tr>
						<tr>
							<td>7</td>
							<td>实业国际</td>
							<td>-</td>
							<td>-</td>
							<td>-</td>
							<td></td>
						</tr>
					</table>
				</div>
				<div class="xz1_div3">
					<p>事件内容</p>
				</div>
			</div>
		</div>
	</section>
	<section class="xz2">
		<div class="module_div xz2_1" id="important">
			<div class="module_head">
				<span>要情处理及时率</span>
				<span></span>
			</div>
			<div class="module_body">
				<div class="xz2_div1">
					<p class="ll2_per1 font_r">0<span>%</span></p>
					<div class="xz2_div11">
						<p>要情总数量<span class="font_r">0</span></p>
						<p>及时上报数量<span class="font_r">0</span></p>
					</div>
					<table>
						<tr>
							<th>序号</th>
							<th>机构名称</th>
							<th>要情数量</th>
							<th>及时上报数量</th>
							<th>要情处理及时率</th>
						</tr>
						<tr>
							<td>1</td>
							<td>海航实业</td>
							<td>-</td>
							<td>-</td>
							<td class="font_r">-</td>
						</tr>
						<tr>
							<td>2</td>
							<td>物流总部</td>
							<td>-</td>
							<td>-</td>
							<td class="font_r">-</td>
						</tr>
						<tr>
							<td>3</td>
							<td>海航基础</td>
							<td>-</td>
							<td>-</td>
							<td class="font_g">-</td>
						</tr>
						<tr>
							<td>4</td>
							<td>供销大集</td>
							<td>-</td>
							<td>-</td>
							<td>-</td>
						</tr>
						<tr>
							<td>5</td>
							<td>海航投资</td>
							<td>-</td>
							<td>-</td>
							<td>-</td>
						</tr>
						<tr>
							<td>6</td>
							<td>教育医疗</td>
							<td>-</td>
							<td>-</td>
							<td>-</td>
						</tr>
						<tr>
							<td>7</td>
							<td>实业国际</td>
							<td>-</td>
							<td>-</td>
							<td>-</td>
						</tr>
					</table>
				</div>
				<div class="xz2_div2" id="importantEcharts1">
					<!-- <img src="img/xz_chart2.png" height="340" width="100%" alt=""> -->
				</div>
				<div class="xz2_div3">
					<!-- <img src="img/xz_chart3.png" height="340" width="100%" alt=""> -->
				</div>
			</div>
		</div>
	</section>
	
	<jsp:include page="../../system/modal.jsp"/>
	
	<script src="<c:url value="/js/echart/echarts.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/bootstrap.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/bootstrap-datetimepicker.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/js/bootstrap-datetimepicker.zh-CN.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/views/home/homeTB/js/administration.js"/>" charset="utf-8"></script>
	<script type="text/javascript">
		var basePath = '${pageContext.request.contextPath}';
		var orderFlg = '${sessionScope.bimShow_orderFlg}';
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
	    
	    
	    /* var pre_date;
	    function show(){
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

	   show(); */
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
	    /* function show(){
		    var mydate = new Date();
		    var str = "" + mydate.getFullYear() + "年";
		    str += ("0"+(mydate.getMonth())).slice(-2) + "月";
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
	    $('.form-control').datetimepicker('update',new Date()); */
		// 设置part1高度统一
		function get_height(){
			var h1 =$('.xz1_1').height();
			var h2 =$('.xz1_2').height();
			var h3 = Math.max(h1,h2);
			$('.xz1_1').height(h3);
			$('.xz1_2').height(h3);
			$('.xz2_div2').height($('.xz2_div1').height());
			$('.xz2_div3').height($('.xz2_div1').height());
			$('.xz3_div1').height($('.xz3_div2').height());
		}
		get_height();
		var queryTime = $('.form-control').val();
		$('.form-control').change(function(){
			queryTime = $(this).val();
			//initData();
		});
		
		 //下达指令
	   //typeid 模块序号
	   //mapId 查询具体指标或者表序号 参考def_task
		function sendorder(typeid,mapId,charttitle){
			var url='${pageContext.request.contextPath}'+"/task/instruction/_add?type="+typeid+"&mapId="+mapId+"&taskName="+(charttitle).replace(/\%/g,'%25');
			mload(url);       
		}
		
		function changOrgAndDate(){
			initData();
		}
	</script>
</body>
</html>
