package com.mage.cms.common.params.admin.req;


import java.util.Date;

import com.mage.cms.common.params.base.req.CmsLineOperator;
import com.mage.cms.common.params.base.resp.CmsLineResp;
/**
 * 登录鉴权处理
 */
public class StatusInfoReq extends CmsLineResp{

	private String ExtensionData;
	private String AlbumSpace_Max;
	private String AlbumSpace_Used;
	private String AllGoodsNumber;
	private String BadEvaluate;
	private String ComplainNumber;
	private String DayOrder_Max;
	private String DayOrder_Used;
	private String EndTime;
	private String FullReduceGoodsNumber;
	private String GoodsId;
	private String GoodsName;
	private Integer Goods_Max =0;
	private Integer Goods_Used =0;
	private Integer GroupBuyGoodsNumber =0;
	private Integer MessageCount_Max =0;
	private Integer MultiuserNumber_Max =0;
	private Integer PackBuyGoodsNumber =0;
	private Integer PageCount_Max =0;
	private Integer PageCount_Used =0;
	private Integer ReduceGoodsNumber =0;
	
	private Integer SeckillingGoodsNumber =0;
	private Integer ShortageGoodsNumber =0;
	private String StoreId;
	public String getExtensionData() {
		return ExtensionData;
	}
	public void setExtensionData(String extensionData) {
		ExtensionData = extensionData;
	}
	public String getAlbumSpace_Max() {
		return AlbumSpace_Max;
	}
	public void setAlbumSpace_Max(String albumSpace_Max) {
		AlbumSpace_Max = albumSpace_Max;
	}
	public String getAlbumSpace_Used() {
		return AlbumSpace_Used;
	}
	public void setAlbumSpace_Used(String albumSpace_Used) {
		AlbumSpace_Used = albumSpace_Used;
	}
	public String getAllGoodsNumber() {
		return AllGoodsNumber;
	}
	public void setAllGoodsNumber(String allGoodsNumber) {
		AllGoodsNumber = allGoodsNumber;
	}
	public String getBadEvaluate() {
		return BadEvaluate;
	}
	public void setBadEvaluate(String badEvaluate) {
		BadEvaluate = badEvaluate;
	}
	public String getComplainNumber() {
		return ComplainNumber;
	}
	public void setComplainNumber(String complainNumber) {
		ComplainNumber = complainNumber;
	}
	public String getDayOrder_Max() {
		return DayOrder_Max;
	}
	public void setDayOrder_Max(String dayOrder_Max) {
		DayOrder_Max = dayOrder_Max;
	}
	public String getDayOrder_Used() {
		return DayOrder_Used;
	}
	public void setDayOrder_Used(String dayOrder_Used) {
		DayOrder_Used = dayOrder_Used;
	}
	
	public String getFullReduceGoodsNumber() {
		return FullReduceGoodsNumber;
	}
	public void setFullReduceGoodsNumber(String fullReduceGoodsNumber) {
		FullReduceGoodsNumber = fullReduceGoodsNumber;
	}
	public String getGoodsId() {
		return GoodsId;
	}
	public void setGoodsId(String goodsId) {
		GoodsId = goodsId;
	}
	public String getGoodsName() {
		return GoodsName;
	}
	public void setGoodsName(String goodsName) {
		GoodsName = goodsName;
	}
	
	public String getStoreId() {
		return StoreId;
	}
	public void setStoreId(String storeId) {
		StoreId = storeId;
	}
	
	/**
	 * 	"\"status_info\":{\"ExtensionData\":null,\"AlbumSpace_Max\":104857600,\"AlbumSpace_Used\":6793462," +
				"\"AllGoodsNumber\":2,\"BadEvaluate\":0,\"ComplainNumber\":0,\"DayOrder_Max\":10," +
				"\"DayOrder_Used\":0,\"EndTime\":\"\\/Date(1403099787000)\\/\",\"FullReduceGoodsNumber\":0," +
				"\"GoodsId\":0,\"GoodsName\":\"\",\"Goods_Max\":30,\"Goods_Used\":2,\"GroupBuyGoodsNumber\":0," +
				"\"HavePayOrderNumber\":0,\"MessageCount_Max\":0,\"MultiuserNumber_Max\":1,\"PackBuyGoodsNumber\":0," +
				"\"PageCount_Max\":10,\"PageCount_Used\":8,\"ReduceGoodsNumber\":0,\"SeckillingGoodsNumber\":0,\"ShortageGoodsNumber\":0,\"StoreId\":15496}}";
				
	 * @return
	 */
	public void vertify(){ //设置无限期
		this.EndTime ="/Date("+new Date().getTime()+")/";
		this.StoreId = CmsLineOperator.getStoreId();
	}
	public Integer getGoods_Max() {
		return Goods_Max;
	}
	public void setGoods_Max(Integer goods_Max) {
		Goods_Max = goods_Max;
	}
	public Integer getGoods_Used() {
		return Goods_Used;
	}
	public void setGoods_Used(Integer goods_Used) {
		Goods_Used = goods_Used;
	}
	public Integer getGroupBuyGoodsNumber() {
		return GroupBuyGoodsNumber;
	}
	public void setGroupBuyGoodsNumber(Integer groupBuyGoodsNumber) {
		GroupBuyGoodsNumber = groupBuyGoodsNumber;
	}
	public Integer getMessageCount_Max() {
		return MessageCount_Max;
	}
	public void setMessageCount_Max(Integer messageCount_Max) {
		MessageCount_Max = messageCount_Max;
	}
	public Integer getMultiuserNumber_Max() {
		return MultiuserNumber_Max;
	}
	public void setMultiuserNumber_Max(Integer multiuserNumber_Max) {
		MultiuserNumber_Max = multiuserNumber_Max;
	}
	public Integer getPackBuyGoodsNumber() {
		return PackBuyGoodsNumber;
	}
	public void setPackBuyGoodsNumber(Integer packBuyGoodsNumber) {
		PackBuyGoodsNumber = packBuyGoodsNumber;
	}
	public Integer getPageCount_Max() {
		return PageCount_Max;
	}
	public void setPageCount_Max(Integer pageCount_Max) {
		PageCount_Max = pageCount_Max;
	}
	public Integer getPageCount_Used() {
		return PageCount_Used;
	}
	public void setPageCount_Used(Integer pageCount_Used) {
		PageCount_Used = pageCount_Used;
	}
	public Integer getReduceGoodsNumber() {
		return ReduceGoodsNumber;
	}
	public void setReduceGoodsNumber(Integer reduceGoodsNumber) {
		ReduceGoodsNumber = reduceGoodsNumber;
	}
	public Integer getSeckillingGoodsNumber() {
		return SeckillingGoodsNumber;
	}
	public void setSeckillingGoodsNumber(Integer seckillingGoodsNumber) {
		SeckillingGoodsNumber = seckillingGoodsNumber;
	}
	public Integer getShortageGoodsNumber() {
		return ShortageGoodsNumber;
	}
	public void setShortageGoodsNumber(Integer shortageGoodsNumber) {
		ShortageGoodsNumber = shortageGoodsNumber;
	}
	public String getEndTime() {
		return EndTime;
	}
	public void setEndTime(String endTime) {
		EndTime = endTime;
	}
	
}
