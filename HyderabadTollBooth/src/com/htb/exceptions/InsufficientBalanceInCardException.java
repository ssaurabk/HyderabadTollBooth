package com.htb.exceptions;

public class InsufficientBalanceInCardException extends Exception {

	private static final long serialVersionUID = 7170749771418535903L;

	public InsufficientBalanceInCardException() {
		super("Card has insufficient balance");

	}

}
