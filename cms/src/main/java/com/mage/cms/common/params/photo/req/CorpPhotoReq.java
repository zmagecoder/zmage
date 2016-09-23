package com.mage.cms.common.params.photo.req;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import com.mage.cms.common.params.base.req.CmsLineReq;
import com.mage.cms.common.params.photo.resp.CorpPhotoResp;
import com.mage.cms.common.params.photo.vo.Photo;
import com.mage.cms.common.params.photo.vo.TransformParameter;
import com.mage.platform.framework.config.EopSetting;
import com.mage.platform.framework.config.FileConfigSetting;
import com.mage.platform.framework.context.SpringContextHolder;
import com.mage.platform.framework.database.DStoreInst;
import com.mage.platform.framework.store.IStoreProcesser;
import com.mage.platform.framework.store.impl.StoreProcesser;
import com.mage.platform.framework.utils.UploadUtil;
import com.mage.platform.service.dfs.IDStoreManager;
import com.mage.platform.service.dfs.IDfsManager;


/**
 * 自由编辑实体对象，同时赋予动作操作
 * @author sguo
 * @date 2014.06.20
 */
public class CorpPhotoReq extends CmsLineReq implements Serializable{
	
	private static final long serialVersionUID = -8331149117589358516L;
	
	private String temp_photo_id;//"3133772"
	private TransformParameter tran_param;
	private String quality;

	@Resource
	IDStoreManager dStoreManager;
	
	@Resource
	IDfsManager dfsManager;
	/**
	 * 裁剪图片
	 * @throws Exception 
	 */
	public CorpPhotoResp edit() throws Exception{
		CorpPhotoResp corpPhotoResp = new CorpPhotoResp();
		OutputStream outs = null;
		InputStream is = null;
		ImageInputStream iis = null;
		String formatName = "gif";
		File file = null;
		try{
			String sql = "select * from es_dstore_inst where seq=?";
			Map<String, Object> dstoreInst = this.getExecutor().queryForMap(sql, this.temp_photo_id);
			String file_path = (String)dstoreInst.get("file_path");
/*			IStoreProcesser netBlog = StoreProcesser.getFileProcesser();
 			byte [] buff = netBlog.getFileUrl(file_path).getBytes();
	        outs = new FileOutputStream(file); 
	        outs.write(buff);*/
			formatName = file_path.substring(file_path.lastIndexOf(".") + 1, file_path.length());
			file = new File(FileConfigSetting.FAST_DFS_SAVE_DIR+temp_photo_id+"."+formatName);
	        file_path = file_path.replace(EopSetting.IMG_SERVER_DOMAIN, "");
	        IStoreProcesser netBlog = StoreProcesser.getFileProcesser();
	        if("DFS".equalsIgnoreCase(netBlog.getStroeType())){
	        	if(dfsManager == null)
	        		dfsManager = SpringContextHolder.getBean("dfsManager");
	        	byte[] picByte = dfsManager.getFileById(file_path);
	        	is = new ByteArrayInputStream(picByte);
	        }else {
	        	is = new FileInputStream(file_path);
	        }
			Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(formatName);
			ImageReader reader = it.next();
			iis = ImageIO.createImageInputStream(is);
			reader.setInput(iis, true);
			ImageReadParam param = reader.getDefaultReadParam();
			int x=Integer.parseInt(this.tran_param.getCrop().getX1()),
				y=Integer.parseInt(this.tran_param.getCrop().getY1()),
				w=Integer.parseInt(this.tran_param.getCrop().getX2())-Integer.parseInt(this.tran_param.getCrop().getX1()),
				h=Integer.parseInt(this.tran_param.getCrop().getY2())-Integer.parseInt(this.tran_param.getCrop().getY1());
			Rectangle rect = new Rectangle(x,y,w,h);
//			Rectangle rect = new Rectangle(99,99,99,99);
			param.setSourceRegion(rect);
			BufferedImage bi = reader.read(0,param);
			ImageIO.write(bi, formatName, file);
			String path = UploadUtil.upload(file,temp_photo_id+"."+formatName,"ckeditor");
			path = UploadUtil.replacePath(path);
			if(null == this.dStoreManager){
				this.dStoreManager = SpringContextHolder.getBean("dStoreManager");
			}
			DStoreInst inst = new DStoreInst();
			inst.setStore_type(netBlog.getStroeType());
			inst.setFile_path(path);
			this.dStoreManager.add(inst);
			Photo photo = new Photo();
			photo.setName(temp_photo_id+"."+formatName);
			photo.setCreateTime("\\/Date(1403141247893)\\/");
			photo.setDomain("img.zhuyun.cn");
			photo.setFileName(inst.getFile_path());
			photo.setFileSize("30159");
			photo.setFolderId("0");
			photo.setGroup("group1");
			photo.setHeight(this.getTran_param().getSize().getH());
			photo.setWidth(this.getTran_param().getSize().getW());
			photo.setId(inst.getSeq());
			photo.setIsTemp(true);
			photo.setPath(path);
			photo.setThumbnail(inst.getFile_path());
			photo.setThumbnailPath(inst.getFile_path());
			photo.setReferCount("1");
			photo.setOriginalId("3118116");
			photo.setTransformParameter(this.getTran_param());
			corpPhotoResp.setPhoto(photo);
			corpPhotoResp.setSuccess(true);
		}finally{
			file.delete();
			if(null != outs)
				outs.close();
			if(null != is )
				is.close();
			if(null != iis)
				iis.close();
		}
		return corpPhotoResp;
	}

	public String getTemp_photo_id() {
		return temp_photo_id;
	}

	public void setTemp_photo_id(String temp_photo_id) {
		this.temp_photo_id = temp_photo_id;
	}

	public TransformParameter getTran_param() {
		return tran_param;
	}

	public void setTran_param(TransformParameter tran_param) {
		this.tran_param = tran_param;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public IDStoreManager getdStoreManager() {
		return dStoreManager;
	}

	public void setdStoreManager(IDStoreManager dStoreManager) {
		this.dStoreManager = dStoreManager;
	}
	
}
