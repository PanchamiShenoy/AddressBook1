package com.yml.books;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBookMain {
	static List<Contact> list = new ArrayList<>();

	public static void main(String[] args) {
		final int EXIT = 4;

		int choice = 0;
		while (choice != EXIT) {

			System.out.println("1 : Add Contact\n2 : Edit Contact\n3 : Display Contact\n" + EXIT + " to exit");
			Scanner r = new Scanner(System.in);
			Scanner sc = new Scanner(System.in);
			choice = r.nextInt();

			switch (choice) {
			case 1:
				addContact();
				break;
			case 2:
				System.out.println("enter the first name");
				String fname = sc.nextLine();
				editPerson(fname);
				break;
			case 3:
				displayContact();
				break;

			}

		}

	}
	private static void addContact() {

		Scanner sc = new Scanner(System.in);
		System.out.println("enter the first name");
		String fname = sc.nextLine();
		System.out.println("enter the last name");
		String lname = sc.nextLine();
		System.out.println("enter the addres");
		String address = sc.nextLine();
		System.out.println("enter the city");
		String city = sc.nextLine();

		System.out.println("enter the state");
		String state = sc.nextLine();
		System.out.println("enter the zip code");
		String zip = sc.nextLine();
		System.out.println("enter the phone Number");
		String phone = sc.nextLine();
		System.out.println("enter the email Id ");
		String email = sc.nextLine();
		Contact contact = new Contact(fname, lname, address, city, state, zip, phone, email);
		list.add(contact);

	}

	private static void displayContact() {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

	}

	private static Contact getPerson(String fname) {
		for (Contact item : list) {
			if (item.firstName.equals(fname)) {
				return item;
			}

		}
		return null;

	}

	private static void editPerson(String fname) {

		Contact person = getPerson(fname);
		if (person == null) {
			System.out.println("No contact found of that name");
		} else {
			int choice;

			Scanner r = new Scanner(System.in);
			Scanner sc = new Scanner(System.in);

			while (true) {
				System.out.println("What do you wanna edit");
				System.out.println(
						"1 First Name\n2 Last Name\n3 Address\n4 City\n5 State\n6Zip\n7 Phone number\n8Email\n9 Exit");
				choice = r.nextInt();
				switch (choice) {
				case 1:
					System.out.println("enter the first name");
					String firstName = sc.nextLine();
					person.firstName = firstName;
					break;
				case 2:
					System.out.println("enter the last name");
					String lastName = sc.nextLine();
					person.lastName = lastName;
					break;
				case 3:
					System.out.println("enter the address");
					String address = sc.nextLine();
					person.address = address;
					break;
				case 4:
					System.out.println("enter the city");
					String city = sc.nextLine();
					person.city = city;
					break;
				case 5:
					System.out.println("enter state");
					String state = sc.nextLine();
					person.state = state;
					break;
				case 6:
					System.out.println("enter the zip code");
					String zip = sc.nextLine();
					person.zip = zip;
					break;
				case 7:
					System.out.println("enter phone number");
					String phone = sc.nextLine();
					person.phoneNumber = phone;
					break;
				case 8:
					System.out.println("enter email");
					String email = sc.nextLine();
					person.eMail = email;
					break;
				case 9:
					return;
				}
			}
		}

	}

	
}