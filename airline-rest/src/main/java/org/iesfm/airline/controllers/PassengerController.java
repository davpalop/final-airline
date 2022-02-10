package org.iesfm.airline.controllers;

import org.iesfm.airline.Passenger;
import org.iesfm.airline.PassengerId;
import org.iesfm.airline.exceptions.FlightNotFoundException;
import org.iesfm.airline.service.PassengerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class PassengerController {

    private PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    public PassengerService getPassengerService() {
        return passengerService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/flights/{flight_id}/passengers")
    public List<Passenger> listPassengers(@RequestParam(name = "flight_id") int flightId) {
        try {
            return passengerService.listPassengersFromFlight(flightId);
        } catch (FlightNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No encontrado el vuelo");
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/flights/{flight_id}/passengers")
    public void insertPassenger(@RequestBody Passenger passenger,
                                @RequestParam(name = "flight_id") int flightId) {
        try {
            if (!passengerService.insertPassenger(passenger, flightId)) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existia el pasajero");
            } else {
                throw new ResponseStatusException(HttpStatus.CREATED, "Ya xistia el pasajero");
            }
        } catch (FlightNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vuelo no encontrado");
        }
    }
}
