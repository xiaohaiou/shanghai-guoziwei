<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>报表详细页面</title>
<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
<!-- 库|插件 -->
<!-- 新加样式 -->
<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
<link rel="stylesheet" href="<c:url value="/css/workbench_model.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">
<style>

.selected{
	background-color:#4492d4 !important;
}

</style>
<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	</head>
	<body>
		<c:choose>
		<c:when test="${op eq 'view'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>查看报表
			</h4>
		</c:when>
		<c:when test="${op eq 'report'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>上报报表
			</h4>
		</c:when>
		<c:when test="${op eq 'examine'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>审核报表
			</h4>
		</c:when>
	</c:choose>
		<div class="label_inpt_div">
		<form id="form1"  class="row"  >
			<input type="hidden" name="organalID" id="organalID" value="${checkedCompanyid }">
			<input type="hidden" name="Date" id="Date" value="${date }">
		</form>
			<div class="model_part">
				<label class="w20" style="text-align: right;">报表单位：</label>
				<label class="w25">${checkedCompanyName}</label>
				<label class="w20" style="text-align: right;"></label>
				<label class="w25" ></label>
				<label class="w20" style="text-align: right;">年份：</label>
				<label class="w25">${year}</label>
				<label class="w20" style="text-align: right;">月份：</label>
				<label class="w25">${month}</label>
				<div id="reportbutton" class="w95">
				<c:forEach items="${ formsList}" var="sys" varStatus="status">
				
				<input type="button" class="btn btn-primary model_btn_ok"  value="${sys.formkindName }${sys.freName}"  id="${sys.id }" onclick="query(this,'${sys.id}')">
				
				</c:forEach>
	
	
				</div>
			
			</div>
			<div class="model_part">
				<div id="ReportDetail" style="width:100%;padding:0 15px;margin: 5px">
				
				
				
				</div>
			</div>
			<div class="" id="createReport">
			</div>
			<c:choose>
				<c:when test="${op eq 'view' || op eq 'report'}">
					
					<c:if test="${ !empty entityExamineviewlist  }">
					<h3 class="data_title1">审核意见</h3>
					<div class="model_part">
					<c:forEach items="${ requestScope.entityExamineviewlist}" var="sys" varStatus="status">
					
						<label class="w20" style="text-align: right">审核人:</label>
						<label class="w25 setleft">${ sys.createPersonName}</label> 
						<label class="w20" style="text-align: right">审核时间:</label>
						<label class="w25 setleft">${ sys.createDate}</label> 
						<label class="w20" style="text-align: right">审核结果:</label>
						<label class="w25 setleft">${ sys.examresultName}</label> 
						<label class="w20"></label>
						<label class="w25 setleft"></label> 
						<label class="w20" style="vertical-align:top;text-align: right">审核意见:</label>
						<label class="w75 setleft" style="word-wrap:break-word;">${ sys.examinestr}</label> 
						
					
					</c:forEach>
					</div>
					</c:if>
					
				</c:when>
				<c:when test="${op eq 'examine'}">
					<div class="model_part">
						<label class="w20" style="vertical-align: top;text-align: right;"><span class="required"> * </span>审核意见:</label>
						<span class="w70 ">
						<textarea  rows="5" cols="50" name="examStr" id="examStr" >
						</textarea></span>
				
					
					</div>
				</c:when>
			</c:choose>
			<jsp:include page="../../system/modal.jsp"/>
			
			<div class="model_btn">
				
				 <c:choose>
				<c:when test="${op eq 'report'}">
					<button class="btn btn-primary model_btn_ok" id="commit-1">上报</button>
				</c:when>
				<c:when test="${op eq 'examine'}">					
					<c:if test="${status!=50115}">
						<button class="btn btn-primary model_btn_ok" id="commit-2">通过</button>
					</c:if>
					<!-- 判断不是预算、或者审核未通过 -->
					<c:if test="${status!=50115 || formType ==52002}">
						<button class="btn btn-primary model_btn_ok" id="commit-3">退回</button>
					</c:if>
					<!-- 判断是预算、审核通过退回 -->
					<c:if test="${status==50115 && formType==52001}">
						<a class="btn btn-primary model_btn_ok" href="javascript:void(0)" onclick="backData()" >退回</a>
					</c:if>
				</c:when>
			</c:choose>
				<button class="btn btn-default model model_btn_close" id="close" onclick="window.close()">关闭</button>
				
			</div>
			
		</div>
		
	</body>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
	
	<script type="text/javascript">
		$("#examStr").val("");
		$("#reportbutton").children()[0].click();
		
		
		
		function check()
		{
			if($("#examStr").val().length>=600)
			{
				win.errorAlert("审核意见不能输入超过600字");
				$.unblockUI();
				return false;
			}
			if($("#examStr").val()=="")
			{
				win.errorAlert("请输入审核意见");
				$.unblockUI();
				return false;
			}
			return true;
		}
		
		$("#commit-1").click(function(){
		$.blockUI({ message: '提交中', css: { width: '275px' } });
					$.post("/shanghai-gzw/reportcommon/report",{groupID:'${groupID}',Date:'${date}',organal:'${checkedCompanyid}'},function(data){
			    		var commitresult=JSON.parse(data);
			    		$.unblockUI();
								if(commitresult.result==true)
									win.successAlert('上报成功！',function(){
											window.opener._query();
											
											window.close();
									});
								else
								{	
									$.unblockUI();
									win.errorAlert(commitresult.exceptionStr,function(){parent.location.reload(true);});
								}
			 		 });
					 return false;
		})
	
		$("#commit-2").click(function(){
		$.blockUI({ message: '提交中', css: { width: '275px' } });
				if(check())
				{
					$.post("/shanghai-gzw/reportcommon/examine",{examineStr:$("#examStr").val(),examineresult:50116,groupID:'${groupID}',Date:'${date}',organal:'${checkedCompanyid}'},function(data){
		    		var commitresult=JSON.parse(data);
		    		$.unblockUI();
							if(commitresult.result==true)
								
								win.successAlert('保存成功！',function(){
										window.opener._query();
										
										window.close();
								});
							else
							{	
								$.unblockUI();
								win.errorAlert(commitresult.exceptionStr,function(){parent.location.reload(true);});
							}
		 		 	});
		 		 }
				 return false;
		})
		
		$("#commit-3").click(function(){
		$.blockUI({ message: '提交中', css: { width: '275px' } });
				if(check())
				{
					$.post("/shanghai-gzw/reportcommon/examine",{examineStr:$("#examStr").val(),examineresult:50117,groupID:'${groupID}',Date:'${date}',organal:'${checkedCompanyid}'},function(data){
			    		var commitresult=JSON.parse(data);
			    		$.unblockUI();
								if(commitresult.result==true)
									win.successAlert('退回成功！',function(){
											window.opener._query();
											window.close();
									});
								else
								{
									win.errorAlert(commitresult.exceptionStr,function(){parent.location.reload(true);});
								}
			 		 });
			 	}
		 		 return false;
		})
	
		function query(obj,formid)
		{
			$("#reportbutton").children().removeClass('selected');
			$(obj).addClass("selected")
			$.post("/shanghai-gzw/reportcommon/showform",{"formid":formid,"organalID":$("#organalID").val(),"Date":$("#Date").val()},function(data1){
				$("#ReportDetail").empty();
				if(data1=="")
				{
					win.errorAlert("未提交该报表");
				}
				else
				{
					var data=JSON.parse(data1);
					
					$("#ReportDetail").append(data.tablestr);
					var rowdata=[];
					 $('tr').each(function(index,item){
						var tds = $('>td',this);
						var min = 1e10;
						tds.each(function(){
							min = Math.min(min,parseInt(this.getAttribute('rowspan')))
							
						})
						if(min>1){
							var diff = min-1;
							tds.each(function(){
					            this.setAttribute('rowspan', parseInt(this.getAttribute('rowspan')) - diff)
					        })
					        
					          $('tr').each(function(index2,item2){
						         if(index2<index)
						         {
						         	var tds2 = $('>td',this);
						         	tds2.each(function(){
										if( index2+parseInt(this.getAttribute('rowspan'))-1>index)
											 this.setAttribute('rowspan', parseInt(this.getAttribute('rowspan')) - diff)
									})
								 }
					        }) 
					        
					        
					    }
					})
					$("#createReport").empty();
					var ReportDetailStr="<h3 class=\"data_title1\">创建上报信息</h3>"+
										"<div class=\"model_part\">"+
											"<label class=\"w20\" style=\"text-align: right;\">数据创建人:</label>"+
											"<label class=\"w25 setleft\">"+ (data.createPersonName==undefined? "":data.createPersonName)+"</label>"+
											"<label class=\"w20\" style=\"text-align: right;\">创建时间:</label>"+
											"<label class=\"w25 setleft\">"+ (data.createDate==undefined? "":data.createDate)+"</label>";
											if(data.reportPersonName!=undefined && data.reportPersonName!="")
											{
												ReportDetailStr+="<label class=\"w20\" style=\"text-align: right;\">数据上报人:</label>";
												ReportDetailStr+="<label class=\"w25 setleft\">"+(data.reportPersonName==undefined? "":data.reportPersonName)+"</label>";
												ReportDetailStr+="<label class=\"w20\" style=\"text-align: right;\">上报时间:</label>";
												ReportDetailStr+="<label class=\"w25 setleft\">"+(data.reportDate==undefined? "":data.reportDate)+"</label>";
											}
											ReportDetailStr+="</div>";
					$("#createReport").append(ReportDetailStr);
				}
			});	
		}

		function backData(){
			var url = "/shanghai-gzw/reportcommon/examinedErrorData?examineStr="+$("#examStr").val()+"&examineresult=50117&groupID="+'${groupID}'+"&Date="+'${date}'+"&organal="+'${checkedCompanyid}';
			console.log(url);
			mload(url);
		}
		
	</script>
</html>