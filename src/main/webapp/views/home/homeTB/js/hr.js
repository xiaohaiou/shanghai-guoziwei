/**
 * 根据deptid 激活左边选择的职能样式并且修改显示位置并且加载数据
 * @param deptid
 */

/**
 * 根据选择时间加载数据

function changtime(){
	hrInit();
} */

function _query(){
	hrInit();
}

/**
 * 根据选择组织机构加载数据

function changOrg(){
	hrInit();
} */
var hrYear;
var hrMonth;
var hrOrgName;
var xAxis=['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月',];

function hrInit(){
	var hrDate = $('.form-control').val();
	var hrOrgId = $('#orgnmId option:selected').val();
	hrOrgName = $('#orgnmId option:selected').text();
	if (hrDate == undefined || hrDate == null){
		var mydate = new Date();
		hrYear = "" + mydate.getFullYear();
		hrMonth = "" + mydate.getMonth();
	} else {
		hrYear = hrDate.slice(0,4);
		var begin = hrDate.indexOf("年") + 1;
		var end = hrDate.indexOf("月");
		hrMonth = hrDate.slice(begin,end);
		if (hrMonth.indexOf("0") != -1){
			hrMonth.slice(1);
		}
	}
	$.ajax({
		url : basePath+"/hrShow/getHrData",
		type : "POST",
		data : {
			year:hrYear,
			month:hrMonth,
			orgID:hrOrgId
		},
		dataType: "json", 
		success : function(data) {
			console.info(data);
			//title命名
			changeTilte();
			//console.info(data[1].SHOWTAB_M18);
			//总人数，管理干部数饼图
			hr_show_one(data[1].SHOWTAB_M18,data[1].SHOWTAB_M19);
			//劳动生产率特殊-- 传入  当月同环比数据，组织维度查询数据
			hr_show_two(data[0].monthType,data[2].orgType);
			//总人数
			hr_show_three(data[1].SHOWTAB_M18);
			//劳动生产率
			hr_show_four(data[1].SHOWTAB_M1);
			//管理干部人数
			hr_show_five(data[1].SHOWTAB_M19);
			//人工成本执行进度
			hr_show_six(data[1].SHOWTAB_M20);
			//人力资源回报率
			hr_show_seven(data[1].SHOWTAB_M21);
		}
	});
}
//修改title
function changeTilte(){
	//修改劳动生产率title
	 $("#titleOneId").html(hrOrgName+"劳动生产率");
	 $("#titleTimeOneId").html(hrYear+"年"+hrMonth+"月");
	//总人数title
	 $("#titleTwoId").html(hrOrgName+"总人数");
	 $("#titleTimeTwoId").html(hrYear+"年"+hrMonth+"月");
	//修改管理干部人数title
	 $("#titleThreeId").html(hrOrgName+"管理干部人数");
	 $("#titleTimeThreeId").html(hrYear+"年"+hrMonth+"月");
	//修改人工成本执行进度title
	 $("#titleFourId").html(hrOrgName+"人工成本执行进度");
	 $("#titleTimeFourId").html(hrYear+"年");
	//修改人工资源回报率title
	 $("#titleFiveId").html(hrOrgName+"人工资源回报率");
	 $("#titleTimeFiveId").html(hrYear+"年");
}

