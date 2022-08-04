package com.example.demo.Service;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.demo.Exception.*;
import com.example.demo.Repo.AccountRepository;
import com.example.demo.Repo.BankRepository;
import com.example.demo.dao.Account;
import com.example.demo.dao.Customer;
import com.example.demo.dao.Transfer;
import com.example.demo.service.AccountService;
import com.example.demo.service.AccountServiceImpl;
import com.example.demo.service.BankServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class TestAccountService {

	
	@InjectMocks
	AccountServiceImpl service;
	
	@Mock
	AccountRepository repository;
	
	

	
	@Mock
	BankRepository repo1;
	
	
	
	
	@Order(1)
	@Test
	public void testcreateaccount()
	{
		Account mockacc=new Account();
		mockacc.setAccountNumber(1);
	   mockacc.setAccountType("savings");
	   mockacc.setBalance(10000);
	   Customer cus=new Customer();
	   cus.setId(1);
	   cus.setFirstName("Hima");
	   cus.setLastName("Himaja");
	   cus.setEmail("123@gmail.com");
	   Set<Account> a=new HashSet<Account>();
	   Account acct=new Account(1,"savings",10000);
	   a.add(acct);
	   
	   when(repo1.existsById(1)).thenReturn(true);
	   when(repository.save(any(Account.class))).thenReturn(mockacc);
	   Account a1=service.createAccount(1, acct);
	   verify(repository,atLeastOnce()).save(acct);
	   	assertEquals(1,a1.getAccountNumber());	
	}
	@Test
    @Order(2)
    public void testingtransfer() {
       
        Transfer transfer=new Transfer();
        transfer.setFromAccount(1);
        transfer.setToAccount(2);
        transfer.setAmount(5000);
        Optional<Account> acct1=Optional.of(new Account(2,"current",80000));
        Optional<Account> acct2=Optional.of(new Account(1,"current",80000));
        when(repository.findById(transfer.getFromAccount())).thenReturn(acct1);
        when(repository.findById(transfer.getToAccount())).thenReturn(acct2);
        Account account2=acct2.get();
        Account account=service.transferMoney(transfer.getFromAccount(),transfer.getToAccount(),transfer.getAmount());
        assertEquals(85000,account2.getBalance());
    }
	
	@Test
	@Order(3)
	public void testtransferwithsameaccoutnumber() throws SameAccountexception  {
		try {
		
	Transfer transfer=new Transfer();
	transfer.setFromAccount(1);
	transfer.setToAccount(1);
   transfer.setAmount(5000);
   Optional<Account> acct1=Optional.of(new Account(1,"savings",80000));
	Optional<Account> acct2=Optional.of(new Account(1,"savings",80000));
   when(repository.findById(transfer.getFromAccount())).thenReturn(acct1);
   when(repository.findById(transfer.getToAccount())).thenReturn(acct2);
	Account account1=acct1.get();
	Account account2=acct2.get();
	when(service.transferMoney(transfer.getFromAccount(),transfer.getToAccount(),transfer.getAmount())).thenThrow(SameAccountexception.class);

            }
catch(Exception e) {
    assertTrue(e instanceof SameAccountexception);
}                                                                
}
	@Test
    @Order(4)
    public void testtransferwithzeroamount()throws NegativeBalanceException {
         try {
        Transfer transfer=new Transfer();
        transfer.setFromAccount(1);
        transfer.setToAccount(2);
        transfer.setAmount(0);
        Optional<Account> acct1=Optional.of(new Account(1,"savings",80000));
        Optional<Account> acct2=Optional.of(new Account(2,"savings",80000));
        when(repository.findById(transfer.getFromAccount())).thenReturn(acct1);
        when(repository.findById(transfer.getToAccount())).thenReturn(acct2);
        Account account1=acct1.get();
        Account account2=acct2.get();
        when(service.transferMoney(transfer.getFromAccount(),transfer.getToAccount(),transfer.getAmount())).thenThrow(NegativeBalanceException.class);
         }
        catch(Exception e) {
            assertTrue(e instanceof NegativeBalanceException);
        }
    }	
	@Test
    @Order(5)
    public void testtransferwithfromaccountbalancelessthanamount()throws BalanceExceedLimitException {
        try {
        Transfer transfer=new Transfer();
        transfer.setFromAccount(1);
        transfer.setToAccount(2);
        transfer.setAmount(5000);
        Optional<Account> acct1=Optional.of(new Account(1,"savings",1));
        Optional<Account> acct2=Optional.of(new Account(2,"savings",80000));
        when(repository.findById(transfer.getFromAccount())).thenReturn(acct1);
        when(repository.findById(transfer.getToAccount())).thenReturn(acct2);
        Account account1=acct1.get();
        Account account2=acct2.get();
        when(service.transferMoney(transfer.getFromAccount(),transfer.getToAccount(),transfer.getAmount())).thenThrow(BalanceExceedLimitException.class);
        }
        catch(Exception e) {
            assertTrue(e instanceof BalanceExceedLimitException);
        }
    }
	
	
	
	@Test
    @Order(6)
    public void testgetaccount() {
        List<Account> accountList=new ArrayList<Account>();
        Account acct=new Account();
        acct.setAccountNumber(1);
        acct.setAccountType("savings");
        acct.setBalance(500000);
        accountList.add(acct);
        when(repository.findAll()).thenReturn(accountList);
        List<Account> acct1=service.getAccounts();
        assertEquals(1,acct1.get(0).getAccountNumber());
    }
    @Test
    @Order(7)
    public void testGetAccountById() {
        Optional<Account> account=Optional.of(new Account(2,"Saving",80000));
        when(repository.findById(1)).thenReturn(account);
        Account acct=service.getAccount(1);
        assertEquals(2,acct.getAccountNumber());
    }
   
}
	
	

