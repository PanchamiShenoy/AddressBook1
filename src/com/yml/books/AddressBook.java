package com.yml.books;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class AddressBook {
	String addressBookName;
	static Scanner sc = new Scanner(System.in);

	AddressBook(String addressBookName) {
		this.addressBookName = addressBookName;
	}

	Map<String, Contact> addressBook = new HashMap<>();

	/*
	 * method to add contact to adressBook
	 */
	public void addContact() {

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
		Set<String> keyset = addressBook.keySet();
		Supplier<Stream<String>> streamSupplier = () -> keyset.stream();
		Optional<String> result1 = streamSupplier.get().findAny();
		if (result1.isEmpty()) {
			System.out.println("Adding details");
			addressBook.put(fname + " " + lname, contact);
		} else {
			if (streamSupplier.get().anyMatch(x -> x.equals(name))) {
				System.out.println("There is already a person with this name ");
			} else {
				System.out.println("Adding details");
				addressBook.put(fname + " " + lname, contact);
			}
		}

	}

	/*
	 * method to search contact based on city or state
	 */
	public int searchContact(String place) {
		Map<String, Contact> statesMap = new HashMap<>();
		Map<String, Contact> cityMap = new HashMap<>();

		Set<Map.Entry<String, Contact>> entries = addressBook.entrySet();
		Stream<Map.Entry<String, Contact>> entriesStream = entries.stream();

		Set<String> keySet = addressBook.keySet();
		Collection<Contact> values = addressBook.values();

		Stream<Contact> valuesStream = values.stream();
		Stream<String> keysStream = keySet.stream();

		valuesStream.forEach((x) -> {
			if (x.state.equals(place)) {
				statesMap.put(x.state, x);
			} else if (x.city.equals(place)) {
				cityMap.put(x.city, x);
			}
		});

		for (Map.Entry<String, Contact> entry : statesMap.entrySet())
			System.out.println(entry.getValue());

		for (Map.Entry<String, Contact> entry : cityMap.entrySet())
			System.out.println(entry.getValue());
		
		return statesMap.size() + cityMap.size();

	}

	/*
	 * method to edit contact in addressBook
	 */
	public void editPerson() {

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

	@Override
	public int hashCode() {
		return Objects.hash(addressBookName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressBook other = (AddressBook) obj;
		return Objects.equals(addressBookName, other.addressBookName);
	}

	/*
	 * method to delete person from addressBook
	 */
	public void deleteperson() {

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

		for (Map.Entry<String, Contact> entry : addressBook.entrySet())
			System.out.println(entry.getValue());

	}

}