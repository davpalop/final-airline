package org.iesfm.airline.controllers;

import org.iesfm.airline.Case;
import org.iesfm.airline.PassengerId;
import org.iesfm.airline.exceptions.FlightNotFoundException;
import org.iesfm.airline.exceptions.PassengerNotFoundException;
import org.iesfm.airline.service.CaseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CaseController {

    private CaseService caseService;

    public CaseController(CaseService caseService) {
        this.caseService = caseService;
    }

    public CaseService getCaseService() {
        return caseService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/flights/{flight_id}/passengers/{nif}/cases")
    public List<Case> listCases(@RequestParam(name = "flight_id") int flightId,
                                @RequestParam(name="nif") String nif) {
        try {
            return caseService.listCases(flightId, nif);
        } catch (PassengerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No encontrado el pasajero");
        }
    }
    @RequestMapping(method = RequestMethod.POST, path = "/flights/{flight_id}/passengers/{nif}/cases")
    public void insertCase(@RequestBody Case luggage,
                           @RequestParam(name = "nif") String nif,
                           @RequestParam(name = "flight_id") int flightId) {
        try {
            if(!getCaseService().insertCase(luggage, new PassengerId(nif, flightId))) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existia el equipaje");
            } else {
                throw new ResponseStatusException(HttpStatus.CREATED, "Ya existia el equipaje");
            }
        } catch (PassengerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado el pasajero");
        }

    }
}
