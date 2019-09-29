$(document).ready(function() {

	$(".departmentLabel >i.iconfont").click(function(){
		$(this).parent(".departmentLabel").siblings("ul.level-2").toggleClass("active");
		$(this).toggleClass("icon-tree-close-2 icon-tree-open-2");
	});
	
	$('.tree-box').on('click','.checkboxList',function() {
		var checked = 
		$(this).closest('li').find('.checkboxList').each(function(){
			var $this = $(this)
			if(checked === undefined)
				checked = $this.hasClass('checkbox_checked')
			if(checked){
				$this.removeClass('checkbox_checked')
				$this.find('input')[0].checked = false
			}else{
				$(this).addClass('checkbox_checked')
				$this.find('input')[0].checked = true
			}
		})
		return false;
	});
});