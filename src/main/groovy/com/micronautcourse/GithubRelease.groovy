package com.micronautcourse

import io.micronaut.core.annotation.Introspected;

@Introspected
public class GithubRelease {

    private final String name;

    public GithubRelease(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}