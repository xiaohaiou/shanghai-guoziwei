<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>合规培训新增、编辑及保存上报页面</title>
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
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">
	<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
</head>
<body>
	<c:choose>
		<c:when test="${op eq 'modify'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>修改合规培训信息
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增合规培训信息
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
	<form:form id="form2" modelAttribute="bimrTrain" method="post" enctype="multipart/form-data">
		<form:hidden path="id"/>
		<form:hidden path="createPersonName"/>
		<form:hidden path="createPersonId"/>
		<form:hidden path="createDate"/>
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20"><span class="required"> * </span>单位名称:</label>
				<span  class="w25"> 
					<input name="companyName" id="companyName" value="${bimrTrain.companyName }" check="NotEmpty_string_._._._._." readonly>
					<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
						<ul id="com_ztree" class="ztree">
		
						</ul>
				   </div>
			    </span>
				<input type="hidden" name="companyId" id="companyId" value="${bimrTrain.companyId}" /><br>
				
				<label class="w20"><span class="required"></span>被培训人姓名:</label>
				<input class="w25" type="text" name="beTrainPerson" id="beTrainPerson" placeholder="请输入被培训人姓名" maxlength="50" value="${bimrTrain.beTrainPerson}" check="Empty_string_50_._._._."/>
				<label class="w20"><span class="required"> * </span>培训名称:</label>
				<input class="w25" type="text" name="trainName" id="trainName" placeholder="不超过200个字符" maxlength="200" value="${bimrTrain.trainName}" check="NotEmpty_string_200_._._._."/><br>
				
				<label class="w20"><span class="required"> * </span>培训日期:</label>
				<input type="text" name="trainDate" id="trainDate" value="${bimrTrain.trainDate}" readonly="readonly" class="w25 time"/>
				<label class="w20"><span class="required"> * </span>授课人:</label>
				<input class="w25" type="text" name="lecturer" id="lecturer" placeholder="请输入授课人姓名" maxlength="50" value="${bimrTrain.lecturer}"/><br>
				
				<label class="w20"><span class="required">  </span>是否内部授课人:</label>
				<select class="w25" name="isInside" id="isInside"  value="${bimrTrain.isInside}"> 
				  <option value="1" <c:if test="${'1' eq bimrTrain.isInside}">selected</c:if> >是</option>       
				  <option value="0" <c:if test="${'0' eq bimrTrain.isInside}">selected</c:if> >否</option>       
				</select>
				<label class="w20"><span class="required"> * </span>授课人所在单位:</label>
				<input class="w25" type="text" name="lecturerCompany" id="lecturerCompany" placeholder="不超过200个字符" maxlength="200" value="${bimrTrain.lecturerCompany}" check="NotEmpty_string_200_._._._."/><br>
				
				<label class="w20"><span class="required">  </span>授课人职位:</label>
				<input class="w25" type="text" name="lecturerJob" id="lecturerJob" placeholder="不超过50个字符" maxlength="50" value="${bimrTrain.lecturerJob}" check="Empty_string_50_._._._."/>
				
				<label class="w20"><span class="required">  </span>授课方式:</label>
				<select class="w25" name="lecturerMode" id="lecturerMode"  value="${bimrTrain.lecturerMode}"> 
				  <option value="1" <c:if test="${'1' eq bimrTrain.lecturerMode}">selected</c:if> >现场</option>       
				  <option value="2" <c:if test="${'2' eq bimrTrain.lecturerMode}">selected</c:if> >现场+视频</option> 
				  <option value="3" <c:if test="${'3' eq bimrTrain.lecturerMode}">selected</c:if> >视频</option>      
				</select>
				<label class="w20"><span class="required"> * </span>培训课时:</label>
				<input class="w25" type="text" name="lecturerHour" id="lecturerHour" placeholder="" maxlength="50" value="${bimrTrain.lecturerHour}" check="NotEmpty_int_4_0_+_._."/><br>
				
				<label class="w20"><span class="required">  </span>培训反馈情况:</label>
				<select class="w25" name="feedback" id="feedback"  value="${bimrTrain.feedback}"> 
				  <option value="1" <c:if test="${'1' eq bimrTrain.feedback}">selected</c:if> >满意</option>       
				  <option value="2" <c:if test="${'2' eq bimrTrain.feedback}">selected</c:if> >不满意</option> 
				</select>
				<label class="w20"><span class="required">  </span>培训地点:</label>
				<select class="w25" name="address" id="address"  value="${bimrTrain.address}"> 
				  <option value="1" <c:if test="${'1' eq bimrTrain.address}">selected</c:if> >境内</option>       
				  <option value="2" <c:if test="${'2' eq bimrTrain.address}">selected</c:if> >境外</option> 
				</select><br>
				
				<label class="w20"><span class="required">  </span>备注:</label>
				<textarea class="w70" rows="3" cols="5" type="text" name="remarks" id="remarks" placeholder="不超过500个字符" maxlength="500" class="w25" check="Empty_string_500_._._._.">${bimrTrain.remarks}</textarea><br>
				
				<label class="w20"><span class="required">  </span>培训资料附件:</label>
				<input class="w25" type="file" id="file1" name="file1" />
				<span><a href="${pageContext.request.contextPath}/common/download?_url=${file_Path1}&_name=${file_Name1}">${file_Name1==null ? "" :file_Name1}</a></span><br>
				
				<label class="w20"><span class="required">  </span>培训人员名单导入:</label>
				<input class="w25" type="file" id="file2" name="file2" />
				<span><a href="${pageContext.request.contextPath}/common/download?_url=${file_Path2}&_name=${file_Name2}">${file_Name2==null ? "" :file_Name2}</a></span>
				
			</div>
			<div class="row model_btn">
				<button class="btn btn-primary model_btn_ok" id="commit-1" >保存</button>
				<button class="btn btn-primary model_btn_ok" id="commit-2" >保存并上报</button>
				<button class="btn btn-default model model_btn_close">取消</button>
			</div>
		</div>
	</form:form>
