package rest;

import client.GithubService;
import entity.Branch;
import entity.Repository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Set;

@Path("/github")
public class GithubResource {

    private String currentLogin;

    @Inject
    @RestClient
    GithubService githubService;

    @GET
    @Path("/users/{login}/repos")
    public Set<Repository> getRepos(String login) {
        return githubService.getByLogin(login);
        //return githubService.getByLogin("bartoszOlewinski");
    }

    /*
    @GET
    @Path("/repos/{username}/{repository}/branches")
    public Set<Branch> getBranches(String login, String repo) {
        return githubService.getByRepo(login, repo);
    }

     */
}
