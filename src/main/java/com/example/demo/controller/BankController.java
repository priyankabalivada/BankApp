package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.NotFoundException;
import com.example.demo.Repo.BankRepository;
import com.example.demo.dao.Customer;
import com.example.demo.service.BankService;
@RestController
public class BankController {

	
	@Autowired
	BankService bankService;
	

	
	@PostMapping("/api/customers")
    public ResponseEntity<Customer> CreateCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(bankService.createCustomer(customer),HttpStatus.CREATED);

    }
	
	@GetMapping("/customers/{CustomerId}")
	public  ResponseEntity<Customer> getCustomer (@PathVariable int CustomerId) throws Exception { 
		
		Customer customer=bankService.getCustomer(CustomerId);  
		System.out.println(customer+"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<InSiDE ConTROllER<<<<<<<");
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);			 
	}
	
	
	@GetMapping ("/api/customers")
	public ResponseEntity<List<Customer>> getCustomers() { 
		List<Customer> Customers=bankService.getCustomers();
		return new ResponseEntity<List<Customer>>(Customers,HttpStatus.OK);

	}
	@PutMapping("/api/{CustomerId}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable int CustomerId,@RequestBody Customer customer) {
		Customer x=bankService.updateCustomer(CustomerId, customer);
		return new ResponseEntity<Customer>(HttpStatus.OK);
}
}
