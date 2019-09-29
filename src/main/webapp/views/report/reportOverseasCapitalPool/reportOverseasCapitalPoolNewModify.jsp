<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>境外资金池建设数据</title>
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
		</style>
</head>
<body>
	<c:choose>
		<c:when test="${op eq 'modify'}">
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>修改境外资金池建设数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增境外资金池建设数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:otherwise>
		
	</c:choose>
<div class="label_inpt_div">
		<form:form id="form2" modelAttribute="entityview" >
		
				<form:hidden path="id"/>
				
				<div class="model_part">
				<label class="w20"><span class="required"> * </span>年份:</label>
				<input class="w25 time" type="text" id="year" name="year" value="${entityview.year }" readonly="true" class="time"  />
				<label class="w20"><span class="required"> * </span>单位名称:</label>
				<span class="w25">
						<%-- <input type="hidden" id="organalID" name="org" value="${entityview.org }" >
						<input name="orgname" id="checkedCompanyName" value="${entityview.orgname }" check="NotEmpty_string_._._._._." readonly>
						<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
							<ul id="com_ztree" class="ztree">
			
							</ul>
					   </div> --%>
					   <select  name="org" class="selectpicker" id="org" onchange="getData()">
							<c:forEach items="${requestScope.organltype}" var="result">
									<option value="${result.id.nnodeId }"  <c:if test="${entityview.org == result.id.nnodeId }">selected="selected"</c:if>>${result.vcFullName }</option>
							</c:forEach>
						</select>
				</span>
			    
				
				<label class="w20"><span class="required"> * </span>季度:</label>
				<span class="w25">
					<select  name="season" class="selectpicker" id="season" onchange="getData()">
							<c:forEach items="${requestScope.seasontype}" var="result">
									<option value="${result.id }"  <c:if test="${entityview.season == result.id}">selected="selected"</c:if>>${result.description }</option>
							</c:forEach>
					</select>
				</span> 
				<label class="w20"></label>
				<label class="w25 setleft"></label> 
				<label class="w20"><span class="required"> * </span>资金通道规模(亿美元):</label>
				<form:input class="w25 calepool" path="capitalchannel" id="capitalchannel" check="NotEmpty_double_13_4_0+_。_."/>
				
				<label class="w20"><span class="required"> * </span>资金出境资金(亿美元):</label>
				<form:input class="w25 calepool" path="outboundFunds" id="outboundFunds" check="NotEmpty_double_13_4_0+_。_."/>
				
				<label class="w20"><span class="required"> * </span>境外下账资金(亿美元):</label>
				<form:input class="w25 calepool" path="accountCapital" id="accountCapital" check="NotEmpty_double_13_4_0+_。_."/>
				
				<label class="w20"><span class="required"> * </span>境外资金池(亿美元):</label>
				<form:input class="w25" path="capitalPool" id="capitalPool" readonly="true"/>
			</div>
			
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
				<button class="btn btn-primary model_btn_ok" id="commit-2">保存并上报</button>
				<button class="btn btn-default model model_btn_close">关闭</button>
				
			</div>
			<input type="hidden" name="IE_bug"> <!-- 这个是为了修正IEbug 后台报错Stream ended unexpectedly -->
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
		
		

		$(".calepool").change(function(){
			caleall()
		})
		
		function caleall()
		{
			var data=[$("#accountCapital").val(),$("#outboundFunds").val(),$("#capitalchannel").val()]
			$("#capitalPool").val(cale(data));
		}
		
		function cale(a)
		{
			var result=0;
			for ( var int = 0; int < a.length; int++) {
				result=addcale(result,a[int])
			}
			return result;
		}

		function addcale(a,b)
		{
			if(a=="")
				a=0;
			if(b=="")
				b=0
			return Number(a)+Number(b);
		}


		function commitcheck()
		{
			if($("#year").val()=="")
			{
				win.errorAlert("年份不能为空");
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
			var url = "${pageContext.request.contextPath}/reportOverseasCapitalPool/save";
			
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
							win.errorAlert(commitresult.exceptionStr);
			     },  
			     error : function(data) {
			     	$.unblockUI();
			     }  
			}); 
			return false;
		})
		$("#commit-2").click(function(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			if(!vaild.all() || !commitcheck())
			{
					$.unblockUI();
					return false;
			}
					//var entityBasicInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/reportOverseasCapitalPool/saveandreport";
			
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

		var timeoutId
 		$('#checkedCompanyName').on('focus click',function(){
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
	    
	    function getData()
	    {
	    	//如果是新增会异步查询 境外下账资金 其默认值为当季各月度执行的投资支出和筹资支出之和
			if('${op}'!='modify')
			{
				$.ajax({
				  type: 'POST',
				  url: "${pageContext.request.contextPath}/reportOverseasCapitalPool/getdata",
				  data:{"season":$("#season").val(),"year":$("#year").val(),"organID":$("#org").val()+""},
				  success: function (data){
				  		console.log(data);
				  		$("#accountCapital").val(data);
				  		caleall();
				  }
				});
			}
	    }
	   

	    $(function () {
	    	
	    getData()
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
	
	
		$(' input.time').jeDate(
			{
				format:"YYYY",
						choosefun:function(elem, val, date) {getData()},
						clearfun:function(elem, val) {getData()},            //清除日期后的回调, elem当前输入框ID, val当前选择的值
			  				okfun:function(elem, val, date) {}, 
			}
		)
		
		
	
		$("#com_ztree").click(function(){
				setTimeout(function(){
	   				if($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0])
						{
							 $("#organalID").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id)
							 $("#checkedCompanyName").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
							 getData();
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
		//清空
		$('.clear').on('click',function(){
			$(this).siblings('input[name="orgname"]').val('');
		});
	</script>
</html>