package com.micronautcourse

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Client("/")
public interface QuestionClient {
    @Post("/question")
    HttpStatus save(@NotNull @Body Question question);
}