/**
 * 产生折线堆叠图
 * @param eleid      元素位置
 * @param lengenddata  展现组
 * @param xAxisdata   x轴坐标
 * @param seriesdata  坐标系
 * @param bardata     哪些lengenddata中需要用柱状效果的集合 其他默认用折线
 */ 
function createlineChart(eleid,lengenddata,xAxisdata,seriesdata,sorttitledata){
	var lineCHObj = echarts.init(document.getElementById(eleid));
	var seriesChdata=createSeriousData(sorttitledata,seriesdata)
	var legendsel=setSellegend(lengenddata,2);
	if(eleid=="SHOWTAB_D24_Chart")legendsel=setSellegend(lengenddata,1);//新媒体
	//alert(JSON.stringify(legendsel))
	var option="";
	if(eleid=="SHOWTAB_M17_Chart"||eleid=="SHOWTAB_M25_Chart"){
		legendsel=setSellegend(lengenddata,3);
		option=setOptionDoubleY(eleid,seriesChdata,legendsel,lengenddata,xAxisdata);//设置双Y轴
	}else if(eleid=="SHOWTAB_M34_Chart"||eleid=="SHOWTAB_M35_Chart"){
		option=setOptionDoubleY(eleid,seriesChdata,legendsel,lengenddata,xAxisdata);//设置双Y轴
	}else{
		option={
				 colors:['#E066FF','#FF3E96','#BCEE68','#8EE5EE','#8470FF', '#008B45', '#24CBE5', '#912CEE', '#FF9655'],
				 tooltip : {
				        trigger: 'axis',
				        axisPointer: {
				            type: 'cross',
				            label: {
				                backgroundColor: '#6a7985'
				            }
				        }
				    },
				legend: {
				        data:lengenddata,
				        y :'bottom',
				        selected:legendsel
				    },
				    grid: {
				        left: '12%', //给y轴流出的显示位置
				        right: '4%',
				        top:'9%',
				        containLabel: false,
				    },
				    xAxis : [
				        {
				            type : 'category',
				            splitLine:{
				                show:true,
				                lineStyle:{
				                       color: 'rgba(255,255,255,0.2)',
				                },
				            },
				            axisLabel:{  
				                textStyle:{
				                       color: '#484f58',
				                       fontSize:'14'
				                },
				            },
				            boundaryGap : true,
				            data : xAxisdata
				        }
				    ],
				    yAxis : [
			        {
			            type : 'value', color:'#000',
			            splitLine:{
			                show:true,
			                lineStyle:{
			                       color: 'rgba(255,255,255,0.2)',
			                },
			            },
			            axisLabel:{
			                textStyle:{
			                        color: '#484f58',
			                        fontSize:'10'
			                },  
			            },
			        }
			    ],
			 series : seriesChdata
		};
	}
	lineCHObj.setOption(option,true);
	window.onresize = lineCHObj.resize;	
	
	lineCHObj.on('click', function (params) {
		try{
			var levelid="1";
			var selLengend=sorttitledata[params.seriesIndex];
			var selXdata=params.name;
			readyNLVdata(levelid,selLengend,selXdata);
		}catch(e){
			alert(e)
		}
	});
 }

/**
 * 设置双Y轴option
 * 
 */
function setOptionDoubleY(eleid,seriesChdata,legendsel,lengenddata,xAxisdata){
	var option={
			 colors:['#E066FF','#FF3E96','#BCEE68','#8EE5EE','#8470FF', '#008B45', '#24CBE5', '#912CEE', '#FF9655'],
			 tooltip : {
			        trigger: 'axis',
			        axisPointer: {
			            type: 'cross',
			            label: {
			                backgroundColor: '#6a7985'
			            }
			        }
			    },
			legend: {
				left: '12%',	
				padding:5,
		        data:lengenddata,
		        y:'bottom',
		        selected:legendsel
		    },
		    grid: {
		    	width:'80%',
		        left: '12%', //给y轴流出的显示位置
		        right: '4%',
		        top:'9%',
		        containLabel: false,
		    },
		    xAxis : [
		        {
		            type : 'category',
		            splitLine:{
		                show:true,
		                lineStyle:{
		                       color: 'rgba(255,255,255,0.2)',
		                },
		            },
		            axisLabel:{  
		                textStyle:{
		                       color: '#484f58',
		                       fontSize:'14'
		                },
		            },
		            boundaryGap : true,
		            data : xAxisdata
		        }
		    ],
		    yAxis : [
             {
                 type : 'value',
                 name : '亿元',
                 show : true,
                 
             },
             {
                 type : 'value',
                 name : '%',
                 show : true,
             }
           ],
	 series : seriesChdata
	};
	if(eleid=="SHOWTAB_M25_Chart") {
		option.yAxis[0].name="次";
		option.yAxis[1].name="小时";
	}else if(eleid=="SHOWTAB_M34_Chart"){
		option.yAxis[0].name="份";
		option.yAxis[1].name="万元";
		option.legend.padding=1;
		option.legend.left=-7;
	}else if(eleid=="SHOWTAB_M35_Chart"){
		option.yAxis[0].name="个";
		option.yAxis[1].name="万元";
	}
	console.info(option.yAxis);
	return option;
}

