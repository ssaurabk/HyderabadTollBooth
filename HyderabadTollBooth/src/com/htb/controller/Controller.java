package com.htb.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.htb.constants.FareAndDistance;
import com.htb.exceptions.EntryAndExitPointSameException;
import com.htb.exceptions.InsufficientBalanceInCardException;
import com.htb.exceptions.TripNotStartedException;
import com.htb.exceptions.VehicleAlreadyInTripException;
import com.htb.tollbooths.EntryPoints;
import com.htb.tollbooths.ExitPoints;
import com.htb.vehicles.Vehicle;
import com.htb.vehicles.VehicleFactory;
import com.htb.vehicles.VehicleType;

public class Controller {

	public static void main(String[] args) {

		FareAndDistance.updateMaps();
		Scanner scanner = new Scanner(System.in);
		Map<String, Vehicle> vehicles = new HashMap<String, Vehicle>();
		vehicles = getVehiclesInputFromUser(scanner, vehicles);
		processJourniesOfVehicles(scanner, vehicles);

	}

	private static Map<String, Vehicle> getVehiclesInputFromUser(
			Scanner scanner, Map<String, Vehicle> vehicles) {
		String vehicleNumber;
		VehicleType vehicleType;
		String commuterName;
		int advanceAmount;
		while (true) {
			System.out.println(
					"Enter vehicle number, vehicle type, commuter name and adnavce amount: ");
			String nextLine = scanner.nextLine();
			if (nextLine.equals("0"))
				break;
			String[] splitToArray = nextLine.split(" ");
			vehicleNumber = splitToArray[0];
			if (splitToArray[1].contains("A") && (splitToArray[1].contains("2")
					|| splitToArray[1].contains("3")
					|| splitToArray[1].contains("4")
					|| splitToArray[1].contains("5"))) {
				vehicleType = VehicleType.VEHICLE_2345A;
			} else if (splitToArray[1].contains("A")
					&& (splitToArray[1].contains("6")
							|| splitToArray[1].contains("7"))) {
				vehicleType = VehicleType.VEHICLE_67A;
			} else {
				vehicleType = VehicleType.valueOf(splitToArray[1]);
			}
			commuterName = splitToArray[2];
			advanceAmount = Integer.parseInt(splitToArray[3]);
			Vehicle vehicle = VehicleFactory.getVehicleIntance(vehicleType,
					commuterName, vehicleNumber, advanceAmount);
			vehicles.put(vehicleNumber, vehicle);
			System.out.println("Enter 0 to exit");
		}
		return vehicles;
	}
	private static void processJourniesOfVehicles(Scanner scanner,
			Map<String, Vehicle> vehicles) {
		String vehicleNumber;
		String tollgate;
		while (true) {
			System.out.println("Enter vehicle number and tollgate number:");
			String nextLine = scanner.nextLine().toUpperCase();
			if (nextLine.equals("0"))
				break;
			String[] splitToArray = nextLine.split(" ");
			vehicleNumber = splitToArray[0];
			Vehicle vehicle = vehicles.get(vehicleNumber);
			tollgate = splitToArray[1];
			try {
				if (tollgate.contains("EN")) {
					vehicle.startTrip(EntryPoints.valueOf(splitToArray[1]));
				} else if (tollgate.contains("EX")) {
					vehicle.endTrip(ExitPoints.valueOf(splitToArray[1]));
				}
			} catch (EntryAndExitPointSameException e) {
				System.err.println("Entry and exit point are same");
			} catch (VehicleAlreadyInTripException e) {
				System.err.println("Vehicle already in trip");
			} catch (InsufficientBalanceInCardException e) {
				System.err.println("Insufficient balance");
			} catch (TripNotStartedException e) {
				System.err.println("Trip not started");
			}
		}

	}

}
