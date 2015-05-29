package com.vohung.model;

import java.io.Serializable;

public class CounterUser implements Serializable {
	private String p;
	private String c;
	private String k;
	private String vd;
	public String getP() {
		return p;
	}
	public void setP(String p) {
		this.p = p;
	}
	public CounterUser(String p, String c, String k, String vd) {
		super();
		this.p = p;
		this.c = c;
		this.k = k;
		this.vd = vd;
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	public String getK() {
		return k;
	}
	public void setK(String k) {
		this.k = k;
	}
	public String getVd() {
		return vd;
	}
	public void setVd(String vd) {
		this.vd = vd;
	}
	
	
	

}