/**
 * 产生折线图Serious
 * @param seriesdata
 */
function createSeriousData(sorttitledata,seriesdata){
	var SeriesCh=new Array();
	for(var i =0;i<sorttitledata.length; i++){
		var serieschdata={};
		var name=sorttitledata[i].index_nm;
		var uom=sorttitledata[i].uom;
		if(uom) name+="("+uom+")";
		serieschdata["name"]=name;
		//serieschdata["smooth"]=true;
		var charttype="bar";
		var yAxisIndex= "0";
		if(uom&&hasPercent(uom)){
			charttype="line";
			if(sorttitledata[i].storetab_name=="SHOWTAB_M17") yAxisIndex= "1";//双Y轴设置
		}else if(uom=="小时"&&sorttitledata[i].storetab_name=="SHOWTAB_M25"){
			yAxisIndex= "1";//双Y轴设置
		}else if((uom=="万元"&&sorttitledata[i].storetab_name=="SHOWTAB_M34")||(uom=="万元"&&sorttitledata[i].storetab_name=="SHOWTAB_M35")){
			yAxisIndex= "1";//双Y轴设置
		}
		serieschdata["type"]=charttype;
		serieschdata["yAxisIndex"]=yAxisIndex;
		serieschdata["areaStyle"]={};		
		serieschdata["label"]={
            normal:{					                  
            	show: true,
            	position: 'top'
            }
        };
		serieschdata["data"]=seriesdata[i];
		SeriesCh.push(serieschdata);
		//alert(JSON.stringify(serieschdata))
		
	}
	return SeriesCh;
}

/**
 * 设置坐标图例选中状态
 * @param legendata 坐标图例列表
 * @param selenum   selenum默认选中数量 输入1 默认勾选前1个 以此类推
 */
function setSellegend(legendata,selenum){
	try{
	var Slegends={};
	for(var i =0;i<legendata.length; i++){
		var key=legendata[i]+"";
		if(i<=selenum-1){
			Slegends[key]=true;	
		}else{
			Slegends[key]=false;	
		}
	}
	}catch(e){alert(e)}
	return Slegends;
}

/**
 * 设置管理干部人数坐标图例选中状态
 * @param legendata 坐标图例列表
 * @param selenum   selenum默认选中数量 输入1 默认勾选前1个 以此类推
 */
function setSellegend19(legendata,selenum){
	try{
	var Slegends={};
	for(var i =0;i<legendata.length; i++){
		var key=legendata[i]+"";
		if(key=="M序列(人)"||key=="非M序列(人)"){ 
			Slegends[key]=false; 
		}else if(i<=selenum-1){
			Slegends[key]=true;	
		}else{
			Slegends[key]=false;	
		}
	}
	}catch(e){alert(e)}
	return Slegends;
}

/**
 * 产生柱状堆叠图
 * @param eleid      元素位置
 * @param lengenddata  展现组
 * @param xAxisdata   x轴坐标
 * @param seriesdata  坐标系
 * @param bardata     哪些lengenddata中需要用柱状效果的集合 其他默认用折线
 */ 
function createStackingChart(eleid,lengenddata,xAxisdata,seriesdata,sorttitledata){

	var StackingCHObj = echarts.init(document.getElementById(eleid));
	var seriesChdata=createStackingSeriousData(sorttitledata,seriesdata);
	var legendsel=setSellegend(lengenddata,2);
	if(eleid=="SHOWTAB_M19_Chart") legendsel=setSellegend19(lengenddata,5);
	var option={
			 tooltip : {
			        trigger: 'axis',
			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        }
			    },
			    legend: {
			        data:lengenddata,
			        y :'bottom',
			        selected:legendsel
			    },
			    grid: {
			        left: '12%', //给y轴流出的显示位置
			        right: '4%',
			        top:'9%',
			        containLabel: false,
			    },
			    xAxis : [
			             {
			                 type : 'category',
			                 data : xAxisdata
			             }
			         ],
			    yAxis : [
			                  {
			                      type : 'value'
			                  }
			              ],
		 series : seriesChdata
	};
	StackingCHObj.setOption(option,true);
	window.onresize = StackingCHObj.resize;	
	//点击下钻功能
	//if("SHOWTAB_M18"==eleid){
		StackingCHObj.on('click', function (params) {
			try{
				var levelid="1";
				var selLengend=sorttitledata;
				var selXdata=params.name;
				readyNLVdata(levelid,selLengend,selXdata);
			}catch(e){
				alert(e)
			}
		});
	//}else{}
	
}

