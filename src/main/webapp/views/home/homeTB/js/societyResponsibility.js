/**
 * 根据deptid 激活左边选择的职能样式并且修改显示位置并且加载数据
 * @param deptid
 */

/**
 * 根据选择时间加载数据
 */
var srYear;
var srMonth;

/**
 * 根据选择组织机构加载数据
 */
function _query(){
	srInit();
}

function srInit(){
	var srDate = $('.form-control').val();
	var srOrgId = $('#orgnmId option:selected').val();
	if (srDate == undefined || srDate == null){
		var mydate = new Date();
		srYear = "" + mydate.getFullYear();
		srMonth = "" + mydate.getMonth();
	} else {
		srYear = srDate.slice(0,4);
		var begin = srDate.indexOf("年") + 1;
		var end = srDate.indexOf("月");
		srMonth = srDate.slice(begin,end);
		if (srMonth.indexOf("0") != -1){
			srMonth.slice(1);
		}
	}
	$.ajax({
		url : basePath+"/srShow/getSrData",
		type : "POST",
		data : {
			year:srYear,
			month:srMonth
		},
		dataType: "json", 
		success : function(data) {
			console.info(data);
			//title命名
			changeTilte();
			//舆情总数SHOWTAB_W23
			sr_SHOWTAB_W23(data[0].SHOWTAB_W23);
			//品牌数
			sr_show_two(data[0].SHOWTAB_T22,srOrgId);
			//新媒体   按天显示
			sr_show_three(data[0].SHOWTAB_D24,srYear,srMonth);
			//志愿服务活动量
			sr_show_four(data[0].SHOWTAB_M25,srYear,srMonth);
			//员工关爱活动覆盖度
			sr_show_five(data[0].SHOWTAB_M26,srYear,srMonth);
			//媒体维护次数
			sr_show_six(data[0].SHOWTAB_M27,srYear,srMonth);
		},
		error : function() {
			alert("获取数据失败");
		}
	});
}
//修改title
function changeTilte(){
	//新媒体
	 $("#titleTimeOneId").html(srYear+"年"+srMonth+"月");
	//志愿服务活动量
	 $("#titleTimeTwoId").html(srYear+"年"+srMonth+"月");
	//修改员工关爱活动覆盖度
	 $("#titleTimeThreeId").html(srYear+"年"+srMonth+"月");
	//修改媒体维护次数
	 $("#titleTimeFourId").html(srYear+"年"+srMonth+"月");
	//修改品牌
	 $("#titleTimeFiveId").html(srYear+"年"+srMonth+"月");
	//修改舆情总数
	 $("#titleTimeSixId").html(srYear+"年"+srMonth+"月");
}
//舆情总数
function sr_SHOWTAB_W23(data){
	// yqzsId
	var sumNum=0;
	var html="<p class='sz1_title1'><span class='font_g'><span id='dyyqzsId'></span>次</span>当月舆情总数</p>";
	html+="<table><tr><th>时间</th><th>舆情总数(条)</th><th>重点报告数(条)</th></tr>";
	for(var i = 0;i<data.length;i++){
		sumNum+=parseInt(data[i].a00105);//月舆情总数
		html+="<tr><td>"+data[i].date_w+"周</td><td>"+data[i].a00105+"</td><td>"+data[i].a00106+"</td></tr>";
	}
	html+="</table>";
	$("#yqzsId").html(html);
	$("#dyyqzsId").html(sumNum);
}

