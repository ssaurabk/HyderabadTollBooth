package com.htb.constants;

import java.util.HashMap;
import java.util.Map;

import com.htb.vehicles.VehicleType;

public class FareAndDistance {
	
	public static Map<String, Integer> lmvFareMap;
	public static Map<String, Integer> lcvFareMap;
	public static Map<String, Integer> distanceMap;
	public static Map<VehicleType, Integer> minimumBalance;
	
	public static final int VEHICLE_2345A_BASE_FARE = 100;
	public static final int VEHICLE_67A_BASE_FARE = 150;
	public static final int VEHICLE_2345A_PER_KM_FARE = 5;
	public static final int VEHICLE_67A_PER_KM_FARE = 7;

	public static final int DISCOUNT = 0;
	
	public static void updateMaps() {
		lmvFareMap = new HashMap<String, Integer>();
		lcvFareMap = new HashMap<String, Integer>();
		distanceMap = new HashMap<String, Integer>();
		minimumBalance = new HashMap<VehicleType,Integer>();
		
		// Update LMV fare
		lmvFareMap.put("EN1-EX2", 60);
		lmvFareMap.put("EN2-EX3", 50);
		lmvFareMap.put("EN3-EX4", 45);
		lmvFareMap.put("EN4-EX1", 40);

		// Update LCM fare
		lcvFareMap.put("EN1-EX2", 100);
		lcvFareMap.put("EN2-EX3", 90);
		lcvFareMap.put("EN3-EX4", 80);
		lcvFareMap.put("EN4-EX1", 70);

		// Update distance map.
		distanceMap.put("EN1-EX2", 55);
		distanceMap.put("EN2-EX3", 45);
		distanceMap.put("EN3-EX4", 40);
		distanceMap.put("EN4-EX1", 41);
		
		//Update Minimum Balance
		minimumBalance.put(VehicleType.LMV, 40);
		minimumBalance.put(VehicleType.LCV, 70);
		minimumBalance.put(VehicleType.VEHICLE_2345A, 300);
		minimumBalance.put(VehicleType.VEHICLE_67A, 430);

	}
}
