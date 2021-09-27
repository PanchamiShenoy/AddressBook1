package com.yml.books;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

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

	/*
	 * method to sort contact based on names
	 */
	public void sort() {

		Map<String, Contact> sortedContact = addressBook.entrySet().stream().sorted(Map.Entry.comparingByKey())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue,
						LinkedHashMap::new));

		for (Map.Entry<String, Contact> entry : sortedContact.entrySet()) {

			System.out.println(entry.getValue());
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
	 * Method to sort contacts based on state
	 */
	public void sortZip() {
		Set<Map.Entry<String, Contact>> entries = addressBook.entrySet();
		Stream<Map.Entry<String, Contact>> entriesStream = entries.stream();

		Set<String> keySet = addressBook.keySet();
		Collection<Contact> values = addressBook.values();

		Stream<Contact> valuesStream = values.stream();
		Stream<String> keysStream = keySet.stream();

		valuesStream.sorted((p1, p2) -> p1.zip.compareTo(p2.zip)).forEach(System.out::println);

	}

	/**
	 * Method to sort contacts based on state
	 */
	public void sortCity() {
		Set<Map.Entry<String, Contact>> entries = addressBook.entrySet();
		Stream<Map.Entry<String, Contact>> entriesStream = entries.stream();

		Set<String> keySet = addressBook.keySet();
		Collection<Contact> values = addressBook.values();

		Stream<Contact> valuesStream = values.stream();
		Stream<String> keysStream = keySet.stream();

		valuesStream.sorted((p1, p2) -> p1.city.compareTo(p2.city)).forEach(System.out::println);

	}

	/**
	 * Method to sort contacts based on state
	 */
	public void sortState() {
		Set<Map.Entry<String, Contact>> entries = addressBook.entrySet();
		Stream<Map.Entry<String, Contact>> entriesStream = entries.stream();

		Set<String> keySet = addressBook.keySet();
		Collection<Contact> values = addressBook.values();

		Stream<Contact> valuesStream = values.stream();
		Stream<String> keysStream = keySet.stream();

		valuesStream.sorted((p1, p2) -> p1.state.compareTo(p2.state)).forEach(System.out::println);
	}

	/*
	 * method to print addressBook
	 */
	public void print() {

		for (Map.Entry<String, Contact> entry : addressBook.entrySet())
			System.out.println(entry.getValue());

	}

	/**
	 * Adds the contacts stored in a file to contacts
	 */
	public void addContactFromFile(BufferedReader br) {
		Contact contact;
		String row;

		try {
			while ((row = br.readLine()) != null) {
				String[] data = row.split(",");
				contact = new Contact(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7]);
				String name = data[0] + " " + data[1];
				Contact c = addressBook.get(name);

				if (c == null) {
					addressBook.put(name, contact);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * writes the contacts in addressbook to file
	 */
	public void writeContact(String fileName) {

		try {

			BufferedWriter f_writer = new BufferedWriter(new FileWriter(fileName, false));
			for (Contact c : addressBook.values()) {
				f_writer.write(String.join(",", c.firstName, c.lastName, c.address, c.city, c.state, c.zip,
						c.phoneNumber, c.eMail));
				f_writer.write("\n");
			}
			f_writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Reads the contact from csv file
	 */
	public void addContactCsv(String file) {
		try {

			// Create an object of filereader class
			// with CSV file as a parameter.
			FileReader filereader = new FileReader(file);

			// create csvReader object
			// and skip first Line
			CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
			List<String[]> allData = csvReader.readAll();
			Contact contact;

			// print Data
			for (String[] row : allData) {
				contact = new Contact(row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7]);
				String name = row[0] + " " + row[1];
				Contact c = addressBook.get(name);

				if (c == null) {
					addressBook.put(name, contact);
				}
			}

			System.out.println();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Writes the contacts in addresBook to csv file
	 */
	public void writeContactCsv(String filePath) {
		File file = new File(filePath);
		try {
			// create FileWriter object with file as parameter
			FileWriter outputfile = new FileWriter(file);

			CSVWriter writer = new CSVWriter(outputfile, ',', CSVWriter.NO_QUOTE_CHARACTER,
					CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
			// adding header to csv
			String[] header = { "FistName", "Lastname", "Address", "City", "State", "Zip", "Phone Number", "Email" };
			writer.writeNext(header);

			for (Contact c : addressBook.values()) {
				String[] data1 = { c.firstName, c.lastName, c.address, c.city, c.state, c.zip, c.phoneNumber, c.eMail };
				writer.writeNext(data1);
			}

			// closing writer connection
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}