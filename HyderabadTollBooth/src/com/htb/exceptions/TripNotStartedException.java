package com.htb.exceptions;

public class TripNotStartedException extends Exception {

	private static final long serialVersionUID = 7170749771418535903L;

	public TripNotStartedException() {
		super("Please start the trip before trying to end it");

	}
}
