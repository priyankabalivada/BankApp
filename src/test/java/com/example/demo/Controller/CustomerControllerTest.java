package com.example.demo.Controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;



import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.example.demo.controller.BankController;
import com.example.demo.dao.Account;
import com.example.demo.dao.Customer;
import com.example.demo.service.BankService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class) //@RunsWith(SpringRunner.class)
@WebMvcTest(BankController.class)



@TestMethodOrder(OrderAnnotation.class)
class CustomerControllerTest {
@Autowired
private MockMvc mockMvc;
@MockBean
private BankService service;



    @Test
    @Order(1)
   void getCustomers() throws Exception {

          RequestBuilder request;
          Set<Account> e=new HashSet<>();
          Set<Account> e1=new HashSet<>();
          Account a=new Account("savings",70000);
          e.add(a);
          Account a1=new Account("savings",70000);
          e1.add(a1);

          List<Customer> asList = Arrays.asList(
          new Customer(1,"Neil","London", "Neil@gmail.com", null),
          new Customer(2,"Naill","Cardiff", "cadriff@wipro.com", e1)
            );
          System.out.println("Response1 :"+asList.toString());
          when(service.getCustomers()).thenReturn(asList);
          request=MockMvcRequestBuilders
         .get("/customers/1")
         .accept(MediaType.APPLICATION_JSON);
 
         MvcResult result =mockMvc.perform(request)
         .andExpect(status().isOk())
         .andReturn();

         MockHttpServletResponse response = result.getResponse();
         assertEquals(HttpStatus.OK.value(), response.getStatus());
          }
             @Test
             @Order(2)
             void createCustomers() throws Exception {
             RequestBuilder request;
             ObjectMapper objectMapper=new ObjectMapper();
             Set<Account> a1=new HashSet<Account>();
             Account a=new Account();
             a.setAccountNumber(1);
             a.setAccountType("savings");
             a.setBalance(100);
             a1.add(a);
          Set<Account> b1=new HashSet<Account>();
             Account b=new Account();
             b.setAccountNumber(1);
             b.setAccountType("savings");
             b.setBalance(100);
             b1.add(b);
             Customer Customer=new Customer(1,"Neil","London", "123@gmail.com",a1);
             Customer mockCustomer=new Customer(2,"Naill","Cardiff", "456@fmail.com", b1);


           when(service.createCustomer(any(Customer.class))).thenReturn(mockCustomer);
           request=MockMvcRequestBuilders
          .post("/api/customers")
           .contentType(MediaType.APPLICATION_JSON)
           .content(objectMapper.writeValueAsString(Customer));
          MvcResult result = mockMvc.perform(request).andReturn();
          MockHttpServletResponse response = result.getResponse();

         Object headerValue = response.getHeaderValue("location");
          System.out.println("Response :"+response.getContentAsString());


           assertEquals(HttpStatus.CREATED.value(), response.getStatus());




}

      @Test
      @Order(4)
      public void testUpdateCustomer() throws Exception {
      RequestBuilder request;
      ObjectMapper objectmapper=new ObjectMapper();
      Customer customer=new Customer(1,"priyanka","priya","12347@gmail.com", null);
      Customer mockCustomer=new Customer(1,"swee","priyanka","123437@gmail.com", null);
      when(service.updateCustomer( 1,customer)).thenReturn(new Customer(1,"priyanka","priya","12347@gmail.com",null));
      request=MockMvcRequestBuilders.put("/api/1")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(objectmapper.writeValueAsString(customer));
      MvcResult result=mockMvc.perform(request).andReturn();
      System.out.println(result.getResponse().getContentAsString());
      MockHttpServletResponse response=result.getResponse();
      Object headervalue=response.getHeaderValue("Location");
      assertEquals(HttpStatus.OK.value(),response.getStatus());
}  

   @Order(5)
   @Test
    public void testGetCustomerById() throws Exception {
    RequestBuilder request;
    int cust_id=1;
    Customer customer=new Customer(1,"R","guttula","rguttula7@gmail.com");
    when(service.getCustomer(cust_id)).thenReturn(customer);
    request=MockMvcRequestBuilders
            .get("/customers/1")
            .accept(MediaType.APPLICATION_JSON);
    MvcResult result =mockMvc.perform(request)
            .andExpect(status().isOk())
            .andReturn();
    MockHttpServletResponse response = result.getResponse();
    System.out.println("Response"+response.getContentAsString());
    System.out.println(response.getStatus()+"from service");
    assertEquals(HttpStatus.OK.value(), response.getStatus());
}
}

