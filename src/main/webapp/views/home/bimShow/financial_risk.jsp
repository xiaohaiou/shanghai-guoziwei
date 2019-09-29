<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
	<title>financial_risk</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/views/home/bimShow/assets/css/financial_risk.css">
</head>
<body onload="rmInit()">
	<div class="top">
		<h1>财务风险预警</h1>
		<div class="top_select">
			<span>公司名称</span>
			<select onchange=changeData() id="company" name="company">
						      <c:forEach var="financeRisk" items="${list}">
								    <option value="${financeRisk[0]}" <c:if test="${orgID == financeRisk[0]}">selected="selected"</c:if>>${financeRisk[1]}</option> 
							  </c:forEach>
		    </select>
			<span>时间</span>
			<select id="year" name="year" onchange=changeData()>
				<option value ="2018" <c:if test="${year == 2018}">selected="selected"</c:if>>2018年</option>
  				<option value ="2017" <c:if test="${year == 2017}">selected="selected"</c:if>>2017年</option>
  				<option value="2016"  <c:if test="${year == 2016}">selected="selected"</c:if>>2016年</option>
			</select>
			<img src="assets/images/exit.png">
		</div>
	</div>
	<div class="line"></div>
	<div class="container">
		<div class="case">
			<div class="case_title">${orgName}${year}年10月债务风险</div>
			<div id="linechart1" class="linechart"></div>
			<div id="linechart2" class="linechart">2</div>
			<div id="linechart3" class="linechart">3</div>
			<div id="linechart4" class="linechart">4</div>
		</div>
		<div class="case">
			<div class="case_title">${orgName}${year}年10月现金流风险</div>
			<div id="linechart5" class="linechart"></div>
			<div id="linechart6" class="linechart">2</div>
			<div id="linechart7" class="linechart">3</div>
			<div id="linechart8" class="linechart">4</div>
		</div>
		<div class="case">
			<div class="case_title">${orgName}${year}年10月债盈利能力风险</div>
			<div id="linechart9" class="linechart">1</div>
			<div id="linechart10" class="linechart">2</div>
			<div id="linechart11" class="linechart">3</div>
		</div>
		<div class="case">
			<div class="case_title">${orgName}${year}年10月债投资风险</div>
			<div id="linechart12" class="linechart">1</div>
			<div id="linechart13" class="linechart">2</div>
			<div id="linechart14" class="linechart">3</div>
		</div>
		<div class="clearfix" style="height: 35px;"></div>
	</div>
	<script src="${pageContext.request.contextPath}/views/home/bimShow/assets/plugins/jquery/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/views/home/bimShow/assets/plugins/echarts/echarts.js"></script>
	<script type="text/javascript">
	var basePath = '${pageContext.request.contextPath}';
	
	var data1=[];
    var data2=[];
    var data3=[];
    var data4=[];
    var data5=[];
    var data6=[];
    var data7=[];
    var data8=[];
    var data9=[];
    var data10=[];
    var data11=[];
    var data12=[];
    var data13=[];
    var data14=[];
	function rmInit() {
		var org = $("#company").val();
		var year = $("#year").val();
		console.log(org,year)
		 // var org = $("#org").val();
		/*var rmOrgId = $('#orgnmId option:selected').val();
		rmOrgName = $('#orgnmId option:selected').text();*/
		$.ajax({
			url : basePath+"/index/getData?org="+org+"&year="+year,
			type : "POST",
			dataType: "json", 
			success : function(data) {
			/* 	var dataArray = JSON.parse(data); */

				for(var j=1;j<13;j++){
					var flag = true;
					for(var i=0;i<data.length;i++){	
						if(j==data[i].month){
							console.log(999)
							data1.push(data[i].assetLiabilities);
							data2.push(data[i].quickRatio);
							data3.push(data[i].netAssetsRatio);
							data4.push(data[i].interestMultiplier);
							data5.push(data[i].cashTotalAssetRatio);
							data6.push(data[i].inventoryTurnover);
							data7.push(data[i].accountsReceivableTurnover);
							data8.push(data[i].surplusCashProtectionMltiple);
							data9.push(data[i].roe);
							data10.push(data[i].operatingProfit);
							data11.push(data[i].ope);
							data12.push(data[i].financialInvestmentYield);
							data13.push(data[i].assetBasedInvestmentYield);
							data14.push(data[i].investmentCashYield);
							flag=false;
							break;
						}
					}
					if(flag){
						data1.push(0);
						data2.push(0);
						data3.push(0);
						data4.push(0);
						data5.push(0);
						data6.push(0);
						data7.push(0);
						data8.push(0);
						data9.push(0);
						data10.push(0);
						data11.push(0);
						data12.push(0);
						data13.push(0);
						data14.push(0);
					}
					
				}	
				
			 	getLineChart_1(data1)
				getLineChart_2(data2)
				getLineChart_3(data3)
				getLineChart_4(data4)
				getLineChart_5(data5)
				getLineChart_6(data6)
				getLineChart_7(data7)
				getLineChart_8(data8)
				getLineChart_9(data9)
				getLineChart_10(data10)
				getLineChart_11(data11)
				getLineChart_12(data12)
				getLineChart_13(data13)
				getLineChart_14(data14) 
			}
		});
	}
	
    function changeData(){
    	var org = $("#company").val();
		var year = $("#year").val();
		 window.open('${pageContext.request.contextPath}/index/financial_risk?year='+year+'&orgID='+org,'_blank')
    }
    
	
	//资产负债率
	function getLineChart_1 (data1) {
		var myChart = echarts.init(document.getElementById('linechart1'));
		var option = {
			title : {
				textAlign:'center',
			    	     textStyle:{
			    	     	color:'#fff',
			    	     	fontSize:'18'
			    	     },
					     text: '资产负债率',

					     color:'#fff',
					     left:'10%',
					     top:'100'
					 },
			    grid: {
			        left: '20%',
			        right: '3%',
			        bottom: '3%',
			        containLabel: true
			    },
			xAxis: {
				     type: 'category',
				     data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
				     axisLabel: {
             						    show: true,
             						    textStyle: {
             						        color: '#fff'
             						    }
             						},
			            axisTick: {
			                alignWithLabel: true
			            },
			            axisLine:{
  						     lineStyle:{
  						         color:'#206599',
  						         width:1
  						     }
  						 }
				 },
				 yAxis: {
				     type: 'value',
				     axisLabel: {
             						    show: true,
             						    textStyle: {
             						        color: '#fff'
             						    }
             						},
			            axisTick: {
			                alignWithLabel: true
			            },
			            axisLine:{
  						     lineStyle:{
  						         color:'#206599',
  						         width:1
  						     }
  						 },
  						 splitLine: {
   						     show: true,
   						     lineStyle:{
   						         color:'#206599'
   						     }
   						 }
				 },
				 series: [{
				     data: data11,
				     type: 'line',
				     itemStyle : {  
                    				    normal : {  
                    				        color:'#bf8c62',  
                    				        lineStyle:{  
                    				            color:'#bf8c62'  
                    				        }  
                    				    }  
                    				}
				 }]
			};
		            myChart.setOption(option)
	}
	
	
	//速动比率
	function getLineChart_2 (data2) {
		var myChart = echarts.init(document.getElementById('linechart2'));
		var option = {
			title : {
				textAlign:'center',
			    	     textStyle:{
			    	     	color:'#fff',
			    	     	fontSize:'18'
			    	     },
					     text: '速动比率',

					     color:'#fff',
					     left:'10%',
					     top:'100'
					 },
			    grid: {
			        left: '20%',
			        right: '3%',
			        bottom: '3%',
			        containLabel: true
			    },
			xAxis: {
				     type: 'category',
				     data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
				     axisLabel: {
             						    show: true,
             						    textStyle: {
             						        color: '#fff'
             						    }
             						},
			            axisTick: {
			                alignWithLabel: true
			            },
			            axisLine:{
  						     lineStyle:{
  						         color:'#206599',
  						         width:1
  						     }
  						 }
				 },
				 yAxis: {
				     type: 'value',
				     axisLabel: {
             						    show: true,
             						    textStyle: {
             						        color: '#fff'
             						    }
             						},
			            axisTick: {
			                alignWithLabel: true
			            },
			            axisLine:{
  						     lineStyle:{
  						         color:'#206599',
  						         width:1
  						     }
  						 },
  						 splitLine: {
   						     show: true,
   						     lineStyle:{
   						         color:'#206599'
   						     }
   						 }
				 },
				 series: [{
				     data: data2,
				     type: 'line',
				     itemStyle : {  
                    				    normal : {  
                    				        color:'#62bf73',  
                    				        lineStyle:{  
                    				            color:'#62bf73'  
                    				        }  
                    				    }  
                    				}
				 }]
			};
		            myChart.setOption(option)
	}

	//担保净资产比
	function getLineChart_3 (data3) {
		var myChart = echarts.init(document.getElementById('linechart3'));
		var option = {
			title : {
				textAlign:'center',
			    	     textStyle:{
			    	     	color:'#fff',
			    	     	fontSize:'18'
			    	     },
					     text: '担保净资产比',

					     color:'#fff',
					     left:'10%',
					     top:'100'
					 },
			    grid: {
			        left: '20%',
			        right: '3%',
			        bottom: '3%',
			        containLabel: true
			    },
			xAxis: {
				     type: 'category',
				     data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
				     axisLabel: {
             						    show: true,
             						    textStyle: {
             						        color: '#fff'
             						    }
             						},
			            axisTick: {
			                alignWithLabel: true
			            },
			            axisLine:{
  						     lineStyle:{
  						         color:'#206599',
  						         width:1
  						     }
  						 }
				 },
				 yAxis: {
				     type: 'value',
				     axisLabel: {
             						    show: true,
             						    textStyle: {
             						        color: '#fff'
             						    }
             						},
			            axisTick: {
			                alignWithLabel: true
			            },
			            axisLine:{
  						     lineStyle:{
  						         color:'#206599',
  						         width:1
  						     }
  						 },
  						 splitLine: {
   						     show: true,
   						     lineStyle:{
   						         color:'#206599'
   						     }
   						 }
				 },
				 series: [{
				     data: data3,
				     type: 'line',
				     itemStyle : {  
                    				    normal : {  
                    				        color:'#bf6f82',  
                    				        lineStyle:{  
                    				            color:'#bf6f82'  
                    				        }  
                    				    }  
                    				}
				 }]
			};
		            myChart.setOption(option)
	}

	// /已获利息倍数
	function getLineChart_4 (data4) {
		var myChart = echarts.init(document.getElementById('linechart4'));
		var option = {
			title : {
				textAlign:'center',
			    	     textStyle:{
			    	     	color:'#fff',
			    	     	fontSize:'18'
			    	     },
					     text: '已获利息倍数',

					     color:'#fff',
					     left:'10%',
					     top:'100'
					 },
			    grid: {
			        left: '20%',
			        right: '3%',
			        bottom: '3%',
			        containLabel: true
			    },
			xAxis: {
				     type: 'category',
				     data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
				     axisLabel: {
             						    show: true,
             						    textStyle: {
             						        color: '#fff'
             						    }
             						},
			            axisTick: {
			                alignWithLabel: true
			            },
			            axisLine:{
  						     lineStyle:{
  						         color:'#206599',
  						         width:1
  						     }
  						 }
				 },
				 yAxis: {
				     type: 'value',
				     axisLabel: {
             						    show: true,
             						    textStyle: {
             						        color: '#fff'
             						    }
             						},
			            axisTick: {
			                alignWithLabel: true
			            },
			            axisLine:{
  						     lineStyle:{
  						         color:'#206599',
  						         width:1
  						     }
  						 },
  						 splitLine: {
   						     show: true,
   						     lineStyle:{
   						         color:'#206599'
   						     }
   						 }
				 },
				 series: [{
				     data: data4,
				     type: 'line',
				     itemStyle : {  
                    				    normal : {  
                    				        color:'#aaa8ff',  
                    				        lineStyle:{  
                    				            color:'#aaa8ff'  
                    				        }  
                    				    }  
                    				}
				 }]
			};
		            myChart.setOption(option)
	}

	//现金总资产比
	function getLineChart_5 (data5) {
		var myChart = echarts.init(document.getElementById('linechart5'));
		var option = {
			title : {
				textAlign:'center',
			    	     textStyle:{
			    	     	color:'#fff',
			    	     	fontSize:'18'
			    	     },
					     text: '现金总资产比',

					     color:'#fff',
					     left:'10%',
					     top:'100'
					 },
			    grid: {
			        left: '20%',
			        right: '3%',
			        bottom: '3%',
			        containLabel: true
			    },
			xAxis: {
				     type: 'category',
				     data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
				     axisLabel: {
             						    show: true,
             						    textStyle: {
             						        color: '#fff'
             						    }
             						},
			            axisTick: {
			                alignWithLabel: true
			            },
			            axisLine:{
  						     lineStyle:{
  						         color:'#206599',
  						         width:1
  						     }
  						 }
				 },
				 yAxis: {
				     type: 'value',
				     axisLabel: {
             						    show: true,
             						    textStyle: {
             						        color: '#fff'
             						    }
             						},
			            axisTick: {
			                alignWithLabel: true
			            },
			            axisLine:{
  						     lineStyle:{
  						         color:'#206599',
  						         width:1
  						     }
  						 },
  						 splitLine: {
   						     show: true,
   						     lineStyle:{
   						         color:'#206599'
   						     }
   						 }
				 },
				 series: [{
				     data:data5,
				     type: 'line',
				     itemStyle : {  
                    				    normal : {  
                    				        color:'#bf8c62',  
                    				        lineStyle:{  
                    				            color:'#bf8c62'  
                    				        }  
                    				    }  
                    				}
				 }]
			};
		            myChart.setOption(option)
	}

	//存货周转率
	function getLineChart_6 (data6) {
		var myChart = echarts.init(document.getElementById('linechart6'));
		var option = {
			title : {
				textAlign:'center',
			    	     textStyle:{
			    	     	color:'#fff',
			    	     	fontSize:'18'
			    	     },
					     text: '存货周转率',

					     color:'#fff',
					     left:'10%',
					     top:'100'
					 },
			    grid: {
			        left: '20%',
			        right: '3%',
			        bottom: '3%',
			        containLabel: true
			    },
			xAxis: {
				     type: 'category',
				     data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
				     axisLabel: {
             						    show: true,
             						    textStyle: {
             						        color: '#fff'
             						    }
             						},
			            axisTick: {
			                alignWithLabel: true
			            },
			            axisLine:{
  						     lineStyle:{
  						         color:'#206599',
  						         width:1
  						     }
  						 }
				 },
				 yAxis: {
				     type: 'value',
				     axisLabel: {
             						    show: true,
             						    textStyle: {
             						        color: '#fff'
             						    }
             						},
			            axisTick: {
			                alignWithLabel: true
			            },
			            axisLine:{
  						     lineStyle:{
  						         color:'#206599',
  						         width:1
  						     }
  						 },
  						 splitLine: {
   						     show: true,
   						     lineStyle:{
   						         color:'#206599'
   						     }
   						 }
				 },
				 series: [{
				     data: data6,
				     type: 'line',
				     itemStyle : {  
                    				    normal : {  
                    				        color:'#62bf73',  
                    				        lineStyle:{  
                    				            color:'#62bf73'  
                    				        }  
                    				    }  
                    				}
				 }]
			};
		            myChart.setOption(option)
	}

	//应收账款周转率
	function getLineChart_7 (data7) {
		var myChart = echarts.init(document.getElementById('linechart7'));
		var option = {
			title : {
				textAlign:'center',
			    	     textStyle:{
			    	     	color:'#fff',
			    	     	fontSize:'18'
			    	     },
					     text: '应收账款\n周转率',

					     color:'#fff',
					     left:'10%',
					     top:'100'
					 },
			    grid: {
			        left: '20%',
			        right: '3%',
			        bottom: '3%',
			        containLabel: true
			    },
			xAxis: {
				     type: 'category',
				     data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
				     axisLabel: {
             						    show: true,
             						    textStyle: {
             						        color: '#fff'
             						    }
             						},
			            axisTick: {
			                alignWithLabel: true
			            },
			            axisLine:{
  						     lineStyle:{
  						         color:'#206599',
  						         width:1
  						     }
  						 }
				 },
				 yAxis: {
				     type: 'value',
				     axisLabel: {
             						    show: true,
             						    textStyle: {
             						        color: '#fff'
             						    }
             						},
			            axisTick: {
			                alignWithLabel: true
			            },
			            axisLine:{
  						     lineStyle:{
  						         color:'#206599',
  						         width:1
  						     }
  						 },
  						 splitLine: {
   						     show: true,
   						     lineStyle:{
   						         color:'#206599'
   						     }
   						 }
				 },
				 series: [{
				     data: data7,
				     type: 'line',
				     itemStyle : {  
                    				    normal : {  
                    				        color:'#bf6f82',  
                    				        lineStyle:{  
                    				            color:'#bf6f82'  
                    				        }  
                    				    }  
                    				}
				 }]
			};
		            myChart.setOption(option)
	}

	//盈余现金保障倍数
	function getLineChart_8 (data8) {
		var myChart = echarts.init(document.getElementById('linechart8'));
		var option = {
			title : {
				textAlign:'center',
			    	     textStyle:{
			    	     	color:'#fff',
			    	     	fontSize:'18'
			    	     },
					     text: '盈余现金\n保障倍数',

					     color:'#fff',
					     left:'10%',
					     top:'100'
					 },
			    grid: {
			        left: '20%',
			        right: '3%',
			        bottom: '3%',
			        containLabel: true
			    },
			xAxis: {
				     type: 'category',
				     data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
				     axisLabel: {
             						    show: true,
             						    textStyle: {
             						        color: '#fff'
             						    }
             						},
			            axisTick: {
			                alignWithLabel: true
			            },
			            axisLine:{
  						     lineStyle:{
  						         color:'#206599',
  						         width:1
  						     }
  						 }
				 },
				 yAxis: {
				     type: 'value',
				     axisLabel: {
             						    show: true,
             						    textStyle: {
             						        color: '#fff'
             						    }
             						},
			            axisTick: {
			                alignWithLabel: true
			            },
			            axisLine:{
  						     lineStyle:{
  						         color:'#206599',
  						         width:1
  						     }
  						 },
  						 splitLine: {
   						     show: true,
   						     lineStyle:{
   						         color:'#206599'
   						     }
   						 }
				 },
				 series: [{
				     data:data8,
				     type: 'line',
				     itemStyle : {  
                    				    normal : {  
                    				        color:'#aaa8ff',  
                    				        lineStyle:{  
                    				            color:'#aaa8ff'  
                    				        }  
                    				    }  
                    				}
				 }]
			};
		            myChart.setOption(option)
	}

	// /净资产收益率
	function getLineChart_9 (data9) {
		var myChart = echarts.init(document.getElementById('linechart9'));
		var option = {
			title : {
				textAlign:'center',
			    	     textStyle:{
			    	     	color:'#fff',
			    	     	fontSize:'18'
			    	     },
					     text: '净资产收益率',

					     color:'#fff',
					     left:'10%',
					     top:'100'
					 },
			    grid: {
			        left: '20%',
			        right: '3%',
			        bottom: '3%',
			        containLabel: true
			    },
			xAxis: {
				     type: 'category',
				     data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
				     axisLabel: {
             						    show: true,
             						    textStyle: {
             						        color: '#fff'
             						    }
             						},
			            axisTick: {
			                alignWithLabel: true
			            },
			            axisLine:{
  						     lineStyle:{
  						         color:'#206599',
  						         width:1
  						     }
  						 }
				 },
				 yAxis: {
				     type: 'value',
				     axisLabel: {
             						    show: true,
             						    textStyle: {
             						        color: '#fff'
             						    }
             						},
			            axisTick: {
			                alignWithLabel: true
			            },
			            axisLine:{
  						     lineStyle:{
  						         color:'#206599',
  						         width:1
  						     }
  						 },
  						 splitLine: {
   						     show: true,
   						     lineStyle:{
   						         color:'#206599'
   						     }
   						 }
				 },
				 series: [{
				     data: data9,
				     type: 'line',
				     itemStyle : {  
                    				    normal : {  
                    				        color:'#bf8c62',  
                    				        lineStyle:{  
                    				            color:'#bf8c62'  
                    				        }  
                    				    }  
                    				}
				 }]
			};
		            myChart.setOption(option)
	}

	//经营性营业利润占总利润的比重
	function getLineChart_10 (data10) {
		var myChart = echarts.init(document.getElementById('linechart10'));
		var option = {
			title : {
				textAlign:'center',
			    	     textStyle:{
			    	     	color:'#fff',
			    	     	fontSize:'18'
			    	     },
					     text: '经营性营业利润\n占总利润的比重',

					     color:'#fff',
					     left:'10%',
					     top:'100'
					 },
			    grid: {
			        left: '20%',
			        right: '3%',
			        bottom: '3%',
			        containLabel: true
			    },
			xAxis: {
				     type: 'category',
				     data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
				     axisLabel: {
             						    show: true,
             						    textStyle: {
             						        color: '#fff'
             						    }
             						},
			            axisTick: {
			                alignWithLabel: true
			            },
			            axisLine:{
  						     lineStyle:{
  						         color:'#206599',
  						         width:1
  						     }
  						 }
				 },
				 yAxis: {
				     type: 'value',
				     axisLabel: {
             						    show: true,
             						    textStyle: {
             						        color: '#fff'
             						    }
             						},
			            axisTick: {
			                alignWithLabel: true
			            },
			            axisLine:{
  						     lineStyle:{
  						         color:'#206599',
  						         width:1
  						     }
  						 },
  						 splitLine: {
   						     show: true,
   						     lineStyle:{
   						         color:'#206599'
   						     }
   						 }
				 },
				 series: [{
				     data: data10,
				     type: 'line',
				     itemStyle : {  
                    				    normal : {  
                    				        color:'#62bf73',  
                    				        lineStyle:{  
                    				            color:'#62bf73'  
                    				        }  
                    				    }  
                    				}
				 }]
			};
		            myChart.setOption(option)
	}

	//主营业务利润率
	function getLineChart_11 (data11) {
		var myChart = echarts.init(document.getElementById('linechart11'));
		var option = {
			title : {
				textAlign:'center',
			    	     textStyle:{
			    	     	color:'#fff',
			    	     	fontSize:'18'
			    	     },
					     text: '主营业务\n利润率',

					     color:'#fff',
					     left:'10%',
					     top:'100'
					 },
			    grid: {
			        left: '20%',
			        right: '3%',
			        bottom: '3%',
			        containLabel: true
			    },
			xAxis: {
				     type: 'category',
				     data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
				     axisLabel: {
             						    show: true,
             						    textStyle: {
             						        color: '#fff'
             						    }
             						},
			            axisTick: {
			                alignWithLabel: true
			            },
			            axisLine:{
  						     lineStyle:{
  						         color:'#206599',
  						         width:1
  						     }
  						 }
				 },
				 yAxis: {
				     type: 'value',
				     axisLabel: {
             						    show: true,
             						    textStyle: {
             						        color: '#fff'
             						    }
             						},
			            axisTick: {
			                alignWithLabel: true
			            },
			            axisLine:{
  						     lineStyle:{
  						         color:'#206599',
  						         width:1
  						     }
  						 },
  						 splitLine: {
   						     show: true,
   						     lineStyle:{
   						         color:'#206599'
   						     }
   						 }
				 },
				 series: [{
				     data: data11,
				     type: 'line',
				     itemStyle : {  
                    				    normal : {  
                    				        color:'#bf6f82',  
                    				        lineStyle:{  
                    				            color:'#bf6f82'  
                    				        }  
                    				    }  
                    				}
				 }]
			};
		            myChart.setOption(option)
	}

	//金融类投资收益率
	function getLineChart_12 (data12) {
		var myChart = echarts.init(document.getElementById('linechart12'));
		var option = {
			title : {
				textAlign:'center',
			    	     textStyle:{
			    	     	color:'#fff',
			    	     	fontSize:'18'
			    	     },
					     text: '金融类投资\n收益率',

					     color:'#fff',
					     left:'10%',
					     top:'100'
					 },
			    grid: {
			        left: '20%',
			        right: '3%',
			        bottom: '3%',
			        containLabel: true
			    },
			xAxis: {
				     type: 'category',
				     data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
				     axisLabel: {
             						    show: true,
             						    textStyle: {
             						        color: '#fff'
             						    }
             						},
			            axisTick: {
			                alignWithLabel: true
			            },
			            axisLine:{
  						     lineStyle:{
  						         color:'#206599',
  						         width:1
  						     }
  						 }
				 },
				 yAxis: {
				     type: 'value',
				     axisLabel: {
             						    show: true,
             						    textStyle: {
             						        color: '#fff'
             						    }
             						},
			            axisTick: {
			                alignWithLabel: true
			            },
			            axisLine:{
  						     lineStyle:{
  						         color:'#206599',
  						         width:1
  						     }
  						 },
  						 splitLine: {
   						     show: true,
   						     lineStyle:{
   						         color:'#206599'
   						     }
   						 }
				 },
				 series: [{
				     data: data12,
				     type: 'line',
				     itemStyle : {  
                    				    normal : {  
                    				        color:'#bf8c62',  
                    				        lineStyle:{  
                    				            color:'#bf8c62'  
                    				        }  
                    				    }  
                    				}
				 }]
			};
		            myChart.setOption(option)
	}

	//资产类投资收益率
	function getLineChart_13 (data13) {
		var myChart = echarts.init(document.getElementById('linechart13'));
		var option = {
			title : {
				textAlign:'center',
			    	     textStyle:{
			    	     	color:'#fff',
			    	     	fontSize:'18'
			    	     },
					     text: '资产类投资\n收益率',

					     color:'#fff',
					     left:'10%',
					     top:'100'
					 },
			    grid: {
			        left: '20%',
			        right: '3%',
			        bottom: '3%',
			        containLabel: true
			    },
			xAxis: {
				     type: 'category',
				     data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
				     axisLabel: {
             						    show: true,
             						    textStyle: {
             						        color: '#fff'
             						    }
             						},
			            axisTick: {
			                alignWithLabel: true
			            },
			            axisLine:{
  						     lineStyle:{
  						         color:'#206599',
  						         width:1
  						     }
  						 }
				 },
				 yAxis: {
				     type: 'value',
				     axisLabel: {
             						    show: true,
             						    textStyle: {
             						        color: '#fff'
             						    }
             						},
			            axisTick: {
			                alignWithLabel: true
			            },
			            axisLine:{
  						     lineStyle:{
  						         color:'#206599',
  						         width:1
  						     }
  						 },
  						 splitLine: {
   						     show: true,
   						     lineStyle:{
   						         color:'#206599'
   						     }
   						 }
				 },
				 series: [{
				     data: data13,
				     type: 'line',
				     itemStyle : {  
                    				    normal : {  
                    				        color:'#62bf73',  
                    				        lineStyle:{  
                    				            color:'#62bf73'  
                    				        }  
                    				    }  
                    				}
				 }]
			};
		            myChart.setOption(option)
	}

	// /投资现金收益率
	function getLineChart_14 (data14) {
		var myChart = echarts.init(document.getElementById('linechart14'));
		var option = {
			title : {
				textAlign:'center',
			    	     textStyle:{
			    	     	color:'#fff',
			    	     	fontSize:'18'
			    	     },
					     text: '投资现金\n收益率',

					     color:'#fff',
					     left:'10%',
					     top:'100'
					 },
			    grid: {
			        left: '20%',
			        right: '3%',
			        bottom: '3%',
			        containLabel: true
			    },
			xAxis: {
				     type: 'category',
				     data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
				     axisLabel: {
             						    show: true,
             						    textStyle: {
             						        color: '#fff'
             						    }
             						},
			            axisTick: {
			                alignWithLabel: true
			            },
			            axisLine:{
  						     lineStyle:{
  						         color:'#206599',
  						         width:1
  						     }
  						 }
				 },
				 yAxis: {
				     type: 'value',
				     axisLabel: {
             						    show: true,
             						    textStyle: {
             						        color: '#fff'
             						    }
             						},
			            axisTick: {
			                alignWithLabel: true
			            },
			            axisLine:{
  						     lineStyle:{
  						         color:'#206599',
  						         width:1
  						     }
  						 },
  						 splitLine: {
   						     show: true,
   						     lineStyle:{
   						         color:'#206599'
   						     }
   						 }
				 },
				 series: [{
				     data: data14,
				     type: 'line',
				     itemStyle : {  
                    				    normal : {  
                    				        color:'#bf6f82',  
                    				        lineStyle:{  
                    				            color:'#bf6f82'  
                    				        }  
                    				    }  
                    				}
				 }]
			};
		            myChart.setOption(option)
	}
	 	/* $(function() {
	 		//getLineChart_1(data1)
			getLineChart_2(data2)
			getLineChart_3(data3)
			getLineChart_4(data4)
			getLineChart_5(data5)
			getLineChart_6(data6)
			getLineChart_7(data7)
			getLineChart_8(data8)
			getLineChart_9(data9)
			getLineChart_10(data10)
			getLineChart_11(data11)
			getLineChart_12(data12)
			getLineChart_13(data13)
			getLineChart_14(data14) 


			
		})  */
	</script>
</body>
</html>