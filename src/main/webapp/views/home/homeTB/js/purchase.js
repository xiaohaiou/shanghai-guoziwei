

var deptid=3;//当前选择的id;
var supply=echarts.init(document.getElementById("supply"));
var moneyamount=echarts.init(document.getElementById("moneyamount"));
var saverate=echarts.init(document.getElementById("saverate"));
var relative1=echarts.init(document.getElementById("relative1"));
var relative2=echarts.init(document.getElementById("relative2"));
var relative3=echarts.init(document.getElementById("relative3"));

function getSeason(Month)
{
	var Season="";
	switch(Month)
	{
		case "01":
		case "02":
		case "03":
			Season=1
			break;
		case "04":
		case "05":
		case "06":
			Season=2
			break;
		case "07":
		case "08":
		case "09":
			Season=3
			break;
		case "10":
		case "11":
		case "12":
			Season=4
			break;
		default:
			Season=1
			break;
	}
	return Season;
}
/**
 * 根据deptid 展示二级页面
 * @param deptid
 */
function pruchaseInit(){
	var pruchaseDate = $('.form-control').val();
	$('.showtimespan').html(pruchaseDate);
	var orgId = $('#org').val();
	var pruchaseYear;
	var pruchaseSeason;
	if (pruchaseDate == "" || pruchaseDate == null){
		var mydate = new Date();
		pruchaseYear = "" + mydate.getFullYear();
	//	pruchaseSeason = "" + getSeason(mydate.getMonth());
	} else {
		pruchaseYear = pruchaseDate.slice(0,4);
	//	pruchaseSeason = "" + getSeason(pruchaseDate.slice(5,7));
	}
	$.ajax({
		url : "/bim_show/purchase/getpruchaseData",
		type : "POST",
		data : {
			year:pruchaseYear
		//	seaon:pruchaseSeason
		},
		success : function(data) {
			var json=JSON.parse(data)
			createPurchaseEchart(json);
			
			
		},
		error : function() {
			alert("获取数据失败");
		}
	});

}


function createPurchaseEchart(data)
{
	createSupply(data.purchaseAmountOfSupply);
	createMoneyAmount(data.purchaseAmountOfMoney)
	createSaveRate(data.purchaseSaveRate);
	//createPurchasePieChart(data.relative);
}


function createSupply(data)
{
	
	var option = {
			color: ['#c23531','#2f4554','#61a0a8','#d48265','#91c7ae','#749f83'],
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    title: {
		        text: '海航实业'+$('.form-control').val().slice(0,4)+'年库内供应商数量',
		        textStyle:{
		        	align:'center'
		        },
		        left: 'center',
		    },
		    legend:{
		    	data:data.legend_title,
		        y :'bottom'		        
		    },
		    xAxis : [
		        {
		        	data:['第一季度','第二季度','第三季度','第四季度'],
		            axisTick: {
		                alignWithLabel: true
		            }
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            name: '个'
		        }
		    ],
		    series : [
		       /* {
		        	name:data.legend_title[0],
		            type:'bar',
		            barWidth: '60%',
		            data:data.datavalue[0]
		        },{
		        	name:data.legend_title[0],
		            type:'bar',
		            barWidth: '60%',
		            data:data.datavalue[0]
		        }
		        */
		    ]
		}
	
	for ( var int = 0; int < data.legend_title.length; int++) {
		var a=new Object();
		a.name=data.legend_title[int];
		a.type='bar';
		a.barrWidth='60%'
		a.data=data.datavalue[int]
		option.series.push(a);
	}
	console.log(option.series)
	/*option.legend.data=data.legend_title;
	option.xAxis.data=data.datax;
	option.series.data=data.datavalue;
	option.series.name=data.legend_title;*/
	
	supply.setOption(option,true);
	window.onresize = supply.resize;	
}


function createMoneyAmount(data)
{
	
	var option = {
			color: ['#c23531','#2f4554','#61a0a8','#d48265','#91c7ae','#749f83'],
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    title: {
		        text: '海航实业'+$('.form-control').val().slice(0,4)+'年累计完成采购金额',
		        textStyle:{
		        	align:'center'
		        },
		        left: 'center',
		    },
		    legend:{
		    	data:data.legend_title,
		        y :'bottom'		        
		    },
		    xAxis : [
		        {
		        	data:['第一季度','第二季度','第三季度','第四季度'],
		            axisTick: {
		                alignWithLabel: true
		            }
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            name: '个'
		        }
		    ],
		    series : [
		     
		    ]
		}
	for ( var int = 0; int < data.legend_title.length; int++) {
		var a=new Object();
		a.name=data.legend_title[int];
		a.type='bar';
		a.barrWidth='60%'
		a.data=data.datavalue[int]
		option.series.push(a);
	}
	moneyamount.setOption(option,true);
	window.onresize = moneyamount.resize;	
}

