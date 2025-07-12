package com.example.JournalApp.controller;

import com.example.JournalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import com.example.JournalApp.Service.UserService;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
   private UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){
       List<User> all = userService.getAll();
       if(!all.isEmpty()&& all != null){
           return new ResponseEntity<>(all,HttpStatus.OK);
       }return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin")
    public ResponseEntity<?> postadmin(@RequestBody User user){
        userService.saveAdmin(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
