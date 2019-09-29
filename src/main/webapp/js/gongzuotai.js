$(function(){
	// 新闻轮播
	var news_banner_num = 0;
	var news_banner_li = document.querySelectorAll(".notice li").length
	// console.log(news_banner_li)
	var news_banner = function(){
		if(news_banner_num==(news_banner_li-2)){
			// console.log("重记");
			news_banner_num=0;
		}
		// console.log($(".notice li").eq(0))
		$(".notice li").hide();
		$(".notice li").eq(news_banner_num).show();
		$(".notice li").eq(news_banner_num+1).show();
		$(".notice li").eq(news_banner_num+2).show();
		news_banner_num++;
		// console.log(news_banner_num)
	}
	setTimeout(news_banner,2000);
	var banner_timer = setInterval(news_banner,2000);
	$(".notice>ul").on("mouseenter",function(){
		clearInterval(banner_timer);
	});
	$(".notice>ul").on("mouseleave",function(){
		banner_timer = setInterval(news_banner,2000);
	})



	// 判断是否出现按钮
	if($('.entrance_box').css('height').slice(0, -2)<= $(".entrance_div").css('height').slice(0, -2)){
		$("#entrance_box_left").addClass('hidden');
		$("#entrance_box_right").addClass('hidden');
	}
	window.onresize=function(){
		if($('.entrance_box').css('height').slice(0, -2)> $(".entrance_div").css('height').slice(0, -2)){
			$("#entrance_box_left").removeClass('hidden');
			$("#entrance_box_right").removeClass('hidden');
		}else if($('.entrance_box').css('height').slice(0, -2)<= $(".entrance_div").css('height').slice(0, -2)){
			$("#entrance_box_left").addClass('hidden');
			$("#entrance_box_right").addClass('hidden');
		}
	}
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