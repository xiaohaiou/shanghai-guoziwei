<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>刑事案件上报</title>
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/workbench_model.css"/>">
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">
		<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	</head>
	<body>
		<h4>
			<span class="glyphicon glyphicon-pencil"></span>刑事案件上报
			<i class="iconfont icon-guanbi"></i>
		</h4>
		<form:form id="form2" modelAttribute="entity" method="post">
			<form:hidden path="id" value="${entity.id}"/>
			<form:hidden path="version" value="${entity.version}"/>
			<form:hidden path="createPersonId" value="${entity.createPersonId}"/>
			<form:hidden path="createPersonName" value="${entity.createPersonName}"/>
			<form:hidden path="createDate" value="${entity.createDate}"/>
			<input type="hidden" id="op" value="${op}"/>
			<input type="hidden" id="caseid" value="${entity.id}"/>
			<div class="label_inpt_div">
				<div class="model_part">
					<label class="w20"><span class="required"> * </span>案件编号：</label>
						<input type="text" name="caseNum" id="caseNum" value="${entity.caseNum}" class="w25" readonly="true"/><br>				
						
					<label class="w20"><span class="required"> * </span>承办单位:</label>
					<span> 
						<input type="hidden" id="vcCompanyId" name="vcCompanyId" value="${entity.vcCompanyId}" >
						<input name="vcCompanyName" id="vcCompanyName" value="${entity.vcCompanyName}" readonly="readonly" class="w70" check="NotEmpty">
						<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:500px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
							<ul id="com_ztree" class="ztree">
							</ul>
					   </div>
				    </span>
				    <br>
						
					<label class="w20"><span class="required"> * </span>犯罪嫌疑人：</label>
						<form:input path="suspect" value="${entity.suspect}" class="w25" check="NotEmpty_string_50_._._._."/>
					<label class="w20"><span class="required"> * </span>所在单位：</label>
						<form:input path="belongCompany" value="${entity.belongCompany}" class="w25" check="NotEmpty_string_50_._._._."/>	
					<label class="w20"><span class="required"> * </span>职务：</label>
						<form:input path="post" value="${entity.post}" class="w25" check="NotEmpty_string_50_._._._."/>
					
					<label class="w20"><span class="required"> * </span>涉嫌罪名：</label>
						<form:input path="accusation" value="${entity.accusation}" class="w25" check="NotEmpty_string_50_._._._."/>
					
					<label class="w20"><span class="required"> * </span>受案单位所在地(省份)：</label>
						<form:input path="province" value="${entity.province}" class="w25" check="NotEmpty_string_50_._._._."/>
					
					<label class="w20"><span class="required"> * </span>基本案情：</label>
						<form:input path="baseMessage" value="${entity.baseMessage}" class="w25" check="NotEmpty_string_100_._._._."/>
					
					<label class="w20"><span class="required"> * </span>涉案金额（人民币万元）：</label>
						<form:input path="amount" value="${entity.amount}" class="w25" check="NotEmpty_double_12_2_*"/>
					<br>	
					<label class="w20"><span class="required"> * </span>是否是清案遗留案件：</label>
						<form:select path="isLeftoverCases" class="w25" >
							<form:option value="1">是</form:option>
							<form:option value="0">否</form:option>
						</form:select>
					<label class="w20"><span class="required"> * </span>是否是新增案件(回头看)：</label>
						<form:select path="isNewadd" class="w25" >
							<form:option value="1">是</form:option>
							<form:option value="0">否</form:option>
						</form:select>
					<label class="w20"><span class="required"> * </span>是否因人员优化工作引发案件：</label>
						<form:select path="isStaffOptimization" class="w25" >
							<form:option value="1">是</form:option>
							<form:option value="0">否</form:option>
						</form:select>
					<br>
					<label class="w20"><span class="required"> * </span>案发时间：</label>
						<form:input path="caseDate" value="${entity.caseDate}"  readonly="true" class="w25 time" check="NotEmpty"/><br>
					
					<label class="w20"><span class="required"> * </span>案龄：</label>
						<form:input path="caseAge" value="${entity.caseAge}"  class="w25" check="NotEmpty_int"/><br>
						
					<label class="w20"><span class="required"> * </span>案件类型：</label>
						<form:select path="type" class="w25" >
							<form:option value="1">重大刑事案件</form:option>
							<form:option value="2">一般刑事案件</form:option>
						</form:select>
					
					<label class="w20"><span class="required"> * </span>最新进展：</label>
						<form:input path="latestProgress" value="${entity.latestProgress}" class="w25" check="NotEmpty_string_100_._._._."/>
						
					<label class="w20"><span class="required"> * </span>办案单位：</label>
						<form:input path="caseHandlingUnit" value="${entity.caseHandlingUnit}" class="w25" check="NotEmpty_string_100_._._._."/>
						
					<label class="w20"><span class="required"> * </span>办案人：</label>
						<form:input path="caseHandlingPerson" value="${entity.caseHandlingPerson}" class="w25" check="NotEmpty_string_100_._._._."/>
						
					<label class="w20"><span class="required"> * </span>集团内部承办单位：</label>
						<form:input path="groupCompany" value="${entity.groupCompany}" class="w25" check="NotEmpty_string_100_._._._."/>
						
					<label class="w20"><span class="required"> * </span>法务承办人：</label>
						<form:input path="lawWork" value="${entity.lawWork}" class="w25" check="NotEmpty_string_50_._._._."/>
						
					<label class="w20"><span class="required"> * </span>是否已问责：</label>
						<form:select path="isAccountability" class="w25" >
							<form:option value="1">是</form:option>
							<form:option value="0">否</form:option>
						</form:select>
						
					<label class="w20"><span class="required"> * </span>是否进行成因分析：</label>
						<form:select path="isAnalysisReason" class="w25" >
							<form:option value="1">是</form:option>
							<form:option value="0">否</form:option>
						</form:select>
					<label class="w20"><span class="required"> * </span>案件成因：</label>
						<input type="text" name="analysisCause" id="analysisCause" value="${entity.analysisCause}" class="w25" check="NotEmpty"/>
					<label class="w20"><span class="required"> * </span>拟问责建议（若无需问责，请说明具体原因）（实业）：</label>
						<input type="text" name="accountabilitySuggest" id="accountabilitySuggest" value="${entity.accountabilitySuggest}" class="w25" check="NotEmpty"/>	
					<label class="w20"><span class="required"> * </span>问责进展情况（含问责人数及名单）（实业）：</label>
						<input type="text" name="accountabilityResults" id="accountabilityResults" value="${entity.accountabilityResults}" class="w25" check="NotEmpty"/>
					<label class="w20"><span class="required"> * </span>协办人(实业)：</label>
						<input type="text" name="assistingPeople" id="assistingPeople" value="${entity.assistingPeople}" class="w25" check="NotEmpty"/>	
					<label class="w20">案件移交公文编号：</label>
						<input type="text" name="transferredNumber" id="transferredNumber" value="${entity.transferredNumber}" class="w25" "/>
					<label class="w20">结案时间及结案报告公文号（实业）：</label>
						<input type="text" name="closingNumber" id="closingNumber" value="${entity.closingNumber}" class="w25" "/>
					<label class="w20"><span class="required"> * </span>规划结案时间：</label>
						<input type="text" name="casePlanDate" id="casePlanDate" value="${entity.casePlanDate}" readonly="true" class="w25 time" check="NotEmpty"/>	
					<label class="w20">备注（实业）：</label>
						<input type="text" name="remark1" id="remark1" value="${entity.remark1}" class="w25" "/>
					
						
					<label class="w20">报案书:</label>
					<!-- <input class="w25" type="file" id="reportmentFile" name="reportmentFile"/> -->
					<button class="" id="selectFile" style="margin-bottom: 10px;">选择文件</button>
					<div id="files" style="display:none;"></div>
					<input class="w25" type="hidden" id="reportment" name="reportment" value="${entity.reportment}"/>
					<c:if test="${not empty entity.reportment}">
					<c:if test="${not empty reportmentFile}">
						<%-- <a href="javascript:void(0)" onclick="downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(reportmentFile.filePath,"\\","\\\\")}&_name=${reportmentFile.fileName}')" target="_blank">${reportmentFile.fileName}</a> --%>
						<c:forEach items="${reportmentFile}" var="reportmentFile" varStatus="index">
						<span id="${index.index+1}" >${index.index+1}.	<a href="javascript:void(0)" onclick="downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(reportmentFile.filePath,"\\","\\\\")}&_name=${reportmentFile.fileName}')" target="_blank">${reportmentFile.fileName}</a> 
						<i class=del style="cursor: pointer;" onclick="delfile(${index.index+1},'${reportmentFile.fileUuid}',1)">&times;</i></span>
						</c:forEach>
					</c:if>
					</c:if>
					<br>
					<label class="w20">判决书:</label>
					<!-- <input class="w25" type="file" id="judgmentFile" name="judgmentFile"/> -->
					<button class="" id="selectFile2" style="margin-bottom: 10px;">选择文件</button>
					<div id="files2" style="display:none;"></div>
					<input class="w25" type="hidden" id="judgment" name="judgment" value="${entity.judgment}"/>
					<c:if test="${not empty entity.judgment}">
					<c:if test="${not empty judgmentFile}">
                    <%--	<a href="javascript:void(0)" onclick="downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(judgmentFile.filePath,"\\","\\\\")}&_name=${judgmentFile.fileName}')" target="_blank">${judgmentFile.fileName}</a>--%>
 						<c:forEach items="${judgmentFile}" var="judgmentFile" varStatus="index">
						<span id="${index.index+1}" >${index.index+1}.	<a href="javascript:void(0)" onclick="downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(judgmentFile.filePath,"\\","\\\\")}&_name=${judgmentFile.fileName}')" target="_blank">${judgmentFile.fileName}</a> 
						<i class=del style="cursor: pointer;" onclick="delfile(${index.index+1},'${judgmentFile.fileUuid}',2)">&times;</i></span>
						</c:forEach>
 					</c:if>
					</c:if>
					<br>
					<label class="w20">其它:</label>
					<!-- <input class="w25" type="file" id="oFile" name="oFile"/> -->
					<button class="" id="selectFile3" style="margin-bottom: 10px;">选择文件</button>
					<div id="files3" style="display:none;"></div>
					<input class="w25" type="hidden" id="otherFile" name="otherFile" value="${entity.otherFile}"/>
					<c:if test="${not empty entity.otherFile}">
					<c:if test="${not empty oFile}">
						<c:forEach items="${oFile}" var="oFile" varStatus="index">
						<span id="${index.index+1}" >${index.index+1}.	<a href="javascript:void(0)" onclick="downloadFile('${pageContext.request.contextPath}/common/download?_url=${fn:replace(oFile.filePath,"\\","\\\\")}&_name=${oFile.fileName}')" target="_blank">${oFile.fileName}</a> 
						<i class=del style="cursor: pointer;" onclick="delfile(${index.index+1},'${oFile.fileUuid}',3)">&times;</i></span>
						</c:forEach>
					</c:if>
					</c:if>
				<br>					
				</div>
				<div class="model_btn">
					<button class="btn btn-primary model_btn_ok" id="commit-1" >保存</button>
					<button class="btn btn-primary model_btn_ok" id="commit-2" >保存并上报</button>
					<button class="btn btn-default model model_btn_close">关闭</button>
				</div>
			</div>
		</form:form>
	</body>
	<link href="<c:url value="/css/window.css"/>" rel="stylesheet" />
	<script src="<c:url value="/js/window.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.ztree.all.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/vaild.js"/>" charset="utf-8"></script>
	<script type="text/javascript">
		$(".icon-guanbi").click(function () {
			parent.mclose();
			return false;
		});
		//关闭弹窗
		$(".model_btn_close").click(function () {
	        window.parent.mclose();
			return false;
		});
		
		$("#commit-1").click(function(){
			if(!vaild.all())
			{
				return false;
			}
			var formData = new FormData($("#form2")[0]);
			var url = "${pageContext.request.contextPath}/bimr/case/saveCriminalcase";
		    $.ajax({  
			     url : url,  
			     type : "POST",  
			     data: formData,
		         async: false,  
		         cache: false,  
		         contentType: false,  
		         processData: false,  
			     success : function(data) {
				    if(data == "success"){
						win.successAlert('保存成功！',function(){
							window.parent.location.reload();
							window.parent.mclose();
							
						});
					}
			     },  
			     error : function(data) {
			     }  
			}); 
			return false;
		});
		
		$("#commit-2").click(function(){
			if(!vaild.all())
			{
				return false;
			}
			var formData = new FormData($("#form2")[0]);
			var url = "${pageContext.request.contextPath}/bimr/case/commitCriminalcase";
		    $.ajax({  
			     url : url,  
			     type : "POST",  
			     data: formData,
		         async: false,  
		         cache: false,  
		         contentType: false,  
		         processData: false,  
			     success : function(data) {
				    if(data == "success"){
						win.successAlert('提交审核成功！',function(){
							window.parent.location.reload();
							window.parent.mclose();
							
						});
					}
			     },  
			     error : function(data) {
			     }  
			}); 
			return false;
		});
				
		$('input.time').jeDate({
			format:"YYYY-MM-DD"
		});
		
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
		function downloadFile(url){
			$.ajax({  
			     url : url,  
			     type : "POST",  
		         success : function(data) {
					var iframe = document.createElement("iframe");  
					iframe.src = url;  
					iframe.style.display = "none";  
					document.body.appendChild(iframe);
			     },  
			     error : function(data) {  
			     	win.errorAlert("下载失败！");
			     }  
			});
		}
		$(function () {
		     //所有企业数据	
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
					$("#vcCompanyId").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].id);
					$("#vcCompanyName").val($.fn.zTree.getZTreeObj("com_ztree").getSelectedNodes()[0].name);
				}
		   	});
		});
		function delfile(e,f,type){
			var id= $("#caseid").val();
			var url = "${pageContext.request.contextPath}/bimr/case/delfileCirminal?id="+id+"&uuid="+f+"&type="+type;
			 $.ajax({
                url : url,
                type : "POST",
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success : function(data) {
                    var r = data.result;
                    if (r == 1){
                        win.successAlert(data.message,function(){
                        //$("#"+e).empty();
                           location.reload(true);
                           // parent.mclose();
                           return;
                        });
					}else{
                        win.errorAlert(data.message);
                        return;
					}
                }
            });
		};
		(function() {
					var count = 0;
					 var clicked = function() {
						var files = $(this).closest('#files')
						$(this).closest('label').remove()
						if (files.find('label').length == 0) {
							files.css('display', 'none');
						}
						return false;
					}
					var changed = function() {
						if (this.value) {
							$(this).closest('label')
								.removeClass('hidden')
								.find('span')
								.text(this.value.substring(this.value.replace(/\\/g, '/').lastIndexOf('/') + 1));
						}
						$('.file.hidden').remove();
					}
					$("#files").on('click', 'label.file i.del', clicked)
						.on('change', 'label.file input[type=file]', changed)
						.on('blur', 'label.file input[type=file]', changed)
				    $("#selectFile").click(function(){
						var files = $(this).parent().find('#files')	
						files.css('display', 'block')
						var id = 'file-' + (++count)
						var el = $('<label class="file hidden"><input class="file" style="display : none;" type="file" name="reportmentFile" id="' + id +
								'" value=""/><span></span><i class=del>&times;</i></label>')
							.appendTo(files)
							.trigger('click')
							return false;
					});
				})();
				(function() {
					var count = 0;
					 var clicked = function() {
						var files = $(this).closest('#files2')
						$(this).closest('label').remove()
						if (files.find('label').length == 0) {
							files.css('display', 'none');
						}
						return false;
					}
					var changed = function() {
						if (this.value) {
							$(this).closest('label')
								.removeClass('hidden')
								.find('span')
								.text(this.value.substring(this.value.replace(/\\/g, '/').lastIndexOf('/') + 1));
						}
						$('.file.hidden').remove();
					}
					$("#files2").on('click', 'label.file i.del', clicked)
						.on('change', 'label.file input[type=file]', changed)
						.on('blur', 'label.file input[type=file]', changed)
				    $("#selectFile2").click(function(){
						var files = $(this).parent().find('#files2')	
						files.css('display', 'block')
						var id = 'file-' + (++count)
						var el = $('<label class="file hidden"><input class="file" style="display : none;" type="file" name="judgmentFile" id="' + id +
								'" value=""/><span></span><i class=del>&times;</i></label>')
							.appendTo(files)
							.trigger('click')
							return false;
					});
				})();
				(function() {
					var count = 0;
					 var clicked = function() {
						var files = $(this).closest('#files3')
						$(this).closest('label').remove()
						if (files.find('label').length == 0) {
							files.css('display', 'none');
						}
						return false;
					}
					var changed = function() {
						if (this.value) {
							$(this).closest('label')
								.removeClass('hidden')
								.find('span')
								.text(this.value.substring(this.value.replace(/\\/g, '/').lastIndexOf('/') + 1));
						}
						$('.file.hidden').remove();
					}
					$("#files3").on('click', 'label.file i.del', clicked)
						.on('change', 'label.file input[type=file]', changed)
						.on('blur', 'label.file input[type=file]', changed)
				    $("#selectFile3").click(function(){
						var files = $(this).parent().find('#files3')	
						files.css('display', 'block')
						var id = 'file-' + (++count)
						var el = $('<label class="file hidden"><input class="file" style="display : none;" type="file" name="oFile" id="' + id +
								'" value=""/><span></span><i class=del>&times;</i></label>')
							.appendTo(files)
							.trigger('click')
							return false;
					});
				})();
	</script>
</html>