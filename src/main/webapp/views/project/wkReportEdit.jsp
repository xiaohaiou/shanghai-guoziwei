<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>采购数据新增、编辑页面</title>
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
	
	<h4>
		<c:if test="${not empty wkReport.id}">
			<span class="glyphicon glyphicon-pencil"></span>修改周报信息
		</c:if>
		<c:if test="${empty wkReport.id}">
			<span class="glyphicon glyphicon-pencil"></span>新增周报信息
		</c:if>
		<i class="iconfont icon-guanbi"></i>
	</h4>
	
		<form id="form2">
			<div class="label_inpt_div">
				<label class="w20"><span class="required"></span>项目名称:</label>
				<c:if test="${ not empty project}">
					<input class="w25" type="text"  name="pjName" value="${project.pjName}" readonly="readonly"/>
				</c:if>
				<c:if test="${empty project}">
					<input class="w25" type="text" id="pjName" name="pjName" value="" readonly="readonly"/>
				</c:if>
				<label class="w20" style="display:none;">周报标题:</label>
				<input class="w25" type="text" style="display:none;" name="wrTitle" id="wrTitle" value="${wkReport.wrTitle }" />
				<label class="w20"><span class="required"> * </span>周报年份:</label>
				<input class="w25 timeY" type="text" name="wrYear"   value="${wkReport.wrYear }" readonly="readonly" id="wrYear"/>
				<label class="w20"><span class="required"> * </span>第几周:</label>
				<input class="w25" type="text" name="wrWeek" value="${wkReport.wrWeek }" check="NotEmpty_int_11_._0+_1_100" id="wrWeek"/>
				<div style="margin-left: 123px;padding-bottom: 12px;">
					<c:if test="${not empty project}">
						<span  style="word-wrap: break-word;font-weight: normal;width: 45%;">
							<a href="javascript:;" class="btn btn-primary" onclick="obtainInfoFromESB();">同步周报信息</a>
						</span>
						<span style="word-wrap: break-word;font-weight: normal;width: 45%;color:red;">
							自动填充周报开始时间、结束时间、工程进度、工程质量、工程安全以及招标采购
						</span>
					</c:if>
				</div>
				<label class="w20"><span class="required"> * </span>周报开始日期:</label>
				<input class="w25 time" readonly="readonly" type="text" name="wrStartTime" id="wrStartTime" value="${wkReport.wrStartTime }"  />
				<label class="w20"><span class="required"> * </span>周报结束日期:</label>
				<input class="w25 time" readonly="readonly" type="text" name="wrEndTime" id="wrEndTime"  value="${wkReport.wrEndTime }"  />
				<br/>
				<label class="w20"><span class="required"> * </span>周报附件:</label>
				<span class="w70" style="text-align: left;"><input class="w25" type="file" id="wrFile" name="wrFile" check="NotEmpty_._._._._._." />
					<c:if test="${not empty wkReport.wrLine}">
						<a href="${pageContext.request.contextPath}/${wkReport.wrLine}" target="_blank">查看原周报PDF</a>
					</c:if>
					<font color="red">注：请上传PDF格式的周报附件</font>
				</span>
				
				<label class="w20">工程进度:</label>
				<textarea class="w70" type="text" name="wrJdContent"  check="Empty_string_1000_._._._." maxlength="1000">${wkReport.wrJdContent}</textarea>
				<br/>
				<label class="w20">工程质量:</label>
				<textarea class="w70" type="text" name="wrZlContent" check="Empty_string_1000_._._._." maxlength="1000">${wkReport.wrZlContent }</textarea>
				<br/>
				<label class="w20">工程安全:</label>
				<textarea class="w70" type="text" name="wrAqContent"  check="Empty_string_1000_._._._." maxlength="1000">${wkReport.wrAqContent }</textarea>
				<br/>
				<label class="w20">招标采购:</label>
				<textarea class="w70" type="text" name="wrCgContent" check="Empty_string_1000_._._._." maxlength="1000">${wkReport.wrCgContent }</textarea>
				<br/>
				<label class="w20">要协调事项:</label>
				<textarea class="w70" type="text" name="wrSxContent" check="Empty_string_1000_._._._." maxlength="1000">${wkReport.wrSxContent }</textarea>
				
				<div class="model_btn">
					<input type="hidden" name="id" value="${wkReport.id }" />
					<input type="hidden" name="pjId" value="${project.id }" />
					<input type="hidden" name="Pid" id="Pid" value="${project.outerPjId}" />
					<c:if test="${ not empty project}">
						<button class="btn btn-primary model_btn_ok" id="commit-2">提交</button>
					</c:if>
					<c:if test="${empty project}">
						<button class="btn btn-primary model_btn_ok" id="commit-1">提交</button>
					</c:if>
					<button class="btn btn-default model model_btn_close">关闭</button>
				</div>
			</div>
		<form>
	<jsp:include page="../system/modal.jsp"/>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script src="<c:url value="/js/vaild.js"/>"></script>
