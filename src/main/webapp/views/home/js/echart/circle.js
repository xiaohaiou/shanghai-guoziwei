
var colorlist=['#669ce0','#48b8b1','#cd1c0d','#e066a8','#ffffff','#48b8b1','#669ce0','#FF7F50','#87CEFA']
var colorpos=0;
var specialindex=['A00003','A00004','A00008']
/**
 * 用于计算八率加载的环形图 其中元素单位%和非%计算方式和加载方式不一样
 * @param eleid   加载元素的id
 * @param spanid 加载元素的名称的spanid
 * @param data    加载元素的数据  
 */
function setcircle(eleid,spanid,data){
	var circleObj = echarts.init(document.getElementById(eleid));
	if(!circleObj) {
		console.log("找不到指定元素id：",eleid);
		return;
	}
	var circleName= data["index_nm"];
	var cicleSelfVal=  data["index_val"];
	if(isNaN(cicleSelfVal)){
		console.log("数据类型错误 非数字",cicleSelfVal);
		return;
	}
	var uom= data["uom"];
	var uomshowval="";
	if(uom) {
		uomshowval=uom;
	}
	var datalable={
			normal: {
                formatter: parseFloat(cicleSelfVal)+""+uomshowval,
                position: 'center',
                show: true,
                textStyle: {
                    fontSize: '22',
                    color: '#fff'
                }
            }			
	};
	var cicleCompareVal=0;//用于率加载环形数据用
	var paramsname="数值"; //悬浮提示框参数名
	if(uom&&hasPercent(uom)){
		cicleCompareVal=100-parseFloat(cicleSelfVal);
		if(cicleCompareVal<0) cicleCompareVal=0 //有时候比例超过百分之100 图形只能显示100%
		paramsname="比率"; 
		datalable={
	            normal: {
	                formatter: '{d} %',
	                position: 'center',
	                show: true,
	                textStyle: {
	                    fontSize: '22',
	                    color: '#fff'
	                }
	            }
	        }
	}
	var itemcolor=colorlist[colorpos]; //环状条颜色
	colorpos++;
	if(colorpos>8) colorpos=0;//循环赋色
	
	var option = {
		    tooltip: { //提示框组件
		        trigger: 'item', //触发类型(饼状图片就是用这个)
		        formatter: function(params, ticket, callback) { //提示框浮层内容格式器
		            var res = params.seriesName;
		            if(uom&&hasPercent(uom)){
		            res += '<br/>' + params.name + ' : ' + params.percent + '%';
		            }else{
		            res += '<br/>' + params.name + ' : ' + cicleSelfVal+""+uomshowval;
		            }
		            return res;
		        }
		    },
		    grid: { //网格
		        bottom: 0,left:0,right:0,top:0,
		    },
		    xAxis: [{  //X坐标
		        type: 'category',
		        show:false,
		        axisLine: {
		            show: false
		        },
		        axisTick: {
		            show: false
		        },
		        axisLabel: {
		            interval: 0
		        },
		        data: ['']
		    }],
		    yAxis: [{ //y坐标
		        show: false
		    }],
		    series: [{
		        name: circleName, hoverAnimation: false,
		        radius: [
		            '60',
		            '55'
		        ],
		        type: 'pie',
		        labelLine: {
		            normal: {
		                show: false
		            }
		        }, 
		        itemStyle: {
		                normal: {
		                    color: itemcolor
		                },
		          },
		        data: [{
		            value: cicleSelfVal,
		            name:  paramsname,		            
		            label: datalable
		        }, {
		            value: cicleCompareVal,
		            name: '',
		            tooltip: {
		                show: false
		            },
		            itemStyle: {
		                normal: {
		                    color: 'rgba(255,255,255,0.1)'
		                },
		                emphasis: {
		                     color: 'rgba(255,255,255,0.1)'
		                }
		            },
		            hoverAnimation: false
		        }]
		    }, ]
		};
	    circleObj.setOption(option);
		window.onresize = circleObj.resize;	
		var spanObj=$('#'+spanid); 
		if(spanObj) {
			var updatetime=data["updatetime"]; //更新时间
			var timetype=data["time_type"]; //时间类型
			var formattime=formatDate(updatetime,timetype);
			if(circleName.indexOf('-')>0){ //处理部分带-的指标显示问题
				var circleNamebeg=circleName.substring(0,circleName.indexOf('-'));
				var circleNameend=circleName.substring(circleName.indexOf('-')+1);
				var circleShowName=circleNamebeg+"<br/>"+circleNameend;
				var circleeles=formattime+circleShowName+"("+uom+")";
				spanObj.html(circleeles);
			}else{
			//	circleName+="(2016.06)"
				//var circleShowName=formattime+"-"+circleName+"("+uom+")";
				var circleShowName=circleName+"("+uom+")<sub>"+formattime+"</sub>"
				
				
				spanObj.html(circleShowName);
			}
			
			
		}
	
}

/**
 * 将更新时间格式化成 如201705-2017.5月  201724-2017.24周
 * @param updatetime 更新时间
 * @param timetype  时间类型 1周 2月 3季度 4年
 */
