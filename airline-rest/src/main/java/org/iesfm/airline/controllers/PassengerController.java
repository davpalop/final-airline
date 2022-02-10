package org.iesfm.airline.controllers;

import org.iesfm.airline.Passenger;
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
        }
        try {
            passengerService.insertPassenger(passenger, flightId);
        } catch (FlightNotFoundException e) {
            e.printStackTrace();
        }
    }




//
//    @RequestMapping(method = RequestMethod.GET, path = "/flights/{flight_id}/passengers/{nif}/cases")
//    public List<Case> listCases(@RequestParam(name = "flight_id") int flightId,
//                                @RequestParam(name = "nif") String nif) {
//        if (passengerService.getById(flightId) == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No encontrado el vuelo");
//        } else if (passengerService.getByPassengerId(new PassengerId(nif, flightId)) == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No encontrado el pasajero");
//        }
//        return passengerService.getByCases(new PassengerId(nif, flightId));
//        }
//
//
//    @RequestMapping(method = RequestMethod.POST, path = "/flights/{flight_id}/passengers/{nif}/cases")
//    public void insertCase(@RequestBody Case luggage,
//                           @RequestParam(name = "nif") String nif,
//                           @RequestParam(name = "flight_id") int flightId) {
//        if (passengerService.getById(flightId) == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No encontrado el vuelo");
//        } else if (passengerService.getByPassengerId(new PassengerId(nif, flightId)) == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No encontrado el pasajero");
//        } else if (passengerService.getByCases(new PassengerId(nif, flightId)) != null){
//            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existia el equipaje");
//        }
//        passengerService.getByCases(new PassengerId(nif, flightId)).add(luggage);
//    }

}
