
$(document).ready(function(){
	getcompanyList();//替换财务低风险企业top10企业数据
}); 

function showData(company){
    getIndustryCommerceCaliberData(company);//替换国资口径和工商口径数据
    getIndustryCommerceSupervisorData(company);//获取董监信息数据
    getIndustryCommerceShareInformation(company);//获取董监信息数据
}

/*
 * 替换财务低风险企业top10企业数据
 */
function getcompanyList(){
	if(date!=''&&date!=null){
        var year=date.slice(0,4);
        var month=date.slice(5,7);
	}else{
        var nowdate = new Date();
        var year=nowdate.getFullYear();
        var month=nowdate.getMonth()+1;
	}
	$.ajax({
		url : basePath+"/industryCommerce/getCommpanyList",
		type : "POST",
		data : {
				year:year,
				month:month
			   },
		success : function(data) {
			var obj = eval(data);
			$(obj).each(function (index) {
				var val = obj[index];
				$("#companyList").append("<li><span onclick='showData(this.innerHTML)' style=\"cursor:pointer;\">"+val+"</span></li>");
			});
		}
	});
}

/*
 * 替换国资口径和工商口径数据
 */
function getIndustryCommerceCaliberData(company){
	if(date!=''&&date!=null){
        var year=date.slice(0,4);
        var month=date.slice(5,7);
	}else{
        var nowdate = new Date();
        var year=nowdate.getFullYear();
        var month=nowdate.getMonth()+1;
	}
	$.ajax({
		url : basePath+"/industryCommerce/getIndustryCommerceCaliberData",
		type : "POST",
		data : {
				year:year,
				month:month,
				company:company
			   },
		success : function(data) {
			var obj = eval(data);
			$(obj).each(function (index) {
				var val = obj[index];
				var i = 1;
				if(2==val[0]){
					i = 2;
				}
				var commerceName = $('#commerceName'+i);
				var commerceStatus = $('#commerceStatus'+i);					
				var commerceZC = $('#commerceZC'+i);							
				var commerceSS = $('#commerceSS'+i);	
				var commercePeople = $('#commercePeople'+i);						
				var commerceField = $('#commerceField'+i);						
				var commerceYearF = $('#commerceYearF'+i);						
				var commerceYearT = $('#commerceYearT'+i);						
				commerceName.val(val[1]);
				commerceStatus.val(val[2]);				
				commerceZC.val(val[3]+'万元');						
				commerceSS.val(val[4]+'万元');					
				commercePeople.val(val[5]);				
				commerceField.val(val[6]);
				commerceYearF.val(val[7]);					
				commerceYearT.val(val[8]);					
			});
			if(''==obj){
				for(var j=1;j<3;j++){
					var commerceName = $('#commerceName'+j);
					var commerceStatus = $('#commerceStatus'+j);					
					var commerceZC = $('#commerceZC'+j);							
					var commerceSS = $('#commerceSS'+j);	
					var commercePeople = $('#commercePeople'+j);						
					var commerceField = $('#commerceField'+j);						
					var commerceYearF = $('#commerceYearF'+j);						
					var commerceYearT = $('#commerceYearT'+j);		
					commerceName.val('');
					commerceStatus.val('');				
					commerceZC.val('');						
					commerceSS.val('');					
					commercePeople.val('');				
					commerceField.val('');
					commerceYearF.val('');					
					commerceYearT.val('');	
				}				
			}
		}
	});
}
	
/*
 * 获取董监信息数据
 */
