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

    private PassengerRepository passengerRepository;
    private CaseRepository caseRepository;

    public CaseService(PassengerRepository passengerRepository, CaseRepository caseRepository) {
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

    public List<Case> listCases(int flightId, String nif) throws PassengerNotFoundException {
        if (!passengerRepository.existsById(new PassengerId(nif, flightId))) {
            throw new PassengerNotFoundException();
        }
        return caseRepository.findAll();
    }

}
