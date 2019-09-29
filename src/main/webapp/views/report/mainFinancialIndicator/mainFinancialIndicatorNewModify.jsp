<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>主要财务指标数据</title>
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
				<span class="glyphicon glyphicon-pencil"></span>修改主要财务指标数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增主要财务指标数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
			
		</c:otherwise>
		
	</c:choose>

		<div class="label_inpt_div">
		<form:form id="form2" modelAttribute="entityview" >
		
				<form:hidden path="id"/>
				
				<div class="model_part">
				<label class="w20"><span class="required"> * </span>单位名称:</label>
				<span class="w25">
						<input type="hidden" id="organalID" name="org" value="${entityview.org }" >
						<input name="orgname" id="checkedCompanyName" value="${entityview.orgname }" check="NotEmpty_string_._._._._." readonly>
						<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
							<ul id="com_ztree" class="ztree">
			
							</ul>
					   </div>
				</span>
				
				<label class="w20"><span class="required"> * </span>年份:</label>
				<input class="w25 time" type="text" id="year" name="year" value="${entityview.year }" readonly="true" class="time" />
				
				
				<label class="w20"><span class="required"> * </span>负责人:</label>
				<form:input class="w25" path="chargePerson" id="chargePerson" check="NotEmpty_string_50_._._。_."/>
				<label class="w20"><span class="required"> * </span>注册地:</label>
				<form:input class="w25" path="address" id="address" check="NotEmpty_string_50_._._。_."/>
				
				
				<label class="w20"><span class="required"> * </span>主运营地:</label>
				<form:input class="w25" path="mainCamp" id="mainCamp" check="NotEmpty_string_100_._._。_."/>
				<label class="w20"><span class="required"> * </span>会计师事务所:</label>
				<form:input class="w25" path="accountingFirm" id="accountingFirm" check="NotEmpty_string_100_._._。_."/>
			
				<label class="w20"><span class="required"> * </span>报表类型:</label>
				<span class="w25">
					<select  name="reportType" class="selectpicker" id="reportType" >
							<c:forEach items="${requestScope.reportType}" var="result">
									<option value="${result.id }"  <c:if test="${entityview.reportType == result.id}">selected="selected"</c:if>>${result.description }</option>
							</c:forEach>
					</select>
				</span>
				<label class="w20"></label>
				<span class="w25"></span>
				
				
				<label class="w20"><span class="required"> * </span>总资产 (元):</label>
				<form:input class="w25" path="totalAssets" id="totalAssets" check="NotEmpty_double_20_2_0+_。_."/>
				<label class="w20"><span class="required"> * </span>总负债(元):</label>
				<form:input class="w25" path="totalLiabilities" id="totalLiabilities" check="NotEmpty_double_20_2_0+_._."/>
				<label class="w20"><span class="required"> * </span>所有者权益(元):</label>
				<form:input class="w25" path="ownerEquity" id="ownerEquity" check="NotEmpty_double_20_2_0+_._."/>
				
				<label class="w20"><span class="required"> * </span>实收资本(元):</label>
				<form:input class="w25" path="paidCapital" id="paidCapital" check="NotEmpty_double_20_2_0+_._."/>
				<label class="w20"><span class="required"> * </span>资产负债率(%):</label>
				<form:input class="w25" path="assetLiabilityRatio" id="assetLiabilityRatio" check="NotEmpty_double_10_2_0+_._."/>
				<label class="w20"><span class="required"> * </span>营业总收入(元):</label>
				<form:input class="w25" path="totalRevenue" id="totalRevenue" check="NotEmpty_double_20_2_0+_._."/>
				<label class="w20"><span class="required"> * </span>营业成本(元):</label>
				<form:input class="w25" path="operatingCost" id="operatingCost" check="NotEmpty_double_20_2_0+_._."/>
				<label class="w20"><span class="required"> * </span>利润总额(元):</label>
				<form:input class="w25" path="totalProfit" id="totalProfit" check="NotEmpty_double_20_2_0+_._."/>
				
				<label class="w20"><span class="required"> * </span>净利润(元):</label>
				<form:input class="w25" path="netProfit" id="netProfit" check="NotEmpty_double_20_2_0+_._."/>
				<label class="w20"><span class="required"> * </span>货币资金(元):</label>
				<form:input class="w25" path="monetaryFund" id="monetaryFund" check="NotEmpty_double_20_2_0+_._."/>
				
				<label class="w20"><span class="required"> * </span>存货(元):</label>
				<form:input class="w25" path="stock" id="stock" check="NotEmpty_double_20_2_0+_._."/>
				<label class="w20"><span class="required"> * </span>其他应收款(元):</label>
				<form:input class="w25" path="otherReceivables" id="otherReceivables" check="NotEmpty_double_20_2_0+_._."/>
			
				<label class="w20"><span class="required"> * </span>投资型房地产(元):</label>
				<form:input class="w25" path="investmentTypeRealEstate" id="investmentTypeRealEstate" check="NotEmpty_double_20_2_0+_._."/>
				<label class="w20"><span class="required"> * </span>固定资产(元):</label>
				<form:input class="w25" path="fixedAssets" id="fixedAssets" check="NotEmpty_double_20_2_0+_._."/>
			
				<label class="w20"><span class="required"> * </span>在建工程(元):</label>
				<form:input class="w25" path="underConstructionProject" id="underConstructionProject" check="NotEmpty_double_20_2_0+_._."/>
				<label class="w20"><span class="required"> * </span>无形资产(元):</label>
				<form:input class="w25" path="intangibleAssets" id="intangibleAssets" check="NotEmpty_double_20_2_0+_._."/>
	
				<label class="w20"><span class="required"> * </span>其他应付款(元):</label>
				<form:input class="w25" path="otherPayable" id="otherPayable" check="NotEmpty_double_20_2_0+_._."/>
				<label class="w20"><span class="required"> * </span>短期借款(元):</label>
				<form:input class="w25" path="shortTermLoan" id="shortTermLoan" check="NotEmpty_double_20_2_0+_._."/>
	
				
				<label class="w20"><span class="required"> * </span>一年内到期的非流动负债(元):</label>
				<form:input class="w25" path="nonCurrentLiabilities" id="nonCurrentLiabilities" check="NotEmpty_double_20_2_0+_._."/>
				<label class="w20"><span class="required"> * </span>长期借款(元):</label>
				<form:input class="w25" path="longTermLoan" id="longTermLoan" check="NotEmpty_double_20_2_0+_._."/>
	
				<label class="w20"><span class="required"> * </span>长期应付款(元):</label>
				<form:input class="w25" path="longTermPayables" id="longTermPayables" check="NotEmpty_double_20_2_0+_._."/>
				<label class="w20"><span class="required"> * </span>未分配利润(元):</label>
				<form:input class="w25" path="undistributedProfit" id="undistributedProfit" check="NotEmpty_double_20_2_0+_._."/>
				
				<label class="w20"><span class="required"> * </span>归属母公司的所有者权益(元):</label>
				<form:input class="w25" path="ownerEquityAttributableToTheParentCompany" id="ownerEquityAttributableToTheParentCompany" check="NotEmpty_double_20_2_0+_._."/>
				<label class="w20"><span class="required"> * </span>少数股东权益(元):</label>
				<form:input class="w25" path="minorityShareholdersEquity" id="minorityShareholdersEquity" check="NotEmpty_double_20_2_0+_._."/>
	
				<label class="w20"><span class="required"> * </span>营业税金及附加(元):</label>
				<form:input class="w25" path="businessTaxesAndSurcharges" id="businessTaxesAndSurcharges" check="NotEmpty_double_20_2_0+_._."/>
				<label class="w20"><span class="required"> * </span>销售费用(元):</label>
				<form:input class="w25" path="sellingExpenses" id="sellingExpenses" check="NotEmpty_double_20_2_0+_._."/>
	
				<label class="w20"><span class="required"> * </span>管理费用(元):</label>
				<form:input class="w25" path="managementExpenses" id="managementExpenses" check="NotEmpty_double_20_2_0+_._."/>
				<label class="w20"><span class="required"> * </span>财务费用(元):</label>
				<form:input class="w25" path="financialExpenses" id="financialExpenses" check="NotEmpty_double_20_2_0+_._."/>
	
				<label class="w20"><span class="required"> * </span>公允价值变动收益(元):</label>
				<form:input class="w25" path="fairValueChangeIncome" id="fairValueChangeIncome" check="NotEmpty_double_20_2_0+_._."/>
				<label class="w20"><span class="required"> * </span>投资收益(元):</label>
				<form:input class="w25" path="incomeFromInvestment" id="incomeFromInvestment" check="NotEmpty_double_20_2_0+_._."/>
	
	
				<label class="w20"><span class="required"> * </span>所得税费用(元):</label>
				<form:input class="w25" path="incomeTaxExpense" id="incomeTaxExpense" check="NotEmpty_double_20_2_0+_._."/>
				<label class="w20"><span class="required"> * </span>注册资本(元):</label>
				<form:input class="w25" path="registeredCapital" id="registeredCapital" check="NotEmpty_double_20_2_0+_._."/>
				
				<input type="hidden" name="stocklist" id="stocklist"/>
				
				<div class="tablebox">
					<table class="row" id="grouptable">
						<tr class="table_header">
							<th style="min-width:150px">股东名</th>
							<th style="min-width: 80px">持股比例(%)</th>
							<th style="min-width: 50px">操作</th>
						</tr>
						
						<tr class="expmale">
						
							<td>
								<input type="text" name="name" class="nameclass" check="NotEmpty_string_20_._._._.">
							</td>
							<td>
								<input type="text" name="precent" class="precentclass" check="NotEmpty_double_._2_._0_100">
							</td>
							<td>
								<i class='del btn'>×</i>
							</td>
						</tr>
						
						<c:if test="${ !empty stock }">
						<c:forEach items="${ stock}" var="itemsys" varStatus="status">
							<tr class="">
							
							<td>
								<input type="text" class="nameclass" name="name" value="${itemsys.name }" check="NotEmpty_string_20_._._._.">
							</td>
							<td>
								<input type="text" class="precentclass" name="precent"  value="${itemsys.precent }" check="NotEmpty_double_._2_._0_100">
							</td>
							<td>
								<i class='del btn'>×</i>
							</td>
						</tr>
						</c:forEach>
						</c:if>
					</table>
			</div>
			
			<div class="row">
				<div class="btn btn-default new"
					style="display:inline-block;margin-left:6.7%;width:10%;line-height:1GU;">新增股东</div>
			</div>
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

		function commitcheck()
		{
			$("#stocklist").val("");
			if($("#year").val()=="")
			{
				win.errorAlert("年份不能为空");
				return false;
			}
			var size=0;
			var	groupitem=[];
			var maxprecent=100;
			$.each($("table tbody tr"),function (index,item)
			{
				if(index!=0)
				{
					var obj= {};
					obj.name=$(item).find(".nameclass").val();
					obj.precent=$(item).find(" .precentclass").val();
					maxprecent=maxprecent-obj.precent;
					groupitem.push(obj);
					size++;
				}
			})
			if(maxprecent!=0)
			{
				win.errorAlert("股东持股总和未到100%");
				return false;
			}
			if(groupitem.length>0)
			{
				$("#stocklist").val(JSON.stringify(groupitem));
			}
			else
			{
				win.errorAlert("股东信息不能为空");
				return false;
			}
			return true;
			
		}	
				
				
		$("#commit-1").click(function(){
			$.blockUI({ message: '提交中', css: { width: '275px' } });
			
			if( !commitcheck() || !vaild.all() )
			{
					$.unblockUI();
					return false;
			}
					//var entityBasicInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/mainFinancialIndicators/save";
			
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
			if(!commitcheck() || !vaild.all() )
			{
					$.unblockUI();
					return false;
			}
					//var entityBasicInfo = $('#form2').serialize();
			var url = "${pageContext.request.contextPath}/mainFinancialIndicators/saveandreport";
			
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
	
	
		$(' input.time').jeDate(
			{
				format:"YYYY"
			}
		)
	
		$("#com_ztree").click(function(){
				setTimeout(function(){
	   				if($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0])
						{
							 $("#organalID").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id)
							 $("#checkedCompanyName").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
						}
			   	});
			
		})

		var example = $(".expmale");
		example.removeClass("example").remove();
		$(".btn.new").click(function(){
			$("table tbody").append(example.clone());
			$('input[check]').each(function(){
				this.setAttribute('placeholder',vaild.msg(this.getAttribute('check')))
			})
		});
		$("body").on("click","i.del.btn",function(){
			$(this).closest("tr").remove();
		});
		//关闭弹窗
		$(".model_btn_close").click(function () {
			parent.mclose();
			return false;
		});
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		
	</script>
</html>