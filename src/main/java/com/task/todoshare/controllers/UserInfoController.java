package com.task.todoshare.controllers;

import com.task.todoshare.dto.UserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;

    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody UserInfoDTO userInfoDTO, UriComponentsBuilder builder) {
        // TODO: Create UserInfoService class
        UserInfoDTO response = userInfoService.createUser(userInfoDTO);

        UriComponents uriComponents = builder.path("/user").build();

        return ResponseEntity.created(uriComponents.toUri()).body(response);
    }

}
