package com.example.JournalApp.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.JournalApp.Repository.JournalEntryRepository;
import org.springframework.transaction.annotation.Transactional;
import com.example.JournalApp.entity.JournalEntry;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import org.bson.types.ObjectId;
import com.example.JournalApp.entity.User;
@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
   private UserService userService;


    public void saveEntry(JournalEntry journalEntry){        //post
        journalEntryRepository.save(journalEntry);
    }
    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){       //post
        User user = userService.findByUsername(userName);
        journalEntry.setDate(LocalDateTime.now());
       JournalEntry saved = journalEntryRepository.save(journalEntry);
       user.getJournalEntries().add(saved);
      // user.setUsername(null);        // make the method roll back...
       userService.saveEntry(user);
    }
    public List<JournalEntry> getAll(){                       //get
     return journalEntryRepository.findAll();

   }

   public void deleteById(ObjectId id){                        //deletebyid
        journalEntryRepository.deleteById(id);
   }

   public void deleteById(String userName, ObjectId id){                  //deletebyid
        User user = userService.findByUsername(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);
   }

   public User findByUsername(String username){
        return userService.findByUsername(username);
   }
   public Optional<JournalEntry> getById(ObjectId id){        //getbyid
         return journalEntryRepository.findById(id);
   }

//    public Optional<JournalEntry> updateById(ObjectId id, JournalEntry newEntry) {
//        Optional<JournalEntry> optionalOldEntry = journalEntryRepository.findById(id);
//
//        if (optionalOldEntry.isPresent()) {
//            JournalEntry oldEntry = optionalOldEntry.get();
//
//            // Update fields only if newEntry provides valid non-null, non-empty values
//            if (newEntry.getHeading() != null && !newEntry.getHeading().trim().isEmpty()) {
//                oldEntry.setHeading(newEntry.getHeading());
//            }
//
//            if (newEntry.getContent() != null && !newEntry.getContent().trim().isEmpty()) {
//                oldEntry.setContent(newEntry.getContent());
//            }
//
//            // Add more fields as needed
//            // e.g. oldEntry.setDate(newEntry.getDate()) if date can be updated
//
//            journalEntryRepository.save(oldEntry);
//            return Optional.of(oldEntry);
//        } else {
//            return Optional.empty(); // Entry not found
//        }
    }


