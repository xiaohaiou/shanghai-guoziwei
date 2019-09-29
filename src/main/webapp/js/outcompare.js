

//加载index对应的数据
function query(a){
	var org=$("#organl").val();
	var outorg=$("#organl2").val();
	var season = $("#season").val();

	if(a == 'year'){
	
		var year = $("#year1").val().replace("年", "");
	}else{
		
		var temp = $("#season1").val().split("年");
		
		var year = temp[0];
		switch(temp[1]){
			case '一季度':
				season = 1;
				break;
			case '二季度':
				season = 2;
				break;
			case '三季度':
				season = 3;
				break;
			case '四季度':
				season = 4;
				break;	
		
		}
	}
	
	$.ajax({
		url : basePath+"/outcompare/"+a,
		type : "POST",
		data : {"orgID":org,"outorgID":outorg,'year':year,'season':season},
		success : function(data1) {
			
			var maxDate="";
			$("*[name='publicorgname']").text($("#organl").find("option:selected").text())
			$("*[name='outorgname']").text($("#organl2").find("option:selected").text())
			if(data1 && data1!=""){
				
				var data=JSON.parse(data1)
				var publicorg=data.publicdata;
				var outorg=data.outdata;
				var maxDate=dealtime(publicorg[0].year+(publicorg[0].season==undefined? "": publicorg[0].season))
				$(".selecttime").val(maxDate);
				
				changevalue(data)
				createbarEcharts2(data)
				createbarEcharts1(data)
			}
			
			
			
		}
	});
}

function changevalue(data)
{
	
	var publicorg=data.publicdata;
	var outorg=data.outdata;
	var orgarr=['publicorgname','outorgname'];//海航上市企业，外部企业
	var index=[];//日期维度
	var target=['','_a00449','_a00450','_a00451','_a00452','_a00453','_a00454','_a00455','_a00456','_a00457','_a00458','_a00459','_a00460','_a00461','_a00462','_a00463','_a00464','_a00465','_a00466']
	for ( var int = 1; int <= data.publicdata.length; int++) {
		index.push("_"+int);
	}
	
	for ( var i = 0; i < orgarr.length; i++) {
		for ( var j = 0; j < index.length; j++) {
			for ( var k = 0; k < target.length; k++) {
				var jqname=orgarr[i]+index[j]+target[k];
				var value="";
				if(i==0)
					value=publicorg[j]
				else
					value=outorg[j]
				if(target[k]!=''){
					var fillval=dealvalue(value[target[k].replace('_','')])
					$.each($("*[name='"+jqname+"']"),function(index,item){
						if($(item).attr("norod")==undefined)
							$(item).text(fillval)
						else if($(item).attr("norod")=="")
							$(item).text(fillval=="-"? "":fillval)
					})
					
					
				}else{
					$("*[name='"+jqname+"']").text( (value['orgNm']==undefined? (i==0? $("#organl").find("option:selected").text():$("#organl2").find("option:selected").text()):value['orgNm']));
					$("*[name='"+jqname+"']").append("<br>")
					$("*[name='"+jqname+"']").append((dealtime(value.year+(value.season==undefined? "":value.season))))
					
				}
				
				
					
			}
		}
	}
	calcompare();
	dealHorizontalCompare(data);//处理左右两边分割的水平图
	dealVerticalCompare(data);//处理上下两边分割的水平图
	dealZCZZL(publicorg[0].a00462,outorg[0].a00462)//资产周转率单独的条
}


function calcompare()
{
	$("*[name='compare']").val()
	
	$.each($("*[name='compare']"),function(index,item){
		var text="";
		var inorgvalue= $(item).prev().prev().text();
		var outorgvalue=$(item).prev().text();
		if(inorgvalue=="" || inorgvalue=="-" || outorgvalue=="" || outorgvalue=="-")
			text="-";
		else{
			var icon="";
			var comparevalue=parseFloat(inorgvalue-outorgvalue).toFixed(2)
			if(comparevalue>0)
			{
				text+="+"+comparevalue;
				icon="<i class=\"arrow-up\"></i>"
			}else if(comparevalue<0)
			{
				text+=comparevalue;
				icon="<i class=\"arrow-down\"></i>"
			}else
				text+=comparevalue;
		}
		$(item).text(text);
		$(item).append(icon);
	})
}

function dealvalue(a)
{
	if(!a || a=="")
		return "-"
	else
		return parseFloat(a).toFixed(2);
}

