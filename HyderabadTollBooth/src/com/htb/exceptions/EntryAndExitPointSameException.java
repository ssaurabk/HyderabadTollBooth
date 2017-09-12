package com.htb.exceptions;

public class EntryAndExitPointSameException extends Exception {

	private static final long serialVersionUID = 7170749771418535903L;

	public EntryAndExitPointSameException() {
		super("Entry and exit point can not be same");

	}

}
