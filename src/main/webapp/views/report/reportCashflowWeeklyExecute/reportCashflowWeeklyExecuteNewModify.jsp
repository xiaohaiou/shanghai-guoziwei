<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>现金流周执行数据</title>
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
				<span class="glyphicon glyphicon-pencil"></span>修改现金流周执行数据
				<i class="iconfont icon-guanbi"></i>
			</h4>
		</c:when>
		<c:otherwise>
			<h4>
				<span class="glyphicon glyphicon-pencil"></span>新增现金流周执行数据
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
						<input name="orgname" id="checkedCompanyName" value="${entityview.orgname }" check="NotEmpty_string_._._._._." readonly="true">
						<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
							<ul id="com_ztree" class="ztree">
			
							</ul>
					   </div>
				</span>				   				   
				<label class="w20"><span class="required"> * </span>所属核心企业:</label>
				<input class="w25" type="text" name="coreCompName" id="coreCompName" readonly value="${entityview.coreCompName }" />
				<input type="hidden" name="coreCompId" id="coreCompId" value="${entityview.coreCompId}" />
			    <label class="w20"><span class="required"> * </span>年份:</label>
				<input class="w25 time" type="text" id="year" name="year" value="${entityview.year }" check="NotEmpty_string_._._._._." readonly="true" class="time" />
				<label class="w20"><span class="required"> * </span>月份:</label>
				<span class="w25">
					<select  name="month" id="month" class="selectpicker"  >
						<c:forEach var= "temp" begin= "1" step= "1" end= "12">
							<option value="${temp}" <c:if test="${entityview.month == temp}">selected="selected"</c:if>>
								${temp}
							</option>
						</c:forEach>
					</select>
				</span>
				<label class="w20"><span class="required"> * </span>周数:</label>
				<span class="w25">
					<select  name="week" id="week" class="selectpicker"  >
						<c:forEach var= "temp1" begin= "1" step= "1" end= "54">
							<option value="${temp1}" <c:if test="${entityview.week == temp1}">selected="selected"</c:if>>
								${temp1}
							</option>
						</c:forEach>
					</select>
				</span>
				<label class="w20"><span class="required"> * </span>周开始日期:</label>
				<input class="w25 date" type="text" name="weekStartDate" id="weekStartDate" check="NotEmpty_string_._._._._." readonly="true" value="${entityview.weekStartDate }" />
				<label class="w20"><span class="required"> * </span>周结束日期:</label>
				<input class="w25 date" type="text" name="weekEndDate" id="weekEndDate" check="NotEmpty_string_._._._._." readonly="true" value="${entityview.weekEndDate }" />
				<label class="w20"><span class="required"> </span></label>
				<input type="button" id='hzcxID' value="汇总" style='background:#009999' onclick='sumData()'/>	
			</div>
			
			<h3 class="data_title1">经营性现金流(金额：万元)</h3>
			<div class="model_part">
				<label class="w20"><span class="required"> * </span>经营性流入:</label>
				<form:input class="w25" path="commercialInto" id="commercialInto" check="NotEmpty_double_16_2_0+_。_." onblur="calrate()"/>
				<label class="w20"><span class="required"> * </span>经营性流出:</label>
				<form:input class="w25" path="commercialOut" id="commercialOut" check="NotEmpty_double_16_2_0+_。_." onblur="calrate()"/>
				<label class="w20"><span class="required"> * </span>经营性在建工程:</label>
				<form:input class="w25" path="commercialProject" id="commercialProject" check="NotEmpty_double_16_2_0+_。_." onblur="calrate()"/>
				<label class="w20"><span class="required"> * </span>经营性净流量:</label>
				<form:input class="w25" path="commercialNetInflow" id="commercialNetInflow" readonly="true"/>
			</div> 
			<h3 class="data_title1">筹资性现金流(金额：万元)</h3>
			<div class="model_part">
				<label class="w20"><span class="required"> * </span>筹资性流入:</label>
				<form:input class="w25" path="financingInto" id="financingInto" check="NotEmpty_double_16_2_0+_。_." onblur="calrate()"/>
				<label class="w20"><span class="required"> * </span>筹资性流出:</label>
				<form:input class="w25" path="financingOut" id="financingOut" check="NotEmpty_double_16_2_0+_。_." onblur="calrate()"/>
				<label class="w20"><span class="required"> * </span>筹资性净流量:</label>
				<form:input class="w25" path="financingNetInflow" id="financingNetInflow" readonly="true"/>
				<label class="w20"></label>
				<label class="w25 setleft"></label>
			</div>
			<h3 class="data_title1">投资性现金流(金额：万元)</h3>
			<div class="model_part">
				<label class="w20"><span class="required"> * </span>投资性流入:</label>
				<form:input class="w25" path="investmentInto" id="investmentInto" check="NotEmpty_double_16_2_0_。_." onblur="calrate()"/>
				<label class="w20"><span class="required"> * </span>在建工程:</label>
				<form:input class="w25" path="constructionInProcess" id="constructionInProcess" check="NotEmpty_double_16_2_0_。_." onblur="calrate()"/>
				<label class="w20"><span class="required"> * </span>飞机投资:</label>
				<form:input class="w25" path="airplaneInvest" id="airplaneInvest" check="NotEmpty_double_16_2_0_。_." onblur="calrate()"/>
				<label class="w20"><span class="required"> * </span>股权投资:</label>
				<form:input class="w25" path="stockEquityInvest" id="stockEquityInvest" check="NotEmpty_double_16_2_0_。_." onblur="calrate()"/>
				<label class="w20"><span class="required"> * </span>土地储备:</label>
				<form:input class="w25" path="landReserve" id="landReserve" check="NotEmpty_double_16_2_0_。_." onblur="calrate()"/>
				<label class="w20"><span class="required"> * </span>固定资产购置:</label>
				<form:input class="w25" path="fixedAssetsPurchase" id="fixedAssetsPurchase" check="NotEmpty_double_16_2_0_。_." onblur="calrate()"/>
				<label class="w20"><span class="required"> * </span>金融性投资:</label>
				<form:input class="w25" path="financeInvest" id="financeInvest" check="NotEmpty_double_16_2_0_。_." onblur="calrate()"/>
				<label class="w20"><span class="required"> * </span>其它投资性支出:</label>
				<form:input class="w25" path="elseInvestExpense" id="elseInvestExpense" check="NotEmpty_double_16_2_0_。_." onblur="calrate()"/>
				<label class="w20"><span class="required"> * </span>投资性流出:</label>
				<form:input class="w25" path="investOut" id="investOut" readonly="true"/>
				<label class="w20"><span class="required"> * </span>投资性净流量:</label>
				<form:input class="w25" path="investNetInflow" id="investNetInflow" readonly="true"/>
			</div>
			<h3 class="data_title1">拆借或贡献现金流(金额：万元)</h3>
			<div class="model_part">
				<label class="w20"><span class="required"> * </span>拆借:</label>
				<form:input class="w25" path="lendingOrContributionInto" id="lendingOrContributionInto" check="NotEmpty_double_16_2_0+_。_." onblur="calrate()"/>
				<label class="w20"><span class="required"> * </span>贡献:</label>
				<form:input class="w25" path="lendingOrContributionOut" id="lendingOrContributionOut" check="NotEmpty_double_16_2_0+_。_." onblur="calrate()"/>
				<label class="w20"><span class="required"> * </span>资金拆借净流量:</label>
				<form:input class="w25" path="lendingOrContributionNetInflow" id="lendingOrContributionNetInflow" readonly="true"/>
				<label class="w20"></label>
				<label class="w25 setleft"></label>
			</div> 
			<h3 class="data_title1">现金流合计(金额：万元)</h3>
			<div class="model_part">
				<label class="w20"><span class="required"> * </span>现金流入:</label>
				<form:input class="w25" path="cashInto" id="cashInto" readonly="true"/>
				<label class="w20"><span class="required"> * </span>现金流出:</label>
				<form:input class="w25" path="cashOut" id="cashOut" readonly="true"/>
				<label class="w20"><span class="required"> * </span>现金净流量:</label>
				<form:input class="w25" path="cashNetInflow" id="cashNetInflow" readonly="true"/>
				<label class="w20"></label>
				<label class="w25 setleft"></label>
				<p><label class="w20" style="text-align:right;margin-bottom: 0"><span class="required"> * </span>备注:</label></p>
				<%-- <input class="w25" type="text" name="eventDescription" id="eventDescription" value="${theEntity.eventDescription }" /> --%>
				<textarea style="margin: 5px 5%;width: 90%;height:6em" rows="5" placeholder="合计的现金净流量为负时，填写备注给出解释说明" name="remark" id="remark">${entityview.remark }</textarea>
			</div> 
			
			<h3 class="data_title1">筹资性流入明细列表
				<a class="btn btn-primary btn-xs" href="javascript:void(0)" onclick="exportExcel('筹资性流入明细列表','1')">导出</a>
				<a class="btn btn-primary btn-xs" href="javascript:void(0)" onclick="importExcel('1')">导入</a>
				<a class="model_btn_plus" id="reportWeeklyFinancingIntoDetail" onclick="addEntity(this)">+</a>
			</h3>
			<div class="model_part">
				<div id="reportWeeklyFinancingIntoDetailList">
				
				</div>
			</div>
			
			<h3 class="data_title1">筹资性流出明细列表
				<a class="btn btn-primary btn-xs" href="javascript:void(0)" onclick="exportExcel('筹资性流出明细列表','2')">导出</a>
				<a class="btn btn-primary btn-xs" href="javascript:void(0)" onclick="importExcel('2')">导入</a>
				<a class="model_btn_plus" id="reportWeeklyFinancingOutDetail" onclick="addEntity(this)">+</a>
			</h3>
			<div class="model_part">
				<div id="reportWeeklyFinancingOutDetailList">
				
				</div>
			</div>
			
			<h3 class="data_title1">投资性流出明细列表
				<a class="btn btn-primary btn-xs" href="javascript:void(0)" onclick="exportExcel('投资性流出明细列表','3')">导出</a>
				<a class="btn btn-primary btn-xs" href="javascript:void(0)" onclick="importExcel('3')">导入</a>
				<a class="model_btn_plus" id="reportWeeklyInvestmentOutDetail" onclick="addEntity(this)">+</a>
			</h3>
			<div class="model_part">
				<div id="reportWeeklyInvestmentOutDetailList">
				
				</div>
			</div>
			
			<%-- <c:if test="${ !empty entityExamineviewlist  }">
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
			</c:if> --%>
			<div class="model_btn">
				<button class="btn btn-primary model_btn_ok" id="commit-1">保存</button>
				<button class="btn btn-primary model_btn_ok" id="commit-2">保存并上报</button>
				<button class="btn btn-default model model_btn_close">关闭</button>
				
			</div>
			<input type="hidden" id="stringList1" name="stringList1">
			<input type="hidden" id="stringList2" name="stringList2">
			<input type="hidden" id="stringList3" name="stringList3">
		</form:form>
	</div>
	<div style="display:none">
		<iframe id="excelIframe">
		</iframe>
	</div>
