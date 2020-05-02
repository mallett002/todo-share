package com.task.todoshare.acceptance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.task.todoshare.requestBody.TodoRequestBody;
import com.task.todoshare.responseBody.TodoCreationResponse;
import com.task.todoshare.utils.RandomGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodoControllerAcceptance {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    RandomGenerator randomGenerator = new RandomGenerator();
    HttpHeaders headers = new HttpHeaders();

    String createTodoEndpoint = "/todo/create";

    @Test
    public void shouldCreateTodo() throws Exception {
        TodoRequestBody body = randomGenerator.createNewTodo("This is a test todo");
        HttpEntity<TodoRequestBody> entity = new HttpEntity<>(body, headers);

        ResponseEntity<TodoCreationResponse> response = restTemplate.exchange(
            createURLWithPort(createTodoEndpoint),
            HttpMethod.POST,
            entity,
            TodoCreationResponse.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(body.getMessage(), Objects.requireNonNull(response.getBody()).getMessage());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
