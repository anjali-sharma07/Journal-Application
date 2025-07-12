package com.example.JournalApp.Repository;
import com.example.JournalApp.entity.JournalEntry;
import com.example.JournalApp.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.bson.types.ObjectId;
@Component
public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {


}

// controller ---> services ---> repository