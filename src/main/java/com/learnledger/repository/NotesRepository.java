package com.learnledger.repository;

import com.learnledger.model.Notes;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotesRepository extends MongoRepository<Notes, String>{
    List<Notes> findByTitleContainingIgnoreCase(String title);
}
