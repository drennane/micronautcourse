package com.micronautcourse

import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.validation.Valid
import javax.validation.constraints.NotNull

@Singleton
class DefaultQuestionService implements QuestionService {


    private static final Logger LOG = LoggerFactory.getLogger(DefaultQuestionService.class)

    @Override
    public List<Question> findAll() {
        return Collections.singletonList(new Question("What will you be working on this week?"))
    }

    @Override
    void save(@Valid @NotNull Question question) {
        LOG.info("saving question with title: {}", question.title)
    }
}
