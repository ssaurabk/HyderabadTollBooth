package com.htb.trip;

import com.htb.tollbooths.EntryPoints;
import com.htb.tollbooths.ExitPoints;

public class Journey {
	EntryPoints entryPoints;
	ExitPoints exitPoints;
	boolean inJourney;
	int fare;
	public EntryPoints getEntryPoints() {
		return entryPoints;
	}
	public void setEntryPoints(EntryPoints entryPoints) {
		this.entryPoints = entryPoints;
	}
	public ExitPoints getExitPoints() {
		return exitPoints;
	}
	public void setExitPoints(ExitPoints exitPoints) {
		this.exitPoints = exitPoints;
	}
	public boolean isInJourney() {
		return inJourney;
	}
	public void setInJourney(boolean inJourney) {
		this.inJourney = inJourney;
	}
	public int getFare() {
		return fare;
	}
	public void setFare(int fare) {
		this.fare = fare;
	}
}
