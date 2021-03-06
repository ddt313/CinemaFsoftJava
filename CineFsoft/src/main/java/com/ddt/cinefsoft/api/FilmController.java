package com.ddt.cinefsoft.api;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ddt.cinefsoft.model.Auditorium;
import com.ddt.cinefsoft.model.Film;
import com.ddt.cinefsoft.model.Reservation;
import com.ddt.cinefsoft.model.ReservationFoody;
import com.ddt.cinefsoft.model.Seat;
import com.ddt.cinefsoft.service.FilmService;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("film")
public class FilmController {

	@Autowired
	private FilmService filmService;

	@GetMapping("{id}")
	public Film getFilmById(@PathVariable int id) {
		return filmService.getFilmById(id);
	}

	@GetMapping("{id}/date")
	public List<Date> getDate(@PathVariable int id) {
		return filmService.getDate(id);
	}

	@GetMapping("{id}/{date}/audi")
	public List<Auditorium> getAuditorium(@PathVariable int id, @PathVariable String date) {
		return filmService.getAuditorium(id, date);
	}

	@GetMapping("{id}/{date}/{audi}/time")
	public List<Time> getTime(@PathVariable int id, @PathVariable String date, @PathVariable String audi) {
		return filmService.getTime(id, date, audi);
	}

	@GetMapping("{date}/{audi}/{time}/price")
	public List<Integer> getTicketPriceAndDiscount(@PathVariable String date,
			@PathVariable String audi, @PathVariable String time) {
		return filmService.getTicketPriceAndDiscount(date, audi, time);
	}

	@GetMapping("{date}/{audi}/{time}/seats-sold")
	public List<Seat> getSeatsSold(@PathVariable String date, @PathVariable String audi, @PathVariable String time) {
		return filmService.getSeatsSold(date, audi, time);
	}
	
	@GetMapping("{date}/{audi}/{time}/all-seats")
	public List<Seat> getAllSeat(@PathVariable String date, @PathVariable String audi, @PathVariable String time) {
		return filmService.getAllSeat(date, audi, time);
	}
	
	@PostMapping("/reservation")
	public boolean postDataReservation(@RequestBody Reservation reservation) {		
		return filmService.postDataReservation(reservation);
	}
	
	@PostMapping("/reservation-food")
	public boolean postDataReservationFoody(@RequestBody ReservationFoody reservationFoody) {
		return filmService.postDataReservationFoody(reservationFoody);
	}

}
