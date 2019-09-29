
$(document).ready(function(){
	showData();
}); 

function showData(){
	indexTimeShow();//替换时间
	getTop10CompanyData(); //替换财务低风险企业top10企业数据
	getFinan_risk_histogram();//替换财务低风险柱状图数据 
	getFinan_icbc_data();//替换工商银行分析饼图数据
	getFinan_icbc_diff();//替换工商银行差异公司
	fin_manage_total();//替换财务与管理口径数据总数
	fin_manage_sasac();
	getpublic_company();//替换上市公司数据
	getData_analysis();//替换数据全景分析
	getData_analysis_desc();
}

/*
 * 获取当前年月时间
 */
function getDateTime(){
	var myDate = new Date();//获取系统当前时间
	var nowYear = myDate.getFullYear();//年
	var nowmonth = myDate.getMonth()+1;//月	
	var nowDate =  nowYear+'-'+nowmonth;
	return nowDate;
}

/*
 * 替换首页时间
 */
function indexTimeShow(){
	$('.time').text(getDateTime());
}

/*
 * 替换财务低风险企业top10企业数据
 */
function getTop10CompanyData(){		
	$.ajax({
		url : basePath+"/index/getFinan_risk_top10",
		type : "POST",
		data : {
				model_id:'finan_risk_top10'
			   },
		success : function(data) {
			var obj = eval(data);
			var year,month,company,time;
            $(obj).each(function (index) {
                var val = obj[index];
                year = val[0];
                month = val[1];
                company = val[2];
                $("#finan_risk_top10").append("<li>" +company+ "</li>");
            });
            $('#finan_risk_top10_time').text(year+'-'+month);
		}
	});	
}

/*
 *替换财务低风险柱状图数据 
 */
function getFinan_risk_histogram(){
	
	$.ajax({
		url : basePath+"/index/getFinan_risk_histogram",
		type : "POST",
		data : {
				model_id:"finan_risk_histogram_debt,finan_risk_histogram_moneyFlow,finan_risk_histogram_profitRisk,finan_risk_histogram_investRisk"
			   },
		success : function(data) {
			var obj = eval(data);
			var risk_red,risk_yellow,risk_green;
            $(obj).each(function (index) {
                var val = obj[index];
              /* risk_red = val[2];
                risk_yellow = val[3];
                risk_green = val[4];
                var data = {risk_red,risk_yellow,risk_green};*/ 
                if(0==index){
                	getBarChart_1(val);
                }
                if(1==index){
                	getBarChart_2(val);
                }
                if(2==index){
                	getBarChart_3(val);
                }
                if(3==index){
                	getBarChart_4(val);
                }               
            });
		}
	});	
}

/*
 * 替换工商银行分析饼图数据
 */
function getFinan_icbc_data(){
	
	$.ajax({
		url : basePath+"/index/getIcbc_model",
		type : "POST",
		data : {
				model_id:"icbc_analysis_num"
			   },
		success : function(data) {
			var obj = eval(data);
			var year,month;
            $(obj).each(function (index) {
                var val = obj[index];
                year = val[0];
                month = val[1];
                getPieChart(val[2],val[3]);
            });
            $('#icbc_time').text(year+'-'+month);
		}
	});	
}

/*
 * 替换工商银行差异公司
 */
function getFinan_icbc_diff(){
	
	$.ajax({
		url : basePath+"/index/getIcbc_model",
		type : "POST",
		data : {
				model_id:"icbc_analysis_diffCompany"
			   },
		success : function(data) {
			var obj = eval(data);
            $(obj).each(function (index) {
                var val = obj[index];
                $("#icbc_analysis_diffCompany").append("<li>" +val+ "</li>");
            });
		}
	});	
}

/*
 * 替换财务与管理口径数据总数
 */
function fin_manage_total(){
	
	$.ajax({
		url : basePath+"/index/getFin_manage",
		type : "POST",
		data : {
				model_id:"fin_manage_total"
			   },
		success : function(data) {
			var obj = eval(data);
			var year,month,total;
            $(obj).each(function (index) {
                var val = obj[index];
                year = val[0];
                month = val[1];
                total = val[2];
            });
            $('#Fin_manage_time').text(year+'-'+month);
            $('#Fin_manage_total').text(total+"个");  
		}
	});	
}


/*
 * 替换财务与管理口径数据总数
 */
function fin_manage_sasac(){
	
	$.ajax({
		url : basePath+"/index/getFin_manage",
		type : "POST",
		data : {
				model_id:"fin_manage_sasac"
			   },
		success : function(data) {
			var obj = eval(data);
            $(obj).each(function (index) {
                var val = obj[index];
                $("#scroll2").append("<li>" +val[0]+"("+val[1]+"家，"+val[2]+"家）"+ "</li>");
            });
		}
	});	
}

/*
 * 替换上市公司数据
 */
function getpublic_company(){
	
	$.ajax({
		url : basePath+"/index/getpublic_company",
		type : "POST",
		data : {
				model_id:"public_company"
			   },
		success : function(data) {
			console.log(data);
			var obj = eval(data);
			var i = 1;
			var year,month;
            $(obj).each(function (index) {
                var val = obj[index];
                year = val[0];
                month = val[1];
                var title = $('#companyTitle'+i);
                var total = $('#companyTotal'+i);
                var current = $('#companyCurrent'+i);
                title.text(val[2]);
                total.text(val[3]);
                current.text(val[4]);
                i++;
            });
            $('#company_time').text(year+"-"+month);
		}
	});	
}

/*
 * 数据全景分析
 */
function getData_analysis(){
	
	$.ajax({
		url : basePath+"/index/getData_analysis",
		type : "POST",
		data : {
				model_id:"data_analysis"
			   },
		success : function(data) {
			var obj = eval(data);
			var year,month,all;
            $(obj).each(function (index) {
                var val = obj[index];
                year = val[0];
                month = val[1];
                all = val[2];
            });
            $('#data_analysis_time').text(year+'-'+month);
            $('#data_analysis_all').text(all);      
		}
	});		
}

/*
 * 数据全景分析
 */
function getData_analysis_desc(){
	
	$.ajax({
		url : basePath+"/index/getData_analysis",
		type : "POST",
		data : {
				model_id:"data_analysis_desc"
			   },
		success : function(data) {
			var obj = eval(data);
            $(obj).each(function (index) {
                var val = obj[index];
                $("#scroll").append("<li>" +val+ "</li>");
            });
		}
	});		
}
