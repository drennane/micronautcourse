package com.micronautcourse

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.client.annotation.Client;

import java.util.List;

@Client(id = "github")
@Header(name = HttpHeaders.USER_AGENT, value = "Micronaut Essentials")
public interface GithubClient {

    @Get("/repos/micronaut-projects/micronaut-security/releases")
    List<GithubRelease> releases();

}