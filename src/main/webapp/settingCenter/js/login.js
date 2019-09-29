
//点击登录按钮
$(document).ready(function(){
	
	$("#password").keydown(function(e) {
		if (e.which == 13) {
			$("#submit").click();
		}
	});
	
	$("#username").keydown(function(e) {
		if (e.which == 13) {
			$("#submit").click();
		}
	});
	
	$("#submit").click(function() {
		var username = $("input[name='username']").val();
		if ($("input[name='username']").val() == "") {
			alert("域账号必须填写!!");
			return;
		}if ($("input[name='password']").val() == "") {
			alert("密码必须填写!!");
			return;
		} else {
			$.ajax({
				url : "/bim_portal/sys/adConnect/_adConnect",
				type : "POST",
				data : {
					username : $("input[name='username']").val(),
					password : $("input[name='password']").val()
				},
				success : function(data) {
					if(data.toString() == "unAuthority"){
						alert("系统未授权！");
						return;
					}
					if(data.toString() != "fail"){
						//alert("域登录成功!");
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
		}
	});
});

$("#re").click = function() {
	$("#username").val("");
	$("#password").val("");
};	
//分析cookie值，显示上次的登陆信息
	

function setCookie(name,value,hours,path){
	var name = escape(name);
	var value = escape(value);
	var expires = new Date();
	 expires.setTime(expires.getTime() + hours*3600000);
	 path = !path? "" : ";path=" + path;
	 _expires = (typeof hours) == "string" ? "" : ";expires=" + expires.toUTCString();
	 document.cookie = name + "=" + value + _expires + path;
}

//获取cookie值
function getCookieValue(name){
	var name = escape(name);
	//读cookie属性，这将返回文档的所有cookie
	var allcookies = document.cookie;	   
	//查找名为name的cookie的开始位置
	 name += "=";
	var pos = allcookies.indexOf(name);	
	//如果找到了具有该名字的cookie，那么提取并使用它的值
	if (pos != -1){											 //如果pos值为-1则说明搜索"version="失败
		var start = pos + name.length;				  //cookie值开始的位置
		var end = allcookies.indexOf(";",start);		//从cookie值开始的位置起搜索第一个";"的位置,即cookie值结尾的位置
		if (end == -1) end = allcookies.length;		//如果end值为-1说明cookie列表里只有一个cookie
		var value = allcookies.substring(start,end); //提取cookie的值
		return unescape(value);						   //对它解码	  
	}   
	else return "";							   //搜索失败，返回空字符串
}

//删除cookie
function deleteCookie(name,path){
	var name = escape(name);
	var expires = new Date(0);
	 path = path == "" ? "" : ";path=" + path;
	 document.cookie = name + "="+ ";expires=" + expires.toUTCString() + path;
}

function init(){
	try{
		$.ajax({
			url : "/bim_portal/app/twoDimension/index",
			type : "POST",
			date:{},
			success : function(data) {
				var x = data.indexOf("uuid%3D");
				uuid = data.substring(x+7);
				var content = data;
				// $("#qrcode").attr("src", "/qrcode/${uuid}");
				jQuery("#qrcode").qrcode({
					render: "table", // 渲染方式有table方式和canvas方式
					width: 150,   //默认宽度
					height: 150, //默认高度
					text: content, //二维码内容
					typeNumber: -1,   //计算模式一般默认为-1
					correctLevel: 2, //二维码纠错级别
					background: "#ffffff",  //背景颜色
					foreground: "#000000"  //二维码颜色
				});
				$("#result").html("扫码登录");
			},
			error : function(data) {
			}
		});
		} catch(e) {
			alert(e);
		};
}

// 二维码扫码
function keepPool(){
	$.post( "/bim_portal/app/twoDimension/pool", {
		uuid : uuid,
	}, function(data) {
		var obj = eval("("+data+")");
		var result=obj[0].result;
		if(result=='success'){
			var username = obj[0].username;
			$("#result").html("登录成功");
			$.ajax({
				url : "/bim_portal/sys/adConnect/_twoDimLogin",
				type : "POST",
				data : {
					username : username
				},
				success : function(data) {
					if(data.toString() == "unAuthority"){
						alert("系统未授权！");
						return;
					}
					if(data.toString() == "noUser"){
						alert("该用户不在岗！");
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