function dealtime(time)
{
	if(time.length==4)
		return time
	else{
		var year=time.slice(0,4);
		var season=time.slice(4);
		switch (season) {
			case "1":
				return year+"/"+"3"+"/31";
				break;
			case "2":
				return year+"/"+"6"+"/30";
				break;
			case "3":
				return year+"/"+"9"+"/30";
				break;
			case "4":
				return year+"/"+"12"+"/31";
				break;
		}
	}
			
}

function dealZCZZL(a,b)
{
	var aval=0;
	var bval=0;
	if( a && a!="" && a!="-")
		aval=parseFloat(a);
	if( b && b!="" && b!="-")
		bval=parseFloat(b);
	var allval=(aval+bval);
	var awidth= allval==0? 0 :aval/allval*100;
	var bwidth= allval==0? 0 :bval/allval*100;
	awidth=awidth>100? 100:awidth;
	bwidth=bwidth>100? 100:bwidth;
	$(".c2barl").css("width",awidth+"%")
	$(".c2barr").css("width",bwidth+"%")
}


//处理上下两边分割的水平图
function dealVerticalCompare(data)
{
	var publicorg=data.publicdata;
	var outorg=data.outdata;
	
	fillVerticalCompareData($("#horizontal_a00456_1"),publicorg[0].a00456,outorg[0].a00456)
	fillVerticalCompareData($("#horizontal_a00457_1"),publicorg[0].a00457,outorg[0].a00457)
	fillVerticalCompareData($("#horizontal_a00458_1"),publicorg[0].a00458,outorg[0].a00458)	
	
	fillVerticalCompareData($("#horizontal_a00459_1"),publicorg[0].a00459,outorg[0].a00459)
	fillVerticalCompareData($("#horizontal_a00460_1"),publicorg[0].a00460,outorg[0].a00460)
	fillVerticalCompareData($("#horizontal_a00461_1"),publicorg[0].a00461,outorg[0].a00461)	
	
}

//填充上下两边分割的水平图数据
function fillVerticalCompareData(obj,a,b,Width)
{
	//差值
	var SubtractionVal=Calsubtraction(a,b);
	
	if(SubtractionVal!="")
	{
		if(SubtractionVal>0)
		{
			obj.removeClass("down").addClass("up");
			obj.text("+"+SubtractionVal);
		}else
		{
			obj.removeClass("up").addClass("down");
			obj.text(SubtractionVal);
		}
	}
	else{
		obj.text("");
		obj.removeClass("up").removeClass("down");
	}
	
	CalVerticalWidth(obj,a,b,Width)

}


//计算横向条长度
function CalVerticalWidth(obj,a,b)
{
	var aval=0;
	var bval=0;
	if( a && a!="" && a!="-")
		aval=parseFloat(a);
	if( b && b!="" && b!="-")
		bval=parseFloat(b);
	var allval=(aval+bval);
	var awidth= allval==0? 0 :aval/allval*100;
	var bwidth= allval==0? 0 :bval/allval*100;
	awidth=awidth>100? 100:awidth;
	bwidth=bwidth>100? 100:bwidth;
	var el = obj.closest('.dc2item')
	el.find('.dc2c1').animate({"width":awidth+"%"})
	el.find('.dc2c2').animate({"width":bwidth+"%"})
}



//处理左右两边分割的水平图
function dealHorizontalCompare(data)
{
	var publicorg=data.publicdata;
	var outorg=data.outdata;
	
	fillHorizontalCompareData($("#compare_a00449_1"),publicorg[0].a00449,outorg[0].a00449)
	fillHorizontalCompareData($("#compare_a00450_1"),publicorg[0].a00450,outorg[0].a00450)
	fillHorizontalCompareData($("#compare_a00451_1"),publicorg[0].a00451,outorg[0].a00451)	
	
	fillHorizontalCompareData($("#compare_a00465_1"),publicorg[0].a00449,outorg[0].a00449)
	fillHorizontalCompareData($("#compare_a00466_1"),publicorg[0].a00450,outorg[0].a00450)
}

//填充 左右两边分割的水平图数据
function fillHorizontalCompareData(obj,a,b)
{
	//差值
	var SubtractionVal=Calsubtraction(a,b);
	
	if(SubtractionVal!="")
	{
		if(SubtractionVal>0)
		{
			obj.removeClass("down").addClass("up");
			obj.text("+"+SubtractionVal);
		}else
		{
			obj.removeClass("up").addClass("down");
			obj.text(SubtractionVal);
		}
	}
	else{
		obj.text("");
		obj.removeClass("up").removeClass("down");
	}
	
	CalWidth(obj,a,b)

}

//减法
function Calsubtraction(a,b)
{
	if(a && b && a!="-" && b!="-")
	{
		return (parseFloat(a)-parseFloat(b)).toFixed(2);
	}
	else
		return ""
}