/**
 * 创建堆叠柱Serious（这边默认堆在一个stack中）
 */
function createStackingSeriousData(sorttitledata,seriesdata){
	var SeriesCh=new Array();
	for(var i =0;i<sorttitledata.length; i++){
		var serieschdata={};
		var name=sorttitledata[i].index_nm;
		var uom=sorttitledata[i].uom;	
		if(uom) name+="("+uom+")";
		serieschdata["name"]=name;
		var charttype="bar";	
		serieschdata["type"]=charttype;
		if(sorttitledata[i].int_storetab_field=='A00159'||sorttitledata[i].int_storetab_field=='A00160'){
			serieschdata["stack"]='M总量';		
		}else{
			serieschdata["stack"]='总量';		
		}
		serieschdata["data"]=seriesdata[i];
		SeriesCh.push(serieschdata);
		//alert(JSON.stringify(serieschdata))
	}
	return SeriesCh;
}

/**
 * 创建横向条状图
 * @param eleid
 * @param lengenddata
 * @param xAxisdata
 * @param seriesdata
 * @param sorttitledata
 */
function createBarChart(eleid,lengenddata,xAxisdata,seriesdata,sorttitledata){
	var BarCHObj = echarts.init(document.getElementById(eleid));
	var seriesChdata=createBarSeriousData(sorttitledata,seriesdata);
	var legendsel=setSellegend(lengenddata,2);
	var option={
				tooltip : {
			          trigger: 'axis'
			      },
			    legend: {
			        data:lengenddata,
			        y :'bottom',
			        selected:legendsel
			    },
			    grid: {
			        left: '12%', //给y轴流出的显示位置
			        right: '4%',
			        top:'9%',
			        containLabel: false,
			    },
			    calculable : true,
			    xAxis : [
			             {
			                 type : 'value',
			                 boundaryGap : [0, 0.01]
			             }
			         ],
			    yAxis : [
			                  {
			                      type : 'category',
			                      data : xAxisdata
			                  }
			              ],
		 series : seriesChdata
	};
	BarCHObj.setOption(option,true);
	window.onresize = BarCHObj.resize;	
	//点击下钻功能
	//if(eleid=="SHOWTAB_M14"){
		BarCHObj.on('click', function (params) {
			try{
			var levelid="1";
			var selLengend=sorttitledata[params.seriesIndex];
			var selXdata=params.name;
			readyNLVdata(levelid,selLengend,selXdata);
			}catch(e){alert(e)}
		});
	//}else{}
}

/**
 * 创建条状图Serious
 */
function createBarSeriousData(sorttitledata,seriesdata){
	var SeriesCh=new Array();
	for(var i =0;i<sorttitledata.length; i++){
		var serieschdata={};
		var name=sorttitledata[i].index_nm;
		var uom=sorttitledata[i].uom;	
		if(uom) name+="("+uom+")";
		serieschdata["name"]=name;
		var charttype="bar";	
		serieschdata["type"]=charttype;
		serieschdata["data"]=seriesdata[i];
		SeriesCh.push(serieschdata);
		//alert(JSON.stringify(serieschdata))
	}
	return SeriesCh;
}

var colorlist=['#E066FF','#FF3E96','#BCEE68','#8EE5EE','#8470FF', '#008B45', '#24CBE5', '#912CEE', '#FF9655']
var colorpos=0;
/**
 * 用于计算八率加载的环形图 其中元素单位%和非%计算方式和加载方式不一样
 * @param eleid   加载元素的id
 * @param spanid 加载元素的名称的spanid
 * @param data    加载元素的数据  
 */
