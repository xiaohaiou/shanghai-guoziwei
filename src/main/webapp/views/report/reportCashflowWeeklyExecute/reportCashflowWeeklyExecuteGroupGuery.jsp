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
		<title>现金流月执行数据汇总</title>
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
		</style>
	
	</head>
	<body>
		<h4>
			现金流月执行数据汇总
			<i class="iconfont icon-guanbi"></i>
		</h4>
		<div class="table_content">
		<form id="form1"  class="row"  >
			<span class="col-md-4">
				<label class="w2">单位名称：</label>
				<input type="hidden" id="organalID" name="org" value="${entityview.org}" >
				<input name="orgname" id="checkedCompanyName" value="${entityview.orgname}" readonly title="${entityview.orgname}">
				
				<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
					<ul id="com_ztree" class="ztree">
	
					</ul>
			   </div>
		   </span>
			<span class="col-md-4">
				<label class="w2">审核状态：</label>
					<select  name="status" class="selectpicker" >
					<option value=""  >全部</option>
						<c:forEach items="${requestScope.examstatustype}" var="result">
								<option value="${result.id }"  <c:if test="${entityview.status == result.id}">selected="selected"</c:if>>${result.description }</option>
							</c:forEach>
					</select>
			</span>
			<span class="col-md-4">
				<label class="w2">时间：</label>
				<input  type="text" value="${entityview.starttime }" name="starttime" readonly class="time1 time_short" />
				<input type="hidden" id="startyear" name="startyear" value="${entityview.startyear }">
				<input type="hidden" id="startmonth" name="startmonth" value="${entityview.startmonth }">
				至
				<input  type="text" value="${entityview.endtime }" name="endtime" readonly class="time2 time_short" />
				<input type="hidden" id="endyear" name="endyear" value="${entityview.endyear }">
				<input type="hidden" id="endmonth" name="endmonth" value="${entityview.endmonth }">
			</span>
			<div class="form_div col-md-12">
				<c:if test="${fn:contains(gzwsession_code,',bimWork_xjlyzxsjtb_cxhjz,')==true }"> <!-- bimWork_xjlyzxsjtb_cxhjz-->
					<input type="button" value="查询合计值" class="form_btn" id="qrybtn" onclick="_query()">
				</c:if>
			</div>
		</form>
			<div class="tablebox">
		<!--  		<table>
					<tr class="table_header"> 	
						<td>类型</td> 						
						<td>经营性流入(万元)</td>    			
						<td>经营性流出(万元)</td>   	
						<td>经营性净流量(万元)</td>    			
						<td>投资性流入(万元)</td> 						
						<td>投资性流出(万元)</td>    			
						<td>投资性净流量(万元)</td> 							
						<td>融资性流入(万元)</td>    			
						<td>融资性流出(万元)</td> 							
						<td>融资性净流量(万元)</td>    			
						<td>净流量(万元)</td> 
						<td>资金贡献(万元)</td> 
						<td>资金拆借(万元)</td> 																							
					</tr>
					<tr> 	
						<td>合计</td> 												
						<td>
							<fmt:formatNumber value="${tempBean.commercialInto}"  pattern="#,##0.00" />
						</td>    			
						<td>
							<fmt:formatNumber value="${tempBean.commercialOut}"  pattern="#,##0.00" />
						</td>   	
						<td>
							<fmt:formatNumber value="${tempBean.commercialNetInflow}"  pattern="#,##0.00" />
						</td>    			
						<td>
							<fmt:formatNumber value="${tempBean.investmentInto}"  pattern="#,##0.00" />
						</td> 						
						<td>
							<fmt:formatNumber value="${tempBean.investOut}"  pattern="#,##0.00" />
						</td>    			
						<td>
							<fmt:formatNumber value="${tempBean.investNetInflow}"  pattern="#,##0.00" />
						</td> 							
						<td>
							<fmt:formatNumber value="${tempBean.financingInto}"  pattern="#,##0.00" />
						</td>    			
						<td>
							<fmt:formatNumber value="${tempBean.financingOut}"  pattern="#,##0.00" />
						</td> 							
						<td>
							<fmt:formatNumber value="${tempBean.financingNetInflow}"  pattern="#,##0.00" />
						</td>    			
						<td>
							<fmt:formatNumber value="${tempBean.commercialNetInflow+tempBean.investNetInflow+tempBean.financingNetInflow}"  pattern="#,##0.00" />
						</td> 	
						<td>
							<fmt:formatNumber value="${tempBean.lendingOrContributionInto}"  pattern="#,##0.00" />
						</td>    			
						<td>
							<fmt:formatNumber value="${tempBean.lendingOrContributionOut}"  pattern="#,##0.00" />
						</td> 																						
					</tr>
					<c:if test="${!empty requestScope.msgPage.list }">
					  <c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
						<c:forEach items="${ requestScope.msgPage.list}" var="sys" varStatus="status">
							<tr>
								<td>
									${recordNumber+ status.index + 1 }. ${sys.orgname}
								</td>
								<td>
									<fmt:formatNumber value="${sys.commercialInto}"  pattern="#,##0.00" />
								</td>    			
								<td>
									<fmt:formatNumber value="${sys.commercialOut}"  pattern="#,##0.00" />
								</td>   	
								<td>
								 	<fmt:formatNumber value="${sys.commercialNetInflow}"  pattern="#,##0.00" />
								</td>    			
								<td>
								 	<fmt:formatNumber value="${sys.investmentInto}"  pattern="#,##0.00" />
								</td> 						
								<td>
									<fmt:formatNumber value="${sys.investOut}"  pattern="#,##0.00" />
								</td>    			
								<td>
									<fmt:formatNumber value="${sys.investNetInflow}"  pattern="#,##0.00" />
								</td> 							
								<td>
									<fmt:formatNumber value="${sys.financingInto}"  pattern="#,##0.00" />
								</td>    			
								<td>
									<fmt:formatNumber value="${sys.financingOut}"  pattern="#,##0.00" />
								</td> 							
								<td>
									<fmt:formatNumber value="${sys.financingNetInflow}"  pattern="#,##0.00" />
								</td>    			
								<td>
									<fmt:formatNumber value="${sys.commercialNetInflow+sys.investNetInflow+sys.financingNetInflow}"  pattern="#,##0.00" /></td> 
								<td>
									<fmt:formatNumber value="${sys.lendingOrContributionInto}"  pattern="#,##0.00" />
								</td> 
								<td>
									<fmt:formatNumber value="${sys.lendingOrContributionOut}"  pattern="#,##0.00" />
								</td> 	
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.msgPage.list}">
						<tr>
							<td colspan="11" align="center">
								查询无记录
							</td>
						</tr>
					</c:if>
				</table>  -->
				<table>
					<c:if test="${!empty requestScope.msgPage.list }">
					<c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
						<tr> 	
							<td>类型</td> 	
							<td>合计</td> 	
							<c:forEach items="${ requestScope.msgPage.list}" var="sys" varStatus="status">					
								<td>
										${recordNumber+ status.index + 1 }. ${sys.orgname}
								</td> 
							</c:forEach> 																				
						</tr>
						<tr>
							<th>经营性流入(万元)</th> 
							<td>
								<fmt:formatNumber value="${tempBean.commercialInto}"  pattern="#,##0.00" />
							</td>  
							<c:forEach items="${ requestScope.msgPage.list}" var="sys">	
								<td>
									<fmt:formatNumber value="${sys.commercialInto}"  pattern="#,##0.00" />
								</td> 
							</c:forEach>    							
						</tr>
						<tr>
							<th>经营性流出(万元)</th>  
							<td>
								<fmt:formatNumber value="${tempBean.commercialOut}"  pattern="#,##0.00" />
							</td>  
							<c:forEach items="${ requestScope.msgPage.list}" var="sys">	
								<td>
									<fmt:formatNumber value="${sys.commercialOut}"  pattern="#,##0.00" />
								</td>																	
							</c:forEach>  
						</tr>
						<tr>
							<th>经营性净流量(万元)</th>  
							<td>
								<fmt:formatNumber value="${tempBean.commercialNetInflow}"  pattern="#,##0.00" />
							</td> 
							<c:forEach items="${ requestScope.msgPage.list}" var="sys">								
								<td>
								 	<fmt:formatNumber value="${sys.commercialNetInflow}"  pattern="#,##0.00" />
								</td> 																					
							</c:forEach>						
						</tr>
						<tr>
							<th>投资性流入(万元)</th> 
							<td>
								<fmt:formatNumber value="${tempBean.investmentInto}"  pattern="#,##0.00" />
							</td> 
							<c:forEach items="${ requestScope.msgPage.list}" var="sys">								
								<td>
								 	<fmt:formatNumber value="${sys.investmentInto}"  pattern="#,##0.00" />
								</td> 																				
							</c:forEach>						
						</tr>
						<tr>
							<th>投资性流出(万元)</th>  
							<td>
								<fmt:formatNumber value="${tempBean.investOut}"  pattern="#,##0.00" />
							</td>  
							<c:forEach items="${ requestScope.msgPage.list}" var="sys">								
								<td>
									<fmt:formatNumber value="${sys.investOut}"  pattern="#,##0.00" />
								</td>  								 											
							</c:forEach>
						</tr>
						<tr>
							<th>投资性净流量(万元)</th> 	
							<td>
								<fmt:formatNumber value="${tempBean.investNetInflow}"  pattern="#,##0.00" />
							</td>	
							<c:forEach items="${ requestScope.msgPage.list}" var="sys">		
								<td>
									<fmt:formatNumber value="${sys.investNetInflow}"  pattern="#,##0.00" />
								</td> 																	
							</c:forEach>					
						</tr>
						<tr>
							<th>融资性流入(万元)</th> 
							<td>
								<fmt:formatNumber value="${tempBean.financingInto}"  pattern="#,##0.00" />
							</td>	
							<c:forEach items="${ requestScope.msgPage.list}" var="sys">	
								<td>
									<fmt:formatNumber value="${sys.financingInto}"  pattern="#,##0.00" />
								</td>  																	
							</c:forEach>
						</tr>
						<tr>
							<th>融资性流出(万元)</th>
							<td>
								<fmt:formatNumber value="${tempBean.financingOut}"  pattern="#,##0.00" />
							</td>
							<c:forEach items="${ requestScope.msgPage.list}" var="sys">	 
								<td>
									<fmt:formatNumber value="${sys.financingOut}"  pattern="#,##0.00" />
								</td>
							</c:forEach>																				
						</tr>
						<tr>
							<th>融资性净流量(万元)</th>  
							<td>
								<fmt:formatNumber value="${tempBean.financingNetInflow}"  pattern="#,##0.00" />
							</td> 
							<c:forEach items="${ requestScope.msgPage.list}" var="sys">								
								<td>
									<fmt:formatNumber value="${sys.financingNetInflow}"  pattern="#,##0.00" />
								</td>																														
							</c:forEach>						
						</tr>
						<tr>
							<th>净流量(万元)</th> 
							<td>
								<fmt:formatNumber value="${tempBean.commercialNetInflow+tempBean.investNetInflow+tempBean.financingNetInflow}"  pattern="#,##0.00" />
							</td> 	
							<c:forEach items="${ requestScope.msgPage.list}" var="sys">	
								<td>
									<fmt:formatNumber value="${sys.commercialNetInflow+sys.investNetInflow+sys.financingNetInflow}"  pattern="#,##0.00" />
								</td> 
							</c:forEach>																										
						</tr>
						<tr>
							<th>资金贡献(万元)</th> 					
							<td>
								<fmt:formatNumber value="${tempBean.lendingOrContributionInto}"  pattern="#,##0.00" />
							</td> 	
							<c:forEach items="${ requestScope.msgPage.list}" var="sys">
								<td>
									<fmt:formatNumber value="${sys.lendingOrContributionInto}"  pattern="#,##0.00" />
								</td> 	
							</c:forEach>																								
						</tr>
						<tr>
							<th>资金拆借(万元)</th> 
							<td>
								<fmt:formatNumber value="${tempBean.lendingOrContributionOut}"  pattern="#,##0.00" />
							</td> 
							<c:forEach items="${ requestScope.msgPage.list}" var="sys">							
								<td>
									<fmt:formatNumber value="${sys.lendingOrContributionOut}"  pattern="#,##0.00" />
								</td>	
							</c:forEach>																																
						</tr>
				</c:if>		
				<c:if test="${empty requestScope.msgPage.list}">
					<tr>
						<td colspan="11" align="center">
							查询无记录
						</td>
					</tr>
				</c:if>
				</table>
				
				
				
				
				<jsp:include page="../../system/page.jsp"/>
			</div>
		</div>
	<!-- 	<jsp:include page="../../system/modal.jsp"/> -->
	</body>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
	
	<script type="text/javascript">
	
	
	
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
	
	
		$(' input.time1').jeDate(
			{
				format:"YYYY-MM",
				choosefun:function(elem, val, date) {changeDate1(val)},
				clearfun:function(elem, val) {changeDate1("")},            //清除日期后的回调, elem当前输入框ID, val当前选择的值
	  				okfun:function(elem, val, date) {},
			}
		)
		$(' input.time2').jeDate(
			{
				format:"YYYY-MM",
				choosefun:function(elem, val, date) {changeDate2(val)},
				clearfun:function(elem, val) {changeDate2("")},            //清除日期后的回调, elem当前输入框ID, val当前选择的值
	  				okfun:function(elem, val, date) {},
			}
		)
		function changeDate1(val){
			if(val == ""){
				$("#startyear").val("");
				$("#startmonth").val("");
			}else{
				var year = parseInt((val.split("-"))[0]);
				$("#startyear").val(year);
				var month = parseInt((val.split("-"))[1]);
				$("#startmonth").val(month);
			}
		}
		function changeDate2(val){
			if(val == ""){
				$("#endyear").val("");
				$("#endmonth").val("");
			}else{
				var year = parseInt((val.split("-"))[0]);
				$("#endyear").val(year);
				var month = parseInt((val.split("-"))[1]);
				$("#endmonth").val(month);
			}
		}
	
		$("#com_ztree").click(function(){
				setTimeout(function(){
	   				if($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0])
						{
							 $("#organalID").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id)
							 $("#checkedCompanyName").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
							 $("#checkedCompanyName").attr('title',$.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
						}
			   	});
			
		})

	
		function _query()
		{
			var form = document.forms[0];
			form.action = "${searchurl}";
			form.method = "post";
			form.submit();		
		}


		//清空
		$('.clear').on('click',function(){
			$(this).siblings('input[name="orgname"]').val('');
			$("#organalID").val("");
			$("#checkedCompanyName").attr('title','');
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
		
		
	</script>
</html>