package com.got_talent.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

import com.got_talent.configs.Config;
import com.got_talent.models.User;

public class UserController {
	Config config;
	Scanner scanner = new Scanner(System.in);
	User user  = new User();
	public UserController() {
		config = new Config("jdbc:mysql://localhost/got_talent", "root", "");
		
	}
	
	public void addUser() throws SQLException, ClassNotFoundException {
        Random rd = new Random();
        long id = (long)(rd.nextDouble()*199999L);; 

          System.out.println("first name:");
        String firstName = scanner.next();
        System.out.println("fname:");
        String lastName = scanner.next();
        System.out.println(" email:");
        String email = scanner.next();
        System.out.println("phone :");
        String phone = scanner.next();
        String query = "INSERT into users (users.id ,first_name, last_name, email, phone) values(?,?,?,?,?)";
        PreparedStatement statement = config.connection().prepareStatement(query);
          statement.setLong(1, id);
          statement.setString(2, firstName);
          statement.setString(3, lastName);
          statement.setString(4, email);
          statement.setString(5, phone );
          statement.executeUpdate();

      System.out.println("added");
  }
	
	public  User findUserById() throws SQLException, ClassNotFoundException {

        System.out.println("Enter the id of the user you are looking for:");
        long id = scanner.nextLong();
        String sqlString = "SELECT * FROM users WHERE id = ?";
        PreparedStatement statement = config.connection().prepareStatement(sqlString);
        statement.setLong(1, id);
        
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()) {
            user.setId(id);
            user.setFirst_name(resultSet.getString("first_name"));
            user.setLast_name(resultSet.getString("last_name"));
            user.setEmail(resultSet.getString("email"));
            user.setPhone(resultSet.getString("phone"));
        }
        System.out.println(user);
        return user;



    }
	public void updateUser() throws SQLException, ClassNotFoundException {
		System.out.println("Id:");
        int id  = scanner.nextInt();
        System.out.println("first name:");
        String firstName = scanner.next();
        System.out.println("fname:");
        String lastName = scanner.next();
        System.out.println(" email:");
        String email = scanner.next();
        System.out.println("phone :");
        String phone = scanner.next();
        String query = "UPDATE users SET first_name=?,last_name=?,email=?,phone=? WHERE id=" + id;
        PreparedStatement statement = config.connection().prepareStatement(query);
          statement.setString(1, firstName);
          statement.setString(2, lastName);
          statement.setString(3, email);
          statement.setString(4, phone );
          statement.executeUpdate();

      System.out.println("Updated");
  }

}