</body>

<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/vailds.js"/>" charset="utf-8"></script>
<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
<script type="text/javascript">
	var data1 = ${data1};
	if(data1 == 1){
		data1 = [];
	}
	
	var data2 = ${data2};
	if(data2 == 1){
		data2 = [];
	}
	
	var data3 = ${data3};
	if(data3 == 1){
		data3 = [];
	}
	
	function initNodeList(v,pageNums,op,data){
		var stringList = "";
		if(data.length != 0){
			stringList = JSON.stringify(data);
		}
		$.ajax({
			data:{
				stringList:stringList,
				pageNums:pageNums,
				op:op
			},
			url:"${pageContext.request.contextPath}/"+v+"/list",
			type:"POST",
			dataType:"text",
			async:false,
			success:function(data){
				$("#"+v+"List").children().remove();
				$("#"+v+"List").append(data);
			}
		});	
	}
	initNodeList('reportWeeklyFinancingIntoDetail',1,"",data1);//初始化筹资性流入明细列表
	initNodeList('reportWeeklyFinancingOutDetail',1,"",data2);//初始化筹资性流出明细列表
	initNodeList('reportWeeklyInvestmentOutDetail',1,"",data3);//初始化投资性流出明细列表

</script>
<script type="text/javascript">
		$(document).ready(function(){
			$('#hzcxID').hide();
		});

		//初始化时，给子iframe中添加form元素
		function addForm(){
			//给子页面iframe添加form元素、input元素和file元素:其中excelName、excelData用于导出，其余元素用于导入
			$("#excelIframe").contents().find("body").append("<form id=\"form3\">" +
				"<input type=\"hidden\" id=\"excelName\" name=\"excelName\">" +
				"<input type=\"hidden\" id=\"excelData\" name=\"excelData\">" +
				"<input id=\"upfile\" type=\"file\" name=\"upfile\" onchange=\"parent.submitExcel()\">" +
				"<input id=\"importExcelUrl\" type=\"hidden\">" + 
				"<input id=\"dataNum\" name=\"dataNum\" type=\"hidden\">" +
				"<input id=\"memberCompId\" name=\"memberCompId\" type=\"hidden\">" +
				"<input id=\"memberCompName\" name=\"memberCompName\" type=\"hidden\">" +
				"<input id=\"coreCompId\" name=\"coreCompId\" type=\"hidden\">" +
				"<input id=\"coreCompName\" name=\"coreCompName\" type=\"hidden\"></form>");
		}
		addForm();
		
		//Excel导出
		function exportExcel(name,dataNum){
			//父页面获取子页面隐藏iframe的表单form3和里面的元素
			var form3 = document.getElementById("excelIframe").contentWindow.document.getElementById("form3");
			var excelName = document.getElementById("excelIframe").contentWindow.document.getElementById("excelName");
			excelName.value = $("#checkedCompanyName").val()+$("#year").val()+"年"+$("#month").val()+"月"+$("#week").val()+"周"+name;
			var excelData = document.getElementById("excelIframe").contentWindow.document.getElementById("excelData");
			var v = "";
			var url = "";
			if(dataNum == '1'){
				if(data1.length != 0){
					excelData.value = JSON.stringify(data1);
				}
				v="reportWeeklyFinancingIntoDetail";
			}else if(dataNum == '2'){
				if(data2.length != 0){
					excelData.value = JSON.stringify(data2);
				}
				v="reportWeeklyFinancingOutDetail";
			}else{
				if(data3.length != 0){
					excelData.value = JSON.stringify(data3);
				}
				v="reportWeeklyInvestmentOutDetail";
			}
			/* url = "${pageContext.request.contextPath}/"+v+"/exportExcel?orgName="+$("#checkedCompanyName").val()+"&year="+$("#year").val()+"&month="+$("#month").val()+"&week="+$("#week").val()+"&stringList="+stringList; */
			url = "${pageContext.request.contextPath}/"+v+"/exportExcel";
			form3.action = url;
			form3.method = "post";
			form3.submit();
			
			//没成功，我放弃了！！！
			//传入后台进行Excel导出
			/* $.ajax({
				data:{
					stringList:stringList,
					orgName:$("#checkedCompanyName").val(),
					year:$("#year").val(),
					month:$("#month").val(),
					week:$("#week").val(),
					name:name
				},
				url:"${pageContext.request.contextPath}/"+v+"/exportExcel",
				type:"POST",
				async:false,
				success:function(data){
					 $("#"+v+"List").children().remove();
					$("#"+v+"List").append(data);
				}
			});	 */
		}
		
		//excel导入
		function importExcel(dataNum){
			var v = "";
			if(dataNum == '1'){
				v="reportWeeklyFinancingIntoDetail";
			}else if(dataNum == '2'){
				v="reportWeeklyFinancingOutDetail";
			}else{
				v="reportWeeklyInvestmentOutDetail";
			}
			//给子页面iframe的form中的元素赋值
			document.getElementById("excelIframe").contentWindow.document.getElementById("importExcelUrl").value = v;
			document.getElementById("excelIframe").contentWindow.document.getElementById("dataNum").value = dataNum;
			document.getElementById("excelIframe").contentWindow.document.getElementById("memberCompId").value = $("#organalID").val();
			document.getElementById("excelIframe").contentWindow.document.getElementById("memberCompName").value = $("#checkedCompanyName").val();
			document.getElementById("excelIframe").contentWindow.document.getElementById("coreCompId").value = $("#coreCompId").val();
			document.getElementById("excelIframe").contentWindow.document.getElementById("coreCompName").value = $("#coreCompName").val();
			var file = document.getElementById("excelIframe").contentWindow.document.getElementById("upfile");
			
			file.click();
		}
		function submitExcel(){
		
			//校验此文件是不是Excel
			var tag = document.getElementById("excelIframe").contentWindow.document.getElementById("upfile");
			
			
			if(!fileChange(tag)){
			  tag.value='';
				return false;
			}
			
			var v = document.getElementById("excelIframe").contentWindow.document.getElementById("importExcelUrl").value;
			var url = "${pageContext.request.contextPath}/"+v+"/importExcel";
			var form = document.getElementById("excelIframe").contentWindow.document.getElementById("form3");
			var formData = new FormData(form);
			var dataNum = document.getElementById("excelIframe").contentWindow.document.getElementById("dataNum").value;
			$.ajax({
				data:formData,
				url:url,
				type:"POST",
				async:false,
				contentType: false,  
		        processData: false,
		        dataType:'json',
				success:function(data){
					if(data.length == 0){
						win.errorAlert("文件内容为空或者文件内容不符，导入失败！");
					}else{
						if(dataNum == '1'){//以excel中获取的数据为准，清空掉以前data1中有的json数据，添加新的json数据
							data1=data;
							initNodeList(v,1,"add",data1);
						}else if(dataNum == '2'){
							data2=data;
							initNodeList(v,1,"add",data2);
						}else{
							data3=data;
							initNodeList(v,1,"add",data3);
						}
					}
				},
				error:function(){
					win.errorAlert("文件为空，导入失败！");
				}
			});
		}
		 
		//file校验
		//验证文件类型和大小
		function fileChange(target){
			//检测上传文件的类型 
			var imgName = target.value;
		    var ext,idx;   
		    if (imgName == ''){  
		        win.generalAlert("请选择需要上传的文件!");  
		        return false; 
		    } else {   
		        //检测上传文件的大小        
			    var isIE = /msie/i.test(navigator.userAgent) && !window.opera;  
			    var fileSize = 0;  
			    if (isIE && !target.files){       
			        var filePath = target.value;       
			        var fileSystem = new ActiveXObject("Scripting.FileSystemObject");          
			        var file = fileSystem.GetFile (filePath);       
			        fileSize = file.Size;      
			    } else {      
			        fileSize = target.files[0].size;       
			    }     
				
			    var size = fileSize / 1024*1024;   
				
			    if(size>(1024*1024*3)){ 
			    	 win.errorAlert("上传文件不能大于3M!");
			    	 
			      	return false; 
			    }
			     idx = imgName.lastIndexOf(".");   
		        if (idx != -1){   
		            ext = imgName.substr(idx+1).toUpperCase();   
		            ext = ext.toLowerCase( ); 
		           // alert("ext="+ext);
		            if (ext != 'xls' && ext != 'xlsx'){
		                win.generalAlert("只能上传.xls类型或者.xlsx类型的Excel文件!"); 
		                return false;  
		            }
		            return true;   
		        } else {  
			           win.generalAlert("只能上传.xls类型或者.xlsx类型的Excel文件!");
			            return false;
		        } 
		    }
		}
		
		//新增
		function addEntity(obj){
			/* var myDate = new Date();
			var str = myDate.getFullYear()+"-"+(myDate.getMonth())+"-"+myDate.getDate(); */
			var v=obj.id;
			var newEntity= {};
			if(v == 'reportWeeklyFinancingIntoDetail'){
				newEntity.id=null;
				newEntity.weekId="";
				newEntity.memberCompId=$("#organalID").val();
				newEntity.memberCompName=$("#checkedCompanyName").val();
				newEntity.coreCompId=$("#coreCompId").val();
				newEntity.coreCompName=$("#coreCompName").val();
				//newEntity.loanCompId="";
				newEntity.loanCompName="";
				newEntity.loanBank="";
				newEntity.loanAmount="";
				newEntity.accountDate="";
				newEntity.dueDate="";
				newEntity.lengthOfMaturity="";
				newEntity.financingAccountCost="";
				//新增
				newEntity.type=112;
				//新增流贷
				newEntity.loanType=114;
				data1.push(newEntity);
				initNodeList(v,1,"add",data1);
			}
			if(v == 'reportWeeklyFinancingOutDetail'){
				newEntity.id=null;
				newEntity.weekId="";
				newEntity.memberCompId=$("#organalID").val();
				newEntity.memberCompName=$("#checkedCompanyName").val();
				newEntity.coreCompId=$("#coreCompId").val();
				newEntity.coreCompName=$("#coreCompName").val();
				//newEntity.loanCompId="";
				newEntity.loanCompName="";
				newEntity.repayBank="";
				newEntity.financingType=150;
				newEntity.financingTypeDetail=200;
				newEntity.repayDate="";
				newEntity.repayAmount="";
				newEntity.currency=69;
				data2.push(newEntity);
				initNodeList(v,1,"add",data2);
			}
			if(v == 'reportWeeklyInvestmentOutDetail'){
				newEntity.id=null;
				newEntity.weekId="";
				newEntity.memberCompId=$("#organalID").val();
				newEntity.memberCompName=$("#checkedCompanyName").val();
				newEntity.coreCompId=$("#coreCompId").val();
				newEntity.coreCompName=$("#coreCompName").val();
				newEntity.payProjectName="";
				newEntity.payAmount="";
				newEntity.payDate="";
				newEntity.currency=69;
				newEntity.specialFundSupportPlan="";
				newEntity.investType=250;
				data3.push(newEntity);
				initNodeList(v,1,"add",data3);
			}
			
		}
		
		//编辑
		function modifyEntity(num,v,obj){
			var propertyName = obj.id;
			var propertyVal = obj.value;
			//需要修改的index
			if(v == 'reportWeeklyFinancingIntoDetail'){
				var modifyNum = num -1;
				var theEntity = data1[modifyNum];
				theEntity[propertyName] = propertyVal;
				data1.splice(modifyNum,1,theEntity);
			}
			if(v == 'reportWeeklyFinancingOutDetail'){
				var modifyNum = num -1;
				var theEntity = data2[modifyNum];
				theEntity[propertyName] = propertyVal;
				data2.splice(modifyNum,1,theEntity);
			}
			if(v == 'reportWeeklyInvestmentOutDetail'){
				var modifyNum = num -1;
				var theEntity = data3[modifyNum];
				theEntity[propertyName] = propertyVal;
				data3.splice(modifyNum,1,theEntity);
			}
		}
		//承贷主体编辑
		function modifyComp(num,v,obj){
			if(v == 'reportWeeklyFinancingIntoDetail'){
				var modifyNum = num -1;
				var theEntity = data1[modifyNum];
				for(var i=0; i<obj.length;i++){
					var propertyName = obj[i].id;
					var propertyVal = obj[i].value;
					//console.log(propertyName+","+propertyVal);
					//需要修改的index
					theEntity[propertyName] = propertyVal;
				}
				data1.splice(modifyNum,1,theEntity);
			}
			if(v == 'reportWeeklyFinancingOutDetail'){
				var modifyNum = num -1;
				var theEntity = data2[modifyNum];
				for(var i=0; i<obj.length;i++){
					var propertyName = obj[i].id;
					var propertyVal = obj[i].value;
					//console.log(propertyName+","+propertyVal);
					//需要修改的index
					theEntity[propertyName] = propertyVal;
				}
				data2.splice(modifyNum,1,theEntity);
			}
		}
		
		//删除
		function del(num,v){
			win.confirm('确定要删除此明细？',function(r){
				if(r){
					if(v == 'reportWeeklyFinancingIntoDetail'){
						//需要删除的index
						var delNum = num -1;
						data1.splice(delNum,1);
						win.successAlert('删除成功！');
						initNodeList(v,1,"del",data1);
					}
					if(v == 'reportWeeklyFinancingOutDetail'){
						//需要删除的index
						var delNum = num -1;
						data2.splice(delNum,1);
						win.successAlert('删除成功！');
						initNodeList(v,1,"del",data2);
					}
					if(v == 'reportWeeklyInvestmentOutDetail'){
						//需要删除的index
						var delNum = num -1;
						data3.splice(delNum,1);
						win.successAlert('删除成功！');
						initNodeList(v,1,"del",data3);
					}
				}
			});
		}
		
		
		function commitcheck()
		{
			if($("#commercialNetInflow").val()=="")
			{
				win.errorAlert("经营性净流量(万元)不能为空");
				return false;
			}
			if(parseFloat($("#commercialProject").val()) > parseFloat($("#commercialOut").val())){
				win.errorAlert("经营性在建工程(万元)应小于或等于经营性流出(万元)");
				return false;
			}
			if($("#financingNetInflow").val()=="")
			{
				win.errorAlert("筹资性净流量(万元)不能为空");
				return false;
			}
			if($("#investOut").val()=="")
			{
				win.errorAlert("投资性流出(万元)不能为空");
				return false;
			}
			if($("#investNetInflow").val()=="")
			{
				win.errorAlert("投资性净流量(万元)不能为空");
				return false;
			}
			if($("#lendingOrContributionNetInflow").val()=="")
			{
				win.errorAlert("拆借或贡献流净流量(万元)不能为空");
				return false;
			}
			if($("#cashInto").val()=="")
			{
				win.errorAlert("现金流入(万元)不能为空");
				return false;
			}
			if($("#cashOut").val()=="")
			{
				win.errorAlert("现金流出(万元)不能为空");
				return false;
			}
			if($("#cashNetInflow").val()=="")
			{
				win.errorAlert("现金净流量(万元)不能为空");
				return false;
			}else{
				var cashNetInflow = parseFloat($("#cashNetInflow").val());
				if(cashNetInflow < 0.00 && $("#remark").val() == ""){
					win.errorAlert("合计的现金净流量为负时，填写备注给出解释说明");
					return false;
				}
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
			var url = "${pageContext.request.contextPath}/reportCashflowWeeklyExecute/save";
			
			var stringList1 = "";
			if(data1.length != 0){
				stringList1 = JSON.stringify(data1);
				$("#stringList1").val(stringList1);
			}
			
			var stringList2 = "";
			if(data2.length != 0){
				stringList2 = JSON.stringify(data2);
				$("#stringList2").val(stringList2);
			}
			
			var stringList3 = "";
			if(data3.length != 0){
				stringList3 = JSON.stringify(data3);
				$("#stringList3").val(stringList3);
			}
			
			var formData = new FormData($("#form2")[0]);
			$.ajax({  
			     url : url,  
			     type : "POST",  
			     data : formData,
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
			var url = "${pageContext.request.contextPath}/reportCashflowWeeklyExecute/saveandreport";
			
			var stringList1 = "";
			if(data1.length != 0){
				stringList1 = JSON.stringify(data1);
				$("#stringList1").val(stringList1);
			}
			
			var stringList2 = "";
			if(data2.length != 0){
				stringList2 = JSON.stringify(data2);
				$("#stringList2").val(stringList2);
			}
			
			var stringList3 = "";
			if(data3.length != 0){
				stringList3 = JSON.stringify(data3);
				$("#stringList3").val(stringList3);
			}
			
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
		
		$(' input.date').jeDate(
			{
				format:"YYYY-MM-DD"
			}
		)
	
		$("#com_ztree").click(function(){
				setTimeout(function(){
	   				if($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0])
						{
							 $("#organalID").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id)
							 $("#checkedCompanyName").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
							 var organalID = $("#organalID").val();
							 var url = "${pageContext.request.contextPath}/reportCashflowWeeklyExecute/getCoreComp";
							 $.ajax({
							 	type:'POST',
							 	data:{organalID:organalID},
							 	dataType:'json',
							 	url:url,
							 	success:function(data){
							 		$("#coreCompId").val(data.id.nnodeId);
							 		$("#coreCompName").val(data.vcFullName);
							 		$('#hzcxID').hide();
							 		if(data.status==50501)
							 			$('#hzcxID').show();							 			
							 		$("#commercialInto").val('');
							 		$("#commercialOut").val('');
							 		$("#commercialProject").val('');
							 		$("#commercialNetInflow").val('');				 		
							 		$("#financingInto").val('');				 		
							 		$("#financingOut").val(data.financingOut);				 		
							 		$("#financingNetInflow").val('');
							 		$("#investmentInto").val('');				 		
							 		$("#constructionInProcess").val('');				 		
							 		$("#airplaneInvest").val('');				 		
							 		$("#stockEquityInvest").val('');
							 		$("#landReserve").val('');				 		
							 		$("#fixedAssetsPurchase").val('');				 		
							 		$("#financeInvest").val('');
							 		$("#elseInvestExpense").val('');
							 		$("#investOut").val('');				 		
							 		$("#investNetInflow").val('');				 		
							 		$("#lendingOrContributionInto").val('');				 		
							 		$("#lendingOrContributionOut").val('');
							 		$("#lendingOrContributionNetInflow").val('');				 		
							 		$("#cashInto").val('');				 		
							 		$("#cashOut").val('');
							 		$("#cashNetInflow").val('');
							 	}
							 });
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
		
		function calrate()
		{
			//经营性流入
			var commercialInto=$("#commercialInto").val();
			//经营性流出
			var commercialOut=$("#commercialOut").val();
			//经营性在建工程
			var commercialProject=$("#commercialProject").val();
			//经营性净流量
			var commercialNetInflow="";
			
			//筹资性流入
			var financingInto=$("#financingInto").val();
			//筹资性流出
			var financingOut=$("#financingOut").val();
			//筹资性净流量
			var financingNetInflow="";
			
			//投资性流入
			var investmentInto=$("#investmentInto").val();
			//在建工程
			var constructionInProcess=$("#constructionInProcess").val();
			//飞机投资
			var airplaneInvest=$("#airplaneInvest").val();
			//股权投资
			var stockEquityInvest=$("#stockEquityInvest").val();
			//土地储备
			var landReserve=$("#landReserve").val();
			//固定资产购置
			var fixedAssetsPurchase=$("#fixedAssetsPurchase").val();
			//金融性投资
			var financeInvest=$("#financeInvest").val();
			//其它投资性支出
			var elseInvestExpense=$("#elseInvestExpense").val();
			//投资性流出
			var investOut="";
			//投资性净流量
			var investNetInflow="";
			
			//拆借或贡献流入
			var lendingOrContributionInto=$("#lendingOrContributionInto").val();
			//拆借或贡献流出
			var lendingOrContributionOut=$("#lendingOrContributionOut").val();
			//拆借或贡献流净流量
			var lendingOrContributionNetInflow="";
			
			//现金流入
			var cashInto="";
			//现金流出
			var cashOut="";
			//现金净流量
			var cashNetInflow="";
			
			//计算经营性净流量
			if(commercialInto != "" && commercialOut != "" && commercialProject != ""){
				//先验证
				//经营性在建工程是经营性流出的一部分，其值应小于或等于经营性流出
				if(parseFloat(commercialProject) > parseFloat(commercialOut)){
					$("#commercialNetInflow").text("");
					$("#commercialNetInflow").val("");
					commercialNetInflow="";
					win.errorAlert("经营性在建工程(万元)应小于或等于经营性流出(万元)");
				}else{
					commercialNetInflow=(parseFloat(commercialInto)-parseFloat(commercialOut)).toFixed(2);
					$("#commercialNetInflow").text(commercialNetInflow);
					$("#commercialNetInflow").val(commercialNetInflow);
				}
			}else{
				$("#commercialNetInflow").text("");
				$("#commercialNetInflow").val("");
				commercialNetInflow="";
			}
			
			//计算筹资性净流量
			if(financingInto != "" && financingOut != ""){
				financingNetInflow=(parseFloat(financingInto)-parseFloat(financingOut)).toFixed(2);
				$("#financingNetInflow").text(financingNetInflow);
				$("#financingNetInflow").val(financingNetInflow);
			}else{
				$("#financingNetInflow").text("");
				$("#financingNetInflow").val("");
				financingNetInflow="";
			}
			
			//计算投资性流出
			if(constructionInProcess != "" && airplaneInvest != "" && stockEquityInvest != "" && landReserve != "" && fixedAssetsPurchase != "" && financeInvest != "" && elseInvestExpense != "")
			{	
				investOut=(parseFloat(constructionInProcess)+parseFloat(airplaneInvest)+parseFloat(stockEquityInvest)+parseFloat(landReserve)+parseFloat(fixedAssetsPurchase)+parseFloat(financeInvest)+parseFloat(elseInvestExpense)).toFixed(2);
				$("#investOut").text(investOut);
				$("#investOut").val(investOut);
			}else	
			{	
				$("#investOut").text("");
				$("#investOut").val("");
				investOut="";
			}
			//计算投资性净流量
			if(investmentInto !="" && investOut !="")
			{	
				var investNetInflow=(parseFloat(investmentInto)-parseFloat(investOut)).toFixed(2);
				$("#investNetInflow").text(investNetInflow);
				$("#investNetInflow").val(investNetInflow);
			}else	
			{	
				$("#investNetInflow").text("");
				$("#investNetInflow").val("");
				investNetInflow="";
			}
			
			//计算拆借或贡献流净流量
			if(lendingOrContributionInto != "" && lendingOrContributionOut != ""){
				lendingOrContributionNetInflow=(parseFloat(lendingOrContributionOut)-parseFloat(lendingOrContributionInto)).toFixed(2);
				$("#lendingOrContributionNetInflow").text(lendingOrContributionNetInflow);
				$("#lendingOrContributionNetInflow").val(lendingOrContributionNetInflow);
			}else{
				$("#lendingOrContributionNetInflow").text("");
				$("#lendingOrContributionNetInflow").val("");
				lendingOrContributionNetInflow="";
			}
			
			//计算现金流入
			if(commercialInto != "" && financingInto != "" && investmentInto != ""){
				cashInto=(parseFloat(commercialInto)+parseFloat(financingInto)+parseFloat(investmentInto)).toFixed(2);
				$("#cashInto").text(cashInto);
				$("#cashInto").val(cashInto);
			}else{
				$("#cashInto").text("");
				$("#cashInto").val("");
				cashInto="";
			}
			//计算现金流出
			if(commercialOut != "" && financingOut != "" && investOut != ""){
				cashOut=(parseFloat(commercialOut)+parseFloat(financingOut)+parseFloat(investOut)).toFixed(2);
				$("#cashOut").text(cashOut);
				$("#cashOut").val(cashOut);
			}else{
				$("#cashOut").text("");
				$("#cashOut").val("");
				cashOut="";
			}
			//计算现金净流量
			if(commercialNetInflow != "" && financingNetInflow != "" && investNetInflow != ""){
				cashNetInflow=(parseFloat(commercialNetInflow)+parseFloat(financingNetInflow)+parseFloat(investNetInflow)).toFixed(2);
				$("#cashNetInflow").text(cashNetInflow);
				$("#cashNetInflow").val(cashNetInflow);
			}else{
				$("#cashNetInflow").text("");
				$("#cashNetInflow").val("");
				cashNetInflow="";
			}
		}
		
		function sumData(){
				 var org = $("#organalID").val();
				 var year = $('#year').val();
				 var month= $('#month').val();
				 var week = $('#week').val();
				 var weekStartDate = $('#weekStartDate').val();
				 var weekEndDate=$('#weekEndDate').val();
				 
				 
				 var url = "${pageContext.request.contextPath}/reportCashflowWeeklyExecute/sumDataForNew";
				 $.ajax({
				 	type:'POST',
				 	data:{org:org,year:year,month:month,week:week,weekStartDate:weekStartDate,weekEndDate:weekEndDate},
				 	dataType:'json',
				 	url:url,
				 	success:function(data){
	 					$("#commercialInto").val(data.commercialInto);
				 		$("#commercialOut").val(data.commercialOut);
				 		$("#commercialProject").val(data.commercialProject);
				 		$("#commercialNetInflow").val(data.commercialNetInflow);				 		
				 		$("#financingInto").val(data.financingInto);				 		
				 		$("#financingOut").val(data.financingOut);				 		
				 		$("#financingNetInflow").val(data.financingNetInflow);
				 		$("#investmentInto").val(data.investmentInto);				 		
				 		$("#constructionInProcess").val(data.constructionInProcess);				 		
				 		$("#airplaneInvest").val(data.airplaneInvest);				 		
				 		$("#stockEquityInvest").val(data.stockEquityInvest);
				 		$("#landReserve").val(data.landReserve);				 		
				 		$("#fixedAssetsPurchase").val(data.fixedAssetsPurchase);				 		
				 		$("#financeInvest").val(data.financeInvest);
				 		$("#elseInvestExpense").val(data.elseInvestExpense);
				 		$("#investOut").val(data.investOut);				 		
				 		$("#investNetInflow").val(data.investNetInflow);				 		
				 		$("#lendingOrContributionInto").val(data.lendingOrContributionInto);				 		
				 		$("#lendingOrContributionOut").val(data.lendingOrContributionOut);
				 		$("#lendingOrContributionNetInflow").val(data.lendingOrContributionNetInflow);				 		
				 		$("#cashInto").val(data.cashInto);				 		
				 		$("#cashOut").val(data.cashOut);
				 		$("#cashNetInflow").val(data.cashNetInflow);	
				 		
				 		$.each(data.list1, function(i,item){				 		
							data1.push(item);
					    });
						initNodeList('reportWeeklyFinancingIntoDetail',1,"add",data1);//初始化筹资性流入明细列表
				 			 		
				 		$.each(data.list2, function(i,item){				 					
							data2.push(item);
					    });
						initNodeList('reportWeeklyFinancingOutDetail',1,"add",data2);

						$.each(data.list3, function(i,item){				 		
							data3.push(item);
					    });
						initNodeList('reportWeeklyInvestmentOutDetail',1,"add",data3);		 						 						 		
					}
				 }); 
		}
	</script>
</html>