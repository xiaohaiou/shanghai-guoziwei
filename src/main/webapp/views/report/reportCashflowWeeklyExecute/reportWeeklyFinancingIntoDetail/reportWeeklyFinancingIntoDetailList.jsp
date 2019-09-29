<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="tablebox" style="overflow-x: auto">
	<style>
		td{position:relative}
	</style>
	<table style="width: 250em">
		<tr class="table_header" id="reportWeeklyFinancingIntoDetailTableTr">
			<th>序号</th>
			<!-- <th>成员公司</th>
			<th>所属核心企业</th> -->
			<th>承贷主体</th>
			<th>贷款银行</th>
			<th>贷款金额(万元)</th>
			<th>下账时间</th>
			<th>到期时间</th>
			<th>贷款期限</th>
			<th>融资下账成本(%)</th>
			<th>新增或续作</th>
			<th>贷款类型</th>
			<th>操作</th>
		</tr>
		<c:if test="${not empty requestScope.msgPage.list }">
			<c:forEach items="${requestScope.msgPage.list }" var="node" varStatus="status">
				<tr>
					<td>${(requestScope.msgPage.pageNum-1)*5+status.count }</td>
					<%-- <td>${node.memberCompName }</td>
					<td>${node.coreCompName }</td> --%>
					<td>
						<input type="text" id="loanCompName" value="${node.loanCompName }" check="NotEmpty_string_._._._._." onblur="modifyEntity('${(msgPage.pageNum-1)*5+status.count }','reportWeeklyFinancingIntoDetail',this)">
						<%-- <input type="hidden" id="loanCompId${status.count }" value="${node.loanCompId }" >
						<input type="text" id="loanCompName${status.count }" value="${node.loanCompName }" check="NotEmpty_string_._._._._." readonly="true">
						<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
							<ul id="detail_com_ztree_${status.count }" class="ztree">
			
							</ul>
					    </div> --%>
					</td>
					<td>
						<input type="text" id="loanBank" value="${node.loanBank }" check="NotEmpty_string_._._._._." onblur="modifyEntity('${(msgPage.pageNum-1)*5+status.count }','reportWeeklyFinancingIntoDetail',this)">
					</td>
					<td>
						<input type="text" id="loanAmount" value="${node.loanAmount }" check="NotEmpty_double_16_2_0+_。_." onblur="modifyEntity('${(msgPage.pageNum-1)*5+status.count }','reportWeeklyFinancingIntoDetail',this)">
					</td>
					<td>
						<input class="w25 date2" type="text" id="accountDate" readonly="true" check="NotEmpty_string_._._._._." value="${node.accountDate }" />
					</td>
					<td>
						<input class="w25 date1" type="text" id="dueDate" readonly="true" check="NotEmpty_string_._._._._." value="${node.dueDate }" />
					</td>
					<td>
						<input type="text" id="lengthOfMaturity" value="${node.lengthOfMaturity }" check="NotEmpty_string_._._._._." onblur="modifyEntity('${(msgPage.pageNum-1)*5+status.count }','reportWeeklyFinancingIntoDetail',this)">
					</td>
					<td>
						<input type="text" id="financingAccountCost" value="${node.financingAccountCost }" check="NotEmpty_double_16_2_0+_。_." onblur="modifyEntity('${(msgPage.pageNum-1)*5+status.count }','reportWeeklyFinancingIntoDetail',this)">
					</td>
					<td>
						<select id="type" onchange="modifyEntity('${(msgPage.pageNum-1)*5+status.count }','reportWeeklyFinancingIntoDetail',this)">
							<c:forEach items="${sequelNew }" var="sys">
								<option value="${sys.id }" <c:if test="${sys.id == node.type }">selected</c:if>>${sys.description }</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<select id="loanType" onchange="modifyEntity('${(msgPage.pageNum-1)*5+status.count }','reportWeeklyFinancingIntoDetail',this)">
							<c:forEach items="${loanType }" var="sys">
								<option value="${sys.id }" <c:if test="${sys.id == node.loanType }">selected</c:if>>${sys.description }</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<a class="btn btn-primary model_btn_ok" onclick="del('${(msgPage.pageNum-1)*5+status.count }','reportWeeklyFinancingIntoDetail')">删除</a>
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
</div>
<c:if test="${not empty requestScope.msgPage.list}">
	<jsp:include page="../page.jsp"/>
