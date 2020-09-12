package com.ddt.cinefsoft.model;

public class Reservation {
	private String acc_username;
	private int seat_id;
	private String date;
	private String audi;
	private String time;

	public Reservation() {
		super();
	}

	public Reservation(String acc_username, int seat_id, String date, String audi, String time) {
		super();
		this.acc_username = acc_username;
		this.seat_id = seat_id;
		this.date = date;
		this.audi = audi;
		this.time = time;
	}

	public String getAcc_username() {
		return acc_username;
	}

	public int getSeat_id() {
		return seat_id;
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
