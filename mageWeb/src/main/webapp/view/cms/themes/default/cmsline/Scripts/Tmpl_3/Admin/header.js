
// ******** 模板必须实现该函数 ******** //
// 显示商店信息,包括logo等
function show_store_info(store_info) {
	// help处理
	//页头、页尾处理开始结束  add by wui 会员中心、购物流程展示页头页尾设置,在ShopStoreTemplateWidgetStatic.html文件有有说明
	$("#store_help_box").empty();
		
	if(Number(page_device) != 2){
		$("#store_help_box_tmpl").tmpl(store_info.store_template)
			.appendTo($("#store_help_box"));
	}

	if (store_info.store_template.ShowHeader) {
		$("#store_header").show();
		$(".footer-box").show();
	} else {
		$("#store_header").hide();
		$(".footer-box").hide();
	}
	
	//页头广告处理开始====================================ShopHeaderWidget.html header_content 生产模式添加代码，需要加上if判断，生产模式才处理
	$("#header_content_box").empty();
	if (store_info.store_template.ShowHeaderAd) {
		if(Number(page_device) != 2){
			$("#header_content_box_tmpl").tmpl(store_info.store_template)
			.appendTo("#header_content_box");
		}
	}
	//页头广告处理结束=================

	// 搜索空字字符串处理，此处代码需要移到后台，对空的过滤掉 pzf TODO
	if (store_info.store_template.SearchKey != null) {
		store_info.store_template.SearchKeyDisplay = store_info.store_template.SearchKey
				.split("\t");
		// 删除空串
		for (var i = store_info.store_template.SearchKeyDisplay.length; i >= 0; i--) {
			if ((store_info.store_template.SearchKeyDisplay[i] == null)
					|| ($.trim(store_info.store_template.SearchKeyDisplay[i]) == "")) {
				store_info.store_template.SearchKeyDisplay.splice(i, 1);
			}
		}
	} else {
		store_info.store_template.SearchKeyDisplay = null;
	}
	
	// 页头处理,参考ShopStoreTemplateWidgetStatic.html代码
	$("#header_logo_box").empty();
	$("#header_logo_box_style" + store_info.store_template.HeaderStyle
			+ "_tmpl").tmpl(store_info.store_template)
			.appendTo("#header_logo_box");

	if (store_nav != null) {
		store_nav.show_store_nav();
	}

	//////add by wyu ShopStoreTemplateWidgetStatic.html 已加入代码处理判断 页尾处理
	$("#store_footer").empty();
	if(Number(page_device) != 2){
		$("#store_footer_tmpl").tmpl(store_info.store_template)
			.appendTo("#store_footer");
	}

//	if (store_info.store_template.ShowZhuyun) {
//		$(".footer-box").find(".zhuyun_support").show();
//		$(".footer-box").find(".footer").removeClass("footer_no_zhuyun");
//	} else {
//		$(".footer-box").find(".zhuyun_support").hide();
//		$(".footer-box").find(".footer").addClass("footer_no_zhuyun");
//	}
	///////////////////////////结束
	
	if (store_info.store_template.ShowHelp) {
		$(".footer-box").find(".footer").show();
	} else {
		$(".footer-box").find(".footer").hide();
	}
	
	//生产模式不需要展示
	// 如果有商店设计权限
	if (permissions[permisson_map["StoreDesign"]]
			&& (store_info.save_level == Constant.PAGE_SAVE_LEVEL_EDIT)) {
		// 帮助编辑
		$("#store_footer_box").find(".help_name").click(function() {
					var help_id = $(this).attr("index");
					store_info.edit_store_help_info(help_id);
				});

		$("#store_info_logo").css("cursor", "pointer");
		$("#store_info_logo").click(function() {
					// 商店基本信息编辑
					store_info.edit_store_info();
				});

		$("#store_info_ad").css("cursor", "pointer");
		$("#store_info_ad").click(function() {
					// 商店广告信息编辑
					store_info.edit_store_ad_info();
				});

		// 设置为可拖拽
		$("#show_store_search").find(".header_hot").sortable({
			stop : function(event, ui) {
				var order = $("#show_store_search").find(".header_hot li")
						.index(ui.item);
				var old_order = $(ui.item).attr("index");
				store_info.order_search_key(old_order, order);
			}
		});

		// 添加搜索关键字
		$("#show_store_search").find(".smallbutton").click(function() {
					var input = $("#show_store_search")
							.find(".smallbutton input");
					input.show();
					input.focus();

					input.focusout(function() {
								var new_title = $.trim($(this).val());
								if (new_title != "") {
									store_info.add_search_key(new_title);
								}
								$(this).val("");
								$(this).hide();
							});

					input.keyup(function(e) {
								// 回车键按下
								if (e.keyCode == 13) {
									input.blur();
								} else if (e.keyCode == 27) {
									// ESC键取消输入
									input.val("");
									input.blur();
								}
							});
				});

		// 点击改名
		$("#show_store_search").find(".search_key").click(function() {
			// 显示编辑框
			var li = $(this).parent("li");
			li.find("input").width($(this).width());
			li.find("input").height($(this).height());
			$(this).hide();
			li.find("input").show();
			li.find("input").focus();

			li.find("input").blur(function() {
						var old_title = $.trim(li.find(".search_key").text());
						var new_title = $.trim($(this).val());
						var index = li.attr("index");
						if (new_title == "") {
							// 删除标签
							store_info.del_search_key(index);
						} else if (old_title != new_title) {
							// 标签改名
							store_info.edit_search_key(index, new_title);
						}
						li.find(".search_key").show();
						li.find("input").hide();
						li.find("input").val(old_title);
					});

			li.find("input").keyup(function(e) {
						// 回车键按下
						if (e.keyCode == 13) {
							li.find("input").blur();
						} else if (e.keyCode == 27) {
							// ESC键取消输入
							li.find("input").val(li.find(".search_key").text());
							li.find("input").blur();
						}
					});
		});
	}

	if (store_info.store_template.ShowHeaderAd) {
		// 页眉编辑
		var width = $("#header_content_box").find("#header_content").width();
		var height = $("#header_content_box").find("#header_real_content")
				.height();
		if (height < 30) {
			// 设置最小高度
			height = 30;
			$("#header_content_box").find("#header_content").height(height);
		}

		// 设置页脚遮罩
		$("#header_content_box").find(".header_mask").width(width);
		$("#header_content_box").find(".header_mask").height(height);

		// 如果有商店设计权限
		if (permissions[permisson_map["StoreDesign"]]
				&& (store_info.save_level == Constant.PAGE_SAVE_LEVEL_EDIT)) {
			$("#header_content_box").find(".header_mask").click(function() {
				var header = $(this).parents("#header_content_box");
				header.find("#header_content").hide();
				header.find(".header_mask").hide();
				header.find("#header_edit_content").show();

				KindEditor.remove('#edit_header');
				// 新闻详细内容编辑
				var header_editor = KindEditor.create("#edit_header", {
							filterMode : false,
							wellFormatMode : false,
							allowFileManager : true,
							fileManagerJson : getActionPath("KindFileManager",
									"AdminOperation"),
							minWidth : 1258,
							width : 1258,
							height : 390,
							resizeType : 0,
							items : ['source', '|', 'fullscreen', '|', 'undo',
									'redo', '|', 'cut', 'copy', 'paste',
									'plainpaste', 'wordpaste', '|', 'image',
									'multiimage', 'flash', 'media',
									'insertfile', 'table', 'hr', 'anchor',
									'link', 'unlink', '|', 'justifyleft',
									'justifycenter', 'justifyright',
									'justifyfull', 'selectall', '|',
									'formatblock', 'fontname', 'fontsize', '|',
									'forecolor', 'hilitecolor', 'bold',
									'italic', 'underline', 'strikethrough',
									'lineheight', 'removeformat'],
							afterBlur : function() {
							}
						});

				// 滚动到页面顶部
				window.scrollTo(0, 0);

				// 点击确定按钮
				header.find("#header_edit_content #button_save").click(
						function() {
							if (header_editor != null) {
								content = header_editor.html();
							} else {
								content = "";
							}

							if (content.length > Constant.INPUT_STORE_HEADER_MAX) {
								alert("页眉内容不能超过"
										+ Constant.INPUT_STORE_HEADER_MAX + "字");
								return;
							}

							var template = $.extend({},
									store_info.store_template, true);
							template.Header = content;

							store_info.edit_store_template(template, true,
									function() {
										header.find("#header_edit_content")
												.hide();
										header.find("#header_content").show();
										header.find(".header_mask").show();
									}, null);
						});

				// 点击取消按钮
				header.find("#header_edit_content #button_cancel").click(
						function() {
							header.find("#header_edit_content").hide();
							header.find("#header_content").show();
							header.find(".header_mask").show();
						});
			});
		}
	}

	
	//
	{
		// 页脚编辑
		var width = $("#store_footer").find("#footer_content").width();
		var height = $("#store_footer").find("#footer_real_content").height();
		if (height < 50) {
			// 设置最小高度
			height = 50;
			$("#store_footer").find("#footer_content").height(height);
		}

		// 设置页脚遮罩
		$("#store_footer").find(".footer_mask").width(width);
		$("#store_footer").find(".footer_mask").height(height);

		// 如果有商店设计权限
		if (permissions[permisson_map["StoreDesign"]]
				&& (store_info.save_level == Constant.PAGE_SAVE_LEVEL_EDIT)) {
			$("#store_footer").find(".footer_mask").click(function() {
				var footer = $(this).parents("#store_footer");
				footer.find("#footer_content").hide();
				footer.find(".footer_mask").hide();
				footer.find("#footer_edit_content").show();

				KindEditor.remove('#edit_footer');
				// 新闻详细内容编辑
				var footer_editor = KindEditor.create("#edit_footer", {
							filterMode : false,
							wellFormatMode : false,
							allowFileManager : true,
							fileManagerJson : getActionPath("KindFileManager",
									"AdminOperation"),
							minWidth : 1258,
							width : 1258,
							height : 390,
							resizeType : 0,
							items : ['source', '|', 'fullscreen', '|', 'undo',
									'redo', '|', 'cut', 'copy', 'paste',
									'plainpaste', 'wordpaste', '|', 'image',
									'multiimage', 'flash', 'media',
									'insertfile', 'table', 'hr', 'anchor',
									'link', 'unlink', '|', 'justifyleft',
									'justifycenter', 'justifyright',
									'justifyfull', 'selectall', '|',
									'formatblock', 'fontname', 'fontsize', '|',
									'forecolor', 'hilitecolor', 'bold',
									'italic', 'underline', 'strikethrough',
									'lineheight', 'removeformat'],
							afterBlur : function() {
							}
						});

				// 滚动到页面底部
				window.scrollTo(0, $("body").height());

				// 点击确定按钮
				footer.find("#footer_edit_content #button_save").click(
						function() {
							if (footer_editor != null) {
								content = footer_editor.html();
							} else {
								content = "";
							}

							if (content.length > Constant.INPUT_STORE_FOOTER_MAX) {
								alert("页脚内容不能超过"
										+ Constant.INPUT_STORE_FOOTER_MAX + "字");
								return;
							}

							var template = $.extend({},
									store_info.store_template, true);
							template.Footer = content;

							store_info.edit_store_template(template, true,
									function() {
										footer.find("#footer_edit_content")
												.hide();
										footer.find("#footer_content").show();
										footer.find(".footer_mask").show();
									}, null);
						});

				// 点击取消按钮
				footer.find("#footer_edit_content #button_cancel").click(
						function() {
							footer.find("#footer_edit_content").hide();
							footer.find("#footer_content").show();
							footer.find(".footer_mask").show();
						});
			});
		}
	}

}

