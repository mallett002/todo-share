package com.task.todoshare.controllers;

import com.task.todoshare.dto.UserInfoDTO;
import com.task.todoshare.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;

    @PostMapping(value = "/create-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserInfoDTO> createUser(@RequestBody UserInfoDTO userInfoDTO, UriComponentsBuilder builder) {
        UserInfoDTO response = userInfoService.createUser(userInfoDTO);

        UriComponents uriComponents = builder.path("/create-user").build();

        return ResponseEntity.created(uriComponents.toUri()).body(response);
    }

    @GetMapping(value = "/log-out")
    public HttpServletResponse logUserOut(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : request.getCookies()) {
                String cookieName = cookie.getName();
                Cookie cookieToDelete = new Cookie(cookieName, null);
                cookieToDelete.setMaxAge(0);
                response.addCookie(cookieToDelete);
            }
        }

        return response;
    }
}
