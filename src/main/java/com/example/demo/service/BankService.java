package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.example.demo.dao.Account;
import com.example.demo.dao.Customer;

public interface BankService {



	Customer createCustomer(Customer Customer);

	Customer getCustomer(int CustomerId);

	List<Customer> getCustomers();

	Customer updateCustomer(int CustomerId, Customer Customers);

}
