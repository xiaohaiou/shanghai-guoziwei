/**
 * 根据deptid 激活左边选择的职能样式并且修改显示位置并且加载数据
 * @param deptid
 */
function initData(){
	var org = $("#org").val();
	var orgName = $("#org").find("option:selected").text();
	$.ajax({
		url : basePath+"/administration/getAdData",
		type : "POST",
		data : {
				queryTime:queryTime,
				org:org
				},
		success : function(data) {
			//分配数据
			//行政督办echarts
			superviseEcharts(data[0], orgName);
			//保密事件table
			riskeventTable(data, orgName);
			//要情处理table/echarts
			importantTableAndEcharts(data, orgName);
			//公文审批table/echarts
			documentTableAndEcharts(data, orgName);
		}
	});
}

//行政督办echarts
function superviseEcharts(data, orgName){
	var superviseData = data.SHOWTAB_M11;
	var year = queryTime.substring(0,4);
	var total = "-";
	var finish = "-";
	var propel = "-";
	var ratio = "-";
	if(superviseData[0] != undefined){
		if(superviseData[0].a00014 != ""){
			total = superviseData[0].a00014;
		}
		if(superviseData[0].a00013 != ""){
			finish = superviseData[0].a00013;
		}
		if(total != "-" && finish != "-"){
			propel = parseInt(total) - parseInt(finish);
		}
		ratio = parseFloat(superviseData[0].a00015).toFixed(2);
	}
	var ele = "<div class=\"module_head\">"
	ele += "<span class='title_short' title='"+orgName+"行政督办办结率'>"+orgName+"行政督办办结率</span><span>"+year+"年</span>";
	if (orderFlg == '1'){
		ele += "<a class=\"zhiling\" onclick=\"sendorder(2,'SHOWTAB_M11','"+orgName+year+"年行政督办办结率')\">+ 指令下达</a>";
	}
	ele += "</div>";
	ele += "<div class=\"module_body\">";
	ele += "<p class=\"ll2_per1 font_g\">"+ratio+"<span>%</span></p>";
	ele += "<div class=\"xz1_div1\"><div><p>当年督办任务总数</p><p class=\"font_g\">"+total+"条</p>";
	ele += "</div>"
	ele += "<div><p>推进中任务数</p><p class=\"font_r\">"+propel+"条</p></div>";
	ele += "<div><p>办结任务数</p><p class=\"font_g\">"+finish+"条</p></div></div>";
	ele += "<div id=\"superviseEcharts\" style=\"height:200px\"></div></div>";
	$("#adSupervise").html(ele);
	//行政督办饼图
	var chartsId = "superviseEcharts";
	var legend= new Array();
	var seriesData = new Array();
	if(superviseData[0] != undefined && total != "-" && finish != "-"){
		legend=['推进中任务数（条）','办结任务数（条）'];//legend 选项
		//推进中任务督办数
		var seriesTempData={};
		seriesTempData["value"] = parseInt(total) - parseInt(finish);
		seriesTempData["name"] = legend[0];
		seriesData.push(seriesTempData);
		//办结任务督办数
		seriesTempData={};
		seriesTempData["value"] = superviseData[0].a00013;
		seriesTempData["name"] = legend[1];
		seriesData.push(seriesTempData);
	}
	cretaeBarCharts(chartsId,legend,seriesData,'');
}

