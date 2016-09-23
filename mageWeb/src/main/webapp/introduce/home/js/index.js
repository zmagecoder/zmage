$(function() {
	$.browser.msie && $.browser.version < 10 && $("body").addClass("ltie10"),
			$("#dakuang").fullpage({
				verticalCentered : !1,
				anchors : [ "page1", "page2" ],
				navigation : !0,
				navigationTooltips : [ "", "" ],
				navigationPosition : "left",
				afterLoad : function(anchorLink, index) {
					if(index > 1){
//						$("#home_nav").animate({"height":"63px","line-height":"60px"});
//						$("div.nav_img").animate({"padding-top":"0px"});
					}else{
//						$("#home_nav").animate({"height":"100px","line-height":"97px"});
//						$("div.nav_img").animate({"padding-top":"19px"});
					}
				}
			});
});