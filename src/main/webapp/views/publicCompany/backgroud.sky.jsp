<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<style>
	body {
	   background: url(../img/bg_f.png) 
	   no-repeat bottom, 
	   url(../img/light.png) no-repeat top, 
	   -webkit-linear-gradient(top, #075cb0, #0e8fe7)
	}
</style>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font/iconfont.css" type="text/css">
<!-- flag -->
<!-- 总开关 -->
<!-- 
<a class="sent-order active" href="javascript:;" title="指令开关">
	<i class="iconfont icon-qizi"></i>
</a>

<a class="orderbtn show" href="javascript:;" style="top: 66px;right: 10px;">
	<i class="iconfont icon-qizi" onclick="sendorder(13,5,'当前预估利润')"></i>
</a>
 -->
<!-- js -->
<script>
	window.addEventListener("load",function(){
		var click = function() {
			$(this).toggleClass("active");
			$(".orderbtn").toggleClass("show");
			return false;
		};
		$(".sent-order").click(click);
		$(document).click(function() {
			$(".sent-order").removeClass("active");
		});
	});
	
</script>
<!-- 样式 -->
<style>
	a.sent-order {
		display: block;
		position: fixed;
		z-index: 1002;
		background-color: #0871a5;
		width: 50px;
		height: 45px;
		right: -10px;
		top: 120px;
		border-radius: 40px 0px 0px 40px;
		text-align: center;
		transition: all linear 0.2s;
	}
	a.sent-order i {
		display: block;
		text-align: center;
		transition: all linear 0.2s;
		font-size: 30px;
		color: rgba(255, 255, 255, 0.6);
		line-height: 45px;
	}
	a.sent-order:hover,
	a.sent-order.active {
		right: 0px;
		background-color: #009be7;
	}
	a.sent-order:hover i,
	a.sent-order.active i {
		color: rgba(255, 255, 255, 1);
	}
	a.orderbtn {
		display: none;
		position: absolute;
		width:auto;
		top: 22px;
   		right: 17px;
		z-index: 999;
	}
	a.orderbtn.larger {
		right: calc(50% - 150px) !important;
		top: 50px;
	}
	a.orderbtn i {
		color: rgba(255, 255, 255, 0.4);
		font-size: 30px;
	}
	a.orderbtn.show i{
		animation:opacity_1 1s infinite;animation-iteration-count: 1;
	    -webkit-animation:opacity_1 0.5s;
	    -webkit-animation-iteration-count:1;
		opacity: 1;
	}
	.show {
	    display: block!important;
	}
	.block {
		position:relative;
	}
	a:focus, a:hover {
	    text-decoration: none;
	}
</style>