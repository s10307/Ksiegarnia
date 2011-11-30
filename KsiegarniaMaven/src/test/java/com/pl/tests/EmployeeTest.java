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
	Employee Pracownik = new Employee("imie", "nazwisko", CustomerList);
	Customer Klient = new Customer("jakiesImie", "JakiesNazwisko", BookList);
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEmployee() {
		assertTrue(Pracownik.getName().equals("imie"));
		assertTrue(Pracownik.getSurname().equals("nazwisko"));
		assertSame(CustomerList, Pracownik.getCustomerList());
	}

	@Test
	public void testAddCustomer() {
		Pracownik.addCustomer(Klient);
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
		assertSame(CustomerList, Pracownik.findAllCustomersByName("JakiesImie"));
	}

	@Test
	public void testFindAllCustomersBySurname() {
		assertSame(CustomerList, Pracownik.findAllCustomersBySurname("JakiesNazwisko"));
	}

	@Test
	public void testRenameAllCustomersByName() {
		Pracownik.renameAllCustomersByName(CustomerList, "NoweImie");
		assertSame(CustomerList, Pracownik.findAllCustomersByName("NoweImie"));
	}

	@Test
	public void testRenameAllCustomersBySurname() {
		Pracownik.renameAllCustomersBySurname(CustomerList, "NoweNazwisko");
		assertSame(CustomerList, Pracownik.findAllCustomersBySurname("NoweNazwisko"));
	}

	@Test
	public void testRemoveAllCustomersByList() {
		Pracownik.removeAllCustomersByList(CustomerList);
		assertNull(Pracownik.isCustomer(Klient));
	}


}
