package org.iesfm.airline.repositories;
import org.iesfm.airline.Case;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CaseRepository extends MongoRepository<Case, Integer>{
}
