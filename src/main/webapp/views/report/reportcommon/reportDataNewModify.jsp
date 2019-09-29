<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>报表上传页面</title>
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
<style>
.inlineblock
{
	display:inline-block !important;
}

</style>

</head>

<body>

	<c:choose>
		<c:when test="${op eq 'modify'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>修改报表数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增报表数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
	</c:choose>
		<input type="hidden" id="operationType" value="${op }"> 

	<div class="label_inpt_div">
		<form id="form2">
			
			<div class="model_part">
				<input type="hidden" name="groupid" id="groupid" value="${groupid}">
				<input type="hidden" name="grouptype" id="grouptype" value="${grouptype}">
				<input type="hidden" name="type" id="type" value="${type}">
				<label class="w40">机构名称:</label>
				<span  class="w45"> 
					<input name="checkedCompanyName" id="checkedCompanyName" value="${checkedCompanyName }" readonly="readonly">
					<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
						<ul id="com_ztree" class="ztree">
		
						</ul>
				   </div>
			   </span>
			   <input type="hidden" id="organalID" name="organalID" value="${organalID }" >
			  
			
				<label class="w40">时间:</label>
				<span class="w45">
					<input  type="text" id="Date" name="Date" value="${Date}" readonly="true" class="time"/>
				</span>
				<div id="formlist">
				
				</div>
			
			<input type="hidden" name="fdsfsdfsdfs"> 
			</div>
	   </form>
			
			<c:if test="${ !empty entityExamineviewlist  }">
			<h3 class="data_title1">审核意见</h3>
			<div class="model_part">
			<c:forEach items="${ requestScope.entityExamineviewlist}" var="sys" varStatus="status">
			
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
				
			
			</c:forEach>
			</div>
			</c:if>
			
			
			<div class="model_btn">
				<button class="btn btn-primary model_btn_ok" id="commit-1">保存</button>
				
				<button class="btn btn-default model model_btn_close">关闭</button>
				
			</div>
	</div>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>

<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>

 <script type="text/javascript">
   		var timeoutId
   		$('#checkedCompanyName').on('focus click',function(){
   			
   		if($("#operationType").val()!="modify")
	   		$(this).next('.com_ztree_box').css('display','block')
   		}).parent().on('mouseenter',function(){
   			clearTimeout(timeoutId)
   		}).on('mouseleave',function(){
   			clearTimeout(timeoutId)
   			timeoutId = setTimeout(function(el){
   				$(el).find('.com_ztree_box').css('display','none')
   			},3e2,this);
   		})
		
	    function createFormsFile(newDate)
	    {
	   		 $("#formlist").empty();
				
				if(newDate!="")
				{	
					$.post("/shanghai-gzw/reportcommon/changegroup",{organalID:$("#organalID").val(),groupid:$("#groupid").val(),grouptype:$("#grouptype").val(),Date:$("#Date").val(),type:${type}},function(data){
					  				
					  		var commitresult;
					  		if(data!="")
					  		{
					  			commitresult=JSON.parse(data);
								var str="";
								$.each(commitresult,function(index,item){
									str+="<div  >";
									str+="<label style=\"text-align: right;\" class=\"w40\">"+item.formkindName+"&nbsp;"+item.freName +":</label>";
									str+="<input type=\"file\" class=\"w45 inlineblock\"  name=\"file\" _fretype=\""+item.fre+"\">";
									str+="<input type=\"hidden\" name=\"fileformsID\" value=\""+item.id+"\">";
									console.log(item.isUpload);
									console.log(item.isUpload == 1);
									console.log(item.isUpload == 1);
									if(item.isUpload == 1){
									 str += "<span>已上传</span>";
									}
									str+="</div>";
								})
								$("#formlist").append(str);
							}
					});
				}
	    
	    }
		
		
		//用作时间比较
		var oldDate='${Date}';
		
		//报表组的变化
		function changeDate(date){
			var newDate=date;
			
			if(oldDate!=newDate)
			{
				
				createFormsFile(newDate);
			}
			
			oldDate=newDate;
		}
		
		//校验是不是xls文件
		function CheckWorkFile(obj) 
		{ 
			
			if(obj.value=='') 
			{ 
				
				return true; 
			} 
			var stuff=obj.value.match(/^(.*)(\.)(.{1,8})$/)[3]; 
			if(stuff!='xls' && stuff!='xlsx') 
			{ 
				win.errorAlert('文件类型不正确，请选择.xls文件');
				return false; 
			} 
			return true; 
		} 
			
		//提交校验
		function checkcommit()
		{
		    if($("#checkedCompanyName").val()=="")
		    {
		    	win.errorAlert("请选择机构");
				return false;
		    }
			if($("[name=file]").length==0)
			{
				return false;
			}
			var flag=false;
			$.each($("[name=file]"),function(index,item){
				if(!CheckWorkFile(item))
					return false;
				if(flag==false && $(item).val()!="")
					flag=true;
			})
			if(flag==false)
			{
				win.errorAlert("请上传文件excel文件");
				return false;
			}
			return true;
		}
				
		$("#commit-1").click(function(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!checkcommit())
			{
					$.unblockUI();
					return false;
			}
			var url = "${pageContext.request.contextPath}/reportcommon/save";
			
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
						if(commitresult.result==true)
							win.successAlert('保存成功！',function(){
									parent.location.reload(true);
									parent.mclose();
							});
						else
						{	
							win.errorAlert(commitresult.exceptionStr);
							
						}
			     },  
			     error : function(data) {
			     	$.unblockUI();
			     }  
			}); 
				

			return false;
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
	    
		    if($("#operationType").val()=="new")
			{   
				var checked_data = '${organalID}';
 				var com_data = ${allCompanyTree};
				$(' input.time').jeDate(
					{
						format:"YYYY-MM",
						choosefun:function(elem, val, date) {changeDate(val)},
						clearfun:function(elem, val) {changeDate("")},            //清除日期后的回调, elem当前输入框ID, val当前选择的值
			  				okfun:function(elem, val, date) {}, 
					}
				)
				
				
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
			else
			{
			
				$("#checkedCompanyName").attr("readOnly","true");
			}
	    	createFormsFile();
	    	
	    });

		$("#com_ztree").click(function(){
				setTimeout(function(){
	   				if($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0])
						{		
							var groupType_temp = $("#grouptype").val();						 
							var temp_organalId = $.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id;
							var temp_checkedCompanyName = $.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name;
							var url = "${pageContext.request.contextPath}/reportcommon/isVirtualCompany";
		
							if(groupType_temp && groupType_temp ==52001){
								$.ajax({  
								     url : url,  
								     type : "POST",  
								     data: {organalId:temp_organalId,companyName:temp_checkedCompanyName},
							         async: false,  
							         cache: false,  
							         dataType:'json', 
								     success : function(data) {
								     	 if("success" == data ){
											 $("#organalID").val(temp_organalId);							 
											 $("#checkedCompanyName").val(temp_checkedCompanyName); 	 
								     	 }else{							     	 
										 	$("#organalID").val('');							 
										 	$("#checkedCompanyName").val(''); 
								     	 }							     
								     },  
								     error : function(data) {
								     }  
								}); 
							}else{
									 $("#organalID").val(temp_organalId);							 
									 $("#checkedCompanyName").val(temp_checkedCompanyName); 
							}	
 
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
	</script>
</html>