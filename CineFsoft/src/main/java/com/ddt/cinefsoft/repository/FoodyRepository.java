package com.ddt.cinefsoft.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ddt.cinefsoft.model.Foody;

@Repository
public class FoodyRepository {

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

	public List<Foody> getFoody() {
		List<Foody> listFoody = new ArrayList<Foody>();

		Connection con = this.connectionDatabase();
		String sql = "select *\n" + "from Foody";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt(1);
				String title = rs.getString(2);
				int price = rs.getInt(3);
				String url = rs.getString(4);
				String alt = rs.getString(5);

				listFoody.add(new Foody(id, title, price, url, alt));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listFoody;
	}

}
