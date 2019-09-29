// 类数组 转换成 数组
var toArray = Array.from || function(arrayLike) {
	return [].slice.call(arrayLike)
};

// 清除文本子节点
var no_text = function(el){
	toArray(el.childNodes).forEach(function(e) {
		if (e.nodeType == 3) {
			el.removeChild(e)
		}
	})
}

document.addEventListener('load',function(){
	toArray(document.body.querySelectorAll('[no~=text]')).forEach(no_text)
})
