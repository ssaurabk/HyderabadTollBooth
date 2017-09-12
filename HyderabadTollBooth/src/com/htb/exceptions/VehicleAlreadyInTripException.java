package com.htb.exceptions;

public class VehicleAlreadyInTripException extends Exception {

	private static final long serialVersionUID = 7170749771418535903L;

	public VehicleAlreadyInTripException() {
		super("Vehicle already in journey.");

	}

}
