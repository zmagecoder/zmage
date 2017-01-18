(function($) {
    $.fn.gridAjaxPager = function(options) {
    	return this.each(function(){
    		bindEvent($(this));
    	});
    	
    	/**
    	 * 绑定分页事件
    	 */
    	function bindEvent(pager,grid){
    		var grid = pager.parent();
    		 pager.find("li[name='gotopage']>a").unbind(".click").bind("click",function(){
    			 var pgObj = jQuery("li[name=setpagesize_li]>input[name=pageSize]");
    			 var pageSize = pgObj.val();
    			 var reg = /\d+$/;
    			 if(!reg.test(pageSize))pageSize = 10;
				 load($(this).attr("pageno"),grid,pageSize);
			 }); 
    		 pager.find("li>input").unbind("change").bind("change",function(){
    			 var pageSize = $(this).val();
    			 var reg = /\d+$/;
    			 if(!reg.test(pageSize))pageSize = 10;
    			 load($(this).attr("pageno"),grid,pageSize);
    		 }); 
    		 pager.find("li[name=setpagesize_li]>a[name=spsize]").unbind("click").bind("click",function(){
    			 var pageSize = $(this).attr("p_size");
    			 var reg = /\d+$/;
    			 if(!reg.test(pageSize))pageSize = 10;
    			 load($(this).attr("page_no"),grid,pageSize);
    		 }); 
    		 pager.find("a.selected").unbind("click");
    	}
    	
    	/**
    	 * 点击分页的加载事件
    	 */
    	function load(pageNo,grid,pageSize){
    		var url = options;
    		url=url+"page="+pageNo;
    		if(pageSize)url += "&pageSize="+pageSize;
    		$.ajax({
    			url:url,
    			success:function(html){
    				grid.empty().append( $(html).find(".gridbody").children() );
    				bindEvent(grid.children(".page"));
    			},
    			error:function(){
    				alert("加载页面出错:(");
    			}
    		});
    	}
    	
    };
})(jQuery);



/**
 * 异步分页jquery插件 -- http POST only 
 */
