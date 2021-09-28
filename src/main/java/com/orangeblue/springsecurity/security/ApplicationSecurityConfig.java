package com.orangeblue.springsecurity.security;

import static com.orangeblue.springsecurity.security.ApplicationUserPermission.*;
import static com.orangeblue.springsecurity.security.ApplicationUserRoles.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder; 
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                // .csrf().disable() // cross site request forgery
                .authorizeRequests() // 요청이 올 때마다 인증
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll() // 이런 주소를 가진 곳에 요청이 갈 때 . 모두 인증이 필요 없다.
                .antMatchers("/api/**").hasRole(STUDENT.name()) // STUDENT ROLES만 /api/~~ 에 해당하는 url에 접근 가능
                // .antMatchers(HttpMethod.GET, "/management/api/**").hasAnyRole(ADMIN.name(), ADMINTRAINEE.name()) // antMatchers의 순서가 중요함.
                // .antMatchers(HttpMethod.DELETE , "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
                // .antMatchers(HttpMethod.POST , "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
                // .antMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
                .anyRequest().authenticated() // 어떤 요청이든 인증을 진행하고
                .and() // 그리고
                .httpBasic(); // httpBasic로그인 방식으로 진행
 
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
            UserDetails annaSmithUser = User.builder()
                                            .username("annasmith")
                                            .password(passwordEncoder.encode("password1")) // password는 encode를 통해 변환해줘야한다.
                                            // .roles(STUDENT.name()) // ROLE_STUDENT
                                            .authorities(STUDENT.getGrantedAuthorities())
                                            .build();
                                            
                    
            UserDetails lindaUser = User.builder()
                                        .username("linda")
                                        .password(passwordEncoder.encode("password2"))
                                        // .roles(ADMIN.name()) // ROLES_ADMIN
                                        .authorities(ADMIN.getGrantedAuthorities())
                                        .build();

            UserDetails tomUser = User.builder()
                                        .username("tom")
                                        .password(passwordEncoder.encode("password3"))
                                        // .roles(ADMINTRAINEE.name()) // ROLES_ADMIN
                                        .authorities(ADMINTRAINEE.getGrantedAuthorities())
                                        .build();
        
        
            return new InMemoryUserDetailsManager(
            annaSmithUser,
            lindaUser,
            tomUser
        );
    }
    
    
    
}
