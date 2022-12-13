package com.micronautcourse

import io.micronaut.core.annotation.Introspected

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
class Question {


    @Size(min = 5)
    @NotBlank
    private final String title

    Question(String title) {
        this.title = title
    }

    String getTitle() {
        return title
    }

}
