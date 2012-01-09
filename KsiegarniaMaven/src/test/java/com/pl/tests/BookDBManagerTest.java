package com.pl.tests;

import static org.junit.Assert.*;

import java.awt.List;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.pl.biblioteka.Book;
import com.pl.biblioteka.Customer;
import com.pl.services.BookDBManager;

public class BookDBManagerTest {
	
	BookDBManager BookManager = new BookDBManager();
	
	@Before
	public void setUp() throws Exception {
		BookManager.addBook(new Book("Tytul","Autor"));
	}

	@After
	public void tearDown() throws Exception {
		BookManager.deleteAllBooks();
	}

	@Test
	public void testAddBook() {
		assertNotNull(BookManager.findBookByName("Tytul"));
	}

	@Test
	public void testGetAllBooks() {
		assertNotNull(BookManager.getAllBooks());
	}

	@Test
	public void testDeleteAllBooks() {
		BookManager.deleteAllBooks();
		assertNull(BookManager.getAllBooks());
	}

	@Test
	public void testFindBookByName() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindBookByAuthor() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testPrintBookWithCondition() {
		fail("Not yet implemented");
	}

}
