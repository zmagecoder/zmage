package com.mage.maven.model;

import com.mage.database.BigTextField;
import com.mage.database.DateField;
import com.mage.database.NotDbField;

public class Attachment {
	
	private int attachmentid;
	
	private String  attachmentname;
	
	private String createdate;
	
	private String notDbField;
	
	private String bigText;

	public int getAttachmentid() {
		return attachmentid;
	}

	public void setAttachmentid(int attachmentid) {
		this.attachmentid = attachmentid;
	}

	public String getAttachmentname() {
		return attachmentname;
	}

	public void setAttachmentname(String attachmentname) {
		this.attachmentname = attachmentname;
	}
	
	@DateField
	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	
	@NotDbField
	public String getNotDbField() {
		return notDbField;
	}

	public void setNotDbField(String notDbField) {
		this.notDbField = notDbField;
	}
	
	@BigTextField
	public String getBigText() {
		return bigText;
	}

	public void setBigText(String bigText) {
		this.bigText = bigText;
	}
}
