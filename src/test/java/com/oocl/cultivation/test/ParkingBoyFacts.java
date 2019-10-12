package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyFacts {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

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

    @Test
    void parking_boy_should_fetch_no_car_if_given_used_ticket() {
        ParkingBoy utusan = new ParkingBoy(new ParkingLot());
        Car car = new Car();
        ParkingTicket ticket = utusan.park(car);

        Car fetchedCar = utusan.fetch(ticket);
        Car fetchedCarAgain = utusan.fetch(ticket);

        assertThat(fetchedCar, is(notNullValue()));
        assertThat(fetchedCarAgain, is(nullValue()));
    }

    @Test
    void parking_boy_should_not_park_car_if_exceed_capacity() {
        ParkingBoy utusan = new ParkingBoy(new ParkingLot());

        for(int i = 0; i < 10; i++) {
            utusan.park(new Car());
        }

        ParkingTicket ticket = utusan.park(new Car());

        assertThat(ticket, is(nullValue()));
    }

    @Test
    void should_output_error_message_when_given_invalid_ticket() {
        System.setOut(new PrintStream(outContent));
        ParkingBoy utusan = new ParkingBoy(new ParkingLot());

        Car fetchedCar = utusan.fetch(new ParkingTicket());

        String result = outContent.toString();

        assertThat(fetchedCar, is(nullValue()));
        assertThat(result, is("Unrecognized parking ticket."));
    }

    @Test
    void should_output_error_message_when_given_no_ticket() {
        System.setOut(new PrintStream(outContent));
        ParkingBoy utusan = new ParkingBoy(new ParkingLot());

        Car fetchedCar = utusan.fetch(null);

        String result = outContent.toString();

        assertThat(fetchedCar, is(nullValue()));
        assertThat(result, is("Please provide your parking ticket."));
    }

    @Test
    void should_output_error_message_if_parking_lot_exceed_capacity() {
        System.setOut(new PrintStream(outContent));
        ParkingBoy utusan = new ParkingBoy(new ParkingLot());

        for(int i = 0; i < 10; i++) {
            utusan.park(new Car());
        }

        ParkingTicket ticket = utusan.park(new Car());

        String result = outContent.toString();

        assertThat(ticket, is(nullValue()));
        assertThat(result, is("Not enough position."));

    }
}
