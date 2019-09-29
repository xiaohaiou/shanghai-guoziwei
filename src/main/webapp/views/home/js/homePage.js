var WholeYear; //全局年份变量 默认是今年  预留给客户进行年份选择 测试用2017年
var WholeMonth;//全局月份变量 默认是这个月 预留给客户进行月份选择  测试用5月
var ButtonList;

//========首页数据加载模块=========================

/**
 * 准备根据年份加载首页数据  年份为空则加载当年的数据
 */
function initData(year,month){	  
	    var nowDate= new Date();
	    if(!WholeYear) WholeYear=nowDate.getFullYear(); 
	    if(!WholeMonth) WholeMonth=nowDate.getMonth(); //默认前一个月
	    if(!year) year=WholeYear;
	    if(!month) month=WholeMonth;
		$.ajax({
			url : basePath+"/home/getIndexData",
			type : "POST",
			data : {years:year,
				    month:month
			},
			success : function(data) {
				 try{
					// alert(JSON.stringify(data))
					 var singledata=data[0].SingleData;
					 if(!singledata){
						 alert("超时,请重新登陆");
						 return;
					 }
					 var tbdata=data[0].TBData;
					 var bldata=data[0].BLMAP;
					 ButtonList = data[0].ButtonList;
					 sortDataByDept(singledata);
					 loadBLDataToChart();
					 loadJYDataToChart();
					 if($.inArray("&bs_ip11&", ButtonList)!=-1){
						 loadFXData();
					 }
					 if($.inArray("&bs_ip9&", ButtonList)!=-1){
						 loadCGdata();
					 }
					 if($.inArray("&bs_ip6&", ButtonList)!=-1){
						 loadCWData();
					 }
					 if($.inArray("&bs_ip4&", ButtonList)!=-1){
						 loadXZdata();
					 }
					 if($.inArray("&bs_ip5&", ButtonList)!=-1){
						 loadRLdata();
					 }
					 loadTBdata(tbdata);
					 if($.inArray("&bs_ip8&", ButtonList)!=-1){
						 loadSZdata();
					 }
					 //DealBLTBdata(bldata,"BL1");
					 }catch(e){alert("异常=="+e)};
			},
			error : function() {
				alert("获取数据失败");
			}
		});
}


/**
 * 以下问存放单体指标的分组数据
 */
var balvlist=new Array(); //8率数据
var jingyinglist=new Array(); //经营指标
var xinzhenlist=new Array(); //行政指标
var renlilist=new Array(); //人力指标
var shezelist=new Array(); //社责体系
var anquanlist=new Array(); //安全体系
var fenxianlist=new Array();//风险管控
var caigoulist=new Array(); //采购来源
var qygmlist=new Array(); //企业规模
var caiwulist=new Array(); //财务指标

/**dept_Id 部门分类情况
 * 1 六率指标      balvlist
 * 2 行政办公体系  xinzhenlist
 * 3 采购体系      caigoulist
 * 4 人力资源体系  renlilist
 * 5 社责体系     shezelist
 * 6 安全体系     anquanlist
 * 7 风控体系     fenxianlist
 * 8 经营指标    jingyinglist
 * 9 企业规模     qygmlist
 * 10 财务体系    caiwulist
 */


/**
 * 将首页加载的单体数据进行分组分别处理
 * @param singledata 单体指标数据集合
 */
function sortDataByDept(singledata){
	for (var i =0;i<singledata.length; i++) {
		var rowdata=singledata[i];
		var dept_id=rowdata.dept_id;
		if(dept_id==1) balvlist.push(rowdata);
		if(dept_id==2) xinzhenlist.push(rowdata);
		if(dept_id==3) caigoulist.push(rowdata);
		if(dept_id==4) renlilist.push(rowdata);
		if(dept_id==5) shezelist.push(rowdata);
		if(dept_id==6) anquanlist.push(rowdata);
		if(dept_id==7) fenxianlist.push(rowdata);
		if(dept_id==8) jingyinglist.push(rowdata);
		if(dept_id==9) qygmlist.push(rowdata);	
		if(dept_id==10) caiwulist.push(rowdata);	
	}	
	
}



/**
 *  将八率数据加载到首页图形 分层
 */
var specialindex=['A00003','A00004','A00008'];
function loadBLDataToChart(){
	var balvist=sortBLDataToChart();
	var norlist=balvist[0];
	var spclist=balvist[1];
	dealnorBL(norlist);
	delSpecBL(spclist);
	
	//setSpecialChart2("SPECCircle_1")
	//alert(JSON.stringify(spclist))
}

/**
 * 处理普通八率指标
 */
