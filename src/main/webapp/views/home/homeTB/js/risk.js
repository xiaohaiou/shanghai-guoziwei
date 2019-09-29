

var deptid=3;//当前选择的id;
var Auditpie=echarts.init(document.getElementById("Auditpie"));
var Auditline=echarts.init(document.getElementById("Auditline"));
var breakcontract=echarts.init(document.getElementById("breakcontract"));
var contractMonthData=echarts.init(document.getElementById("contractMonthData"));
var InquireData=echarts.init(document.getElementById("InquireData"));
var EventData=echarts.init(document.getElementById("EventData"));


/**
 * 根据deptid 展示二级页面
 * @param deptid
 */
function riskinit(){
	var riskDate = $('.form-control').val();
	var orgId = $('#org').val();
	var riskYear;
	var riskMonth;
	if (riskDate == "" || riskDate == null){
		var mydate = new Date();
		riskYear=mydate.getFullYear();
		riskMonth = mydate.getMonth();
	
	} else {
		riskYear= riskDate.slice(0,4);
		riskMonth =  riskDate.slice(5,7);
	}
	$.ajax({
		url : "/bim_show/risk/getriskData",
		type : "POST",
		data : {
			year:riskYear,
			month:riskMonth,
			organal:orgId
		},
		success : function(data) {
			var json=JSON.parse(data)
			createRiskEchart(json);
		},
		error : function() {
			alert("获取数据失败");
		}
	});

}


function createRiskEchart(data)
{
	createriskPieChart(data.riskPointData);
	createriskauditMonth(data.riskAuditMonthData);
	
	createriskContractData(data.riskContractPointData);
	
	createriskbreakContractData(data.riskAgainstContractMonthData);
	createMoneyAmount(data.riskContractMonthData);
	
	createInquireData(data.riskInquireMonthData)
	createEventData(data.riskEventData);
	
}

function createriskContractData(data)
{
	$("#contractAllAmount").html(data.executingContract+"份");
	$("#contractAmountMoney").html(data.contractAllAmount+"亿元");
	$("#appealContract").html(data.appealContract+"份");
	$("#appealContractAmount").html(data.appealContractAmount+"亿元");
	$("#breakContract").html(data.breakContract+"份");
	$("#mebreakContract").html(data.mebreakContract+"份");
	$("#otherbreakContract").html(data.otherbreakContract+"份");
	$("#overdueReceivable").html(data.overdueReceivable+"亿元");
	$("#overdueDue").html(data.overdueDue+"亿元");
	$("#damagesReceivable").html(data.damagesReceivable+"亿元");
	$("#damagesPayable").html(data.damagesPayable+"亿元");
	
}

function createriskPieChart(data)
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
			         data:[{name:"未整改",value:data.notFinishAudit},
			               {name:"已整改",value:data.hasFinishAudit}
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
		 Auditpie.setOption(option,true);
		 window.onresize = Auditpie.resize;
		 $("#allAmount").html(data.auditThisMonth+'项');
		 $("#problemAmount").html(data.auditProblem+'个');
		 $("#Auditrate").html(data.auditreform+'%');
		 $("#callPerson").html(data.auditPersonCount+'个');
}


function createriskauditMonth(data)
{
	option = {
			 
		    title: {
		        text: organName+$('.form-control').val().slice(0,4)+'年审计情况趋势图',
		        textStyle:{
		        	align:'center'
		        },
		        left: 'center',
		    },
			color: ['#5b9bd4','#ec7c32','#febf13'],
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'cross',
		            crossStyle: {
		                color: '#999'
		            }
		        }
		    },
		    legend: {
		    	data:data.legend_title,
		    	y :'bottom'	,
		    	bottom:'20'
		    		
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '25px',
		        containLabel: true
		    },
		    xAxis: [
		        {
		            type: 'category',
		            data: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
		            axisPointer: {
		                type: 'shadow'
		            }
		        }
		    ],
		    yAxis: [
		        {
		            type: 'value',
		            name: '个',
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
	Auditline.setOption(option,true);
	window.onresize = Auditline.resize;	

}


function createriskbreakContractData(data)
{
	
	var option = {
			title: {
		        text: organName+$('.form-control').val().slice(0,4)+'年合同履行违约情况',
		        textStyle:{
		        	align:'center'
		        },
		        left: 'center',
		    },
			
		    color: ['#c23531','#2f4554','#61a0a8','#d48265','#91c7ae','#749f83'],
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '25px',
		        containLabel: true
		    },
		    legend:{
		    	data:data.legend_title,
		        y :'bottom'	,
		    	bottom:'20'	        
		    },
		    xAxis : [
		        {
		        	  data: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
				      axisTick: {
		              alignWithLabel: true
		            }
		        }
		    ],
		    yAxis: [
			        {
			            type: 'log',
			            name: '份',
			            axisLabel: {
			                formatter: '{value}'
			            }
			        },
			        {
			            type: 'log',
			            name: '万元',
			            axisLabel: {
			                formatter: '{value}'
			            }
			        }
			    ],
		    series : [
		              	{
				        	name:data.legend_title[0],
				            type:'bar',
				            data:data.datavalue[0]
				        },
				        {
				        	name:data.legend_title[1],
				            type:'bar',
				            data:data.datavalue[1]
				        }, {
				        	yAxisIndex: 1,
				        	name:data.legend_title[2],
				            type:'bar',
				            data:data.datavalue[2]
				        }
				        , {
				        	yAxisIndex: 1,
				        	name:data.legend_title[3],
				            type:'bar',
				            data:data.datavalue[3]
				        } , {
				        	yAxisIndex: 1,
				        	name:data.legend_title[4],
				            type:'bar',
				            data:data.datavalue[4]
				        } , {
				        	yAxisIndex: 1,
				        	name:data.legend_title[5],
				            type:'bar',
				            data:data.datavalue[5]
				        }
		    ]
		}
	breakcontract.setOption(option,true);
	window.onresize = breakcontract.resize;	
}


