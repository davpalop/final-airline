package org.iesfm.airline.repositories;
import org.iesfm.airline.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PassengerRepository extends MongoRepository<Passenger, PassengerId>{

    @Query("{'_id.flightId' : ?0}")
    List<Passenger> findByFlight(Integer flightId);

    List<Case> cases(PassengerId passengerId);

    Passenger findByPassengerId(PassengerId passengerId);



}
