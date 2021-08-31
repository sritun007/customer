package com.customer.exception;

public class CustomerNotFoundException extends Exception {
	private Integer id;
	public CustomerNotFoundException(Integer id) {
	        super(String.format("Customer is not found with id : '%s'", id));
	        }
	}
