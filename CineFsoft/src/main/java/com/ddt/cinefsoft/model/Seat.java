package com.ddt.cinefsoft.model;

public class Seat {
	private int id;
	private String name;

	public Seat() {

	}

	public Seat(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
