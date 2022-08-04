package com.example.demo.Repo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import com.example.demo.dao.Account;
import com.example.demo.dao.Customer;
@SpringBootTest
class BankRepositoryTest {
  @Autowired
  BankRepository repository;
	
	
	 @Test
	    @Order(1)
	    void testSaveCustomer() {
	        Customer firstCustomer=new Customer();
	        firstCustomer.setFirstName("Nobi");
	        firstCustomer.setLastName("Nobita");
	        firstCustomer.setEmail("NobiNobita@gmail.com");

	        Customer secondCustomer=new Customer();
	        secondCustomer.setFirstName("dore");
	        secondCustomer.setLastName("doremon");
	        secondCustomer.setEmail("doredoremon@gmail.com");

	        Account savingAccountOne=new Account();
	        savingAccountOne.setAccountType("Savings");
	        savingAccountOne.setBalance(9000000);

	        Account savingAccountTwo=new Account();
	        savingAccountTwo.setAccountType("Savings");
	        savingAccountTwo.setBalance(700000);

	        Account currentAccountOne=new Account();
	        currentAccountOne.setAccountType("Current");
	        currentAccountOne.setBalance(100000);

	        Account currentAccountTwo=new Account();
	        currentAccountTwo.setAccountType("Current");
	        currentAccountTwo.setBalance(250000);

	        Set<Account> accountsForCustomerOne=new HashSet<Account>();
	        accountsForCustomerOne.add(savingAccountOne);
	        accountsForCustomerOne.add(currentAccountOne);
	        firstCustomer.setAccounts(accountsForCustomerOne);
	        Set<Account> accountsForCustomerTwo=new HashSet<Account>();
	        accountsForCustomerTwo.add(savingAccountTwo);
	        accountsForCustomerTwo.add(currentAccountTwo);
	        secondCustomer.setAccounts(accountsForCustomerTwo);

	        Set<Customer> customers=new HashSet<Customer>();
	        customers.add(firstCustomer);
	        repository.save(firstCustomer);
	        customers=null;
	        customers=new HashSet<Customer>();
	        customers.add(secondCustomer);
	        repository.save(secondCustomer);
	    }
	    @Transactional
	    @Test
	    @Order(2)
	    void test_getCustomer() {
	        System.out.println("In test_getCustomer ");
	        Iterable<Customer> customers=repository.findAll();
	        Iterator<Customer> itr=customers.iterator();
	        while(itr.hasNext()) {
	            Customer customer=itr.next();
	            System.out.println(customer);
	            Set<Account> accounts=customer.getAccounts();
	            System.out.println("Account Details");
	            for(Account account: accounts) {
	                System.out.println(account);
	            }

	        }

	    }
	    
	    
	    @Transactional
	    @Test
	    @Order(3)
	    void test_Update() {
	        System.out.println("In test_update_customer");
	        Customer firstCustomer=new Customer();
	        firstCustomer.setFirstName("Nobi");
	        firstCustomer.setLastName("Nobita");
	        firstCustomer.setEmail("NobiNobita@gmail.com");

	        Account savingAccountOne=new Account();
	        savingAccountOne.setAccountType("Savings");
	        savingAccountOne.setBalance(9000000);
            
	        
	        Set<Account> accountsForCustomerOne=new HashSet<Account>();
	        accountsForCustomerOne.add(savingAccountOne);
	        
	        
	        Set<Customer> customers=new HashSet<Customer>();
	        customers.add(firstCustomer);
	        repository.save(firstCustomer);
	        
	        Optional<Customer> cust=repository.findById(firstCustomer.getId());
			Customer customer = cust.get();
			customer.setLastName("sweety");
			customer.setFirstName("swe");
			customer.setEmail("sweesweety@gmail.com");
			Account updatedAcc=new Account();
			updatedAcc.setAccountType("current");
			updatedAcc.setBalance(100000);
			 Set<Account> accountCustomerOne=new HashSet<Account>();
		        accountCustomerOne.add(updatedAcc);
		        Set<Customer> customers1=new HashSet<Customer>();
		        customers1.add(customer);
		        repository.save(customer);
	        
	        

	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    

}
