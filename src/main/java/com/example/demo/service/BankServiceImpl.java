package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.AccountAlreadyexistException;
import com.example.demo.Exception.NotFoundException;
import com.example.demo.Repo.BankRepository;
import com.example.demo.dao.Account;
import com.example.demo.dao.Customer;


@Service
public class BankServiceImpl implements BankService{

@Autowired
BankRepository bankRepository;


@Override
public Customer createCustomer(Customer Customer) {
List<Customer> customerList1=bankRepository.findAll();
for(Customer customerList:customerList1) {String y = Customer.getEmail();
if(customerList.getEmail().equalsIgnoreCase(y))
{
	
	throw new AccountAlreadyexistException("Account with email:"+" "+Customer.getEmail()+" already present."); 
}}

Customer saveCustomer =bankRepository.save(Customer);
return saveCustomer;

}

@Override
public Customer getCustomer(int CustomerId)   {

	//Customer customer = bankRepository.findById(CustomerId).get();
		Optional<Customer> customer= bankRepository.findById(CustomerId);
		if(!customer.isPresent()) {
			throw new NotFoundException("Account with id->"+CustomerId+" not found."); //row not present
		}
	 Customer cus = customer.get();
	  if(cus==null)
	  {
			throw new NotFoundException("Account with id->"+CustomerId+" not found.");
		  
	  }
		return cus;}

@Override
public List<Customer> getCustomers() {
	  List<Customer> customers = new ArrayList<Customer>();  
	  bankRepository.findAll().forEach(customer -> customers.add(customer));  
	  System.out.println("QQQQQQQQQQQjhjhjhjhghfnghvghfg");
	  return customers;  
}

@Override
public Customer updateCustomer(int CustomerId, Customer Customers) {
	//Customer c = null;
	Optional<Customer> cl = bankRepository.findById(CustomerId);		
	if(!cl.isPresent()) {
		throw new NotFoundException("customer with id->"+CustomerId+" not found.");
	} 
	 Customer cust=cl.get();
	 if(cust==null)
	 {
		 throw new NotFoundException("customer with id->"+CustomerId+" not found.");
	 }
	 else {
	cust.setFirstName(Customers.getFirstName());
	cust.setLastName(Customers.getLastName());
	cust.setEmail(Customers.getEmail());
    Customer save = bankRepository.save(cust);
	 return save;
}} 













}