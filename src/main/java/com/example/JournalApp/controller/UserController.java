package com.example.JournalApp.controller;
import java.util.*;

import com.example.JournalApp.entity.JournalEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.example.JournalApp.Service.UserService;
import com.example.JournalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import com.example.JournalApp.Repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userServices;
    @Autowired
    private UserRepository userRepository;




    @DeleteMapping
   public ResponseEntity<?> deleteUserByUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        userRepository.deleteByUsername(userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }


    @PutMapping
    public ResponseEntity<?> updatebyid (@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String userName = authentication.getName();
       Optional<User> userInDB= Optional.ofNullable(userServices.findByUsername(userName));
        if(userInDB.isPresent() ){
            User exist = userInDB.get();
          exist.setUsername(user.getUsername());
          exist.setPassword(user.getPassword());
            userServices.saveNewEntry(exist);
    }
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);

}

}