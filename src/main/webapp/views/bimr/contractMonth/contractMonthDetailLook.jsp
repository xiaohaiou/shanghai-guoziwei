<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>合同信息查询</title>
<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
<link rel="stylesheet" href="<c:url value="/css/font/iconfont.css"/>" />
  <link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
<!-- 库|插件 -->
<!-- 新加样式 -->
<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/zTreeStyle.css"/>">
<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
<script src="<c:url value="/js/jquery.ztree.all.min.js"/>"
	type="text/javascript"></script>
<script src="<c:url value="/js/jquery.form.js"/>" charset="utf-8"></script>
</head>
<body>
	<h4>合同信息查询</h4>
	<div class="table_content">

		<form id="form1" class="row">
				<span class="col-md-4"><span style="text-align: left;width: 6em"  class="search_title">生效日期：</span> 
					<input type="text" id="startTime" name="startTime" value="${entity.startTime }" readonly="true" class="time" style="width:calc(50% - 5em);"/> 
					~ 
					<input type="text" id="endTime" name="endTime" value="${entity.endTime }" readonly="true" class="time" style="width:calc(50% - 5em);"/>
				</span>
				
				<span class="col-md-4"><span class="search_title" style="padding-left: 10px;text-align: left;width: 6em">签署单位：</span>  
				<input type="hidden" id="compId" name="compId" value="${entity.compId }"> <input
					name="compName" id="compName" value="${entity.compName }" readonly
					title="${compName }">
					<div class="com_ztree_box"
						style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
						<ul id="com_ztree" class="ztree">

						</ul>
					</div> <i class="clear iconfont icon-guanbi"></i> 
				</span>
					<span class="col-md-4"><span style="text-align: left;width: 6em" class="search_title">重大合同：</span>
					<select name="isMajorContract" id="isMajorContract" class="selectpicker">
							<option value="">全部</option>
							<option value="false">否</option>
							<option value="true">是</option>
					</select> 
				</span>
				
				<span class="col-md-4">
				<span class="search_title" style="width: 6em;text-align: left;width: 6em">合同类型: </span>
				<input type="text" name="contractTypeText" value="${entity.contractTypeText}" style="width: calc(100% - 10em);"/>
			</span>
			<span class="col-md-4">
				<span class="search_title" style="width: 6em;text-align: left;width: 6em">合同类别: </span>
				<input type="text" name="contractKindText" value="${entity.contractKindText}" style="width: calc(100% - 10em);"/>
			</span>
			<span class="col-md-4" ><span style="text-align: left;width: 6em;" class="search_title">是否涉诉：</span>
					<select name=isInvolving id="isInvolving" class="selectpicker">
							<option value="">全部</option>
							<option value="false">否</option>
							<option value="true">是</option>
					</select> 
				</span>
				<span class="col-md-4">
				<span class="search_title" style="width: 6em;text-align: left;width: 8em;">我方违约情况: </span>
				<input type="text" name="ourDefault" value="${entity.ourDefault}" style="width: calc(100% - 10em);"/>
			</span>
			
			<span class="col-md-4">
				<span class="search_title" style="width: 6em;text-align: left;width: 8em;">对方违约情况: </span>
				<input type="text" name="otherDefault" value="${entity.otherDefault}" style="width: calc(100% - 10em);"/>
			</span>
			<span class="col-md-4">
				<span class="search_title" style="width: 6em;text-align: left;width: 5em;">风险描述: </span>
				<input type="text" name="riskDescription" value="${entity.riskDescription}" style="width: calc(100% - 10em);"/>
			</span>
				<span class="col-md-4">
				<span class="search_title" style="width: 9em;text-align: left;width: 6em;">风险成因: </span>
				<input type="text" name="riskCause" value="${entity.riskCause}" style="width: calc(100% - 10em);"/>
			</span>
			<span class="col-md-4" >风险等级：
			<select name="riskGrade" id="riskGrade" class="selectpicker">
					<option value="0" <c:if test="${riskGrade eq ''}">selected</c:if>></option>
					<option value="1" <c:if test="${riskGrade eq '5'}">selected</c:if>>风险失控类（5级）</option>
					<option value="2" <c:if test="${riskGrade eq '4'}">selected</c:if>>风险障碍类（4级）</option>
					<option value="3" <c:if test="${riskGrade eq '3'}">selected</c:if>>风险可疑类（3级）</option>
					<option value="4" <c:if test="${riskGrade eq '2'}">selected</c:if>>风险关注类（2级）</option>
					<option value="5" <c:if test="${riskGrade eq '1'}">selected</c:if>>风险正常类（1级）</option>
					<%-- <option value="6" <c:if test="${ourOverPay eq '6'}">selected</c:if>>1000万元以上</option> --%>
				</select>
			</span>
				<span class="col-md-4">是否属于集团内部企业间合同：
					<select name="isCom" id="isCom" class="selectpicker">
							<option value="">全部</option>
							<option value="false">否</option>
							<option value="true">是</option>
					</select>
				</span>
				<!-- <span class="col-md-4"><span class="search_title">合同类型：</span>
					<select name="contractTypeText" id="contractTypeText" class="selectpicker">
							<option value="true">海航实业</option>	
					</select> 
				</span> -->
			
			<%-- <span class="col-md-4"><span class="search_title">合同类型：</span>
				<select name="contractTypeText" class="selectpicker">
					<option value="">全部</option>
					<c:forEach items="${requestScope.contractTypeText}" var="result">
						<option value="${result.id }"
							<c:if test="${entity.contractTypeText == result.id}">selected="selected"</c:if>>
							${result.description}</option>
					</c:forEach>
				</select> 
			</span> --%>
			
			
				
				<!-- 风险等级 -->
			<%-- <span class="col-md-4">
				<span class="search_title" style="width: 9em;">风险等级: </span>
				<input type="text" name="riskGrade" value="${entity.riskGrade}" style="width: calc(100% - 10em);"/>
			</span> --%>
		
	
		
			</span>
			
			
			
			
			
			<span class="col-md-4">是否属于重点关注合同：
					<select name="isFocus" id="isFocus" class="selectpicker">
							<option value="">全部</option>
							<option value="false">否</option>
							<option value="true">是</option>
					</select> 
			</span>
			
			
			
			
			<!-- 风险处置方案及进展 -->
			<span class="col-md-4">风险处置方案及进展:
				<input type="text" name="riskManage" value="${entity.riskManage}" style="width: calc(100% - 10em);"/>
			</span>
			<!-- 风险是否排除及排除情况 -->
			
			<span class="col-md-4">风险是否排除及排除情况:
				<input type="text" name="riskExcluded" value="${entity.riskExcluded}" style="width: calc(100% - 10em);"/>
			</span>
			
			<span class="col-md-4">我方逾期应付款总额：

			<select name="ourOverPay" id="ourOverPay" class="selectpicker" ">
					<option value="0" <c:if test="${ourOverPay eq ''}">selected</c:if>></option>
					<option value="1" <c:if test="${ourOverPay eq '1'}">selected</c:if>>100万元以下</option>
					<option value="2" <c:if test="${ourOverPay eq '2'}">selected</c:if>>100-500万元</option>
					<option value="3" <c:if test="${ourOverPay eq '3'}">selected</c:if>>500-1000万元</option>
					<option value="4" <c:if test="${ourOverPay eq '4'}">selected</c:if>>1000-5000万元</option>
					<option value="5" <c:if test="${ourOverPay eq '5'}">selected</c:if>>5000万元以上</option>
					<%-- <option value="6" <c:if test="${ourOverPay eq '6'}">selected</c:if>>1000万元以上</option> --%>
				</select>
			</span>
			<span class="col-md-4">对方逾期应收款总额：

			<select name="otherOverPay" id="otherOverPay" class="selectpicker" ">
					<option value="0" <c:if test="${otherOverPay eq ''}">selected</c:if>></option>
					<option value="1" <c:if test="${otherOverPay eq '1'}">selected</c:if>>100万元以下</option>
					<option value="2" <c:if test="${otherOverPay eq '2'}">selected</c:if>>100-500万元</option>
					<option value="3" <c:if test="${otherOverPay eq '3'}">selected</c:if>>500-1000万元</option>
					<option value="4" <c:if test="${otherOverPay eq '4'}">selected</c:if>>1000-5000万元</option>
					<option value="5" <c:if test="${otherOverPay eq '5'}">selected</c:if>>5000万元以上</option>
					<%-- <option value="6" <c:if test="${otherOverPay eq '6'}">selected</c:if>>1000万元以上</option> --%>
				</select>
			</span>
			<span class="col-md-4" >是否存在违约/异常：
					<select name="isDefault" id="isDefault" class="selectpicker">
							<option value="">全部</option>
							<option value="false">否</option>
							<option value="true">是</option>
					</select> 
				</span>
			
				
				
		
			
			<br>
			<br>
			<div class="form_div col-md-12">
			    <c:if test="${fn:contains(gzwsession_code,',bimWork_htxxcx_query,')==true }"> 
						<input type="button" value="查询" class="form_btn" id="qrybtn"
							onclick="_query()">
				</c:if> 
				<%-- <input type="button" value="导出" class="form_btn" id="exportbtn" onclick="mload('${mapurl}/export')"> --%>
			</div>
		</form>
		<div class="tablebox">
			<table>
				<tr class="table_header">
					<th>序号</th>
					<th>生效日期</th>
					<th>单位名称</th>
					<th>合同名称</th>
					<th>合同类型</th>
					<th>签署单位</th>
					<th>是否履约异常</th>
					<th>是否重大合同</th>
					
					<th>操作</th>
				</tr>
				<c:if test="${!empty requestScope.msgPage.list }">
					<c:set var="recordNumber"
						value="${(requestScope.msgPage.pageNum - 1) * 10 }" />
					<c:forEach items="${ requestScope.msgPage.list}" var="resultList"
						varStatus="status">
						<tr>
							<!-- 序号 -->
							<td style="text-align: center;">${recordNumber+ status.index + 1 }</td>
							<td style="text-align: center;">${resultList.contractSignDate}</td>
							<td style="text-align: left;">${resultList.contractSubordinateCompany }</td>
							<td style="text-align: left;">${resultList.contractName }</td>
							<td style="text-align: center;">${resultList.contractTypeText }</td>
							<td style="text-align: left;">${resultList.ourContractSubject }</td>
							<td style="text-align: center;">${resultList.isDefault?"是":"否" }</td>
							<td style="text-align: center;">${resultList.isMajorContract?"是":"否" }</td>
							
							<!-- 操作按钮-->
							<td>
							      <c:if test="${fn:contains(gzwsession_code,',bimWork_htxxcx_show,')==true }">
										<a href="javascript:void(0)"
										onclick="mload('${pageContext.request.contextPath}/bimr/contractmonth/detailView?id=${resultList.id}')">查看</a>
							      </c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty requestScope.msgPage.list}">
					<tr>
						<td colspan="10" align="center">查询无记录</td>
					</tr>
				</c:if>
			</table>
			<jsp:include page="/views/system/page.jsp" />
		</div>
	</div>
	<jsp:include page="/views/system/modal.jsp" />
