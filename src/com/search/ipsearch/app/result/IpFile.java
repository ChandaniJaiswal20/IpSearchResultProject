package com.search.ipsearch.app.result;

public class IpFile {

	
	private int fileSeries;
	
	private int fileNumber;
	
	private String fileSequence;
	
	private String fileType;
	

	public int getFileSeries() {
		return fileSeries;
	}

	public void setFileSeries(int fileSeries) {
		this.fileSeries = fileSeries;
	}

	public int getFileNumber() {
		return fileNumber;
	}

	public void setFileNumber(int fileNumber) {
		this.fileNumber = fileNumber;
	}

	public String getFileSequence() {
		return fileSequence;
	}

	public void setFileSequence(String fileSequence) {
		this.fileSequence = fileSequence;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	public String getMarkIdFormatted(){
	String markIdFormatted = this.getFileSequence() + "/" +
			this.getFileType() + "/" +
			this.getFileSeries() + "/" +
			this.getFileNumber();
	
	return markIdFormatted;
	}
}
