<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>导入新加入的创新类融资项目数据</title>
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
		<span class="glyphicon glyphicon-pencil"></span>导入新加入的创新类融资项目数据
		<i class="iconfont icon-guanbi"></i>
	</h4>
		
	<div class="label_inpt_div">
		<form:form id="fileForm" modelAttribute="entityview" >
			<form:hidden path="id"/>
			<div class="model_part">
				<label class="w20"><span class="required"> * </span>单位名称:</label>
				<span class="w25">
					<select  name="coreOrg" id="coreOrg" class="selectpicker" onchange="getCoreOrgname()">
						<option value=""  ></option>
						<c:forEach items="${requestScope.coreCompSelect}" var="result">
							<option value="${result.id.nnodeId }"  <c:if test="${entityview.coreOrg eq result.id.nnodeId}">selected="selected"</c:if>>${result.vcFullName }</option>
						</c:forEach>
					</select>
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

<script type="text/javascript">

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
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!vaild.all() || !commitcheck())
			{
					$.unblockUI();
					return false;
			}
					//var entityBasicInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/reportFinancingInnovate/excelReport";
			
			$("#excelNameId").val($("#czlrExcelImportId").val().split(".xl")[1]);//sx&&s
			fileChange($("#excelNameId"));
			var formData = new FormData($("#fileForm")[0]);
		   	//var file = $("#czlrExcelImportId").get(0).files[0];
		   	//异步文件上传
		    $.ajax({  
			     url : url,  
			     type : "POST",  
			     data: formData,
		         cache: false,  
		         contentType: false,  
		         processData: false,  
			     success : function(data) {
			     	$.unblockUI();
			    		console.info(JSON.parse(data));	
			    		/* var dataArray = JSON.parse(data);
			    		loadExcelData(dataArray); */
			     },  
			     error : function(data) {
			     	$.unblockUI();
			     }  
			}); 
		});
	
		$("#operate_com_ztree").click(function(){
			setTimeout(function(){
   				if($.fn.zTree.getZTreeObj("operate_com_ztree").getSelectedNodes()[0])
					{
						 $("#operateOrg").val($.fn.zTree.getZTreeObj("operate_com_ztree").getSelectedNodes()[0].id)
						 $("#operateOrgname").val($.fn.zTree.getZTreeObj("operate_com_ztree").getSelectedNodes()[0].name);
					}
		   	});
		})
	 
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
	            /* if (indexOf(imgName,'xls')==0){
	                win.generalAlert("只能上传.xls类型或者.xlsx类型的Excel文件!"); 
	                return false;  
	            } */
	            return true;    
		    }
		}
		
	</script>
</html>