//总人数，管理干部饼图
function hr_show_one(zrsDate,glgbDate){
	var TempMonth=hrMonth.substring(1,0)==0?hrMonth.substring(1):hrMonth;
	//总人数饼图
	var chartsId = "hr_echarts_one_zrs";
	var legend=['劳动用工数','其他用工数'];//legend 选项
	var seriesData = new Array();
	//总人数title
	//$(".orgNameClass").html(hrOrgName);
	var sumtext="";
	for(var i=0;i<zrsDate.length;i++){
		
		if(zrsDate[i].date_y==hrYear&&zrsDate[i].date_m==TempMonth){
			var seriesTempData={};
			sumtext= zrsDate[i].a00016+"人";
			$("#zrsTitleId").html(sumtext);
			//劳动用工
			var seriesTempData={};
			seriesTempData["value"]=zrsDate[i].a00002;
			seriesTempData["name"]=legend[0];
			seriesData.push(seriesTempData);
			//其他用工
			seriesTempData={};
			seriesTempData["value"]=zrsDate[i].a00046;
			seriesTempData["name"]=legend[1];
			seriesData.push(seriesTempData);
			
			/*seriesData.push(zrsDate[i].a00002);
			seriesData.push(zrsDate[i].a00046);*/
		}
	}
	if(sumtext==""){
		$("#zrsTitleId").html("");
	}
	createBarCharts(chartsId,legend,seriesData,'');
	//管理干部饼图
	chartsId="hr_echarts_one_gl";
	MchartsId="hr_echarts_one_Mgl";
	legend=['管理干部男性人数','管理干部女性人数'];//legend 选项
	Mlegend=['管理干部M序列人数','管理干部非M序列人数'];//legend 选项
	seriesData = new Array();
	var MseriesData = new Array();
	//设置title数字
	var subtext="";
	for(var i=0;i<glgbDate.length;i++){
		if(glgbDate[i].date_y==hrYear&&glgbDate[i].date_m==TempMonth){
			subtext= glgbDate[i].a00059+"人";
			$("#glgbTitleId").html(subtext);
			$("#glgbMTitleId").html(subtext);
			//男人数
			var seriesTempData={};
			seriesTempData["value"]=glgbDate[i].a00157;
			seriesTempData["name"]=legend[0];
			seriesData.push(seriesTempData);
			//女人数
			seriesTempData={};
			seriesTempData["value"]=glgbDate[i].a00158;
			seriesTempData["name"]=legend[1];
			seriesData.push(seriesTempData);
			//M序列人数
			seriesTempData={};
			seriesTempData["value"]=glgbDate[i].a00159;
			seriesTempData["name"]=Mlegend[0];
			MseriesData.push(seriesTempData);
			//非M序列人数
			seriesTempData={};
			seriesTempData["value"]=glgbDate[i].a00160;
			seriesTempData["name"]=Mlegend[1];
			MseriesData.push(seriesTempData);
			//seriesData.push(glgbDate[i].a00158);
		}
	}
	if(subtext==""){
		$("#glgbTitleId").html("");
		$("#glgbMTitleId").html("");
	}
	//性别饼图
	createBarCharts(chartsId,legend,seriesData,'');
	//M序列饼图
	createBarCharts(MchartsId,Mlegend,MseriesData,'');
}

//劳动生产率特殊
function hr_show_two(monthDate,orgData){
	//劳动生产率文字
	var f=0,f1=0,f2=0;
	for(var k=0;k<monthDate.length;k++){
		//人均收入-当月--人均利润-当月
		if(monthDate[k].date_y==hrYear&&monthDate[k].date_m==hrMonth.replace(/^0/, '')&&f!=1){
			$("#rjsrId").html(parseFloat(monthDate[k].a00003).toFixed(2)+"万元");
			$("#rjlr").html(parseFloat(monthDate[k].a00004).toFixed(2)+"万元");
			f=1;
		}else if(f!=1){
			$("#rjsrId").html("-万元");//人均收入-当月
			$("#rjlr").html("-万元");	//人均利润-当月
		}
		//人均收入同比-去年当月--人均利润同比-去年当月
		if(monthDate[k].date_m==hrMonth.replace(/^0/, '')&&parseInt(monthDate[k].date_y)==(parseInt(hrYear)-1)&&f1!=1){
			$("#srtbId").html(parseFloat(monthDate[k].a00003).toFixed(2)+"万元");
			$("#lrtbId").html(parseFloat(monthDate[k].a00004).toFixed(2)+"万元");
			f1=1;
		}else if(f1!=1){
			$("#srtbId").html("-万");//人均收入同比-去年当月
			$("#lrtbId").html("-万");//人均利润同比-去年当月
		}
		//人均收入环比-上月--人均利润环比-上月
		if(hrMonth.replace(/^0/, '')!=1&&monthDate[k].date_y==hrYear&&parseInt(monthDate[k].date_m)==(parseInt(hrMonth)-1)&&f2!=1){
			$("#srhbId").html(parseFloat(monthDate[k].a00003).toFixed(2)+"万元");//人均收入环比-上月
			$("#lrhbId").html(parseFloat(monthDate[k].a00004).toFixed(2)+"万元");//人均利润环比-上月
			f2=1;
		}else if(hrMonth.replace(/^0/, '')==1&&parseInt(monthDate[k].date_y)==(parseInt(hrYear)-1)&&monthDate[k].date_m=='1'&&f2!=1){
			$("#srhbId").html(parseFloat(monthDate[k].a00003).toFixed(2)+"万元");//人均收入环比-上月
			$("#lrhbId").html(parseFloat(monthDate[k].a00004).toFixed(2)+"万元");//人均利润环比-上月
			f2=1;
		}else if(f2!=1){
			$("#srhbId").html("-万");//人均收入环比-上月
			$("#lrhbId").html("-万");//人均利润环比-上月
		}
	}
	//劳动生产率饼图
	var legend=new Array();		//legend 选项相同
	//1.当月收入业态饼图
	var chartsIdSr = "hr_echarts_two_sr";
	var seriesDataSr = new Array();	//数据
	var titleSr=hrOrgName+"当月收入业态分布图";	
	//2.当月利润饼图数据
	var chartsIdLr = "hr_echarts_two_lr";
	var seriesDataLr = new Array();	//数据
	var titleLr=hrOrgName+"当月利润业态分布图";	
	
	for(var i=0;i<orgData.length;i++){
		//设置收入饼图数据
		legend.push(orgData[i].orgnm);
		var seriesTempData={};
		seriesTempData["value"]=orgData[i].a00063;//当月收入
		seriesTempData["name"]=orgData[i].orgnm;
		seriesDataSr.push(seriesTempData);
		//设置利润饼图数据
		seriesTempData={};
		seriesTempData["value"]=orgData[i].a00065;//当月收入
		seriesTempData["name"]=orgData[i].orgnm;
		seriesDataLr.push(seriesTempData);
	}
	//收入饼图创建
	createNBarCharts(chartsIdSr,legend,seriesDataSr,titleSr);
	//利润饼图创建
	createNBarCharts(chartsIdLr,legend,seriesDataLr,titleLr);
}


