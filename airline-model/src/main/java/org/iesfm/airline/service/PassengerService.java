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

    private PassengerRepository passengerRepository;
    private FlightRepository flightRepository;

    public PassengerService(PassengerRepository passengerRepository, FlightRepository flightRepository) {
        this.passengerRepository = passengerRepository;
        this.flightRepository = flightRepository;
    }

    public List<Passenger> getPassengersByFlight(Integer flightId) throws FlightNotFoundException {
        if (!flightRepository.existsById(flightId)) {
            throw new FlightNotFoundException();
        }
        return passengerRepository.findByFlight(flightId);
    }

    public boolean insertPassenger(Passenger passenger, Integer flightId) throws FlightNotFoundException {
        if (!flightRepository.existsById(flightId)) {
            throw new FlightNotFoundException();
        }
        passengerRepository.insert(passenger);
        return true;
    }

        public boolean insertCase(Case backpack, int flight_id, String nif) {
        if (!flightRepository.existsById(flight_id)) {
            return false;
        } else if (passengerRepository.existsById(new PassengerId(nif, flight_id))) {
            return false;
        }
        Passenger passenger = passengerRepository.findById(new PassengerId(nif, flight_id)).get();
        passenger.getCases().add(backpack);
        return true;
    }

    public List<Case> getCases(int flightId, String nif) {
        if (!flightRepository.existsById(flightId)){
            return null;
        } else if (!passengerRepository.existsById(new PassengerId(nif, flightId))) {
            return null;
        } else {
            return passengerRepository.getCases(new PassengerId(nif, flightId));
        }
    }

    public Passenger findByPassengerId(PassengerId passengerId) {
        return passengerRepository.findByPassengerId(passengerId);
    }

    public Flight getById(int flight_id) {
        return flightRepository.getById(flight_id);
    }

}
