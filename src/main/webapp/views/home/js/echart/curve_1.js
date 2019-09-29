/**
 * 加载行政和人数首页图表
 * @param eleid
 * @param lengenddata
 * @param xAxisdata
 * @param seriesdata
 * @returns
 */
function loadXZChart(eleid,lengenddata,xAxisdata,seriesdata){
	var chartele=document.getElementById(eleid);
	if(!chartele) return;
	var curve_1 = echarts.init(document.getElementById(eleid));
	
	console.log(lengenddata,1)
	if(lengenddata[1].indexOf('收入')!=-1){
		var tmp = seriesdata[0];
		seriesdata[0] = seriesdata[1];
		seriesdata[1] = tmp;
		
		var tmp2 = lengenddata[0];
		lengenddata[0] = lengenddata[1];
		lengenddata[1] = tmp2;
	}
	
	if(lengenddata[0].indexOf('要情')!=-1){
		var tmp = seriesdata[0];
		seriesdata[0] = seriesdata[1];
		seriesdata[1] = tmp;
		
		var tmp2 = lengenddata[0];
		lengenddata[0] = lengenddata[1];
		lengenddata[1] = tmp2;
	}
	
	if(lengenddata[1].indexOf('重点报道')!=-1){
		var tmp = seriesdata[0];
		seriesdata[0] = seriesdata[1];
		seriesdata[1] = tmp;
		
		var tmp2 = lengenddata[0];
		lengenddata[0] = lengenddata[1];
		lengenddata[1] = tmp2;
	}
	console.log(lengenddata,2)
	var spllengdata=lengenddata[1];
	var yqcllengdata=lengenddata[0];
	var spldata=seriesdata[1];
	var yqcdata=seriesdata[0];
	//alert(JSON.stringify(spldata)+"==="+JSON.stringify(yqcdata))
	var option = {
			   tooltip : {
			        trigger: 'axis',
			        axisPointer: {
			            type: 'cross',
			            label: {
			                backgroundColor: '#6a7985'
			            }
			        },
			        //formatter:'{b}<br/>{a0}:{c0}<br/>{a1}:{c1}'
			    },			
    color: ["#c23531","#32688a"],		
    legend: {
        icon:'circle',
        bottom:0,
        //data:['保密风险事件数量','公文审批及时率','要情处理及时率']
         data:[
            {
                name:spllengdata,
                textStyle:{
                    fontSize:12,
                    fontWeight:'bolder',
                    color:'#fff',
                    borderWidth:2,
                    borderColor:'#fff',
                },
                //icon:'circle'
                //icon:'image:${pageContext.request.contextPath}/views/home/img/point3.png',
            },
            {
                name:yqcllengdata,
                textStyle:{
                    fontSize:12,
                    fontWeight:'bolder',
                    color:'#fff',
                    borderWidth:2,
                    borderColor:'#fff',
                },
                //icon:'image:${pageContext.request.contextPath}/views/home/img/point2.png',
            },
            
        ]
    },
    grid: {
        left: '10%',
        right: '4%',
        top:'2%',
        containLabel: false,
    },
    
    xAxis : [
        {
            type : 'category',
            splitLine:{
                show:true,
                lineStyle:{
                       color: 'rgba(255,255,255,0.2)',
                },
            },
            axisLabel:{  
                textStyle:{
                       color: '#484f58',
                       fontSize:'14'
                },
            },
            boundaryGap : false,
            data : xAxisdata
        }
    ],
    yAxis : [
        {
            type : 'value', color:'#000',
            splitLine:{
                show:true,
                lineStyle:{
                       color: 'rgba(255,255,255,0.2)',
                },
            },
            axisLabel:{
                textStyle:{
                        color: '#484f58',
                        fontSize:'10'
                },  
            },
        }
    ],
    series : [
            {
                name:yqcllengdata,
                type:'line',
                smooth:true,
                symbol: 'circle',
    	        symbolSize: 5,
    	        showSymbol: false,
    	        areaStyle: {normal: { color:'rgba(240,132,129,0.4)',}},
                lineStyle:{
                     normal:{
                     	
                         color:'rgba(255,255,255,0.2)',
                         },
                },
                data:yqcdata
            }, {
                name:spllengdata,
                smooth:true,
                type:'line',
                symbol: 'circle',
    	        symbolSize: 5,
    	        showSymbol: false,
    	        areaStyle: {normal: {
                    color:'rgba(0,160,233,0.6)',
                }},

                lineStyle:{
                     normal:{
                         color:'rgba(255,255,255,0.2)',
                         },
                },
                data:spldata
                }
    ]
};
	curve_1.setOption(option);
	window.onresize = curve_1.resize;
	/*window.addEventListener('resize',curve_1.resize.bind(curve_1));
	setTimeout(curve_1.resize.bind(curve_1),2000)*/
}



