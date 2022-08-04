package com.example.demo.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
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
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;



import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.example.demo.Repo.BankRepository;
import com.example.demo.dao.Account;
import com.example.demo.dao.Customer;
import com.example.demo.service.BankService;
import com.example.demo.service.BankServiceImpl;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class BankServiceTests{
    @InjectMocks
    BankServiceImpl service;
    @Mock
    private BankRepository repository; //repository=mock(BankRepository);


      @Order(1)
      @Test
    void testcreatebank() {
       Customer Bank=new Customer();
       Bank.setFirstName("Neil");
       Bank.setLastName("Cardiff");
       Bank.setEmail("pbalivada@gmail.com");
       Account savingAccountOne=new Account();
       savingAccountOne.setAccountType("Savings");
       savingAccountOne.setBalance(9000000);
       Set<Account> s=new HashSet<Account>();
       s.add(savingAccountOne);
       Bank.setAccounts(s);
      Customer mockBank=new Customer();
      mockBank.setId(1);
      mockBank.setFirstName("Tim");
      mockBank.setLastName("London");
      mockBank.setEmail("sarah@gmail.com");
      Account savingAccountTwo=new Account();
      savingAccountTwo.setAccountType("Savings");
      savingAccountTwo.setBalance(90000);
      Set<Account> s1=new HashSet<Account>();
      s1.add(savingAccountOne);
      mockBank.setAccounts(s1);
     when(repository.save(any(Customer.class))).thenReturn(mockBank) .thenThrow(NullPointerException.class);
     Customer emp=service.createCustomer(Bank);
     System.out.println(emp.toString());
//System.out.println(Bank.toString());
     verify(repository,atLeastOnce()).save(Bank);
     assertEquals(1,emp.getId());
}

    @Test
    @Order(2)
    void testgetbank() {
    Set<Account> e=new HashSet<Account>();
    Account acc=new Account("savings",900000);
    e.add(acc);
    Optional<Customer> Banks = Optional.of(new Customer(1, "Tim","Cardiff","Tim@gmail.com", e));
    when(repository.findById(any(Integer.class))).thenReturn(Banks);
    Customer Bank = service.getCustomer(1);
    Customer savedBank=Banks.get();
     System.out.println(savedBank.getId()+"\t"+savedBank.getFirstName());
     assertEquals(1,Bank.getId());


}
    @Test
    @Order(3)
    void testgetallbanks() {
    List<Customer> BanksList=new ArrayList<Customer>();
    Set<Account> e=new HashSet<Account>();
     Account acc=new Account("savings",900000);
     e.add(acc);
     BanksList.add(new Customer(1,"Vinay","Bangalore", "vinay@gmail.com", e));
     BanksList.add(new Customer(2,"Vijay","Chennai", "vijay#gmail.com", e));
    when(repository.findAll()).thenReturn(BanksList);

    List<Customer> Banks = service.getCustomers();

      for(Customer Bank: Banks) {
       System.out.println(Bank.getId()+"\t"+Bank.getFirstName());
       }
      assertEquals(2,Banks.get(1).getId());



}

      @Test
      @Order(4)
      public void testupdateCustomerById() {
      Customer mockcust=new Customer();
      mockcust.setId(2);
      mockcust.setFirstName("tim");
      mockcust.setLastName("cardiff");
      mockcust.setEmail("tim7@gmail.com");
      Set<Account> acctset=new HashSet<Account>();
      Account account=new Account("Savings",100000);
      acctset.add(account);
      mockcust.setAccounts(acctset);
      Customer cust=new Customer();
      cust.setId(1);
      cust.setFirstName("george");
      cust.setLastName("George");
      cust.setEmail("12345677@gmail.com");
      Set<Account> acctset1=new HashSet<Account>();
      Account account1=new Account("Savings",100000);
      acctset1.add(account1);
      cust.setAccounts(acctset1);
      Optional<Customer> customers=Optional.of(new Customer(1,"tim","cardiff","tim7@gmail.com",acctset1));
      when(repository.findById(1)).thenReturn(customers);
      Customer cus=customers.get();
      cus.setId(mockcust.getId());
      cus.setFirstName(mockcust.getFirstName());
      cus.setLastName(mockcust.getLastName());
      cus.setEmail(mockcust.getEmail());
      cus.setAccounts(mockcust.getAccounts());
      when(repository.save(cus)).thenReturn(cus);
      Customer c=service.updateCustomer(1, cust);
      verify(repository,atLeastOnce()).findById(1);
      assertEquals(2,c.getId());
}



}