function dealnorBL(norlist){
	for (var i =0;i<norlist.length; i++) {
		var rowdata=norlist[i];
		var eleid="Circle_"+(i+1);
		var spanid="Span_Circle_"+(i+1);
		setcircle(eleid,spanid,rowdata);							
	}		
}

function delSpecBL(spclist){
	//alert(JSON.stringify(spclist))
	var rjsr=0;//人均收入   
	var rjlr=0;//人均利润
	var hhdc=0;//海航地产占有率
	var kp=0;//酷派占有率
	var rjsrele="";
	var rjlrele="";
	var hhdcele="";
	var kpele="";
	var ldupdatime=""; //劳动生产率更新时间
	var scupdatime=""; //市场占有率更新时间
	var lduom; //劳动生产率单位
	var ldtime_type="";//劳动生产率时间类型
	var sctime_type=""; //市场占有率时间类型
	var scuom;//市场占有率单位
	
	for (var i =0;i<spclist.length; i++) {
		var rowdata=spclist[i];
		var blname=removeBlankEnter(rowdata["index_nm"]);
		if(blname=="人均收入"){ 
			rjsr=parseFloat(rowdata["index_val"]);
			rjsrele=blname+""+rjsr;
			ldupdatime=rowdata["updatetime"];
			ldtime_type=rowdata["time_type"];
			lduom=rowdata["uom"];
		}
		if(blname=="人均利润"){
			rjlr=parseFloat(rowdata["index_val"]);
			rjlrele=blname+""+rjlr;
		}
		if(blname=="海航地产"){
			hhdc=parseFloat(rowdata["index_val"]);
			hhdcele=blname+""+hhdc;
		}
		if(blname=="酷铺"){
			kp = parseFloat(rowdata["index_val"]);
			kpele=blname+""+kp;
		}
		// 市场占有率时间、单位取得
		if(rowdata["index_filed"] == "A00008"){
			scupdatime=rowdata["updatetime"];
			sctime_type=rowdata["time_type"];
			scuom=rowdata["uom"];
		}
	}
	var laodon="SPECCircle_1"; //劳动生产率
	var zyl="SPECCircle_2";  //市场占有率
	if(rjsr>rjlr){
		setSpecialChart2("SPECCircle_1")
	}else{
		setSpecialChart1("SPECCircle_1")
	}
	
	if(hhdc>kp){
		setSpecialChart1("SPECCircle_2")
	}else{
		setSpecialChart2("SPECCircle_2")
	}
	//alert("rjsrele="+rjsrele+"  rjlrele="+rjlrele+"    hhdcele="+hhdcele+"  kpele="+kpele)
	$("#rjsr").text(rjsrele);$("#rjlr").text(rjlrele);$("#hhzyl").text(hhdcele);$("#kpzyl").text(kpele);
	if(lduom == undefined){
		lduom = "";
	} else {
		lduom = "(" + lduom + ")";
	}
	if(scuom == undefined){
		scuom = "";
	} else {
		scuom = "(" + scuom + ")";
	}
	var ldspanele="劳动生产率"+lduom+""+"<sub>"+formatDate(ldupdatime,ldtime_type)+"</sub>";
	var scspanele="市场占有率"+scuom+""+"<sub>"+formatDate(scupdatime,sctime_type)+"</sub>";
	$('#ldscl').html(ldspanele);
	$('#sczyl').html(scspanele);
}


function sortBLDataToChart(){
	var norlist=new Array(); //普通八率
	var spclist=new Array(); //特殊8率
	var balvist=new Array(); //集合
	for (var i =0;i<balvlist.length; i++) {
		var rowdata=balvlist[i];
		var index_filed=rowdata["index_filed"];
		if($.inArray(index_filed, specialindex)!=-1){
			spclist.push(rowdata);
		}else{
			norlist.push(rowdata);
		}				
	}	
	balvist.push(norlist);
	balvist.push(spclist);
	return balvist;
}



/**
 * 将经营指标加载到首页图形
 */
function loadJYDataToChart(){
	if(jingyinglist.length==0) return;		
	var eleid="Dou_Circle_1";   //图形位置
	var spanidprofitid="jinyin_profit"; //显示利润的位置元素id
	var spanidincomeid="jinyin_income"; //显示收入的位置元素id
	setdoubleCicle(eleid,spanidprofitid,spanidincomeid,jingyinglist);
}

/**
 * 加载风险指标到首页
 */