//保密事件table
function riskeventTable(data, orgName){
	var year = queryTime.substring(0,4);
	var month = parseInt(queryTime.substring(5,queryTime.length-1));
	//1-3月为1季度保密事件数；4-6月为2季度保密事件数；7-9为3季度保密事件数 ；10-12为4季度保密事件数
	var season = "";
	var eventsNum = "-";
	if(month <= 3){
		season = 1;
		if(data[0].SHOWTAB_S13.length != 0){
			for(var i = 0; i<data[0].SHOWTAB_S13.length; i++){
				if(data[0].SHOWTAB_S13[i].date_s == 1){
					eventsNum = data[0].SHOWTAB_S13[i].a00054;
				}
			}
		}
	}else if(7 > month && month >= 4){
		season = 2;
		if(data[0].SHOWTAB_S13.length != 0){
			for(var i = 0; i<data[0].SHOWTAB_S13.length; i++){
				if(data[0].SHOWTAB_S13[i].date_s == 2){
					eventsNum = data[0].SHOWTAB_S13[i].a00054;
				}
			}
		}
	}else if(10 > month && month >= 7){
		season = 3;
		if(data[0].SHOWTAB_S13.length != 0){
			for(var i = 0; i<data[0].SHOWTAB_S13.length; i++){
				if(data[0].SHOWTAB_S13[i].date_s == 3){
					eventsNum = data[0].SHOWTAB_S13[i].a00054;
				}
			}
		}
	}else{
		season = 4;
		if(data[0].SHOWTAB_S13.length != 0){
			for(var i = 0; i<data[0].SHOWTAB_S13.length; i++){
				if(data[0].SHOWTAB_S13[i].date_s == 4){
					eventsNum = data[0].SHOWTAB_S13[i].a00054;
				}
			}
		}
	}
	var shtml = "";
	shtml += "<div class=\"module_head\">";
	shtml += "<span>"+orgName+"保密风险事件数</span>";
	shtml += "<span>"+year+"年"+season+"季度</span>";
	if (orderFlg == '1'){
		shtml += "<a class=\"zhiling\" onclick=\"sendorder(2,'SHOWTAB_S13','"+orgName+year+"年"+season+"季度保密事件数')\">+ 指令下达</a>";
	}
	shtml += "</div>";
	shtml += "<div class=\"module_body\">";
	shtml += "<div class=\"xz1_div2\">";
	shtml += "<p class=\"ll2_per1 font_r\"><span style='color:#333;font-size:14px;font-weight: bold;'>当季保密风险事件数： </span>"+eventsNum+"条</p>";
	shtml += "<table>";
	shtml += "<tr><th>序号</th><th>机构名称</th><th>第一季度</th><th>第二季度</th><th>第三季度</th><th>第四季度</th></tr>"

	var a = 1;
	for(var i=0;i<data.length;i++){
		var riskeventDatas = data[i].SHOWTAB_S13;
		if(riskeventDatas.length != 0){
			var name = riskeventDatas[0].orgnm
			if(i==0){
				shtml += "<tr class=\"tr_choosed\"><td>"+a+"</td><td>"+name+"</td>";
			}else{
				shtml += "<tr><td>"+a+"</td><td>"+name+"</td>";
			}
			for(var j=1;j<5;j++){
				var flag = false;
				for(var k = 0; k<riskeventDatas.length; k++){
					if(riskeventDatas[k].date_s == j){
						var val = riskeventDatas[k].a00054;
						shtml += "<td>"+val+"条</td>";
						flag = true;
						break;
					}
				}
				if(!flag){
					shtml += "<td>-</td>";
				}
			}
			shtml += "</tr>";
			a++;
		}
	}
	if(a==1){
		shtml += "<tr><td colspan=\"6\" align=\"center\">暂无数据</td></tr>";
	}
	shtml += "</table></div><div class=\"xz1_div3\"><p>事件内容</p></div></div>";
	$("#riskevent").html(shtml);	
}

