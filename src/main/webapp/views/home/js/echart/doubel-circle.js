/*var circle_7 = echarts.init(document.getElementById('Dou_Circle_1'));
var dataStyle = { 
    normal: {
        label: {show:false},
        labelLine: {show:false},
        shadowBlur: 1,
        shadowColor: 'rgba(40, 40, 40, 0.5)',
    }
};
var placeHolderStyle = {
    normal : {
        color: 'rgba(255,255,255,0)',
        label: {show:false},
        labelLine: {show:false}
    },
    emphasis : {
        color: 'rgba(255,255,255,0)',
    }
};
var dbHolderStyle = {
    normal : {
        color: 'rgba(255,255,255,0.1)',
        label: {show:false},
        labelLine: {show:false}
    },
    emphasis : {
        color: 'rgba(255,255,255,0.1)',
        label: {show:false},
        labelLine: {show:false}
    }
};
option = {
   color: ['#43a1be', '#cc8e13'],
    tooltip : {
        show: true,
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        itemGap:12,
      
    },
    toolbox: {
        show : false,
        feature : {
            mark : {show: false},
            dataView : {show: false, readOnly: false},
            restore : {show: false},
            saveAsImage : {show: false}
        }
    },
    series : [{
            type:'pie',
            clockWise:false,
            tooltip:{
                show:false,
            },
            hoverAnimation: false,
            radius : [135,156],
            data:[
                {
                    value:110,
                    name:'invisible',
                    itemStyle : dbHolderStyle
                }
            ]
        },
        {
            name:'Line 1',
            type:'pie',
            tooltip:{
                show:false,
            },
            clockWise:false,
            radius : [150,156],
            itemStyle : dataStyle,
            hoverAnimation: false,
       
            data:[
                {
                    value:300,
                    name:'01'
                },
                {
                    value:300,
                    name:'invisible',
                    itemStyle : placeHolderStyle
                }
         
            ]
        }, 
        {
            type:'pie',
            clockWise:false,
            tooltip:{
                show:false,
            },
            hoverAnimation: false,
            radius : [90, 110],
            data:[
                {
                    value:0,
                    name:'invisible',
                    itemStyle : dbHolderStyle,
                }
            ]
        }, {
            name:'Line 3',
            type:'pie',
            tooltip:{
                show:false,
            },
            clockWise:false,
            hoverAnimation: false,
            radius : [105, 110],
            itemStyle : dataStyle,
            data:[
                {
                    value:20, 
                    name:'03',
                },
                {
                    value:100,
                    name:'invisible',
                    itemStyle : placeHolderStyle
                }
            ]
        },

    ]
};
circle_7.setOption(option);
window.onresize = circle_7.resize;
*/

/**
 * 加载经济指标双圆数据
 * @param eleid 图形元素位置
 * spanidprofit 利润文字元素显示位置
 * spanidincome 收入文字元素显示位置
 * @param data  数据集合 
 */