function createSaveRate(data)
{
	option = {
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'cross',
		            crossStyle: {
		                color: '#999'
		            }
		        }
		    },
		    title: {
		        text: '海航实业'+$('.form-control').val().slice(0,4)+'年工程采购成本节约率',
		        textStyle:{
		        	align:'center'
		        },
		        left: 'center',
		    },
		    legend: {
		    	data:data.legend_title,
		    	y :'bottom'		
		    },
		    xAxis: [
		        {
		            type: 'category',
		            data: ['第一季度','第二季度','第三季度','第四季度'],
		            axisPointer: {
		                type: 'shadow'
		            }
		        }
		    ],
		    yAxis: [
		        {
		            type: 'value',
		            name: '亿元',
		            axisLabel: {
		                formatter: '{value}'
		            }
		        },
		        {
		            type: 'value',
		            name: '%',
		            axisLabel: {
		                formatter: '{value}'
		            }
		        }
		    ],
		    series: [
		        {
		            name:data.legend_title[0],
		            type:'bar',
		            data:data.datavalue[0]
		        },
		        {
		            name:data.legend_title[1],
		            type:'bar',
		            data:data.datavalue[1]
		        },
		        {
		            name:data.legend_title[2],
		            type:'line',
		            yAxisIndex: 1,
		            data:data.datavalue[2]
		        }
		    ]
		};
	saverate.setOption(option,true);
	window.onresize = saverate.resize;	

}


function createPurchasePieChart(data)
{

	var colorlist=['#EC7C32','#5B9BD4','#BCEE68','#8EE5EE','#8470FF', '#008B45', '#24CBE5', '#912CEE', '#FF9655']
	
	var showdiv=[relative1,relative2,relative3];
	for(var i=0;i<data.title.length;i++)
	{
		var option = {
				 
			   color:['#EC7C32','#5B9BD4','#BCEE68','#8EE5EE','#8470FF', '#008B45', '#24CBE5', '#912CEE', '#FF9655'],
			   title: [{
			    //  text: (100*data.relativerate[i][0]/data.relativerate[i][1]).toFixed(2)+'%\n'+ data.title[i],
				
//			      subtext: '2017年',
			      x: 'center',
			      y: 'bottom',
			      padding: 0,     
			      textStyle: {
			          fontWeight: 'normal',
			          fontSize: 16,
			         }		      
			      }],
			      tooltip : {
			          trigger: 'item',
			          formatter: "{b} : {c} ({d}%)"
			      },
			     series: [{
			    	 labelLine:{
			    		 normal:{
			    			 show:false
			    		 }
			    	 },
			    	label:{
			    		normal:{show:false}
			    	},
			         hoverAnimation: false, //设置饼图默认的展开样式
			         radius : '80%',
			         center: ['50%', '50%'],
			         
			         type: 'pie',
			         selectedMode: 'single',
			         selectedOffset: 16, //选中是扇区偏移量
			         data:[{name:"上期累计数",value:data.relativerate[i][0]},
			               {name:"本期数",value:data.relativerate[i][1]}
			         ],
			         itemStyle: {
			                emphasis: {
			                	show:false,
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                }
			            }
			     }]
			 };
		showdiv[i].setOption(option,true);
		 window.onresize = showdiv[i].resize;
		 if(data.relativerate[i][0]!=0)
			 $("#titlerelative"+i).html(((100*data.relativerate[i][1]/data.relativerate[i][0])).toFixed(2)+'%');
		 else
			 $("#titlerelative"+i).html(0+'%');
	}
	$("#all").html(data.all == undefined ? "" :data.all+"<span>亿元</span>");

}


/**
 * 根据选择时间加载数据
 */
function changtime(){
	pruchaseInit();
}

/**
 * 根据选择组织机构加载数据
 */
function changOrg(){
	pruchaseInit();
}
