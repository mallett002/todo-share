package com.task.todoshare.controllers;

import com.task.todoshare.dto.AuthenticationRequest;
import com.task.todoshare.dto.AuthenticationResponse;
import com.task.todoshare.services.JwtUserDetailsService;
import com.task.todoshare.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResponse> createAuthorizationToken(@RequestBody AuthenticationRequest authRequest) throws Exception {
        authenticate(authRequest.getUsername(), authRequest.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    username,
                    password
                )
            );
        } catch (DisabledException e) {
            throw new Exception("User has been disabled", e);
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
    }

//    @RequestMapping(value = "/unauthenticate")
//    public ResponseEntity<?> unauthenticateUser() {
        /*
        https://stackoverflow.com/questions/43569723/jwt-authentication-how-to-implement-logout
        Options:
        1- No endpoint needed. Just remove the jwt from the client
        2- Store jwt in database (kind of defeats the purpose of stateless jwt though
        3- Short token lifetime
        4- Rotate tokens. Refresh tokens. Give Jwt & refresh token, store refresh token in DB.
            -Authenticated requests: Client uses Jwt
            -When token expires (or about to) client requests with refresh token in exchange for new JWT.
            -When log out, invalidate stored refresh token (or someone could get a new JWT even though user is logged out
        5- Remove valid token from authentication manager (I like this one)
        */
//    }
}
