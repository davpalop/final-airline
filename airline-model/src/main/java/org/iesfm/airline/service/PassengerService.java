package org.iesfm.airline.service;

import org.iesfm.airline.*;
import org.iesfm.airline.exceptions.FlightNotFoundException;
import org.iesfm.airline.exceptions.PassengerNotFoundException;
import org.iesfm.airline.repositories.FlightRepository;
import org.iesfm.airline.repositories.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {

    private final PassengerRepository passengerRepository;
    private final FlightRepository flightRepository;

    public PassengerService(PassengerRepository passengerRepository, FlightRepository flightRepository) {
        this.passengerRepository = passengerRepository;
        this.flightRepository = flightRepository;
    }

    public List<Passenger> getPassengersByFlight(Integer flightId) throws FlightNotFoundException {
        if (!flightRepository.existsById(flightId)) {
            throw new FlightNotFoundException();
        }
        return passengerRepository.findByPassengerIdFlightId(flightId);
    }

    public void insertPassenger(Passenger passenger) throws FlightNotFoundException {
        if (!flightRepository.existsById(passenger.getPassengerId().getFlightId())) {
            throw new FlightNotFoundException();
        }
        passengerRepository.insert(passenger);
    }

    public void insertCase(Case backpack, String nif, Integer flightId) throws FlightNotFoundException, PassengerNotFoundException {
        if (!flightRepository.existsById(flightId)) {
            throw new FlightNotFoundException();
        } else if (!passengerRepository.existsById(new PassengerId(nif, flightId))) {
            throw new PassengerNotFoundException();
        }
        Passenger passenger = passengerRepository.findByPassengerId(new PassengerId(nif, flightId));
        passenger.getCases().add(backpack);
        passengerRepository.save(passenger);
    }

    public List<Case> findCases(PassengerId passengerId) {
        if (!flightRepository.existsById(passengerId.getFlightId())) {
            return null;
        } else if (!passengerRepository.existsById(passengerId)) {
            return null;
        } else {
            return passengerRepository.findByPassengerId(passengerId).getCases();
        }
    }

    public Passenger findByPassengerId(PassengerId passengerId) {
        return passengerRepository.findByPassengerId(passengerId);
    }

    public Flight getById(int flight_id) {
        return flightRepository.getById(flight_id);
    }

}
