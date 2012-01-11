package com.pl.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.pl.biblioteka.Book;
import com.pl.biblioteka.Customer;
import com.pl.services.BookCustomerDBManager;
import com.pl.services.BookDBManager;
import com.pl.services.CustomerDBManager;

public class BookCustomerDBManagerTest {

	BookDBManager BookManager = new BookDBManager();
	CustomerDBManager CustomerManager = new CustomerDBManager();
	BookCustomerDBManager LinkedManager = new BookCustomerDBManager();

	@Before
	public void setUp() throws Exception {
		BookManager.addBook(new Book("Tytul1", "Autor1"));
		BookManager.addBook(new Book("Tytul2", "Autor2"));
		BookManager.addBook(new Book("Tytul3", "Autor2"));

		CustomerManager.addCustomer(new Customer("Imie1", "Nazwisko1"));
		CustomerManager.addCustomer(new Customer("Imie2", "Nazwisko2"));
		LinkedManager.LendBookToCustomer(
				CustomerManager.findCustomerByName("Imie1"),
				BookManager.findBookByAuthor("Autor2"));
	}

	@After
	public void tearDown() throws Exception {
		LinkedManager.deleteAllLendings();
		BookManager.deleteAllBooks();
		CustomerManager.deleteAllCustomers();
	}
	
	@Test
	public void testLendBookToCustomer() {
		BookManager.addBook(new Book("Tytul4","Autor1"));
		LinkedManager.LendBookToCustomer(CustomerManager.findCustomerByName("Imie1"), BookManager.findBookByName("Tytul4"));
		assertEquals(3, LinkedManager.getCustomerBook(CustomerManager.findCustomerByName("Imie1")).size());
	}

	@Test
	public void testDeleteCustomerLendings() {
		assertEquals(2, LinkedManager.getCustomerBook(CustomerManager.findCustomerByName("Imie1")).size());
		LinkedManager.deleteCustomerLendings(CustomerManager.findCustomerByName("Imie1"));
		assertEquals(0, LinkedManager.getCustomerBook(CustomerManager.findCustomerByName("Imie1")).size());
	}

	@Test
	public void testDeleteAllLendings() {
		LinkedManager.deleteAllLendings();
		assertTrue(LinkedManager.getCustomerBook(CustomerManager.findCustomerByName("Imie1")).isEmpty());
	}

	@Test
	public void testGetCustomerBook() {
		assertEquals(2, LinkedManager.getCustomerBook(CustomerManager.findCustomerByName("Imie1")).size());
	}

}
