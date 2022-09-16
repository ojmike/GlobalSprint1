package com.globalsprint.globalsprint1.config.security.security;

import com.globalsprint.globalsprint1.config.security.security.MyUserDetails;
import com.globalsprint.globalsprint1.model.User;
import com.globalsprint.globalsprint1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        user.orElseThrow(() -> new UsernameNotFoundException(String.format("Not found %s",username)));
        return user.map(MyUserDetails::new).get();
    }
}
