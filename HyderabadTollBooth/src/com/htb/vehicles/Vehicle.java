package com.htb.vehicles;

import java.util.ArrayList;
import java.util.List;

import com.htb.cards.SmartCard;
import com.htb.constants.FareAndDistance;
import com.htb.exceptions.EntryAndExitPointSameException;
import com.htb.exceptions.InsufficientBalanceInCardException;
import com.htb.exceptions.TripNotStartedException;
import com.htb.exceptions.VehicleAlreadyInTripException;
import com.htb.tollbooths.EntryPoints;
import com.htb.tollbooths.ExitPoints;
import com.htb.trip.Journey;

public abstract class Vehicle {

	String commuterName;
	String vehicleNumber;
	List<Journey> journies;
	List<SmartCard> smartCards;
	public Vehicle(String commuterName, String vehicleNumber,
			int advancedAmount) {
		super();
		this.commuterName = commuterName;
		this.vehicleNumber = vehicleNumber;
		journies = new ArrayList<Journey>();
		SmartCard smartCard = new SmartCard(true, true, advancedAmount);
		smartCards = new ArrayList<SmartCard>();
		smartCards.add(smartCard);
	}

	public String getCommuterName() {
		return commuterName;
	}

	public void startTrip(EntryPoints entryPoint)
			throws EntryAndExitPointSameException,
			VehicleAlreadyInTripException, InsufficientBalanceInCardException {
		verifyStartTrip(entryPoint);
		Journey journey = new Journey();
		journey.setEntryPoints(entryPoint);
		journey.setInJourney(true);
		journies.add(journey);

	}

	public void endTrip(ExitPoints exitPoint)
			throws TripNotStartedException, EntryAndExitPointSameException,
			InsufficientBalanceInCardException {
		EntryPoints entryPoint = null;
		if (journies.size() > 0
				&& journies.get(journies.size() - 1).isInJourney())
			entryPoint = journies.get(journies.size() - 1).getEntryPoints();
		verifyEndTrip(entryPoint, exitPoint);
		Journey journey = journies.get(journies.size() - 1);
		journey.setExitPoints(exitPoint);
		journey.setInJourney(false);
		updateAdvanceAmount();

	}

	protected int getTotalBalanceInCards() {
		int totalbalance = 0;
		for (SmartCard card : smartCards) {
			totalbalance = totalbalance + card.getBalance();
		}
		return totalbalance;
	}

	private void verifyEndTrip(EntryPoints entryPoint, ExitPoints exitPoint)
			throws TripNotStartedException, EntryAndExitPointSameException {
		if (entryPoint == null)
			throw new TripNotStartedException();
		if (entryPoint.toString().substring(2)
				.equals(exitPoint.toString().substring(2))) {
			throw new EntryAndExitPointSameException();
		}
	}

	private void verifyStartTrip(EntryPoints entryPoint)
			throws EntryAndExitPointSameException,
			VehicleAlreadyInTripException, InsufficientBalanceInCardException {
		if (vehicleStillInJorney()) {
			throw new VehicleAlreadyInTripException();
		}
		verifyBalanceAtStartTrip();

	}

	protected void updateBalanceInCards(int amountToBeDeducted) throws InsufficientBalanceInCardException {
		for (SmartCard card : smartCards) {
			if (amountToBeDeducted == 0)
				break;
			int balanceInCard = card.getBalance();
			if (balanceInCard >= amountToBeDeducted) {
				card.setBalance(balanceInCard - amountToBeDeducted);
				amountToBeDeducted = 0;
				break;
			} else if (balanceInCard < amountToBeDeducted) {
				card.setBalance(0);
				amountToBeDeducted = amountToBeDeducted - balanceInCard;
			}

		}
		if (amountToBeDeducted != 0) {
			throw new InsufficientBalanceInCardException();
		}
	}

	protected void displayDetails(int totalBalance, int tripFare) {
		System.out.println(
				"Commuter Name: " + getCommuterName() + " ,balance deducted: "
						+ tripFare + " ,remaining balance: " + totalBalance);
	}

	private boolean vehicleStillInJorney() {
		if (journies.size() > 0
				&& journies.get(journies.size() - 1).isInJourney()) {
			journies.get(journies.size() - 1).setInJourney(false);
			return true;
		}
		return false;
	}
	
	protected ExitPoints getReachedPoint(EntryPoints entryPoint) {
		if (entryPoint == EntryPoints.EN1) {
			return ExitPoints.EX2;
		} else if (entryPoint == EntryPoints.EN2) {
			return ExitPoints.EX3;
		} else if (entryPoint == EntryPoints.EN3) {
			return ExitPoints.EX4;
		} else if (entryPoint == EntryPoints.EN4) {
			return ExitPoints.EX1;
		}
		return ExitPoints.UNSPECIFIED;
	}

	protected EntryPoints getNextEntryPoint(EntryPoints entryPoint) {
		if (entryPoint == EntryPoints.EN1) {
			return EntryPoints.EN2;
		} else if (entryPoint == EntryPoints.EN2) {
			return EntryPoints.EN3;
		} else if (entryPoint == EntryPoints.EN3) {
			return EntryPoints.EN4;
		} else if (entryPoint == EntryPoints.EN4) {
			return EntryPoints.EN1;
		}
		return EntryPoints.UNSPECIFIED;
	}

	protected int getDistanceBetween(EntryPoints entryPoint,
			ExitPoints exitPoint) {
		ExitPoints reached = null;
		int distance = 0;
		while (!exitPoint.equals(reached)) {

			for (String pointToPoint : FareAndDistance.distanceMap.keySet()) {
				if (pointToPoint.contains(entryPoint.toString())) {
					distance = distance
							+ FareAndDistance.distanceMap.get(pointToPoint);
					reached = getReachedPoint(entryPoint);
					entryPoint = getNextEntryPoint(entryPoint);
					break;
				}
			}
		}

		return distance;
	}
	
	public abstract void verifyBalanceAtStartTrip()
			throws InsufficientBalanceInCardException;
	public abstract void updateAdvanceAmount()
			throws InsufficientBalanceInCardException;
	protected abstract int getFare(EntryPoints entryPoint, ExitPoints exitPoint);

}
