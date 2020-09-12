package com.ddt.cinefsoft.model;

public class Foody {
	private int id;
	private String title;
	private int price;
	private String url;
	private String alt;

	public Foody() {
		super();
	}

	public Foody(int id, String title, int price, String url, String alt) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.url = url;
		this.alt = alt;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public int getPrice() {
		return price;
	}

	public String getUrl() {
		return url;
	}

	public String getAlt() {
		return alt;
	}

}
