package com.mage.platform.service.img;

public interface IThumbnailCreator {
	public void resize(int w, int h) ;
	public String getDestFile() ;
}
