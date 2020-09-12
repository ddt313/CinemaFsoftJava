package com.ddt.cinefsoft.model;

public class ReservationFoody {
	private String acc_username;
	private int foo_id;
	private int quantity;
	private String date;
	private String audi;
	private String time;

	public ReservationFoody() {
		super();
	}

	public ReservationFoody(String acc_username, int foo_id, int quantity, String date, String audi, String time) {
		super();
		this.acc_username = acc_username;
		this.foo_id = foo_id;
		this.quantity = quantity;
		this.date = date;
		this.audi = audi;
		this.time = time;
	}

	public String getAcc_username() {
		return acc_username;
	}

	public int getFoo_id() {
		return foo_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getDate() {
		return date;
	}

	public String getAudi() {
		return audi;
	}

	public String getTime() {
		return time;
	}

}
