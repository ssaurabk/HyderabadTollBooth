package com.htb.vehicles;

import com.htb.constants.FareAndDistance;
import com.htb.exceptions.InsufficientBalanceInCardException;
import com.htb.tollbooths.EntryPoints;
import com.htb.tollbooths.ExitPoints;
import com.htb.trip.Journey;

public class Vehicle2345A extends Vehicle {
	
	Vehicle2345A(String commuterName, String vehicleNumber, int advancedAmount) {
		super(commuterName, vehicleNumber, advancedAmount);
	}

	@Override
	public void updateAdvanceAmount() throws InsufficientBalanceInCardException {
		Journey journey = journies.get(journies.size() - 1);
		EntryPoints entryPoint = journey.getEntryPoints();
		ExitPoints exitPoint = journey.getExitPoints();
		int tripFare = getFare(entryPoint,
				exitPoint);
		
		updateBalanceInCards(tripFare);
		int totalBalance = getTotalBalanceInCards();
		displayDetails(totalBalance, tripFare);
	}

	@Override
	public void verifyBalanceAtStartTrip()
			throws InsufficientBalanceInCardException {
		int totalBalance = getTotalBalanceInCards();
		if(totalBalance<FareAndDistance.minimumBalance.get(VehicleType.VEHICLE_2345A)){
			throw new InsufficientBalanceInCardException();
		}
	}
	protected int getFare(EntryPoints entryPoint, ExitPoints exitPoint) {
		int distance = getDistanceBetween(entryPoint, exitPoint);
		int fare = FareAndDistance.VEHICLE_2345A_BASE_FARE
				+ (distance * FareAndDistance.VEHICLE_2345A_PER_KM_FARE);
		return fare;
	}
}
