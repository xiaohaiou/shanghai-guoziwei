<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>知识库新增、编辑页面</title>
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
	<style type="text/css">
		#com_ztree span {
			padding-left:0;
		}
	</style>
</head>
<body>
	<c:choose>
		<c:when test="${op eq 'modify'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>修改知识库
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增知识库
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
	<form:form id="form2" modelAttribute="knowledgeStoreHouse" method="post">
		<form:hidden path="id"/>
		<form:hidden path="createPersonName"/>
		<form:hidden path="createPersonId"/>
		<form:hidden path="createDate"/>
		<div class="label_inpt_div">
			<div class="model_part">
				<label class="w20"><span class="required"> * </span>文件号:</label>
				<input class="w25" type="text" name="documentNo" id="documentNo" maxlength="50" placeholder="请输入文件号,50字符以内" value="${knowledgeStoreHouse.documentNo}"/>
				<label class="w20"><span class="required"> * </span>文件名:</label>
				<input class="w25" type="text" name="documentName" id="documentName" maxlength="50" placeholder="请输入文件名,50字符以内" value="${knowledgeStoreHouse.documentName}"/><br>
				<label class="w20"><span class="required"> * </span>机构:</label>
				<span class="w25"> 
					<input type="hidden" id="organizationId" name="organizationId" value="${knowledgeStoreHouse.organizationId}" >
					<input name="organizationName" id="organizationName" value="${knowledgeStoreHouse.organizationName}" placeholder="请输入机构" readonly="readonly">
					<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
						<ul id="com_ztree" class="ztree">
						</ul>
				   </div>
			    </span>
				<label class="w20"><span class="required"> * </span>模块:</label>
				<select name="moduleId" id="moduleId" class="selectpicker w25" >
					<c:forEach items="${requestScope.moduleId}" var="result">
							<option value="${result.id}"  <c:if test="${knowledgeStoreHouse.moduleId == result.id}">selected="selected"</c:if>>${result.description}</option>
						</c:forEach>
				</select>
				<label class="w20"><span class="required"> * </span>时间:</label>
				<input class="w25 time" type="text" name="year" id="year" maxlength="50" placeholder="请输入时间" value="${knowledgeStoreHouse.year}" readonly="readonly"/><br>
				<label class="w20"><span class="required"> * </span>附件上传:</label>
				<input class="w25" type="file" id="pjFile" name="pjFile" />
				<span><a href="${pageContext.request.contextPath}/common/download?_url=${file_Path}&_name=${file_Name}">${file_Name==null ? "" :file_Name}</a></span>
			</div>
			<div class="row model_btn">
				<button class="btn btn-primary model_btn_ok" id="commit-1" >保存</button>
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
		$('#organizationName').on('focus click',function(){
			$(this).next('.com_ztree_box').css('display','block')
		}).parent().on('mouseenter',function(){
			clearTimeout(timeoutId)
		}).on('mouseleave',function(){
			clearTimeout(timeoutId)
			timeoutId = setTimeout(function(el){
				$(el).find('.com_ztree_box').css('display','none')
			},3e2,this);
		});
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
			if(childrenNodes[0]!=null){
				treeObj.expandNode(childrenNodes[0],true);
			}
		});
		$("#com_ztree").click(function(){
			setTimeout(function(){
				if($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0]) {
					$("#organizationId").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id)
					$("#organizationName").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
				}
		   	});
		});
		$('input.time').jeDate({
			format:"YYYY"
		});
		function checkcommit() {
			if($("#documentNo").val()=="") {
				win.errorAlert("文件号不能为空");
				return false;
			}
			if($("#documentName").val()=="") {
				win.errorAlert("文件名不能为空");
				return false;
			}
			if($("#organizationName").val()=="") {
				win.errorAlert("机构不能为空");
				return false;
			}
			if($("#moduleId").val()=="") {
				win.errorAlert("模块不能为空");
				return false;
			}
			if($("#year").val()=="") {
				win.errorAlert("时间不能为空");
				return false;
			}
			var tag = $("#pjFile")[0];
			if(fileChange(tag)){
				return true;
			}else{
				return false;
			}
			return true;
		};	
		$("#commit-1").click(function(){
			var op = '${op}';
			if(op == 'add'){
				if(!checkcommit() || !vaild.all()) {
					return false;
				}
			}
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			var formData = new FormData($("#form2")[0]);
			var url = "${pageContext.request.contextPath}/knowledgeStoreHouse/saveOrUpdate";
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
			     if(data == "success"){
					win.successAlert('保存成功！',function(){
						window.open("${pageContext.request.contextPath}/knowledgeStoreHouse/list?type=${type}", "mainFrame");
					});
				}else{
					win.errorAlert('该数据已被删除！',function(){
						parent.location.reload();
						parent.mclose();
					});
				}
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
		//验证文件类型和大小
		function fileChange(target){  
			//检测上传文件的类型 
			var imgName = document.all.pjFile.value;
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
		};
	</script>
</html>