//计算横向条长度
function CalWidth(obj,a,b)
{
	var aval=0;
	var bval=0;
	if( a && a!="" && a!="-")
		aval=parseFloat(a);
	if( b && b!="" && b!="-")
		bval=parseFloat(b);
	var allval=(aval+bval);
	var awidth= allval==0? 0 :aval/allval*100;
	var bwidth= allval==0? 0 :bval/allval*100;
	awidth=awidth>100? 100:awidth;
	bwidth=bwidth>100? 100:bwidth;
	obj.parent().parent().css("width",bwidth+"%")
	obj.parent().parent().parent().prev().children().css("width",awidth+"%")
}

//柱状图
function createbarEcharts1(data){
	
	var bar = echarts.init(document.getElementById("echart_1"));
	var option = {
	 
		//color: ['#328dcb','#ff7000'],
		color: ["#fd4a72","#4e99cc","#aaa8ff","#ffa8ce","#66f2f1","#ff0000"],
		tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        }
	    },

	    legend: {
	    	 top:"40px",
	    	 textStyle:{
		        	color: "#ffffff"
		        },
		     data:[$("#organl").find("option:selected").text(),$("#organl2").find("option:selected").text()]
	    },
	    grid: {
	    	top: '100',
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
		 	            data: ['营业总收入','毛利润','归属利润','扣非净利润'],
		 	            axisLine: {
		                     lineStyle: {
		                         type: 'solid',
		                         color: '#ffffff',//左边线的颜色
		                         width:'1'//坐标线的宽度
		                     }
		                 }
		 	        }
		 	    ],
	    yAxis: [
	            {
		            type: 'value',
		            name: '亿',
		            splitLine:{
		            	lineStyle:{
		            		color: '#ffffff'
		            	},
		            	show: false
		            },
		            min: 0,
		            //interval: 5000,
		            axisLabel: {
		                formatter: '{value}'
		            },
		            axisLine: {
	                    lineStyle: {
	                        type: 'solid',
	                        color: '#ffffff',//左边线的颜色
	                        width:'1'//坐标线的宽度
	                    }
	                }
		        }
	    ],
	    series: [
	        {
	            name:$("#organl").find("option:selected").text(),
	            type:'bar',
	            data:[data.publicdata[0].a00452,data.publicdata[0].a00453,data.publicdata[0].a00454,data.publicdata[0].a00455]
	        },
	        {
	            name:$("#organl2").find("option:selected").text(),
	            type:'bar',
	            data:[data.outdata[0].a00452,data.outdata[0].a00453,data.outdata[0].a00454,data.outdata[0].a00455]
	        }
	    ]
	};
	bar.setOption(option,true);
	window.onresize = bar.resize;
}


//柱状图
function createbarEcharts2(data){
	
	var bar = echarts.init(document.getElementById("echart_2"));
	var option = {
	 
		//color: ['#328dcb','#ff7000'],
		color: ["#fd4a72","#4e99cc","#aaa8ff","#ffa8ce","#66f2f1","#ff0000"],
		tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        }
	    },
 
	    legend: {
	    	 top:"40px",
	    	 textStyle:{
		        	color: "#ffffff"
		        },
		     data:[$("#organl").find("option:selected").text(),$("#organl2").find("option:selected").text()]
	    },
	    grid: {
	    	top: '100',
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
		 	            data: ['存货周转天数','应收账款天数'],
		 	            axisLine: {
		                     lineStyle: {
		                         type: 'solid',
		                         color: '#ffffff',//左边线的颜色
		                         width:'1'//坐标线的宽度
		                     }
		                 }
		 	        }
		 	    ],
	    yAxis: [
	            {
		            type: 'value',
		            name: '天',
		            splitLine:{
		            	lineStyle:{
		            		color: '#ffffff'
		            	},
		            	show: false
		            },
		            min: 0,
		            //interval: 5000,
		            axisLabel: {
		                formatter: '{value}'
		            },
		            axisLine: {
	                    lineStyle: {
	                        type: 'solid',
	                        color: '#ffffff',//左边线的颜色
	                        width:'1'//坐标线的宽度
	                    }
	                }
		        }
	    ],
	    series: [
	        {
	            name:$("#organl").find("option:selected").text(),
	            type:'bar',
	            data:[data.publicdata[0].a00463,data.publicdata[0].a00464]
	        },
	        {
	            name:$("#organl2").find("option:selected").text(),
	            type:'bar',
	            data:[data.outdata[0].a00463,data.outdata[0].a00464]
	        }
	    ]
	};
	bar.setOption(option,true);
	window.onresize = bar.resize;
}
