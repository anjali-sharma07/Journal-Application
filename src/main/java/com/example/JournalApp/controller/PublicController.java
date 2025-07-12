package com.example.JournalApp.controller;

import com.example.JournalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.JournalApp.Service.UserService;



@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userServices;

    @PostMapping("/create")
    public boolean post(@RequestBody User user){
        userServices.saveNewEntry(user);
        return true;
    }


}
