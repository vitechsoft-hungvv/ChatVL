package com.vohung.model;

import java.io.Serializable;

public class ImagesStory implements Serializable {
	private String id;
	private String ext;
	private String url;
	private int h;
	private int w;

	public ImagesStory(String id, String ext, String url, int i, int j) {
		super();
		this.id = id;
		this.ext = ext;
		this.url = url;
		this.h = i;
		this.w = j;
	}

	public ImagesStory() {
		super();
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	
	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	

	
}
