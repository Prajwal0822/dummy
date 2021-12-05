package com.gvnc.sample.web.identity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.stereotype.Service;
import com.gvnc.sample.web.model.user.User;
import com.gvnc.sample.web.repository.UserRepository;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

    @Override
    public final TokenUser loadUserByUsername(String username) throws UsernameNotFoundException, DisabledException {

        final User user = userRepository.findOneByUserId(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        TokenUser currentUser;
        currentUser = new TokenUser(user);

        detailsChecker.check(currentUser);
        return currentUser;
    }
}
