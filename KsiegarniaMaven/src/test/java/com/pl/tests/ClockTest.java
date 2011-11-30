package com.pl.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.pl.biblioteka.*;
public class ClockTest {

	Clock testClock = new Clock();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testSetTime() throws ImpossibleHourException {
		testClock.setTime(-2, -7);
	}

	@Test
	public void testClock() {
		assertTrue(testClock.timeOfDayHours == 12);
		assertTrue(testClock.timeOfDayMinutes == 0);
	}

}
