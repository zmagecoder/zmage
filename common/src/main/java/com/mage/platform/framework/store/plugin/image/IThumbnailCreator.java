package com.mage.platform.framework.store.plugin.image;

public interface IThumbnailCreator {
	
	public void resize(int w, int h) ;
	
	public String getDestFile() ;
	
}
