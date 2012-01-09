package com.pl.biblioteka;

import org.apache.log4j.*;

import com.pl.services.*;
import java.util.*;

public class Main {

	public static void cleanDB(BookDBManager BookMng,
			CustomerDBManager CustMng, BookCustomerDBManager LnkdMng) {
		LnkdMng.deleteAllLendings();
		BookMng.deleteAllBooks();
		CustMng.deleteAllCustomers();
	}

	private static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) {

		PropertyConfigurator
				.configure("src/resources/java/com/pl/reso/Log4j.properties");

		Clock zegar = new Clock(12, 0);

		Customer Typek = new Customer("Jakis", "Typek");
		Customer Typeczek = new Customer("Inny", "Typek");

		List<Customer> CustomerList = new ArrayList<Customer>();
		CustomerList.add(Typek);
		CustomerList.add(Typeczek);

		Book Biblia = new Book("Nowy Testament", "God");
		Book Elementarz = new Book("Elementarz", "Pan Obok");
		Book Podrecznik = new Book("Do Matematyki", "Soviet");
		List<Book> BookList = new ArrayList<Book>();
		BookList.add(Podrecznik);
		BookList.add(Elementarz);
		BookList.add(Biblia);

		try {
			zegar.setTime(26, 00);
			zegar.showTime();
		} catch (ImpossibleHourException exc) {
			logger.error(exc);
		}

		// ----------------------------------------------------------------------------------------------------

		CustomerDBManager CustomerManager = new CustomerDBManager();

		CustomerManager.addCustomer(Typek);
		CustomerManager.addCustomer(Typeczek);
		System.out.println("Wszystkie Typki:");
		for (Customer customer : CustomerManager.getAllCustomers()) {
			System.out.println("Name= " + customer.getName());
			System.out.println("Surname= " + customer.getSurname());
		}
		/*
		 * !USUWANIE!
		 * CustomerManager.deleteCustomer(CustomerManager.findCustomerByName
		 * ("Inny")); for (Customer Customer :
		 * CustomerManager.getAllCustomers()) {
		 * System.out.println(customer.getName()); }
		 */

		BookDBManager BookManager = new BookDBManager();

		BookManager.addBook(Biblia);
		BookManager.addBook(Elementarz);
		BookManager.addBook(Podrecznik);
		System.out.println("Wszystkie Ksiazki:");
		for (Book book : BookManager.getAllBooks()) {
			System.out.println("Name: " + book.getName() + "\nAuthor: "
					+ book.getAuthor());
		}
		/*
		 * !USUWANIE!
		 * BookManager.deleteBook(BookManager.findBookByName("Elementarz")); for
		 * (Book book : BookManager.getAllBooks()) { System.out.println("Name: "
		 * + book.getName() + "\nAuthor: " + book.getAuthor()); }
		 */

		// ----------------------------------------------------------------------------------------------------

		BookCustomerDBManager LinkedManager = new BookCustomerDBManager();
		List<Integer> CustomerID = CustomerManager.findCustomerByName("Jakis");
		List<Integer> BookID = BookManager.findBookByName("Elementarz");
		
		for(Integer ID : CustomerID ){
			System.out.println("Customer: " + ID);
		}
		for(Integer ID : BookID ){
			System.out.println("Book: " + ID);
		}
		
		LinkedManager.LendBookToCustomer(CustomerID, BookID);
		
		for (Book book : LinkedManager.getCustomerBook(CustomerManager
				.findCustomerByName("Jakis"))) {
			System.out.println("Name: " + book.getName() + "\nAuthor: "
					+ book.getAuthor());
		}

		// -warunki-do-klas-anonimowych-------------------------------------------------------------------------
		System.out.println("Tytul dluzszy niz 25 znakow");
		BookManager.printBookWithCondition(BookManager.getAllBooks(),
				new Condition() {
					@Override
					public boolean getCondition(Book book) {
						if (book.getName().length() > 25)
							return true;
						return false;
					}

					@Override
					public boolean getCondition(Customer customer) {
						return false;
					}
				});
		System.out.println("Imie dluzsze niz 4 znaki");
		CustomerManager.printCustomerWithCondition(
				CustomerManager.getAllCustomers(), new Condition() {
					@Override
					public boolean getCondition(Book book) {
						return false;
					}

					@Override
					public boolean getCondition(Customer customer) {
						if (customer.getName().length() > 4)
							return true;
						return false;
					}
				});
		//cleanDB(BookManager, CustomerManager, LinkedManager);
	}
}