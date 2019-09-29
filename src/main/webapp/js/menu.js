$(document).ready(function() {

	$(".search-order").wrap("<div class=\"scroll-x\"></div>");
	//	$(".mask").height($(document).height());
	$("ul.page-sidebar-menu.page-header-fixed > li.nav-item > a.nav-link.nav-toggle").click(function() {
		$(this).siblings("ul.sub-menu").slideToggle();
		$(this).parent("li.nav-item").addClass("active", "open");
		$(this).children("span.arrow").addClass("open");
		$(this).parent("li.nav-item").siblings("li.nav-item").children("ul.sub-menu").slideUp();
		$(this).parent("li.nav-item").siblings("li.nav-item").removeClass("active", "open");
		$(this).parent("li.nav-item").siblings("li.nav-item").children("a.nav-link.nav-toggle").children("span.arrow").removeClass("open");
	});
	$(".nav-item > ul.sub-menu > li.nav-item > a").click(function() {
		$("html,body").scrollTop(49);
	})
	$(".menu-toggler.sidebar-toggler").click(function() {
		$("ul.page-sidebar-menu.page-header-fixed").toggleClass("page-sidebar-menu-closed");
		$("body").toggleClass("page-sidebar-closed");
		$("img.logo-default1").toggle();
		$("img.logo-default").toggle();
	});

	$("a.btn-sm.btn-more").click(function() {
		$(this).parent(".btn-box").siblings(".more-conditions").toggle();
		$(this).parent().toggleClass("col-sm-3 col-md-3 col-lg-3");
		$(this).parent(".btn-box").parent(".uu").toggleClass("ab-pos");
		$(this).children("span:nth-child(1)").toggle();
		$(this).children("span:nth-child(2)").toggle();
	});

	$("a.btn-more.more-detail").click(function() {
		$(this).parent(".line-b").siblings(".more-detail").toggle();
		$(this).children("span:nth-child(1)").toggle();
		$(this).children("span:nth-child(2)").toggle();
	});


/*	$(".table-link").click(function() {
		parent.window.openDialog($('.jump-floor-1')[0]);
	});*/

	$("a.guoj").click(function() {
		$(this).addClass("active");
		$(this).siblings("a.guoj").removeClass("active");
	});
	
	$("form").keydown(function(event){
		if(event.keyCode==13)return false;
	});

/*	$(".level > ul.xipt> li > i.open").click(function click() {
		$(this).parent("li").addClass("opt");
		$(this).parent("li").parent("ul.xipt").parent(".level").next(".level").children("ul.xipt").toggleClass("show");
		$(this).parent("li").siblings("li").children("i.open").toggle();
		$(this).parent("li").siblings("li").toggleClass("io");
		$(this).parent("li").siblings("li").removeClass("opt");
		var root_centre_left = this.clientWidth/2;
		var e = this;
		while(true){
			root_centre_left += e.offsetLeft;
			if(e.nodeName == 'UL') break;
			if(e.parentNode) e = e.parentNode;
		}
		
		var child_1 = $(this).parent("li").parent("ul.xipt").parent(".level").next(".level").children("ul.xipt")[0];
		console.log(root_centre_left,child_1.clientWidth/2)
		var ul_width = root_centre_left - child_1.clientWidth/2;
		ul_width = ul_width<0?0:ul_width+child_1.clientWidth>e.parentNode.clientWidth?e.parentNode.clientWidth-child_1.clientWidth/2:ul_width;
        //判断是否靠边（左右）
        $(this).parent("li").parent("ul.xipt").parent(".level").next(".level").children("ul.xipt").css("margin-left", ul_width+'px');
		//$(this).find("> .level > ul.xipt > li > i.open").click(click)
		$("#os2 .optiscroll-content").css("overflow-y","hidden");
		$("#os2 .optiscroll-v").css("display","none");
		var chus_height = $(".level > ul.xipt.level-2").outerHeight();
		var ul_height = $(".level").find("ul.xipt.show").outerHeight();
		var ul_nmber = $(".level").find("ul.xipt.show").length;
		var total_heihgt = ul_height * ul_nmber + chus_height;
		$(".tree-height-1").css("height",total_heihgt+120+'px');
//		if($(this).parent("li").parent("ul.xipt").parent(".level").siblings(".level").find("ul.xipt.show").length  > 1) {
//			$(this).parent("li").parent("ul.xipt").parent(".level").nextAll(".level").find("ul.xipt.show").removeClass("show");
//      }
	})*/

})