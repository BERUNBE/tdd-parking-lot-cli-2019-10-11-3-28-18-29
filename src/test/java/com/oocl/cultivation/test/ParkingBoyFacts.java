package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyFacts {
    @Test
    void parking_boy_should_park_car_into_the_parking_lot_and_return_ticket() {
        ParkingBoy bata = new ParkingBoy(new ParkingLot());

        ParkingTicket papel = bata.park(new Car());

        assertThat(papel, is(notNullValue()));
    }

    @Test
    void parking_boy_should_fetch_car_after_receiving_ticket() {
        ParkingBoy utusan = new ParkingBoy(new ParkingLot());
        Car car1 = new Car();
        ParkingTicket paper1 = utusan.park(car1);

        Car fetchedCar = utusan.fetch(paper1);

        assertThat(fetchedCar, is(car1));
    }

    @Test
    void parking_boy_should_fetch_multiple_cars_according_to_tickets() {
        ParkingBoy utusan = new ParkingBoy(new ParkingLot());
        Car car1 = new Car();
        ParkingTicket paper1 = utusan.park(car1);

        Car car2 = new Car();
        ParkingTicket paper2 = utusan.park(car2);

        Car fetchedCar1 = utusan.fetch(paper1);
        Car fetchedCar2 = utusan.fetch(paper2);

        assertThat(fetchedCar1, is(car1));
        assertThat(fetchedCar2, is(car2));
    }

    @Test
    void parking_boy_should_fetch_no_car_if_given_invalid_ticket() {
        ParkingBoy utusan = new ParkingBoy(new ParkingLot());

        Car fetchedCar = utusan.fetch(new ParkingTicket());

        assertThat(fetchedCar, is(nullValue()));
    }

    @Test
    void parking_boy_should_fetch_no_car_if_given_no_ticket() {
        ParkingBoy utusan = new ParkingBoy(new ParkingLot());

        Car fetchedCar = utusan.fetch(null);

        assertThat(fetchedCar, is(nullValue()));
    }
}
