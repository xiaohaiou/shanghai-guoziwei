$(document).ready(function() {
	$(".departmentLabel >i.iconfont").click(function(){
		$(this).parent(".departmentLabel").siblings("ul.level-2").toggleClass("active");
		$(this).toggleClass("icon-tree-close-2 icon-tree-open-2");
	});
})