function createMoneyAmount(data)
{
	
	var option = {
			title: {
		        text: organName+$('.form-control').val().slice(0,4)+'年正在履行合同情况',
		        textStyle:{
		        	align:'center'
		        },
		        left: 'center',
		    },
		
		    color: ['#c23531','#2f4554','#61a0a8','#d48265','#91c7ae','#749f83'],
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '25px',
		        containLabel: true
		    },
		    legend:{
		    	data:data.legend_title,
		        y :'bottom',
		    	bottom:'20'	        
		    },
		    xAxis : [
		        {
		        	  data: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
				      axisTick: {
		              alignWithLabel: true
		            }
		        }
		    ],
		    yAxis: [
			        {
			            type: 'log',
			            name: '份',
			            axisLabel: {
			                formatter: '{value}'
			            }
			        },
			        {
			            type: 'log',
			            name: '万元',
			            axisLabel: {
			                formatter: '{value}'
			            }
			        }
			    ],
		    series : [
		              	{
				        	name:data.legend_title[0],
				            type:'bar',
				            data:data.datavalue[0]
				        },
				        {
				        	yAxisIndex: 1,
				        	name:data.legend_title[1],
				            type:'bar',
				            data:data.datavalue[1]
				        }, {
				        	name:data.legend_title[2],
				            type:'bar',
				            data:data.datavalue[2]
				        }
				        , {
				        	yAxisIndex: 1,
				        	name:data.legend_title[3],
				            type:'bar',
				            data:data.datavalue[3]
				        } 
		    ]
		}
	contractMonthData.setOption(option,true);
	window.onresize = contractMonthData.resize;	
}

function createInquireData(data)
{
	
	var option = {
			title: {
			        text: organName+$('.form-control').val().slice(0,4)+'年在办合规调查数',
			        textStyle:{
			        	align:'center'
			        },
			        left: 'center',
			 },
		    color: ['#5b9bd5'],
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '20px',
		        containLabel: true
		    },
		    legend:{
		    	data:data.legend_title,
		        y :'bottom'		        
		    },
		    xAxis : [
		            {
		            	data: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
		        	    axisTick: {
		                alignWithLabel: true
		            }
		        }
		    ],
		    yAxis : [
		        {
		        	name: '个',
		            type : 'value',
		            min:0
		        }
		    ],
		    series : [
		        {
		        	
		        	name:data.legend_title,
		            type:'bar',
		            barWidth: '60%',
		            data:data.datavalue[0]
		        }
		    ]
		}
	InquireData.setOption(option,true);
	window.onresize = InquireData.resize;	
}


function createEventData(data)
{
	
	var option = {
			title: {
			        text: organName+$('.form-control').val().slice(0,4)+'年案件发生数',
			        textStyle:{
			        	align:'center'
			        },
			        left: 'center',
			    },
		    color: ['#5b9bd5'],
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    legend:{
		    	data:data.legend_title,
		        y :'bottom'	
		        	
		    },
		    xAxis : [
		        { 
		        	  data: ['第一季度','第二季度','第三季度','第四季度'],
		        	  axisTick: {
		                alignWithLabel: true
		              }
		        }
		    ],
		    yAxis : [
		        {
		        	name: '个',
		        	min:0,
		            type : 'value'
		        }
		    ],
		    series : [
		        {
		        	 
		        	name:data.legend_title,
		            type:'bar',
		            barWidth: '60%',
		            data:data.datavalue[0]
		        }
		    ]
		}
	EventData.setOption(option,true);
	window.onresize = EventData.resize;	
}




/**
 * 根据选择时间加载数据
 */
function changtime(){
	riskinit();
}

/**
 * 根据选择组织机构加载数据
 */
function changOrg(){
	riskinit();
}