function loadFXData(){
	if(fenxianlist.length==0) return;
    var sjwtzg='-';//审计问题整改率  
    var sjxms=0; //审计项目数
    var fxwts=0; //发现问题树
    var lvhts=0; //履行合同总数
    var whtwys=0; // 我方合同违约数
    var dhtwys=0; // 对方合同违约数
    var htwys=0; //合同违约数
    var FXupdatetime="";
	var FXtime_type=2;
	for (var i =0;i<fenxianlist.length; i++) {
		var row=fenxianlist[i];
		var index_filed=row["index_filed"];
		if(index_filed=="A00038"){
			sjwtzg=parseFloat(row["index_val"]).toFixed(2); //审计问题整改率  如果数据库字段修改这边就必须修改对应的字段
			var FXupdatetime=row["updatetime"];
			var FXtime_type=row["time_type"];
		}
		if(index_filed=="A00031") sjxms=parseFloat(row["index_val"]); //审计项目总数
		if(index_filed=="A00036") fxwts=parseFloat(row["index_val"]); //发现问题树
		if(index_filed=="A00039") lvhts=parseFloat(row["index_val"]); //履行合同总数
		if(index_filed=="A00121") whtwys=parseFloat(row["index_val"]); //我方合同违约数
		if(index_filed=="A00122") dhtwys=parseFloat(row["index_val"]); //对方合同违约数
	}
	htwys = whtwys + dhtwys;

	var sjwtzgele='<h1>'+sjwtzg+'<sub>%</sub><sup class="title">审计问题整改率</sup></h1>';
	//var sjwtzgele='<h1>'+sjwtzg+'<sub>%</sub><span class="triangle"></span></h1><span class="title">审计问题整改率</span>';
	$('#sjwtzg').html(sjwtzgele);
	
	var sjxmsele='<div class="title">审计项目数</div><div class="number-bigsize">'+sjxms+'<sub>个</sub></div>';
	$('#sjxms').html(sjxmsele);
	
	var fxwtsele='<div class="title">发现问题数</div><div class="number-bigsize">'+fxwts+'<sub>个</sub></div>';
	$('#fxwts').html(fxwtsele);
	
	var lvhtsele='<div class="title">履行合同总数</div><div class="number-bigsize">'+lvhts+'<sub>个</sub></div>';
	$('#lvhts').html(lvhtsele);
	var htwylele='<div class="col-sm-7"><div class="title">合同违约数</div><div class="number-bigsize">'+htwys+'<sub>个</sub></div></div>';
	htwylele+='<div class="col-sm-5 pieChart"><div id="htwyl_pie" style="width: 100%; height: 100px;"></div></div>';
	$('#htwyl').html(htwylele);

	var FXchartid="htwyl_pie";
	setHTChart(FXchartid,htwys,lvhts);
	
	var FXformattime=formatDate(FXupdatetime,FXtime_type);
	var FXele=FXformattime;
	if (!FXupdatetime){
		FXele = '';
	}
	$('#DeptTime_7').text(FXele)
	
}

/**
 * 加载采购数据
 */
function loadCGdata(){
	//alert(JSON.stringify(caigoulist))
	if(caigoulist.length==0) return;
	var CGupdatetime="";
	var CGtime_type=2;
	var caigouele=$('#caigou');
	for (var i =0;i<caigoulist.length; i++) {
		var row=caigoulist[i];
		var index_filed=row["index_filed"];
		if(index_filed=="A00041") { //A00041累计采购总金额			
			var ele='<h1>'+parseFloat(row["index_val"])+'<sub>'+row["uom"]+'</sub><sup>累计完成采购金额</sup</h1>';
			$('#ljcg').html(ele);
			var CGupdatetime=row["updatetime"];
			var CGtime_type=row["time_type"];
		}else if(index_filed=="A00043") { //A00043 成本节约率	
		     var cbval=parseFloat(row["index_val"]);
		     var cbstatue=row["statue"];
			 cbval=cbval.toFixed(2);
			var eleid="cb_pie";
			var cbstatueele='<i class="up"></i>';
			if(cbstatue&&cbstatue==1)  cbstatueele='<i class="down"></i>';
			var colourlist= ['#cc8e12','rgba(255,255,255,0.2)']
			var spanele=cbval+"<sub>%</sub>"+cbstatueele;
			$('#cb_span').html(spanele);
			setHTChart2(eleid,cbval,'成本节约率',colourlist)
		}else if(index_filed=="A00142") { //A00142 采购金额环比	
			var jihua=parseFloat(row["index_val"]);
			var jihuastatue=row["statue"];
			jihua=jihua.toFixed(2);
			var eleid="jh_pie";
			var  jihuastatueele='<i class="up"></i>';
			if(jihuastatue&&jihuastatue==1)  jihuastatueele='<i class="down"></i>';
			var spanele=jihua+'<sub>%</sub>'+jihuastatueele;
			$('#jh_span').html(spanele);
			var colourlist= ['#43a1be ','rgba(255,255,255,0.2)']
			setHTChart2(eleid,jihua,'采购金额环比',colourlist)
		}
	}
	//--修改截止时间
	var CGformattime=formatDate(CGupdatetime,CGtime_type);
	var CGele=CGformattime;
	if (!CGele){
		CGele = '';
	}
	$('#DeptTime_3').text(CGele)
	
}