//总人数
function hr_show_three(data){
	var chartsId="hr_echarts_three";
	var title=hrOrgName+hrYear+"年总人数数据";
	//var xAxis=new Array();	//xAxis x轴
	var series=new Array();//series 分度数据
	var seriesLdData = new Array();
	var seriesQtData = new Array();
	var stack=['0','1'];	//叠加参数
	var legend=['劳动用工数','其他用工数'];//legend 选项
	var type='bar';			//bar 柱状图 line  折线图
	for(var i=0;i<12;i++){
		var f=0;
		for(var j=0;j<data.length;j++){
			if(data[j]!=undefined&&data[j].date_m==(i+1)){
				seriesLdData.push(data[j].a00002);
				seriesQtData.push(data[j].a00046);
				f=1;
				break;
			}else{
			}
		}
		if(f!=1){
			seriesLdData.push("");
			seriesQtData.push("");
		}
	}
	//设置series
	series.push(seriesLdData);
	series.push(seriesQtData);
	//创建Echarts
	createLineCharts(chartsId,legend,xAxis,series,stack,type,title);
}

//劳动生产率hr_echarts_four
function hr_show_four(data){
	var chartsId="hr_echarts_four";
	var title=hrOrgName+hrYear+"年劳动生产率数据";
	//var xAxis=new Array();					//xAxis x轴
	var seriesData=new Array();				//series 分度数据
	var seriesSrData = new Array();
	var seriesLrData = new Array();
	var stack=['0','1'];					//叠加参数
	var legend=['人均收入(万元)','人均利润(万元)'];	//legend 选项
	var type='bar';							//bar 柱状图 line  折线图
	for(var i=0;i<12;i++){
		var f=0;
		for(var j=0;j<data.length;j++){
			if(data[j]!=undefined&&data[j].date_m==(i+1)){
				seriesSrData.push(data[j].a00003);
				seriesLrData.push(data[j].a00004);
				f=1;
				break;
			}else{
			}
		}
		if(f!=1){
			seriesSrData.push("");
			seriesLrData.push("");
		}
	}
	//设置series
	seriesData.push(seriesSrData);
	seriesData.push(seriesLrData);
	//创建Echarts
	createLineCharts(chartsId,legend,xAxis,seriesData,stack,type,title);
}


//管理干部数hr_echarts_five
function hr_show_five(data){
	var chartsId="hr_echarts_five";
	var title=hrOrgName+hrYear+"年管理干部数数据";
	//var xAxis=new Array();			//xAxis x轴
	var seriesData=new Array();		//series 分度数据
	var seriesNanData = new Array();
	var seriesNvData = new Array();
	var seriesMData = new Array();
	var seriesUmvData = new Array();
	var stack=['0','0','1','1'];	//叠加参数
	var legend=["管理干部男性人数","管理干部女性人数","管理干部M序列人数","管理干部非M序列人数"];//legend 选项
	for(var i=0;i<12;i++){
		var f=0;
		for(var j=0;j<data.length;j++){
			if(data[j]!=undefined&&data[j].date_m==(i+1)){
				seriesNanData.push(data[j].a00157);
				seriesNvData.push(data[j].a00158);
				seriesMData.push(data[j].a00159);
				seriesUmvData.push(data[j].a00160);
				f=1;
				break;
			}else{
			}
		}
		if(f!=1){
			seriesNanData.push("");
			seriesNvData.push("");
			seriesMData.push("");
			seriesUmvData.push("");
		}
	}
	
	//设置series
	seriesData.push(seriesNanData);
	seriesData.push(seriesNvData);
	seriesData.push(seriesMData);
	seriesData.push(seriesUmvData);	
	//创建Echarts
	createDlineCharts(chartsId,legend,xAxis,seriesData,stack,title);
}

