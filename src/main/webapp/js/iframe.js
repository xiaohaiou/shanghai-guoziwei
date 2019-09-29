$(function(){
// 新建角色
$("#model_add").on("click",function(){
	$(".bg_model_xg").css("display","block");
	$("#char_title")[0].innerText="新增";
　　$(".bg_model_xg").fadeTo(300,1);
　　$("body").css({ "overflow": "hidden" });
})
// 模态内标签选择
$(".model_content a").on("click",function(){
	var check_id = $(this).attr("id");
	$(this).addClass("btn-info").removeClass('btn-default').siblings('a').addClass("btn-default").removeClass('btn-info');
	$("."+check_id).addClass('show').removeClass('hidden').siblings('ul').removeClass('show').addClass('hidden');
})

// 删除角色
$(".model_btn_char_del").on("click",function(){
	
})

// 删除员工名单
$("#peopel_list_del").on("click",function(){
	for(var i=0;i<$("#people_choosed li").length;i++){
		// console.log($("#com_list li")[i])
		if($("#people_choosed li")[i].className=="list-group-item com_choosed"){
			$("#people_choosed li")[i].remove();
		}
	}
	$("#peopel_list_add").removeClass('btn-default').addClass('btn-info');
});

});
