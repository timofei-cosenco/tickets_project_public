package com.example.usm.tickets_project.security;

import com.example.usm.tickets_project.model.Employee;
import com.example.usm.tickets_project.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public UserDetailsServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String login) {
        Employee user = employeeRepository.findByLogin(login);
        if ( user == null){
            throw new UsernameNotFoundException("User does not exist");
        }
        return SecurityUser.fromUser(user);
    }
}
