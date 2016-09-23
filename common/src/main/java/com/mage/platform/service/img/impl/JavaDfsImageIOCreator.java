package com.mage.platform.service.img.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;

import com.mage.platform.framework.context.SpringContextHolder;
import com.mage.platform.framework.store.IStoreProcesser;
import com.mage.platform.framework.store.impl.DFSProcesser;
import com.mage.platform.service.dfs.IDfsManager;
import com.mage.platform.service.img.IThumbnailCreator;
import com.mage.platform.service.img.ImageRuntimeException;
import com.sun.image.codec.jpeg.ImageFormatException;

/**
 * 使用javax image io生成缩略图
 * 
 * @author kingapex 2010-7-10下午11:43:05
 */
public class JavaDfsImageIOCreator implements IThumbnailCreator {

	private String srcFile;

	private String destFile;

	private int width;

	private int height;

	private Image img;

	@Resource
	IDfsManager dfsManager;

	public JavaDfsImageIOCreator(String sourcefile, String targetFile) {
		this.srcFile = sourcefile;
		File _fileD = new File(targetFile);
		this.destFile = _fileD.getName();
		try {
			if (null == dfsManager) {
				dfsManager = SpringContextHolder.getBean("dfsManager");
			}
			byte[] buff = dfsManager.getFileById(sourcefile);
			InputStream input = new ByteArrayInputStream(buff);
			img = javax.imageio.ImageIO.read(input);
			width = img.getWidth(null); // 得到源图宽
			height = img.getHeight(null); // 得到源图长
		} catch (IOException e) {
			e.printStackTrace();
		} // 构造Image对象

	}

	public void resize(int w, int h) {
		int target_w, target_h; // 目标宽高
		int x = 0, y = 0; // 缩略图在背景的座标
		x = y = 0;
		target_w = w;
		target_h = h;
		/* 计算目标宽高 */
		if (width / height > w / h) { // 原图长:上下补白
			target_w = w;
			target_h = (int) (target_w * height / width);
			x = 0;
			y = (int) (h - target_h) / 2;

		}
		if (width / height < w / h) { // 原图高:左右补白
			target_h = h;
			target_w = (int) (target_h * width / height);
			y = 0;
			x = (int) (w - target_w) / 2;

		}

		BufferedImage _image = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);
		Graphics graphics = _image.getGraphics();
		graphics.setColor(Color.white);
		graphics.fillRect(0, 0, _image.getWidth(), _image.getHeight());
		graphics.drawImage(img, x, y, target_w, target_h, null); // 绘制缩小后的图
		FileOutputStream out;
		try {
			IStoreProcesser netBlog = new DFSProcesser();
			if (null == dfsManager) {
				dfsManager = SpringContextHolder.getBean("dfsManager");
			}
			byte[] buff = dfsManager.getFileById(this.srcFile);
			InputStream input = new ByteArrayInputStream(buff);
			this.destFile = netBlog.upload(input, this.srcFile, ""); // 文件上传返回目标文件路径
		} catch (ImageFormatException e) { // 输出到文件流
			e.printStackTrace();
			throw new ImageRuntimeException(srcFile, "生成缩略图");
		}

	}

	@Override
	public String getDestFile() {
		return this.destFile;
	}

	public IDfsManager getDfsManager() {
		return dfsManager;
	}

	public void setDfsManager(IDfsManager dfsManager) {
		this.dfsManager = dfsManager;
	}

}
