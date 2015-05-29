package com.vohung.model;

public class DrawerItem {

	String ItemName;
	int imgResID;
	String title;
	User user;
	boolean isUser;
	boolean isShowHide;
	boolean isSpinner;

	public DrawerItem(boolean isSpinner) {
		super();
		this.isSpinner = isSpinner;
	}

	public boolean isSpinner() {
		return isSpinner;
	}

	public void setSpinner(boolean isSpinner) {
		this.isSpinner = isSpinner;
	}

	public DrawerItem(String title) {
		super();
		this.title = title;
	}

	public DrawerItem(User user, boolean isUser) {
		super();
		this.user = user;
		this.isUser = isUser;
	}

	public DrawerItem(String itemName, int imgResID) {
		super();
		ItemName = itemName;
		this.imgResID = imgResID;
	}

	public boolean isShowHide() {
		return isShowHide;
	}

	public void setShowHide(boolean isShowHide) {
		this.isShowHide = isShowHide;
	}

	public DrawerItem(String itemName, int imgResID, boolean isShowHide) {
		super();
		ItemName = itemName;
		this.imgResID = imgResID;
		this.isShowHide = isShowHide;
	}

	public String getItemName() {
		return ItemName;
	}

	public void setItemName(String itemName) {
		ItemName = itemName;
	}

	public int getImgResID() {
		return imgResID;
	}

	public void setImgResID(int imgResID) {
		this.imgResID = imgResID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isUser() {
		return isUser;
	}

	public void setUser(boolean isUser) {
		this.isUser = isUser;
	}

}
