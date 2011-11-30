package com.pl.biblioteka;

public class Clock {

	public int timeOfDayHours;
	public int timeOfDayMinutes;
	public boolean isOpening;
	public boolean isClosing;
	
	public void setTime(int newHour, int newMinutes) throws ImpossibleHourException{
		
		if(newHour<0 || newHour>24){
			throw new ImpossibleHourException("Trying to set hour out of it's 24h bound; try 0<->24. ");
		}
		if(newMinutes<0 || newMinutes>60){
			throw new ImpossibleHourException("Trying to set minutes out of it's 60m bound; try 0<->60. ");
		}
		timeOfDayHours = newHour;
		timeOfDayMinutes = newMinutes;
	}
	
	public void showTime(){
			String MinutesString = "";
		if(timeOfDayMinutes<10) MinutesString +="0";
		MinutesString +=timeOfDayMinutes;
		System.out.println(timeOfDayHours+":"+MinutesString);
	}
	
	public Clock(){
		timeOfDayHours = 12;
		timeOfDayMinutes = 0;
	}
	
	public Clock(int hour, int minutes){
		timeOfDayHours = hour;
		timeOfDayMinutes = minutes;
	}

	public boolean isOpening() {
		return isOpening;
	}

	public void setOpening(boolean isOpening) {
		this.isOpening = isOpening;
	}

	public boolean isClosing() {
		return isClosing;
	}

	public void setClosing(boolean isClosing) {
		this.isClosing = isClosing;
	}
	
}
