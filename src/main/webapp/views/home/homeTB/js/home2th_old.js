var seldeptid=0;//当前选择的id;

//===公共方法=======
function init(){ 
	// 重点基建工程
	if($.inArray("bs_sp1", ButtonList)==-1){
		$('#dept_11').hide();
	}
	// 六率
	if($.inArray("bs_sp2", ButtonList)==-1){
		$('#dept_1').hide();
	}
	// 经营指标
	if($.inArray("bs_sp3", ButtonList)==-1){
		$('#dept_8').hide();
	}
	// 行政办公
	if($.inArray("bs_sp4", ButtonList)==-1){
		$('#dept_2').hide();
	}
	// 人力资源
	if($.inArray("bs_sp5", ButtonList)==-1){
		$('#dept_4').hide();
	}
	// 财务管理
	if($.inArray("bs_sp6", ButtonList)==-1){
		$('#dept_10').hide();
	}
	// 大额资产管理
	if($.inArray("bs_sp7", ButtonList)==-1){
		$('#dept_12').hide();
	}
	// 社会责任
	if($.inArray("bs_sp8", ButtonList)==-1){
		$('#dept_5').hide();
	}
	// 采购管理
	if($.inArray("bs_sp9", ButtonList)==-1){
		$('#dept_3').hide();
	}
	// 安全生产
	if($.inArray("bs_sp10", ButtonList)==-1){
		$('#dept_6').hide();
	}
	// 风险控制
	if($.inArray("bs_sp11", ButtonList)==-1){
		$('#dept_7').hide();
	}
        activeDeptIcon2(DeptId);
        seldeptid=DeptId;
        setTime();
      // alert(JSON.stringify(TBData))
 }

/**
 * 根据传入时间设定下拉框的值
 */
function setTime(){
	 if(WholeYear){
		/* var syear=$("#yearsel option:selected").val();
		 $("#yearsel option[value='"+syear+"']").attr("selected", false);*/
		 $("#yearsel").val("");
		 $("#yearsel").val(WholeYear);
		// $("#yearsel option[value='"+WholeYear+"']").attr("selected", true);
	 }
	 if(WholeMonth){
		 $("#monthsel").val("");
		 $("#monthsel").val(WholeMonth);
		/* $("#monthsel").find("option:selected").remove();
		 $("#monthsel option[value='"+WholeMonth+"']").attr("selected", true);*/
	 }
}




/**
 * 根据选择时间加载数据
 */
function changtime(){
	var syear=$("#yearsel option:selected").val();
	var smonth=$("#monthsel option:selected").val();
	WholeYear=syear;
	WholeMonth=smonth;
	loadTBdata(seldeptid);
}

/**
 * 打开一级弹窗
 */
function openlevel1window(){
	$('#chart-modal').jqm({overlay:75}).jqmShow()
}

/**
 * 打开二级弹窗
 */
function openlevel2window(){
    $('[data-remodal-id=modal2]').remodal({
		closeOnEscape: false,
	}).open();
}

/**
 * 根据deptid 激活左边选择的职能样式并且修改显示位置并且加载数据
 * @param deptid
 */
function activeDeptIcon2(deptid){
	try{
	$("[id^='dept_']").attr("class", "");
	$("#dept_"+deptid).attr("class", "active");
	var deptname=$("#dept_"+deptid+" span").text();
	
	if(deptname) {
		$("#loc_dept").text(deptname);
		$("#logo_dept").text(deptname);
	}
	  

	$("#loc_year").text(WholeYear+"年");
	$("#loc_month").text(WholeMonth+"月");


	if(deptid!=seldeptid&&seldeptid){
		loadTBdata(deptid);
	}else{
		selectedLoadMethod(deptid);
	}
	
	}catch(e){alert(e)}
	//$("#dept_2").attr("class", "");
}

/**
 * 根据deptid 激活左边选择的职能样式并且修改显示位置并且加载数据
 * @param deptid
 */
function activeDeptIcon(deptid){
	try{
	$("[id^='dept_']").attr("class", "");
	$("#dept_"+deptid).attr("class", "active");
//	var deptname=$("#dept_"+deptid+" span").text();
//	
//	if(deptname) {
//		$("#loc_dept").text(deptname);
//		$("#logo_dept").text(deptname);
//	}
//	  
//
//	$("#loc_year").text(WholeYear+"年");
//	$("#loc_month").text(WholeMonth+"月");
//
//
//	if(deptid!=seldeptid&&seldeptid){
//		loadTBdata(deptid);
//	}else{
//		selectedLoadMethod(deptid);
//	}
	location.href = basePath + "/home/getTBDataByDept1?year="+WholeYear+"&month="+WholeMonth+"&deptid="+deptid;
	
	}catch(e){alert(e)}
	//$("#dept_2").attr("class", "");
}


/**
 * 根据部门id加载表数据
 * @param deptid
 */
function loadTBdata(deptid){
		$.ajax({
			url : basePath+"/home/getTBDataByDept2",
			type : "POST",
			data : {
				years:WholeYear,
		        month:WholeMonth,
				deptid:deptid      
			},
			success : function(data) {
				TBData=data;
				//alert(JSON.stringify(data))
				seldeptid=deptid;
				selectedLoadMethod(deptid);
			},
			error : function() {
				alert("获取数据失败");
			}
		});
	
}

/**
 * 准备下穿数据
 * @param selLengend 当前选择的坐标系数据
 * @param selXdata   当前选择的x轴数据
 */
function readyNLVdata(levelid,selLengend,selXdata){
	var timetype=selLengend.showtab_time_type;
	var tbid=selLengend.storetab_name;
	if(selLengend.length > 1){
		timetype=selLengend[0].showtab_time_type;
		tbid=selLengend[0].storetab_name;
	}
	var year="";
	var month="";
    var season="";
    var week="";
    var day="";
    if(timetype==1){ //周数据
    	year=WholeYear;
    	month=WholeMonth;
    	week=getDateByxAxis(selXdata);
    }else if(timetype==2){ //月数据
    	year=WholeYear;
    	month=getDateByxAxis(selXdata);
    }else if(timetype==3){ //季度数据
    	year=WholeYear;
    	season=getDateByxAxis(selXdata);
    }else if(timetype==4){ //年度数据
    	year=getDateByxAxis(selXdata);
    }else if(timetype==5){ //天数据
    	year=WholeYear;
    	month=WholeMonth;
    	day=getDateByxAxis(selXdata);
    }
    
   // alert("levelid="+levelid+" year="+year+" month="+month+" season="+season+" week="+week+" day="+day+" tbid="+tbid+" timetype="+timetype)
    loadNextLVTbdata(levelid,tbid,timetype,year,month,season,week,day,selLengend,selXdata)
}

/**
 * 将x周的数据单位去除还原原来的数据
 * @param time_type
 * @param valuedata
 * @returns {Array}
 */
function getDateByxAxis(valuedata){
	var dates="";
	dates=valuedata.replace(/[^0-9]/ig,"");	
	return dates;
}


/**
 * 核心企业下穿
 * @param levelid 下穿登记 最多到3级
 * @param timetype 时间类型 不同的时间类型需要不同的时间字段
 * @param tbid 表id 必填
 * @param year 必有
 * @param month  根据时间类型需要来 如果是月度数据就为必有
 * @param season   根据时间类型需要来 如果是季度数据就为必有
 * @param week     根据时间类型需要来 如果是周度数据就为必有
 * @param day     根据时间类型需要来 如果是日度数据就为必有
 */