/**
 * 加载行政数据
 */
function loadXZdata(){
	if(xinzhenlist.length==0) return;	
	var XZupdatetime="";
	var XZtime_type=2;
	for(var i =0;i<xinzhenlist.length; i++){
		var row=xinzhenlist[i];
		var index_filed=row["index_filed"];
		if(index_filed=="A00015"){//A00015 行政督办办结率
			//var xzbjlele='<h1>'+parseFloat(row["index_val"])+'<sub>'+row["uom"]+'</sub><span class="triangle"></span></h1><span class="title">'+row["index_nm"]+'</span>';
			var xzbjlele='<h1>'+parseFloat(row["index_val"])+'<sub>'+row["uom"]+'</sub><sup class="title">'+row["index_nm"]+'</sup></h1>';
		   $('#xzbjl').html(xzbjlele);
		   XZupdatetime=row["updatetime"];
		   XZtime_type=row["time_type"];
		}
		break;	
	}
	var XZformattime=formatDate(XZupdatetime,XZtime_type);
	var XZele=XZformattime;
	if (!XZele){
		XZele = '';
	}
	$('#DeptTime_2').text(XZele)
}


/**
 * 加载社会责任
 */
function loadSZdata(){
	var SZupdatetime="";
	var SZtime_type=2;
	for (var i =0;i<shezelist.length; i++) {
		var row=shezelist[i];
		var index_filed=row["index_filed"];
		if(index_filed=="A00110") { //A00110志愿者时长	
			var xzbjlele='<h1>'+parseFloat(row["index_val"])+'<sub>'+row["uom"]+'</sub><sup class="title">'+row["index_nm"]+'</sup></h1>';
			$('#zycsc').html(xzbjlele);
			SZupdatetime=row["updatetime"];
			SZtime_type=row["time_type"];
		}	
	}	
	var SZformattime=formatDate(SZupdatetime,SZtime_type);
	var SZele=SZformattime;
	if (!SZele){
		SZele = '';
	}
	$('#DeptTime_5').text(SZele)
}


/**
 * 加载人力资源数据
 */
function loadRLdata(){
	if(renlilist.length==0) return;
	var ldrs=0;//劳动用公人数
	var  qtrs=0;//其他人数
	for(var i =0;i<renlilist.length; i++){
		var row=renlilist[i];
		var index_filed=row["index_filed"];
		if(index_filed=="A00016"){//A00016 总人数
			//var zrslele='<h1>'+parseFloat(row["index_val"])+'<sub>'+row["uom"]+'</sub><span class="triangle"></span></h1><span class="title">'+row["index_nm"]+'</span>';
			var zrslele='<h1>'+commafy(parseFloat(row["index_val"]))+'<sub>'+row["uom"]+'</sub><sup class="title">'+row["index_nm"]+'</sup></h1>';
		    $('#zrs').html(zrslele);
		    RLupdatetime=row["updatetime"];
		    RLtime_type=row["time_type"];
		}else if(index_filed=="A00002"){ //劳动用公人数
			ldrs=row["index_val"];
		}else if(index_filed=="A00046"){ //其他人数
			qtrs=row["index_val"];
		}
			
	}
	var eleid="rs_pie";
	setcircle2(eleid,commafy(parseFloat(ldrs)),commafy(parseFloat(qtrs)),"劳动用工","其它用工",'总人数')
	var RLformattime=formatDate(RLupdatetime,RLtime_type);
	var RLele=RLformattime;
	if (!RLele){
		RLele = '';
	}
	$('#DeptTime_4').text(RLele)
}

/**
 * 加载首页的图表数据
 * @param tbdata
 */
