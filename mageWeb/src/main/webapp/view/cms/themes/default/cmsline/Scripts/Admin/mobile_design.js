// 滚动条设置
$(".ScrollBox").mCustomScrollbar({
	autoHideScrollbar: false,
	scrollInertia: 0,
	scrollButtons:{
		enable:true
	},
	callbacks:{
		onScroll: function(){
			$(".ShowBox_Content").scrollTop(mcs.top * -1);
		},
		whileScrolling: function(){
			$(".ShowBox_Content").scrollTop(mcs.top * -1);
		}
	},
	advanced:{
		updateOnContentResize: true
	}
});
$(".ScrollContent").height($(".ScrollBox").height());
$(".ScrollBox").mCustomScrollbar("scrollTo", 0);

var istore_layout = null;

istore_mobile_option.on_scroll = function(scroll_left, scroll_top, width, height) {
	$(".ScrollContent").width(width);

	if (height <= $(".ScrollBox").height()) {
		$(".ScrollContent").height($(".ScrollBox").height());
		$(".ScrollBox").mCustomScrollbar("scrollTo", 0);
	}
	else {
		$(".ScrollContent").height(height);
		$(".ScrollBox").mCustomScrollbar("scrollTo", scroll_top);
	}
};

$(".ShowBox_Content").mousewheel(function (event, delta) {
	var pos = $(".ScrollBox").find(".mCSB_container").position();
	$(".ScrollBox").mCustomScrollbar("scrollTo", (pos.top + delta * 100) * -1);
});



//$(document).ready(function () {
// Page的ID设置
istore_mobile_option.page_id = mobile_page_id;
// 启动页面布局
toolbar.get_store_status(false, function () {
	istore_layout = new iStoreLayout(istore_mobile_option, mobile_plugin_option, permissions[permisson_map["PageDesign"]], permissions[permisson_map["PageContent"]]);
	istore_layout.get_store_layout();
});

var mobile_pages = [];
// 页面编辑处理
var pages_edit = new StorePage(null, Constant.STORE_DEVICE_MOBILE, Constant.PAGE_SAVE_LEVEL_EDIT, Constant.STORE_PAGE_TYPE_MAIN, function (store_menu) {
	var data = {};
	if (store_menu.data != null) {
		data.Pages = store_menu.data;
	}
	else {
		data.Pages = [];
	}
	mobile_pages = mobile_pages.concat(data.Pages)
}, false);
pages_edit.get_data();

// 页面编辑处理
var custom_pages_edit = new StorePage(null, Constant.STORE_DEVICE_MOBILE, Constant.PAGE_SAVE_LEVEL_EDIT, Constant.STORE_PAGE_TYPE_CUSTOM, function (store_menu) {
	var data = {};
	if (store_menu.data != null) {
		data.Pages = store_menu.data;
	}
	else {
		data.Pages = [];
	}
	mobile_pages = mobile_pages.concat(data.Pages)
}, false);
custom_pages_edit.get_data();

$("#mobile_pages_select_tmpl").tmpl({ Pages: mobile_pages, CurrPageId: mobile_page_id }).appendTo("#mobile_page_select");
// select插件风格设置
$('#mobile_page_select').selectBoxIt({ autoWidth: false});

$('#mobile_page_select').change(function () {
	OpenLoadingDialog();
	var open_url = $(this).find("option:selected").attr("open_url");
	goto_mobile_design_page(open_url);
});

// select插件风格设置
$('#mobile_plugin_select').selectBoxIt({ autoWidth: false });
$('#mobile_plugin_select').change(function () {
	var plugin_class = $(this).find("option:selected").val();
	if (plugin_class > 0) {
		$('.Tools_FunctionIcon .FunctionIcon_01').hide();
		$('.Tools_FunctionIcon .plugin_class_' + plugin_class).show();
	}
	else {
		$('.Tools_FunctionIcon .FunctionIcon_01').show();
	}
});

// 点击添加页面
$("#add_mobile_page").click(function () {
	edit_store_page(-1);
});

// 点击编辑页面
$("#edit_mobile_page").click(function () {
	var type = $('#mobile_page_select').find("option:selected").attr("type");
	var page_id = $('#mobile_page_select').find("option:selected").attr("page_id");

	if (type == Constant.STORE_PAGE_TYPE_MAIN) {
		// 商城信息编辑
		store_info.edit_store_info();
	}
	else {
		// 商城页面编辑
		edit_store_page(page_id);
	}
});

// 编辑商店页面
function edit_store_page (page_id) {
	var This = this;
	var type = Constant.STORE_PAGE_TYPE_CUSTOM;
	var store_page = custom_pages_edit;

	var page = null;
	if (page_id > 0) {
		page = store_page.get_page(page_id);

		if (page == null) {
			alert("操作发生错误,请稍候重试");
			return;
		}
	}
	else {
		page = {
			Name: "",
			Title: "",
			Keyword: "",
			Description: "",
			IsShow: true,
			Device:"1",
			Page_url:""
		};
	}

	var dialog = $("#store_page_edit_dialog");
	dialog.empty();
	page.store_page_type = type;
	$("#store_page_edit_dialog_tmpl").tmpl(page).appendTo(dialog);
	dialog.dialog({
		autoOpen: false,
		resizable: false,
		modal: true,
		width: 500,
		//height: 430,
		title: "商店页面信息编辑"
	});

	if (page_id <= 0) {
		// 添加页面
		dialog.find("#button_delete").hide();
	}
	else {
		if (page.IndexId == 0) {
			// 首页
			dialog.find("#button_delete").hide();
			dialog.find("input[name=page_name]").attr("disabled", "disabled");
		}
		else {
			// 首页以外
			dialog.find("#button_delete").show();
		}
	}

	dialog.dialog("open");

	// 点击取消
	dialog.find("#button_cancel").click(function () {
		dialog.dialog("close");
	});

	// 点击确定
	dialog.find("#button_ok").click(function () {
		var data = {};
		data.Name = dialog.find("#page_name").val();
		data.Title = dialog.find("#page_title").val();
		data.Keyword = dialog.find("#page_keyword").val();
		data.Description = dialog.find("#page_description").val();
		data.Type = type;
		data.IsShow = dialog.find("input:radio[name=page_is_show]:checked").val();
		if (data.IsShow == "false") {
			data.IsShow = false;
		}
		else {
			data.IsShow = true;
		}

		if (page_id > 0) {
			// 编辑
			store_page.edit_page(page_id, data, function () {
				dialog.dialog("close");
			}, null);
		}
		else {
			// 添加
			store_page.add_page(data, function (page_data) {
				dialog.dialog("close");
				goto_mobile_design_page(page_data.store_page.Page_url);
			}, null);
		}
	});

	// 点击删除
	dialog.find("#button_delete").click(function () {
		if (page != null) {
			ShowConfirmDialog("确实要删除这个页面吗?<br>击确定将删除该页面以及其中的所有插件!", function () {
				store_page.delete_page(page_id);
				dialog.dialog("close");
				//删除页面后取第一个页面展示
				var open_url = $("#mobile_page_select option").eq(0).attr("open_url");
				var pageId = $("#mobile_page_select option").eq(0).val();
				if(page_id == pageId){
					open_url = $("#mobile_page_select option").eq(1).attr("open_url");
				}
				goto_mobile_design_page(open_url);
			});
		}
	});
}
