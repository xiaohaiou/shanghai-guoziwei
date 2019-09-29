<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="tablebox" style="overflow-x: auto">
	<table style="width: 250em">
		<tr class="table_header" id="reportMonthlyFinancingOutDetailTableTr">
			<th>序号</th>
			<!-- <th>成员公司</th>
			<th>所属核心企业</th> -->
			<th>承贷主体</th>
			<th>还贷银行</th>
			<th>融资类型</th>
			<th>融资类型明细</th>
			<th>还款日期</th>
			<th>还款金额(万元)</th>
			<th>币种</th>
			<th>操作</th>
		</tr>
		<c:if test="${not empty requestScope.msgPage.list }">
			<c:forEach items="${requestScope.msgPage.list }" var="node" varStatus="status">
				<tr>
					<td>${(requestScope.msgPage.pageNum-1)*5+status.count }</td>
					<%-- <td>${node.memberCompName }</td>
					<td>${node.coreCompName }</td> --%>
					<td>
						<input type="text" id="loanCompName" value="${node.loanCompName }" check="NotEmpty_string_._._._._." onblur="modifyEntity('${(msgPage.pageNum-1)*5+status.count }','reportMonthlyFinancingOutDetail',this)">
						<%-- <input type="hidden" id="loanCompId${status.count }_${status.count}" value="${node.loanCompId }" >
						<input type="text" id="loanCompName${status.count }_${status.count}" value="${node.loanCompName }" check="NotEmpty_string_._._._._." readonly="true">
						<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
							<ul id="out_com_ztree_${status.count }" class="ztree">
			
							</ul>
					    </div> --%>
					</td>
					<td>
						<input type="text" id="repayBank" value="${node.repayBank }" check="NotEmpty_string_._._._._." onblur="modifyEntity('${(msgPage.pageNum-1)*5+status.count }','reportMonthlyFinancingOutDetail',this)">
					</td>
					<td>
						<select id="financingType" onchange="modifyEntity('${(msgPage.pageNum-1)*5+status.count }','reportMonthlyFinancingOutDetail',this)">
							<c:forEach items="${financingType }" var="sys">
								<option value="${sys.id }" <c:if test="${sys.id == node.financingType }">selected</c:if>>${sys.description }</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<select id="financingTypeDetail" onchange="modifyEntity('${(msgPage.pageNum-1)*5+status.count }','reportMonthlyFinancingOutDetail',this)">
							<c:forEach items="${financingTypeDetail }" var="sys">
								<option value="${sys.id }" <c:if test="${sys.id == node.financingTypeDetail }">selected</c:if>>${sys.description }</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<input class="w25 date3" type="text" id="repayDate" readonly="true" check="NotEmpty_string_._._._._." value="${node.repayDate }" />
					</td>
					<td>
						<input type="text" id="repayAmount" value="${node.repayAmount }" check="NotEmpty_double_16_2_0_。_." onblur="modifyEntity('${(msgPage.pageNum-1)*5+status.count }','reportMonthlyFinancingOutDetail',this)">
					</td>
					<td>
						<select id="currency" onchange="modifyEntity('${(msgPage.pageNum-1)*5+status.count }','reportMonthlyFinancingOutDetail',this)">
							<c:forEach items="${currencyKind }" var="sys">
								<option value="${sys.id }" <c:if test="${sys.id == node.currency }">selected</c:if>>${sys.description }</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<a class="btn btn-primary model_btn_ok" onclick="del('${(msgPage.pageNum-1)*5+status.count }','reportMonthlyFinancingOutDetail')">删除</a>
					</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty requestScope.msgPage.list}">
			<tr>
				<td colspan="9" align="center">
					查询无记录
				</td>
			</tr>
		</c:if>
	</table>
</div>
<c:if test="${not empty requestScope.msgPage.list}">
	<jsp:include page="../page.jsp"/>
