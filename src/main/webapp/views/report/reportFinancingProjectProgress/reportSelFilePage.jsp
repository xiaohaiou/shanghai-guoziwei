<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>导入新加入的融资项目数据</title>
<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
<!-- 库|插件 -->
<!-- 新加样式 -->
<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
<link rel="stylesheet" href="<c:url value="/css/workbench_model.css"/>">
<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">
		<style type="text/css">
			.selectone{
				white-space: nowrap;
				font-size: 14px;
				cursor: pointer;
			}
			.selectone:hover, .selectone.selectedOne{
				box-sizing: border-box;
				background-color: rgba(255,255,255,0.8);
				border-bottom:1px solid rgba(0,0,0,0.5);
			}
			.selectone>* {
				display:inline-block;
				min-width: 60px;
			}
			.selectedOne{
				border-color:#4a22cc;
				color: #4a22cc;
			}
			#com_ztree span {
				padding-left:0;
			}
			.tablebox table tr:first-child {
			    background: rgba(0, 0, 0, 0);
			}
			.tablebox table tr:first-child:hover {
			    background: #f2f8ff;
			}
		</style>
</head>
<body>
		
	<h4>
		<span class="glyphicon glyphicon-pencil"></span>导入新加入的融资项目数据
		<i class="iconfont icon-guanbi"></i>
	</h4>
		
	<div class="label_inpt_div">
		<form:form id="fileForm" modelAttribute="entityview" >
			<form:hidden path="id"/>
			<div class="model_part">
			<!-- <label class="w20"><span class="required"> * </span>单位名称:</label>
				<span class="w25">
					<select  name="coreOrg" id="coreOrg" class="selectpicker" onchange="getCoreOrgname()">
						<option value=""  ></option>
						<c:forEach items="${requestScope.coreCompSelect}" var="result">
							<option value="${result.id.nnodeId }"  <c:if test="${entityview.coreOrg eq result.id.nnodeId}">selected="selected"</c:if>>${result.vcFullName }</option>
						</c:forEach>
					</select>
				</span> -->
				<label class="w20"><span class="required"> * </span>单位名称:</label>
				<span class="w25"">
					<input type="hidden" name="groupid" id="groupid" value="${groupid}">
					<input type="hidden" name="grouptype" id="grouptype" value="${grouptype}">
					<input type="hidden" id="operationType" value="${op}"> 
					<input name="checkedCompanyName" id="checkedCompanyName" value="${checkedCompanyName}" with="200px" readonly="readonly"/>
					<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
						<ul id="com_ztree" class="ztree">
		
						</ul>
				   </div>
				   <input type="hidden" id="organalID" name="organalID" value="${organalID}" >
				</span>
				
				
				
				
				<form:hidden path="coreOrgname" id="coreOrgname"/>
				<label class="w20"></label>
				<label class="w25 setleft"></label> 
				<label class="w20"><span class="required"> * </span>新加入融资项目:</label>
				<input id="czlrExcelImportId" type="file" name="excelFile"/><!--accept="*"  style="display:none;"   onchange="changeFiles()" -->
				<input type="hidden" id="fileTypeId" name="fileType" value="0"/>
				<input type="hidden" id="excelNameId" name="excelName" value="0"/>
			</div>
			<div class="model_btn">
				<button class="btn btn-primary model_btn_ok" id="commit-1">导入</button>
				<button class="btn btn-default model model_btn_close">取消</button>
			</div>
		</form:form>
	</div>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>
<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/listPageCompanyTreeSelect.js"/>" type="text/javascript"></script>

