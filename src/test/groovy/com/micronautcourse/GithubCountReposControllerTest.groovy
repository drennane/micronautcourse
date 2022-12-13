package com.micronautcourse

import io.micronaut.context.ApplicationContext;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class GithubCountReposControllerTest {

    @Test
    void mockGithubApi() {
        try (EmbeddedServer githubApi = ApplicationContext.run(EmbeddedServer.class, Collections.singletonMap("spec.name",
                "mockgithub"))) {
            Map<String, Object> props = Collections.singletonMap("micronaut.http.services.github.url",
                    "http://localhost:" + githubApi.getPort());
            try (EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer.class, props)){
                HttpClient httpClient = embeddedServer.getApplicationContext().createBean(HttpClient.class, embeddedServer.getURL());
                BlockingHttpClient client = httpClient.toBlocking();
                HttpResponse<GithubInformation> response = client.exchange("/github", GithubInformation.class);
                assertEquals(HttpStatus.OK, response.getStatus());
                Optional<GithubInformation> githubInformationOptional = response.getBody();
                assertTrue(githubInformationOptional.isPresent());
                assertEquals(0, githubInformationOptional.get().getNumberOfRepositories());
            }
        }
    }

    @Requires(property = "spec.name", value = "mockgithub")
    @Controller
    static class GithubReleaseMock {
        @Get("/repos/micronaut-projects/micronaut-security/releases")
        String index() {
            return "[]";
        }
    }

}