/**
 * 加载行政和人数首页图表(双轴图)
 * @param eleid
 * @param lengenddata
 * @param xAxisdata
 * @param seriesdata
 * @returns
 */
function loadXZChart2(eleid,lengenddata,xAxisdata,seriesdata){
	var curve_1 = echarts.init(document.getElementById(eleid));
	if(lengenddata[1].indexOf('收入')!=-1){
		var tmp = seriesdata[0];
		seriesdata[0] = seriesdata[1];
		seriesdata[1] = tmp;
		
		var tmp2 = lengenddata[0];
		lengenddata[0] = lengenddata[1];
		lengenddata[1] = tmp2;
	}
	var spllengdata=lengenddata[1];
	var yqcllengdata=lengenddata[0];
	//alert("spllengdata="+spllengdata+"yqcllengdata="+yqcllengdata)
	var  spldata=seriesdata[1];
	var yqcdata=seriesdata[0];
	//alert(JSON.stringify(spldata)+"==="+JSON.stringify(yqcdata))
	var option = {
			 tooltip : {
			        trigger: 'axis',
			        axisPointer: {
			            type: 'cross',
			            label: {
			                backgroundColor: '#6a7985'
			            }
			        },
			        //formatter:'{b}<br/>{a0}:{c0}<br/>{a1}:{c1}'
			    },		
    color: ["#c23531","#32688a"],
    legend: {
        icon:'circle',
        bottom:0,
        //data:['保密风险事件数量','公文审批及时率','要情处理及时率']
         data:[
            {
                name:yqcllengdata,
                textStyle:{
                    fontSize:12,
                    fontWeight:'bolder',
                    color:'#fff',
                    borderWidth:2,
                    borderColor:'#fff',
                },
                icon:'circle'
                //icon:'image:${pageContext.request.contextPath}/views/home/img/point2.png',
            },
            {
                name:spllengdata,
                textStyle:{
                    fontSize:12,
                    fontWeight:'bolder',
                    color:'#fff',
                    borderWidth:2,
                    borderColor:'#fff',
                },
                icon:'circle'
                //icon:'image:${pageContext.request.contextPath}/views/home/img/point3.png',
            },
            
        ]
    },
    grid: {
        left: '10%',
        right: '4%',
        top:'2%',
        containLabel: false,
    },
    
    xAxis : [
        {
            type : 'category',
            splitLine:{
                show:true,
                lineStyle:{
                       color: 'rgba(255,255,255,0.2)',
                },
            },
            axisLabel:{  
                textStyle:{
                       color: '#484f58',
                       fontSize:'14'
                },
            },
            boundaryGap : false,
            data : xAxisdata
        }
    ],
    yAxis : [
        {
            type : 'value', color:'#000',
            splitLine:{
                show:true,
                lineStyle:{
                       color: 'rgba(255,255,255,0.2)',
                },
            },
            axisLabel:{
                textStyle:{
                        color: '#484f58',
                        fontSize:'10'
                },  
            },
        }
    ],
    series : [
       
        {
            name:yqcllengdata,
            type:'line',
           
            smooth:true,	        
            symbol: 'circle',
	        symbolSize: 5,
	        showSymbol: false,
	        areaStyle: {normal: { color:'rgba(240,132,129,0.4)',}},
            lineStyle:{
                 normal:{
                 	
                     color:'rgba(255,255,255,0.2)',
                     },
            },
            data:yqcdata
        }, {
            name:spllengdata,
        
            smooth:true,
            type:'line',	        
            symbol: 'circle',
	        symbolSize: 5,
	        showSymbol: false,
	        areaStyle: {normal: {
                color:'rgba(0,160,233,0.6)',
            }},

            lineStyle:{
                 normal:{
                     color:'rgba(255,255,255,0.2)',
                     },
            },
            data:spldata
            }
        
    ]
};
	curve_1.setOption(option);
	window.onresize = curve_1.resize;
}

