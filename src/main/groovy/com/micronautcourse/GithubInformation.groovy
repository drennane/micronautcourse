package com.micronautcourse

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.Min;

@Introspected
public class GithubInformation {

    @Min(0L)
    private final Integer numberOfRepositories;

    public GithubInformation(Integer numberOfRepositories) {
        this.numberOfRepositories = numberOfRepositories;
    }

    public Integer getNumberOfRepositories() {
        return numberOfRepositories;
    }
}