<script type="text/javascript">

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
	    
		    if($("#operationType").val()=="new")
			{   
			
				$(' input.time').jeDate(
					{
						format:"YYYY-MM",
						choosefun:function(elem, val, date) {changedate(val)},
						clearfun:function(elem, val) {changedate("")},            //清除日期后的回调, elem当前输入框ID, val当前选择的值
			  				okfun:function(elem, val, date) {}, 
					}
				)
				var checked_data = '${organalID}';
				var com_data = ${allCompanyTree};
				initFinanceTree(checked_data,com_data);
			
			}
			else
			{
			
				$("#checkedCompanyName").attr("readOnly","true");
			}
	    	
	    	
	    });
	    
	    /**
	     * 初始化财务树	
	     */
	    function initFinanceTree(checked_data,com_data){
			zTreeObj = $.fn.zTree.init($("#com_ztree"), com_ztree_setting, com_data);
	    	//将每一个tree节点进行遍历，获取Id，与此角色已保存的Id（checked_data）进行比较，Id相同择设为选中
	    	var treeObj = $.fn.zTree.getZTreeObj("com_ztree");
			var nodes = treeObj.getNodes();
			
			//transformToArray()此方法获取所有节点（父节点和子节点）
    		var childrenNodes = treeObj.transformToArray(nodes);
    		if(childrenNodes[0]!=null)
    			treeObj.expandNode(childrenNodes[0],true);
	    	for(var i=0;i<childrenNodes.length;i++){
	    		for(var j=0;j<checked_data.length;j++){
	    			if(childrenNodes[i].id == checked_data[j].id){
	    				treeObj.expandNode(childrenNodes[i], true); //展开选中的  
                    	treeObj.checkNode(childrenNodes[i], true);
                    	//treeObj.updateNode(childrenNodes[i]);
	    			}
	    		}
	    	}
	    }

		$("#com_ztree").click(function(){
				setTimeout(function(){
	   				if($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0])
						{
							 $("#organalID").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id)
							 $("#checkedCompanyName").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
							 if(oldorgan!=$("#organalID").val())
							 	initNodeList("",1);
							 oldorgan=$("#organalID").val();
						}
			   	});
			
		})
		
		var timeoutId
 		$('#operateOrgname').on('focus click',function(){
 			$(this).next('.com_ztree_box').css('display','block')
 		}).parent().on('mouseenter',function(){
 			clearTimeout(timeoutId)
 		}).on('mouseleave',function(){
 			clearTimeout(timeoutId)
 			timeoutId = setTimeout(function(el){
 				$(el).find('.com_ztree_box').css('display','none')
 			},3e2,this);
 		})
 		var timeoutId1
 		$('#financingOrgname').on('focus click',function(){
 			$(this).next('.com_ztree_box').css('display','block')
 		}).parent().on('mouseenter',function(){
 			clearTimeout(timeoutId1)
 		}).on('mouseleave',function(){
 			clearTimeout(timeoutId1)
 			timeoutId1 = setTimeout(function(el){
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
	    	var zTreeObj = $.fn.zTree.init($("#operate_com_ztree"), com_ztree_setting, com_data);
	    	//将每一个tree节点进行遍历，获取Id，与此角色已保存的Id（checked_data）进行比较，Id相同择设为选中
	    	var treeObj = $.fn.zTree.getZTreeObj("operate_com_ztree");
			var nodes = treeObj.getNodes();
			
			//transformToArray()此方法获取所有节点（父节点和子节点）
    		var childrenNodes = treeObj.transformToArray(nodes);
    		if(childrenNodes[0]!=null)
    			treeObj.expandNode(childrenNodes[0],true);
	    });
	    $(function () {
		     //所有企业数据	
		    var com_data = ${allCompanyTree};
			//加载列表公司选择
	   		var zTreeObj1 = $.fn.zTree.init($("#financing_com_ztree"), com_ztree_setting, com_data);
	    	//将每一个tree节点进行遍历，获取Id，与此角色已保存的Id（checked_data）进行比较，Id相同择设为选中
	    	var treeObj1 = $.fn.zTree.getZTreeObj("financing_com_ztree");
			var nodes1 = treeObj1.getNodes();
			//transformToArray()此方法获取所有节点（父节点和子节点）
	   		var childrenNodes1 = treeObj1.transformToArray(nodes1);
	   		if(childrenNodes1[0]!=null){
	   			treeObj1.expandNode(childrenNodes1[0],true);
   			}
   		});
	
		$(' input.date').jeDate(
			{
				format:"YYYY-MM-DD"
			}
		)
	
		$("#operate_com_ztree").click(function(){
				setTimeout(function(){
	   				if($.fn.zTree.getZTreeObj("operate_com_ztree").getSelectedNodes()[0])
						{
							 $("#operateOrg").val($.fn.zTree.getZTreeObj("operate_com_ztree").getSelectedNodes()[0].id)
							 $("#operateOrgname").val($.fn.zTree.getZTreeObj("operate_com_ztree").getSelectedNodes()[0].name);
						}
			   	});
			
		})
		$("#financing_com_ztree").click(function(){
			setTimeout(function(){
  				if($.fn.zTree.getZTreeObj("financing_com_ztree").getSelectedNodes()[0])
				{
					 $("#financingOrg").val($.fn.zTree.getZTreeObj("financing_com_ztree").getSelectedNodes()[0].id)
					 $("#financingOrgname").val($.fn.zTree.getZTreeObj("financing_com_ztree").getSelectedNodes()[0].name);
				}
		   	});
			
		})
		
		function getCoreOrgname()
		{
			$("#coreOrgname").val($("#coreOrg").find("option:selected").text());
		}

		function commitcheck()
		{
			if($("#coreOrg").val()=="")
			{
				win.errorAlert("请选择业态公司");
				return false;
			}
			return true;
		}
			
			
			
				
		$("#commit-1").click(function(){
		
			var url = '${pageContext.request.contextPath}/reportFinancingProjectProgress/excelReport';
			
			$("#excelNameId").val($("#czlrExcelImportId").val().split(".xl")[1]);//sx&&s
			if(fileChange($("#czlrExcelImportId")[0])==false){
				 $("#czlrExcelImportId").val("");
				$.unblockUI();
				return false;
			}
			var formData = new FormData($("#fileForm")[0]);
		   	//异步文件上传
		    $.ajax({  
			     url : url,  
			     type : "POST",  
			     data: formData,
		         cache: false,  
		         contentType: false,  
		         async:false,
		         processData: false,  
			     success : function(data) {			     
			     	var commitresult=JSON.parse(data);
					 $.each(commitresult, function (index, item) {  
		                 //循环获取数据    
			     		 alert(item);  
		             });
			     },  
			     error : function(data) {
			     	alert("导入数据失败");
			     }, 
			}); 
		});
	
	 
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		
		//关闭弹窗
		$(".model_btn_close").click(function () {
			parent.mclose();
			return false;
		});
		
		//file校验
		//验证文件类型和大小
		function fileChange(target){
			//检测上传文件的类型 
			var imgName = target.value;
		    var ext,idx;   
		    if (imgName == ''|| imgName == undefined){  
		        win.generalAlert("请选择正确的文件上传!");  
		        return false; 
		    } else {   
		    
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
		    idx = imgName.lastIndexOf(".");   
		        if (idx != -1){   
		            ext = imgName.substr(idx+1).toUpperCase();   
		            ext = ext.toLowerCase( ); 
		           // alert("ext="+ext);
		            if (ext != 'xls' && ext != 'xlsx'){
		                win.generalAlert("只能上传.xls类型或者.xlsx类型的Excel文件!"); 
		                return false;  
		            }
		            return true;    
		        } else {  
			           win.generalAlert("只能上传.xls类型或者.xlsx类型的Excel文件!");
			            return false;
		        }      
		    }
		}
		
	</script>
</html>