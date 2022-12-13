package com.micronautcourse

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

import java.util.List;

@Controller
public class GithubCountReposController {

    private final GithubClient githubClient;

    public GithubCountReposController(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    @ExecuteOn(TaskExecutors.IO)
    @Get("/github")
    GithubInformation info() {
        List<GithubRelease> json = githubClient.releases();
        return new GithubInformation(json.size());
    }
}