function setcircle(eleid,spanid,circleName,cicleSelfVal,uom){
	//alert("eleid="+eleid+"  circleName="+circleName+" cicleSelfVal="+cicleSelfVal+" uom="+uom)
	var circleObj = echarts.init(document.getElementById(eleid));
	if(!circleObj) {
		console.log("找不到指定元素id：",eleid);
		return;
	}

	if(isNaN(cicleSelfVal)){
		console.log("数据类型错误 非数字",cicleSelfVal);
		return;
	}
	var uomshowval="";
	if(uom) {
		uomshowval=uom;
	}
	var datalable={
			normal: {
                formatter: parseFloat(cicleSelfVal)+""+uomshowval,
                position: 'center',
                show: true,
                textStyle: {
                    fontSize: '22',
                    color: '#000000'
                }
            }			
	};
	var cicleCompareVal=0;//用于率加载环形数据用
	var paramsname="数值"; //悬浮提示框参数名
	if(uom&&hasPercent(uom)){
		cicleCompareVal=100-parseFloat(cicleSelfVal);
		if(cicleCompareVal<0) cicleCompareVal=0 //有时候比例超过百分之100 图形只能显示100%
		paramsname="比率"; 
		datalable={
	            normal: {
	                formatter: '{d} %',
	                position: 'center',
	                show: true,
	                textStyle: {
	                    fontSize: '22',
	                    color: '#000000'
	                }
	            }
	        }
	}
	var itemcolor=colorlist[colorpos]; //环状条颜色
	colorpos++;
	if(colorpos>8) colorpos=0;//循环赋色
	
	var option = {
		    tooltip: { //提示框组件
		        trigger: 'item', //触发类型(饼状图片就是用这个)
		        formatter: function(params, ticket, callback) { //提示框浮层内容格式器
		            var res = params.seriesName;
		            if(uom&&hasPercent(uom)){
		            res += '<br/>' + params.name + ' : ' + params.percent + '%';
		            }else{
		            res += '<br/>' + params.name + ' : ' + cicleSelfVal+""+uomshowval;
		            }
		            return res;
		        }
		    },
		    grid: { //网格
		        bottom: 0,left:0,right:0,top:0,
		    },
		    xAxis: [{  //X坐标
		        type: 'category',
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
		    yAxis: [{ //y坐标
		        show: false
		    }],
		    series: [{
		        name: circleName, hoverAnimation: false,
		        radius: [
		            '80',
		            '75'
		        ],
		        type: 'pie',
		        labelLine: {
		            normal: {
		                show: false
		            }
		        }, 
		        itemStyle: {
		                normal: {
		                    color: itemcolor
		                },
		          },
		        data: [{
		            value: cicleSelfVal,
		            name:  paramsname,		            
		            label: datalable
		        }, {
		            value: cicleCompareVal,
		            name: '',
		            tooltip: {
		                show: false
		            },
		            itemStyle: {
		                normal: {
		                    color: 'rgba(0,0,0,0.5)'
		                },
		                emphasis: {
		                     color: 'rgba(0,0,0,0.5)'
		                }
		            },
		            hoverAnimation: false
		        }]
		    }, ]
		};
	    circleObj.setOption(option,true);
		window.onresize = circleObj.resize;	
		
		var spanObj=$('#'+spanid); 
		if(spanObj) {
			if(circleName.indexOf('-')>0){ //处理部分带-的指标显示问题
				var circleNamebeg=circleName.substring(0,circleName.indexOf('-'));
				var circleNameend=circleName.substring(circleName.indexOf('-')+1);
				var circleShowName=circleNamebeg+"<br/>"+circleNameend;
				spanObj.html(circleShowName);
			}else{
				spanObj.html(circleName);
			}
			
		}
		
}



/**
 * 创建饼图
 * @param eleid 元素位置id
 * @param originalData 数据 格式参考如下
 *  var originalData = [{
     value: 55,
     name: '核心产品'
 }, {
     value: 70,
     name: '核心品牌'
 }, {
     value: 25,
     name: "孵化"
 }];
 * @param titlenm 标题名字
 */
function createpieChart(eleid,originalData,titlenm){
	var pieObj = echarts.init(document.getElementById(eleid));
	var colourindex=0;
	 echarts.util.each(originalData, function(item, index) {
		 colourindex++;
		 if(colourindex>8) colourindex=0;
	     item.itemStyle = {
	         normal: {
	             color: colorlist[colourindex]
	         }
	     };
	 });
	
	var option = {
		   title: [{
//		      text: '海航实业品\n\n牌数量占比',
			   text:titlenm,
//		      subtext: '2017年',
		      x: 'center',
		      y: 'top',
		      padding: 0,     
		      textStyle: {
		          fontWeight: 'normal',
		          fontSize: 16,
		         }		      
		      }],
		      tooltip : {
		          trigger: 'item',
		          formatter: "{a} <br/>{b} : {c} ({d}%)"
		      },
		     series: [{
		         hoverAnimation: false, //设置饼图默认的展开样式
		         radius : '80%',
		         center: ['50%', '50%'],
		         name: titlenm,
		         type: 'pie',
		         selectedMode: 'single',
		         selectedOffset: 16, //选中是扇区偏移量
		       //  clockwise: true,
		       //  startAngle: 90,		        
		         data: originalData,
		         itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		     }]
		 };
	 pieObj.setOption(option,true);
	 window.onresize = pieObj.resize;
	 // 点击下钻显示品牌信息列表
	 pieObj.on('click', function (params) {
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
  

var level1chartOBj;//一级下层对象

/**
 * 产生一级下层用折线堆叠图
 * @param eleid      元素位置
 * @param lengenddata  展现组
 * @param xAxisdata   x轴坐标
 * @param seriesdata  坐标系
 * @param selLengend    初级页面点击的做坐标系信息
 * fontSize 字体大小
 * rotate  字体偏移度
 */ 
function createlineChartLevel(eleid,lengenddata,xAxisdata,seriesdata,selLengend,orgindata,indexid,fontSize,rotate){

	if (level1chartOBj && level1chartOBj.dispose) {
		level1chartOBj.dispose();
    } 
	var lineCHObj = echarts.init(document.getElementById(eleid));
	var uom=selLengend.uom;
	var seriesChdata=createLv1SeriousData(lengenddata,seriesdata,uom)
	var fontSizeX='14';//字体大小
	var rotateX=0;//字体角度
	if(fontSize) fontSizeX=fontSize;
	if(rotate) rotateX=rotate;
	//alert(JSON.s tringify(legendsel))
	var option={
			 tooltip : {
			        trigger: 'axis',
			        axisPointer: {
			            type: 'cross',
			            label: {
			                backgroundColor: '#6a7985'
			            }
			        },
			        formatter:'{b}:{c0}'
			    },		
			legend: {
			        data:lengenddata,
			        	
			    },
			    grid: {
			        left: '12%', //给y轴流出的显示位置
			        right: '4%',
			        top:'9%',
			        y2:150,
			        containLabel: false,
			    },
			    xAxis : [
					        {
					            type : 'category',

					            splitLine:{
					                show:true,
					                lineStyle:{
					                       color: 'rgba(255,255,255,0.2)',
					                },
					            },
					            axisLabel:{ 
						            rotate: rotateX,
					            	interval:0 ,
					                textStyle:{
					                       color: '#484f58',
					                       fontSize:fontSizeX
					                },
					            },
					            boundaryGap : true,
					            data : xAxisdata
					        }
					    ],
					    yAxis : [
							        {
							            type : 'value', color:'#000', name: uom,
							            splitLine:{
							                show:true,
							                lineStyle:{
							                       color: 'rgba(255,255,255,0.2)',
							                },
							            },
							            axisLabel:{
							                textStyle:{
							                        color: '#484f58',
							                        fontSize:'10'
							                },  
							            },
							        }
							    ],
		 series : seriesChdata
	};
	lineCHObj.setOption(option,true);
	level1chartOBj=lineCHObj;
	window.onresize = lineCHObj.resize;
	
	lineCHObj.on('click', function (params) {
	  
		try{
		   var levelid="2";
		   var selXdata=params.name;
		   loadNextLV2Tbdata(levelid,orgindata,selXdata,lengenddata,indexid,selLengend)
		}catch(e){alert(e)}
	});
 }

/**
 * 获取一级下钻图形坐标系数据 由于永远只有一组坐标系 不需要循环
 * @param sorttitledata
 * @param seriesdata
 * @returns {Array}
 */
function createLv1SeriousData(lengenddata,seriesdata,uom){
	    var SeriesCh=new Array();
		var serieschdata={};
		var charttype="bar";
		if(uom&&hasPercent(uom)){
			charttype="line";
		}
		serieschdata["name"]=lengenddata;
		serieschdata["type"]=charttype;
		serieschdata["areaStyle"]={};		
		serieschdata["label"]={
					                normal:{					                  
					                	show: true,
					                    position: 'top'
					                    }
					              };
		serieschdata["data"]=seriesdata;
		SeriesCh.push(serieschdata);
		//alert(JSON.stringify(serieschdata))
	   return SeriesCh;
}

/**
 * 产生一级下钻柱状堆叠图
 * @param eleid      元素位置
 * @param lengendselect  展现组
 * @param xAxisdata   x轴坐标
 * @param seriesdata  坐标系
 */ 
function createStackingChartLevel(eleid,lengenddata,xAxisdata,seriesdata,selLengend,orgindata,lengendselect){

	if (level1chartOBj && level1chartOBj.dispose) {
		level1chartOBj.dispose();
    }
	var showtab=selLengend[0].storetab_name;
	var StackingCHObj = echarts.init(document.getElementById(eleid));
	var seriesChdata=createStackingSeriousData(selLengend,seriesdata);
	var legendsel=setSellegend(lengendselect,2);
	if(showtab=="SHOWTAB_M19") legendsel=setSellegend(lengendselect,4);
	var option={
			 tooltip : {
			        trigger: 'axis',
			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        }
			    },
			    legend: {
			        data:lengendselect,
			        y :'bottom',
			        selected:legendsel
			    },
			    grid: {
			        left: '12%', //给y轴流出的显示位置
			        right: '4%',
			        top:'9%',
			        containLabel: false,
			    },
			    xAxis : [
			             {
			                 type : 'category',
			                 data : xAxisdata
			             }
			         ],
			    yAxis : [
			                  {
			                      type : 'value'
			                  }
			              ],
		 series : seriesChdata
	};
	StackingCHObj.setOption(option,true);
	level1chartOBj=StackingCHObj;
	window.onresize = StackingCHObj.resize;	
	//点击下钻功能
	StackingCHObj.on('click', function (params) {
		try{
			var levelid="2";
			var selXdata=params.name;
			loadNextLV2Tbdata(levelid,orgindata,selXdata,lengenddata,lengendselect,selLengend);
		}catch(e){
			alert(e)
		}
	});
}

/**
 * 生成阶梯瀑布图
 * @param eid 图表所在div的ID
 * @param legendData 显示元素
 * @param xAxisdata X坐标刻度数组
 * @param totalData 累计值数组
 * @param oneDate 单个值数组
 */
function createLaoDongChart(eid,mData){
	var myChart = echarts.init(document.getElementById(eid));
	var legendData=['人均收入(万元)','人均利润(万元)'];
	var xAxisdata = new Array();
	
	var seriesData1 = new Array();
	var seriesData2 = new Array();
	var seriesData = new Array();
	
//	alert("mData:::::"+mData);
	for(var i=0; i<mData.length; i++){
//		alert("obj:-"+mData[i]);
		xAxisdata.push(mData[i].date_m+"月");
		seriesData1.push(parseFloat(mData[i].a00003));
		seriesData2.push(parseFloat(mData[i].a00004));
	}
	var obj1 = {
            name:'人均收入(万元)',
            type:'line',
            stack: '总量1',
            areaStyle: {normal: {}},
            data:seriesData1
        };
	var obj2 =  {
            name:'人均利润(万元)',
            type:'line',
            stack: '总量2',
            areaStyle: {normal: {}},
            data:seriesData2
        };
	seriesData.push(obj1);
	seriesData.push(obj2);
	var option = {
		    tooltip : {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'cross',
		            label: {
		                backgroundColor: '#6a7985'
		            }
		        }
		    },
		    legend: {
		        data:legendData
		    },
		   
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		            data : xAxisdata
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            name: "万元",
		        }
		    ],
		    series : seriesData
		};

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option,true);
    myChart.on('click', function (params) {
		try{
			var poskey=params.dataIndex;
			var titleName = "海航实业各核心企业" + mData[0].date_y+"年"+params.name+"劳动生产率";
			var seriseIndex = params.seriesIndex;
			setLevelChartAndOpenWinLaoDong(titleName,seriseIndex,mData[poskey].date_y,mData[poskey].date_m);
		}catch(e){alert(e)}
	});
    myChart.on('mouseover', function (params) {
		try{
			var poskey=params.dataIndex;
			changeProcessLaoDong(legendData,seriesData,poskey);
		}catch(e){alert(e)}
	});
    
}

