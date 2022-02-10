package org.iesfm.airline.repositories;

import org.iesfm.airline.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FlightRepository extends MongoRepository<Flight, Integer>{

    Flight getById(Integer flightId);

}
