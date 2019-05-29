package com.videorental;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

class Customer {
	private String name;
	private List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public String getName() {
		return name;
	};

	public String statement() {
		Iterator<Rental> iterator = rentals.iterator();
		String result = "Rental Record for " + getName() + "\n";

		while ( iterator.hasNext() ) {
			Rental each = (Rental) iterator.next();
			// determine amounts for each line

			// add frequent renter points
			// show figures
			result += "\t" +  each.getCharge() + "(" + each.getMovie().getTitle() + ")" + "\n";

		}
		result += "Amount owed is " + getTotalCharge() + "\n";
		result += "You earned " + getFrequentRentalPoints() + " frequent renter pointers";

		return result;
	}

	private int getFrequentRentalPoints() {
		int frequentRenterPoints = 0;
		for (Rental rental : rentals) {
			frequentRenterPoints ++;
			if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE) && rental.getDaysRented() > 1)
				frequentRenterPoints++;
		}
		return frequentRenterPoints;
	}

	private double getTotalCharge() {
		double totalAmount = 0;
		for (Rental rental : rentals) {
			totalAmount  += rental.getCharge();
		}
		return totalAmount;
	}
}