/**
 * 加载社责首页图表
 * @param eleid
 * @param lengenddata
 * @param xAxisdata
 * @param seriesdata
 * @returns
 */
function loadSZChart(eleid,lengenddata,xAxisdata,seriesdata){
	var chartele=document.getElementById(eleid);
	if(!chartele) return;
	var curve_1 = echarts.init(document.getElementById(eleid));
	
	console.log(lengenddata,1)
	if(lengenddata[1].indexOf('舆情总数')!=-1){
		var tmp = seriesdata[0];
		seriesdata[0] = seriesdata[1];
		seriesdata[1] = tmp;
		var tmp2 = lengenddata[0];
		lengenddata[0] = lengenddata[1];
		lengenddata[1] = tmp2;
	}

	console.log(lengenddata,2)



	var spllengdata=lengenddata[1];
	var yqcllengdata=lengenddata[0];
	var spldata=seriesdata[1];
	var yqcdata=seriesdata[0];
	var option = {
			   tooltip : {
			        trigger: 'axis',
			        axisPointer: {
			            type: 'cross',
			            label: {
			                backgroundColor: '#6a7985'
			            }
			        },
			        //formatter:'{b}<br/>{a0}:{c0}<br/>{a1}:{c1}'
			    },			
    color: ["#c23531","#32688a"],	
    legend: {
        icon:'circle',
        bottom:0,
        data:[
            {
                name:spllengdata,
                textStyle:{
                    fontSize:12,
                    fontWeight:'bolder',
                    color:'#fff',
                    borderWidth:2,
                    borderColor:'#fff',
                },
                //icon:'circle'
                //icon:'image:${pageContext.request.contextPath}/views/home/img/point3.png',
            },
            {
                name:yqcllengdata,
                textStyle:{
                    fontSize:12,
                    fontWeight:'bolder',
                    color:'#fff',
                    borderWidth:2,
                    borderColor:'#fff',
                },
                //icon:'image:${pageContext.request.contextPath}/views/home/img/point2.png',
            },
            
        ]
    },
    grid: {
        left: '10%',
        right: '4%',
        top:'2%',
        containLabel: false,
    },
    
    xAxis : [
        {
            type : 'category',
            splitLine:{
                show:true,
                lineStyle:{
                       color: 'rgba(255,255,255,0.2)',
                },
            },
            axisLabel:{  
                textStyle:{
                       color: '#484f58',
                       fontSize:'14'
                },
            },
            boundaryGap : false,
            data : xAxisdata
        }
    ],
    yAxis : [
        {
            type: 'log',color:'#000',
            axisLabel: {
                formatter: '{value}'
            },
            splitLine:{
                show:true,
                lineStyle:{
                       color: 'rgba(255,255,255,0.2)',
                },
            },
            axisLabel:{
              textStyle:{
                      color: '#484f58',
                      fontSize:'10'
              },  
          },
        }
//        {
//            type : 'value', color:'#000',
//            splitLine:{
//                show:true,
//                lineStyle:{
//                       color: 'rgba(255,255,255,0.2)',
//                },
//            },
//            axisLabel:{
//                textStyle:{
//                        color: '#484f58',
//                        fontSize:'10'
//                },  
//            },
//        }
    ],
    series : [
            {
                name:yqcllengdata,
                type:'line',
                smooth:true,
                symbol: 'circle',
    	        symbolSize: 5,
    	        showSymbol: false,
    	        areaStyle: {normal: { color:'rgba(240,132,129,0.4)',}},
                lineStyle:{
                     normal:{
                         color:'rgba(255,255,255,0.2)',
                     },
                },
                //yAxisIndex: 1,
                data:yqcdata
            },
            {
                name:spllengdata,
                smooth:true,
                type:'line',
                symbol: 'circle',
    	        symbolSize: 5,
    	        showSymbol: false,
    	        areaStyle: {normal: {
                    color:'rgba(0,160,233,0.6)',
                	}
    	        },
                lineStyle:{
                     normal:{
                         color:'rgba(255,255,255,0.2)',
                     },
                },
                data:spldata
             }
    ]
};
	curve_1.setOption(option);
	window.onresize = curve_1.resize;
	/*window.addEventListener('resize',curve_1.resize.bind(curve_1));
	setTimeout(curve_1.resize.bind(curve_1),2000)*/
}

