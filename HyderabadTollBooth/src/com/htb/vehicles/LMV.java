package com.htb.vehicles;

import com.htb.constants.FareAndDistance;
import com.htb.exceptions.InsufficientBalanceInCardException;
import com.htb.tollbooths.EntryPoints;
import com.htb.tollbooths.ExitPoints;
import com.htb.trip.Journey;

public class LMV extends Vehicle {

	LMV(String commuterName, String vehicleNumber, int advancedAmount) {
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
	
	protected int getFare(EntryPoints entryPoint, ExitPoints exitPoint) {
		ExitPoints reached = null;
		int fare = 0;
		while (!exitPoint.equals(reached)) {

			for (String pointToPoint : FareAndDistance.lmvFareMap.keySet()) {
				if (pointToPoint.contains(entryPoint.toString())) {
					fare = fare + FareAndDistance.lmvFareMap.get(pointToPoint);
					reached = getReachedPoint(entryPoint);
					entryPoint = getNextEntryPoint(entryPoint);
					break;
				}
			}
		}

		return fare;
	}


	@Override
	public void verifyBalanceAtStartTrip()
			throws InsufficientBalanceInCardException {
		int totalBalance = getTotalBalanceInCards();
		if (totalBalance < FareAndDistance.minimumBalance
				.get(VehicleType.LMV)) {
			throw new InsufficientBalanceInCardException();
		}
	}

}