function create4LVChart(eid,mData){
	var myChart = echarts.init(document.getElementById(eid));
	var legendData=['资产周转率','资产负债率','资本利润率','净资产收益率'];
	var xAxisdata = new Array();
	
	var seriesData1 = new Array();
	var seriesData2 = new Array();
	var seriesData3 = new Array();
	var seriesData4 = new Array();
	
//	alert("mData:::::"+mData);
	for(var i=0; i<mData.length; i++){
//		alert("obj:-"+mData[i]);
		xAxisdata.push(mData[i].date_m+"月");
		seriesData1.push(parseFloat(mData[i].a00066));
		seriesData2.push(parseFloat(mData[i].a00067));
		seriesData3.push(parseFloat(mData[i].a00068));
		seriesData4.push(parseFloat(mData[i].a00069));
	}
	var seriesData = new Array();
	var obj1 = {
            name:'资产周转率',
            type:'line',
            stack: '总量1',
            data:seriesData1
        };
	var obj2 = {
            name:'资产负债率',
            type:'line',
            stack: '总量2',
            data:seriesData2
        };
	var obj3 = {
            name:'资本利润率',
            type:'line',
            stack: '总量3',
            data:seriesData3
        };
	var obj4 = {
            name:'净资产收益率',
            type:'line',
            stack: '总量4',
            data:seriesData4
        };
	seriesData.push(obj1);
	seriesData.push(obj2);
	seriesData.push(obj3);
	seriesData.push(obj4);
	
	var option = {
	    tooltip: {
	        trigger: 'axis'
	    },
	    legend: {
	        data:legendData
	    },
	    xAxis: {
	        type: 'category',
	        boundaryGap: false,
	        data: xAxisdata
	    },
	    yAxis: {
	        type: 'value',
	        axisLabel: {
                formatter: '{value}%'
            }
	    },
	    series: seriesData
	};
	myChart.setOption(option,true);
	myChart.on('click', function (params) {
		try{
			var poskey=params.dataIndex;
			var titleName = "海航实业各核心企业" + mData[0].date_y+"年"+params.name+"财务四率";
			var seriseIndex = params.seriesIndex;
			setLevelChartAndOpenWin4V(titleName,seriseIndex,mData[poskey].date_y,mData[poskey].date_m);
		}catch(e){alert(e)}
	});
	myChart.on('mouseover', function (params) {
		try{
			var poskey=params.dataIndex;
			changeProcess4V(legendData,seriesData,poskey);
		}catch(e){alert(e)}
	});
}