function loadTBdata(tbdata){
	var titledata=tbdata[0];
	var valdata=tbdata[1];
	for(var i in valdata){
		if(i=="SHOWVIEW_M73"){
			var rowdata=valdata[i];
			if (rowdata != "" && rowdata != undefined && rowdata != null){
				var sorttitledata=new Array();
				sorttitledata=getIndexByKey(titledata,i)
				var time_type=sorttitledata[0].showtab_time_type ;//同一个表的时间类型都一样 取第一个数据就行
				var xAxisdata=getxAxis(time_type,rowdata);
				var seriesdata=getSeriesData(sorttitledata,rowdata);
				var lengenddata=getlengenddata(sorttitledata);		
				var eleid=i+"_CURVE";
				loadXZChart(eleid,lengenddata,xAxisdata,seriesdata);
			}
		}else if(i=="SHOWTAB_M28"){ //生产安全
			var rowdata=valdata[i];
			if (rowdata != "" && rowdata != undefined && rowdata != null){
				var sortrowdata=dealAQTBdata(rowdata);
				var AQday=getContinueAQD(sortrowdata);
				var AQdayele='<h1>'+AQday+'<sub>天</sub><sup>连续安全工作日</sup></h1>'
				$('#CAQD').html(AQdayele);
			}
		}else if(i=="SHOWTAB_M11"){ //行政督办办结率
			var rowdata=valdata[i];
			if (rowdata != "" && rowdata != undefined && rowdata != null){
				var sorttitledata=new Array();
				sorttitledata=getIndexByKey(titledata,i)
				var time_type=sorttitledata[0].showtab_time_type ;//同一个表的时间类型都一样 取第一个数据就行
				var xAxisdata=getxAxis(time_type,rowdata);
				var seriesdata=getSeriesData(sorttitledata,rowdata);
				getdubanlineChart(sorttitledata,seriesdata,xAxisdata);
			}
		}else if(i=="SHOWTAB_M71"){ //收入利润
			var rowdata=valdata[i];
			if (rowdata != "" && rowdata != undefined && rowdata != null){
				var sorttitledata=new Array();
				var sortindexArray=new Array();
				sortindexArray.push('A00001');sortindexArray.push('A00005');
				sorttitledata=getIndexByKey2(titledata,i,sortindexArray)
				var time_type=sorttitledata[0].showtab_time_type ;//同一个表的时间类型都一样 取第一个数据就行		    
				var xAxisdata=getxAxis(time_type,rowdata);
				var seriesdata=getSeriesData(sorttitledata,rowdata);
				var lengenddata=getlengenddata(sorttitledata);
				var eleid=i+"_CURVE";
				loadXZChart2(eleid,lengenddata,xAxisdata,seriesdata);
			}
		}else if(i=="SHOWTAB_W23"){ // 舆情信息
			var rowdata=valdata[i];
			if (rowdata != "" && rowdata != undefined && rowdata != null){
				var sorttitledata=new Array();
				sorttitledata=getIndexByKey(titledata,i)
				var time_type=sorttitledata[0].showtab_time_type ;//同一个表的时间类型都一样 取第一个数据就行
				var xAxisdata=getxAxis(time_type,rowdata);
				var seriesdata=getSeriesData(sorttitledata,rowdata);
				var lengenddata=getlengenddata(sorttitledata);		
				 var eleid=i+"_CURVE";
				 loadSZChart(eleid,lengenddata,xAxisdata,seriesdata);
				var chartele=document.getElementById(eleid);
				echarts.getInstanceByDom(chartele).resize();
			}
		}	
		else{
			var rowdata=valdata[i];
			if (rowdata != "" && rowdata != undefined && rowdata != null){
				var eleid=i+"_CURVE";
				setIndexCurveChart(eleid,titledata,rowdata,i);
			}
		}	
	}	
}

/**
 * 加载财务模块
 */
