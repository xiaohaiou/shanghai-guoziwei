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
		<title>节税任务数据填报</title>
		<link rel="stylesheet" href="<c:url value="/css/lin_1.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/font/iconfont.css"/>" />
		<!-- 库|插件 -->
		<!-- 新加样式 -->
		<link rel="stylesheet" href="<c:url value="/css/bootstrap_self.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/iframe.css"/>">
		<link rel="stylesheet" href="<c:url value="/css/jedate.css"/>">
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/zTreeStyle.css"/>">
		<script src="<c:url value="/js/jquery-3.1.1.min.js"/>" charset="utf-8"></script>
	</head>
	<body>
		<h4>节税金额数据填报</h4>
		<div class="table_content">
		<form id="form1"  class="row"  >
			<span class="col-md-4">
				<span class="search_title">单位名称：</span>
				<input type="hidden" id="organalID" name="org" value="${entityview.org }" >
				<textarea style="display:none;" id="allCompanyTree" >${allCompanyTree}</textarea>
				<input id="company" name="orgName" value="${entityview.orgName }" readonly title="${entityview.orgName }">
				<div class=" com_ztree_box" style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
					<ul id="com_ztree" class="ztree">
					</ul>
				</div>
				<i class="clear iconfont icon-guanbi"></i>
			</span>
			<span class="col-md-4">年月：
				<input  type="text" name="year" value="${entityview.year }" readonly="true" class="time" />
			</span>
			<span class="col-md-4">
				<span class="search_title">审核状态：</span>
				<select  name="status" class="selectpicker" >
				<option value="" >全部</option>
					<c:forEach items="${requestScope.examstatustype}" var="result">
						<option value="${result.id }" <c:if test="${entityview.status == result.id}">selected="selected"</c:if>>${result.description }</option>
					</c:forEach>
				</select>
			</span>
			<div class="form_div col-md-12">
				<c:if test="${fn:contains(gzwsession_code,',bimWork_jsjesjtb_query,')==true }">	
					<input type="button" value="查询" class="form_btn" id="qrybtn" onclick="_query()">
				</c:if>
				<c:if test="${fn:contains(gzwsession_code,',bimWork_jsjesjtb_import,')==true }">	
					<input type="button" value="数据导入" class="form_btn" id="addbtn" onclick="mload('${mapurl}/newmodify')">
				</c:if>
				<c:if test="${fn:contains(gzwsession_code,',bimWork_jsjesjtb_download,')==true }">	
				    <input type="button" value="模板下载" class="form_btn" id="export" onclick="exportfile()">
				</c:if>
				<c:if test="${fn:contains(gzwsession_code,',bimWork_jsjesjtb_total,')==true }">		
					<input type="button" value="汇总查看" class="form_btn" id="exportbtn" onclick="mload('${mapurl}/collect')">
				</c:if>	
				<c:if test="${fn:contains(gzwsession_code,',bimWork_jsjesjtb_export,')==true }">	
					<a href="${pageContext.request.contextPath}/views/report/taxSave/taxSave.xlsx" id="exportfile" style="display:none"></a>
				</c:if>		
			</div>
		</form>
			<div class="tablebox">
				<table>
					<tr class="table_header">
						<th>序号</th>
						<th>时间</th>
						<th>单位名称</th>
						<th>年度节税目标</th>
						<th>本月节税金额</th>
				<!-- 		<th>本年累计节税金额</th> -->
						<th>节税任务完成情况</th>
						<th>创建人</th>
						<th>上报人</th>
						<th>审核人</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
					<c:if test="${!empty requestScope.msgPage.list }">
						<c:set var="recordNumber" value="${(msgPage.pageNum - 1) * 10 }" />
						<c:forEach items="${ requestScope.msgPage.list}" var="sys" varStatus="status">
							<tr>
								<td>
									${recordNumber+ status.index + 1 }
								</td>
								<td>
									${sys.year }
								</td>
								<td>
									${sys.orgName }
								</td>
								<td style="text-align: right">
									<fmt:formatNumber value="${sys.taxTask }"  pattern="#,##0.00" />万元
								</td>
								<td style="text-align: right">
									<fmt:formatNumber value="${sys.taxSave }"  pattern="#,##0.00" />万元
								</td>
								<%-- 				
								<td style="text-align: right">
									<fmt:formatNumber value="${sys.accTaxSave }"  pattern="#,##0.00" />万元
								</td>
								 --%>
								<td style="text-align: right">
									<c:if test="${ !empty sys.taxTask && sys.taxTask !=0}">
										<fmt:formatNumber value="${100*sys.accTaxSave/sys.taxTask }"  pattern="#,##0.00" />
									</c:if> %
								</td>
								<td style="text-align: center">
									${sys.createPersonName }
								</td>
								<td style="text-align: center">
									${sys.reportPersonName }
								</td>
								<td style="text-align: center">
									${sys.auditorPersonName }
								</td>
								<td style="text-align: center">
									<c:if test="${ sys.status==50112}">
										<span style="color:#3366ff">${sys.statusName }</span>
									</c:if>
									<c:if test="${ sys.status==50113}">
										<span style="color:#ff9933">${sys.statusName }</span>
									</c:if>
									<c:if test="${ sys.status==50114}">
										<span style="color:#ff399d">${sys.statusName }</span>
									</c:if>
									<c:if test="${ sys.status==50115}">
										<span style="color:#00cc66">${sys.statusName }</span>
									</c:if>
								</td>
								<td>
									<c:if test="${fn:contains(gzwsession_code,',bimWork_jsjesjtb_query,')==true }">
										<a href="javascript:void(0)" onclick="checkdata('${mapurl}/view?op=modify&id=${sys.id}',${sys.id })" >查看</a>
									</c:if>
									<c:if test="${sys.status==50114 || sys.status==50112 }">
										<c:if test="${fn:contains(gzwsession_code,',bimWork_jsjesjtb_report,')==true }">
											<a href="javascript:void(0)" onclick="checkdata('${mapurl}/postexam?op=report&id=${sys.id}',${sys.id })">上报</a>
										</c:if>
										<c:if test="${fn:contains(gzwsession_code,',bimWork_jsjesjtb_delete,')==true}">
											<a href="javascript:void(0)" onclick="del('${sys.id}')" >删除</a>
										</c:if>
									</c:if>
									
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty requestScope.msgPage.list}">
						<tr>
							<td colspan="12" align="center">
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
	<script type="text/javascript" src="<c:url value="/js/jquery.jedate.min.js"/>"></script>
	<script src="<c:url value="/js/jquery.ztree.all.min.js"/>" type="text/javascript"></script>
	
	<script type="text/javascript">
		// 校验
		function checkdata(url,id){
			var commitresult="";
			var checkurl = "${mapurl}/hasCheck?id=" + id;
			$.ajax({
				type: 'POST',
				url: checkurl,
				// async:false,
				success: function (data){
					commitresult=JSON.parse(data);
					if (commitresult.result == true) {
						mload(url);
					} else {
						win.errorAlert(commitresult.exceptionStr,function(){window.location.reload(true);});
					}
				}
			});
		}
		// 组织机构树
		var timeoutId;
		$("#company").on('focus click',function(){
			console.log($(this).next('.com_ztree_box'));
			$(this).next('.com_ztree_box').css('display','block');
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
				enable:true,
				chkStyle: "checkbox",
				chkboxType: { "Y": "s", "N": "s" }
			},
			data:{
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "pId",
					rootPId: 0
				}
			},
			callback: {
				onCheck: zTreeOnCheck
			}
		};
		/**
		*  勾选树节点时触发事件
		*/
		   function zTreeOnCheck(event, treeId, treeNode){
		   		var nodes = $.fn.zTree.getZTreeObj("com_ztree").getCheckedNodes(true);//获取勾选的节点
			var comIds = '';
			var comName = '';
			for(var i = 0; i < nodes.length; i ++){
				comIds += nodes[i].id + ',';
				comName += nodes[i].name + ',';
			}
			comIds = comIds.substring(0, comIds.length-1);
			comName = comName.substring(0, comName.length-1);
			$("#organalID").val(comIds);
			 $("#company").val(comName);
			 $("#company").attr('title',comName);
		   }
		
		  $(function () {
			     //所有企业数据	
		    var com_data = eval("("+$("#allCompanyTree").val()+")");
			zTreeObj = $.fn.zTree.init($("#com_ztree"), com_ztree_setting, com_data);
			//将每一个tree节点进行遍历，获取Id，与此角色已保存的Id（checked_data）进行比较，Id相同择设为选中
			var treeObj = $.fn.zTree.getZTreeObj("com_ztree");
			var nodes = treeObj.getNodes();
			//transformToArray()此方法获取所有节点（父节点和子节点）
			var childrenNodes = treeObj.transformToArray(nodes);
			if(childrenNodes[0]!=null)
				treeObj.expandNode(childrenNodes[0],true);
			//反绑	
			var ids = $("#organalID").val();
			if(ids != undefined && ids != ''){
				var idArray = ids.split(",");
				for(var i = 0; i < idArray.length; i++){
					var fbNode = treeObj.getNodeByParam("id", idArray[i]);
					expandParent(treeObj, fbNode);
					fbNode.checked = true;
					treeObj.updateNode(fbNode,false );
				}
			}	
		});
		
		/**
			展开上级节点
		*/
		function expandParent(treeObj,fbNode){
			treeObj.expandNode(fbNode,true);
			var pfbNode = treeObj.getNodeByParam("id", fbNode.pId);
			if(pfbNode != null){
				expandParent(treeObj,pfbNode);
			}
		}
		/**
		 * 清空
		 */
		$('.clear').on('click',function(){
			$(this).siblings("#company").val('');
			$("#organalID").val("");
			$("#company").attr('title','');
			$.fn.zTree.getZTreeObj("com_ztree").checkAllNodes(false);//取消勾选 全部节点
		});

		// 日期控件
		$(' input.time').jeDate(
			{
				format:"YYYY-MM"
			}
		);
		// 查询
		function _query(){
			var form = document.forms[0];
			form.action = "${searchurl}";
			form.method = "post";
			form.submit();
		}
		// 删除
		function del(id){
			win.confirm('确定要删除？',function(r){
				if(r){
					var url = "${mapurl}/delete?id=" + id;
					$.post(url, function(data) {
						var commitresult=JSON.parse(data);
						if(commitresult.result == true){
							win.successAlert('删除成功！');
							_query();
						}else{
							win.errorAlert(commitresult.exceptionStr,function(){window.location.reload(true);});
						}
					});
				}
			});
		}
		// 模版下载
		function exportfile(){
			document.getElementById('exportfile').click();
		}
	</script>
</html>