package org.iesfm.airline.repositories;
import org.iesfm.airline.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PassengerRepository extends MongoRepository<Passenger, PassengerId>{

    Passenger getByPassengerId(PassengerId passengerId);

    List<Case> getByCases(PassengerId passengerId);

    @Query("{flightId : flightId}")
    List<Passenger> getPassengersByFlight(Integer FlightId);



}
