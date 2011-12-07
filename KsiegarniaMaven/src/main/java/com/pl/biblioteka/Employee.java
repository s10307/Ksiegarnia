package com.pl.biblioteka;

import java.util.ArrayList;
import java.util.List;

public class Employee {
	
	String name;
	String surname;
	List<Customer> CustomerList = new ArrayList<Customer>();
	
	public Employee(String imie, String surname, List<Customer> CustomerList){
		this.name = imie;
		this.surname = surname;
		this.CustomerList = CustomerList;
	}
	
	public void addCustomer(Customer k){
		CustomerList.add(k);
	}
	
	public void removeCustomer(Customer k){
		CustomerList.remove(k);
	}
	
	public Customer isCustomer(Customer c){
		for(Customer q : CustomerList){
			if(c.equals(q)){
				return q;
			}
		}
		return null;
	}

	public void printCustomerList(){
		for(Customer c : CustomerList){
			c.printCustomer();
			c.printBookList();
		}
	}
	
	public List<Customer> findAllCustomersByName(String name) {
		List<Customer> results = new ArrayList<Customer>();
		for (Customer c : CustomerList) {
			if (c.getName().equals(name)) {
				results.add(c);
			}
		}
		return results;
	}
	
	public List<Customer> findAllCustomersBySurname(String surname) {
		List<Customer> results = new ArrayList<Customer>();
		for (Customer c : CustomerList) {
			if (c.getSurname().equals(surname)) {
				results.add(c);
			}
		}
		return results;
	}
	
	public void renameAllCustomersByName(List<Customer> customerList, String newName){
		for(Customer c : customerList){
			c.setName(newName);
		}
	}
	
	public void renameAllCustomersBySurname(List<Customer> customerList, String newSurname){
		for(Customer c : customerList){
			c.setSurname(newSurname);
		}
	}
	
	public void removeAllCustomersByList(List<Customer> tmpCustomerList){
		for(Customer c : CustomerList){
			for(Customer tmpC : tmpCustomerList){
				if(c.equals(tmpC)){
					CustomerList.remove(c);
				}
			}
		}
	}

	public void printEmployee() {
		System.out.println("_Employee_");
		System.out.println("Name: " + this.name +
				"; Surname: " + this.surname);	
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public List<Customer> getCustomerList() {
		return CustomerList;
	}
	public void setCustomerList(List<Customer> customerList) {
		CustomerList = customerList;
	}
}