(function($) {
    $.fn.gridPostPager = function(options) {
    	
    	
    	return this.each(function(){
    		bindEvent($(this));
    	});
    	
    	/**
    	 * 绑定分页事件
    	 */
    	function bindEvent(pager,grid){
    		var grid = pager.parent();
    		 pager.find("li[name='gotopage']>a").unbind(".click").bind("click",function(){
    				 loadForm($(this).attr("pageno"),grid);
			 }); 
    		 pager.find("li>input").unbind("change").bind("change",function(){
    			 var pageSize = $(this).val();
    			 var reg = /\d+$/;
    			 if(!reg.test(pageSize))pageSize = 10;
    			 $(this).val(pageSize);
				 loadForm($(this).attr("pageno"),grid);
    		 });
    		 pager.find("li[name=setpagesize_li]>a[name=spsize]").unbind("click").bind("click",function(){
    			 var pageSize = $(this).attr("p_size");
    			 var reg = /\d+$/;
    			 if(!reg.test(pageSize))pageSize = 10;
    			 pager.find("li>input").val(pageSize);
				 loadForm($(this).attr("page_no"),grid);
    		 }); 
    		 pager.find("a.selected").unbind("click");
    	}
    	
    	function loadForm(pageNo, grid) {
    		var $form;
    		if (options.formId == "") {
    			window.console && console.log("没有设置formId");
    			$form = guessForm();
    		} else {
    			$form = $("#" + options.formId);
    		}
    		if (options.ajax == "yes") {
    			lordFormByAjax($form, pageNo, grid);
    		} else {
    			simpleQueryForm($form, pageNo);
    		}
    	}
    	
    	function lordFormByAjax($form, pageNo, grid) {
    		var param = {page: pageNo};
    		var psiseObj =  $form.find("input[name='pageSize']");
    		if(!psiseObj || psiseObj.length<1){
    			var pge_apze = jQuery("li[name=setpagesize_li]>input[name=pageSize]").val();
    			var reg = /\d+$/;
    			if(!reg.test(pge_apze))pge_apze = 10;
    			param.pageSize=pge_apze;
    		}
			var s_options = {
					data : param,
	    			success:function(html){
	    				grid.empty().append( $(html).find(".gridbody").children() );
	    				bindEvent(grid.children(".page"));
	    			},
	    			error:function(a,b){
	    				alert("加载页面出错:("+b);
	    				return ;
	    			}
			};
			
			var override_url; 
			if (options.action != "") {
				override_url = options.action;
			} else {
				override_url = options.url; //根据request URL设置提交action，不使用form自己的action，因为可能用js重写了action
			}
			override_url = override_url.replace(/\?\&/, '?');
			override_url = override_url.replace(/\&\&/g, '&');
			//var reg2=new RegExp("pageSize=(\\d+)","i");
			//override_url=override_url.replace(reg2,"");
			//alert(override_url);
			window.console && console.log("=== lordFormByAjax form action is ===" + override_url);
			s_options = jQuery.extend( s_options, {url:override_url} );
			
			$form.ajaxSubmit(s_options); 
    	}
    	
    	function simpleQueryForm($form, pageNo) {
			var override_url; 
			if (options.action != "") {
				override_url = options.action;
			} else {
				override_url = options.url;  //根据request URL设置提交action，不使用form自己的action，因为可能用js重写了action
			}
			if(override_url.indexOf("?")>-1) 
				override_url +="&page=" + pageNo; //可能以?&page=1结束
			else
				override_url +="?page=" + pageNo;
			override_url = override_url.replace(/\?\&/, '?');
			window.console && console.log("=== simpleQueryForm form action ===" + override_url);
			var pageSizeObj = $($form[0]).find("input[name=pageSize]");
			var page = $(".page>ul>li>input[name=pageSize]");
			var pSize = page.val();
			var reg = /\d+$/;
			if(!reg.test(pSize))pSize = 10;
			page.val(pSize);
			if(!pageSizeObj || pageSizeObj.length==0){
				$($form[0]).append("<input type='hidden' name='pageSize' value='"+pSize+"' />");
			}else{
				pageSizeObj.val(pSize);
			}
			$form[0].setAttribute("action", override_url); // 使用DOM操作。在ie使用attr会有问题，jQuery阻止了修改属性 有时候出现。
			$form.submit();
    	}
    	
    	function guessForm() {
    		window.console && console.log("======= guess form begin =======");
    		var $forms = $("form");
    		var size = $forms.size(); 
    		window.console && console.log("======= form size =======" + size);
    		if (size == 1) {
    			return $forms;
    		} 
    		if (size == 0) {
    			alert("该页面并没有表单");
    			throw "该页面并没有表单";
    		}
    		window.console && console.log("======= more than one form in the page  =======" + size);
    		var url = options.url;
    		window.console && console.log("======= the request url =======" + url);
    		var regexp = /[a-zA-Z0-9]+[^\/]*\!{0,1}[^\/]*\.do/;
    		var guessAction = url.match(regexp);
    		if (guessAction != null) {
    			guessAction = guessAction.toString();
    		};
    		window.console && console.log("======= guessAction =======" + guessAction);
    		var match_pos = -1;
    		for (var i=0; i<size; i++) {
    			var f = $forms.get(i); 
    			var action = f.getAttribute("action");
    			if (action && action != "") {
    				var f_action = action.match(regexp);
    				if (f_action != null && f_action.toString() == guessAction) {
    					match_pos = i;
    					window.console && console.log("======= one form match the guessAction, the pos is =======" + match_pos);
    					break;
    				}
    			}
    		}
    		window.console && console.log("======= guess form end =======");
    		if (match_pos == -1) {
    			alert("该页面有多个表单，请在grid:grid标签指明formId参数");
    			throw "该页面有多个表单，请在grid:grid标签指明formId参数";
    		}
    		return $($forms.get(match_pos));
    	}
    	
    };
})(jQuery);