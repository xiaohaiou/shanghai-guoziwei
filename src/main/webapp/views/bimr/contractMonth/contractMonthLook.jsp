<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>信息检索查询</title>
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
	<h4>信息检索查询</h4>
	<div class="table_content">

		<form id="form1" class="row">
				<span class="col-md-4"><span class="search_title">单位名称：</span>  <input type="hidden"
					id="compId" name="compId" value="${compId }"> <input
					name="compName" id="compName" value="${compName }" readonly
					title="${compName }">
					<div class="com_ztree_box"
						style="display:none;height:auto;overflow:auto;max-height:300px;box;min-width:63%;position:absolute;left:6rem;background-color:#fff;z-index: 1;">
						<ul id="com_ztree" class="ztree">

						</ul>
					</div> <i class="clear iconfont icon-guanbi"></i> </span>
			
			
				<span class="col-md-4"><span class="search_title">年份：</span> <input type="text" name="year"
					value="${year}" readonly="true" class="time" /> </span> 
					
				<span class="col-md-4"><span class="search_title">信息类型：</span>
					<select name="month"
						class="selectpicker">
							<option value="">全部</option>
							<c:forEach var="temp" begin="1" step="1" end="12">
								<option value="${temp}"
									<c:if test="${month == temp}">selected="selected"</c:if>>
									${temp}月</option>
							</c:forEach>
					</select> 
				</span>
				<span class="col-md-4"><span class="search_title">审核状态：</span> 
					 <select name="status" class="selectpicker">
							<option value="">全部</option>
							<c:forEach items="${contractStatus}" var="result">
								<option value="${result.id }"
									<c:if test="${status == result.id}">selected="selected"</c:if>>
									${result.description}</option>
							</c:forEach>
					</select> 
				</span>
			<br>
			<br>
			<div class="form_div col-md-12">
						<input type="button" value="查询" class="form_btn" id="qrybtn"
							onclick="_query()">
				        <input type="button" value="导出" class="form_btn" id="exportbtn" onclick="_export()"> 
			</div>
		</form>
		<div class="tablebox">
			<table>
				<tr class="table_header">
					<th>序号</th>
					<th>年月</th>
					<th>单位名称</th>
					<th>信息总量</th>
					<th>信息类型</th>
					<th>创建人</th>
					<th>上报人</th>
					<th>审核人</th>
					<th>状态</th>
					<th>操作</th>

				</tr>

				<c:if test="${!empty requestScope.msgPage.list }">
					<c:set var="recordNumber"
						value="${(requestScope.msgPage.pageNum - 1) * 10 }" />
					<c:forEach items="${ requestScope.msgPage.list}" var="resultList"
						varStatus="status">
						<tr>
							<!-- 序号 -->
							<td style="text-align: center;">${recordNumber+ status.index
								+ 1 }</td>
							<!-- 年月 -->
							<td style="text-align: center;">${resultList.year
								}-${resultList.month }</td>
							<!-- 单位 -->
							<td style="text-align: left;">${resultList.compName }</td>
							<!-- 合同数 -->
							<td style="text-align: right;">${resultList.totalContract }
							</td>
							<!-- 合同总额-->
							<td style="text-align: right;">${resultList.totalMoney }</td>
							<!-- 创建人-->
							<td style="text-align: center;">
								${resultList.createPersonName }</td>
							<!-- 上报人-->
							<td style="text-align: center;">
								${resultList.reportPersonName }</td>
							<!-- 审核人-->
							<td style="text-align: center;">
								${resultList.auditorPersonName }</td>
							<!-- 状态-->
							<td><c:if test="${ resultList.status==50112}">
									<span style="color:#3366ff">未上报</span>
								</c:if> <c:if test="${ resultList.status==50113}">
									<span style="color:#00a59d">待审核</span>
								</c:if> <c:if test="${ resultList.status==50114}">
									<span style="color:#ff399d">已退回</span>
								</c:if> <c:if test="${ resultList.status==50115}">
									<span style="color:#00cc66">已审核</span>
								</c:if></td>
							<!-- 操作按钮-->
							<td>
									<a href="javascript:void(0)"
									onclick="mload('${pageContext.request.contextPath}/bimr/contractmonth/view?id=${resultList.id}')">查看</a>
									
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
		zTreeObj = $.fn.zTree
				.init($("#com_ztree"), com_ztree_setting, com_data);
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
		format : "YYYY"
	});
	//-----------------------------------------按钮时间-----------------------------------------------
	function _query() {
		var form = document.forms[0];
		form.action = "${searchurl}";
		form.method = "post";
		form.submit();
	}
	function _export() {
		var form = document.forms[0];
		form.action = "${pageContext.request.contextPath}/bimr/contractmonth/export";
		form.method = "post";
		form.submit();
	}
	function checkdata(url) {
		var commitresult = "";
		var checkurl = url;
		$.ajax({
			type : 'POST',
			url : checkurl,
			// async:false,
			success : function(data) {
				commitresult = JSON.parse(data);
				if (commitresult.result == true) {
					mload(url);
				} else {
					win.errorAlert(commitresult.exceptionStr, function() {
						window.location.reload(true);
					});
				}
			}
		});
	}
	function deleteData(url) {
		parent.win.confirm('确定要删除？', function(r) {
			if (r) {
				$.post(url, function(data) {
					var commitresult = JSON.parse(data);
					if (commitresult.result) {
						win.successAlert('删除成功！', _query);
					} else {
						win.errorAlert('此数据已被删除！');
					}
				});
			}
		});
	}
</script>
</html>