function loadCWData(){
	if(caiwulist.length==0) return;
	var CWupdatetime="";
	var CWtime_type=2;
	for (var i =0;i<caiwulist.length; i++) {
		var row=caiwulist[i];
		var index_filed=row["index_filed"];
		if(index_filed=="A00070") { //A00070总资产
			var ele='<h1>'+parseFloat(row["index_val"]).toFixed(2)+'<sub>'+row["uom"]+'</sub><sup class="title">'+row["index_nm"]+'</sup></h1>';
			//ele+='<span class="triangle"></span></h1><span class="title">'+row["index_nm"]+'</span>';		
			
			$('#caiwu').html(ele);
			CWupdatetime=row["updatetime"];
			CWtime_type=row["time_type"];
		}else if((index_filed=="A00058") ){ //现金流平衡
			var xjl=parseInt(row["index_val"]);
			if(xjl>0){
				$('#xjlph').attr('class','iconfont icon-xunhuan lightgreen');
			}else{
				//放个叉图样式待写
				$('#xjlph').attr('class','iconfont icon-xunhuan lightgreen');//先放个假的
			}
		}
	}
	
	var CWformattime=formatDate(CWupdatetime,CWtime_type);
	var CWele=CWformattime;
	if (!CWele){
		CWele = '';
	}
	$('#DeptTime_10').text(CWele)
	
	
	/*if(caiwulist.length==0) return;
	var caiwuele=$('#caiwu');
	var CGheaderele="";//采购模块最上方显示指标元素(目前是累计采购金额) 
	var CGmiddleele='<div id="caiwu" class="block-middle clearfix">';//采购模块中间指标元素(目前是节约金额和节约率) 
	var CGbottomele='<div class="block-bottom" id="DeptShow_10"   onclick="trunToChartShow(this)"><span class="block-name">财务体系</span><a   href="javascript:void(0);"><span class="line"></span></a></div></div>';
	for (var i =0;i<caiwulist.length; i++) {
		var row=caiwulist[i];
		var index_filed=row["index_filed"];
		if(index_filed=="A00056") { //A00056总资产
			CGheaderele='<div class="block-header clearfix"><div   class="col-sm-6 number-bigsize">';
			CGheaderele+='<h1>'+parseFloat(row["index_val"])+'<sub>'+row["uom"]+'</sub></h1></div>';
			CGheaderele+='<div class="col-sm-6 jind-box"><span class="title">'+row["index_nm"]+'</span><div></div></div></div>';
		}else{
			CGmiddleele+='<div class="col-sm-6">';
			CGmiddleele+='<div id="CW_pie_'+i+'" style="width: 100%; height: 102px;"></div>';
			CGmiddleele+='<span  class="number-pecent">'+parseFloat(row["index_val"])+'<sub>'+row["uom"]+'</sub></span>';
			CGmiddleele+='<span class="Charttitle">'+row["index_nm"]+'</span></div>'
		}
	
	}	
	CGmiddleele+="</div>";
	var CGele="";
	CGele=CGheaderele+CGmiddleele+CGbottomele;
	if(caiwuele) caiwuele.html(CGele);*/
}

/**
 * 处理首页安全结果的数据
 * @param tbdata 安全结果样式
 */
function dealAQTBdata(tbdata){
	
	var data=sortAQTBdata('a00092','99',tbdata);
	return data;
}

/**
 从安全结果的数据中筛选安全类型
 * @param AQType 你需要的安全类型
 * @param AQ_index 安全类型字段名
 * @param tbdata 表数据
 */
function sortAQTBdata(AQ_index,AQType,tbdata){

	var sortedTBdata=new Array();
	for(var i =0;i<tbdata.length; i++){
		var row=tbdata[i];
		var AQTypeDa=row[AQ_index];
		if(AQTypeDa==AQType){
			sortedTBdata.push(row);
		}
	}
	return sortedTBdata;
}

/**
 * 从过滤的安全数据中获得连续安全的日子并且渲染元素
 * @param data
 */
function getContinueAQD(data){
	var Aqday=new Array(); //连续安全月
	var AQele="";
	var count=1;
	var year=data[0].date_y;
	for(var i =0;i<data.length; i++){
		var AQval=data[i].a00030; //发送事故的数量 大于0都是不安全的 字段参考数据
		var AQclass="circle";
		if(parseInt(AQval)>0){
			Aqday=new Array();
			AQclass="circle active";
		}else{
			Aqday.push(i);
		} 
		if(i==0) AQele+="<tr>";
		if((i+1)%4==1&&i>0) AQele+="</tr><tr>";
		AQele+='<td><a class="'+AQclass+'" href="javascript:;"></a></td>';
		count++;		
	}
	
  var month=count-1;
  var anele=year+"年"+month+"月"
  $('#DeptTime_6').text(anele)
  if(count<=12){
	  for(var j =count;j<=12; j++){
		  if((j)%4==1&&j>0) AQele+="<tr>";
		  AQele+='<td>&nbsp;</td>';
	  }
  }
	
	AQele+="</tr>";
	AQele+="<tr>&nbsp;</tr><tr>&nbsp;</tr><tr>&nbsp;</tr>";
	if(AQele=="") AQele="暂无今年数据";
	$('#aqjg').html(AQele);
	
	var continDay=getDayByMonth(year,Aqday);	
	return continDay;
}


/**
 * 根据月份集合获取当日天数
 * @param monthArray 月份集合
 */
function getDayByMonth(year,monthArray){
	var continDay=0;
	for(var i =0;i<monthArray.length; i++){
		var currentmonth=monthArray[i];
		var days=getDaysInOneMonth(year,currentmonth)
		continDay=parseInt(continDay)+parseInt(days);
	}
	return continDay;
}

/**
 * 根据年份月份获取当月的天数
 * @param year
 * @param month
 * @returns
 */
function getDaysInOneMonth(year, month){  
    month = parseInt(month, 10);  
    var d= new Date(year, month, 0);  
    return d.getDate();  
  }  

