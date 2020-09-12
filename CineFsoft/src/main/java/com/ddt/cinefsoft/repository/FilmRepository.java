package com.ddt.cinefsoft.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ddt.cinefsoft.model.Auditorium;
import com.ddt.cinefsoft.model.Film;
import com.ddt.cinefsoft.model.Reservation;
import com.ddt.cinefsoft.model.ReservationFoody;
import com.ddt.cinefsoft.model.Seat;

@Repository
public class FilmRepository {

	private Connection connectionDatabase() {
		String url = "jdbc:sqlserver://localhost;databaseName=CinemaProjectFSoft";
		String userDB = "sa";
		String passDB = "Ducthanh@313";

		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, userDB, passDB);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return con;
	}

	public Film getFilmById(int id) {
		Film film = null;

		Connection con = this.connectionDatabase();
		String sql = "select mov_id, mov_title, mov_thumb, mov_banner, mov_imdb, mov_duration, mov_trailer\n"
				+ "from Movie\n" + "where mov_id =" + id;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
//				int id = rs.getInt(1);
				String title = rs.getString(2);
				String thumb = rs.getString(3);
				String banner = rs.getString(4);
				String imdb = rs.getString(5);
				int duration = rs.getInt(6);
				String trailler = rs.getString(7);

				film = new Film(id, title, thumb, banner, imdb, duration, trailler);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	public List<Date> getDate(int id) {
		List<Date> listDate = new ArrayList<Date>();

		Connection con = this.connectionDatabase();
		String sql = "select distinct Screening.scr_date\n" + "from Screening\n" + "where mov_id = " + id;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				Date date = rs.getDate("scr_date");

				listDate.add(date);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listDate;
	}

	public List<Auditorium> getAuditorium(int id, String date) {
		List<Auditorium> listAudi = new ArrayList<Auditorium>();

		Connection con = this.connectionDatabase();
		String sql = "select distinct Auditorium.audi_id, Auditorium.audi_name\n" + "from Auditorium\n"
				+ "inner join Screening\n" + "    on Auditorium.audi_id = Screening.audi_id\n" + "where mov_id = " + id
				+ " and scr_date = '" + date + "'";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				int idAudi = rs.getInt(1);
				String name = rs.getString(2);

				listAudi.add(new Auditorium(idAudi, name));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listAudi;
	}

	public List<Time> getTime(int id, String date, String audi) {
		List<Time> listTime = new ArrayList<Time>();

		Connection con = this.connectionDatabase();
		String sql = "select distinct Screening.scr_time\n" + "from Screening\n" + "inner join Auditorium\n"
				+ "    on Screening.audi_id = Auditorium.audi_id\n" + "where mov_id = " + id + " and scr_date = '"
				+ date + "' and Auditorium.audi_id = " + audi;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				Time time = rs.getTime("scr_time");
				listTime.add(time);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listTime;
	}

	public List<Integer> getTicketPriceAndDiscount(String date, String audi, String time) {
		List<Integer> ticketPriceAndDiscount = new ArrayList<>();

		Connection con = this.connectionDatabase();
		String sql = "select Screening.scr_price, Screening.scr_discount\n" + "from Screening\n"
				+ "inner join Auditorium\n" + "    on Screening.audi_id = Auditorium.audi_id\n" + "where scr_date = '"
				+ date + "' and Auditorium.audi_id = " + audi + " and scr_time = '" + time + "'";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				int price = rs.getInt("scr_price");
				int discount = rs.getInt("scr_discount");

				ticketPriceAndDiscount.add(price);
				ticketPriceAndDiscount.add(discount);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ticketPriceAndDiscount;
	}

	public List<Seat> getSeatsSold(String date, String audi, String time) {
		List<Seat> seatsSold = new ArrayList<>();

		Connection con = this.connectionDatabase();
		String sql = "select seat_id, seat_number\n" + "from Seat\n" + "where seat_id in (\n" + "select seat_id\n"
				+ "from Reservation\n" + "where scr_id = (\n" + "    select Screening.scr_id\n" + "    from Screening\n"
				+ "    inner join Auditorium\n" + "        on Screening.audi_id = Auditorium.audi_id\n"
				+ "    where scr_date = '" + date + "' and Screening.audi_id = " + audi + " and scr_time = '" + time
				+ "'\n" + ")\n" + ")";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);

				seatsSold.add(new Seat(id, name));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seatsSold;
	}

	public List<Seat> getAllSeat(String date, String audi, String time) {
		List<Seat> allSeats = new ArrayList<Seat>();

		Connection con = this.connectionDatabase();
		String sql = "select seat_id, seat_number\n" + "from Seat\n" + "where audi_id = (\n"
				+ "    select Screening.audi_id\n" + "    from Screening\n" + "    inner join Auditorium\n"
				+ "        on Screening.audi_id = Auditorium.audi_id\n" + "	 	where scr_date = '" + date
				+ "' 		and Auditorium.audi_id = " + audi + " and scr_time = '" + time + "')";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("seat_id");
				String name = rs.getString("seat_number");

				allSeats.add(new Seat(id, name));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return allSeats;
	}

	public boolean postDataReservation(Reservation reservation) {
		int scr_id = 0;
		boolean check = true;

		Connection con = this.connectionDatabase();
		String sql01 = "select Screening.scr_id\n" + "    from Screening\n" + "    inner join Auditorium\n"
				+ "        on Screening.audi_id = Auditorium.audi_id\n" + "    where scr_date = '"
				+ reservation.getDate() + "' and Screening.audi_id = " + reservation.getAudi() + " and scr_time = '"
				+ reservation.getTime() + "'";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql01);

			while (rs.next()) {
				scr_id = rs.getInt(1);
			}
		} catch (SQLException e) {
			check = false;
			e.printStackTrace();
		}
		
		String sql02 = "insert into Reservation values('" + reservation.getAcc_username() + "', "
				+ reservation.getSeat_id() + ", " + scr_id + ", 'online')";

		try {
			Statement st = con.createStatement();
			int row = st.executeUpdate(sql02);

			if (row == 0) {
				check = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			check = false;
		}

		return check;
	}

	public boolean postDataReservationFoody(ReservationFoody reservationFoody) {
		int scr_id = 0;
		boolean check = true;
		boolean existance = false;

		Connection con = this.connectionDatabase();
		String sql01 = "select Screening.scr_id\n" + "    from Screening\n" + "    inner join Auditorium\n"
				+ "        on Screening.audi_id = Auditorium.audi_id\n" + "    where scr_date = '"
				+ reservationFoody.getDate() + "' and Screening.audi_id = " + reservationFoody.getAudi() + " and scr_time = '"
				+ reservationFoody.getTime() + "'";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql01);

			while (rs.next()) {
				scr_id = rs.getInt(1);
			}
		} catch (SQLException e) {
			check = false;
			e.printStackTrace();
		}

		String sql02 = "select *\n" + "from ReservationFoody\n" + "where acc_username = '"
				+ reservationFoody.getAcc_username() + "' and scr_id = " + scr_id + " and foo_id = "
				+ reservationFoody.getFoo_id() + "";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql02);

			if (rs.next()) {
				existance = true;
			}
		} catch (SQLException e) {
			check = false;
			e.printStackTrace();
		}

		String sql03 = "insert into ReservationFoody values(" + scr_id + ", '" + reservationFoody.getAcc_username()
				+ "', " + reservationFoody.getFoo_id() + ", " + reservationFoody.getQuantity() + ")";
		String sql04 = "update ReservationFoody\n" + "set quantity = quantity + " + reservationFoody.getQuantity()
				+ "\n" + "where acc_username = '" + reservationFoody.getAcc_username() + "' and scr_id = " + scr_id
				+ " and foo_id = " + reservationFoody.getFoo_id();
		try {
			Statement st = con.createStatement();
			String sql = "";

			if (existance) {
				sql = sql04;
			} else {
				sql = sql03;
			}
			int row = st.executeUpdate(sql);

			if (row == 0) {
				check = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			check = false;
		}

		return check;
	}

}
