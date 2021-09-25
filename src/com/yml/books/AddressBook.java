package com.yml.books;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddressBook {
	String addressBookName;
	static Scanner sc = new Scanner(System.in);

	AddressBook(String addressBookName) {
		this.addressBookName = addressBookName;
	}
	static Map<String, Contact> addressBook = new HashMap<>();
/*
 * method to add contact to adressBook
 */
	public static void addContact() {

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
		String name = fname + " " + lname;
		Contact c = addressBook.get(name);
		if (c != null) {
			System.out.println("There is already a person with this name ");
		} else {
			addressBook.put(fname + " " + lname, contact);
		}

	}
/*
 * method to edit contact in addressBook
 */
	public static void editPerson() {

		System.out.println("enter the first name");
		String fname = sc.nextLine();
		System.out.println("enter the last name");
		String lname = sc.nextLine();

		String name = fname + " " + lname;

		Contact c = addressBook.get(name);
		if (c == null) {
			System.out.println("Person of that name not exits in this book");
		} else {
			int choice;

			Scanner r = new Scanner(System.in);
			while (true) {
				System.out.println("What do you wanna edit");
				System.out.println(
						"1 First Name\n2 Last Name\n3 Address\n4 City\n5 State\n6Zip\n7 Phone number\n8Email\n9Go back");
				choice = r.nextInt();
				switch (choice) {
				case 1:
					System.out.println("enter the first name");
					String firstName = sc.nextLine();
					// person.firstName = firstName;
					c.firstName = firstName;
					break;
				case 2:
					System.out.println("enter the last name");
					String lastName = sc.nextLine();
					c.lastName = lastName;
					break;
				case 3:
					System.out.println("enter the address");
					String address = sc.nextLine();
					c.address = address;
					break;
				case 4:
					System.out.println("enter the city");
					String city = sc.nextLine();
					c.city = city;
					break;
				case 5:
					System.out.println("enter state");
					String state = sc.nextLine();
					c.state = state;
					break;
				case 6:
					System.out.println("enter the zip code");
					String zip = sc.nextLine();
					c.zip = zip;
					break;
				case 7:
					System.out.println("enter phone number");
					String phone = sc.nextLine();
					c.phoneNumber = phone;
					break;
				case 8:
					System.out.println("enter email");
					String email = sc.nextLine();
					c.eMail = email;
					break;
				case 9:
					return;
				}
			}

		}

	}
/*
 * method to delete person from addressBook
 */
	public static void deleteperson() {

		System.out.println("enter the first name");
		String fname = sc.nextLine();
		System.out.println("enter the last name");
		String lname = sc.nextLine();

		String name = fname + " " + lname;

		Contact c = addressBook.get(name);
		if (c == null) {
			System.out.println("Person of that name not exits in this book");

		} else {
			addressBook.remove(name);
		}

	}
/*
 * method to print addressBook
 */
	public void print() {

		for (Contact c : addressBook.values()) {
			System.out.println(c);
		}

	}

}