<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>民事案件查询</title>
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/css/font/iconfont.css"/>" />
		<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">
		<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
		<style type="text/css">
			.search_title {
			    display: inline-block;
			    width: 10em;
			    padding: 0 !important;
			    text-align: right;
			}
			body{
				padding:0;
			}
			h4{
				margin: 20px 40px;
			}
			.table_content {
			    margin: 0 30px;
			}
		</style>
	</head>
	<body class="hn_grey">
		<h4>民事案件查询</h4>
		<div class="table_content">
		
		<form id="form1"  class="row"  >
			<span class="col-md-4" style="letter-spacing: -1px;">案发起始日期：
				<input  type="text" name="caseDateStart" id="caseDateStart"  readonly="true" class="time" value="${caseDateStart}"/>
			</span>
			<span class="col-md-4" style="letter-spacing: -1px;">案发结束日期：
				<input  type="text" name="caseDateEnd" id="caseDateEnd"  readonly="true" class="time" value="${caseDateEnd}"/>
			</span>
			<span class="col-md-4">调处阶段：
				<select  name="mediatingStage" id="mediatingStage" class="selectpicker" >
					<option value="" <c:if test="${entity.mediatingStage eq ''}">selected</c:if>></option>
					<option value="1" <c:if test="${entity.mediatingStage eq '1'}">selected</c:if>>一审</option>
					<option value="2" <c:if test="${entity.mediatingStage eq '2'}">selected</c:if>>二审</option>
					<option value="3" <c:if test="${entity.mediatingStage eq '3'}">selected</c:if>>再审</option>
					<option value="4" <c:if test="${entity.mediatingStage eq '4'}">selected</c:if>>执行</option>
					<option value="5" <c:if test="${entity.mediatingStage eq '5'}">selected</c:if>>结案</option>
				</select>
			</span>
			<br>
			<%-- <span class="col-md-4">审核状态：
				<select  name="approvalState" id="approvalState" class="selectpicker" >
					<option value="" <c:if test="${entity.approvalState eq ''}">selected</c:if>></option>
					<option value="1" <c:if test="${entity.approvalState eq '1'}">selected</c:if>>新增</option>
					<option value="2" <c:if test="${entity.approvalState eq '2'}">selected</c:if>>审核中</option>
					<option value="3" <c:if test="${entity.approvalState eq '3'}">selected</c:if>>审核通过</option>
					<option value="4" <c:if test="${entity.approvalState eq '4'}">selected</c:if>>审核未通过</option>
					
				</select>
			</span> --%>
			
			
			<span class="col-md-4" style="letter-spacing: -1px;">案件归属单位：
			<%-- <input  type="text" name="vcCompanyName" id="vcCompanyName" value="${entity.vcCompanyName}"  /> --%>
			<input type="hidden" id="vcCompanyId" name="vcCompanyId" value="${entity.vcCompanyId}" >
				<input name="vcCompanyName" id="vcCompanyName" value="${entity.vcCompanyName}" readonly="readonly">
				<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
					<ul id="com_ztree" class="ztree">
					</ul>
			   </div>
			   <i class="clear iconfont icon-guanbi"></i>
			
			
			</span>
				<span class="col-md-4">诉讼地位：
				<select name="litigation" id="litigation" class="selectpicker" >
									<option value="" <c:if test="${entity.litigation eq ''}">selected</c:if>></option>
					<option value="1" <c:if test="${entity.litigation eq '1'}">selected</c:if>>主诉</option>
					<option value="2" <c:if test="${entity.litigation eq '2'}">selected</c:if>>被诉</option>
					<option value="3" <c:if test="${entity.litigation eq '3'}">selected</c:if>>申请人</option>
					<option value="4" <c:if test="${entity.litigation eq '4'}">selected</c:if>>被申请人</option>
					
				</select>
			</span>
			
			<span class="col-md-4">涉案金额：

			<select name="amountSection" id="amountSection" class="selectpicker" ">
					<option value="0" <c:if test="${amountSection eq ''}">selected</c:if>> </option>
					<option value="1" <c:if test="${amountSection eq '1'}">selected</c:if>>10万元以下</option>
					<option value="2" <c:if test="${amountSection eq '2'}">selected</c:if>>10-50万元</option>
					<option value="3" <c:if test="${amountSection eq '3'}">selected</c:if>>50-100万元</option>
					<option value="4" <c:if test="${amountSection eq '4'}">selected</c:if>>100-500万元</option>
					<option value="5" <c:if test="${amountSection eq '5'}">selected</c:if>>500-1000万元</option>
					<option value="6" <c:if test="${amountSection eq '6'}">selected</c:if>>1000万元以上</option>
				</select>
			</span>
			
		
			
			<%-- <span class="col-md-4">案由：
			<input  type="text" name="reason" id="reason" value="${entity.reason}"  />
			</span> --%>
		
			
				<span class="col-md-4">
				<span class="search_title" style="width: 9em;text-align: left">受案单位所在地: </span>
				<input type="text" name="province" value="${entity.province}" style="width: calc(100% - 10em);"/>
			</span>
			<%-- <span class="col-md-4">审核状态：
				<select  name="approvalState" id="approvalState" class="selectpicker" >
					<option value="" <c:if test="${entity.approvalState eq ''}">selected</c:if>></option>
					<option value="0" <c:if test="${entity.approvalState eq '0'}">selected</c:if>>新增</option>
					<option value="1" <c:if test="${entity.approvalState eq '1'}">selected</c:if>>审核中</option>
					<option value="2" <c:if test="${entity.approvalState eq '2'}">selected</c:if>>审核通过</option>
					<option value="3" <c:if test="${entity.approvalState eq '3'}">selected</c:if>>审核未通过</option>
				</select>
			</span> --%>
			<span class="col-md-4">案件类型：
				<select name="type" id="casetype" class="selectpicker" >
				<option value="" <c:if test="${entity.type eq ''}">selected</c:if>></option>
					<option value="1" <c:if test="${entity.type eq '1'}">selected</c:if>>合同纠纷</option>
					<option value="2" <c:if test="${entity.type eq '2'}">selected</c:if>>劳动纠纷</option>
					<option value="3" <c:if test="${entity.type eq '3'}">selected</c:if>>侵权纠纷</option>
					<option value="4" <c:if test="${entity.type eq '4'}">selected</c:if>>行政纠纷</option>
					<option value="5" <c:if test="${entity.type eq '5'}">selected</c:if>>其他纠纷</option>
				</select>
			</span>
			
			<span class="col-md-4" id="reason1">
				<span class="search_title" style="width:3em;text-align: left">案由: </span>
				<input type="text" name="reason" id="reasoninput1" value="${entity.reason}" style="width: calc(100% - 10em);"/>
			</span>
			
			
			<span class="col-md-4" style="display: none;" id="reason2">
				<span class="search_title" style="width:3em;text-align: left">案由: </span>
				
				<select name="reason" id="reasoninput2" class="selectpicker" >
				<option value=""></option>
				<c:forEach items="${reasonlist}" var="r">
				
				<option value="${r.num}" <c:if test="${entity.type eq '1'}"><c:if test="${entity.reason == r.num}">selected</c:if></c:if>>${r.description}</option>	
				</c:forEach>
				</select>
			
			</span>
			
			<span class="col-md-4">
				<span class="search_title" style="width: 5em;text-align: left">判决结果: </span>
				<input type="text" name="verdictResult" value="${entity.verdictResult}" style="width: calc(100% - 10em);"/>
			</span>

			<span class="col-md-4">是否问责：
				<select name="isAccountability" id="isAccountability" class="selectpicker" >
				<option value="" <c:if test="${entity.isAccountability eq ''}">selected</c:if>></option>
				<option value="1" <c:if test="${entity.isAccountability eq '1'}">selected</c:if>>是</option>
				<option value="0" <c:if test="${entity.isAccountability eq '0'}">selected</c:if>>否</option>	
				</select>
			</span>
		
			
			<%-- <span class="col-md-4">判决结果：
				<select name="verdictResult" id="verdictResult" class="selectpicker" >
					<option value="" <c:if test="${entity.verdictResult eq ''}">selected</c:if>></option>
					<option value="1" <c:if test="${entity.verdictResult eq '1'}">selected</c:if>>测试</option>
					<option value="2" <c:if test="${entity.verdictResult eq '2'}">selected</c:if>>ok</option>
					<option value="3" <c:if test="${entity.verdictResult eq '3'}">selected</c:if>>结果666</option>
				</select>
			</span> --%>
			
			
			
			
			
			<span class="col-md-4">
				<span class="search_title" style="width: 6em;text-align: left">承办法务 :</span>
				<input type="text" name="lawWork" value="${entity.lawWork}" style="width: calc(100% - 10em);"/>
			</span>
			<%-- <span class="col-md-4">受案单位所在地：
				<select name="province" id="province" class="selectpicker" >
					<option value="" <c:if test="${entity.province eq ''}">selected</c:if>></option>
					<option value="1" <c:if test="${entity.province eq '1'}">selected</c:if>>海南</option>
					<option value="2" <c:if test="${entity.province eq '2'}">selected</c:if>>江苏</option>
					<option value="3" <c:if test="${entity.province eq '3'}">selected</c:if>>海口</option>
				</select>
			</span> --%>
			
			<span class="col-md-4">
				<span class="search_title" style="width: 6em;text-align: left"">受案机构 :</span>
				<input type="text" name="admissibleCourt" value="${entity.admissibleCourt}" style="width: calc(100% - 10em);"/>
			</span>
			<!-- 基本案情 -->
			<span class="col-md-4">
				<span class="search_title" style="width: 6em;text-align: left"">基本案情 :</span>
				<input type="text" name="baseMessage" value="${entity.baseMessage}" style="width: calc(100% - 10em);"/>
			</span>
			<span class="col-md-4" style="letter-spacing: -1px;">是否为重大案件：
				<select name="isMajorCase" id="isMajorCase" class="selectpicker" >
				<option value="" <c:if test="${entity.isMajorCase eq ''}">selected</c:if>></option>
				<option value="1" <c:if test="${entity.isMajorCase eq '1'}">selected</c:if>>是</option>
				<option value="0" <c:if test="${entity.isMajorCase eq '0'}">selected</c:if>>否</option>	
				</select>
			</span>
			
			
			
			
			
			<%-- <span class="col-md-4">原告/申请人/第三人:
				<select name="plaintiff" id="plaintiff" class="selectpicker" >
					<option value="" <c:if test="${entity.plaintiff eq ''}">selected</c:if>></option>
					<option  <c:if test="${entity.plaintiff }">selected</c:if>>原告</option>
					<option  <c:if test="${entity.plaintiff }">selected</c:if>>申请人</option>
					<option  <c:if test="${entity.plaintiff }">selected</c:if>>第三人</option>
				</select>
			</span> --%>
			<span class="col-md-4">原告/申请人/第三人:
				<input type="text" name="plaintiff" value="${entity.plaintiff}" style="width: calc(100% - 10em);"/>
			</span>
			<span class="col-md-4">被告/被申请人/第三人：
				<input type="text" name="defendant" value="${entity.defendant}" style="width: calc(100% - 10em);"/>
			</span>
			<%-- <span class="col-md-4">被告/被申请人/第三人 (多人)：
				<select name="defendant" id="defendant" class="selectpicker" >
					<option value="" <c:if test="${entity.defendant eq ''}">selected</c:if>></option>
					<option <c:if test="${entity.defendant }">selected</c:if>>被告</option>
					<option <c:if test="${entity.defendant }">selected</c:if>>被申请人</option>
					<option <c:if test="${entity.defendant }">selected</c:if>>第三人(多人)</option>
					<option value="3" <c:if test="${entity.defendant eq '3'}">selected</c:if>>第三人(多人)</option>
				</select>
			</span> --%>
			<span class="col-md-4" style="letter-spacing: -1px; text-align: left;">是否进行/完成成因分析:
				<select name="isAnalysis" id="isAnalysis" class="selectpicker"  >
				<option value="" <c:if test="${entity.isAnalysis eq ''}">selected</c:if>></option>
				<option value="1" <c:if test="${entity.isAnalysis eq '1'}">selected</c:if>>是</option>
				<option value="0" <c:if test="${entity.isAnalysis eq '0'}">selected</c:if>>否</option>	
				</select>
			</span>
		<%-- <span class="col-md-4">承办法官/仲裁员：
				<input type="text" name="judge" value="${entity.judge}" style="width: calc(100% - 10em);"/>
			</span> --%>
			<span class="col-md-4">承办法官/仲裁员： 
				<input type="text" name="judge" value="${entity.judge}" style="width: calc(100% - 10em);"/>
			</span>
			<%-- <span class="col-md-4" style="letter-spacing: -1px;width: 20%">承办法官/仲裁员：
				<select name="judge" id="judge" class="selectpicker" >
					<option value="" <c:if test="${entity.judge eq ''}">selected</c:if>></option>
					<option  <c:if test="${entity.judge }">selected</c:if>>承办法官</option>
					<option  <c:if test="${entity.judge }">selected</c:if>>仲裁员</option>	
				</select>
			</span> --%>
			
			
			
			<%-- <span class="col-md-4">
				<span class="search_title" style="width: 8em;text-align: left">原告/申请人/第三人:</span>
				<input type="text" name="plaintiff" value="${entity.plaintiff}" style="width: calc(100% - 10em);"/>
			</span> --%>
			<%-- <span class="col-md-4">
				<span class="search_title" style="width: 8em;text-align: left">被告/被申请人/第三人 (多人):</span>
				<input type="text" name="defendant" value="${entity.defendant}" style="width: calc(100% - 10em);"/>
			</span> --%>
			<%-- <span class="col-md-4">受案机构：
				<select name="admissibleCourt" id="admissibleCourt" class="selectpicker" >
					<option value="" <c:if test="${entity.admissibleCourt eq ''}">selected</c:if>></option>
					<option value="1" <c:if test="${entity.admissibleCourt eq '1'}">selected</c:if>>海口法院</option>
					<option value="2" <c:if test="${entity.admissibleCourt eq '2'}">selected</c:if>>第一法院</option>
					<option value="3" <c:if test="${entity.admissibleCourt eq '3'}">selected</c:if>>三亚市城郊人民法院</option>
					
				</select>
			</span> --%>
			
			<!--下面写个js export() 函数 -->
			<div class="form_div col-md-12">	
				<c:if test="${fn:contains(gzwsession_code,',bimWork_msajcx_query,')==true }">
						<input type="button" value="查询" class="form_btn" id="qrybtn" onclick="_query()">
				</c:if>
				 <c:if test="${fn:contains(gzwsession_code,',bimWork_msajcx_export,')==true }"> 
						 <input type="button" value="导出" class="form_btn" id="exportbtn" onclick="_export()"> 
				 </c:if>	 
			</div>
		</form>
		
		
			<div class="tablebox">
				<table>
					<tr class="table_header">
						
						<th style="width:5%;">序号</th>
						<th style="width:5%;">原告/申请人/第三人</th>
						<th style="width:10%;">被告/申请人/第三人</th>
						<th style="width:10%;">是否为重大案件</th>
						<th style="width:10%;">涉案金额</th>
						<th style="width:8%;">案件类型</th>
						<th style="width:5%;">调处阶段</th>
						<th style="width:5%;">审核状态</th>
						<th style="width:5%;">操作</th>
						
					</tr>
					<c:if test="${!empty requestScope.msgPage.list }">
					<c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
						<c:forEach items="${ requestScope.msgPage.list}" var="case" varStatus="status">
							<tr>
							
								<td style="text-align:center;">
									${recordNumber + status.index + 1 }
								</td>
								<td style="text-align:center;">
								   ${case.plaintiff}
								</td>
								<td style="text-align:center;">
									 ${case.defendant}
								</td>
								<td style="text-align:center;word-wrap:break-word;word-break:break-all;">
									<c:if test="${case.isMajorCase eq '0'}">否</c:if>
									<c:if test="${case.isMajorCase eq '1'}">是</c:if>
								</td>
								<td style="text-align:center;">
									${case.amount}
								</td>
								<td style="text-align:center;">
									<c:if test="${case.type eq '1'}">合同纠纷</c:if>
									<c:if test="${case.type eq '2'}">劳动纠纷</c:if>
									<c:if test="${case.type eq '3'}">侵权纠纷</c:if>
									<c:if test="${case.type eq '4'}">行政纠纷</c:if>
									<c:if test="${case.type eq '5'}">其他纠纷</c:if>
								</td>
								<td style="text-align:center;word-wrap:break-word;word-break:break-all;">
									<c:if test="${case.mediatingStage eq '1'}">一审</c:if>
									<c:if test="${case.mediatingStage eq '2'}">二审</c:if>
									<c:if test="${case.mediatingStage eq '3'}">再审</c:if>
									<c:if test="${case.mediatingStage eq '4'}">执行</c:if>
									<c:if test="${case.mediatingStage eq '5'}">结案</c:if>
								</td>
								
								<td style="text-align:center;word-wrap:break-word;word-break:break-all;">
									<c:if test="${case.approvalState eq '0'}">未上报</c:if>
									<c:if test="${case.approvalState eq '1'}">待审核</c:if>
									<c:if test="${case.approvalState eq '2'}">审核通过</c:if>
									<c:if test="${case.approvalState eq '3'}">审核未通过</c:if>
									
								</td>
								<td>
								    <c:if test="${fn:contains(gzwsession_code,',bimWork_msajcx_show,')==true }">
									    <a href="javascript:void(0)" onclick="mload('${pageContext.request.contextPath}/bimr/case/viewCivilcase?id=${case.id}')">查看</a>
								    </c:if>
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
				</table>
				<jsp:include page="../../system/page.jsp"/>
			</div>
		</div>
		<jsp:include page="../../system/modal.jsp"/>
	</body>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script type="text/javascript">
	var timeoutId;
		$('#vcCompanyName').on('focus click',function(){
			$(this).next('.com_ztree_box').css('display','block')
		}).parent().on('mouseenter',function(){
			clearTimeout(timeoutId)
		}).on('mouseleave',function(){
			clearTimeout(timeoutId)
			timeoutId = setTimeout(function(el){
				$(el).find('.com_ztree_box').css('display','none')
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
		     //--所有企业数据	
		    var com_data = ${allCompanyTree};
			zTreeObj = $.fn.zTree.init($("#com_ztree"), com_ztree_setting, com_data);
			//将每一个tree节点进行遍历，获取Id，与此角色已保存的Id（checked_data）进行比较，Id相同择设为选中
			var treeObj = $.fn.zTree.getZTreeObj("com_ztree");
			var nodes = treeObj.getNodes();
			//transformToArray()此方法获取所有节点（父节点和子节点）
			var childrenNodes = treeObj.transformToArray(nodes);
			if(childrenNodes[0]!=null){
				treeObj.expandNode(childrenNodes[0],true);
			}
		});
		$("#com_ztree").click(function(){
			setTimeout(function(){
				if($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0]) {
					$("#vcCompanyId").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id)
					$("#vcCompanyName").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
				}
		   	});
		});
	
	
	   $('.clear').on('click',function(){
			$(this).siblings("input[name='vcCompanyName']").val("");
			$(this).siblings("input[name='vcCompanyId']").val("");
		});
	
		function _query() {
		
		
			var beginDate=$("#caseDateStart").val();    
            var endDate=$("#caseDateEnd").val();    
 			var d1 = new Date(beginDate.replace(/\-/g, "\/"));    
 			/* 将-这个转换为/ */
 			var d2 = new Date(endDate.replace(/\-/g, "\/"));    
    
  			if(beginDate!=""&&endDate!=""&&d1 >d2)    
 			{    
  			parent.win.generalAlert("案发起始日期时间不能大于结束时间！");    
  			return false;    
 			}  
 			
 			
			var form = document.forms[0];
			form.action = "${searchurl}";
			form.method = "post";
			form.submit();
		};
		$("#queryBtn").click(function() {
			if(checkTime($("#doDate").val(),$("#doDate2").val()) == false){
				return false;
			}
			_query();	
		});
		$('input.time').jeDate({
			format:"YYYY-MM-DD"	
		});
		
	/* 动态绑定 */
	$(function(){
		
		$("#plaintiff").val('${entity.plaintiff}');
		$("#defendant").val('${entity.defendant}');
		$("#judge").val('${entity.judge}');
		$("#baseMessage").val('${entity.baseMessage}');   
		
	})
		function _export()
		{
		
		 var form = document.forms[0];
			form.action = "${pageContext.request.contextPath}/bimr/case/civilcaseExport";
			form.method = "post";
			form.submit();
		}
		
		
		function getId() {
			var ids = document.getElementById('ids').value.split(',');
			var chk_value = [];
			var userid = {};
			for ( var i = 0; i < ids.length; ++i) {
				if (!userid[ids[i]])
					chk_value.push(ids[i]);
				userid[ids[i]] = true;
			}
			$('input[name="checkbox"]:checked').each(function() {
				if (!userid[$(this).val()])
					chk_value.push($(this).val());
			});
			$("#ids").val(chk_value);
		};
		var chknum = 0;
		function chk() {
			if (chknum == 0) {
				$("input[name='checkbox']").prop("checked", true);
				chknum = 1;
			} else {
				$("input[name='checkbox']").prop("checked", false);
				chknum = 0;
			}
		};
		$("#topBtn").click(function() {
			var chk_value = [];
			$('input[name="checkbox"]:checked').each(function() {
				chk_value.push($(this).val());
			});
			if (chk_value.length == 0) {
				parent.win.generalAlert('你还没有选择任何内容！');
			} else {
				parent.win.confirm('确定要置顶？', function(r) {
					if (r) {
						var url = "${pageContext.request.contextPath}/knowledgeBase/top?ids="+ chk_value + "";
						$.post(url, function(data) {
							parent.win.successAlert('置顶成功！');
							window.location.reload();
							$('#all').prop("checked", false);
							$('input[name="checkbox"]:checked').each(
								function() {
									$(this).prop("checked", false);
								});
						});
					}
				});
			}
			return false;
		});
		$("#delTopBtn").click(function() {
			var chk_value = [];
			$('input[name="checkbox"]:checked').each(
					function() {
						chk_value.push($(this).val());
					});
			if (chk_value.length == 0) {
				parent.win.generalAlert('你还没有选择任何内容！');
			} else {
				parent.win.confirm('确定要撤除置顶？',function(r) {
					if (r) {
						var url = "${pageContext.request.contextPath}/knowledgeBase/delTop?ids="+ chk_value+ "";
						$.post(url, function(data) {
							parent.win.successAlert('置顶撤除成功！');
							window.location.reload();
							$('#all').prop("checked", false);
							$('input[name="checkbox"]:checked').each(
								function() {
									$(this).prop("checked",false);
							});
						});
					}
				});
			}
			return false;
		});
		$(function(){
			if($("#casetype").val()!=1){
				$("#reason1").css('display','');
				$("#reason2").css('display','none');
				$("#reasoninput2").val(null);
				$("#reasoninput2").removeAttr('name');
			}else{
				$("#reason1").css('display','none');
				$("#reason2").css('display','');
				$("#reasoninput1").removeAttr('name');
			}
		     
			$("#casetype").change(function(){
				$("#reasoninput1").val("");
				var value=$(this).val();
				if(value==1){
				$("#reason1").css('display','none');
				$("#reason2").css('display','');
				$("#reasoninput1").removeAttr('name');
				$("#reasoninput2").attr('name','reason');
				}else{
				$("#reason1").css('display','');
				$("#reason2").css('display','none');
				$("#reasoninput2").removeAttr('name');
				$("#reasoninput1").attr('name','reason');
				}
			});
		})
	</script>
</html>