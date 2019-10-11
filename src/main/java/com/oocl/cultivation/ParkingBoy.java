package com.oocl.cultivation;

public class ParkingBoy {

    private final ParkingLot parkingLot;
    private String lastErrorMessage;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        return parkingLot.addCar(car);
    }

    public Car fetch(ParkingTicket ticket) {
        Car car = parkingLot.getCars().get(ticket);
        parkingLot.removeCarWithTicket(ticket);
        return car;
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }
}
