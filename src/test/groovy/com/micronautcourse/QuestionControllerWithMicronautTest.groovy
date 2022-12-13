package com.micronautcourse

import io.micronaut.context.ApplicationContext;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest
class QuestionControllerWithMicronautTest {
    @Inject
    @Client("/")
    HttpClient httpClient;

    @Test
    void startTheEmbeddedServerWithoutMicronautTest() {
        BlockingHttpClient client = httpClient.toBlocking();

        HttpResponse<List<Question>> httpResponse = client.exchange(HttpRequest.GET("/question"), Argument.listOf(Question.class));
        assertEquals(HttpStatus.OK, httpResponse.getStatus());
        Optional<List<Question>> optionalQuestions = httpResponse.getBody();
        assertTrue(optionalQuestions.isPresent());
        List<Question> questions = optionalQuestions.get();
        assertEquals(1, questions.size());
        assertNotNull(questions.get(0));

        questions = client.retrieve(HttpRequest.GET("/question"), Argument.listOf(Question.class));
        assertEquals(1, questions.size());
        assertNotNull(questions.get(0));
    }
}