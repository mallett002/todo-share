//package com.task.todoshare.utils;
//
//import com.task.todoshare.dto.TodoDTO;
//import org.apache.commons.lang3.RandomStringUtils;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Random;
//
//public class RandomGenerator {
//
//    Random random;
//
//    public RandomGenerator() {
//        random = new Random();
//    }
//
//    public Long getRandomLong() {
//        return random.nextLong();
//    }
//
//    public String getRandomDate() {
//        int randomAmountOfDays = random.nextInt(10 - 5 + 1) + 5;
//        DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
//        LocalDateTime localDateTime = LocalDateTime.now().plusDays(randomAmountOfDays);
//
//        return localDateTime.format(formatter);
//    }
//
//    public boolean createRandomBool() {
//        return random.nextBoolean();
//    }
//
//    public String createRandomString() {
//        return RandomStringUtils.random(15, true, true);
//    }
//
//    public TodoDTO createNewTodo(String newTodoMessage) {
//        TodoDTO body = new TodoDTO();
//        body.setMessage(newTodoMessage);
//        body.setCompleted(createRandomBool());
//        body.setUserId(createRandomString());
//        body.setPrivate(createRandomBool());
//        body.setDueDate(getRandomDate());
//
//        return body;
//    }
//
//    public TodoDTO buildNewTodoDTO() {
//        Long id = 1L;
//        String message = createRandomString();
//
//        TodoDTO todoDTO = createNewTodo(message);
//        todoDTO.setId(id);
//
//        return todoDTO;
//    }
//}