</body>
<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/vaild.js"/>"
	charset="utf-8"></script>
<script type="text/javascript">
	$(function() {
		var zTreeObj;
		var com_ztree_setting = {
			check : {
				enable : false,
				chkStyle : "checkbox",
				chkboxType : {
					"Y" : "ps",
					"N" : "ps"
				}
			},
			data : {
				simpleData : {
					enable : true,
					idKey : "id",
					pIdKey : "pId",
					rootPId : 0
				}
			}
		};

		var timeoutId;
		//公司选择初始化
		$('#compName').on('focus click', function() {
			$(this).next('.com_ztree_box').css('display', 'block')
		}).parent().on('mouseenter', function() {
			clearTimeout(timeoutId)
		}).on('mouseleave', function() {
			clearTimeout(timeoutId)
			timeoutId = setTimeout(function(el) {
				$(el).find('.com_ztree_box').css('display', 'none')
			}, 3e2, this);
		});

		//所有企业数据	
		var com_data = ${allCompanyTree};
		zTreeObj = $.fn.zTree.init($("#com_ztree"), com_ztree_setting, com_data);
		//将每一个tree节点进行遍历，获取Id，与此角色已保存的Id（checked_data）进行比较，Id相同择设为选中
		var treeObj = $.fn.zTree.getZTreeObj("com_ztree");
		var nodes = treeObj.getNodes();

		//transformToArray()此方法获取所有节点（父节点和子节点）
		var childrenNodes = treeObj.transformToArray(nodes);
		if (childrenNodes[0] != null)
			treeObj.expandNode(childrenNodes[0], true);

		//清空
		$('.clear').on('click', function() {
			$(this).siblings('input[name="compName"]').val('');
			$("#compId").val("");
			$("#compName").attr('title', '');
		});

		$("#com_ztree").click(
				function() {
					setTimeout(function() {
						if ($.fn.zTree.getZTreeObj("com_ztree")
								.getSelectedNodes()[0]) {
							$("#compId").val(
									$.fn.zTree.getZTreeObj("com_ztree")
											.getSelectedNodes()[0].id)
							$("#compName").val(
									$.fn.zTree.getZTreeObj("com_ztree")
											.getSelectedNodes()[0].name);
						}
					});

				})
	});

	$(' input.time').jeDate({
		format : "YYYY-MM-DD"
	});
	//-----------------------------------------按钮时间-----------------------------------------------
	function _query() {
    if(checkTime($("#startTime").val(),$("#endTime").val())){
      var form = document.forms[0];
	    form.action = "${searchurl}";
	    form.method = "post";
	    form.submit();
    }
	};
/* 动态绑定 */
	$(function(){
		
		$("#isDefault").val('${entity.isDefault}');
		$("#isMajorContract").val('${entity.isMajorContract}');
		$("#isCom").val('${entity.isCom}');
		$("#isFocus").val('${entity.isFocus}');
		$("#ourOverPay").val('${entity.ourOverPay}');
		$("#otherOverPay").val('${entity.otherOverPay}');
		$("#isInvolving").val('${entity.isInvolving}');
		$("#riskGrade").val('${entity.riskGrade}');
		
	})

  //校验开始时间与结束时间。 start > end, return false;
  function checkTime(start,end) {
  debugger;
    var flag = true;
    if (end != "" && start > end) {
      parent.win.generalAlert("结束日期不应在开始日期之前!");
      flag = false;
    };
    return flag;
  };
</script>
</html>