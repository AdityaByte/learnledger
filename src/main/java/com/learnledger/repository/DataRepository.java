package com.learnledger.repository;

import java.util.List;
import com.learnledger.model.DataDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DataRepository extends MongoRepository<DataDocument, String> {
    List<DataDocument> findByTypeofDocument(String typeofDocument);
}