/**
 * 
 * @param eid 图表对象ID
 * @param legendData 
 * @param xAxisData X轴显示值
 * @param seriesData 图表数据
 * @param pointStr 格式化Y轴刻度为百分比
 * @param unitName Y轴单位
 * @param isClick 是否加click事件
 */
function createColumnChart(eid,legendData,xAxisData,seriesData,pointStr,unitName,isMouseover){
	if (level1chartOBj && level1chartOBj.dispose) {
		level1chartOBj.dispose();
    }
	
	if(pointStr==undefined){
		pointStr = "";
	}
	if(unitName==undefined){
		unitName = "";
	}
	var myChart = echarts.init(document.getElementById(eid));
	var option = {
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
	        data:legendData
	    },
	    xAxis: [{
            type: 'category',
            data: xAxisData,
            axisPointer: {
                type: 'shadow'
            },
            axisLabel:{  
                interval:0,//横轴信息全部显示  
                rotate:0,//-30度角倾斜显示  
           } 
        }],
	    yAxis: {
            type: 'value',
            name: unitName,
            axisLabel: {
                formatter: '{value}'+pointStr
            }
        },
	    series: seriesData
	};
	myChart.setOption(option,true);
	if(isMouseover!=undefined && isMouseover==true){
		myChart.on('mouseover', function (params) {
			try{
				var poskey=params.dataIndex;
				changeProcessSC(legendData,seriesData,poskey);
			}catch(e){alert(e)}
		});
	}else{
		level1chartOBj=myChart;
	}
}