//品牌总数
function sr_show_two(data,org){
	//总人数饼图
	var hxpp=0;qtpp=0;hxcp=0,fhpp=0;
	var chartsId = "sr_echarts_two";
	var legend=['核心品牌','其他品牌','核心产品','孵化品牌'];//legend 选项
	var title='海航实业品牌数量占比';	
	var seriesData = new Array();
	
	for(var i=0;i<data.length;i++){
		if(data[i].a00018=="核心品牌"){
			hxpp+=1;
		}else if(data[i].a00018=="其他品牌"){
			qtpp+=1;
		}else if(data[i].a00018=="核心产品"){
			hxcp+=1;
		}else if(data[i].a00018=="孵化品牌"){
			fhpp+=1;
		}
	}
	
	var sumNum=hxpp+qtpp+hxcp+fhpp;
	$("#sumNumId").html(sumNum+"个");
	//核心品牌
	var seriesTempData={};
	seriesTempData["value"]=hxpp;
	seriesTempData["name"]=legend[0];
	seriesData.push(seriesTempData);
	//其他品牌
	var seriesTempData={};
	seriesTempData["value"]=qtpp;
	seriesTempData["name"]=legend[1];
	seriesData.push(seriesTempData);
	//核心产品
	var seriesTempData={};
	seriesTempData["value"]=hxcp;
	seriesTempData["name"]=legend[2];
	seriesData.push(seriesTempData);
	//孵化品牌
	var seriesTempData={};
	seriesTempData["value"]=fhpp;
	seriesTempData["name"]=legend[3];
	seriesData.push(seriesTempData);
	createBarCharts(chartsId,legend,seriesData,title);
}

//新媒体
function sr_show_three(data,year,month){
	//设置text初始化
	var xmtydcs=0,zqds=0,zps=0;//月阅读次数，总渠道数，总篇数
	//title
	var title="海航实业"+year+"年新媒体各指标情况";
	var legend=['总阅读（次）','总渠道（个）','总篇数（篇）'];//legend 选项
	var selected={'总阅读（次）': true,'总渠道（个）': false,'总篇数（篇）': false};//selected 选项
	//图表数据
	var chartsId="sr_echarts_three";
	time_uom="号";
	var xAxisdata=new Array();	//xAxis x轴
	var seriesData=new Array();//series 分度数据
	var seriesZydData = new Array();
	var seriesZqdData = new Array();
	var seriesZpsData = new Array();
	
	
	for(var i=0;i<data.length;i++){
		//text数据获取
		xmtydcs+= parseInt(data[i].a00167);//阅读累加
		zqds+= parseInt(data[i].a00168);//渠道累加
		zps+= parseInt(data[i].a00108);//篇数累加
		//柱状图数据获取
		//特殊拼接x轴
		if(data[i].date_from==data[i].date_end){
			var x_Axis=data[i].date_from.substr(data[i].date_from.length-2,2).replace(/\b(0+)/gi,"")+time_uom;
		}else{
			var x_Axis=data[i].date_from.substr(data[i].date_from.length-2,2).replace(/\b(0+)/gi,"")+time_uom;
			x_Axis+="-";
			x_Axis+=data[i].date_end.substr(data[i].date_end.length-2,2).replace(/\b(0+)/gi,"")+time_uom;
		}
		if($.inArray(x_Axis, xAxisdata)==-1){
	    	xAxisdata.push(x_Axis);
	    }
		//总阅读（次）
		seriesZydData.push(data[i].a00167);
		//总篇数
		seriesZqdData.push(data[i].a00108);
		//总渠道数
		seriesZpsData.push(data[i].a00168);
	}
	//设置text内容
	$("#xmtydcsId").html(xmtydcs+"次");
	$("#zqdsId").html(zqds+"个");
	$("#zpsId").html(zps+"篇");
	//设置series
	seriesData.push(seriesZydData);
	seriesData.push(seriesZqdData);
	seriesData.push(seriesZpsData);
	//创建Echarts
	createTLineCharts(chartsId,legend,xAxisdata,seriesData,selected,title);
}

