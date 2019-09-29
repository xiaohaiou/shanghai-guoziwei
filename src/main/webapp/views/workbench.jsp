<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>工作台</title>
	
	<!-- 库 -->
	<link rel="stylesheet" type="text/css" href="<c:url value="/font/iconfont.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/bim_workbench.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css//scroll/jquery.mCustomScrollbar.css"/>">
	<style>
	
		/* fix 2018-9-11 */
		/* fix end */
	
		body{
			overflow:hidden;
		}
		.part5 .part_body{
		    display: block;
		    height: 84%;
		    overflow: hidden;
		    width: 100%;
		}
		.detail4{
		    position: relative;
		    width: 25%;
		    text-align: center;
		    margin-top: 5vh;
		}
		.detail4>p{
		    color: rgba(255,255,255,0.8);
		}
		.enter{
			display: none;
		}
		.more{
		    float: right;
		    font-size: 14px;
		    margin-right: 5%;
		    cursor: pointer;
		}
		.more:hover{
		    text-decoration: underline;
		}
		.search > input{
			display: inline-block;
			height: 41px;
			color: #ccc;
		}
		input::-webkit-input-placeholder, textarea::-webkit-input-placeholder {
			color: #ccc;
		}
	　　 input:-moz-placeholder, textarea:-moz-placeholder {
			color:#ccc;
		}
		input::-moz-placeholder, textarea::-moz-placeholder {
			color:#ccc;
		}
		input:-ms-input-placeholder, textarea:-ms-input-placeholder {
			color:#ccc;
		}
		.menu.i,.i_btn>i{
			cursor: pointer;
		}
		._p{
		  	font-size: 14px;
		  	line-height:200%
		}
		._hr{
			margin-top:1px;
		    *margin: 0;
		    border: 0;
		    color: rgba(126, 176, 207, 0.45);
		    background-color:rgba(126, 176, 207, 0.45); 
		    height: 1px
		}
		._img{
			 /* width:450px; */
			 height:200px;
			 text-align:center;
			 padding-top:80px;
		}
	</style>
