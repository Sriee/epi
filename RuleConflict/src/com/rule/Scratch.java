package com.rule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Scratch {

	public static void main(String[] args) {
		
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Rules?autoReconnect=true&useSSL=false",
					"root", "welcome");
			PreparedStatement stmt = connection.prepareStatement("show tables");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				System.out.println(rs.getString("Tables_in_Rules"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null && !connection.isClosed())
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
