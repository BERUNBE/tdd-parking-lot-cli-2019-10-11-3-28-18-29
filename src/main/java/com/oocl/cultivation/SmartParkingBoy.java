package com.oocl.cultivation;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy{

    private final List<ParkingLot> parkingLotList;
    private String lastErrorMessage;

    public SmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
        this.parkingLotList = parkingLotList;
    }

    @Override
    public ParkingTicket park(Car car) {
        ParkingLot parkingLot = parkingLotList.stream()
                .filter(aParkingLot -> aParkingLot.getAvailableParkingPosition() > 0)
                .max(Comparator.comparing(ParkingLot::getAvailableParkingPosition))
                .orElse(null);

        if (parkingLot == null) {
            lastErrorMessage = "Not enough position.";
            return null;
        }

        return parkingLot.addCar(car);
    }
}
