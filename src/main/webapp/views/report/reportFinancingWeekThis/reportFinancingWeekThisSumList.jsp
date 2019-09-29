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
		<title>周融资下账情况</title>
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		
		<!-- 库|插件 -->
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
		<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>">
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
		<h4>周融资下账情况</h4>
		<div class="table_content">
		<form id="form1"  class="row"  >
			<span class="col-md-4">
				<span class="search_title">周数：</span>
					<input  type="text" value="${entityview.year }" name="year" readonly class="time time_short" style="width:80px;height:30px" />
					<select  name="week" id="week" style="width:80px;height:30px" >
					<option value=""  ></option>
						<c:forEach var="n" begin="1" end="53" step="1">
							<option value="${n}"  <c:if test="${entityview.week == n}">selected="selected"</c:if>>${n}</option>
						</c:forEach>
					</select>
			</span>
	<!-- 		<span class="col-md-4">
				<span class="search_title">上报单位：</span>
					<select  name="org" class="selectpicker" >
					<option value=""  >全部</option>
						<c:forEach items="${requestScope.coreCompSelect}" var="result">
							<option value="${result.id.nnodeId }"  <c:if test="${entityview.org eq result.id.nnodeId}">selected="selected"</c:if>>${result.vcFullName }</option>
						</c:forEach>
					</select>
				
			</span>
		 -->	
			<span class="col-md-4">
				<input type="hidden" name="groupid" id="groupid" value="${groupid}">
				<input type="hidden" name="grouptype" id="grouptype" value="${grouptype}">
				<input type="hidden" id="operationType" value="${op }"> 
				<span class="search_title">单位名称:</span>
				<input name="checkedCompanyName" id="checkedCompanyName" value="${checkedCompanyName }" readonly="readonly">
				<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
					<ul id="com_ztree" class="ztree">
		
					</ul>
			   </div>
			   <input type="hidden" id="organalID" name="organalID" value="${organalID}" >
			</span>
			
			
			
			
			
			
			
			
			
			
			<div class="form_div col-md-12">
				<c:if test="${fn:contains(gzwsession_code,',bimWork_zrzxzqkCx_q,')==true }">	
					<input type="button" value="查询" class="form_btn" id="qrybtn" onclick="_query()">
				</c:if>
			</div>
		</form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th>序号</th>
						<th>单位名称</th>
						<th>已下账融资项目数(笔)</th>
						<th>已下账融资项目金额(亿元)</th>
					</tr>
					<c:if test="${!empty dataList }">
						<c:forEach items="${dataList}" var="sys" varStatus="status">
							<tr>
								<td>
									${status.index + 1 }
								</td>
								<td style="text-align: center;">
									${sys[0]}
								</td>
								<td style="text-align: center;"><!-- right -->
									${sys[1]}
								</td>
								<td style="text-align: center"><!-- right -->
									<fmt:formatNumber value="${sys[2]}"  pattern="#,##0.0000" />
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty dataList}">
						<tr>
							<td colspan="4" align="center">
								查询无记录
							</td>
						</tr>
					</c:if>
				</table>
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
	
		$(' input.time').jeDate(
			{
				format:"YYYY",
			}
		)
		
		function _query(){
			var form = document.forms[0];
			form.target = "_self";
			form.action = "${searchurl}";
			form.method = "post"; 
			form.submit();	
		}
		
		function _export(type){
			var form = document.forms[0];
			form.target = "_blank";
			form.action = "${exporturl}";
			form.method = "post";
			if(type == 0)
				$("#orgType").val("0");
			form.submit();
			/* var checkurl = "${exporturl}?id=" + id;
			window.open(checkurl); */
		}
		
	</script>
</html>