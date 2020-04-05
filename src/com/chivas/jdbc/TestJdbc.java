package com.chivas.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJdbc {

	public static void main(String[] args) {
		try {
			String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false";
			String user = "hbstudent";
			String pass = "hbstudent";

			System.out.println("Connecting to the database:" + jdbcUrl);
			Connection conn = DriverManager.getConnection(jdbcUrl, user, pass);
			System.out.println("Connection success.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
