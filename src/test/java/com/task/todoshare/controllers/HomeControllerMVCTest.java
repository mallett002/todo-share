//package com.task.todoshare.controllers;
//
//import com.task.todoshare.services.GreetingService;
//import com.task.todoshare.services.JwtUserDetailsService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.hamcrest.Matchers.containsString;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(HomeController.class)
//public class HomeControllerMVCTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private JwtUserDetailsService jwtUserDetailsService;
//
//    @MockBean
//    private GreetingService service;
//
//    @Test
//    @WithMockUser
//    public void shouldReturnDefaultMessage() throws Exception {
//        String greeting = "Hello world!";
//
//        when(service.greet())
//            .thenReturn(greeting);
//
//        mockMvc.perform(get("/"))
//            .andDo(print())
//            .andExpect(status().isOk())
//            .andExpect(content().string(containsString(greeting)));
//    }
//}
