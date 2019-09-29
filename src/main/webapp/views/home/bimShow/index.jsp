<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
  	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/views/home/bimShow/assets/css/style.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/views/home/bimShow/assets/css/scroll.css"/>
</head>
<body>
	<div class="container">
		<h1>上海市国资委国资监管风控大数据平台</h1>
		<div class="line1 clearfix">
			<div class="case case1">
				<div class="item1">
					<h2><a href="" onclick="turnToFinanceRisk()" style='color: #00FFFF;text-decoration:none;'>财务风险预警<span class="time" id='finan_risk_top10_time'></span></a></h2>
					<h3>低风险企业top10</h3>
					<ul id='finan_risk_top10'>
						<!-- 
						<li>上海光明集团有限公司</li>
						<li>上海有理想有限公司</li>
						<li>上海面雅信息技术有限公司</li>
						<li>上海和平信息技术有限公司</li>
						<li>上海富强信息技术有限公司</li>
						<li>上海繁荣信息技术有限公司</li>
						<li>上海天地仁信息技术有限公司</li>
						<li>上海天地义信息技术有限公司</li>
						<li>上海天地理信息技术有限公司</li>
						<li>上海企顺信息技术有限公司</li>
 						-->
					</ul>
				</div>
				<div class="item2">
					<div id="barchart_1" style="width:48%;height: 220px;"></div>
					<div id="barchart_2" style="width:48%;height: 220px;"></div>
					<div id="barchart_3" style="width:48%;height: 220px;"></div>
					<div id="barchart_4" style="width:48%;height: 220px;"></div>
				</div>
			</div>
			<div class="case case2">
				<h2><a href="" style='color: #00FFFF;text-decoration:none;'onclick="industryCommerce()">工商数据分析<span class="time" id='icbc_time'></span></a></h2>
				<div id="piechart" style="width: 100%;height: 220px;"></div>
				<h3>国资与工商数据41家中有差异公司：</h3>
				<ul id="icbc_analysis_diffCompany">
				<!--  
					<li>上海国盛集团有限公司</li>
					<li>光明集团有限公司</li>
					<li>上海电气集团有限公司</li>
				-->
				</ul>
			</div>
		</div>
		<div class="line2 clearfix">
			<div class="case case3">
				<h2><a href='' style='color: #00FFFF;text-decoration:none;' onclick="turnToFinancialProperty()">财务与产权口径<span class="time" id="Fin_manage_time"></span></a></h2>
				<div class="clearfix"></div>
				<img class="err" src="${pageContext.request.contextPath}/views/home/bimShow/assets/images/err.png">
				<div class="err_info" id="Fin_manage_total">89 <span>个<br>当前不匹配总数量</span></div>
				<div class="clearfix"></div>
				<span class="sys_tree">公司结构树</span>
				<div class="clearfix"></div>
				<div class="gzw_total">上海国资委(41家)</div>
				<ul class="gzw_detail" id="scroll2">
					<!-- 
					<li>上海国盛集团（200家，12家）</li>
					<li>上海国际集团（100家，23家）</li>
					<li>光明集团（300家，23家）</li>
					<li>上海国盛集团（200家，12家）</li>
					<li>上海国际集团（100家，23家）</li>
					<li>光明集团（300家，23家）</li>
					<li>光明集团（300家，23家）</li>
					<li>上海国盛集团（200家，12家）</li>
					<li>上海国际集团（100家，23家）</li>
					<li>光明集团（300家，23家）</li>
					 -->
				</ul>
			</div>
			<div class="case case4">
				<h2 style="margin: 0 auto;" ><a href='' style='color: #00FFFF;text-decoration:none;' onclick="turnToOutCompare()">上市公司数据<span class="time" id="company_time"></span></a> </h2>
				<div class="clearfix"></div>
				<div class="data">
					<div class="data_title" id='companyTitle1'>上市公司A  （代码：23243）</div>
					<img class="data_icon" src="${pageContext.request.contextPath}/views/home/bimShow/assets/images/gold.png">
					<div class="data_value1" id='companyTotal1'>3434 <span>亿 <br>总市值 </span></div>
					<img class="data_icon Rotation" src="${pageContext.request.contextPath}/views/home/bimShow/assets/images/circulate.png">
					<div class="data_value2" id='companyCurrent1'>34343 <span>亿 <br>流通市值 </span></div>
				</div>
				<div class="data">
					<div class="data_title" id='companyTitle2'>上市公司A  （代码：23243）</div>
					<img class="data_icon" src="${pageContext.request.contextPath}/views/home/bimShow/assets/images/gold.png">
					<div class="data_value1" id='companyTotal2'>3434 <span>亿 <br>总市值 </span></div>
					<img class="data_icon Rotation" src="${pageContext.request.contextPath}/views/home/bimShow/assets/images/circulate.png">
					<div class="data_value2" id='companyCurrent2'>34343 <span>亿 <br>流通市值 </span></div>
				</div>
				<div class="data">
					<div class="data_title" id='companyTitle3'>上市公司A  （代码：23243）</div>
					<img class="data_icon" src="${pageContext.request.contextPath}/views/home/bimShow/assets/images/gold.png">
					<div class="data_value1" id='companyTotal3'>3434 <span>亿 <br>总市值 </span></div>
					<img class="data_icon Rotation" src="${pageContext.request.contextPath}/views/home/bimShow/assets/images/circulate.png">
					<div class="data_value2" id='companyCurrent3'>34343 <span>亿 <br>流通市值 </span></div>
				</div>
				<div class="data">
					<div class="data_title" id='companyTitle4'>上市公司A  （代码：23243）</div>
					<img class="data_icon" src="${pageContext.request.contextPath}/views/home/bimShow/assets/images/gold.png">
					<div class="data_value1" id='companyTotal4'>3434 <span>亿 <br>总市值 </span></div>
					<img class="data_icon Rotation" src="${pageContext.request.contextPath}/views/home/bimShow/assets/images/circulate.png">
					<div class="data_value2" id='companyCurrent4'>34343 <span>亿 <br>流通市值 </span></div>
				</div>
				<div class="data">
					<div class="data_title" id='companyTitle5'>上市公司A  （代码：23243）</div>
					<img class="data_icon" src="${pageContext.request.contextPath}/views/home/bimShow/assets/images/gold.png">
					<div class="data_value1" id='companyTotal5'>3434 <span>亿 <br>总市值 </span></div>
					<img class="data_icon Rotation" src="${pageContext.request.contextPath}/views/home/bimShow/assets/images/circulate.png">
					<div class="data_value2" id='companyCurrent5'>34343 <span>亿 <br>流通市值 </span></div>
				</div>
				<div class="data">
					<div class="data_title" id='companyTitle6'>上市公司A  （代码：23243）</div>
					<img class="data_icon" src="${pageContext.request.contextPath}/views/home/bimShow/assets/images/gold.png">
					<div class="data_value1" id='companyTotal6'>3434 <span>亿 <br>总市值 </span></div>
					<img class="data_icon Rotation" src="${pageContext.request.contextPath}/views/home/bimShow/assets/images/circulate.png">
					<div class="data_value2" id='companyCurrent6'>34343 <span>亿 <br>流通市值 </span></div>
				</div>
			</div>
			<div class="case case5">
				<h2>数据全景分析<span class="time" id='data_analysis_time'></span> </h2>
				<img class="ball" src="${pageContext.request.contextPath}/views/home/bimShow/assets/images/ball.png">
				<div class="ball_info"><span id='data_analysis_all'>20</span> <span>TB<br>分析数据总量</span></div>
				<div class="clearfix">
					
				</div>
				<ul class="ul_container" id="scroll">
						<!--
						 <li>2018/03/09  AA系统中AA公司的AA信息已纳入数据分析</li>
						<li>2018/08/20  BB系统中BB公司的BB信息已纳入数据分析</li>
						<li>2018/12/01  CC系统中CC公司的BB信息已纳入数据分析</li>
						<li>2019/01/30  EEE系统中EEEEEEEEEEE公司的EEEEE信息已纳入数据分析</li>
						<li>2018/08/20  BB系统中BB公司的BB信息已纳入数据分析</li>
						<li>2018/12/01  CC系统中CC公司的BB信息已纳入数据分析</li>
						<li>2019/01/30  EEE系统中EEEEEEEEEEE公司的EEEEE信息已纳入数据分析</li>
						-->
				</ul>
			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/views/home/bimShow/assets/plugins/jquery/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/views/home/bimShow/assets/plugins/echarts/echarts.js"></script>
	<script src="${pageContext.request.contextPath}/views/home/bimShow/assets/js/scroll.js" type="text/javascript" charset="utf-8"></script>
	<script src="${pageContext.request.contextPath}/views/home/bimShow/assets/js/index.js" type="text/javascript" charset="utf-8"></script>
	<script>
		/*这是启动方式*/
		/*
		 * demo 父容器(ul)的id
		 * -36px 子元素li的高度
		 * 3000  滚动间隔时间
		 * 每次滚动持续时间可到css文件中修改
		 */
		myScroll.upScroll("scroll","-38px",3000);
		
	</script>
	<script>
		/*这是启动方式*/
		/*
		 * demo 父容器(ul)的id
		 * -36px 子元素li的高度
		 * 3000  滚动间隔时间
		 * 每次滚动持续时间可到css文件中修改
		 */
		myScroll.upScroll("scroll2","-47px",3000);
		
	</script>
	<script type="text/javascript">
		//财务风险预警
		function getBarChart_1(data){
			var myChart = echarts.init(document.getElementById('barchart_1'));
			var option = {
				    title : {
				    	     textStyle:{
				    	     	color:'#fff',
				    	     	fontSize:'18'
				    	     },
						     text: '债务风险',
						     color:'#fff',
						     borderWidth:'1',
						     borderColor:'#0efefb',
						     shadowBlur:'10',
						     shadowColor:'#0efefb',
						     right:'0',
						     top:'10'
						 },
				    tooltip : {
				        trigger: 'axis',
				        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
				        }
				    },
				    grid: {
				        left: '3%',
				        right: '20%',
				        bottom: '3%',
				        containLabel: true
				    },
				    xAxis : [
				        {
				            type : 'category',
				            data : ['红色企业', '黄色企业', '绿色企业'],
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
         						         color:'#4e99cc',
         						         width:1,//这里是为了突出显示加上的，可以去掉
         						     }
         						 }
				        }
				    ],
				    yAxis : [
				        {splitLine: {
          						     show: true,
          						     lineStyle:{
          						         type:'dashed',
          						         color:'#394a6d'
          						     }
          						 },
				            type : 'value',
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
         						         color:'#4e99cc',
         						         width:1,//这里是为了突出显示加上的，可以去掉
         						     }
         						 }
				        }
				    ],
				    series : [
				        {
				            name:'数量',
				            type:'bar',
				            barWidth: '60%',
				            /*markPoint : {
							  data : [
							  {name : '年最高', value : 350, xAxis: 2, yAxis: 350}
							         ]
						      },*/
				            data:data,
				            //配置样式
				            itemStyle: {   
				                //通常情况下：
				                normal:{  
				　　　　　　　　　　　　//每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
				                    color: function (params){
				                        var colorList = ['#b33653','#eec06a','#6bcac4'];
				                        return colorList[params.dataIndex];
				                    }
				                },
				                //鼠标悬停时：
				                emphasis: {
				                        shadowBlur: 10,
				                        shadowOffsetX: 0,
				                        shadowColor: 'rgba(0, 0, 0, 0.1)'
				                }
				            }
				        }
				    ]
				};
			   myChart.setOption(option)
		}

			//
			function getBarChart_2 (data) {
				var myChart = echarts.init(document.getElementById('barchart_2'));
				var option = {
					title : {
					    	     textStyle:{
					    	     	color:'#fff',
					    	     	fontSize:'18'
					    	     },
 						     text: '现金流风险',
 						     color:'#fff',
 						     borderWidth:'1',
 						     borderColor:'#0efefb',
 						     shadowBlur:'10',
 						     shadowColor:'#0efefb',
 						     right:'0',
 						     top:'10'
 						 },
					    tooltip : {
					        trigger: 'axis',
					        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
					            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
					        }
					    },
					    grid: {
					        left: '3%',
					        right: '20%',
					        bottom: '3%',
					        containLabel: true
					    },
					    xAxis : [
					        {
					            type : 'category',
					            data : ['红色企业', '黄色企业', '绿色企业'],
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
          						         color:'#4e99cc',
          						         width:1,//这里是为了突出显示加上的，可以去掉
          						     }
          						 }
					        }
					    ],
					    yAxis : [
					        {splitLine: {
           						     show: true,
           						     lineStyle:{
           						         type:'dashed',
           						         color:'#394a6d'
           						     }
           						 },
					            type : 'value',
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
          						         color:'#4e99cc',
          						         width:1,//这里是为了突出显示加上的，可以去掉
          						     }
          						 }
					        }
					    ],
					    series : [
					        {
					            name:'数量',
					            type:'bar',
					            barWidth: '60%',
					            /*markPoint : {
								  data : [
								  {name : '年最高', value : 350, xAxis: 2, yAxis: 350}
								         ]
							      },*/
					            data:data,
					            //配置样式
					            itemStyle: {   
					                //通常情况下：
					                normal:{  
					　　　　　　　　　　　　//每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
					                    color: function (params){
					                        var colorList = ['#b33653','#eec06a','#6bcac4'];
					                        return colorList[params.dataIndex];
					                    }
					                },
					                //鼠标悬停时：
					                emphasis: {
					                        shadowBlur: 10,
					                        shadowOffsetX: 0,
					                        shadowColor: 'rgba(0, 0, 0, 0.1)'
					                }
					            }
					        }
					    ]
					};

				            myChart.setOption(option)
			}

			//
			function getBarChart_3 (data) {
				var myChart = echarts.init(document.getElementById('barchart_3'));
				var option = {
					title : {
					    	     textStyle:{
					    	     	color:'#fff',
					    	     	fontSize:'18'
					    	     },
 						     text: '盈利风险',
 						     color:'#fff',
 						     borderWidth:'1',
 						     borderColor:'#0efefb',
 						     shadowBlur:'10',
 						     shadowColor:'#0efefb',
 						     right:'0',
 						     top:'10'
 						 },
					    tooltip : {
					        trigger: 'axis',
					        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
					            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
					        }
					    },
					    grid: {
					        left: '3%',
					        right: '20%',
					        bottom: '3%',
					        containLabel: true
					    },
					    xAxis : [
					        {
					            type : 'category',
					            data : ['红色企业', '黄色企业', '绿色企业'],
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
          						         color:'#4e99cc',
          						         width:1,//这里是为了突出显示加上的，可以去掉
          						     }
          						 }
					        }
					    ],
					    yAxis : [
					        {splitLine: {
           						     show: true,
           						     lineStyle:{
           						         type:'dashed',
           						         color:'#394a6d'
           						     }
           						 },
					            type : 'value',
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
          						         color:'#4e99cc',
          						         width:1,//这里是为了突出显示加上的，可以去掉
          						     }
          						 }
					        }
					    ],
					    series : [
					        {
					            name:'数量',
					            type:'bar',
					            barWidth: '60%',
					           /* markPoint : {
								  data : [
								  {name : '年最高', value : 350, xAxis: 2, yAxis: 350}
								         ]
							      },*/
					            data:data,
					            //配置样式
					            itemStyle: {   
					                //通常情况下：
					                normal:{  
					　　　　　　　　　　　　//每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
					                    color: function (params){
					                        var colorList = ['#b33653','#eec06a','#6bcac4'];
					                        return colorList[params.dataIndex];
					                    }
					                },
					                //鼠标悬停时：
					                emphasis: {
					                        shadowBlur: 10,
					                        shadowOffsetX: 0,
					                        shadowColor: 'rgba(0, 0, 0, 0.1)'
					                }
					            }
					        }
					    ]
					};

				            myChart.setOption(option)
			}

			//
			function getBarChart_4 (data) {
				var myChart = echarts.init(document.getElementById('barchart_4'));
				var option = {
					title : {
					    	     textStyle:{
					    	     	color:'#fff',
					    	     	fontSize:'18'
					    	     },
 						     text: '投资风险',
 						     color:'#fff',
 						     borderWidth:'1',
 						     borderColor:'#0efefb',
 						     shadowBlur:'10',
 						     shadowColor:'#0efefb',
 						     right:'0',
 						     top:'10'
 						 },
					    tooltip : {
					        trigger: 'axis',
					        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
					            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
					        }
					    },
					    grid: {
					        left: '3%',
					        right: '20%',
					        bottom: '3%',
					        containLabel: true
					    },
					    xAxis : [
					        {
					            type : 'category',
					            data : ['红色企业', '黄色企业', '绿色企业'],
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
          						         color:'#4e99cc',
          						         width:1,//这里是为了突出显示加上的，可以去掉
          						     }
          						 }
					        }
					    ],
					    yAxis : [
					        {splitLine: {
           						     show: true,
           						     lineStyle:{
           						         type:'dashed',
           						         color:'#394a6d'
           						     }
           						 },
					            type : 'value',
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
          						         color:'#4e99cc',
          						         width:1,//这里是为了突出显示加上的，可以去掉
          						     }
          						 }
					        }
					    ],
					    series : [
					        {
					            name:'数量',
					            type:'bar',
					            barWidth: '60%',
					            /*markPoint : {
								  data : [
								  {name : '年最高', value : 350, xAxis: 2, yAxis: 350}
								         ]
							      },*/
					            data:data,
					            //配置样式
					            itemStyle: {   
					                //通常情况下：
					                normal:{  
					                    color: function (params){
					                        var colorList = ['#b33653','#eec06a','#6bcac4'];
					                        return colorList[params.dataIndex];
					                    }
					                },
					                //鼠标悬停时：
					                emphasis: {
					                        shadowBlur: 10,
					                        shadowOffsetX: 0,
					                        shadowColor: 'rgba(0, 0, 0, 0.1)'
					                }
					            }
					        }
					    ]
					};

				            myChart.setOption(option)
			}

			    // function getPieChart(){
				   //  var myChart = echarts.init(document.getElementById('piechart'));
				   //  var option = {
				   //      color:['#db4b4c','#efca8e'],        
				   //      series: [
				   //          {
				   //              type:'pie',
				   //              radius: ['20%', '50%'],
				   //              center: ['55%', '55%'],
				   //              label: {
				   //                  normal: {
				   //                      color:'#FFF',
				   //                      formatter: '{a|{b}}',
				   //                      rich: {
				   //                             a:{
				   //                                    color: '#FFF',
				   //                                    fontSize: 14,
				   //                                    lineHeight: 20,
				   //                                    align: 'center'
				   //                              }
				                                  
				   //                      }
				   //                  }
				   //              },
				   //              labelLine: {
				   //                  normal: {
				   //                      length: 15 ,
				   //                      length2: 70
				                        
				   //                  }
				   //              },
				   //              data:[
				                    
				   //                  { value:user[1], name:'微信绑\n定会员',
				   //                    label:{
				   //                      normal:{
				   //                        padding:[-50,0,0,-60]
				
				   //                      }
				   //                    }
				   //                  },
				   //                  { value:user[0], name:'注册会员',
				   //                    label:{
				   //                      normal:{
				   //                        padding:[-30,-65,0,0]
				   //                      }
				   //                    }
				   //                  }
				                    
				   //              ]
				   //          }
				   //      ]
				   //  };
				   //  myChart.setOption(option)
				   //        // myChart.dispatchAction({    //高亮指定的数据图形。
				   //        //     type: 'highlight',
				   //        //     seriesIndex: 0,
				   //        //     dataIndex: 1
				   //        // })
				   //  }


			//工商银行分析
			function getPieChart(data1,data2){
				var myChart = echarts.init(document.getElementById('piechart'));
				var option = {
				        title: {
  					    text: '工商数据\n不一致公司',
  					    left: 'center',
  					    top: 85,
  					    left: 200,
  					    textAlign:'center',
  					    textStyle: {
  					        color: '#0efefb',

  					    }
  					},
				        color:['#eec06a','#a3f2fa'],        
				        series: [
				            {
				                type:'pie',
				                radius: ['60%', '80%'],
				                center: ['50%', '50%'],
				                label: {
				                    normal: {
				                        color:'#FFF',
				                        formatter: '{a|{b}\n{c}}',
				                        rich: {
				                               a:{
				                                      color: '#FFF',
				                                      fontSize: 14,
				                                      lineHeight: 20,
				                                      align: 'center'
				                                }
				                                  
				                        }
				                    }
				                },
				                labelLine: {
				                    normal: {
				                        length: 15 ,
				                        length2: 70
				                        
				                    }
				                },
				                data:[
				                    
				                    { value:data1, name:'工商数据\n不一致公司',
				                      label:{
				                        normal:{
				                          padding:[-20,0,0,-70]
				
				                        }
				                      }
				                    },
				                    { value:data2, name:'企业总数',
				                      label:{
				                        normal:{
				                          padding:[0,-65,0,0]
				                        }
				                      }
				                    }
				                    
				                ]
				            }
				        ]
				    };
				            myChart.setOption(option)
			}
		var basePath = '${pageContext.request.contextPath}';
		
		
		
		function turnToOutCompare()
		{
			var year;
			var season;
			var month;
			 if($("#company_time").text()!=""){
			
				 year=$("#company_time").text().slice(0,4);
				 season=$("#company_time").text().slice(5,6);
			}else{
				var nowdate = new Date();
				year=nowdate.getFullYear();    //获取完整的年份(4位,1970-????)
				month=nowdate.getMonth()+1;       //获取当前月份(0-11,0代表1月)
				if(month==1){
					month=12;
					year=year-1;
				}else
					month=month-1;
				season=	Math.ceil(month/4)+1
			} 	
			window.open('${pageContext.request.contextPath}/outcompare/page?year='+year+'&season='+season,'_blank')
		}
		function industryCommerce(){
			var date;
			if($("#icbc_time").text()!=""){
                var date = $("#icbc_time").text();
			}else{
                var myDate = new Date();//获取系统当前时间
                var nowYear = myDate.getFullYear();//�?
                var nowmonth = myDate.getMonth()+1;//�?
			    date = nowYear+'-'+nowmonth;
			}
            window.open('${pageContext.request.contextPath}/index/analysis?date='+date,'_blank');
		}
		function turnToFinancialProperty()
		{
			window.open('${pageContext.request.contextPath}/financialProperty/page','_blank')
		}
		function turnToFinanceRisk(){
			var year;
			 if($("#company_time").text()!=""){			
				 year=$("#company_time").text().slice(0,4);
			}else{
				var nowdate = new Date();
				year=nowdate.getFullYear();    //获取完整的年份(4位,1970-????)
			} 	
			 window.open('${pageContext.request.contextPath}/index/financial_risk?year='+year,'_blank')
		}
	</script>
</body>
</html>