package com.orangeblue.springsecurity.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class ApplicationUserService implements UserDetailsService {

    private final ApplicationUserDao applicationUserDao; // db가 바뀌어도 interface이기 때문에 

    @Autowired
    public ApplicationUserService(@Qualifier("fake") ApplicationUserDao applicationUserDao) {
        this.applicationUserDao = applicationUserDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        return applicationUserDao.selectApplicationUserByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
    }
    
}
