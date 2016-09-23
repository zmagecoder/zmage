package com.mage.platform.service.img;

public class ImageRuntimeException extends RuntimeException {
	
	private static final long serialVersionUID = -1883123575096901059L;

	public ImageRuntimeException(String path,String proesstype){
		super("对图片"+path +"进行"  + proesstype +"出错" );
	}
}
