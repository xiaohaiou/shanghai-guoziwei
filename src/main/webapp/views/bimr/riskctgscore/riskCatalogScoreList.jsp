<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>二级风险目录评分</title>
<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
<link rel="stylesheet" href="<c:url value="/css/font/iconfont.css"/>" />
<!-- 库|插件 -->
<!-- 新加样式 -->
<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
</head>
<body>
	<h4>二级风险目录评分</h4>
	<div class="table_content">
		<form id="form1" class="row" method="post">
			<span class="col-md-4">年份： <input type="text" name="year" value="${year}" readonly="true" id="inp_year_month" class="time" /></span> 
			<span class="col-md-4">季度： 
				<select  name="month" class="selectpicker" style="width:80px" onchange="tijiao();">
					<option value="1" <c:if test="${month == 1}">selected="selected"</c:if>>1</option>
					<option value="2" <c:if test="${month == 2}">selected="selected"</c:if>>2</option>
					<option value="3" <c:if test="${month == 3}">selected="selected"</c:if>>3</option>
					<option value="4" <c:if test="${month == 4}">selected="selected"</c:if>>4</option>
				</select> 
			</span>
			<c:if test="${not empty companys}">	
				<span class="col-md-4">机构选择： 
					<select  id="coreOrg" name="coreOrg" class="selectpicker" onchange="tijiao();">
						<c:forEach items="${companys}" var="company" >
						    <option value="${company.nnodeId }" <c:if test="${company.nnodeId == coreOrg}">selected="selected"</c:if>>${company.vcFullName }</option>
						</c:forEach>
					</select> 
					<input type="hidden" name="coreOrgName" value="${coreOrgName}" id="coreOrgName"/>
				</span>	
			</c:if>		
		</form>
		<div class="tablebox">
			<table>
				<tr class="table_header">
					<th style="width:3%" >序号</th>
					<th style="width:15%" >年季度</th>
					<th style="width:20%">二级风险目录名称</th>
					<th style="width:10%" >关联事件个数</th>
					<th style="width:10%" >关联重要风险事件个数</th>
					<th style="width:10%" >机构名称</th>
					<th style="width:15%" >发生概率评分</th>
					<th style="width:15%" >严重程度评分</th> 
				</tr>
				
				<c:if test="${!empty scores }">
					<c:forEach items="${scores}" var="item" varStatus="status">
						<tr>
							<td style="text-align: center;">
							  ${recordNumber+ status.index + 1 }
							  <input type="hidden" id="inp_id${status.index}" name="id" value="${item.id}"/>
							</td>
							<td style="text-align: center;">
							  ${item.year}年第${item.month}季度
							  <input type="hidden" id="inp_year${status.index}" name="year" value="${item.year}"/>
							  <input type="hidden" id="inp_month${status.index}" name="month" value="${item.month}"/>
							</td>
							<td style="text-align: left;">
							  ${item.ctgName}
							  <input type="hidden" id="inp_ctgId${status.index}" name="ctgId" value="${item.ctgId}"/>
							</td>
							<td style="text-align: center;">${item.eventNum}
								 <input type="hidden" id="inp_eventNum${status.index}" value="${item.eventNum}"/>
							</td>
							<td style="text-align: center;">${item.eventImportantNum}
								<input type="hidden" id="inp_eventImportantNum${status.index}" value="${item.eventImportantNum}"/>
							</td>
							<td style="text-align: center;">${item.coreOrgName}
								<input type="hidden" id="inp_coreOrg${status.index}" value="${item.coreOrg}"/>
								 <input type="hidden" id="inp_coreOrgName${status.index}" value="${item.coreOrgName}"/>
							</td>
							<td style="text-align: center;">
								<select id="sel_hapScore${status.index}" name="hapScore" class="selectpicker" style="width:80px">
									<option value="1" <c:if test="${item.hapScore == 1}">selected="selected"</c:if>>1</option>
									<option value="2" <c:if test="${item.hapScore == 2}">selected="selected"</c:if>>2</option>
									<option value="3" <c:if test="${item.hapScore == 3}">selected="selected"</c:if>>3</option>
									<option value="4" <c:if test="${item.hapScore == 4}">selected="selected"</c:if>>4</option>
									<option value="5" <c:if test="${item.hapScore == 5}">selected="selected"</c:if>>5</option>
								</select> 
							</td>
							<td style="text-align: center;">
								<select id="sel_criScore${status.index}" name="criScore" class="selectpicker" style="width:80px">
									<option value="1" <c:if test="${item.criScore == 1}">selected="selected"</c:if>>1</option>
									<option value="2" <c:if test="${item.criScore == 2}">selected="selected"</c:if>>2</option>
									<option value="3" <c:if test="${item.criScore == 3}">selected="selected"</c:if>>3</option>
									<option value="4" <c:if test="${item.criScore == 4}">selected="selected"</c:if>>4</option>
									<option value="5" <c:if test="${item.criScore == 5}">selected="selected"</c:if>>5</option>
								</select> 
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty scores}">
					<tr>
						<td colspan="8" align="center">查询无记录</td>
					</tr>
				</c:if>
			</table>
			<div style="text-align:center;margin-top: 16px;">
				<c:if test="${fn:contains(gzwsession_code,',bimWork_ejfxmlpf_saveRate,')==true}">
				       <button class="btn btn-primary model model_btn_save" id="but_save">保存评分</button>
				</c:if>
			    <c:if test="${fn:contains(gzwsession_code,',bimWork_ejfxmlpf_cancel,')==true}">
				       <button class="btn btn-default model model_btn_close" id="but_cancle">取消</button>
				</c:if>
			</div>
		</div>
	</div>