//===========首页跳转报表展现模块==============================
function trunToChartShow(Obj){
	var deptid=$(Obj)[0].id;
	// 六率
	if (deptid == "DeptShow_1"){
		if($.inArray("&bs_ip2&", ButtonList)==-1){
			alert("您没有这块的权限");
			return;
		}
	// 经营指标
	} else if (deptid == "DeptShow_8"){
		if($.inArray("&bs_ip3&", ButtonList)==-1){
			alert("您没有这块的权限");
			return;
		}
	// 行政办公
	} else if (deptid == "DeptShow_2"){
		if($.inArray("&bs_ip4&", ButtonList)==-1){
			alert("您没有这块的权限");
			return;
		}
	// 人力资源
	} else if (deptid == "DeptShow_4"){
		if($.inArray("&bs_ip5&", ButtonList)==-1){
			alert("您没有这块的权限");
			return;
		}
	// 财务管理
	} else if (deptid == "DeptShow_10"){
		if($.inArray("&bs_ip6&", ButtonList)==-1){
			alert("您没有这块的权限");
			return;
		}
	// 社会责任
	} else if (deptid == "DeptShow_5"){
		if($.inArray("&bs_ip8&", ButtonList)==-1){
			alert("您没有这块的权限");
			return;
		}
	// 采购管理
	} else if (deptid == "DeptShow_3"){
		if($.inArray("&bs_ip9&", ButtonList)==-1){
			alert("您没有这块的权限");
			return;
		}
	// 安全生产
	} else if (deptid == "DeptShow_6"){
		if($.inArray("&bs_ip10&", ButtonList)==-1){
			alert("您没有这块的权限");
			return;
		}
	// 风险控制
	} else if (deptid == "DeptShow_7"){
		if($.inArray("&bs_ip11&", ButtonList)==-1){
			alert("您没有这块的权限");
			return;
		}
	}
	location.href = basePath + "/home/getTBDataByDept1?year="+WholeYear+"&month="+WholeMonth+"&deptid="+deptid;
	//location.href=basePath+"/views/home/homeTB/home2th.jsp";
	/*$.ajax({
		url :  basePath+"/home/getTBDataByDept",
		type : "POST",
		data : {		
			year:WholeYear,
			month:WholeMonth,
			deptid:deptid
		},
		success : function(data) {
			alert(data)
				
		},
		error : function() {
			alert("获取职能表数据失败");
		}
	});*/
	
}

function turnToProject(){
	if($.inArray("&bs_ip1&", ButtonList)==-1){
		alert("您没有这块的权限");
		return;
	}
	var url = "/bim_show/project/_list";
	window.location.href = url;
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
			sorttitledata.push(titledata[i]);
		}
	}
	return sorttitledata;
}

/**
 * 根据表名和过滤字段过滤表字段
 * @param titledata
 * @param valuekey
 * @param sortArray 过滤集合 Array类型
 * @returns {Array}
 */
