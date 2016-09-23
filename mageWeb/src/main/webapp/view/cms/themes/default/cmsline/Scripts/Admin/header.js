// ////////////////////////////////////////////////////////////////////////////////////////////////
// / HISTORY = iKuCloud,header,01-00-00,2013/09/01,Create : IKU0100000
// / HISTORY = iKuCloud,header,01-00-00,2013/10/16,jiaqing : IKU0100025
// ////////////////////////////////////////////////////////////////////////////////////////////////

// 商店信息类定义
var Store = function(store_tmpl_data, device, save_level, on_show) {
	this.on_show = on_show;
	this.store_template = store_tmpl_data;
	this.device = device;
	this.save_level = save_level;
	this.help_editor = null;
}

Store.prototype = {
	// 取得该商店的信息
	show_store_info : function() {
		// 调用模板函数 显示商店信息
		this.on_show(this);
	},
	get_store_info : function() {
		var This = this;
		$.ajax({
					url : getActionPath("GetStoreTemplate", "Store"),
					dataType : "json",
					contentType : 'application/json',
					type : "POST",
					data : stringify({
								device : This.device,
								save_level : This.save_level,
								page_id : CurrentIndexId
							}),
					success : function(data) {
						if (data.success == true) {
							This.store_template = data.store_template;
							This.show_store_info();
							if (store_menu != null) {
								store_menu.show_data();
							}
						} else {
							ShowActionError(data.error, "admin");
						}
					},
					error : function(request, status, errorThrown) {
						ShowAjaxError(request, status, errorThrown);
					}
				});
	},

	edit_store_info : function() {
		var This = this;

		var dialog = $('#store_info_edit_dialog');
		dialog.dialog({
					autoOpen : false,
					resizable : false,
					modal : true,
					width : 500,
					// height: 510,
					title : "商店基本信息编辑"
				});

		dialog.empty();

		$("#store_info_edit_dialog_tmpl").tmpl(this.store_template)
				.appendTo(dialog);
		// 设置logo宽高
		switch (this.store_template.HeaderStyle) {
			case 0 :
				dialog.find("#logo").width(286);
				dialog.find("#logo").height(61);
				break;
			case 1 :
				dialog.find("#logo").width(286);
				dialog.find("#logo").height(61);
				break;
			case 2 :
				dialog.find("#logo").width(300);
				dialog.find("#logo").height(80);
				break;
		}
		dialog.dialog('open');

		var template = $.extend({}, This.store_template);

		// 点击修改图片
		dialog.find("#logo").css("cursor", "pointer");
		dialog.find("#logo").click(function() {
			// 初始化图片上传
			var clipping = new ImageClipping(dialog.find("#logo").width(),
					dialog.find("#logo").height(), template.LogoPhotoId,
					Constant.IMAGE_QUALITY_BEST, function(photo) {
						template.LogoPhotoId = photo.Id;
						template.Logo = photo.Path;
						dialog.find("#logo").attr("src", photo.Path);
					});
			clipping.open();
		});

		// 点击确定
		dialog.find("#button_ok").click(function() {
					template.Name = dialog.find("input[name=store_name]").val();
					template.Description = dialog.find("#description").val();
					template.Title = dialog.find("#title").val();
					template.Keyword = dialog.find("#keyword").val();

					// 编辑商店模版信息
					This.edit_store_template(template, true, function() {
								dialog.dialog('close');
							}, null);
				});

		// 点击取消
		dialog.find("#button_cancel").click(function() {
					dialog.dialog("close");
				});

		// 取消焦点
		dialog.find("input[name=store_name]").blur();
	},

	edit_store_ad_info : function() {
		var This = this;

		var dialog = $('#store_ad_info_edit_dialog');
		dialog.dialog({
					autoOpen : false,
					resizable : false,
					modal : true,
					width : 500,
					title : "商店广告信息编辑"
				});

		dialog.empty();

		// 去掉链接前的http://
		if (CheckInput.IsUrl($.trim(this.store_template.AdPhotoUrl))) {
			this.store_template.AdPhotoUrl = this.store_template.AdPhotoUrl
					.substr(7);
		}
		if (CheckInput.IsUrl($.trim(this.store_template.AdUrl))) {
			this.store_template.AdUrl = this.store_template.AdUrl.substr(7);
		}

		$("#store_ad_info_edit_dialog_tmpl").tmpl(this.store_template)
				.appendTo(dialog);
		// 设置广告图片宽高
		switch (this.store_template.HeaderStyle) {
			case 0 :
				dialog.find("#ad_photo").width(305);
				dialog.find("#ad_photo").height(50);
				break;
			case 1 :
				dialog.find("#ad_photo").width(305);
				dialog.find("#ad_photo").height(50);
				break;
			case 2 :
				dialog.find("#ad_photo").width(305);
				dialog.find("#ad_photo").height(50);
				break;
		}

		dialog.dialog('open');

		var template = $.extend({}, This.store_template);

		// 点击修改图片
		dialog.find("#ad_photo").css("cursor", "pointer");
		dialog.find("#ad_photo").click(function() {
			var ad_type = dialog.find("input[name=ad_type]:checked").val();
			// if (ad_type == Constant.STORE_AD_TYPE_PHOTO) {
			// 初始化图片上传
			var clipping = new ImageClipping(dialog.find("#ad_photo").width(),
					dialog.find("#ad_photo").height(), template.AdPhotoId,
					Constant.IMAGE_QUALITY_BEST, function(photo) {
						template.AdPhotoId = photo.Id;
						template.AdPhoto = photo.Path;
						dialog.find("#ad_photo").attr("src", photo.Path);
					});
			clipping.open();
				// }
		});

		// 点击确定
		dialog.find("#button_ok").click(function() {
			template.AdPhotoUrl = dialog.find("input[name=ad_photo_url]").val();
			template.AdType = dialog.find("input[name=ad_type]:checked").val();
			template.AdPhone = dialog.find("input[name=ad_phone]").val();
			template.AdPhotoTitle = dialog.find("input[name=ad_photo_title]")
					.val();
			template.AdText = dialog.find("input[name=ad_text]").val();
			template.AdUrl = dialog.find("input[name=ad_url]").val();

			// 编辑商店模版信息
			This.edit_store_template(template, true, function() {
						dialog.dialog('close');
					}, null);
		});

		// 点击取消
		dialog.find("#button_cancel").click(function() {
					dialog.dialog("close");
				});
	},
	// 编辑商店模版信息
	edit_store_template : function(template, is_loading, call_success,
			call_error) {
		var This = this;
		// 输入检查
		var ret = this.check_input(template);
		if (ret.result) {

			// 添加http://
			if (!CheckInput.IsUrl($.trim(template.AdPhotoUrl))) {
				template.AdPhotoUrl = "http://" + template.AdPhotoUrl;
			}

			if (!CheckInput.IsUrl($.trim(template.AdUrl))) {
				template.AdUrl = "http://" + template.AdUrl;
			}

			if (is_loading) {
				OpenLoadingDialog();
			}
			$.ajax({
						url : getActionPath("EditStoreTemplate", "Store"),
						dataType : "json",
						contentType : 'application/json',
						type : "POST",
						data : stringify({
									template : template,
									page_id : CurrentIndexId
								}),
						success : function(data) {
							if (is_loading) {
								CloseLoadingDialog();
							}

							if (data.success == true) {
								if (call_success != null) {
									call_success(data);
								}
								This.get_store_info();
							} else {
								if (call_error != null) {
									call_error(data);
								}
								ShowActionError(data.error, "admin");
							}
						},
						error : function(request, status, errorThrown) {
							if (call_success != null) {
								call_error(null);
							}
							ShowAjaxError(request, status, errorThrown);
						}
					});
		} else {
			alert(ret.error);
		}
	},

	// 添加搜索关键字
	add_search_key : function(key) {
		var This = this;

		if ((key != null) && ($.trim(key) != "")) {
			var template = $.extend({}, This.store_template, true);

			key = $.trim(key);

			if (key.length > Constant.INPUT_STORE_SEARCH_KEY_MAX) {
				alert("搜索关键字最多不能超过" + Constant.INPUT_STORE_SEARCH_KEY_MAX + "字");
				return;
			}

			template.SearchKey = $.trim(template.SearchKey); // IKU0100025
			if (template.SearchKey == null) { // IKU0100025
				template.SearchKey = ""; // IKU0100025
			} // IKU0100025
			var old_key = template.SearchKey.split("\t"); // IKU0100025
			for (var i = 0; i < old_key.length; i++) {
				old_key[i] = $.trim(old_key[i]);
				if (old_key[i] == key) {
					alert("搜索关键字重复,请更换为其他关键字");
					return;
				}
			}
			old_key.push(key);
			template.SearchKey = old_key.join('\t');

			This.edit_store_template(template, true, null, null);
		}
	},
	// 删除搜索关键字
	del_search_key : function(index) {
		var This = this;

		var template = $.extend({}, This.store_template, true);
		template.SearchKey = $.trim(template.SearchKey); // IKU0100025
		if (template.SearchKey == null) { // IKU0100025
			template.SearchKey = ""; // IKU0100025
		} // IKU0100025
		var old_key = template.SearchKey.split("\t"); // IKU0100025

		if ((index >= 0) && (index < old_key.length)) {
			for (var i = 0; i < old_key.length; i++) {
				old_key[i] = $.trim(old_key[i]);
			}
			old_key.splice(index, 1);
			template.SearchKey = old_key.join('\t');

			This.edit_store_template(template, true, null, null);
		}
	},

	// 编辑搜索关键字
	edit_search_key : function(index, key) {
		var This = this;
		var template = $.extend({}, This.store_template, true);
		template.SearchKey = $.trim(template.SearchKey); // IKU0100025
		if (template.SearchKey == null) { // IKU0100025
			template.SearchKey = ""; // IKU0100025
		} // IKU0100025
		var old_key = template.SearchKey.split("\t"); // IKU0100025

		if ((key != null) && ($.trim(key) != "")) {

			key = $.trim(key);

			if (key.length > Constant.INPUT_STORE_SEARCH_KEY_MAX) {
				alert("搜索关键字最多不能超过" + Constant.INPUT_STORE_SEARCH_KEY_MAX + "字");
				return;
			}

			template.SearchKey = $.trim(template.SearchKey); // IKU0100025
			if (template.SearchKey == null) { // IKU0100025
				template.SearchKey = ""; // IKU0100025
			} // IKU0100025
			var old_key = template.SearchKey.split("\t"); // IKU0100025
			for (var i = 0; i < old_key.length; i++) {
				old_key[i] = $.trim(old_key[i]);
				if ((old_key[i] == key) && (i != index)) {
					alert("搜索关键字重复,请更换为其他关键字");
					return;
				}
			}
			old_key[index] = key;
			template.SearchKey = old_key.join('\t');

			This.edit_store_template(template, true, null, null);
		}
	},
	// 排序搜索关键字
	order_search_key : function(old_order, new_order) {
		var This = this;
		var template = $.extend({}, This.store_template, true);
		template.SearchKey = $.trim(template.SearchKey); // IKU0100025
		if (template.SearchKey == null) { // IKU0100025
			template.SearchKey = ""; // IKU0100025
		} // IKU0100025
		var old_key = template.SearchKey.split("\t"); // IKU0100025

		if ((old_order < old_key.length) && (new_order < old_key.length)) {
			var tmp = old_key[old_order];

			old_key.splice(old_order, 1);
			old_key.splice(new_order, 0, tmp);
			template.SearchKey = old_key.join('\t');

			This.edit_store_template(template, true, null, null);
		}
	},

	check_input : function(template) {
		var This = this;

		var error_str = null;

		while (1) {
			template.Logo = $.trim(template.Logo);
			if (template.Logo.length > Constant.INPUT_URL_MAX) {
				error_str = "Logo图片地址长度不能超过" + Constant.INPUT_URL_MAX + "字";
				break;
			} else if (template.Logo.length <= 0) {
				error_str = "请设置Logo图片";
				break;
			}

			template.Name = $.trim(template.Name);
			if (template.Name == "") {
				error_str = "商店名不能为空";
			} else if (template.Name.length > Constant.INPUT_STORE_NAME_MAX) {
				error_str = "商店名长度不能超过" + Constant.INPUT_STORE_NAME_MAX + "字";
				break;
			}

			template.SearchKey = $.trim(template.SearchKey);
			if (template.SearchKey.length > Constant.INPUT_STORE_SEARCH_KEY_TOTAL_MAX) {
				error_str = "搜索关键字总长度不能超过"
						+ Constant.INPUT_STORE_SEARCH_KEY_TOTAL_MAX + "字";
				break;
			}

			template.Footer = $.trim(template.Footer);
			if (template.Footer.length > Constant.INPUT_STORE_FOOTER_MAX) {
				error_str = "页脚总长度不能超过" + Constant.INPUT_STORE_FOOTER_MAX + "字";
				break;
			}

			template.AdPhoto = $.trim(template.AdPhoto);
			if (template.AdPhoto.length > Constant.INPUT_URL_MAX) {
				error_str = "广告图片地址长度不能超过" + Constant.INPUT_URL_MAX + "字";
				break;
			}

			template.AdPhotoUrl = $.trim(template.AdPhotoUrl);
			if (template.AdPhotoUrl.length > Constant.INPUT_URL_MAX) {
				error_str = "广告图片链接地址长度不能超过" + Constant.INPUT_URL_MAX + "字";
				break;
			}

			template.AdPhone = $.trim(template.AdPhone);
			if (template.AdPhone.length > Constant.INPUT_PHONE_MAX) {
				error_str = "电话号码长度不能超过" + Constant.INPUT_PHONE_MAX + "字";
				break;
			}

			template.AdPhotoTitle = $.trim(template.AdPhotoTitle);
			if (template.AdPhotoTitle.length > Constant.INPUT_STORE_PHOTO_TITLE_MAX) {
				error_str = "图片标题不能超过" + Constant.INPUT_STORE_PHOTO_TITLE_MAX
						+ "字";
				break;
			}

			template.AdText = $.trim(template.AdText);
			if (template.AdText.length > Constant.INPUT_STORE_AD_TEXT_MAX) {
				error_str = "文字内容不能超过" + Constant.INPUT_STORE_AD_TEXT_MAX + "字";
				break;
			}

			template.AdUrl = $.trim(template.AdUrl);
			if (template.AdUrl.length > Constant.INPUT_URL_MAX) {
				error_str = "链接长度不能超过" + Constant.INPUT_URL_MAX + "字";
				break;
			}

			template.Description = $.trim(template.Description);
			if (template.Description.length > Constant.INPUT_STORE_DESCRIPTION_MAX) {
				error_str = "商店描述不能超过" + Constant.INPUT_STORE_DESCRIPTION_MAX
						+ "字";
				break;
			}

			template.Title = $.trim(template.Title);
			if (template.Title.length > Constant.INPUT_STORE_TITLE_MAX) {
				error_str = "商店标题不能超过" + Constant.INPUT_STORE_TITLE_MAX + "字";
				break;
			}

			template.Keyword = $.trim(template.Keyword);
			if (template.Keyword.length > Constant.INPUT_STORE_KEYWORD_MAX) {
				error_str = "商店关键字不能超过" + Constant.INPUT_STORE_KEYWORD_MAX
						+ "字";
				break;
			}

			break;
		}

		ret = {};
		if (error_str != null) {
			ret.error = error_str;
			ret.result = false;
		} else {
			ret.error = null;
			ret.result = true;
		}
		return ret;
	},

	edit_store_help_info : function(help_id) {
		var This = this;
		// 取得help

		OpenLoadingDialog();
		$.ajax({
			url : getActionPath("GetStoreTemplateHelp", "Store"),
			dataType : "json",
			contentType : 'application/json',
			type : "POST",
			data : stringify({
						save_level : Constant.PAGE_SAVE_LEVEL_EDIT,
						help_id : help_id
					}),
			success : function(data) {
				CloseLoadingDialog();
				if (data.success == true) {
					var dialog = $('#store_help_edit_dialog');
					dialog.dialog({
								autoOpen : false,
								resizable : false,
								modal : true,
								width : 820,
								title : "商店帮助信息编辑"
							});

					dialog.empty();
					$("#store_help_edit_dialog_tmpl").tmpl(data)
							.appendTo(dialog);

					var dialog_id = dialog.attr("id");
					dialog.find("#editor").attr("id", dialog_id + "_editor");

					KindEditor.remove("#" + dialog_id + "_editor");
					This.help_editor = null;

					This.help_editor = KindEditor.create("#" + dialog_id
									+ "_editor", {
								filterMode : false,
								wellFormatMode : false,
								allowFileManager : true,
								fileManagerJson : getActionPath(
										"KindFileManager", "AdminOperation"),
								minWidth : 787,
								width : 787,
								height : 390,
								resizeType : 0,
								items : ['source', '|', 'fullscreen', '|',
										'undo', 'redo', '|', 'cut', 'copy',
										'paste', 'plainpaste', 'wordpaste',
										'|', 'image', 'multiimage', 'flash',
										'media', 'insertfile', 'table', 'hr',
										'anchor', 'link', 'unlink', '|',
										'justifyleft', 'justifycenter',
										'justifyright', 'justifyfull',
										'selectall', '|', 'formatblock',
										'fontname', 'fontsize', '|',
										'forecolor', 'hilitecolor', 'bold',
										'italic', 'underline', 'strikethrough',
										'lineheight', 'removeformat'],
								afterBlur : function() {
								}
							});
					if (data.content) {
						This.help_editor.html(data.content);
					}

					dialog.find("input[name=store_help_sel]").click(function() {
								var val = $(this).val();
								if (val == 0) {
									// 系统默认帮助
									dialog.find("#store_help_content").hide();
								} else {
									// 自定义帮助
									dialog.find("#store_help_content").show();
								}
							});

					// 点击取消按钮
					dialog.find("#button_cancel").click(function() {
								dialog.dialog('close');
							});

					// 点击保存按钮
					dialog.find("#button_ok").click(function() {
						var error_str = null;
						var help_title = {};
						var help_content = null;
						// 输入检查
						while (1) {
							help_title.Name = $
									.trim(dialog
											.find("input[name=store_help_title]")
											.val());
							if (help_title.Name == "") {
								error_str = "必须输入标题";
							} else if (help_title.Name.length > Constant.INPUT_STORE_TEMPLATE_HELP_TITLE_MAX) {
								error_str = "标题长度不能超过"
										+ Constant.INPUT_STORE_TEMPLATE_HELP_TITLE_MAX
										+ "字";
								break;
							}

							if (dialog
									.find("input[name=store_help_sel]:checked")
									.val() == 0) {
								// 系统默认帮助
								help_content = null;
							} else {
								// 自定义帮助
								help_content = $.trim(This.help_editor.html());
								if (help_content == "") {
									error_str = "必须输入帮助内容";
								} else if (help_content.length > Constant.INPUT_STORE_TEMPLATE_HELP_MAX) {
									error_str = "帮助内容长度不能超过"
											+ Constant.INPUT_STORE_TEMPLATE_HELP_MAX
											+ "字";
									break;
								}
							}
							break;
						}

						// 保存帮助信息
						if (error_str == null) {
							// 未出错
							OpenLoadingDialog();
							$.ajax({
								url : getActionPath("EditStoreTemplateHelp",
										"Store"),
								dataType : "json",
								contentType : 'application/json',
								type : "POST",
								data : stringify({
											save_level : Constant.PAGE_SAVE_LEVEL_EDIT,
											help_id : help_id,
											help_title : help_title,
											content : help_content
										}),
								success : function(data) {
									CloseLoadingDialog();
									if (data.success == true) {
										dialog.dialog('close');
										This.get_store_info();
									} else {
										ShowActionError(data.error, "admin");
									}
								},
								error : function(request, status, errorThrown) {
									ShowAjaxError(request, status, errorThrown);
								}
							});

						} else {
							alert(error_str);
						}
					});

					dialog.dialog('open');
				} else {
					ShowActionError(data.error, "admin");
				}
			},
			error : function(request, status, errorThrown) {
				ShowAjaxError(request, status, errorThrown);
			}
		});
	}
}
// 商店页面定义
var StorePage = function(data, device, save_level, page_type, on_show, async) {
	this.on_show = on_show;
	this.data = data;
	this.device = device;
	this.save_level = save_level;
	this.page_type = page_type;
	if (async == null) {
		this.async = true;
	} else {
		this.async = async;
	}
}