</body>
<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
<script src="<c:url value="/js/window.js"/>"></script>
<script src="<c:url value="/js/bootstrap-datetimepicker.min.js"/>" charset="utf-8"></script>
<script src="<c:url value="/js/bootstrap-datetimepicker.zh-CN.js"/>" charset="utf-8"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript">
    function chooseYearMonth(obj){
    	var val = obj.val();
    	if(val){
    		$("#coreOrgName").val($("#coreOrg option:checked").text());
    		$("#form1").submit();
    	}
	}
	
	function tijiao(){
		$("#coreOrgName").val($("#coreOrg option:checked").text());
		$("#form1").submit();
	}
    
	$('#inp_year_month').jeDate({
		format:"YYYY",
		choosefun: chooseYearMonth
	});
	
	
	
	$('#but_cancle').click(function(){
		parent.win.confirm('确定要取消二级风险目录评分修改？',function(r){
			if(r){
				$("#form1").submit();
			}
		});
	});
	
	function saveScore(){
		var count = ${scoresCount};
		var objs = [];
		for(var i= 0; i < count; i++){
			var o = {};
			o['id'] = $('#inp_id'+i).val();
			o['ctgId'] = $('#inp_ctgId'+i).val();
			o['year'] = $('#inp_year'+i).val();
			o['month'] = $('#inp_month'+i).val();
			o['hapScore'] = $('#sel_hapScore'+i).val();
			o['criScore'] = $('#sel_criScore'+i).val();
			o['eventNum'] = $('#inp_eventNum'+i).val();
			o['eventImportantNum'] = $('#inp_eventImportantNum'+i).val();
			o['coreOrg'] = $('#inp_coreOrg'+i).val();
			o['coreOrgName'] = $('#inp_coreOrgName'+i).val();
			objs.push(o);
		}
		
		var url = '${mapurl}/save';
		$.blockUI({ message: '提交中', css: { width: '275px' } });
		 $.ajax({
             url: url,
             type: "POST",
             contentType : 'application/json;charset=UTF-8',
             dataType:"json",
             data: JSON.stringify(objs),            
             success: function(data){
            	 $.unblockUI();
            	 if(data.result==true){
     				win.successAlert('保存成功！',function(){
     					//none instance
     			    });
     			}else{
     			    win.errorAlert(data.exceptionStr);
     			}
             },
             error: function(res){
            	 $.unblockUI();
            	 win.errorAlert("保存错误");
             }
         });
	}
	
	$('#but_save').click(function(){
		parent.win.confirm('确定要保存二级风险目录评分？',function(r){
			if(r){
				saveScore();
			}
		});
	});
		
</script>

</html>