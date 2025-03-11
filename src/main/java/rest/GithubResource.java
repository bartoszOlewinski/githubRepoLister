package rest;

import client.GithubService;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import response.RepositorySummaryResponse;
import response.github.BranchResponse;
import response.github.RepositoryResponse;

import service.BranchService;
import service.RepositoryService;

import java.util.List;



@Path("/github")
public class GithubResource {

    @Inject
    @RestClient
    GithubService githubService;

    private static final Logger log = LoggerFactory.getLogger(GithubResource.class);

    BranchService branchService = new BranchService();
    RepositoryService repositoryService = new RepositoryService();

    @GET
    @Path("/users/{login}/repos/TEST")
    public List<RepositoryResponse> getRepos(@PathParam("login") String login) {
        return repositoryService.getRepositoriesByLogin(login, githubService);
    }

    @GET
    @Path("/repos/{login}/{repository}/branches/TEST")
    public List<BranchResponse> getBranches(@PathParam("login") String login, @PathParam("repository") String repo) {
        return branchService.getBranchesByLoginAndRepo(login, repo, githubService);
    }


    @GET
    @Blocking
    @Path("/repositories/{login}")
    public Uni<List<RepositorySummaryResponse>> getRepositories(String login) throws WebApplicationException {
        return Uni.createFrom().item(repositoryService.getSummaryResponse(login, githubService));
    }


}
