package org.iesfm.airline.service;

import org.iesfm.airline.Flight;
import org.iesfm.airline.exceptions.FlightNotFoundException;
import org.iesfm.airline.repositories.FlightRepository;
import org.iesfm.airline.repositories.PassengerRepository;

import java.util.List;

public class FlightService {

    private FlightRepository flightRepository;
    private PassengerRepository passengerRepository;

    public FlightService(FlightRepository flightRepository, PassengerRepository passengerRepository) {
        this.flightRepository = flightRepository;
        this.passengerRepository = passengerRepository;
    }

    public List<Flight> listFlights() {
        return flightRepository.findAll();
    }

    public boolean insertFlight(Flight flight) throws FlightNotFoundException {
        if (flightRepository.existsById(flight.getId())) {
            throw new FlightNotFoundException();
        }
        flightRepository.insert(flight);
        return true;
    }

}
