<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>数据交换信息新增、编辑页面</title>
<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
<link rel="stylesheet" href="<c:url value="/css/font/iconfont.css"/>" />
<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
<!-- 库|插件 -->
<!-- 新加样式 -->
<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
<link rel="stylesheet" href="<c:url value="/css/workbench_model.css"/>">
<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
</head>
<body>
	<c:choose>
		<c:when test="${op eq 'modify'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>编辑数据交换信息
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增数据交换信息
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
	<form:form id="form2" modelAttribute="entityview" >
		<form:hidden path="id"/>
		<form:hidden path="year"/>
		<form:hidden path="month"/>
		<form:hidden path="status"/>
		<form:hidden path="createPersonId"/>
		<form:hidden path="createPersonName"/>
		<form:hidden path="createDate"/>
		<form:hidden path="reportPersonId"/>
		<form:hidden path="reportPersonName"/>
		<form:hidden path="reportDate"/>
		<form:hidden path="auditorPersonId"/>
		<form:hidden path="auditorPersonName"/>
		<form:hidden path="auditorDate"/>
		<form:hidden path="attachAdress"/>
		<div class="label_inpt_div">
			<h3 class="data_title1">数据交换信息</h3>
			<div class="model_part">
				<label class="w20">
					<span class="required"> * </span>开始日期:
				</label>
				<input id="dateFrom" class="w25 time" type="text" name="dateFrom" value="${entityview.dateFrom }" readonly="true"/>
				<label class="w20">
					<span class="required"> * </span>结束日期:
				</label>
				<input id="dateTo" class="w25 time" type="text" name="dateTo" value="${entityview.dateTo }" readonly="true"/>
				<label class="w20"><span class="required"> * </span>周数:</label>
				<form:input id="week" class="w25" path="week" check="NotEmpty_int_2_0_0+_._." />
				<label class="w20"><span class="required"> * </span>数据输出方:</label>
				<form:input class="w25" path="consensusCount" check="NotEmpty_int_10_0_0+_._." />
				<label class="w20"><span class="required"> * </span>数据输入方:</label>
				<form:input class="w25" path="reportConut" check="NotEmpty_int_10_0_0+_._." />
				<label class="w20"></label>
				<label class="w25 setleft"></label> 
				<label class="w20">数据交换信息附件:</label>
				<input class="w25" type="file" id="wrFile" name="wrFile" />
				<c:if test="${not empty entityview.attachAdress}">
					<a href="${pageContext.request.contextPath}/${entityview.attachAdress}" target="_blank">查看原周报PDF</a>
				</c:if>
			</div>
			<c:if test="${ !empty entityExamineviewlist }">
			<h3 class="data_title1">审核意见</h3>
			<c:forEach items="${ requestScope.entityExamineviewlist}" var="sys" varStatus="status">
			<div class="model_part">
				<label class="w20">审核人:</label>
				<label class="w25 setleft">${ sys.createPersonName}</label>
				<label class="w20">审核时间:</label>
				<label class="w25 setleft">${ sys.createDate}</label>
				<label class="w20">审核结果:</label>
				<label class="w25 setleft">${ sys.examresultName}</label>
				<label class="w20"></label>
				<label class="w25 setleft"></label> 
				<label class="w20" style="vertical-align:top;">审核意见:</label>
				<label class="w75 setleft" style="word-wrap:break-word;">${ sys.examinestr}</label>
			</div>
			</c:forEach>
			</c:if>
			<div class="model_btn">
				<button class="btn btn-primary model_btn_ok" id="commit-1">保存</button>
				<button class="btn btn-primary model_btn_ok" id="commit-2">保存并上报</button>
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
		</div>
	</form:form>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>
