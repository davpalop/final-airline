package org.iesfm.airline.repositories;
import org.iesfm.airline.Passenger;
import org.iesfm.airline.PassengerId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PassengerRepository extends MongoRepository<Passenger, PassengerId>{

}
