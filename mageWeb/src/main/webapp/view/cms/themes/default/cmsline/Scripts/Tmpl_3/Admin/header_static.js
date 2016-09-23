function show_store_nav(store_nav) {
	
	$("#show_store_nav").empty();
	if (store_tmpl_data.ShowNav) {
		$("#store_head_nav_static_tmpl").tmpl(store_nav)
				.appendTo("#show_store_nav");
		
		// 导航栏宽度计算
		var nav_width = 0;
		$("#show_store_nav").find(".subnav_li").each(function() {
					nav_width += $(this).outerWidth();
				});
		nav_width = nav_width > 1200 ? 1200 : nav_width;
		$("#show_store_nav").find(".header_subnav").width(nav_width);
		
		$("#show_store_nav .header_subnav .subnav_li").hover(function () {
			$(this).siblings().removeClass("subnav_header");
			$(this).addClass("subnav_header");

			var index = $("#show_store_nav .header_subnav .subnav_li").index($(this));
			$("#show_store_nav .header_subclass .header_subclass_nav").hide();

			var width = $("#show_store_nav .header_subnav_border").width();
			var x = $(this).position().left;
			var sub_class = $("#show_store_nav .header_subclass .header_subclass_nav").eq(index);
			if (x <= (width / 2)) {
				sub_class.css("left", 0);
			}
			else {
				sub_class.css("left", Math.floor(width - sub_class.width()));
			}
			sub_class.show();
		});
		
		$("#show_store_nav .header_subclass .header_subclass_nav").hover(function () {
		},
		function () {
			$(this).hide();
			$("#show_store_nav .header_subnav .subnav_li").removeClass("subnav_header");
		});

		$("#show_store_nav .header_subnav_border").hover(function () {
		},
		function () {
			$("#show_store_nav .header_subclass .header_subclass_nav").hide();
			$("#show_store_nav .header_subnav .subnav_li").removeClass("subnav_header");
		});
	}
	
	//浮动导航
	$(".nav_style1_main_content_border").empty();
	$("#store_float_nav_static_tmpl").tmpl(store_nav).appendTo(".nav_style1_main_content_border");
	
	// 全部商品分类
	// 光标设置
	var min_height = 60;
	var header_short_nav = $("#store_header").find(".header_sort");
	header_short_nav.find(".nav_style1_content").css("cursor", "default");

	// 鼠标滑过展开下级处理
	header_short_nav.find(".nav_style1_content_li").hover(function () {
		header_short_nav.find(".nav_style1_content_li").removeClass("nav_style1_content_hover");
		$(this).addClass("nav_style1_content_hover");

		var index = header_short_nav.find(".nav_style1_content_li").index(this);

		header_short_nav.find(".nav_style1_sub").hide();
		header_short_nav.find(".nav_style1_sub").eq(index).show();
		if (header_short_nav.find(".nav_style1_sub").eq(index).height() < min_height) {
			header_short_nav.find(".nav_style1_sub").eq(index).height(min_height);
		}

		var h_pos = $(this).offset().top - header_short_nav.find(".nav_style1_main_content").offset().top;
		h_pos += $(this).height() + 10;
		// 位置偏移处理
		if (header_short_nav.find(".nav_style1_sub").eq(index).height() < h_pos) {
			header_short_nav.find(".nav_style1_sub").eq(index).css("top", Math.round(h_pos - header_short_nav.find(".nav_style1_sub").eq(index).height()) + "px");
		}
	},
	function () {
	});

	header_short_nav.find(".nav_style1_main_content").hover(function () {
	},
	function () {
		header_short_nav.find(".nav_style1_content_li").removeClass("nav_style1_content_hover");
		header_short_nav.find(".nav_style1_sub").hide();
	});

	// 显示隐藏全体商品分类
	header_short_nav.hover(function () {
		$(this).find(".Nav_TitleStyleBox").show();
	},
	function () {
		$(this).find(".Nav_TitleStyleBox").hide();
	});
}