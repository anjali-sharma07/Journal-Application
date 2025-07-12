//package com.example.JournalApp.controller;
//import com.example.JournalApp.Service.JournalEntryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.example.JournalApp.entity.JournalEntry;
//import org.springframework.web.bind.annotation.RequestBody;
//import java.time.LocalDateTime;
//import java.util.*;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.bson.types.ObjectId;
//import org.springframework.web.bind.annotation.PutMapping;;
//
//@RestController
//@RequestMapping("/journalproject")
//public class JournalEntryController {
//
//    @Autowired
//    private JournalEntryService journalEntryService;
//
////    @PostMapping
////    public JournalEntry Post(@RequestBody JournalEntry myEntry){
////        myEntry.setDate(LocalDateTime.now());
////       journalEntryService.saveEntry(myEntry);
////       return myEntry;
////    }
//
//    @PostMapping
//    public ResponseEntity<JournalEntry> Post(@RequestBody JournalEntry myEntry){
//        try{
//            myEntry.setDate(LocalDateTime.now());
//            journalEntryService.saveEntry(myEntry);
//            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
//        }catch(Exception e){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
////    @GetMapping
////    public List<JournalEntry> Get() {
////        return journalEntryService.getAll();
////    }
//
//    @GetMapping
//    public ResponseEntity<?> get(){
//       List<JournalEntry> journalEntry = journalEntryService.getAll();
//       if(journalEntry != null) return new ResponseEntity<>(journalEntry, HttpStatus.OK);
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
//
////     @DeleteMapping("id/{myId}")
////     public boolean Delete(@PathVariable ObjectId myId){
////           journalEntryService.deleteById(myId);
////           return true;
////     }
//
//    @DeleteMapping
//    public ResponseEntity<?> Delete(@PathVariable ObjectId myId){
//        journalEntryService.deleteById(myId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
////     @GetMapping("id/{myId}")
////     public JournalEntry GetById(@PathVariable ObjectId myId){
////         return journalEntryService.getById(myId).orElse(null);
////     }
//
//    @GetMapping("id/{myId}")
//    public ResponseEntity<JournalEntry> GetById(@PathVariable ObjectId myId){
//        Optional<JournalEntry>  journalEntry= journalEntryService.getById(myId);
//        if(journalEntry.isPresent()){
//            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
//        } return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//
//
//    @PutMapping("id/{myId}")
//    public JournalEntry updatebyid(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry){
//        JournalEntry old = journalEntryService.getById(myId).orElse(null);
//        if(old != null ){
//            newEntry.setHeading(newEntry.getHeading() != null && !newEntry.getHeading().equals(" ") ?
//                    newEntry.getHeading(): old.getHeading());
//            newEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals(" ")?
//                    newEntry.getContent() : old.getContent());
//        }
//         journalEntryService.saveEntry(old);
//        return old;
//     }
//}