<script type="text/javascript">
			
		$(' input.time').jeDate({
				format:"YYYY-MM-DD"
			}
		);

		function checkcommit(){
			if($("#dateFrom").val()==""){
				win.errorAlert("开始日期不能为空");
				return false;
			}
			if($("#dateTo").val()==""){
				win.errorAlert("结束日期不能为空");
				return false;
			}
			var beginDate=$("#dateFrom").val();
			var endDate=$("#dateTo").val();
			if(beginDate > endDate){
				win.errorAlert("开始日期不能大于结束日期");
				return false;
			}
/* 			if (DateDiff(beginDate,endDate) != 7){
				win.errorAlert("舆情监测周期必须为一周");
				return false;
			} */
			if($("#week").val()==""){
				win.errorAlert("周数不能为空");
				return false;
			}
			var week = parseInt($("#week").val());
			if(week > 53){
				win.errorAlert("一年没有这么多周数");
				return false;
			}
			if($("#consensusCount").val()==""){
				win.errorAlert("数据输出方");
				return false;
			}
			if($("#reportConut").val()==""){
				win.errorAlert("数据输入方");
				return false;
			}
			var tag = $("#wrFile")[0];
			if(tag.value != ""){
				if(!fileChange(tag)){
					return false;
				}
			}
			return true;
		}
		// 保存
		$("#commit-1").click(function(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!checkcommit()){
				$.unblockUI();
				return false;
			}
			var url = "${pageContext.request.contextPath}/social/consensus/save";
			var formData = new FormData($("#form2")[0]);
			$.ajax({  
				url : url,  
				type : "POST",  
				data: formData,
				async: false,  
				cache: false,  
				contentType: false,  
				processData: false,  
				success : function(data) {
					$.unblockUI();
					var commitresult=JSON.parse(data);
					if(commitresult.result==true){
						win.successAlert('保存成功！',function(){
								parent.location.reload(true);
								parent.mclose();
						});
					} else {
						win.errorAlert(commitresult.exceptionStr);
					}
				},
				error : function(data) {
					$.unblockUI();
				}
			});
			return false;
		});
		// 保存并上报
		$("#commit-2").click(function(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!checkcommit()){
				$.unblockUI();
				return false;
			}
			var url = "${pageContext.request.contextPath}/social/consensus/saveandreport";
			var formData = new FormData($("#form2")[0]);
			$.ajax({  
				url : url,  
				type : "POST",  
				data: formData,
				async: false,  
				cache: false,  
				contentType: false,  
				processData: false,  
				success : function(data) {
					$.unblockUI();
					var commitresult=JSON.parse(data);
					if(commitresult.result==true){
						win.successAlert('上报成功！',function(){
								parent.location.reload(true);
								parent.mclose();
						});
					} else {
						win.errorAlert(commitresult.exceptionStr);
					}
				},
				error : function(data) {
					$.unblockUI();
				}
			});
			return false;
		});

		var example = $(".expmale");
		example.removeClass("example").remove();
		$(".btn.new").click(function(){
			$("table tbody").append(example.clone());
		});
		$("body").on("click","i.del.btn",function(){
			$(this).closest("tr").remove();
		});
/* 		// 开始日期
		$("#dateFrom").mouseleave(function(){
			var beginDate=$("#dateFrom").val();
			if (beginDate != null && beginDate != ''){
				calrateWeek();
			}
		});
		// 计算周数
		function calrateWeek(){
			var date=$("#dateFrom").val();
			if(date != "" && date != null){
				var dateArray  =  date.split("-");
				// 年
				var year = dateArray[0];
				// 月
				var month = dateArray[1];
				// 日
				var day = dateArray[2];
				// 计算
				var date1 = new Date(year, 0, 1);
				var date2 = new Date(year, month-1, day, 1);
				var dayMS = 24*60*60*1000;
				var firstDay = (7-date1.getDay())*dayMS;
				var weekMS = 7*dayMS;
				date1 = date1.getTime();
				date2 = date2.getTime();
				var week = Math.ceil((date2-date1-firstDay)/weekMS)+1;
				$("#weekCount").text(week);
				$("#weekCount").val(week);
			} else {
				$("#weekCount").text("");
				$("#weekCount").val("");
			}
		} */
		// 计算两个日期相差天数
		function DateDiff(sDate1, sDate2) {  //sDate1和sDate2是yyyy-MM-dd格式
			var aDate, oDate1, oDate2, iDays;
			aDate = sDate1.split("-");
			oDate1 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]);  //转换为yyyy-MM-dd格式
			aDate = sDate2.split("-");
			oDate2 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]);
			iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 / 24); //把相差的毫秒数转换为天数
			return iDays;//返回相差天数
		}

		//关闭弹窗
		$(".model_btn_close").click(function () {
			parent.mclose();
			return false;
		});
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		
		//验证文件类型和大小
		function fileChange(target){  
			//检测上传文件的类型 
			var imgName = document.all.wrFile.value;
		    var ext,idx;   
		    if (imgName == ''){  
		         win.generalAlert("请选择需要上传的文件!");  
		        return false; 
		    } else {   
		        idx = imgName.lastIndexOf(".");   
		        if (idx != -1){   
		            ext = imgName.substr(idx+1).toUpperCase();   
		            ext = ext.toLowerCase( ); 
		           // alert("ext="+ext);
		            if (ext != 'pdf'){
		                win.generalAlert("只能上传.pdf类型的文件!"); 
		                return false;  
		            }   
		        } else {  
			           win.generalAlert("只能上传.pdf类型的文件!");
			            return false;
		        }   
		    }
		    
		    //检测上传文件的大小        
		    var isIE = /msie/i.test(navigator.userAgent) && !window.opera;  
		    var fileSize = 0;           
		    if (isIE && !target.files){       
		        var filePath = target.value;       
		        var fileSystem = new ActiveXObject("Scripting.FileSystemObject");          
		        var file = fileSystem.GetFile (filePath);       
		        fileSize = file.Size;      
		    } else {      
		        fileSize = target.files[0].size;       
		    }     
		
		    var size = fileSize / 1024*1024;   
		
		    if(size>(1024*1024*30)){ 
		    	 win.generalAlert("上传文件不能大于31M!"); 
		      	return false; 
		    }
		    return true;
		} 
	</script>
</html>