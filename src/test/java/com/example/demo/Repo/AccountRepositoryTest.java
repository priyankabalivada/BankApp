package com.example.demo.Repo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import com.example.demo.dao.Account;
import com.example.demo.dao.Customer;
import com.example.demo.dao.Transfer;

@SpringBootTest
class AccountRepositoryTest {

	@Autowired
	AccountRepository b;
	
	
	@Test
	void testsave() {
		Account a=new Account("saving",70000);
		b.save(a);
		Account a1=new Account("current",70000);
		b.save(a1);
		
	}
	
	@Test
	void getbyid() {
		Account a=new Account("saving",70000);
		b.save(a);
		b.findById(a.getAccountNumber());
	 assertEquals("saving",a.getAccountType());
	}
	@Test
	void getAll() {
	    List<Account> a2=b.findAll();
		for(Account t:a2)
		{
			System.out.println(t);
		}
	}
	
	 @Transactional
	    @Test
	    @Order(3)
	    void testTransfer() {
	        System.out.println("In test_update_customer");
	        Account firstCustomer=new Account();
	        firstCustomer.setAccountNumber(1);
	        firstCustomer.setAccountType("Nobita");
	        firstCustomer.setBalance(9000);
	        Account firstCustomer1=new Account();
	        firstCustomer1.setAccountNumber(2);
	        firstCustomer1.setAccountType("Nobi");
	        firstCustomer1.setBalance(9000);
	        b.save(firstCustomer);
	        b.save(firstCustomer1);

	        Transfer t=new Transfer();
	        t.setFromAccount(1);
	        t.setToAccount(2);
	        t.setAmount(100);
	        int x=t.getFromAccount();
	        if((x==firstCustomer.getAccountNumber())&&(firstCustomer.getBalance()>t.getAmount()))
	        		{
	        	        int balance1=firstCustomer.getBalance()-t.getAmount();
	        	        firstCustomer.setBalance(balance1);
	        	        b.save(firstCustomer);
	        	        int balance2=firstCustomer1.getBalance()+t.getAmount();
	        	        firstCustomer1.setBalance(balance2);
	        	        b.save(firstCustomer1);
	        	        
	        		}
	        if((x==firstCustomer1.getAccountNumber())&&(firstCustomer1.getBalance()>t.getAmount()))
    		{
    	        int balance1=firstCustomer1.getBalance()-t.getAmount();
    	        firstCustomer1.setBalance(balance1);
    	        b.save(firstCustomer1);
    	        int balance2=firstCustomer.getBalance()+t.getAmount();
    	        firstCustomer.setBalance(balance2);
    	        b.save(firstCustomer1);
    	        
    		}
	        assertEquals(firstCustomer1.getBalance(),9100);
	        
	        System.out.println("cust1 balance"+firstCustomer.getBalance());
	        

	    }
	
	    @Test
	    @Order(6)
	    void testfindByAccountTypeAndBalance() {
	        Account account=b.findByAccountTypeAndBalance("Current", 100000);
	        System.out.println("Account Details:"+account);
	    }
	    @Test
	    @Order(7)
	    void testfindByAccountTypeOrBalance() {
	        List<Account> accounts=b.findByAccountTypeOrBalance("Current", 100000);
	      
	        for(Account account: accounts) {
	            System.out.println("Account Details"+account);
	        }

	    }
	   
	    
	    
	    

}