function loadNextLVTbdata(levelid,tbid,timetype,year,month,season,week,day,selLengend,selXdata){
	var datajson={
			levelid:levelid,
			timetype:timetype,
			tbid:tbid,
			year:year,
			month:month,
			week:week,
			season:season,
			day:day};
	$.ajax({
		url : basePath+"/home/getTBDataByLevel",
		type : "POST",
		data : datajson,
		success : function(data) {
		    //alert(JSON.stringify("128==="+JSON.stringify(data)))
			if(data==0){
				alert("无对应下层数据");
			}else{
				if(levelid==1) deallevel1data(data,selLengend,selXdata,datajson)
				//openlevel1window();
			}
		},
		error : function() {
			alert("获取数据失败");
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
	var datajson={
			levelid:levelid,
			timetype:timetype,
			tbid:tbid,
			nameTitle:nameTitle,
			name1:name1,
			name2:name2};
	$.ajax({
		url : basePath+"/home/getExtraTBDataByLevel",
		type : "POST",
		data : datajson,
		success : function(data) {
			if(data==0){
				alert("无对应下层数据");
			}else{
				if(levelid==1){
					var level1_title = name + "  信息";
					if(tbid=="SHOWTAB_M28"){
						level1_title = nameTitle+name1+"年第"+name2+"月安全结果指标监管情况";
						createSafetyResultsLevel(data);
					}else if(tbid == 'SHOWTAB_S29'){
						level1_title = nameTitle+name1+"年第"+name2+"季度安全过程指标监管情况";
						createAQGCLevel(data);
					}else{
						createPinPaiLevel(data);
					}
					$('#level1_title').text(level1_title);
					openlevel1window();
					level1chartOBj.resize();
				}
			}
		},
		error : function() {
			alert("获取数据失败");
		}
	});
}

/**
 * 企业二级下钻
 * @param levelid  下钻层级
 * @param orgindata 原始数据
 * @param org2nm   选取的二级企业名字
 * @param lengenddata   一级下钻的标题
 * @param org2nm   一级下钻选取的指标
 */
function loadNextLV2Tbdata(levelid,orgindata,org2nm,lengenddata,indexid,selLengend){
	orgindata.org2nm=org2nm;	
	orgindata.levelid=levelid;
	//alert(JSON.stringify(orgindata));
	$.ajax({
		url : basePath+"/home/getTBDataByLevel2",
		type : "POST",
		data : orgindata,
		success : function(data) {
		   // alert(JSON.stringify("211==="+JSON.stringify(data)))
			if(data==0){
				alert("无对应下层数据");
			}else{
				deallevel2data(data,orgindata,org2nm,lengenddata,indexid,selLengend)
				//openlevel1window();
			}
		},
		error : function() {
			alert("获取数据失败");
		}
	});
}



/**
 * 选择加载不同职能体系的方式
 * @param deptid
 */

function selectedLoadMethod(deptid){
	seldeptid=deptid;
	if(deptid==2) loadXZTBdata(deptid); //行政指标
	if(deptid==4) loadRLTBdata(deptid); //人力资源
	if(deptid==10) loadCZTBdata(deptid);//财政指标
	if(deptid==1) loadBLTBdata(deptid); //六率指标
	if(deptid==7) loadFKTBdata(deptid);//风控指标
	if(deptid==8) loadNEWJYTBdata(deptid);//经营指标
	if(deptid==5) loadSZTBdata(deptid);//社会责任指标
	if(deptid==3) loadCGTBdata(deptid);//采购指标
	if(deptid==6) loadAQTBdata(deptid);//加载安全指标
	
	//cf,此div只用于安全生产模块显示，其余模块下隐藏此div
	if(deptid!=6){
		$("#Chart_depit1").css('display','none');
	}else{
		$("#Chart_depit1").css('display','block');
	}
}

/**
 * 根据表头信息自动生成展现图形的元素（通用）
 * @param titledata 表头信息集合
 * @returns
 */
function createDeptChartEle(titledata,deptid){
	var path=basePath+"";
	try {
		var sortlist=new Array();
		var ChartEle="";
		if(!deptid) deptid=0;
		for(var i =0;i<titledata.length; i++){
			var chartid=titledata[i].storetab_name;
			var charttitle=titledata[i].showtab_nm;
			if(chartid == 'SHOWTAB_M68'){
				charttitle = '财务四率（月）';
			}
			if(chartid =='SHOWTAB_W67'){
				charttitle = '财务四率（周）';
			}
			if(chartid == 'SHOWTAB_M71'){
				charttitle = '总收入利润（月）';
			}
			if(chartid == 'SHOWVIEW_M75'){
				charttitle = '创新类收入利润（月）';
			}
			if(chartid == 'SHOWVIEW_M76'){
				charttitle = '投资类收入利润（月）';
			}
			if(chartid == 'SHOWVIEW_M77'){
				charttitle = '运营类收入利润（月）';
			}
			if(chartid == 'SHOWTAB_W70'){
				charttitle = '收入利润（周）';
			}
			if(chartid == 'SHOWTAB_M61'){
				charttitle = '现金流平衡（月）';
			}
			if(chartid =='SHOWTAB_W62'){
				charttitle = '现金流平衡（周）';
			}
			var ChartTbEle='';
			if($.inArray(chartid, sortlist)==-1){
				sortlist.push(chartid);
				ChartTbEle='<div class="col-sm-6"><div class="white-bg"><span  class="title"><span id="'+chartid+'_Charttitle">'+charttitle+'</span>';
				// 指令按钮
				if($.inArray("bs_sp12", ButtonList)!=-1){
					ChartTbEle+="<span class='click new bg-blue' onclick=\"mload('"+path+"/task/instruction/_add?type="+deptid+"&mapId="+chartid+"&taskName="+(charttitle).replace(/\%/g,'%25')+"')\">+&nbsp;指令下达</span>";
				} 
				ChartTbEle+='</span><div class="chartbox"><div id="'+chartid+'_Chart" style="width: 100%; height: 300px;"></div></div>';
				ChartTbEle+='</div></div>';
				ChartEle+=ChartTbEle;
			}
		}
		$('#Chart_depit').html(ChartEle);
	} catch(e) {
		alert(e);
	}
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
			if(titledata[i].int_storetab_field=='A00059')continue;
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
 * 社会责任x轴按日拼接周五特殊
 * @param time_type 时间类型
 * 
 */
function getxAxisD24(time_type,valuedata){
	time_key="date_d";
	time_uom="号";
	var xAxisdata=new Array();
	for(var i =0;i<valuedata.length; i++){
		if(valuedata[i].date_from==valuedata[i].date_end){
			var x_Axis=valuedata[i].date_from.substr(valuedata[i].date_from.length-2,2).replace(/\b(0+)/gi,"")+time_uom;
		}else{
			var x_Axis=valuedata[i].date_from.substr(valuedata[i].date_from.length-2,2).replace(/\b(0+)/gi,"")+time_uom;
			x_Axis+="-";
			x_Axis+=valuedata[i].date_end.substr(valuedata[i].date_end.length-2,2).replace(/\b(0+)/gi,"")+time_uom;
		}
		if($.inArray(x_Axis, xAxisdata)==-1){
	    	xAxisdata.push(x_Axis);
	    }
	}
	return xAxisdata;
}

/**
 * 风控x轴年月拼接展示纬度
 * @param time_type 时间类型
 * 
 */
function getxAxisFK(time_type,valuedata){
	var time_key="date_m";
	var time_uom="月"
	if(time_type=="1"){time_key="date_w";time_uom="周";}
	if(time_type=="2"){time_key="date_m";time_uom="月";}
	if(time_type=="3"){time_key="date_s";time_uom="季";}
	if(time_type=="4"){time_key="date_y";time_uom="年";}
	if(time_type=="5"){time_key="date_d";time_uom="天";}
	var xAxisdata=new Array();
	for(var i =0;i<valuedata.length; i++){
		if(valuedata[i].date_m!="12"){
			var x_Axis=valuedata[i][time_key]+time_uom+valuedata[i].date_m+"月";
	    	if($.inArray(x_Axis, xAxisdata)==-1){
	    		xAxisdata.push(x_Axis);
	    	}
		}else{
			var x_Axis=valuedata[i][time_key]+time_uom;
	    	if($.inArray(x_Axis, xAxisdata)==-1){
	    		xAxisdata.push(x_Axis);
	    	}
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
 * @param  表对应数据集
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

/**
 * 获取坐标系名称
 * @param sorttitledata 坐标系参数集合
 * @returns {Array}
 */
function getlengenddata(sorttitledata){
	var lengend=new Array();
	for(var i =0;i<sorttitledata.length; i++){
		//alert(JSON.stringify(sorttitledata))
		var lengendname=sorttitledata[i].index_nm;
		var lengenduom=sorttitledata[i].uom;	
		if(lengenduom) lengendname+="("+lengenduom+")";
		lengend.push(lengendname);
	}
	return lengend;
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


//============处理一级下钻数据===========

/**
 * 将一级下钻数据处理
 * @param tbdata    获取的一级下钻数据
 * @param selLengend 选中的坐标系
 * @param selXdata   选中的x周数据
 * @param orgindata  原图数据
 */
function deallevel1data(tbdata,selLengend,selXdata,orgindata){
	try{
		var titleStr = "";
		if (orgLevel == "0") {
			titleStr = "海航实业各核心企业 ";
		} else {
			titleStr = tbdata[0].org2nm + "各单位 ";
		}
		// 堆叠图特殊处理
		if(selLengend.length > 1){
			var indexnm=selLengend[0].showtab_nm;
			var indexuom=selLengend[0].uom;
			var lengenddata=titleStr+WholeYear+"年"+selXdata+indexnm;
			var seriusdata = getSeriesData(selLengend,tbdata);
			var lengendselect=getlengenddata(selLengend);
		} else {
			var indexid=(selLengend.int_storetab_field).toLowerCase();
			var indexnum=selLengend.index_nm;
			var indexuom=selLengend.uom;
			var lengenddata=titleStr+WholeYear+"年"+selXdata+indexnum;
			var seriusdata=getLevelSeriesDataBySeriesName(indexid,tbdata);
		}
		
		var xAxisdata="";
		if (orgLevel == "0") {
			xAxisdata=getxAxisBylevel("org2nm",tbdata);
		} else {
			xAxisdata=getxAxisBylevel("org3nm",tbdata);
			if(selLengend.storetab_name=="SHOWTAB_M30"){
				xAxisdata=getxAxisBylevel("shenji_type_nm",tbdata);
			}else if(selLengend.storetab_name=="SHOWTAB_M31"){
				xAxisdata=getxAxisBylevel("shenjiwenti_type_nm",tbdata);
			}
		}
		var eleid="level1_chart";
		if(selLengend.storetab_name=="SHOWTAB_M15"){
			var xAxisdata=getxAxisBylevel("a00089",tbdata);
			lengenddata=lengenddata.replace("海航实业各核心企业","库内工程类");
		}else if(selLengend.storetab_name=="SHOWTAB_M16"){
			var xAxisdata=getxAxisBylevel("a00091",tbdata);
			lengenddata=lengenddata.replace("海航实业各核心企业","");
			lengenddata=lengenddata.replace("采购金额","累计完成采购金额");
		}else if(selLengend.storetab_name=="SHOWTAB_M27"){
			var xAxisdata=getxAxisBylevel("a00119",tbdata);
			lengenddata=lengenddata.replace("海航实业各核心企业","各媒体类型");
		}else if(selLengend.storetab_name=="SHOWVIEW_M75"){
			lengenddata=lengenddata.replace("收入","创新类收入");
		}else if(selLengend.storetab_name=="SHOWVIEW_M76"){
			lengenddata=lengenddata.replace("收入","投资类收入");
		}else if(selLengend.storetab_name=="SHOWVIEW_M77"){
			lengenddata=lengenddata.replace("收入","运营类收入");
		}
		
		// 堆叠图特殊处理
		if(selLengend.length > 1){
			var showtab=selLengend[0].storetab_name;
			// 经营指标_现金流平衡
			if(showtab=="SHOWTAB_M61"){
				var linedata=new Array();
				linedata.push("A00084");
				createPuBuChartLevel(eleid,lengenddata,xAxisdata,seriusdata,selLengend,orgindata,lengendselect,linedata);
			} else {
				createStackingChartLevel(eleid,lengenddata,xAxisdata,seriusdata,selLengend,orgindata,lengendselect);	
			}
		}else{
			if (orgLevel != "0" && selLengend.storetab_name=="SHOWTAB_M31") {
				createlineChartLevel(eleid,lengenddata,xAxisdata,seriusdata,selLengend,orgindata,indexid,'14',30);
			} else {
				createlineChartLevel(eleid,lengenddata,xAxisdata,seriusdata,selLengend,orgindata,indexid);
			}
		}
		
		$('#level1_title').text(lengenddata);
		openlevel1window();
		level1chartOBj.resize();
	}catch(e){
		alert("123=="+e)
	}
	//alert(JSON.stringify(tbdata))
	//alert(JSON.stringify(seriusdata))
}

/**
 * 获取一级下钻x轴坐标
 * @param time_type 时间类型
 * 
 */
function getxAxisBylevel(key,valuedata){
	var xAxisdata=new Array();
	for(var i =0;i<valuedata.length; i++){
	       var x_Axis=valuedata[i][key];
	       if($.inArray(x_Axis, xAxisdata)==-1){
	    	   xAxisdata.push(x_Axis);
	       }
	 }
	return xAxisdata;
}

/**
 * 二级下钻处理
 * @param data
 * @param orgindata
 * @param org2nm
 * @param lengenddata
 * @param indexid
 * @param selLengend
 */
function deallevel2data(data,orgindata,org2nm,lengenddata,indexid,selLengend){
	try{
		var rotate=0;
		if(orgindata.tbid=="SHOWTAB_M30"){
			var xAxisdata=getxAxisBylevel("shenji_type_nm",data);
			lengenddata=lengenddata.replace("核心企业","审计项目类型");
		}else if(orgindata.tbid=="SHOWTAB_M31"){
			var xAxisdata=getxAxisBylevel("shenjiwenti_type_nm",data);
			rotate=30;
			lengenddata=lengenddata.replace("核心企业","审计发现问题类型");
		}else{
			var xAxisdata=getxAxisBylevel("org3nm",data);
		}
		var eleid="level1_chart";
		// 堆叠图特殊处理
		if(selLengend.length > 1){
			var seriusdata = getSeriesData(selLengend,data);
			var showtab=selLengend[0].storetab_name;
			// 经营指标_现金流平衡
			if(showtab=="SHOWTAB_M61"){
				var linedata=new Array();
				linedata.push("A00084");
				createPuBuChartLevel(eleid,lengenddata,xAxisdata,seriusdata,selLengend,orgindata,indexid,linedata);
			} else {
				createStackingChartLevel(eleid,lengenddata,xAxisdata,seriusdata,selLengend,orgindata,indexid);
			}
			
		} else {
			var seriusdata=getLevelSeriesDataBySeriesName(indexid,data);
			createlineChartLevel(eleid,lengenddata,xAxisdata,seriusdata,selLengend,orgindata,indexid,'14',rotate);
		}
		lengenddata=lengenddata.replace("海航实业",org2nm)
		//alert(lengenddata)
		//lengenddata=lengenddata+"("+org2nm+"下级)"
		$('#level1_title').text(lengenddata);
	}catch(e){alert("123=="+e)}
	//alert(JSON.stringify(seriusdata))
}



/**
 * 根据坐标轴名获取对应的坐标轴数据
 * @param SeriesName 目录名
 * @param  表对应数据集
 * @param xAxisval   对应的x轴数据
 */
function getLevelSeriesDataBySeriesName(indexid,valuedata){
	var date=new Array();
	for(var i =0;i<valuedata.length; i++){
		var dataval=parseFloat(valuedata[i][indexid]);
		if(!dataval) dataval=0;
		date.push(dataval);		
	}
	return date;
}




//=======行政办公数据加载模块js================
function loadXZTBdata(deptid){
	var titledata=TBData[0];
	var valdata=TBData[1];
	var dept=2;
	if(deptid) dept=deptid;
	createDeptChartEle(titledata,dept);
	for(var i in valdata){
		var rowdata=valdata[i];	
		var sorttitledata=new Array();
		sorttitledata=getIndexByKey(titledata,i)
		var time_type=sorttitledata[0].showtab_time_type ;//同一个表的时间类型都一样 取第一个数据就行
		try{
		var xAxisdata=getxAxis(time_type,rowdata);
		var seriesdata=getSeriesData(sorttitledata,rowdata);
		var lengenddata=getlengenddata(sorttitledata);
		//alert("sorttitledata "+i+" ==="+JSON.stringify(sorttitledata));
	    //alert("valdata"+i+"==="+JSON.stringify(valdata[i]))
		//alert("xAxisdata "+i+" ==="+JSON.stringify(xAxisdata));
		//alert("seriesdata "+i+" ==="+JSON.stringify(seriesdata));
		var eleid=i+"_Chart";
		if(i=="SHOWTAB_M14"){
		  createBarChart(eleid,lengenddata,xAxisdata,seriesdata,sorttitledata);
		}else{
		  createlineChart(eleid,lengenddata,xAxisdata,seriesdata,sorttitledata);
		}
		}catch(e){alert(e)}

	}	
}

/**
 * 得到行政图形 其中会定义一些表的特殊展现方式 如果没有定义一律定义为柱状图
 */
function getXZChart(){
	
}

//=======人力资源数据加载模块js================
function loadRLTBdata(deptid){
	var titledata=TBData[0];
	var valdata=TBData[1];
	var dept=4;
	if(deptid) dept=deptid;
	createDeptChartEle(titledata,dept);
	for(var i in valdata){
	
		var rowdata=valdata[i];	
		var sorttitledata=new Array();
		sorttitledata=getIndexByKey(titledata,i)
		var time_type=sorttitledata[0].showtab_time_type;//同一个表的时间类型都一样 取第一个数据就行
		try{
		var xAxisdata=getxAxis(time_type,rowdata);
		var seriesdata=getSeriesData(sorttitledata,rowdata);
		var lengenddata=getlengenddata(sorttitledata);
		//alert("sorttitledata "+i+" ==="+JSON.stringify(sorttitledata));
	    //alert("valdata"+i+"==="+JSON.stringify(valdata[i]))
		//alert("xAxisdata "+i+" ==="+JSON.stringify(xAxisdata));
		//alert("seriesdata "+i+" ==="+JSON.stringify(seriesdata));
		var eleid=i+"_Chart";
		if(i=="SHOWTAB_M18"||i=="SHOWTAB_M19"){
			createStackingChart(eleid,lengenddata,xAxisdata,seriesdata,sorttitledata);
		}else if(i=="SHOWTAB_M21"){
			createBarChart(eleid,lengenddata,xAxisdata,seriesdata,sorttitledata);
		}else{
			createlineChart(eleid,lengenddata,xAxisdata,seriesdata,sorttitledata);
		}
		
		}catch(e){alert(e)}

	}	
}

/**
 * 人力-管理干部人数单独处理 
 */
function getXZ_GLRSChart(eleid,lengenddata,xAxisdata,seriesdata,sorttitledata){
	
}

/**
 * 产生独立人力资源的html展现元素
 * @param titledata
 */
function createXZEle(titledata){
	try{
		var sortlist=new Array();
		var ChartEle="";
		var SHOWTAB_M19Ele="";
			var path=basePath+"";
		for(var i =0;i<titledata.length; i++){
			var chartid=titledata[i].storetab_name;
			var charttitle=titledata[i].showtab_nm;
			var ChartTbEle='';
			if($.inArray(chartid, sortlist)==-1){
				sortlist.push(chartid);
				if(chartid=="SHOWTAB_M19"){
					SHOWTAB_M19Ele='<div class="col-sm-12"><div class="white-bg"><span class="title"><span id="'+chartid+'_Charttitle">'+charttitle+'</span>';
					// 指令按钮
					if($.inArray("bs_sp12", ButtonList)!=-1){
						SHOWTAB_M19Ele+="<span class='click new bg-blue' onclick=\"mload('"+path+"/task/instruction/_add&taskName="+(charttitle).replace(/\%/g,'%25')+"')\">+&nbsp;指令下达 </span>";
					}
					SHOWTAB_M19Ele+='</span><div class="chartbox"><div id="'+chartid+'_Chart" style="width: 100%; height: 300px;"></div></div>';
					SHOWTAB_M19Ele+='</div></div>';
					SHOWTAB_M19Ele+=ChartTbEle;
				}else{
					ChartTbEle='<div class="col-sm-4"><div class="white-bg"><span class="title"><span  id="'+chartid+'_Charttitle">'+charttitle+'</span>';
					// 指令按钮
					if($.inArray("bs_sp12", ButtonList)!=-1){
						ChartTbEle+="<span class='click new bg-blue' onclick=\"mload('"+path+"/task/instruction/_add&taskName="+(charttitle).replace(/\%/g,'%25')+"')\">+&nbsp;指令下达 </span>";
					}
					ChartTbEle+='</span><div class="chartbox"><div id="'+chartid+'_Chart" style="width: 100%; height: 300px;"></div></div>';
					ChartTbEle+='</div></div>';
					ChartEle+=ChartTbEle;
				}
			}
		}
		ChartEle+=SHOWTAB_M19Ele;
		$('#Chart_depit').html(ChartEle);
	}catch(e){
		alert(e);
	}
}

//===========财政模块=============================
/**
 * 加载财政数据
 */
function loadCZTBdata(deptid){
	var titledata=TBData[0];
	var valdata=TBData[1];
	var dept=10;
	if(deptid) dept=deptid;	
	createDeptChartEle(titledata,dept);
	for(var i in valdata){
		var rowdata=valdata[i];	
		var sorttitledata=new Array();
		sorttitledata=getIndexByKey(titledata,i)
		var time_type=sorttitledata[0].showtab_time_type ;//同一个表的时间类型都一样 取第一个数据就行
		try{
		var xAxisdata=getxAxis(time_type,rowdata);
		var seriesdata=getSeriesData(sorttitledata,rowdata);
		var lengenddata=getlengenddata(sorttitledata);
		var eleid=i+"_Chart";
		if(i=="SHOWTAB_M72"){
		   //准备搞个别的图
		   createlineChart(eleid,lengenddata,xAxisdata,seriesdata,sorttitledata);
		}else{
		  createlineChart(eleid,lengenddata,xAxisdata,seriesdata,sorttitledata);
		}
		}catch(e){alert(e)}

	}	
}

//========8率模块======
function loadBLTBdata(deptid){
	var dept=1;
	if(deptid) dept=deptid;
	createBLChartEle(dept);
	var rowdata=TBData[0];
	try{
	for(var i in rowdata){
		//alert(i+"==="+JSON.stringify(rowdata[i]))
		var kedata=rowdata[i];
		if(i=="SHOWTAB_M2" && orgLevel == "0"){
			var mdata=kedata; 
//			alert(JSON.stringify(mdata));
			
			var indexid=BLtitledata[2];
			
			var eid = indexid+'_Chart';
			createSCChart(eid,mdata);
			
			var processHtml = "";
			var p1 = "";
			var p2 = "";
			
			for(var jj=mdata.length-1; jj>=0; jj--){
				if(mdata[jj].org2id=="4010" && p1==""){
					p1 = parseFloat(mdata[jj].a00008).toFixed(2);
				}else if(mdata[jj].org2id=="27534" && p2==""){
					p2 = parseFloat(mdata[jj].a00008).toFixed(2);
				}
			}
			
			processHtml+='<div class="one_item"><p>'+p1+'<span>%</span></p><p>酷铺</p><div class="progress">';
			processHtml+='<div class="progress-bar progress-bar-self" aria-valuemin="0" aria-valuemax="100" style="width: '+p1+'%;"></div>';
			processHtml+='</div></div>';
			
			processHtml+='<div class="one_item"><p>'+p2+'<span>%</span></p><p>海航地产</p><div class="progress">';
			processHtml+='<div class="progress-bar progress-bar-self" aria-valuemin="0" aria-valuemax="100" style="width: '+p2+'%;"></div>';
			processHtml+='</div></div>';
			
		    $('#right2').html(processHtml);
			
		}else if(i=="SHOWTAB_M68"){
			var mdata=kedata; 
//			alert(JSON.stringify(mdata));
			
			var indexid=BLtitledata[1];
			
			var eid = indexid+'_Chart';
			create4LVChart(eid,mdata);
			
			var processHtml = "";
			processHtml+='<div class="one_item"><p>'+parseFloat(mdata[mdata.length-1].a00066)+'<span>%</span></p><p>资产周转率</p><div class="progress">';
			processHtml+='<div class="progress-bar progress-bar-self" aria-valuemin="0" aria-valuemax="100" style="width: '+mdata[mdata.length-1].a00066+'%;"></div>';
			processHtml+='</div></div>';
			
			processHtml+='<div class="one_item"><p>'+parseFloat(mdata[mdata.length-1].a00067)+'<span>%</span></p><p>资产负债率</p><div class="progress">';
			processHtml+='<div class="progress-bar progress-bar-self" aria-valuemin="0" aria-valuemax="100" style="width: '+mdata[mdata.length-1].a00067+'%;"></div>';
			processHtml+='</div></div>';
			
			processHtml+='<div class="one_item"><p>'+parseFloat(mdata[mdata.length-1].a00068)+'<span>%</span></p><p>资本利润率</p><div class="progress">';
			processHtml+='<div class="progress-bar progress-bar-self" aria-valuemin="0" aria-valuemax="100" style="width: '+mdata[mdata.length-1].a00068+'%;"></div>';
			processHtml+='</div></div>';
			
			processHtml+='<div class="one_item"><p>'+parseFloat(mdata[mdata.length-1].a00069)+'<span>%</span></p><p>净资产收益率</p><div class="progress">';
			processHtml+='<div class="progress-bar progress-bar-self" aria-valuemin="0" aria-valuemax="100" style="width: '+mdata[mdata.length-1].a00069+'%;"></div>';
			processHtml+='</div></div>';
			
		    $('#right1').html(processHtml);
			
		}else if(i=="SHOWTAB_M1"){
			var mdata=kedata; 
//			alert(JSON.stringify(mdata));
			
			var indexid=BLtitledata[0];
			
			var eid = indexid+'_Chart';
			createLaoDongChart(eid,mdata);
			
			var p1 = parseFloat(mdata[mdata.length-1].a00003);
			var p2 = parseFloat(mdata[mdata.length-1].a00004);
			var point1 = "";
			var point2 = "";
			var n = Math.max(p1,p2);
			if(n==0){
				point1 = "0";
				point1 = "0";
			}else{
				if(n==p1){
					point1 = "80";
					point2 = ""+((p2*80)/p1);
				}else if(n==p2){
					point2 = "80";
					point1 = ""+(p1*80/p2);
				}
			}
			var processHtml = "";
			processHtml+='<div class="one_item"><p>'+parseFloat(mdata[mdata.length-1].a00003)+'<span>万元</span></p><p>人均收入</p><div class="progress">';
			processHtml+='<div class="progress-bar progress-bar-self" aria-valuemin="0" aria-valuemax="100" style="width: '+point1+'%;"></div>';
			processHtml+='</div></div>';
			
			processHtml+='<div class="one_item"><p>'+parseFloat(mdata[mdata.length-1].a00004)+'<span>万元</span></p><p>人均利润</p><div class="progress">';
			processHtml+='<div class="progress-bar progress-bar-self" aria-valuemin="0" aria-valuemax="100" style="width: '+point2+'%;"></div>';
			processHtml+='</div></div>';
			
		    $('#right0').html(processHtml);
		}
	}
	}catch(e){alert("获取数据为空")}
}

function getBLChart(keypos,kedata){	
	var indexid=BLtitledata[keypos];
	//alert("kedata="+JSON.stringify(kedata)+"  indexid="+indexid)
	var cicleSelfVal=0;

	cicleSelfVal=kedata[indexid];
	
	var eleid=indexid+'_Chart';
	var spanid=indexid+'_span';
	var circleName=BLtitlenm[keypos]+'(月)';	
	var uom=BtitleUOM[keypos];
	setcircle(eleid,spanid,circleName,cicleSelfVal,uom);
}


//var BLtitledata=["a00003","a00004","a00067","a00066","a00068","a00069","a00008-1","a00008-2"];//根据数据库的指标名来 其中A00008-1 对应海航地产 A00008-2对应酷铺
//var BLtitlenm=["人均收入","人均利润","资产负债率","资产周转率","资本利润率","净资产收益率","海航地产-市场占有率","酷铺-市场占有率"];//根据数据库的指标名来 其中A00008-1 对应海航地产 A00008-2对应酷铺
//var BtitleUOM=["万","万","%","%","%","%","%","%"];

var BLtitledata=["a00003","a00004","a00005"];//根据数据库的指标名来 其中A00008-1 对应海航地产 A00008-2对应酷铺
var BLtitlenm=["劳动生产率","财务四率","市场占有率"];//根据数据库的指标名来 其中A00008-1 对应海航地产 A00008-2对应酷铺
var BtitleUOM=["万","万"];

function createBLChartEle(deptid){
//	try{	
//		var path=basePath+"";
//	var ChartEle=""; 
//	for(var i =0;i<BLtitledata.length; i++){
//	       var chartid=BLtitledata[i];
//	       var charttitle=BLtitlenm[i];
//	       var ChartTbEle='';	    	 
//	       ChartTbEle='<div class="col-sm-6"><div class="white-bg"><span class="title">'+charttitle+
 //   	   "<span class='click new bg-blue' onclick=\"mload('"+path+"/task/instruction/_add')\">+&nbsp;指令下达</span></span>";
 //   	   ChartTbEle+='<div class="chartbox"><div id="'+chartid+'_Chart" style="width: 100%; height: 300px;"></div></div>';
 //   	   ChartTbEle+='</div></div>';
 //   	   ChartEle+=ChartTbEle;
//	       
//	 }
//	$('#Chart_depit').html(ChartEle)
//	}catch(e){alert(e)}
	
	try {
		var path=basePath+"";
		var ChartEle=""; 
		if (orgLevel != "0"){
			BLtitledata=["a00003","a00004"];
			BLtitlenm=["劳动生产率","财务四率"];
		}
		for(var i =0;i<BLtitlenm.length; i++){
			var chartid=BLtitledata[i];
			var charttitle=BLtitlenm[i];
			var ChartTbEle='';	 
			ChartTbEle='<div class="col-sm-12"><div class="white-bg clearfix"><span  class="title"><span id="'+chartid+'_Charttitle">'+charttitle+'</span>';
			// 指令按钮
			if($.inArray("bs_sp12", ButtonList)!=-1){
				ChartTbEle+="<span class='click new bg-blue' onclick=\"mload('"+path+"/task/instruction/_add?type="+deptid+"&mapId="+chartid+"&taskName="+(charttitle).replace(/\%/g,'%25')+"')\">+&nbsp;指令下达</span>";
			}
			ChartTbEle+='</span><div class="l_chartbox"><div id="'+chartid+'_Chart" class="canvas_wraper"></div><div class="rightn" id="right'+i+'">';
//		    ChartTbEle+='<div class="one_item"><p>114.7<span>亿</span></p><p>现金流入</p><div class="progress">';
//		    ChartTbEle+='<div class="progress-bar progress-bar-self" aria-valuemin="0" aria-valuemax="100" style="width: 30%;"></div>';
//		    ChartTbEle+='</div></div>';
			ChartTbEle+='</div></div></div></div>';
//		    ChartTbEle='<div class="col-sm-6"><div class="white-bg"><span class="title">'+charttitle+
//		    "<span class='click new bg-blue' onclick=\"mload('"+path+"/task/instruction/_add')\">+&nbsp;指令下达</span></span>";
//		    ChartTbEle+='<div class="chartbox"><div id="'+chartid+'_Chart" style="width: 100%; height: 300px;"></div></div>';
//		    ChartTbEle+='</div></div>';
			ChartEle+=ChartTbEle;
		}
		$('#Chart_depit').html(ChartEle);
	} catch(e) {
		alert(e);
	}
}
/**
 * 获取二级页面六率图形
 * @param keypos 在六率集合中的位置  对应BLtitledata，BLtitlenm，BtitleUOM
 * @param kedata 当前数据信息
 * @param index  是否自定义指标名不使用对应的BLtitledata中抽取的指标名
 */
function getBLChart(keypos,kedata,index){	
	var indexid=BLtitledata[keypos];
	//alert("kedata="+JSON.stringify(kedata)+"  indexid="+indexid)
	var cicleSelfVal=0;
	if(index){
		cicleSelfVal=kedata[index];
	}else{
		cicleSelfVal=kedata[indexid];
	}
	var eleid=indexid+'_Chart';
	var spanid=indexid+'_span';
	var circleName=BLtitlenm[keypos]+'(月)';	
	var uom=BtitleUOM[keypos];
	setcircle(eleid,spanid,circleName,cicleSelfVal,uom);
}

//=========风控模块=========
/**
 * 获取风控图表数据
 */
function loadFKTBdata(deptid){
	var titledata=TBData[0];
	var valdata=TBData[1];
	var dept=7;
	if(deptid) dept=deptid;
	createDeptChartEle(titledata,dept);
	for(var i in valdata){
		var rowdata=valdata[i];	
		var sorttitledata=new Array();
		sorttitledata=getIndexByKey(titledata,i)
		var time_type=sorttitledata[0].showtab_time_type ;//同一个表的时间类型都一样 取第一个数据就行
		try{
		if(i=="SHOWTAB_M33"||i=="SHOWTAB_M36"){
			var xAxisdata=getxAxisFK(time_type,rowdata);
		}else{
			var xAxisdata=getxAxis(time_type,rowdata);
		}
		var seriesdata=getSeriesData(sorttitledata,rowdata);
		var lengenddata=getlengenddata(sorttitledata);
		var eleid=i+"_Chart";
		if(i=="SHOWTAB_M72"){
		   //准备搞个别的图
		   createlineChart(eleid,lengenddata,xAxisdata,seriesdata,sorttitledata);
		}else{
		  createlineChart(eleid,lengenddata,xAxisdata,seriesdata,sorttitledata);
		}
		}catch(e){alert("获取数据为空")}

	}	
}

//===========经营指标============
function loadJYTBdata(){
	createJYChartEle();
	var rowdata=TBData[0];
	try{
	for(var i in rowdata){
		//alert(i+"==="+JSON.stringify(rowdata[i]))
		var kedata=rowdata[i];
	     if(i=="SHOWTAB_M71"){
			var mdata=kedata[0]; //只会有一条数据
			var key=[0,1,2,3];
			for(var k=0;k<key.length;k++){
				var keypos=key[k]
				getJYChart(keypos,mdata)
			}
			
		}else if(i=="SHOWTAB_M61"){
			var mdata=kedata[0]; //只会有一条数据
			var key=[4,5];
			for(var k=0;k<key.length;k++){
				var keypos=key[k]
				getJYChart(keypos,mdata)
			}
		}
	}
	}catch(e){alert("获取数据为空")}
}

var JYtitledata=["a00001","a00005","a00074","a00075","a00082","a00083"];//根据数据库的指标名来 其中A00008-1 对应海航地产 A00008-2对应酷铺
var JYtitlenm=["收入当期值","利润当期值","收入累计值","利润累计值","现金流入","现金流出"];//根据数据库的指标名来 其中A00008-1 对应海航地产 A00008-2对应酷铺
var JYtitleUOM=["亿元","亿元","亿元","亿元","亿元","亿元"];

function createJYChartEle(){
	try {
		var path=basePath+"";
		var ChartEle=""; 
		for(var i =0;i<JYtitledata.length; i++){
			var chartid=JYtitledata[i];
			var charttitle=JYtitlenm[i];
			var ChartTbEle='';
			ChartTbEle='<div class="col-sm-6"><div class="white-bg"><span  class="title"><span id="'+chartid+'_Charttitle">'+charttitle+'</span>';
			// 指令按钮
			if($.inArray("bs_sp12", ButtonList)!=-1){
				ChartTbEle+="<span class='click new bg-blue' onclick=\"mload('"+path+"/task/instruction/_add&taskName="+(charttitle).replace(/\%/g,'%25')+"')\">+&nbsp;指令下达</span>";
			}
			ChartTbEle+='</span><div class="chartbox"><div id="'+chartid+'_Chart" style="width: 100%; height: 300px;"></div></div>';
			ChartTbEle+='</div></div>';
			ChartEle+=ChartTbEle;
		}
		$('#Chart_depit').html(ChartEle);
	} catch(e) {
		alert(e);
	}
}

/**
 * 获取二级页面经验指标图形
 * @param keypos 在经营指标集合中的位置  对应BLtitledata，BLtitlenm，BtitleUOM
 * @param kedata 当前数据信息
 * @param index  是否自定义指标名不使用对应的BLtitledata中抽取的指标名
 */
function getJYChart(keypos,kedata,index){	
	var indexid=JYtitledata[keypos];
	//alert("kedata="+JSON.stringify(kedata)+"  indexid="+indexid)
	var cicleSelfVal=0;
	if(index){
		cicleSelfVal=kedata[index];
	}else{
		cicleSelfVal=kedata[indexid];
	}
	var eleid=indexid+'_Chart';
	var spanid=indexid+'_span';
	var circleName=JYtitlenm[keypos]+'(月)';	
	var uom=JYtitleUOM[keypos];
	setcircle(eleid,spanid,circleName,cicleSelfVal,uom);
}



//============社会责任模块=============
function loadSZTBdata(deptid){
	var titledata=TBData[0];
	var valdata=TBData[1];
	var dept=5;
	if(deptid) dept=deptid;
	createDeptChartEle(titledata,dept);
	for(var i in valdata){	
		if(i=="SHOWTAB_T22"){ //品牌信息
			try{
			var eleid=i+"_Chart";
			var originalData=new Array();
			var rowdata=valdata[i];
			for(var k in rowdata){
				var rmap={};
				rmap["value"]=rowdata[k].value;
				rmap["name"]=rowdata[k].a00018;
				originalData.push(rmap);
			}			
			createpieChart(eleid,originalData,"海航实业品牌数量占比")
			}catch(e){alert(e)}
		}else{
			var rowdata=valdata[i];	
			var sorttitledata=new Array();
			sorttitledata=getIndexByKey(titledata,i)
			var time_type=sorttitledata[0].showtab_time_type ;//同一个表的时间类型都一样 取第一个数据就行
			if(i=="SHOWTAB_D24"){
				var xAxisdata=getxAxisD24(time_type,rowdata);
			}else{
				var xAxisdata=getxAxis(time_type,rowdata);
			}
			var seriesdata=getSeriesData(sorttitledata,rowdata);
			var lengenddata=getlengenddata(sorttitledata);
			var eleid=i+"_Chart";
			createlineChart(eleid,lengenddata,xAxisdata,seriesdata,sorttitledata);
		}	
   }
}

//=====采购模块=============
function loadCGTBdata(deptid){
	var titledata=TBData[0];
	var valdata=TBData[1];
	var dept=3;
	if(deptid) dept=deptid;
	createDeptChartEle(titledata,dept);
	for(var i in valdata){
		var rowdata=valdata[i];	
		var sorttitledata=new Array();
		sorttitledata=getIndexByKey(titledata,i)
		var time_type=sorttitledata[0].showtab_time_type ;//同一个表的时间类型都一样 取第一个数据就行
		try{
		var xAxisdata=getxAxis(time_type,rowdata);
		var seriesdata=getSeriesData(sorttitledata,rowdata);
		var lengenddata=getlengenddata(sorttitledata);
		var eleid=i+"_Chart";
		createlineChart(eleid,lengenddata,xAxisdata,seriesdata,sorttitledata);
		}catch(e){alert("获取数据为空")}

	}	
}

//=========安全模块===========
function loadAQTBdata(deptid){
	var dept=6;
	if(deptid) dept=deptid;
	// 安全结果
	var orglist=TBData[0].orglist; //组织机构集合
	var orgdata=TBData[0].orgdata; //组织机构数据集合
	var AQOrgData=sortAQTBdataByOrg(orglist,orgdata);
	createAQele(AQOrgData,orgdata,orglist,dept);
	// cf,安全过程
	var orglist1 = TBData[0].orglist1;
	var orgdata1 = TBData[0].orgdata1;
	var AQOrgData1 = sortAQTBdataByOrg(orglist1,orgdata1);
	createAQele1(AQOrgData1,orgdata1,orglist1,dept);
}

/**
 * 根据组织机构代码将企业数据分拣
 */
function sortAQTBdataByOrg(orglist,orgdata){
	var AQOrgData={};
	for(var i =0;i<orglist.length; i++){
		var orgid=orglist[i].org2id;
		var orgnm=orglist[i].org2nm;
		if (orgLevel == "1") {
			orgid=orgdata[i].org2id;
			orgnm=orgdata[i].org2nm;
		}
		var orgsortdata=new Array();
		for(var j=0;j<orgdata.length; j++){
			var roworg=orgdata[j].org2id;
//			if (orgLevel == "1") {
//				roworg=orgdata[j].org3id;
//			}
			if(roworg==orgid){
				orgsortdata.push(orgdata[j]);
			}
		}
		AQOrgData[orgnm]=orgsortdata;
	}
	//alert("1003=="+JSON.stringify(AQOrgData))
	return AQOrgData;
}

function createAQele(AQOrgData,orgdata,orglist,deptid){
	var ChartEle="";
	$('#Chart_depit').html(ChartEle);
	var chartid="SHOWTAB_M28";//安全结果
	var ChartTbEle='<div class="col-sm-12"><div class="white-bg"><span  class="title"><span id="AQJG">'+'安全生产结果'+'</span>';
	// 指令按钮
	if($.inArray("bs_sp12", ButtonList)!=-1){
		ChartTbEle+="<span class='click new bg-blue' onclick=\"mload('"+path+"/task/instruction/_add?type="+deptid+"&mapId="+chartid+"&taskName="+('安全生产结果').replace(/\%/g,'%25')+"')\">+&nbsp;指令下达</span>";
	}
	if(!$.isEmptyObject(AQOrgData)){
		var path=basePath+"";
		var orgname=orglist[0].org2nm;
		if (orgLevel == "1") {
			orgname=orgdata[0].org2nm;
		}
		var title=AQOrgData[orgname];
		var ChartEle="";
		var AQeletitle=getAQeletitle(title,2);
		var AQOrgele=getOrg(AQOrgData);
		ChartTbEle+='</span><div align="center" style="margin-top:20px"><table border="1" cellspacing="0" cellpadding="0" id="AQJGTB"  style="width:95%" class="table  table-striped table-bordered table-hover">'+AQeletitle+AQOrgele+'</table></div>';
		ChartTbEle+='<div align="right" style="margin-right:40px"> 安全&nbsp;:&nbsp;<img src="'+basePath+'/views/home/homeTB/img/green.gif" /></div>';
		ChartTbEle+='<div align="right" style="margin-right:40px"> 事故&nbsp;:&nbsp;<img src="'+basePath+'/views/home/homeTB/img/red.gif" /></div>';
	}
	ChartTbEle+='</div></div>';
	ChartEle+=ChartTbEle;
	//alert(JSON.stringify(title))
	$('#Chart_depit').html(ChartEle)
}

/** 
 * @param AQOrgData1
 * @param orglist1
 * @param deptid
 * @author cf
 * 安全过程
 */
function createAQele1(AQOrgData1,orgdata1,orglist1,deptid){
	var ChartEle="";
	$('#Chart_depit1').html(ChartEle);
	var chartid="SHOWTAB_S29";//安全过程
	var ChartTbEle='<div class="col-sm-12"><div class="white-bg"><span  class="title"><span id="AQJG">'+'安全过程考核'+'</span>';
	if(!$.isEmptyObject(AQOrgData1)){
		var path=basePath+"";
		var orgname=orglist1[0].org2nm;
		if (orgLevel == "1") {
			orgname=orgdata1[0].org2nm;
		}
		var title=AQOrgData1[orgname];
		var AQeletitle=getAQeletitle(title,3);
		var AQOrgele=getOrg1(AQOrgData1);
		// 指令按钮
		if($.inArray("bs_sp12", ButtonList)!=-1){
			ChartTbEle+="<span class='click new bg-blue' onclick=\"mload('"+path+"/task/instruction/_add?type="+deptid+"&mapId="+chartid+"&taskName="+('安全过程考核').replace(/\%/g,'%25')+"')\">+&nbsp;指令下达</span>";
		}
		ChartTbEle+='</span><div align="center" style="margin-top:20px"><table border="1" cellspacing="0" cellpadding="0" id="AQJGTB"  style="width:95%" class="table  table-striped table-bordered table-hover">'+AQeletitle+AQOrgele+'</table></div>';
		ChartTbEle+='<div align="right" style="margin-right:40px"> 达标&nbsp;:&nbsp;<img src="'+basePath+'/views/home/homeTB/img/green.gif" /></div>';
		ChartTbEle+='<div align="right" style="margin-right:40px"> 不达标&nbsp;:&nbsp;<img src="'+basePath+'/views/home/homeTB/img/yellow.gif" /></div>';
		ChartTbEle+='<div align="right" style="margin-right:40px"> 不达标&nbsp;:&nbsp;<img src="'+basePath+'/views/home/homeTB/img/red.gif" /></div>';
	}
	ChartTbEle+='</div></div>';
	ChartEle+=ChartTbEle;
	//alert(JSON.stringify(title))
	$('#Chart_depit1').html(ChartEle);
}

/**
 * 产生标题头
 * @param title 标题数据
 * @param datatype 标题时间类型 4年 2月 3季
 */
function getAQeletitle(title,datatype){
	//alert(JSON.stringify(title))
	var datekey="date_m";
	var dateuom="月";
	if(datatype==4){
		datekey="date_y";
		dateuom="年";
	}else if(datatype==3){
		datekey="date_s";
		dateuom="季";
	}	
	var titlele="<tr><td width='10%' align='center'>组织机构名称</td>";
	for(var i=0;i<title.length; i++){		
		var dateele="<td width='7.5%' align='center'>";
		dateele+=title[i][datekey]+dateuom;
		dateele+="</td>";
		titlele+=dateele;
	}
	titlele+="<tr>";
	if (title.length == "0"){
		titlele = "";
	}
	return titlele;
}

/**
 * 获取全部单位的加载元素
 */
function getOrg(AQOrgData){
	var orgele="";
	for(var k in AQOrgData){
		var row=AQOrgData[k];
		orgele+= getOrgByone(row,k)
	}
	return orgele;
}

/**
 * cf,安全过程
 * 获取全部单位的加载元素
 */
function getOrg1(AQOrgData1){
	var orgele="";
	for(var k in AQOrgData1){
		var row=AQOrgData1[k];
		orgele+= getOrgByone1(row,k)
	}
	return orgele;
}

/**
 * 获得具体一家单位的元素
 */
function getOrgByone(orgdate,orgnm){
	var titlele="<tr><td width='10%' align='center'>"+orgnm+"</td>";
	for(var i=0;i<orgdate.length; i++){		
		var dateele="<td width='7.5%' align='center'>";
		var orgstatue=orgdate[i].a00030;
		var orgstatuele='<a href="#"><img src="'+basePath+'/views/home/homeTB/img/green.gif" onclick=\'loadNextLVExtraTbdata("1","2","SHOWTAB_M28","'+orgnm+'","'+orgdate[i].date_y+'","'+orgdate[i].date_m+'")\'/></a>'
		if(orgstatue!=0){
			orgstatuele='<a href="#"><img src="'+basePath+'/views/home/homeTB/img/red.gif" onclick=\'loadNextLVExtraTbdata("1","2","SHOWTAB_M28","'+orgnm+'","'+orgdate[i].date_y+'","'+orgdate[i].date_m+'")\'/></a>'
		} //0表示无事故 绿灯 超过0为红灯
		dateele+=orgstatuele;
		//dateele+=orgdate[i].a00030;
		dateele+="</td>";
		titlele+=dateele;
		}
	titlele+="<tr>";
	return titlele;
}

/**
 * cf,安全过程
 * 获得具体一家单位的元素
 */
function getOrgByone1(orgdate,orgnm){
	var titlele="<tr><td width='10%' align='center'>"+orgnm+"</td>";
	for(var i=0;i<orgdate.length; i++){		
		var dateele="<td width='7.5%' align='center'>";
		var orgstatue=orgdate[i].suma00093;
		var year=orgdate[i].date_y;
		//获取季度
		var orgQuarter=orgdate[i].date_s;
		dateele+="<span class='click' onclick=\"loadNextLVExtraTbdata('1','3','SHOWTAB_S29','"+orgnm+"','"+year+"','"+orgQuarter+"')\"><a href='#'>";
		var orgstatuele='<img src="'+basePath+'/views/home/homeTB/img/green.gif" />'
		if(orgstatue!=0){
			if(orgQuarter!=4){
				orgstatuele='<img src="'+basePath+'/views/home/homeTB/img/yellow.gif" />'
			}else{
				orgstatuele='<img src="'+basePath+'/views/home/homeTB/img/red.gif" />'
			}
		}//0表示无事故 绿灯 ；超过0且季度为4时，为红灯；超过0且季度不为4时，为黄灯
		dateele+=orgstatuele;
		dateele+="</a></span>";
		dateele+="</td>";
		titlele+=dateele;
		}
	titlele+="<tr>";
	return titlele;
}



//=====经营指标修改后模块===
//===========经营指标============
function loadNEWJYTBdata(deptid){
	var titledata=TBData[0];
	var valdata=TBData[1];	
	var dept=8;
	if(deptid) dept=deptid;
	try{
		createJYChartEle(titledata,dept);
		for(var i in valdata){
			var rowdata=valdata[i];	
			var sorttitledata=new Array();
			sorttitledata=getIndexByKey(titledata,i)
			var time_type=sorttitledata[0].showtab_time_type ;//同一个表的时间类型都一样 取第一个数据就行
			var xAxisdata=getxAxis(time_type,rowdata);
			var seriesdata=getSeriesData(sorttitledata,rowdata);
			var lengenddata=getlengenddata(sorttitledata);
			var eleid=i+"_Chart";
			if(i=="SHOWTAB_M71"){
			   //收入利润下钻图
			   createlineChart(eleid,lengenddata,xAxisdata,seriesdata,sorttitledata);
			}else if(i=="SHOWTAB_M61"){
				var linedata=new Array();
				linedata.push("A00084")
				createPuBuChart(eleid,lengenddata,xAxisdata,seriesdata,sorttitledata,linedata)
				var posindex=xAxisdata.length-1;
				linkJYXJIden(sorttitledata,seriesdata,posindex) //默认初始加载最后1个月
			 // createlineChart(eleid,lengenddata,xAxisdata,seriesdata,sorttitledata);
			}
		}	
	}
	catch(e){alert("获取数据为空")}
}

/**
 * 创建图形展现元素
 * @param titledata
 */
function createJYChartEle(titledata,deptid){
	var path=basePath+"";
	try {
		var sortlist=new Array();
		var ChartEle="";
		//下面是收入利润的特殊箱子图 特别加入元素
		/*var specele='<div class="col-sm-12"><div class="white-bg clearfix"><span class="title">收入增长';
		specele+="<span class='click new bg-blue' onclick=\"mload('"+path+"/task/instruction/_add?type="+deptid+"&mapId="+chartid+"&taskName="+(charttitle).replace(/\%/g,'%25')+"')\">+&nbsp;指令下达 </span></span>";
		specele+='<div class="l_chartbox"><div id="'+chartid+'_Chart" class="canvas_wraper"></div>';
		specele+='<div id="right"><div class="one_item"><p>114.7<span>亿</span></p><p>现金流入</p>';
		specele+='<div class="progress"> <div class="progress-bar progress-bar-self" aria-valuemin="0" aria-valuemax="100" style="width: 30%;"></div></div></div>';
		specele+='<div class="one_item"><p>114.7<span>亿</span></p><p>现金流入</p><div class="progress">  <div class="progress-bar progress-bar-self" aria-valuemin="0" aria-valuemax="100" style="width: 40%;"></div></div></div>';
		specele+='<div class="one_item"><p>114.7<span>亿</span></p><p>现金流入</p><div class="progress">  <div class="progress-bar progress-bar-self" aria-valuemin="0" aria-valuemax="100" style="width: 40%;"></div></div></div>'
		specele+='</div></div></div></div>'
		ChartEle+=specele;*/
		for(var i =0;i<titledata.length; i++){
			var chartid=titledata[i].storetab_name;
			var charttitle=titledata[i].showtab_nm;
			var ChartTbEle='';
			if($.inArray(chartid, sortlist)==-1){
				sortlist.push(chartid);
				if(chartid=="SHOWTAB_M71"){ //收入利润
					var SHOWTAB_M71Ele='<div class="col-sm-12"><div class="white-bg"><span  class="title"><span id="'+chartid+'_Charttitle">'+charttitle+'</span>';
					// 指令按钮
					if($.inArray("bs_sp12", ButtonList)!=-1){
						SHOWTAB_M71Ele+="<span class='click new bg-blue' onclick=\"mload('"+path+"/task/instruction/_add?type="+deptid+"&mapId="+chartid+"&taskName="+(charttitle).replace(/\%/g,'%25')+"')\">+&nbsp;指令下达 </span>";
					}
					SHOWTAB_M71Ele+='</span><div class="chartbox"><div id="'+chartid+'_Chart" style="width: 100%; height: 300px;"></div></div>';
					SHOWTAB_M71Ele+='</div></div>';
					ChartEle+=SHOWTAB_M71Ele;
				}else if((chartid=="SHOWTAB_M61")){ //现金流
					ChartTbEle='<div class="col-sm-12"><div class="white-bg"><span class="title"><span id="'+chartid+'_Charttitle">'+charttitle+'</span>';
					// 指令按钮
					if($.inArray("bs_sp12", ButtonList)!=-1){
						ChartTbEle+="<span class='click new bg-blue' onclick=\"mload('"+path+"/task/instruction/_add?type="+deptid+"&mapId="+chartid+"&taskName="+(charttitle).replace(/\%/g,'%25')+"')\">+&nbsp;指令下达 </span>";
					}
					ChartTbEle+='</span><div class="l_chartbox"><div id="'+chartid+'_Chart" class="canvas_wraper"></div>';
					ChartTbEle+='<div id="right"><div class="one_item"><p id="IDEN_A00082">114.7<span>亿</span></p><p>现金流入</p>';
					ChartTbEle+='<div class="progress"> <div id="PRO_A00082"  class="progress-bar progress-bar-self" aria-valuemin="0" aria-valuemax="100" style="width: 30%;"></div></div></div>';
					ChartTbEle+='<div class="one_item"><p id="IDEN_A00083">114.7<span>亿</span></p><p>现金流出</p><div class="progress">  <div id="PRO_A00083" class="progress-bar progress-bar-self" aria-valuemin="0" aria-valuemax="100" style="width: 40%;"></div></div></div>';
					ChartTbEle+='<div class="one_item"><p id="IDEN_A00084">114.7<span>亿</span></p><p>现金净流入</p><div class="progress">  <div id="PRO_A00084" class="progress-bar progress-bar-self" aria-valuemin="0" aria-valuemax="100" style="width: 40%;"></div></div></div>'
					ChartTbEle+='</div></div></div></div>'
					ChartEle+=ChartTbEle;
				}
			}
		}
		//alert(ChartEle)
		$('#Chart_depit').html(ChartEle);
	} catch(e) {
		alert(e);
	}
}

/**
 * 联动经营-现金流
 */
function linkJYXJIden(sorttitledata,seriesdata,posindex){
	var siden=new Array();
	for(var i =0;i<sorttitledata.length; i++){
		var iden=sorttitledata[i].int_storetab_field;
		var idenval=seriesdata[i][posindex];
		var idenuom=sorttitledata[i].uom;
		if(idenval == undefined){
			idenval = "";
		}
		var idenele=idenval+"<span>"+idenuom+"</span>"
		var ideneleid="IDEN_"+iden;
		$('#'+ideneleid).html(idenele);	
		siden.push(Math.abs(idenval));
	}
	var maxiden=siden.max();
	for(var i =0;i<sorttitledata.length; i++){
		var iden=sorttitledata[i].int_storetab_field;
		var idenval=seriesdata[i][posindex]
		var idenuom=sorttitledata[i].uom;
		var ideneleid="PRO_"+iden;
		var cen=0;
		if(Math.abs(maxiden)!=0){
			cen=Math.abs(idenval)/Math.abs(maxiden);
		} 
		var prograssval=parseInt(cen*80)+"%"
		document.getElementById(ideneleid).style.width=prograssval;
	}
	/*dealProgess(siden,sorttitledata);
	var maxindex=dealProgess(siden,sorttitledata);
	
	var maxpos=maxindex-1;
	var maxval=seriesdata[maxpos][posindex];
*/	
	
	
	//alert(JSON.stringify(sorttitledata))
	//alert(JSON.stringify(seriesdata))
}

Array.prototype.max = function(){ 
	return Math.max.apply({},this) 
} 
	Array.prototype.min = function(){ 
	return Math.min.apply({},this) 
} 
	
function dealProgess(siden,sorttitledata){	
	var idenmax=parseFloat(siden.max());
	var maxindex=getPosIndex(siden,idenmax);
	return maxindex;
}

/**
 * 获取数组对应值下标
 * @param siden
 * @param max
 * @returns {Number}
 */
function getPosIndex(siden,val){
	var index=0;
	for(var i=0,len=siden.length;i<len;i++){
	    if(siden[i]==val){
	        index=i;
	        break;
	    }
	}
	return index;
}

function createSCChart(eid,mData){
	var legendData=['酷铺','海航地产'];
	var xAxisData = new Array();
	var seriesData = new Array();
	
	var seriesData1 = new Array();
	var seriesData2 = new Array();
	
//	alert("mData:::::"+mData);
	for(var i=0; i<mData.length; i++){
//		alert("obj:-"+mData[i]);
		if(mData[i].org2id=="4010"){
			seriesData1.push(""+parseFloat(mData[i].a00008).toFixed(2));
			xAxisData.push(mData[i].date_m+"月");
		}else if(mData[i].org2id=="27534"){
			seriesData2.push(""+parseFloat(mData[i].a00008).toFixed(2));
		}
	}
	var obj1 = {
        name:'酷铺',
        type:'bar',
        data:seriesData1
    };
	var obj2 = {
        name:'海航地产',
        type:'bar',
        data:seriesData2
    };
	seriesData.push(obj1);
	seriesData.push(obj2);
	createColumnChart(eid,legendData,xAxisData,seriesData,"%","",true);
}

function setLevelChartAndOpenWinLaoDong(titleName,seriseIndex,year,month){
	var datajson={
		year:year,
		month:month
	};
	
	$.ajax({
		url : basePath+"/home/getBLDataByLevel",
		type : "POST",
		data : datajson,
		success : function(data) {
		    //alert(JSON.stringify("128==="+JSON.stringify(data)))
			if(data==0){
				alert("无对应下层数据");
			}else{
				$('#level1_title').text(titleName);
				var xAxisId = new Array();
				var xAxisData = new Array();
				var seriesData = new Array();
				var seriesData1 = new Array();
				var seriesData2 = new Array();
				for(var i=0; i<data.length; i++){
					var xAxisObj = data[i].org2id;
					if(xAxisId.length==0 || xAxisId.toString().indexOf(xAxisObj) == -1){
						xAxisId.push(xAxisObj);
						xAxisData.push(data[i].org2nm);
					}
					seriesData1.push(""+parseFloat(data[i].a00003));
					seriesData2.push(""+parseFloat(data[i].a00004));
				}
				var obj1 = {
			        name:'人均收入(万元)',
			        type:'bar',
			        data:seriesData1
			    };
				var obj2 = {
			        name:'人均利润(万元)',
			        type:'bar',
			        data:seriesData2
			    };
				seriesData.push(obj1);
				seriesData.push(obj2);
				
				var legendData=['人均收入(万元)','人均利润(万元)'];
				createColumnChart("level1_chart",legendData,xAxisData,seriesData,"","万元");
				openlevel1window();
				level1chartOBj.resize();
				
			}
		},
		error : function() {
			alert("获取数据失败");
		}
	});
}

function setLevelChartAndOpenWin4V(titleName,seriseIndex,year,month){
	var datajson={
		year:year,
		month:month
	};
	
	$.ajax({
		url : basePath+"/home/get4VDataByLevel",
		type : "POST",
		data : datajson,
		success : function(data) {
		    //alert(JSON.stringify("128==="+JSON.stringify(data)))
			if(data==0){
				alert("无对应下层数据");
			}else{
				$('#level1_title').text(titleName);
				var xAxisId = new Array();
				var xAxisData = new Array();
				var seriesData = new Array();
				var seriesData1 = new Array();
				var seriesData2 = new Array();
				var seriesData3 = new Array();
				var seriesData4 = new Array();
				for(var i=0; i<data.length; i++){
					var xAxisObj = data[i].org2id;
					if(xAxisId.length==0 || xAxisId.toString().indexOf(xAxisObj) == -1){
						xAxisId.push(xAxisObj);
						xAxisData.push(data[i].org2nm);
					}
					seriesData1.push(""+parseFloat(data[i].a00066).toFixed(2));
					seriesData2.push(""+parseFloat(data[i].a00067).toFixed(2));
					seriesData3.push(""+parseFloat(data[i].a00068).toFixed(2));
					seriesData4.push(""+parseFloat(data[i].a00069).toFixed(2));
				}
				var obj1 = {
			        name:'资产周转率(%)',
			        type:'bar',
			        data:seriesData1
			    };
				var obj2 = {
			        name:'资产负债率(%)',
			        type:'bar',
			        data:seriesData2
			    };
				var obj3 = {
			        name:'资本利润率(%)',
			        type:'bar',
			        data:seriesData3
			    };
				var obj4 = {
			        name:'净资产收益率(%)',
			        type:'bar',
			        data:seriesData4
			    };
				seriesData.push(obj1);
				seriesData.push(obj2);
				seriesData.push(obj3);
				seriesData.push(obj4);
				
				var legendData=['资产周转率(%)','资产负债率(%)','资本利润率(%)','净资产收益率(%)'];
				createColumnChart("level1_chart",legendData,xAxisData,seriesData,"%");
				openlevel1window();
				level1chartOBj.resize();
				
			}
		},
		error : function() {
			alert("获取数据失败");
		}
	});
}

function changeProcessSC(legenddata,mdata,index){
	var processHtml = "";
	for(var i =0;i<legenddata.length; i++){
		var data=mdata[i].data[index];
		processHtml+='<div class="one_item"><p>'+data+'<span>%</span></p><p>'+legenddata[i]+'</p><div class="progress">';
		processHtml+='<div class="progress-bar progress-bar-self" aria-valuemin="0" aria-valuemax="100" style="width: '+data+'%;"></div>';
		processHtml+='</div></div>';
	}
	$('#right2').html(processHtml);
}

function changeProcessLaoDong(legenddata,mdata,index){
	var processHtml = "";
	for(var i =0;i<legenddata.length; i++){
		var data=mdata[i].data[index];
		processHtml+='<div class="one_item"><p>'+data+'<span></span></p><p>'+legenddata[i]+'</p><div class="progress">';
		processHtml+='<div class="progress-bar progress-bar-self" aria-valuemin="0" aria-valuemax="100" style="width: '+data+'%;"></div>';
		processHtml+='</div></div>';
	}
	$('#right0').html(processHtml);
}

function changeProcess4V(legenddata,mdata,index){
	var processHtml = "";
	for(var i =0;i<legenddata.length; i++){
		var data=mdata[i].data[index];
		processHtml+='<div class="one_item"><p>'+data+'<span>%</span></p><p>'+legenddata[i]+'</p><div class="progress">';
		processHtml+='<div class="progress-bar progress-bar-self" aria-valuemin="0" aria-valuemax="100" style="width: '+data+'%;"></div>';
		processHtml+='</div></div>';
	}
	$('#right1').html(processHtml);
}

/**
*功能:删除字符串中的空格和换行符
*/
function removeBlankEnter(tmpText){
	//删除字符串中的空格和换行符
	var tmpText = tmpText;
	for(var i=tmpText.length-1; i>=0; i--){
		tmpText = tmpText.replace("\n","");
	}
	var str = "";
	for(var i=tmpText.length-1; i>=0; i--){
		if(tmpText.charAt(i)!=" "&&tmpText.charAt(i)!="\r")str=tmpText.charAt(i)+str;
	}
	return str;
}
