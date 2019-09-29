<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.util.UUID" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, ">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>上海市国资委国资监管风控大数据平台</title>
<link rel="apple-touch-icon-precomposed" href="<c:url value="/img/dt.png"/>">
<link rel="stylesheet" href="<c:url value="/css/font/iconfont.css"/>">
<link href="css/lin.css" rel="stylesheet" type="text/css">
<style type="text/css">
	.hn_log_title{
		position:relative;
	}
	.hn_log_title img{
		position:absolute;
	    top: -48px;
    	right: -210px;;
	}
.main {
	width: 250px;
	height: 250px;
	background-color: white;
    top: calc(50% + 100px);
    right: calc(50% + 425px);
	text-align: center;
	position: absolute;
	border-radius: 4px;
	margin-right: -555px;
	margin-top: -150px;
}

.title {
	margin-top: 10px;
	padding: 10px;
	font-size: 20px;
}
.title>table{
	margin: 0 auto;
}
.change{
	display: inline-block;
    position: absolute;
    right: 60px;
    bottom: 280px;
}
.triangle{
    width: 0;
    height: 0;
    border-top: 50px solid #a2aab5;
    border-left: 50px solid transparent;
}
.change i{
    position: absolute;
    top: 3px;
    color: #000;
    font-size: 20px;
    cursor: pointer;
}
.app{
    color: #dedede;
    font-size: 16px;
    letter-spacing: 1px;
    text-align: right;
    padding-right: 25%;
    cursor: pointer;
    text-decoration: underline !important;
}
.app a{
    color: #fff;
}
.hn_login_check_inp{
	height: 40px;
	display: block;
	margin: 0px auto;
	padding: 0;
	border: 1px solid rgba(255, 255, 255, 0.37);
	border-radius: 3px;
	margin-bottom: 10px;
	background-color: rgba(85, 85, 85, 0.70);
    border: 1px solid rgba(112, 112, 112, 0.70);
    font-size: 16px;
    color: #e1e1e2;
    padding: 0 20px;
    width: 240px;
}
</style>
<script type="text/javascript" src="<c:url value="/js/jquery-3.1.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.qrcode.min.js"/>"></script>
<script type="text/javascript">
	var basePath = '${pageContext.request.contextPath}';
</script>
<script type="text/javascript" src="<c:url value="/js/login.js"/>"></script>
</head>
<body>
<video autoplay loop poster="${pageContext.request.contextPath}/img/login.jpg" id="bgvid" class="hn_bg">
	<source src="film/login.mp4" type="video/mp4">  
</video>
<!-- 
<div class="hn_top">
  <div class="hn_logo"><img src="img/hn_wl_logo.png" alt="上海市国资委国资监管风控大数据平台"/></div>
</div>
 -->
<div class="hn_login">
	<!-- <img src="img/hn_logo_big.png" alt="海航"/> -->
  <h1 class="hn_log_title">上海市国资委国资监管风控大数据平台
  	<img alt="" src="${pageContext.request.contextPath}/img/V1_0.png">
  </h1>
 

  <div class="main" id="ewm" style="display:none">
		<div id="qrcode" class="title">
			<!-- <img id="qrcode" alt="" src=""> -->
		</div>
		<div id="result" class="title"></div>
	</div>
  <div class="hn_login_entry" id="zhmm">
    <input type="text" class="hn_login_entry_inp" autofocus placeholder="OA账号" name="username" id="username">
    <input type="password" class="hn_login_entry_inp" placeholder="OA密码" name="password" id="password">
    <div >
    <p class="hn_login_check_inp">验证码：<input type="text"  name="checkCode" id="checkCode" onchange="check()">
    <img id ="imageCheckCode" style="position:relative;top:-18px;left:200px;" src="${pageContext.request.contextPath}/pictureCheckCode/getCheckCode" onclick="refresh()"  alt="验证码"/>
    </p> 
    </div>
    <input type="submit" class="hn_login_entry_button" value="登入" id="submit" onclick="javascript:void(0);">
  </div>
  
</div>
		
</body>
<script type="text/javascript">
	function refresh(){
		document.getElementById('imageCheckCode').src=basePath+"/pictureCheckCode/getCheckCode"+"?randomNum="+Math.random()* 1000;
	}
	function check(){
		var code = $('#checkCode').val();
		$.ajax({
			url : basePath+"/pictureCheckCode/checkCode",
			type :"post",
			data :{
				code : code
			},
		success : function(data){
			if(data=="false"){
				$(".hn_login_entry_button").attr("disabled", true);
				alert("验证码不对！请重新输入！");
			}else{
				$(".hn_login_entry_button").attr("disabled", false);
			}
		}
		})
	}
	$(document).ready(function(){
		try{
			//获取工作日历
			$.ajax({
			    url : basePath + "/sys/adConnect/_toVisit",  
			    type : "POST",
			    success : function(data) {
			    },  
			    error : function(data) {
			    }  
			});
			} catch(e) {
				alert(e);
			};
	});
	$("#change").on("click",function(){
		if($(this).attr("title")=="扫码登录"){
			$(".icon-erweima").removeClass("icon-erweima").addClass("icon-denglu");
			$(this).attr("title","账号密码登录");
			$("#zhmm").hide();
			init();
			$("#ewm").show();
		}else{
			$(".icon-denglu").removeClass("icon-denglu").addClass("icon-erweima");
			$(this).attr("title","扫码登录");
			$("#ewm").hide();
			$("#ewm>div").text("");
			$("#zhmm").show();
		}
	});
	var uuid = '';
	$(function() {
		keepPool(uuid);
	});
	
	function toXuanchuan(){
		try{
			//获取工作日历
			$.ajax({
			    url : basePath + "/sys/adConnect/_toVisitPage",  
			    type : "POST",
			    success : function(data) {
			    },  
			    error : function(data) {
			    }  
			});
			} catch(e) {
				alert(e);
			};
		//var url = "http://hobim.hnaholding.com:13628/bimc/xuanchuan.html";
		var url = "http://10.127.25.182:13628/bimc/xuanchuan_up.html";
		window.open(url);
	}
</script>
</html>