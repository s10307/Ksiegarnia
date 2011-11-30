package com.pl.biblioteka;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.*;

public class Main {
	
	private static Logger myLogger = Logger.getLogger(Main.class);
	
	public static void main(String[] args){
	
		PropertyConfigurator.configure("Log4j.properties");
		
		Clock WallClock = new Clock();
		WallClock.showTime();
		try {
			WallClock.setTime(24, 50);
			WallClock.setTime(26, 70);
		} catch (ImpossibleHourException e) {
			System.out.println(e);
		}
		WallClock.showTime();
	
		Book Awatar = new Book(BookRepo.Awatar, Authors.M_Flitwichky);
		Book Bullocks = new Book(BookRepo.Bullocks, Authors.O_Scott_Card);
		Book Djuna = new Book(BookRepo.Djuna, Authors.O_Scott_Card);
		Book Faraon = new Book(BookRepo.Faraon, Authors.H_Sienkiewicz);
		Book Kiszyniov = new Book(BookRepo.Kiszyniov, Authors.A_Brezniev);
		
		Book Nowa = new Book(BookRepo.W_pustyni_i_gluszy, Authors.H_Sienkiewicz);
		
		List<Book> listaKsiazek1 = new ArrayList<Book>();
		listaKsiazek1.add(Awatar);
		listaKsiazek1.add(Bullocks);
		listaKsiazek1.add(Djuna);
		List<Book> listaKsiazek2 = new ArrayList<Book>();
		listaKsiazek2.add(Faraon);
		listaKsiazek2.add(Kiszyniov);
		
		
		
		
		Customer Typek = new Customer("Jakis", "Typek", listaKsiazek1);
		Customer Koleszka = new Customer("Inny", "Koleszka", listaKsiazek2);
		
		List<Customer> listaKlientow = new ArrayList<Customer>();
		listaKlientow.add(Typek);
		listaKlientow.add(Koleszka);
		
		
		
		Employee roku = new Employee("Pan", "Wladek", listaKlientow);
		roku.printEmployee();
		roku.printCustomerList();
		
		Typek.borrowBook(Nowa);
		Koleszka.returnBook(Faraon);
		Koleszka.returnBook(Kiszyniov);
		Koleszka.borrowBook(Nowa);
		
		roku.printEmployee();
		roku.printCustomerList();
		
		
	}
}
