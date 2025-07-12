package com.example.JournalApp.Service;

import com.example.JournalApp.Repository.UserRepository;
import com.example.JournalApp.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    UserRepository userRepository;

   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user = userRepository.findByUsername(username);
      if(user!= null) {
          UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                  .username(user.getUsername())
                  .password(user.getPassword())
                  .roles(user.getRoles().toArray(new String[0]))
                  .build();
          return userDetails;
      } throw new UsernameNotFoundException("User Not Found"+ username);
   }


}
