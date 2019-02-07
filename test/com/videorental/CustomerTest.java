package com.videorental;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import org.junit.Test;

public class CustomerTest {
	
	@Test
	public void returnNewCustomer()	{
		Customer customer = new Customer("NAME_NOT_IMPORTANT");
	    
	    assertThat(customer, is(notNullValue()));
	}
	
	@Test 
	public void statmentForNoRental() {

	    // arrange
	    Customer customer = new Customer("NAME_NOT_IMPORTANT");

	    // act
	    String statement = customer.statement();

	    // assert
	    assertThat(statement, is("Rental Record for NAME_NOT_IMPORTANT\n"
	        + "Amount owed is 0.0\n"
	        + "You earned 0 frequent renter pointers"));
	}
	
	@Test 
	public void statementForRegularMovieRentalForLessThan3Days() {

	    // arrange
	    Customer customer = new Customer("NAME_NOT_IMPORTANT");
	    Movie movie = new Movie("TITLE_NOT_IMPORTANT", Movie.REGULAR);
	    int daysRented = 2;
	    Rental rental = new Rental(movie, daysRented);
	    customer.addRental(rental);

	    // act
	    String statement = customer.statement();

	    // assert
	    assertThat(statement, is("Rental Record for NAME_NOT_IMPORTANT\n" 
	        + "\t2.0(TITLE_NOT_IMPORTANT)\n" 
	        + "Amount owed is 2.0\n"
	        + "You earned 1 frequent renter pointers"));
	} 


	
}
