package epam.com.springBoot.service.impl;

import epam.com.springBoot.exceptions.UserNotFoundException;
import epam.com.springBoot.model.CustomUserDetails;
import epam.com.springBoot.model.User;
import epam.com.springBoot.repository.UserRepository;
import epam.com.springBoot.service.ActivityService;
import epam.com.springBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service(value = "userDetailsServiceImpl")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> {
            throw new UserNotFoundException();
        });
        return new CustomUserDetails(user);
    }
}