</body>
<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>
<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
<script type="text/javascript">	
		var timeoutId;
		$('#companyName').on('focus click',function(){
 			$(this).next('.com_ztree_box').css('display','block')
 		}).parent().on('mouseenter',function(){
 			clearTimeout(timeoutId)
 		}).on('mouseleave',function(){
 			clearTimeout(timeoutId)
 			timeoutId = setTimeout(function(el){
 				$(el).find('.com_ztree_box').css('display','none')
 			},3e2,this);
 		})
	
		var zTreeObj;
	    var com_ztree_setting = {
			check:{
				enable:false,
				chkStyle: "checkbox",
				chkboxType: { "Y": "ps", "N": "ps" }
			},
			data:{
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "pId",
					rootPId: 0
				}
			}
	    };
	    
	    $(function () {
		     //所有企业数据	
		    var com_data = ${allCompanyTree};
	    	zTreeObj = $.fn.zTree.init($("#com_ztree"), com_ztree_setting, com_data);
	    	//将每一个tree节点进行遍历，获取Id，与此角色已保存的Id（checked_data）进行比较，Id相同择设为选中
	    	var treeObj = $.fn.zTree.getZTreeObj("com_ztree");
			var nodes = treeObj.getNodes();
			
			//transformToArray()此方法获取所有节点（父节点和子节点）
    		var childrenNodes = treeObj.transformToArray(nodes);
    		if(childrenNodes[0]!=null)
    			treeObj.expandNode(childrenNodes[0],true);
	    });
	
		$("#com_ztree").click(function(){
				setTimeout(function(){
	   				if($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0])
						{
							 $("#companyId").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id)
							 $("#companyName").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
						}
			   	});
			
		})
		
		$('input.time').jeDate({
			format:"YYYY-MM-DD"
		});
		
		function checkcommit() {
			if($("#companyId").val()=="") {
				win.errorAlert("单位名称不能为空");
				return false;
			}
			if($("#beTrainPerson").val()=="") {
				win.errorAlert("被培训人姓名不能为空");
				return false;
			}
			if($("#trainName").val()=="") {
				win.errorAlert("培训名称不能为空");
				return false;
			}
			if($("#trainDate").val()=="") {
				win.errorAlert("培训日期不能为空");
				return false;
			}
			if($("#lecturer").val()=="") {
				win.errorAlert("授课人不能为空");
				return false;
			}
			if($("#lecturerCompany").val()=="") {
				win.errorAlert("授课人所在单位不能为空");
				return false;
			}
			if($("#trainObject").val()=="") {
				win.errorAlert("培训对象不能为空");
				return false;
			}
			if($("#lecturerHour").val()=="") {
				win.errorAlert("培训课时不能为空");
				return false;
			}
			var file1 = $("#file1")[0];
			if(fileChange(file1)){
				return true;
			}else{
				return false;
			}
			var file2 = $("#file2")[0];
			if(fileChange(file2)){
				return true;
			}else{
				return false;
			}
			return true;
		};
		
		
		$("#commit-1").click(function(){
			if(!checkcommit() || !vaild.all()) {
				return false;
			}
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			var formData = new FormData($("#form2")[0]);
			var url = "${pageContext.request.contextPath}/bimr/train/saveOrUpdate";
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
						parent.location.reload(true);
						parent.mclose();
				 });
			     },  
			     error : function(data) {
			     	$.unblockUI();
			     }  
			});
			return false;
		});
		
		$("#commit-2").click(function(){
			if(!checkcommit()) {
				return false;
			}
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			var formData = new FormData($("#form2")[0]);
			var url = "${pageContext.request.contextPath}/bimr/train/saveOrUpdateAndReported";
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
			     if(data=='success'){
			      win.successAlert('保存成功！',function(){
						parent.location.reload(true);
						parent.mclose();
				 });
			     }else{
			     	win.successAlert('该数据已经被上报，不能再执行当前操作',function(){
						parent.location.reload();
						parent.mclose();
					});
			     }
				
			     },  
			     error : function(data) {
			     
					$.unblockUI();
			     }  
			});
			return false;
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
		$(document).ready(function() {
			var isDefault = $("#isDefault").val();
			if(isDefault == 1){
				$("#wyInfo").show();
			}else{
				$("#wyInfo").hide();
			}
		});
		//验证文件类型和大小
		function fileChange(target){ 
		    //检测上传文件的大小        
		    var isIE = /msie/i.test(navigator.userAgent) && !window.opera;  
		    var fileSize = 0;
		    if(!target.files){
			    if (isIE){       
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
			}
		    return true;
		};
	</script>
</html>