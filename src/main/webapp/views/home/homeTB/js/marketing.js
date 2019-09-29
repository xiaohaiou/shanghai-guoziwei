
var cash_chart = echarts.init(document.getElementById("cash_chart"));
var profit_chart = echarts.init(document.getElementById("profit_chart"));
var income_chart = echarts.init(document.getElementById("income_chart"));

function marketingInit(){
	createEchart(company,inList,outList,sList);
	createProfitEchart(proCList);
	createIncomeEchart(inCList);
}

function createEchart(company,inList,outList,sList)
{
	option = {
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    legend: {
		        data:['净流量', '总流出', '总流入']
		    },
		    grid: {
		    	top: '10%',
		        left: '5%',
		        right: '2%',
		        bottom: '0%',
		        containLabel: true
		    },
		    xAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    yAxis : [
		        {
		            type : 'category',
		            axisTick : {show: false},
		            data : company
		        }
		    ],
		    series : [
		        {
		            name:'净流量',
		            type:'bar',
		            label: {
		                normal: {
		                    show: true,
		                    position: 'inside'
		                }
		            },
		            data:sList
		        },
		        {
		            name:'总流入',
		            type:'bar',
		            stack: '总量',
		            label: {
		                normal: {
		                    show: true,
		                    position: 'inside'
		                }
		            },
		            data:inList
		        },
		        {
		            name:'总流出',
		            type:'bar',
		            stack: '总量',
		            label: {
		                normal: {
		                    show: true,
		                    position: 'inside'
		                }
		            },
		            data:outList
		        }
		    ]
		}
	cash_chart.setOption(option);
}

function createProfitEchart(proCList)
{
	var option2 = {
	    	tooltip : {
		        trigger: 'axis',
		        axisPointer : {          
		            type : 'shadow'     
		        }
		    },
	    	legend: {
		        data:['月度预算','月度利润','月度预算累计','累计利润','年度预算','全年累计完成率']
		    },
		    grid: {
	    		left: 100,
	    		right: 100,
	    		top: 60,
	    		bottom: 20
		    },
	        xAxis: {
	        	type : 'category',
	            data: ["1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"]
	        },
	        yAxis: [
		            {
			            type: 'value',
			            name: '金额(亿元)',
			            splitLine: {
			            	show: false
			            },
			            max: 800,
		                interval: 100
		            },
		            {
		                type: 'value',
		                name: '比例',
		                min: 0,
		                max: 100,
		                interval: 10,
		                axisLabel: {
		                    formatter: '{value} %'
		                }
		            }
		        ],
	   		series: [
				{
				    name:'月度预算',
				    type:'bar',
				    barWidth : 15,
				    data:proCList[1]
				},
	   			{
		            name:'月度利润',
		            type:'bar',
		            barWidth : 15,
		            data:proCList[2]
		        },
		        {
		            name:'月度预算累计',
		            type:'bar',
		            barWidth : 15,
		            data:proCList[3]
		        },
		        {
		            name:'累计利润',
		            type:'bar',
		            barWidth : 15,
		            data:proCList[4]
		        },
		        {
		            name:'年度预算',
		            type:'bar',
		            barWidth : 15,
		            data:proCList[5]
		        },
		        {
		            name:'全年累计完成率',
		            type:'line',
		            yAxisIndex: 1,
		            data:proCList[6]
		        }
	   		]
	    }
	profit_chart.setOption(option2);
}

function createIncomeEchart(inCList)
{
	var option3 = {
	    	tooltip : {
		        trigger: 'axis',
		        axisPointer : {          
		            type : 'shadow'     
		        }
		    },
	    	legend: {
		        data:['月度预算','月度收入','月度累计预算','累计收入','年度预算','全年累计完成率']
		    },
		    grid: {
	    		left: 100,
	    		right: 100,
	    		top: 60,
	    		bottom: 20
		    },
	        xAxis: {
	        	type : 'category',
	            data: ["1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"]
	        },
	        yAxis: [
	            {
		            type: 'value',
		            name: '金额(亿元)',
		            splitLine: {
		            	show: false
		            },
		            max: 3000
	            },
	            {
	                type: 'value',
	                name: '比例',
	                min: 0,
	                max: 100,
	                interval: 10,
	                axisLabel: {
	                    formatter: '{value} %'
	                }
	            }
	        ],
	   		series: [
	   			{
		            name:'月度预算',
		            type:'bar',
		            barWidth : 15,
		            data:inCList[1]
		        },
		        {
		            name:'月度收入',
		            type:'bar',
		            barWidth : 15,
		            data:inCList[2]
		        },
		        {
		            name:'月度累计预算',
		            type:'bar',
		            barWidth : 15,
		            data:inCList[3]
		        },
		        {
		            name:'累计收入',
		            type:'bar',
		            barWidth : 15,
		            data:inCList[4]
		        },
	        	{
	    			name:'年度预算',
		            type:'bar',
		            barWidth : 15,
		            data:inCList[5]
	        	},
	        	{
	    			name:'全年累计完成率',
		            type:'line',
		            yAxisIndex: 1,
		            data:inCList[6]
	        	}
	   		]
	    }
	income_chart.setOption(option3);
}