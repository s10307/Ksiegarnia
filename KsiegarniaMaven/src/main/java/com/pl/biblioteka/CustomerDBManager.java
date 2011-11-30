package com.pl.biblioteka;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDBManager {

	private Connection conn;

	private Statement stat;
	private PreparedStatement addCustomerStmt;
	private PreparedStatement getCustomerStmt;

	private PreparedStatement getCustomersStmt;

	public CustomerDBManager() {
		try {
			conn = DriverManager
					.getConnection("jdbc:hsqldb:hsql://localhost/workdb");

			stat = conn.createStatement();
			boolean customerTableExists = false;

			ResultSet rs = conn.getMetaData().getTables(null, null, null, null);
			while (rs.next()) {
				if ("Customer".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					customerTableExists = true;
					break;
				}
			}

			if (!customerTableExists) {
				stat.executeUpdate(""
						+ "CREATE TABLE customer("
						+ "id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,"
						+ "name varchar(20)," + "" + ")");
			}

			addCustomerStmt = conn.prepareStatement(""
					+ "INSERT INTO person (name) VALUES (?)" + "");

			getCustomersStmt = conn.prepareStatement(""
					+ "SELECT * FROM person" + "");

			stat.executeUpdate("CREATE TABLE customer("
					+ "id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,"
					+ "name varchar(20)" + ")");

			addCustomerStmt = conn.prepareStatement(""
					+ "INSERT INTO Customer (name) VALUES (?)" + "");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void addCustomer(Customer c) {
		try {
			addCustomerStmt.setString(1, c.getName());

			addCustomerStmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public List<Customer> getAllCustomers() {
		List<Customer> CustomerList = new ArrayList<Customer>();

		try {
			ResultSet rs = getCustomersStmt.executeQuery();

			while (rs.next()) {
				CustomerList
						.add(new Customer(rs.getString("name"), null, null));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return CustomerList;
	}
}
