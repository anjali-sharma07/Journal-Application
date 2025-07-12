package com.example.JournalApp.Service;

import com.example.JournalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.JournalApp.Repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.example.JournalApp.entity.User;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;


@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveEntry(User users) {        //post

        userRepository.save(users);
    }

    public void saveNewEntry(User user) {        //post
          user.setPassword(passwordEncoder.encode(user.getPassword()));
          user.setRoles(Arrays.asList("USER"));
          userRepository.save(user);
    }
    public List<User> getAll() {                       //get
        return userRepository.findAll();

    }
    public void deleteById(ObjectId id) {                        //deletebyid
       userRepository.deleteById(id);
    }

    public Optional<User> getById(ObjectId id) {        //getbyid
        return userRepository.findById(id);
    }

    public User findByUsername(String username){           //put
        return userRepository.findByUsername(username);
    }

    public void saveAdmin(User user) {        //post
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);


    }

}










