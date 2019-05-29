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
		Movie movie = new Movie(TITLE, Movie.CHILDRENS);
		int daysRented = 4;
		Rental rental = new Rental(movie, daysRented);
		customer.addRental(rental);
		
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
		Movie movie = new Movie(TITLE, Movie.CHILDRENS);
		int daysRented = 3;
		Rental rental = new Rental(movie, daysRented);
		customer.addRental(rental);
		
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
		Movie movie = new Movie(TITLE, Movie.NEW_RELEASE);
		int daysRented = 1;
		Rental rental = new Rental(movie, daysRented);
		customer.addRental(rental);
		
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
		Movie movie = new Movie(TITLE, Movie.NEW_RELEASE);
		int daysRented = 2;
		Rental rental = new Rental(movie, daysRented);
		customer.addRental(rental);
		
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
		Movie newReleaseMovie = new Movie(TITLE, Movie.NEW_RELEASE);
		Movie regularMovie = new Movie(TITLE, Movie.REGULAR);
		Movie childrensMovie = new Movie(TITLE, Movie.CHILDRENS);

		customer.addRental(new Rental(regularMovie, 1));
		customer.addRental(new Rental(newReleaseMovie, 4));
		customer.addRental(new Rental(childrensMovie, 4));
		
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