function setdoubleCicle(eleid,spanidprofitid,spanidincomeid,data){
	var circleObj = echarts.init(document.getElementById(eleid));
	if(!circleObj) {
		console.log("找不到指定元素id：",eleid);
		return;
	}
	
	var yugulirun= 0 ;
	var yugushouru= 0 ;
	var lejishouru= 0 ;
	var leijilirun= 0;
	var jyuom="";
	var jyupdatime="";
	var jytime_type=2;
		
	for (var i =0;i<data.length; i++) {
		var row=data[i];
		var index_filed=row["index_filed"];
		if(index_filed=="A00075") {
			yugulirun=row["index_val"]; //利润截止累计 如果数据库字段修改这边就必须修改对应的字段
			jytime_type=row["time_type"];
			jyuom=row["uom"];
			jyupdatime=row["updatetime"];
		}
		if(index_filed=="A00074") yugushouru=row["index_val"]; //收入截止累计
		if(index_filed=="A00135") lejishouru=row["index_val"]; //收入年度预算
		if(index_filed=="A00138") leijilirun=row["index_val"]; //利润年度预算
		
	}		 
	
  
	var jyformattime=formatDate(jyupdatime,jytime_type);
	var jyele="经营指标("+jyuom+")"+"<sub>"+jyformattime+"</sub>";
	$('#DeptTime_8').html(jyele)
	
	
	
	try{
	var yuguliruncompare=parseFloat(leijilirun)-parseFloat(yugulirun); //饼图用利润对比值
	var yugushourucompare=parseFloat(lejishouru)-parseFloat(yugushouru) ;//饼图用收入对比值
	
	}catch(e){
		yuguliruncompare=0;
		yugushourucompare=0
		console.log("指标数据格式错误");
		
		
	}
	
	var dataStyle = { 
		    normal: {
		        label: {show:false},
		        labelLine: {show:false},
		        shadowBlur: 1,
		        shadowColor: 'rgba(40, 40, 40, 0.5)',
		    }
		};
		var placeHolderStyle = {
		    normal : {
		        color: 'rgba(255,255,255,0)',
		        label: {show:false},
		        labelLine: {show:false}
		    },
		    emphasis : {
		        color: 'rgba(255,255,255,0)',
		    }
		};
		var dbHolderStyle = {
		    normal : {
		        color: 'rgba(255,255,255,0.1)',
		        label: {show:false},
		        labelLine: {show:false}
		    },
		    emphasis : {
		        color: 'rgba(255,255,255,0.1)',
		        label: {show:false},
		        labelLine: {show:false}
		    }
		};
		option = {
		   color: ['#43a1be', '#cc8e13'],
		    tooltip : {
		        show: true,
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        itemGap:12,
		      
		    },
		    toolbox: {
		        show : false,
		        feature : {
		            mark : {show: false},
		            dataView : {show: false, readOnly: false},
		            restore : {show: false},
		            saveAsImage : {show: false}
		        }
		    },
		    series : [{
		            type:'pie',
		            clockWise:false,
		            tooltip:{
		                show:false,
		            },
		            hoverAnimation: false,
		            radius : [135,156],
		            data:[
		                {
		                    value:110,
		                    name:'invisible',
		                    itemStyle : dbHolderStyle
		                }
		            ]
		        },
		        {
		            name:'Line 1',
		            type:'pie',
		            tooltip:{
		                show:false,
		            },
		            clockWise:false,
		            radius : [150,156],
		            itemStyle : dataStyle,
		            hoverAnimation: false,
		       
		            data:[
		                {
		                    value:yugulirun,
		                    name:'01'
		                },
		                {
		                    value:yuguliruncompare,
		                    name:'invisible',
		                    itemStyle : placeHolderStyle
		                }
		         
		            ]
		        }, 
		        {
		            type:'pie',
		            clockWise:false,
		            tooltip:{
		                show:false,
		            },
		            hoverAnimation: false,
		            radius : [90, 110],
		            data:[
		                {
		                    value:0,
		                    name:'invisible',
		                    itemStyle : dbHolderStyle,
		                }
		            ]
		        }, {
		            name:'Line 3',
		            type:'pie',
		            tooltip:{
		                show:false,
		            },
		            clockWise:false,
		            hoverAnimation: false,
		            radius : [105, 110],
		            itemStyle : dataStyle,
		            data:[
		                {
		                    value:yugushouru, 
		                    name:'03',
		                },
		                {
		                    value:yugushourucompare,
		                    name:'invisible',
		                    itemStyle : placeHolderStyle
		                }
		            ]
		        },

		    ]
		};
		circleObj.setOption(option);
		window.onresize = circleObj.resize;
	
     //   var spanprofitele=parseFloat(yugulirun)+"/"+parseFloat(leijilirun)+"<sub>亿</sub>";
     //   var spanidincomeele=parseFloat(yugushouru)+"/"+parseFloat(lejishouru)+"<sub>亿</sub>";
		var spanprofitele=parseFloat(yugulirun)+"/"+parseFloat(leijilirun);
		var spanidincomeele=parseFloat(yugushouru)+"/"+parseFloat(lejishouru);
        var spanprofit=$('#'+spanidprofitid); 
        var spanidincome=$('#'+spanidincomeid); 
        if(spanprofit) spanprofit.html(spanprofitele);
        if(spanidincome) spanidincome.html(spanidincomeele);
} 

