package com.example.JournalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.example.JournalApp.Service.JournalEntryService;
import com.example.JournalApp.Service.UserService;
import com.example.JournalApp.entity.JournalEntry;
import java.util.*;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import com.example.JournalApp.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/journal")
public class UserJournalEntryController {
    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getJournalEntryFromUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        List<JournalEntry> all = user.getJournalEntries();
        if (all != null) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    public ResponseEntity<?> postJournalEntryOfUser(@RequestBody JournalEntry journalEntry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        journalEntryService.saveEntry(journalEntry, username);
        if (journalEntry != null) {
            return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<?> getJournalEntryOfUserById(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = journalEntryService.findByUsername(username);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myId))
                .collect(Collectors.toList());
        if (!collect.isEmpty()) {
            Optional<JournalEntry> journalEntry = journalEntryService.getById(myId);
            if (journalEntry.isPresent()) {
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> DeleteById(@PathVariable ObjectId myId) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            journalEntryService.deleteById(username, myId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<?> put(
            @PathVariable ObjectId myId,
            @RequestBody JournalEntry newEntry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = journalEntryService.findByUsername(username);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myId))
                .collect(Collectors.toList());
        if (!collect.isEmpty()) {
            Optional<JournalEntry> journalEntry = journalEntryService.getById(myId);
            if (journalEntry.isPresent()) {
                JournalEntry old = journalEntryService.getById(myId).orElse(null);
                if (old != null) {
                    old.setHeading(newEntry.getHeading() != null && !newEntry.getHeading().equals(" ") ?
                            newEntry.getHeading() : old.getHeading());
                    old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals(" ") ?
                            newEntry.getContent() : old.getContent());


                }
                journalEntryService.saveEntry(old);
                  return new ResponseEntity<>(old, HttpStatus.OK);
            }
        }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}