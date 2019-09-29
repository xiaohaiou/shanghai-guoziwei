<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>合同台帐数据导入</title>
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
	<h4>
		<span class="glyphicon glyphicon-pencil"></span>合同台帐数据导入
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<form:form id="form2"   method="post" enctype="multipart/form-data">	
		<div class="label_inpt_div">
			<div class="model_part">
			<label class="w20"><span class="required"> * </span>单位名称:</label>
				<span  class="w25"> 
					<input name="compName" id="compName" value="${compName }" check="NotEmpty_string_._._._._." readonly>
					<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
						<ul id="com_ztree" class="ztree">
		
						</ul>
				   </div>
			    </span>
				<input type="hidden" name="compId" id="compId" value="${compId}" />
				<label class="w20"><span class="required"> * </span>年份:</label>
				<input class="w25 time" type="text" name="year" id="year" value="${year}" check="NotEmpty_string_._._._._." readonly="true" class="time" />
				<label class="w20"><span class="required"> * </span>月份:</label>
				<span class="w25">
					<select  name="month" id="month" class="selectpicker"  >
						<c:forEach var= "temp" begin= "1" step= "1" end= "12">
							<option value="${temp}" <c:if test="${month == temp}">selected="selected"</c:if>>
								${temp}月
							</option>
						</c:forEach>
					</select>
				</span>
				<label class="w20"><span class="required"> * </span>附件:</label>
				<input class="w25" type="file" name="updatafile" id="updatafile"  />
				<input type="hidden" id ="hid_status" name="status" value="${status}">
			</div>
			<div class="model_btn">
				<button class="btn btn-primary model_btn_ok" id="but_save" >保存</button>
				<button class="btn btn-primary model_btn_ok" id="but_save_apply" >保存并上报</button>
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
<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
<script type="text/javascript">
		var timeoutId
 		$('#compName').on('focus click',function(){
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
	   		    if($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0]){
					$("#compId").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id)
					$("#compName").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
				}
			});
		})
		
		$('input.time').jeDate({
			format:"YYYY"
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
		
		function validateUpdataFile(){
			if( !$("#updatafile").val()){
				win.errorAlert("附件不能为空！");
				return false;
			}
			return true;
		}
		
		function submitForm(status){
			if(!vaild.all() || !validateUpdataFile()){
				return false;
			}
			var file=$("#updatafile")[0];
			if(!fileChange(file)){
			   return false;
			}	
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			
			$('#hid_status').val(status);
			var formData = new FormData($("#form2")[0]);
			var url =  "${pageContext.request.contextPath}/bimr/contractmonth/save";
			$.ajax({  
			     url : url,  
			     type : "POST",  
			     data: formData,
		         async: true,  
		         cache: false,  
		         contentType: false,  
		         processData: false,  
			     success : function(data) {
			    	 $.unblockUI();
			    	 if(data.result){
						win.successAlert('保存成功！',function(){
							parent.location.reload(true);
							parent.mclose();
						});
			    	 }else{
			    		  win.errorAlert(data.exceptionStr);
			    	 }
			     },  
			     error : function(data) {
			     	$.unblockUI();
			     	 win.errorAlert("服务器异常！");
			     }  
			}); 
        	return false;
		}
		
		$("#but_save").click(function(){
			submitForm('50112');
			return false;
		});
		
		$("#but_save_apply").click(function(){
			submitForm('50113');
        	return false;
		});
		//验证文件类型和大小
		function fileChange(target){  
			//检测上传文件的类型 
			var imgName = document.all.updatafile.value;
		    if(imgName != ''){
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
			
			    if(size>(1024*1024*3)){ 
			    	 win.errorAlert("上传文件不能大于3M!"); 
			      	return false; 
			    }
			    return true;
		    
		    }else{
		    		return true;
		    } 
		}     
	</script>
</html>