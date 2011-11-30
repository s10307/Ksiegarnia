package com.pl.events;

import com.pl.biblioteka.Clock;
import com.pl.biblioteka.ImpossibleHourException;

public class LibraryClosing implements EventInterface {
	Clock wallClock = new Clock();

	@Override
	public void processEvent(Clock wallClock, worldEvent event) throws ImpossibleHourException {
		wallClock.setTime(20, 0);
		wallClock.setOpening(false);
		wallClock.setClosing(true);
	}
}
