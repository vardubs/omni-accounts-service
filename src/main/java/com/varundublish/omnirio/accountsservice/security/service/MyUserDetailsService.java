package com.varundublish.omnirio.accountsservice.security.service;

import com.varundublish.omnirio.accountsservice.client.CustomerServiceFeignClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LogManager.getLogger(MyUserDetailsService.class);

    @Autowired
    CustomerServiceFeignClient customerServiceClient;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return null;
    }
}