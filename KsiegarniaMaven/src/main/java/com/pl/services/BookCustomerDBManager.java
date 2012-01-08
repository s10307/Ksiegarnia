package com.pl.services;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import com.pl.biblioteka.*;

public class BookCustomerDBManager {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement LendBookToCustomerStmt;
	private PreparedStatement deleteAllLendingsStmt;
	private PreparedStatement deleteCustomerLendingsStmt;
	private PreparedStatement getCustomerBookStmt;

	public BookCustomerDBManager() {
		try {
			Properties props = new Properties();

			try {
				props.load(ClassLoader
						.getSystemResourceAsStream("com.pl/jdbs.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}

			conn = DriverManager.getConnection(props.getProperty("url"));

			stmt = conn.createStatement();
			boolean CustomerBookTableExists = false;

			ResultSet rs = conn.getMetaData().getTables(null, null, null, null);

			while (rs.next()) {
				if ("CustomerBook".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					CustomerBookTableExists = true;
					break;
				}
			}

			if (!CustomerBookTableExists) {
				stmt.executeUpdate("CREATE TABLE CustomerBook(customer_id int, book_id int,"
						+ " CONSTRAINT customer_id_fk FOREIGN KEY (customer_id) REFERENCES customer (id),"
						+ " CONSTRAINT book_id_fk FOREIGN KEY (book_id) REFERENCES book (id))");
			}

			LendBookToCustomerStmt = conn
					.prepareStatement("INSERT INTO CustomerBook (customer_id, book_id) VALUES (?, ?)");

			deleteCustomerLendingsStmt = conn
					.prepareStatement("DELETE FROM CustomerBook WHERE customer_id = ?");

			deleteAllLendingsStmt = conn
					.prepareStatement("DELETE FROM CustomerBook");

			getCustomerBookStmt = conn
					.prepareStatement("SELECT book.name, book.author FROM book,"
							+ " CustomerBook WHERE customer_id = ? and book_id = book.id");

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void LendBookToCustomer(List<Integer> CustomerList,
			List<Integer> BookList) {
		try {
			for (Integer customerID : CustomerList) {
				for (Integer bookID : BookList) {
					LendBookToCustomerStmt.setInt(1, customerID);
					LendBookToCustomerStmt.setInt(2, bookID);
					LendBookToCustomerStmt.executeUpdate();
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void deleteCustomerLendings(List<Integer> CustomerList) {
		try {
			for (Integer customerID : CustomerList) {
				deleteCustomerLendingsStmt.setInt(1, customerID);
				deleteCustomerLendingsStmt.executeUpdate();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void deleteAllLendings() {
		try {
			deleteAllLendingsStmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public List<Book> getCustomerBook(List<Integer> CustomerList) {
		List<Book> BookList = new ArrayList<Book>();
		try {
			for (Integer customerID : CustomerList) {
				getCustomerBookStmt.setInt(1, customerID);
				ResultSet rs = getCustomerBookStmt.executeQuery();
				while (rs.next()) {
					BookList.add(new Book(rs.getString("name"), rs
							.getString("author")));
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return BookList;
	}

}