function formatDate(updatetime,timetype){
	var formatDateStr="";
	var year=updatetime.substring(0,4);
	if(timetype==1) {
		var week=updatetime.substring(4,6);
		formatDateStr=year+"年第"+week+"周";
	}else if(timetype==2){
		var month=updatetime.substring(4,6);
		formatDateStr=year+"年"+parseInt(month)+"月";	
	}else{
		formatDateStr=updatetime; //没写完 不过6率只有周月数据
	}
	return  formatDateStr;
}


/**
 * 判断字符串是否存在百分号
 * @param str
 */
function hasPercent(str){
	if(!str) return false;
	var reg=/%+/;
	var boolean=true;
	if(reg.test(str)){
		boolean=true;
	}else{
		boolean=false;
	}	
	return boolean;
}

/**
 * 设置八率特殊的图形1
 */
function setSpecialChart1(eleid){
	var circlOBj = echarts.init(document.getElementById(eleid));
	option = {
	    grid: {
	        left: '-15%',
	        right: '0%',
	        bottom: '0%',
	        containLabel: true
	    },
	    xAxis : [
	        {
	            show:false,
	            type : 'category',
	            boundaryGap : false,
	            data : ['周一','周二','周三','周四']
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	            show:false,
	        }
	    ],
	    series : [
	        {
	            name:'邮件营销',
	            type:'line',
	            stack: '总量',
	            showSymbol:false,
	            smooth:true,
	            areaStyle: {normal: {}},
	            data:[60, 62, 74, 84,],
	            itemStyle: {
	                normal: {
	                    color: '#fff'
	                }
	            },
	            areaStyle: {
	                normal: {
	                    color:'#e066a8',
	                }
	            },
	        },
	        {
	            name:'邮件营销',
	            type:'line',
	            stack: '总量',
	            showSymbol:false,
	            smooth:true,
	            areaStyle: {normal: {}},
	            data:[46, 82, 84, 89,],
	            itemStyle: {
	                normal: {
	                    color: '#fff'
	                }
	            },
	            areaStyle: {
	                normal: {
	                    color:'#669ce0',
	                }
	            },
	        }
	    ]
	};
	circlOBj.setOption(option);
	window.onresize = circlOBj.resize;
}

/**
 * 设置八率特殊的图形2
 */
function setSpecialChart2(eleid){
	var circlOBj = echarts.init(document.getElementById(eleid));
	var option = {
	
		    grid: {
		        left: '-15%',
		        right: '0%',
		        bottom: '0%',
		        containLabel: true
		    },
		
		    xAxis : [
		 	        {
		 	            show:false,
		 	            type : 'category',
		 	            boundaryGap : false,
		 	            data : ['周一','周二','周三','周四']
		 	        }
		 	    ],
		 	   yAxis : [
		 		        {
		 		            type : 'value',
		 		            show:false
		 		        }
		 		    ],
		    series : [
		        {
		            name:'邮件营销1',
		            type:'line',
		            stack: '总量',
		            symbol:'',
		            showSymbol:false,
		            smooth:true,
		            itemStyle: {
		                normal: {
		                    color: '#fff'
		                }
		            },
		            areaStyle: {
		                normal: {
		                    color:'#669ce0',
		                }
		            },
		            data:[46, 82, 84, 89,]
		        },
		        
		        {
		        	 name:'邮件营销2',
		            type:'line',
		            stack: '总量',
		            symbol:'',
		            showSymbol:false,
		            smooth:true,
		            itemStyle: {
		                normal: {
		                    color: '#fff'
		                }
		            },
		            areaStyle: {
		                normal: {
		                    color:'#e066a8',
		                }
		            },
		            data:[60, 62, 74, 84,]
		        },
		      
		    ]
		};
	circlOBj.setOption(option);
	window.onresize = circlOBj.resize;
}


/**
 * 处理总人数的环图
 * @param eleid
 * @param spanid
 * @param data
 */
function setcircle2(eleid, data1, data2, data1Name, data2Name, seriosname) {
	var circleObj = echarts.init(document.getElementById(eleid));

	var option = {
		tooltip: {
			trigger: 'item',
			formatter: "{b}: {c} ({d}%)"
		},
		color: ['white', 'red', 'yellow', 'blueviolet'],
		grid: { //网格
			bottom: 0,
			left: 0,
			right: 0,
			top: 0,
			zlevel:2,
		},
		graphic: [{ // 一个图形元素，类型是 text，指定了 id。
			type: 'text',
			style:{
		    	text:data2Name+data2+"人",
		    	font: '12px "微软雅黑",monospace',
		    	fill:'grey',

			},
			left:115,
			top:30,
			zlevel:2,
		},{ // 一个图形元素，类型是 text，指定了 id。
			type: 'text',
			style:{
		    	text:data1Name+data1+"人",
		    	font: '12px "微软雅黑",monospace',
		    	fill:'grey',
			},
			bottom:35,
			left:115,
		}],series: [{
			name: seriosname,
			center: ['30%', '50%'],
			type: 'pie',
			radius: ['30%', '70%'],
			avoidLabelOverlap: false,
			label: {
				normal: {
					show: false,
					position: 'insideTopLeft',
					formatter: "{b}",
					/*textStyle:{
		                        	 color:'#fff'
		                         }*/

				}
			},
			labelLine: {
				normal: {
					show: false
				}
			},
			data: [{
				value: data1,
				name: data1Name
			},
			{
				value: data2,
				name: data2Name
			},
			]
		}]
	};
	circleObj.setOption(option);
	window.onresize = circleObj.resize;

}