//志愿服务活动量
function sr_show_four(data,year,month){
	//设置text初始化
	var zyfwhdl=0,zyfwljsc=0,zyfwljrc=0;//志愿服务活动的累计次数，志愿服务活动累计时长，志愿服务活动累计人次
	//title
	var title="海航实业"+year+"年志愿服务活动量";
	var legend=['志愿服务活动的累计次数','志愿服务活动累计时长','志愿服务活动累计人次'];//legend 选项
	var selected={'志愿服务活动的累计次数': true,'志愿服务活动的累计次数': true,'志愿服务活动的累计次数': true};//selected 选项
	//图表数据
	var chartsId="sr_echarts_four";
	var xAxisdata=new Array();	//xAxis x轴
	var seriesData=new Array();//series 分度数据
	var seriesCsData = new Array();
	var seriesScData = new Array();
	var seriesRcData = new Array();
	for(var i=0;i<data.length;i++){
		//text数据获取
		zyfwhdl+= parseInt(data[i].a00109);//阅读累加
		zyfwljsc+= parseInt(data[i].a00110);//渠道累加
		zyfwljrc+= parseInt(data[i].a00111);//篇数累加
		//柱状图数据获取
		//拼接x轴
		xAxisdata.push(data[i].date_m+'月');
		//志愿服务活动的累计次数
		seriesCsData.push(data[i].a00109);
		//志愿服务活动累计时长
		seriesScData.push(data[i].a00110);
		//志愿服务活动累计人次
		seriesRcData.push(data[i].a00111);
	}
	//设置text内容
	$("#zyfwhdlId").html(zyfwhdl+"次");
	$("#zyfwljscId").html(zyfwljsc+"小时");
	$("#zyfwljrcId").html(zyfwljrc+"人次");
	//设置series
	seriesData.push(seriesCsData);
	seriesData.push(seriesScData);
	seriesData.push(seriesRcData);
	//创建Echarts
	createDYLineCharts(chartsId,legend,xAxisdata,seriesData,selected,title);
}

function sr_show_five(data,year,month,org){
	//定义text显示参数
	var ljcyd=0,rjcyd=0;
	//title
	var title="海航实业"+year+"年员工关爱活动覆盖度";
	var legend=['活动累计人均参与度（人次）','活动人均参与度（人次）'];//legend 选项
	//图标信息
	var chartsId="sr_echarts_five";
	var xAxis=new Array();	//xAxis x轴
	var series=new Array();	//series 分度数据
	var seriesLjData = new Array();
	var seriesRjData = new Array();
	var stack=['0','1'];	//叠加参数
	var type='bar';			//bar 柱状图 line  折线图
	
	for(var i=0;i<data.length;i++){
		//text数据获取
		ljcyd+= parseInt(data[i].a00166);//累计人均参与度累加
		rjcyd+= parseInt(data[i].a00114);//人均参与度累加
		//柱状图数据获取
		var xTemp=data[i].date_m+"月";
		xAxis.push(xTemp);
		seriesLjData.push(data[i].a00166);
		seriesRjData.push(data[i].a00114);
	}
	//设置text内容
	$("#ljcydId").html(ljcyd+"人次");
	$("#rjcydId").html(rjcyd+"人次");
	//设置series
	series.push(seriesLjData);
	series.push(seriesRjData);
	//创建Echarts
	createLineCharts(chartsId,legend,xAxis,series,stack,type,title);
}

//媒体维护
function sr_show_six(data,year,month){
	//设置text初始化
	var bhcs=0,bhzrs=0,bhmts=0;//媒体拜会次数，拜会媒体总人数，拜会媒体数量
	//title
	var title="海航实业"+year+"年媒体维护次数";
	var legend=['拜会次数（次）','拜会媒体总人数（人）','拜会媒体数量（个）'];//legend 选项
	var selected={'拜会次数（次）': true,'拜会媒体总人数（人）': true,'拜会媒体数量（个）': true};//selected 选项
	//图表数据
	var chartsId="sr_echarts_six";
	var xAxisdata=new Array();	//xAxis x轴
	var seriesData=new Array();//series 分度数据
	var seriesCsData = new Array();
	var seriesZrsData = new Array();
	var seriesMtsData = new Array();
	
	for(var i=0;i<data.length;i++){
		//text数据获取
		bhcs+= parseInt(data[i].a00116);
		bhzrs+= parseInt(data[i].a00117);
		bhmts+= parseInt(data[i].a00118);
		//柱状图数据获取
		//拼接x轴
		xAxisdata.push(data[i].date_m+'月');
		//媒体拜会次数
		seriesCsData.push(data[i].a00116);
		//拜会媒体总人数
		seriesZrsData.push(data[i].a00117);
		//，拜会媒体数量
		seriesMtsData.push(data[i].a00118);
	}
	//设置text内容
	$("#bhcsId").html(bhcs+"次");
	$("#bhzrsId").html(bhzrs+"人");
	$("#bhmtsId").html(bhmts+"个");
	//设置series
	seriesData.push(seriesCsData);
	seriesData.push(seriesZrsData);
	seriesData.push(seriesMtsData);
	//创建Echarts
	createTLineCharts(chartsId,legend,xAxisdata,seriesData,selected,title);
}

