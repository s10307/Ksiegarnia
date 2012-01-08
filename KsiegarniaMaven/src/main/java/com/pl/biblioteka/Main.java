package com.pl.biblioteka;

import org.apache.log4j.*;

import com.pl.services.Condition;
import com.pl.services.BookDBManager;
import com.pl.services.CustomerDBManager;
import com.pl.services.BookCustomerDBManager;
import java.util.*;

public class Main {

	private static Logger logger = Logger.getLogger(Main.class);

	public void cleanDB(BookDBManager BookMng, CustomerDBManager CustMng,
			BookCustomerDBManager LnkdMng) {
		BookMng.deleteAllBooks();
		CustMng.deleteAllCustomers();
		LnkdMng.deleteAllLendings();
	}

	public static void main(String[] args) {

		PropertyConfigurator.configure("src/resources/java/Log4J.properties");

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

		Typek.borrowBook(Biblia);
		Typek.borrowBook(Elementarz);
		Typek.printBookList();

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
		for (Customer customer : CustomerManager.getAllCustomers()) {
			System.out.println(customer.getName());
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

		LinkedManager.LendBookToCustomer(
				CustomerManager.findCustomerByName("Jakis"),
				BookManager.findBookByName("Elementarz"));
		LinkedManager.LendBookToCustomer(
				CustomerManager.findCustomerBySurname("Typek"),
				BookManager.findBookByName("Podrecznik"));

		for (Book book : LinkedManager.getCustomerBook(CustomerManager
				.findCustomerByName("Jakis"))) {
			System.out.println("Name: " + book.getName() + "\nAuthor: "
					+ book.getAuthor());
		}

		// warunki do klas anonimowych

		BookManager.printBookWithCondition(BookManager.getAllBooks(),
				new Condition() {
					@Override
					public boolean getCondition(Book book) {
						if (book.getName().length() > 35)
							return true;
						return false;
					}

					@Override
					public boolean getCondition(Customer customer) {
						return false;
					}
				});
		
		CustomerManager.printCustomerWithCondition(CustomerManager.getAllCustomers(),
				new Condition() {
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
	}
}