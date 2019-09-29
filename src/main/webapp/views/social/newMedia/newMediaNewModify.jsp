<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>汇总统计新增、编辑页面</title>
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
				<span class="glyphicon glyphicon-pencil"></span>编辑汇总统计数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增汇总统计数据
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
			<h3 class="data_title1">汇总统计信息</h3>
			<div class="model_part">
				<label class="w20">
					<span class="required"> * </span>开始日期:
				</label>
				<input id="dateFrom" class="w25 time" type="text" name="dateFrom" value="${entityview.dateFrom }" readonly="true" class="time" />
				<label class="w20">
					<span class="required"> * </span>结束日期:
				</label>
				<input id="dateTo" class="w25 time" type="text" name="dateTo" value="${entityview.dateTo }" readonly="true" class="time" />
				<label class="w20"><span class="required"> * </span>总资产(亿):</label>
				<form:input class="w25" path="articles" check="NotEmpty_int_10_0_0+_._." />
				<label class="w20"><span class="required"> * </span>资产变化(亿):</label>
				<form:input class="w25" path="interaction" check="NotEmpty_int_10_0_0+_._." />
				<label class="w20"><span class="required"> * </span>增长率(%):</label>
				<form:input class="w25" path="channel" check="NotEmpty_int_10_0_0+_._."/>
				<label class="w20"></label>
				<label class="w25 setleft"></label> 
				<label class="w20">汇总统计附件:</label>
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
			if($("#articles").val()==""){
				win.errorAlert("总资产不能为空");
				return false;
			}
			if($("#interaction").val()==""){
				win.errorAlert("资产变化不能为空");
				return false;
			}
			if($("#channel").val()==""){
				win.errorAlert("增长率不能为空");
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
			var url = "${pageContext.request.contextPath}/social/newMedia/save";
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
			var url = "${pageContext.request.contextPath}/social/newMedia/saveandreport";
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