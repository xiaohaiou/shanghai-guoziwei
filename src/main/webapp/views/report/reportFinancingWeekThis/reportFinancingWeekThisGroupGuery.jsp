<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>本周融资项目进度数据汇总</title>
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		
		<!-- 库|插件 -->
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
		<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>">
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
			input[name="stratYear"],input[name="endYear"]{
			    border-top-right-radius: 0;
    			border-bottom-right-radius: 0;
			}
			input[name="stratWeek"],input[name="endWeek"]{
			    border-top-left-radius: 0;
			    border-bottom-left-radius: 0;
			    margin-left: -10px;
			}
		</style>
	
	</head>
	<body>
		<h4>
			本周融资项目进度数据汇总
			<i class="iconfont icon-guanbi"></i>
		</h4>
		<div class="table_content">
		<form id="form1"  class="row" >
			<span class="col-md-4">		
				<label class="w20">年月:</label>				
				<input class="w25 date1" type="text" id="year" name="year" value="${year}"/>
				<select  name="month" id="month" class="w25 selectpicker" >
				<option value=""  ></option>
					<c:forEach var="n" begin="1" end="12" step="1">
						<option value="${n}"  <c:if test="${entityview.week == n}">selected="selected"</c:if>>${n}</option>
					</c:forEach>
				</select>
			</span>	
			
			<span class="col-md-4">
				<label class="w22">融资类别：</label>
				<select  name="category" class="selectpicker" >
					<option value=""  >全部</option>
					<c:forEach items="${requestScope.financingCategory}" var="result">
						<option value="${result.id }"  <c:if test="${category == result.id}">selected="selected"</c:if>>${result.description }</option>
					</c:forEach>
				</select>
			</span>				
			
			<span class="col-md-3">
				<label class="w20">周数:</label>
				<select  name="week" id="week" class="selectpicker" >
				<option value=""  ></option>
					<c:forEach var="n" begin="1" end="53" step="1">
						<option value="${n}"  <c:if test="${entityview.week == n}">selected="selected"</c:if>>${n}</option>
					</c:forEach>
				</select>
			</span>		
			
			<span class="col-md-4">
				<label class="w22">周日期范围:</label>
				<input class="w25 date" type="text" id="weekStratDate" name="weekStratDate" value="${entityview.weekStratDate }" readonly="true" check="NotEmpty_string_._._._._." class="date" />
				~
				<input class="w25 date" type="text" id="weekEndDate" name="weekEndDate" value="${entityview.weekEndDate }" readonly="true" check="NotEmpty_string_._._._._." class="date" />
			</span>



			<span class="col-md-4">
				<label class="w22">单位名称:</label>
				<input type="hidden" name="groupid" id="groupid" value="${groupid}">
				<input type="hidden" name="grouptype" id="grouptype" value="${grouptype}">
				<input type="hidden" id="operationType" value="${op }"> 
				<input name="checkedCompanyName" id="checkedCompanyName" value="${checkedCompanyName }" readonly="readonly">
				<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
					<ul id="com_ztree" class="ztree">
		
					</ul>
			   </div>
			   <input type="hidden" id="organalID" name="organalID" value="${organalID}" >
			</span>
			<input type = "hidden" id ="orgType" name="orgType">
			<input type = "hidden" id ="flag" name="flag">
	
			<div class="form_div col-md-12">
				<span style="position:absolute;left:50px;">合计已下账:${sumAlreadyAccountAmounts}/合计规模：${sumScale}  (亿元)</span>
				<c:if test="${fn:contains(gzwsession_code,',bimWork_bzrzjzTb_q,')==true }"> <!-- bimWork_bzrzjzTb_cxhjz -->
					<input type="button" value="查询合计值" class="form_btn" id="qrybtn" onclick="_query()">
				</c:if>
			</div>
		</form>
			<div class="tablebox">
 			<table class="table table-condensed table-striped">
					<tr class="table_header">         
						<th>序号</th>
						<th>单位名称</th>		
						<th>类别名</th>
						<th>操作主体</th>		
						<th>抵质押信息</th>		
						<th>模式</th>		
						<th>金融机构</th>
						<th>总规模</th>		
						<th>期限（月）</th>	
						<th>综合成本</th>		
						<th>新/续</th>
						<th>条件及结构</th>		
						<th>融资进展</th>		
						<th>下账时间</th>		
						<th>下账金额（亿元）</th>
						<th>责任人</th>		
						<th>经办人</th>																		
					</tr>
					<c:if test="${!empty requestScope.msgPage.list }">
					  <c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
						<c:forEach items="${ requestScope.msgPage.list}" var="sys" varStatus="status">
							<tr>
								<td>
									${recordNumber+ status.index + 1 }
								</td>
								<td >
									${sys.coreOrgname}
								</td>	
								<td >
									${sys.categoryName}
								</td>
								<td >
									${sys.operateOrgname}
								</td>	
								<td >
									${sys.hypothecationInformation}
								</td>								
								<td >
									${sys.pattern}
								</td>	
								<td >
									${sys.financialInstitution}
								</td>
								<td >
									<fmt:formatNumber value="${sys.scale}"  pattern="#,##0.0000" />
								</td>	
								<td >
									${sys.term}
								</td>
								<td >
									${sys.comprehensiveFinancingCostRatio}
								</td>	
								<td >
									${sys.typeName}
								</td>
								<td >
									${sys.conditionStructure}
								</td>	
								<td >
									${sys.projectProgressName}
								</td>								
								<td >
									${sys.expectAccountDate}
								</td>	
								<td >
									<fmt:formatNumber value="${sys.alreadyAccountAmounts}"  pattern="#,##0.0000" />
								</td>
								<td >
									${sys.personLiable}
								</td>	
								<td >
									${sys.operator}
								</td>																																												
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.msgPage.list}">
						<tr>
							<td colspan="13" align="center">
								查询无记录
							</td>
						</tr>
					</c:if>
				</table>  
				<jsp:include page="../../system/page.jsp"/>
			</div>
		</div>
		<jsp:include page="../../system/modal.jsp"/>
	</body>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/js/listPageCompanyTreeSelect.js"/>" type="text/javascript"></script>
	
	<script type="text/javascript">
	
		/**
	     * 初始化财务树	
	     */
	    var oldorgan="";
 		var olddate="";
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
			else{
			
				$("#checkedCompanyName").attr("readOnly","true");
			}
    	
    	});
	
	
		function checkdata(url,id)
		{
		    var commitresult="";
			var checkurl = "${mapurl}/hasCheck?id=" + id;
			
			$.ajax({
			  type: 'POST',
			  url: checkurl,
			 // async:false,
			  success: function (data){
			  		commitresult=JSON.parse(data);
			  		if(commitresult.result == true){
						mload(url);
					}
					else
						win.errorAlert(commitresult.exceptionStr,function(){window.location.reload(true);});
	
			  }
			});
		}
	
		$(' input.date').jeDate(
			{
				format:"YYYY-MM-DD"
			}
		)
		
		$(' input.date1').jeDate(
			{
				format:"YYYY"
			}
		)
	
		
		function _query()
		{
			var form = document.forms[0];
			form.action = "/shanghai-gzw/reportFinancingWeekThis/g_query";
			form.method = "post";
			form.submit();		
		}
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