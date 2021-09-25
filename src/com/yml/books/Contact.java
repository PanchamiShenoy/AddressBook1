package com.yml.books;

public class Contact {
	
	public String firstName,lastName,address,city,state,zip,phoneNumber,eMail;
	
	Contact(String firstName,String lastName,String address,String city,String state,String zip,String phoneNumber,String eMail){
		this.firstName=firstName;
		this.lastName=lastName;
		this.address=address;
		this.city=city;
		this.state=state;
		this.phoneNumber=phoneNumber;
		this.zip=zip;
		this.eMail=eMail;
		
		
	}

	@Override
	public String toString() {
		return "Contact [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", city=" + city
				+ ", state=" + state + ", zip=" + zip + ", phoneNumber=" + phoneNumber + ", eMail=" + eMail + "]";
	}
	

}
