package com.micronautcourse

import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Status
import jakarta.inject.Inject
import org.slf4j.LoggerFactory

import javax.validation.Valid
import java.util.logging.Logger

@Controller
class QuestionController {

    @Inject
    private final QuestionService questionService

    QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // By default Micronaut assumes you want to produce JSON
    // and it assumes you want to respond a 200 OK
    @Get("/question")
    List<Question> index() {
        return questionService.findAll();
    }

    @Post("/question")
    @Status(HttpStatus.CREATED)
    void saveQuestion(@Body @Valid Question question) {
        questionService.save(question);
    }

    @Get("/question/nocontent")
    HttpStatus noContent() {
        return HttpStatus.NO_CONTENT;
    }
}