/*
 * 柱状图line
 * chartsId 接受图标dom的Id
 * legend 选项
 * xAxis x轴
 * series 分度数据
 */
function createLineCharts(chartsId,legend,xAxis,seriesData,stack,type,title){
	var BarCWObj = echarts.init(document.getElementById(chartsId));
	option = {
		title : {
	        text: title,
	        x:'35%'
	    },
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        }
	    },
	    legend: {
	        data:legend,//['人均收入','人均利润']
	        right:'0%',
	        top:"30",
	    },
	    grid: {
	    	top:"80",
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    xAxis : [
	        {
	            type : 'category',
	            data : xAxis//['1月','2月','3月','4月','5月','6月','7月']
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value'
	        }
	    ],
	    series : 
	    	[
                {
                    name:legend[0],
                    type:'bar',
                    stack:stack[0],
                    data:seriesData[0]
                },
                {
                    name:legend[1],
                    type:'bar',
                    stack:stack[1],
                    data:seriesData[1]
                }
            ]
	};
	BarCWObj.setOption(option,true);
	window.onresize = BarCWObj.resize;
}
/*
 * 柱状图/折线图
 * chartsId 接受图标dom的Id
 * legend 选项
 * xAxis x轴
 * series 分度数据
 */
function createTLineCharts(chartsId,legend,xAxis,seriesData,selected,title){
	var BarCWObj = echarts.init(document.getElementById(chartsId));
	option = {
		title : {
	        text: title,
	        x:'35%'
	    },
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        }
	    },
	    legend: {
	        data:legend,//['人均收入','人均利润']
	        selected: selected,
	       	right:'0%',
	        top:"30"
	        /*{
		        // 选中'系列1'
		        '系列1': true,
		        // 不选中'系列2'
		        '系列2': false
	        }*/
	    },
	    grid: {
	    	top:"80",
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    xAxis : [
	        {
	            type : 'category',
	            data : xAxis//['1月','2月','3月','4月','5月','6月','7月']
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value'
	        }
	    ],
	    series : 
	    	[
             {
                 name:legend[0],
                 type:'bar',
                 data:seriesData[0]
             },
             {
                 name:legend[1],
                 type:'bar',
                 data:seriesData[1]
             },
         	{
         		name:legend[2],
                 type:'bar',
                 data:seriesData[2]
         	}
         ]
	};
	BarCWObj.setOption(option,true);
	window.onresize = BarCWObj.resize;
	if(chartsId=="sr_echarts_six"){
		 // 点击下钻显示品牌信息列表
		BarCWObj.on('click', function (params) {
			try{
				
				var levelid="1";
				var timetype=6;
				var tbid="SHOWTAB_M27";
				var name=params.name;
				loadNextLVExtraTbdata(levelid,timetype,tbid,name,"","");
			}catch(e){
				alert(e)
			}
		 });
	}
}
/*
 * 双Y轴
 * chartsId 接受图标dom的Id
 * legend 选项
 * xAxis x轴
 * series 分度数据
 */
function createDYLineCharts(chartsId,legend,xAxis,seriesData,selected,title){
	var BarCWObj = echarts.init(document.getElementById(chartsId));
	option = {
		title : {
	        text: title,
	        x:'35%'
	    },
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        }
	    },
	    legend: {
	        data:legend,//['人均收入','人均利润']
	        selected: selected,
	        right:'0%',
	        top:"30"
	        /*{
		        // 选中'系列1'
		        '系列1': true,
		        // 不选中'系列2'
		        '系列2': false
	        }*/
	    },
	    grid: {
	    	top:"80",
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    xAxis : [
	        {
	            type : 'category',
	            data : xAxis//['1月','2月','3月','4月','5月','6月','7月']
	        }
	    ],
	    yAxis : 
	    [
 	        {
	            type: 'value',
	            name: '次数',
	            min: 0,
	            interval: 500,
	            axisLabel: {
	                formatter: '{value} 次'
	            }
	        },
	        {
	            type: 'value',
	            name: '小时',
	            min: 0,
	            interval: 500,
	            axisLabel: {
	                formatter: '{value} 小时'
	            }
	        }
 	    ],
	 	    
	    series : 
	    	[
             {
                 name:legend[0],
                 type:'bar',
                 data:seriesData[0]
             },
             {
                 name:legend[1],
                 type:'bar',
                 yAxisIndex: 1,
                 data:seriesData[1]
             },
         	{
         		name:legend[2],
                 type:'bar',
                 data:seriesData[2]
         	}
         ]
	};
	BarCWObj.setOption(option,true);
	window.onresize = BarCWObj.resize;
}


