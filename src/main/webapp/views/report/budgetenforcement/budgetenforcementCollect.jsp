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

	
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>预算执行报表汇总生成
				<i class="iconfont icon-guanbi"></i>
			</h4>
		
		<input type="hidden" id="operationType" value="${op }"> 

	<div class="label_inpt_div">
		<form id="form2">
			
			<div class="model_part">
				<input type="hidden" name="groupid" id="groupid" value="${groupid}">
				<input type="hidden" name="grouptype" id="grouptype" value="${grouptype}">
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
			</div>
			<div id="nodeList" class="model_part">
			
			
			
			</div>
			
			<input type="hidden" name="fdsfsdfsdfs"> 
			
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
 
 		var oldorgan="";
 		var olddate="";
 
 		function initNodeList(v,pageNums){
 	
			$.ajax({
				url:"${pageContext.request.contextPath}/reportbudgetenforcementcollect/_itemViewList?pageNums="+pageNums+"&time="+$("#Date").val()+"&organalID="+$("#organalID").val()+"&groupType="+$("#grouptype").val(),
				type:"POST",
				dataType:"text",
				async:false,
				success:function(data){
					$("#nodeList").children().remove();
					$("#nodeList").append(data);
				}
			});	
		}
		initNodeList("",1);//初始化节点列表
		
 
 
 		function changedate(val)
 		{
 			if(olddate!=val){
				getDateChangeafterFinanceTree(val);		
 				initNodeList("",1);
 			}
 			olddate=val;
 		}
 		
 		/**
 		 *  获取时间改变后新的财务树
 		 */
 		function getDateChangeafterFinanceTree(val){
 			var url = "${pageContext.request.contextPath}/reportcommon/getFinanceTreeByTime?time=" + val; 
 			$.get(url,function(data,status){
 				var checked_data = $("#organalID").val();
 				var com_data = eval("(" + data + ")");
 				initFinanceTree(checked_data,com_data);	
 			});
 		}
 		
 		function checkcommit()
 		{
 		    var flag=true;
 			if($("#Date").val()=="")
 			{
 				win.errorAlert("时间不能为空");
 				flag=false;
 			}
 			if($("#organalID").val()=="")
 			{
 				win.errorAlert("机构名称不能为空");
 				flag=false;
 			}
 			return flag;
 		}
 
 
 
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
		
				
		$("#commit-1").click(function(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!checkcommit())
			{
					$.unblockUI();
					return false;
			}
			var url = "${pageContext.request.contextPath}/reportcommon/collect";
			
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