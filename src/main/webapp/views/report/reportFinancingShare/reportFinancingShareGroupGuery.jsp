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
		<title>融资项目进度数据汇总</title>
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
		
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
					.table{
        				table-layout: fixed;
        				text-align: center; 
   					}
				</style>
	
	</head>
	<body>
		<h4>
			共享类融资项目进度数据汇总
			<i class="iconfont icon-guanbi"></i>
		</h4>
		<div class="label_inpt_div">
			<form id="form1"  class="row"  >
				<input type = "hidden" id ="orgType" name="orgType">
				<input type = "hidden" id ="flag" name="flag">
				<span class="col-md-3">
					<label>融资类别：</label>
					<select  name="category" class="selectpicker" >
						<option value=""  >全部</option>
						<c:forEach items="${requestScope.financingCategory}" var="result">
							<option value="${result.id }"  <c:if test="${entityview.category == result.id}">selected="selected"</c:if>>${result.description }</option>
						</c:forEach>
					</select>
				</span>
	
				<span class="col-md-3">
					<label>单位名称:</label>
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
	
				
				<div class="form_div col-md-12">
					<c:if test="${fn:contains(gzwsession_code,',bimWork_gxlrzxzTb_cxhjz,')==true }">
						<span style="position:absolute;left:50px;">合计已下账:${sumAlearyAccountMoney }/合计规模：${sumScaleMoney}  (亿元)</span>
						<input type="button" value="查询合计值" class="form_btn" id="qrybtn" onclick="_query()">
					</c:if>	
				</div>			
		</form>
			<div class="tablebox">
				<table>
					<tr class="table_header">             
						<th>序号</th>
						<th>单位名称</th>
						<th>类别名</th>		
						<th>抵质押信息 </th>	
						<th>模式 </th>	
						<th>金融机构</th>			
						<th>总规模 </th>
						<th>期限（月） </th>							
						<th>综合成本</th>							
						<th>融资结构</th>							
						<th>用途介绍</th>		
						<th>具体进展</th>	
						<th>具体需求及建议</th>		
						<th>已下账金额（亿元）</th>	
						<th>责任人</th>		
						<th>经办人</th>																					
					</tr>
					<c:if test="${!empty requestScope.msgPage.list }">
					  <c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
						<c:forEach items="${ requestScope.msgPage.list}" var="sys" varStatus="status">
							<tr>
								<td>
									${recordNumber+ status.index + 1}
								</td>
								<td >
									${sys.coreOrgname}
								</td>															
								<td >
									${sys.categoryName}
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
									<fmt:formatNumber value="${sys.scale }"  pattern="#,##0.0000" />
								</td>	
								<td >
									${sys.term}
								</td>									
								<td >
									${sys.comprehensiveFinancingCostRatio}
								</td>
								<td >
									${sys.financingStructure}
								</td>	
								<td >
									${sys.purposeToIntroduce}
								</td>	
								<td >
									${sys.specificProgress}
								</td>	
								<td >
									${sys.spSuggestions}
								</td>	
								<td >
									${sys.alreadyAccountAmounts}
								</td>	
								<td >
									${sys.personLiable}
								</td>	
								<td >
									${sys.operator}
								</td>								
								<!-- 
								<td>
									<c:if test="${fn:contains(gzwsession_code,',bimWork_yblrzxzTb_q,')==true }">	
										<a href="javascript:void(0)" onclick="checkdata('${mapurl}/view?id=${sys.id}',${sys.id })" >查看</a>
									</c:if>
									<c:if test="${sys.status==50114 || sys.status==50112 || sys.status==50115}">
										<c:if test="${fn:contains(gzwsession_code,',bimWork_yblrzxzTb_e,')==true }">	
											<a href="javascript:void(0)" onclick="checkdata('${mapurl}/newmodify?op=modify&id=${sys.id}',${sys.id })" >修改</a>
										</c:if>
										<c:if test="${fn:contains(gzwsession_code,',bimWork_yblrzxzTb_r,')==true }">	
											<a href="javascript:void(0)" onclick="checkdata('${mapurl}/postexam?op=report&id=${sys.id}',${sys.id })">上报</a>
										</c:if>
										<c:if test="${fn:contains(gzwsession_code,',bimWork_yblrzxzTb_d,')==true }">
											<a href="javascript:void(0)" onclick="del('${sys.id}')" >删除</a>
										</c:if>
									</c:if>
								</td>
								 -->
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
				<!-- <button class="btn btn-default model model_btn_close" style="position:absolute;left:1200px;">关闭</button>	 -->			
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
	
		$(' input.time1').jeDate(
			{
				format:"YYYY-MM-DD",
			}
		)
		$(' input.time2').jeDate(
			{
				format:"YYYY-MM-DD",
			}
		)
	
		function _query()
		{
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
		
		function exportout(){
			var form = document.forms[0];
			with (form) {
				form.action = '${pageContext.request.contextPath}/reportcommon/downloadtemplet?_url=${templet.filePath }&_name=${templet.fileName }';
				form.method = "post";
				form.submit();
			}
		}

		function del(id){
			win.confirm('确定要删除？',function(r){
				if(r){
					var url = "${mapurl}/delete?id=" + id;
					$.post(url, function(data) {
					var commitresult=JSON.parse(data);
						if(commitresult.result == true){
							win.successAlert('删除成功！');
							_query();
						}else
						{
							win.errorAlert(commitresult.exceptionStr,function(){window.location.reload(true);});
						}
						
					});
				}
			});
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
		
		$("#backId").click(function(){
		
			var form = document.forms[0];
			form.target = "_self";
			form.action = "/shanghai-gzw/reportFinancingProjectProgress/list";
			form.method = "post";
			form.submit();	
		
		});

	</script>
</html>