/*var curve_2= echarts.init(document.getElementById('cruve_2'));
option = {
    tooltip: {
        trigger: 'axis'
    },
    xAxis:  {
        type: 'category',
        boundaryGap: false,
        show:false,
        data: ['1月','2月','3月','4月','5月']
    },
    yAxis: {
        show:false,
        type: 'value',
        axisLabel: {
            show:false,
            formatter: '{value} %'
        }
    },
    series: [
        {
            name:'2017年',
            symbolSize:8,
            type:'line',
            data:[11, 21, 15, 43, 62, ],
            itemStyle:{
              normal:{
                  color:'#000',fontSize:20,
                   lineStyle:{
                   	width:1,//折线宽度
                    color:'rgba(255,255,255,0.8)',//折线颜色
                   },
              }  
            },
        },
       
    ]
};

curve_2.setOption(option);
window.onresize = curve_2.resize;
*/

/*var curve_3 = echarts.init(document.getElementById('cruve_3'));
option = {
    legend: {
        icon:'circle',
        bottom:0,
        //data:['保密风险事件数量','公文审批及时率','要情处理及时率']
         data:[
            {
                name:'保密风险事件数量',
                textStyle:{
                    fontSize:12,
                    fontWeight:'bolder',
                    color:'#fff',
                    borderWidth:2,
                    borderColor:'#fff',
                },
                //icon:'circle'
                icon:'image://./img/point1.png',
            },
            {
                name:'公文审批及时率',
                textStyle:{
                    fontSize:12,
                    fontWeight:'bolder',
                    color:'#fff',
                    borderWidth:2,
                    borderColor:'#fff',
                },
                icon:'image://./img/point2.png',
            },
            {
                name:'要情处理及时率',
                textStyle:{
                    fontSize:12,
                    fontWeight:'bolder',
                    color:'#fff',
                    borderWidth:2,
                    borderColor:'#fff',
                },
                icon:'image://./img/point3.png',
            }
        ]
    },
    grid: {
        left: '6%',
        right: '4%',
        top:'2%',
        containLabel: false,
    },
    
    xAxis : [
        {
            type : 'category',
            splitLine:{
                show:true,
                lineStyle:{
                       color: 'rgba(255,255,255,0.2)',
                },
            },
            axisLabel:{  
                textStyle:{
                       color: '#484f58',
                       fontSize:'14'
                },
            },
            boundaryGap : false,
            data : ['1月','2月','3月','4月','5月','6月','7月']
        }
    ],
    yAxis : [
        {
            type : 'value', color:'#000',
            splitLine:{
                show:true,
                lineStyle:{
                       color: 'rgba(255,255,255,0.2)',
                },
            },
            axisLabel:{
                textStyle:{
                        color: '#484f58',
                        fontSize:'10'
                },  
            },
        }
    ],
    series : [
        {
            name:'公文审批及时率',
            smooth:true,
            type:'line',
	        symbol: 'circle',
	        symbolSize: 5,
	        showSymbol: false,
            areaStyle: {normal: { color:'rgba(240,132,129,0.3)',}},
            lineStyle:{
                 normal:{
                     color:'rgba(255,255,255,0.2)',
                     },
            },
            data:[50, 32, 21, 54, 90, 30, 10]
            },
        {
            name:'要情处理及时率',
            type:'line',
            smooth:true,
	        symbol: 'circle',
	        symbolSize: 5,
	        showSymbol: false,
            areaStyle: {normal: {
                color:'rgba(0,160,233,0.3)',
            }},
            lineStyle:{
                 normal:{
                 	
                     color:'rgba(255,255,255,0.2)',
                     },
            },
            data:[20, 32, 76, 34, 40,30, 20]
        }
    ]
};

curve_3.setOption(option);
window.onresize = curve_3.resize;
*/




/**
 * 
 * 加载首页的Curve数据
 * @param eleid  元素位置
 * @param titledata 标题数据
 * @param valuedata 存储数据
 * @param valuekey  表名 根据这个从标题集合titledata获取属于表名的字段
 */
