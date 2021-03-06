package com.ddt.cinefsoft.service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddt.cinefsoft.model.Auditorium;
import com.ddt.cinefsoft.model.Film;
import com.ddt.cinefsoft.model.Reservation;
import com.ddt.cinefsoft.model.ReservationFoody;
import com.ddt.cinefsoft.model.Seat;
import com.ddt.cinefsoft.repository.FilmRepository;

@Service
public class FilmService {
	
	@Autowired
	private FilmRepository filmRepository;

	public Film getFilmById(int id) {
		return filmRepository.getFilmById(id);
	}

	public List<Date> getDate(int id) {
		return filmRepository.getDate(id);
	}

	public List<Auditorium> getAuditorium(int id, String date) {
		return filmRepository.getAuditorium(id, date);
	}

	public List<Time> getTime(int id, String date, String audi) {
		return filmRepository.getTime(id, date, audi);
	}

	public List<Integer> getTicketPriceAndDiscount(String date, String audi, String time) {
		return filmRepository.getTicketPriceAndDiscount(date, audi, time);
	}

	public List<Seat> getSeatsSold(String date, String audi, String time) {
		return filmRepository.getSeatsSold(date, audi, time);
	}

	public List<Seat> getAllSeat(String date, String audi, String time) {
		return filmRepository.getAllSeat(date, audi, time);
	}

	public boolean postDataReservation(Reservation reservation) {
		return filmRepository.postDataReservation(reservation);
	}

	public boolean postDataReservationFoody(ReservationFoody reservationFoody) {
		return filmRepository.postDataReservationFoody(reservationFoody);
	}

}