//要情处理table/echarts
function importantTableAndEcharts(data, orgName){
	var shtml = "";
	var total = "-";
	var reported = "-";
	var ratio = "-";
	var month = parseInt(queryTime.substring(5,queryTime.length-1));
	var year = queryTime.substring(0,4);
	var title = orgName + year + "年要情处理及时率"
	var mainDatas = data[0].SHOWTAB_M14;
	if(mainDatas.length != 0){
		for(var i=0; i<mainDatas.length;i++){
			if(mainDatas[i].date_m == month){
				total = mainDatas[i].a00139;
				reported = mainDatas[i].a00140;
				ratio = parseFloat(mainDatas[i].a00055).toFixed(2);
			}
		}
	}
	shtml += "<div class=\"module_head\">";
	shtml += "<span>"+orgName+"要情处理及时率</span><span>"+year+"年"+month+"月</span>";
	if (orderFlg == '1'){
		shtml += "<a class=\"zhiling\" onclick=\"sendorder(2,'SHOWTAB_M14','"+orgName+year+"年"+month+"月要情处理及时率')\">+ 指令下达</a>"
	}
	shtml += "</div>";
	shtml += "<div class=\"module_body\"><div class=\"xz2_div1\">";
	shtml += "<p class=\"ll2_per1 font_r\">"+ratio+"<span>%</span></p>";
	shtml += "<div class=\"xz2_div11\">";
	shtml += "<p>要情总数量：<span class=\"font_r\">"+total+"条</span></p>";
	shtml += "<p>及时上报数量：<span class=\"font_r\">"+reported+"条</span></p></div>";
	shtml += "<table><tr><th>序号</th><th>机构名称</th><th>要情数量</th><th>及时上报数量</th><th>要情处理及时率</th></tr>";

	var a = 1;
	for(var i=0;i<data.length;i++){
		var importantDatas = data[i].SHOWTAB_M14;
		if(importantDatas.length != 0){
			for(var j=0;j<importantDatas.length;j++){
				var importantData = importantDatas[j];
				if(importantData.date_m == month){
					if(i == 0){
						shtml += "<tr class=\"tr_choosed\">";
					}else{
						shtml += "<tr>";
					}
					shtml += "<td>"+a+"</td><td>"+importantData.orgnm+"</td>";
					shtml += "<td>"+importantData.a00139+"条</td>";
					shtml += "<td>"+importantData.a00140+"条</td>";
					if(parseFloat(importantData.a00055).toFixed(2) > parseFloat("95.00")){
						shtml += "<td class=\"font_g\">"+parseFloat(importantData.a00055).toFixed(2)+"%</td></tr>";
					}else{
						shtml += "<td class=\"font_r\">"+parseFloat(importantData.a00055).toFixed(2)+"%</td></tr>";
					}
					a++;
				}
			}
		}
	}
	if(a==1){
		shtml += "<tr><td colspan=\"5\" align=\"center\">暂无数据</td></tr>";
	}
	shtml += "</table></div><div class=\"xz2_div2\" id=\"importantEcharts1\" style=\"height:250px\"></div></div>";
	$("#important").html(shtml);
	
	//要情处理柱状图
	var chartsId="importantEcharts1";
	var xAxis=new Array();			//xAxis x轴
	var seriesData=new Array();		//series 分度数据
	var legend=['要情处理及时率(%)'];//legend 选项
	var theDatas = data[0].SHOWTAB_M14;
	xAxis = ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'];
	for(var i = 0; i < xAxis.length; i++){
		//var xTemp=theDatas[i].date_m+"月";
		//xAxis.push(xTemp);
		var flag = false;
		if(theDatas.length != 0){
			for(var j = 0; j < theDatas.length; j++){
				if(theDatas[j].date_m+"月" == xAxis[i]){
					seriesData.push(parseFloat(theDatas[j].a00055).toFixed(2));
					flag = true;
					break;
				}
			}
			if(!flag){
				seriesData.push("-");
			}
		}else{
			seriesData.push("-");
		}
	}
	//创建Echarts
	cretaeCrossCharts(chartsId,legend,xAxis,seriesData,title);
	
	/*//要情处理
	var chartsId = "importantEcharts2";
	var legend=['一类要情','二类要情'];//legend 选项
	var seriesData = new Array();
	var seriesTempData={};
	//推进中任务督办数
	var seriesTempData={};
	seriesTempData["value"] = parseInt(total) - parseInt(finish);
	seriesTempData["name"] = legend[0];
	seriesData.push(seriesTempData);
	//办结任务督办数
	seriesTempData={};
	seriesTempData["value"] = superviseData[0].a00013;
	seriesTempData["name"] = legend[1];
	seriesData.push(seriesTempData);
	cretaeBarCharts(chartsId,legend,seriesData,'');*/
}

