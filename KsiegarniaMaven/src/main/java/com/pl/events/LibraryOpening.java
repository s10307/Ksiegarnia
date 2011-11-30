package com.pl.events;
import com.pl.biblioteka.*;
import com.pl.events.*;

public class LibraryOpening implements EventInterface  {
	
	Clock wallClock = new Clock();
	
	@Override
	public void processEvent(Clock wallClock, worldEvent event) throws ImpossibleHourException {
		wallClock.setTime(8, 0);
		wallClock.setOpening(true);
		wallClock.setClosing(false);
	}

}
