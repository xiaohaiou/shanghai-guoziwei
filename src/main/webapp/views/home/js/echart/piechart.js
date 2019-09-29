/*var Mypie_1 = echarts.init(document.getElementById('pie_1'));
var data = [{
    value:61,
    name: '<10w'
}, {
    value: 57,
    name: '10w-50w'
}, {
    value: 12,
    name: '50w-100w'
}];

var dbHolderStyle = {
    normal : {
        color: 'rgba(255,255,255,0.1)',
        label: {show:false},
        labelLine: {show:false}
    },
    emphasis : {
        show : false,
        color: 'rgba(255,255,255,0.1)',
        label: {show:false},
        labelLine: {show:false}
    }
};
option = {
    tooltip: {
        show: true,
        trigger: 'item',
        formatter: "{b}: {c} \n({d}%)"
    },
    legend: {
        orient: 'horizontal',
        bottom: '0%',
        show:false,
        data: ['<10w', '10w-50w', '50w-100w', '100w-500w', '>500w']
    },
    series: [{
            type:'pie',
            clockWise:false,
            hoverAnimation: false,
            tooltip:{
                show:false,
            },
             radius: ['20', '34'],
            data:[
                {
                    value:0,
                    name:'invisible',
                    itemStyle : dbHolderStyle
                }
            ]
        },{
        type: 'pie',
        selectedMode: 'single',
        radius: ['20', '34'],
        color: ['#d4d5d8', '#cd1c0d', '#3b4048'],
        itemStyle:{
            emphasis:{
                color: 'rgba(255,255,255,0.2)',
            }
        },
        label: {
            normal: {
                    show:false,
                position: 'inner',
                formatter: '{d}%',
                textStyle: {
                    color: '#fff',
                    fontWeight: 'bold',
                    fontSize: 14
                }
            }
        },
        labelLine: {
            normal: {
                show: false
            }
        },
        data: data
    },]
};
Mypie_1.setOption(option);
window.onresize = Mypie_1.resize;*/


function setHTChart(eleid,wys,sum){
var eleobj=document.getElementById(eleid);
if(!eleobj) return;
var HTChartObj = echarts.init(document.getElementById(eleid));
//if(!wys || !sum){
//	console.log("无数据：",data);
//	return;
//}
if(!HTChartObj) {
	console.log("找不到指定元素id：",eleid);
	return;
}
var comPareData=parseFloat(sum - wys);
if(comPareData<0) comPareData=0;
var option = {
    tooltip: {
        trigger: 'item',
        formatter: "{a}{b} : {c} ({d}%)"
    }, xAxis: [{
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
    yAxis: [{
        show: false
    }],
    series: [{
        name: '',
        type: 'pie',
        radius: '27',
        clockwise: false,
        data: [{
            value: comPareData,
            name:"正常数"
        }, {
            value: wys,
            name:"违约数"
        }],
        label: {
            normal: {
                show: false
            }
        },
        labelLine: {
            normal: {
                show: false
            }
        },
        itemStyle: {
            normal: {
            },
        }
    }],
    color: [
        'rgba(255,255,255,0.45)','#00acee'
    ]
};
HTChartObj.setOption(option);
window.onresize = HTChartObj.resize;
}


/**
 * 通用
 * @param eleid
 * @param data
 */
function setHTChart2(eleid,data,dataname,colourlist){
	var eleobj=document.getElementById(eleid);
	if(!eleobj) return;
	var HTChartObj = echarts.init(document.getElementById(eleid));
	if(!data){
		console.log("无数据：",data);
		return;
	}
	if(!HTChartObj) {
		console.log("找不到指定元素id：",eleid);
		return;
	}
	var comPareData=100-parseFloat(data);
	if(comPareData<0) comPareData=0;
	var option = {
			 tooltip: {
			        trigger: 'item',
			        formatter: function(params, ticket, callback) {
			            var res = params.seriesName;
			            if(params.name){
			            	res += '<br/>' + params.name + ' : ' + params.percent + '%';
			            }else{
			            	res="";
			            }
			            
			            return res;
			        }
			    },
			    grid: {
			        bottom: 0,left:0,right:0,top:0,
			    },  
			    xAxis: [{
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
			    yAxis: [{
			        show: false
			    }],
	    series: [{
	        name: dataname,
	        hoverAnimation: false,
	        type: 'pie',
	        center:['50%','50%'],
	        radius: [
			            '37',
			            '21'
			        ],
	        data: [ {
	            value: data,
	            name:dataname
	        },{
	            value: comPareData,
	            name:""
	        }],
	        label: {
	            normal: {
	                show: false
	            }
	        },
	        labelLine: {
	            normal: {
	                show: false
	            }
	        },
	        itemStyle: {
	            normal: {
	            },
	        }
	    }],
	    color:colourlist
	};
	HTChartObj.setOption(option);
	window.onresize = HTChartObj.resize;
	}
