package com.vohung.model;

import java.io.Serializable;

public class User implements Serializable{
	private String avatar;
	private String id;
	private String iid;
	private String lname;
	private String name;
	private String mail;
	private String status;
	private String uhash;
	private String token;
	private String callback;
	private String massge;
	private CounterUser counterUser;	
	public User() {
		super();
	}
	
	
	public User(String avatar, String id, String iid, String lname,
			String name, String mail, String status, String uhash, String token) {
		super();
		this.avatar = avatar;
		this.id = id;
		this.iid = iid;
		this.lname = lname;
		this.name = name;
		this.mail = mail;
		this.status = status;
		this.uhash = uhash;
		this.token = token;
	}


	public User(String avatar, String id, String iid, String lname,
			String name, String mail, String status, String uhash,
			String token, String callback, String massge,
			CounterUser counterUser) {
		super();
		this.avatar = avatar;
		this.id = id;
		this.iid = iid;
		this.lname = lname;
		this.name = name;
		this.mail = mail;
		this.status = status;
		this.uhash = uhash;
		this.token = token;
		this.callback = callback;
		this.massge = massge;
		this.counterUser = counterUser;
	}

	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public CounterUser getCounterUser() {
		return counterUser;
	}
	public void setCounterUser(CounterUser counterUser) {
		this.counterUser = counterUser;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUhash() {
		return uhash;
	}
	public void setUhash(String uhash) {
		this.uhash = uhash;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	public String getMassge() {
		return massge;
	}
	public void setMassge(String massge) {
		this.massge = massge;
	}
}