StorePage.prototype = {
	show_data : function() {
		this.on_show(this);
	},
	// 取得该商店的新闻信息
	get_data : function() {
		var This = this;
		$.ajax({
					url : getActionPath("GetStorePage", "Store"),
					dataType : "json",
					contentType : 'application/json',
					type : "POST",
					async : This.async,
					data : stringify({
								save_level : This.save_level,
								device : This.device,
								type : This.page_type
							}),
					success : function(data) {
						if (data.success == true) {
							This.data = data.store_page;
							store_menu_page = data.store_page;
							This.show_data();
						} else {
							ShowActionError(data.error, "admin");
						}
					},
					error : function(request, status, errorThrown) {
						ShowAjaxError(request, status, errorThrown);
					}
				});
	},
	get_page : function(id) {
		for (var i = 0; i < this.data.length; i++) {
			if (this.data[i].PageId == id) {
				return this.data[i];
			}
		}
		return null;
	},

	edit_page : function(id, data, callback_ok, callback_ng) {
		var page = this.get_page(id);
		if (page != null) {
			var This = this;

			var ret = this.check_input(data);
			if (ret.result) {
				var param = page;
				param.Name = data.Name;
				param.Title = data.Title;
				param.Keyword = data.Keyword;
				param.Description = data.Description;
				param.IsShow = data.IsShow;
				param.Device = data.Device;
				param.Page_url = data.Page_url;

				OpenLoadingDialog();
				$.ajax({
							url : getActionPath("UpdateStorePage", "Store"),
							dataType : "json",
							contentType : 'application/json',
							type : "POST",
							data : stringify({
										store_page : param
									}),
							success : function(data) {
								CloseLoadingDialog();

								if (data.success == true) {
									This.get_data();

									if (callback_ok != null) {
										callback_ok();
									}
								} else {
									ShowActionError(data.error, "admin");
									This.get_data();
									if (callback_ng != null) {
										callback_ng();
									}
								}
							},
							error : function(request, status, errorThrown) {
								ShowAjaxError(request, status, errorThrown);
								This.get_data();
							}
						});
			} else {
				alert(ret.error);
			}
		}
	},
	add_page : function(data, callback_ok, callback_ng) {
		var This = this;

		if (toolbar != null) {
			toolbar.get_store_status(false, function(store_status) {
				if (store_status.PageCount_Used > store_status.PageCount_Max) {
					alert("您的商城最大支持 " + store_status.PageCount_Max
							+ " 个自定义页面，当前数量为 " + store_status.PageCount_Used
							+ " 个，如需要增加自定义页面的最大数量，请您与客服联系，谢谢。 ");
				} else {
					var ret = This.check_input(data);
					if (ret.result) {
						var param = {};
//						param.Device = This.device;
						param.Name = $.trim(data.Name);
						param.Title = $.trim(data.Title);
						param.Keyword = $.trim(data.Keyword);
						param.Description = $.trim(data.Description);
						param.Type = This.page_type;
						param.IsShow = data.IsShow;
						param.Order = 0;
						param.Device = data.Device;
						param.Page_url = data.Page_url;

						OpenLoadingDialog();
						$.ajax({
									url : getActionPath("AddStorePage", "Store"),
									dataType : "json",
									contentType : 'application/json',
									type : "POST",
									data : stringify({
												store_page : param
											}),
									success : function(data) {
										CloseLoadingDialog();

										if (data.success == true) {
											This.get_data();

											if (callback_ok != null) {
												callback_ok(data);
											}
										} else {
											ShowActionError(data.error, "admin");
											if (callback_ng != null) {
												callback_ng(data);
											}
										}
									},
									error : function(request, status,
											errorThrown) {
										ShowAjaxError(request, status,
												errorThrown);
									}
								});
					} else {
						alert(ret.error);
					}
				}
			});
		}
	},

	delete_page : function(id) {
		var This = this;
		// 删除确认对话框
		OpenLoadingDialog();
		$.ajax({
					url : getActionPath("DeleteStorePage", "Store"),
					dataType : "json",
					contentType : 'application/json',
					async : false,
					type : "POST",
					data : stringify({
								store_page_id : id
							}),
					success : function(data) {
						CloseLoadingDialog();

						if (data.success == true) {
							This.get_data();
						} else {
							ShowActionError(data.error, "admin");
							This.get_data();
						}
					},
					error : function(request, status, errorThrown) {
						ShowAjaxError(request, status, errorThrown);
						This.get_data();
					}
				});
	},
	move_page : function(id, order) {
		var This = this;
		var page = this.get_page(id);

		var param = page;
		param.Order = order;

		$.ajax({
					url : getActionPath("UpdateStorePage", "Store"),
					dataType : "json",
					contentType : 'application/json',
					async : false,
					type : "POST",
					data : stringify({
								store_page : param
							}),
					success : function(data) {
						if (data.success == true) {
							This.get_data();
						} else {
							ShowActionError(data.error, "admin");
							This.get_data();

						}
					},
					error : function(request, status, errorThrown) {
						ShowAjaxError(request, status, errorThrown);
						// alert("发生错误,无法移动标题信息,请稍候重试");
						This.get_data();
					}
				});
	},

	check_input : function(page) {
		var This = this;

		var error_str = null;

		while (1) {
			page.Name = $.trim(page.Name);
			if (page.Name == "") {
				error_str = "必须输入页面名称";
			} else if (page.Name.length > Constant.INPUT_STORE_PAGE_NAME_MAX) {
				error_str = "页面名称长度不能超过" + Constant.INPUT_STORE_PAGE_NAME_MAX
						+ "字";
				break;
			}

			page.Description = $.trim(page.Description);
			if (page.Description.length > Constant.INPUT_STORE_PAGE_DESCRIPTION_MAX) {
				error_str = "页面描述(SEO)不能超过"
						+ Constant.INPUT_STORE_PAGE_DESCRIPTION_MAX + "字";
				break;
			}

			page.Title = $.trim(page.Title);
			if (page.Title.length > Constant.INPUT_STORE_PAGE_TITLE_MAX) {
				error_str = "页面标题(SEO)不能超过"
						+ Constant.INPUT_STORE_PAGE_TITLE_MAX + "字";
				break;
			}

			page.Keyword = $.trim(page.Keyword);
			if (page.Keyword.length > Constant.INPUT_STORE_PAGE_KEYWORD_MAX) {
				error_str = "页面关键字(SEO)不能超过"
						+ Constant.INPUT_STORE_PAGE_KEYWORD_MAX + "字";
				break;
			}

			break;
		}

		ret = {};
		if (error_str != null) {
			ret.error = error_str;
			ret.result = false;
		} else {
			ret.error = null;
			ret.result = true;
		}
		return ret;
	}

}

// 导航类定义
var StoreNav = function(store_nav_data, on_show) {
	this.on_show = on_show;
	this.EditClassID = 0;
	this.EditClassLever = 0;
	this.EditClass1id = 0;
	this.EditClass2id = 0;
	this.store_nav = store_nav_data.store_nav;
}

StoreNav.prototype = {
	// 取得该商店的导航栏信息
	show_store_nav : function() {
		this.on_show(this);
	},
	get_store_navinfo : function() {
		var This = this;
		$.ajax({
					url : getActionPath("GetStoreNav", "Store"),
					dataType : "json",
					contentType : 'application/json',
					type : "POST",
					success : function(data) {
						if (data.success == true) {
							This.store_nav = data.store_nav;
							This.show_store_nav();
						} else {
							ShowActionError(data.error, "admin");
						}
					},
					error : function(request, status, errorThrown) {
						ShowAjaxError(request, status, errorThrown);
					}
				});
	}
}

var StoreCustomService = function(store_service, on_show) {
	this.data = null;
	this.set_service = store_service.store_set_service;
	this.services = store_service.store_services
	this.on_show = on_show;
}

StoreCustomService.prototype = {
	// 显示客服工具
	show_service : function() {
		this.on_show(this);
	}
}
