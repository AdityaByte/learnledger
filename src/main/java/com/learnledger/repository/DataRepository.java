package com.learnledger.repository;

import com.learnledger.enums.DocumentType;
import java.util.List;
import com.learnledger.model.DataDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DataRepository extends MongoRepository<DataDocument, String> {
    List<DataDocument> findByTypeofDocument(DocumentType typeofDocument);
    Long countByTypeofDocument(DocumentType typeofDocument);
}

