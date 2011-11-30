package com.pl.events;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.pl.biblioteka.Book;
import com.pl.biblioteka.Clock;
import com.pl.biblioteka.ImpossibleHourException;


public class EventMaster {

	private Book ksiazka;
	private List processes = new ArrayList();
	private Clock wallClock = new Clock();
	public Book getBook() {
		return ksiazka;
	}

	public void setBook(Book ksiazka) {
		this.ksiazka = ksiazka;
	}
	public synchronized void addProcess(EventInterface process) {
		processes.add(process);
	}

	public synchronized void removeProcess(EventInterface process) {
		processes.remove(process);
	}

	public synchronized void executeProcesses(List<Book> list) throws ImpossibleHourException {
		for (Book b : list) {
			setBook(ksiazka);
			worldEvent event = new worldEvent();
			Iterator proc = processes.iterator();
			while (proc.hasNext()) {
				((EventInterface) proc.next()).processEvent(wallClock, event);
			}
		}
	}

}
