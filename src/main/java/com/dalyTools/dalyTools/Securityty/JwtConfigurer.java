package com.dalyTools.dalyTools.Securityty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

public class JwtConfigurer  extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public JwtConfigurer(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @Override
    public void configure(HttpSecurity builder) throws Exception {
        super.configure(builder);
    }
}
