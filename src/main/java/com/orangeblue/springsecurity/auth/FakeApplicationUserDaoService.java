package com.orangeblue.springsecurity.auth;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.Lists;
import com.orangeblue.springsecurity.security.ApplicationUserRoles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;


@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {

    private final PasswordEncoder passwordEncoder;


    @Autowired
    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUser().stream().filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }
    
    private List<ApplicationUser> getApplicationUser() {
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
            new ApplicationUser(
                "annasmith",
                passwordEncoder.encode("password1"),
                ApplicationUserRoles.STUDENT.getGrantedAuthorities(),
                true,
                true,
                true,
                true
            ),
            new ApplicationUser(
                "linda",
                passwordEncoder.encode("password2"),
                ApplicationUserRoles.ADMIN.getGrantedAuthorities(),
                true,
                true,
                true,
                true
            ),
            new ApplicationUser(
                "tom",
                passwordEncoder.encode("password3"),
                ApplicationUserRoles.ADMINTRAINEE.getGrantedAuthorities(),
                true,
                true,
                true,
                true
            )
        );

        return applicationUsers;
    }    

}
