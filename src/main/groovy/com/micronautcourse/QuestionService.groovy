package com.micronautcourse

import javax.validation.Valid
import javax.validation.constraints.NotNull

interface QuestionService {

    List<Question> findAll()

    void save(@Valid @NotNull Question question)

}