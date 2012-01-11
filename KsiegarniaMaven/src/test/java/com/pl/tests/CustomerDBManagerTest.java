package com.pl.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.pl.biblioteka.Book;
import com.pl.biblioteka.Customer;
import com.pl.services.CustomerDBManager;

public class CustomerDBManagerTest {

	CustomerDBManager CustomerManager = new CustomerDBManager();
	@Before
	public void setUp() throws Exception {
		CustomerManager.addCustomer(new Customer("Imie", "Nazwisko"));
	}

	@After
	public void tearDown() throws Exception {
		CustomerManager.deleteAllCustomers();
	}

	@Test
	public void testAddCustomer() {
		CustomerManager.addCustomer(new Customer("Imie2","Nazwisko2"));
		assertEquals(2, CustomerManager.getAllCustomers().size());
	}

	@Test
	public void testGetAllCustomers() {
		assertEquals(1, CustomerManager.getAllCustomers().size());
	}

	@Test
	public void testDeleteAllCustomers() {
		CustomerManager.deleteAllCustomers();
		assertTrue(CustomerManager.getAllCustomers().isEmpty());
	}

	@Test
	public void testFindCustomerByName() {
		CustomerManager.addCustomer(new Customer("Imie","Nazwisko2"));
		assertEquals(2, CustomerManager.findCustomerByName("Imie").size());
	}

	@Test
	public void testFindCustomerBySurname() {
		CustomerManager.addCustomer(new Customer("Imie2","Nazwisko"));
		CustomerManager.addCustomer(new Customer("Imie2","Nazwisko2"));
		assertEquals(2, CustomerManager.findCustomerByName("Nazwisko").size());
	}

	@Test
	public void testDeleteCustomer() {
		CustomerManager.addCustomer(new Customer("Imie2","Nazwisko"));
		CustomerManager.addCustomer(new Customer("Imie2","Nazwisko2"));
		CustomerManager.deleteCustomer(CustomerManager.findCustomerByName("Imie"));
		assertEquals(2, CustomerManager.getAllCustomers().size());
	}

}
