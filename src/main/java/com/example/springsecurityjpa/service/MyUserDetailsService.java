package com.example.springsecurityjpa.service;

import com.example.springsecurityjpa.model.MyUserDetails;
import com.example.springsecurityjpa.model.User;
import com.example.springsecurityjpa.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    /**
     *
     * @param username
     * @return UserDetails
     * Since it returns UserDetails so we need to create our own custom UserDetails implementing
     * already present one.
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(username);
        System.out.println(user.isPresent());
        user.orElseThrow(()-> new UsernameNotFoundException("Not found: "+ username));
        return user.map(MyUserDetails::new).get();


    }
}
