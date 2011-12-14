package com.pl.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.pl.biblioteka.*;


public class EmployeeTest {
	
	List<Customer> CustomerList = new ArrayList<Customer>();
	List<Book> BookList = new ArrayList<Book>();
	Employee Pracownik = new Employee("Pracownik_Name", "Pracownik_Surname", CustomerList);
	Customer Klient = new Customer("Klient_Name", "Klient_surname", BookList);
	@Before
	public void setUp() throws Exception {
		Pracownik.addCustomer(Klient);
	}

	@After
	public void tearDown() throws Exception {
		Pracownik.removeCustomer(Klient);
	}

	@Test
	public void testEmployee() {
		assertTrue(Pracownik.getName().equals("Pracownik_Name"));
		assertTrue(Pracownik.getSurname().equals("Pracownik_Surname"));
		assertSame(CustomerList, Pracownik.getCustomerList());
	}

	@Test
	public void testAddCustomer() {
		assertSame(Klient, Pracownik.isCustomer(Klient));
	}

	@Test
	public void testRemoveCustomer() {
		Pracownik.removeCustomer(Klient);
		assertNull(Pracownik.isCustomer(Klient));
	}

	@Test
	public void testIsCustomer() {
		Pracownik.removeCustomer(Klient);
		assertNull(Pracownik.isCustomer(Klient));
	}

	@Test
	public void testFindAllCustomersByName() {
		List<Customer> TestList = Pracownik.findAllCustomersByName("Klient_Name");
		assertEquals(Pracownik.findAllCustomersByName("Klient_Name"), TestList);
	}

	@Test
	public void testFindAllCustomersBySurname() {
		List<Customer> TestList = Pracownik.findAllCustomersBySurname("Klient_Surname");
		assertEquals(Pracownik.findAllCustomersBySurname("Klient_Surname"), TestList);
	}

	@Test
	public void testRenameAllCustomersByName() {
		Pracownik.renameAllCustomersByName(CustomerList, "Klient_Name1");
		assertEquals(CustomerList, Pracownik.findAllCustomersByName("Klient_Name1"));
		
	}

	@Test
	public void testRenameAllCustomersBySurname() {
		Pracownik.renameAllCustomersBySurname(CustomerList, "Klient_Surname1");
		assertEquals(CustomerList, Pracownik.findAllCustomersBySurname("Klient_Surname1"));
	}
/*
	@Test
	public void testRemoveAllCustomersByList() {
		Pracownik.removeAllCustomersByList(CustomerList);
		assertTrue(Pracownik.getCustomerList().isEmpty());
	}
*/

}