//公文审批table/echarts
function documentTableAndEcharts(data, orgName){
	var shtml = "";
	var total = "-";
	var overTime = "-";
	var avgTime = "-";
	var avgNode = "-";
	var ratio = "-";
	var month = parseInt(queryTime.substring(5,queryTime.length-1));
	var year = queryTime.substring(0,4);
	var title = orgName + year + "年公文审批情况";
	var mainDatas = data[0].SHOWTAB_M12;
	if(mainDatas.length != 0){
		for(var i = 0; i < mainDatas.length; i++){
			if(mainDatas[i].date_m == month){
				total = mainDatas[i].a00051;
				overTime = parseInt(mainDatas[i].a00051) - parseInt(mainDatas[i].a00052);
				avgTime = mainDatas[i].a00205;
				avgNode = mainDatas[i].a00206;
				ratio = parseFloat(mainDatas[i].a00053).toFixed(2);
			}
		}
	}
	shtml += "<div class=\"module_head\">";
	shtml += "<span>"+orgName+"公文审批及时率</span><span>"+year+"年"+month+"月</span>";
	if (orderFlg == '1'){
		shtml += "<a class=\"zhiling\" onclick=\"sendorder(2,'SHOWTAB_M12','"+orgName+year+"年"+month+"月公文审批及时率')\">+ 指令下达</a>";
	}
	shtml += "</div>";
	shtml += "<div class=\"module_body\">";
	shtml += "<div class=\"xz3_div1\">";
	shtml += "<p class=\"ll2_per1 font_g\">"+ratio+"<span>%</span></p><p class=\"xz3_clear\"></p>";
	shtml += "<p class=\"xz3_p1\">公文总量：<span class=\"font_g\">"+total+"条</span></p>";
	shtml += "<p class=\"xz3_p1\">超时公文总量：<span class=\"font_r\">"+overTime+"条</span></p>";
	shtml += "<p class=\"xz3_p2\">平均公文流转时间： <span>"+avgTime+"小时</span></p>";
	shtml += "<p class=\"xz3_p2\">" + "平均流转节点：<span>"+avgNode+"个</span></p>";
	shtml += "</div>";
	shtml += "<div class=\"xz3_div2\">";
	shtml += "<table><tr><th>序号</th><th>机构名称</th><th>公文总量</th><th>超时公文总量</th><th>公文审批及时率</th><th>平均公文流转时间</th><th>平均流转节点</th></tr>";

	var a = 1;
	for(var i=0;i<data.length;i++){
		var documentDatas = data[i].SHOWTAB_M12;
		if(documentDatas.length != 0){
			for(var j=0;j<documentDatas.length;j++){
				var documentData = documentDatas[j];
				if(documentData.date_m == month){
					if(i == 0){
						shtml += "<tr class=\"tr_choosed\">";
					}else{
						shtml += "<tr>";
					}
					shtml += "<td>"+a+"</td><td>"+documentData.orgnm+"</td>";
					shtml += "<td>"+documentData.a00051+"条</td>";
					shtml += "<td>"+(parseInt(documentData.a00051)-parseInt(documentData.a00052))+"条</td>";
					if(parseFloat(documentData.a00053).toFixed(2) >  parseFloat("95.00")){
						shtml += "<td class=\"font_g\">"+parseFloat(documentData.a00053).toFixed(2)+"%</td>";
					}else{
						shtml += "<td class=\"font_r\">"+parseFloat(documentData.a00053).toFixed(2)+"%</td>";
					}
					shtml += "<td>"+documentData.a00205+"小时</td><td>"+documentData.a00206+"个</td></tr>"
					a++;
				}
			}
		}
	}
	if(a==1){
		shtml += "<tr><td colspan=\"7\" align=\"center\">暂无数据</td></tr>";
	}
	shtml += "</table></div></div><div id=\"documentEcharts\" class=\"xz1_div4\" style=\"height:400px\"></div>";
	$("#document").html(shtml);
	
	//创建echarts
	var chartsId="documentEcharts";
	var xAxis=new Array();			//xAxis x轴
	var legend=['公文总数（条）','超时公文数（条）','平均公文流转时间（小时）','平均流转节点（个）','公文审批及时率（%）'];//legend 选项
	var theDatas = data[0].SHOWTAB_M12;
	var seriesData=new Array();				//series 分度数据,分成五份
	var totalData = new Array();
	var overTimeData = new Array();
	var avgTimeData = new Array();
	var avgNodeData = new Array();
	var ratioData = new Array();
	xAxis = ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'];
	for(var i = 0; i < xAxis.length; i++){
		//var xTemp=theDatas[i].date_m+"月";
		//xAxis.push(xTemp);
		var flag = false;
		if(mainDatas.length != 0){
			for(var j = 0; j < theDatas.length; j++){
				if(theDatas[j].date_m+"月" == xAxis[i]){
					totalData.push(theDatas[j].a00051);
					overTimeData.push(parseInt(theDatas[j].a00051) - parseInt(theDatas[j].a00052));
					avgTimeData.push(theDatas[j].a00205);
					avgNodeData.push(theDatas[j].a00206);
					ratioData.push(parseFloat(theDatas[j].a00053).toFixed(2));
					flag = true;
					break;
				}
			}
			if(!flag){
				totalData.push("-");
				overTimeData.push("-");
				avgTimeData.push("-");
				avgNodeData.push("-");
				ratioData.push("-");
			}
		}else{
			totalData.push("-");
			overTimeData.push("-");
			avgTimeData.push("-");
			avgNodeData.push("-");
			ratioData.push("-");
		}
	}
	//设置series
	seriesData.push(totalData);
	seriesData.push(overTimeData);
	seriesData.push(avgTimeData);
	seriesData.push(avgNodeData);
	seriesData.push(ratioData);
	//创建Echarts
	cretaeMixCharts(chartsId,legend,xAxis,seriesData,title);
}

