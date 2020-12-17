package com.got_talent.app;

import java.sql.SQLException;
import java.util.Scanner;

import com.got_talent.controllers.AdminController;
import com.got_talent.controllers.ParticipationController;
import com.got_talent.controllers.UserController;

public class Test {

	public static void main(String[] args) {
		

		Scanner input = new Scanner(System.in);
		UserController user = new UserController();
		AdminController admin = new AdminController();
		ParticipationController participation = new ParticipationController();

		while (true) {

			System.out.println("Main Menu");
			System.out.println("1. I'm an User");
			System.out.println("2. I'm an Admin");

			int choice = input.nextInt();

			switch (choice) {
			case 1:

				int choice1 = userMenu();
				switch (choice1) {
				case 1:
					try {
						user.addUser();
					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}
					break;
				case 2:
					try {
						user.updateUser();
					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}
					break;
				case 3:
					try {
						participation.addParticipation();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				case 4:
					break;
				}
				break;

			case 2:
				boolean a = true;
				System.out.println("Email :");
				String email = input.next();
				System.out.println("Password :");
				String pswrd = input.next();
				
				
				if (email.equals("ahmed.mahmoud.admin@gmail.com") && pswrd.equals("0000")) {
					try {
						admin.adminConnect();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					while (a) {
						int choice2 = adminMenu();
						switch (choice2) {
						case 1:
							try {
								admin.findAllUsers();
							} catch (ClassNotFoundException | SQLException e) {
								e.printStackTrace();
							}
							break;
						case 2:
							try {
								user.findUserById().toString();
							} catch (ClassNotFoundException | SQLException e) {
								e.printStackTrace();
							}
							break;
						case 3:
							try {
								admin.findParticipations();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							break;
						case 4:
							admin.findParticipationByUserEmail();
							break;
						case 5:
							try {
								admin.validateParticipation();
							} catch (SQLException e) {
								e.printStackTrace();
							}
							break;
						case 6:
							try {
								admin.adminLoggedOut();
							} catch (SQLException e) {
								e.printStackTrace();
							}
							a = false;
							break;
						}

					}
				} else {
					System.out.println("Wrong Cridentials !");
				}

			}

		}
	}
	public static int userMenu() {

		Scanner liSele = new Scanner(System.in);

		System.out.println("Choose an action:");
		System.out.println("*****************************\n");
		System.out.println("1 - Register");
		System.out.println("2 - Update your information");
		System.out.println("3 - Participate to Youcode Got Talent");
		System.out.println("4 - Back");

		return liSele.nextInt();

	}

	public static int adminMenu() {

		Scanner liSele = new Scanner(System.in);

		System.out.println("Choose an action:");
		System.out.println("*****************************\n");
		System.out.println("1 - Find all users");
		System.out.println("2 - Find user by  id");
		System.out.println("3 - Find all participations");
		System.out.println("4 - Find Participation by email");
		System.out.println("5 - Validate Participation ");
		System.out.println("6 - LogOut");

		return liSele.nextInt();

	}

}