</head>
<body>
	<jsp:include page="/views/public/title.jsp"></jsp:include>
	<script type="text/javascript">
		var to_def_img = function(el){
			el.src = "<c:url value="/img/head.png"/>";
		};
	</script>
	<div class="head">
		<p>工作台</p>
	</div>
	<p class="msg">
		<i class="iconfont icon-broadcast"></i>
	</p>
	<marquee>
		<c:forEach items="${ requestScope.msgPage.list}" var="news" varStatus="status">
			<p>${news.title}：${news.description};</p>
		</c:forEach>
	</marquee>
	<div class="body1">
		<div class="part1">
			<p class="part_title">接口统计</p>
			<div class="part_body">
				<div class="detail" >
					<img src="<c:url value="/img/left_fs.png"/>" height="60" width="60" alt="">
					<p>系统A接口总数</p>
					<span class="num num1">119</span>
				</div>
				<div class="detail" >
					<img src="<c:url value="/img/left_js.png"/>" height="60" width="60" alt="">
					<p>系统B接口总数</p>
					<span class="num num2">139</span>
				</div>
			</div>
			<div class="part_body">
				<div class="detail" >
					<img src="<c:url value="/img/left_zl.png"/>" height="60" width="60" alt="">
					<p>系统C接口总数</p>
					<span class="num num3" id="s-yuqi">22</span>
				</div>
				<div class="detail" >
					<img src="<c:url value="/img/left_rw.png"/>" height="60" width="60" alt="" >
					<p>系统D接口总数</p>
					<span class="num num3">45</span>
				</div>
			</div>
		</div>
		<div class="part2">
			<p class="part_title2">应用中心</p>
				<div class="enter" name="gssgzw" style="display:block;">
						<div class="center_icon" >
							<!--<img src="" height="80" width="80" alt="" onclick="">
							<p>aaa</p>-->
						</div>
				</div>
			<div class="center_btn">
				<table>
					<tr>
						<td class="td_active" id="gssgzw">市国资委</td>
					</tr>
				</table>
			</div>
			<script type="text/javascript">
				$('.center_btn td').on('click',function(){
					$(this).addClass('td_active').siblings('td').removeClass('td_active');
					div_name = $(this).attr('id');
					$("div[name = "+ div_name +"]").show().siblings("div[class=enter]").hide();
				})
			</script>
		</div>
		<div class="part3">
			<p class="part_title">预警中心<span id="warncenterMore" class="more" onclick="openNewWindow('${pageContext.request.contextPath}/warncenter/list');">&gt;&gt;更多</span></p>
			<div class="part_body">
				<div class="work_list" id="warnCenter" style="overflow: hidden">
					
				</div>
			</div>
		</div>
	</div>
	<div class="body2">
		<div class="part4">
			<p class="part_title">业务中心</p>
			<div class="part_body">
				<div class="detail4">
					<span class="icon" onclick="trunToDetail(2,'${pageContext.request.contextPath}/system/menu?menu=2')"><i class="iconfont icon-hangzheng"></i></span>
					<p>财务管理</p>
				</div>
				<div class="detail4">
					<span class="icon" onclick="trunToDetail(4,'${pageContext.request.contextPath}/system/menu?menu=4')"><i class="iconfont icon-renliziyuan"></i></span>
					<p>数据采集模块</p>
				</div>
				<div class="detail4" >
					<span class="icon" onclick="trunToDetail(5,'${pageContext.request.contextPath}/system/menu?menu=5')"><i class="iconfont icon-icon-test"></i></span>
					<p>数据统计模块</p>
				</div>
				<div class="detail4" >
					<span class="icon" onclick="trunToDetail(6,'${pageContext.request.contextPath}/system/menu?menu=6')"><i class="iconfont icon-shzr"></i></span>
					<p>数据治理平台</p>
				</div>
			</div>
			<div class="part_body">
				<div class="detail4">
					<span class="icon"  onclick="trunToDetail(8,'${pageContext.request.contextPath}/system/menu?menu=8')"><i class="iconfont icon-caigouguanli1"></i></span>
					<p>数据分析系统</p>
				</div>
				<div class="detail4">
					<span class="icon"  onclick="trunToDetail(10,'${pageContext.request.contextPath}/settingCenter/manager/_frame')"><i class="iconfont icon-caigouguanli1"></i></span>
					<p>权限管理</p>
				</div>
			</div>
		</div>
		<div class="part5">
			<p class="part_title">消息提醒<span class="more" onclick="openNewWindow('${pageContext.request.contextPath}/protal/list');">&gt;&gt;更多</span></p>
			<div class="part_body">
				<div class="work_list" id="msgList" style="overflow: hidden">
					
				</div>
			</div>
		</div>
		<div class="part6">
			<p class="part_title">知识库</p>
			<p class="search">
				<input type="text" placeholder="请输入搜索内容" id="documentName">
				<span onclick="seachKnowledge1('bimWork_zxk_serach');">搜索</span>
			</p>
			<div class="part_body">
				<div class="detail3">
					<span class="icon" onclick="seachKnowledge2('bimWork_zxk_cw','cw')"><i class="iconfont icon-caiwu1"></i></span>
					<p>财务</p>
				</div>
				<div class="detail3">
					<span class="icon" onclick="seachKnowledge2('bimWork_zxk_xzbg','xzbg')"><i class="iconfont icon-hangzheng"></i></span>
					<p>分析模型</p>
				</div>
				<div class="detail3">
					<span class="icon" onclick="seachKnowledge2('bimWork_zxk_rlzy','rlzy')"><i class="iconfont icon-renliziyuan"></i></span>
					<p>人力资源</p>
				</div>
			</div>
			<div class="part_body">
				<div class="detail3">
					<span class="icon" onclick="seachKnowledge2('bimWork_zxk_cgsj','cgsj')"><i class="iconfont icon-caigouguanli1"></i></span>
					<p>数据治理</p>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<c:url value="/js/jquery-1.8.3.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/mCustomScrollbar.min.js"/>"></script>
	
	<script type="text/javascript">
	var pageList = '${gzwsession_page}';
	var pageCodes = '${gzwsession_code}';
	var basepath = '${pageContext.request.contextPath}';
	$('.center_btn td').on('click',function(){
		$(this).addClass('td_active').siblings('td').removeClass('td_active');
		div_name = $(this).attr('id');
		$("div[name = "+ div_name +"]").show().siblings("div[class=enter]").hide();
	})
	
	
	//初始化指令部分
	/*$.ajax({
		url : basepath+"/task/instruction/initTaskPart",
			type : "POST",
			dataType:"text",
			success : function(data) {
				$(".part1").children().remove();
				$(".part1").append(data);
				console.log($("#s-yuqi").text());
				$("#yujingzhiling").text($("#s-yuqi").text());
			},
			error : function() {
				alert("获取指令失败！");
			}
	});*/
	
	
	
	//初始化app应用中心
	/*$.ajax({
		url : basepath+"/appCenter/getAppCenterPart",
			type : "POST",
			dataType:"text",
			success : function(data) {
				$(".part2").children().remove();
				$(".part2").append(data);
			},
			error : function() {
				alert("初始化app应用中心失败！");
			}
	});*/
	
	//加载工作提醒
	$.ajax({
			url : "/shanghai-gzw/protal/_getPortalMsg",
			type : "POST",
			data : {
			},
			success : function(data) {
				$("#msgList").html(data.toString());
			},
			error : function() {
				alert("获取待办事务失败！");
			}
	});
	
	//加载预警中心数据
	$.ajax({
			url : "/shanghai-gzw/warncenter/_getWarnlMsg",
			type : "POST",
			data : {
			},
			success : function(data) {
				var result = JSON.parse(data);
				$("#warnCenter").html(result[1]);
				if('false'==result[0]){
					$("#warncenterMore").hide();
				}
			},
			error : function() {
				//alert("获取预警消息失败！");
			}
	});
	
	//台帐管理
	function trunToDetail(pageIndex,url){
		// 采购管理
		if (pageIndex == 1){
			if (pageList.indexOf('bimWork_cgsjsb') >= 0 || pageList.indexOf('bimWork_cgsjsh') >= 0){
				//window.location.href = url;
				window.open(url, "_blank");
				return;
			} else {
				alert("您没有这块的权限");
				return;
			}
		}
		// 财务管理
		if (pageIndex == 2){
			
			var auth=pageList.split(",")
			if (auth.indexOf('bimWork_yszxbbsb')>=0 || auth.indexOf('bimWork_yszxbbsh')>=0 || 							
			 auth.indexOf('bimWork_yszxbbhzsb')>=0 || auth.indexOf('bimWork_yszxbbhzsh')>=0 ||
			 auth.indexOf('bimWork_grjksjtb')>=0 || auth.indexOf('bimWork_grjksjsh')>=0 ||
			 auth.indexOf('bimWork_gsygxlyfcybj')>=0 || auth.indexOf('bimWork_grjkcqxxyl')>=0 ||
			 auth.indexOf('bimWork_grjkxxhz')>=0 || auth.indexOf('bimWork_yszqsjtb_nb')>=0 ||
			 auth.indexOf('bimWork_yszqsjsh_nb')>=0 || auth.indexOf('bimWork_yszqmxcx_nb')>=0 ||
			 auth.indexOf('bimWork_yszhzcx_nb')>=0 || auth.indexOf('bimWork_gsdeyszqcx_nb')>=0 ||
			 auth.indexOf('bimWork_hxqydeyszqcx_nb')>=0 || auth.indexOf('bimWork_yszqsjtb_wb')>=0 ||
			 auth.indexOf('bimWork_yszqsjsh_wb')>=0 || auth.indexOf('bimWork_yszqmxcx_wb')>=0 ||
			 auth.indexOf('bimWork_yszhzcx_wb')>=0 || auth.indexOf('bimWork_gsdeyszqcx_wb')>=0 ||
			 auth.indexOf('bimWork_hxqydeyszqcx_wb')>=0 || auth.indexOf('bimWork_cqyszqzkwcsyl_wb')>=0 ||
			 auth.indexOf('bimWork_jwzczbsjtb')>=0 || auth.indexOf('bimWork_jwzczbsjsh')>=0 ||
			 auth.indexOf('bimWork_hsbbsb')>=0 || auth.indexOf('bimWork_hsbbsh')>=0 ||
			 auth.indexOf('bimWork_hswcjgscx')>=0 || auth.indexOf('bimWork_glkjhsjtb')>=0 ||
			 auth.indexOf('bimWork_glkjhsjsh')>=0 || auth.indexOf('bimWork_zycwzbsjtb')>=0 ||
			 auth.indexOf('bimWork_ndzdgztb')>=0 || auth.indexOf('bimWork_ndzdgzsh')>=0 ||
			 auth.indexOf('bimWork_yblrzxztb')>=0 || auth.indexOf('bimWork_yblrzxzsh')>=0 ||
			 auth.indexOf('bimWork_rzxmtjqkCx')>=0 || auth.indexOf('bimWork_gxlrzxztb')>=0 ||
			 auth.indexOf('bimWork_gxlrzxzsh')>=0 || auth.indexOf('bimWork_gxxmhzCx')>=0 ||
			 auth.indexOf('bimWork_cxlrzxztb')>=0 || auth.indexOf('bimWork_cxlrzxzsh')>=0 ||
			 auth.indexOf('bimWork_zqlrzxztb')>=0 || auth.indexOf('bimWork_zqlrzxzsh')>=0 ||
			 auth.indexOf('bimWork_zqlrzhzCx')>=0 || auth.indexOf('bimWork_bzrzxzTb')>=0 ||
			 auth.indexOf('bimWork_bzrzxzSh')>=0 || auth.indexOf('bimWork_zrzxzqkCx')>=0 ||
			 auth.indexOf('bimWork_xzrzxzTb')>=0 || auth.indexOf('bimWork_xzrzxzSh')>=0 ||
			 auth.indexOf('bimWork_zdrzxzqkCx')>=0 || auth.indexOf('bimWork_zjtctb')>=0 ||
			 auth.indexOf('bimWork_zjtcsh')>=0 || auth.indexOf('bimWork_rzxzsjtb')>=0 ||
			 auth.indexOf('bimWork_rzxzsjsh')>=0 || auth.indexOf('bimWork_xjlzjhTb')>=0 ||
			 auth.indexOf('bimWork_xjlzjhSh')>=0 || auth.indexOf('bimWork_xjlyjhTb')>=0 ||
			 auth.indexOf('bimWork_xjlyjhSh')>=0 || auth.indexOf('bimWork_xjlyzxsjtb')>=0 ||
			 auth.indexOf('bimWork_xjlyzxsjsh')>=0 || auth.indexOf('bimWork_clfzsjtb')>=0 ||
			 auth.indexOf('bimWork_clfzsjsh')>=0 || auth.indexOf('bimWork_jwzjcjssjtb')>=0 ||
			 auth.indexOf('bimWork_jwzjcjssjsh')>=0 || auth.indexOf('bimWork_nssjtb')>=0 ||
			 auth.indexOf('bimWork_nssjsh')>=0 || auth.indexOf('bimWork_jsrwsjtb')>=0 ||
			 auth.indexOf('bimWork_jsrwsjsh')>=0 || auth.indexOf('bimWork_jsjesjtb')>=0 ||
			 auth.indexOf('bimWork_jsjesjsh')>=0 || auth.indexOf('bimWork_cwryzzsjtb')>=0 ||
			 auth.indexOf('bimWork_cwryzzsjsh')>=0 || auth.indexOf('bimWork_ndjhjhgl')>=0 ||
			 auth.indexOf('bimWork_jhxmjbxxwh')>=0 || auth.indexOf('bimWork_jhxmsxxxwh')>=0 ||
			 auth.indexOf('bimWork_sqbjxmsh')>=0 || auth.indexOf('bimWork_ldshgsjhsx')>=0 ||
			 auth.indexOf('bimWork_jhzgxxwh')>=0 || auth.indexOf('bimWork_jhsxldcx')>=0 ||
			 auth.indexOf('bimWork_jhsxybcx')>=0 || auth.indexOf('bimWork_ndjhgsfxwtqd')>=0 ||
			 auth.indexOf('bimWork_ndjhzgcswtqd')>=0 || auth.indexOf('bimWork_ndjhgsfxwttj')>=0 ||
			 auth.indexOf('bimWork_bbz')>=0 || auth.indexOf('bimWork_bbys')>=0 ||
			 auth.indexOf('bimWork_cwstz')>=0 || auth.indexOf('bimWork_zsk_cwgl')>=0 ||
			 auth.indexOf('bimWork_wbdbgx')>=0 || auth.indexOf('bimWork_wbdbsjtb')>=0 ||
			 auth.indexOf('bimWork_wbdbsjsh')>=0 || auth.indexOf('bimWork_yswcjgscx')>=0 ||
			 auth.indexOf('bimWork_cwlsstz')>=0 || auth.indexOf('bimWork_swwtbgscx')>=0){
				window.open(url, "_blank");
				return;
			} else {
				alert("您没有这块的权限");
				return;
			}
		}
		
		// 数据采集
		if (pageIndex == 4){
			var auth=pageList.split(",")
			if (auth.indexOf('Work_qyxxcj')>=0 || auth.indexOf('Work_qyxxcjsh')>=0 || 							
			 auth.indexOf('Work_ysxxcj')>=0 || auth.indexOf('Work_ysxxcjsh')>=0 ||
			 auth.indexOf('Work_jgyjsjcj')>=0 || auth.indexOf('Work_jgyjsjcjsh')>=0 ||
			 auth.indexOf('Work_qycwsjcj')>=0 || auth.indexOf('Work_qycwsjcjsh')>=0 ||
			 auth.indexOf('Work_zdsxsjcj')>=0 || auth.indexOf('Work_zdsxsjcjsh')>=0 ||
			 auth.indexOf('Work_cqxxcj')>=0 || auth.indexOf('Work_cqxxcjsh')>=0 ||
			 auth.indexOf('Work_dshkpxxcj')>=0 || auth.indexOf('Work_dshkpxxcjsh')>=0 ||
			 auth.indexOf('Work_zcxxcj')>=0 || auth.indexOf('Work_zcxxcjsh')>=0 ||
			 auth.indexOf('Work_xcsjcj')>=0 || auth.indexOf('Work_xcsjcjsh')>=0 ||
			 auth.indexOf('Work_xfsjcj')>=0 || auth.indexOf('Work_xfsjcjsh')>=0 ||
			 auth.indexOf('Work_djgzxxcj')>=0 || auth.indexOf('Work_djgzxxcjsh')>=0 ||
			 auth.indexOf('Work_zxxxcj')>=0 || auth.indexOf('Work_zxxxcjsh')>=0 ){
				window.open(url, "_blank");
				return;
			} else {
				alert("您没有这块的权限");
				return;
			}
		}
		// 数据统计模块
		if (pageIndex == 5){
			var auth=pageList.split(",")
			if (auth.indexOf('work_qyxxcjhztj')>=0 || auth.indexOf('work_yssjhztj')>=0 || 							
			 auth.indexOf('work_jgyjsjhztj')>=0 || auth.indexOf('work_qycwsjhztj')>=0 ||
			 auth.indexOf('work_zdsxsjhztj')>=0 || auth.indexOf('work_cqxxhztj')>=0 ||
			 auth.indexOf('work_dshkpxxhztj')>=0 || auth.indexOf('work_zcxxhztj')>=0 ||
			 auth.indexOf('work_xcshhztj')>=0 || auth.indexOf('work_xfsjhztj')>=0 ||
			 auth.indexOf('work_djgzxxhztj')>=0 || auth.indexOf('work_zxxxhztj')>=0){
				window.open(url, "_blank");
				return;
			} else {
				alert("您没有这块的权限");
				return;
			}
		}
		// 数据治理模块 
		if (pageIndex == 6){
			var auth=pageList.split(",")
			if (auth.indexOf('work_qyxxsjjhqk')>=0 || auth.indexOf('work_ysxxsjjhqk')>=0 || 							
			 auth.indexOf('work_jgyjsjjhqk')>=0 || auth.indexOf('work_qycwsjjhqk')>=0 ||
			 auth.indexOf('work_zdsxsjjhqk')>=0 || auth.indexOf('work_cqxxhzsjjhqk')>=0 ||
			 auth.indexOf('work_dshkpxxsjjhqk')>=0 || auth.indexOf('work_zcxxsjjhqk')>=0 ||
			 auth.indexOf('work_xcsjjhqk')>=0 || auth.indexOf('work_xfsjjhqk')>=0 ||
			 auth.indexOf('work_djgzxxsjjhqk')>=0 || auth.indexOf('work_zxxxsjjhqk')>=0){
				window.open(url, "_blank");
				return;
			} else {
				alert("您没有这块的权限");
				return;
			}
		}
		// 数据分析模块 
		if (pageIndex == 8){
			var auth=pageList.split(",")
			if (auth.indexOf('work_zymlqd')>=0 || auth.indexOf('work_zymlqdsh')>=0 || 							
			 auth.indexOf('work_sjkb')>=0 || auth.indexOf('work_sjkbsh')>=0 ||
			 auth.indexOf('work_xxjs')>=0 || auth.indexOf('work_xxjssh')>=0){
				window.open(url, "_blank");
				return;
			} else {
				alert("您没有这块的权限");
				return;
			}
		}
		
		
		
		//权限管理
		if (pageIndex == 10){
			var auth=pageList.split(",")
			if (auth.indexOf('work_xtzc')>=0 || auth.indexOf('work_mkzc')>=0 || 							
			 auth.indexOf('work_ymzc')>=0 || auth.indexOf('work_gnzc')>=0 ||
			 auth.indexOf('work_jsgl')>=0 || auth.indexOf('work_mrmkpz')>=0 ||
			 auth.indexOf('work_dlrz')>=0 || auth.indexOf('work_yhgl')>=0){
				window.open(url, "_blank");
				return;
			}else{
				alert("您没有这块的权限");
				return;
			}
		}
	}
	
	function openNewWindowWithAuth(authority,url) {
		if (pageCodes.indexOf(authority) >= 0){
			window.open(url, "_blank");
			return;
		} else {
			alert("您没有这块的权限");
			return;
		}
	}	
	
	function openNewWindowAlarm(id,url) {
		var temp = $("#"+id).text();
		if(temp != 0 && temp != '-' ){
			window.open(url, "_blank");
			return;
		}else{
			return;
		}
	}
	
	function openNewWindow(url) {
			window.open(url, "_blank");
	}	
	
	function seachKnowledge1(authority){
		if (pageCodes.indexOf(authority) >= 0){
			var documentName = $("#documentName").val();
			var url = basepath + '/knowledgeStoreHouse/index?documentName=' + documentName;
			openNewWindow(url);
			return;
		} else {
			alert("您没有这块的权限");
			return;
		}
		
	}
	
	function seachKnowledge2(authority,type){
		if (pageCodes.indexOf(authority) >= 0){
			var documentName = $("#documentName").val();
			var url = basepath + '/knowledgeStoreHouse/moduleList?documentName=' + documentName +"&type=" + type;
			openNewWindow(url);
			return;
		} else {
			alert("您没有这块的权限");
			return;
		}
		
	}
	</script>
</body>
</html>
<script type="text/javascript" src="<c:url value="/js/validation.js"/>"></script>