function setIndexCurveChart(eleid,titledata,valuedata,valuekey){
	if(!document.getElementById(eleid)) return;
	var curveObj = echarts.init(document.getElementById(eleid));	
	var sorttitledata=new Array();
	sorttitledata=getIndexByKey(titledata,valuekey)
	var time_type=sorttitledata[0].showtab_time_type ;//同一个表的时间类型都一样 取第一个数据就行
	var xAxisdata=getxAxis(time_type,valuedata);
	var seriesdata=getSeriesData(sorttitledata,valuedata);
	var lengenddata="";
	lengenddata=getlengenddata(sorttitledata);
	var seriesChdata=getSeriesChData(sorttitledata,seriesdata);
	var option = {

		   /* tooltip: {
					    trigger: 'item',           // 触发类型，默认数据触发，见下图，可选为：'item' ¦ 'axis'
						showDelay: 20,             // 显示延迟，添加显示延迟可以避免频繁切换，单位ms
						hideDelay: 100,            // 隐藏延迟，单位ms
					    transitionDuration : 0.4,  // 动画变换时间，单位s
						backgroundColor: 'rgba(0,0,0,0.7)',     // 提示背景颜色，默认为透明度为0.7的黑色
						borderColor: '#333',       // 提示边框颜色
						borderRadius: 4,           // 提示边框圆角，单位px，默认为4
						borderWidth: 0,            // 提示边框线宽，单位px，默认为0（无边框）
						padding: 5,                // 提示内边距，单位px，默认各方向内边距为5，
						                                   // 接受数组分别设定上右下左边距，同css
						axisPointer : {            // 坐标轴指示器，坐标轴触发有效
						     type : 'line',         // 默认为直线，可选为：'line' | 'shadow'
						     lineStyle : {          // 直线指示器样式设置
						     color: '#48b',
						     width: 2,
						     type: 'solid'
						     },
						     shadowStyle : {                       // 阴影指示器样式设置
						        width: 'auto',                   // 阴影大小
						        color: 'rgba(150,150,150,0.3)'  // 阴影颜色
						            }
						        },
						    textStyle: {
						            color: '#fff'
						        }
						    },	*/	
			 tooltip : {
			        trigger: 'axis',
			        axisPointer: {
			            type: 'cross',
			            label: {
			                backgroundColor: '#6a7985'
			            }
			        },
			        //formatter:'{b}<br/>{a0}:{c0}<br/>{a1}:{c1}'
			    },		
		    legend: {
		        icon:'circle',
		        bottom:0,		      
		         data:lengenddata,
		         textStyle:{    //图例文字的样式
		             color:'white',
		             fontSize:12
		         }
		    },
		    grid: {
		        left: '15%',
		        right: '4%',
		        top:'5%',
		        containLabel: false,
		    },
		    
		    xAxis : [
		        {
		            type : 'category',
		            splitLine:{
		                show:true,
		                lineStyle:{
		                       color: 'rgba(255,255,255,0.2)',
		                },
		            },
		            axisLabel:{  
		                textStyle:{
		                       color: '#484f58',
		                       fontSize:'14'
		                },
		            },
		            boundaryGap : true,
		            data : xAxisdata
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value', color:'#000',
		            splitLine:{
		                show:true,
		                lineStyle:{
		                       color: 'rgba(255,255,255,0.2)',
		                },
		            },
		            axisLabel:{
		                textStyle:{
		                        color: '#484f58',
		                        fontSize:'10'
		                },  
		            },
		        }
		    ],
		    series : seriesChdata
		};
	//alert(JSON.stringify(option))
	curveObj.setOption(option);
	window.onresize = curveObj.resize;	
	if(valuekey=="SHOWTAB_M11") getdubanlineChart(sorttitledata,seriesdata,xAxisdata);	
	//alert("336=="+JSON.stringify(seriesdata))
} 

/**
 * 根据表名过滤表字段
 * @param titledata
 * @param valuekey
 * @returns {Array}
 */
function getIndexByKey(titledata,valuekey){
	var sorttitledata=new Array();
	for(var i =0;i<titledata.length; i++){
		var storetab_name=titledata[i].storetab_name;
		if(storetab_name==valuekey){
			sorttitledata.push(titledata[i]);
		}
	}
	return sorttitledata;
}




