package com.ddt.cinefsoft.model;

public class Film {
	private int id;
	private String title;
	private String thumb;
	private String banner;
	private String imdb;
	private int duration;
	private String trailler;

	public Film() {
		super();
	}

	public Film(int id, String title, String thumb, String banner, String imdb, int duration, String trailler) {
		super();
		this.id = id;
		this.title = title;
		this.thumb = thumb;
		this.banner = banner;
		this.imdb = imdb;
		this.duration = duration;
		this.trailler = trailler;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getThumb() {
		return thumb;
	}

	public String getBanner() {
		return banner;
	}

	public String getImdb() {
		return imdb;
	}

	public int getDuration() {
		return duration;
	}

	public String getTrailler() {
		return trailler;
	}

}