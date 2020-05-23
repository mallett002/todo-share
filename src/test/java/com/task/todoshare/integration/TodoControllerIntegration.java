//package com.task.todoshare.integration;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import com.task.todoshare.dto.AuthenticationRequest;
//import com.task.todoshare.dto.AuthenticationResponse;
//import com.task.todoshare.dto.TodoDTO;
//import com.task.todoshare.utils.RandomGenerator;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.*;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Objects;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class TodoControllerIntegration {
//    RandomGenerator randomGenerator = new RandomGenerator();
//    String accessToken;
//
//    @LocalServerPort
//    private static int port;
//
//    @Autowired
//    private static RestTemplate restTemplate;
//
//    @BeforeAll
//    public void getAuthToken() {
//        try {
//            AuthenticationRequest authBody = new AuthenticationRequest("foo", "foo");
//            HttpHeaders authHeaders = new HttpHeaders();
//            authHeaders.add("Content-type", "application/json");
//            HttpEntity<AuthenticationRequest> authRequest = new HttpEntity<>(authBody, authHeaders);
//
//            ResponseEntity<AuthenticationResponse> authResponse = restTemplate.exchange(
//                    createURLWithPort("/authenticate"),
//                    HttpMethod.POST,
//                    authRequest,
//                    AuthenticationResponse.class
//            );
//
//            accessToken = authResponse.getBody().getJwt();
//
//            System.out.println("accessToken" + ": " + accessToken);
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//    }
//
//    @Test
//    public void shouldCreateTodo() throws Exception {
//        TodoDTO body = randomGenerator.createNewTodo("This is a test todo");
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", accessToken);
//        HttpEntity<TodoDTO> request = new HttpEntity<>(body, headers);
//
//        System.out.println("accessToken" + ": " + accessToken); // This is null... Need to use java.util.concurrent?
//
//        ResponseEntity<TodoDTO> response = restTemplate.exchange(
//                createURLWithPort("/todos"),
//                HttpMethod.POST,
//                request,
//                TodoDTO.class
//        );
//
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertEquals(body.getMessage(), Objects.requireNonNull(response.getBody()).getMessage());
//    }
//
//    private static String createURLWithPort(String uri) {
//        return "http://localhost:" + port + uri;
//    }
//
//}
