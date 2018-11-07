package com.team24.stp.shared;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FileUploadResponseData {
	
	private String code;
	private String fileName;
	private String filePath;
	
	public FileUploadResponseData() {
		super();
		clearProperties();
	}
			
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void clearProperties() {
		this.code = "ERROR";
		this.fileName = "";
		this.filePath = "";
	}

}
