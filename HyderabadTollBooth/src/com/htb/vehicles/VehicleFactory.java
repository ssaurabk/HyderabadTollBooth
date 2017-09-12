package com.htb.vehicles;

import com.htb.vehicles.Vehicle;
import com.htb.vehicles.VehicleType;

public class VehicleFactory {
	public static Vehicle getVehicleIntance(VehicleType vehicleType,
			String commuterName, String vehicleNumber, int advancedAmount) {

		if (vehicleType.equals(VehicleType.LMV)) {
			return new LMV(commuterName, vehicleNumber, advancedAmount);
		} else if (vehicleType.equals(VehicleType.LCV)) {
			return new LCV(commuterName, vehicleNumber, advancedAmount);
		} else if (vehicleType.equals(VehicleType.VEHICLE_2345A)) {
			return new Vehicle2345A(commuterName, vehicleNumber,
					advancedAmount);
		} else if (vehicleType.equals(VehicleType.VEHICLE_67A)) {
			return new Vehicle67A(commuterName, vehicleNumber, advancedAmount);
		}
		throw new IllegalArgumentException("Please enter valid vehicle type");

	}
}
