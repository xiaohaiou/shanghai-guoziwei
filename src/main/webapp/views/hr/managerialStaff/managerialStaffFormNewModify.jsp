<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>资产信息新增、编辑页面</title>
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
<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">
<style>
	.search_title {
	    font-weight:bold;
	}
	#company{
		width:16em;
	}
	@media screen and (max-width: 1630px){
		.clear {
		    right: -16em;
		    width: auto;
		    top: 2px
		}
	}
</style>
</head>
<body>
	<c:choose>
		<c:when test="${op eq 'modify'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>修改资产信息采集数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增资产信息采集数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
	<div class="model_body">
		<form:form id="form2" modelAttribute="entityview" >
		
				<form:hidden path="id"/>
				<div class="label_inpt_div">
					<div class="model_part">
						<label class="w20"><span class="required"> <font color=red>*</font> </span>年份:</label>
						<input  type="text" name="year" value="${entityview.year}" readonly="true" class="w25 time" check="NotEmpty" />
						<label class="w20"><span class="required"> <font color=red>*</font> </span>月份:</label>
						<span class="w25">
							<select  name="month" class="selectpicker" >
								<c:forEach  var="m"  begin="1"    end="12"    step="1">
									<option value="${m}" <c:if test="${m eq entityview.month}">selected</c:if>>${m}</option>
								</c:forEach>
							</select>
						</span>
						<span class="" style="display:block">
							<span class="search_title w20"><font color=red>*</font>单位名称:
								 <!-- <i class="clear iconfont icon-guanbi"></i> -->
							</span>
							<input type="hidden" id="organalId" name="organalId" value="${entityview.organalId }" >
							<input  class="w25" name="company" id="company" value="${entityview.company }" readonly title="${entityview.company }" check="NotEmpty" >
							
							<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
								<ul id="com_ztree" class="ztree">
				
								</ul>
						   </div>
					   </span>
					</div>
				<h3 class="data_title1">管理干部</h3>
				<div class="model_part">
					<label class="w20"><font color=red>*</font>资产信息采集1:</label>
					<form:input class="w25" id="manNumber" path="manNumber" check="NotEmpty_int_7_0_0+_._."/>
					<label class="w20"><font color=red>*</font>资产信息采集2:</label>
					<form:input class="w25" id="womanNumber" path="womanNumber" check="NotEmpty_int_7_0_0+_._."/>
					<label class="w20"><font color=red>*</font>资产信息采集3:</label>
					<form:input class="w25" id="sequenceM" path="sequenceM" check="NotEmpty_int_7_0_0+_._."/>
					<label class="w20"><font color=red>*</font>资产信息采集4:</label>
					<form:input class="w25" id="unSequenceM" path="unSequenceM" check="NotEmpty_int_7_0_0+_._."/>
				</div>
				<div class="model_btn">
					<button class="btn btn-primary model_btn_ok" id="commit_1">保存</button>
					<button class="btn btn-primary model_btn_ok" id="commit_2">保存并上报</button>
					<button class="btn btn-default model model_btn_close">关闭</button>
					
				</div>
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
				if($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0]) {
					 $("#organalId").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id)
					 $("#company").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);vaild.vaild($("#company")[0]);
					  $("#company").attr('title',$.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
				}
		   	});
		});		
		$(".clear").on('click',function(){
			$(this).parent().siblings('input[name="company"]').val('');
			$("organalId").val("");
		})
		
		$(' input.time').jeDate(
			{
				format:"YYYY"
			}
		)

		function checkcommit()
		{
			if($("#year").val()=="") {
				win.errorAlert("年份不能为空");
				return false;
			}
			if($("#company").val()=="") {
				win.errorAlert("单位不能为空");
				return false;
			}
			if((parseInt($("#manNumber").val())+parseInt($("#womanNumber").val()))!=(parseInt($("#sequenceM").val())+parseInt($("#unSequenceM").val()))){
				win.errorAlert("男、女性管理干部人数总和等于M、非M序列管理干部人数总和");
				return false;
			}
			return true;
		}
				
		$("#commit_1").click(function(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!vaild.all() || !checkcommit())
			{
					$.unblockUI();
					return false;
			}
					//var entityBasicInfo = $('#form2').serialize();
			var op = '${requestScope.op}';
			var url="${pageContext.request.contextPath}/managerialStaff/saveOrUpdate";
			if(op=='modify'){url="${pageContext.request.contextPath}/managerialStaff/update";}
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
						if(commitresult.result==true)
							win.successAlert('保存成功！',function(){
								parent.location.reload(true);
								parent.mclose();
							});
						else
							win.errorAlert(commitresult.exceptionStr);
			     },  
			     error : function(data) {
			     	$.unblockUI();
			     }  
			}); 
				

			return false;
		})
		$("#commit_2").click(function(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!vaild.all() || !checkcommit())
			{
					$.unblockUI();
					return false;
			}
					//var entityBasicInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/managerialStaff/saveandreport";
			
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
						if(commitresult.result==true)
							win.successAlert('上报成功！',function(){
									parent.location.reload(true);
									parent.mclose();
							});
						else
							win.errorAlert(commitresult.exceptionStr);
			     },  
			     error : function(data) {
			     	$.unblockUI();
			     }  
			}); 
				

			return false;
		})

	
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
	</script>
</html>