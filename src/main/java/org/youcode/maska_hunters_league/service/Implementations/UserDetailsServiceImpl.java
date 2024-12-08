package org.youcode.maska_hunters_league.service.Implementations;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.youcode.maska_hunters_league.config.CustomUserDetails;
import org.youcode.maska_hunters_league.domain.entities.User;
import org.youcode.maska_hunters_league.repository.UserRepository;
import org.slf4j.Logger;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        logger.debug("Entering in loadUserByUsername Method...");

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    logger.error("Email not found: " + email);
                    return new UsernameNotFoundException("User not found with email: " + email);
                });

        logger.info("User Authenticated Successfully: " + user.toString());
        return new CustomUserDetails(user);
    }
}

