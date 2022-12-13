package com.micronautcourse

import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import javax.validation.ConstraintViolationException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest
class QuestionSaveTest {

    @Inject
    QuestionClient questionClient;

    @Test
    void doAPostWithADeclarativeHttpClient() {
        Question q = new Question("What will you be working on this week?");
        HttpStatus status = questionClient.save(q);
        assertEquals(HttpStatus.CREATED, status);

        Executable e = () -> questionClient.save(null);
        assertThrows(ConstraintViolationException.class, e);

        e = () -> questionClient.save(new Question("abc"));
        //then: for spock
        //thrown(HttpClientResponseException)
        HttpClientResponseException thrown = assertThrows(HttpClientResponseException.class, e);
        assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatus());
        Optional<String> jsonOptional = thrown.getResponse().getBody(String.class);
        assertTrue(jsonOptional.isPresent());
        assertTrue(jsonOptional.get().contains("question.title: size must be between"));
    }
}