//人工成本执行进度率hr_echarts_six
function hr_show_six(data){
	var chartsId="hr_echarts_six";
	var title=hrOrgName+hrYear+"年人工成本执行进度数据";
	//var xAxis=new Array();			//xAxis x轴
	var seriesData=new Array();		//series 分度数据
	var seriesZxData = new Array();
	var stack=['0','1'];			//叠加参数
	var legend=['人工成本执行进度率',''];	//legend 选项
	var type='line';				//bar 柱状图 line  折线图
	
	for(var i=0;i<12;i++){
		var f=0;
		for(var j=0;j<data.length;j++){
			if(data[j]!=undefined&&data[j].date_m==(i+1)){
				seriesZxData.push(data[j].a00061);
				f=1;
				break;
			}else{
			}
		}
		if(f!=1){
			seriesZxData.push("");
		}
	}
	//设置series
	seriesData.push(seriesZxData);
	seriesData.push('');
	//创建Echarts
	createLineCharts(chartsId,legend,xAxis,seriesData,stack,type,title);
}

//人力资源回报率hr_echarts_seven
function hr_show_seven(data){
	var chartsId="hr_echarts_seven";
	var title=hrOrgName+hrYear+"年人力资源回报率数据";
	//var xAxis=new Array();		//xAxis x轴
	var seriesData=new Array();	//series 分度数据
	var seriesHbData = new Array();
	var stack=['0','1'];		//叠加参数
	var legend=['人力资源回报率',''];//legend 选项
	var type='line';			//bar 柱状图 line  折线图
	for(var i=0;i<12;i++){
		var f=0;
		for(var j=0;j<data.length;j++){
			if(data[j]!=undefined&&data[j].date_m==(i+1)){
				seriesHbData.push(data[j].a00064);
				f=1;
				break;
			}else{
			}
		}
		if(f!=1){
			seriesHbData.push("");
		}
	}
	//设置series
	seriesData.push(seriesHbData);
	seriesData.push('');
	//创建Echarts
	createLineCharts(chartsId,legend,xAxis,seriesData,stack,type,title);
}

/*
 * 柱状图/折线图
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
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        }
	    },
	    legend: {
	        data:legend,//['人均收入','人均利润']
	        right:'0%'
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    xAxis : [
	        {
	        	axisLabel: {
	        	     interval:0//横轴信息全部显示
	        	},
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
                    type:type,
                    stack:stack[0],
                    data:seriesData[0]
                },
                {
                    name:legend[1],
                    type:type,
                    stack:stack[1],
                    data:seriesData[1]
                }
            ]
	};
	BarCWObj.setOption(option,true);
	window.onresize = BarCWObj.resize;
}

/*
 * 堆叠柱状图line
 * chartsId 接受图标dom的Id
 * legend 选项
 * xAxis x轴
 * series 分度数据
 */
function createDlineCharts(chartsId,legend,xAxis,seriesData,stack,title){
	var BarCWObj = echarts.init(document.getElementById(chartsId));
	option = {
		title : {
	        text: title,
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        }
	    },
	    legend: {
	        data:legend,
	        top:'10%'
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    xAxis : [
	        {
	        	axisLabel: {
	        	     interval:0//横轴信息全部显示
	        	},
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
                },
            	{
            		name:legend[2],
                    type:'bar',
                    stack:stack[2],
                    data:seriesData[2]
            	},
            	{
            		name:legend[3],
                    type:'bar',
                    stack:stack[3],
                    data:seriesData[3]
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
	    series :
		    [
	        {
	            name: '类型',
	            type: 'pie',
	            radius : '55%',
	            center: ['50%', '60%'],
	            data:seriesData,
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

//南丁格尔图
function createNBarCharts(chartsId,legend,seriesData,title){
	var BarCWObj = echarts.init(document.getElementById(chartsId));
	option = {
	 title : {
	        text: title,
	        subtext:"单位：万元",
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
	    series :[
	        {
	        	labelLine:{
		    		 normal:{
		    			 show:false
		    		 }
		    	 },
		    	label:{
		    		normal:{show:false}
		    	},
	            name:'类型(万元)',
	            type:'pie',
	            radius : [30, 100],
	            center : ['50%', '50%'],
	            roseType : 'area',
	            data:seriesData
	        }
	    ]
	};
	BarCWObj.setOption(option,true);
	window.onresize = BarCWObj.resize;
}