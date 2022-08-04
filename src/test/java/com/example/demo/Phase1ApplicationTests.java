package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@WebAppConfiguration
class Phase1ApplicationTests {

	@Test
	@Order(1)
	void contextLoads() {
	}
	
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	
	private MockMvc mockMVC;
	
	@BeforeEach
	private void setup() throws Exception{
		this.mockMVC = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}
	
	
	@Test
	@Order(2)
	public void CreateBookDetaTest() throws Exception {
		
		String payload = "	{\n"
				+ "    \"id\": 1,\n"
				+ "    \"firstName\": \"priya\",\n"
				+ "    \"lastName\": \"balivada\",\n"
				+ "    \"email\": \"bpriyanka@gmail.com\",\n"
				+ "    \"accounts\": [\n"
				+ "        {\n"
				+ "            \"accountNumber\": 1,\n"
				+ "            \"accountType\": \"Savings\",\n"
				+ "            \"balance\": 70000\n"
				+ "        },\n"
				+ "        {\n"
				+ "            \"accountNumber\": 2,\n"
				+ "            \"accountType\": \"Current\",\n"
				+ "            \"balance\": 80000\n"
				+ "        }\n"
				+ "    ]\n"
				+ "} ";
		
		mockMVC.perform(post("http://localhost:1237/api/customers").contentType(MediaType.APPLICATION_JSON).content(payload))
		.andExpect(status().isCreated())
		.andReturn();
		
	}
	/*@Order(3)
	@Test
	public void fetchBookByIdTest() throws Exception {
		mockMVC.perform(get("/api/books/{id}",1L)).andExpect(status().isOk()).andReturn();
	}
	   @Test
	  public  void findAllBooks() throws Exception {
	      
	        mockMVC.perform(get("/api/books")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk()).andReturn();
	    }
	   @Order(5)
	   @Test
	 		public void deleteBookByIdTest() throws Exception {
		       
	 			mockMVC.perform(delete("/api/books/{id}",1L)).andExpect(status().isNoContent());
 		}
	   @Order(4)
	   @Test
	   public void updateByBookId() throws Exception 
	   {
		   
			String payload = "	{\n"
					+ "    \"id\": 1,\n"
					+ "    \"title\": \"lal\",\n"
					+ "    \"description\": \"good\",\n"
					+ "    \"published\": false\n"
					+ "}";
	     mockMVC.perform(put("/api/books/{id}",1L)
	         .contentType(MediaType.APPLICATION_JSON)
	         .content(payload)
	         .accept(MediaType.APPLICATION_JSON))
	         .andExpect(status().isOk()).andReturn();
	   }

	        

	   @Test
	   @Order(6)
	   public  void DeleteAllBooks() throws Exception {
		      
	   mockMVC.perform(delete("/api/books")
		          .contentType(MediaType.APPLICATION_JSON))
		          .andExpect(status().isNoContent());
		    }*/
}
