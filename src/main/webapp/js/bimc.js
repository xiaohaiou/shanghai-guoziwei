$(function(){
	// 工作台，左侧按钮
	var entrance_num = 0;
	$("#entrance_box_left").on("click",function(){
		if($('.entrance_box').css('height').slice(0, -2)<= $(".entrance_div").css('height').slice(0, -2)){
			return
		}else{
			// $("#entrance_box_left").removeClass('entrance_over');
			// $("#entrance_box_right").removeClass('entrance_over');
			var entrance_cell = $(".entrance_div").css('width').slice(0, -2);
			entrance_num+=1;
			$(".entrance_box").css('left', "-"+(entrance_cell*entrance_num)+"px");
			// if($('.entrance_box').css('height').slice(0, -2)<= $(".entrance_div").css('height').slice(0, -2)){
			// 	$("#entrance_box_left").addClass('entrance_over')
			// }
		}
		
	})
	// 工作台，右侧按钮
	$("#entrance_box_right").on("click",function(){
		if(entrance_num==0){
			return
		}else{
			// $("#entrance_box_left").removeClass('entrance_over');
			// $("#entrance_box_right").removeClass('entrance_over');
			var entrance_cell = $(".entrance_div").css('width').slice(0, -2);
			entrance_num-=1;
			$(".entrance_box").css('left', "-"+(entrance_cell*entrance_num)+"px");
			// if($('.entrance_box').css('height').slice(0, -2)<= $(".entrance_div").css('height').slice(0, -2)){
			// 	$("#entrance_box_left").addClass('entrance_over')
			// }
			// if($('.entrance_box').css('height').slice(0, -2)> $(".entrance_div").css('height').slice(0, -2)){
			// 	$("#entrance_box_right").addClass('entrance_over')
			// }
		}
	})
})