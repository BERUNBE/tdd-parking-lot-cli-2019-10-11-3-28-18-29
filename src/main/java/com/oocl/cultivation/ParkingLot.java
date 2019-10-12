package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final int capacity;
    private Map<ParkingTicket, Car> cars = new HashMap<>();

    public ParkingLot() {
        this(10);
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public int getAvailableParkingPosition() {
        return cars.size() - capacity;
    }

    public ParkingTicket addCar(Car car) {
        if (cars.size() < capacity) {
            ParkingTicket parkingTicket = new ParkingTicket();
            cars.put(parkingTicket, car);
            return parkingTicket;
        } else {
            System.out.print("Not enough position.");
            return null;
        }
    }

    public Map<ParkingTicket, Car> getCars() {
        return cars;
    }

    public void removeCarWithTicket(ParkingTicket ticket) {
        cars.remove(ticket);
    }
}
