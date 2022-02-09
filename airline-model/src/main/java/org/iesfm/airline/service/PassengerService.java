package org.iesfm.airline.service;

import org.iesfm.airline.Passenger;
import org.iesfm.airline.exceptions.FlightNotFoundException;
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

    public List<Passenger> listPassengersFromFlight(int flight_id) throws FlightNotFoundException {
        if (!flightRepository.existsById(flight_id)) {
            throw new FlightNotFoundException();
        }
        return passengerRepository.findAll();
    }

    
}
