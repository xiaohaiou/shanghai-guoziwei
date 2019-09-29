<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>纳税数据导入页面</title>
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
<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
<style type="text/css">
	.inlineblock
	{
		display:inline-block !important;
	}
	.label_inpt_div {
	    min-height: 290px;
	}
</style>
</head>
<body>
	<h4>
		<span class="glyphicon glyphicon-pencil"></span>纳税数据导入
		<i class="iconfont icon-guanbi"></i>
	</h4>
	<div class="label_inpt_div">
		<form:form id="form2" enctype="multipart/form-data" method="post" modelAttribute="entityview" >
			<form:hidden path="id"/>
			<div class="model_part">
				<label class="w20"><span class="required"> <font color=red>*</font> </span>年月:</label>
				<input  type="text" name="year" value="${entityview.year}" readonly="true" class="w25 time" check="NotEmpty" />
				<label class="w20"><span class="required"> * </span>纳税数据上报单位:</label>
				<span class="w25">
					<input type="hidden" id="organalID" name="org" value="${entityview.org }" >
					<input name="orgName" style="width: 100% !important;" id="company" value="${entityview.orgName }" check="NotEmpty_string_._._._._." readonly>
					<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
						<ul id="com_ztree" class="ztree">
						</ul>
					</div>
				</span>
				<label class="w20"><span class="required"> * </span>其中:海南省纳税额(万元):</label>
				<form:input class="w25" path="hnTax" check="NotEmpty_double_20_6_0+_._."/>
			</div>
			<div class="model_part">	
				<label class="w20"><span class="required"> * </span>纳税数据文件:</label>
				<input type="file" class="w75 inlineblock" style="display:inline" name="excelFile" id="excelFile" >
				<label class="w20">备注:</label>
				<span class="w70 "><textarea rows="5" cols="50" name="remark" id="remark"></textarea></span>
			</div>
			<div class="model_btn">
				<button class="btn btn-primary model_btn_ok" id="commit-1">导入</button>
				<button class="btn btn-primary model_btn_ok" id="commit-2">导入并上报</button>
				<button class="btn btn-default model model_btn_close">关闭</button>
			</div>
		</form:form>
	</div>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>

<script type="text/javascript">
		// 校验
		function commitcheck(){
			if($("#year").val()==""){
				win.errorAlert("年份不能为空");
				return false;
			}
			if($("#hnTax").val()==""){
				win.errorAlert("海南省纳税额不能为空");
				return false;
			}
			var fileDir = $("#excelFile").val();
			var suffix = fileDir.substr(fileDir.lastIndexOf("."));
			if("" == fileDir){
				alert("请选择需要导入的Excel文件！");
				return false;
			}
			if(".xls" != suffix && ".xlsx" != suffix ){
				alert("请选择Excel格式的文件导入！");
				return false;
			}
			return true;
		}
		// 导入
		$("#commit-1").click(function(){
			$.blockUI({ message: '导入中', css: { width: '275px' } });
			if(!vaild.all() || !commitcheck()){
				$.unblockUI();
				return false;
			}
			var url = "${pageContext.request.contextPath}/taxPay/save";
			var formData = new FormData($("#form2")[0]);
			$.ajax({
				url : url,
				type : "POST",
				data: formData,
				cache: false,
				contentType: false,
				processData: false,
				success : function(data) {
					$.unblockUI();
					var commitresult=JSON.parse(data);
					if (commitresult.result == true) {
						win.successAlert('导入成功！',function(){
							parent.location.reload(true);
							parent.mclose();
						});
					} else {
					    if(commitresult.entity==1){
						    win.confirm(commitresult.exceptionStr,function(r){
								if(r){
									var url = "${pageContext.request.contextPath}/taxPay/save?isConfirm=1";
									var formData = new FormData($("#form2")[0]);
									$.ajax({
										url : url,
										type : "POST",
										data: formData,
										cache: false,
										contentType: false,
										processData: false,
										success : function(data) {
											$.unblockUI();
												var commitresult=JSON.parse(data);
												if (commitresult.result == true) {
													win.successAlert('导入成功！',function(){
														parent.location.reload(true);
														parent.mclose();
													});
												} else {
												    win.errorAlert(commitresult.exceptionStr);
												}
								       }
								})
								} 
							});
					    }else{
					         win.errorAlert(commitresult.exceptionStr);
					    }
					 	
						
					}
				},
				error : function(data) {
					$.unblockUI();
				}
			});
			return false;
		});
		// 导入并上报
		$("#commit-2").click(function(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!vaild.all() || !commitcheck()){
				$.unblockUI();
				return false;
			}
			var url = "${pageContext.request.contextPath}/taxPay/saveandreport";
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
					if(commitresult.result==true) {
						win.successAlert('上报成功！',function(){
									parent.location.reload(true);
									parent.mclose();
							});
					} else {
						 if(commitresult.entity==1){
						    win.confirm(commitresult.exceptionStr,function(r){
								if(r){
									var url = "${pageContext.request.contextPath}/taxPay/saveandreport?isConfirm=1";
									var formData = new FormData($("#form2")[0]);
									$.ajax({
										url : url,
										type : "POST",
										data: formData,
										cache: false,
										contentType: false,
										processData: false,
										success : function(data) {
											$.unblockUI();
												var commitresult=JSON.parse(data);
												if (commitresult.result == true) {
													win.successAlert('导入成功！',function(){
														parent.location.reload(true);
														parent.mclose();
													});
												} else {
												    win.errorAlert(commitresult.exceptionStr);
												}
								       }
								})
								} 
							});
					    }else{
					         win.errorAlert(commitresult.exceptionStr);
					    }
					}
				},
				error : function(data) {
					$.unblockUI();
				}
			});
			return false;
		});
		// 组织机构树
		var timeoutId;
		$('#company').on('focus click',function(){
			$(this).next('.com_ztree_box').css('display','block');
		}).parent().on('mouseenter',function(){
			clearTimeout(timeoutId);
		}).on('mouseleave',function(){
			clearTimeout(timeoutId);
			timeoutId = setTimeout(function(el){
				$(el).find('.com_ztree_box').css('display','none');
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
			if(childrenNodes[0]!=null)
				treeObj.expandNode(childrenNodes[0],true);
		});
		$("#com_ztree").click(function(){
			setTimeout(function(){
				if($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0]){
					$("#organalID").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id);
					$("#company").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
					vaild.vaild($("#company")[0]);
					$("#company").attr('title',$.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
				}
			});
		});
		//清空
		$('.clear').on('click',function(){
			$(this).siblings('input[name="orgName"]').val('');
			$("#organalID").val("");
			$("#company").attr('title','');
		});
		// 日期控件
		$('input.time').jeDate({
			format:"YYYY-MM"
		});
		//关闭弹窗
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		
		$(".model_btn_close").click(function () {
			parent.mclose();
			return false;
		});
	</script>
</html>