/**
 * 产生现金流瀑布图
 * @param eleid      元素位置
 * @param lengenddata  展现组
 * @param xAxisdata   x轴坐标
 * @param seriesdata  坐标系
 * @param linedata    需要产生线的坐标系名集合
 */
function createPuBuChart(eleid,lengenddata,xAxisdata,seriesdata,sorttitledata,linedata){
	var PubuChart = echarts.init(document.getElementById(eleid));
	//alert("sorttitledata=== "+JSON.stringify(lengenddata));
    //alert("xAxisdata  ==="+JSON.stringify(xAxisdata))
	//alert("seriesdata  ==="+JSON.stringify(sorttitledata));
    //alert("linedata  ==="+JSON.stringify(linedata));
	var seriesChdata=createPubuSeriousData(sorttitledata,seriesdata,linedata);
	
	var option = {
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
		        data: lengenddata
		        
		    },
		   
		   
		    xAxis: {
		        type : 'category',
		        data:xAxisdata,
		         axisPointer: {
		            type: 'shadow'
		        }
		  
		    },
		    yAxis: {
		        type : 'value'
		    },
		    
		   
		    series: seriesChdata
		};
	PubuChart.setOption(option,true);
	
	PubuChart.on('mouseover', function (params) {

		//console.log(params);
		try{
	/*	var selLengend=sorttitledata[params.seriesIndex];
		var selXdata=params.name;*/
		var poskey=params.dataIndex;
		linkJYXJIden(sorttitledata,seriesdata,poskey)
		}catch(e){alert(e)}
	});
	
	PubuChart.on('click', function (params) {

		//console.log(params);
		try{
		var levelid="1";
		var selLengend=sorttitledata;
		//alert(JSON.stringify(selLengend))
		var selXdata=params.name;
		var poskey=params.dataIndex;
		readyNLVdata(levelid,selLengend,selXdata)
		}catch(e){alert(e)}
	});
}

/**
 * 产生现金流下钻瀑布图
 * @param eleid      元素位置
 * @param lengenddata  标题
 * @param xAxisdata   x轴坐标
 * @param seriesdata  坐标系
 * @param selLengend  初级页面点击的做坐标系信息
 * @param orgindata  DB检索的数据
 * @param lengendselect  展现组
 * @param linedata    需要产生线的坐标系名集合
 */
function createPuBuChartLevel(eleid,lengenddata,xAxisdata,seriusdata,selLengend,orgindata,lengendselect,linedata){
	if (level1chartOBj && level1chartOBj.dispose) {
		level1chartOBj.dispose();
    }
	var PubuChart = echarts.init(document.getElementById(eleid));
	var seriesChdata=createPubuSeriousData(selLengend,seriusdata,linedata);
	
	var option = {
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
		        data: lengendselect
		    },
		    xAxis: {
		        type : 'category',
		        data:xAxisdata,
		        axisPointer: {
		            type: 'shadow'
		        }
		  
		    },
		    yAxis: {
		        type : 'value'
		    },
		    series: seriesChdata
		};
	PubuChart.setOption(option,true);
	level1chartOBj=PubuChart;
	window.onresize = PubuChart.resize;

	PubuChart.on('mouseover', function (params) {
		try{
			var poskey=params.dataIndex;
			linkJYXJIden(selLengend,seriusdata,poskey);
		}catch(e){
			alert(e);
		}
	});
	//点击下钻功能
	PubuChart.on('click', function (params) {
		try{
			var levelid="2";
			var selXdata=params.name;
			loadNextLV2Tbdata(levelid,orgindata,selXdata,lengenddata,lengendselect,selLengend);
		}catch(e){
			alert(e)
		}
	});
}

