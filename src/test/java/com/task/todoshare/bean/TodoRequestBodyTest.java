package com.task.todoshare.bean;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

public class TodoRequestBodyTest {

    TodoRequestBody todoRequestBody;

    Random random = new Random();

    @BeforeEach
    void populateFields() {
        String message = RandomStringUtils.random(15, true, false);
        Boolean isComleted = random.nextBoolean();
        String userId = RandomStringUtils.random(15, true, true);
        Boolean isPrivate = random.nextBoolean();
        String dueDate = generateRandomDate();

        todoRequestBody = new TodoRequestBody();
        todoRequestBody.setCompleted(isComleted);
        todoRequestBody.setDueDate(dueDate);
        todoRequestBody.setMessage(message);
        todoRequestBody.setPrivate(isPrivate);
        todoRequestBody.setUserId(userId);
    }

    @Test
    public void shouldHaveAllFieldMembers() throws Exception {
        // How to make assertions?
    }

    String generateRandomDate() {
        int randomAmountOfDays = random.nextInt(10 - 5 + 1) + 5;
        DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(randomAmountOfDays);

        return localDateTime.format(formatter);
    }
}