<script src="<c:url value="/js/validation.js"/>"></script>
<script type="text/javascript">
		<c:if test="${ not empty project}">
			var pjName = '${project.pjName}';
		</c:if>
		<c:if test="${empty project}">
			//初始化项目名称
			$("#pjName").val($("#ppjName", window.parent.document).val());
			var pjName = $("#pjName").val();
		</c:if>
		
		
		$(' input.time').jeDate(
			{
				format:"YYYY-MM-DD"
			}
		);
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		$('input.timeY').jeDate(
			{
				format:"YYYY",
				choosefun: function(datas){ //选择日期完毕的回调
			        var year = datas.val();//选择
			        var week = $("#wrWeek").val();
			        $("#wrTitle").val(year + "年" + pjName + "项目第" + week + "周");
			    }
			}
		)
		
		$("#wrWeek").on("input",function(){
			var year = $("#wrYear").val();
			if(year == ''){
				var crtDate = new Date();
				year = crtDate.getFullYear();
				$("#wrYear").val(year);
			}
			var week = $("#wrWeek").val();
			if(week > 0 && week < 101){
		        $("#wrTitle").val(year + "年" + pjName + "项目第" + week + "周");
			}else{
				$("#wrTitle").val(year + "年" + pjName + "项目第" + week + "周");
			}
		});
		
		//关闭弹窗
		$(".model_btn_close").click(function () {
	        window.parent.mclose();
			return false;
		});
		
		function checkcommit()
		{
			var form = document.getElementById("form2")
			if(isEmpty(form.wrTitle.value)){
				form.wrTitle.focus();
				win.generalAlert("周报标题不能为空！");
				return false;
			}
			if(isEmpty(form.wrYear.value)){
				form.wrYear.focus();
				win.generalAlert("周报年份不能为空！");
				return false;
			}
			if(isEmpty(form.wrWeek.value)){
				form.wrWeek.focus();
				win.generalAlert("第几周不能为空！");
				return false;
			}
			if(isEmpty(form.wrStartTime.value)){
				form.wrStartTime.focus();
				win.generalAlert("周报开始日期不能为空！");
				return false;
			}
			if(isEmpty(form.wrEndTime.value)){
				form.wrEndTime.focus();
				win.generalAlert("周报结束日期不能为空！");
				return false;
			}
			console.log(compareDate(form.wrStartTime.value,form.wrEndTime.value));
			if(compareDate(form.wrStartTime.value,form.wrEndTime.value) > 0){
				win.generalAlert("周报开始日期不能大于结束日期！");
				return false;
			}
			<c:if test="${empty wkReport.id}">
				var tag = $("#wrFile")[0];
				if(!fileChange(tag)){
					return false;
				}
			</c:if>
			return true;
		}
		
		$("#commit-2").click(function(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!checkcommit())
			{
					$.unblockUI();
					return false;
			}
					//var entityBasicInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/project/wkReport/_saveWkReport";
			
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
					 win.successAlert('保存成功！',function(){
					 		parent.initNodeList('wkReport',1);
							parent.mclose();
					 });
			     },  
			     error : function(data) {
			     	console.log(data)
			     	$.unblockUI();
			     }  
			}); 
				

			return false;
		
		});
		
		$("#commit-1").click(function(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!checkcommit())
			{
					$.unblockUI();
					return false;
			}
					//var entityBasicInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/project/wkReport/_saveWkReportTemp";
			
			var formData = new FormData($("#form2")[0]);
			console.log(formData);
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
			     	console.log(data);
			     	var v = eval("("+data+")");
			     	var t = '${wkReport.id}';
			     	if(t != '')
			     		modifyNodeTableTr(v);
			     	else
			     		createNodeTableTr(v);
					 win.successAlert('保存成功！',function(){
							parent.mclose();
					 });
			     },  
			     error : function(data) {
			     	$.unblockUI();
			     }  
			}); 
			return false;
		})
		
	// 在父页面创建nodeTable中的tr
	function createNodeTableTr(v) {
		var b1 = "<a class=\"btn btn-primary model_btn_ok\" onclick=\"mload('${pageContext.request.contextPath}/project/wkReport/_modifyWkReportTemp?id="+v.id+"')\">编辑</a>";
		var b2 = "<a class=\"btn btn-primary model_btn_ok\" onclick=\"delTemp('"+v.id+"','wkReport')\">删除</a>";
		var h1 = "<input type=\"hidden\" name=\"wkReportIds\" value=\""+v.id+"\"/>";
		var reportStatus = v.reportStatus;
		var status = '';
		if(reportStatus == 0){
			status = "<span style=\"color:#3366ff\">未上报</span>";
		}else if(reportStatus == 1){
			status = "<span style=\"color:#ff9933\">待审核</span>";
		}else if(reportStatus == 2){
			status = "<span style=\"color:#00cc66\">已审核</span>";
		}else if(reportStatus == 3){
			status = "<span style=\"color:#ff399d\">已退回</span>";
		}
		var e1 = $("<tr id=\"wkReport"+v.id+"\"><td>新</td><td>" + v.wrTitle + "</td><td>"
				+ v.wrYear + "</td><td>" + v.wrWeek
				+ "</td><td>" + v.wrStartTime+" ~ "+v.wrEndTime + "</td><td><a href=\"${pageContext.request.contextPath}/"+v.wrLine+"\" target=\"_blank\">查看周报</a></td><td>"+status+"</td><td>" + b1
				+ b2 + h1 + "</td></tr>");
		$("#wkReportTableTr", window.parent.document).after(e1);
	}
	
	//编辑在父页面nodeTable中的tr
	function modifyNodeTableTr(v){
		var reportStatus = v.reportStatus;
		var status = '';
		if(reportStatus == 0){
			status = "<span style=\"color:#3366ff\">未上报</span>";
		}else if(reportStatus == 1){
			status = "<span style=\"color:#ff9933\">待审核</span>";
		}else if(reportStatus == 2){
			status = "<span style=\"color:#00cc66\">已审核</span>";
		}else if(reportStatus == 3){
			status = "<span style=\"color:#ff399d\">已退回</span>";
		}
		var e1 = $("#wkReport"+v.id, window.parent.document).empty();//清除tr中的td
		var b1 = "<a class=\"btn btn-primary model_btn_ok\" onclick=\"mload('${pageContext.request.contextPath}/project/wkReport/_modifyWkReportTemp?id="+v.id+"')\">编辑</a>";
		var b2 = "<a class=\"btn btn-primary model_btn_ok\" onclick=\"delTemp('"+v.id+"','wkReport')\">删除</a>";
		var h1 = "<input type=\"hidden\" name=\"wkReportIds\" value=\""+v.id+"\"/>";
		var td1 = $("<td>新</td>");
		var td2 = $("<td>" + v.wrTitle + "</td>");
		var td3 = $("<td>"+ v.wrYear + "</td>");
		var td4 = $("<td>" + v.wrWeek + "</td>");
		var td5 = $("<td>" + v.wrStartTime+ " ~ " +v.wrEndTime + "</td>");
		var td6 = $("<td><a href=\"${pageContext.request.contextPath}/"+v.wrLine+"\" target=\"_blank\">查看周报PDF</a></td>");
		var td7 = $("<td>" + status + "</td>");
		var td8 = $("<td>" + b1 + b2 + h1 + "</td>");
		e1.append(td1).append(td2).append(td3).append(td4).append(td5).append(td6).append(td7).append(td8);;
	}
	
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
		
	function obtainInfoFromESB(){
		var url = "${pageContext.request.contextPath}/project/wkReport/weekESBInfo";
		
		//验证
		var form = document.getElementById("form2")
		
		if(isEmpty(form.wrYear.value)){
			form.wrYear.focus();
			win.generalAlert("周报年份不能为空！");
			return false;
		}
		if(isEmpty(form.wrWeek.value)){
			form.wrWeek.focus();
			win.generalAlert("第几周不能为空！");
			return false;
		}
		if(isEmpty(form.Pid.value)){
			win.generalAlert("未关联海建工程ID");
			return false;
		}
		var formData = new FormData($("#form2")[0]);
		//
		$.ajax({  
		     url : url,  
		     type : "POST",  
		     data: formData,
	         async: false,  
	         cache: false,  
	         contentType: false,  
	         processData: false, 
	         dataType:"json",
		     success : function(data) {
		    	console.log(data)
		     	if(!isEmptyObject(data)){
			     	$("textarea[name='wrJdContent']").text(data.thisProgress);
			     	$("textarea[name='wrZlContent']").text(data.thisQuality);
			     	$("textarea[name='wrAqContent']").text(data.thisSHE);
			     	$("textarea[name='wrCgContent']").text(data.thisPurchase);
			     	if(data.startDate){
			     		data.startDate = data.startDate.split("T")[0];//通过接口获取的数据日期和时分秒通过'T'这个符号分隔
			     	}
			     	if(data.endDate){
			     		data.endDate = data.endDate.split("T")[0];
			     	}
			     	$("#wrStartTime").val(data.startDate);
			     	$("#wrEndTime").val(data.endDate);
		     	}else{
		     		win.generalAlert("通过接口未能获取到数据");
		     	}
		     	
		     },  
		     error : function(data) {
		     	win.generalAlert("通过接口未能获取到数据");
		     }  
		}); 
	}
		
	function isEmptyObject(e) {
		var t;
		for (t in e)
			return !1;
		return !0
	}
		    
</script>
</html>