
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
			alert("账号必须填写!!");
			return;
		}if ($("input[name='password']").val() == "") {
			alert("密码必须填写!!");
			return;
		} else {
			$.ajax({
				url : "/shanghai-gzw/sys/adConnect/_adConnect",
				type : "POST",
				data : {
					username : $("input[name='username']").val(),
					password : $("input[name='password']").val()
				},
				success : function(data) {
					//alert("域登录成功!");
					if(data.toString() == "failByTwo"){
						alert("已有用户登入，未及时注销！");
						return;
					}else{
						if(data.toString() == "outOfTimes" ){
							alert("密码错误次数过多，请明天再来登录！");
						}
						if(data.toString() =="success"){
							location.href ="/shanghai-gzw/system/ladp_portal";
						}else{
							alert("登入密码或账号有误，请重新输入！");
						}	
					}
				},
				error : function() {
					alert("登陆失败");
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
    if (pos != -1){                                             //如果pos值为-1则说明搜索"version="失败
        var start = pos + name.length;                  //cookie值开始的位置
        var end = allcookies.indexOf(";",start);        //从cookie值开始的位置起搜索第一个";"的位置,即cookie值结尾的位置
        if (end == -1) end = allcookies.length;        //如果end值为-1说明cookie列表里只有一个cookie
        var value = allcookies.substring(start,end); //提取cookie的值
        return unescape(value);                           //对它解码      
	}   
    else return "";                               //搜索失败，返回空字符串
}

//删除cookie
function deleteCookie(name,path){
    var name = escape(name);
    var expires = new Date(0);
     path = path == "" ? "" : ";path=" + path;
     document.cookie = name + "="+ ";expires=" + expires.toUTCString() + path;
}