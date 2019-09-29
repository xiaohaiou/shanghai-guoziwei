<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style> 
	.modal-container{
		min-width:1300px;
	}
</style>
<div class="modal-container" data-remodal-id="modal" no=text>
	<iframe id="modal-frame" src="" style=""></iframe>
</div>

<script src="<c:url value="/js/remodal.min.js"/>" charset="utf-8"></script>
<script type="text/javascript">
	// modal
	window.modal_load = window.mload = function(url, callback) {
		if (url) {
			var ifm = document.getElementById('modal-frame');
			$('>.modal-close', ifm.parentNode).css('display', 'block')
			ifm.onload = function() {
				ifm.onload = undefined;
				$('>.modal-close', ifm.parentNode).css('display', '')
				var h = ifm.contentDocument.body.scrollHeight;
				ifm.style.height = h+1 + 'px'
				modal_resize();
				if (callback) {
					setTimeout(callback.bind(ifm.contentWindow))
				}
			};
			ifm.src = url;
			$('[data-remodal-id=modal]').remodal({
				closeOnEscape : false,
				closeOnOutsideClick : false,
			}).open();
		}
	}
	window.modal_close = window.mclose = function() {
		var ifm = $('[data-remodal-id=modal] iframe')[0];
		$('[data-remodal-id=modal]').remodal().close();
		ifm.src = '';
		ifm.style.height = '';
	}
	// modal - 居中
	var modal_resize = function() {
		var ifm = document.getElementById('modal-frame');
		var hc = ifm.offsetHeight;
		if (hc == 0)
			return;
		var hr = parseInt(ifm.style.height) || 0;
		var container = ifm.parentNode;
		var c = container.offsetHeight;
			
		if (hc == hr) {
			container.style['padding-top'] = (c - hc) / 2 + 'px';
		} else if(hr < c){
			container.style['padding-top'] = (c - hr) / 2 + 'px';
		} else {			
			container.style['padding-top'] = '';
		}
	}
	function model_height(){
		var ifm = document.getElementById('modal-frame');
		var subWeb = document.frames ? document.frames["modal-frame"].document : ifm.contentDocument;
		//$('.modal-container').css('height',subWeb.body.scrollHeight);
		$('#modal-frame').css('height',subWeb.body.scrollHeight);
	}
	window.setInterval(model_height,200)
	window.setInterval(modal_resize,1e3)
	$(window).resize(modal_resize);
		setInterval(function(){
			var ifm = document.getElementById('modal-frame')
			if(ifm.offsetHeight>0){
				if(document.body.offsetHeight !== ifm.contentDocument.body.scrollHeight){
					document.body.style.height = Math.max(document.body.offsetHeight,ifm.contentDocument.body.scrollHeight)+'px'
				}
			}else if(document.body.style.height !== 'auto'){
				document.body.style.height = 'auto'
			}
		},2e2)
</script>


<link rel="stylesheet" href="<c:url value="/css/remodal.css"/>">
<link rel="stylesheet" href="<c:url value="/css/remodal-default-theme.css"/>">
<style>
/*modal*/
.remodal-wrapper {
	padding: 0px;
	overflow:hidden;
}

.modal-container {
	position: relative;
	padding: 0;
	margin: 0;
	height: 100%;
	background-color: rgba(0, 0, 0, 0);
	max-width: 1000px;
}

[data-remodal-id=modal] iframe {
	position: relative;
	width: 100%;
	max-height: 100%;
	border:0;
	display:block;
}

.modal-close {
	display: none;
	position: absolute;
	height: 20px;
	right: 12px;
	top: 12px;
}

.modal-close>img {
	height: 100%;
}

.remodal-overlay.night-theme {
	background-color: #f44336;
}

.remodal.night-theme {
	background: #fff;
}
</style>