</c:if>
<script type="text/javascript" src="<c:url value="/js/vailds.js"/>" charset="utf-8"></script>
<script type="text/javascript">
	$(' input.date2').jeDate(
		{
			format:"YYYY-MM-DD",
			choosefun:function(elem, val, date) {changeDate(val,$(elem).closest('tr').find('td')[0].innerText);},
				clearfun:function(elem, val) {changeDate('',$(elem).closest('tr').find('td')[0].innerText);},            //清除日期后的回调, elem当前输入框ID, val当前选择的值
	  				okfun:function(elem, val, date) {changeDate(val,$(elem).closest('tr').find('td')[0].innerText);}, 
		}
	)
	function changeDate(val,text){
		 var obj = {};
		 obj.id="accountDate";
		 obj.value=val;
		 modifyEntity(text,'reportWeeklyFinancingIntoDetail',obj);
	}
	
	$(' input.date1').jeDate(
		{
			format:"YYYY-MM-DD",
			choosefun:function(elem, val, date) {changeDate1(val,$(elem).closest('tr').find('td')[0].innerText);},
				clearfun:function(elem, val) {changeDate1('',$(elem).closest('tr').find('td')[0].innerText);},            //清除日期后的回调, elem当前输入框ID, val当前选择的值
	  				okfun:function(elem, val, date) {changeDate1(val,$(elem).closest('tr').find('td')[0].innerText);}, 
		}
	)
	function changeDate1(val,text){
		 var obj = {};
		 obj.id="dueDate";
		 obj.value=val;
		 modifyEntity(text,'reportWeeklyFinancingIntoDetail',obj);
	}
	
	/* var timeoutId1
	$('#loanCompName1').on('focus click',function(){
		$(this).next('.com_ztree_box').css('display','block')
	}).parent().on('mouseenter',function(){
		clearTimeout(timeoutId1)
	}).on('mouseleave',function(){
		clearTimeout(timeoutId1)
		timeoutId1 = setTimeout(function(el){
			$(el).find('.com_ztree_box').css('display','none')
		},3e2,this);
	})
	
	var timeoutId2
	$('#loanCompName2').on('focus click',function(){
		$(this).next('.com_ztree_box').css('display','block')
	}).parent().on('mouseenter',function(){
		clearTimeout(timeoutId2)
	}).on('mouseleave',function(){
		clearTimeout(timeoutId2)
		timeoutId2 = setTimeout(function(el){
			$(el).find('.com_ztree_box').css('display','none')
		},3e2,this);
	})
	
	var timeoutId3
	$('#loanCompName3').on('focus click',function(){
		$(this).next('.com_ztree_box').css('display','block')
	}).parent().on('mouseenter',function(){
		clearTimeout(timeoutId3)
	}).on('mouseleave',function(){
		clearTimeout(timeoutId3)
		timeoutId3 = setTimeout(function(el){
			$(el).find('.com_ztree_box').css('display','none')
		},3e2,this);
	})
	
	var timeoutId4
	$('#loanCompName4').on('focus click',function(){
		$(this).next('.com_ztree_box').css('display','block')
	}).parent().on('mouseenter',function(){
		clearTimeout(timeoutId4)
	}).on('mouseleave',function(){
		clearTimeout(timeoutId4)
		timeoutId4 = setTimeout(function(el){
			$(el).find('.com_ztree_box').css('display','none')
		},3e2,this);
	})
	
	var timeoutId5
	$('#loanCompName5').on('focus click',function(){
		$(this).next('.com_ztree_box').css('display','block')
	}).parent().on('mouseenter',function(){
		clearTimeout(timeoutId5)
	}).on('mouseleave',function(){
		clearTimeout(timeoutId5)
		timeoutId5 = setTimeout(function(el){
			$(el).find('.com_ztree_box').css('display','none')
		},3e2,this);
	})
	
	$("#detail_com_ztree_1").click(function(){
		var text = $(this).closest('tr').find('td')[0].innerText;
		setTimeout(function(){
  				if($.fn.zTree.getZTreeObj("detail_com_ztree_1").getSelectedNodes()[0])
				{
					 $("#loanCompId1").val($.fn.zTree.getZTreeObj("detail_com_ztree_1").getSelectedNodes()[0].id)
					 $("#loanCompName1").val($.fn.zTree.getZTreeObj("detail_com_ztree_1").getSelectedNodes()[0].name);
					 var obj = [];
					 var loanCompId = {};
					 loanCompId.id="loanCompId";
					 loanCompId.value=$("#loanCompId1").val();
					 var loanCompName = {};
					 loanCompName.id="loanCompName";
					 loanCompName.value=$("#loanCompName1").val();
					 obj.push(loanCompId);
					 obj.push(loanCompName);
					 modifyComp(text,'reportWeeklyFinancingIntoDetail',obj);
				}
	   	});
		
	})
	$("#detail_com_ztree_2").click(function(){
		var text = $(this).closest('tr').find('td')[0].innerText;
		setTimeout(function(){
  				if($.fn.zTree.getZTreeObj("detail_com_ztree_2").getSelectedNodes()[0])
				{
					 $("#loanCompId2").val($.fn.zTree.getZTreeObj("detail_com_ztree_2").getSelectedNodes()[0].id)
					 $("#loanCompName2").val($.fn.zTree.getZTreeObj("detail_com_ztree_2").getSelectedNodes()[0].name);
					 var obj = [];
					 var loanCompId = {};
					 loanCompId.id="loanCompId";
					 loanCompId.value=$("#loanCompId2").val();
					 var loanCompName = {};
					 loanCompName.id="loanCompName";
					 loanCompName.value=$("#loanCompName2").val();
					 obj.push(loanCompId);
					 obj.push(loanCompName);
					 modifyComp(text,'reportWeeklyFinancingIntoDetail',obj);
				}
	   	});
		
	})
	$("#detail_com_ztree_3").click(function(){
		var text = $(this).closest('tr').find('td')[0].innerText;
		setTimeout(function(){
  				if($.fn.zTree.getZTreeObj("detail_com_ztree_3").getSelectedNodes()[0])
				{
					 $("#loanCompId3").val($.fn.zTree.getZTreeObj("detail_com_ztree_3").getSelectedNodes()[0].id)
					 $("#loanCompName3").val($.fn.zTree.getZTreeObj("detail_com_ztree_3").getSelectedNodes()[0].name);
					 var obj = [];
					 var loanCompId = {};
					 loanCompId.id="loanCompId";
					 loanCompId.value=$("#loanCompId3").val();
					 var loanCompName = {};
					 loanCompName.id="loanCompName";
					 loanCompName.value=$("#loanCompName3").val();
					 obj.push(loanCompId);
					 obj.push(loanCompName);
					 modifyComp(text,'reportWeeklyFinancingIntoDetail',obj);
				}
	   	});
		
	})
	$("#detail_com_ztree_4").click(function(){
		var text = $(this).closest('tr').find('td')[0].innerText;
		setTimeout(function(){
  				if($.fn.zTree.getZTreeObj("detail_com_ztree_4").getSelectedNodes()[0])
				{
					 $("#loanCompId4").val($.fn.zTree.getZTreeObj("detail_com_ztree_4").getSelectedNodes()[0].id)
					 $("#loanCompName4").val($.fn.zTree.getZTreeObj("detail_com_ztree_4").getSelectedNodes()[0].name);
					 var obj = [];
					 var loanCompId = {};
					 loanCompId.id="loanCompId";
					 loanCompId.value=$("#loanCompId4").val();
					 var loanCompName = {};
					 loanCompName.id="loanCompName";
					 loanCompName.value=$("#loanCompName4").val();
					 obj.push(loanCompId);
					 obj.push(loanCompName);
					 modifyComp(text,'reportWeeklyFinancingIntoDetail',obj);
				}
	   	});
		
	})
	$("#detail_com_ztree_5").click(function(){
		var text = $(this).closest('tr').find('td')[0].innerText;
		setTimeout(function(){
  				if($.fn.zTree.getZTreeObj("detail_com_ztree_5").getSelectedNodes()[0])
				{
					 $("#loanCompId5").val($.fn.zTree.getZTreeObj("detail_com_ztree_5").getSelectedNodes()[0].id)
					 $("#loanCompName5").val($.fn.zTree.getZTreeObj("detail_com_ztree_5").getSelectedNodes()[0].name);
					 var obj = [];
					 var loanCompId = {};
					 loanCompId.id="loanCompId";
					 loanCompId.value=$("#loanCompId5").val();
					 var loanCompName = {};
					 loanCompName.id="loanCompName";
					 loanCompName.value=$("#loanCompName5").val();
					 obj.push(loanCompId);
					 obj.push(loanCompName);
					 modifyComp(text,'reportWeeklyFinancingIntoDetail',obj);
				}
	   	});
		
	})
	
	 $(function () {
	     //所有企业数据	
	    var com_data = ${companyTrees};
		//加载列表公司选择
		var sizeNum = ${sizeNum};
		if(sizeNum > 0) {
	   		var zTreeObj1 = $.fn.zTree.init($("#detail_com_ztree_1"), com_ztree_setting, com_data);
	    	//将每一个tree节点进行遍历，获取Id，与此角色已保存的Id（checked_data）进行比较，Id相同择设为选中
	    	var treeObj1 = $.fn.zTree.getZTreeObj("detail_com_ztree_1");
			var nodes1 = treeObj1.getNodes();
			//transformToArray()此方法获取所有节点（父节点和子节点）
	   		var childrenNodes1 = treeObj1.transformToArray(nodes1);
	   		if(childrenNodes1[0]!=null){
	   			treeObj1.expandNode(childrenNodes1[0],true);
	   		}
   		}
   		
   		if(sizeNum >1){
   			var zTreeObj2 = $.fn.zTree.init($("#detail_com_ztree_2"), com_ztree_setting, com_data);
	    	//将每一个tree节点进行遍历，获取Id，与此角色已保存的Id（checked_data）进行比较，Id相同择设为选中
	    	var treeObj2 = $.fn.zTree.getZTreeObj("detail_com_ztree_2");
			var nodes2 = treeObj2.getNodes();
			//transformToArray()此方法获取所有节点（父节点和子节点）
	   		var childrenNodes2 = treeObj2.transformToArray(nodes2);
	   		if(childrenNodes2[0]!=null){
	   			treeObj2.expandNode(childrenNodes2[0],true);
	   		}
   		}
   		
   		if(sizeNum >2){
   			var zTreeObj3 = $.fn.zTree.init($("#detail_com_ztree_3"), com_ztree_setting, com_data);
	    	//将每一个tree节点进行遍历，获取Id，与此角色已保存的Id（checked_data）进行比较，Id相同择设为选中
	    	var treeObj3 = $.fn.zTree.getZTreeObj("detail_com_ztree_3");
			var nodes3 = treeObj3.getNodes();
			//transformToArray()此方法获取所有节点（父节点和子节点）
	   		var childrenNodes3 = treeObj3.transformToArray(nodes3);
	   		if(childrenNodes3[0]!=null){
	   			treeObj3.expandNode(childrenNodes3[0],true);
	   		}
   		}
		
		if(sizeNum >3){
			var zTreeObj4 = $.fn.zTree.init($("#detail_com_ztree_4"), com_ztree_setting, com_data);
	    	//将每一个tree节点进行遍历，获取Id，与此角色已保存的Id（checked_data）进行比较，Id相同择设为选中
	    	var treeObj4 = $.fn.zTree.getZTreeObj("detail_com_ztree_4");
			var nodes4 = treeObj4.getNodes();
			//transformToArray()此方法获取所有节点（父节点和子节点）
	   		var childrenNodes4 = treeObj4.transformToArray(nodes4);
	   		if(childrenNodes4[0]!=null){
	   			treeObj4.expandNode(childrenNodes4[0],true);
	   		}
		}   		
   		
   		if(sizeNum >4){ 	
   			var zTreeObj5 = $.fn.zTree.init($("#detail_com_ztree_5"), com_ztree_setting, com_data);
	    	//将每一个tree节点进行遍历，获取Id，与此角色已保存的Id（checked_data）进行比较，Id相同择设为选中
	    	var treeObj5 = $.fn.zTree.getZTreeObj("detail_com_ztree_5");
			var nodes5 = treeObj5.getNodes();
			//transformToArray()此方法获取所有节点（父节点和子节点）
	   		var childrenNodes5 = treeObj5.transformToArray(nodes5);
	   		if(childrenNodes5[0]!=null){
	   			treeObj5.expandNode(childrenNodes5[0],true);
	   		}
   		}
    }); */
</script>