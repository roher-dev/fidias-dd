package com.fidias.database.modeler.api.dto;

public class FilesDto extends BasicDto {

	private static final long serialVersionUID = 1L;

	private String filename;
	private long size;

	public FilesDto(String filename, long size) {
		super();
		this.filename = filename;
		this.size = size;
	}
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}

}
