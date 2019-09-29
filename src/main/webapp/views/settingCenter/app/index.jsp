<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>扫码页面</title>
<script type="text/javascript" src="<c:url value="/js/jquery-3.1.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.qrcode.min.js"/>"></script>
<style type="text/css">
body {
	background-color: gray;
	text-align: center;
}

.main {
	width: 370px;
	height: 460px;
	background-color: white;
	top: 50%;
	left: 50%;
	text-align: center;
	position: absolute;
	border-radius: 4px;
	margin-left: -185px;
	margin-top: -230px;
}

.title {
	margin-top: 30px;
	padding: 20px;
	font-size: 20px;
}
.title>table{
	margin: 0 auto;
}
</style>
</head>
<body>
	<div class="main">
		<div id="qrcode" class="title">
			<!-- <img id="qrcode" alt="" src=""> -->
		</div>
		<div id="result" class="title"></div>
	</div>
</body>
<script type="text/javascript">
	var basePath = '${pageContext.request.contextPath}';
	var uuid = '${uuid}';
	$(function() {
		var content = uuid;
		// $("#qrcode").attr("src", "/qrcode/${uuid}");
		jQuery("#qrcode").qrcode({
			render: "table", // 渲染方式有table方式和canvas方式
			width: 256,   //默认宽度
			height: 256, //默认高度
			text: content, //二维码内容
			typeNumber: -1,   //计算模式一般默认为-1
			correctLevel: 2, //二维码纠错级别
			background: "#ffffff",  //背景颜色
			foreground: "#000000"  //二维码颜色
		});
		$("#result").html("使用手机扫描二维码");
		keepPool();
	});

	function keepPool(){
		$.post( basePath + "/app/twoDimension/pool", {
			uuid : uuid,
		}, function(data) {
			var obj = eval("("+data+")");
			var result=obj[0].result;
			if(result=='success'){
				var username = obj[0].username;
				var password = obj[0].password;
				$("#result").html("登录成功");
				//window.location.href = basePath +"/views/app/main.jsp";
				$.ajax({
					url : "/bim_portal/sys/adConnect/_adConnect",
					type : "POST",
					data : {
						username : username,
						password : password
					},
					success : function(data) {
						if(data.toString() == "unAuthority"){
							alert("系统未授权！");
							return;
						}
						if(data.toString() != "fail"){
							location.href ="/bim_portal/system/ladp_portal_login";
						}
						else{
							alert("域账号或密码错误！");
						}					
					},
					error : function() {
						alert("域登陆失败");
					}
				});
			}else if(result=='timeout'){
				$("#result").html("该二维码已经失效,请重新获取");
			}else{
				keepPool();
			}
		});
	}
</script>
</html>