package com.customer.controller;

import java.util.*;

import org.springframework.*;


import com.customer.exception.CustomerNotFoundException;
import com.customer.model.Customer;
import com.customer.repository.CustomerRepository;

@RestController
public class Customer {
	@Autowired
	CustomerRepository customerRepository;
	@GetMapping("/customer")
	public List<Customer> getList(){
		return customerRepository.findAll();
	}
	@PostMapping("/customer")
	public Customer saveData(@RequestBody Customer customer) {
		return customerRepository.save(customer);
		
	}
	@GetMapping("/customer/{id}")
	public Customer getCustomerById(@PathVariable(value = "id") Integer id) throws CustomerNotFoundException {
		return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
	}
	@PutMapping("/customer/{id}")
	public Customer updateCustomer(@PathVariable(value = "id") Integer id,  @RequestBody Customer customerDetails)
			throws CustomerNotFoundException {

		Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));

		customer.setId(customerDetails.getId());
		customer.setName(customerDetails.getName());
		customer.setAddress(customerDetails.getAddress());
		
		Customer updatedCustomer = customerRepository.save(customer);

		return updatedCustomer;
	}
	@DeleteMapping("/customer/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Integer id) throws CustomerNotFoundException {
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));

		customerRepository.delete(customer);

		return ResponseEntity.ok().build();
	}
	

}
