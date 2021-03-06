package org.iesfm.airline.controllers;

import org.iesfm.airline.Case;
import org.iesfm.airline.Passenger;
import org.iesfm.airline.PassengerId;
import org.iesfm.airline.exceptions.FlightNotFoundException;
import org.iesfm.airline.exceptions.PassengerNotFoundException;
import org.iesfm.airline.service.PassengerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class PassengerController {

    private final PassengerService passengerService;


    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    public PassengerService getPassengerService() {
        return passengerService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/flights/{flightId}/passengers")
    public List<Passenger> listPassengers(@PathVariable(name = "flightId") Integer flightId) throws FlightNotFoundException {
        if (passengerService.getById(flightId) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No encontrado el vuelo");
        }
        return passengerService.getPassengersByFlight(flightId);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/flights/{flightId}/passengers")
    public void insertPassenger(@RequestBody Passenger passenger,
                                @PathVariable(name = "flightId") Integer flightId) {
        if (passengerService.getById(flightId) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No encontrado el vuelo");
        } else {
            try {
                passengerService.insertPassenger(passenger);
            } catch (FlightNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/flights/{flightId}/passengers/{nif}/cases")
    public List<Case> listCases(@PathVariable(name = "flightId") int flightId,
                                @PathVariable(name = "nif") String nif) {
        if (passengerService.getById(flightId) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No encontrado el vuelo");
        } else if (passengerService.findByPassengerId(new PassengerId(nif, flightId)) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No encontrado el pasajero");
        } else {
            return passengerService.findCases(new PassengerId(nif, flightId));
        }
        }


    @RequestMapping(method = RequestMethod.PUT, path = "/flights/{flightId}/passengers/{nif}/cases")
    public void insertCase(@RequestBody Case luggage,
                           @PathVariable(name = "flightId") int flightId,
                           @PathVariable(name = "nif") String nif) {
        if(!passengerService.findByPassengerId(new PassengerId(nif, flightId)).getCases().contains(luggage))
        try {
            passengerService.insertCase(luggage, nif, flightId);
        } catch (FlightNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No encontrado el vuelo");
        } catch (PassengerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No encontrado el pasajero");
        }
        else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe la maleta");
        }


    }

}