// ******** 模板必须实现该函数 ******** //
// 显示商店导航菜单
function show_store_menu(store_menu) {
	$("#show_store_menu").empty();

	var data = {};
	if (store_menu.data != null) {
		data.Pages = store_menu.data;
	} else {
		data.Pages = [];
	}
	$("#store_menu_style" + store_info.store_template.HeaderStyle + "_tmpl")
			.tmpl(data).appendTo($("#show_store_menu"));

	// 如果有商店设计权限
	if (permissions[permisson_map["StoreDesign"]]) {
		// 设置导航菜单可拖拽
		$("#show_store_menu").find(".menu_hot").sortable({
			stop : function(event, ui) {
				var page_id = ui.item.attr("page_id");
				var order = $("#show_store_menu").find(".page_link")
						.index(ui.item)
						+ 2;
				store_menu.move_page(page_id, order);
				toolbar.front_show_page(Constant.STORE_DEVICE_PC, true);
			}
		});
	}
}

// ******** 模板必须实现该函数 ******** //
// 显示商店分类导航
function show_store_nav(store_nav) {
	$("#show_store_nav").empty();
	if (store_info.store_template.ShowNav) {
		$("#store_head_nav_tmpl").tmpl(store_nav.store_nav)
				.appendTo("#show_store_nav");
		// 导航栏宽度计算
		var nav_width = 0;
		$("#show_store_nav").find(".subnav_li").each(function() {
					nav_width += $(this).outerWidth();
				});
		nav_width = nav_width > 1200 ? 1200 : nav_width;
		$("#show_store_nav").find(".header_subnav").width(nav_width);
		
		if(bar_type != 'admin_design'){
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
	}
	
	if(bar_type != 'admin_design'){
		//浮动导航
		$(".nav_style1_main_content_border").empty();
		$("#store_float_nav_static_tmpl").tmpl(store_nav.store_nav).appendTo(".nav_style1_main_content_border");
		
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
}