function getIndustryCommerceSupervisorData(company){
	if(date!=''&&date!=null){
        var year=date.slice(0,4);
        var month=date.slice(5,7);
	}else{
        var nowdate = new Date();
        var year=nowdate.getFullYear();
        var month=nowdate.getMonth()+1;
	}
	$.ajax({
		url : basePath+"/industryCommerce/getIndustryCommerceSupervisorData",
		type : "POST",
		data : {
				year:year,
				month:month,
				company:company
			   },
		success : function(data) {
			var i=1;
			var j=1;
			var obj = eval(data);
			if(''!=obj){
				$("#supervisor1").empty();
				$("#supervisor2").empty();				
				$("#supervisor1").append('<tr><th >序号</th><th>姓名</th><th>职务类别</th><th>类型</th><th>就职日期</th><th>是否离职</th></tr>');
				$("#supervisor2").append('<tr><th >序号</th><th>姓名</th><th>职务类别</th><th>类型</th><th>就职日期</th><th>是否离职</th></tr>');
				$(obj).each(function (index) {
					var val = obj[index];
					if(1==val[0]){
						$("#supervisor1").append("<tr><td>"+i+"</td><td>"+val[1]+"</td><td>"+val[2]+"</td><td>"+val[3]+"</td><td>"+val[4]+"</td><td>"+val[5]+"</td></tr>");
						i++;
					}
					if(2==val[0]){
						$("#supervisor2").append("<tr><td>"+j+"</td><td>"+val[1]+"</td><td>"+val[2]+"</td><td>"+val[3]+"</td><td>"+val[4]+"</td><td>"+val[5]+"</td></tr>");
						j++;
					}
				});
			}
			if(''==obj){
				$("#supervisor1").empty();
				$("#supervisor2").empty();				
				$("#supervisor1").append('<tr><th >序号</th><th>姓名</th><th>职务类别</th><th>类型</th><th>就职日期</th><th>是否离职</th></tr>');
				$("#supervisor2").append('<tr><th >序号</th><th>姓名</th><th>职务类别</th><th>类型</th><th>就职日期</th><th>是否离职</th></tr>');
			}
		}
	});
}	
	
/*
 * 获取董监信息数据
 */
function getIndustryCommerceShareInformation(company){
	if(date!=''&&date!=null){
        var year=date.slice(0,4);
        var month=date.slice(5,7);
	}else{
        var nowdate = new Date();
        var year=nowdate.getFullYear();
        var month=nowdate.getMonth()+1;
	}
	$.ajax({
		url : basePath+"/industryCommerce/getIndustryCommerceShareInformation",
		type : "POST",
		data : {
				year:year,
				month:month,
				company:company
			   },
		success : function(data) {
			var i=1;
			var j=1;
			var obj = eval(data);
			if(''!=obj){
				$("#shareInformation1").empty();
				$("#shareInformation2").empty();
				$("#shareInformation1").append('<tr><th>序号</th><th>股东名称</th><th>股东类别</th><th>认缴金额</th><th>认缴日期</th><th>认缴方式</th><th>认缴日期</th><th>认缴方式</th><th>货币</th><th>占比</th></tr>');
				$("#shareInformation2").append('<tr><th>序号</th><th>股东名称</th><th>股东类别</th><th>认缴金额</th><th>认缴日期</th><th>认缴方式</th><th>认缴日期</th><th>认缴方式</th><th>货币</th><th>占比</th></tr>');
				$(obj).each(function (index) {
					var val = obj[index];
					if(1==val[0]){
						$("#shareInformation1").append("<tr><td>"+i+"</td><td>"+val[1]+"</td><td>"+val[2]
						+"</td><td>"+val[3]+"</td><td>"+val[4]+"</td><td>"
						+val[5]+"</td><td>"+val[6]+"</td><td>"+val[7]+"</td><td>"
						+val[8]+"</td><td>"+val[9]+"</td></tr>");
						i++;
					}
					if(2==val[0]){
						$("#shareInformation2").append("<tr><td>"+i+"</td><td>"+val[1]+"</td><td>"+val[2]
						+"</td><td>"+val[3]+"</td><td>"+val[4]+"</td><td>"+val[5]
						+"</td><td>"+val[6]+"</td><td>"+val[7]+"</td><td>"+val[8]
						+"</td><td>"+val[9]+"</td></tr>");
						j++;
					}
				});
			}
			if(''==obj){
				$("#shareInformation1").empty();
				$("#shareInformation2").empty();
				$("#shareInformation1").append('<tr><th>序号</th><th>股东名称</th><th>股东类别</th><th>认缴金额</th><th>认缴日期</th><th>认缴方式</th><th>认缴日期</th><th>认缴方式</th><th>货币</th><th>占比</th></tr>');
				$("#shareInformation2").append('<tr><th>序号</th><th>股东名称</th><th>股东类别</th><th>认缴金额</th><th>认缴日期</th><th>认缴方式</th><th>认缴日期</th><th>认缴方式</th><th>货币</th><th>占比</th></tr>');
			}
		}
	});
}	
	