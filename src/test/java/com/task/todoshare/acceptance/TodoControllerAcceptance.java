package com.task.todoshare.acceptance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.task.todoshare.dto.AuthenticationRequest;
import com.task.todoshare.dto.AuthenticationResponse;
import com.task.todoshare.dto.TodoDTO;
import com.task.todoshare.utils.RandomGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodoControllerAcceptance {
    String createTodoEndpoint = "/todos";

    @LocalServerPort
    private static int port;

    @Autowired
    private static TestRestTemplate restTemplate;

    RandomGenerator randomGenerator = new RandomGenerator();
    HttpHeaders headers = new HttpHeaders();
    static HttpHeaders authHeaders = new HttpHeaders();

    @BeforeAll
    private static void authenticate() {
        AuthenticationRequest request = new AuthenticationRequest("foo", "foo");
        HttpEntity<AuthenticationRequest> entity = new HttpEntity<>(request, authHeaders);
        ResponseEntity<AuthenticationResponse> response = restTemplate.exchange(
                createURLWithPort("/authenticate"),
                HttpMethod.POST,
                entity,
                AuthenticationResponse.class
        );

        System.out.println(response.getBody());
    }

    @Test
    public void shouldCreateTodo() throws Exception {
        TodoDTO body = randomGenerator.createNewTodo("This is a test todo");
        HttpEntity<TodoDTO> entity = new HttpEntity<>(body, headers);

        ResponseEntity<TodoDTO> response = restTemplate.exchange(
            createURLWithPort(createTodoEndpoint),
            HttpMethod.POST,
            entity,
            TodoDTO.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(body.getMessage(), Objects.requireNonNull(response.getBody()).getMessage());
    }

    private static String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
