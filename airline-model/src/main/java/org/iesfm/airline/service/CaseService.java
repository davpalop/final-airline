package org.iesfm.airline.service;

import org.iesfm.airline.Case;
import org.iesfm.airline.PassengerId;
import org.iesfm.airline.exceptions.PassengerNotFoundException;
import org.iesfm.airline.repositories.CaseRepository;
import org.iesfm.airline.repositories.FlightRepository;
import org.iesfm.airline.repositories.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseService {

    private FlightRepository flightRepository;
    private PassengerRepository passengerRepository;
    private CaseRepository caseRepository;

    public CaseService(FlightRepository flightRepository, PassengerRepository passengerRepository, CaseRepository caseRepository) {
        this.flightRepository = flightRepository;
        this.passengerRepository = passengerRepository;
        this.caseRepository = caseRepository;
    }

    public boolean insertCase(Case backpack, PassengerId passengerId) throws PassengerNotFoundException {
        if (!passengerRepository.existsById(passengerId)) {
            throw new PassengerNotFoundException();
        }
        caseRepository.insert(backpack);
        return true;
    }

    public List<Case> listCases(PassengerId passengerId) throws PassengerNotFoundException {
        if (!passengerRepository.existsById(passengerId)) {
            throw new PassengerNotFoundException();
        }
        return caseRepository.findAll();
    }

}
