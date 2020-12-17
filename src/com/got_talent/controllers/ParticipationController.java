package com.got_talent.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

import com.got_talent.configs.Config;

public class ParticipationController {

	Config config;
	Scanner input = new Scanner(System.in);
	public ParticipationController() {
		config = new Config("jdbc:mysql://localhost/got_talent", "root", "");
	}
public void addParticipation() throws SQLException {
		
		System.out.println("Enter your id:");
		String idStr = input.nextLine();
		long id = Long.parseLong(idStr);
		
		
		
		System.out.println("Choose your category:");

		String sqlString = "SELECT * FROM category";
		PreparedStatement statement = config.connection().prepareStatement(sqlString);
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			System.out.println(resultSet.getLong("id")+". "+resultSet.getString("name"));
		}
		
		String categoryStr = input.nextLine();
		long category = Long.parseLong(categoryStr);
		
		System.out.println("Enter the description of your talent:");
		String desc = input.nextLine();
		
		System.out.println("Enter the start time of your show: (yyy-mmm-dd h:m:s)");
		String startTime = input.nextLine();
		Timestamp startTimestamp = Timestamp.valueOf(startTime);
		
		System.out.println("Enter the end time of your show: (yyy-mmm-dd h:m:s)");
		String endTime = input.nextLine();
		Timestamp endTimestamp = Timestamp.valueOf(endTime);
		
		System.out.println("Enter the path of your attached file:");
		String file = input.nextLine();
		
		boolean is_accepted = false;
		
		String query = "INSERT into participation (id_user ,id_category, description, show_start_time, show_end_time,attached_file,is_accepted) values(?,?,?,?,?,?,?)";
		  PreparedStatement statement1 = config.connection().prepareStatement(query);
			statement1.setLong(1, id);
			statement1.setLong(2, category);
			statement1.setString(3, desc);
			statement1.setTimestamp(4, startTimestamp);
			statement1.setTimestamp(5, endTimestamp );
			statement1.setString(6, file);
			statement1.setBoolean(7, is_accepted );
			
			statement1.executeUpdate();
		 
		System.out.println("Check your email for more information.");
				
	}
	
}