//饼图
function cretaeBarCharts(chartsId,legend,seriesData,title){
	var BarCWObj = echarts.init(document.getElementById(chartsId));
	option = {
	 title : {
	        text: title,
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{b} : {c} ({d}%)"
	    },
	    legend: {
	        orient: 'vertical',
	        left:'left',
	        data: legend
	    },
	    series : [
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

//柱状图
function cretaeCrossCharts(chartsId,legend,xAxis,seriesData,title){
	var BarCWObj = echarts.init(document.getElementById(chartsId));
	option = {
		    title: {
		        text: title,
		        x:'center'
		    },
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'shadow'
		        }
		    },
		    /*legend: {
		        data: legend,
		        bottom: 'auto'
		    },*/
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis: {
		    	type: 'category',
		        data: xAxis
		    },
		    yAxis: {
		    	min: 0,
	            max: 100,
		        type: 'value',
		        boundaryGap: [0, 0.01]
		    },
		    series: [
		        {
		            name: legend[0],
		            type: 'bar',
		            data: seriesData
		        }
		    ]
		};
	BarCWObj.setOption(option,true);
	window.onresize = BarCWObj.resize;
}

/*
 * 柱状图与折线图混合
 * chartsId 接受图标dom的Id
 * legend 选项
 * xAxis x轴
 * series 分度数据
 */
function cretaeMixCharts(chartsId,legend,xAxis,seriesData,title){
	var BarCWObj = echarts.init(document.getElementById(chartsId));
	option = {
		title : {
		    text: title,
		    x:'center'
		},
		grid: {
		        top: '20%',
		    },
	    tooltip: {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'cross',
	            crossStyle: {
	                color: '#999'
	            }
	        }
	    },
	    /*toolbox: {
	        feature: {
	            dataView: {show: true, readOnly: false},
	            magicType: {show: true, type: ['line', 'bar']},
	            restore: {show: true},
	            saveAsImage: {show: true}
	        }
	    },*/
	    legend: {
	        data:legend,
	        center: 'auto',
	        top:"10%"
	    },
	    xAxis: [
	        {
	            type: 'category',
	            data: xAxis,
	            axisPointer: {
	                type: 'shadow'
	            }
	        }
	    ],
	    yAxis: [
	        {
	            type: 'value',
	            name: '数量',
	            min: 0,
	            max: 100000,
	            interval: 10000,
	        },
	        {
	            type: 'value',
	            name: '及时率',
	            min: 0,
	            max: 100,
	            interval: 10,
	        }
	    ],
	    series: [
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
	        },
	        {
	            name:legend[3],
	            type:'bar',
	            data:seriesData[3]
	        },
	        {
	            name:legend[4],
	            type:'line',
	            yAxisIndex: 1,
	            data:seriesData[4]
	        }
	    ]
	};
	BarCWObj.setOption(option,true);
	window.onresize = BarCWObj.resize;
}