package com.kiiolabs.cally.model.bean;

public class NavigationItem {

	private String title;
	private int icon;
	private String count = "0";

	public NavigationItem() {
	}

	public NavigationItem(String title, int icon) {
		this.title = title;
		this.icon = icon;
	}


	public String getTitle() {
		return this.title;
	}

	public int getIcon() {
		return this.icon;
	}

	public String getCount() {
		return this.count;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public void setCount(String count) {
		this.count = count;
	}

}