//饼图
function createBarCharts(chartsId,legend,seriesData,title){
	var BarCWObj = echarts.init(document.getElementById(chartsId));
	option = {
	 title : {
	        text: title,
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	        orient: 'vertical',
	        right:'right',
	        data: legend
	    },
	    series : [
	        {
	            name: '类型',
	            type: 'pie',
	            radius : '55%',
	            center: ['50%', '60%'],
	            data:seriesData,
	            /*data:[
	                  {value:seriesData[0], name:legend[0]},
	                  {value:seriesData[1], name:legend[1]}
	              ],*/
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ]
	};
	BarCWObj.setOption(option,true);
	window.onresize = BarCWObj.resize;
}


//饼图
function createBarCharts(chartsId,legend,seriesData,title){
	var BarCWObj = echarts.init(document.getElementById(chartsId));
	option = {
	 title : {
	        text: title,
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	        orient: 'vertical',
	        right:'right',
	        top:'10%',
	        data: legend
	    },
	    series : [
	        {
	            name: '类型',
	            type: 'pie',
	            radius : '55%',
	            center: ['50%', '60%'],
	            data:seriesData,
	            /*data:[
	                  {value:seriesData[0], name:legend[0]},
	                  {value:seriesData[1], name:legend[1]}
	              ],*/
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ]
	};
	BarCWObj.setOption(option,true);
	window.onresize = BarCWObj.resize;
	 // 点击下钻显示品牌信息列表
	BarCWObj.on('click', function (params) {
		try{
			
			var levelid="1";
			var timetype=6;
			var tbid="SHOWTAB_T22";
			var name=params.name;
			loadNextLVExtraTbdata(levelid,timetype,tbid,name,"","");
		}catch(e){
			alert(e)
		}
	 });
}

/**
 * 品牌信息下穿
 * @param levelid 下穿登记 最多到3级
 * @param timetype 时间类型 不同的时间类型需要不同的时间字段
 * @param tbid 表id 必填
 * @param nameTitle 下穿画面标题名称 必有
 * @param name1 参数1
 * @param name2 参数2
 */
function loadNextLVExtraTbdata(levelid,timetype,tbid,nameTitle,name1,name2){
	var url="/home/getExtraTBDataByLevel"
	if(tbid=="SHOWTAB_M27"){
		url="/srShow/getDataByLevel";
	}
	var datajson={
		levelid:levelid,
		timetype:timetype,
		tbid:tbid,
		nameTitle:nameTitle,
		year:srYear,
	};
	$.ajax({
		url : basePath+url,
		type : "POST",
		data : datajson,
		success : function(data) {
			if(data==0){
				alert("无对应下层数据");
			}else{
				if(levelid==1){
					if(tbid=="SHOWTAB_T22"){
						openlevel1window();
						//var level1_title = name + "  信息";
						createPinPaiLevel(data,nameTitle);
						//$('#level1_title').text(level1_title);
						
					}else if(tbid=="SHOWTAB_M27"){
						createWeiHuLevel(data,nameTitle);
					}
					//level1chartOBj.resize();
				}
			}
		},
		error : function() {
			alert("获取数据失败");
		}
	});
}

/**
 * 打开一级弹窗
 */
function openlevel1window(){
	$('#chart-modal').jqm({overlay:75}).jqmShow()
}
function openlevel1window2(){
	$('#chart-modal2').jqm({overlay:75}).jqmShow()
}

/**
 * 媒体维护下钻信息
 * @param data      数据
 */
function createWeiHuLevel(data,nameTitle){
	console.info("data--------------->"+data);
	//设置text初始化
	var bhcs=0,bhzrs=0,bhmts=0;//媒体拜会次数，拜会媒体总人数，拜会媒体数量
	//title
	var title="海航实业"+srYear+"年"+nameTitle+"媒体维护次数详细";
	var legend=['拜会次数（次）','拜会媒体总人数（人）','拜会媒体数量（个）'];//legend 选项
	var selected={'拜会次数（次）': true,'拜会媒体总人数（人）': true,'拜会媒体数量（个）': true};//selected 选项
	//图表数据
	var chartsId="level2_chart";
	var xAxisdata=new Array();	//xAxis x轴
	var seriesData=new Array();//series 分度数据
	var seriesCsData = new Array();
	var seriesZrsData = new Array();
	var seriesMtsData = new Array();
	for(var i=0;i<data.length;i++){
		//柱状图数据获取
		//拼接x轴
		xAxisdata.push(data[i].a00119);
		//媒体拜会次数
		seriesCsData.push(data[i].a00116);
		//拜会媒体总人数
		seriesZrsData.push(data[i].a00117);
		//，拜会媒体数量
		seriesMtsData.push(data[i].a00118);
	}
	//设置series
	seriesData.push(seriesCsData);
	seriesData.push(seriesZrsData);
	seriesData.push(seriesMtsData);
	//创建Echarts
	openlevel1window2();
	createTLineCharts(chartsId,legend,xAxisdata,seriesData,selected,title);
	$('#level1_title').html("");
}

/**
 * 产生品牌下钻信息
 * @param data      数据
 */
function createPinPaiLevel(data,nameTitle){
	var level1chartOBj="";
	if (level1chartOBj && level1chartOBj.dispose) {
		level1chartOBj.dispose();
    } 
	level1chartOBj='<div align="center" style="margin-top:20px;overflow-y:auto;height:400px;"><table border="1" cellspacing="0" cellpadding="0" id="PPXX" style="width:95%" class="table  table-striped table-bordered table-hover">';
	// 表头
	level1chartOBj+='<tr>';
    level1chartOBj+='<td width="10%" align="center">品牌分类</td>';
    level1chartOBj+='<td width="10%" align="center">品牌位阶</td>';
    level1chartOBj+='<td width="10%" align="center">年收入</td>';
	level1chartOBj+='<td width="10%" align="center">收入年增长率</td>';
	level1chartOBj+='<td width="10%" align="center">公司资产</td>';
    level1chartOBj+='<td width="10%" align="center">市场覆盖区域</td>';
    level1chartOBj+='<td width="10%" align="center">行业地位</td>';
	level1chartOBj+='<td width="10%" align="center">品牌属性</td>';
	level1chartOBj+='<td width="10%" align="center">品牌抽屉类别</td>';
    level1chartOBj+='</tr>';
    // 数据
    for(var i=0;i<data.length; i++){
    	var ppList = data[i];
    	level1chartOBj+='<tr><td width="10%" align="center">'+ppList.a00018+'</td>';
    	level1chartOBj+='<td width="10%" align="center">'+ppList.a00141+'</td>';
    	level1chartOBj+='<td width="10%" align="center">'+ppList.a00019+'</td>';
    	level1chartOBj+='<td width="10%" align="center">'+ppList.a00020+'</td>';
    	level1chartOBj+='<td width="10%" align="center">'+ppList.a00021+'</td>';
    	level1chartOBj+='<td width="10%" align="center">'+ppList.a00022+'</td>';
    	level1chartOBj+='<td width="10%" align="center">'+ppList.a00023+'</td>';
    	level1chartOBj+='<td width="10%" align="center">'+ppList.a00131+'</td>';
    	level1chartOBj+='<td width="10%" align="center">'+ppList.a00132+'</td>';
    }
	level1chartOBj+='</table></div>';
	$('#level1_title').html(nameTitle+"详细信息");
	$('#level1_chart').html(level1chartOBj);
}
