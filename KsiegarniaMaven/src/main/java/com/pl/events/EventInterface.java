package com.pl.events;

import com.pl.biblioteka.Clock;
import com.pl.biblioteka.ImpossibleHourException;

public interface EventInterface {

	public void processEvent(Clock wallClock, worldEvent someEvent) throws ImpossibleHourException;
}