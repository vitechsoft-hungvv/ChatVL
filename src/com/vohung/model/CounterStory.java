package com.vohung.model;

import java.io.Serializable;

public class CounterStory implements Serializable {
	private String vote;
	private String point;
	private String c;
	private String fbc;
	private String tc;
	private String vd;
	private String p1_vote;
	private String p2_vote;
	private String hn;
	public CounterStory(String vote, String point, String c, String fbc,
			String tc, String vd, String p1_vote, String p2_vote) {
		super();
		this.vote = vote;
		this.point = point;
		this.c = c;
		this.fbc = fbc;
		this.tc = tc;
		this.vd = vd;
		this.p1_vote = p1_vote;
		this.p2_vote = p2_vote;
	}
	public CounterStory(String vote, String point, String c, String fbc,
			String tc, String vd, String p1_vote, String p2_vote, String hn) {
		super();
		this.vote = vote;
		this.point = point;
		this.c = c;
		this.fbc = fbc;
		this.tc = tc;
		this.vd = vd;
		this.p1_vote = p1_vote;
		this.p2_vote = p2_vote;
		this.hn = hn;
	}
	public String getVote() {
		return vote;
	}
	public void setVote(String vote) {
		this.vote = vote;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	public String getFbc() {
		return fbc;
	}
	public void setFbc(String fbc) {
		this.fbc = fbc;
	}
	public String getTc() {
		return tc;
	}
	public void setTc(String tc) {
		this.tc = tc;
	}
	public String getVd() {
		return vd;
	}
	public void setVd(String vd) {
		this.vd = vd;
	}
	public String getP1_vote() {
		return p1_vote;
	}
	public void setP1_vote(String p1_vote) {
		this.p1_vote = p1_vote;
	}
	public String getP2_vote() {
		return p2_vote;
	}
	public void setP2_vote(String p2_vote) {
		this.p2_vote = p2_vote;
	}
	public String getHn() {
		return hn;
	}
	public void setHn(String hn) {
		this.hn = hn;
	}

	
}