/**
 * 获取x轴展示纬度
 * @param time_type 时间类型
 * 
 */
function getxAxis(time_type,valuedata){
	var time_key="date_m";
	var time_uom="月"
	if(time_type=="1"){time_key="date_w";time_uom="周";}
	if(time_type=="2"){time_key="date_m";time_uom="月";}
	if(time_type=="3"){time_key="date_s";time_uom="季";}
	if(time_type=="4"){time_key="date_y";time_uom="年";}
	if(time_type=="5"){time_key="date_d";time_uom="天";}
	var xAxisdata=new Array();
	for(var i =0;i<valuedata.length; i++){
	       var x_Axis=valuedata[i][time_key]+time_uom;
	       if($.inArray(x_Axis, xAxisdata)==-1){
	    	   xAxisdata.push(x_Axis);
	       }
	 }
	return xAxisdata;
}

/**
 * 获得坐标轴群组数据
 * @param sorttitledata
 * @param valuedata
 */
function getSeriesData(sorttitledata,valuedata){
	var dates=new Array();
	for(var i =0;i<sorttitledata.length; i++){
		var key=(sorttitledata[i].int_storetab_field).toLowerCase();
		var date=getSeriesDataBySeriesName(key,valuedata);
		dates.push(date);
	}
	return dates;
}

/**
 * 根据坐标轴名获取对应的坐标轴数据
 * @param SeriesName 目录名
 * @param valuedata  表对应数据集
 * @param xAxisval   对应的x轴数据
 */
function getSeriesDataBySeriesName(SeriesName,valuedata){
	var date=new Array();
	for(var i =0;i<valuedata.length; i++){
		var dataval=parseFloat(valuedata[i][SeriesName]);
		if(!dataval) dataval=0;
		date.push(dataval);		
	}
	return date;
}

function getlengenddata(sorttitledata){
	var lengend=new Array();
	for(var i =0;i<sorttitledata.length; i++){
		var lengenddata={};
		lengenddata["name"]=sorttitledata[i].index_nm;	
		var lecolor=getAreaStylecolour(i)
		var textStyle={
		        fontSize:12,
		        fontWeight:'bolder',
		        color:'#fff',
		        borderWidth:2,
		        borderColor:lecolor,
		    }
		lengenddata["textStyle"]=textStyle;
		lengend.push(lengenddata);
	}
	return lengend;
}

function getSeriesChData(sorttitledata,seriesdata){
	var SeriesCh=new Array();
	for(var i =0;i<seriesdata.length; i++){
		var serieschdata={};
		var name=sorttitledata[i].index_nm;
		serieschdata["name"]=name;
		serieschdata["smooth"]=true;
		var type='bar';
		/*if(i%3==1){
			type='line'
		}*/
		serieschdata["type"]=type;
		serieschdata["symbol"]='circle';
		serieschdata["symbolSize"]=5;
		serieschdata["showSymbol"]=false;
		var areaStylecolor=getAreaStylecolour(i);	
			serieschdata["areaStyle"]={normal: {  opacity :"0.5"}};			
		serieschdata["lineStyle"]={
					                normal:{					                  
					                    opacity :"0.8"
					                    }
					              };
		serieschdata["data"]=seriesdata[i];
		SeriesCh.push(serieschdata);
	}
	return SeriesCh;
}
/**
 * 获得统一颜色系
 */
function getAreaStylecolour(i){
	var   colors=[ '#E066FF','#FF3E96','#BCEE68','#8EE5EE','#8470FF', '#008B45', '#24CBE5', '#912CEE', '#FF9655', 
					  '#9ACD32', '#98FB98','#058DC7','#50B432'] 
	var pos=(i%14);
	var color=colors[pos];
	return color;
}

/**
 * 利用SHOWTAB_M11柱状图中的数据加载SHOWTAB_M11（行政督办）折线图
 */
