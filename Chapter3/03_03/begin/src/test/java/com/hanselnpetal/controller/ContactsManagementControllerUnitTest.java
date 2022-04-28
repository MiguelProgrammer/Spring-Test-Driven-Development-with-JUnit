package com.hanselnpetal.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.hanselnpetal.domain.CustomerContact;
import com.hanselnpetal.service.ContactsManagementService;


@RunWith(SpringRunner.class)
@WebMvcTest(ContactsManagementController.class)
public class ContactsManagementControllerUnitTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ContactsManagementService contactsManagementService;
	
	@InjectMocks
	private ContactsManagementController contactsManagementController;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);	
	}
	
	@Test
	public void testAddContactHappyPath() throws Exception {
		// setup mock Contact returned the mock service component
		CustomerContact mockCustomerContact = new CustomerContact();
		mockCustomerContact.setFirstName("Miguel");
		
		when(contactsManagementService.add(any(CustomerContact.class)))
			.thenReturn(mockCustomerContact);
		
		// simulate the form bean that would POST from the web page
		CustomerContact aContact = new CustomerContact();
		aContact.setFirstName("Miguel");
		aContact.setEmail("miguel@gmail.com");
		
		// simulate the form submit (POST)
		mockMvc
			.perform(post("/addContact", aContact))
			.andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void testAddContactBizServiceRuleNotSatisfied() throws Exception {
		
		// setup a mock response of NULL object returned from the mock service component
		when(contactsManagementService.add(any(CustomerContact.class)))
			.thenReturn(null);
		
		// simulate the form bean that would POST from the web page
		CustomerContact aContact = new CustomerContact();
		aContact.setLastName("Pereira da Silva");
		
		// simulate the form submit (POST)
		mockMvc
			.perform(post("/addContact", aContact))
			.andExpect(status().is(302))
			.andReturn();
	}
	
	@Test
	public void testAddContactOccasionHappyPath() throws Exception {
		// implement this
	}
}