</c:if>
<script type="text/javascript" src="<c:url value="/js/vailds.js"/>" charset="utf-8"></script>
<script type="text/javascript">
	$(' input.date3').jeDate(
		{
			format:"YYYY-MM-DD",
			choosefun:function(elem, val, date) {changeDate3(val,$(elem).closest('tr').find('td')[0].innerText);},
				clearfun:function(elem, val) {changeDate3('',$(elem).closest('tr').find('td')[0].innerText);},            //清除日期后的回调, elem当前输入框ID, val当前选择的值
	  				okfun:function(elem, val, date) {changeDate3(val,$(elem).closest('tr').find('td')[0].innerText);}, 
		}
	)
	function changeDate3(val,text){
		 var obj = {};
		 obj.id="repayDate";
		 obj.value=val;
		 modifyEntity(text,'reportMonthlyFinancingOutDetail',obj);
	}
	
	/* var timeoutId1_1
	$('#loanCompName1_1').on('focus click',function(){
		$(this).next('.com_ztree_box').css('display','block')
	}).parent().on('mouseenter',function(){
		clearTimeout(timeoutId1_1)
	}).on('mouseleave',function(){
		clearTimeout(timeoutId1_1)
		timeoutId1_1 = setTimeout(function(el){
			$(el).find('.com_ztree_box').css('display','none')
		},3e2,this);
	})
	
	var timeoutId2_2
	$('#loanCompName2_2').on('focus click',function(){
		$(this).next('.com_ztree_box').css('display','block')
	}).parent().on('mouseenter',function(){
		clearTimeout(timeoutId2_2)
	}).on('mouseleave',function(){
		clearTimeout(timeoutId2_2)
		timeoutId2_2 = setTimeout(function(el){
			$(el).find('.com_ztree_box').css('display','none')
		},3e2,this);
	})
	
	var timeoutId3_3
	$('#loanCompName3_3').on('focus click',function(){
		$(this).next('.com_ztree_box').css('display','block')
	}).parent().on('mouseenter',function(){
		clearTimeout(timeoutId3_3)
	}).on('mouseleave',function(){
		clearTimeout(timeoutId3_3)
		timeoutId3_3 = setTimeout(function(el){
			$(el).find('.com_ztree_box').css('display','none')
		},3e2,this);
	})
	
	var timeoutId4_4
	$('#loanCompName4_4').on('focus click',function(){
		$(this).next('.com_ztree_box').css('display','block')
	}).parent().on('mouseenter',function(){
		clearTimeout(timeoutId4_4)
	}).on('mouseleave',function(){
		clearTimeout(timeoutId4_4)
		timeoutId4_4 = setTimeout(function(el){
			$(el).find('.com_ztree_box').css('display','none')
		},3e2,this);
	})
	
	var timeoutId5_5
	$('#loanCompName5_5').on('focus click',function(){
		$(this).next('.com_ztree_box').css('display','block')
	}).parent().on('mouseenter',function(){
		clearTimeout(timeoutId5_5)
	}).on('mouseleave',function(){
		clearTimeout(timeoutId5_5)
		timeoutId5_5 = setTimeout(function(el){
			$(el).find('.com_ztree_box').css('display','none')
		},3e2,this);
	})
	
	$("#out_com_ztree_1").click(function(){
		var text = $(this).closest('tr').find('td')[0].innerText;
		setTimeout(function(){
  				if($.fn.zTree.getZTreeObj("out_com_ztree_1").getSelectedNodes()[0])
				{
					 $("#loanCompId1_1").val($.fn.zTree.getZTreeObj("out_com_ztree_1").getSelectedNodes()[0].id)
					 $("#loanCompName1_1").val($.fn.zTree.getZTreeObj("out_com_ztree_1").getSelectedNodes()[0].name);
					 var obj = [];
					 var loanCompId = {};
					 loanCompId.id="loanCompId";
					 loanCompId.value=$("#loanCompId1_1").val();
					 var loanCompName = {};
					 loanCompName.id="loanCompName";
					 loanCompName.value=$("#loanCompName1_1").val();
					 obj.push(loanCompId);
					 obj.push(loanCompName);
					 modifyComp(text,'reportMonthlyFinancingOutDetail',obj);
				}
	   	});
		
	})
	$("#out_com_ztree_2").click(function(){
		var text = $(this).closest('tr').find('td')[0].innerText;
		setTimeout(function(){
  				if($.fn.zTree.getZTreeObj("out_com_ztree_2").getSelectedNodes()[0])
				{
					 $("#loanCompId2_2").val($.fn.zTree.getZTreeObj("out_com_ztree_2").getSelectedNodes()[0].id)
					 $("#loanCompName2_2").val($.fn.zTree.getZTreeObj("out_com_ztree_2").getSelectedNodes()[0].name);
					 var obj = [];
					 var loanCompId = {};
					 loanCompId.id="loanCompId";
					 loanCompId.value=$("#loanCompId2_2").val();
					 var loanCompName = {};
					 loanCompName.id="loanCompName";
					 loanCompName.value=$("#loanCompName2_2").val();
					 obj.push(loanCompId);
					 obj.push(loanCompName);
					 modifyComp(text,'reportMonthlyFinancingOutDetail',obj);
				}
	   	});
		
	})
	$("#out_com_ztree_3").click(function(){
		var text = $(this).closest('tr').find('td')[0].innerText;
		setTimeout(function(){
  				if($.fn.zTree.getZTreeObj("out_com_ztree_3").getSelectedNodes()[0])
				{
					 $("#loanCompId3_3").val($.fn.zTree.getZTreeObj("out_com_ztree_3").getSelectedNodes()[0].id)
					 $("#loanCompName3_3").val($.fn.zTree.getZTreeObj("out_com_ztree_3").getSelectedNodes()[0].name);
					 var obj = [];
					 var loanCompId = {};
					 loanCompId.id="loanCompId";
					 loanCompId.value=$("#loanCompId3_3").val();
					 var loanCompName = {};
					 loanCompName.id="loanCompName";
					 loanCompName.value=$("#loanCompName3_3").val();
					 obj.push(loanCompId);
					 obj.push(loanCompName);
					 modifyComp(text,'reportMonthlyFinancingOutDetail',obj);
				}
	   	});
		
	})
	$("#out_com_ztree_4").click(function(){
		var text = $(this).closest('tr').find('td')[0].innerText;
		setTimeout(function(){
  				if($.fn.zTree.getZTreeObj("out_com_ztree_4").getSelectedNodes()[0])
				{
					 $("#loanCompId4_4").val($.fn.zTree.getZTreeObj("out_com_ztree_4").getSelectedNodes()[0].id)
					 $("#loanCompName4_4").val($.fn.zTree.getZTreeObj("out_com_ztree_4").getSelectedNodes()[0].name);
					 var obj = [];
					 var loanCompId = {};
					 loanCompId.id="loanCompId";
					 loanCompId.value=$("#loanCompId4_4").val();
					 var loanCompName = {};
					 loanCompName.id="loanCompName";
					 loanCompName.value=$("#loanCompName4_4").val();
					 obj.push(loanCompId);
					 obj.push(loanCompName);
					 modifyComp(text,'reportMonthlyFinancingOutDetail',obj);
				}
	   	});
		
	})
	$("#out_com_ztree_5").click(function(){
		var text = $(this).closest('tr').find('td')[0].innerText;
		setTimeout(function(){
  				if($.fn.zTree.getZTreeObj("out_com_ztree_5").getSelectedNodes()[0])
				{
					 $("#loanCompId5_5").val($.fn.zTree.getZTreeObj("out_com_ztree_5").getSelectedNodes()[0].id)
					 $("#loanCompName5_5").val($.fn.zTree.getZTreeObj("out_com_ztree_5").getSelectedNodes()[0].name);
					 var obj = [];
					 var loanCompId = {};
					 loanCompId.id="loanCompId";
					 loanCompId.value=$("#loanCompId5_5").val();
					 var loanCompName = {};
					 loanCompName.id="loanCompName";
					 loanCompName.value=$("#loanCompName5_5").val();
					 obj.push(loanCompId);
					 obj.push(loanCompName);
					 modifyComp(text,'reportMonthlyFinancingOutDetail',obj);
				}
	   	});
		
	})
	
	 $(function () {
	     //所有企业数据	
	    var com_data = ${companyTrees};
		//加载列表公司选择
		var sizeNum = ${sizeNum};
		if(sizeNum > 0) {
	   		var zTreeObj6 = $.fn.zTree.init($("#out_com_ztree_1"), com_ztree_setting, com_data);
	    	//将每一个tree节点进行遍历，获取Id，与此角色已保存的Id（checked_data）进行比较，Id相同择设为选中
	    	var treeObj6 = $.fn.zTree.getZTreeObj("out_com_ztree_1");
			var nodes6 = treeObj6.getNodes();
			//transformToArray()此方法获取所有节点（父节点和子节点）
	   		var childrenNodes6 = treeObj6.transformToArray(nodes6);
	   		if(childrenNodes6[0]!=null){
	   			treeObj6.expandNode(childrenNodes6[0],true);
	   		}
   		}
   		
   		if(sizeNum >1){
   			var zTreeObj7 = $.fn.zTree.init($("#out_com_ztree_2"), com_ztree_setting, com_data);
	    	//将每一个tree节点进行遍历，获取Id，与此角色已保存的Id（checked_data）进行比较，Id相同择设为选中
	    	var treeObj7 = $.fn.zTree.getZTreeObj("out_com_ztree_2");
			var nodes7 = treeObj7.getNodes();
			//transformToArray()此方法获取所有节点（父节点和子节点）
	   		var childrenNodes7 = treeObj7.transformToArray(nodes7);
	   		if(childrenNodes7[0]!=null){
	   			treeObj7.expandNode(childrenNodes7[0],true);
	   		}
   		}
   		
   		if(sizeNum >2){
   			var zTreeObj8 = $.fn.zTree.init($("#out_com_ztree_3"), com_ztree_setting, com_data);
	    	//将每一个tree节点进行遍历，获取Id，与此角色已保存的Id（checked_data）进行比较，Id相同择设为选中
	    	var treeObj8 = $.fn.zTree.getZTreeObj("out_com_ztree_3");
			var nodes8 = treeObj8.getNodes();
			//transformToArray()此方法获取所有节点（父节点和子节点）
	   		var childrenNodes8 = treeObj8.transformToArray(nodes8);
	   		if(childrenNodes8[0]!=null){
	   			treeObj8.expandNode(childrenNodes8[0],true);
	   		}
   		}
		
		if(sizeNum >3){
			var zTreeObj9 = $.fn.zTree.init($("#out_com_ztree_4"), com_ztree_setting, com_data);
	    	//将每一个tree节点进行遍历，获取Id，与此角色已保存的Id（checked_data）进行比较，Id相同择设为选中
	    	var treeObj9 = $.fn.zTree.getZTreeObj("out_com_ztree_4");
			var nodes9 = treeObj9.getNodes();
			//transformToArray()此方法获取所有节点（父节点和子节点）
	   		var childrenNodes9 = treeObj9.transformToArray(nodes9);
	   		if(childrenNodes9[0]!=null){
	   			treeObj9.expandNode(childrenNodes9[0],true);
	   		}
		}   		
   		
   		if(sizeNum >4){ 	
   			var zTreeObj10 = $.fn.zTree.init($("#out_com_ztree_5"), com_ztree_setting, com_data);
	    	//将每一个tree节点进行遍历，获取Id，与此角色已保存的Id（checked_data）进行比较，Id相同择设为选中
	    	var treeObj10 = $.fn.zTree.getZTreeObj("out_com_ztree_5");
			var nodes10 = treeObj10.getNodes();
			//transformToArray()此方法获取所有节点（父节点和子节点）
	   		var childrenNodes10 = treeObj10.transformToArray(nodes10);
	   		if(childrenNodes10[0]!=null){
	   			treeObj10.expandNode(childrenNodes10[0],true);
	   		}
   		}
    }); */
</script>