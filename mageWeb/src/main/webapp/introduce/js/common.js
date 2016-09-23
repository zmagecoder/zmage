$(document).ready(function(){
	//内页的导航滚动滚动条变化
	$(window).scroll( function(){
		var diffY=$(document).scrollTop();
		if(diffY > 100){
			$(".float_nav").fadeIn(150);
		}else
			$(".float_nav").fadeOut(150);
	});
	//当前位置
	$(".lx_wz").animate({marginTop:'0',opacity:'1'},400);
});




















