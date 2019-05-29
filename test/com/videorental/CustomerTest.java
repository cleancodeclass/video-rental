package com.videorental;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import org.junit.Test;

public class CustomerTest {
	private static final String TITLE = "TITLE_NOT_IMPORTANT";
	private static final String NAME = "NAME_NOT_IMPORTANT";
	Customer customer = new Customer(NAME);
	
	@Test
	public void returnNewCustomer() {
		assertThat(customer, is(notNullValue()));
	}
	
	@Test
	public void statementForNoRental() {
		// arrange
		
		//act
		//assert
		assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n"
				+ "Amount owed is 0.0\n"
				+ "You earned 0 frequent renter pointers"));
	}
	
	@Test
	public void statementForRegularMovieRentalForLessThan3Days() {
		
		// arrange
		customer.addRental(createRentalFor(Movie.REGULAR, 2));
		
		//act
		//assert
		assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n\t"
				+ "2.0(TITLE_NOT_IMPORTANT)\n"
				+ "Amount owed is 2.0\n"
				+ "You earned 1 frequent renter pointers"));
	}

	private Rental createRentalFor(int priceCode, int daysRented) {
		Movie movie = new Movie(TITLE, priceCode);
		Rental rental = new Rental(movie, daysRented);
		return rental;
	}
	
	@Test
	public void statementForRegularMovieRentalForMoreThan2Days() {
		
		// arrange
		customer.addRental(createRentalFor(Movie.REGULAR, 3));
		
		//act
		//assert
		assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n\t"
				+ "3.5(TITLE_NOT_IMPORTANT)\n"
				+ "Amount owed is 3.5\n"
				+ "You earned 1 frequent renter pointers"));
	}
	
	@Test
	public void statementForChildrenMovieForMoreThan3Days() {
		
		// arrange
		customer.addRental(createRentalFor(Movie.CHILDRENS, 4));
		
		//act
		//assert
		assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n\t"
				+ "3.0(TITLE_NOT_IMPORTANT)\n"
				+ "Amount owed is 3.0\n"
				+ "You earned 1 frequent renter pointers"));
	}
	@Test
	public void statementForChildrenMovieForLessThan4Days() {
		
		// arrange
		customer.addRental(createRentalFor(Movie.CHILDRENS, 3));
		
		//act
		//assert
		assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n\t"
				+ "1.5(TITLE_NOT_IMPORTANT)\n"
				+ "Amount owed is 1.5\n"
				+ "You earned 1 frequent renter pointers"));
	}
	
	@Test
	public void statementForNewReleaseMovie() {
		
		// arrange
		customer.addRental(createRentalFor(Movie.NEW_RELEASE, 1));
		
		//act
		//assert
		assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n\t"
				+ "3.0(TITLE_NOT_IMPORTANT)\n"
				+ "Amount owed is 3.0\n"
				+ "You earned 1 frequent renter pointers"));
	}
	@Test
	public void statementForNewReleaseMovieForMoreThan1Days() {
		
		// arrange
		customer.addRental(createRentalFor(Movie.NEW_RELEASE, 2));
		
		//act
		//assert
		assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n\t"
				+ "6.0(TITLE_NOT_IMPORTANT)\n"
				+ "Amount owed is 6.0\n"
				+ "You earned 2 frequent renter pointers"));
	}
	
	@Test
	public void statementForFewMovieRentals() {
		
		// arrange
		customer.addRental(createRentalFor(Movie.REGULAR, 1));
		customer.addRental(createRentalFor(Movie.NEW_RELEASE, 4));
		customer.addRental(createRentalFor(Movie.CHILDRENS, 4));
		
		//act
		//assert
		assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n\t"
				+ "2.0(TITLE_NOT_IMPORTANT)\n\t"
				+ "12.0(TITLE_NOT_IMPORTANT)\n\t"
				+ "3.0(TITLE_NOT_IMPORTANT)\n"
				+ "Amount owed is 17.0\n"
				+ "You earned 4 frequent renter pointers"));
	}
	
}