/**
 * 产生折线图Serious
 * @param seriesdata 坐标信息
 * @param linedata    指定产生线的图形坐标名
 */
function createPubuSeriousData(sorttitledata,seriesdata,linedata){
	var SeriesCh=new Array();
	var colourlist=['#C23531',"#2F4554"];
	var cplpos=0;
	for(var i =0;i<sorttitledata.length; i++){
		var serieschdata={};
		var name=sorttitledata[i].index_nm;
	    var index_name=sorttitledata[i].int_storetab_field
		var uom=sorttitledata[i].uom;
		if(uom) name+="("+uom+")";
		serieschdata["name"]=name;
		//serieschdata["smooth"]=true;
		var charttype="bar";
		if($.inArray(index_name, linedata)!=-1){
			charttype="line";
			serieschdata["smooth"]=true;
		}		
		var sercolour=colourlist[cplpos];
		if(i>1) cplpos=0;
		cplpos++;
		serieschdata["type"]=charttype;
		serieschdata["stack"]='one';		
		serieschdata["itemStyle"]={
					                normal:{					                  
					                	color:sercolour
					                    }
					              };
		serieschdata["data"]=seriesdata[i];
		SeriesCh.push(serieschdata);
		//alert(JSON.stringify(serieschdata))
		
	}
	return SeriesCh;
}

/**
 * 产生品牌下钻信息
 * @param data      数据
 */
function createPinPaiLevel(data){
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
	$('#level1_chart').html(level1chartOBj);
}

/**
 * 产生安全生产过程下钻信息
 * @param data      数据
 */
function createAQGCLevel(data){
	if (level1chartOBj && level1chartOBj.dispose) {
		level1chartOBj.dispose();
    } 
	level1chartOBj='<div align="center" style="margin-top:20px;overflow-y:auto;height:400px;"><table border="1" cellspacing="0" cellpadding="0" id="PPXX" style="width:95%" class="table  table-striped table-bordered table-hover">';
	// 表头
	var date = data[0].date_s;
	level1chartOBj+='<tr>';
    level1chartOBj+='<td width="30%" align="center">指标描述</td>';
    level1chartOBj+='<td width="30%" align="center">'+date+'季</td>';
    level1chartOBj+='</tr>';
    // 数据
    for (var i=0;i<data.length; i++) {
    	var list = data[i];
    	var view = "";
    	if (list.a00093 == "不适用") {
    		view = "不适用";
    	}else{
    		view = list.a00093 + "%";
    	}
    	level1chartOBj+='<tr><td width="30%" align="center">'+list.a00094+'</td>';
    	
    	level1chartOBj+='<td width="30%" align="center">'+view+'</td></tr>';
    }
	level1chartOBj+='</table></div>';
	$('#level1_chart').html(level1chartOBj);
}

/**
 * 产生安全生产结果下钻信息
 * @param data      数据
 */
function createSafetyResultsLevel(data){
	if (level1chartOBj && level1chartOBj.dispose) {
		level1chartOBj.dispose();
    } 
	level1chartOBj='<div align="center" style="margin-top:20px;overflow-y:auto;height:400px;"><table border="1" cellspacing="0" cellpadding="0" id="PPXX" style="width:95%" class="table  table-striped table-bordered table-hover">';
	// 表头
	level1chartOBj+='<tr>';
    level1chartOBj+='<td width="10%" align="center">事故类型</td>';
    level1chartOBj+='<td width="10%" align="center">事故数量 </td>';
    level1chartOBj+='</tr>';
    // 数据
    for(var i=0;i<data.length; i++){
    	var ppList = data[i];
    	level1chartOBj+='<tr><td width="10%" align="center">'+ppList.a00029+'</td>';
    	level1chartOBj+='<td width="10%" align="center">'+ppList.a00030+'</td>';
    }
	level1chartOBj+='</table></div>';
	$('#level1_chart').html(level1chartOBj);
	//Test html2canvas
	//testHtml2Canvas();
}

/*function testHtml2Canvas(){
	html2canvas($("#Chart_depit"), {
        onrendered: function (canvas) {
            var url = canvas.toDataURL("image/jpeg");
            $('#level1_chart').html(canvas);
            //以下代码为下载此图片功能
            //var triggerDownload = $("<a>").attr("href", url).attr("download", getNowFormatDate()+"异常信息.png").appendTo("body");
            //triggerDownload[0].click();
            //triggerDownload.remove();
            console.info(canvas);
        },
        width:800,
        height:400
	});
}*/