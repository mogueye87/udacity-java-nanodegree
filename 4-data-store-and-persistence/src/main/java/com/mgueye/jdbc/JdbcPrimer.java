package com.mgueye.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcPrimer {

	public static void main(String[] args) {
		// step1: obtain connection object from DriverManager
		// -> gives all informations for the database
		// -> gives url, database name, username and password
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jdnd_c3?serverTimezone=UTC&user=root&password=MOG123ueye.")) {
				System.out.println(
						"Database connection has been established: " + conn.getMetaData().getDatabaseProductName());
				// step2: create a statement object that allows to create sql query
				try(Statement stmt = conn.createStatement()){
					// step3: use the SQL statement to execute SQL query
					String sqlQuery = "SELECT * FROM POSTS";
					ResultSet rs = stmt.executeQuery(sqlQuery);
					System.out.println("SQL query executed successfully");
					
					// step: process the ResultSet and object that holds the cursor which point just before the first row
					while(rs.next()) {
						int  id = rs.getInt("post_id");
						String title = rs.getString("post_title");
						String comment = rs.getString("post_text");
						System.out.println(" post id : " + id);
						System.out.println(" post title : " + title);
						System.out.println(" post comment : " + comment);
						
					}
				}
			
				
				// String sql = "INSERT INTO posts ('Java developer nanodegree', 'Prepare for
				// devops nanodegree to add to my porfolio')";
				// int rowNum = stmt.executeUpdate(sql);
				// System.out.println("number of rows affected: " + rowNum);
			}
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + ((SQLException) e).getSQLState());
			System.out.println("VendorErrorCode: " + ((SQLException) e).getErrorCode());
		}
	}

}
