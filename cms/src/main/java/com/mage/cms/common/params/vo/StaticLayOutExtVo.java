package com.mage.cms.common.params.vo;

import java.util.ArrayList;

import com.mage.cms.common.params.window.vo.Label;


/**
 * 
 * @author wu.i
 * 
 * 静态模式访问扩展参数参数构造处理
 *
 */
public class StaticLayOutExtVo {
	
	/**
	 * 
	 * add by wui TODO 此处参数变量需要继续定义处理
	 * g.ShowTitle = Number($(this).attr("plugin_data_show_title"));
			g.Style = Number($(this).attr("plugin_data_style"));
			g.Type = Number($(this).attr("plugin_data_type"));
			switch (b) {
				case Constant.PLUGIN_TYPE_ADVERTISING :
					var k = $(this).find(".plugin_advertising_content");
					g.Interval = k.attr("Interval");
					g.Direction = k.attr("Direction");
					break;
				case Constant.PLUGIN_TYPE_CAROUSEL :
					k = $(this).find(".plugin_carousel_content");
					g.Setting = {};
					g.Setting.ShowNumber = k.attr("ShowNumber");
					g.Setting.Interval = k.attr("Interval");
					g.Setting.Direction = k.attr("Direction");
					g.Setting.Gap = k.attr("Gap");
					g.Setting.ShowText = k.attr("ShowText");
					break;
				case Constant.PLUGIN_TYPE_NEWS_TEXT :
				case Constant.PLUGIN_TYPE_NEWS_GRAPHIC :
					k = $(this).find(".plugin_news_main_content");
					g.Roll = "True" == k.attr("Roll")
							|| "true" == k.attr("Roll");
					g.Interval = k.attr("Interval");
					g.Direction = k.attr("Direction");
					break;
				case Constant.PLUGIN_TYPE_SHOWCASE1 :
				case Constant.PLUGIN_TYPE_SHOWCASE2 :
				case Constant.PLUGIN_TYPE_SHOWCASE3 :
					k = $(this).find(".showcase_main_content"), g.GoodsStyle = k
							.attr("goods_style"), g.Roll = "True" == k
							.attr("Roll")
							|| "true" == k.attr("Roll"), g.Interval = k
							.attr("Interval"), g.Direction = k
							.attr("Direction")
			}
			
	 */
	
	//add by wui写入modual表时需要将值写入
	private Integer direction =0;//滚动方向
	private Integer interval =0;//间隔
	private Integer setting =0;//设置
	private Integer showTitle =0;//显示标题 3
	private Integer style =0;//风格,有无边框
	private String title ="";//标题
	private Integer type =0;//类型
	java.util.List<String> searchKeyDisplay = new ArrayList<String>(); //plugin_type=2需要设置此值
	java.util.List<Label> labels=new ArrayList<Label>(); //plugin_type =3需要设置此值
	private Integer goodsStyle = 0;						   //橱窗插件展示的商品类型
	
	public Integer getDirection() {
		return direction;
	}
	public void setDirection(Integer direction) {
		this.direction = direction;
	}
	public Integer getInterval() {
		return interval;
	}
	public void setInterval(Integer interval) {
		this.interval = interval;
	}
	public Integer getSetting() {
		return setting;
	}
	public void setSetting(Integer setting) {
		this.setting = setting;
	}
	public Integer getShowTitle() {
		return showTitle;
	}
	public void setShowTitle(Integer showTitle) {
		this.showTitle = showTitle;
	}
	public Integer getStyle() {
		return style;
	}
	public void setStyle(Integer style) {
		this.style = style;
	}
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public java.util.List<String> getSearchKeyDisplay() {
		return searchKeyDisplay;
	}
	public void setSearchKeyDisplay(java.util.List<String> searchKeyDisplay) {
		this.searchKeyDisplay = searchKeyDisplay;
	}
	public java.util.List<Label> getLabels() {
		return labels;
	}
	public void setLabels(java.util.List<Label> labels) {
		this.labels = labels;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getGoodsStyle() {
		return goodsStyle;
	}
	public void setGoodsStyle(Integer goodsStyle) {
		this.goodsStyle = goodsStyle;
	}
	
}
