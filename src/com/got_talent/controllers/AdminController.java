package com.got_talent.controllers;

import java.net.Authenticator;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import com.got_talent.configs.Config;
import com.got_talent.models.Participation;
import com.got_talent.models.User;

import enums.ConDb;
import enums.Credential;

import javax.mail.*;
import javax.mail.internet.*;

public class AdminController {

	Config config;
	Scanner input = new Scanner(System.in);

	public AdminController() {
		config = new Config("jdbc:mysql://localhost/got_talent", "root", "");

	}

	public ArrayList<User> findAllUsers() throws SQLException, ClassNotFoundException {

		// declaring the array list
		ArrayList<User> usersList = new ArrayList<>();

		String query = "SELECT * FROM users";
		PreparedStatement statement = config.connection().prepareStatement(query);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			User user = new User(resultSet.getLong("id"), resultSet.getString("first_name"),
					resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getString("phone"));
			usersList.add(user);

		}

		for (User list : usersList) {
			System.out.println(list.toString());
		}

		return usersList;

	}

	public void adminConnect() throws SQLException {
		String query = "Update adminsession SET  is_connected=true WHERE id=15970010";
		PreparedStatement stmt = config.connection().prepareStatement(query);
		stmt.executeUpdate();
		System.out.println("Admin Logged in succesfully");
	}
	public void adminLoggedOut() throws SQLException {
		
		String sqlString = "Update adminsession SET  is_connected=false WHERE id=15970010";
		PreparedStatement statement = config.connection().prepareStatement(sqlString);
		statement.executeUpdate();
		System.out.println("Admin Logged out succesfully");
	}

	public List<Participation> findParticipations() throws SQLException {
		String query = "SELECT * FROM participation";
		Statement statement = config.connection().createStatement();
		ResultSet resultSet = statement.executeQuery(query);

		while (resultSet.next()) {

			System.out.print(resultSet.getString("id_category")+" ");
			System.out.print(resultSet.getString("id_user")+" ");
			System.out.print(resultSet.getString("description")+" ");
			System.out.print(resultSet.getString("show_start_time")+" ");
			System.out.print(resultSet.getString("show_end_time")+" ");
			System.out.println(resultSet.getString("is_accepted")+" ");

		}

		return null;

	}

	public Participation findParticipationByUserEmail() {

		System.out.println("Email ");
		String email = input.next();
		Participation participation = new Participation();

		try {
			String query = "SELECT * FROM participation p, users u WHERE p.id_user= u.id AND u.email='" + email
					+ "'";
			PreparedStatement stmt = config.connection().prepareStatement(query);
			ResultSet resultSet = stmt.executeQuery(query);
			while (resultSet.next()) {

				System.out.print(resultSet.getString("id_user")+" ");
				System.out.println(resultSet.getString("description"));

			}
		} catch (Exception e) {
			System.out.println("Vous avez une erreur " + e.getMessage());
		}

		return null;

	}
	public boolean is_Exist(String email,String password) throws SQLException {
		String query = "SELECT * FROM administrator WHERE email='"+email+"' AND password='"+password+"'";
		PreparedStatement stmt = config.connection().prepareStatement(query);
		ResultSet resultSet = stmt.executeQuery(query);
		
		return false;
	}

	public void validateParticipation() throws SQLException {
		findParticipations();
		System.out.println("id category");
		int idCat = input.nextInt();
		System.out.println("id user");
		String idUser = input.next();
		String query = "SELECT * FROM participation WHERE id_user='" + idUser + "' AND id_category='" + idCat + "'";
		PreparedStatement statement = config.connection().prepareStatement(query);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {

			System.out.println("Accepter(1) ou Refuser(2) ? ");
			int valid = input.nextInt();
			if (Integer.valueOf(valid) != null) {
				if (valid == 1) {
					String query1 = "Update participation SET  is_accepted=true WHERE id_user='" + idUser
							+ "' AND id_category='" + idCat + "'  ";
					String query2 ="SELECT * FROM users WHERE id='"+idUser+"'";
					PreparedStatement stmt = config.connection().prepareStatement(query1);
					PreparedStatement stmt2 = config.connection().prepareStatement(query2);
					stmt.executeUpdate();
					ResultSet rs=stmt2.executeQuery();
					if(rs.next()) {
						sendMail(rs.getString("email"));
					}
					System.out.println("la participation a ete bien acceptée");
					
				} else if (valid == 2) {
					String query1 = "Update participation SET  is_accepted=true WHERE id_user='" + idUser
							+ "' AND id_category='" + idCat + "'  ";
					PreparedStatement stmt = config.connection().prepareStatement(query1);
					stmt.executeUpdate();
					System.out.println("la participation a ete refusée");
				} else {
					System.out.println("Vous avez choisi un choix n'existe pas");
				}
			} else {
				System.out.println("Essayer d\'ajouter un nomber");
			}

		} else {
			System.out.println("Cette participation n\'existe pas");
		}

	}

	public void sendMail(String recEmail) {

		Properties properties = new Properties();

		properties.put(ConDb.AUTH.getProps(),ConDb.AUTH.getValue());
		properties.put(ConDb.SSL.getProps(),ConDb.SSL.getValue());
		properties.put(ConDb.HOST.getProps(),ConDb.HOST.getValue());
		properties.put(ConDb.PORT.getProps(),ConDb.PORT.getValue());
		String myAccount = Credential.ACCOUNT.getAccPass();
		String password = Credential.PASSWORD.getAccPass();
		
		Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccount, password);
			}
		});
		
		try {  
		     MimeMessage message = new MimeMessage(session);  
		     message.setFrom(new InternetAddress(myAccount));  
		     message.addRecipient(Message.RecipientType.TO,new InternetAddress(recEmail));  
		     message.setSubject("YouCode Got Talent");  
		     message.setText("Bonjour , Je tiens a vous informer que vous etes accepté de participer dans la competition youcode got talents.");  

		       
		    //send the message  
		     Transport.send(message);  
		  
		   
		     } catch (MessagingException e) {e.printStackTrace();}  
		 }  
	}