function getIndexByKey2(titledata,valuekey,sortArray){
	var sorttitledata=new Array();
	for(var i =0;i<titledata.length; i++){
		var storetab_name=titledata[i].storetab_name;
		var index_id=titledata[i].int_storetab_field;
		if(storetab_name==valuekey){
			  if($.inArray(index_id, sortArray)!=-1){
				  sorttitledata.push(titledata[i]);
		       }
			
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

var ldblCD; //劳动6率折线图
var fzblCD; //资产负债率图
var zzblCD; //资产周转率
var lrblCD ;//资本利润率
var jzclCD;//净资产收益率
var sczylCD;//市场占有率

/**
 * 处理6率折线图
 * @param BLTBdata 6率数据
 * @param BLID 6率序号
 */
function DealBLTBdata(BLTBdata,BLID){
	 if(BLID=="BL1"){
		 BLLDTOChart(ldblCD,BLID,BLTBdata) 
	 }
	//劳动生产率
	// var rlb=BLTBdata.SHOWTAB_M2; //劳动生产率原始数据
	//alert(JSON.stringify(bl))
}

/**
 * 劳动生产率生成折线图
 */
function BLLDTOChart(ChartOBj,BLID,BLTBdata){
	if(ChartOBj){
		//直接加载option
	}else{
		if(BLID=="BL1"){
			  var orglist=new Array();
			  var orgdata=BLTBdata["SHOWTAB_M2"];
			  orglist.push("27534");orglist.push("4010");
			  var AQOrgData=sortBLLDTOTBdataByOrg(orglist,orgdata)
			  getsortBLLDTOTBdataByOrgchart(AQOrgData)
		} 
	}
	
}


/**
 * 将数组根据二级组织机构代码进行分割 数据如下
 * [{"date_y":"2017","org1id":"26655","org1nm":"海航实业","org2id":"27534","org2nm":"海航基础","a00008":"0.0650"},
 * {"date_y":"2017","date_m":"2","org1id":"26655","org1nm":"海航实业","org2id":"27534","org2nm":"海航基础","a00008":"0.1180"}
 * 分割为 组织机构代码为键 过滤后数据为值的ma集合 如27534：[{"date_y":"2017","org1id":"26655","org1nm":"海航实业","org2id":"27534","org2nm":"海航基础","a00008":"0.0650"}]
 */
function sortBLLDTOTBdataByOrg(orglist,orgdata){
	var AQOrgData={};
	for(var i =0;i<orglist.length; i++){
		var orgid=orglist[i];
		var orgsortdata=new Array();
		for(var j=0;j<orgdata.length; j++){
			var roworg=orgdata[j].org2id;
			if(roworg==orgid){
				orgsortdata.push(orgdata[j]);
			}
		}
		AQOrgData[orgid]=orgsortdata;
	}
	//alert("834=="+JSON.stringify(AQOrgData))
	return AQOrgData;
}

function getsortBLLDTOTBdataByOrgchart(AQOrgData){
	var BLLDSeious=new Array();
	var XAXIS=getBLxAxis(AQOrgData[4010]);
	var ymax=0;
	var ymin=0;
	for(var i in AQOrgData){
		var BLLDSeriousData={};
		var BLdata=getBLData(AQOrgData[i],'a00008');
		var sermax=parseFloat(BLdata.max());
		var sermin=parseFloat(BLdata.min());
		if(sermax>ymax) ymax=sermax;
		if(sermin>ymin) ymin=sermin;
		//alert("847==="+JSON.stringify(BLdata))
		var name="海航地产";
		if(i=="4010") name="酷铺";
		BLLDSeriousData["name"]=name;
		BLLDSeriousData["type"]='line';
		BLLDSeriousData["smooth"]=true;
		BLLDSeriousData["symbol"]='circle';
		BLLDSeriousData["symbolSize"]=5;
		BLLDSeriousData["showSymbol"]=false;
		BLLDSeriousData["lineStyle"]={
	            normal: {
	                width: 1
	            }
	        };
		BLLDSeriousData["areaStyle"]= {
	            normal: {
	                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
	                    offset: 0,
	                    color: 'rgba(35, 53, 69, 0.3)'
	                }, {
	                    offset: 0.8,
	                    color: 'rgba(35, 53, 69, 0.8)'
	                }], false),
	                shadowColor: 'rgba(0, 0, 0, 0.1)',
	                shadowBlur: 10
	            }
	        };
		BLLDSeriousData["itemStyle"]={
	            normal: {
	                color: 'rgba(255, 255, 255,0.2)',
	                borderColor: 'rgba(255, 255, 255,0.1)',
	                borderWidth: 12

	            }
	        };
		BLLDSeriousData["data"]=BLdata;
		BLLDSeious.push(BLLDSeriousData)
	}
	var eleid="bllinech";
	createBLLineChart(eleid,XAXIS,BLLDSeious,ymax,ymin)
}

function getBLData(datalist,idenkey){
	var dataSer=new Array();
	for(var i=0;i<datalist.length;i++){
		var row=datalist[i];
		var val=row[idenkey];
		if(!val) val=0;
		dataSer.push(val);
	}
	return dataSer;
}

/**
 * 获取x轴展示纬度
 * @param time_type 时间类型
 * 
 */
function getBLxAxis(datalist){
	var xAxisdata=new Array();
	for(var i =0;i<datalist.length; i++){
	       var x_Axis=datalist[i]['date_m']+"月";
	       if($.inArray(x_Axis, xAxisdata)==-1){
	    	   xAxisdata.push(x_Axis);
	       }
	 }
	
	return xAxisdata;
}

/**
 * 数字添加千分符
 * 
 */
function commafy(num) {   
	//1.先去除空格,判断是否空值和非数   
	num = num + "";   
	num = num.replace(/[ ]/g, ""); //去除空格  
	    if (num == "") {   
	    return;   
	    }   
	    if (isNaN(num)){  
	    return;   
	    }   
	    //2.针对是否有小数点，分情况处理   
	    var index = num.indexOf(".");   
	    if (index==-1) {//无小数点   
	      var reg = /(-?\d+)(\d{3})/;   
	        while (reg.test(num)) {   
	        num = num.replace(reg, "$1,$2");   
	        }   
	    } else {   
	        var intPart = num.substring(0, index);   
	        var pointPart = num.substring(index + 1, num.length);   
	        var reg = /(-?\d+)(\d{3})/;   
	        while (reg.test(intPart)) {   
	        intPart = intPart.replace(reg, "$1,$2");   
	        }   
	       num = intPart +"."+ pointPart;   
	    }   
	return num;   
	}  