function getdubanlineChart(sorttitledata,seriesdata,xAxisdata){
	  var sortseriesdata=new Array();
	  for(var i =0;i<sorttitledata.length; i++){
			var index_filed=sorttitledata[i].int_storetab_field;
			if(index_filed=="A00015"){ //A00015 行政督办办结率 参考数据字段
				sortseriesdata=seriesdata[i];
				break;
			}
		}
	 var eleid="xzdbbjl";//行政督办办结率折线图显示用div的id
	 getlineChart(eleid,xAxisdata,sortseriesdata,"行政督办办结率");
}

Array.prototype.max = function(){ 
	return Math.max.apply({},this) 
} 
	Array.prototype.min = function(){ 
	return Math.min.apply({},this) 
} 

/**
 * 
 * @param eleid 加载元素位置
 * @param xAxis  x轴数据
 * @param series  坐标系数据
 * @param seriesname 坐标系名字
 */
function getlineChart(eleid,xAxis,seriesdata,seriesname){
	var lineObj= echarts.init(document.getElementById(eleid));
	var yAxisMin=parseInt(seriesdata.min());
	var yAxisMaX=parseInt(seriesdata.max());
	var option = {
	    tooltip: {
	        trigger: 'axis',
	        formatter: function(params, ticket, callback) { //提示框浮层内容格式器
	            var res = params[0].seriesName;
	            res += '<br/>' + params[0].name + ' : ' + params[0].value + '%';
	            return res;
	        },
	        position: function(point, params, dom) { 
                var posDis = window.innerWidth - dom.offsetWidth; 
                return posDis<point[0]?[posDis, '10%']:[point[0], '60%'];

            },
	    },
	    xAxis:  {
	        type: 'category',
	        boundaryGap: false,
	        show:false,
	        data: xAxis
	    },
	    yAxis: {
	        show:false,
	        type: 'value',
	        min:yAxisMin,
	        max:yAxisMaX,
	        axisLabel: {
	            show:false,
	            formatter: seriesname+'{value} %'
	        }
	    },
	    series: [
	        {
	            name:seriesname,
	            symbolSize:8,
	            type:'line',
	            data:seriesdata,
	            itemStyle:{
	              normal:{
	                  color:'#000',fontSize:20,
	                   lineStyle:{
	                   	width:1,//折线宽度
	                    color:'rgba(255,255,255,0.8)',//折线颜色
	                   },
	              }  
	            },
	        },
	       
	    ]
	};
	
	lineObj.setOption(option);
	window.onresize = lineObj.resize;
}


/**
 * 首页6率悬浮后进行中间的折线图切换
 */
function createBLLineChart(eleid,xAxisdata,seriesdata,ymax,ymin){
	ymin=parseInt(ymin)
	var BLLineObj = echarts.init(document.getElementById(eleid));
	var option = {
	    title: {
	        text: '请求数',
	        show:false,
	        textStyle: {
	            fontWeight: 'normal',
	            fontSize: 16,
	            color: '#F1F1F3'
	        },
	        left: '0%'
	    },
	    tooltip: {
	        trigger: 'axis',
	        axisPointer: {
	            lineStyle: {
	                color: '#233545'
	            }
	        }
	    },
	    legend: {
	        icon: 'rect',
	        itemWidth: 14,
	        itemHeight: 5,
	        itemGap: 13,
	        right: '0%',
	        textStyle: {
	            fontSize: 12,
	            color: '#F1F1F3'
	        }
	    },
	     grid: {
	        left: '-2%',
	        right: '0%',
	        bottom: '0%',
	        containLabel: true
	    },
	    xAxis: [{
	        axisLabel:{
	            margin:-16,
	        },
	        type: 'category',
	        boundaryGap: false,
	        axisLine: {
	            lineStyle: {
	            fontSize: 32,
	            color:'#fff'
	            }
	        },
	        data: xAxisdata
	    }],
	    yAxis: [{
	        type: 'value',
	        name: '单位（%）',
	        min:ymin,
	        max:ymax,
	        show:false,
	        axisTick: {
	            show: false
	        },
	        axisLine: {
	            lineStyle: {
	                color: '#233545'
	            }
	        },
	        axisLabel: {
	            margin: -10,
	            textStyle: {
	                fontSize: 14
	            }
	        },
	        splitLine: {
	            lineStyle: {
	                color: '#233545'
	            }
	        }
	    }],
	    series:seriesdata
	};

	BLLineObj.setOption(option,true);
	window.onresize = BLLineObj.resize;

}